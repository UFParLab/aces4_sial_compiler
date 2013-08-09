//This class is a quick attempt to preserve the ability to write 
//.sio files in the original aces format.  It has not been tested.

package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import sial.code_gen.IndexTable.Entry;

public class ExpandedIndexTable extends IndexTable {

	public ExpandedIndexTable() {
		// TODO Auto-generated constructor stub
	}

	public ExpandedIndexTable(int mx_nindex_table) {
		super(mx_nindex_table);
		// TODO Auto-generated constructor stub
	}

	public Entry readEntry(DataInput input) throws IOException{
		Entry entry = new Entry();
		entry.readExpanded(input);
		return entry;
	}
	
	// returns a string representation of the index table
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i != n_index_table_sip; i++) {
			sb.append(entries.get(i).toStringExpanded());
		}
		return sb.toString();
	}
	
	
	public void write(DataOutput output) throws IOException{
		for (int i = 0; i != n_index_table_sip; i++) {
			entries.get(i).writeExpanded(output);
		}
	}

	
/*reads the index table with n entries from a DataInputStream.  This is used
 * for debugging and reverse engineering of the original compiler	
 */
	public static IndexTable readIndexTable(int nentries, DataInput input) throws IOException{
		IndexTable table = new ExpandedIndexTable(nentries);
		table.read(nentries, input);
        return table;
	}
	


}
