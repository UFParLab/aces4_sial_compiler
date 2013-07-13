package sial.code_gen;

import java.io.EOFException;
import java.io.IOException;

import sial.io.SIADataInput;
import sial.io.SIADataOutput;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;


//this class represents an array of function pointers that will be
//filled in at runtime with the address of user provided super instructions.
//The compiler uses the index number in execute statements.
//Note that in constrast with other names in the programs, these are case sensitive.
public class SpecialInstructionTable {
	BiMap<String, Integer> specialBiMap; // maps special instruction names to the index in the function table
    int nSpecials; // number of entries in the array table
    int nextIndex = 0;
    
    public SpecialInstructionTable(){
    	specialBiMap = HashBiMap.create();
    	nSpecials = 0;
    }
    
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(nSpecials);
    	sb.append('\n');
		BiMap<Integer,String> inverse = specialBiMap.inverse();
		for (int i = 0; i != nSpecials; i++){
		 sb.append(inverse.get(i));
		 sb.append('\n');
		}
    	return sb.toString();
    }
    
    public String getName(int index){
    	return specialBiMap.inverse().get(index);
    }
    
//    public int getIndex(String name){
//    	return specialBiMap.get(name.toLowerCase());
//    }
    
    public int getIndex(String name){
    	return specialBiMap.get(name);
    }
    
    //
    public int addEntry(String name){
    	int index = nSpecials;
    	Integer oldVal = specialBiMap.put(name, nSpecials);
    	assert oldVal==null:"attempted to insert a duplicate into the special instruction table";
    	nSpecials++;
    	return index;
    }
    
    
	public void write(SIADataOutput output) throws IOException {
		assert nSpecials==specialBiMap.size(): "mismatch between nSpecials="+nSpecials+" and size="+specialBiMap.size();
		output.writeInt(nSpecials);
		BiMap<Integer,String> inverse = specialBiMap.inverse();
		for (int i = 0; i != nSpecials; i++){
			String name = inverse.get(i);
			//server_barrier and sip_barrier are now keywords instead
			//of special super instructions.
			//to deal with this, they are stored internally as 
			//"aceshack_server_barrier" and "aceshack_sip_barrier"
			//TODO Need to check if this is really still necessary
			if (name.startsWith("aceshack_")){
				name = name.substring("aceshack_".length()); 
			}
		output.writeString(name);	
		}
	}
	

	public void read(SIADataInput input) throws IOException {
		nSpecials = input.readInt();
		// System.out.println("nSpecials = " + nSpecials);
		for (int i = 0; i != nSpecials; i++) {
			try {
				String s = input.readString();
				// System.out.println(s);
				addEntry(s);
				// addEntry(input.readString());
			} catch (EOFException e) {
				System.out.println("EOF exception int SpecialInstructionTable read with i=" + i);
				System.out.print("nSpecials=");
				System.out.println(nSpecials);
				throw new RuntimeException(e);
			}
		}
	}
	
	public static SpecialInstructionTable readSpecialInstructionTable(SIADataInput input) throws IOException{
		SpecialInstructionTable specialTable = new SpecialInstructionTable();
		specialTable.read(input);
		return specialTable;
	}
    
}
