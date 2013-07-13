package sial.io;

import java.io.DataInput;
import java.io.IOException;

public interface SIADataInput extends DataInput {
	
	String readString() throws IOException;

}
