package sial.utils;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import com.google.common.io.LittleEndianDataInputStream;

import sial.imp.code_gen.ArrayTable;
import sial.imp.code_gen.IndexTable;
import sial.imp.code_gen.Header;
import sial.imp.code_gen.OpTable;
import sial.imp.code_gen.ScalarTable;
import sial.imp.code_gen.SipTypeConstants;
import static java.lang.System.out;

/*takes a file name and reads (and prints) the .sio file */

public class SIOCompare implements SipTypeConstants{

	/**
	 * @param args file name
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String fileExpected = "sio_files/" + arg[0] + ".sio";
		String fileGenerated = "sial_files/" + arg[0] + ".siox";
		DataInput inputExpected = 
				new LittleEndianDataInputStream(new FileInputStream(fileExpected));
		DataInput inputProduced = 
				new LittleEndianDataInputStream(new FileInputStream(fileProduced));
		boolean compareInput(inputExpected, inputProduced);
	}

	private static boolean compareInput(DataInput inputExpected,
			DataInput inputProduced) throws IOException {
		boolean consistent = true;
		Header headerExpected = Header.readHeader(inputExpected);
		Header headerProduced = Header.readHeader(inputProduced);
		AssertTrue(Header.compare(headerExpected,headerProduced));
		IndexTable indexExpected = IndexTable.readIndexTable()
	}

	public static void readInput(DataInput input) throws IOException {
		Header header = Header.readHeader(input);
		//print header
		System.out.println(header);
		//print index table
		System.out.println("index table:");
		IndexTable indexTable = IndexTable.readIndexTable(header.getMx_nindex_table(),input);
		System.out.println(indexTable);
		//print array table
		System.out.println("array table:");
		ArrayTable arrayTable = ArrayTable.readArrayTable(header.getMx_narray_table(),input);
		System.out.println(arrayTable);
		//print op table
		System.out.println("optable:");
		OpTable opTable = OpTable.readOpTable(header.getMx_noptable(),input);
		System.out.println(opTable);
		//print scalar table
		System.out.println("scalar table:");
		ScalarTable scalarTable= ScalarTable.readScalarTable(header.getMx_scalar_table(),input);
		System.out.println(scalarTable);
		//check for EOF
		try{
		int eof = input.readInt();  
		System.out.println("Finished reading before EOF");
		}
		catch(EOFException e){}
	}    
}