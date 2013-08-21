//TODO:  cleanup to get rid of junk left from aces3
//TODO:  fix to handle subindices

/* Index table format in .siox file
     <IndexTable> ::= num_entries (<String> <IndexTableEntry>)*
     <IndexTableEntry> ::= <bseg> <eseg> <index_type>
     <bseg> ::= int
     <eseg> ::= int
     <index_type> ::= int
*/
package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sial.io.SIADataInput;
import sial.io.SIADataOutput;
import sial.parser.SialParsersym;
import sial.parser.Ast.ArrayDec;
import sial.parser.Ast.IDec;
import sial.parser.Ast.IndexDec;
import sial.parser.Ast.ScalarDec;
import sial.parser.Ast.Sial;
import sial.parser.Ast.SubIndexDec;
import sial.parser.context.ASTUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/*index table entry.  Entries other than bseg, eseg, and index_type are 0.
 typedef struct {
 f_int index_size;
 f_int nsegments;
 f_int current_seg;
 f_int bseg;         //Index into scalarTable or intTable of lower bound.  
 //If this entry is a subindex, bseg is the index of the superindex
 f_int eseg;         //Index into scalarTable or intTable of upper bound 
 f_int index_type;   //also set in compiler
 f_int next_seg;
 f_int subindex_ptr;  //will be set at runtime
 } index_table_entry_t;
 */
public class IndexTable implements SipConstants, SialParsersym {

	static class Entry {
		int index_size;
		int nsegments;
		int current_seg;
		int bseg;
		int eseg;
		int index_type;
		int next_seg;
		int subindex_ptr;

		Entry(int bseg, int eseg, int index_type) {
			this.bseg = bseg;
			this.eseg = eseg;
			this.index_type = index_type;
		}

		Entry() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + bseg;
			result = prime * result + eseg;
			result = prime * result + index_type;
			result = prime * result + subindex_ptr;
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Entry))
				return false;
			Entry other = (Entry) obj;
			if (bseg != other.bseg)
				return false;
			if (eseg != other.eseg)
				return false;
			if (index_type != other.index_type)
				return false;
			if (subindex_ptr != other.subindex_ptr)
				return false;
			return true;
		}

		public void read(DataInput input) throws IOException {
			// index_size = input.readInt();
			// nsegments = input.readInt();
			// current_seg = input.readInt();
			bseg = input.readInt();
			eseg = input.readInt();
			index_type = input.readInt();
			// next_seg = input.readInt();
			// subindex_ptr= input.readInt();
		}

		public void readExpanded(DataInput input) throws IOException {
			index_size = input.readInt();
			nsegments = input.readInt();
			current_seg = input.readInt();
			bseg = input.readInt();
			eseg = input.readInt();
			index_type = input.readInt();
			next_seg = input.readInt();
			subindex_ptr = input.readInt();
		}

		// @SuppressWarnings("unused")
		// public void readExpanded(DataInput input) throws IOException{
		// int index_size = input.readInt();
		// int nsegments = input.readInt();
		// int current_seg = input.readInt();
		// bseg = input.readInt();
		// eseg = input.readInt();
		// index_type = input.readInt();
		// int next_seg = input.readInt();
		// int subindex_ptr= input.readInt();
		// }

//		public void write(DataOutput output) throws IOException {
//			// output.writeInt(0 /*index_size*/);
//			// output.writeInt(0 /*nsegments*/);
//			// output.writeInt(0 /*current_seg*/);
//			output.writeInt(bseg);
//			output.writeInt(eseg);
//			output.writeInt(index_type);
//			// output.writeInt(0 /*next_seg*/);
//			// output.writeInt(0 /*subindex_ptr*/);
//		}
		public void write(DataOutput output) throws IOException {
		output.writeInt(bseg); //value given in sial program, may be symbolic predefined
		output.writeInt(eseg); //value in sial program, may be symbolic predefined
		output.writeInt(index_type);
		// output.writeInt(0 /*subindex_ptr*/);
	}		
		

//		public void writeExpanded(DataOutput output) throws IOException {
//			output.writeInt(0 /* index_size */);
//			output.writeInt(0 /* nsegments */);
//			output.writeInt(0 /* current_seg */);
//			output.writeInt(bseg);
//			output.writeInt(eseg);
//			output.writeInt(index_type);
//			output.writeInt(0 /* next_seg */);
//			output.writeInt(0 /* subindex_ptr */);
//		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(bseg);
			sb.append(',');
			sb.append(eseg);
			sb.append(',');
			sb.append(index_type);
			// sb.append(',');
			// sb.append(0 /*subindex_ptr*/);
			sb.append('\n');
			return sb.toString();
		}

	} // end of class Entry

	int num_entries; // next entry in index table, also the current number
							// of
							// entries
	HashBiMap<IDec, Integer> indexBiMap; // maps index dec to location in index
											// table
	List<Entry> entries; // the index table contents
	
	List<String> symbols; //list of symbols, only used when reading a .siox file 

	public IndexTable() {
		num_entries = 0;
		indexBiMap = HashBiMap.create();
		entries = new ArrayList<Entry>();
	}

	// used for reading from existing sio file
	public IndexTable(int mx_nindex_table) {
		entries = new ArrayList<Entry>(mx_nindex_table);
		num_entries = 0;
		symbols = new ArrayList<String>();		
	}

	public Entry readEntry(DataInput input) throws IOException {
		Entry entry = new Entry();
		entry.read(input);
		return entry;
	}
	
	public static Entry read(DataInput input) throws IOException {
		Entry entry = new Entry();
		entry.read(input);
		return entry;
	}

	// returns a string representation of the index table
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(num_entries);
		sb.append('\n');
		for (int i = 0; i != num_entries; ++i) {
			sb.append(entries.get(i).toString());
		}
		if (symbols != null){
			for (int i = 0; i != num_entries; ++i){
				sb.append(symbols.get(i));
				sb.append('\n');
			}
		}
		return sb.toString();
	}


	public int getIndex(IDec dec) {
		assert (dec instanceof IndexDec || dec instanceof SubIndexDec) : "internal error:  attempting to get non-index from index table";
		return indexBiMap.get(dec);
	}

	public int getFortranIndex(IDec dec) {
		assert (dec instanceof IndexDec || dec instanceof SubIndexDec) : "internal error:  attempting to get non-index from index table";
		return getIndex(dec) + 1;
	}

	int addEntry(IDec dec, int beg, int end, int type) {
		int index = num_entries++;
		indexBiMap.put(dec, index);
		Entry entry = new Entry(beg, end, type);
		int slot = entries.size();
		entries.add(entry);
		System.out.println("In IndexTable.addEntry:  " + dec.toString() + " index " + index + " slot=" + slot);                                                                                             
		return index;
	}
	
	public void write(SIADataOutput output) throws IOException { 
		output.writeInt(num_entries);
		BiMap<Integer, IDec> inverse = indexBiMap.inverse();
		for (int i = 0; i != num_entries; i++) {
//			String name = ((IndexDec)indexBiMap.inverse().get(i)).getName();
			IDec dec = inverse.get(i);
			String name = ASTUtils.getQualifiedName(dec);	
			output.writeString(name);
			entries.get(i).write(output);
		}
	}

	/*
	 * reads the index table with n entries from a DataInputStream. This is used
	 * for debugging 
	 */
	public static IndexTable readIndexTable(SIADataInput input)
			throws IOException {
		int nentries = input.readInt();
		IndexTable table = new IndexTable(nentries);
		for (int i = 0; i < nentries; i++) {
			String name = input.readString();
			table.symbols.add(name);
			Entry entry = read(input);
			table.entries.add(entry);
			table.num_entries++;
		}
		return table;
	}


	public boolean equalVals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof IndexTable))
			return false;
		IndexTable o = (IndexTable) other;
		if (this.num_entries != o.num_entries)
			return false;
		return entries.equals(o.entries);
	}

	public void writeSymbols(SIADataOutput output) throws IOException {
		output.writeInt(num_entries);
		BiMap<Integer, IDec> inverse = indexBiMap.inverse();
		for (int i = 0; i != num_entries; i++) {
			IDec dec = inverse.get(i);
			String name = ASTUtils.getQualifiedName(dec);
			output.writeString(name);
		}
	}

	public String symbolsToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(num_entries);
		sb.append('\n');
		BiMap<Integer, IDec> inverse = indexBiMap.inverse();
		for (int i = 0; i != num_entries; i++) {
			IDec dec = inverse.get(i);
			String name = ASTUtils.getQualifiedName(dec);
			sb.append(name);
			sb.append('\n');
		}
		return sb.toString();
	}

	public String symbolsFromInputFileToString(){
		if (symbols == null) return "no index symbols";
		StringBuilder sb = new StringBuilder();
		int nsymbols = symbols.size();
		sb.append(nsymbols);
		sb.append('\n');
		for (int i = 0; i < nsymbols; ++i){
			sb.append(symbols.get(i));
			sb.append('\n');
		}
		return sb.toString();
	}
	public void readSymbols(SIADataInput input) throws IOException {
		int nsymbols = input.readInt();
		symbols = new ArrayList(nsymbols);
		for (int i = 0; i < nsymbols; i++){
			symbols.add(input.readString());
		}
	}
}
