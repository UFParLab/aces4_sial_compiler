package sial.code_gen;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import sial.compiler.SialCompiler;
import sial.io.SIADataInput;
import sial.io.SIALittleEndianDataInputStream;

//This test suite compares output from the new compiler with the old compiler.  It
//does not check the special instruction entries in the .siox files since these are not
//present in the .sio files.
public class CompareWithOldCompiler {
	boolean VERBOSE = false;

	private void runTest(String name) throws IOException {
//		String[] args = {"-v", "-sp", "sial_files" , "-o", "sial_files", name + ".sial" };
		String[] args = {"-sp", "sial_files" ,"-suffix", "sial", "-e", "-o", "sial_files", name + ".sial" };		
		SialCompiler.main(args);
		assertTrue(SialCompiler.errs == 0);
		String fileExpected = "sio_files/" + name + ".sio";
		String fileProduced = "sial_files/" + name + ".siox";
		SIADataInput inputExpected = new SIALittleEndianDataInputStream(
				new FileInputStream(fileExpected));
		SIADataInput inputProduced = new SIALittleEndianDataInputStream(
				new FileInputStream(fileProduced));
		Header headerExpected = Header.readHeader(inputExpected);
		Header headerProduced = Header.readHeader(inputProduced);
		boolean headerOK = (headerExpected.equalVals(headerProduced));
		IndexTable indexExpected = IndexTable.readIndexTable(
				headerExpected.getMx_nindex_table(), inputExpected);
		IndexTable indexProduced = IndexTable.readIndexTable(
				headerProduced.getMx_nindex_table(), inputProduced);
		boolean indexTableOK = (indexExpected.equalVals(indexProduced));
		ArrayTable arrayExpected = ArrayTable.readArrayTable(
				headerExpected.getMx_narray_table(), inputExpected);
		ArrayTable arrayProduced = ArrayTable.readArrayTable(
				headerProduced.getMx_narray_table(), inputProduced);
		boolean arrayTableOK = (arrayExpected.equalVals(arrayProduced));
		OpTable opExpected = OpTable.readOpTable(
				headerExpected.getMx_noptable(), inputExpected);
		OpTable opProduced = OpTable.readOpTable(
				headerProduced.getMx_noptable(), inputProduced);
		boolean opTableOK = (opExpected.equalVals(opProduced));
		ScalarTable scalarExpected = ScalarTable.readScalarTable(
				headerExpected.getMx_scalar_table(), inputExpected);
		ScalarTable scalarProduced = ScalarTable.readScalarTable(
				headerProduced.getMx_scalar_table(), inputProduced);
		boolean scalarTableOK = (scalarExpected.equalVals(scalarProduced));



		
		
		
		
		//		if (VERBOSE) {
//			if (!headerOK || !indexTableOK || !arrayTableOK || !opTableOK
//					|| !scalarTableOK) {
//				System.out.println("Error in " + name);
//				BufferedReader r = new BufferedReader(new FileReader(args[0]));
//				String line = r.readLine();
//				while (line != null) {
//					System.out.println(line);
//					line = r.readLine();
//				}
//				if (!headerOK) {
//					System.out.println("HEADER --------------------");
//					System.out.println(headerExpected);
//					System.out.println("------");
//					System.out.println(headerProduced);
//				}
//				if (!indexTableOK) {
//					System.out.println("INDEX TABLE --------------------");
//					System.out.println(indexExpected);
//					System.out.println("------");
//					System.out.println(indexProduced);
//				}
//				if (!arrayTableOK) {
//					System.out.println("ARRAY TABLE --------------------");
//					System.out.println(arrayExpected);
//					System.out.println("------");
//					System.out.println(arrayProduced);
//				}
//				if (!opTableOK) {
//					System.out.println("OP TABLE --------------------");
//					System.out.print(opExpected);
//					System.out.println("------");
//					System.out.print(opProduced);
//				}
//				if (!scalarTableOK) {
//					System.out.println("SCALAR TABLE --------------------");
//					System.out.println(scalarExpected);
//					System.out.println("------");
//					System.out.println(scalarProduced);
//				}
//			}
//		}
		assertTrue(headerOK);
		assertTrue(indexTableOK);
		assertTrue(arrayTableOK);
		assertTrue(opTableOK);
		assertTrue(scalarTableOK);
	}

	@Test
	public void test0() throws IOException {
		runTest("t0");
	}

	@Test
	public void testt1() throws IOException {
		runTest("t1");
	}

	@Test
	public void testt2() throws IOException {
		runTest("t2");
	}

	@Test
	public void testt3() throws IOException {
		runTest("t3");
	}

	@Test
	public void testt4() throws IOException {
		runTest("t4");
	}

	@Test
	public void testt5() throws IOException {
		runTest("t5");
	}

	@Test
	public void testt6() throws IOException {
		runTest("t6");
	}

	@Test
	public void testt7() throws IOException {
		runTest("t7");
	}

	@Test
	public void testt8() throws IOException {
		runTest("t8");
	}

	@Test
	public void testt9() throws IOException {
		runTest("t9");
	}

	@Test
	public void testt10() throws IOException {
		runTest("t10");
	}

	@Ignore //index not defined error
	@Test
	public void testt11() throws IOException {
		runTest("t11");
	}

	@Test
	public void testt12() throws IOException {
		runTest("t12");
	}

	@Ignore //index not defined error
	@Test
	public void testt13() throws IOException {
		runTest("t13");
	}

	@Test
	public void testt14() throws IOException {
		runTest("t14");
	}

	@Test
	public void testt15() throws IOException {
		runTest("t15");
	}

	@Test
	public void testt16() throws IOException {
		runTest("t16");
	}

	@Test
	public void test17() throws IOException {
		runTest("t17");
	}

	@Test
	public void test18() throws IOException {
		runTest("t18");
	}

	@Test
	public void test19() throws IOException {
		runTest("t19");
	}

	@Test
	public void test20() throws IOException {
		runTest("t20");
	}

	@Test
	public void test21() throws IOException {
		runTest("t21");
	}

	@Test
	public void test22() throws IOException {
		runTest("t22");
	}

	@Test
	public void test23() throws IOException {
		runTest("t23");
	}


	// this case is inconsistent with others on how the old compiler handles the subindex in the enddo statement
	@Ignore
	@Test
	public void test24_subindex() throws IOException {
		runTest("t24");
	}

	@Test
	public void test25() throws IOException {
		runTest("t25");
	}

	@Test
	public void test26() throws IOException {
		runTest("t26");
	}

	@Test
	public void test27() throws IOException {
		runTest("t27");
	}

	@Test
	public void test28() throws IOException {
		runTest("t28");
	}

	@Test
	public void test29() throws IOException {
		runTest("t29");
	}

	@Test
	public void test30() throws IOException {
		runTest("t30");
	}

	@Test
	public void test31() throws IOException {
		runTest("t31");
	}

	@Test
	public void test32() throws IOException {
		runTest("t32");
	}

	@Test
	public void test33() throws IOException {
		runTest("t33");
	}

	@Test
	public void test34() throws IOException {
		runTest("t34");
	}

	@Ignore
	@Test
	public void test35_execute() throws IOException {
		runTest("t35");
	}

	@Test
	public void test36() throws IOException {
		runTest("t36");
	}

	@Test
	public void test37() throws IOException {
		runTest("t37");
	}

	@Test
	public void test38_computeintegrals() throws IOException {
		runTest("t38");
	}

	@Test
	public void test39_allocate_deallocate() throws IOException {
		runTest("t39");
	}
	
	@Ignore //contains block addition
	@Test
	public void test40() throws IOException {
		runTest("t40");
	}
	
	@Test
	public void test41() throws IOException {
		runTest("t41");
	}
	
	@Test
	public void test42() throws IOException {
		runTest("t42");
	}
	
@Ignore
	@Test
	public void test43() throws IOException {
		runTest("t43");
	}
	
//	@Ignore
	@Test
	public void cis_rhf() throws IOException {
		runTest("cis_rhf");
	}
	
	@Test
	public void test44() throws IOException {
		runTest("t44");
	}
	
	@Test
	public void test45() throws IOException {
		runTest("t45");
	}
	
//	@Test
	public void test46() throws IOException {
		runTest("t46");
	}
	

//	@Ignore
	@Test
	public void ea_eomcc() throws IOException {
		runTest("ea_eomcc");
	}
	

//	@Ignore
	@Test
	public void cis_uhf_mo_ao() throws IOException {
		runTest("cis_uhf_mo_ao");
	}
	
	@Test
	public void scf_rhf_aguess() throws IOException {
		runTest("scf_rhf_aguess");
	}
	
	//need to run old compiler without argument to barrier
	@Ignore
	@Test
	public void mp2_rhf_sp() throws IOException{
		runTest("mp2_rhf_sp");
	}
	
	@Test
	public void rccsdpt_sub_aaa() throws IOException{
		runTest("rccsdpt_sub_aaa");
	}
	
	@Test
	public void rccsdpt_sub_aab() throws IOException{
		runTest("rccsdpt_sub_aab");
	}
}
