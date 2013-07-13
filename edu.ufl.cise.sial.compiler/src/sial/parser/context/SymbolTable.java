package sial.parser.context;

import static sial.parser.context.IdentUtils.getQualifier;
import static sial.parser.context.IdentUtils.getSimpleName;
import static sial.parser.context.IdentUtils.isSimple;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import sial.parser.Ast.IDec;
import sial.parser.Ast.Sial;

/**
 * This class manages names defined in SIAL programs.  There is one instance per AST.  
 * @author Beverly Sanders
 */

public class SymbolTable {
	public Sial parentAst;  
	public String qualifier; // name of program, also the qualifier for this symbol table.
	private LinkedHashMap<String, IDec> symtab;
	private LinkedHashMap<String, SymbolTable> imports; //for convenience, maintain 
	                                      // map associating imported program name (and thus its qualifier) to its
										  //symbol table.
	boolean _symbolTablePopulated; // initially false

	public SymbolTable(Sial parentAst) {
		symtab = new LinkedHashMap<String, IDec>();
		imports = new LinkedHashMap<String, SymbolTable>();
		this.parentAst = parentAst;
		_symbolTablePopulated = false;
		this.qualifier = parentAst.getProgram().getStartName();
	}
	
	// Add a program to the map of imports.  This should be called as each
	//imported program's ast is generated.  
	public SymbolTable addImportSymtab(String qualifier, SymbolTable table) {
		return imports.put(qualifier, table);
	}

	public void set_symbolTablePopulated(boolean b) {
		_symbolTablePopulated = b;
	}

	public boolean isSymbolTablePopulated() {
		return _symbolTablePopulated;
	}

	// looks up the name and return declaration of the name if declared in
	// current program
	// returns null if not in this program
	public IDec simpleLookup(String name) {
		return symtab.get(name);
	}

	public IDec lookup(String name) throws AmbiguousNameException {
		IDec dec;
		if (isSimple(name)) {  //name is unqualified
			// first, check this symbol table
			dec = simpleLookup(name);
			if (dec != null)
				return dec;
			// not found, so check imported programs
			int n = imports.size();
			if (n == 0)
				return null;
			if (n == 1) {
				for (SymbolTable table : imports.values()) {
					assert table.isSymbolTablePopulated():  "attempting to look up value in uninitialized symbol table";
					return table.simpleLookup(name);
				}
			}
			// there are at least two imports, check for duplicate declarations
			List<IDec> defList = new ArrayList<IDec>(n); //holds all decs found in imports
			for (SymbolTable table : imports.values()) {
				IDec importedDec = table.simpleLookup(name);
				if (importedDec != null) {
					defList.add(importedDec);
				}
			}
			if (defList.size() == 0) // no matching dec found
				return null;
			if (defList.size() == 1) // only one IDec found, return it
				return defList.get(0);
			// duplicate declarations of same name throw
			// AmbiguousNameException
			String fileName0 = defList.get(0).getLeftIToken().getILexStream()
					.getFileName();
			String fileName1 = defList.get(1).getLeftIToken().getILexStream()
					.getFileName();
			throw new AmbiguousNameException(name + " multiply defined in "
					+ fileName0 + " and " + fileName1 + ". Use qualified name\n");

		} else {// this name is qualified
			String prefix = getQualifier(name);
			String simpleName = getSimpleName(name);
			SymbolTable table;
			if (qualifier.equals(prefix))  //look up in this table 
				table = this;
			else
				table = imports.get(prefix);  //look up in the table 
			if (table == null)
				return null;
			return table.lookup(simpleName);
		}

	}

	public boolean insert(String name, IDec dec)  {
		try {
			if (lookup(name) != null)
				return false;
		} catch (AmbiguousNameException e) {
			//if this exception is thrown, there are at least three declarations
			//of name.  Just return false, if only one is removed, the remaining
			//duplication will be caught on the next attempt.
			return false;
		}
		symtab.put(name, dec);
		return true;
	}


}


