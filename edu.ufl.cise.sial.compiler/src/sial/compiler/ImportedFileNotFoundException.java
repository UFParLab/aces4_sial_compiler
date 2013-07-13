package sial.compiler;

import java.io.IOException;

@SuppressWarnings("serial")
public class ImportedFileNotFoundException extends IOException{

	public ImportedFileNotFoundException(String msg, Exception e){
		super(msg,e);
	}	
	
}
