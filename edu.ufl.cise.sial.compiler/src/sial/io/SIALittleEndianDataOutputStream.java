package sial.io;

import java.io.IOException;
import java.io.OutputStream;



import com.google.common.io.LittleEndianDataOutputStream;

public class SIALittleEndianDataOutputStream extends
		LittleEndianDataOutputStream implements SIADataOutput {

	public SIALittleEndianDataOutputStream(OutputStream out) {
		super(out);
	}
	

	@Override
	public void writeString(String s) throws IOException {
		int length = s.length();
		writeInt(length);
		write(s.getBytes("US-ASCII"),0,length);
	}

}
