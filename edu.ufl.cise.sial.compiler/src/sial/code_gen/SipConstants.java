package sial.code_gen;

import sial.parser.Ast.IndexDec;

public interface SipConstants {
	
//type constants
public final static int 
	aoindex_t = 1001,
    moindex_t = 1002,
    moaindex_t = 1003,
    mobindex_t = 1004,
    index_t = 1005,
    laindex_t = 1006,
    subindex_t = 1007,
    served_array_t = 201,
    static_array_t = 202,
    distributed_array_t = 203,
    temp_array_t =  204,
    scalar_value_t = 205,
    local_array_t =  206,
    dummy_array_type = 207,
    int_value_t = 208; //TODO  check this value, new type
    
//constants in .sio files
public final static int
    magic = 70707,
    version = 1,
    release = 0,
    wild = 90909;

//mask used by sip to recognize a subindex
    int in_index_mask = 65536;

//opcodes
public final static int 
	contraction_op = 101,
	sum_op         = 102,
	reindex_op     = 103,
	do_op          = 104,
	enddo_op       = 105,
	get_op         = 106,
	user_sub_op    = 107,
	put_op         = 108,
	go_to_op       = 109,
	create_op      = 110,
	delete_op      = 111,
	call_op        = 112,
	return_op      = 113,
	jz_op          = 114,
	stop_op        = 115,
	sp_add_op      = 116,
	sp_sub_op      = 117,
	sp_mult_op     = 118,
	sp_div_op      = 119,
	sp_equal_op    = 120,
	sp_nequal_op   = 121,
	sp_ge_op       = 122,
	sp_le_op       = 123,
	sp_gt_op       = 124,
	sp_lt_op       = 125,
	sp_ldi_op      = 126,
	sp_ldindex_op  = 127,
	pardo_op       = 128,
	endpardo_op    = 129,
	exit_op        = 130,
	assignment_op  = 131,      // =
	cycle_op       = 132,
	self_multiply_op = 134,    // *= scalar
	subtract_op    = 135,      // -
	collective_sum_op = 136,
	divide_op      = 137,
	prepare_op     = 138,
	request_op     = 139,
	compute_integrals_op = 140,
	put_replace_op = 141,
	tensor_op      = 142,
	fl_add_op      = 146,
	fl_sub_op      = 147,
	fl_mult_op     = 148,
	fl_div_op      = 149,
	fl_eq_op       = 150,
	fl_ne_op       = 151,
	fl_ge_op       = 152,
	fl_le_op       = 153,
	fl_gt_op       = 154,
	fl_lt_op       = 155,
	fl_load_value_op  = 157,
	prepare_increment_op = 158,
	allocate_op    = 159,
	deallocate_op  = 160,
	sp_ldi_sym_op  = 161,
	destroy_op     = 162,
	prequest_op    = 163,
	where_op       = 164,
	print_op       = 165,
	println_op     = 166,
	print_index_op = 167,
	print_scalar_op = 168,
	last_opcode    = print_scalar_op;
}