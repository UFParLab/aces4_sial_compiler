/** This class stores string literals.  
 * 
 * String literals are case sensitive in SIAL
 */

package sial.code_gen;

import java.io.EOFException;
import java.io.IOException;

import sial.io.SIADataInput;
import sial.io.SIADataOutput;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class StringLiteralTable {
	BiMap<String, Integer> literalBiMap; // maps special instruction names to
											// the index in the function table
	int nStringLiterals; // number of entries in the array table
	int nextIndex = 0;

	public StringLiteralTable() {
		literalBiMap = HashBiMap.create();
		nStringLiterals = 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nStringLiterals);
		sb.append('\n');
		BiMap<Integer, String> inverse = literalBiMap.inverse();
		for (int i = 0; i != nStringLiterals; i++) {
			sb.append(inverse.get(i));
			sb.append('\n');
		}
		return sb.toString();
	}

	public String getLiteral(int index) {
		assert 0 <= index && index < literalBiMap.size() : "out of range index for string literal lookup";
		return literalBiMap.inverse().get(index);
	}



	int getAndAdd(String literal) {
		Integer idx = literalBiMap.get(literal);
		if (idx != null){
			return idx;
		}
		int nextIndex = literalBiMap.size();
		literalBiMap.put(literal, nextIndex);
		return nextIndex;
	}

	public void write(SIADataOutput output) throws IOException {
		int nStringLiterals = literalBiMap.size();
		output.writeInt(nStringLiterals);
		BiMap<Integer, String> inverse = literalBiMap.inverse();
		for (int i = 0; i != nStringLiterals; i++) {
			String literal = inverse.get(i);
			assert literal != null : "problem with traversal of literalBiMap";
			output.writeString(literal);
		}
	}

	public void read(SIADataInput input) throws IOException {
		nStringLiterals = input.readInt();
		for (int i = 0; i != nStringLiterals; i++) {
			String s = input.readString();
			getAndAdd(s);
		}
	}

	public static StringLiteralTable readStringLiteralTable(SIADataInput input)
			throws IOException {
		StringLiteralTable stringLiteralTable = new StringLiteralTable();
		stringLiteralTable.read(input);
		return stringLiteralTable;
	}

}
