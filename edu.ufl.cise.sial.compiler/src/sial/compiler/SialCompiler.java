//TODO  fix indirect imports and test various import related feature
//TODO  modify grammar to allow multiple defs on one line, and initialization of
//scalars and ints
package sial.compiler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lpg.runtime.IAst;
import lpg.runtime.IPrsStream;
import sial.code_gen.CodeGenVisitor;
import sial.code_gen.SipTable;
import sial.io.SIADataOutput;
import sial.io.SIADataOutputStream;
import sial.io.SIALittleEndianDataOutputStream;
import sial.parser.SialLexer;
import sial.parser.SialParser;
import sial.parser.Ast.ImportProg;
import sial.parser.Ast.ImportProgList;
import sial.parser.Ast.Sial;
import sial.parser.context.TypeCheckVisitor;
//import sial.utils.SIOReader;
//import sial.utils.SIOReader.*;

public class SialCompiler {

	public SialCompiler(CommandLine options) {
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
			Path importedFilePath = Paths.get(options.getPATH().findPath(
					importedProg.getName()));
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

		Timer parseTimer = new Timer();
		// populate symbol table for this ast
		TypeCheckVisitor topVisitor = new TypeCheckVisitor(parser);
		ast.accept(topVisitor);
		parseTimer.printElapsed(options.isVERBOSE(), "parse time for File: "
				+ file);
		return ast;
	}

	// Input: Ast representing program being compiled
	// Precondition: the symbol table is populated.
	// Returns: SipTable containing the generated code
	SipTable generate_code(Sial ast) {
		Timer codeGenTimer = new Timer();
		CodeGenVisitor visitor = new CodeGenVisitor(options);
		ast.accept(visitor);
		codeGenTimer.printElapsed(options.isVERBOSE(), "codeGen time");
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
		SialCompiler compiler = new SialCompiler(options);
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
			if (options.isVERBOSE())
				System.err.println(options.getINPUT_FILE()
						+ ": parsing failed.  ");
		} else if (!ast.isSymbolTablePopulated()) {
			System.err.println(options.getINPUT_FILE()
					+ ": illegal cyclic import dependency");
		} else {
			Set<String> processedFiles = compiler.astCache.keySet();
			if (options.isVERBOSE())
				System.out.println("Parsed files: " + processedFiles);
		}
		SystemErrMessageHandler handler = (SystemErrMessageHandler) compiler.msgHandler;

		int errors = handler.getErrorCount();
		errs = errors;
		if (errors > 0) {
			System.err.println(errors + (errors <= 1 ? " error." : " errors."));
			return;
		}

		if (options.isNO_GENERATE()) {
			return;
		}
		// generate the code
		SipTable sipTable = compiler.generate_code(ast);

		// set up output file
		// if not otherwise specified, use same directory as input
		File outputDir;
		if (options.getOUTPUT_DIR() == null) {
//			System.out.println("inputPath = " + inputPath);
			outputDir = inputPath.toAbsolutePath().getParent().toFile();
		} else {
			outputDir = options.getOUTPUT_DIR().toFile();
		}
		String sourceName = inputPath.getFileName().toString();
		assert !sourceName.equals("");
		int suffixLoc = sourceName.lastIndexOf(options.getSUFFIX());
		String outputFileName = sourceName.substring(0, suffixLoc) + "siox";
		File outputFile = new File(outputDir, outputFileName);
		SIADataOutput out = null;
		// Using a BufferedOutputStream is crucial for reasonable
		// performance on large source files.
		BufferedOutputStream fileOutputStream = new BufferedOutputStream(
				new FileOutputStream(outputFile));
		if (options.isBIG_ENDIAN()) {
			out = new SIADataOutputStream(fileOutputStream);
		} else {
			out = new SIALittleEndianDataOutputStream(fileOutputStream);
		}

		Timer outputTimer = new Timer();
		// write the sipTable
//		System.out.println("writing sip Table ");
//		System.out.println(sipTable.toString());
		sipTable.write(out);
		outputTimer.printElapsed(options.isVERBOSE(), "Output time for file "
				+ outputFileName);

		if (options.isVERBOSE())
			System.out.println("Output written to file "
					+ outputFile.getAbsolutePath());
		fileOutputStream.close();
		if (options.isBIG_ENDIAN()) {
			((SIADataOutputStream) out).close();
		} else {
			((SIALittleEndianDataOutputStream) out).close();
		}

	}

}
