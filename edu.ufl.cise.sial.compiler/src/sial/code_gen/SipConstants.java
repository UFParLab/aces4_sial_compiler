package sial.code_gen;

import sial.parser.Ast.IndexDec;

public interface SipConstants {
	
//TODO change this to get from command line and add consistency checking in the SioxReader
public static final int max_rank = 6;  
	
//type constants
public final static int 
	aoindex_t = 1001,
    moindex_t = 1002,
    moaindex_t = 1003,
    mobindex_t = 1004,
    index_t = 1005,
    laindex_t = 1006,
    subindex_t = 1007
    ;


//bit flags representing constants for new array attributes
//these can be combined with bitwise or as below
//individual flags can be tested with bitwise and--
//   attribute & attr_contiguous is non zero if attribute has attr_contiguous set.
public static final int
attr_integer = 0x01, //0x01 ==   1 == "00000001"
attr_sip_consistent = 0x02,     //0x02 ==   2 == "00000010"
attr_contiguous =0x04,  //0x04 ==   4 == "00000100"
attr_auto_allocate = 0x08,  //0x08 ==   8 == "00001000"
attr_scope_extent = 0x10, //0x10 ==  16 == "00010000"
attr_predefined = 0x20,  //0x20 ==  32 == "00100000"
attr_persistent = 0x40,  //0x40 ==  64 == "01000000"
attr_scalar = 0x80,  //0x80 == 128 == "10000000"
attr_sparse = 0x100; //0x100 == 256 == "100000000"

//attributes for existing types
public final static int
	served_array_t = attr_sip_consistent, // ==2
//   static_array_t = (attr_contiguous | attr_auto_allocate | attr_predefined | attr_persistent),  //==108
	static_array_t = (attr_contiguous | attr_auto_allocate),  //==12
    distributed_array_t = served_array_t, //==2
    temp_array_t = (attr_auto_allocate | attr_scope_extent), //== 24
    scalar_value_t = (attr_contiguous | attr_auto_allocate | attr_scalar), //==140
    local_array_t = 0, //all defaults give a local_array
    int_value_t = (attr_integer | scalar_value_t),
    local_contiguous_array_t = attr_contiguous ;
  
    
//priorities for the block server
public final static int
   served_array_priority = 1,
   distributed_array_priority = 2,
   local_all_indices_simple = 3;


//constants in .siox files
public final static int
    magic = 70707,  //recorded in header and checked at load time
    version = 3,    //recorded in header and checked at load time
    release = 0,    //recorded in header and checked at load time
    wild = 90909;   // indicates wild card value for allocate statements
//    push_result_int = -1,  //indicates that result of expression evaluation should be pushed onto the sip control_stack
//    push_result_scalar = -2,  //indicates that the result of expression evaluation should be pushed onto the sip expression stack
//    invalid_dest_for_block = -3;  //temporary value for destiniation of expressions with block values.  This should be replaced during code generation.
    

}