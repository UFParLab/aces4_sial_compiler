package sial.code_gen;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import sial.compiler.SialCompiler;

//runTypeCheckTest takes the name of a .sialx file and the expected number of errors
//that should occur during compilation and type checking

public class TestTypeChecking {
	boolean VERBOSE = true;
	
	static String dir;

	private void runTypeCheckTest(String name, int numErrs) throws IOException {
        String dir =  System.getProperty("user.dir");
        System.out.println("dir: " + dir);
		String sialpath = dir + File.separatorChar +"type_checking_test_cases";
		String[] args = {"-v", "-sp", sialpath, name + ".sialx" };
		System.out.println("Sialpath is " + sialpath);
		System.out.print("args = ");
		for (String s: args) { System.out.print(s + " ");
		}
		System.out.println("starting " + name);
		SialCompiler.main(args);
		System.out.println("returned from SialCompiler.main(args) in test "+ name);
		assertEquals(numErrs,SialCompiler.errs);
	}
	
    @Test
    public void subindex_pass() throws IOException{
    	runTypeCheckTest("subindex_pass",0);
    }
    
	@Test
	public void t0() throws IOException {
		runTypeCheckTest("t0", 0);
	}
	
	@Test
	public void special_dec() throws IOException {
		runTypeCheckTest("special_dec",0);
	}
	
	@Test
	public void special_dec_fail() throws IOException {
		runTypeCheckTest("special_dec_fail",1);
	}
	
	@Test
	public void hello_world() throws IOException {
		runTypeCheckTest("hello_world",0);
	}

	@Test
	public void t2imports() throws IOException {
		runTypeCheckTest("t2imports", 4);
	}
	
	@Test
	public void t2() throws IOException {
		runTypeCheckTest("t2", 1);
	}
	
	@Test
	public void t3pass() throws IOException {
		runTypeCheckTest("t3pass", 0);
	}
	
	@Test
	public void t3pass2() throws IOException {
		runTypeCheckTest("t3pass2", 0);
	}
	
	@Test
	public void t3fail() throws IOException {
		runTypeCheckTest("t3fail", 1);
	}
	
	@Test
	public void t3fail2() throws IOException {
		runTypeCheckTest("t3fail2", 1);
	}
	
	@Test
	public void t3fail3() throws IOException {
		runTypeCheckTest("t3fail3", 2);
	}
	
	@Test
	public void t3circular_def1() throws IOException {
		runTypeCheckTest("t3circular_def1", 2);
	}
	
	@Test
	public void tcircularimports() throws IOException {
		runTypeCheckTest("tcircularimports", 2);
	}
	
	@Test
	public void simpleIndexToScalarPass() throws IOException{
		runTypeCheckTest("simpleIndexToScalarPass",0);
	}

	@Test
	public void eccsd_rhf_modularFail() throws IOException{
		runTypeCheckTest("eccsd_rhf_modular",1);
	}
	
	@Test
	public void rccsd_rhf_modularPass() throws IOException{
		runTypeCheckTest("rccsd_rhf_modular",0);
	}
	
	@Test
	public void nonnested_pardo() throws IOException{
		runTypeCheckTest("nonnested_pardo",0);
	}
	
	@Test
	public void nested_pardo() throws IOException{
		runTypeCheckTest("nested_pardo",1);
	}
	
	
}
