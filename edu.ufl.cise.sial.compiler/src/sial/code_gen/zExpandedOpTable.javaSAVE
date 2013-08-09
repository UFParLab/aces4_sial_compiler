//This class is a quick attempt to preserve the original aces .sio file structure
//it has not been tested.


package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import sial.code_gen.OpTable.Entry;
import sial.io.SIADataOutput;

public class ExpandedOpTable extends OpTable {

	public ExpandedOpTable() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override public void write(DataOutput output) throws IOException {
		assert nOps == entries.size();
		for (int i = 0; i != nOps; i++)
			entries.get(i).writeExpanded(output);	
	}
	
	// returns a string representation of the expanded op table
	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i != nOps; i++) {
			sb.append(entries.get(i).toStringExpanded());
			sb.append('\n');
		}
		return sb.toString();
	}

	@Override
	public void read(int nentries, DataInput input) throws IOException{
		for (int i = 0; i < nentries; i++){
			   Entry entry = Entry.readExpandedEntry(input);
			   entries.add(entry);
			   nOps++;
			}
	}

}
