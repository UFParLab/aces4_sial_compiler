package sial.code_gen;

import static sial.parser.context.ASTUtils.getDoubleVal;
import static sial.parser.context.ASTUtils.getIntVal;
import static sial.parser.context.ASTUtils.getRoot;

import java.io.DataOutput;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;

import lpg.runtime.IAst;

import sial.compiler.CommandLine;
import sial.parser.SialParsersym;
import sial.parser.Ast.ASTNode;
import sial.parser.Ast.ASTNodeToken;
import sial.parser.Ast.AbstractVisitor;
import sial.parser.Ast.AddExpr;
import sial.parser.Ast.AllocIndexIdent;
import sial.parser.Ast.AllocIndexList;
import sial.parser.Ast.AllocIndexListopt;
import sial.parser.Ast.AllocIndexWildCard;
import sial.parser.Ast.AllocateStatement;
import sial.parser.Ast.ArgList;
import sial.parser.Ast.ArrayDec;
import sial.parser.Ast.AssertSame;
import sial.parser.Ast.AssignToBlock;
import sial.parser.Ast.AssignToIdent;
import sial.parser.Ast.CallStatement;
import sial.parser.Ast.CollectiveStatement;
import sial.parser.Ast.CreateStatement;
import sial.parser.Ast.CycleStatement;
import sial.parser.Ast.DataBlock;
import sial.parser.Ast.DataBlockArg;
import sial.parser.Ast.DataBlockExpr;
import sial.parser.Ast.DeallocateStatement;
import sial.parser.Ast.DecList;
import sial.parser.Ast.DeleteStatement;
import sial.parser.Ast.DestroyStatement;
import sial.parser.Ast.DimensionList;
import sial.parser.Ast.DivExpr;
import sial.parser.Ast.DoStatement;
import sial.parser.Ast.DoStatementSubIndex;
import sial.parser.Ast.DoubleLitArg;
import sial.parser.Ast.DoubleLitExpr;
import sial.parser.Ast.ExecuteStatement;
import sial.parser.Ast.ExitStatement;
import sial.parser.Ast.GPUAllocateBlock;
import sial.parser.Ast.GPUFreeBlock;
import sial.parser.Ast.GPUGetBlock;
import sial.parser.Ast.GPUPutBlock;
import sial.parser.Ast.GPUSection;
import sial.parser.Ast.GetStatement;
import sial.parser.Ast.IDec;
import sial.parser.Ast.IExpression;
import sial.parser.Ast.IRangeVal;
import sial.parser.Ast.IUnaryExpression;
import sial.parser.Ast.Ident;
import sial.parser.Ast.IdentArg;
import sial.parser.Ast.IdentExpr;
import sial.parser.Ast.IdentList;
import sial.parser.Ast.IdentRangeVal;
import sial.parser.Ast.IfElseStatement;
import sial.parser.Ast.IfStatement;
import sial.parser.Ast.ImportProg;
import sial.parser.Ast.ImportProgList;
import sial.parser.Ast.IndexDec;
import sial.parser.Ast.IndexKind;
import sial.parser.Ast.IntCastExpr;
import sial.parser.Ast.IntDec;
import sial.parser.Ast.IntLitExpr;
import sial.parser.Ast.IntLitRangeVal;
import sial.parser.Ast.ModifierList;
import sial.parser.Ast.NegRangeVal;
import sial.parser.Ast.NegatedUnaryExpr;
import sial.parser.Ast.PardoStatement;
import sial.parser.Ast.ParenExpr;
import sial.parser.Ast.PrepareStatement;
import sial.parser.Ast.PrequestStatement;
import sial.parser.Ast.PrintIndexStatement;
import sial.parser.Ast.PrintIntStatement;
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
import sial.parser.Ast.RestorePersistent;
import sial.parser.Ast.ReturnStatement;
import sial.parser.Ast.ScalarCastExpr;
import sial.parser.Ast.ScalarDec;
import sial.parser.Ast.Section;
import sial.parser.Ast.ServerBarrierStatement;
import sial.parser.Ast.SetPersistent;
import sial.parser.Ast.Sial;
import sial.parser.Ast.SipBarrierStatement;
import sial.parser.Ast.SpecialDec;
import sial.parser.Ast.StarExpr;
import sial.parser.Ast.StatementList;
import sial.parser.Ast.StringLitExpr;
import sial.parser.Ast.StringLiteral;
import sial.parser.Ast.SubIndexDec;
import sial.parser.Ast.SubtractExpr;
import sial.parser.Ast.TensorExpr;
import sial.parser.Ast.WhereClause;
import sial.parser.Ast.WhereClauseList;
import sial.parser.context.ASTUtils;
import sial.parser.context.AmbiguousNameException;
import sial.parser.context.ExpressionType;
//import sial.parser.Ast.PersistentModifier;
//import sial.parser.Ast.DimensionListopt;
import sial.parser.context.ExpressionType.EType;
import static sial.parser.context.ExpressionType.EType.*;

/** Visits AST and generates code for the SIAL program */
public class CodeGenVisitor extends AbstractVisitor implements SialParsersym,
		SipConstants {

	SipTable sipTable; // main data structure of code generation
	Sial ast; // for debugging convenience
	CommandLine options;
	// local copies of sipTable fields for convenience
	// Header header;
	ArrayTable arrayTable;
	IndexTable indexTable;
	ScalarTable scalarTable;
	IntTable intTable;
	OpTable opTable;
	SpecialInstructionTable specialInstructionTable;
	StringLiteralTable stringLiteralTable;

	HashSet<Sial> visitedAstCache; // includes asts that have already been
									// translated
									// including this one (set in visit(Sial n))

	DataOutput out;

	/*
	 * The next data structures are to allow information to be passed from
	 * children to a parent. The need for this is a result of specifying the lpg
	 * preorder option rather than the default visitor.
	 */

	LinkedList<Integer> operandStack; // stack for children to pass array table or int table
										// indices parent
	LinkedList<int[]> indexArrayStack; // stack for children to pass index table
										// indices to parent
	LinkedList<Integer> backpatchInstructionStack; // stack for children to pass
	// address of instructions that need backpatching to parent
	

	//the difference between 0's and 1's was inherited from aces3.  No rhyme or reason has been discovered.
	static int[] defaultOneInd; // array initialized to ones. Needed in many opTable
							// entries, so do it once.
	static int[] defaultZeroInd; // array initialized to zeros. Needed in some opTable
							// entries, so do it once.
	static int[] defaultUndefInd; // array initialized to -1
	
	int noArgExecuteArg = -1;
	
	static {
		defaultOneInd = new int[TypeConstantMap.max_rank];
		defaultUndefInd = new int[TypeConstantMap.max_rank];
		for (int i = 0; i != defaultOneInd.length; i++) {
			defaultOneInd[i] = 1;
			defaultUndefInd[i] = -1;
		}
		defaultZeroInd = new int[TypeConstantMap.max_rank];
	}

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
		indexTable = sipTable.getIndexTable();
		arrayTable = sipTable.getArrayTable();
		opTable = sipTable.getOpTable();
		scalarTable = sipTable.getScalarTable();
		specialInstructionTable = sipTable.getSpecialInstructionTable();
		stringLiteralTable = sipTable.getStringLiteralTable();
		intTable = sipTable.getIntTable();
		visitedAstCache = new HashSet<Sial>();
		visitedAstCache = new HashSet<Sial>();
		operandStack = new LinkedList<Integer>();
		indexArrayStack = new LinkedList<int[]>();
		backpatchInstructionStack = new LinkedList<Integer>();


	}

	public SipTable getSipTable() {
		return sipTable;
	}

	int lineno(ASTNode n) {
		return n.getLeftIToken().getLine();
	}

	@Override
	public boolean visit(Sial n) {
		if (visitedAstCache.contains(n)) { // only visit an AST once
			return false; // children will not be visited by visitor
		}
		visitedAstCache.add(n);
		this.ast = n;
		// add initial instruction: jump to optable instruction 1
		if (opTable.nOps == 0) { // this program is the main program being compiled.
									// insert the jump instruction to the value
									// needed for an empty program.

			opTable.addOptableEntry(go_to_op, 1, defaultOneInd, lineno(n));

		}

		return true;
	}

	@Override
	public void endVisit(Sial n) {
	}

	@Override
	public boolean visit(Program n) {
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
		// this takes care of the degenerate case where there is no "main"
		// program
		// it is useful for testing
		if (!opTable.isFirstOpInitialized() && !getRoot(n).isImported()) {
			opTable.backpatchBranch(0);
			opTable.setFirstOpInitialized(true);
		}
		n.getStatementList().accept(this);
		return false;
	}

	@Override
	public void endVisit(Program n) { /* nop */
	}

	@Override
	public boolean visit(ImportProg n) {
		Sial importAst = n.getAst();
		importAst.accept(this);
		return false;
	}

	@Override
	public void endVisit(ImportProg n) {/* nop */
	}

	@Override
	public boolean visit(DecList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DecList n) { /* nop */
	}

	@Override
	public boolean visit(ScalarDec n) {
		int attribute = scalar_value_t;
		attribute = attribute | ASTUtils.getModifierAttributes(n); //may be predefined
		ASTNodeToken initialValueToken = n.getScalarInitializationOpt().getDOUBLELIT();
		int scalarTableSlot;
		double value = 0.0;
		if (initialValueToken != null){
            value = ASTUtils.getDoubleVal(initialValueToken);			
		}
		scalarTableSlot = scalarTable.addScalar(n,value);
		arrayTable.addScalarEntry(n, attribute, scalarTableSlot);
		return false;
	}

	@Override
	public void endVisit(ScalarDec n) { /* nop */
	}

	@Override
	public boolean visit(IntDec n) {
		int attribute = int_value_t;
		attribute = attribute | ASTUtils.getModifierAttributes(n); //may be predefined 
		ASTNodeToken initialValueToken = n.getIntInitializationOpt().getINTLIT();
		int value = 0;
		if (initialValueToken != null){
			value = ASTUtils.getIntLitVal(initialValueToken);
		}
		intTable.addInteger(n,value);   //default initial value is zero
		return false;
	}

	@Override
	public void endVisit(IntDec n) {
	}

	@Override
	public boolean visit(ArrayDec n) {
		int attribute = TypeConstantMap.getTypeConstant(n.getTypeName());
		attribute = attribute | ASTUtils.getModifierAttributes(n);
		int priority = 0;
		if (n.getTypeName().toLowerCase()== "distributed")
			priority = SipConstants.distributed_array_priority;
		else if (n.getTypeName().toLowerCase() == "served")
			priority = SipConstants.served_array_priority;
		DimensionList dimensions = n.getDimensionList();
		int rank = dimensions.size();
		int[] indarray = new int[rank];
		for (int i = 0; i != rank; i++) {
			IDec indexIDec = dimensions.getDimensionAt(i).getDec();
			indarray[i] = indexTable.getIndex(indexIDec);
		}
		arrayTable.addArrayEntry(n, rank, attribute, indarray,
				priority);
		return false;
	}

	@Override
	public void endVisit(ArrayDec n) { /* nop */
	}

//	@Override
//	public boolean visit(ArrayKind n) {/* nothing to do */
//		return false;
//	}
//
//	@Override
//	public void endVisit(ArrayKind n) { /* nop */
//	}

	@Override
	public boolean visit(DimensionList n) { /*  visit children //TODO is this necessary? */
		return true;
	}

	@Override
	public void endVisit(DimensionList n) {
		/* noop */
	}

	@Override
	public boolean visit(IndexDec n) {
		int indexTypeNum = TypeConstantMap.getTypeConstant(n.getTypeName());
		int bseg, eseg;
		boolean bsegIsSymbolic = false;
		boolean esegIsSymbolic = false;
		IRangeVal range1 = n.getRange().getRangeValStart();
		if (range1 instanceof IdentRangeVal) { // this must be a predefined int, already checked during type checking
			IntDec dec1 = (IntDec) ((IdentRangeVal) range1).getDec();
			bseg = intTable.getIntIndex(dec1);
			bsegIsSymbolic = true;
		} else { 
			bseg = getIntVal(range1);
		} 
		IRangeVal range2 = n.getRange().getRangeValEnd();
		if (range2 instanceof IdentRangeVal) { // this must be a predefined int, already checked during type checking
			IntDec dec2 = (IntDec)((IdentRangeVal) range2).getDec();
			eseg = intTable.getIntIndex(dec2);
			esegIsSymbolic = true;
		} else {
			eseg = getIntVal(range2);
		} 
		indexTable.addEntry(n, bseg, bsegIsSymbolic, eseg, esegIsSymbolic, indexTypeNum);
		return false;
	}

	@Override
	public void endVisit(IndexDec n) { /* nop */
	}

	@Override
	public boolean visit(IndexKind n) { /* nothing to do */
		return false;
	}

	@Override
	public void endVisit(IndexKind n) { /* nop */
	}

	@Override
	public boolean visit(SubIndexDec n) {
		int indexTypeNum = TypeConstantMap.getTypeConstant("subindex");
		IndexDec parentDec = (IndexDec) n.getParentIdent().getDec();
		int parentIndex = indexTable.getIndex(parentDec);
		indexTable.addEntry(n, parentIndex, false, 0, false, indexTypeNum);
		return false;
	}

	@Override
	public void endVisit(SubIndexDec n) {/* nop */
	}

	@Override
	public boolean visit(Range n) { /* visit children */
		return true;
	}

	public void endVisit(Range n) { /* nop */
	}

	@Override
	public boolean visit(IntLitRangeVal n) { /* handled by IndexDec */
		return false;
	}

	@Override
	public void endVisit(IntLitRangeVal n) { /* nop */
	}
	
	@Override
	public boolean visit(NegRangeVal n) { /* handled by IndexDec */
		return false;
	}

	@Override
	public void endVisit(NegRangeVal n) { /* nop */
	}

	@Override
	public boolean visit(IdentRangeVal n) {/* handled by IndexDec */
		return false;
	}

	@Override
	public void endVisit(IdentRangeVal n) { /* nop */
	}

	@Override
	public boolean visit(ProcDec n) {
		if (n.getCallSites().size() == 0)  //call sites should have been collected during type checking.
			return false; // if the proc is never called, don't generate code
							// for it.
		int addr = opTable.nOps;
		n.setAddr(addr);
		n.getStatementList().accept(this);
		// add return op
		opTable.addOptableEntry(return_op, defaultOneInd,
				lineno(n.getendIdent()));
		return false;
	}

	@Override
	public void endVisit(ProcDec n) {
//		// add return op
//		opTable.addOptableEntry(return_op, defaultOneInd,
//				lineno(n.getendIdent()));
	}

	@Override
	public boolean visit(SpecialDec n) {  //FIXME  what is going on with compute_intergrals???
		String name = n.getIdent().getName();
		// if this is compute_integrals, ignore it. This is hack to deal with
		// the design of the sip that treats compute_integrals as a sial
		// language statement
		if (name.equals("compute_integrals"))
			return false;
		// append '@' followed by the signature string to the end of the name
		String sig = n.getNumArgs() == 0 ? "" : n.getSignature().getName();
		int index = specialInstructionTable.addEntry(name + '@' + sig);
		n.setAddr(index); // store index into special instruction table in this
							// dec
		return false;
	}

	@Override
	public void endVisit(SpecialDec n) { /* nop */
	}

	@Override
	public boolean visit(StatementList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(StatementList n) {/* nop */
	}

	@Override
	public boolean visit(WhereClause n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(WhereClause n) { /* nop, handled in RelationalExpression */
	}

	@Override
	public boolean visit(WhereClauseList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(WhereClauseList n) { /* nop */
	}

	@Override
	public boolean visit(CallStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(CallStatement n) {
		int addr = operandStack.pop();
		// int addr = ((ProcDec)n.getIdent().getDec()).getAddr();
		opTable.addOptableEntry(call_op, addr, defaultOneInd, lineno(n));
	}

	@Override
	public boolean visit(ReturnStatement n) {
		opTable.addOptableEntry(return_op, defaultOneInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(ReturnStatement n) { /* nop */
	}

	@Override
	public boolean visit(ServerBarrierStatement n) {
		opTable.addOptableEntry(sip_barrier_op, defaultZeroInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(ServerBarrierStatement n) {/* nop */
	}

	public boolean visit(SipBarrierStatement n) {
		Ident identOpt = n.getIdentOpt();
		if (identOpt == null) {
			opTable.addOptableEntry(sip_barrier_op, defaultZeroInd, lineno(n));
		} else
			assert false : "arguments for barriers not supported";
		return false;
	}

	@Override
	public void endVisit(SipBarrierStatement n) { /* nop */
	}

	// @Override
	// public boolean visit(Section n) { /* visit children */
	// return true;
	// }

	// @Override
	// public void endVisit(Section n) { // AcesHack for now, this is treated as
	// an
	// // "execute aceshack_server_barrier"
	// // instruction
	// // The Section object holds the SpecialDec, which was set by the
	// // TypeCheckVisitor
	// int functionAddr = ((SpecialDec) n.getDec()).getAddr();
	// opTable.addOptableEntry(user_sub_op, noArgExecuteArg, defaultZeroInd,
	// functionAddr, lineno(n));
	// }

	@Override
	public boolean visit(DoStatement n) {
		// this method visits children manually
		// visit the loop variable ident to get its index
		n.getStartIndex().accept(this);
		int[] ind = Arrays.copyOf(defaultOneInd, defaultOneInd.length);
		ind[0] = operandStack.pop(); // index of loop variable overwrites the firstelement of the ind array, so need a copy.
		int do_instruction = opTable.addOptableEntry(do_op, ind, lineno(n));
		// visit remaining children
		if (n.getWhereClauseList() != null)
			n.getWhereClauseList().accept(this);
		if (n.getStatementList() != null)
			n.getStatementList().accept(this);
		opTable.backpatchBranch(do_instruction); // packpatches the do
													// instruction to add the pc
													// of this endDo to
													// "result_array" field
		opTable.addOptableEntry(enddo_op, ind, lineno(n.getEndIndex()));

		// backpatch do instruction with address matching enddo. put is in the
		// result_array.
		return false;
	}

	@Override
	public void endVisit(DoStatement n) { /* nop */
	}

	// This is the same as Do except that it has its own opcode and the
	// instruction includes the slot of the parent index
	@Override
	public boolean visit(DoStatementSubIndex n) {
		// this method visits children manually
		// visit the loop variable ident to get its index
		n.getStartIndex().accept(this);
		// visit the parent loop variable ident to get its index
		n.getStartParentIndex().accept(this);
		int[] ind = Arrays.copyOf(defaultOneInd, defaultOneInd.length);
		// get index of parent loop var
		int parent = operandStack.pop();
		// replace ind[0] with index of loop variable
		ind[0] = operandStack.pop();
		int dosubindex_instruction = opTable.addOptableEntry(dosubindex_op,
				parent, 0, ind, lineno(n)); // the zero is put in "result_array"
											// slot to reserve
											// space for backpatch. Parent goes
											// in op1_array slot.
		// visit remaining children
		if (n.getWhereClauseList() != null)
			n.getWhereClauseList().accept(this);
		if (n.getStatementList() != null)
			n.getStatementList().accept(this);
		opTable.backpatchBranch(dosubindex_instruction); // packpatches the do
															// instruction to
															// add the pc of
															// this endDo to
															// "result_array"
															// field
		opTable.addOptableEntry(enddosubindex_op, parent, 0, ind,
				lineno(n.getEndIndex())); // parent in op_1 slot for consistency
		return false;

	}

	@Override
	public void endVisit(DoStatementSubIndex n) { /* nop */
	}

	@Override
	// ACES4 includes number of indices in instruction
	public boolean visit(PardoStatement n) {
		// visit children manually
		n.getStartIndices().accept(this);
		// the index array is on top of the indexArrayStack
		int num_indices = n.getStartIndices().size();
		int[] ind = indexArrayStack.pop();
		int pardo_instruction = opTable.addOptableEntry(pardo_op, num_indices,
				0, ind, lineno(n));
		// visit children
		if (n.getWhereClauseList() != null)
			n.getWhereClauseList().accept(this);
		if (n.getStatementList() != null)
			n.getStatementList().accept(this);
		opTable.backpatchBranch(pardo_instruction);
		opTable.addOptableEntry(endpardo_op, ind, lineno(n.getEndIndices()));
		return false;
	}

	@Override
	public void endVisit(PardoStatement n) {/* nop */
	}

	@Override
	public boolean visit(ExitStatement n) {
		opTable.addOptableEntry(exit_op, defaultOneInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(ExitStatement n) { /* nop */
	}

	@Override
	public boolean visit(CycleStatement n) {
		assert false : "cycle statement not yet implemented";
		return true;
	}

	@Override
	public void endVisit(CycleStatement n) {
		// TODO test this and change TypeCheckVisitor to allow it
		int index = operandStack.pop();
		opTable.addOptableEntry(cycle_op, index, defaultOneInd, lineno(n));
	}

	@Override
	public boolean visit(IfStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(IfStatement n) {
		// the relational expression has generated a jmpz instruction
		// and pushed its address on the bakcpatchInstruction stack
		int pc = backpatchInstructionStack.pop();
		// set the result index to the index of next instruction
		opTable.backpatchBranch(pc);
	}

	@Override
	public boolean visit(IfElseStatement n) {
		// visit children manually

		// visit relational expression
		n.getRelationalExpression().accept(this);
		// the relational instruction visit method has generated a jmpz
		// instruction
		// and pushed its address on the backpatchInstruction stack
		int jmpzPC = backpatchInstructionStack.pop();
		assert jmpzPC == opTable.nOps - 1 : "backpatch stack had unexpected value in IfElseStatement";
		// visit the statements of the if block
		n.getifStatements().accept(this);
		// add a goto to jump over the else part
		int gotoPC = opTable.addOptableEntry(go_to_op, 0, defaultOneInd,
				lineno(n));
		// patch the jmpz instruction
		opTable.backpatchBranch(jmpzPC);
		// visit the statements of the else block
		n.getelseStatements().accept(this);
		// backpatch the goto statement
		opTable.backpatchBranch(gotoPC);
		return false;
	}

	@Override
	public void endVisit(IfElseStatement n) {/* nop */
	}

	@Override
	public boolean visit(AllocateStatement n) { /* visit children */
		return true;
	}

	
	//TODO fix to handle contiguous locals 
	//TODO grammar does not seem to require a selector.  Does this still work if that is the case?    See Deallocate for how this should/could be handled.
	@Override
	public void endVisit(AllocateStatement n) {
		// get index of array to be allocated
		int index = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		opTable.addOptableEntry(allocate_op, index, ind, lineno(n));
	}

	@Override
	public boolean visit(DeallocateStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(DeallocateStatement n) {
		int index = operandStack.pop();
		int[] ind = null;
		if (n.getAllocIndexListopt() != null) {
			ind = indexArrayStack.pop();
		} else {  //if no block selector, create one with all wild values.
			ind = defaultUndefInd;
			ArrayDec arrayDec = (ArrayDec) n.getIdent().getDec();
			DimensionList decDims = arrayDec.getDimensionList();
			for (int i = 0; i < decDims.size(); ++i) {
				ind[i] = wild;
			}
		}
		opTable.addOptableEntry(deallocate_op, index, ind, lineno(n));
	}

	@Override
	public boolean visit(CreateStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(CreateStatement n) { //FIXME  does a create ever need a selector?
		int[] ind = null;
		if (n.getAllocIndexListopt() != null) {
			ind = indexArrayStack.pop();
		} else
			ind = defaultZeroInd;
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(create_op, arrayIndex, ind, lineno(n));
	}

	@Override
	public boolean visit(DeleteStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DeleteStatement n) { //FIXME  does a delete ever need a selector?
		int[] ind = null;
		if (n.getAllocIndexListopt() != null) // {
			ind = indexArrayStack.pop();
		// } else
		ind = defaultOneInd;
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(delete_op, arrayIndex, ind, lineno(n));
	}

	@Override
	public boolean visit(PutStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(PutStatement n) {
		int rhsIndex = operandStack.pop();
		int lhsIndex = operandStack.pop();
		int[] rhsInd = indexArrayStack.pop();
		int[] lhsInd = indexArrayStack.pop();
		int rhsRank = arrayTable.getRank(rhsIndex);
		int lhsRank = arrayTable.getRank(lhsIndex);
		int opcode = 0;
		if (n.getAssignOp().getop().getKind() == TK_ASSIGN) {
			opcode = put_replace_op;
		} else if (n.getAssignOp().getop().getKind() == TK_PLUS_ASSIGN) {
			opcode = put_accumulate_op;
		} else
			assert false : "illegal operator for put statement";
		opTable.addOptableEntry(push_block_selector_op, lhsRank, lhsIndex, lhsInd,
				lineno(n));
		opTable.addOptableEntry(push_block_selector_op, rhsRank, rhsIndex, rhsInd,
				lineno(n));
		opTable.addOptableEntry(opcode, rhsIndex, lhsIndex, defaultOneInd,
				lineno(n));
	}

	@Override
	public boolean visit(GetStatement n) {/* visit the children */
		return true;
	}

	@Override
	public void endVisit(GetStatement n) {
		int index = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		int rank = arrayTable.getRank(index);
		opTable.addOptableEntry(push_block_selector_op, rank, index, ind, lineno(n));
		opTable.addOptableEntry(get_op, index, ind, lineno(n));
	}

	@Override
	public boolean visit(PrepareStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(PrepareStatement n) {
		int rhsIndex = operandStack.pop();
		int lhsIndex = operandStack.pop();
		int[] rhsInd = indexArrayStack.pop();
		int[] lhsInd = indexArrayStack.pop();
		int rhsRank = arrayTable.getRank(rhsIndex);
		int lhsRank = arrayTable.getRank(lhsIndex);
		int opcode = 0;
		if (n.getAssignOp().getop().getKind() == TK_ASSIGN) {
			opcode = prepare_op;
		} else if (n.getAssignOp().getop().getKind() == TK_PLUS_ASSIGN) {
			opcode = prepare_increment_op;
		} else
			assert false : "illegal operator for prepare statement";
		opTable.addOptableEntry(push_block_selector_op, lhsRank, lhsIndex, lhsInd,
				lineno(n));
		opTable.addOptableEntry(push_block_selector_op, rhsRank, rhsIndex, rhsInd,
				lineno(n));
		opTable.addOptableEntry(opcode, rhsIndex, lhsIndex, defaultOneInd,
				lineno(n));
	}

	@Override
	public boolean visit(RequestStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(RequestStatement n) { // FIXME: eliminate the "hint"?
		if (n.getIdentOpt() != null) {
			operandStack.pop(); // discard hint
		}
		int index = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		int rank = arrayTable.getRank(index);
		opTable.addOptableEntry(push_block_selector_op, rank, index, ind,
				lineno(n));
		opTable.addOptableEntry(request_op, index, ind, lineno(n));
	}

	@Override
	public boolean visit(PrequestStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(PrequestStatement n) {
		int rhsIndex = operandStack.pop();
		int lhsIndex = operandStack.pop();
		int[] rhsInd = indexArrayStack.pop();
		int[] lhsInd = indexArrayStack.pop();
		int rhsRank = arrayTable.getRank(rhsIndex);
		int lhsRank = arrayTable.getRank(lhsIndex);
		opTable.addOptableEntry(push_block_selector_op, lhsRank, lhsIndex, lhsInd,
				lineno(n));
		opTable.addOptableEntry(push_block_selector_op, rhsRank, rhsIndex, rhsInd,
				lineno(n));
		opTable.addOptableEntry(prequest_op, rhsIndex, lhsIndex, defaultOneInd,
				lineno(n));
	}

	@Override
	public boolean visit(CollectiveStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(CollectiveStatement n) {
		int rhsIndex = operandStack.pop();
		int lhsIndex = operandStack.pop();
		opTable.addOptableEntry(collective_sum_op, rhsIndex, lhsIndex,
				defaultOneInd, lineno(n));
	}

	@Override
	public boolean visit(ExecuteStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(ExecuteStatement n) {
		ArgList args = n.getArgList();
		int numArgs = args.size();
//		for (int i = 0; i < numArgs; ++i) {
//			IArg arg = args.getArgAt(numArgs - 1 - i); // args will be popped
//														// off the operand stack
//														// in reverse order
//			int array_table_slot = noArgExecuteArg;
//			int rank = 0;
//			int[] ind = defaultUndefInd;
//			if (arg instanceof IdentExpr || arg instanceof DoubleLitExpr) {
//				array_table_slot = operandStack.pop();
//				rank = 0;
//			} else if (arg instanceof DataBlockExpr) {
//				array_table_slot = operandStack.pop();
//				rank = arrayTable.getRank(array_table_slot);
//				ind = indexArrayStack.pop();
//			}
//			// A static or contiguous array without a block selector will have a rank =0  here.
//			//  The sip will compare with the declared rank to determine how to handle this.
//			opTable.addOptableEntry(push_block_selector_op, rank, array_table_slot, ind,
//					lineno(n)); 
//		}
		int functionAddr = operandStack.pop();
		opTable.addOptableEntry(user_sub_op, functionAddr, 0, numArgs,
				defaultUndefInd, lineno(n));
	}
	
	//a DataBlock arg encloses a DataBlock.  Add instruction to push blockselector
	@Override
    public boolean visit(DataBlockArg n) {  return true; }
	@Override
    public void endVisit(DataBlockArg n) { 
		int arraySlot = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		int rank = arrayTable.getRank(arraySlot);
		opTable.addOptableEntry(push_block_selector_op, rank, arraySlot, ind, lineno(n));
	}

	//this is a separate type from an Ident. It must be either a scalar or a static or contiguous array
	@Override
    public boolean visit(IdentArg n) { 
		IDec dec = n.getDec();
        if (dec instanceof ScalarDec || dec instanceof ArrayDec) {
			int arrayTableSlot = arrayTable.getIndex(dec);
			opTable.addOptableEntry(push_block_selector_op, 0, arrayTableSlot, defaultZeroInd, lineno(n));
			return false;
        }
		assert false:  "compiler bug: IdentArg " + n + " not handled properly in code generation at sialx line " + n.getIDENTIFIER().getLine();
        return false;
	}


	@Override
    public void endVisit(IdentArg n) { /*nop*/ }

	@Override
    public boolean visit(DoubleLitArg n) {
		double val = getDoubleVal(n.getDOUBLELIT());
		int nScalars = scalarTable.nScalars; // current number of scalars
		int scalarTableSlot = scalarTable.addDoubleLiteral(val); //searches the scalar table for this value.  If it is already there, it returns the index, if not it adds it and returns the new index.
		int arrayTableSlot;
		if (scalarTable.nScalars > nScalars) { 
			// this is a new value, add to array table
			arrayTableSlot = arrayTable.addScalarEntry(null, scalar_value_t,
					scalarTableSlot);
		} 
		else arrayTableSlot = arrayTable.getIndexOfScalarEntry(scalarTableSlot);  //FIXME  check how to handle literal arguments to execute statements
		opTable.addOptableEntry(push_block_selector_op, /*rank=*/0, arrayTableSlot, defaultZeroInd, lineno(n));
		return false;		
	}
	@Override
    public void endVisit(DoubleLitArg n) {/*nop*/ }


	@Override
	public boolean visit(DestroyStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DestroyStatement n) {
		int[] ind = defaultOneInd;
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(destroy_op, arrayIndex, ind, lineno(n));
	}

	@Override
	public boolean visit(DataBlock n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(DataBlock n) {
      /* this is a noop--visiting the children will leave the array slot and index list on the operandStack and indexArrayStack, respectively.*/
	}

	//These idents should all be indices
	@Override
	public boolean visit(IdentList n) { /* visit children */
		return true;
	}



	@Override
	public void endVisit(IdentList n) {
		// Each ident in the list represents and index
		// Visiting the children has left the index table slot of each on the operand stack
		// Remove them and put into an  array.
		// Push the array on the indexStack
		int nindex = n.size();
		int[] ind = new int[TypeConstantMap.max_rank];
		for (int i = nindex - 1; i >= 0; i--) {
			ind[i] = operandStack.pop();
		}
		indexArrayStack.push(ind);
	}

	@Override
	public boolean visit(AllocIndexIdent n) {
		IDec dec = n.getDec();
		int identIndex;
		assert (dec instanceof IndexDec || dec instanceof SubIndexDec);
		identIndex = indexTable.getIndex(dec);
		operandStack.push(identIndex);
		return false;
	}

	@Override
	public void endVisit(AllocIndexIdent n) { /* nop */
	}

	@Override
	public boolean visit(AllocIndexWildCard n) {
		operandStack.push(wild); 
		return false;
	}

	@Override
	public void endVisit(AllocIndexWildCard n) { /* nop */
	}

	@Override
	public boolean visit(AllocIndexList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(AllocIndexList n) {
		// construct the index array
		int nindex = n.size();
		// get the index table entries from the stack and fill the ind array
		int[] ind = new int[TypeConstantMap.max_rank];
		for (int i = nindex - 1; i >= 0; i--) {
			ind[i] = operandStack.pop();
		}
		indexArrayStack.push(ind);
	}

	@Override
	public boolean visit(RelationalExpression n) {/* visit children */
		return true;
	}


	@Override
	public void endVisit(RelationalExpression n) {
		IExpression eleft = n.getUnaryExpressionLeft();
		EnumSet<EType> argType = ASTUtils.getIExprTypes(eleft); 
		//type checking already checked that both args have same type												
		int opcode;
		if (argType.contains(SCALAR)) {
			opcode = getRelOpcodeScalar(n.getRelOp());
		} else if (argType.contains(INT) || argType.contains(INDEX)) {
			opcode = getRelOpcodeInt(n.getRelOp());
		} else {
			assert false : "compiler bug:  unexpected type in relational expression at line " + lineno(n) + ": " + n;
			opcode = -1;
		}
		opTable.addOptableEntry(opcode, defaultUndefInd, lineno(n));
		// add a jz and push its index onto the
		// backPatchInstructionStack
		int opTableIndex = opTable.addOptableEntry(jz_op, defaultUndefInd,
				lineno(n));
		backpatchInstructionStack.push(opTableIndex); // TODO move to parent
		return;
	}

	int getRelOpcodeScalar(RelOp relOp) {
		int kind = relOp.getop().getKind();
		switch (kind) {
		case TK_GREATER:
			return scalar_gt_op;
		case TK_LESS:
			return scalar_lt_op;
		case TK_LEQ:
			return scalar_le_op;
		case TK_GEQ:
			return scalar_ge_op;
		case TK_EQ:
			return scalar_eq_op;
		case TK_NEQ:
			return scalar_ne_op;
		default:
			assert false : relOp + "is illegal operator";
		}
		return -1;
	}

	int getRelOpcodeInt(RelOp relOp) {
		int kind = relOp.getop().getKind();
		switch (kind) {
		case TK_GREATER:
			return int_gt_op;
		case TK_LESS:
			return int_lt_op;
		case TK_LEQ:
			return int_le_op;
		case TK_GEQ:
			return int_ge_op;
		case TK_EQ:
			return int_equal_op;
		case TK_NEQ:
			return int_nequal_op;
		default:
			assert false : relOp + " illegal operator";
		}
		return -1;
	}


	@Override
	public boolean visit(NegatedUnaryExpr n){
		return true;
	}

	@Override
	public void endVisit(NegatedUnaryExpr n) { 
		EnumSet<EType> t= ASTUtils.getIExprTypes(n);
		if (t.contains(INT)){
			opTable.addOptableEntry(int_neg_op, defaultUndefInd, lineno(n));
			return;
		}
		if (t.contains(SCALAR)){
			opTable.addOptableEntry(scalar_neg_op, defaultUndefInd, lineno(n));
			return;
		}
		assert false;
	}

//	@Override
//	public boolean visit(IntLitPrimary n) {
//		// TODO remove AcesHack
//		// An int literal appearing in a relational expressions being compared
//		// with
//		// an index value or symbolic constant does not have an entry in the
//		// array table.
//		// Its value is stored directly in the op1_array entry of the
//		// the instruction, i.e in the OpTable entry's op1_array slot.
//		// Here, we push the value onto the operand stack
//
//		// check if ancestor is RelationalExpression
//
//		IAst ancestor = n.getParent();
//		while (ancestor != null && !(ancestor instanceof IRelationalExpression)) {
//			ancestor = ancestor.getParent();
//		}
//		if (ancestor != null) {// this is part of a relational expression
//			int val = getIntVal(n.getINTLIT());
//			operandStack.push(val);
//			return false;
//		}
//		// is not part of relational expression, coerce to float
//		double val = getDoubleVal(n.getINTLIT());
//		int arrayTableIndex;
//		int nScalars = scalarTable.nScalars; // current number of scalars
//		// int scalarTableIndex = scalarTable.addDoubleLiteralFortranIndex(val);
//		int scalarTableIndex = scalarTable.addDoubleLiteral(val); // ACES4
//		if (scalarTable.nScalars > nScalars) { // this is a new value, add to
//												// array table
//			arrayTableIndex = arrayTable.addScalarEntry(null, scalar_value_t,
//					scalarTableIndex);
//		} else {
//			arrayTableIndex = arrayTable
//					.getIndexOfScalarEntry(scalarTableIndex);
//		}
//		// push onto operand stack
//		operandStack.push(arrayTableIndex);
//		return false;
//	}
//
//	@Override
//	public void endVisit(IntLitPrimary n) {/* nop */
//	}

//	@Override
//	public boolean visit(DoubleLitPrimary n) {
//		double val = getDoubleVal(n.getDOUBLELIT());
//		int arrayTableIndex;
//		int nScalars = scalarTable.nScalars; // current number of scalars
//		// int scalarTableIndex = scalarTable.addDoubleLiteralFortranIndex(val);
//		int scalarTableIndex = scalarTable.addDoubleLiteral(val);
//		if (scalarTable.nScalars > nScalars) { // this is a new value, add to
//												// array table
//			arrayTableIndex = arrayTable.addScalarEntry(null, scalar_value_t,
//					scalarTableIndex);
//		} else {
//			arrayTableIndex = arrayTable
//					.getIndexOfScalarEntry(scalarTableIndex);
//		}
//		// push onto operand stack
//		operandStack.push(arrayTableIndex);
//		return false;
//	}

//	public void endVisit(DoubleLitPrimary n) { /* nop */
//	}

	/** pushes slot onto operand stack for use by enclosing statements */
	@Override
	public boolean visit(Ident n) {
		//Idents in the prog name and in declarations are not visited so we don't need to handle those cases.
		// Get the slot of the Ident in the appropriate table and
		// push on to operandStack
		IDec dec = n.getDec();
		int identIndex;
		if (dec instanceof IndexDec || dec instanceof SubIndexDec) {
			identIndex = indexTable.getIndex((IDec) dec);			
		} else if (dec instanceof ScalarDec) {
			identIndex = arrayTable.getIndex(dec);
		} else if (dec instanceof IntDec) {
			identIndex = intTable.getIntIndex((IntDec) dec);
		} else if (dec instanceof ArrayDec) { 
			identIndex = arrayTable.getIndex(dec);
		} else if (dec instanceof ProcDec) { // this is a proc
			identIndex = ((ProcDec) dec).getAddr();
		} else if (dec instanceof SpecialDec) {// this is a special instruction
			identIndex = ((SpecialDec) dec).getAddr();
		} else {
			identIndex = -1;
			assert false:  "Ident " + n + " not handled properly in code generation at sialx line " + n.getIDENTIFIER().getLine();
		}
		operandStack.push(identIndex);
		return false;
	}

	@Override
	public void endVisit(Ident n) { /* nop */
	}

//	@Override
//	public boolean visit(AssignStatement n) { /* visit children */
//		return true;
//	}

	// @Override
	// public void endVisit(AssignStatement n){
	// /* There are several cases
	// a(..) = b(..) op c(..)
	// a = b op c
	// a(..) = b(..) which may be slice or insert
	// a += b
	// a -= b
	// a *= b
	// */
	// }

//	@Override
//	public void endVisit(AssignStatement n) {
//		// There some ugliness in this left over from needing to match
//		// the original aces3 compiler.
//		IExpression rhs = n.getExpression();
//		int expr1Index = 0;
//		int expr2Index = 0;
//		int[] expr1IndexArray = null;
//		int[] expr2IndexArray = null;
//		if (rhs instanceof BinaryExpression) {
//			// get indices and Ind arrays, if any for Binary expression
//			expr2Index = operandStack.pop();
//			if (((BinaryExpression) rhs).getExpr2() instanceof DataBlockPrimary) {
//				expr2IndexArray = indexArrayStack.pop();
//			}
//			expr1Index = operandStack.pop();
//			if (((BinaryExpression) rhs).getExpr1() instanceof DataBlockPrimary) {
//				expr1IndexArray = indexArrayStack.pop();
//			}
//		} else {// rhs is a unary expression, put in expr2
//			expr2Index = operandStack.pop();
//			if (rhs instanceof DataBlockPrimary) {
//				expr2IndexArray = indexArrayStack.pop();
//			}
//		}
//		// get index and indexArray for lhs
//		IScalarOrBlockVar lhs = n.getScalarOrBlockVar();
//		int lhsIndex = 0;
//		int[] lhsIndexArray = null;
//		lhsIndex = operandStack.pop();
//		if (lhs instanceof DataBlock) {
//			lhsIndexArray = indexArrayStack.pop();
//		}
//		// get the opcode
//		IAssignOp assignOp = n.getAssignOp();
//		IBinOp binOp = (rhs instanceof BinaryExpression) ? ((BinaryExpression) rhs)
//				.getBinOp() : null;
//		if (assignOp instanceof AssignOpEqual
//				& !(rhs instanceof BinaryExpression)) {
//			// assignment is of the form x = e
//			if (lhsIndexArray != null) {
//				int lhsRank = arrayTable.getRank(lhsIndex);
//				opTable.addOptableEntry(push_block_selector_op, lhsRank, lhsIndex,
//						lhsIndexArray, lineno(n));
//			}
//			if (expr2IndexArray != null) {
//				opTable.addOptableEntry(push_block_selector_op,
//						arrayTable.getRank(expr2Index), expr2Index,
//						expr2IndexArray, lineno(n));
//			}
//			int opcode;
//			if (n.isInsert()) {
//				opcode = insert_op;
//			} else if (n.isSlice()) {
//				opcode = slice_op;
//			} else
//				opcode = assignment_op;
//			opTable.addOptableEntry(opcode, expr2Index, lhsIndex,
//					defaultOneInd, lineno(n));
//			return;
//		}
//		if (!(rhs instanceof BinaryExpression)) {
//			// op is +=, -= or *=
//			// x += e gets converted to x = x + e
//			expr1Index = lhsIndex;
//			expr1IndexArray = lhsIndexArray;
//		}
//		// generate reindex instructions for any datablocks
//		// starting from the left
//		// this will do the same instruction twice for +=, etc.
//		// it is left that way for now to match the current compiler
//		if (lhsIndexArray != null) {
//			opTable.addOptableEntry(push_block_selector_op, arrayTable.getRank(lhsIndex),
//					lhsIndex, lhsIndexArray, lineno(n));
//		}
//		if (expr1IndexArray != null) {
//			opTable.addOptableEntry(push_block_selector_op, arrayTable.getRank(expr1Index),
//					expr1Index, expr1IndexArray, lineno(n));
//		}
//		if (expr2IndexArray != null) {
//			opTable.addOptableEntry(push_block_selector_op, arrayTable.getRank(expr2Index),
//					expr2Index, expr2IndexArray, lineno(n));
//		}
//		int opcode = getAssignOpcode(assignOp, binOp);
//		// AcesHack the next statement is to match the old aces3 compiler
//		// Need to check if it is still needed
//		int[] ind;
//		if (opcode == contraction_op)
//			ind = getContractedIndices(expr1IndexArray, expr2IndexArray);
//		else if (opcode == tensor_op || opcode == assignment_op)
//			ind = defaultZeroInd;
//		else
//			ind = defaultOneInd;
//		opTable.addOptableEntry(opcode, expr1Index, expr2Index, lhsIndex, ind,
//				lineno(n));
//	}
//
//	private int getAssignOpcode(IAssignOp assignOp, IBinOp binOp) {
//		if (assignOp instanceof AssignOpEqual) {
//			if (binOp == null)
//				return assignment_op;
//			if (binOp instanceof BinOpPlus)
//				return sum_op;
//			if (binOp instanceof BinOpMinus)
//				return subtract_op;
//			if (binOp instanceof BinOpDiv)
//				return divide_op;
//			if (binOp instanceof BinOpStar)
//				return contraction_op;
//			if (binOp instanceof BinOpTensor)
//				return tensor_op;
//		}
//		if (assignOp instanceof AssignOpPlus)
//			return sum_op;
//		if (assignOp instanceof AssignOpMinus)
//			return subtract_op;
//		if (assignOp instanceof AssignOpStar)
//			return self_multiply_op;
//		assert false : "Bug in type checking of assignment statements";
//		return -1;
//	}

	//
	// @Override public boolean visit(ASTNodeToken n) {
	// unimplementedVisitor("visit(ASTNodeToken)");
	// return true;
	// }
	//
	// @Override public void endVisit(ASTNodeToken n) {
	// unimplementedVisitor("endVisit(ASTNodeToken)");
	// }

	@Override
	public void unimplementedVisitor(String s) {
	}

	@Override
	public boolean visit(ImportProgList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(ImportProgList n) { /* nop */
	}

	// modifiers are currently handled via the ASTUtils methods
	@Override
	public boolean visit(ModifierList n) { /* nothing to do */
		return false;
	}

	@Override
	public void endVisit(ModifierList n) { /* nop */
	}





	@Override
	public boolean visit(ArgList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(ArgList n) { /* nop */
	}

	@Override
	public boolean visit(AllocIndexListopt n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(AllocIndexListopt n) { /* nop */
	}

	@Override
	public boolean visit(RelOp n) { //handled in enclosing context
		return false;
	}

	@Override
	public void endVisit(RelOp n) {
		/*nop*/
	}

//	@Override
//	public boolean visit(IdentPrimary n) {
//		// Get the "address" of the Ident in the appropriate table and
//		// push onto the op stack
//		IDec dec = n.getDec();
//		int identIndex;
//		if (dec instanceof IndexDec || dec instanceof SubIndexDec) {
//			// if this Ident is an index or subindex, look up in the indexTable
//			identIndex = indexTable.getIndex(dec);
//			// indexStack.push(identIndex); //TODO TRIAL try without separate
//			// indexStack
//			operandStack.push(identIndex);
//		} else if (dec instanceof ScalarDec) {// if this is a scalar, look up in
//												// array table
//			identIndex = arrayTable.getIndex(dec);
//			operandStack.push(identIndex);
//		} else if (dec instanceof IntDec) {
//			identIndex = scalarTable.getIntIndex(dec);
//			// In the index table, indices for symbolic constants have
//			// negative values to indicate symbolic constant.
//			// In expressions, indices have positive values
//			// with indices starting at 0. The SIP get_symbolic_constant
//			// routine adds one to the index.
//			// Since the index will be incremented when it is added to
//			// the optable, decrement it here.
//			if (identIndex < 0)
//				identIndex = -identIndex - 1;
//			operandStack.push(identIndex);
//		} else if (dec instanceof ArrayDec) { // if it is an array, first
//												// generate setindex instruction
//			identIndex = arrayTable.getIndex(dec);
//			operandStack.push(identIndex); // now push ident
//		} else if (dec instanceof ProcDec) { // this is a procedure
//			identIndex = ((ProcDec) dec).getAddr();
//			operandStack.push(identIndex);
//		} else
//			assert false;
//		return false;
//	}

//	@Override
//	public void endVisit(IdentPrimary n) { /* nop */
//	}

//	@Override
//	public boolean visit(DataBlockPrimary n) { /* visit children */
//		return true;
//	}
//
//	@Override
//	public void endVisit(DataBlockPrimary n) { /* nop */
//	}

//	@Override
//	public boolean visit(StringLitPrimary n) {
//		return true;
//	}

//	@Override
//	public void endVisit(StringLitPrimary n) { 
//		String s = ASTUtils.getStringVal(n);
//	    int stringIndex = stringLiteralTable.getAndAdd(s);	
//	    operandStack.push(stringIndex);
//	}
//
////	@Override
////	public boolean visit(StringLiteral n) {
//////		assert false : "string literals not yet supported except in print and println statements";
////		return false;
////	}
	
	@Override
	public boolean visit(StringLiteral n){
		String s = ASTUtils.getStringVal(n);
	    int stringIndex = stringLiteralTable.getAndAdd(s);	
	    operandStack.push(stringIndex);
	    return false;
	}

	@Override
	public void endVisit(StringLiteral n) {		
		/*nop*/
	}

	
	@Override
	public boolean visit(PrintStatement n){ /*visit child*/
		return true;			
	}

	//At some point, we might want to change this and println so that the argument is a StringlitExpr rather than a StringLit
	@Override
	public void endVisit(PrintStatement n) { 
		int stringIndex = operandStack.pop();
		opTable.addOptableEntry(print_op, stringIndex, defaultZeroInd,lineno(n));		
	}

	
	@Override 
	public boolean visit(PrintlnStatement n){
		return true;
	}

	@Override
	public void endVisit(PrintlnStatement n) { 		
		int stringIndex = operandStack.pop();
	    opTable.addOptableEntry(println_op, stringIndex, defaultZeroInd,lineno(n));		
	}

	@Override
	public boolean visit(PrintIndexStatement n) { /* visit child */
		return true;
	}

	@Override
	public void endVisit(PrintIndexStatement n) {
		int index_slot = operandStack.pop();
		opTable.addOptableEntry(print_index_op, index_slot, defaultZeroInd,
				lineno(n));
	}

	@Override
	public boolean visit(PrintScalarStatement n) { /* visit child */
		return true;
	}

	@Override
	public void endVisit(PrintScalarStatement n) {
		int scalar_slot = operandStack.pop();
		opTable.addOptableEntry(print_scalar_op, scalar_slot, defaultZeroInd, lineno(n));
	}
	
	@Override
	public boolean visit(PrintIntStatement n) { /* visit child */
		return true;
	}

	@Override
	public void endVisit(PrintIntStatement n) {
		int int_slot = operandStack.pop();
		opTable.addOptableEntry(print_int_op, int_slot, defaultZeroInd, lineno(n));
	}
	
//    public boolean visit(GPUStatement n) { //FIXME  ALL GPU related statements currently ignored both here and in the type checker
//    	return false; }
//    public void endVisit(GPUStatement n) { }
    
	@Override
	public boolean visit(GPUSection n){
		opTable.addOptableEntry(gpu_on_op, defaultZeroInd, lineno(n));
		return true;
	}
	
	@Override
	public void endVisit(GPUSection n){
		opTable.addOptableEntry(gpu_off_op, defaultZeroInd, lineno(n));
	}


	@Override
	public boolean visit(GPUAllocateBlock n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUAllocateBlock n) {
//		IPrimary arg = n.getPrimary();
//		int array_table_slot = -1;
//		int[] ind = null;
//		int rank = -1;
//		if (arg instanceof IdentExpr) {
//			array_table_slot = operandStack.pop();
//			ind = defaultUndefInd;
//			rank = 0;
//		} else if (arg instanceof DataBlockExpr) {
//			array_table_slot = operandStack.pop();
//			rank = arrayTable.getRank(array_table_slot);
//			ind = indexArrayStack.pop();
//		} else
//			assert false;
//		// If this is an array, with rank 0, it is a static or contiguous array
//		// without a block selector.
//		opTable.addOptableEntry(push_block_selector_op, rank, array_table_slot, ind,
//				lineno(n)); // array_table_slot goes in resultIndex slot of
//							// OptableEntry
//		opTable.addOptableEntry(gpu_allocate_op, array_table_slot,
//				defaultUndefInd, lineno(n));
	}

	@Override
	public boolean visit(GPUFreeBlock n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUFreeBlock n) {
//		IPrimary arg = n.getPrimary();
//		int array_table_slot = -1;
//		int[] ind = null;
//		int rank = -1;
//		if (arg instanceof IdentPrimary) {
//			array_table_slot = operandStack.pop();
//			ind = defaultUndefInd;
//			rank = 0;
//		} else if (arg instanceof DataBlockPrimary) {
//			array_table_slot = operandStack.pop();
//			rank = arrayTable.getRank(array_table_slot);
//			ind = indexArrayStack.pop();
//		} else
//			assert false;
//		// If this is an array, with rank 0, it is a static or contiguous array
//		// without a block selector.
//		opTable.addOptableEntry(push_block_selector_op, rank, array_table_slot, ind,
//				lineno(n)); // array_table_slot goes in resultIndex slot of
//							// OptableEntry
//		opTable.addOptableEntry(gpu_free_op, array_table_slot,
//				defaultUndefInd, lineno(n));
	}

	@Override
	public boolean visit(GPUPutBlock n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUPutBlock n) {
//		IPrimary arg = n.getPrimary();
//		int array_table_slot = -1;
//		int[] ind = null;
//		int rank = -1;
//		if (arg instanceof IdentPrimary) {
//			array_table_slot = operandStack.pop();
//			ind = defaultUndefInd;
//			rank = 0;
//		} else if (arg instanceof DataBlockPrimary) {
//			array_table_slot = operandStack.pop();
//			rank = arrayTable.getRank(array_table_slot);
//			ind = indexArrayStack.pop();
//		} else
//			assert false;
//		// If this is an array, with rank 0, it is a static or contiguous array
//		// without a block selector.
//		opTable.addOptableEntry(push_block_selector_op, rank, array_table_slot, ind,
//				lineno(n)); // array_table_slot goes in resultIndex slot of
//							// OptableEntry
//		opTable.addOptableEntry(gpu_put_op, array_table_slot,
//				defaultUndefInd, lineno(n));
	}

	@Override
	public boolean visit(GPUGetBlock n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUGetBlock n) {
//		IPrimary arg = n.getPrimary();
//		int arrayTableSlot = -1;
//		int[] ind = null;
//		int rank = -1;
//		if (arg instanceof IdentPrimary) {
//			arrayTableSlot = operandStack.pop();
//			ind = defaultUndefInd;
//			rank = 0;
//		} else if (arg instanceof DataBlockPrimary) {
//			arrayTableSlot = operandStack.pop();
//			rank = arrayTable.getRank(arrayTableSlot);
//			ind = indexArrayStack.pop();
//		} else
//			assert false;
//		// If this is an array, with rank 0, it is a static or contiguous array
//		// without a block selector.
//		opTable.addOptableEntry(push_block_selector_op, rank, arrayTableSlot, ind,
//				lineno(n)); // array_table_slot goes in resultIndex slot of
//							// OptableEntry
//		opTable.addOptableEntry(gpu_get_op, arrayTableSlot,
//				defaultUndefInd, lineno(n));
	}
	
	public boolean visit(SetPersistent n) {
		return true;
	}

	public void endVisit(SetPersistent n) {
		int stringTableSlot = operandStack.pop();
		int arrayTableSlot = operandStack.pop();
		opTable.addOptableEntry(set_persistent_op, stringTableSlot,
				arrayTableSlot, defaultZeroInd, lineno(n));
	}

	public boolean visit(RestorePersistent n) {
		return true;
	}

	public void endVisit(RestorePersistent n) {
		int stringTableSlot = operandStack.pop();
		int arrayTableSlot = operandStack.pop();
		opTable.addOptableEntry(restore_persistent_op, stringTableSlot,
				arrayTableSlot, defaultZeroInd, lineno(n));
	}



	@Override
	public boolean visit(Section n) {
		opTable.addOptableEntry(begin_pardo_section_op, lineno(n));
		return true;
	}

	@Override
	public void endVisit(Section n) {
		opTable.addOptableEntry(end_pardo_section_op, lineno(n));
	}

	@Override
	public boolean visit(AssignToIdent n) {
		return true;  //visit children t generate code to evaluate rhs
	}

	@Override
	public void endVisit(AssignToIdent n) { //FIXME
//		int lhs_slot = operandStack.pop();
//		//set destiniation slot in previous instruction 
//		opTable.setDestination(lhs_slot);
		
		Ident lhs = n.getIdent();
		IDec dec = lhs.getDec();
		if (dec instanceof IntDec){
			int slot = intTable.getIntIndex((IntDec) dec);
			opTable.addOptableEntry(int_store_op, slot, defaultZeroInd,  lineno(n));
			return;
		}
		if (dec instanceof ScalarDec){
			int slot = arrayTable.getIndex(dec);
			opTable.addOptableEntry(scalar_store_op, slot, defaultZeroInd, lineno(n));
			return;
		}
		assert false : "Unexecpted type on lhs of assignment " + n;
	}
	
	@Override
	public boolean visit(AssignToBlock n) {
		return true;  //visit children.  Generate code to evaluate rhs and leave slot and selector of lhs on operand and indexArray stacks.
	}

	@Override
	public void endVisit(AssignToBlock n) {
		//get destination block
		int lhs_slot = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		int op = n.getAssignOp().getop().getKind(); //should be one of TK_ASSIGN, TK_PLUS_ASSIGN, TK_MINUS_ASSIGN, TK_STAR_ASSIGN
		IExpression rhs = n.getExpression();
		if (op == TK_ASSIGN){
			if (ASTUtils.getIExprTypes(rhs).contains(SCALAR)){
				opTable.addOptableEntry(get_block_for_writing_op, lhs_slot, ind, lineno(n));
				opTable.addOptableEntry(fill_block_op, lineno(n)); //gets value from expression stack, destination from block stack
				return;
			}
			if (n.getExpression() instanceof DataBlockExpr){  //assignment has form a[i,j] = b[i,j]
				opTable.addOptableEntry(get_block_for_writing_op, lhs_slot, ind, lineno(n));
				opTable.addOptableEntry(copy_or_transpose_block_op, lineno(n));  //gets all args from block stack
				return;
			}
			//otherwise rhs is binary expression
			if (ASTUtils.isBinary(n.getExpression())){
				opTable.addOptableEntry(get_block_for_writing_op, lhs_slot, ind, lineno(n));
				//a binary expression with block arguments always looks for a destination argument--just swap the instructions so block stack has arg1, arg2, dest
				opTable.swap();
				return;
			}
			assert false : " compiler bug:  unhandled case in endVisit(AssignToBlock) where op == TK_ASSIGN " + lineno(n) + ": "+ n;
			return;
		}
		//rhs is datablock or scalar, unless it is a contraction. 
		if (op == TK_PLUS_ASSIGN){
			if (rhs instanceof StarExpr){
				opTable.addOptableEntry(get_block_for_writing_op, lhs_slot, ind, lineno(n));
				//a binary expression with block arguments always looks for a destination argument--just swap the instructions so block stack has arg1, arg2, dest
				//the StarExpr routine is responsible for checking the context and choosing the appropriate opcode--here is should be contract_accumulate_op
				opTable.swap();	
				assert opTable.lastOpcode() == contraction_accumulate_op;
				return;
			}
			//otherwise, this is a datablock or scalar
			opTable.addOptableEntry(get_block_for_reading_op, lhs_slot, ind, lineno(n));
			opTable.swap();
			opTable.addOptableEntry(get_block_for_writing_op, lhs_slot, ind, lineno(n));
			opTable.addOptableEntry(block_add_op, lineno(n));
			return;
		}
		if (op == TK_MINUS_ASSIGN){
			opTable.addOptableEntry(get_block_for_reading_op, lhs_slot, ind, lineno(n));
			opTable.swap();
			opTable.addOptableEntry(get_block_for_writing_op, lhs_slot, ind, lineno(n));
			opTable.addOptableEntry(block_subtract_op, lineno(n));
			return;			
		}
		if (op == TK_STAR_ASSIGN){ 
			opTable.addOptableEntry(get_block_for_reading_op, lhs_slot, ind, lineno(n));
			opTable.swap();
			opTable.addOptableEntry(get_block_for_writing_op, lhs_slot, ind, lineno(n));
			opTable.addOptableEntry(block_subtract_op, lineno(n));
			return;			
		}
		assert false : "unhandled operator " + n;
		
	}

	@Override
	public boolean visit(AssertSame n) {
		return true;
	}

	@Override
	public void endVisit(AssertSame n) {
		int scalarSlot = operandStack.pop();
		opTable.addOptableEntry(assert_same_op, scalarSlot);
	}



	@Override
	public boolean visit(AddExpr n) {
		return true;
	}

	@Override
	public void endVisit(AddExpr n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(INT)){
			opTable.addOptableEntry(int_add_op, lineno(n));
			return;
		}
		if (t.contains(SCALAR)){
			opTable.addOptableEntry(scalar_add_op, lineno(n));
			return;
		}
		if (t.contains(BLOCK)){
			opTable.addOptableEntry(block_add_op, lineno(n));
			return;
		}
		assert false: "unhandled type of AddExpr " + n;
	}

	@Override
	public boolean visit(SubtractExpr n) {
		return true;
	}

	@Override
	public void endVisit(SubtractExpr n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(INT)){
			opTable.addOptableEntry(int_subtract_op, lineno(n));
			return;
		}
		if (t.contains(SCALAR)){
			opTable.addOptableEntry(scalar_subtract_op, lineno(n));
			return;
		}
		if (t.contains(BLOCK)){
			opTable.addOptableEntry(block_subtract_op, lineno(n));
			return;
		}
		assert false: "compiler bug:  unhandled type of SubtactExpr " + n;
		
	}

	@Override
	public boolean visit(StarExpr n) {
		return true;
	}

	@Override
	public void endVisit(StarExpr n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(INT)){ //FIXME  contract to scalar not handled correctly
			opTable.addOptableEntry(int_multiply_op, lineno(n));
			return;
		}
		if (t.contains(SCALAR)){
			opTable.addOptableEntry(scalar_multiply_op, lineno(n));
			return;
		}
		if (t.contains(BLOCK)){
			//check assignment operator in enclosing statement
			IAst parent =  n.getParent();
			if (parent instanceof AssignToBlock){
			int op = ((AssignToBlock) parent).getAssignOp().getop().getKind();
			if (op == TK_ASSIGN){
			opTable.addOptableEntry(block_contract_op, lineno(n));
			}
			else if (op == TK_PLUS_ASSIGN){
				opTable.addOptableEntry(block_contract_accumulate_op, lineno(n));
			}
			else assert false: "compiler bug:  unexpected assigment operator for contraction " + n;
			return;
			}
			if (parent instanceof AssignToIdent){
				int op = ((AssignToIdent) parent).getAssignOp().getop().getKind();
				if (op == TK_ASSIGN){
				opTable.addOptableEntry(block_contract_op, lineno(n));
				}
				else if (op == TK_PLUS_ASSIGN){
					opTable.addOptableEntry(block_contract_accumulate_op, lineno(n));
				}
				else assert false: "compiler bug:  unexpected assigment operator for contraction " + n;
				return;
				}			
		}
		assert false: "unhandled type of StarExpr " + n;
	}

	@Override
	public boolean visit(DivExpr n) {
		return true;  //visit children.  leaves values of operatnds on top of control or expression stack in sip
	}

	@Override
	public void endVisit(DivExpr n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(INT)){
		     opTable.addOptableEntry(int_divide_op, lineno(n));
		     return;
		}
		if (t.contains(SCALAR)){
			opTable.addOptableEntry(scalar_divide_op, lineno(n));
			return;
		}
		assert false: "compiler bug:  unexpected type for div argument";
	}

	@Override
	public boolean visit(TensorExpr n) {
		// TODO Auto-generated method stub
		return true;
	}

	//START HERE **************************************************
	@Override
	public void endVisit(TensorExpr n) { //This is exactly the same as contraction, except that it only applies to blocks.
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(BLOCK)){
			//check assignment operator in enclosing statement
			AssignToBlock parent = (AssignToBlock) n.getParent();
			int op = parent.getAssignOp().getop().getKind();
			if (op == TK_ASSIGN){
			opTable.addOptableEntry(block_contract_op, lineno(n));
			}
			else if (op == TK_PLUS_ASSIGN){
				opTable.addOptableEntry(block_contract_accumulate_op, lineno(n));
			}
			else assert false: "compiler bug:  unexpected assigment operator for ^ " + n;
			return;
		}
		assert false: "unhandled type of TensorExpr " + n;
	}

	@Override
	public boolean visit(IntCastExpr n) {
		return true;  //visit children, leaves value on top of control stack in sip
	}

	@Override
	public void endVisit(IntCastExpr n) {
		opTable.addOptableEntry(cast_to_scalar_op, lineno(n));	
	}

	@Override
	public boolean visit(ScalarCastExpr n) {
		return true; //visits children, leaves value on top of scalar expression stack in sip.
	}

	@Override
	public void endVisit(ScalarCastExpr n) {
		opTable.addOptableEntry(cast_to_scalar_op,  lineno(n));
	}


	//paren expressions simply fall through to the enclosed expression.
	@Override
	public boolean visit(ParenExpr n) {
		return true;
	}

	@Override
	public void endVisit(ParenExpr n) {
		/*nop*/
	}

	@Override
	public boolean visit(IntLitExpr n) { //the value of int literals is stored in the instruction.
		int value = Integer.valueOf(n.getINTLIT().toString());
		opTable.addOptableEntry(int_load_literal_op, value, defaultUndefInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(IntLitExpr n) {
		/* nop */	
	}

	/**Depending on the context, a double literal may be treated as an array of rank 0, or as a scalar.
	 * The former is the case if this is an argument of an execute statement.
	 */
	@Override
	public boolean visit(DoubleLitExpr n) {
		double val = getDoubleVal(n.getDOUBLELIT());
		int nScalars = scalarTable.nScalars; // current number of scalars
		int scalarTableSlot = scalarTable.addDoubleLiteral(val); //searches the scalar table for this value.  If it is already there, it returns the index, if not it adds it and returns the new index.
		int arrayTableSlot;
		if (scalarTable.nScalars > nScalars) { 
			// this is a new value, add to array table
			arrayTableSlot = arrayTable.addScalarEntry(null, scalar_value_t,
					scalarTableSlot);
		} 
		else arrayTableSlot = arrayTable.getIndexOfScalarEntry(scalarTableSlot);  //FIXME  check how to handle literal arguments to execute statements
//		if (ASTUtils.isExecuteArgument(n)){
//			opTable.addOptableEntry(push_block_selector_op, /*rank=*/0, arrayTableSlot, defaultZeroInd, lineno(n));
//		}
		opTable.addOptableEntry(scalar_load_value_op, arrayTableSlot, defaultZeroInd, lineno(n)); 
		return false;
	}
	


	@Override
	public void endVisit(DoubleLitExpr n) {
		/*nop*/
	}

	/** Depending on the type, generates code to push value of ident expression on the sip's expression, control, or block selector stack*/
	//FIXME  should arguments to super instructions be handled differently?  i.e. push_block_selector with rank 0?
	@Override
	public boolean visit(IdentExpr n) { 
		int slot;
		IDec dec = n.getDec();
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if( t.contains(INT)) {
			slot = intTable.getIntIndex((IntDec) dec);
			opTable.addOptableEntry(int_load_value_op, slot, defaultZeroInd, lineno(n));
			return false;
		}
		if (t.contains(SCALAR)) {
//			slot = scalarTable.getScalarSlot((Sc;alarDec) dec);
			slot = arrayTable.getIndex(dec);
			opTable.addOptableEntry(scalar_load_value_op, slot, defaultZeroInd, lineno(n));
			return false;
		}
		if (t.contains(INDEX)){
			slot = indexTable.getIndex(dec);
			opTable.addOptableEntry(index_load_value_op, slot, defaultZeroInd, lineno(n));
			return false;
		}
		if (t.contains(ARRAY)){
			slot = arrayTable.getIndex(dec);
			opTable.addOptableEntry(push_block_selector_op, /*rank=*/0, slot, defaultZeroInd, lineno(n));
			return false;
		}
		assert false: "compiler bug:  unhandled ident";
		return false;
	}
	
	
//	@Override
//	public boolean visit(Ident n) {
//		IAst ancestor = n.getParent();
//		if (ancestor instanceof Program)
//			return false; // If this Ident is the program name, return
//		while (ancestor != null && !(ancestor instanceof IDec)) {
//			ancestor = ancestor.getParent();
//		}
//		if (ancestor != null & !(ancestor instanceof ProcDec)){
//			assert false:  "should not be visiting children of declarations";
//			return false;// If this Ident is part of a variable declaration,
//							// return
//		}
//		// Get the "address" of the Ident in the appropriate table and
//		// push on to operandStack
//		IDec dec = n.getDec();
//		int identIndex;
//		if (dec instanceof IndexDec || dec instanceof SubIndexDec) {
//			identIndex = indexTable.getIndex((IDec) dec);			
//		} else if (dec instanceof ScalarDec) {
//			identIndex = arrayTable.getIndex(dec);
//		} else if (dec instanceof IntDec) {
//			identIndex = intTable.getIntIndex((IntDec) dec);
//		} else if (dec instanceof ArrayDec) { 
//			identIndex = arrayTable.getIndex(dec);
//		} else if (dec instanceof ProcDec) { // this is a proc
//			identIndex = ((ProcDec) dec).getAddr();
//		} else if (dec instanceof SpecialDec) {// this is a special instruction
//			identIndex = ((SpecialDec) dec).getAddr();
//		} else {
//			identIndex = -1;
//			assert false:  "Ident not handled properly in code generation";
//		}
//		operandStack.push(identIndex);
//		return false;
//	}

	
	
	

	@Override
	public void endVisit(IdentExpr n) {
		/*nop*/
	}

	@Override
	public boolean visit(DataBlockExpr n) {//contains a datablock, so visiting children add arrayslot and indices to stacks
		 return true;
	}

	@Override
	public void endVisit(DataBlockExpr n) {
		int arraySlot = operandStack.pop();
		int rank = arrayTable.getRank(arraySlot);
		int[] ind = indexArrayStack.pop();
		opTable.addOptableEntry(push_block_selector_op, rank, arraySlot, ind, lineno(n));
		opTable.addOptableEntry(get_block_for_reading_op, lineno(n));
	}

	@Override
	public boolean visit(StringLitExpr n) {
		String s = ASTUtils.getStringVal(n);
		int slot = stringLiteralTable.getAndAdd(s);
		opTable.addOptableEntry(string_load_literal_op, /*rank=*/0, slot, defaultZeroInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(StringLitExpr n) {
		/* nop */		
	}

//	@Override
//	public boolean preVisit(IAst element) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void postVisit(IAst element) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean visit(ASTNode n) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void endVisit(ASTNode n) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean visit(ASTNodeToken n) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void endVisit(ASTNodeToken n) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public boolean visit(Modifier n) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void endVisit(Modifier n) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean visit(ScalarInitialValue n) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void endVisit(ScalarInitialValue n) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean visit(IntInitialValue n) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void endVisit(IntInitialValue n) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public boolean visit(ArrayKind n) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void endVisit(ArrayKind n) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public boolean visit(IdentArg n) {
//		//only arrays and scalars allowed as arguments
//		IDec dec = n.getDec();
//		int identIndex;
//        if (dec instanceof ScalarDec) {
//			identIndex = arrayTable.getIndex(dec);
//		} else if (dec instanceof ArrayDec) { 
//			identIndex = arrayTable.getIndex(dec);
//		} else {
//			identIndex = -1;
//			assert false:  "Compiler bug:  Super instruction arugument not handled properly in code generation " + n;
//		}
//		operandStack.push(identIndex);
//		return false;
//	}
//
//	@Override
//	public void endVisit(IdentArg n) {
//		/*nop*/
//	}
//
//	@Override
//	public boolean visit(DataBlockArg n) {
//		
//		return true;
//	}
//
//	@Override
//	public void endVisit(DataBlockArg n) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean visit(DoubleLitArg n) {
//		double val = getDoubleVal(n.getDOUBLELIT());
//		int nScalars = scalarTable.nScalars; // current number of scalars
//		int scalarTableSlot = scalarTable.addDoubleLiteral(val); //serarches the scalar table for this value.  If it is already there, it returns the index, if not it adds it and returns the new index.
//		int arrayTableSlot;
//		if (scalarTable.nScalars > nScalars) { 
//			// this is a new value, add to array table
//			arrayTableSlot = arrayTable.addScalarEntry(null, scalar_value_t,
//					scalarTableSlot);
//		} 
//		else arrayTableSlot = arrayTable.getIndexOfScalarEntry(scalarTableSlot);
//
////		opTable.addOptableEntry(push_block_selector_op, /*rank=*/0, arrayTableSlot, defaultZeroInd, lineno(n));
//
//		return false;
//	}
//
//	@Override
//	public void endVisit(DoubleLitArg n) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public boolean visit(AssignOp n) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void endVisit(AssignOp n) {
//		// TODO Auto-generated method stub
//		
//	}
	

}
