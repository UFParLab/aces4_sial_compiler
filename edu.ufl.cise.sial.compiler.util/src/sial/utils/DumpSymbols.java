//TODO  fix indirect imports and test various import related feature
//TODO  modify grammar to allow multiple defs on one line, and initialization of
//scalars and ints
package sial.utils;

import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lpg.runtime.IAst;
import lpg.runtime.IPrsStream;
import sial.code_gen.CodeGenVisitor;
import sial.code_gen.SipTable;
import sial.compiler.CommandLine;
import sial.compiler.ISialMessageHandler;
import sial.compiler.ImportedFileNotFoundException;
import sial.compiler.SystemErrMessageHandler;
import sial.parser.SialLexer;
import sial.parser.SialParser;
import sial.parser.Ast.ImportProg;
import sial.parser.Ast.ImportProgList;
import sial.parser.Ast.Sial;
import sial.parser.context.TypeCheckVisitor;
//import sial.utils.SIOReader;
//import sial.utils.SIOReader.*;

public class DumpSymbols {

	public DumpSymbols(CommandLine options) {
		this.options = options;
		astCache = new HashMap<String, Sial>();
	}

	CommandLine options;
	public Map<String, Sial> astCache; // maps canonical paths of source files
										// to ASTs of file contents

	ISialMessageHandler msgHandler = new SystemErrMessageHandler();
	// IMessageHandler msgHandler = new FileMessageHandler(new File("errors"));

	public static int errs;

	// recursively parses the given file and all imported files.
	// if an ast is returned without its symbol table being populated,
	// there is a cyclic import list in the program. The caller
	// should check this.
	IAst parseFile(Path file, boolean isImported) throws IOException {
		// does file exist?
		File f = null;
		try {
			f = options.getPATH().findFile(file);
		} catch (FileNotFoundException e) {
			if (isImported)
				throw new ImportedFileNotFoundException("Imported file " + file
						+ " not found", e);
			else
				throw new ImportedFileNotFoundException("Source file " + file
						+ " not found", e);
		}
		String canonicalPath = f.getCanonicalPath(); // system-wide unique name
														// for file
		// check cache. If already there and valid then just return the AST
		Sial ast = astCache.get(canonicalPath);
		if (ast != null && ast.after(f.lastModified()))
			return ast;

		// Need to parse input, setup LPG generated lexer and parser
		ast = null;
		SialLexer lexer = null;
		lexer = new SialLexer(canonicalPath, 1); // this should not raise an
													// exception due to error
													// checking in findFile
		SialParser parser = new SialParser();
		parser.reset(lexer.getILexStream());
		IPrsStream prs = parser.getIPrsStream();
		prs.setMessageHandler(msgHandler);
		lexer.lexer(prs); // lex the stream obtain tokens
		ast = (Sial) parser.parser(); // parse to obtain ast
		if (ast == null)
			return null; // no ast, error handled by caller

		// An ast was returned, save it in the cache and initialize its fields
		astCache.put(canonicalPath, ast);
		ast.initialize(isImported);

		// parse imported programs and populate their symbol tables
		ImportProgList imports = ast.getImportProgList();
		for (int i = 0; i != imports.size(); i++) {
			ImportProg importedProg = imports.getImportProgAt(i);
			Path importedFilePath = Paths.get(options.getPATH()
					.findPath(importedProg.getName()));
			Sial importedProgAst = (Sial) parseFile(importedFilePath, true);
			if (importedProgAst == null) {
				parser.emitError(importedProg, "error parsing imported file "
						+ importedFilePath);
				continue; // go to next import
			}
			if (!importedProgAst.isSymbolTablePopulated()) {
				parser.emitError(importedProg,
						"illegal cyclic import dependency");
				return null;
			}
			// successfully parsed imported file, add info to this ast and
			// symbol table
			importedProg.setAst(importedProgAst);
			String importedProgQualifier = importedProgAst.getProgram()
					.getStartName();
			ast.getSymbolTable().addImportSymtab(importedProgQualifier,
					importedProgAst.getSymbolTable());
		}


		// populate symbol table for this ast
		TypeCheckVisitor topVisitor = new TypeCheckVisitor(parser);
		ast.accept(topVisitor);

		return ast;
	}

	// Input: Ast representing program being compiled
	// Precondition: the symbol table is populated.
	// Returns: SipTable containing the generated code
	SipTable generate_code(Sial ast) {

		CodeGenVisitor visitor = new CodeGenVisitor(options);
		ast.accept(visitor);

		return visitor.getSipTable();
	}

	public static void main(String[] args) throws IOException {
		CommandLine options = new CommandLine(args);
		if (options.isVERBOSE())
			System.out.println(options.toString());
		final String SUFFIX = options.getSUFFIX();

		/*
		 * Gets name from command line and constructs list of files to compile
		 * If the given name is a directory, the list will contain all files
		 * with ".sialx" suffix
		 */
		Path inputPath = options.getINPUT_FILE();
		if (!inputPath.toString().endsWith(SUFFIX)) {
			System.err.println(inputPath + " must end with " + SUFFIX);
			System.exit(2);
		}

		errs = 0;
		DumpSymbols compiler = new DumpSymbols(options);
		Sial ast = null;
		try {
			ast = (Sial) compiler.parseFile(options.getINPUT_FILE(), false);
		} catch (ImportedFileNotFoundException e) {
			compiler.msgHandler.handleFileNotFoundMessage(e.getMessage());
		} catch (IOException e) {
			compiler.msgHandler.handleFileNotFoundMessage(e.getMessage());
		}

		/* Check for successful parse and output error messages */
		if (ast == null) {
			if (options.isVERBOSE()) System.err.println(options.getINPUT_FILE() + ": parsing failed.  ");
		} else if (!ast.isSymbolTablePopulated()) {
			System.err.println(options.getINPUT_FILE() +": illegal cyclic import dependency");
		} else {
			Set<String> processedFiles = compiler.astCache.keySet();
			if(options.isVERBOSE()) System.out.println("Parsed files: " + processedFiles);
		}
		SystemErrMessageHandler handler = (SystemErrMessageHandler) compiler.msgHandler;

		int errors = handler.getErrorCount();
		errs = errors;
		if (errors > 0) {
			System.err.println(errors + (errors <= 1 ? " error." : " errors."));
			System.exit(errors);

		}

		else if (!options.isNO_GENERATE()) {
			// generate the code
			SipTable sipTable = compiler.generate_code(ast);

        //dump symbols to standard output.

			//print index table
			out.println("\nindex table:");
			out.println(sipTable.getIndexTable());

			//print array table
			out.println("array table:");
	
			out.println(sipTable.getArrayTable().toStringWithFortranIndices());

			//print op table
			out.println("optable:");
			out.println(sipTable.getOpTable());

			//print scalar table
			out.println("scalar table:");
			out.println(sipTable.getScalarTable().toStringScalarWithFortranIndices());
			out.println("\nspecial instruction table:");
			out.println(sipTable.getSpecialInstructionTable());
			out.println("\nsymbolic constants:");
			out.println(sipTable.getScalarTable().constantsToString());			
		
		}

	}
}
