
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
public class StringLiteralTable {
	BiMap<String, Integer> literalBiMap; // maps special instruction names to the index in the function table
    int nStringLiterals; // number of entries in the array table
    int nextIndex = 0;
    
    public StringLiteralTable(){
    	literalBiMap = HashBiMap.create();
    	nStringLiterals = 0;
    }
    
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	sb.append(nStringLiterals);
    	sb.append('\n');
		BiMap<Integer,String> inverse = literalBiMap.inverse();
		for (int i = 0; i != nStringLiterals; i++){
		 sb.append(inverse.get(i));
		 sb.append('\n');
		}
    	return sb.toString();
    }
    
    public String getLiteral(int index){
    	assert 0 <= index && index < literalBiMap.size(): "out of range index for string literal lookup";
    	return literalBiMap.inverse().get(index);
    }
    
//    public int getIndex(String name){
//    	return specialBiMap.get(name.toLowerCase());
//    }
    
//    public Integer getIndex(String literal){
//    	Integer index = literalBiMap.get(literal);
//    	if (index == NULL){
//    		return 
//    	}
//
//    }
    
//    public int addEntry(String name){
//    	if (nextIndex == nStringLiterals) nStringLiterals++;
//    	int index = nextIndex++;
//    	specialBiMap.put(name, index);
//    	return index;
//    }
    
//    public int addEntry(String literal){
//    	Integer index = specialBiMap.get(literal);
//    	
//    }
    
    int getAndAdd(String literal){
    	Integer idx = literalBiMap.get(literal);
    	int nextIndex = literalBiMap.size();
    	if (idx == null){
    		idx = literalBiMap.put(literal, nextIndex);
    	}
    	return idx.intValue();
    }
	public void write(SIADataOutput output) throws IOException {
		int nStringLiterals = literalBiMap.size();
		output.writeInt(nStringLiterals);
		BiMap<Integer,String> inverse = literalBiMap.inverse();
		for (int i = 0; i != nStringLiterals; i++){
			String literal = inverse.get(i);
			assert literal != null: "problem with traversal of literalBiMap";
		    output.writeString(literal);	
		}
	}
	

	public void read(SIADataInput input) throws IOException {
		nStringLiterals = input.readInt();
		// System.out.println("nSpecials = " + nSpecials);
		for (int i = 0; i != nStringLiterals; i++) {
			try {
				String s = input.readString();
				// System.out.println(s);
				getAndAdd(s);
				// addEntry(input.readString());
			} catch (EOFException e) {
				System.out.println("EOF exception in StringLiteralsTable read with i=" + i);
				System.out.print("nStringLiterals=");
				System.out.println(nStringLiterals);
				throw new RuntimeException(e);
			}
		}
	}
	
	public static StringLiteralTable readStringLiteralTable(SIADataInput input) throws IOException{
		StringLiteralTable stringLiteralTable = new StringLiteralTable();
		stringLiteralTable.read(input);
		return stringLiteralTable;
	}
    
}
