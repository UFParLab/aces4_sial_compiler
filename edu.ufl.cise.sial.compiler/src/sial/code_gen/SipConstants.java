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
    int_value_t = (attr_integer | scalar_value_t);
  
    
//priorities for the block server
public final static int
   served_array_priority = 1,
   distributed_array_priority = 2;


//constants in .siox files
public final static int
    magic = 70707,  //recorded in header and checked at load time
    version = 2,    //recorded in header and checked at load time
    release = 0,    //recorded in header and checked at load time
    wild = 90909;   // indicates wild card value for allocate statements
//    push_result_int = -1,  //indicates that result of expression evaluation should be pushed onto the sip control_stack
//    push_result_scalar = -2,  //indicates that the result of expression evaluation should be pushed onto the sip expression stack
//    invalid_dest_for_block = -3;  //temporary value for destiniation of expressions with block values.  This should be replaced during code generation.
    




/*//opcodes
public final static int 
//	contraction_op = 101,
//	sum_opp         = 102,
	push_block_selector_op       = 103,
	do_op          = 104,
	enddo_op       = 105,
	get_op         = 106,
	user_sub_op    = 107,
	put_accumulate_op         = 108,
	go_to_op       = 109,
	create_op      = 110,
	delete_op      = 111,
	call_op        = 112,
	return_op      = 113,
	jz_op          = 114, //not used.  check this
	stop_op        = 115,
	int_add_op      = 116,
	int_subtract_op      = 117,
	int_multiply_op     = 118,
	int_divide_op      = 119,
	int_equal_op    = 120,
	int_nequal_op   = 121,
	int_ge_op       = 122,
	int_le_op       = 123,
	int_gt_op       = 124,
	int_lt_op       = 125,
	int_load_literal_op      = 126,
	index_load_value_op  = 127,
	pardo_op       = 128,
	endpardo_op    = 129,
	exit_op        = 130,
//	int_assignment_op  = 131,      // =
	cycle_op       = 132,
//	self_multiply_op = 134,    // *= scalar
//	subtract_op    = 135,      // -
	collective_sum_op = 136,
//	divide_op      = 137,
	prepare_op     = 138,
	request_op     = 139,
//	compute_integrals_op = 140,
	put_replace_op = 141,
	tensor_op      = 142,
	scalar_add_op      = 146,
	scalar_subtract_op      = 147,
	scalar_multiply_op     = 148,
	scalar_divide_op      = 149,
	scalar_eq_op       = 150,
	scalar_ne_op       = 151,
	scalar_ge_op       = 152,
	scalar_le_op       = 153,
	scalar_gt_op       = 154,
	scalar_lt_op       = 155,
	scalar_load_value_op  = 157,
	prepare_increment_op = 158,
	allocate_op    = 159,
	deallocate_op  = 160,
	int_load_value_op  = 161,
	destroy_op     = 162,
	prequest_op    = 163,
	where_op       = 164,
	print_op       = 165,
	println_op     = 166,
	print_index_op = 167,
	print_scalar_op = 168,
//	assign_scalar_op = 169,
//	assign_block_op = 170,
//	assign_fill_op = 180,
//	assign_transpose_op = 181,
	dosubindex_op = 182,
	enddosubindex_op = 183,
	slice_op = 184,
	insert_op = 185,
	sip_barrier_op = 186,
	server_barrier_op = 187,
	gpu_on_op = 188,
	gpu_off_op = 189,
	gpu_allocate_op= 190,
	gpu_free_op = 191,
	gpu_put_op = 192,
	gpu_get_op = 193,
	set_persistent_op = 194,
	restore_persistent_op=195,
	int_neg_op = 196,
	scalar_neg_op = 197,
//	int_load_literal_op = 198,
	string_load_literal_op = 199,
	print_int_op = 100,
	cast_to_int_op = 101,
	cast_to_scalar_op = 102,
//	scalar_assignment_op = 103,
	contraction_accumulate_op = 104,
	begin_pardo_section_op = 105,
	end_pardo_section_op = 106,
	get_block_for_reading_op = 107,
	get_block_for_accumulate_op = 108,
	get_block_for_writing_op = 109,
	get_block_for_update_op = 110,
	copy_or_transpose_block_op = 111,
	fill_block_op = 112,
	block_add_op = 113,
	block_subtract_op = 114,
	block_contract_op = 115,
	block_contract_accumulate_op = 116,
	assert_same_op = 117,
	int_modulo_op = 118,
	int_store_op = 119,
	scalar_store_op = 120
//	,
//	last_opcode = scalar_store_op
	;
}*/

	;
}