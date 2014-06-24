package sial.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import sial.code_gen.Opcode;

public class GenerateLatexOpcodes {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
	
		String table = Opcode.generateLatexTable();
		System.out.println(table);
		Writer writer = new FileWriter("opcode.tex");
		writer.write(table);
		writer.close();
	}
}
