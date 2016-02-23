/** Manages information about arrays.  Arrays are identified in the sip by their position in the 
 * array table.  For each array, the array table contains an entry with the rank, the type, the index
 * used in its declaration (identified by its position in the index table).  Scalars are treated uniformly
 * with arrays (they have rank zero).  Their values are stored in in the sip in a mutable data structure
 * at a position indicated in the scalar_index field.
 */

package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import sial.io.SIADataInput;
import sial.io.SIADataOutput;
import sial.parser.SialParsersym;
import sial.parser.Ast.ArrayDec;
import sial.parser.Ast.IDec;
import sial.parser.Ast.ScalarDec;
import sial.parser.context.ASTUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;


public class ArrayTable implements SipConstants, SialParsersym {

	static public class Entry {
		int rank; 
		int array_type; // scalar, int, served, distributed, etc. Values
						// in SipTypeConstants
		int[] index_array; // each element contains the position in the IndexTable of the
		                   //corresponding index in the array declaration
		int scalar_table_slot_or_server_priority; // if scalar or int, this is index into the scalar,
							// int table.  If not, this indicates priority for
		                    // for data servers.  Priority values are 
		                    // distributed_array_priority and served_array_priority
		                    // defined in SipConstants.java  (Local and
		                    // temp arrays have value 0 here.  We may want 
		                    //to enhance this later with more values, values
		                    //that can be changed, etc.


		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + array_type;
			result = prime * result + Arrays.hashCode(index_array);
			result = prime * result + rank;
			result = prime * result + scalar_table_slot_or_server_priority;
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
			if (rank != other.rank)
				return false;
			if (scalar_table_slot_or_server_priority != other.scalar_table_slot_or_server_priority)
				return false;
			return true;
		}


		Entry(int max_rank, int rank, int array_type, int[] indarray,
				int scalar_table_slot_or_server_priority) {
			this.index_array = new int[max_rank];
			this.rank = rank;
			this.array_type = array_type;
			this.scalar_table_slot_or_server_priority = scalar_table_slot_or_server_priority;
			int i;
			if (indarray == null) {  //item is scalar or int
				i = 0;
			} else { //copy given elements into index_array
				for (i = 0; i != indarray.length; i++) {
					this.index_array[i] = indarray[i];
				}
			}
			//set unused index_array elements to zero
			for (int j = i; j != max_rank; j++) {
				this.index_array[j] = 0;
			}
		}
		

		public Entry() {}
		
		void read(SIADataInput in) throws IOException {
			rank = in.readInt(); // dimension of array

			int max = TypeConstantMap.max_rank;
			index_array = new int[max]; // each element contains the fortran index
								// in the index array of the corresponding index
			for(int i = 0; i != rank; i++){
				index_array[i] = in.readInt();
			}
			for (int i = rank; i != max; i++){
				index_array[i] = 0;
			}
			array_type = in.readInt(); // type: served, distributed, etc. Values in SipTypeConstants
			scalar_table_slot_or_server_priority = in.readInt(); // if scalar
			                     //this is index into the Scalar Table, if distributed or served, this is
								// the server priorty
		}
		
		
		public String toString(){
			int max = TypeConstantMap.max_rank;
			StringBuilder sb = new StringBuilder();
			sb.append(max);
			sb.append('\n');
			sb.append(rank); sb.append(',');
			sb.append(array_type); sb.append(',');
			sb.append('[');
			sb.append(index_array[0]);
			for (int i = 1; i < max; i++){
				sb.append(',');
				sb.append(index_array[i]);
			}
			sb.append("],");
			sb.append(scalar_table_slot_or_server_priority); sb.append(',');
			return sb.toString();
		}
		

		public static Entry readEntry(SIADataInput in) throws IOException{
			Entry entry = new Entry();
			entry.read(in);
			return entry;
		}
		
		void write(SIADataOutput out) throws IOException {
			out.writeInt(rank);
			//forloop added for aces4
			for (int i = 0; i != rank; i++) {
				out.writeInt(index_array[i]);
			}
			out.writeInt(array_type);
			out.writeInt(scalar_table_slot_or_server_priority); 

		} 
		

	}//end of class entry

	BiMap<IDec, Integer> arrayBiMap; // maps declarations to
									 // index in array table and vice versa
	int nvars; // number of entries in the array table
	ArrayList<Entry> entries;  //the array_table itself
	ArrayList<String> symbols; //used only to read .siox file
	
	ArrayList<Entry> distributed;
	ArrayList<IDec>  distDec;
	ArrayList<Entry> local;
	ArrayList<IDec>  localDec;
	ArrayList<Entry> temp;
	ArrayList<IDec>  tempDec;
	ArrayList<Entry> contigLocal;
	ArrayList<IDec>  contigLocalDec;
	ArrayList<Entry> sialStatic;
	ArrayList<IDec>  sialStaticDec;
	ArrayList<Entry> scalar;
	ArrayList<IDec>  scalarDec;
	
	
	/** Constructs the combined array table and bimap from declaration to slot number from the structures for individual array types.
	 * All declaration should be visited prior to calling this method.  The only additional entries will be scalar literals.
	 * 
	 */
	void createArrayTable(){		
		entries = new ArrayList<Entry>();
		arrayBiMap = HashBiMap.create();
		//add distributed and served arrays
		entries.addAll(distributed);
		int begin = 0;
		int end = entries.size();
		for (int i = begin; i < end; ++i){
			arrayBiMap.put(distDec.get(i-begin), i);
		}
		//add local arrays
		entries.addAll(local);
		begin = end;
		end = begin + local.size();
		for (int i = begin; i < end; ++i){
			arrayBiMap.put(localDec.get(i-begin), i);
		}
		//add local contiguous arrays
		entries.addAll(contigLocal);
		begin = end;
		end = begin + contigLocal.size();
		for (int i = begin; i < end; ++i){
			arrayBiMap.put(contigLocalDec.get(i-begin), i);
		}		
		//add temp arrays
		entries.addAll(temp);
		begin = end;
		end = begin + temp.size();
		for (int i = begin; i < end; ++i){
			arrayBiMap.put(tempDec.get(i-begin), i);
		}	
		//add static arrays
		entries.addAll(sialStatic);
		begin = end;
		end = begin + sialStatic.size();
		for (int i = begin; i < end; ++i){
			arrayBiMap.put(sialStaticDec.get(i-begin), i);
		}
		//add scalars
		entries.addAll(scalar);
		begin = end;
		end = begin + scalar.size();
		for (int i = begin; i < end; ++i){
			arrayBiMap.put(scalarDec.get(i-begin), i);
		}
		
		//initialize number of variables
		nvars = entries.size();
	}

	public ArrayTable() {
		distributed = new ArrayList<Entry>();
		distDec = new ArrayList<IDec>();
		local = new ArrayList<Entry>();
		localDec = new ArrayList<IDec>();
		temp = new ArrayList<Entry>();
		tempDec = new ArrayList<IDec>();
		contigLocal = new ArrayList<Entry>();
		contigLocalDec = new ArrayList<IDec>();
		sialStatic = new ArrayList<Entry>();
		sialStaticDec = new ArrayList<IDec>();
		scalar = new ArrayList<Entry>();
		scalarDec = new ArrayList<IDec>();
	}

	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i != entries.size(); i++) {
			sb.append(entries.get(i).toString());
			sb.append('\n');
		}
		return sb.toString(); 
	}
	

	public int getIndex(IDec dec) {
		return arrayBiMap.get(dec);
	}

	public int getRank(int i) {
		return entries.get(i).rank;
	}


	void addScalarEntry(IDec dec, int attributes, int scalarIndex){
		assert ((attributes & scalar_value_t) == scalar_value_t): "Illegal attribute for scalar entry in array table";
		Entry entry = new Entry(max_rank, 0, attributes, null, scalarIndex);
		scalar.add(entry);
		scalarDec.add(dec);

	}
	
	int addScalarLiteral(IDec dec, int attributes, int scalarIndex){
		assert ((attributes & scalar_value_t) == scalar_value_t): "Illegal attribute for scalar entry in array table";
		return addScalarEntry(dec, 0, attributes, null, scalarIndex);		
	}


	void addDistributedOrServedArrayEntry(ArrayDec dec, int rank, int attributes, int[] indarray, int priority) {
		Entry entry = new Entry(max_rank, rank, attributes, indarray, priority);
			distributed.add(entry);
			distDec.add(dec);
		}

	void addTempArrayEntry(ArrayDec dec, int rank, int attributes, int[] indarray, int priority) {
		Entry entry = new Entry(max_rank, rank, attributes, indarray, priority);
			temp.add(entry);
			tempDec.add(dec);
		}

	void addLocalArrayEntry(ArrayDec dec, int rank, int attributes, int[] indarray, int priority) {
		Entry entry = new Entry(max_rank, rank, attributes, indarray, priority);
			local.add(entry);
			localDec.add(dec);
		}
	
	void addContigLocalArrayEntry(ArrayDec dec, int rank, int attributes, int[] indarray, int priority) {
		Entry entry = new Entry(max_rank, rank, attributes, indarray, priority);
			contigLocal.add(entry);
			contigLocalDec.add(dec);
		}
	
	
	void addStaticArrayEntry(ArrayDec dec, int rank, int attributes, int[] indarray, int priority) {
		Entry entry = new Entry(max_rank, rank, attributes, indarray, priority);
		sialStatic.add(entry);
		sialStaticDec.add(dec);
	}

	int addScalarEntry(IDec dec, int rank, int array_type, int[] indarray, int scalar_table_slot_or_server_priority) {
		int slot = nvars++;
		if (dec != null) arrayBiMap.put(dec, slot);
		int max_rank = TypeConstantMap.max_rank;
		Entry entry = new Entry(max_rank, rank, array_type, indarray, scalar_table_slot_or_server_priority);
		entries.add(entry);
		return slot;
	}
	
	int getNvars(){return nvars;}

	public static ArrayTable readArrayTable(SIADataInput input) throws IOException {
		ArrayTable arrayTable = new ArrayTable();
		arrayTable.nvars = input.readInt();
		for (int i = 0; i != arrayTable.nvars; i++){
			arrayTable.entries.add(Entry.readEntry(input));
		}
		return arrayTable;
	}
	

/** Precondition:  createArrayTable has been called to create combined table.
 * 
 * @param output
 * @throws IOException
 */
	public void write(SIADataOutput output) throws IOException {
		assert (entries != null): "Called write  array table before combined table has been created";
		int size = entries.size();
		assert (size == nvars): "ArrayTable entries.size= "+size + " nvars=" + nvars;
		output.writeInt(size);
		for (int i = 0; i < entries.size(); i++){
			IDec dec = getDec(i);
			if (dec == null) {output.writeString("scalar literal");}
			else if (dec instanceof ArrayDec){output.writeString(((ArrayDec)dec).getName());}
			else if (dec instanceof ScalarDec){output.writeString(((ScalarDec)dec).getName());}
			else assert false;
			entries.get(i).write(output);
		}
	}
	
	//this will be null if index represents a scalar literal
	public IDec getDec(int index){
		return  arrayBiMap.inverse().get(index);
	}

	//precondition:  index of literal is in array table
	public int getIndexOfScalarEntry(int scalarTableIndex) {
		//linear search.  Perhaps replace with something better
		int i;
		for (i = 0; i < entries.size() && (entries.get(i).scalar_table_slot_or_server_priority != scalarTableIndex || entries.get(i).array_type != scalar_value_t); i++);
		assert i<entries.size();
		return i;
	}

	
	public void writeSymbols(SIADataOutput output) throws IOException {
		output.writeInt(nvars);
		BiMap<Integer,IDec> inverse = arrayBiMap.inverse();

		for (int i = 0; i != nvars; i++){
			IDec dec =  inverse.get(i);
			String name;
			if (dec == null){  //scalar literals have a slot in the array table, but no declaration. 
				//Look up their value and create a name.
				int scalar_table_index = entries.get(i).scalar_table_slot_or_server_priority - 1; //this is a fortran index
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
