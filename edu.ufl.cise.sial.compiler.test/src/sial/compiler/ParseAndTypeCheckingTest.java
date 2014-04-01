package sial.compiler;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import sial.compiler.SialCompiler;

//runTypeCheckTest takes the name of a .sialx file and the expected number of errors
//that should occur during compilation and type checking

public class ParseAndTypeCheckingTest {
	boolean VERBOSE = true;
	
	static String dir;

	private void runTypeCheckTest(String name, int numErrs) throws IOException {
        String dir =  System.getProperty("user.dir");
        System.out.println("dir: " + dir);
		String sialpath = dir + File.separatorChar +"parse_and_type_checking_test_cases";
		String[] args = {"-ne", "-v", "-sp", sialpath, name + ".sialx" };
		System.out.println("Sialpath is " + sialpath);
		System.out.print("args = ");
		for (String s: args) { System.out.print(s + " ");
		}
		System.out.println("\nstarting " + name);
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
	
//	@Test
//	public void special_dec_fail() throws IOException {
//		runTypeCheckTest("special_dec_fail",1);
//	}
	
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
		runTypeCheckTest("rccsd_rhf_modular",4);
	}
	
	@Test
	public void nonnested_pardo() throws IOException{
		runTypeCheckTest("nonnested_pardo",0);
	}
	
	@Test
	public void nested_pardo() throws IOException{
		runTypeCheckTest("nested_pardo",1);
	}
	
	@Test
	public void scf_rhf_coreh() throws IOException{
		runTypeCheckTest("scf_rhf_coreh",0);
}
	@Test
	public void scf_tmp() throws IOException{
		runTypeCheckTest("scf_tmp",691);  
		//this has a serious error in the imported defs file. 
		//Mainly this should avoid throwing a null pointer exception
}
	
	@Test
	public void fill_sequential() throws IOException{
		runTypeCheckTest("fill_sequential",0);  
	}
	
	@Test
	public void gpu_ops() throws IOException{
		runTypeCheckTest("gpu_ops",0);
	}
	
	@Test
	public void gpu_ops_fail1() throws IOException{
		runTypeCheckTest("gpu_ops_fail1",1);
	}
	
	@Test
	public void gpu_ops_fail2() throws IOException{
		runTypeCheckTest("gpu_ops_fail2",3);
	}
	
	@Test
	public void assignments_pass() throws IOException{
		runTypeCheckTest("assignments_pass",0);
	}
	
	@Test
	public void assignments_fail() throws IOException{
		runTypeCheckTest("assignments_fail",1);
	}
	
	@Test
	public void set_persistent_pass() throws IOException{
		runTypeCheckTest("set_persistent_pass", 0);
	}
	
	@Test
	public void set_persistent_fail() throws IOException{
		runTypeCheckTest("set_persistent_fail", 2);
	}
	
	@Test
	public void set_persistent_predefined_fail() throws IOException{
		runTypeCheckTest("set_persistent_predefined_fail", 1);
	}
	
	@Test
	public void simpleIndexRange_pass() throws IOException{
		runTypeCheckTest("simpleIndexRange_pass",0);
	}
	
	@Test
	public void indexRange_fail() throws IOException{
		runTypeCheckTest("indexRange_fail",1);
	}
	
	@Test
	public void negSimpleIndexRange_pass() throws IOException{
		runTypeCheckTest("negSimpleIndexRange_pass",0);
	}
	
	@Test
	public void restore_persistent_test_fail() throws IOException{
		runTypeCheckTest("restore_persistent_test_fail",1);
	}

}
