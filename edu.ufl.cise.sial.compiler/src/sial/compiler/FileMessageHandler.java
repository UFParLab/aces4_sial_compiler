package sial.compiler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import lpg.runtime.IMessageHandler;

public class FileMessageHandler implements IMessageHandler {
	int errorCount = 0;
	File file;
	FileWriter writer;
	
	FileMessageHandler(File file){
		this.file = file;
		try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getErrorCount(){return errorCount;}
	public void closeWriter(){
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void handleMessage(int errorCode, int[] msgLocation,
			int[] errorLocation, String filename, String[] errorInfo) {
		int line = msgLocation[IMessageHandler.START_LINE_INDEX], column = msgLocation[IMessageHandler.START_COLUMN_INDEX];
		String message = "";
		for (int i = 0; i < errorInfo.length; i++)
			message += (errorInfo[i] + (i < errorInfo.length - 1 ? " " : ""));

		try {
			System.out.print("*");
			writer.write(filename + "[" + line + ":" + column + "] " + message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		errorCount++;
	}
}