package sial.parser.context;

import java.util.ArrayList;

import sial.parser.Ast.ASTNode;
import sial.parser.Ast.AbstractVisitor;
import sial.parser.Ast.CallStatement;
import sial.parser.Ast.ProcDec;

public class CollectCallSiteVisitor extends AbstractVisitor {
	
	ASTNode root;
	ProcDec proc;
	String procName; 
	ArrayList<CallStatement> callSites;

	public CollectCallSiteVisitor(ASTNode root, ProcDec proc){
		this.root = root;
		this.proc = proc;
		procName = proc.getName();
		callSites = new ArrayList<CallStatement>();
		root.accept(this);
	}
	

	public ArrayList<CallStatement> getCallSites() {
		return callSites;
	}


	@Override
	public void unimplementedVisitor(String s) { /*nop*/}
	
	@Override
    public void endVisit(CallStatement n) { 
		 String name = n.getIdent().getName();
		 if (name.equals(procName)) callSites.add(n);
	}

	
}
