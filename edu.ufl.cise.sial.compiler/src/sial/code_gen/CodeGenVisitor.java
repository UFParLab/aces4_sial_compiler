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
import sial.parser.Ast.*;
import sial.parser.context.ASTUtils;
import sial.parser.context.AmbiguousNameException;
import sial.parser.context.ExpressionType;
//import sial.parser.Ast.PersistentModifier;
//import sial.parser.Ast.DimensionListopt;
import sial.parser.context.ExpressionType.EType;
import static sial.parser.context.ExpressionType.EType.*;

import sial.code_gen.*;
import static sial.code_gen.Opcode.*;

/** Visits AST and generates code for the SIAL program */
public class CodeGenVisitor extends AbstractVisitor implements SialParsersym, SipConstants {

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

	LinkedList<Integer> operandStack; // stack for children to pass array table
										// or int table
										// indices parent
	LinkedList<int[]> indexArrayStack; // stack for children to pass index table
										// indices to parent
	LinkedList<Integer> backpatchInstructionStack; // stack for children to pass
	// address of instructions that need backpatching to parent

	static int[] defaultUndefInd; // array initialized to -1

	static int[] defaultUnusedInd;

	static final int unused = -2;
	static final int toBackpatch = -3;
	static final int undefined = -1;

	int noArgExecuteArg = -1;

	static {
		defaultUndefInd = new int[TypeConstantMap.max_rank];
		for (int i = 0; i != defaultUndefInd.length; i++) {
			defaultUndefInd[i] = undefined;
		}
		defaultUnusedInd = defaultUndefInd;
	}

	static void warn(String msg) {
		System.out.println(msg);
	} // TODO FIX THIS

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
		if (opTable.nOps == 0) { // this program is the main program being
									// compiled.
									// insert the jump instruction to the value
									// needed for an empty program.
			opTable.addOptableEntry(goto_op, 1, unused, unused, defaultUnusedInd, lineno(n));

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
			opTable.backpatchArg0(0);
			opTable.setFirstOpInitialized(true);
		}
		// this takes care of the degenerate case where there is no "main"
		// program which may be useful for testing
		if (!opTable.isFirstOpInitialized() && !getRoot(n).isImported()) {
			opTable.backpatchArg0(0);
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
		attribute = attribute | ASTUtils.getModifierAttributes(n); // may be
																	// predefined
		ASTNodeToken initialValueToken = n.getScalarInitializationOpt().getDOUBLELIT();
		int scalarTableSlot;
		double value = 0.0; // default initial value is 0.0
		if (initialValueToken != null) {
			value = ASTUtils.getDoubleVal(initialValueToken);
		}
		scalarTableSlot = scalarTable.addScalar(n, value);
		arrayTable.addScalarEntry(n, attribute, scalarTableSlot);
		return false;
	}

	@Override
	public void endVisit(ScalarDec n) { /* nop */
	}

	@Override
	public boolean visit(IntDec n) {
		int attribute = int_value_t;
		attribute = attribute | ASTUtils.getModifierAttributes(n); // may be
																	// predefined
		ASTNodeToken initialValueToken = n.getIntInitializationOpt().getINTLIT();
		int value = 0; // default initial value is zero
		if (initialValueToken != null) {
			value = ASTUtils.getIntLitVal(initialValueToken);
		}
		intTable.addInteger(n, value);
		return false;
	}

	@Override
	public void endVisit(IntDec n) { /* nop */
	}

	@Override
	public boolean visit(ArrayDec n) {
		int attribute = TypeConstantMap.getTypeConstant(n.getTypeName());
		attribute = attribute | ASTUtils.getModifierAttributes(n);
		int priority = 0;
		if (n.getTypeName().toLowerCase() == "distributed")
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
		arrayTable.addArrayEntry(n, rank, attribute, indarray, priority);
		return false;
	}

	@Override
	public void endVisit(ArrayDec n) { /* nop */
	}

	@Override
	public boolean visit(ArrayKind n) {/* nothing to do */
		return false;
	}

	@Override
	public void endVisit(ArrayKind n) { /* nop */
	}

	@Override
	public boolean visit(DimensionList n) { /*
											 * no need to visit children,
											 * already handled in parent
											 * ArrayDec
											 */
		return false;
	}

	@Override
	public void endVisit(DimensionList n) {
		/* nop */
	}

	@Override
	public boolean visit(IndexDec n) {
		int indexTypeNum = TypeConstantMap.getTypeConstant(n.getTypeName());
		int bseg, eseg;
		boolean bsegIsSymbolic = false;
		boolean esegIsSymbolic = false;
		IRangeVal range1 = n.getRange().getRangeValStart();
		if (range1 instanceof IdentRangeVal) { // this must be a predefined int,
												// already checked during type
												// checking
			IntDec dec1 = (IntDec) ((IdentRangeVal) range1).getDec();
			bseg = intTable.getIntIndex(dec1);
			bsegIsSymbolic = true;
		} else {
			bseg = getIntVal(range1);
		}
		IRangeVal range2 = n.getRange().getRangeValEnd();
		if (range2 instanceof IdentRangeVal) { // this must be a predefined int,
												// already checked during type
												// checking
			IntDec dec2 = (IntDec) ((IdentRangeVal) range2).getDec();
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
		indexTable.addEntry(n, parentIndex, false /* unused */, unused, false /* unused */, indexTypeNum);
		return false;
	}

	@Override
	public void endVisit(SubIndexDec n) {/* nop */
	}

	@Override
	public boolean visit(Range n) { /* handled by IndexDec */// TODO CHECK
																// THIS!!!
		return false;
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
		if (n.getCallSites().size() == 0) { // call sites should have been
											// collected during type checking.
			warn("Procedure " + n.getName() + "at line " + lineno(n) + " is never called.  No code generated");
			return false; // if the proc is never called, don't generate code
		} // for it.
		int addr = opTable.nOps;
		n.setAddr(addr);
		return true;
	}

	@Override
	public void endVisit(ProcDec n) {
		if (n.getCallSites().size() == 0)
			return; // no code generated
		// add return op (It doesn't matter if this is redundant)
		opTable.addOptableEntry(return_op, unused, unused, unused, defaultUnusedInd, lineno(n.getendIdent()));
	}

	@Override
	public boolean visit(SpecialDec n) {
		String name = n.getIdent().getName();
		// append '@' followed by the signature string to the end of the name
		String sig = n.getNumArgs() == 0 ? "" : n.getSignature().getName();
		int index = specialInstructionTable.addEntry(name + '@' + sig);
		n.setAddr(index); // store index into special instruction table
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
	public boolean visit(WhereClause n) { /*
										 * visit child to generate code to
										 * evaluate condition
										 */
		return true;
	}

	@Override
	public void endVisit(WhereClause n) {
		opTable.addOptableEntry(where_op, unused, unused, unused, defaultUnusedInd, lineno(n));
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
		opTable.addOptableEntry(call_op, addr, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(ReturnStatement n) {
		opTable.addOptableEntry(return_op, unused, unused, unused, defaultUnusedInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(ReturnStatement n) { /* nop */
	}

	public boolean visit(StopStatement n) {
		opTable.addOptableEntry(stop_op, unused, unused, unused, defaultUnusedInd, lineno(n));
		return false;
	}

	public void endVisit(StopStatement n) { /* nop */
	}

	// TODO get rid of server_barrier
	@Override
	public boolean visit(ServerBarrierStatement n) { /*
													 * This is a legacy from
													 * aces3. It is the same as
													 * a sip_barrier
													 */
		opTable.addOptableEntry(sip_barrier_op, unused, unused, unused, defaultUnusedInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(ServerBarrierStatement n) {/* nop */
	}

	public boolean visit(SipBarrierStatement n) {
		opTable.addOptableEntry(sip_barrier_op, unused, unused, unused, defaultUnusedInd, lineno(n));
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
		int[] ind = Arrays.copyOf(defaultUnusedInd, defaultUnusedInd.length);
		ind[0] = operandStack.pop(); // index of loop variable overwrites the
										// first element of the ind array, so
										// need a copy.
		int do_instruction = opTable.addOptableEntry(do_op, toBackpatch, 1, unused, ind, lineno(n)); // 1
																										// is
																										// number
																										// of
																										// indices
																										// for
																										// consistency
																										// with
																										// pardo,
		// visit remaining children
		if (n.getWhereClauseList() != null)
			n.getWhereClauseList().accept(this);
		if (n.getStatementList() != null)
			n.getStatementList().accept(this);
		opTable.backpatchArg0(do_instruction); // packpatches the do
												// instruction to add the pc
												// of this endDo to
												// "result_array" field
		opTable.addOptableEntry(enddo_op, unused, unused, unused, ind, lineno(n.getEndIndex()));
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
		int[] ind = Arrays.copyOf(defaultUnusedInd, defaultUnusedInd.length);
		// get index of parent loop var
		int parent = operandStack.pop();
		// replace ind[0] with index of loop variable
		ind[0] = operandStack.pop();
		int dosubindex_instruction = opTable
				.addOptableEntry(dosubindex_op, toBackpatch, parent, unused, ind, lineno(n)); // the
																								// zero
																								// is
																								// put
																								// in
																								// "result_array"
		// slot to reserve
		// space for backpatch. Parent goes
		// in op1_array slot.
		// visit remaining children
		if (n.getWhereClauseList() != null)
			n.getWhereClauseList().accept(this);
		if (n.getStatementList() != null)
			n.getStatementList().accept(this);
		opTable.backpatchArg0(dosubindex_instruction); // packpatches the do
														// instruction to
														// add the pc of
														// this endDo to
														// "result_array"
														// field
		opTable.addOptableEntry(enddosubindex_op, unused, parent, unused, ind, lineno(n.getEndIndex())); // parent
																											// in
																											// op_1
																											// slot
																											// for
																											// consistency
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
		int pardo_instruction = opTable.addOptableEntry(pardo_op, toBackpatch, num_indices, unused, ind, lineno(n));
		// visit children
		if (n.getWhereClauseList() != null)
			n.getWhereClauseList().accept(this);
		if (n.getStatementList() != null)
			n.getStatementList().accept(this);
		opTable.backpatchArg0(pardo_instruction);
		opTable.addOptableEntry(endpardo_op, unused, num_indices, unused, ind, lineno(n.getEndIndices()));
		return false;
	}

	@Override
	public void endVisit(PardoStatement n) {/* nop */
	}

	@Override
	public boolean visit(ExitStatement n) {
		opTable.addOptableEntry(exit_op, unused, unused, unused, defaultUnusedInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(ExitStatement n) { /* nop */
	}

	@Override
	public boolean visit(IfStatement n) {
		// visit children manually
		// visit relational expression
		n.getRelationalExpression().accept(this);
		int jmpzPC = opTable.addOptableEntry(jump_if_zero_op, toBackpatch, unused, unused, defaultUndefInd, lineno(n));
		// visit the statements of the if block
		n.getStatementList().accept(this);
		// add a goto to jump over the else part, destination will be added
		// later
		// backpatch the jump_if_zero instruction
		opTable.backpatchArg0(jmpzPC);
		return false;

	}

	@Override
	public void endVisit(IfStatement n) { /* nop */
	}

	@Override
	public boolean visit(IfElseStatement n) {
		// visit children manually
		// visit relational expression
		n.getRelationalExpression().accept(this);
		int jmpzPC = opTable.addOptableEntry(jump_if_zero_op, toBackpatch, unused, unused, defaultUndefInd, lineno(n));
		// visit the statements of the if block
		n.getifStatements().accept(this);
		// add a goto to jump over the else part, destination will be added
		// later
		int gotoPC = opTable.addOptableEntry(goto_op, toBackpatch, unused, unused, defaultUnusedInd, lineno(n));
		// backpatch the jump_if_zero instruction
		opTable.backpatchArg0(jmpzPC);
		// visit the statements of the else block
		n.getelseStatements().accept(this);
		// backpatch the goto statement
		opTable.backpatchArg0(gotoPC);
		return false;
	}

	@Override
	public void endVisit(IfElseStatement n) {/* nop */
	}

	@Override
	public boolean visit(AllocateStatement n) { /* visit children */
		return true;
	}

	// TODO fix to handle contiguous locals
	// TODO grammar does not seem to require a selector. Does this still work if
	// that is the case? See Deallocate for how this should/could be handled.
	@Override
	public void endVisit(AllocateStatement n) {
		// get index of array to be allocated
		int array_slot = operandStack.pop();
		int rank = arrayTable.getRank(array_slot);
		int[] selector_indices = indexArrayStack.pop();
		opTable.addOptableEntry(allocate_op, rank, array_slot, unused, selector_indices, lineno(n));
	}

	@Override
	public boolean visit(DeallocateStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(DeallocateStatement n) {
		int array_slot = operandStack.pop();
		int rank = arrayTable.getRank(array_slot);
		int[] selector_indices = null;
		if (n.getAllocIndexListopt() != null) {
			selector_indices = indexArrayStack.pop();
		} else { // if no block selector, create one with all wild values.
			selector_indices = defaultUndefInd;
			ArrayDec arrayDec = (ArrayDec) n.getIdent().getDec();
			DimensionList decDims = arrayDec.getDimensionList();
			for (int i = 0; i < decDims.size(); ++i) {
				selector_indices[i] = wild;
			}
		}
		opTable.addOptableEntry(deallocate_op, rank, array_slot, unused, selector_indices, lineno(n));
	}

	@Override
	public boolean visit(CreateStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(CreateStatement n) {
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(create_op, arrayIndex, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(DeleteStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DeleteStatement n) {
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(delete_op, arrayIndex, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(PutStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(PutStatement n) {
		int rhsArrayTableSlot = operandStack.pop();
		int lhsArrayTableSlot = operandStack.pop();
		int[] rhsInd = indexArrayStack.pop();
		int[] lhsInd = indexArrayStack.pop();
		int rhsRank = arrayTable.getRank(rhsArrayTableSlot);
		int lhsRank = arrayTable.getRank(lhsArrayTableSlot);
		Opcode opcode = null;
		if (n.getAssignOp().getop().getKind() == TK_ASSIGN) {
			opcode = put_replace_op;
		} else if (n.getAssignOp().getop().getKind() == TK_PLUS_ASSIGN) {
			opcode = put_accumulate_op;
		} else
			assert false : "illegal operator for put statement";
		opTable.addOptableEntry(push_block_selector_op, lhsRank, lhsArrayTableSlot, unused, lhsInd, lineno(n));
		opTable.addOptableEntry(push_block_selector_op, rhsRank, rhsArrayTableSlot, unused, rhsInd, lineno(n));
		opTable.addOptableEntry(opcode, rhsArrayTableSlot, lhsArrayTableSlot, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(GetStatement n) {/* visit the children */
		return true;
	}

	@Override
	public void endVisit(GetStatement n) {
		int arrayTableSlot = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		int rank = arrayTable.getRank(arrayTableSlot);
		opTable.addOptableEntry(push_block_selector_op, rank, arrayTableSlot, unused, ind, lineno(n));
		opTable.addOptableEntry(get_op, arrayTableSlot, unused, unused, ind, lineno(n));
	}

	@Override
	public boolean visit(PrepareStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(PrepareStatement n) {
		int rhsArrayTableSlot = operandStack.pop();
		int lhsArrayTableSlot = operandStack.pop();
		int[] rhsInd = indexArrayStack.pop();
		int[] lhsInd = indexArrayStack.pop();
		int rhsRank = arrayTable.getRank(rhsArrayTableSlot);
		int lhsRank = arrayTable.getRank(lhsArrayTableSlot);
		Opcode opcode = null;
		if (n.getAssignOp().getop().getKind() == TK_ASSIGN) {
			opcode = put_replace_op;
		} else if (n.getAssignOp().getop().getKind() == TK_PLUS_ASSIGN) {
			opcode = put_accumulate_op;
		} else
			assert false : "illegal operator for prepare statement";
		opTable.addOptableEntry(push_block_selector_op, lhsRank, lhsArrayTableSlot, unused, lhsInd, lineno(n));
		opTable.addOptableEntry(push_block_selector_op, rhsRank, rhsArrayTableSlot, unused, rhsInd, lineno(n));
		opTable.addOptableEntry(opcode, rhsArrayTableSlot, lhsArrayTableSlot, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(RequestStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(RequestStatement n) {
		int arrayTAbleSlot = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		int rank = arrayTable.getRank(arrayTAbleSlot);
		opTable.addOptableEntry(push_block_selector_op, rank, arrayTAbleSlot, unused, ind, lineno(n));
		opTable.addOptableEntry(get_op, arrayTAbleSlot, unused, unused, ind, lineno(n));
	}

	// @Override
	// public boolean visit(PrequestStatement n) { /* visit children */
	// return true;
	// }
	//
	// @Override
	// public void endVisit(PrequestStatement n) {
	// int rhsIndex = operandStack.pop();
	// int lhsIndex = operandStack.pop();
	// int[] rhsInd = indexArrayStack.pop();
	// int[] lhsInd = indexArrayStack.pop();
	// int rhsRank = arrayTable.getRank(rhsIndex);
	// int lhsRank = arrayTable.getRank(lhsIndex);
	// opTable.addOptableEntry(push_block_selector_op, lhsRank, lhsIndex,
	// lhsInd,
	// lineno(n));
	// opTable.addOptableEntry(push_block_selector_op, rhsRank, rhsIndex,
	// rhsInd,
	// lineno(n));
	// opTable.addOptableEntry(prequest_op, rhsIndex, lhsIndex, defaultOneInd,
	// lineno(n));
	// }

	@Override
	public boolean visit(CollectiveStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(CollectiveStatement n) {
		int lhsIndex = operandStack.pop();
		opTable.addOptableEntry(collective_sum_op, lhsIndex, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(ExecuteStatement n) {/*
											 * visit children manually so that
											 * ops are pushed in opposite order
											 * (this requires fewer mods to
											 * interpreter
											 */
		n.getIdent().accept(this);
		int functionAddr = operandStack.pop();
		ArgList args = n.getArgList();
		int numArgs = args.size();
		for (int i = numArgs - 1; i >= 0; --i) {
			IArg arg = args.getArgAt(i);
			arg.accept(this);
		}
		opTable.addOptableEntry(execute_op, functionAddr, numArgs, unused, defaultUndefInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(ExecuteStatement n) {
	}

	// ContiguousDataBlockArg encloses a ContiguousDataBloc. Add instruction to
	// push block selector
	@Override
	public boolean visit(ContiguousDataBlockArg n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousDataBlockArg n) {
		int arrayTableSlot = operandStack.pop();
		int rank = arrayTable.getRank(arrayTableSlot);
		opTable.addOptableEntry(push_block_selector_op, rank, arrayTableSlot, unused, defaultUnusedInd, lineno(n));
	}

	// a DataBlock arg encloses a DataBlock. Add instruction to push
	// blockselector
	@Override
	public boolean visit(DataBlockArg n) {
		return true;
	}

	@Override
	public void endVisit(DataBlockArg n) {
		int arrayTableSlot = operandStack.pop();
		int[] ind = indexArrayStack.pop();
		int rank = arrayTable.getRank(arrayTableSlot);
		opTable.addOptableEntry(push_block_selector_op, rank, arrayTableSlot, unused, ind, lineno(n));
	}

	// this is a separate type from an Ident. Type checking ensures it is either
	// a scalar or a static or contiguous array.
	// In the latter case, there is no selector indicating the entire array.
	// A zero rank indicates to the SIP that there is no selector.
	@Override
	public boolean visit(IdentArg n) {
		IDec dec = n.getDec();
		int arrayTableSlot = arrayTable.getIndex(dec);
		opTable.addOptableEntry(push_block_selector_op, 0, arrayTableSlot, unused, defaultUnusedInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(IdentArg n) { /* nop */
	}

	@Override
	public boolean visit(DoubleLitArg n) {
		double val = getDoubleVal(n.getDOUBLELIT());
		int nScalars = scalarTable.nScalars; // current number of scalars
		int scalarTableSlot = scalarTable.addDoubleLiteral(val); // searches the
																	// scalar
																	// table for
																	// this
																	// value.
		// If it is already there, it returns the index, if not it adds it and
		// returns the new index, also incrementing nScalars
		int arrayTableSlot;
		if (scalarTable.nScalars > nScalars) {
			// this is a new value, add to array table
			arrayTableSlot = arrayTable.addScalarEntry(null, scalar_value_t, scalarTableSlot);
		} else
			arrayTableSlot = arrayTable.getIndexOfScalarEntry(scalarTableSlot);
		opTable.addOptableEntry(push_block_selector_op, 0, arrayTableSlot, unused, defaultUnusedInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(DoubleLitArg n) {/* nop */
	}

	@Override
	public boolean visit(DestroyStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DestroyStatement n) {
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(delete_op, arrayIndex, unused, unused, defaultUnusedInd, lineno(n));
	}

	public boolean visit(BroadcastStatic n) { /* visit children */
		return true;
	}

	public void endVisit(BroadcastStatic n) {
		int arrayIndex = operandStack.pop();
		opTable.addOptableEntry(broadcast_static_op, arrayIndex, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(DataBlock n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(DataBlock n) {/*
										 * A DataBlock is on the lhs of some
										 * operation and will be included in the
										 * instruction generated by this node's
										 * parent. Visiting the children has
										 * left the array slot and selector on
										 * the stacks to be popped by the
										 * parent. Thus there is nothing to do
										 * here.
										 */
	}

	@Override
	public boolean visit(ContiguousDataBlock n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(ContiguousDataBlock n) {/*
												 * A ContiguousDataBlock is on
												 * the lhs of some operation and
												 * will be included in the
												 * instruction generated by this
												 * node's parent. Visiting the
												 * children has generated code
												 * to evaluate the range values
												 * and left the array id on the
												 * stack to be popped by the
												 * parent. Thus there is nothing
												 * to do here.
												 */
	}

	// These idents should all be indices
	@Override
	public boolean visit(IdentList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(IdentList n) {
		// Each ident in the list represents an index
		// Visiting the children has left the index table slot of each on the
		// compiler's operand stack
		// Remove them and put into an array.
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
	public boolean visit(ContiguousAllocateStatement n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousAllocateStatement n) {
		int arraySlot = operandStack.pop();
		IDec dec = n.getIdent().getDec();
		int rank = ((ArrayDec) dec).getDimensionList().size();
		opTable.addOptableEntry(allocate_contiguous_op, rank, arraySlot, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(ContiguousDeallocateStatement n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousDeallocateStatement n) {
		int arraySlot = operandStack.pop();
		IDec dec = n.getIdent().getDec();
		int rank = ((ArrayDec) dec).getDimensionList().size();
		opTable.addOptableEntry(deallocate_contiguous_op, rank, arraySlot, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(ContiguousIndexRangeExpr n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousIndexRangeExpr n) {/*
													 * nop, code to push values
													 * has onto sip control
													 * stack has been generated
													 * by children
													 */
	}

	@Override
	public boolean visit(ContiguousIndexRangeExprList n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousIndexRangeExprList n) {/* nop */
	}

	@Override
	public boolean visit(RelationalExpression n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(RelationalExpression n) {
		IExpression eleft = n.getUnaryExpressionLeft();
		EnumSet<EType> argType = ASTUtils.getIExprTypes(eleft);
		// type checking already checked that both args have same type
		Opcode opcode;
		if (argType.contains(SCALAR)) {
			opcode = getRelOpcodeScalar(n.getRelOp());
		} else {
			opcode = getRelOpcodeInt(n.getRelOp());
		}
		opTable.addOptableEntry(opcode, unused, unused, unused, defaultUndefInd, lineno(n));
		return;
	}

	Opcode getRelOpcodeScalar(RelOp relOp) {
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
		return invalid_op;
	}

	Opcode getRelOpcodeInt(RelOp relOp) {
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
		return invalid_op;
	}

	@Override
	public boolean visit(NegatedUnaryExpr n) { /* visit child */
		return true;
	}

	@Override
	public void endVisit(NegatedUnaryExpr n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		Opcode opcode;
		if (t.contains(INT)) {
			opcode = int_neg_op;
		} else {
			opcode = scalar_neg_op;
			assert t.contains(SCALAR) : "compiler bug, unexpected type";
		}
		opTable.addOptableEntry(opcode, unused, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(SqrtUnaryExpr n) { /* visit child */
		return true;
	}

	@Override
	public void endVisit(SqrtUnaryExpr n) {
		opTable.addOptableEntry(scalar_sqrt_op, unused, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(Ident n) {
		// Idents in the prog name and in declarations are not visited so we
		// don't need to handle those cases.
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
			assert false : "Ident " + n + " not handled properly in code generation at sialx line "
					+ n.getIDENTIFIER().getLine();
		}
		operandStack.push(identIndex);
		return false;
	}

	@Override
	public void endVisit(Ident n) { /* nop */
	}

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
	public boolean visit(RelOp n) { // handled in enclosing context
		return false;
	}

	@Override
	public void endVisit(RelOp n) {/* nop */
	}

	@Override
	public boolean visit(StringLiteral n) {
		String s = ASTUtils.getStringVal(n);
		int stringIndex = stringLiteralTable.getAndAdd(s);
		operandStack.push(stringIndex);
		return false;
	}

	@Override
	public void endVisit(StringLiteral n) { /* nop */
	}

	@Override
	public boolean visit(PrintStatement n) { /* visit child */
		return true;
	}

	@Override
	public void endVisit(PrintStatement n) {
		IExpression e = n.getExpression();
		EnumSet<EType> argType = ASTUtils.getIExprTypes(e);
		Opcode opcode = invalid_op;
		int appendNewLine = 0; /* do not add /n */
		int slot = unused;
		if (argType.contains(SCALAR)) {
			opcode = Opcode.print_scalar_op;
			if (e instanceof IdentExpr) {
				IDec dec = ((IdentExpr) e).getDec();
				slot = arrayTable.getIndex(dec);
			}
		} else if (argType.contains(INDEX)) {
			opcode = Opcode.print_index_op;
			IDec dec = ((IdentExpr) e).getDec(); // this must be an IdentExpr of
													// an index
			slot = indexTable.getIndex(dec);
		} else if (argType.contains(INT)) {
			opcode = Opcode.print_int_op;
			if (e instanceof IdentExpr) {
				IDec dec = ((IdentExpr) e).getDec();
				slot = intTable.getIntIndex((IntDec) dec);
			}
		} else if (argType.contains(STRING)) {
			opcode = Opcode.print_string_op;
		} else if (argType.contains(BLOCK) || argType.contains(ARRAY) || argType.contains(CONTIG_BLOCK)) {
			opcode = Opcode.print_block_op;
		}
		opTable.addOptableEntry(opcode, appendNewLine, slot, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(PrintlnStatement n) { /* visit child */
		return true;
	}

	@Override
	public void endVisit(PrintlnStatement n) {
		IExpression e = n.getExpression();
		while (e instanceof ParenExpr) {
			e = ((ParenExpr) e).getExpression();
		}
		EnumSet<EType> argType = ASTUtils.getIExprTypes(e);
		Opcode opcode = invalid_op;
		int appendNewLine = 1; /* do add /n */
		int slot = unused;
		if (argType.contains(SCALAR)) {
			opcode = Opcode.print_scalar_op;
			if (e instanceof IdentExpr) {
				IDec dec = ((IdentExpr) e).getDec();
				slot = arrayTable.getIndex(dec);
			}
		} else if (argType.contains(INT)) {
			opcode = Opcode.print_int_op;
			if (e instanceof IdentExpr) {
				IDec dec = ((IdentExpr) e).getDec();
				slot = intTable.getIntIndex((IntDec) dec);
			}
		} else if (argType.contains(INDEX)) {
			opcode = Opcode.print_index_op;
			IDec dec = ((IdentExpr) e).getDec(); // this must be an IdentExpr of
													// an index
			slot = indexTable.getIndex(dec);
		} else if (argType.contains(STRING)) {
			opcode = Opcode.print_string_op;
		} else if (argType.contains(BLOCK) || argType.contains(ARRAY) || argType.contains(CONTIG_BLOCK)) {
			opcode = Opcode.print_block_op;
		}
		opTable.addOptableEntry(opcode, appendNewLine, slot, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(GPUSection n) {
		opTable.addOptableEntry(gpu_on_op, unused, unused, unused, defaultUnusedInd, lineno(n));
		return true;
	}

	@Override
	public void endVisit(GPUSection n) {
		opTable.addOptableEntry(gpu_off_op, unused, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(GPUAllocate n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUAllocate n) {
		assert false : "GPUAllocate not implemented";
		// IPrimary arg = n.getPrimary();
		// int array_table_slot = -1;
		// int[] ind = null;
		// int rank = -1;
		// if (arg instanceof IdentExpr) {
		// array_table_slot = operandStack.pop();
		// ind = defaultUndefInd;
		// rank = 0;
		// } else if (arg instanceof DataBlockExpr) {
		// array_table_slot = operandStack.pop();
		// rank = arrayTable.getRank(array_table_slot);
		// ind = indexArrayStack.pop();
		// } else
		// assert false;
		// // If this is an array, with rank 0, it is a static or contiguous
		// array
		// // without a block selector.
		// opTable.addOptableEntry(push_block_selector_op, rank,
		// array_table_slot, ind,
		// lineno(n)); // array_table_slot goes in resultIndex slot of
		// // OptableEntry
		// opTable.addOptableEntry(gpu_allocate_op, array_table_slot,
		// defaultUndefInd, lineno(n));
	}

	@Override
	public boolean visit(GPUFree n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUFree n) {
		assert false : "GPUFree not yet implemented";
		// IPrimary arg = n.getPrimary();
		// int array_table_slot = -1;
		// int[] ind = null;
		// int rank = -1;
		// if (arg instanceof IdentPrimary) {
		// array_table_slot = operandStack.pop();
		// ind = defaultUndefInd;
		// rank = 0;
		// } else if (arg instanceof DataBlockPrimary) {
		// array_table_slot = operandStack.pop();
		// rank = arrayTable.getRank(array_table_slot);
		// ind = indexArrayStack.pop();
		// } else
		// assert false;
		// // If this is an array, with rank 0, it is a static or contiguous
		// array
		// // without a block selector.
		// opTable.addOptableEntry(push_block_selector_op, rank,
		// array_table_slot, ind,
		// lineno(n)); // array_table_slot goes in resultIndex slot of
		// // OptableEntry
		// opTable.addOptableEntry(gpu_free_op, array_table_slot,
		// defaultUndefInd, lineno(n));
	}

	@Override
	public boolean visit(GPUPut n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUPut n) {
		assert false : "GPUPut not yet implemented";
		// IPrimary arg = n.getPrimary();
		// int array_table_slot = -1;
		// int[] ind = null;
		// int rank = -1;
		// if (arg instanceof IdentPrimary) {
		// array_table_slot = operandStack.pop();
		// ind = defaultUndefInd;
		// rank = 0;
		// } else if (arg instanceof DataBlockPrimary) {
		// array_table_slot = operandStack.pop();
		// rank = arrayTable.getRank(array_table_slot);
		// ind = indexArrayStack.pop();
		// } else
		// assert false;
		// // If this is an array, with rank 0, it is a static or contiguous
		// array
		// // without a block selector.
		// opTable.addOptableEntry(push_block_selector_op, rank,
		// array_table_slot, ind,
		// lineno(n)); // array_table_slot goes in resultIndex slot of
		// // OptableEntry
		// opTable.addOptableEntry(gpu_put_op, array_table_slot,
		// defaultUndefInd, lineno(n));
	}

	@Override
	public boolean visit(GPUGet n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUGet n) {
		assert false : "GPUGet not yet implemented";
		// IPrimary arg = n.getPrimary();
		// int arrayTableSlot = -1;
		// int[] ind = null;
		// int rank = -1;
		// if (arg instanceof IdentPrimary) {
		// arrayTableSlot = operandStack.pop();
		// ind = defaultUndefInd;
		// rank = 0;
		// } else if (arg instanceof DataBlockPrimary) {
		// arrayTableSlot = operandStack.pop();
		// rank = arrayTable.getRank(arrayTableSlot);
		// ind = indexArrayStack.pop();
		// } else
		// assert false;
		// // If this is an array, with rank 0, it is a static or contiguous
		// array
		// // without a block selector.
		// opTable.addOptableEntry(push_block_selector_op, rank, arrayTableSlot,
		// ind,
		// lineno(n)); // array_table_slot goes in resultIndex slot of
		// // OptableEntry
		// opTable.addOptableEntry(gpu_get_op, arrayTableSlot,
		// defaultUndefInd, lineno(n));
	}

	public boolean visit(SetPersistent n) {
		return true;
	}

	public void endVisit(SetPersistent n) {
		int stringTableSlot = operandStack.pop();
		int arrayTableSlot = operandStack.pop();
		opTable.addOptableEntry(set_persistent_op, stringTableSlot, arrayTableSlot, unused, defaultUnusedInd, lineno(n));
	}

	public boolean visit(RestorePersistent n) {
		return true;
	}

	public void endVisit(RestorePersistent n) {
		int stringTableSlot = operandStack.pop();
		int arrayTableSlot = operandStack.pop();
		opTable.addOptableEntry(restore_persistent_op, stringTableSlot, arrayTableSlot, unused, defaultUnusedInd,
				lineno(n));
	}

	@Override
	public boolean visit(Section n) {
		opTable.addOptableEntry(begin_pardo_section_op, unused, unused, unused, defaultUnusedInd, lineno(n));
		return true;
	}

	@Override
	public void endVisit(Section n) {
		opTable.addOptableEntry(end_pardo_section_op, unused, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(AssignToIdent n) { /*
											 * visit children to generate code
											 * to evaluate right hand side
											 */
		return true;
	}

	@Override
	public void endVisit(AssignToIdent n) { // TODO, allow static to static,
		int op = n.getAssignOp().getop().getKind(); // should be one of
													// TK_ASSIGN,
													// TK_PLUS_ASSIGN,
													// TK_MINUS_ASSIGN,
													// TK_STAR_ASSIGN
		Ident lhs = n.getIdent();
		IDec dec = lhs.getDec();
		if (dec instanceof IntDec) { // type of rhs is an int. Code has been
										// generated when visiting children to
										// leave rhs value on top of sip control
										// stack.
			int intSlot = operandStack.pop();
			int secondOpcode = int_store_op.opcodeValue; // op = TK_ASSIGN
			if (op == TK_PLUS_ASSIGN) {
				secondOpcode = int_add_op.opcodeValue;
			} else if (op == TK_MINUS_ASSIGN) {
				secondOpcode = int_subtract_op.opcodeValue;
			} else if (op == TK_STAR_ASSIGN) {
				secondOpcode = int_multiply_op.opcodeValue;
			}
			opTable.addOptableEntry(int_store_op, intSlot, secondOpcode, unused, defaultUnusedInd, lineno(n));
			return;
		}
		if (dec instanceof ScalarDec) { // type of rhs is a scalar. Code has
										// been generted when visiting children
										// to leave rhs value on to of sip
										// expression stack.
			// This is also the case if the rhs is a contract to scalar, or a
			// block with all simple indices.
			int scalarSlot = operandStack.pop();
			int secondOpcode = scalar_store_op.opcodeValue; // op = TK_ASSIGN
			if (op == TK_PLUS_ASSIGN) {
				secondOpcode = scalar_add_op.opcodeValue;
			} else if (op == TK_MINUS_ASSIGN) {
				secondOpcode = scalar_subtract_op.opcodeValue;
			} else if (op == TK_STAR_ASSIGN) {
				secondOpcode = scalar_multiply_op.opcodeValue;
			}
			opTable.addOptableEntry(scalar_store_op, scalarSlot, secondOpcode, unused, defaultUnusedInd, lineno(n));
			return;
		}
		assert false : "Unexpected  assignment " + n;
	}

	@Override
	public boolean visit(AssignToBlock n) {
		return true; // visit children. Generate code to evaluate rhs. Leave
						// slot and selector of lhs block on operand and
						// indexArray stacks.
	}

	@Override
	public void endVisit(AssignToBlock n) {
		// get the actual expression
		IExpression rhs = n.getExpression();
		while (rhs instanceof ParenExpr) {
			rhs = ((ParenExpr) rhs).getExpression();
		}

		// Every binary operation with block result needs a destination block,
		// so there is nothing to do here.
		// The expression will have already popped the slot and selector.
		// The StarExpr also handles case of accumulate into lhs
		EnumSet<EType> type = ASTUtils.getIExprTypes(rhs);
		if (type.contains(BLOCK)
				&& (rhs instanceof AddExpr || rhs instanceof SubtractExpr || rhs instanceof StarExpr
						|| rhs instanceof TensorExpr || rhs instanceof NegatedUnaryExpr))
			return;

		// Otherwise, we could have a DoubleLitExpr, an IdentExpr, or a
		// DatablockExpr
		// Get lhs info
		int lhs_slot = operandStack.pop();
		int lhs_rank = arrayTable.getRank(lhs_slot);
		int[] lhs_ind = indexArrayStack.pop();

		int op = n.getAssignOp().getop().getKind(); // should be one of
													// TK_ASSIGN,
													// TK_PLUS_ASSIGN,
													// TK_MINUS_ASSIGN,
													// TK_STAR_ASSIGN

		if (op == TK_ASSIGN) {
			if (type.contains(SCALAR)) {
				opTable.addOptableEntry(block_fill_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n)); // gets
																										// value
																										// from
																										// expression
																										// stack,
																										// destination
																										// block
																										// selector
																										// from
																										// block
																										// selector
																										// stack
				return;
			}
			if (type.contains(CONTIG_BLOCK)) {
				opTable.addOptableEntry(block_copy_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
				return;
			}
			// otherwise is a copy, transpose, slice, or insert op. These
			// options have been determined during type checking. RHS block
			// selector has been pushed, then LHS block selector
			if (n.isInsert()) {
				opTable.addOptableEntry(insert_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
				return;
			}
			if (n.isSlice()) {
				opTable.addOptableEntry(slice_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
				return;
			}
			DataBlock lhsDataBlock = n.getDataBlock();
			DataBlock rhsDataBlock = ((DataBlockExpr) n.getExpression()).getDataBlock();
			int[] permutation = getPermutation(lhsDataBlock, rhsDataBlock);
			if (permutation != null) {
				opTable.addOptableEntry(push_block_selector_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
				opTable.addOptableEntry(block_permute_op, unused, unused, unused, permutation, lineno(n));
				return;
			}
			// just a plain old copy
			opTable.addOptableEntry(block_copy_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
			return;
		}
		if (op == TK_PLUS_ASSIGN) {
			if (type.contains(SCALAR)) {
				opTable.addOptableEntry(block_accumulate_scalar_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
				return;
			}
			opTable.addOptableEntry(push_block_selector_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n)); // converts
																												// to
																												// lhs
																												// =
																												// lhs
																												// +
																												// rhs.
			opTable.addOptableEntry(block_add_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n)); //
			return;
		}
		if (op == TK_MINUS_ASSIGN) {
			if (type.contains(SCALAR)) { // convert to lhs = lhs + -rhs
				opTable.addOptableEntry(scalar_neg_op, unused, unused, unused, defaultUnusedInd, lineno(n));
				opTable.addOptableEntry(block_accumulate_scalar_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
				return;
			}
			opTable.addOptableEntry(push_block_selector_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n)); // converts
																												// to
																												// lhs
																												// =
																												// lhs
																												// -
																												// rhs.
			opTable.swap(); // reorder block selector instructions so
							// subtraction will be done correctly
			opTable.addOptableEntry(block_subtract_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n)); //
			return;
		}
		if (op == TK_STAR_ASSIGN && type.contains(SCALAR)) {
			opTable.addOptableEntry(block_scale_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
			return;
		}

		assert false : "unhandled operator " + n;

	}

	private int[] getPermutation(DataBlock lhs, DataBlock rhs) {
		int permutation[] = new int[max_rank];
		IdentList lhsIndices = lhs.getIndices();
		int lhsRank = lhsIndices.size();
		IdentList rhsIndices = rhs.getIndices();
		int rhsRank = rhsIndices.size();
		if (lhsRank != rhsRank)
			return null;
		for (int i = 0; i < lhsRank; ++i) {
			// int lhs_index = lhs_selector[i];
			String lhs_index = lhsIndices.getIdentAt(i).getName();
			int j;
			for (j = 0; j < rhsRank && !(rhsIndices.getIdentAt(j).getName().equals(lhs_index)); ++j) {/*
																									 * keep
																									 * looking
																									 * until
																									 * matching
																									 * index
																									 * found
																									 */
			}
			permutation[j] = i;
		}
		for (int i = lhsRank; i < max_rank; ++i) {
			// fill in unused dims with -1 to cause failure if accessed
			permutation[i] = -1;
		}
		// do sanity check on permutation
		int tmpsum = 0;
		int psum = 0;
		for (int i = 0; i < lhsRank; ++i) {
			tmpsum += i;
			psum += permutation[i];
		}
		assert tmpsum == psum : "Problem with calculation of permutation";
		// the number of indices is the same in both arrays, check to see if a
		// copy suffices

		for (int i = 0; i < lhsRank; ++i) {
			if (permutation[i] != i)
				return permutation;
		}
		// if here, then permutation was [0,1,2, lhsRank..] a copy will do
		return null;
	}

	public boolean visit(AssignToContigousDataBlock n) {
		return true;
	}

	public void endVisit(AssignToContigousDataBlock n) {
		// get the actual expression
		IExpression rhs = n.getExpression();
		while (rhs instanceof ParenExpr) {
			rhs = ((ParenExpr) rhs).getExpression();
		}

		// //Every binary operation with block result needs a destination block,
		// so there is nothing to do here.
		// //The expression will have already popped the slot and selector.
		// //The StarExpr also handles case of accumulate into lhs
		EnumSet<EType> type = ASTUtils.getIExprTypes(rhs);

		// Get lhs info
		int lhs_slot = operandStack.pop();
		int lhs_rank = arrayTable.getRank(lhs_slot);
		int op = n.getAssignOp().getop().getKind(); // should be one of
													// TK_ASSIGN,
													// TK_PLUS_ASSIGN,
													// TK_MINUS_ASSIGN,
													// TK_STAR_ASSIGN
		if (type.contains(SCALAR)) {
			opTable.addOptableEntry(block_fill_op, lhs_rank, lhs_slot, unused, defaultUnusedInd, lineno(n)); // gets
																												// value
																												// from
																												// expression
																												// stack,
																												// destination
																												// block
																												// selector
																												// from
																												// instruction
			return;
		}
		// just a plain old copy--type checking should have raised error message for other uses
		opTable.addOptableEntry(block_copy_op, lhs_rank, lhs_slot, unused, defaultUnusedInd, lineno(n));
		return;

	}

	@Override
	public boolean visit(AssertSame n) { /* visit child */
		return true;
	}

	@Override
	public void endVisit(AssertSame n) {
		int scalarSlot = operandStack.pop();
		opTable.addOptableEntry(assert_same_op, scalarSlot, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(AddExpr n) {
		return true;
	}

	@Override
	public void endVisit(AddExpr n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(INT)) {
			opTable.addOptableEntry(int_add_op, unused, unused, unused, defaultUnusedInd, lineno(n));
			return;
		}
		if (t.contains(SCALAR)) {
			opTable.addOptableEntry(scalar_add_op, unused, unused, unused, defaultUnusedInd, lineno(n));
			return;
		}
		if (t.contains(BLOCK)) { // visiting subexpressions has generated
									// push_block_selector_op instructions for
									// each argument
			// get the destination
			int lhs_slot = operandStack.pop();
			int lhs_rank = arrayTable.getRank(lhs_slot);
			int[] lhs_ind = indexArrayStack.pop();
			opTable.addOptableEntry(block_add_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
			return;
		}
		assert false : "unhandled type of AddExpr " + n;
	}

	@Override
	public boolean visit(SubtractExpr n) {
		return true;
	}

	@Override
	public void endVisit(SubtractExpr n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(INT)) {
			opTable.addOptableEntry(int_subtract_op, unused, unused, unused, defaultUnusedInd, lineno(n));
			return;
		}
		if (t.contains(SCALAR)) {
			opTable.addOptableEntry(scalar_subtract_op, unused, unused, unused, defaultUnusedInd, lineno(n));
			return;
		}
		if (t.contains(BLOCK)) {
			// get the destination
			int lhs_slot = operandStack.pop();
			int lhs_rank = arrayTable.getRank(lhs_slot);
			int[] lhs_ind = indexArrayStack.pop();
			opTable.addOptableEntry(block_subtract_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
			return;
		}
		assert false : "compiler bug:  unhandled type of SubtactExpr " + n;

	}

	@Override
	public boolean visit(StarExpr n) {
		return true;
	}

	@Override
	public void endVisit(StarExpr n) {
		EnumSet<EType> resultType = ASTUtils.getIExprTypes(n);
		EnumSet<EType> e0Type = ASTUtils.getIExprTypes(n.getTerm());
		EnumSet<EType> e1Type = ASTUtils.getIExprTypes(n.getExponentExpression());

		if (resultType.contains(INT)) { // evaluates e0 * e1 and leaves result
										// on sip control stack
			opTable.addOptableEntry(int_multiply_op, unused, unused, unused, defaultUnusedInd, lineno(n));
			return;
		}
		if (resultType.contains(SCALAR)) {
			if (e0Type.contains(SCALAR) && e1Type.contains(SCALAR)) { // evaluates
																		// e0 *
																		// e1
																		// and
																		// leaves
																		// result
																		// on
																		// sip
																		// expression
																		// stack
				opTable.addOptableEntry(scalar_multiply_op, unused, unused, unused, defaultUnusedInd, lineno(n));
				return;
			} else {// this is a contract to scalar. evaluates the contraction
					// and leaves result on sip expression stack
				opTable.addOptableEntry(block_contract_to_scalar_op, unused, unused, unused, defaultUnusedInd,
						lineno(n));
				return;
			}
		}
		if (resultType.contains(BLOCK)) {
			// get assignment operator in enclosing statement
			IAst parent = n.getParent();
			int op = ((AssignToBlock) parent).getAssignOp().getop().getKind();
			// get the destination
			int lhs_slot = operandStack.pop();
			int lhs_rank = arrayTable.getRank(lhs_slot);
			int[] lhs_ind = indexArrayStack.pop();
			if (op == TK_ASSIGN) {
				if (e0Type.contains(SCALAR) && !e0Type.contains(BLOCK) || e1Type.contains(SCALAR)
						&& !e1Type.contains(BLOCK)) {
					// this is multiply scalar times block and is not a
					// contraction
					opTable.addOptableEntry(block_scale_assign_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
				} else {
					opTable.addOptableEntry(block_contract_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
				}
			} else if (op == TK_PLUS_ASSIGN) {
				if (e0Type.contains(SCALAR) || e1Type.contains(SCALAR)) {
					opTable.addOptableEntry(block_scale_accumulate_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
				} else {
					opTable.addOptableEntry(block_contract_accumulate_op, lhs_rank, lhs_slot, unused, lhs_ind,
							lineno(n));
				}
			} else
				assert false : "compiler bug:  unexpected assigment operator for contraction " + n;
			return;
		}
		assert false : "compiler bug:  unhandled type of StarExpr " + n;
	}

	@Override
	public boolean visit(DivExpr n) {
		return true; // visit children. leaves values of operatnds on top of
						// control or expression stack in sip
	}

	@Override
	public void endVisit(DivExpr n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(INT)) {
			opTable.addOptableEntry(int_divide_op, unused, unused, unused, defaultUnusedInd, lineno(n));
			return;
		}
		if (t.contains(SCALAR)) {
			opTable.addOptableEntry(scalar_divide_op, unused, unused, unused, defaultUnusedInd, lineno(n));
			return;
		}
		assert false : "compiler bug:  unexpected type for div argument";
	}

	@Override
	public boolean visit(TensorExpr n) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void endVisit(TensorExpr n) { // This is exactly the same as
											// contraction, except that it only
											// applies to blocks.
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(BLOCK)) {
			// get assignment operator in enclosing statement
			IAst parent = n.getParent();
			int op = ((AssignToBlock) parent).getAssignOp().getop().getKind();
			// get the destination
			int lhs_slot = operandStack.pop();
			int lhs_rank = arrayTable.getRank(lhs_slot);
			int[] lhs_ind = indexArrayStack.pop();
			if (op == TK_ASSIGN) {
				opTable.addOptableEntry(block_contract_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
			} else if (op == TK_PLUS_ASSIGN) {
				opTable.addOptableEntry(block_contract_accumulate_op, lhs_rank, lhs_slot, unused, lhs_ind, lineno(n));
			} else
				assert false : "compiler bug:  unexpected assigment operator for contraction " + n;
			return;
		}

		assert false : "unhandled type of TensorExpr " + n;
	}

	public boolean visit(ExponentExpr n) { /* visit children */
		return true;
	}

	public void endVisit(ExponentExpr n) {
		opTable.addOptableEntry(scalar_exp_op, unused, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(IntCastExpr n) {
		return true; // visit children, leaves scalar value on top of expression
						// stack in sip
	}

	@Override
	public void endVisit(IntCastExpr n) {
		opTable.addOptableEntry(cast_to_int_op, unused, unused, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(ScalarCastExpr n) {
		return true; // visits children, leaves value on top of scontrol stack
						// in sip.
	}

	@Override
	public void endVisit(ScalarCastExpr n) {
		opTable.addOptableEntry(cast_to_scalar_op, unused, unused, unused, defaultUnusedInd, lineno(n));
	}

	// paren expressions simply fall through to the enclosed expression.
	@Override
	public boolean visit(ParenExpr n) {
		return true;
	}

	@Override
	public void endVisit(ParenExpr n) {
		/* nop */
	}

	@Override
	public boolean visit(IntLitExpr n) { // the value of int literals is stored
											// in the instruction.
		int value = Integer.valueOf(n.getINTLIT().toString());
		opTable.addOptableEntry(int_load_literal_op, value, unused, unused, defaultUndefInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(IntLitExpr n) {
		/* nop */
	}

	/**
	 * Generates code to load the value onto the expression stack. (DoubleLitArg
	 * is the case where it is treated like a block)
	 */
	@Override
	public boolean visit(DoubleLitExpr n) { // TODO refactor to put most of this
											// in the Scalar table

		double val = getDoubleVal(n.getDOUBLELIT());
		int nScalars = scalarTable.nScalars; // current number of scalars
		int scalarTableSlot = scalarTable.addDoubleLiteral(val); // searches the
																	// scalar
																	// table for
																	// this
																	// value. If
																	// it is
																	// already
																	// there, it
																	// returns
																	// the
																	// index, if
																	// not it
																	// adds it
																	// and
																	// returns
																	// the new
																	// index.
		int arrayTableSlot;
		if (scalarTable.nScalars > nScalars) {
			// this is a new value, add to array table
			arrayTableSlot = arrayTable.addScalarEntry(null, scalar_value_t, scalarTableSlot);
		} else
			arrayTableSlot = arrayTable.getIndexOfScalarEntry(scalarTableSlot);
		opTable.addOptableEntry(scalar_load_value_op, arrayTableSlot, unused, unused, defaultUnusedInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(DoubleLitExpr n) {
		/* nop */
	}

	/**
	 * Depending on the type, generates code to push value of ident expression
	 * on the sip's expression, control, or block selector stack
	 */
	@Override
	public boolean visit(IdentExpr n) {
		int slot;
		IDec dec = n.getDec();
		EnumSet<EType> t = ASTUtils.getIExprTypes(n);
		if (t.contains(INDEX)) { // t also contains INT, so make sure to s
			slot = indexTable.getIndex(dec);
			opTable.addOptableEntry(index_load_value_op, slot, unused, unused, defaultUnusedInd, lineno(n));
			return false;
		}
		if (t.contains(INT)) {
			slot = intTable.getIntIndex((IntDec) dec);
			opTable.addOptableEntry(int_load_value_op, slot, unused, unused, defaultUnusedInd, lineno(n));
			return false;
		}
		if (t.contains(SCALAR)) {
			slot = arrayTable.getIndex(dec);
			opTable.addOptableEntry(scalar_load_value_op, slot, unused, unused, defaultUnusedInd, lineno(n));
			return false;
		}
		if (t.contains(ARRAY)) {
			slot = arrayTable.getIndex(dec);
			opTable.addOptableEntry(push_block_selector_op, /* rank= */0, slot, unused, defaultUnusedInd, lineno(n));
			return false;
		}
		assert false : "compiler bug:  unhandled ident";
		return false;
	}


	@Override
	public void endVisit(IdentExpr n) {
		/* nop */
	}

	@Override
	public boolean visit(DataBlockExpr n) {// contains a datablock, so visiting
											// children add arrayslot and
											// indices to stacks
		return true;
	}

	@Override
	public void endVisit(DataBlockExpr n) {
		int arraySlot = operandStack.pop();
		int rank = arrayTable.getRank(arraySlot);
		int[] ind = indexArrayStack.pop();
		opTable.addOptableEntry(push_block_selector_op, rank, arraySlot, unused, ind, lineno(n));
		// if the type contains a scalar (which will be the case if all indices
		// of the block ar simple), push the scalar value onto the stack
		EnumSet<EType> types = ASTUtils.getIExprTypes(n);
		if (types.contains(SCALAR)) {
			opTable.addOptableEntry(block_load_scalar_op, unused, unused, unused, defaultUnusedInd, lineno(n)); // code
																												// to
																												// leave
																												// scalar
																												// value
																												// on
																												// stack
		}
	}

	@Override
	public boolean visit(ContiguousDataBlockExpr n) {// contains a
														// ContiguousDataBlock,
		// and generates code to leave the index range value on top of the sip
		// control stack
		// so visiting childres adds the array slot to the stack
		return true;
	}

	@Override
	public void endVisit(ContiguousDataBlockExpr n) {
		int arraySlot = operandStack.pop();
		int rank = arrayTable.getRank(arraySlot);
		opTable.addOptableEntry(push_block_selector_op, rank, arraySlot, unused, defaultUnusedInd, lineno(n));
	}

	@Override
	public boolean visit(StringLitExpr n) { /*
											 * generates string_load_literal
											 * instruction
											 */
		String s = ASTUtils.getStringVal(n);
		int stringTableSlot = stringLiteralTable.getAndAdd(s);
		opTable.addOptableEntry(string_load_literal_op, stringTableSlot, unused, unused, defaultUnusedInd, lineno(n));
		return false;
	}

	@Override
	public void endVisit(StringLitExpr n) { /* nop */

	}


}
