package sial.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;



public class SIADataOutputStream extends DataOutputStream implements
		SIADataOutput {

	public SIADataOutputStream(OutputStream out) {
		super(out);
	}

	@Override
	public void writeString(String s) throws IOException {
		int length = s.length();
		writeInt(length);
		write(s.getBytes("US-ASCII"),0,length);
	}
	
}
