package sial.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import sial.code_gen.Opcode;

public class GenerateCPPOpcodes {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String header = Opcode.generateCPPHeader();
		System.out.println(header);
		
		Writer writer = new FileWriter("opcode.h");
		writer.write(header);
		writer.close();

	}

}
