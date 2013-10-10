package sial.compiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * This CommandLineOptions class encapsulates the command line options that can
 * be passed to the SIAL compiler.
 * 
 * The String array obtained from the command line is passed to the constructor,
 * which decodes the elements and sets the corresponding variables
 * appropriately. The default values are given below.
 * 
 * Note that PATH is an instance of a SialPath class, which manages this value.
 * 
 * OUTPUT_DIR and INPUT_FILE_OR_DIR are stored as File object.
 * 
 * Note:  these options should work, but have not been tested with aces4.
 */

public// TODO check default value for OUTPUT_DIR. null or "".
class CommandLine {
	private boolean BIG_ENDIAN = false;
	private boolean VERBOSE = false;
	private boolean NO_GENERATE = false; // if true, only check. Do not generate code.
	boolean ACES = true; // this is used with aces
	private String SUFFIX = "sialx"; // when given a directory, process files with this
								// suffix
	private SialPath PATH; // where to look for imported files
	private Path OUTPUT_DIR;
	private Path INPUT_FILE;

	public CommandLine(String[] args) throws IOException {
		setPATH(new SialPath());
		if (args == null || args.length == 0) {
			err.println("java SialCompiler [options] fileOrDir\n"
					+ "java SialCompiler -help prints options");
			System.exit(0);
		}
		setINPUT_FILE(null);
		for (int i = 0; i < args.length; i++) {
			if (args[i].charAt(0) == '-') {
				if (args[i].equals("-bigEndian") || args[i].equals("-b")) {
					setBIG_ENDIAN(true);
					err.println("warning:  big_endian feature has not been thoroughly tested.");
				} else if (args[i].equals("-noGenerate")
						|| args[i].equals("-n"))
					setNO_GENERATE(true);
				else if (args[i].equals("-verbose") || args[i].equals("-v"))
					setVERBOSE(true);
				else if (args[i].equals("-sialPath") || args[i].equals("-sp")) {
					++i;
					if (i >= args.length) {
						err.println("java SialCompiler [options] fileOrDir\n"
								+ "java SialCompiler -help prints options");
						System.exit(0);
					}
					String paths = args[i];
					getPATH().addCommandLinePath(paths);
				} else if (args[i].equals("-suffix") || args[i].equals("-e")) {
					++i;
					if (i >= args.length) {
						err.println("java SialCompiler [options] fileOrDir\n"
								+ "java SialCompiler -help prints options");
						System.exit(0);
					}
					setSUFFIX(args[i]);
					assert getSUFFIX().equals("sialx");
				} else if (args[i].equals("-outputDir") || args[i].equals("-o")) {
					setOUTPUT_DIR(Paths.get(args[++i]));

				} else if (args[i].equals("-help") || args[i].equals("-h")) {
					out.println("java [options] fileOrDir\n"
							+ "-b, -bigEndian :  generate output in big Endian format\n"
							+ "-n, -noGenerate     :  do not generate and write .sio file\n"
							+ "-v, -verbose      :  verbose output\n"
							+ "-sp [pathlist], -sialpath [pathlist] : specify paths to look for imported files.  Paths are separated with colon"
					);
					System.exit(0);
				}
			} else {
				if (getINPUT_FILE() == null) {
					setINPUT_FILE(Paths.get(args[i]));
				} else { // multiple file names in argument list
					err.println("error: multiple file or directory names");
					System.exit(0);
				}
			}
		}
		if (isVERBOSE()) {
			System.out.println("input path = " + getINPUT_FILE().toString());
		}
		assert getINPUT_FILE() != null;
	}

	public CommandLine() {
		// creates options with default values. For IDE.
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("BIG_ENDIAN: " + isBIG_ENDIAN());
		sb.append("\nVERBOSE: " + isVERBOSE());
		sb.append("\nNO_GENERATE: " + isNO_GENERATE());
		sb.append("\nACES: " + ACES);
		sb.append("\nSUFFIX: " + getSUFFIX());
		sb.append("\nPATH: " + getPATH());
		sb.append("\nOUTPUT_DIR: " + getOUTPUT_DIR());
		sb.append("\nINPUT_FILE: " + getINPUT_FILE());
		return sb.toString();
	}

	public SialPath getPATH() {
		return PATH;
	}

	public void setPATH(SialPath pATH) {
		PATH = pATH;
	}

	public boolean isVERBOSE() {
		return VERBOSE;
	}

	public void setVERBOSE(boolean vERBOSE) {
		VERBOSE = vERBOSE;
	}

	public String getSUFFIX() {
		return SUFFIX;
	}

	public void setSUFFIX(String sUFFIX) {
		SUFFIX = sUFFIX;
	}

	public Path getINPUT_FILE() {
		return INPUT_FILE;
	}

	public void setINPUT_FILE(Path iNPUT_FILE) {
		INPUT_FILE = iNPUT_FILE;
	}

	public boolean isNO_GENERATE() {
		return NO_GENERATE;
	}

	public void setNO_GENERATE(boolean nO_GENERATE) {
		NO_GENERATE = nO_GENERATE;
	}

	public boolean isBIG_ENDIAN() {
		return BIG_ENDIAN;
	}

	public void setBIG_ENDIAN(boolean bIG_ENDIAN) {
		BIG_ENDIAN = bIG_ENDIAN;
	}

	public Path getOUTPUT_DIR() {
		return OUTPUT_DIR;
	}

	public void setOUTPUT_DIR(Path oUTPUT_DIR) {
		OUTPUT_DIR = oUTPUT_DIR;
	}

}
