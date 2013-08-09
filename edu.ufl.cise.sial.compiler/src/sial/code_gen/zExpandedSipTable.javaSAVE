//this class is an attempt to preserve the ability to generate original
//sial object code structure.  It has not been tested.

package sial.code_gen;

public class ExpandedSipTable extends SipTable {

	public ExpandedSipTable(Header header, IndexTable indexTable,
			ArrayTable arrayTable, OpTable opTable, ScalarTable scalarTable,
			SpecialInstructionTable specialInstructionTable,
			StringLiteralTable stringLiteralTable) {
		super(header, indexTable, arrayTable, opTable, scalarTable,
				specialInstructionTable, stringLiteralTable);
		// TODO Auto-generated constructor stub
	}

	public ExpandedSipTable() {
		header = new Header();
		setScalarTable(new ScalarTable());
//		indexTable = new IndexTable(scalarTable);
		setIndexTable(new IndexTable());
		setArrayTable(new ArrayTable());
		setOpTable(new ExpandedOpTable());
		setSpecialInstructionTable(new SpecialInstructionTable());
		setStringLiteralTable(new StringLiteralTable());
	}

}
