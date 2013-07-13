package sial.compiler;

import java.io.File;

import lpg.runtime.IMessageHandler;

public class SystemOutMessageHandler implements IMessageHandler {
	int errorCount = 0;
	
	public int getErrorCount(){return errorCount;}
	
	public void handleMessage(int errorCode, int[] msgLocation,
			int[] errorLocation, String filename, String[] errorInfo) {
		int line = msgLocation[IMessageHandler.START_LINE_INDEX], column = msgLocation[IMessageHandler.START_COLUMN_INDEX];
		String message = "";
		for (int i = 0; i < errorInfo.length; i++)
			message += (errorInfo[i] + (i < errorInfo.length - 1 ? " " : ""));

		System.out.println(filename + "[" + line + ":" + column + "] " + message);
		errorCount++;
	}
}