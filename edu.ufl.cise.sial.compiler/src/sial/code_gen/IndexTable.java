/**  Represent the Index table.  
 * 
 * Index table format in .siox file:
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


public class IndexTable implements SipConstants, SialParsersym {

	static class Entry {
		int bseg;  //also parent index for subindices
		int eseg;
		int index_type;

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
			return true;
		}

		public void read(DataInput input) throws IOException {
			bseg = input.readInt();
			eseg = input.readInt();
			index_type = input.readInt();
		}


		public void write(DataOutput output) throws IOException {
		output.writeInt(bseg); //value given in sial program, may be symbolic predefined
		output.writeInt(eseg); //value in sial program, may be symbolic predefined
		output.writeInt(index_type);
	}		
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(bseg);
			sb.append(',');
			sb.append(eseg);
			sb.append(',');
			sb.append(index_type);
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

	int addEntry(IDec dec, int beg, int end, int type) {
		int index = num_entries++;
		indexBiMap.put(dec, index);
		Entry entry = new Entry(beg, end, type);
		entries.add(entry);                                                                                          
		return index;
	}
	
	public void write(SIADataOutput output) throws IOException { 
		output.writeInt(num_entries);
		BiMap<Integer, IDec> inverse = indexBiMap.inverse();
		for (int i = 0; i != num_entries; i++) {
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


	public void readSymbols(SIADataInput input) throws IOException {
		int nsymbols = input.readInt();
		symbols = new ArrayList(nsymbols);
		for (int i = 0; i < nsymbols; i++){
			symbols.add(input.readString());
		}
	}
}
