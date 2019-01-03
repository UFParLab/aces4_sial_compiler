package sial.parser.context;

import java.util.BitSet;

public class ExpressionType {
	
	
	//note that the order is important, or at least it is useful that INT < SCALAR < BLOCK
	public enum EType {
		INDEX, INT, SCALAR, BLOCK, CONTIG_BLOCK, ARRAY, STRING;
	}
	

}
