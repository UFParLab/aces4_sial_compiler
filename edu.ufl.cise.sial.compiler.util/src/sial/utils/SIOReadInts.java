package sial.utils;

import java.io.DataInput;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.common.io.LittleEndianDataInputStream;

public class SIOReadInts {
	public static void main(String[] args) throws FileNotFoundException {
		String file = args[0];	
		DataInput input = new LittleEndianDataInputStream(new FileInputStream(file));
		System.out.println("opened " + args[0]);
		int n = 0;
		try{
		for (;;){
			for (int i = 1; i != 5; i++){
			int x = input.readInt();
			System.out.print(x + " ");
			n++;
			}
		  System.out.print('\n');
		}
		}
		catch(EOFException e){} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
