package sial.parser.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import lpg.runtime.IAst;

import sial.parser.Ast.*;

public class CheckBarriersInPardoVisitor extends AbstractVisitor {
	
	final HashMap<ProcDec, SipBarrierStatement> procs;
	final HashMap<SipBarrierStatement, PardoStatement> errors = new HashMap<SipBarrierStatement, PardoStatement>();
	boolean modified;
    boolean done;
    final SymbolTable symbols;
	
	CheckBarriersInPardoVisitor(Sial root, SymbolTable symbols, HashMap<ProcDec, SipBarrierStatement> procs){
		this.procs = procs;
		this.modified = false;
		this.symbols = symbols;
		root.accept(this);
	}
	
	@Override
    public void endVisit(SipBarrierStatement n) { 
		IAst node = n.getParent();
		while ( node != null && !(node instanceof PardoStatement) && !(node instanceof ProcDec)) node = node.getParent();
		if (node instanceof PardoStatement) {errors.put(n, (PardoStatement) node);}
		else if (node instanceof ProcDec) {SipBarrierStatement key = procs.put((ProcDec) node, n); if (key == null) modified = true;}
	}
	
	@Override
	public void endVisit(CallStatement n){
		ProcDec dec = (ProcDec) n.getIdent().getDec();
		SipBarrierStatement barrier = procs.get(dec);
		if (barrier != null) {
			IAst node = n.getParent();
			while (node != null && !(node instanceof PardoStatement)
					&& !(node instanceof ProcDec))
				node = node.getParent();
			if (node instanceof PardoStatement)
				errors.put(barrier, (PardoStatement) node);
			else if (node instanceof ProcDec) {
				SipBarrierStatement old = procs.put((ProcDec) node, barrier);
				if (old == null)
					modified = true;
			}
		}
	}

	@Override
	public void unimplementedVisitor(String s) {
		// TODO Auto-generated method stub

	}
	
	public boolean hasError(){ return ! errors.isEmpty();}
	public Iterator<Entry<SipBarrierStatement, PardoStatement>> getErrorIterator(){ return errors.entrySet().iterator(); }
	public boolean modified(){ return modified;}

}
