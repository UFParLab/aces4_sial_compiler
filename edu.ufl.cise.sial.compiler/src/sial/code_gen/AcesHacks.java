package sial.code_gen;

import java.util.HashMap;


import com.google.common.collect.HashBiMap;
import sial.parser.SialParsersym;
//import com.google.common.collect.BiMap;
//import sial.code_gen.IndexTable.Entry;
//import sial.parser.SialParser;
//
//import sial.parser.Ast.ASTNodeToken;
//import sial.parser.Ast.IDec;
//import sial.parser.Ast.IIndexDec;
//import sial.parser.Ast.Ident;
//import sial.parser.Ast.ImportProg;
//import sial.parser.Ast.IndexDec;
//import sial.parser.Ast.IndexKind;
//import sial.parser.Ast.Range;
//import lpg.runtime.IAstVisitor;
//import lpg.runtime.IToken;

/** This class defines a collection of elements needed for initial compatibilityfl
 * with the current implementation.  Eventually, this file will no longer be needed.
 */ 


public class AcesHacks implements SipConstants, SialParsersym{
 
    static{
    	typeConstants = HashBiMap.create();
    	initializeTypeConstants();
    	initializeWhereCodes();
    }

//	public static enum operators {
//		norb, nocc, nvirt, bocc, eocc, bvirt, evirt, naocc, 
//		nbocc, navirt, nbvirt, baocc, bbocc, eaocc, ebocc, 
//		bavirt, bbvirt, eavirt, ebvirt, noccorb, nvirtorb, 
//		boccorb, eoccorb, bvirtorb, evirtorb, naoccorb, nboccorb, 
//		navirtorb, nbvirtorb, baoccorb, bboccorb, eaoccorb, eboccorb, 
//		bavirtorb, bbvirtorb, eavirtorb, ebvirtorb, cc_iter, cc_hist, 
//		cc_beg, scf_iter, scf_hist, scf_beg, natoms, itrips, itripe, 
//		ihess1, ihess2, jhess1, jhess2, subb, sube, sip_sub_segsize, 
//		sip_sub_occ_segsize, sip_sub_virt_segsize, sip_sub_ao_segsize, 
//		indexx, aoindex, moindex, moaindex, mobindex, laindex, subindex, 
//		window_index, served, temp, distributed, staticc, local, userinstr, 
//		scalar, sial, endsial, proc, endproc, call, doo, enddo, iff, 
//		computeint, setindex, put, putadd, collective, execute, get, 
//		prepare, prepareadd, request, destroye, writee, create, deletee, 
//		allocatee, deallocatee, endiff, elsee, cycle, exitt, pardo, endpardo, 
//		also, returnsil, prequest, where, of_kwd, in_kwd, addassign, subassign, 
//		multassign, divassign, eq, ge, le, gt, lt, ne, assign, add, sub, 
//		mult, divide, tensor, lparen, rparen, lbracket, rbracket, comma, 
//		returnn, id, fconst, iconst, symbolic_const, jump, start, contract, 
//		sum, diff, eqf, gef, lef, gtf, ltf, nef, addf, subf, multf, dividef, 
//		idf, divide_array, error
//	};

//	static final String[] keywords = { "norb", "nocc", "nvirt", "bocc", "eocc",
//			"bvirt", "evirt", "naocc", "nbocc", "navirt", "nbvirt", "baocc",
//			"bbocc", "eaocc", "ebocc", "bavirt", "bbvirt", "eavirt", "ebvirt",
//			"noccorb", "nvirtorb", "boccorb", "eoccorb", "bvirtorb",
//			"evirtorb", "naoccorb", "nboccorb", "navirtorb", "nbvirtorb",
//			"baoccorb", "bboccorb", "eaoccorb", "eboccorb", "bavirtorb",
//			"bbvirtorb", "eavirtorb", "ebvirtorb", "cc_iter", "cc_hist",
//			"cc_beg", "scf_iter", "scf_hist", "scf_beg", "natoms", "itrips",
//			"itripe", "ihess1", "ihess2", "jhess1", "jhess2", "subb", "sube",
//			"sip_sub_segsize", "sip_sub_occ_segsize", "sip_sub_virt_segsize",
//			"sip_sub_ao_segsize", "index", "aoindex", "moindex", "moaindex",
//			"mobindex", "laindex", "subindex", "window_index", "served",
//			"temp", "distributed", "static", "local", "userinstr", "scalar",
//			"sial", "endsial", "proc", "endproc", "call", "do", "enddo", "if",
//			"compute_integrals", "setindex", "put", "put", "collective",
//			"execute", "get", "prepare", "prepare", "request", "destroy",
//			"write", "create", "delete", "allocate", "deallocate", "endif",
//			"else", "cycle", "exit", "pardo", "endpardo", "also", "return",
//			"prequest", "where", "of", "in", "+=", "-=", "*=", "/=", "==",
//			">=", "<=", ">", "<", "!=", "=", "+", "-", "*", "/", "^", "(", ")",
//			"[", "]", ",", "\n", "id", "fconst", "iconst", "symbolic_const",
//			"jump", "start", "contract", "sum", "diff", "==", ">=", "<=", ">",
//			"<", "!=", "+", "-", "*", "/", "idf", "/", "error" };

	
	public static final int max_array_index = 6;  //rename to max dimensions

	
//	// linear search of array containing Aces keywords
//		public static int findAcesKeyword(String s) {
//		int i;
//		for (i = 0; i != keywords.length && !keywords[i].equals(s); i++) {
//		}
//		if (i == keywords.length)
//			return -1;
//		return i;
//	}
	


//	public final static int 
//	aoindex_t = 1001,
//    moindex_t = 1002,
//    moaindex_t = 1003,
//    mobindex_t = 1004,
//    index_t = 1005,
//    laindex_t = 1006,
//    subindex_t = 1007,
//    served_array_t = 201,
//    static_array_t = 202,
//    distributed_array_t = 203,
//    temp_array_t =  204,
//    scalar_value_t = 205,
//    local_array_t =  206,
//    dummy_array_type = 207,
//    int_value_t = 208; //TODO  check this value, new type

	public static HashBiMap<String,Integer> typeConstants;
	
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
		typeConstants.put("dummy", dummy_array_type);
		typeConstants.put("int", int_value_t);
    }
    
    static String getTypeName(int type){
    	return typeConstants.inverse().get(type);
    }
    
    static int getTypeConstant(String name){
    	return typeConstants.get(name.toLowerCase());
    }


    
//operator codes for where clause  (See decode_cond_code.f)
	public static HashMap<Integer,Integer> whereCodes;

	//note that these values will be incremented 
	//by the addOptableEntry routine so are
	//one less than the constants used in the sip
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
//    static class AcesImportProg extends ImportProg{
//
//		public AcesImportProg(SialParser environment, IToken leftIToken,
//				IToken rightIToken, ASTNodeToken _STRINGLIT) {
//			super(environment, leftIToken, rightIToken, _STRINGLIT);
//			throw new UnsupportedOperationException();
//		}
//		
//		public AcesImportProg(String file){
//			super(null, null,null, null);
//			this.file = file;
//		}
//    	
//		String file;
//		
//		@Override
//		public String getName(){return file;}
//		
//		@Override
//		public
//		SialParser getEnvironment(){
//			throw new UnsupportedOperationException();
//		}
//		
//		@Override
//		public boolean equals(Object o){
//	        if (o == this) return true;
//	        if (! (o instanceof AcesImportProg)) return false;
//	        if (! super.equals(o)) return false;
//	        AcesImportProg other = (AcesImportProg) o;
//	        if (! file.equals(other.file)) return false;
//	        return true;
//	    }
//		
//		@Override
//	    public int hashCode()
//	    {
//	        int hash = super.hashCode();
//	        hash = hash * 31 + (file.hashCode());
//	        return hash;
//	    }
//    }
//    
//    //this class used for dummy declarations for the predefined index types
//    //it adds a string _Name, and overrides certain methods to throw an UnsupportedOperationException.
//    static class AcesIndexDec extends IndexDec{
//
//    	private String _Name;
//    	
//		public AcesIndexDec(SialParser environment, IToken leftIToken,
//				IToken rightIToken, IndexKind _IndexKind, Ident _Ident,
//				Range _Range) {
//			super(environment, leftIToken, rightIToken, _IndexKind, _Ident, _Range);
//			throw new UnsupportedOperationException();
//		}
//		
//		public AcesIndexDec(IndexKind _IndexKind, String _Name, Range _Range){
//			super(null,null,null,_IndexKind,null,_Range);
//			this._Name = _Name;
//		}
//		
//        @Override
//        public String getName() { return _Name; }
//        
//        @Override
//        public String getTypeName() {
//        	return getName();
//        }
//               
//        @Override
//        public Ident getIdent(){ 
//        	throw new UnsupportedOperationException();
//        }
//
//		@Override
//		public IToken getLeftIToken() {
//			throw new UnsupportedOperationException();
//		}
//		@Override
//		public IToken getRightIToken() {
//			throw new UnsupportedOperationException();
//		}
//		@Override
//		public void accept(IAstVisitor v) {
//			throw new UnsupportedOperationException();
//		}
//		
//		@Override
//	    public int hashCode()
//	    {
//	        int hash = super.hashCode();
//	        hash = hash * 31 + (getIndexKind().hashCode());
//	        hash = hash * 31 + (_Name.hashCode());
//	        hash = hash * 31 + (getRange().hashCode());
//	        return hash;
//	    } 
//	
//    }
   

    
//    //Call to put moindex, etc in Index table as currently done in Aces.
//    static final void initializeAcesIndexTable(SipTable sipTable){
//       IndexTable indexTable = sipTable.indexTable;
//    	 indexTable.addEntry(new AcesIndexDec(null, "moindex",  null), 1, 0, moindex_t);
//    	 indexTable.addEntry(new AcesIndexDec(null, "moaindex",  null),1,0,moaindex_t);
//    	 indexTable.addEntry(new AcesIndexDec(null, "mobindex",  null),1,0,mobindex_t);
//    	 indexTable.addEntry(new AcesIndexDec(null, "aoindex",  null), 1,0,aoindex_t);
//    	 indexTable.addEntry(new AcesIndexDec(null, "index",  null), 1,0,index_t);
//      }
//    
	
//public String opcodeName(int opcode)
//	switch (opcode){
//	case contraction_op: return "contraction_op";
//	case sum_op: return "sum_op";
//	case reindex_op  return "reindex_op";
//	case do_op       return "
//	case enddo_op    return "
//	case get_op       return "
//	case user_sub_op   return "
//	case put_op        return "
//	case go_to_op       return "
//	case create_op    return "
//	case delete_op     return "
//	case call_op        return "
//	case return_op     return "
//	case jz_op         return "
//	case stop_op        return "
//	case sp_add_op      return "
//	case sp_sub_op      return "
//	case sp_mult_op     return "
//	case sp_div_op      return "
//	case sp_equal_op    = 120,
//	case sp_nequal_op   = 121,
//	case sp_ge_op       = 122,
//	case sp_le_op       = 123,
//	case sp_gt_op       = 124,
//	case sp_lt_op       = 125,
//	case sp_ldi_op      = 126,
//	case sp_ldindex_op  = 127,
//	case pardo_op       = 128,
//	case endpardo_op    = 129,
//	case exit_op        = 130,
//	case assignment_op  = 131,      // =
//	case cycle_op       = 132,
//	case self_multiply_op = 134,    // *= scalar
//	case subtract_op    = 135,      // -
//	case collective_sum_op = 136,
//	case divide_op      = 137,
//	case prepare_op     = 138,
//	case request_op     = 139,
//	case compute_integrals_op = 140,
//	case put_replace_op = 141,
//	case tensor_op      = 142,
//	case fl_add_op      = 146,
//	case fl_sub_op      = 147,
//	case fl_mult_op     = 148,
//	case fl_div_op      = 149,
//	case fl_eq_op       = 150,
//	case fl_ne_op       = 151,
//	case fl_ge_op       = 152,
//	case fl_le_op       = 153,
//	case fl_gt_op       = 154,
//	case fl_lt_op       = 155,
//	case fl_load_value_op  = 157,
//	case prepare_increment_op = 158,
//	case allocate_op    = 159,
//	case deallocate_op  = 160,
//	case sp_ldi_sym_op  = 161,
//	case destroy_op     = 162,
//	case prequest_op    = 163,
//	case where_op       = 164,	
//}
