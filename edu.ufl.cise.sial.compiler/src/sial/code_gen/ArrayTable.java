//TODO  int table not in current runtime.  Can also modify to reflect 

package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import sial.io.SIADataInput;
import sial.io.SIADataOutput;
import sial.parser.Ast.ArrayDec;
import sial.parser.Ast.IDec;
import sial.parser.Ast.ScalarDec;
import sial.parser.context.ASTUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/*  Structure of the array table.  Only nindex, array_type, index_array, scalar_index are set at compile time.
 typedef struct {
 f_int nindex;     //dimension of array
 f_int array_type; //type: scalar, int, served, distributed, etc.  Values in SipTypeConstants
 f_int numblks;
 f_int index_array[mx_array_index];  //each intry contains the index in the index array of the
 //corresponding index
 f_int index_range1[mx_array_index]; //in fortran, this is also index_original
 f_int index_range2[mx_array_index];
 f_int block_map;
 f_int scalar_index;         //if scalar, nindex = 0 and this is index into the scalar
 f_int create_flag;
 f_int put_flag;
 f_int prepare_flag;
 f_int current_blkndx;
 f_int block_list;
 f_int array_stack;
 f_int array_status;
 } array_table_entry_t;
 */

public class ArrayTable implements SipConstants {

	static public class Entry {
		int nindex; // dimension of array
		int array_type; // type: scalar, int, served, distributed, etc. Values
						// in SipTypeConstants
		int numblks;
		int[] index_array; // each element contains the fortran index
							// in the index array of the corresponding index
		int[] index_range1; // in fortran, this is also index_original
		int[] index_range2;
		int block_map;
		int scalar_index; // if scalar or int, this is index into the scalar,
							// int table
		int create_flag;
		int put_flag;
		int prepare_flag;
		int current_blkndx;
		int block_list;
		int array_stack;
		int array_status;
		
//		@Override
//		public boolean equals(Object other){
//			if (this == other) return true;
//			if (!(other instanceof Entry)) return false;
//			Entry o = (Entry)other;
//			//Only nindex, array_type, index_array, scalar_index are set at compile time.
//			return nindex == o.nindex &&
//				   array_type == o.array_type &&
//			       Arrays.equals(index_array, o.index_array) &&
//			       scalar_index == o.scalar_index;		
//		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + array_type;
			result = prime * result + Arrays.hashCode(index_array);
			result = prime * result + nindex;
			result = prime * result + scalar_index;
			return result;
		}


		/* (non-Javadoc)
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
			if (array_type != other.array_type)
				return false;
			if (!Arrays.equals(index_array, other.index_array))
				return false;
			if (nindex != other.nindex)
				return false;
			if (scalar_index != other.scalar_index)
				return false;
			return true;
		}


		Entry(int max_array_index, int arraynindex, int type, int[] indarray,
				int scalarIndex) {
			this.index_array = new int[max_array_index];
			this.index_range1 = new int[max_array_index];
			this.index_range2 = new int[max_array_index];
			this.nindex = arraynindex;
			this.array_type = type;
			this.scalar_index = scalarIndex;
			int i;
			if (indarray == null) {  //item is scalar or int
				i = 0;
			} else { //copy given elements into index_array
				for (i = 0; i != indarray.length; i++) {
					this.index_array[i] = indarray[i];
				}
			}
			//set unused index_array elements to zero
			for (int j = i; j != max_array_index; j++) {
				this.index_array[j] = 0;
			}
		}
		

		protected Entry(int nindex, int array_type, int numblks,
				int[] index_array, int[] index_range1, int[] index_range2,
				int block_map, int scalar_index, int create_flag, int put_flag,
				int prepare_flag, int current_blkndx, int block_list,
				int array_stack, int array_status) {
			super();
			this.nindex = nindex;
			this.array_type = array_type;
			this.numblks = numblks;
			this.index_array = index_array;
			this.index_range1 = index_range1;
			this.index_range2 = index_range2;
			this.block_map = block_map;
			this.scalar_index = scalar_index;
			this.create_flag = create_flag;
			this.put_flag = put_flag;
			this.prepare_flag = prepare_flag;
			this.current_blkndx = current_blkndx;
			this.block_list = block_list;
			this.array_stack = array_stack;
			this.array_status = array_status;
		}

		public Entry() {}

		void read(DataInput in) throws IOException {
			nindex = in.readInt(); // dimension of array
			array_type = in.readInt(); // type: scalar, int, served, distributed, etc. Values
							// in SipTypeConstants
			int max = AcesHacks.max_array_index;
			index_array = new int[max]; // each element contains the fortran index
								// in the index array of the corresponding index
			for(int i = 0; i != max; i++){
				index_array[i] = in.readInt();
			}
			scalar_index = in.readInt(); // if scalar or int, this is index into the scalar,
								// int table
		}
		
		void readExpanded(DataInput in) throws IOException {
			nindex = in.readInt(); // dimension of array
			array_type = in.readInt(); // type: scalar, int, served, distributed, etc. Values
							// in SipTypeConstants
			numblks = in.readInt();
			int max = AcesHacks.max_array_index;
			index_array = new int[max]; // each element contains the fortran index
								// in the index array of the corresponding index
			for(int i = 0; i != max; i++){
				index_array[i] = in.readInt();
			}
			index_range1 = new int[max]; // in fortran, this is also index_original
			for(int i = 0; i != max; i++){
				index_range1[i] = in.readInt();
			}
			index_range2 = new int[max];
			for(int i = 0; i != max; i++){
				index_range2[i] = in.readInt();
			}
			block_map = in.readInt();
			scalar_index = in.readInt(); // if scalar or int, this is index into the scalar,
								// int table
			create_flag = in.readInt();
			put_flag = in.readInt();
			prepare_flag = in.readInt();
			current_blkndx = in.readInt();
			block_list = in.readInt();
			array_stack = in.readInt();
			array_status = in.readInt();
		}
		
		public String toString(){
			int max = AcesHacks.max_array_index;
			StringBuilder sb = new StringBuilder();
			sb.append(max);
			sb.append('\n');
			sb.append(nindex); sb.append(',');
			sb.append(array_type); sb.append(',');
//			sb.append(numblks); sb.append(',');
			
			sb.append('[');
			sb.append(index_array[0]);
			for (int i = 1; i < max; i++){
				sb.append(',');
				sb.append(index_array[i]);
			}
			sb.append("],");
//			sb.append('[');
//			sb.append(index_range1[0]);
//			for (int i = 1; i < max; i++){
//				sb.append(',');
//				sb.append(index_range1[i]);
//			}
//			sb.append("],");
//			sb.append('[');
//			sb.append(index_range2[0]);
//			for (int i = 1; i < max; i++){
//				sb.append(',');
//				sb.append(index_range2[i]);
//			}
//			sb.append("],");
//			sb.append(block_map); sb.append(',');
			sb.append(scalar_index); sb.append(',');
//			sb.append(create_flag); sb.append(',');
//			sb.append(put_flag); sb.append(',');
//			sb.append(prepare_flag); sb.append(',');
//			sb.append(current_blkndx); sb.append(',');
//			sb.append(block_list); sb.append(',');
//			sb.append(array_stack); sb.append(',');
//			sb.append(array_status); 
			return sb.toString();
		}
		
		public String toStringExpanded(){
			int max = AcesHacks.max_array_index;
			StringBuilder sb = new StringBuilder();
			sb.append(nindex); sb.append(',');
			sb.append(array_type); sb.append(',');
			sb.append(numblks); sb.append(',');
			
			sb.append('[');
			sb.append(index_array[0]);
			for (int i = 1; i < max; i++){
				sb.append(',');
				sb.append(index_array[i]);
			}
			sb.append("],");
			sb.append('[');
			sb.append(index_range1[0]);
			for (int i = 1; i < max; i++){
				sb.append(',');
				sb.append(index_range1[i]);
			}
			sb.append("],");
			sb.append('[');
			sb.append(index_range2[0]);
			for (int i = 1; i < max; i++){
				sb.append(',');
				sb.append(index_range2[i]);
			}
			sb.append("],");
			sb.append(block_map); sb.append(',');
			sb.append(scalar_index); sb.append(',');
			sb.append(create_flag); sb.append(',');
			sb.append(put_flag); sb.append(',');
			sb.append(prepare_flag); sb.append(',');
			sb.append(current_blkndx); sb.append(',');
			sb.append(block_list); sb.append(',');
			sb.append(array_stack); sb.append(',');
			sb.append(array_status); 
			return sb.toString();
		}
		public static Entry readEntry(DataInput in) throws IOException{
			Entry entry = new Entry();
			entry.read(in);
			return entry;
		}
		
		public static Entry readExpandedEntry(DataInput in) throws IOException{
			Entry entry = new Entry();
			entry.readExpanded(in);
			return entry;
		}
		void write(DataOutput out) throws IOException {
			out.writeInt(nindex);
			out.writeInt(array_type);
//			out.writeInt(numblks);
			for (int i = 0; i != AcesHacks.max_array_index; i++) {
				out.writeInt(index_array[i]);
			}
//			for (int i = 0; i != AcesHacks.max_array_index; i++) {
//				out.writeInt(index_range1[i]);
//			}
//			for (int i = 0; i != AcesHacks.max_array_index; i++) {
//				out.writeInt(index_range2[i]);
//			}
//			out.writeInt(block_map);
			out.writeInt(scalar_index); // if scalar or int, this is index into
										// the scalar, int table
//			out.writeInt(create_flag);
//			out.writeInt(put_flag);
//			out.writeInt(prepare_flag);
//			out.writeInt(current_blkndx);
//			out.writeInt(block_list);
//			out.writeInt(array_stack);
//			out.writeInt(array_status);
		} 
		
		void writeExpanded(DataOutput out) throws IOException {
			out.writeInt(nindex);
			out.writeInt(array_type);
			out.writeInt(numblks);
			for (int i = 0; i != AcesHacks.max_array_index; i++) {
				out.writeInt(index_array[i]);
			}
			for (int i = 0; i != AcesHacks.max_array_index; i++) {
				out.writeInt(index_range1[i]);
			}
			for (int i = 0; i != AcesHacks.max_array_index; i++) {
				out.writeInt(index_range2[i]);
			}
			out.writeInt(block_map);
			out.writeInt(scalar_index); // if scalar or int, this is index into
										// the scalar, int table
			out.writeInt(create_flag);
			out.writeInt(put_flag);
			out.writeInt(prepare_flag);
			out.writeInt(current_blkndx);
			out.writeInt(block_list);
			out.writeInt(array_stack);
			out.writeInt(array_status);
		} 

	}//end of class entry

	BiMap<IDec, Integer> arrayBiMap; // maps declarations to
									 // index in array table and vice versa
	int nvars; // number of entries in the array table
	ArrayList<Entry> entries;
	ArrayList<String> symbols; //used only to read .siox file


	public ArrayTable() {
		entries = new ArrayList<Entry>();
		arrayBiMap = HashBiMap.create();
	}

	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i != entries.size(); i++) {
			sb.append(entries.get(i).toString());
			sb.append('\n');
		}
		return sb.toString(); 
	}
	
	public String toStringWithFortranIndices(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i != entries.size(); i++) {
			int j = i+1;
			sb.append(j<10 ? j + ":   " : (j<100 ? j + ":  ": (j<1000 ? j+": " : j+":")));
			sb.append(entries.get(i).toString());
			sb.append('\n');
		}
		return sb.toString();

	}



	public int getFortranIndex(IDec dec) {
		return getIndex(dec) + 1;
	}

	public int getIndex(IDec dec) {
		return arrayBiMap.get(dec);
	}



	public int getNIndex(int i) {
		return entries.get(i).nindex;
	}

	public int[] getIndexArray(int i) {
		return entries.get(i).index_array;
	}

	int addScalarEntry(IDec dec, int scalarIndex){
		return addEntry(dec, 0, scalar_value_t, null, scalarIndex);
	}
	
	int addArrayEntry(IDec dec, int arraynindex, int type, int[] indarray) {
		return addEntry(dec, arraynindex, type, indarray, 0);
	}

	int addEntry(IDec dec, int arraynindex, int type, int[] indarray, int scalarIndex) {
		int index = nvars++;
		if (dec != null) arrayBiMap.put(dec, index);
		int max_array_index = AcesHacks.max_array_index;
		Entry entry = new Entry(max_array_index, arraynindex, type, indarray, scalarIndex);
		entries.add(entry);
		return index;
	}
	
	int getNvars(){return nvars;}

	public static ArrayTable readArrayTable(DataInput input) throws IOException {
		ArrayTable arrayTable = new ArrayTable();
		arrayTable.nvars = input.readInt();
		for (int i = 0; i != arrayTable.nvars; i++){
			arrayTable.entries.add(Entry.readEntry(input));
		}
		return arrayTable;
	}
	


	public void write(DataOutput output) throws IOException {
		int size = entries.size();
		assert (size == nvars): "ArrayTable entries.size= "+size + " nvars=" + nvars;
		output.writeInt(size);
		for (int i = 0; i < entries.size(); i++){
			entries.get(i).write(output);
		}
	}
	
	//this will be null if index represents a scalar literal
	public ArrayDec getDec(int index){
		return (ArrayDec) arrayBiMap.inverse().get(index);
	}

	//precondition:  index of literal is in array table
	public int getIndexOfScalarEntry(int scalarTableIndex) {
		//linear search.  Perhaps replace with something better
		int i;
		for (i = 0; i < entries.size() && entries.get(i).scalar_index != scalarTableIndex; i++);
		assert i<entries.size();
		return i;
	}

	public int[] getIndexArrayAt(int identIndex) {
		Entry e = entries.get(identIndex);
		return  e.index_array;
	}
	

	public boolean equalVals(Object other){
		if (this == other) return true;
		if (!(other instanceof ArrayTable))return false;
		ArrayTable o = (ArrayTable)other;
		return nvars == o.nvars && entries.equals(o.entries);
	}
	
	public void writeSymbols(SIADataOutput output) throws IOException {
		output.writeInt(nvars);
		BiMap<Integer,IDec> inverse = arrayBiMap.inverse();

		for (int i = 0; i != nvars; i++){
			IDec dec =  inverse.get(i);
			String name;
//			if (dec == null){ name = ""; }
//			else if (dec instanceof ArrayDec){
//				name = ((ArrayDec)dec).getName();
//			}
//			else if (dec instanceof ScalarDec){
//				name = ((ScalarDec)dec).getName();
//			}
//			else { assert false: "Unexpected IDec type in ArrayTable";}
			if (dec == null){  //scalar literals have a slot in the array table, but no declaration. 
				//Look up their value and create a name.
				int scalar_table_index = entries.get(i).scalar_index - 1; //this is a fortran index
				double scalarValue = ScalarTable.global_scalars.get(scalar_table_index);
				name = "SCALAR_LITERAL_" + scalarValue;
			}
			else name = ASTUtils.getQualifiedName(dec);
		output.writeString(name);	
		}
	}
	
	public void readSymbols(SIADataInput input) throws IOException  {
		int nSymbols = input.readInt();
		symbols = new ArrayList<String>();
		for (int i = 0; i < nSymbols; ++i){
		   symbols.add(input.readString());
		}
	}
	
	public String symbolsFromInputFileToString(){
		if (symbols == null) return "no array symbols";
		StringBuilder sb = new StringBuilder();
		sb.append(symbols.size());
		sb.append('\n');
		for(String s: symbols){
			sb.append(s);
			sb.append('\n');
		}
		return sb.toString();
	}
	public String symbolsToString(){
		StringBuilder sb = new StringBuilder();
		sb.append(nvars);
		sb.append('\n');
		BiMap<Integer,IDec> inverse = arrayBiMap.inverse();

		for (int i = 0; i != nvars; i++){
			IDec dec =  inverse.get(i);
			String name = null;
			if (dec == null){ name = ""; }
			else if (dec instanceof ArrayDec){
				name = ((ArrayDec)dec).getName();
			}
			else if (dec instanceof ScalarDec){
				name = ((ScalarDec)dec).getName();
			}
			else { assert false: "Unexpected IDec type in ArrayTable";}
		sb.append(name);
		sb.append('\n');
		}
		return sb.toString();
	}







}
