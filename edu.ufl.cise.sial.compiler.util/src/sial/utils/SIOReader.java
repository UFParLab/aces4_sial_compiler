package sial.utils;

import static java.lang.System.err;
import static java.lang.System.out;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import sial.code_gen.SipConstants;
import sial.code_gen.SipTable;
import sial.io.SIADataInput;
import sial.io.SIALittleEndianDataInputStream;

/*takes a file name and reads (and prints) the .sio file */

public class SIOReader implements SipConstants{

	/**
	 * @param args file name
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if (args.length>0){
		String file = args[0];	
		SIADataInput input = new SIALittleEndianDataInputStream(new FileInputStream(file));
		out.println("opened " + args[0]);
		readInput(input);
		}
		else err.println("missing .siox file name argument");
	}

	public static void readInput(SIADataInput input) throws IOException {
		try{
			SipTable sipTable = SipTable.readSipTable(input);
			out.println(sipTable.toStringFromInputFile());
		}
		catch (EOFException e){
			out.println("unexpected EOF");
		}                                                    

		} 
	
	}    
