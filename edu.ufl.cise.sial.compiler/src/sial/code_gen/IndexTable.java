//TODO:  fix to reflect fact that only bseg, eseg, and index_type are nonzero.  

package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	class Entry {
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

		public void write(DataOutput output) throws IOException {
			// output.writeInt(0 /*index_size*/);
			// output.writeInt(0 /*nsegments*/);
			// output.writeInt(0 /*current_seg*/);
			output.writeInt(bseg);
			output.writeInt(eseg);
			output.writeInt(index_type);
			// output.writeInt(0 /*next_seg*/);
			// output.writeInt(0 /*subindex_ptr*/);
		}

		public void writeExpanded(DataOutput output) throws IOException {
			output.writeInt(0 /* index_size */);
			output.writeInt(0 /* nsegments */);
			output.writeInt(0 /* current_seg */);
			output.writeInt(bseg);
			output.writeInt(eseg);
			output.writeInt(index_type);
			output.writeInt(0 /* next_seg */);
			output.writeInt(0 /* subindex_ptr */);
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			// sb.append(0 /*index_size*/); sb.append(',');
			// sb.append(0 /*nsegments*/); sb.append(',');
			// sb.append(0 /*current_seg*/); sb.append(',');
			sb.append(bseg);
			sb.append(',');
			sb.append(eseg);
			sb.append(',');
			sb.append(index_type);
			// sb.append(',');
			// sb.append(0 /*next_seg*/); sb.append(',');
			// sb.append(0 /*subindex_ptr*/);
			sb.append('\n');
			return sb.toString();
		}

		public String toStringExpanded() {
			StringBuilder sb = new StringBuilder();
			sb.append(0 /* index_size */);
			sb.append(',');
			sb.append(0 /* nsegments */);
			sb.append(',');
			sb.append(0 /* current_seg */);
			sb.append(',');
			sb.append(bseg);
			sb.append(',');
			sb.append(eseg);
			sb.append(',');
			sb.append(index_type);
			sb.append(',');
			sb.append(0 /* next_seg */);
			sb.append(',');
			sb.append(0 /* subindex_ptr */);
			sb.append('\n');
			return sb.toString();
		}

		// private IndexTable getOuterType() {
		// return IndexTable.this;
		// }

		// public boolean equals(Object other){
		// if (this == other) return true;
		// if (! (other instanceof Entry)) return false;
		// Entry o = (Entry)other;
		// return index_size == o.index_size &&
		// nsegments == o.nsegments &&
		// current_seg == o.current_seg &&
		// bseg == o.bseg &&
		// eseg == o.eseg &&
		// index_type == o.index_type &&
		// next_seg == o.next_seg &&
		// subindex_ptr == o.subindex_ptr;
		// // return bseg == o.bseg && eseg == o.eseg && index_type ==
		// o.index_type;
		// }

		// public String toReadableString(){
		// StringBuilder sb = new StringBuilder();
		// sb.append(AcesHacks.getTypeName(index_type));
		// sb.append("=");
		// //if bseg (or eseg) <= 0, this is a symbolic constant
		// //if there is a scalar Table, look up its name and print the name if
		// found.
		// //otherwise print the int value
		// sb.append(bseg <= 0 && scalarTable != null &&
		// scalarTable.getIntName(bseg) != null
		// ? scalarTable.getIntName(bseg)
		// : bseg );
		// sb.append(",");
		// sb.append(eseg <= 0 && scalarTable != null &&
		// scalarTable.getIntName(eseg) != null
		// ? scalarTable.getIntName(eseg)
		// : eseg );
		// return sb.toString();
		// }
	} // end of class Entry

	int n_index_table_sip; // next entry in index table, also the current number
							// of
							// entries
	HashBiMap<IDec, Integer> indexBiMap; // maps index dec to location in index
											// table
	List<Entry> entries; // the index table contents

	public IndexTable() {
		n_index_table_sip = 0;
		indexBiMap = HashBiMap.create();
		entries = new ArrayList<Entry>();
	}

	// used for reading from existing sio file
	public IndexTable(int mx_nindex_table) {
		entries = new ArrayList<Entry>(mx_nindex_table);
		n_index_table_sip = 0;
	}

	public Entry readEntry(DataInput input) throws IOException {
		Entry entry = new Entry();
		entry.read(input);
		return entry;
	}

	// returns a string representation of the index table
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i != n_index_table_sip; i++) {
			sb.append(entries.get(i).toString());
		}
		return sb.toString();
	}

	// public int getIndex(IndexDec dec) {
	// return indexBiMap.get(dec);
	// }
	//
	// public int getFortranIndex(IndexDec dec) {
	// return getIndex(dec) + 1;
	// }

	public int getIndex(IDec dec) {
		assert (dec instanceof IndexDec || dec instanceof SubIndexDec) : "internal error:  attempting to get non-index from index table";
		return indexBiMap.get(dec);
	}

	public int getFortranIndex(IDec dec) {
		assert (dec instanceof IndexDec || dec instanceof SubIndexDec) : "internal error:  attempting to get non-index from index table";
		return getIndex(dec) + 1;
	}

	int addEntry(IDec dec, int beg, int end, int type) {
		int index = n_index_table_sip++;
		indexBiMap.put(dec, index);
		Entry entry = new Entry(beg, end, type);
		entries.add(entry);
		return index;
	}

	public void write(DataOutput output) throws IOException {
		for (int i = 0; i != n_index_table_sip; i++) {
			entries.get(i).write(output);
		}
	}

	/*
	 * reads the index table with n entries from a DataInputStream. This is used
	 * for debugging and reverse engineering of the original compiler
	 */
	public static IndexTable readIndexTable(int nentries, DataInput input)
			throws IOException {
		IndexTable table = new IndexTable(nentries);
		table.read(nentries, input);
		return table;
	}

	public void read(int nentries, DataInput input) throws IOException {
		for (int i = 0; i < nentries; i++) {
			Entry entry = readEntry(input);
			entries.add(entry);
			n_index_table_sip++;
		}
	}

	public boolean equalVals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof IndexTable))
			return false;
		IndexTable o = (IndexTable) other;
		if (this.n_index_table_sip != o.n_index_table_sip)
			return false;
		return entries.equals(o.entries);
	}

	public void writeSymbols(SIADataOutput output) throws IOException {
		output.writeInt(n_index_table_sip);
		BiMap<Integer, IDec> inverse = indexBiMap.inverse();
		for (int i = 0; i != n_index_table_sip; i++) {
			IDec dec = inverse.get(i);
			String name = ((IndexDec) dec).getName();
			output.writeString(name);
		}
	}

	public String symbolsToString() {
		StringBuilder sb = new StringBuilder();
		sb.append(n_index_table_sip);
		sb.append('\n');
		BiMap<Integer, IDec> inverse = indexBiMap.inverse();
		for (int i = 0; i != n_index_table_sip; i++) {
			IDec dec = inverse.get(i);
			Sial declaringProgram = ASTUtils.getRoot(dec);
			if (declaringProgram.isImported()){  //defined in imported file, prefix name with qualifier, the program name
				String progName = declaringProgram.getProgram().getStartName();
				sb.append(progName);
				sb.append('.');
			}
			String name = ((IndexDec) dec).getName();
			sb.append(name);
			sb.append('\n');
		}
		return sb.toString();
	}
}
