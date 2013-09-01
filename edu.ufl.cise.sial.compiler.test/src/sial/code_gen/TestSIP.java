package sial.code_gen;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import sial.compiler.SialCompiler;
import sial.io.SIALittleEndianDataOutputStream;

//runTypeCheckTest takes the name of a .sialx file and the expected number of errors
//that should occur during compilation and type checking

public class TestSIP {
	boolean VERBOSE = true;
	
//	static String dir;
//
//
//	
//	private void runTypeCheckTest(String name, int numErrs) throws IOException {
//        String dir =  System.getProperty("user.dir");
//        System.out.println("dir: " + dir);
//		String sialpath = dir + File.separatorChar +"SIPTestCases";
//		String[] args = {"-v", "-sp", sialpath, name + ".sialx" };
//		System.out.println("Sialpath is " + sialpath);
//		System.out.print("args = ");
//		for (String s: args) { System.out.print(s + " ");
//		}
//		System.out.println("starting " + name);
//		SialCompiler.main(args);
//		System.out.println("returned from SialCompiler.main(args) in test "+ name);
//		assertEquals(numErrs,SialCompiler.errs);
//	}
//	

//	@Test
//	public void t0() throws IOException {
//		runTest("t0", "t0imports");
//	}
//
//	@Test
//	public void t1() throws IOException {
//		runTest("t1","t1imports");
//	}
	
	@Test
	public void scalarTable() throws IOException {
		SIALittleEndianDataOutputStream out = 
				new SIALittleEndianDataOutputStream(new FileOutputStream("scalarTableTest.out"));
		out.writeInt(Integer.MAX_VALUE);
		out.writeInt(Integer.MIN_VALUE);
		out.writeInt(1);
		out.writeInt(2);
		out.writeInt(3);
		out.writeString("abcd");
		out.writeInt(Integer.MAX_VALUE);
		out.writeInt(1);
		out.writeInt(2);
		out.writeString("ba");
		out.writeInt(Integer.MIN_VALUE);
		out.writeInt(1);
		out.writeInt(2);
		out.writeInt(3);
		out.writeString("abc");
		out.writeInt(Integer.MIN_VALUE);
		out.writeInt(1);
		out.writeInt(2);
		out.writeInt(3);		
		out.close();		
	}

//	@Test
//	public void t2imports() throws IOException {
//		runTypeCheckTest("t2imports", 4);
//	}
//	
//	@Test
//	public void t2() throws IOException {
//		runTypeCheckTest("t2", 1);
//	}
//	
//	@Test
//	public void t3pass() throws IOException {
//		runTypeCheckTest("t3pass", 0);
//	}
//	
//	@Test
//	public void t3pass2() throws IOException {
//		runTypeCheckTest("t3pass2", 0);
//	}
//	
//	@Test
//	public void t3fail() throws IOException {
//		runTypeCheckTest("t3fail", 1);
//	}
//	
//	@Test
//	public void t3fail2() throws IOException {
//		runTypeCheckTest("t3fail2", 1);
//	}
//	
//	@Test
//	public void t3fail3() throws IOException {
//		runTypeCheckTest("t3fail3", 2);
//	}
//	
//	@Test
//	public void t3circular_def1() throws IOException {
//		runTypeCheckTest("t3circular_def1", 2);
//	}
//	
//	@Test
//	public void tcircularimports() throws IOException {
//		runTypeCheckTest("tcircularimports", 2);
//	}
//	
//	@Test
//	public void simpleIndexToScalarPass() throws IOException{
//		runTypeCheckTest("simpleIndexToScalarPass",0);
//	}
//
//	@Test
//	public void eccsd_rhf_modularFail() throws IOException{
//		runTypeCheckTest("eccsd_rhf_modular",1);
//	}
//	
//	@Test
//	public void rccsd_rhf_modularPass() throws IOException{
//		runTypeCheckTest("rccsd_rhf_modular",0);
//	}
//	
//	@Test
//	public void nonnested_pardo() throws IOException{
//		runTypeCheckTest("nonnested_pardo",0);
//	}
//	
//	@Test
//	public void nested_pardo() throws IOException{
//		runTypeCheckTest("nested_pardo",1);
//	}
//	
	
}