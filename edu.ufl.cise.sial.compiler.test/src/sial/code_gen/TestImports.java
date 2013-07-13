package sial.code_gen;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import sial.compiler.SialCompiler;

//This test suite compares output from the new compiler with the old compiler.  It
//does not check the special instruction entries in the .siox files since these are not
//present in the .sio files.
public class TestImports {
	boolean VERBOSE = false;

	static String dir;

	// private void runTest(String name, String name2) throws IOException {
	// System.out.println(dir);
	// String[] args1 = {dir + File.separatorChar + name + ".sialx" };
	// SialCompiler.main(args1);
	// assertTrue(SialCompiler.errs == 0);
	// String fileExpected = dir + File.separatorChar + name + ".siox";
	//
	// String[] args2 = {dir + File.separatorChar + name2 + ".sialx" };
	// SialCompiler.main(args2);
	// assertTrue(SialCompiler.errs == 0);
	// String fileProduced = dir + File.separatorChar + name2 + ".siox";
	//
	// SIADataInput inputExpected = new SIALittleEndianDataInputStream(
	// new FileInputStream(fileExpected));
	// SIADataInput inputProduced = new SIALittleEndianDataInputStream(
	// new FileInputStream(fileProduced));
	// Header headerExpected = Header.readHeader(inputExpected);
	// Header headerProduced = Header.readHeader(inputProduced);
	// boolean headerOK = (headerExpected.equals(headerProduced));
	// IndexTable indexExpected = IndexTable.readIndexTable(
	// headerExpected.getMx_nindex_table(), inputExpected);
	// IndexTable indexProduced = IndexTable.readIndexTable(
	// headerProduced.getMx_nindex_table(), inputProduced);
	// boolean indexTableOK = (indexExpected.equals(indexProduced));
	// ArrayTable arrayExpected = ArrayTable.readArrayTable(
	// headerExpected.getMx_narray_table(), inputExpected);
	// ArrayTable arrayProduced = ArrayTable.readArrayTable(
	// headerProduced.getMx_narray_table(), inputProduced);
	// boolean arrayTableOK = (arrayExpected.equals(arrayProduced));
	// OpTable opExpected = OpTable.readOpTable(
	// headerExpected.getMx_noptable(), inputExpected);
	// OpTable opProduced = OpTable.readOpTable(
	// headerProduced.getMx_noptable(), inputProduced);
	// boolean opTableOK = (opExpected.equals(opProduced));
	// ScalarTable scalarExpected = ScalarTable.readScalarTable(
	// headerExpected.getMx_scalar_table(), inputExpected);
	// ScalarTable scalarProduced = ScalarTable.readScalarTable(
	// headerProduced.getMx_scalar_table(), inputProduced);
	// boolean scalarTableOK = (scalarExpected.equals(scalarProduced));
	//
	//
	// if (VERBOSE) {
	// if (!headerOK || !indexTableOK || !arrayTableOK || !opTableOK
	// || !scalarTableOK) {
	// System.out.println("Error in " + name);
	// BufferedReader r = new BufferedReader(new FileReader(args1[0]));
	// String line = r.readLine();
	// while (line != null) {
	// System.out.println(line);
	// line = r.readLine();
	// }
	// if (!headerOK) {
	// System.out.println("HEADER --------------------");
	// System.out.println(headerExpected);
	// System.out.println("------");
	// System.out.println(headerProduced);
	// }
	// if (!indexTableOK) {
	// System.out.println("INDEX TABLE --------------------");
	// System.out.println(indexExpected);
	// System.out.println("------");
	// System.out.println(indexProduced);
	// }
	// if (!arrayTableOK) {
	// System.out.println("ARRAY TABLE --------------------");
	// System.out.println(arrayExpected);
	// System.out.println("------");
	// System.out.println(arrayProduced);
	// }
	// if (!opTableOK) {
	// System.out.println("OP TABLE --------------------");
	// System.out.print(opExpected);
	// System.out.println("------");
	// System.out.print(opProduced);
	// }
	// if (!scalarTableOK) {
	// System.out.println("SCALAR TABLE --------------------");
	// System.out.println(scalarExpected);
	// System.out.println("------");
	// System.out.println(scalarProduced);
	// }
	// }
	// }
	// assertTrue(headerOK);
	// assertTrue(indexTableOK);
	// assertTrue(arrayTableOK);
	// assertTrue(opTableOK);
	// assertTrue(scalarTableOK);
	// }

	private void runTypeCheckTest(String name, int numErrs) throws IOException {
		System.out.println(dir);
		String[] args1 = new String[1];
		if (dir != null)
			args1[0] = dir + File.separatorChar + name + ".sialx";
		else
			args1[0] = name + ".sialx";
		SialCompiler.main(args1);
		assertEquals(numErrs, SialCompiler.errs);
	}

	@BeforeClass
	public static void setup() {
		dir = System.getProperty("SIALPATH");
	}

	// @Test
	// public void t0() throws IOException {
	// runTest("t0", "t0imports");
	// }
	//
	// @Test
	// public void t1() throws IOException {
	// runTest("t1","t1imports");
	// }

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
	public void simpleIndexToScalarPass() throws IOException {
		runTypeCheckTest("simpleIndexToScalarPass", 0);
	}

	@Test
	public void eccsd_rhf_modularFail() throws IOException {
		runTypeCheckTest("eccsd_rhf_modular", 1);
	}

	@Test
	public void rccsd_rhf_modularPass() throws IOException {
		runTypeCheckTest("rccsd_rhf_modular", 0);
	}

}
