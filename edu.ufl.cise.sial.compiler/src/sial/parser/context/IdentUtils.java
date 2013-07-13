package sial.parser.context;

public class IdentUtils {
	
	
	//the string is legal identifier
	public static boolean isWellFormed(String ident){
		//a dot is not the first or the last character and there is at most one of them
		int firstDotPos = ident.indexOf('.');  
		int lastDotPos = ident.lastIndexOf('.');
		return firstDotPos != 0 && lastDotPos != ident.length()-1 && firstDotPos==lastDotPos;
	}
	
	//the string is a simple name without a qualifier
	public static boolean isSimple(String ident){
		return ident.indexOf('.')<0;
	}
	
	//the string is qualified name
	public static boolean isQualified(String ident){
		return isWellFormed(ident) && ident.indexOf('.') >= 0;
	}
	
	//returns the simple name, removing the qualifier
	//precondition:  name is well-formed
	public static String getSimpleName(String ident){
		 assert isWellFormed(ident);
		 int dotPos = ident.indexOf('.');
		 if (dotPos < 0) return ident;
		 return ident.substring(dotPos+1);
	}
	
	
	//returns the qualifier
	//precondition:  name is qualified
	public static String getQualifier(String ident){
		assert isQualified(ident);
		return ident.substring(0, ident.indexOf('.'));
	}

}
