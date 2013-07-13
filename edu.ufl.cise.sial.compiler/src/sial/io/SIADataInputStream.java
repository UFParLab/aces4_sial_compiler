package sial.io;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;



public class SIADataInputStream 
		implements SIADataInput {
	
	DataInputStream inputStream;

	public SIADataInputStream(FileInputStream fileInputStream) {
		inputStream = new DataInputStream(fileInputStream);
	}

	@Override
	public String readString() throws IOException {
		int length = inputStream.readInt();
		byte[] bytes = new byte[length];
		inputStream.readFully(bytes,0,length);
		return new String(bytes,"US-ASCII");
	}

	@Override
	public boolean readBoolean() throws IOException {
		return inputStream.readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return inputStream.readByte();
	}

	@Override
	public char readChar() throws IOException {
		return inputStream.readChar();
	}

	@Override
	public double readDouble() throws IOException {
		return inputStream.readDouble();
	}

	@Override
	public float readFloat() throws IOException {
		return inputStream.readFloat();
	}

	@Override
	public void readFully(byte[] b) throws IOException {
		inputStream.readFully(b);
		
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		inputStream.readFully(b,off,len);
		
	}

	@Override
	public int readInt() throws IOException {
		return inputStream.readInt();
	}

	@SuppressWarnings("deprecation")
	@Override
	public String readLine() throws IOException {
		// TODO Auto-generated method stub
		return inputStream.readLine();
	}

	@Override
	public long readLong() throws IOException {
		return inputStream.readLong();
	}

	@Override
	public short readShort() throws IOException {
		return inputStream.readShort();
	}

	@Override
	public String readUTF() throws IOException {
		return inputStream.readUTF();
	}

	@Override
	public int readUnsignedByte() throws IOException {
		return inputStream.readUnsignedByte();
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return inputStream.readUnsignedShort();
	}

	@Override
	public int skipBytes(int n) throws IOException {
		return inputStream.skipBytes(n);
	}

}
