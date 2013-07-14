/* In the current implementation of the compiler, the index in the scalar
 * array is the same as the index of the variable in the array table.  
 * This leaves a lot of empty space in the ScalarTable.  Fix this eventually
 * 
 * Note that for symbolic constants, the .sio file uses negative C indexed, i.e.
 * norb is -, nocc is -1, etc.
 */

package sial.code_gen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

import sial.io.SIADataInput;
import sial.io.SIADataOutput;
import sial.parser.Ast.IDec;
import sial.parser.Ast.IntDec;
import sial.parser.Ast.ScalarDec;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ScalarTable {

	/*This sip data structure is currently an array of doubles.  It will also have 
	 * an array of int for int type.
	 */
	
	BiMap<ScalarDec, Integer> scalarBiMap; // maps scalar names to 
	                                       // index in scalar array
	BiMap<String, Integer> intBiMap; //maps int names to index in integer array

	BiMap<Double, Integer> doubleLiteralBiMap; //maps double literals to
	                                 //index in scalar array
	BiMap<Integer, Integer> intLiteralBiMap;  //maps int literals to 
	                                 //index in int (or scalar) array
	static ArrayList<Double> global_scalars;
 
	ArrayList<Double> scalars; // scalar table
	ArrayList<Integer> integers; // integer table.  
	ArrayList<Integer> constants; //AcesHacks: for aces symbolic constants
	int nScalars; // number of scalars
	int nIntegers; //number of ints
	int nConstants; //number of constants
	
	ScalarTable() {
		scalarBiMap = HashBiMap.create();
		intBiMap = HashBiMap.create();
		doubleLiteralBiMap = HashBiMap.create();
		intLiteralBiMap = HashBiMap.create();
		
		scalars = new ArrayList<Double>();
		global_scalars = scalars;
		integers = new ArrayList<Integer>();
		constants = new ArrayList<Integer>();
		nScalars = 0;
		nIntegers = 0;
		nConstants = 0;
	}
	
	public String toString(){  //currently shows only scalars,
		                       //all ints are symbolic constants
//		StringBuilder sb = new StringBuilder();
//		sb.append("[");
//		int size = scalars.size();
//		assert size == nScalars : "mismatch between scalars.size() and nScalars";
//		for (int i = 0; i < size; i++){
//			sb.append(scalars.get(i) + (i<size-1?",":"")); 
//		}
//		sb.append("]");
//		return sb.toString();	
		StringBuilder sb = new StringBuilder();
		sb.append(nScalars);
		sb.append('\n');
		for (int i = 0; i < nScalars; i++){
		   sb.append(scalars.get(i));
		   sb.append('\n');
		}
		return sb.toString();	
	}
	
	public String toStringScalarWithFortranIndices(){  //currently shows only scalars,
        //all ints are symbolic constants
StringBuilder sb = new StringBuilder();
int size = scalars.size();
assert size == nScalars : "mismatch between scalars.size() and nScalars";
BiMap<Integer, ScalarDec> names = scalarBiMap.inverse();
for (int i = 0; i < size; i++){
	int j = i+1;
	sb.append(j<10 ? j + ":   " : (j<100 ? j + ":  ": (j<1000 ? j+": " : j+":")));
	if (names.get(i) != null) sb.append(names.get(i).getName());
	else sb.append("literal = ");
	sb.append ("=");
    sb.append(scalars.get(i)); 
    sb.append('\n');
}
sb.append("]");
return sb.toString();	
}
	
	public String constantsToString(){  //symbolic constant names
		 StringBuilder sb = new StringBuilder();
		 int size = intBiMap.size();
		 sb.append(size);
		 sb.append('\n');
		 assert size == nConstants : "mismatch between intBiMap.size()=" + size +  " and nConstants=" + nConstants;
		 BiMap<Integer,String> names = intBiMap.inverse();
			for (int i = 0; i < size; i++){
				sb.append(names.get(-i) + '\n'); 
			}
		return sb.toString();
	}

	//all of these getName and getIndex methods will fail
	//with a null pointer exception if the object is not present.
	public String getIntName(int index) {
		return intBiMap.inverse().get(index);
	}

	public int getIntIndex(IDec dec) {
		assert dec instanceof IntDec: dec.toString();
		return intBiMap.get(((IntDec)dec).getName());
	}
	

	int addScalar(ScalarDec dec) {
		return addScalar(dec, 0.0);
	}
	
	//add scalar entry at specified index.  
	//AcesHack, this allows the compiler to give scalars the same 
	//index in the scalar table as in the array table
	//the given index must not currently hold a value
	int addScalar(ScalarDec dec, double value, int index){
		assert nScalars == index;  //if this remains true, can eliminate while loop and this method
		while(nScalars<index){scalars.add(0.0); nScalars++; } //fill in with 0s
		scalars.add(value);
		nScalars++;
		if (dec != null) scalarBiMap.put(dec, index);
		return index;
	}
	
	int addScalar(ScalarDec dec, int index){
		return addScalar(dec, 0.0, index);
	}
	
	int addScalar(ScalarDec dec, double value){
		return addScalar(dec, value, nScalars);
	}
	
	int addScalarFortranIndex(ScalarDec dec){
		return addScalar(dec) + 1;
	}
	
	//AcesHack: constants go in the special constant array in the sip
	//currently they cannot be given a value when assigned.
	int addConstant(String name){
		int index = nConstants++;
		Integer oldVal = intBiMap.put(name, -index);  //insert negative of index
		assert oldVal == null: "adding duplicate constant name";
		return index;
	}


	//if double literal already exists, return its index,
	//otherwise add to scalar table at given index
	int addDoubleLiteral(double value){
		Double d = Double.valueOf(value);
		if (doubleLiteralBiMap.containsKey(d)){
			return doubleLiteralBiMap.get(d);
		}
		int index = addScalar(null,value);
		doubleLiteralBiMap.put(d,index);
		return index;
	}
	
	int addDoubleLiteralFortranIndex(double value){
		return addDoubleLiteral(value)+1;
	}

	// AcesHack: convert the int to a double and treat as double literal
	int addIntLiteral(int value) {
		double doubleValue = (double) value;
		return addDoubleLiteral(doubleValue);
	}

	/* reads n values from input into this ScalarTable, n can be found in header */
	public void read(int n, DataInput input) throws IOException{
		for (int i = 0; i != n; i++){
			double d = input.readDouble();
			scalars.add(d);
			nScalars++;
		}
	}
	
	public void readConstants(SIADataInput input) throws IOException{
	int nConstantsToRead = input.readInt();
		for (int i = 0; i != nConstantsToRead; i++){

				String s = input.readString();
			addConstant(s);

		}
	}
	
	public void write(DataOutput out) throws IOException{
		out.writeInt(nScalars);
		for (int i = 0; i != nScalars; i++){
			double v = scalars.get(i);
			out.writeDouble(v);
			}
	}
	
	public void writeConstants(SIADataOutput output) throws IOException{
		assert nConstants == intBiMap.size();
		output.writeInt(nConstants);
		BiMap<Integer, String> inverse = intBiMap.inverse();
		for (int i = 0; i != nConstants; i++){
			String name = inverse.get(-i);
		    output.writeString(name);	
		}
	}
	
	
	public static ScalarTable readScalarTable(SIADataInput input) throws IOException {
		int n = input.readInt();
		ScalarTable scalarTable = new ScalarTable();
		scalarTable.read(n,input);
		scalarTable.nScalars = n;
		return scalarTable;
	}
	

	//Only compares the array of scalars
	//For now, this is changed to compare within epsilon
	public  boolean equalVals(Object other){
		if (this == other) return true;
		if ( !(other instanceof ScalarTable)) return false;
		ArrayList<Double> scalars1 = ((ScalarTable) other).scalars;
		if ( ! (scalars.size() == scalars1.size())) return false;
		return scalars.equals(((ScalarTable)other).scalars);
		///		double EPSILON = 1.0e-323;		
//		for (int i = 0; i < scalars.size(); i++){
//			double d0 = scalars.get(i);
//			double d1 = scalars1.get(i);
//			if (d0 != d1){
//				System.out.println(i + ": d0=" + Double.doubleToRawLongBits(d0) 
//						+ " d1=" + Double.doubleToRawLongBits(d1));
//				double diff = d0 - d1;
//				if ( -EPSILON >= diff || diff >= EPSILON ) {
//					System.out.println("comparison failed on " + i);
//					return false;
//				}
//			}
//			
//		}
//		return true;
	}

	//prints symbolic constants with fortran index
	public String enumeratedConstantsToString() {
		 StringBuilder sb = new StringBuilder();
		 int size = intBiMap.size();
		 assert size == nConstants : "mismatch between intBiMap.size()=" + size +  " and nConstants=" + nConstants;
		 BiMap<Integer,String> names = intBiMap.inverse();
			for (int i = 0; i < size; i++){
				sb.append((i+1) + ": " + names.get(-i) + '\n'); 
			}
		return sb.toString();
	}


}
