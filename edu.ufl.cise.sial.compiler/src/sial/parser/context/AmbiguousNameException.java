package sial.parser.context;

import sial.parser.Ast.IDec;

@SuppressWarnings("serial")
public class AmbiguousNameException extends Exception {

	public AmbiguousNameException(String string) {
		super(string);
	}
	


}
