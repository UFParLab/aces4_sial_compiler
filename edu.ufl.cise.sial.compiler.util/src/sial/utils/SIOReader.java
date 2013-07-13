package sial.utils;

import static java.lang.System.out;
import static java.lang.System.err;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import sial.code_gen.ArrayTable;
import sial.code_gen.Header;
import sial.code_gen.IndexTable;
import sial.code_gen.OpTable;
import sial.code_gen.ScalarTable;
import sial.code_gen.SipConstants;
import sial.code_gen.SpecialInstructionTable;
import sial.code_gen.StringLiteralTable;
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
		Header header = Header.readHeader(input);
		//print header
		out.println(header);
		//print index table
		out.println("\nindex table:");
		IndexTable indexTable = IndexTable.readIndexTable(header.getMx_nindex_table(),input);
		out.println(indexTable);
		indexTable = null;
		//print array table
		out.println("array table:");
		ArrayTable arrayTable = ArrayTable.readArrayTable(header.getMx_narray_table(),input);
		out.println(arrayTable);
		arrayTable = null;
		//print op table
		out.println("optable:");
		OpTable opTable = OpTable.readOpTable(header.getMx_noptable(),input);
		out.println(opTable);
		opTable = null;
		//print scalar table
		out.println("scalar table:");
		ScalarTable scalarTable= ScalarTable.readScalarTable(header.getMx_scalar_table(),input);
		out.println(scalarTable);
		//print literal table
		out.println("\nstring literal table: ");		
		StringLiteralTable stringLiteralTable = StringLiteralTable.readStringLiteralTable(input);
		out.println(stringLiteralTable);
		stringLiteralTable = null;
		//special instructions
		out.println("\nspecial instruction table:");
		SpecialInstructionTable specialInstructionTable = SpecialInstructionTable.readSpecialInstructionTable(input);
		out.println(specialInstructionTable);
		specialInstructionTable = null;
        //array names
		out.println("\narray names:");
		int narrays = input.readInt();
		for (int i = 0; i < narrays; ++i){
			String s = input.readString();
			out.println(s);
		}
		//index names
		out.println("\nindex names:");
		int nindex = input.readInt();
		for (int i = 0; i < nindex; ++i){
			String s = input.readString();
			out.println(s);
		}
		//constants
		out.println("\nsymbolic constants:");
		ScalarTable.readConstants(scalarTable, input);
		out.println(scalarTable.constantsToString());
		
		}
		catch (EOFException e){
			out.println("unexpected EOF");
		}                                                    

		} 
	
	}    
