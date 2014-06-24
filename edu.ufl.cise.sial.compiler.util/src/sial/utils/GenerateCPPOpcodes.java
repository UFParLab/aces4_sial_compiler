package sial.utils;

import java.io.FileWriter;
import java.io.Writer;

import sial.code_gen.Opcode;

public class GenerateCPPOpcodes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String header = Opcode.generateCPPHeader();
		System.out.println(header);
		
		Writer writer = new FileWriter("opcode.h");
		writer.write(header);
		writer.close();

	}

}
