/** This class contains the generated instructions. */

package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.collect.HashBiMap;

import sial.code_gen.TypeConstantMap;
import sial.code_gen.IndexTable.Entry;
import sial.io.SIADataInput;
import sial.io.SIADataOutput;
import sial.parser.SialParsersym;



public class OpTable {
	
	public static class Entry{
		int opcode; //1
		int arg0; //2
		int arg1; //3
		int arg2; //4
		int[]  selector; //size is max_array_index
		int lineno;

		
//		//execute instruction
//		Entry(int opcode, int user_sub, int lineno){
//			this.opcode = opcode;
//			this.user_sub = user_sub;
//			this.lineno = lineno;
//			ind = new int[AcesHacks.max_array_index];
//		}
		//execute instruction with one argument
//		Entry(int opcode, int result_array, int[] ind, int user_sub, int lineno){
//			this.opcode = opcode;
//			this.arg2 = result_array;
//			this.selector = ind;
////			this.user_sub = user_sub;
//			this.lineno = lineno;
//		}
		

		public boolean equalVals(Object other){
			if (this == other) return true;
			if (!(other instanceof Entry)) return false;
			Entry o = (Entry)other;
			return  opcode ==o.opcode &&//1
					arg0 == o.arg0 &&//2
					arg1 == o.arg1 && //3
					arg2==o.arg2 && //4
			Arrays.equals(selector, o.selector) //&& //size is max_array_index	
			;
		}
//		//go_to_op
//		Entry(int opcode, int result_array, int[] ind, int lineno){
//			this.opcode = opcode;
//			this.arg2 = result_array;
//			assert ind.length == TypeConstantMap.max_rank;
//			this.selector = ind; 
//			this.lineno = lineno;
//		}
//		
//		//assign_op
//		Entry(int opcode, int operand_array, int result_array,  int[] ind, int lineno){
//			this.opcode = opcode;
//			this.arg0 = operand_array;
//			this.arg1 = -1;
//			this.arg2 = result_array;
//			this.selector = ind;
//			this.lineno = lineno;
//		}
//		public Entry() {
//		}

		public Entry(int opcode, int arg0, int arg1, int arg2, int[] selector,
				int lineno) {
			this.opcode = opcode;
			this.arg0 = arg0;
			this.arg1 = arg1;
			this.arg2 = arg2;
			this.selector = selector;
			this.lineno = lineno;
		}


//		void read(DataInput input) throws IOException{
//			opcode = input.readInt(); //1
//			arg0 = input.readInt(); //2
//			arg1 = input.readInt(); //3
//			arg2 = input.readInt(); //4
//			selector = new int[TypeConstantMap.max_rank]; //size is max_array_index
//			for (int i = 0; i < TypeConstantMap.max_rank; i++){
//				selector[i] = input.readInt();
//			}
//			lineno = input.readInt();     
//		}

		
		static Entry readEntry(DataInput input) throws IOException{
			int opcode = input.readInt(); 
			int arg0 = input.readInt(); 
			int arg1 = input.readInt(); 
			int arg2 = input.readInt(); 
			int[] selector = new int[TypeConstantMap.max_rank]; //size is max_array_index
			for (int i = 0; i < TypeConstantMap.max_rank; i++){
				selector[i] = input.readInt();
			}
			int lineno = input.readInt();     
			Entry entry = new Entry(opcode, arg0, arg1, arg2, selector, lineno);
			return entry;
		}
		
		void write(DataOutput output) throws IOException{
			output.writeInt(opcode); //1
			output.writeInt(arg0); //2
			output.writeInt(arg1); //3
			output.writeInt(arg2); //4
			for (int i = 0; i < TypeConstantMap.max_rank; i++){  //This approach requires the size of this to be fixed, and for the sip to read individual ints
				                                                   //instead of reading an array
				output.writeInt(selector[i]);
			}
			output.writeInt(lineno);
		}
		

		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append(opcode); sb.append(',');//1  
			sb.append(arg0); sb.append(',');//2
			sb.append(arg1); sb.append(',');//3
			sb.append(arg2); sb.append(',');//4
			sb.append('[');
			sb.append(selector[0]);
			for (int i = 1; i < TypeConstantMap.max_rank; i++){
				sb.append(','); sb.append(selector[i]);
			}
			sb.append(']'); sb.append(',');
			sb.append(lineno); sb.append(',');
			return sb.toString();
		}
	
}//class Entry	
	
  	 HashBiMap<String,Integer> procBiMap;  //maps procedure names optable index
   	 ArrayList<Entry> entries;
   	 int nOps;
   	 
	OpTable(){
		procBiMap = HashBiMap.create();
		entries = new ArrayList<Entry>();
		nOps = 0; 	 
	}

//	public int addOptableEntry(int opcode, int lineno){
//		return addOptableEntry(opcode, 0,0,0, defaultZeroInd, lineno);
//	}
//
//	public int addOptableEntry(int opcode, int result_array, int[] ind, int lineno) {
//		 int index = nOps++;
//		 assert ind.length <= TypeConstantMap.max_rank;
//		 entries.add(new Entry(opcode, result_array, ind, lineno));
//		 return index;
//	}
//
//	public int addOptableEntry(int op_code, int operandIndex, int resultIndex, int[] ind, 
//			 int lineno){
//		int index = nOps++;
//		assert ind.length <= TypeConstantMap.max_rank;
//		entries.add(new Entry(op_code, operandIndex, resultIndex, ind, lineno));
//		return index;
//	}
//	
//
//	public int addOptableEntry(int opcode, int operand1, int operand2,
//			int resultIndex, int[] ind, int lineno) {
//		int index = nOps++;
//		assert ind.length <= TypeConstantMap.max_rank;
//		entries.add(new Entry(opcode, operand1, operand2, resultIndex, ind, lineno));
//		return index;	
//	}
//	
//	//do_op, enddo_op, exit_op
//	public int addOptableEntry(int opcode, int[] ind, int lineno) {
//		int index = nOps++;
//		assert ind.length <= TypeConstantMap.max_rank;
//		entries.add(new Entry(opcode, 0, 0, 0, ind, lineno));
//		return index;
//	}
//	
//
//	
//	//execute instruction
//	//ACES4  TODO  check this!  
//	public int addOptableEntry(int opcode, int result_array, int[] ind, int user_sub, int lineno){
//		int index = nOps++;
//		entries.add(new Entry(opcode, result_array, ind, user_sub, lineno));
//		return index;
//	}
	
//	public int addOptableEntry(Opcode opcode, int lineno){
//		return addOptableEntry(opcode, 0,0,0, defaultZeroInd, lineno);
//	}
//
//	public int addOptableEntry(Opcode opcode, int result_array, int[] ind, int lineno) {
//		 int index = nOps++;
//		 assert ind.length <= TypeConstantMap.max_rank;
//		 entries.add(new Entry(opcode.ordinal(), result_array, ind, lineno));
//		 return index;
//	}
//
//	public int addOptableEntry(Opcode opcode, int operandIndex, int resultIndex, int[] ind, 
//			 int lineno){
//		int index = nOps++;
//		assert ind.length <= TypeConstantMap.max_rank;
//		entries.add(new Entry(opcode.ordinal(), operandIndex, resultIndex, ind, lineno));
//		return index;
//	}
	

	public int addOptableEntry(Opcode opcode, int arg0, int arg1,
			int arg2, int[] selector, int lineno) {
		int index = nOps++;
		assert selector.length <= TypeConstantMap.max_rank;
		entries.add(new Entry(opcode.getOpcodeValue(), arg0, arg1, arg2, selector, lineno));
		return index;	
	}
	
//	//do_op, enddo_op, exit_op
//	public int addOptableEntry(Opcode opcode, int[] ind, int lineno) {
//		int index = nOps++;
//		assert ind.length <= TypeConstantMap.max_rank;
//		entries.add(new Entry(opcode.ordinal(), 0, 0, 0, ind, lineno));
//		return index;
//	}
//	
//
//	
//	//execute instruction
//	//ACES4  TODO  check this!  
//	public int addOptableEntry(Opcode opcode, int result_array, int[] ind, int user_sub, int lineno){
//		int index = nOps++;
//		entries.add(new Entry(opcode.ordinal(), result_array, ind, user_sub, lineno));
//		return index;
//	}
	
	public int addOptableEntry(Entry e){
		int index = nOps++;
		entries.add(e);
		return index;
	}

	
//	public void read(int nentries, DataInput input) throws IOException{
//			for (int i = 0; i < nentries; i++){
//				   Entry entry = Entry.readEntry(input);
//				   entries.add(entry);
//				   nOps++;
//				}
//		}
	
	
	// returns a string representation of the op table
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nOps);
		sb.append('\n');
		for (int i = 0; i != nOps; i++) {
			sb.append(entries.get(i).toString());
			sb.append('\n');
		}
		return sb.toString();
	}

	public void write(DataOutput output) throws IOException {
		assert nOps == entries.size();
		output.writeInt(nOps);
		for (int i = 0; i != nOps; i++)
			entries.get(i).write(output);	
	}


	public boolean equalVals(Object other){
		if (this == other) return true;
		if ( !(other instanceof OpTable))return false;
		OpTable o = (OpTable)other;
		for (int i = 0; i != entries.size(); i++){
			if (i >= o.entries.size()){ 
				return false;
			}
			boolean isEqual = entries.get(i).equalVals(o.entries.get(i));
			if (!isEqual){
				return false;
			}
	
		}
		return true;
	}
	
	//backpatch jump instruction if If statement.  
	public void backpatchArg0(int instructionToChange){
		entries.get(instructionToChange).arg0 = nOps;
	}
	
	//set destination slot for assignments in preceding instruction
	public void setDestination(int arraySlot, int [] ind){
		Entry entry = entries.get(nOps-1);
		entry.arg2 = arraySlot;
		entry.selector = ind;
	}
	
	/**swaps the position of the two most recent instruction in the optable */
    public void swap(){
    	Entry tmp = entries.get(nOps-2);
    	entries.set(nOps-2,  entries.get(nOps-1));
    	entries.set(nOps-1, tmp);
    }
	
//	public void setDestination(int scalar_slot) {
//		setDestination(scalar_slot, defaultZeroInd);
//		
//	}
	
	public Opcode lastOpcode(){
		return Opcode.class.getEnumConstants()[entries.get(nOps-1).opcode];
	}
	
//	public void updateLastOpcode(Opcode expected, Opcode replacement){
//		assert (lastOpcode() == expected): "compiler bug, update last opcode encountered unexpected opcode";
//		entries.get(nOps-1).opcode = replacement.ordinal();
//	}
    boolean firstOpInitialized = false;

	public boolean isFirstOpInitialized() {
		return firstOpInitialized;
	}

	public void setFirstOpInitialized(boolean firstOpInitialized) {
		this.firstOpInitialized = firstOpInitialized;
	}

//	public sial.code_gen.OpTable.Entry getEntryAt(int i) {
//		return entries.get(i);
//	}


//		public static OpTable readOpTable(DataInput input) throws IOException{
//			OpTable table = new OpTable();
//			int numOps = input.readInt();
//			for (int i = 0; i < numOps; i++){
//				   Entry entry = Entry.readEntry(input);
//				   table.addOptableEntry(entry);
//				}
//	        return table;
//	}




//	/** reorders the instructions in the optable so the latest one goes before the third on in. 
//	 * in other words, if the current Optable has entries   ...... entryN-3, entryN-2, entryN-1, then 
//	 * after this method, the will be ....,entryN-1, entryN-3, entryN-2*/
//    //
//    public void swap2(){
//    	Entry tmp = entries.get(nOps-3);
//    	entries.set(nOps-3, entries.get(nOps-2));
//    	entries.set(nOps-2, entries.get(nOps-1));
//    	entries.set(nOps-1,  tmp);
//    }


    

//	public void backpatchDoOp(int addr, int startIndex) {
//		entries.get(addr).ind[0] = startIndex + 1;	
//	}


}
