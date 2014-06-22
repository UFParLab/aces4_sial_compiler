package sial.parser.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import lpg.runtime.IAst;

import sial.parser.Ast.*;

/** Visitor that statically checks whether a Pardo or SipBarriers occurs within a Pardo statement, which would be an error.  If either is in a procedure, then
 * all call sites of the procedure are checked.
 * 
 *
 */
public class CheckEnclosingPardoVisitor extends AbstractVisitor {
	
	final HashMap<ProcDec, IStatement> procs;
	final HashMap<IStatement, PardoStatement> errors = new HashMap<IStatement, PardoStatement>();
	boolean modified;
    boolean done;
    final SymbolTable symbols;
	
	CheckEnclosingPardoVisitor(SymbolTable symbols, HashMap<ProcDec, IStatement> procs){
		this.procs = procs;
		this.modified = false;
		this.symbols = symbols;
	}
	
	@Override
    public void endVisit(SipBarrierStatement n) { 
		IAst node = n.getParent();
		while ( node != null && !(node instanceof PardoStatement) && !(node instanceof ProcDec)) node = node.getParent();
		if (node instanceof PardoStatement) {errors.put(n, (PardoStatement) node);}
		else if (node instanceof ProcDec) {IStatement key = procs.put((ProcDec) node, n); if (key == null) modified = true;}
	}
	
	@Override
    public void endVisit(PardoStatement n) { 
		IAst node = n.getParent();
		while ( node != null && !(node instanceof PardoStatement) && !(node instanceof ProcDec)) node = node.getParent();
		if (node instanceof PardoStatement) {errors.put(n, (PardoStatement) node);}
		else if (node instanceof ProcDec) {IStatement key = procs.put((ProcDec) node, n); if (key == null) modified = true;}
	}
	
	@Override
	public void endVisit(CallStatement n){
		ProcDec dec = (ProcDec) n.getIdent().getDec();
		IStatement statement = procs.get(dec);
		if (statement != null) {
			IAst node = n.getParent();
			while (node != null && !(node instanceof PardoStatement)
					&& !(node instanceof ProcDec))
				node = node.getParent();
			if (node instanceof PardoStatement)
				errors.put(statement, (PardoStatement) node);
			else if (node instanceof ProcDec) {
				IStatement old = procs.put((ProcDec) node, statement);
				if (old == null)
					modified = true;
			}
		}
	}

	@Override
	public void unimplementedVisitor(String s) { /*nop*/
	}
	
	public boolean hasError(){ return ! errors.isEmpty();}
	public Iterator<Entry<IStatement, PardoStatement>> getErrorIterator(){ return errors.entrySet().iterator(); }
	public boolean modified(){ return modified;}

}
