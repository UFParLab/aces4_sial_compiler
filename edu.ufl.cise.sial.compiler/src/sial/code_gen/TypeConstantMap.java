/** Contains maps to store certain constant values. */

package sial.code_gen;

import java.util.HashMap;


import com.google.common.collect.HashBiMap;
import sial.parser.SialParsersym;

public class TypeConstantMap implements SipConstants, SialParsersym{
 
    static{
    	typeConstants = new HashMap<String,Integer>();
    	initializeTypeConstants();
    	initializeWhereCodes();
    }

	
	public static final int max_rank = 6;  

	public static HashMap<String,Integer> typeConstants;
	
    static void initializeTypeConstants(){
    	typeConstants.put("aoindex",aoindex_t);
    	typeConstants.put("moindex",moindex_t);
    	typeConstants.put("moaindex",moaindex_t);
    	typeConstants.put("mobindex", mobindex_t);
    	typeConstants.put("index", index_t);
    	typeConstants.put("laindex",laindex_t);
    	typeConstants.put("subindex", subindex_t);
    	typeConstants.put("served", served_array_t);
    	typeConstants.put("static", static_array_t);
    	typeConstants.put("distributed", distributed_array_t);
        typeConstants.put("scalar", scalar_value_t);
        typeConstants.put("temp", temp_array_t);
		typeConstants.put("local", local_array_t);
		typeConstants.put("int", attr_integer);
		typeConstants.put("sip_scope", attr_sip_consistent);
		typeConstants.put("contiguous", attr_contiguous);
		typeConstants.put("auto_allocate", attr_auto_allocate);
		typeConstants.put("scope_extent", attr_scope_extent);
		typeConstants.put("predefined", attr_predefined);
		typeConstants.put("persistent", attr_persistent );
    }
    
    static int getTypeConstant(String name){
    	return typeConstants.get(name.toLowerCase());
    }


    
//operator codes for where clauses
	public static HashMap<Integer,Integer> whereCodes;
	private static void initializeWhereCodes() {
		whereCodes = new HashMap<Integer,Integer>();
		whereCodes.put(TK_EQ,0);
		whereCodes.put(TK_GEQ, 1);
		whereCodes.put(TK_LEQ, 2);
		whereCodes.put(TK_GREATER, 3);
		whereCodes.put(TK_LESS, 4);
		whereCodes.put(TK_NEQ, 5);		
	}
    
}

   

    


