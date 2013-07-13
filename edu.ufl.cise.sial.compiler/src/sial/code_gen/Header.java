package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;



/** The internal representation of sio file header */

public class Header {
	int magic; 
	int version;
	int release;
	int mx_nindex_table;
	int mx_narray_table;
	int mx_noptable;
	int mx_scalar_table;	
	
//	public Header(int magic, int version, int release, int mx_nindex_table,
//			int mx_narray_table, int mx_noptable, int mx_scalar_table) {
//		super();
//		this.magic = magic;
//		this.version = version;
//		this.release = release;
//		this.mx_nindex_table = mx_nindex_table;
//		this.mx_narray_table = mx_narray_table;
//		this.mx_noptable = mx_noptable;
//		this.mx_scalar_table = mx_scalar_table;
//	}


//	public Header(int mx_nindex_table,
//			int mx_narray_table, int mx_noptable, int mx_scalar_table) {
//		super();
//		this.magic = SipTypeConstants.magic;
//		this.version = SipTypeConstants.version;
//		this.release = SipTypeConstants.release;
//		this.mx_nindex_table = mx_nindex_table;
//		this.mx_narray_table = mx_narray_table;
//		this.mx_noptable = mx_noptable;
//		this.mx_scalar_table = mx_scalar_table;
//	}

	Header(){
		this.magic = SipConstants.magic;
		this.version = SipConstants.version;
		this.release = SipConstants.release;
	}

	public int getMx_nindex_table() {
		return mx_nindex_table;
	}
//
//	public void setMx_nindex_table(int mx_nindex_table) {
//		this.mx_nindex_table = mx_nindex_table;
//	}
//
	public int getMx_narray_table() {
		return mx_narray_table;
	}
//
//	public void setMx_narray_table(int mx_narray_table) {
//		this.mx_narray_table = mx_narray_table;
//	}
//
	public int getMx_noptable() {
		return mx_noptable;
	}
//
//	public void setMx_noptable(int mx_noptable) {
//		this.mx_noptable = mx_noptable;
//	}
//
//
	public int getMx_scalar_table() {
		return mx_scalar_table;
	}


//	public void setMx_scalar_table(int mx_scalar_table) {
//		this.mx_scalar_table = mx_scalar_table;
//	}


	public void read(DataInput input) throws IOException{
	magic = input.readInt();
	version = input.readInt();
	release = input.readInt();
	mx_nindex_table = input.readInt();
	mx_narray_table = input.readInt();
	mx_noptable = input.readInt();
	mx_scalar_table = input.readInt();
	}
	
	public static Header readHeader(DataInput input) throws IOException{
		Header header = new Header();
		header.read(input);
		return header;
	}
	
	public void write(DataOutput out) throws IOException{
		out.writeInt(magic);
		out.writeInt(version);
		out.writeInt(release);
		out.writeInt(mx_nindex_table);
		out.writeInt(mx_narray_table);
		out.writeInt(mx_noptable);
		out.writeInt(mx_scalar_table);
	}
	
	@Override
	public String toString(){
		return magic + "," + version + "," + release + "," + mx_nindex_table + "," + 
		mx_narray_table + "," + mx_noptable + "," + mx_scalar_table;
	}
	
	/* validates first three entries of header for original .sio format */
	//TODO change version numbers on "C" .sio files
	public boolean validate(){
		return magic == SipConstants.magic && 
		       version == SipConstants.version && 
		       release == SipConstants.release;
	}


	public void setValues(int n_index_table_sip, int nvars, int nOps,
			int nScalars) {
		this.mx_nindex_table = n_index_table_sip;
		this.mx_narray_table = nvars;
		this.mx_noptable = nOps;
		this.mx_scalar_table = nScalars;
	}
	
	public boolean equalVals(Object other){
		if (this == other) return true;
		if (! (other instanceof Header)) return false;
	    Header o = (Header)other;
	    return magic == o.magic && 
	    		version == o.version && 
	    		release == o.release && 
	    		mx_nindex_table == o.mx_nindex_table && 
	    		mx_narray_table == o.mx_narray_table &&
	    		mx_noptable == o.mx_noptable &&
	    		mx_scalar_table == o.mx_scalar_table;
	}



}
