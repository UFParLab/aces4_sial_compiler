package sial.code_gen;

import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;

import sial.code_gen.ArrayTable.Entry;
import sial.io.SIADataOutput;
import sial.parser.Ast.IDec;
import sial.parser.Ast.IntDec;
import sial.parser.context.ASTUtils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class IntTable implements SipConstants {
	
//	static public class Entry {
//		int attributes;  //should be either attr_int, or attr_int | attr_predefined
//		int value;       //zero unless an int literal.
//		
//	Entry(int attributes, int value){
//		this.attributes = attributes;
//		this.value = value;
//	}
//	
//	Entry(int attributes){
//		this.attributes = attributes;
//		this.value = 0;
//	}
//	}
	
	BiMap<IntDec, Integer> intBiMap; //maps declarations to index in integer array
	BiMap<Integer, Integer> intLiteralBiMap;  //maps int literals to 
	                                 //index in integer array	
	ArrayList<Integer> integers; // the integer table itself. 

	int nIntegers; //number of ints

	
	IntTable() {
		intBiMap = HashBiMap.create();
		intLiteralBiMap = HashBiMap.create();
		integers = new ArrayList<Integer>();
		nIntegers = 0;
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(nIntegers);
		
		BiMap<Integer, IntDec> names = intBiMap.inverse();
		for(int i = 0; i < nIntegers; i++){
			sb.append('\n');
			Integer value = integers.get(i);
			IntDec dec  = names.get(i);
			if (dec == null){
				sb.append("integer literal ");
				sb.append(value);
			}
			else if (ASTUtils.isPredefined(dec)){ 
				sb.append("predefined "); 
				sb.append("int ");
				sb.append (dec.getName());
			}
			else {
				sb.append("int ");
				sb.append (dec.getName());
			}
		}
		return sb.toString();
	}
	
	
	public String getIntName(int index) {
		IntDec dec = intBiMap.inverse().get(index);
		if (dec == null) return "Integer Literal";
		return dec.getName();
	}
	

	
	public int getIntIndex(IntDec dec) {
		return intBiMap.get(dec);
	}

//	//AcesHack: constants go in the special constant array in the sip
//	//currently they cannot be given a value when assigned.
//	int addConstant(String name){
//		int index = nConstants++;
//		Integer oldVal = intBiMap.put(name, index);  //ADDED EXTRA FIELD TO DETECT SYMBOLIC CONSTANTS, NO LONGER THE NEGATIVE OF INDEX.
//		assert oldVal == null: "adding duplicate constant name";
//		return index;
//	}


	int addInteger(IntDec dec, int value){
		int index = nIntegers++;
		Integer oldVal = intBiMap.put(dec, index);  
		assert oldVal == null: "adding duplicate int name";
		integers.add(value);
		return index;
	}

	int addConstant(IntDec dec){
	    return addInteger(dec, 0);
	}
	
//	public String toString(){  //currently shows only scalars,
//		                       //all ints are symbolic constants
//		StringBuilder sb = new StringBuilder();
//		sb.append(nIntegers)
//		sb.append('\n');
//		for (int i = 0; i < nIntegers; i++){
//		   sb.append(integers.get.get(i));
//		   sb.append('\n');
//		}
//		return sb.toString();	
//	}
	
	
//	public String constantsToString(){  //symbolic constant names
//		 StringBuilder sb = new StringBuilder();
//		 int size = intBiMap.size();
//		 sb.append(size);
//		 sb.append('\n');
//		 assert size == nConstants : "mismatch between intBiMap.size()=" + size +  " and nConstants=" + nConstants;
//		 BiMap<Integer,String> names = intBiMap.inverse();
//			for (int i = 0; i < size; i++){
//				sb.append(names.get(i) + '\n');  //NO LONGER -1
//			}
//		return sb.toString();
//	}
//	
//	static boolean isPredefined(int attribute){return (attr_predefined & attribute) == attr_predefined;}

	

	


	// AcesHack: convert the int to a double and treat as double literal
	int addIntLiteral(int value) {
//		Integer integerValue = Integer.valueOf(value);
		if (intLiteralBiMap.containsKey(value)){
			return intLiteralBiMap.get(value);
		}
		int index = addInteger(null,value);
		intLiteralBiMap.put(value, index);
		return index;
	}
	
//	public void write(DataOutput out) throws IOException{
//		out.writeInt(nScalars);
////		System.out.println("writing nscalars = " + nScalars);
//		for (int i = 0; i != nScalars; i++){
//			double v = scalars.get(i);
//			out.writeDouble(v);
//			}
//	}
//	
//	public void writeConstants(SIADataOutput output) throws IOException{
//		assert nConstants == intBiMap.size();
//		output.writeInt(nConstants);
//		BiMap<Integer, String> inverse = intBiMap.inverse();
//		for (int i = 0; i != nConstants; i++){
//			
//			String name = inverse.get(i); //no longer need to negate constant index
//			//output.write(i);
//		    output.writeString(name);	
//		}
//	}
	
	
	public void write(SIADataOutput output) throws IOException{
		assert nIntegers == intBiMap.size();
		output.writeInt(nIntegers);
		for (int i = 0; i != nIntegers; i++){
			output.writeInt(integers.get(i));  //value
			output.writeInt(ASTUtils.getModifierAttributes(intBiMap.inverse().get(i))); //attributes
			output.writeString(getIntName(i));  //bane or "Integer Literal"
		}
	}
}
