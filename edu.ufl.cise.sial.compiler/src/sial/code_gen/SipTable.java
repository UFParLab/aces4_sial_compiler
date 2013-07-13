package sial.code_gen;

import java.io.IOException;

import sial.io.SIADataOutput;

/** This class holds the internal representation of an SIO file
 * and provides methods to write it to a file.
 *
 */
public class SipTable {
	Header header;
	private IndexTable indexTable;
	private ArrayTable arrayTable;
	private OpTable opTable;
	private ScalarTable scalarTable;
	private SpecialInstructionTable specialInstructionTable;
	private StringLiteralTable stringLiteralTable;
	
	protected SipTable(Header header, IndexTable indexTable, ArrayTable arrayTable,
			OpTable opTable, ScalarTable scalarTable, SpecialInstructionTable specialInstructionTable,
			StringLiteralTable stringLiteralTable) {
		super();
		this.header = header;
		this.setIndexTable(indexTable);
		this.setArrayTable(arrayTable);
		this.setOpTable(opTable);
		this.setScalarTable(scalarTable);
		this.setSpecialInstructionTable(specialInstructionTable);
		this.setStringLiteralTable(stringLiteralTable);
	}

	public SipTable() {
		header = new Header();
		setScalarTable(new ScalarTable());
//		indexTable = new IndexTable(scalarTable);
		setIndexTable(new IndexTable());
		setArrayTable(new ArrayTable());
		setOpTable(new OpTable());
		setSpecialInstructionTable(new SpecialInstructionTable());
		setStringLiteralTable(new StringLiteralTable());
	}
	
	
//	//USED FOR TESTING ONLY
//	public void write(SIADataOutput output) throws IOException{
////	header.write(output);
////	indexTable.write(output);
////	arrayTable.write(output);
////	opTable.write(output);
//	output.writeInt(scalarTable.nScalars);
//	scalarTable.write(output);
////	specialInstructionTable.write(output);
////	scalarTable.writeConstants(output);
//}	

	public void write(SIADataOutput output) throws IOException{
		header.write(output);
		getIndexTable().write(output);
		getArrayTable().write(output);
		getOpTable().write(output);
		getScalarTable().write(output);
		getStringLiteralTable().write(output);
		getSpecialInstructionTable().write(output);

		getArrayTable().writeSymbols(output);
		getIndexTable().writeSymbols(output);
		getScalarTable().writeConstants(output);
	}
	
	
	public String toString(){
		String h = header != null ? header.toString() : "header==null";
		String i = getIndexTable() != null ? getIndexTable().toString(): "indexTable==null";
		String a = getArrayTable() != null ? getArrayTable().toString() : "arrayTable==null";
		String s = getScalarTable() != null ? getScalarTable().toString() : "scalarTable == null";
		String o = getOpTable() != null ? getOpTable().toString() : "optable == null";
		String sp = getSpecialInstructionTable() != null ? getSpecialInstructionTable().toString(): 
			"specialInstructionTable == null";
		String c = getScalarTable() != null ? getScalarTable().constantsToString() : "no constants, scalarTable == null";
		String slit = getStringLiteralTable() != null ? getStringLiteralTable().toString() : "stringLiteralTable == null";
		String index_symbols = getIndexTable() != null ? getIndexTable().symbolsToString(): "no index symbols, indexTable == null";
		String array_symbols = getArrayTable() != null ? getArrayTable().symbolsToString(): "no array symbols, arrayTable == null";
		return "header:\n" + h + "\nindex:\n"+ i + "\narray:\n"+a+"\nop:\n"+o + "\nscalar:\n"+ s 
		     + "\nspecialIntructionTable:\n"+ sp + "\nconstants:\n" + c + index_symbols + array_symbols;
	}

	public IndexTable getIndexTable() {
		return indexTable;
	}

	public void setIndexTable(IndexTable indexTable) {
		this.indexTable = indexTable;
	}

	public ArrayTable getArrayTable() {
		return arrayTable;
	}

	public void setArrayTable(ArrayTable arrayTable) {
		this.arrayTable = arrayTable;
	}

	public OpTable getOpTable() {
		return opTable;
	}

	public void setOpTable(OpTable opTable) {
		this.opTable = opTable;
	}

	public ScalarTable getScalarTable() {
		return scalarTable;
	}

	public void setScalarTable(ScalarTable scalarTable) {
		this.scalarTable = scalarTable;
	}

	public SpecialInstructionTable getSpecialInstructionTable() {
		return specialInstructionTable;
	}

	public void setSpecialInstructionTable(SpecialInstructionTable specialInstructionTable) {
		this.specialInstructionTable = specialInstructionTable;
	}
	
	public StringLiteralTable getStringLiteralTable() {
		return stringLiteralTable;
	}

	public void setStringLiteralTable(StringLiteralTable stringLiteralTable) {
		this.stringLiteralTable = stringLiteralTable;
	}
}
