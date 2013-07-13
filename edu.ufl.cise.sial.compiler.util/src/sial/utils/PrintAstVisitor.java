package sial.utils;

import sial.parser.Ast.AbstractVisitor;



public class PrintAstVisitor extends AbstractVisitor{

	@Override
	public void unimplementedVisitor(String s) {
         System.out.println(s);

	}

}
