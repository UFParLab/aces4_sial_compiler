package sial.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedHashSet;


/**
 * This class manages the sialpath, which may be specified in several ways,
 * including the command line (-sp or -sialpath), a system property, or system
 * environment variable, with searching done in the order given (i.e. command
 * line first, etc.)
 */

public class SialPath {
	LinkedHashSet<File> dirs; // ordered set of canonical File objects representing directories
								// to search for sial source code
	File workingDir;

	public SialPath() throws IOException {
		String separator = File.pathSeparator;
		dirs = new LinkedHashSet<File>();
		String workingDirProperty = System.getProperty("user.dir");
		assert workingDirProperty != null;
		workingDir = new File(workingDirProperty);
		dirs.add(workingDir.getCanonicalFile());
		String propertyPath = System.getProperty("SIALPATH");
		if (propertyPath != null) {
			String[] propPaths = propertyPath.split(separator);
			for (String s : propPaths) {
				File fs = new File(s);
				assert fs.isDirectory() : "SIALPATH property contains invalid path "
						+ s;
				dirs.add(fs.getCanonicalFile());
			}
		}

		String envPath = System.getenv("SIALPATH");
		if (envPath != null) {
			String[] envPaths = envPath.split(separator);
			for (String s : envPaths) {
				File fs = new File(s);
				assert fs.isDirectory() : "env var SAILPATH  contains invalid path "
						+ s;
				dirs.add(fs.getCanonicalFile());
			}
		}

	}

	/* findFile takes a String representing a filename and returns the canonical File object
	 * for the corresponding file.  The directories in the SIAL path and the working
	 * directory are searched.  
	 * 
	 * If the file does not exist in a directory in the SialPath, a FileNotFoundException is thrown.
	 * 
	 */
	public File findFile(String fileName) throws IOException {
		File file = new File(fileName);
		assert file != null;
		if (file.canRead())
			return file.getCanonicalFile();
		// file not in current directory, search directories in dirs
		Iterator<File> iter = dirs.iterator();
		File searching = file;
		while (!searching.canRead() && iter.hasNext()) {
			searching = new File(iter.next(),fileName);
		}
		if (searching.canRead()) {
			return searching;
		}
		throw new FileNotFoundException(fileName + " not found");
	}

	public File findFile(Path filePath) throws IOException {
		File file = filePath.toAbsolutePath().toFile();
		String fileName = filePath.getFileName().toString();
		assert file != null;
		if (file.canRead())
			return file.getCanonicalFile();
		// file not in current directory, search directories in dirs
		Iterator<File> iter = dirs.iterator();
		File searching = file;
		while (!searching.canRead() && iter.hasNext()) {
			searching = new File(iter.next(),fileName);
		}
		if (searching.canRead()) {
			return searching;
		}
		throw new FileNotFoundException(fileName + " not found");
	}
	/** returns the canonical path of the file with the given file name, if a file with this
	 * name exists in the SialPath.  If not, then an IOException is thrown.
	 */

	//TODO:  should this throw a FileNotFoundException???
	public String findPath(String fileName) throws IOException {
		return findFile(fileName).getCanonicalPath();
	}

	/** adds new paths to the SialPath list.  This is typically called when a path is given 
	 * on the command line
	 * @param commandLinePath
	 * @throws IOException
	 */
	public void addCommandLinePath(String commandLinePath) throws IOException {
		assert commandLinePath != null;
		LinkedHashSet<File> tmp = dirs;
		dirs = new LinkedHashSet<File>();
		dirs.add(workingDir);
		String separator = File.pathSeparator;
		String[] commandLinePaths = commandLinePath.split(separator);
		for (String s : commandLinePaths) {
			File fs = new File(s);
			assert fs.isDirectory() : "env var SAILPATH  contains invalid path "
					+ s;
			dirs.add(fs.getCanonicalFile());
		}
		for (File fs : tmp){
			dirs.add(fs);
		}
	}

	public String toString() {
		return "dirs: " + dirs.toString() + "\nworkingDir: " + workingDir;

	}

}
