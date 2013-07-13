package sial.utils;

import static java.lang.System.out;

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
import sial.io.SIADataInput;
import sial.io.SIALittleEndianDataInputStream;

/*takes a file name and reads (and prints) the .sio file */

public class SymbolicConstantReader implements SipConstants{

	/**
	 * @param args file name
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String file = args[0];	
		SIADataInput input = new SIALittleEndianDataInputStream(new FileInputStream(file));
		out.println("opened " + args[0]);
		readInput(input);
	}

	public static void readInput(SIADataInput input) throws IOException {
		Header header = Header.readHeader(input);
		//print header
//		out.println(header);
		//print index table
//		out.println("\nindex table:");
		IndexTable indexTable = IndexTable.readIndexTable(header.getMx_nindex_table(),input);
//		out.println(indexTable);
		indexTable = null;
		//print array table
//		out.println("array table:");
		ArrayTable arrayTable = ArrayTable.readArrayTable(header.getMx_narray_table(),input);
//		out.println(arrayTable);
		arrayTable = null;
		//print op table
//		out.println("optable:");
		OpTable opTable = OpTable.readOpTable(header.getMx_noptable(),input);
//		out.println(opTable);
		opTable = null;
		//print scalar table
//		out.println("scalar table:");
		ScalarTable scalarTable= ScalarTable.readScalarTable(header.getMx_scalar_table(),input);
//		out.println(scalarTable);
//		out.println("\nspecial instruction table:");
		SpecialInstructionTable specialInstructionTable = null;
		try{
		specialInstructionTable = SpecialInstructionTable.readSpecialInstructionTable(input);
//		out.println(specialInstructionTable);
		out.println("\nsymbolic constants:");
		ScalarTable.readConstants(scalarTable,input);
		out.println(scalarTable.enumeratedConstantsToString());
		}
		catch (EOFException e){
			out.println("unexpected EOF");
		}                                                    

		} 
	
	}    
