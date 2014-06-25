
package sialx1tosialx2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;

import lpg.runtime.IPrsStream;
import sial.compiler.CommandLine;
import sial.compiler.ISialMessageHandler;
import sial.compiler.ImportedFileNotFoundException;
import sial.compiler.SystemErrMessageHandler;
import sial.parser.SialLexer;
import sial.parser.SialParser;
import sial.parser.Ast.Sial;
//import sial.utils.SIOReader;
//import sial.utils.SIOReader.*;

public class Sialx1toSialx2 {

	public Sialx1toSialx2(CommandLine options) {
		this.options = options;
	}

	CommandLine options;

	ISialMessageHandler msgHandler = new SystemErrMessageHandler();
	// IMessageHandler msgHandler = new FileMessageHandler(new File("errors"));

	public static int errs;


	String parseFile(Path file, boolean isImported) throws IOException {
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

		// Need to parse input, setup LPG generated lexer and parser
		SialLexer lexer = null;
		lexer = new SialLexer(canonicalPath, 1); // this should not raise an
													// exception due to error
													// checking in findFile
		SialParser parser = new SialParser();
		parser.reset(lexer.getILexStream());
		IPrsStream prs = parser.getIPrsStream();
		prs.setMessageHandler(msgHandler);
		lexer.lexer(prs); // lex the stream obtain tokens
		Sial ast = (Sial) parser.parser(); // parse to obtain ast
		if (ast == null)
			return null; // no ast, error handled by caller

		//instantiate a visitor to traverse the AST and construct a modified version of the program
		ConvertVisitor visitor = new ConvertVisitor(ast);
		ast.accept(visitor);
		return visitor.getString();
	}


	public static void main(String[] args) throws IOException {
		CommandLine options = new CommandLine(args);

		final String SUFFIX = ".sialx";

		/*
		 * Gets name from command line and constructs list of files to compile
		 * If the given name is a directory, the list will contain all files
		 * with ".sialx" suffix.
		 * 
		 * All the files should compile correctly before using thie program
		 */
		Path inputPath = options.getINPUT_FILE();
		if (!inputPath.toString().endsWith(SUFFIX)) {
			System.err.println(inputPath + " must end with " + SUFFIX);
			System.exit(2);
		}

		Sialx1toSialx2 compiler = new Sialx1toSialx2(options);
		String convertedProg = "";
		try {
			convertedProg = (String) compiler.parseFile(options.getINPUT_FILE(), false);
		} catch (ImportedFileNotFoundException e) {
			compiler.msgHandler.handleFileNotFoundMessage(e.getMessage());
		} catch (IOException e) {
			compiler.msgHandler.handleFileNotFoundMessage(e.getMessage());
		}


		SystemErrMessageHandler handler = (SystemErrMessageHandler) compiler.msgHandler;

		int errors = handler.getErrorCount();
		errs = errors;
		if (errors > 0) {
			System.err.println(errors + (errors <= 1 ? " error." : " errors."));
			if (options.isNO_EXIT_ON_ERROR()) return;
			System.exit(-1);
		}


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
		String outputFileName = sourceName.substring(0, suffixLoc) + "sialx2";
		File outputFile = new File(outputDir, outputFileName);
		Writer writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write(convertedProg);
		writer.close();


	}

}
