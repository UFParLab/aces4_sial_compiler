/** This class represents the .siox file header
 * 
 */

package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;



/** The internal representation of sio file header */
public class Header {
	/** all .xiox files should start with this value at the beginning of the file */
	int magic; 
	/** the version number */
	int version;
	/** the maximum rank.  This is needed because the structure of the .siox file depends on this.
	 * It should be checked for compatibility in the sip.
	 */
	int max_rank;


	Header(){
		this.magic = SipConstants.magic;
		this.version = SipConstants.version;
		this.max_rank = TypeConstantMap.max_rank;
	}

	public Header(int magic, int version, int max_rank) {
		this.magic = magic;
		this.version = version;
		this.max_rank = max_rank;
	}



	public static Header readHeader(DataInput input) throws IOException{
		int magic = input.readInt();
		int version = input.readInt();
		int max_rank = input.readInt();
		return new Header(magic, version, max_rank);
		
	}

	
	public void write(DataOutput out) throws IOException{
		out.writeInt(magic);
		out.writeInt(version);
		out.writeInt(max_rank);
	}
	
	@Override
	public String toString(){
		return magic + "," + version + "," + max_rank; 
	}
	

	
	public boolean equalVals(Object other){
		if (this == other) return true;
		if (! (other instanceof Header)) return false;
	    Header o = (Header)other;
	    return magic == o.magic && 
	    		version == o.version && 
	    		max_rank == o.max_rank;

	}



}
