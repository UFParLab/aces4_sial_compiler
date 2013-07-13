package sial.code_gen;

import static sial.parser.context.ASTUtils.getDoubleVal;
import static sial.parser.context.ASTUtils.getIntVal;
import static sial.parser.context.ASTUtils.getRoot;
import static sial.parser.context.ASTUtils.isConstant;
import static sial.parser.context.ASTUtils.getContractedIndices;

import java.io.DataOutput;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;


import lpg.runtime.IAst;
import sial.compiler.CommandLine;
import sial.parser.SialParsersym;
import sial.parser.Ast.ASTNode;
import sial.parser.Ast.ASTNodeToken;
import sial.parser.Ast.AbstractVisitor;
import sial.parser.Ast.AllocIndexIdent;
import sial.parser.Ast.AllocIndexList;
import sial.parser.Ast.AllocIndexListopt;
import sial.parser.Ast.AllocIndexWildCard;
import sial.parser.Ast.AllocateStatement;
import sial.parser.Ast.ArgList;
import sial.parser.Ast.ArrayDec;
import sial.parser.Ast.ArrayKind;
import sial.parser.Ast.AssignOpEqual;
import sial.parser.Ast.AssignOpMinus;
import sial.parser.Ast.AssignOpPlus;
import sial.parser.Ast.AssignOpStar;
import sial.parser.Ast.AssignStatement;
import sial.parser.Ast.BinOpDiv;
import sial.parser.Ast.BinOpMinus;
import sial.parser.Ast.BinOpPlus;
import sial.parser.Ast.BinOpStar;
import sial.parser.Ast.BinOpTensor;
import sial.parser.Ast.BinaryExpression;
import sial.parser.Ast.CallStatement;
import sial.parser.Ast.CollectiveStatement;
import sial.parser.Ast.ConstantModifier;
import sial.parser.Ast.CreateStatement;
import sial.parser.Ast.CycleStatement;
import sial.parser.Ast.DataBlock;
import sial.parser.Ast.DataBlockPrimary;
import sial.parser.Ast.DeallocateStatement;
import sial.parser.Ast.DecList;
import sial.parser.Ast.DeleteStatement;
import sial.parser.Ast.DestroyStatement;
import sial.parser.Ast.DimensionList;
//import sial.parser.Ast.DimensionListopt;
import sial.parser.Ast.DoStatement;
import sial.parser.Ast.DoStatementSubIndex;
import sial.parser.Ast.DoubleLitPrimary;
import sial.parser.Ast.ExecuteStatement;
import sial.parser.Ast.ExitStatement;
import sial.parser.Ast.GetStatement;
import sial.parser.Ast.IArg;
import sial.parser.Ast.IAssignOp;
import sial.parser.Ast.IBinOp;
import sial.parser.Ast.IDec;
import sial.parser.Ast.IExpression;
import sial.parser.Ast.IPrimary;
import sial.parser.Ast.IRangeVal;
import sial.parser.Ast.IRelationalExpression;
import sial.parser.Ast.IScalarOrBlockVar;
import sial.parser.Ast.IUnaryExpression;
import sial.parser.Ast.Ident;
import sial.parser.Ast.IdentList;
import sial.parser.Ast.IdentPrimary;
import sial.parser.Ast.IdentRangeVal;
import sial.parser.Ast.IfElseStatement;
import sial.parser.Ast.IfStatement;
import sial.parser.Ast.ImportProg;
import sial.parser.Ast.ImportProgList;
import sial.parser.Ast.IndexDec;
import sial.parser.Ast.IndexKind;
import sial.parser.Ast.IntDec;
import sial.parser.Ast.IntLitPrimary;
import sial.parser.Ast.IntLitRangeVal;
import sial.parser.Ast.ModifierList;
import sial.parser.Ast.NegatedUnary;
import sial.parser.Ast.PardoStatement;
import sial.parser.Ast.PersistentModifier;
import sial.parser.Ast.PredefinedModifier;
import sial.parser.Ast.PrepareStatement;
import sial.parser.Ast.PrequestStatement;
import sial.parser.Ast.PrintIndexStatement;
import sial.parser.Ast.PrintScalarStatement;
import sial.parser.Ast.PrintStatement;
import sial.parser.Ast.PrintlnStatement;
import sial.parser.Ast.ProcDec;
import sial.parser.Ast.Program;
import sial.parser.Ast.PutStatement;
import sial.parser.Ast.Range;
import sial.parser.Ast.RelOp;
import sial.parser.Ast.RelationalExpression;
import sial.parser.Ast.RequestStatement;
import sial.parser.Ast.ReturnStatement;
import sial.parser.Ast.ScalarDec;
import sial.parser.Ast.Section;
import sial.parser.Ast.ServerBarrierStatement;
import sial.parser.Ast.Sial;
import sial.parser.Ast.SipBarrierStatement;
import sial.parser.Ast.SpecialDec;
import sial.parser.Ast.StatementList;
import sial.parser.Ast.StringLitPrimary;
import sial.parser.Ast.StringLiteral;
import sial.parser.Ast.SubIndexDec;
import sial.parser.Ast.WhereClause;
import sial.parser.Ast.WhereClauseList;
import sial.parser.context.ASTUtils;

public class CodeGenVisitor extends AbstractVisitor implements SialParsersym,
		SipConstants {

	SipTable sipTable; // main data structure of code generation
	Sial ast; // for debugging convenience
	CommandLine options;
	// local copies of sipTable fields for convenience
	Header header;
	ArrayTable arrayTable;
	IndexTable indexTable;
	ScalarTable scalarTable;
	OpTable opTable;
	SpecialInstructionTable specialInstructionTable;
	StringLiteralTable stringLiteralTable;
	
	int floatScratchPadIndex = 1000; // this is for compatibility with the
										// existing
										// compiler.
	private int intScratchPadIndex = 1000; // this is for compatibility with the
	// existing compiler.

	HashSet<Sial> visitedAstCache; // includes asts that have already been
									// translated
									// including this one (set in visit(Sial n))

	DataOutput out;
	
	
	/* The next data structures are to allow information to be passed from children 
	 * to a parent.  The need for this is a result of specifying the lpg preorder option rather
	 * than the default visitor.  
	 */

	LinkedList<Integer> operandStack; // stack for children to pass array table
										// indices parent
	LinkedList<int[]> indexArrayStack; // stack for children to pass index table
									// indices to parent
	LinkedList<Integer> backpatchInstructionStack; // stack for children to pass
							     // address
	                            // of instructions that need backpatching to parent
	int[] defaultOneInd; // array initialized to ones. Needed in many opTable
							// entries, so do it once.
	int[] defaultZeroInd; // array initialized to zeros. Needed in some opTable
							// entries, so do it once.
	int noArgExecuteArg = -1;



	public CodeGenVisitor(SipTable sipTable) {
		this.sipTable = sipTable;
		init(sipTable);
	}

	public CodeGenVisitor(CommandLine options) {
		sipTable = new SipTable();
		init(sipTable);
		this.options = options;
	}

	private void init(SipTable sipTable) {
		header = sipTable.header;
		indexTable = sipTable.getIndexTable();
		arrayTable = sipTable.getArrayTable();
		opTable = sipTable.getOpTable();
		scalarTable = sipTable.getScalarTable();
		specialInstructionTable = sipTable.getSpecialInstructionTable();
		stringLiteralTable = sipTable.getStringLiteralTable();
		visitedAstCache = new HashSet<Sial>();
		visitedAstCache = new HashSet<Sial>();
		operandStack = new LinkedList<Integer>();
		indexArrayStack = new LinkedList<int[]>();
		backpatchInstructionStack = new LinkedList<Integer>();
		defaultOneInd = new int[AcesHacks.max_array_index];
		for (int i = 0; i != defaultOneInd.length; i++) {
			defaultOneInd[i] = 1;
		}
		defaultZeroInd = new int[AcesHacks.max_array_index];
	}

	public SipTable getSipTable() {
		return sipTable;
	}

	int lineno(ASTNode n) {
		return n.getLeftIToken().getLine();
	}

	
	@Override public boolean visit(Sial n) {
		if (visitedAstCache.contains(n)) { // only visit an AST once
			return false; // children will not be visited by visitor
		}
		visitedAstCache.add(n);
		this.ast = n;
		// add initial instruction: jump to optable instruction 1 (which is 2 in
		// fortran)
		if (opTable.nOps == 0){ //this is program main program being compiled.
			                   //insert the jump instruction to the value
			                   //needed for an empty program.

		opTable.addOptableEntry(go_to_op, 1, defaultOneInd, lineno(n));

		}
		
		return true;
	}

	@Override public void endVisit(Sial n) { 
		// create header now that table sizes are known
		// this will be done multiple times, but the last one
		// is the one we want, so we won't bother checking.
		sipTable.header.setValues(indexTable.n_index_table_sip,
				arrayTable.nvars, opTable.nOps, scalarTable.nScalars);
	}

	@Override public boolean visit(Program n) {
		// visit manually to handle backpatching the statement list.
		// We don't need to visit the names at the start and end, 
        // since they have been checked during
		// type checking
		n.getDecList().accept(this);
		// We need to set the destination of the first instruction to
		// the address of the first instruction of the first "main" program 
		// encountered.
		// So, if there are statements in this statement list, and
		// the destination of the first instruction has not already
		// been initialized, do it now
		if (n.getStatementList().size() > 0 && !opTable.isFirstOpInitialized()) {
			opTable.backpatchBranch(0);
			opTable.setFirstOpInitialized(true);
		}
		//this takes care of the degenerate case where there is no "main" program
		//it is useful for testing
		if (!opTable.isFirstOpInitialized() && ! getRoot(n).isImported()){
			opTable.backpatchBranch(0);
			opTable.setFirstOpInitialized(true);
		}	
		n.getStatementList().accept(this);
		return false;
	}
	@Override public void endVisit(Program n) { /*nop*/ }

	@Override
	public boolean visit(ImportProg n) {
		Sial importAst = n.getAst();
		importAst.accept(this);
		return false;
	}
	@Override public void endVisit(ImportProg n) {/*nop*/}
	
	@Override public boolean visit(DecList n) { /* visit children */ return true; }
	@Override public void endVisit(DecList n) { /*nop*/}


	@Override public boolean visit(ScalarDec n) {
		int index = scalarTable.addScalarFortranIndex(n);
		arrayTable.addScalarEntry(n, index);
		return false;
	}
	@Override public void endVisit(ScalarDec n) { /*nop*/ }

    @Override public boolean visit(IntDec n) {
		if (isConstant(n)) {// this is a constant
			scalarTable.addConstant(n.getName());
			return false;
		}
		assert false; // non constant ints not yet implemented
		return false;
	}
	@Override  public void endVisit(IntDec n) { }

	@Override public boolean visit(ArrayDec n) {
		int arrayTypeNum = AcesHacks.getTypeConstant(n.getTypeName());
		DimensionList dimensions = n.getDimensionList();
		int arraynindex = dimensions.size();
		int[] indarray = new int[arraynindex];
		for (int i = 0; i != arraynindex; i++) {
			IDec indexIDec = dimensions.getDimensionAt(i).getDec();
			indarray[i] = indexTable.getFortranIndex(indexIDec);
		}
		arrayTable.addArrayEntry(n, arraynindex, arrayTypeNum, indarray);
		return false;
	}
	@Override public void endVisit(ArrayDec n) { /*nop*/}

	@Override public boolean visit(ArrayKind n) {/* nothing to do */ return false; }
	@Override public void endVisit(ArrayKind n) { /*nop*/ }

	@Override public boolean visit(DimensionList n) {
	 unimplementedVisitor("visit(DimensionList)"); return true; }
	@Override  public void endVisit(DimensionList n) {
	 unimplementedVisitor("endVisit(DimensionList)"); }

	@Override public boolean visit(IndexDec n) {
		int indexTypeNum = AcesHacks.getTypeConstant(n.getTypeName());
		int bseg, eseg;
		IRangeVal range1 = n.getRange().getRangeValStart();
		if (range1 instanceof IdentRangeVal) { // this must be an int
			IDec dec1 = ((IdentRangeVal) range1).getDec();
			bseg = scalarTable.getIntIndex(dec1);
		} else { // bseg is a literal
			bseg = getIntVal(range1);
		}
		IRangeVal range2 = n.getRange().getRangeValEnd();
		if (range2 instanceof IdentRangeVal) { // this must be an int
			IDec dec2 = ((IdentRangeVal) range2).getDec();
			eseg = scalarTable.getIntIndex(dec2);
		} else { // bseg is a literal
			eseg = getIntVal(range2);
		}
		indexTable.addEntry(n, bseg, eseg, indexTypeNum);
		return false;
	}
	@Override  public void endVisit(IndexDec n) { /*nop*/ }

	@Override public boolean visit(IndexKind n) { /* nothing to do*/ return false;}
	@Override public void endVisit(IndexKind n) { /*nop*/ }

	@Override public boolean visit(SubIndexDec n) {
		int indexTypeNum = AcesHacks.getTypeConstant("subindex");
		IndexDec parentDec = (IndexDec) n.getParentIdent().getDec();
		int parentIndex = indexTable.getIndex(parentDec);  
		indexTable.addEntry(n, parentIndex, 0, indexTypeNum);
		return false;
	}
	@Override public void endVisit(SubIndexDec n) {/*nop*/}

	@Override public boolean visit(Range n) { /* visit children */ return true; }
	 public void endVisit(Range n) { /*nop*/ }

	 @Override public boolean visit(IntLitRangeVal n){ /*handled by IndexDec*/ return false; }
	 @Override public void endVisit(IntLitRangeVal n) { /*nop*/ }

	 @Override public boolean visit(IdentRangeVal n) {/*handled by IndexDec*/ return false; }
	 @Override public void endVisit(IdentRangeVal n) { /*nop*/ }

	 @Override public boolean visit(ProcDec n) {
		if (n.getCallSites().size() == 0 && !options.isEXPAND()) return false;  //if the method is never called, don't generate code for it.
		int addr = opTable.nOps;
		n.setAddr(addr);
		return true;
	}

	 @Override public void endVisit(ProcDec n) {
		//add return op
		opTable.addOptableEntry(return_op, defaultOneInd, lineno(n.getendIdent()));
	}

	 @Override public boolean visit(SpecialDec n) {
		String name = n.getIdent().getName();
		//if this is compute_integrals, ignore it.  This is  hack to deal with 
		// the design of the sip that treats compute_integrals as a sial language statement
		if (name.equals("compute_integrals")) return false;
     	int index = specialInstructionTable.addEntry(name);
     	n.setAddr(index);  //store index into special instruction table in this dec
		return false;
	}
	 @Override public void endVisit(SpecialDec n) { /* nop */}

	 @Override public boolean visit(StatementList n) { /* visit children */ return true;}
	 @Override public void endVisit(StatementList n) {/* nop */}

	 @Override public boolean visit(WhereClause n) { /* visit children */return true;}
     @Override public void endVisit(WhereClause n) { /* nop */}

	 @Override public boolean visit(WhereClauseList n) { /* visit children */ return true;}
	 @Override public void endVisit(WhereClauseList n) { /* nop */}

	 @Override public boolean visit(CallStatement n) { /* visit children */ return true;}
	 @Override public void endVisit(CallStatement n) {
		int addr = operandStack.pop();
//		int addr = ((ProcDec)n.getIdent().getDec()).getAddr();
		opTable.addOptableEntry(call_op, addr, defaultOneInd, lineno(n));
	}

	 @Override public boolean visit(ReturnStatement n) {
		opTable.addOptableEntry(return_op, defaultOneInd, lineno(n));
		return false;
	}
	 @Override public void endVisit(ReturnStatement n) { /* nop */ }

	 @Override public boolean visit(ServerBarrierStatement n) {
	//AcesHack for now, this is treated as an "execute aceshack_server_barrier" instruction
	//The ServerBarrier object holds the SpecialDec
	int functionAddr =  ((SpecialDec) n.getDec()).getAddr();
	opTable.addOptableEntry(user_sub_op, noArgExecuteArg, defaultZeroInd, functionAddr, lineno(n));
	return false;
	}
    @Override public void endVisit(ServerBarrierStatement n) {/* nop */}

	public boolean visit(SipBarrierStatement n) {
		//AcesHack for now, this is treated as an "execute aceshack_sip_barrier" instruction
		//The ServerBarrier object holds the SpecialDec , which was set by the TypeCheckVisitor
		int functionAddr =  ((SpecialDec) n.getDec()).getAddr();
		Ident identOpt = n.getIdentOpt();
		if (identOpt==null){
		opTable.addOptableEntry(user_sub_op, noArgExecuteArg, defaultZeroInd, functionAddr, lineno(n));
		}
		else assert false: "arguments for barriers not supported";
		return false;
	}
	@Override public void endVisit(SipBarrierStatement n) { /* nop */}

	@Override public boolean visit(Section n){ /*visit children */
		return true;		
	}
	
	@Override public void endVisit(Section n){ 	//AcesHack for now, this is treated as an "execute aceshack_server_barrier" instruction
		//The Section object holds the SpecialDec, which was set by the TypeCheckVisitor
		int functionAddr =  ((SpecialDec) n.getDec()).getAddr();
		opTable.addOptableEntry(user_sub_op, noArgExecuteArg, defaultZeroInd, functionAddr, lineno(n));
		}
	
	@Override
	public boolean visit(DoStatement n) {
		// this method visits children manually
		// visit the loop variable ident to get its index
		n.getStartIndex().accept(this);
		int[] ind = Arrays.copyOf(defaultOneInd, defaultOneInd.length);
		// replace ind[0] with (fortran) index of loop variable
		ind[0] = operandStack.pop() + 1;
		opTable.addOptableEntry(do_op, ind, lineno(n));
		// visit remaining children
		if (n.getWhereClauseList() != null)
			n.getWhereClauseList().accept(this);
		if (n.getStatementList() != null)
			n.getStatementList().accept(this);
		opTable.addOptableEntry(enddo_op, ind, lineno(n.getEndIndex()));
		return false;
	}
	@Override public void endVisit(DoStatement n) { /*nop*/ }

	@Override public boolean visit(DoStatementSubIndex n) {
//		//TODO implement DoStatementSubIndex
//		// I think there is a bug in the original compiler
//		assert false: "subindex do statement not yet implemented";
//	    //check this--it may not be anything close to correct
//		int[] ind = Arrays.copyOf(defaultOneInd, defaultOneInd.length);
//		int opTableIndex = opTable.addOptableEntry(do_op, ind,
//				lineno(n));
//		backpatchInstructionStack.push(opTableIndex);
//		return true;
		// this method visits children manually
		// visit the loop variable ident to get its index
		n.getStartIndex().accept(this);
		int[] ind = Arrays.copyOf(defaultOneInd, defaultOneInd.length);
		// replace ind[0] with (not fortran, for some reason) index of loop variable and bitwise or with in_index_mask
		ind[0] = (operandStack.pop() + 1)| in_index_mask;; 
		opTable.addOptableEntry(do_op, ind, lineno(n));
		// visit remaining children  (parent index is redunant and does not need to be visited)
		if (n.getWhereClauseList() != null)
			n.getWhereClauseList().accept(this);
		if (n.getStatementList() != null)
			n.getStatementList().accept(this);
		opTable.addOptableEntry(enddo_op, ind, lineno(n.getEndIndex()));
		return false;		
		
		
		
		
	}
	@Override public void endVisit(DoStatementSubIndex n) { /*nop*/}

	@Override public boolean visit(PardoStatement n) {
		//visit children manually
		n.getStartIndices().accept(this);
		//the index array is on top of the indexArrayStack 
		int[] ind = indexArrayStack.pop();
		opTable.addOptableEntry(pardo_op, ind, lineno(n));
		//visit children
		if (n.getWhereClauseList() != null) n.getWhereClauseList().accept(this);
		if (n.getStatementList() != null) n.getStatementList().accept(this);
		opTable.addOptableEntry(endpardo_op, ind, lineno(n.getEndIndices()));
		return false;	
	}
	@Override public void endVisit(PardoStatement n) {/*nop*/ }

	@Override public boolean visit(ExitStatement n) {
		opTable.addOptableEntry(exit_op, defaultOneInd, lineno(n));
		return false;
	}
	@Override public void endVisit(ExitStatement n) { /*nop*/}

	@Override public boolean visit(CycleStatement n) { 
		assert false:  "cycle statement not yet implemented";
		return true;
	}
	@Override public void endVisit(CycleStatement n) {
		//TODO  test this
		int index = operandStack.pop();
		opTable.addOptableEntry(cycle_op, index, defaultOneInd, lineno(n) );
	}

	@Override public boolean visit(IfStatement n) {/* visit children */ return true;}
	@Override public void endVisit(IfStatement n) {
		// the relational expression has generated a jmpz instruction
		// and pushed its address on the bakcpatchInstruction stack
		int pc = backpatchInstructionStack.pop();
		// set the result index to the index of next instruction
		opTable.backpatchBranch(pc);
	}

	@Override public boolean visit(IfElseStatement n) {
		// visit manually
		
		//visit relational expression
		n.getRelationalExpression().accept(this);
		//the relational instruction visit method has generated a jmpz instruction
		//and pushed its address on the backpatchInstruction stack
		int jmpzPC = backpatchInstructionStack.pop();
		assert jmpzPC == opTable.nOps -1 : "backpatch stack had unexpected value in IfElseStatement";		
		//visit the statements of the if block
		n.getifStatements().accept(this);		
		//add a goto to jump over the else part 
		int gotoPC = opTable.addOptableEntry(go_to_op, 0,
				defaultOneInd, lineno(n));
		//patch the jmpz instruction
		opTable.backpatchBranch(jmpzPC);	
        //visit the statements of the else block
		n.getelseStatements().accept(this);
		//backpatch the goto statement
		opTable.backpatchBranch(gotoPC);
		return false;
	}
	@Override public void endVisit(IfElseStatement n) {/* nop */}


    @Override public boolean visit(AllocateStatement n) { /* visit children */ return true;}
	@Override public void endVisit(AllocateStatement n) {
		//get index of array to be allocated
		int index = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		opTable.addOptableEntry(allocate_op, index, ind, lineno(n));	
	}

	@Override public boolean visit(DeallocateStatement n) {/* visit children */ return true;}
	@Override public void endVisit(DeallocateStatement n) {
		int index = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		opTable.addOptableEntry(deallocate_op, index, ind, lineno(n));	
	}

	@Override public boolean visit(CreateStatement n) {/* visit children */ return true; }
	@Override public void endVisit(CreateStatement n) {
		int[] ind = null;
		if (n.getAllocIndexListopt() != null){
			ind = indexArrayStack.pop();
		}
		else ind = defaultZeroInd;
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(create_op, arrayIndex, ind, lineno(n));
	}

	@Override public boolean visit(DeleteStatement n) { /* visit children */ return true;}
	@Override public void endVisit(DeleteStatement n) {
		int[] ind = null;
		if (n.getAllocIndexListopt() != null) //{
			ind = indexArrayStack.pop();
//		} else
			ind = defaultOneInd;
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(delete_op, arrayIndex, ind, lineno(n));
	}



	@Override public boolean visit(PutStatement n) {/* visit children */ return true; }
	@Override public void endVisit(PutStatement n) {
		int rhsIndex = operandStack.pop();
		int lhsIndex = operandStack.pop();
		int[] rhsInd = indexArrayStack.pop();
		int[] lhsInd = indexArrayStack.pop();
		int opcode = 0;
		if (n.getAssignOp() instanceof AssignOpEqual){
			opcode = put_replace_op;
		}
		else if (n.getAssignOp() instanceof AssignOpPlus){
			opcode = put_op;
		}
		else  assert false: "illegal operator for put statement";
		opTable.addOptableEntry(reindex_op, lhsIndex, lhsInd, lineno(n));
		opTable.addOptableEntry(reindex_op, rhsIndex, rhsInd, lineno(n));
		opTable.addOptableEntry(opcode,rhsIndex,lhsIndex,defaultOneInd, lineno(n));
	}


	@Override public boolean visit(GetStatement n) {/*visit the children*/ return true;}
	@Override public void endVisit(GetStatement n) {
		int index = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		opTable.addOptableEntry(reindex_op, index, ind, lineno(n));
		opTable.addOptableEntry(get_op, index, ind, lineno(n));
	}

	@Override public boolean visit(PrepareStatement n) {/* visit children */ return true;}

	@Override
	public void endVisit(PrepareStatement n) {
		int rhsIndex = operandStack.pop();
		int lhsIndex = operandStack.pop();
		int[] rhsInd = indexArrayStack.pop();
		int[] lhsInd = indexArrayStack.pop();
		int opcode = 0;
		if (n.getAssignOp() instanceof AssignOpEqual) {
			opcode = prepare_op;
		} else if (n.getAssignOp() instanceof AssignOpPlus) {
			opcode = prepare_increment_op;
		} else
			assert false : "illegal operator for prepare statement";
		opTable.addOptableEntry(reindex_op, lhsIndex, lhsInd, lineno(n));
		opTable.addOptableEntry(reindex_op, rhsIndex, rhsInd, lineno(n));
		opTable.addOptableEntry(opcode, rhsIndex, lhsIndex, defaultOneInd,
				lineno(n));
	}

	@Override public boolean visit(RequestStatement n) {/* visit children */ return true;}
	@Override public void endVisit(RequestStatement n) {
		operandStack.pop();  //discard hint
		int index = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		opTable.addOptableEntry(reindex_op, index, ind, lineno(n));
		opTable.addOptableEntry(request_op, index, ind, lineno(n));
	}

	@Override public boolean visit(PrequestStatement n) { /* visit children */return true;}
	@Override public void endVisit(PrequestStatement n) {
		int rhsIndex = operandStack.pop();
		int lhsIndex = operandStack.pop();
		int[] rhsInd = indexArrayStack.pop();
		int[] lhsInd = indexArrayStack.pop();
		opTable.addOptableEntry(reindex_op, lhsIndex, lhsInd, lineno(n));
		opTable.addOptableEntry(reindex_op, rhsIndex, rhsInd, lineno(n));
		opTable.addOptableEntry(prequest_op, rhsIndex, lhsIndex, defaultOneInd,
				lineno(n));
	}

	@Override public boolean visit(CollectiveStatement n) { /* visit children */ return true;}
	@Override public void endVisit(CollectiveStatement n) {
		int rhsIndex = operandStack.pop();
		int lhsIndex = operandStack.pop();
		opTable.addOptableEntry(collective_sum_op, rhsIndex, lhsIndex, defaultOneInd,
				lineno(n));
	}

	@Override public boolean visit(ExecuteStatement n) {/*visit children */ return true;}
	@Override public void endVisit(ExecuteStatement n) {
		ArgList args = n.getArgList();
		int resultIndex = noArgExecuteArg;
		int op1Index = noArgExecuteArg;
		int op2Index = noArgExecuteArg;
		int[] ind = defaultZeroInd;
		if (args.size() == 1){ 
			IArg arg = args.getArgAt(0);
			if (arg instanceof IdentPrimary){
				resultIndex = operandStack.pop();
			}
			if (arg instanceof DataBlockPrimary){
				resultIndex = operandStack.pop();
				ind = indexArrayStack.pop();
				opTable.addOptableEntry(reindex_op, resultIndex, ind, lineno(n));
			}
		}
		else if (args.size() == 2){
			IArg arg1 = args.getArgAt(0);
			IArg arg2 = args.getArgAt(1);
			op1Index = operandStack.pop();
			resultIndex = operandStack.pop();
			if (arg2 instanceof DataBlockPrimary){ind = indexArrayStack.pop();}
			if (arg1 instanceof DataBlockPrimary){ind = indexArrayStack.pop();}
		}
		int functionAddr =  operandStack.pop();
		if (n.getIdent().getName().equals("compute_integrals")){
			//special case compute_integrals for now
			//TODO eventually fix this
			opTable.addOptableEntry(compute_integrals_op, op1Index, op2Index, resultIndex, ind, lineno(n));
			return;
		}
		opTable.addOptableEntry(user_sub_op, op1Index, op2Index, resultIndex, ind, functionAddr, lineno(n));
	}

	@Override public boolean visit(DestroyStatement n) { /* visit children */return true;}
	@Override public void endVisit(DestroyStatement n) {
		int[] ind = defaultOneInd;
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(destroy_op, arrayIndex, ind, lineno(n));
	}
	
	@Override public boolean visit(DataBlock n) {/* visit children */ return true;}
	@Override public void endVisit(DataBlock n) { /*nop*/}

	@Override public boolean visit(IdentList n) { /* visit children */ return true;}
	@Override public void endVisit(IdentList n) {
		//Each ident in this  list has left its address on the operand stack
		//Remove them and put into an in array, converting to Fortran indices.
		//Push the array on the indexStack
		int nindex = n.size();
		// Get the index table entries from the stack and fill the ind array,
		// converting to fortran indices
		int[] ind = new int[AcesHacks.max_array_index];
		for (int i = nindex - 1; i >= 0; i--) {
			ind[i] = operandStack.pop() + 1;
		}
		indexArrayStack.push(ind);
	}

	@Override
	public boolean visit(AllocIndexIdent n) {
		IDec dec = n.getDec();
		int identIndex;
		assert (dec instanceof IndexDec || dec instanceof SubIndexDec);
		identIndex = indexTable.getIndex( dec);
		operandStack.push(identIndex);
		return false;
	}
	@Override public void endVisit(AllocIndexIdent n) { /*nop*/ }

	@Override public boolean visit(AllocIndexWildCard n) {
		operandStack.push(wild-1); //will be incremented when
		                           //inserted in optable.
		return false;
	}
	@Override public void endVisit(AllocIndexWildCard n) { /* nop */}

	@Override public boolean visit(AllocIndexList n) { /* visit children */ return true;}
	@Override public void endVisit(AllocIndexList n) {		
		//construct the index array
		int nindex = n.size();
		// get the index table entries from the stack and fill the ind array
		// convert to fortran indices
		int[] ind = new int[AcesHacks.max_array_index];
		for (int i = nindex - 1; i >= 0; i--) {
			ind[i] = operandStack.pop() + 1;
		};
		indexArrayStack.push(ind);
	}

	@Override public boolean visit(RelationalExpression n) {/* visit children */return true;}
	@Override public void endVisit(RelationalExpression n) {
//		// TODO get rid of AcesHack
//		// determine if this requires int or floating point comparison.  Type checking has ensured compatibiltiy
//		// it requires int if either argument is an index
//		// The other argument could be an int literal or a symbolic constant
//		// int comparison is also used if both arguments are ints, either symbolic constant or literal
//		IUnaryExpression eleft = n.getUnaryExpressionLeft();
////		boolean eLeftIsIndex = eleft instanceof Ident
////				&& ((Ident) eleft).getDec() instanceof IndexDec;
////		boolean eLeftIsInt = eleft instanceof Ident
////				&& ((Ident) eleft).getDec() instanceof IntDec;
//		boolean eLeftIsIndex = eleft instanceof IdentPrimary
//			&& (((IdentPrimary) eleft).getDec() instanceof IndexDec || ((IdentPrimary) eleft).getDec() instanceof SubIndexDec);
//        boolean eLeftIsInt = (eleft instanceof IdentPrimary
//			&& ((IdentPrimary) eleft).getDec() instanceof IntDec) ;
//		IUnaryExpression eright = n.getUnaryExpressionRight();
////		boolean eRightIsIndex = eright instanceof Ident
////				&& ((Ident) eright).getDec() instanceof IndexDec;
////		boolean eRightIsInt = eright instanceof Ident
////				&& ((Ident) eright).getDec() instanceof IntDec;
//		boolean eRightIsIndex = eright instanceof IdentPrimary
//			&& (((IdentPrimary) eright).getDec() instanceof IndexDec || ((IdentPrimary) eright).getDec() instanceof SubIndexDec) ;
//		boolean eRightIsInt = (eright instanceof IdentPrimary
//			&& ((IdentPrimary) eright).getDec() instanceof IntDec) ;
//
////		if (eLeftIsIndex || eRightIsIndex || (eLeftIsInt || 
////				(eleft instanceof IntLitPrimary) && (eRightIsInt || (eright instanceof IntLitPrimary)))){//use int comparison
//		if (eLeftIsIndex || 
//				eRightIsIndex || 
//				(eLeftIsInt && eRightIsInt) ||
//				(eLeftIsInt && (eright instanceof IntLitPrimary)) ||
//				((eleft instanceof IntLitPrimary) && eRightIsInt))  {
//			int operand2 = operandStack.pop();
//			int operand1 = operandStack.pop();
//			//AcesHack  treat expressions in Where clauses differently
//			if (n.getParent() instanceof WhereClause){
//				int whereOpCode = AcesHacks.whereCodes.get(n.getRelOp().getop().getKind());
//				opTable.addOptableEntry(where_op, operand1, whereOpCode, operand2, defaultOneInd,
//						lineno(n));
//				return;
//			}
//			// args and op are ints
//			int loadOpcode;
//			int loc1 = intScratchPadIndex++;
//			int loc2 = intScratchPadIndex++;
//			if (eLeftIsIndex) {
//				loadOpcode = sp_ldindex_op;
//			} else if (eLeftIsInt) {
//				loadOpcode = sp_ldi_sym_op;
//			} else {
//				loadOpcode = sp_ldi_op;
//				operand1--; // this is a value of an int literal stored in the instruction, not an index,
//				// decrement to compensate for
//				// addOptableEntry increment to make Fortan index
//			}
//			opTable.addOptableEntry(loadOpcode, operand1, loc1, defaultOneInd,
//					lineno(n));
//			if (eRightIsIndex) {
//				loadOpcode = sp_ldindex_op;
//			} else if (eRightIsInt) {
//				loadOpcode = sp_ldi_sym_op;
//			} else {
//				loadOpcode = sp_ldi_op;
//				operand2--; // this is a value of an int  literal stored in the instruction, not an index,
//							// decrement to compensate for
//							// addOptableEntry increment to make Fortan index
//			}
//			opTable.addOptableEntry(loadOpcode, operand2, loc2, defaultOneInd,
//					lineno(n));
//
//			int opcode = 0;
//			int exprOpKind = n.getRelOp().getIToken().getKind();
//			opcode = getRelOpcodeInt(exprOpKind);
//			opTable.addOptableEntry(opcode, loc1, loc2, loc1, defaultOneInd,
//					lineno(n));
//			intScratchPadIndex--;
//
//			int opTableIndex = opTable.addOptableEntry(jz_op, loc1,
//					0, defaultOneInd, lineno(n));
//			intScratchPadIndex--;
//			backpatchInstructionStack.push(opTableIndex);//TODO move to parent??
//			return;
//		}
//		// do floating point comparison
//		int loadOpcode = fl_load_value_op;
//		int operand2 = operandStack.pop();
//		int operand1 = operandStack.pop();
//		int loc1 = floatScratchPadIndex++;
//		int loc2 = floatScratchPadIndex++;
//		opTable.addOptableEntry(loadOpcode, operand1, loc1, defaultOneInd,
//				lineno(n));
//		opTable.addOptableEntry(loadOpcode, operand2, loc2, defaultOneInd,
//				lineno(n));
//
//		int opcode = 0;
//		int exprOpKind = n.getRelOp().getIToken().getKind();
//		opcode = getRelOpcodeFP(exprOpKind);
//		opTable.addOptableEntry(opcode, loc1, loc2, loc1, defaultOneInd,
//				lineno(n));
//		floatScratchPadIndex--;
//		// add a jz and push its index onto the
//		// backPatchInstructionStack
//		int opTableIndex = opTable.addOptableEntry(jz_op, loc1, 0,
//				defaultOneInd, lineno(n));
//		// floatScratchPadIndex--; I think this should be here, but
//		// it is eliminated to match the current sial compiler
//		intScratchPadIndex--; // this is a benign bug, but it is here to match
//								// the current sial
//								// compiler
//		backpatchInstructionStack.push(opTableIndex);  //TODO move to parent ????
//	}
		

		IUnaryExpression eleft = n.getUnaryExpressionLeft();
        boolean eLeftHasDoubleType = (eleft instanceof IdentPrimary && ((IdentPrimary)eleft).getDec() instanceof ScalarDec) 
        		|| (eleft instanceof DoubleLitPrimary) 
        		|| ((eleft instanceof NegatedUnary) && (((NegatedUnary)eleft).getPrimary() instanceof DoubleLitPrimary));
        if (eLeftHasDoubleType){ //type checking ensure right hand side is double, too
		// do floating point comparison
		int loadOpcode = fl_load_value_op;
		int operand2 = operandStack.pop();
		int operand1 = operandStack.pop();
//		int loc1 = floatScratchPadIndex++;
//		int loc2 = floatScratchPadIndex++;
//TODO  this shouldn't make a difference, but just in case
		int loc1 = ++floatScratchPadIndex;
		int loc2 = ++floatScratchPadIndex;
		opTable.addOptableEntry(loadOpcode, operand1, loc1, defaultOneInd,
				lineno(n));
		opTable.addOptableEntry(loadOpcode, operand2, loc2, defaultOneInd,
				lineno(n));

		int opcode = 0;
		int exprOpKind = n.getRelOp().getIToken().getKind();
		opcode = getRelOpcodeFP(exprOpKind);
		opTable.addOptableEntry(opcode, loc1, loc2, loc1, defaultOneInd,
				lineno(n));
		floatScratchPadIndex--;
		// add a jz and push its index onto the
		// backPatchInstructionStack
		int opTableIndex = opTable.addOptableEntry(jz_op, loc1, 0,
				defaultOneInd, lineno(n));
		// floatScratchPadIndex--; I think this should be here, but
		// it is eliminated to match the current sial compiler
		intScratchPadIndex--; // this is a benign bug, but it is here to match
								// the current sial
								// compiler
		backpatchInstructionStack.push(opTableIndex);  //TODO move to parent ????
		return;
		}
        //Operands are ints
		boolean eLeftIsIndex = eleft instanceof IdentPrimary
			&& (((IdentPrimary) eleft).getDec() instanceof IndexDec || ((IdentPrimary) eleft).getDec() instanceof SubIndexDec);
        boolean eLeftIsInt = (eleft instanceof IdentPrimary
			&& ((IdentPrimary) eleft).getDec() instanceof IntDec) ;

        IUnaryExpression eright = n.getUnaryExpressionRight();
		boolean eRightIsIndex = eright instanceof IdentPrimary
			&& (((IdentPrimary) eright).getDec() instanceof IndexDec || ((IdentPrimary) eright).getDec() instanceof SubIndexDec) ;
		boolean eRightIsInt = (eright instanceof IdentPrimary
			&& ((IdentPrimary) eright).getDec() instanceof IntDec) ;

			int operand2 = operandStack.pop();
			int operand1 = operandStack.pop();
			//AcesHack  treat expressions in Where clauses differently
			if (n.getParent() instanceof WhereClause){
				int whereOpCode = AcesHacks.whereCodes.get(n.getRelOp().getop().getKind());
				opTable.addOptableEntry(where_op, operand1, whereOpCode, operand2, defaultOneInd,
						lineno(n));
				return;
			}

			int loadOpcode;
			int loc1 = intScratchPadIndex++;
			int loc2 = intScratchPadIndex++;
			if (eLeftIsIndex) {
				loadOpcode = sp_ldindex_op;
			} else if (eLeftIsInt) {
				loadOpcode = sp_ldi_sym_op;
			} else { //is literal
				loadOpcode = sp_ldi_op;
				operand1--; // this is a value of an int literal stored in the instruction, not an index,
				// decrement to compensate for
				// addOptableEntry increment to make Fortan index
			}
			opTable.addOptableEntry(loadOpcode, operand1, loc1, defaultOneInd,
					lineno(n));
			if (eRightIsIndex) {
				loadOpcode = sp_ldindex_op;
			} else if (eRightIsInt) {
				loadOpcode = sp_ldi_sym_op;
			} else { //is literal
				loadOpcode = sp_ldi_op;
				operand2--; // this is a value of an int  literal stored in the instruction, not an index,
							// decrement to compensate for
							// addOptableEntry increment to make Fortan index
			}
			opTable.addOptableEntry(loadOpcode, operand2, loc2, defaultOneInd,
					lineno(n));

			int opcode = 0;
			int exprOpKind = n.getRelOp().getIToken().getKind();
			opcode = getRelOpcodeInt(exprOpKind);
			opTable.addOptableEntry(opcode, loc1, loc2, loc1, defaultOneInd,
					lineno(n));
			intScratchPadIndex--;

			int opTableIndex = opTable.addOptableEntry(jz_op, loc1,
					0, defaultOneInd, lineno(n));
			intScratchPadIndex--;
			backpatchInstructionStack.push(opTableIndex);//TODO move to parent??
			return;
		

	}

	int getRelOpcodeFP(int exprOpKind) {
		switch (exprOpKind) {
		case TK_GREATER: return fl_gt_op;
		case TK_LESS: return fl_lt_op;
		case TK_LEQ: return fl_le_op;
		case TK_GEQ: return fl_ge_op;
		case TK_EQ: return fl_eq_op;
		case TK_NEQ: return fl_ne_op;
		default:
			assert false : exprOpKind + " illegal operator";
		}
		return -1;
	}

	int getRelOpcodeInt(int exprOpKind) {
		switch (exprOpKind) {
		case TK_GREATER: return sp_gt_op;
		case TK_LESS: return sp_lt_op;
		case TK_LEQ: return sp_le_op;
		case TK_GEQ: return sp_ge_op;
		case TK_EQ: return sp_equal_op;
		case TK_NEQ: return sp_nequal_op;
		default:
			assert false : exprOpKind + " illegal operator";
		}
		return -1;
	}

	@Override public boolean visit(BinaryExpression n) {
		unimplementedVisitor("visit(BinaryExpression)");
		return true;
	}

	@Override public void endVisit(BinaryExpression n) {
		unimplementedVisitor("endVisit(BinaryExpression)");
	}

	@Override public boolean visit(NegatedUnary n) {
		// Currently, the sia doesn't support a real unary operator. Negate is
		// part of the token.
		// here we will just handle as a DoubleLitPrimary and negate the value
		IPrimary primary = n.getPrimary();
		if (primary instanceof DoubleLitPrimary) {
			double val = - getDoubleVal(((DoubleLitPrimary) primary)
					.getDOUBLELIT());
			int arrayTableIndex;
			int nScalars = scalarTable.nScalars; // current number of scalars
			int scalarTableIndex = scalarTable
					.addDoubleLiteralFortranIndex(val);
			if (scalarTable.nScalars > nScalars) { // this is a new value, add
													// to array table
				arrayTableIndex = arrayTable.addScalarEntry(null,
						scalarTableIndex);
			} else {
				arrayTableIndex = arrayTable
						.getIndexOfScalarEntry(scalarTableIndex);
			}
			// push onto operand stack
			operandStack.push(arrayTableIndex);
			return false; // don't visit child DoubleLitPrimary again
		} else if (primary instanceof IntLitPrimary) {
			// for now, coerce to double
			double val = - getDoubleVal(((IntLitPrimary) primary)
					.getINTLIT());
			int arrayTableIndex;
			int nScalars = scalarTable.nScalars; // current number of scalars
			int scalarTableIndex = scalarTable
					.addDoubleLiteralFortranIndex(val);
			if (scalarTable.nScalars > nScalars) { // this is a new value, add
													// to array table
				arrayTableIndex = arrayTable.addScalarEntry(null,
						scalarTableIndex);
			} else {
				arrayTableIndex = arrayTable
						.getIndexOfScalarEntry(scalarTableIndex);
			}
			// push onto operand stack
			operandStack.push(arrayTableIndex);
			return false; // don't visit child IntLitPrimary again
		} else
			assert false;
		return false;
	}

	@Override public void endVisit(NegatedUnary n) { /*nop*/}

	@Override public boolean visit(IntLitPrimary n) {
		// TODO remove AcesHack
		// An int literal appearing in a relational expressions being compared
		// with
		// an index value or symbolic constant does not have an entry in the array table.
		// Its value is stored directly in the op1_array entry of the
		// the instruction, i.e in the OpTable entry's op1_array slot.
		// Here, we push the value onto the operand stack

		// check if ancestor is RelationalExpression

		IAst ancestor = n.getParent();
		while (ancestor != null && !(ancestor instanceof IRelationalExpression)) {
			ancestor = ancestor.getParent();
		}
		if (ancestor != null) {// this is part of a relational expression
			int val = getIntVal(n.getINTLIT());
			operandStack.push(val);
			return false;
		}
		// is not part of relational expression, coerce to float
		double val = getDoubleVal(n.getINTLIT());
		int arrayTableIndex;
		int nScalars = scalarTable.nScalars; // current number of scalars
		int scalarTableIndex = scalarTable.addDoubleLiteralFortranIndex(val);
		if (scalarTable.nScalars > nScalars) { // this is a new value, add to
												// array table
			arrayTableIndex = arrayTable.addScalarEntry(null, scalarTableIndex);
		} else {
			arrayTableIndex = arrayTable
					.getIndexOfScalarEntry(scalarTableIndex);
		}
		// push onto operand stack
		operandStack.push(arrayTableIndex);
		return false;
	}
	@Override public void endVisit(IntLitPrimary n) {/* nop  */}

	@Override public boolean visit(DoubleLitPrimary n) {
		double val = getDoubleVal(n.getDOUBLELIT());
		int arrayTableIndex;
		int nScalars = scalarTable.nScalars; // current number of scalars
		int scalarTableIndex = scalarTable.addDoubleLiteralFortranIndex(val);
		if (scalarTable.nScalars > nScalars) { // this is a new value, add to
												// array table
			arrayTableIndex = arrayTable.addScalarEntry(null, scalarTableIndex);
		} else {
			arrayTableIndex = arrayTable
					.getIndexOfScalarEntry(scalarTableIndex);
		}
		// push onto operand stack
		operandStack.push(arrayTableIndex);
		return false;
	}
    public void endVisit(DoubleLitPrimary n) { /*nop*/ }

	@Override public boolean visit(Ident n) {
		IAst ancestor = n.getParent();
		if (ancestor instanceof Program)
			return false; // If this Ident is the program name, return
		while (ancestor != null && !(ancestor instanceof IDec)) {
			ancestor = ancestor.getParent();
		}
		if (ancestor != null & !(ancestor instanceof ProcDec))
			return false;// If this Ident is part of a variable declaration, return

		//Get the "address" of the Ident in the appropriate table and
		//push onto the op stack
		IDec dec = n.getDec();
		int identIndex;
		if (dec instanceof IndexDec || dec instanceof SubIndexDec) {// if this Ident is an index, look up in
										// indexTable
			identIndex = indexTable.getIndex((IDec) dec);
			// indexStack.push(identIndex); //TODO TRIAL try without separate
			// indexStack
			operandStack.push(identIndex);
		} else if (dec instanceof ScalarDec) {// if this is a scalar, look up in
												// array table
			identIndex = arrayTable.getIndex(dec);
			operandStack.push(identIndex);
		} else if (dec instanceof IntDec) {
			identIndex = scalarTable.getIntIndex(dec);
			// In the index table, indices for symbolic constants have
			// negative values to indicate symbolic constant.
			// In expressions, indices have positive values
			// with indices starting at 0. The SIP get_symbolic_constant
			// routine adds one to the index.
			// Since the index will be incremented when it is added to
			// the optable, decrement it here.
			if (identIndex < 0) identIndex = - identIndex -1;
			operandStack.push(identIndex);
		} else if (dec instanceof ArrayDec) { // if it is an array, first
												// generate setindex instruction
			identIndex = arrayTable.getIndex(dec);
			operandStack.push(identIndex); // now push ident
		} else if (dec instanceof ProcDec){ //this is a proc
			identIndex = ((ProcDec)dec).getAddr();
			operandStack.push(identIndex);
		}
		else if (dec instanceof SpecialDec){//this is a special instruction
			identIndex = ((SpecialDec)dec).getAddr();
			operandStack.push(identIndex);
		}
		else assert false;
		return false;
	}
	@Override public void endVisit(Ident n){ /*nop*/ }

    @Override public boolean visit(AssignStatement n) { /* visit children */return true; }
	@Override public void endVisit(AssignStatement n) {
		// There some ugliness in this routine solely to match
		// the current compiler. It really doesn't matter what order
		// the redindex ops are generated in
		// get index and indexArrays for rhs
		IExpression rhs = n.getExpression();
		int expr1Index = 0;
		int expr2Index = 0;
		int[] expr1IndexArray = null;
		int[] expr2IndexArray = null;
		if (rhs instanceof BinaryExpression) {
			// get indices and Ind arrays, if any for Binary expression
			expr2Index = operandStack.pop();
			if (((BinaryExpression) rhs).getExpr2() instanceof DataBlockPrimary) {
				expr2IndexArray = indexArrayStack.pop();
			}
			expr1Index = operandStack.pop();
			if (((BinaryExpression) rhs).getExpr1() instanceof DataBlockPrimary) {
				expr1IndexArray = indexArrayStack.pop();
			}
		} else {// rhs is a unary expression, put in expr2
			expr2Index = operandStack.pop();
			if (rhs instanceof DataBlockPrimary) {
				expr2IndexArray = indexArrayStack.pop();
			}
		}
		// get index and indexArray for lhs
		IScalarOrBlockVar lhs = n.getScalarOrBlockVar();
		int lhsIndex = 0;
		int[] lhsIndexArray = null;
		lhsIndex = operandStack.pop();
		if (lhs instanceof DataBlock) {
			lhsIndexArray = indexArrayStack.pop();
		}
		IAssignOp assignOp = n.getAssignOp();
		IBinOp binOp = (rhs instanceof BinaryExpression) ? ((BinaryExpression) rhs)
				.getBinOp() : null;
		if (assignOp instanceof AssignOpEqual
				& !(rhs instanceof BinaryExpression)) {
			// assignment is of the form x = e
			if (lhsIndexArray != null) {
				opTable.addOptableEntry(reindex_op, lhsIndex, lhsIndexArray,
						lineno(n));
			}
			if (expr2IndexArray != null) {
				opTable.addOptableEntry(reindex_op, expr2Index,
						expr2IndexArray, lineno(n));
			}
			opTable.addOptableEntry(assignment_op, expr2Index, lhsIndex,
					defaultOneInd, lineno(n));
			return;
		}
		if (!(rhs instanceof BinaryExpression)) { 
			// op is +=, -= or *=
			//  x += e gets converted to x = x + e
			expr1Index = lhsIndex;
			expr1IndexArray = lhsIndexArray;
		}
		//generate reindex instructions for any datablocks
		//starting from the left
		//this will do the same instruction twice for +=, etc.  
		//it is left that way for now to match the current compiler
		if (lhsIndexArray != null) {
			opTable.addOptableEntry(reindex_op, lhsIndex, lhsIndexArray,
					lineno(n));
		}
		if (expr1IndexArray != null) {
			opTable.addOptableEntry(reindex_op, expr1Index, expr1IndexArray,
					lineno(n));
		}
		if (expr2IndexArray != null) {
			opTable.addOptableEntry(reindex_op, expr2Index, expr2IndexArray,
					lineno(n));
		}
		int opcode = getAssignOpcode(assignOp, binOp);
		//AcesHack  the next statement is to match the current compiler
		//it is probably not necessary, but I haven't checked the sip code.
		int[] ind;
		if (opcode == contraction_op) ind =  getContractedIndices(expr1IndexArray,expr2IndexArray);
		else if (opcode == tensor_op || opcode == assignment_op) ind = defaultZeroInd;
		else ind = defaultOneInd;		            	             
		opTable.addOptableEntry(opcode, expr1Index, expr2Index, lhsIndex,
				ind, lineno(n));
	}
	

	private int getAssignOpcode(IAssignOp assignOp,IBinOp binOp) {
		if (assignOp instanceof AssignOpEqual){
			if (binOp == null) return assignment_op;
			if (binOp instanceof BinOpPlus) return sum_op;
		    if (binOp instanceof BinOpMinus) return subtract_op;
		    if (binOp instanceof BinOpDiv) return divide_op;
		    if (binOp instanceof BinOpStar) return contraction_op;
		    if (binOp instanceof BinOpTensor) return tensor_op;
		}
		if (assignOp instanceof AssignOpPlus) return sum_op;
		if (assignOp instanceof AssignOpMinus) return subtract_op;
		if (assignOp instanceof AssignOpStar) return contraction_op;
		assert false: "Bug in type checking of assignment statements";
		return -1;	    
	}
	
//
//	@Override public boolean visit(ASTNodeToken n) {
//		unimplementedVisitor("visit(ASTNodeToken)");
//		return true;
//	}
//
//	@Override public void endVisit(ASTNodeToken n) {
//		unimplementedVisitor("endVisit(ASTNodeToken)");
//	}

	@Override public void unimplementedVisitor(String s) { }

	@Override public boolean visit(ImportProgList n) { /* visit children */ return true; }
	@Override  public void endVisit(ImportProgList n) { /*nop*/ }

    //modifiers are currently handled via the ASTUtils methods
	@Override public boolean visit(ModifierList n) { /* nothing to do */ return false; }
	@Override public void endVisit(ModifierList n) { /*nop*/ }

	@Override public boolean visit(ConstantModifier n) { /* nothing to do */ return false; }
	@Override public void endVisit(ConstantModifier n) { /*nop*/ }

	@Override  public boolean visit(PredefinedModifier n) { /* nothing to do */ return false; }
	@Override public void endVisit(PredefinedModifier n) { /*nop*/ }

	@Override public boolean visit(PersistentModifier n) { /* nothing to do */ return false; }
	@Override public void endVisit(PersistentModifier n) { /*nop*/ }

    //public boolean visit(DimensionListopt n) { /* visit children */ return true; }
    //public void endVisit(DimensionListopt n) { /* nop */ }

	@Override public boolean visit(ArgList n) { /* visit children */ return true; }
	@Override public void endVisit(ArgList n) { /*nop*/ }

	@Override public boolean visit(AllocIndexListopt n) { /* visit children */ return true; }
	@Override public void endVisit(AllocIndexListopt n) { /*nop*/ }

	@Override public boolean visit(RelOp n) { unimplementedVisitor("visit(RelOp)"); return true; }
	@Override public void endVisit(RelOp n) { unimplementedVisitor("endVisit(RelOp)"); }

	@Override  public boolean visit(IdentPrimary n) { 		
    	//Get the "address" of the Ident in the appropriate table and
		//push onto the op stack
		IDec dec = n.getDec();
		int identIndex;
		if (dec instanceof IndexDec || dec instanceof SubIndexDec) {
			// if this Ident is an index or subindex, look up in the indexTable
			identIndex = indexTable.getIndex(dec);
			// indexStack.push(identIndex); //TODO TRIAL try without separate
			// indexStack
			operandStack.push(identIndex);
		} else if (dec instanceof ScalarDec) {// if this is a scalar, look up in
												// array table
			identIndex = arrayTable.getIndex(dec);
			operandStack.push(identIndex);
		} else if (dec instanceof IntDec) {
			identIndex = scalarTable.getIntIndex(dec);
			// In the index table, indices for symbolic constants have
			// negative values to indicate symbolic constant.
			// In expressions, indices have positive values
			// with indices starting at 0. The SIP get_symbolic_constant
			// routine adds one to the index.
			// Since the index will be incremented when it is added to
			// the optable, decrement it here.
			if (identIndex < 0) identIndex = -identIndex -1;
			operandStack.push(identIndex);
		} else if (dec instanceof ArrayDec) { // if it is an array, first
												// generate setindex instruction
			identIndex = arrayTable.getIndex(dec);
			operandStack.push(identIndex); // now push ident
		} else if (dec instanceof ProcDec){ //this is a procedure
			identIndex = ((ProcDec)dec).getAddr();
			operandStack.push(identIndex);
		}
		else assert false;
		return false;
	} 
	@Override public void endVisit(IdentPrimary n) { /*nop*/ }

	@Override public boolean visit(DataBlockPrimary n) { /* visit children */ return true; }
	@Override public void endVisit(DataBlockPrimary n) { /* nop */ }

	@Override public boolean visit(StringLitPrimary n) { assert false: "string literals not yet supported"; return false;}
	@Override public void endVisit(StringLitPrimary n) { /* nop */ }

	@Override public boolean visit(StringLiteral n) { assert false: "string literals not yet supported"; return false; }
	@Override public void endVisit(StringLiteral n) { /* nop */ }
    
	@Override public boolean visit(PrintStatement n){
		String s = ASTUtils.getStringVal(n.getSTRINGLIT());
		int stringIndex = stringLiteralTable.getAndAdd(s);
		opTable.addOptableEntry(print_op, stringIndex, defaultZeroInd, lineno(n));
		return false;  //should not visit child
	}
	@Override public void endVisit(PrintStatement n){ /* nop */
	}
	@Override public boolean visit(PrintlnStatement n){
		String s = ASTUtils.getStringVal(n.getSTRINGLIT());
		int stringIndex = stringLiteralTable.getAndAdd(s);
		opTable.addOptableEntry(println_op, stringIndex, defaultZeroInd, lineno(n));
		return false;  //should not visit child
	}
	@Override public void endVisit(PrintlnStatement n){ /* nop */
	}
	
	@Override public boolean visit(PrintIndexStatement n){ /* visit child */
		return true;
	}
	@Override public void endVisit(PrintIndexStatement n){ 
		int[] ind = Arrays.copyOf(defaultOneInd, defaultOneInd.length);
		// replace ind[0] with index in index array of argument
		//NOTE:  THIS IS NOT A FORTRAN INDEX
		ind[0] = operandStack.pop();
		opTable.addOptableEntry(print_index_op, ind, lineno(n));
	}
	
	@Override public boolean visit(PrintScalarStatement n){ /* visit child */
		return true;
	}
	@Override public void endVisit(PrintScalarStatement n){ 
		int[] ind = Arrays.copyOf(defaultOneInd, defaultOneInd.length);
		// replace ind[0] with index in scalar table of argument 
		//NOTE:  THIS IS NOT A FORTRAN INDEX
		ind[0] = operandStack.pop();  
		opTable.addOptableEntry(print_scalar_op, ind, lineno(n));
	}
}
