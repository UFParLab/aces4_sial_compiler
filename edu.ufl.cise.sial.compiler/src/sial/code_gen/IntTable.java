/****NOT USED IN CURRENT IMPLEMENTATION****/

package sial.code_gen;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import sial.parser.Ast.IntDec;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class IntTable {

	/* This sip data structure is an array of Integers
	 * Eventually, predefined constants will appear in this
	 * table like anything else.  
	 * 
	 *  For now, only predefined constants are ints, and 
	 *  int literals are actually stored in the scalarTable.
	 *  The intBiMap is used to hold the predefined constants
	 *  with negative index values as expected by the sip. 
	 *  (See interpreter.h and build_segement_table.F)
	 */

	BiMap<String, Integer> intBiMap; // maps ints to index in int table
	                                 // currently, this is negative number for
	                                 // predefined constants
	ArrayList<Integer> ints; // int table
	int nInts; // number of scalars

	IntTable() {
		intBiMap = HashBiMap.create();
		ints = new ArrayList<Integer>();
		nInts = 0;
	}
	
	public String getName(int index){
		return intBiMap.inverse().get(index);
	}
	
	public boolean contains(String name){
		return intBiMap.containsKey(name);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i != ints.size(); i++) {
			if (i != 0)
				sb.append(",");
			sb.append(i).append(":");
			String name = intBiMap.inverse().get(i);
			if (name == null) {
				sb.append(ints.get(i));
			} else
				sb.append(name);
		}
		return sb.toString();
	}

	public int getIndex(String name) {
		return intBiMap.get(name.toLowerCase());
	}

	public int getIndex(IntDec dec) {
		return getIndex(dec.getIdent().toString().toLowerCase());
	}

	public int getFortranIndex(String name) {
		return getIndex(name) + 1;
	}

	public int getFortranIndex(IntDec dec) {
		return getIndex(dec) + 1;
	}

	// returns the index of the given double literal, adding it
	// if necessary
	public int getIndex(int literal) {
		// search scalar array to determine if literal already exists
		int i;
		for (i = 0; i != nInts && literal != ints.get(i).intValue(); i++){}
        if (i == nInts){ //not found, add literal
    		nInts++;
    		ints.add(literal);
        }
		return i;
	}

	public int getFortranIndex(int literal) {
		return getIndex(literal) + 1;
	}

	int addEntry(IntDec dec) {
		String name = dec.getIdent().toString();
		return addEntry(name);
	}

	int addEntry(String name) {
		int index = nInts++;
		intBiMap.put(name.toLowerCase(), index);
		ints.add(0);
		return index;
	}

	public byte[] toByteArray() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(stream);
		for (int i = 0; i != ints.size(); i++) {
			try {
				dataStream.writeInt(ints.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stream.toByteArray();
	}

	void setAcesIndexRangeConstants() {
		intBiMap.put("norb", 0);
		intBiMap.put("nocc", -1);
		intBiMap.put("nvirt", -2);
		intBiMap.put("bocc", -3);
		intBiMap.put("eocc", -4);
		intBiMap.put("bvirt", -5);
		intBiMap.put("evirt", -6);
		intBiMap.put("naocc", -7);
		intBiMap.put("nbocc", -8);
		intBiMap.put("navirt", -9);
		intBiMap.put("nbvirt", -10);
		intBiMap.put("baocc", -11);
		intBiMap.put("bbocc", -12);
		intBiMap.put("eaocc", -13);
		intBiMap.put("ebocc", -14);
		intBiMap.put("bavirt", -15);
		intBiMap.put("bbvirt", -16);
		intBiMap.put("eavirt", -17);
		intBiMap.put("ebvirt", -18);
		intBiMap.put("noccorb", -19);
		intBiMap.put("nvirtorb", -20);
		intBiMap.put("boccorb", -21);
		intBiMap.put("eoccorb", -22);
		intBiMap.put("bvirtorb", -23);
		intBiMap.put("evirtorb", -24);
		intBiMap.put("naoccorb", -25);
		intBiMap.put("nboccorb", -26);
		intBiMap.put("navirtorb", -27);
		intBiMap.put("nbvirtorb", -28);
		intBiMap.put("baoccorb", -29);
		intBiMap.put("bboccorb", -30);
		intBiMap.put("eaoccorb", -31);
		intBiMap.put("eboccorb", -32);
		intBiMap.put("bavirtorb", -33);
		intBiMap.put("bbvirtorb", -34);
		intBiMap.put("eavirtorb", -35);
		intBiMap.put("ebvirtorb", -36);
		intBiMap.put("cc_iter_cons", -37);
		intBiMap.put("cc_hist_cons", -38);
		intBiMap.put("cc_beg_cons", -39);
		intBiMap.put("scf_iter_cons", -40);
		intBiMap.put("scf_hist_cons", -41);
		intBiMap.put("scf_beg_cons", -42);
		intBiMap.put("natoms_cons", -43);
		intBiMap.put("itrips_parm", -44);
		intBiMap.put("itripe_parm", -45);
		intBiMap.put("ihess1_parm", -46);
		intBiMap.put("ihess2_parm", -47);
		intBiMap.put("jhess1_parm", -48);
		intBiMap.put("jhess2_parm", -49);
		intBiMap.put("subb_parm", -50);
		intBiMap.put("sube_parm", -51);
		intBiMap.put("sip_sub_segsize_parm", -52);
		intBiMap.put("sip_sub_occ_segsize_parm", -53);
		intBiMap.put("sip_sub_virt_segsize_parm", -54);
		intBiMap.put("sip_sub_ao_segsize_parm", -55);
		
		//take care of 0th entry in array, which holds "norb"
		ints.add(0); 
		nInts++;
	}
}