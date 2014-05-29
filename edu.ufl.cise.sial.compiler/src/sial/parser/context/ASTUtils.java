//this class was adapted from org.eclipse.imp.lpg.parser.ASTUtils


package sial.parser.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static sial.code_gen.SipConstants.static_array_t;


//import org.eclipse.core.runtime.IPath;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.core.runtime.NullProgressMonitor;
//import org.eclipse.core.runtime.Path;
//import org.eclipse.imp.model.ICompilationUnit;
//import org.eclipse.imp.model.ISourceProject;
//import org.eclipse.imp.model.ModelFactory;
//import org.eclipse.imp.parser.IParseController;

import lpg.runtime.IAst;
import lpg.runtime.IToken;
import sial.code_gen.TypeConstantMap;
import sial.parser.SialParsersym;
import sial.parser.Ast.ASTNode;
import sial.parser.Ast.ASTNodeToken;
import sial.parser.Ast.ArrayDec;
import sial.parser.Ast.CallStatement;
import sial.parser.Ast.ContiguousModifier;
import sial.parser.Ast.DoStatement;
import sial.parser.Ast.IASTNodeToken;
import sial.parser.Ast.IDec;
import sial.parser.Ast.IRangeVal;
import sial.parser.Ast.Ident;
import sial.parser.Ast.IdentList;
import sial.parser.Ast.ImportProg;
import sial.parser.Ast.ImportProgList;
import sial.parser.Ast.IndexDec;
import sial.parser.Ast.IntDec;
import sial.parser.Ast.IntLitRangeVal;
import sial.parser.Ast.NegRangeVal;
import sial.parser.Ast.PardoStatement;
import sial.parser.Ast.SparseModifier;
//import sial.parser.Ast.PersistentModifier;
import sial.parser.Ast.PredefinedModifier;
import sial.parser.Ast.ProcDec;
import sial.parser.Ast.Program;
import sial.parser.Ast.ScalarDec;
import sial.parser.Ast.Sial;
import sial.parser.Ast.SubIndexDec;

public class ASTUtils implements SialParsersym{
	private ASTUtils(){}
	
	/** returns the root of the AST containing the given IAst node */
	public static Sial getRoot(IAst node){
        while (node != null && !(node instanceof Sial))
            node= node.getParent();
        return (Sial) node;
    }
	
	/** returns the root of the AST containing the given IDec */
	public static Sial getRoot(IDec dec){
		return getRoot( (IAst)dec);
    }
	
	/** returns the AST node declaring the enclosing program of the given node, or null if not in a procedure */
	public static Program getEnclosingProgram(IAst node){
        while (node != null && !(node instanceof Program))
            node= node.getParent();
        return (Program) node;
    }
	
	
	/** returns the AST node declaring the enclosing procedure of the given node, or null if not in a procedure */
	public static ProcDec getEnclosingProc(IAst node){
		IAst tnode = node.getParent();
		while (tnode != null && !(tnode instanceof ProcDec))
			tnode = tnode.getParent();
		return (ProcDec) tnode;
	}
	
	/**
	 * 
	 * @param n  The identifier represeting an index
	 * @param node  The AST node whose ancestors should define a value for the index
	 * @return  the AST node that is either a Dec in which the index appears, Do or Pardo loop that defines the given index, or the AST node of the procedure containing the node, or null if none of the above
	 */
	static IAst getEnclosingLoopOrIDec(Ident n, IAst node){
		IndexDec dec = (IndexDec) n.getDec();
		IAst tnode = node.getParent();
		while (tnode != null && !(tnode instanceof IDec || tnode instanceof ProcDec || tnode instanceof DoStatement || tnode instanceof PardoStatement)){
			tnode = tnode.getParent();
		}
		if (tnode == null ) return tnode; //not found
		if (tnode instanceof IDec) return tnode; //this context of this ident was a declaration.
		if (tnode instanceof DoStatement){
			DoStatement doStatement = (DoStatement)tnode;
			IDec doVarDec = doStatement.getStartIndex().getDec();
			if (doVarDec == dec) return doStatement;
			return getEnclosingLoopOrIDec(n, doStatement);
		}
		if (tnode instanceof PardoStatement){
			PardoStatement pardoStatement = (PardoStatement)tnode;
			IdentList identList  = pardoStatement.getStartIndices();
			for (int i = 0; i < identList.size(); ++i){
				Ident pardoVar = (Ident) identList.getElementAt(i);
				IDec pardoVarDec = pardoVar.getDec();
				if (pardoVarDec == dec) return pardoStatement;
			}
			return getEnclosingLoopOrIDec(n, pardoStatement);
		}
		return null;
	}
	
	static ArrayList<CallStatement> getCallSites(IndexDec dec, ProcDec procDec){
		Sial root = getRoot((IAst)procDec);
		ArrayList<CallStatement> callStatements = new CollectCallSiteVisitor(root, procDec).getCallSites();
		return callStatements;
	}
	
	
//	/**
//	 * determines whether the given index has a defined value at this point.
//	 * Either it is within a pardo or do loop that defines the variable, or it
//	 * is in a proc, and is defined in all callsites of the proc. Since the 
//	 * callsites have not necessarily been visited yet, they are added to
//	 * a list, indexAtCallSitesToCheck.  These are checked at the end of the program.
//	 * 
//	 * @param n
//	 *            ident representing index 
//	 * @param node
//	 *            node whose ancesters should define the index
//	 * @return
//	 */
//	public static boolean isDefinedInEnclosingScope(Ident n, IAst node) {
//		IndexDec dec = (IndexDec) n.getDec();
//		IAst tnode = getEnclosingLoopOrIDec(n, node);
//		if (tnode == null)
//			return false;
//		if (tnode instanceof DoStatement || tnode instanceof PardoStatement)
//			return true;
//		if (tnode instanceof ProcDec) {
////			ArrayList<CallStatement> callSites = getCallSites(dec,
////					(ProcDec) tnode); // if here, tnode is ProcDec
////			Iterator<CallStatement> iter = callSites.iterator();
////			while (iter.hasNext()) {
////				if (!isDefinedInEnclosingScope(dec, iter.next()))
////					return false;
////			}
////			return true;
//			indexAtCallSitesToCheck.add(new IndexAtCallSites(dec, (ProcDec) tnode));
//		}
//		return true;
//	}
//	
//	static List<IndexAtCallSites> indexAtCallSitesToCheck = new ArrayList<IndexAtCallSites>();
//	public static boolean checkCallSites(){
//		Iterator<IndexAtCallSites> iter = indexAtCallSitesToCheck.iterator();
//		while(iter.hasNext()){
//			if ( !iter.next().check() ) return false;
//		}
//		return true;
//	}
//	
//	static class IndexAtCallSites{
//		ArrayList<CallStatement> callSites;
//		ProcDec procDec;
//		Ident ident;
//		IndexDec indexDec;
//
//		
//		IndexAtCallSites(Ident ident, IndexDec indexDec, ProcDec procDec){
//			callSites = getCallSites(indexDec, procDec);
//			this.ident = ident;
//			this.procDec = procDec;
//			this.indexDec = indexDec;
//		}
//		
//		boolean checkCallSite(){
//			Iterator<CallStatement> iter = callSites.iterator();
//			while (iter.hasNext()) {
//				CallStatement callStatement = iter.next();
////				if (!isDefinedInEnclosingScope(ident, callStatement)){
////					System.out.println("checking call site  " + callStatement + " for index " + indexDec);
////					return false;
////				}
//				if (!check(isDefinedInEnclosingScope(ident, callStatement), ident, "index " + ident + " not defined at call site " + callStatement + " at line " + callStatement.getLeftIToken().getLine())
//						
//			}
//			return true;
//		}
//	}

	public static String stripName(String rawId) {
        int idx= rawId.indexOf('$');

        return (idx >= 0) ? rawId.substring(0, idx) : rawId;
    }


    /** returns the int value of the given IRangeVal, which is required to be an int literal or negated int literal */
	public static int getIntVal(IRangeVal range1){
		if( range1 instanceof IntLitRangeVal){
		assert range1.getLeftIToken().getKind()== TK_INTLIT: range1.toString();
		String text = range1.toString();
		return Integer.parseInt(text);
		}
		NegRangeVal negRangVal = (NegRangeVal)range1;
		String text = negRangVal.getINTLIT().toString();
		return - Integer.parseInt(text);
	}
	
	/** returns the int value of the given IToken, which is required to an int literal*/
	public static int getIntVal(IToken intlit) {
		assert intlit.getKind()== TK_INTLIT;
		String text = intlit.toString();
		return Integer.parseInt(text);
	}
	
	/** returns the double value of the given AstNode, which is required to be double literal*/
	public static double getDoubleVal(ASTNodeToken node){
		assert node.getIToken().getKind() == TK_DOUBLELIT;
		String text = node.toString();
		return Double.parseDouble(text);	
	}
	
	
	/** returns the double value of the given IToken, which is required to be a double literal */
	public static double getDoubleVal(IToken token){
		assert token.getKind() == TK_DOUBLELIT || token.getKind() == TK_INTLIT;
		String text = token.toString();
		return Double.parseDouble(text);	
	}
	
	
	/** returns a String containing the  qualified name for this declaration */
	public static String getQualifiedName(IDec dec) {
		StringBuilder sb = new StringBuilder();
		Sial declaringProgram = ASTUtils.getRoot(dec);
		if (declaringProgram == null) System.out.println(dec + "has null declaring program");
		if (declaringProgram.isImported()){  //defined in imported file, prefix name with qualifier, the program name
			String progName = declaringProgram.getProgram().getStartName();
			sb.append(progName);
			sb.append('.');
		}
		String name = "";
		if (dec instanceof ArrayDec) name = ((ArrayDec) dec).getName();
		else if (dec instanceof IndexDec) name = ((IndexDec) dec).getName();
		else if (dec instanceof ScalarDec) name = ((ScalarDec) dec).getName();
		else if (dec instanceof IntDec) name = ((IntDec) dec).getName();
		else if (dec instanceof SubIndexDec) name = ((SubIndexDec) dec).getName();
		else assert false: "getQualifiedName does not support type of dec" + dec;
		sb.append (name);
		return sb.toString();
	}
	
	/** returns the string value of this Token, which is required to be a string literal */
	public static String getStringVal(ASTNodeToken node){
		assert node.getIToken().getKind() == TK_STRINGLIT;
		String text = node.toString();
		return getStringVal(text);
	}
	
	/** removes the quotes etc. for String literals.  Currently, escaped characters
	 * not supported. */
	public static String getStringVal(String text) {
		char[] chars = text.toCharArray();
		StringBuilder builder = new StringBuilder();
		assert chars[0] == '"':"Malformed string literal" ;
		assert chars[chars.length-1] == '"': "Malformed string literal" ;
        int i = 1;
		for (i=1; i< chars.length-1; i++)
		{  char curr = chars[i];
		//maybe do this later
//		   if (curr == '\\'){//the next char is escape
//			   char escape = chars[++i];
//		       switch (escape){
//		       case 't' : curr = '\t'; break;
//		       case 'b' : curr = '\b'; break;
//		       case  'n' : curr = '\n'; break;
//		       case  'r' : curr = '\r'; break;
//		       case  'f' : curr = '\f'; break;
//		       case	 '\\' : curr = '\\'; break;
//		       case   '"' : curr = '"'; break;
//		       default: assert false: "malformed string literal";
//		       }
//		   }
		   builder.append(curr);	       
		}
		return builder.toString();
	}


//	/**  Indicates whether given Idec has the constant modifier */
//    public static boolean isConstant(IDec n){
//    	List modifiers = null;
//    	if (n instanceof ScalarDec)
//    	    modifiers = ((ScalarDec) n).getModifiersopt().getList();
//    	else if (n instanceof IndexDec)
//    		modifiers = ((IndexDec) n).getModifiersopt().getList();
//    	else if (n instanceof IntDec)
//    		modifiers = ((IntDec) n).getModifiersopt().getList();
//    	else if (n instanceof ArrayDec)
//    		modifiers = ((ArrayDec) n).getModifiersopt().getList();
//    	if (modifiers== null || modifiers.isEmpty()) return false;
//    	for( Object m: modifiers){
//    		if (m instanceof ConstantModifier) return true;
//    	}
//    	return false;
//    }	
    
    /** returns true if the given IDec is an array, and is static or contiguous */
    public static boolean isStaticOrContiguousArray(IDec n){
    	if (n instanceof ArrayDec){
    		ArrayDec arrayDec = (ArrayDec)n;
    		if (arrayDec.getArrayKind().toString().equals("static")) return true;
    		//check for contiguous declaration
    		List modifiers = arrayDec.getModifiersopt().getList();
        	for( Object m: modifiers){
        		if (m instanceof ContiguousModifier) return true;
        	}
    	}
    	return false;
    }	    
    
    
	public static boolean isSparseDistributedOrServed(IDec n) {
		if (n instanceof ArrayDec) {
			ArrayDec arrayDec = (ArrayDec) n;
			if (arrayDec.getArrayKind().toString().equals("distributed")
					|| arrayDec.getArrayKind().toString().equals("served")) {
				// check for contiguous declaration
				List modifiers = arrayDec.getModifiersopt().getList();
				for (Object m : modifiers) {
					if (m instanceof SparseModifier)
						return true;
				}
			}
		}
		return false;
	}
    
    public static boolean isPredefined(IDec n){
    	List modifiers = null;
    	if (n instanceof ScalarDec)
    	    modifiers = ((ScalarDec) n).getModifiersopt().getList();
    	else if (n instanceof IndexDec)
    		modifiers = ((IndexDec) n).getModifiersopt().getList();
    	else if (n instanceof IntDec)
    		modifiers = ((IntDec) n).getModifiersopt().getList();
    	else if (n instanceof ArrayDec)
    		modifiers = ((ArrayDec) n).getModifiersopt().getList();
    	else if (n instanceof ScalarDec)
    		modifiers = ((ScalarDec) n).getModifiersopt().getList();
    	if (modifiers== null || modifiers.isEmpty()) return false;
    	for( Object m: modifiers){
    		if (m instanceof PredefinedModifier) return true;
    	}
    	return false;
    }	    
//    public static boolean isPersistent(IDec n){
//    	List modifiers = null;
//    	if (n instanceof ScalarDec)
//    	    modifiers = ((ScalarDec) n).getModifiersopt().getList();
//    	else if (n instanceof IndexDec)
//    		modifiers = ((IndexDec) n).getModifiersopt().getList();
//    	else if (n instanceof IntDec)
//    		modifiers = ((IntDec) n).getModifiersopt().getList();
//    	else if (n instanceof ArrayDec)
//    		modifiers = ((ArrayDec) n).getModifiersopt().getList();
//    	else if (n instanceof ScalarDec)
//    		modifiers = ((ScalarDec) n).getModifiersopt().getList();
//    	if (modifiers== null || modifiers.isEmpty()) return false;
//    	for( Object m: modifiers){
//    		if (m instanceof PersistentModifier) return true;
//    	}
//    	return false;
//    }

    public static int[] getContractedIndices(int[] a1, int[] a2) {
    	if (a1 == null || a2 == null) return new int[TypeConstantMap.max_rank];
    	//size of interesection not more than smallest input array
    	int[] intersection = new int[ a1.length>=a2.length? a2.length : a1.length];
    	int count = 0;
    	//find the intersection we have
    	//this alg is OK for the small number of indices
    	int val1;
    	for (int i = 0; i < a1.length && 0 < (val1 = a1[i]) ; i++){
    		//search for val in a2
    		int j;
    		int val2;
    		for (j = 0; j < a2.length && 0 < (val2 = a2[j]) ; j++){
    			if (val2 == val1){
    				intersection[count++]=val1;
    				break;
    			}
    		}
    	}
    	//sort the nonzero elements
    	Arrays.sort(intersection,0,count);
    	//return, padding with zeros
    	return Arrays.copyOf(intersection, TypeConstantMap.max_rank);
	}
     

}
