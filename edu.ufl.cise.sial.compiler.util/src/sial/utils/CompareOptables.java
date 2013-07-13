package sial.utils;

import static java.lang.System.out;

import java.io.DataInput;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import sial.code_gen.ArrayTable;
import sial.code_gen.Header;
import sial.code_gen.IndexTable;
import sial.code_gen.OpTable;
import sial.code_gen.ScalarTable;
import sial.code_gen.SipConstants;

import com.google.common.io.LittleEndianDataInputStream;

/*takes a file name and reads (and prints) the .sio file */

public class CompareOptables implements SipConstants{

	/**
	 * @param args file name
	 * @throws IOException 
	 */
	public static final int MAX_ERRORS_TO_PRINT = 10;
	public static void main(String[] args) throws IOException {
		String fileExpected = "sio_files/" + args[0] + ".sio";
		String fileProduced = "sial_files/" + args[0] + ".siox";
		DataInput inputExpected = 
				new LittleEndianDataInputStream(new FileInputStream(fileExpected));
		DataInput inputProduced = 
				new LittleEndianDataInputStream(new FileInputStream(fileProduced));
		Header headerExpected = Header.readHeader(inputExpected);
		Header headerProduced = Header.readHeader(inputProduced);
		int opTableLengthExpected = headerExpected.getMx_noptable();
		int opTableLengthProduced = headerProduced.getMx_noptable();
		if (opTableLengthExpected != opTableLengthProduced){
			out.println("length: "+opTableLengthExpected + ", "+opTableLengthProduced);
		}
		IndexTable.readIndexTable(headerExpected.getMx_nindex_table(),inputExpected);
		IndexTable.readIndexTable(headerProduced.getMx_nindex_table(),inputProduced);	
		ArrayTable.readArrayTable(headerExpected.getMx_narray_table(), inputExpected);
		ArrayTable.readArrayTable(headerProduced.getMx_narray_table(), inputProduced);
		OpTable opTableExpected = OpTable.readOpTable(headerExpected.getMx_noptable(),inputExpected);
		OpTable opTableProduced = OpTable.readOpTable(headerProduced.getMx_noptable(),inputProduced);
		int numErrors = 0;
		for (int i=1; i < headerExpected.getMx_noptable() && 
		                    numErrors < MAX_ERRORS_TO_PRINT; i++){
			OpTable.Entry entryExpected = opTableExpected.getEntryAt(i);
			OpTable.Entry entryProduced = opTableProduced.getEntryAt(i);
			if (! entryExpected.equals(entryProduced)){
				numErrors++;
				out.println(entryExpected);
				out.println("-----------------");
				out.println(entryProduced);
				out.println("========================");
			}
		}
	}

//	private static boolean compareInput(DataInput inputExpected,
//			DataInput inputProduced) throws IOException {
//		boolean consistent = true;
//		Header headerExpected = Header.readHeader(inputExpected);
//		Header headerProduced = Header.readHeader(inputProduced);
//		AssertTrue(Header.compare(headerExpected,headerProduced));
//		IndexTable indexExpected = IndexTable.readIndexTable()
//		
//	}

	public static void readInput(DataInput input) throws IOException {
		Header header = Header.readHeader(input);
		//print header
		out.println(header);
		//print index table
		out.println("index table:");
		IndexTable indexTable = IndexTable.readIndexTable(header.getMx_nindex_table(),input);
		out.println(indexTable);
		//print array table
		out.println("array table:");
		ArrayTable arrayTable = ArrayTable.readArrayTable(header.getMx_narray_table(),input);
		out.println(arrayTable);
		//print op table
		out.println("optable:");
		OpTable opTable = OpTable.readOpTable(header.getMx_noptable(),input);
		out.println(opTable);
		//print scalar table
		out.println("scalar table:");
		ScalarTable scalarTable= ScalarTable.readScalarTable(header.getMx_scalar_table(),input);
		out.println(scalarTable);
		//check for EOF
		try{
		int eof = input.readInt();  
		out.println("Finished reading before EOF");
		}
		catch(EOFException e){}
	}    
}