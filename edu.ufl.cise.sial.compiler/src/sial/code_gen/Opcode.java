package sial.code_gen;

//opcodes
public enum Opcode {
	
//sequential control
	//branch
	goto_op("","","","optable slot of destination","unconditional jump"),
	jump_if_zero_op("optable slot of destination","","","","jump if top of sip control stack is zero"),
	stop_op("","","","","immediately halt sial program.  Useful during debugging"),

	//procedure calls
	call_op("optable slot of procedure","","","","call a sial procedure, first push slot of next instruction on control stack"),
	return_op("","","","","return from procedure. optable slot of caller is on the control stack"),	
	
	//user provided super instruction
	execute_op("super instruction table slot","","number of arguments","",
			"execute indicated user provided super instruction.  The argumnents are on the block selector stack"),	
	
	//sequential loops
	do_op("","","optable slot of enddo","first element is slot of loop index variable","serial do loop"),	
	enddo_op("","","","first element is slot of loop index variable","marks end of serial do loop"),		
	dosubindex_op("parent index","","optable slot of enddosubindex","first element is slot of loop index variable","serial loop over subindex"),	
	enddosubindex_op("parent","","","first element is slot of loop index variable","marks end of loop over subindex variable"),
	exit_op("","","","","exit current do loop"),		
	where_op("","","","",""),			
	
//parallel control
	
	//parallel loop
	pardo_op("number of indices","","","indices indicated in loop","beginning of pardo loop"),	
	endpardo_op("number of indices","","","indices indicated in loop","end of pardo loop"),	
	
	//structure multiple parallel loops
	begin_pardo_section_op("","","","","start of a pardo section"),
	end_pardo_section_op("","","","","end of a pardo section"),
	
	//barriers and broadcast
	sip_barrier_op("","","","",""),
	broadcast_static_op("index of static array","","","","broadcast static array from rank whose value is on top of control stack"),
	
//local block management 
	push_block_selector_op("rank","array table slot","","selector slots",
			"push the block selector onto the sip block selector stack.  If the rank is 0, this is either a scalar or a static or contig array given without a selector"),
//	get_block_for_reading_op("rank","arraySlot","","selector","gets indicated block and pushes BlockPtr onto sip block stack.  It is a fatal error if this block does not exist"),
//	get_block_for_accumulate_op("array table slot","","","selector","gets indicated block, creating and initializing to zero if it doesn't exists.  Push the BlockPtr onto sip block selector stack"),
//	get_block_for_writing_op("array table slot","","","selector","gets indicated block, creating it if it doesn't exist.  Push the BlockPtr onto sip block stack"),
//	get_block_for_update_op("","","","",""),
	allocate_op("array table slot","","","block selector indices(may contain wild cards)","allocate block(s) of local array.  "),
	deallocate_op("array table slot","","","block selector indices","deallocate block(s) of local array"),
	allocate_contiguous_op("array","rank","","","allocates memory for a region of a contiguous local array.  The boundaries are obtained from the control_stack where they have been "+
	   "pushed in the order rank-1_lower, rank-1_upper, 0_lower, 0_upper, etc."),
	deallocate_contiguous_op("array","rank","","","deallocates memory for a region of a contiguous local array.  The boundaries are obtained from the control_stack where they have been "+
	   "pushed in the order rank-1_lower, rank-1_upper, 0_lower, 0_upper, etc."),
	
//distributed and served arrays
// In aces4m SIAL syntax supports distributed and served arrays as a legacy from ACES3.  They are 
// handled identically and request, prepare, and destroy statements generate  the same code
// as get, put, and delete.
	get_op("array table slot of desired block","","","selector slots","get block selector from selector stack and send get request to appropriate server (args are redundant)"),
	put_accumulate_op("array table slot of right hand side","array table slot of left hand side","","",
			"get right and left side blocks (left pushed firsts) from selector stack and send rhs block to appropriate server to accumulate into its copy of lhs block"),
	put_replace_op("array table slot of right hand side","array table slot of left hand side","","",
			"get right and left side blocks (left pushed firsts) from selector stack and send rhs block to appropriate server to replace its copy of lhs block"),
	create_op("array table slot","","","","create distributed array.  In aces4, blocks are created lazily"),
	delete_op("array table slot","","","","delete distributed array"),

////served arrays  (this is a legacy from aces3.  In Aces4, distributed and served arrays are handled uniformly)
//	request_op("array table slot of desired block","","","selector slots","get block selector from selector stack and send get request to appropriate server (args are redundant)"),
//	prepare_op("array table slot of right hand side array","array table slot of left hand side array","","",
//			"get right and left side blocks (left pushed firsts) from selector stack and send rhs block to appropriate server to replace its copy of lhs block"),
//	prepare_accumulate_op("array table slot of right hand side array","array table slot of left hand side array","","",
//			"get right and left side blocks (left pushed firsts) from selector stack and send rhs block to appropriate server to accumulate into its copy of lhs block"),
//	destroy_op("","","","",""),
	
//integer expressions and local data movement-
	int_load_value_op("IntTable slot","","","","loads current value of indicated int onto sip control stack"),
	int_load_literal_op("","","","",""),
	int_store_op("IntTable slot","opcode of operator, or int_store_op if plain assignment","","",
			"removes value from top of sip control stack, performs indicated op with value of given int, and stores in given int"),
	index_load_value_op("IndexTable slot","","","","load current value of index and stores it on the control stack"),
	int_add_op("","","","","removes the top two values from the control stack, adds them together, and pushes the result on the control stack."),
	int_subtract_op("","","","","removes the top two values from the control stack, subtracts the first popped from the second, and pushes the result onto the control stack "),
	int_multiply_op("","","","","removes the top two values from the control stack, multiplies  them together, and pushes the result on the control stack."),
	int_divide_op("","","","","removes the top two values from the control stack, divides the second popped by the first, and pushes the result onto the control stack "),
	int_equal_op("","","","","==, args are popped from sip control stack, result is placed on control stack"),
	int_nequal_op("","","","","!=, args are popped from sip control stack, result is placed on control stack"),
	int_ge_op("","","","",">=, args are popped from sip control stack, result is placed on control stack"),
	int_le_op("","","","","<=, args are popped from sip control stack, result is placed on control stack"),
	int_gt_op("","","","",">, args are popped from sip control stack, result is placed on control stack"),
	int_lt_op("","","","","<,  args are popped from sip control stack, result is placed on control stack"),
	int_neg_op("","","","","unary negation, arg is popped from sip control stack, result is placed on control stack"),
	cast_to_int_op("","","","","removes scalar value from expression stack, converts to int, and puts it on the control stack"),

	
//scalar expresssions
	scalar_load_value_op("array table slot","","","","loads value of scalar in given slot onto sip expression stack"),
	scalar_store_op("array table slot of scalar","opcode of operator or scalar_store_op if plain assignment","","",
			"removes value from top of sip expression stack, performs indicated op with value of given scalar,  and stores in given scalar"),
	scalar_add_op("","","","","removes top two elements from expression stack, adds together, pushes result on expression stack"),
	scalar_subtract_op("","","","","removes top two elements from expression stack, subtracts top from next-to-top (i.e. args pushed left to right),  pushes result on expressio stack"),
	scalar_multiply_op("","","","","removes top two elements from expression stack, multiplies together, pushes result on expression stack"),
	scalar_divide_op("","","","","removes top two elements from expression stack, divides next-to-top by top (i.e. args pushed left to right), pushes result on expression stack"),
	scalar_exp_op("","","","","removes top two elements s,t from expression stack, computer s^t (c++ pow(s,t)), args pushed from left to right, pushes result onto expression stack"),
	scalar_eq_op("","","","","==, args are popped from sip expression stack, result is placed on control stack"),
	scalar_ne_op("","","","","!=, args are popped from sip expression stack, result is placed on control stack"),
	scalar_ge_op("","","","",">=, args are popped from sip expression stack, result is placed on control stack"),
	scalar_le_op("","","","","<=, args are popped from sip expression stack, result is placed on control stack"),
	scalar_gt_op("","","","",">, args are popped from sip expression stack, result is placed on control stack"),
	scalar_lt_op("","","","","<, args are popped from sip expression stack, result is placed on control stack"),
	scalar_neg_op("","","","","unary negation, arg is popped from sip expression stack, result is placed on expression stack"),
	scalar_sqrt_op("","","","","computes square root of value on top of sip expression stack, leaves result on top of expression stack"),	
	cast_to_scalar_op("","","","","removes top element from control stack, converts to double and leaves on top of expression stack"),


	//scalar collective operations
	
	collective_sum_op("array table slot of rhs scalar","array table slot of lhs scalar","","",
			"allreduce of rhs scalar into lhs scalar"),
	assert_same_op("array table slot of scalan","","","","checks that value of scalar is within epsilon on all workers, and resets all to master's value"),

	
	//expressions and operations on blocks.  In many cases, the target block selector is included in the instruction.
	tensor_op("lhs rank","lhs array table slot","","lhs selector","outer product, uses same routine as tensor contraction"),
	block_copy_op("lhs rank","lhs array table slot","","lhs selector",
			"copies block from top of block selector stack to block in instruction. If one array is larger than the other, the extra indices are simple"),
	block_permute_op("","","","permutation",
			"permute the block on the right side using the given permutation . RHS and LHS block selectors have been pushed onto block selector stack, first rhs then lhs"),
	fill_block_op("lhs rank","lhs array table slot","","lhs indices","gets value from expression stack and block instruction.  Adds the scalar value to all of the elements of the block"),
	scale_block_op("lhs rank","lhs array table slot","","lhs indices","gets value from expression stack and block from instruction.  Multiplies all of the elements of the block by the value"),
	accumulate_scalar_into_block_op("lhs rank","lhs arrayTable slot","","lhs selector indices","gets scalar value from expression stack and from instruction.  Adds the scalar to each value in the block"),
	block_add_op("lhs rank","lhs array slot","","lhs selector","adds two blocks together element-wise and puts the result in the lhs array"),
	block_subtract_op("lhs rank","lhs array slot","","lhs selector","subtracts two blocks elementwise and puts the result in the lhs array"),
	block_contract_op("lhs rank","lhs array slot","","lhs selector","contracts two blocks and puts the result in the lhs array"),
	block_contract_accumulate_op("lhs rank","lhs array slot","","lhs selector","contracts two blocks and accumulates the result in the lhs array"),
	block_contract_to_scalar_op("","","","","contracts two blocks where the result of contraction is a scalar.  Leaves the result on the sip expression stack"),
	block_load_scalar_op("","","","","all indices of block on top of selector stack are simple, \"block\" is a single scalar value, load it onto the sip expression stack"),
	slice_op("lhs rank","lhs array table slot","","lhs selector indices","copies subblock on lhs from rhs superblock"),
	insert_op("lhs rank","lhs array table slot","","lhs selector indices","inserts sublock on rhs into superblock on lhs"),
	
	//expressions and operations on strings
	string_load_literal_op("slot in string literal table","","","","loads slot in string table onto control stack."),

	//output commands
	print_string_op("add \n if  1","string table slot of string to print","","","master prints the string; slot in string table is on the sip control stack"),
	println_op("","","","","master prints '\n')"),
	print_index_op("add \n if  1","","","","master prints the current value of given index; the value is on the sip control stack"),
	print_scalar_op("add \n if  1","","","","master prints scalar; value is on the sip epression stack "),
	print_int_op("add \n if  1","","","","master prints int; value is on the sip control stack"),
	
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
	set_persistent_op("string table slot","array table slot","","","Marks array with the given label as persistent. At the end of the current SIAL program, it will be saved for restoration in a future program in same run"),
	restore_persistent_op("string_table_slot","array_table_slot","","","restores contents of persistent array with given label as indicated array"),

	//stack management
	idup_op("","","","","duplicates the value on top of the control (integer) stack"),
	iswap_op("","","","","swaps the top two values on the control (integer) stack"),
	sswap_op("","","","","swaps the top two values on the expression (scalar) stack"),
	
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