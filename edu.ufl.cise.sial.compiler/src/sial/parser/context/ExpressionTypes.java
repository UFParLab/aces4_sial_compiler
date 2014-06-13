package sial.parser.context;

public class ExpressionTypes {
	
	public enum ExpressionType {  //note that the order is important, or at least it is useful that INT < SCALAR < BLOCK.
	INT, 
	SCALAR, 
	BLOCK, 
	ARRAY,
	STRING, 
	ERROR;
	
public boolean isType(ExpressionType ... types){
		for (ExpressionType t : types){
			if (this == t) return true;
		}
		return false;
	}
}

}
