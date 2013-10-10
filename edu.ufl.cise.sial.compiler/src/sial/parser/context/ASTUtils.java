//this class was adapted from org.eclipse.imp.lpg.parser.ASTUtils


package sial.parser.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
import sial.parser.Ast.ConstantModifier;
import sial.parser.Ast.IASTNodeToken;
import sial.parser.Ast.IDec;
import sial.parser.Ast.IRangeVal;
import sial.parser.Ast.ImportProg;
import sial.parser.Ast.ImportProgList;
import sial.parser.Ast.IndexDec;
import sial.parser.Ast.IntDec;
import sial.parser.Ast.PersistentModifier;
import sial.parser.Ast.PredefinedModifier;
import sial.parser.Ast.ProcDec;
import sial.parser.Ast.Program;
import sial.parser.Ast.ScalarDec;
import sial.parser.Ast.Sial;
import sial.parser.Ast.SubIndexDec;

public class ASTUtils implements SialParsersym{
	private ASTUtils(){}
	
	public static Sial getRoot(IAst node){
        while (node != null && !(node instanceof Sial))
            node= node.getParent();
        return (Sial) node;
    }
	
	public static Program getEnclosingProgram(IAst node){
        while (node != null && !(node instanceof Program))
            node= node.getParent();
        return (Program) node;
    }
	
	public static ProcDec getEnclosingProc(IAst node){
		IAst tnode = node.getParent();
		while (tnode != null && !(tnode instanceof ProcDec))
			tnode = tnode.getParent();
		return (ProcDec) tnode;
	}
	
	public static Sial getRoot(IDec dec){
		return getRoot( (IAst)dec);
    }
	
    public static String stripName(String rawId) {
        int idx= rawId.indexOf('$');

        return (idx >= 0) ? rawId.substring(0, idx) : rawId;
    }

    
//TODO if needed, move to ide specific package
//    //returns names of imported files
//    //we are importing files, not including them, so
//    //we do not collect names of files imported by imports.
//    //this is different than LPG's included files
//    protected static List<String> collectImportedFiles(Sial root, ICompilationUnit refUnit){
//    	List<String> result = new ArrayList<String>();
//    	ImportProgList imports = root.getImportProgList();
//    	for (int i=0; i != imports.size(); i++){
//    		ImportProg imported = imports.getImportProgAt(i);
//    		String fileName = getStringVal(imported.getSTRINGLIT());
//    		System.out.println("STRINGLIT: " + imported.getSTRINGLIT() + " file name: "
//    		 + fileName);
//    		result.add(fileName);
//    	}
//    	return result;
//    }
    
    //finds definition by looking in imported files.  Should
    //only be called after lookup in own symbol table has failed
    //TODO recondsider return type
//    public static Object findDefOf(IASTNodeToken s, Sial root, ICompilationUnit refUnit, IProgressMonitor monitor){
//    	String id = stripName(s.toString());
//    	List<String> importedFiles = collectImportedFiles(root, refUnit);
//    	for( String fileName: importedFiles){
//    		Sial importedRoot = (Sial) findAndParseSourceFile(refUnit.getProject(),refUnit.getPath(),fileName, monitor);
//    		if (importedRoot != null){
//    			//TODO  consider types stored in SymbolTable
//    			//and the type here.  Should it be a Dec?
//    			ASTNode decl = (ASTNode) importedRoot.getSymbolTable().lookup(id);
//    			if (decl != null) return decl;
//    		}
//    	}
//    	return null;
//    }
    
    
    //TODO consider return type of this (and above) and
    //type stored in symbol table
//    public static Object findDefOf(IASTNodeToken s, Sial root, IParseController parseController){
//    	String id = stripName(s.toString());
//    	ASTNode decl = (ASTNode) root.getSymbolTable().get(id);
//    	if (decl == null){
//    		assert ((ASTNodeToken) s).getParent() != decl: 
//    			"token was own decl, see line 165 in org.eclipse.imp.lpg.parser.ASTUtils";
//    	
//    	//now look in imported files
//    	Object def = findDefOf(s, root, 
//    			ModelFactory.open(parseController.getPath(),parseController.getProject()),//creates an ICompilationUnit
//    					new NullProgressMonitor());
//    	return def;
//    	}
//    	return decl;
//    }
    
    
    

//	private static Object findAndParseSourceFile(ISourceProject project,
//			IPath path, String fileName, IProgressMonitor monitor) {
//		ICompilationUnit unit = lookupSourceFile(project, path, fileName);
//		if (unit != null)
//			return unit.getAST(null, monitor);
//		return null;
//	}

	
//	private static ICompilationUnit lookupSourceFile(ISourceProject project,
//			IPath refLocation, String filePath) {
//        // Can an ICompilationUnit refer to non-existent cu???
//
//        // First try to find the file relative to the referencing location
//        ICompilationUnit icu= ModelFactory.open(project.getRawProject()
//                .getFile(
//                        refLocation.removeFirstSegments(1)
//                                .removeLastSegments(1).append(filePath)),
//                project);
//
//        if (icu == null)
//            icu= ModelFactory.open(new Path(filePath), project);
//
//        return icu;
//	}
    
	public static int getIntVal(IRangeVal range1){
		assert range1.getLeftIToken().getKind()== TK_INTLIT: range1.toString();
		String text = range1.toString();
		return Integer.parseInt(text);
	}
	
	public static int getIntVal(IToken intlit) {
		assert intlit.getKind()== TK_INTLIT;
		String text = intlit.toString();
		return Integer.parseInt(text);
	}
	
	public static double getDoubleVal(ASTNodeToken node){
		assert node.getIToken().getKind() == TK_DOUBLELIT;
		String text = node.toString();
		return Double.parseDouble(text);	
	}
	
	public static double getDoubleVal(IToken token){
		assert token.getKind() == TK_DOUBLELIT || token.getKind() == TK_INTLIT;
		String text = token.toString();
		return Double.parseDouble(text);	
	}
	
//	//this removes the quotes, handles escape chars, etc. for String literals    
//	public static String getStringVal(String text){
//		char[] chars = text.toCharArray();
//		StringBuilder builder = new StringBuilder();
//		assert chars[0] == '"':"Malformed string literal" ;
//		assert chars[chars.length-1] == '"': "Malformed string literal" ;
//        int i = 1;
//		for (i=1; i< chars.length-1; i++)
//		{  char curr = chars[i];
//		//maybe do this later
////		   if (curr == '\\'){//the next char is escape
////			   char escape = chars[++i];
////		       switch (escape){
////		       case 't' : curr = '\t'; break;
////		       case 'b' : curr = '\b'; break;
////		       case  'n' : curr = '\n'; break;
////		       case  'r' : curr = '\r'; break;
////		       case  'f' : curr = '\f'; break;
////		       case	 '\\' : curr = '\\'; break;
////		       case   '"' : curr = '"'; break;
////		       default: assert false: "malformed string literal";
////		       }
////		   }
//		   builder.append(curr);
//		}
//		   return builder.toString();
//	}
	
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
//		System.out.println("getQualifiedName: " +declaringProgram.getProgram().getStartName() + "." + name);
		return sb.toString();
	}
	
	//this removes the quotes, handles escape chars, etc. for String literals
	public static String getStringVal(ASTNodeToken node){
		assert node.getIToken().getKind() == TK_STRINGLIT;
		String text = node.toString();
		return getStringVal(text);
	}
	
	//this removes the quotes, handles escape chars, etc. for String literals
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


	/*  Checks for declaration modifiers */
    public static boolean isConstant(IDec n){
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
    		if (m instanceof ConstantModifier) return true;
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
    public static boolean isPersistent(IDec n){
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
    		if (m instanceof PersistentModifier) return true;
    	}
    	return false;
    }

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
