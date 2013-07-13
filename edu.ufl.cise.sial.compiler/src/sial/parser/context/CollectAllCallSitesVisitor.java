package sial.parser.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sial.parser.Ast.ASTNode;
import sial.parser.Ast.AbstractVisitor;
import sial.parser.Ast.CallStatement;
import sial.parser.Ast.ProcDec;
import sial.parser.Ast.Sial;

public class CollectAllCallSitesVisitor extends AbstractVisitor {
	
	ASTNode root;
	HashMap<ProcDec, List<CallStatement>> callSiteMap;

	public CollectAllCallSitesVisitor(ASTNode root){
		this.root = root;
		assert (root instanceof Sial) && ((Sial) root).isSymbolTablePopulated();
		callSiteMap = new HashMap<ProcDec, List<CallStatement>>();
		root.accept(this);
	}
	

	public List<CallStatement> getCallSites(ProcDec dec) {
		return callSiteMap.get(dec);
	}


	@Override
	public void unimplementedVisitor(String s) { /*nop*/}
	
	@Override
    public void endVisit(CallStatement n) { 
		 String name = n.getIdent().getName();
		 
	}

	
}
