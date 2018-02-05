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


		public Entry(int opcode, int arg0, int arg1, int arg2, int[] selector,
				int lineno) {
			this.opcode = opcode;
			this.arg0 = arg0;
			this.arg1 = arg1;
			this.arg2 = arg2;
			this.selector = selector;
			this.lineno = lineno;
		}

		
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
//			sb.append(Opcode.toString(opcode));sb.append(':');
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
   	 private int nOps;
   	 
	OpTable(){
		procBiMap = HashBiMap.create();
		entries = new ArrayList<Entry>();
		nOps = 0; 	 
	}

	
	public int addOptableEntry(Opcode opcode, int arg0, int arg1,
			int arg2, int[] selector, int lineno) {
		int index = nOps++;
		assert selector.length <= TypeConstantMap.max_rank;
		entries.add(new Entry(opcode.getOpcodeValue(), arg0, arg1, arg2, selector, lineno));
		return index;	
	}
	
	
	public int addOptableEntry(Entry e){
		int index = nOps++;
		entries.add(e);
		return index;
	}

	
	// returns a string representation of the op table
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nOps);
		sb.append('\n');
		for (int i = 0; i != nOps; i++) {
			sb.append(i).append(": ");
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
	
	//backpatch jump instruction of if statement.  
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
	
	
//	public Opcode lastOpcode(){
//		return Opcode.class.getEnumConstants()[entries.get(nOps-1).opcode - Opcode.offset];
//	}
	
	public int getArg0(int pc){
		return entries.get(pc).arg0;
	}
	
	public int getArg1(int pc){
		return entries.get(pc).arg1;
	}
	
	public int lastArg0(){
		return entries.get(nOps-1).arg0;
	}
	
	public int lastArg1(){
		return entries.get(nOps-1).arg1;
	}
	
	public int lastArg2(){
		return entries.get(nOps-1).arg2;
	}
	
    boolean firstOpInitialized = false;

	public boolean isFirstOpInitialized() {
		return firstOpInitialized;
	}

	public void setFirstOpInitialized(boolean firstOpInitialized) {
		this.firstOpInitialized = firstOpInitialized;
	}
	
	public int getOpcode(int pc){
		return entries.get(pc).opcode;
	}
	
	public int getLine(int pc){
		return entries.get(pc).lineno;
	}


	public int getnOps() {
		return nOps;
	}
	
	public String optableEntrytoString(int pc){
		return entries.get(pc).toString();
	}




}
