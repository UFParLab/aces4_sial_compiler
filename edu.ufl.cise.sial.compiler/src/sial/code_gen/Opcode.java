package sial.code_gen;

//opcodes
public enum Opcode {
	
//sequential control
	//branch
	goto_op("optable slot of destination","","","","unconditional jump"),
	jump_if_zero_op("optable slot of destination","","","","jump if top of sip control stack is zero"),
	stop_op("","","","","immediately halt sial program.  Useful during debugging"),

	//procedure calls
	call_op("optable slot of procedure","","","","call a sial procedure, first push slot of next instruction on control stack"),
	return_op("","","","","return from procedure. optable slot of caller is on the control stack"),	
	
	//user provided super instruction
	execute_op("","","","",""),	
	
	//sequential loops
	do_op("","","","",""),	
	enddo_op("","","","",""),		
	dosubindex_op("","","","",""),	
	enddosubindex_op("","","","",""),
	exit_op("","","","",""),		
	where_op("","","","",""),	
	cycle_op("","","","",""),		
	
//parallel control
	
	//parallel loop
	pardo_op("","","","",""),	
	endpardo_op("","","","",""),	
	
	//structure multiple parallel loops
	begin_pardo_section_op("","","","",""),
	end_pardo_section_op("","","","",""),
	
	//barriers
	sip_barrier_op("","","","",""),
	server_barrier_op("","","","",""),
	
//local block management 
	push_block_selector_op("","","","",""),
	get_block_for_reading_op("","","","",""),
	get_block_for_accumulate_op("","","","",""),
	get_block_for_writing_op("","","","",""),
	get_block_for_update_op("","","","",""),
	allocate_op("","","","",""),
	deallocate_op("","","","",""),
	
//distributed arrays
	get_op("","","","",""),
	put_accumulate_op("","","","",""),
	put_replace_op("","","","",""),
	create_op("","","","",""),
	delete_op("","","","",""),

//served arrays  (this is a legacy from aces3.  In Aces4, distributed and served arrays are handled uniformly)
	request_op("","","","",""),
	prequest_op("","","","",""),
	prepare_op("","","","",""),
	prepare_accumulate_op("","","","",""),
	destroy_op("","","","",""),
	
//integer expressions and local data movement-
	int_load_value_op("","","","",""),
	int_load_literal_op("","","","",""),
	int_store_op("","","","",""),
	index_load_value_op("","","","",""),
	int_add_op("","","","",""),
	int_subtract_op("","","","",""),
	int_multiply_op("","","","",""),
	int_divide_op("","","","",""),
	int_equal_op("","","","",""),
	int_nequal_op("","","","",""),
	int_ge_op("","","","",""),
	int_le_op("","","","",""),
	int_gt_op("","","","",""),
	int_lt_op("","","","",""),
	int_neg_op("","","","",""),
	cast_to_int_op("","","","",""),

	
//scalar expresssions
	scalar_load_value_op("","","","",""),
	scalar_store_op("","","","",""),
	scalar_neg_op("","","","",""),
	scalar_add_op("","","","",""),
	scalar_subtract_op("","","","",""),
	scalar_multiply_op("","","","",""),
	scalar_divide_op("","","","",""),
	scalar_eq_op("","","","",""),
	scalar_ne_op("","","","",""),
	scalar_ge_op("","","","",""),
	scalar_le_op("","","","",""),
	scalar_gt_op("","","","",""),
	scalar_lt_op("","","","",""),
	cast_to_scalar_op("","","","",""),

	//scalar collective operations
	collective_sum_op("","","","",""),
	assert_same_op("","","","",""),
	slice_op("","","","",""),
	insert_op("","","","",""),
	
	//expressions and operations on blocks
	tensor_op("","","","",""),
	contraction_accumulate_op("","","","",""),
	copy_or_transpose_block_op("","","","",""),
	fill_block_op("","","","",""),
	block_add_op("","","","",""),
	block_subtract_op("","","","",""),
	block_contract_op("","","","",""),
	block_contract_accumulate_op("","","","",""),
	
	//expressions and operations on strings
	string_load_literal_op("","","","",""),

	//output commands
	print_op("","","","",""),
	println_op("","","","",""),
	print_index_op("","","","",""),
	print_scalar_op("","","","",""),
	print_int_op("","","","",""),
	
	//gpu commands
	gpu_on_op("","","","",""),
	gpu_off_op("","","","",""),
	gpu_allocate_op("","","","",""),
	gpu_free_op("","","","",""),
	gpu_put_op("","","","",""),
	gpu_get_op("","","","",""),
	gpu_get_int_op("","","","",""),
	gpu_put_int_op("","","","",""),

	
    //manage data between sial programs 	
	set_persistent_op("","","","",""),
	restore_persistent_op("","","","",""),

	//sentinal
	invalid_op("","","","","");
	
	
	String arg1Desc, arg2Desc, arg3Desc, indDesc, comment; 
	private Opcode(String arg1Desc, String arg2Desc, String arg3Desc, String indDesc, String comment ){
		this.arg1Desc = arg1Desc;
		this.arg2Desc = arg2Desc;
		this.arg3Desc = arg3Desc;
		this.indDesc = indDesc;
		this.comment = comment;
	}
	
	final int offset = 100;
	int opcodeValue = this.ordinal()+ offset;
	
	
	public String arg1(){return arg1Desc;}
	public String arg2(){return arg2Desc;}
	public String arg3(){return arg3Desc;}
	public String indArray(){return indDesc;}
	public String comment(){return comment;}

	boolean isPrintable(){return false;}

	
	
	public String getMacro(){
		StringBuilder sb = new StringBuilder();
		sb.append("SIPOP(");
		sb.append(name()).append(',');
		sb.append(opcodeValue).append(',');
		sb.append('\"').append(toString()).append("\",");
		sb.append(isPrintable());
		sb.append(')');
		return sb.toString();
	}
	
	public static String generateCPPHeader(){
		StringBuilder sb = new StringBuilder();
		sb.append("#define SIP_OPCODES \\\n"); 

		for (Opcode op : Opcode.values()){
			sb.append(op.getMacro());
			sb.append("\\\n");					
		}
		return sb.toString();
	}
	
	public String toString(){
		return name().substring(0,name().lastIndexOf("_op"));
	}
		

	String latexLine(){return "";}
	public static String generateLatexTable(){
		StringBuilder sb = new StringBuilder();
		sb.append("\\documentclass{article}\n");
		sb.append("\\begin{document}\n");
		sb.append("\\begin{tabular}{r l c c c c p{2in}}\n");
		sb.append("\\hline\\hline\n");
		sb.append("opcode & name & arg 1 & arg 2 & arg 3 * selector array & comment \\\\\n");
		sb.append("\\hline\\hline\n");
		for (Opcode op: Opcode.values()){
			sb.append(op.opcodeValue).append('&');
			sb.append("\\verb|").append(op.name()).append("|&");
			sb.append("\\verb|").append(op.arg1()).append("|&");
			sb.append("\\verb|").append(op.arg2()).append("|&");
			sb.append("\\verb|").append(op.arg2()).append("|&");
			sb.append("\\verb|").append(op.arg1()).append("|&");
			sb.append(op.comment());
			sb.append("\\\\\n\\hline\n");

		}
		sb.append("\\end{tabular}\n");
		sb.append("\\end{document}\n");
		return sb.toString();
	}
}