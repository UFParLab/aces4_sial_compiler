package sial.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SialToSialX {
	
	public static void main(String[] args) throws IOException{
		//if args[0] is a directory, convert all .sial files in the directory
		//if args[0] is a file with .sial suffix, convert that file
		if ( args.length ==0){
			System.out.println("needs file or directory name on command line") ;
			return;
		}
		String name = args[0].trim();
		File input = new File(name);
		System.out.println( input.getCanonicalPath());
		if (input.isFile() && name.endsWith(".sial")){
			handleSialFile(input);
			return;
		}
		//file is a directory
		assert input.isDirectory(): "input is neither a .sail file or a direcory";
		handleDir(input);
	}
	
	static void handleDir(File input) throws IOException {
		assert input.isDirectory(): "handleDir expected directory";
		File[] files;
		files = input.listFiles();
		/* Process each file */
		for (File file : files) {
			if (file.isDirectory()) handleDir(file);
			else if (file.getName().endsWith("sial")) handleSialFile(file);
		}
	}
	
	static void handleSialFile(File input) throws IOException{
		String name = input.getName();
		if (name.equals("aces_defs.sial") || name.equals("aces_defs.sialx")) return;
		assert input.isFile(): name + ":  handleSialFile expected ordinary file";
		assert name.endsWith(".sial"): name + ": handleSialFile expected   .sial suffix";
		BufferedReader r = new BufferedReader(new FileReader(input));
		File output = new File(input.getCanonicalPath() + "x");
		BufferedWriter w = new BufferedWriter(new FileWriter(output));
		insert_import_aces_defs(name, r,w);
		convert(name,r,w);
		r.close();
		w.close();
	}
	
	
	static final String importString = "import \"aces_defs.sialx\"\n";
	static void insert_import_aces_defs(String name, BufferedReader r, BufferedWriter w) throws IOException{
		
		//insert import statement
		String line0 = r.readLine();
		if(line0 == null){
			System.out.println("File " + name + " is empty.");
			return;
		}
		if (line0.trim() == "#" || line0.trim() == "" || line0.contains("import")){
			//first line is empty, has an empty comment, or already contains an import statement.  Replace it.
			w.append(importString);
		}
		else if (line0.toLowerCase().contains("sial")){
			//first line starts program.  Nothing to do but insert the import statement
			// and mess up the line numbers.  Print a message
			w.append(importString);
			w.append(line0);
			w.newLine();
			System.out.println("Adding import shifted line numbers in " + name);
		}
		else {
			String line1 = r.readLine();
			//invariant all lines prior to line0 have been copied.  line0 and line1 contain the next two lines
			while (line1 != null && !line1.trim().equals("#") && !line1.toLowerCase().contains("sial")){
				w.append(line0);
				w.newLine();
				line0 = line1;
				line1 = r.readLine();
			}
			assert line1 != null:  "no empty comment or opening sial statement in " + name;
			if (line1.trim() != "#"){ //replace line1 with import
				w.append(line0);
				w.newLine();
				w.append(importString);
			}
			else if (line1.toLowerCase().contains("sial")){  //replace line0 with import
				w.append(importString);
				w.append(line1);
				w.newLine();
			}
		}
	}
		/* Go through rest of file and perform the following
		 *       execute sip_barrier  =>   sip_barrier
		 *       execute sip_barrier X => sip_barrier
		 *       execute server_barrier => server_barrier
		 *       execute server_barrier X => server_barrier
		 *       execute print_scalar X => print_scalar X
		 *       compute_integrals => execute compute_integrals
		 */
	public static void convert(String name, BufferedReader r, BufferedWriter w) throws IOException{
		 String line = r.readLine();

		 while (line != null){
			 String lineBeforeComment = null;
			 if (line.toLowerCase().contains("#")){
				 int commentPos = line.indexOf("#");
				 lineBeforeComment = line.substring(0,commentPos);
			 }
			 else {
				 lineBeforeComment = line;
			 }
			 if (lineBeforeComment.toLowerCase().contains("sip_barrier") && lineBeforeComment.toLowerCase().contains("execute")){
				 String command = "sip_barrier";
				 //find comment if any
				 int beforeIndex = line.toLowerCase().indexOf("execute");  
				 if (beforeIndex < 0){
					 w.append(line);  //this should only happen in aces_defs
				 }
				 else{
				 int afterIndex = line.toLowerCase().indexOf(command) + command.length();
				 w.append(line.substring(0,beforeIndex)); //copy initial white space
				 w.append(command); //add command
//				 w.append(line.substring(afterIndex,line.length()));  //copy trailing chars
				 for (int i = afterIndex; i < line.length(); i++){//copy trailing whitespace and comments, but skip arguments
				     char ch = line.charAt(i);
				     if (Character.isWhitespace(ch)) w.append(ch); 
				     else if (ch == '#'){ //start of comment, just append rest of line
				    	 w.append(line.substring(i,line.length()));
				    	 i = line.length();
				     }
				 }
				 }
				 w.newLine();
			 }
			 else  if (lineBeforeComment.toLowerCase().contains("server_barrier") && lineBeforeComment.toLowerCase().contains("execute")){
				 String command = "server_barrier";
				 //find comment if any
				 int beforeIndex = line.toLowerCase().indexOf("execute");  
				 int afterIndex = line.toLowerCase().indexOf(command) + command.length();
				 w.append(line.substring(0,beforeIndex)); //copy initial white space
				 w.append(command); //add command
//				 w.append(line.substring(afterIndex,line.length()));  //copy trailing chars
				 for (int i = afterIndex; i < line.length(); i++){//copy trailing whitespace and comments, but skip arguments
				     char ch = line.charAt(i);
				     if (Character.isWhitespace(ch)) w.append(ch); 
				     else if (ch == '#'){ //start of comment, just append rest of line
				    	 w.append(line.substring(i,line.length()));
				    	 i = line.length();
				     }
				 }
				 w.newLine();
			 }
			 else if (lineBeforeComment.toLowerCase().contains("print_scalar")&& lineBeforeComment.toLowerCase().contains("execute")){
				 int executeStart = line.toLowerCase().indexOf("execute");
					w.append(line.substring(0, executeStart));  //copy part of line before "execute"
				    w.append(line.substring(executeStart + "execute".length(),line.length()));  //copy part of line after "execute"
				    w.newLine();
			 }
			 else if (lineBeforeComment.toLowerCase().contains("compute_integrals") && !lineBeforeComment.toLowerCase().contains("execute")){
				 int beforeIndex = line.toLowerCase().indexOf("compute_integrals");  
				 w.append(line.substring(0,beforeIndex)); //copy initial white space
				 w.append("execute "); 
				 w.append(line.substring(beforeIndex,line.length()));  //copy rest of line (including "compute_integrals")
				 w.newLine();
			 }
			 else {
				 w.append(line);
				 w.newLine();
			 }
			 line = r.readLine();
	}
		

		
	}
	

}
