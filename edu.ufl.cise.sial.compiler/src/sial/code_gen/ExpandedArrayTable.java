//this class was a quick attempt to preserve the ability to write the original
//aces3 array table format.  It has not been tested.

package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import sial.code_gen.ArrayTable.Entry;

public class ExpandedArrayTable extends ArrayTable {

	public ExpandedArrayTable() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i != entries.size(); i++) {
			sb.append(entries.get(i).toString());
			sb.append('\n');
		}
		return sb.toString(); 
	}
	
	@Override
	protected
	void read(int mx_narray_table, DataInput input) throws IOException{
		for (int i = 0; i != mx_narray_table; i++){
			entries.add(Entry.readExpandedEntry(input));
		}
		nvars = mx_narray_table;
	}
	

	public static ArrayTable readArrayTable(int mx_narray_table, DataInput input) throws IOException {
		ArrayTable arrayTable = new ExpandedArrayTable();
		arrayTable.read(mx_narray_table, input);
		return arrayTable;
	}

	@Override
	public void write(DataOutput output) throws IOException {
		for (int i = 0; i < nvars; i++){
			entries.get(i).writeExpanded(output);
		}
		
	}
}
