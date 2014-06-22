package sial.parser.context;

import java.util.BitSet;

public class ExpressionType {
	
//	public enum ExpressionType {  //note that the order is important, or at least it is useful that INT < SCALAR < BLOCK.
//	INT, 
//	SCALAR, 
//	BLOCK, 
//	ARRAY,
//	STRING, 
//	INDEX,
//	ERROR;
//	
//public boolean isType(ExpressionType ... types){
//		for (ExpressionType t : types){
//			if (this == t) return true;
//		}
//		return false;
//	}
//}
	
	
	public enum EType {
		INDEX, INT, SCALAR, BLOCK, ARRAY, STRING;
	}
	
//	EnumSet<Etype> etype;
//	public void setType(Type type){ types.set(type.ordinal());}
//	public boolean isType(Type type){ return types.get(type.ordinal());}
////	public Type getType(){		
////		Type largestType = null;
////		for (Type t: Type.values()){
////			if (types.get(t.ordinal())) largestType = t; 			
////		}
////		return largestType;
////	}
//	
//	static newExpressionType(Type value){
//		ExpressionType t = new ExpressionType();
//		t.types.set(value.ordinal());
//		return t;
//	}


}
