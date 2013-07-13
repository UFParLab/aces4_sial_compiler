package sial.io;

import java.io.DataOutput;
import java.io.IOException;

public interface SIADataOutput extends DataOutput {
	void writeString(String s) throws IOException;
}
