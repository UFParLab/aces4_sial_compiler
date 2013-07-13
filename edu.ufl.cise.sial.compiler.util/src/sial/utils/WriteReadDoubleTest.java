package sial.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import sial.code_gen.SipConstants;
import sial.io.SIADataInput;
import sial.io.SIADataOutput;
import sial.io.SIALittleEndianDataInputStream;
import sial.io.SIALittleEndianDataOutputStream;

/*takes a file name and reads (and prints) the .sio file */

public class WriteReadDoubleTest implements SipConstants{

	/**
	 * @param args file name
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String file = args[0];	
		System.out.println("writing file " + args[0]);
		SIADataOutput output = new SIALittleEndianDataOutputStream(new FileOutputStream(file));
		//write NI and ND, then write NI ints followed by ND doubles
		int NI=727; 
		int ND=500;
		output.writeInt(NI);
		output.writeInt(ND);
		for (int ii = 0; ii < NI; ii++) output.writeInt(ii);
		for (int i = 0; i < ND; i++){
			double d = 0.0;
			output.writeDouble(d);
		}
		((OutputStream) output).close();
		
		System.out.println("reading file " + args[0]);
		SIADataInput input = new SIALittleEndianDataInputStream(new FileInputStream(file));
		int NIR = input.readInt();
		int NDR = input.readInt();
		
		int[] intVals = new int[NIR];
		for (int ii = 0; ii < NI; ii++) intVals[ii] = input.readInt();
		ArrayList<Double> vals = new ArrayList<Double>();
		for (int j=0; j < NDR; j++){
			vals.add(input.readDouble());
		}
		System.out.println("writing values");
		System.out.println("NR="+NDR);
		for (int k=0; k<vals.size(); k++){
			System.out.println(vals.get(k));
		}
	}

//	public static void readInput(SIADataInput input) throws IOException {
//		Header header = Header.readHeader(input);
//		//print header
//		System.out.println(header);
//		//print index table
//		System.out.println("\nindex table:");
//		IndexTable indexTable = IndexTable.readIndexTable(header.getMx_nindex_table(),input);
//		System.out.println(indexTable);
//		//print array table
//		System.out.println("array table:");
//		ArrayTable arrayTable = ArrayTable.readArrayTable(header.getMx_narray_table(),input);
//		System.out.println(arrayTable);
//		//print op table
//		System.out.println("optable:");
//		OpTable opTable = OpTable.readOpTable(header.getMx_noptable(),input);
//		System.out.println(opTable);
//		//print scalar table
//		System.out.println("scalar table:");
//		ScalarTable scalarTable= ScalarTable.readScalarTable(header.getMx_scalar_table(),input);
//		System.out.println(scalarTable);
//		System.out.println("\nspecial instruction table:");
//		SpecialInstructionTable specialInstructionTable = null;
//		try{
//		specialInstructionTable = SpecialInstructionTable.readSpecialInstructionTable(input);
//		System.out.println(specialInstructionTable);
//		System.out.println("\nsymbolic constants:");
//		ScalarTable.readConstants(scalarTable,input);
//		System.out.println(scalarTable.constantsToString());
//		}
//		catch (EOFException e){
//			System.out.println("EOF");
//		}                                                    
//
//		} 
//	
	}    
