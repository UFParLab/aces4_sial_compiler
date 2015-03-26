package sial.parser.context;

import static sial.parser.context.ASTUtils.getEnclosingProc;
import static sial.parser.context.ASTUtils.getIntVal;
import static sial.parser.context.ASTUtils.isPredefined;
import static sial.parser.context.ASTUtils.isStaticOrContiguousArray;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import lpg.runtime.IAst;
import lpg.runtime.IAstVisitor;
import lpg.runtime.IToken;
import sial.code_gen.SipConstants;
import sial.parser.SialParser;
import sial.parser.SialParsersym;
import sial.parser.Ast.*;
import sial.parser.context.ExpressionType.EType;
import static sial.parser.context.ExpressionType.EType.*;
import static sial.parser.context.ASTUtils.*;

/** Performs type checking on SIAL programs */
public class TypeCheckVisitor extends AbstractVisitor implements SialParsersym, SipConstants {

	SymbolTable symbolTable;
	SialParser parser;
	Sial root; // the AST

	public static Set<String> specials = new HashSet<String>();

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	public SialParser getParser() {
		return parser;
	}

	public boolean isSymbolTablePopulated() {
		return symbolTable._symbolTablePopulated;
	}

	// public List<IDec> constants = new ArrayList<IDec>();

	public TypeCheckVisitor(SialParser parser) {
		this.parser = parser;
	}

	/**
	 * Finds and returns the declaration of the given Ident, and sets the
	 * Ident's dec field. If the ident is unqualified and defined in multiple
	 * imported files, this will throw an AmbiguousNameException.
	 * 
	 * @param s
	 *            the Ident to look up
	 * @return the IDec representing the declaration of s
	 * @throws AmbiguousNameException
	 */
	private IDec findAndSetDec(Ident s) throws AmbiguousNameException {
		assert s != null : "findDec with null argument";
		String name = s.getName();
		IDec dec = symbolTable.lookup(name);
		s.setDec(dec);
		return dec;
	}

	/** same as the version that takes an Ident */
	private IDec findAndSetDec(IdentArg s) throws AmbiguousNameException {
		assert s != null : "findDec with null argument";
		String name = s.getName();
		IDec dec = symbolTable.lookup(name);
		s.setDec(dec);
		return dec;
	}

	private IDec findAndSetDec(IdentExpr s) throws AmbiguousNameException {
		assert s != null : "findDec with null argument";
		String name = s.getName();
		IDec dec = symbolTable.lookup(name);
		s.setDec(dec);
		return dec;
	}

	/**
	 * Finds and returns the declaration of the given AllocIndexIdent and sets
	 * its dec field. If the ident is unqualified and defined in multiple
	 * imported files, this will throw an AmbiguousNameException.
	 * 
	 * @param s
	 *            the IdentExpr to look up
	 * @return the IDec representing the declaration of s
	 * @throws AmbiguousNameException
	 */
	private IDec findAndSetDec(AllocIndexIdent s) {
		assert s != null : "findDec with null argument";
		try {
			String name = s.getName();
			IDec dec = symbolTable.lookup(name);
			s.setDec(dec);
			return dec;
		} catch (AmbiguousNameException e) {
			emitError(s, e.getMessage());
			return null;
		}
	}

	/**
	 * Finds and returns the declaration of the given IdentRangeVal and sets its
	 * dec field. If the ident is unqualified and defined in multiple imported
	 * files, this will give an error.
	 * 
	 * @param s
	 *            the IdentExpr to look up
	 * @return the IDec representing the declaration of s
	 * @throws AmbiguousNameException
	 */
	private IDec findAndSetDec(IdentRangeVal s) {
		try {
			String name = s.getName();
			IDec dec = symbolTable.lookup(name);
			s.setDec(dec);
			return dec;
		} catch (AmbiguousNameException e) {
			emitError(s, e.getMessage());
			return null;
		}
	}

	/**
	 * Reports errors
	 * 
	 * @param node
	 *            the AST node involved in the error
	 * @param message
	 *            the error message to be reported
	 */
	public void emitError(ASTNode node, String message) {
		parser.emitError(node, message);
	}

	public void emitWarning(ASTNode node, String message) {
		parser.emitWarning(node, message);
	}

	/**
	 * Convenience function for managing error checks. If the condition is
	 * false, an error message is printed. For the most part, the typing rules
	 * of the language are embedded in calls to check statements.
	 * 
	 * @param condition
	 *            The condition to evaluate
	 * @param n
	 *            The current ASTNode
	 * @param msg
	 *            The Error message to report
	 * @return whether the condition holds or not
	 */
	boolean check(boolean condition, ASTNode n, String msg) {
		if (condition)
			return true;
		emitError(n, msg);
		return false;
	}

	boolean checkAndWarn(boolean condition, ASTNode n, String msg) {
		if (condition)
			return true;
		emitWarning(n, msg);
		return false;
	}

	@Override
	public boolean visit(Sial n) {
		root = n;
		symbolTable = root.getSymbolTable();
		return true;
	}

	@Override
	public void endVisit(Sial n) {
		symbolTable.set_symbolTablePopulated(true);
		checkCallSites();
		checkBarriersInPardo(n, symbolTable);

	}

	// constructs a visitor that traverses the AST and checks that no
	// sip_barrier or pardo is enclosed in a pardo loop.
	// If the sip_barrier or pardo is in a procedure, then no call site of that
	// procedure can be enclosed in a pardo loop.
	// Since procedures may be enclosed in other procedures, multiple traversals
	// may be required.
	private void checkBarriersInPardo(Sial n, SymbolTable symbolTable2) {
		HashMap<ProcDec, IStatement> procs = new HashMap<ProcDec, IStatement>();
		int bound = 50; // just to prevent any chance of infinite loop
		int count = 0;
		CheckEnclosingPardoVisitor visitor;
		do {
			visitor = new CheckEnclosingPardoVisitor(symbolTable, procs);
			// pass in procs, the set of barrier containing procedures found in
			// a previous iteration.
			// The first time, it is empty
			n.accept(visitor);
			count++;
		} while (visitor.modified() && count < bound); // Either a fixed point
		// Either a fixed point (closure) of procedures calling procedure that
		// contain a pardo) was reached or the bound was exceeded, which
		// probably
		// indicates a bug.
		assert (count < bound) : "Barrier check required more than " + bound + " iterations";
		if (visitor.hasError()) {
			Iterator<Entry<IStatement, PardoStatement>> iter = visitor.getErrorIterator();
			while (iter.hasNext()) {
				Entry<IStatement, PardoStatement> entry = iter.next();
				ASTNode sourceStatement = (ASTNode) entry.getKey();
				check(false, sourceStatement, (sourceStatement instanceof SipBarrierStatement ? "sip_barrier "
						: "pardo ")
						+ " at line "
						+ sourceStatement.getLeftIToken().getLine()
						+ " is enclosed in pardo statement at line " + entry.getValue().getLeftIToken().getLine());
			}
		}

	}

	// dummy class to allow putting the program name in the symbol table.
	static class ProgramDec implements IDec {
		Program prog;

		public String getName() {
			return prog.getStartName();
		}

		public ProgramDec(Program n) {
			prog = n;
		}

		@Override
		public IToken getLeftIToken() {
			return prog.getLeftIToken();
		}

		@Override
		public IToken getRightIToken() {
			return prog.getRightIToken();
		}

		@Override
		public void accept(IAstVisitor v) {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public boolean visit(Program n) {
		String name = n.getStartName();
		check(symbolTable.insert(name, new ProgramDec(n)), n, "program name already used");
		return true;
	}

	@Override
	public void endVisit(Program n) {
		String name = n.getStartName();
		String endName = n.getendName().getName();
		check(name.equals(endName), n, " mismatched names at beginning and end of program");
	}

	@Override
	public boolean visit(ImportProgList n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(ImportProgList n) { /* nop */
	}

	@Override
	public boolean visit(ImportProg n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(ImportProg n) { /* nop */
	}

	@Override
	public boolean visit(DecList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DecList n) { /* nop */
	}

	/* scalar declaration */
	@Override
	public boolean visit(ScalarDec n) {
		Ident id = n.getIdent();
		String name = n.getName();
		check(symbolTable.insert(name, n), id, "Duplicate declaration of " + name);
		return true;
	}

	@Override
	public void endVisit(ScalarDec n) { /* nop */
	}

	/* int declaration */
	@Override
	public boolean visit(IntDec n) {
		// check(isPredefined(n), n,
		// "only constant, predefined ints currently implemented");
		Ident id = n.getIdent();
		String name = n.getName();
		check(symbolTable.insert(name, n), id, "Duplicate declaration of " + name);
		// constants.add(n); // add to constant list //TODO Check THIS!!!!
		return true;
	}

	@Override
	public void endVisit(IntDec n) { /* nop */
	}

	/* array declaration */
	@Override
	public boolean visit(ArrayDec n) {
		// check(!isConstant(n), n, "Constant arrays are not implemented");
		Ident id = n.getIdent();
		String name = n.getName();
		check(symbolTable.insert(name, n), id, "Duplicate declaration of " + name);
		return true;
	}

	@Override
	public void endVisit(ArrayDec n) {
		n.setUsed(false);
	}

	@Override
	public boolean visit(ArrayKind n) {
		return false;
	}

	@Override
	public void endVisit(ArrayKind n) { /* nop */
	}

	@Override
	public boolean visit(DimensionList n) {
		for (int i = 0; i < n.size(); i++) {
			Ident ident = n.getDimensionAt(i);
			try {
				IDec dec = findAndSetDec(ident);
				check((dec instanceof IndexDec || dec instanceof SubIndexDec), ident, ident
						+ " must be previously declared index or subindex");
			} catch (AmbiguousNameException e) {
				emitError(ident, e.getMessage());
			}
		}
		return false;
	}

	@Override
	public void endVisit(DimensionList n) { /* nop */
	}

	/* index and subindex declarations */
	@Override
	public boolean visit(IndexDec n) {
		// check(!isConstant(n), n, "index cannot be constant");
		Ident id = n.getIdent();
		String name = n.getName();
		check(symbolTable.insert(name, n), id, "Duplicate declaration of " + name);
		return true;
	}

	@Override
	public void endVisit(IndexDec n) { /* nop */
	}

	@Override
	public boolean visit(IndexKind n) {
		return false;
	}

	@Override
	public void endVisit(IndexKind n) { /* nop */
	}

	@Override
	public boolean visit(SubIndexDec n) {
		// insert name of new subindex
		Ident id = n.getIdent();
		String name = n.getName();
		if (!check(symbolTable.insert(name, n), id, "Duplicate declaration of " + name))
			return false;
		// check that parent index is 1) already declared and 2) is an index
		// type
		Ident parentId = n.getParentIdent();
		String parentName = n.getParentName();
		IDec parentNode;
		try {
			parentNode = findAndSetDec(parentId);

			check(parentNode instanceof IndexDec, parentId, parentName + " must be declared as an index type");
		} catch (AmbiguousNameException e) {
			assert false : "this shouldn't happen";
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void endVisit(SubIndexDec n) { /* nop */
	}

	@Override
	public boolean visit(Range n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(Range n) {
		check(AcesRangeChecks.isNotInvalid(n), n, "invalid range");
	}

	@Override
	public boolean visit(IntLitRangeVal n) {
		IndexDec parent = (IndexDec) n.getParent().getParent();
		check(getIntVal(n) >= 1 || (parent.getIndexKind().getikind().getKind() == TK_index), n,
				"index range must be at least 1 (unless index is simple)");
		return false;
	}

	@Override
	public void endVisit(IntLitRangeVal n) { /* nop */
	}

	@Override
	public boolean visit(NegRangeVal n) {
		IndexDec parent = (IndexDec) n.getParent().getParent();
		check(parent.getIndexKind().getikind().getKind() == TK_index, n,
				"negative range only allowed for simple indices");
		return false;
	}

	@Override
	public void endVisit(NegRangeVal n) { /* nop */
	}

	@Override
	public boolean visit(IdentRangeVal n) {
		IDec dec = findAndSetDec(n);
		check(dec instanceof IntDec, n, n.getName() + " not declared");
		check(isPredefined(dec), n, n.getName() + " must be predefined");
		return false;
	}

	@Override
	public void endVisit(IdentRangeVal n) { /* nop */
	}

	@Override
	public boolean visit(ProcDec n) {
		String name = n.getName();
		check(symbolTable.insert(name, n), n.getIdent(), "Duplicate declaration of " + name);
		check(getEnclosingProc(n) == null, n, "nested procedures are not allowed");
		// visit children
		return true;
	}

	@Override
	public void endVisit(ProcDec n) {
		String startName = n.getName();
		String endName = n.getendIdent().getName();
		check(startName.equals(endName), n, "mismatched names at beginning and end of procedure");
	}

	// special instructions can only be declared once. No qualification is
	// possible
	@Override
	public boolean visit(SpecialDec n) {
		String name = n.getName();
		check(symbolTable.insert(name, n), n, "Duplicate declaration of " + name);
		Ident signature = n.getSignature();
		if (signature == null) {
			n.setNumArgs(0);
		} else {
			String args = signature.getName(); // lower case version of string
												// that was given as signature
			int numArgs = args.length();
			for (int i = 0; i < numArgs; ++i) {
				char intent_val = args.charAt(i);
				switch (intent_val) {
				case 'r':
				case 'w':
				case 'u':
					break; // there are the legal options
				default:
					check(false, n, "Illegal character " + intent_val + " in special instruction signature descriptor");
				}
			}
			n.setNumArgs(numArgs);
		}
		return false; // don't visit children
	}

	@Override
	public void endVisit(SpecialDec n) { /* nop */
	}

	@Override
	public boolean visit(StatementList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(StatementList n) { /* nop */
	}

	@Override
	public boolean visit(WhereClause n) { /* visit children */
		return true;
	}

	@Override
	// FIXME use inferred expression types
	public void endVisit(WhereClause n) { /*
										 * At least one argument must be an
										 * index. Together with type checking of
										 * relational statement, this implies
										 * the other is an int
										 */
		RelationalExpression e = n.getRelationalExpression();
		IExpression eleft = e.getUnaryExpressionLeft();
		IExpression eright = e.getUnaryExpressionRight();
		boolean eLeftIsIndex = eleft instanceof IdentExpr
				&& (((IdentExpr) eleft).getDec() instanceof IndexDec || ((IdentExpr) eleft).getDec() instanceof SubIndexDec);
		boolean eRightIsIndex = eright instanceof IdentExpr
				&& (((IdentExpr) eright).getDec() instanceof IndexDec || ((IdentExpr) eright).getDec() instanceof SubIndexDec);
		check((eLeftIsIndex || eRightIsIndex), n, "At least one argument in a where clause must be an index");
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
		IDec dec = n.getIdent().getDec();
		check(dec instanceof ProcDec, n, n.getIdent().toString() + " not declared as proc");
		((ProcDec) dec).getCallSites().add(n);
		// check(!inpardo || !procsContainingPardo.contains(dec), n,
		// "Procedure containing a pardo is being called from inside a pardo");
	}

	@Override
	public boolean visit(ReturnStatement n) { /* nothing to check */
		return false;
	}

	@Override
	public void endVisit(ReturnStatement n) { /* nop */
	}

	@Override
	public boolean visit(ServerBarrierStatement n) {
		// check(false, n,
		// "Server_barrier no longer supported.  Use sip_barrier instead ");
		return false;
	}

	@Override
	public void endVisit(ServerBarrierStatement n) { /* nop */
	}

	@Override
	public boolean visit(SipBarrierStatement n) {
		return false;
	}

	@Override
	public void endVisit(SipBarrierStatement n) { /* nop */
	}

	@Override
	public boolean visit(DoStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DoStatement n) {
		Ident identTop = n.getStartIndex();
		Ident identEnd = n.getEndIndex();
		check(identTop.getName().equals(identEnd.getName()), identEnd,
				"unmatched indices at beginning and end of do loop");
		IDec dec = identTop.getDec();
		check(dec instanceof IndexDec, n, identTop.toString() + " is not an index");
		return;
	}

	@Override
	public boolean visit(DoStatementSubIndex n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DoStatementSubIndex n) {
		Ident startIndexIdent = n.getStartIndex();
		Ident startParentIndexIdent = n.getStartParentIndex();
		String startIndexName = startIndexIdent.getName();
		String startParentIndexName = startParentIndexIdent.getName();
		String endIndexName = n.getEndIndex().getName();
		String endParentIndexName = n.getEndParentIndex().getName();
		// check that names match
		check(startIndexName.equals(endIndexName), startIndexIdent,
				"mismatched subindex names at start and end of do loop");
		check(startParentIndexName.equals(endParentIndexName), startParentIndexIdent,
				"mismatched parent index names at start and end of do loop");
		// check types
		// 1. index must be a subindex
		// 2. parent index must be an index, and must match type of parent index
		// in declaration
		IDec dec = startIndexIdent.getDec();
		if (check(dec instanceof SubIndexDec, startIndexIdent, "do loop sub index must be declared as subindex type")) {
			SubIndexDec subIndexDec = (SubIndexDec) dec;
			IDec parentIndexDecFromDec = subIndexDec.getParentIdent().getDec();
			IDec parentIndexDecFromUse = startParentIndexIdent.getDec();
			check(parentIndexDecFromDec.equals(parentIndexDecFromUse), startParentIndexIdent,
					"do loop parent index type must match type of declared parent index");
		}
	}

	// boolean inpardo = false;
	Set<ProcDec> procsContainingPardo = new HashSet<ProcDec>();

	@Override
	public boolean visit(PardoStatement n) {
		// check(!inpardo, n, "pardo nested in another pardo");
		// inpardo = true;
		procsContainingPardo.add(getEnclosingProc(n)); // adds null if not in
														// proc
		IdentList startIndices = n.getStartIndices();
		check(startIndices != null && startIndices.size() > 0, n, "missing indices in pardo statement");
		/* visit children */
		return true;
	}

	@Override
	public void endVisit(PardoStatement n) {
		IdentList startIdents = n.getStartIndices();
		IdentList endIdents = n.getEndIndices();
		// inpardo = false;
		if (!check(startIdents.size() == endIdents.size(), n, "unmatched indices at beginning and end of pardo"))
			return;
		for (int i = 0; i < n.getStartIndices().size(); i++) {
			if (!check(startIdents.getIdentAt(i).getName().equals(endIdents.getIdentAt(i).getName()), n,
					"unmatched indices at beginning and end of pardo"))
				return;
		}
	}

	// TODO add check that pardos are independent
	@Override
	public boolean visit(Section n) {
		// all statements must be pardo statements
		List statements = n.getStatementList().getList();
		for (Object s : statements) {
			check((s instanceof PardoStatement), (ASTNode) s, "illegal statement in section, must be a pardo");
		}
		return true;
	}

	// @Override
	// public void endVisit(Section n){
	// IDec dec = findAndSetDec(n);
	// check(dec != null,
	// n,
	// "current implementation requires declaration of special aces_hacks_server_barrier");
	// }

	@Override
	public boolean visit(ExitStatement n) { /* nothing to check */
		return false;
	}

	@Override
	public void endVisit(ExitStatement n) { /* nop */
	}

	@Override
	public boolean visit(IfStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(IfStatement n) { /* nop */
	}

	@Override
	public boolean visit(IfElseStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(IfElseStatement n) { /* nop */
	}

	@Override
	public boolean visit(AllocateStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(AllocateStatement n) {
		// check array name
		IDec dec = n.getIdent().getDec();
		if (!(check(dec instanceof ArrayDec && ((ArrayDec) dec).getTypeName().equals("local"), n, n.getIdent()
				.toString() + "must be declared as local array"))) {
			return;
		}
		// check indices
		ArrayDec arrayDec = (ArrayDec) dec;
		// arrayDec.setUsed();
		DimensionList decDims = arrayDec.getDimensionList();
		if (n.getAllocIndexListopt() == null)
			return; // no indices given
		AllocIndexList allocDims = n.getAllocIndexListopt().getAllocIndexList();
		if (!check(decDims.size() == allocDims.size(), n,
				"index list in allocate statement incompatible with declaration of " + n.getIdent()))
			return;
		// check that declared type and actual type of non-wildcard indices
		// match
		for (int i = 0; i < decDims.size(); i++) {
			IAllocIndex index = allocDims.getAllocIndexAt(i);
			if (index instanceof AllocIndexWildCard)
				continue;
			// not a wildcard--check type compatibility
			IDec indexDec = ((AllocIndexIdent) index).getDec();
			// The dimension list has been visited already, ensuring that
			// indexDec is an IndexDec or SubIndexDec
			IDec declaredIDec = decDims.getDimensionAt(i).getDec();
			if (indexDec instanceof IndexDec) {
				check(declaredIDec instanceof IndexDec, (AllocIndexIdent) index,
						"index in allocate statement incompatible with declaration of array");
				String indexType = ((IndexDec) indexDec).getTypeName();

				IndexDec declaredDec = (IndexDec) declaredIDec;
				String declaredIndexType = declaredDec.getTypeName();
				check(indexType.equals(declaredIndexType), (AllocIndexIdent) index,
						"index in allocate statement incompatible with declaration of array");
				return;
			}
			// indexDec is a SubIndex, parent declarations must be the same
			// This rule may be too strict.
			check(declaredIDec instanceof SubIndexDec, (AllocIndexIdent) index,
					"index in allocate statement incompatible with declaration of array");
			String indexParent = ((SubIndexDec) indexDec).getParentName();
			SubIndexDec declaredDec = (SubIndexDec) declaredIDec;
			String declaredIndexParent = declaredDec.getParentName();
			check(indexParent.equals(declaredIndexParent), (AllocIndexIdent) index,
					"index in allocate statement incompatible with declaration of array; superindices must match");
			return;
		}

	}

	@Override
	public boolean visit(DeallocateStatement n) { /* visit children */
		return true;
	}

	//
	@Override
	public void endVisit(DeallocateStatement n) { // check array name
		IDec dec = n.getIdent().getDec();
		if (!(check(dec instanceof ArrayDec && ((ArrayDec) dec).getTypeName().equals("local"), n, n.getIdent()
				.toString() + "must be declared as local array"))) {
			return;
		}
		// check indices
		ArrayDec arrayDec = (ArrayDec) dec;
		DimensionList decDims = arrayDec.getDimensionList();
		if (n.getAllocIndexListopt() == null)
			return; // no indices given
		AllocIndexList allocDims = n.getAllocIndexListopt().getAllocIndexList();
		if (!check(decDims.size() == allocDims.size(), n,
				"index list in allocate statement incompatible with declaration of " + n.getIdent()))
			return;
		// check that declared type and actual type of non-wildcard indices
		// match
		for (int i = 0; i < decDims.size(); i++) {
			IAllocIndex index = allocDims.getAllocIndexAt(i);
			if (index instanceof AllocIndexWildCard)
				continue;
			// not a wildcard--check type compatibility
			IDec indexDec = ((AllocIndexIdent) index).getDec();
			// The dimension list has been visited already, ensuring that
			// indexDec is an IndexDec or SubIndexDec
			IDec declaredIDec = decDims.getDimensionAt(i).getDec();
			if (indexDec instanceof IndexDec) {
				check(declaredIDec instanceof IndexDec, (AllocIndexIdent) index,
						"index in allocate statement incompatible with declaration of array");
				String indexType = ((IndexDec) indexDec).getTypeName();

				IndexDec declaredDec = (IndexDec) declaredIDec;
				String declaredIndexType = declaredDec.getTypeName();
				check(indexType.equals(declaredIndexType), (AllocIndexIdent) index,
						"index in allocate statement incompatible with declaration of array");
				return;
			}
			// indexDec is a SubIndex, parent declarations must be the same
			// This rule may be too strict.
			check(declaredIDec instanceof SubIndexDec, (AllocIndexIdent) index,
					"index in allocate statement incompatible with declaration of array");
			String indexParent = ((SubIndexDec) indexDec).getParentName();
			SubIndexDec declaredDec = (SubIndexDec) declaredIDec;
			String declaredIndexParent = declaredDec.getParentName();
			check(indexParent.equals(declaredIndexParent), (AllocIndexIdent) index,
					"index in allocate statement incompatible with declaration of array; superindices must match");
			return;
		}
	}

	@Override
	public boolean visit(CreateStatement n) {/* visit children */
		return true;
	}

	@Override
	public void endVisit(CreateStatement n) {
		// check array name
		IDec dec = n.getIdent().getDec();
		check(dec instanceof ArrayDec && ((ArrayDec) dec).getTypeName().equals("distributed"), n, n.getIdent()
				.toString() + " must be declared as distributed array");
		// ((ArrayDec)dec).setUsed();
		check(!isInPardo(n), n, n + " is inside pardo.");
	}

	@Override
	public boolean visit(DeleteStatement n) { /* visit children */
		return true;
	}

	// this was copied from endVisit(CreateStatement n)
	@Override
	public void endVisit(DeleteStatement n) {
		// check array name
		IDec dec = n.getIdent().getDec();
		check(dec instanceof ArrayDec && ((ArrayDec) dec).getTypeName().equals("distributed"), n, n.getIdent()
				.toString() + " must be declared as distributed array");
		// if (!(check(dec instanceof ArrayDec && ((ArrayDec)
		// dec).getTypeName().equals("distributed"), n, n.getIdent()
		// .toString() + "must be declared as distributed array"))) {
		// return;
		// }
		// // check indices
		// ArrayDec arrayDec = (ArrayDec) dec;
		// DimensionList decDims = arrayDec.getDimensionList();
		// if (n.getAllocIndexListopt() == null)
		// return; // no indices given
		// AllocIndexList allocDims =
		// n.getAllocIndexListopt().getAllocIndexList();
		// if (!check(decDims.size() == allocDims.size(), n,
		// "index list in allocate statement incompatible with declaration of "
		// + n.getIdent()))
		// return;
		// // check that declared type and actual type of non-wildcard indices
		// // match
		// for (int i = 0; i < decDims.size(); i++) {
		// IAllocIndex index = allocDims.getAllocIndexAt(i);
		// if (index instanceof AllocIndexWildCard)
		// continue;
		// // not a wildcard--check type compatibility
		// IDec indexDec = ((AllocIndexIdent) index).getDec();
		// // The dimension list has been visited already, ensuring that
		// // indexDec is an IndexDec or SubIndexDec
		// IDec declaredIDec = decDims.getDimensionAt(i).getDec();
		// if (indexDec instanceof IndexDec) {
		// check(declaredIDec instanceof IndexDec, (AllocIndexIdent) index,
		// "index in allocate statement incompatible with declaration of array");
		// String indexType = ((IndexDec) indexDec).getTypeName();
		//
		// IndexDec declaredDec = (IndexDec) declaredIDec;
		// String declaredIndexType = declaredDec.getTypeName();
		// check(indexType.equals(declaredIndexType), (AllocIndexIdent) index,
		// "index in allocate statement incompatible with declaration of array");
		// return;
		// }
		// // indexDec is a SubIndex, parent declarations must be the same
		// // This rule may be too strict.
		// check(declaredIDec instanceof SubIndexDec, (AllocIndexIdent) index,
		// "index in allocate statement incompatible with declaration of array");
		// String indexParent = ((SubIndexDec) indexDec).getParentName();
		// SubIndexDec declaredDec = (SubIndexDec) declaredIDec;
		// String declaredIndexParent = declaredDec.getParentName();
		// check(indexParent.equals(declaredIndexParent), (AllocIndexIdent)
		// index,
		// "index in allocate statement incompatible with declaration of array; superindices must match");
		// return;
		// }
	}

	@Override
	public boolean visit(PutStatement n) {
		return true;
	}

	@Override
	public void endVisit(PutStatement n) {

		// handle left hand side
		DataBlock lhs = n.getLHSDataBlock();
		// check that lhs is a distributed array
		IDec dec = lhs.getIdent().getDec();
		if (!(check(dec instanceof ArrayDec && ((ArrayDec) dec).getTypeName().equals("distributed"), n, lhs.getIdent()
				.toString() + "must be a distributed array"))) {
			return;
		}
		// check that given indices are compatible with declaration
		check(hasCompatibleIndices(lhs), n, "improper indices on lhs of put");

		// handle right hand side
		IExpression expr = n.getExpression();
		EnumSet<EType> t = ASTUtils.getIExprTypes(expr);
		if (t.isEmpty())
			return; // error already reported by child
		if (!check(t.contains(SCALAR) || t.contains(BLOCK), n,
				"right hand side of put must be distribted block or scalar"))
			return;
		int op = n.getAssignOp().getop().getKind();
		if (t.contains(SCALAR)){
			check(op == TK_ASSIGN || op == TK_PLUS_ASSIGN || (op == TK_STAR_ASSIGN), n, "illegal operator;  only = , +=, and *= are supported");			
			return;
		}
		// rhs is a block, check that indices are compatible
		if (expr instanceof DataBlockExpr) { // rhs is a single data block
			check(op == TK_ASSIGN || op == TK_PLUS_ASSIGN , n, "illegal operator;  only = and += are supported");
			DataBlock rhs = ((DataBlockExpr) expr).getDataBlock();
			check(hasCompatibleIndices(rhs), n, "improper indices on rhs of put");
			checkCompatibleBlocks(n, lhs, rhs);
			return;
		}
	}

	/**
	 * indices on both sides match exactly, or are a permutation. Returns an
	 * error message or null if no error
	 */
	private String isCompatibleBockWithTranspose(DataBlock lhs, DataBlock rhs) {
		IdentList lhsIndices = lhs.getIndices();
		IdentList rhsIndices = rhs.getIndices();
		int lhsSize = lhsIndices.size();
		int rhsSize = rhsIndices.size();
		if (lhsSize == rhsSize) {// must be a permutation
			for (int i = 0; i < lhsSize; i++) {
				if (!contains(rhsIndices, lhsIndices.getIdentAt(i)))
					return "incompatible index lists";
			}
			return null;
		}
		// otherwise one is longer than the other--extras must be simple indices
		// first check that common positions have the same contents
		int i;
		for (i = 0; i < lhsSize && i < rhsSize; i++) {
			if (!lhsIndices.getIdentAt(i).getName().equals(rhsIndices.getIdentAt(i).getName()))
				return "inconsistent index lists";

		}
		// if there are more on the lhs, make sure they are simple
		for (; i < lhsSize; i++) {
			if (!((IndexDec) lhsIndices.getIdentAt(i).getDec()).getTypeName().equals("index"))
				return "extra indices on left side must be simple indices, i.e. the index type must be \"index\"";
		}
		// if there are more on the rhs, make sure they are simple
		for (; i < rhsSize; i++) {
			if (!((IndexDec) rhsIndices.getIdentAt(i).getDec()).getTypeName().equals("index"))
				return "extra indices on right side must be simple indices, i.e. the index type must be \"index\"";
		}
		return null;
	}

	private void checkCompatibleBlocks(ASTNode n, DataBlock lhs, DataBlock rhs) {
		// check that indices on both sides match exactly, or that there is an
		// extra index which is a simple index
		IdentList lhsIndices = lhs.getIndices();
		IdentList rhsIndices = rhs.getIndices();
		int lhsSize = lhsIndices.size();
		int rhsSize = rhsIndices.size();
		int minIndices = lhsSize < rhsSize ? lhsSize : rhsSize;
		for (int i = 0; i < minIndices; i++) { // check that common "positions"
												// have the same contents
			if (!check(lhsIndices.getIdentAt(i).getName().equals(rhsIndices.getIdentAt(i).getName()), n,
					"inconsistent index lists"))
				return;
		}
		for (int i = minIndices; i < lhsSize; i++) { // if there are more
														// indices on the lhs,
														// make sure they are
														// simple
			if (!check(((IndexDec) lhsIndices.getIdentAt(i).getDec()).getTypeName().equals("index"), n,
					"extra indices on left side must be simple indices, i.e. the index type must be \"index\""))
				;
		}
		for (int i = minIndices; i < rhsSize; i++) { // if there are more
														// indices on the rhs,
														// make sure they are
														// simple
			if (!check(((IndexDec) rhsIndices.getIdentAt(i).getDec()).getTypeName().equals("index"), n,
					"extra indices on right side must be simple indices, i.e. the index type must be \"index\""))
				;
		}
	}

	@Override
	public boolean visit(GetStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GetStatement n) {
		IDec dec = n.getDataBlock().getIdent().getDec();
		if (!(check(dec instanceof ArrayDec && ((ArrayDec) dec).getTypeName().equals("distributed"), n, n
				.getDataBlock().getIdent().toString()
				+ "must be a distributed array"))) {
			return;
		}
	}

	@Override
	public boolean visit(PrepareStatement n) {
		return true;
	}

	@Override
	public void endVisit(PrepareStatement n) {
		// handle left hand side
		DataBlock lhs = n.getLHSDataBlock();
		// check that lhs is a distributed array
		IDec dec = lhs.getIdent().getDec();
		if (!(check(dec instanceof ArrayDec && ((ArrayDec) dec).getTypeName().equals("served"), n, lhs.getIdent()
				.toString() + "must be a served array"))) {
			return;
		}
		// check that given indices are compatible with declaration
		check(hasCompatibleIndices(lhs), n, "improper indices on lhs of put");

		// handle right hand side
		IExpression expr = n.getExpression();
		EnumSet<EType> t = ASTUtils.getIExprTypes(expr);
		if (t.isEmpty())
			return; // error already reported by child
		if (!check(t.contains(SCALAR) || t.contains(BLOCK), n,
				"right hand side of put must be distribted block or scalar"))
			return;
		int op = n.getAssignOp().getop().getKind();
		if (t.contains(SCALAR)){
			check(op == TK_ASSIGN || op == TK_PLUS_ASSIGN || (op == TK_STAR_ASSIGN), n, "illegal operator;  only = , +=, and *= are supported");			
			return;
		}
		// rhs is a block, check that indices are compatible
		if (expr instanceof DataBlockExpr) { // rhs is a single data block
			check(op == TK_ASSIGN || op == TK_PLUS_ASSIGN , n, "illegal operator;  only = and += are supported");
			DataBlock rhs = ((DataBlockExpr) expr).getDataBlock();
			check(hasCompatibleIndices(rhs), n, "improper indices on rhs of put");
			checkCompatibleBlocks(n, lhs, rhs);
			return;
		}
	}

	@Override
	public boolean visit(RequestStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(RequestStatement n) {
		IDec dec = n.getDataBlock().getIdent().getDec();
		if (!(check(dec instanceof ArrayDec && ((ArrayDec) dec).getTypeName().equals("served"), n, n.getDataBlock()
				.getIdent().toString()
				+ "must be a served array"))) {
			return;
		}
	}

	// @Override
	// public boolean visit(PrequestStatement n) { /* visit children */
	// return true;
	// }
	//
	// @Override
	// public void endVisit(PrequestStatement n) {
	// DataBlock lhs = n.getLHSDataBlock();
	// DataBlock rhs = n.getRHSDataBlock();
	// // check that lhs is a temp array
	// IDec lhsDec = lhs.getIdent().getDec();
	// if (!(check(lhsDec instanceof ArrayDec && ((ArrayDec)
	// lhsDec).getTypeName().equals("temp"), n, lhs.getIdent()
	// .toString() + " must be a temp array"))) {
	// return;
	// }
	// // check that rhs is served array
	// IDec rhsDec = rhs.getIdent().getDec();
	// if (!(check(rhsDec instanceof ArrayDec && ((ArrayDec)
	// rhsDec).getTypeName().equals("served"), n, lhs.getIdent()
	// .toString() + " must be a served array"))) {
	// return;
	// }
	// // check that indices on both sides have compatible types.
	// // this means that the indices are identical until some point where the
	// // lhs indices change to
	// // something of index type
	// // example T(a,b,i,j) = S(a,b,c,d) where i and j are declared index.
	// IdentList lhsIndices = lhs.getIndices();
	// IdentList rhsIndices = rhs.getIndices();
	// if (!check(lhsIndices.size() == rhsIndices.size(), n,
	// "index lists do not have the same size")) {
	// return;
	// }
	//
	// for (int i = 0; i < lhsIndices.size(); i++) {
	// String lhsType = ((IndexDec)
	// lhsIndices.getIdentAt(i).getDec()).getTypeName();
	// String rhsType = ((IndexDec)
	// rhsIndices.getIdentAt(i).getDec()).getTypeName();
	//
	// if (!check(lhsType.equals(rhsType) || lhsType.equals("index"), n,
	// "inconsistent types for indices "
	// + lhsType + " and " + rhsType))
	// return;
	// }
	// }

	@Override
	public boolean visit(CollectiveStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(CollectiveStatement n) {
		IDec lhsDec = n.getIdent().getDec();
		check(lhsDec instanceof ScalarDec, n, "Collective requires scalar arguments");
		EnumSet<EType> t = ASTUtils.getIExprTypes(n.getExpression());
		check(t.contains(SCALAR), n, "rhs of collective sum must be scalar expression " + n);
		int op = n.getAssignOp().getop().getKind();
		check(op == TK_PLUS_ASSIGN, n, "Collective operator must be +=");
	}

	@Override
	public boolean visit(ExecuteStatement n) {
		// visit the children
		return true;
	}

	// bare array names are allowed for some instructions
	@Override
	public void endVisit(ExecuteStatement n) {
		IDec dec = n.getIdent().getDec();
		if (!check(dec instanceof SpecialDec, n, n.getIdent().toString() + " not declared as special instruction"))
			return;
		int expected_args = ((SpecialDec) dec).getNumArgs();
		int num_args = n.getArgList().size();
		check(num_args == expected_args, n, "execute  " + n.getIdent() + " has " + num_args + " arguments but expects "
				+ expected_args);
	}

	// A ContiguousDataBlock arg encloses a ContiguousDataBlock. Visiting the
	// children suffices.
	@Override
	public boolean visit(ContiguousDataBlockArg n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousDataBlockArg n) {
	}

	// a DataBlock arg encloses a DataBlock. Visiting the children suffices
	@Override
	public boolean visit(DataBlockArg n) {
		return true;
	}

	@Override
	public void endVisit(DataBlockArg n) {
	}

	// this is a separate type from an Ident. It must be either a scalar or a
	// static or contiguous array
	@Override
	public boolean visit(IdentArg n) {
		try {
			IDec dec = findAndSetDec(n);
			check(dec != null, n, n.toString() + " not declared");
			if (dec instanceof ScalarDec)
				return false;
			if (dec instanceof ArrayDec) {
				check(ASTUtils.isStaticOrContiguousArray(dec) || nonstatic_noselector_array_allowed(n),
						n,
						"Normally, a non-contiguous array requires block selector. "
								+ "A list of special cases is built in to the compiler.  This super instruction is not in the list");
				((ArrayDec) dec).setUsed();
				return false;
			}
			check(false, n, "illegal argument type for execute statement");
		} catch (AmbiguousNameException e) {
			check(false, n, e.getMessage());
		}
		return false;
	}

	@Override
	public void endVisit(IdentArg n) {
	}

	/**
	 * These are the special superinstruction that take non-static array
	 * arguments without a block selector blocks_to_list list_to_blocks
	 * array_copy
	 * */
	boolean nonstatic_noselector_array_allowed(IdentArg n) {
		ExecuteStatement executeStatement = (ExecuteStatement) n.getParent().getParent(); // parent
																							// is
																							// ArgList
																							// whose
																							// parent
																							// is
																							// ExecuteStatement
		String instruction_name = executeStatement.getIdent().getName();
		if (instruction_name.equals("blocks_to_list"))
			return true;
		if (instruction_name.equals("list_to_blocks"))
			return true;
		if (instruction_name.equals("array_copy"))
			return true;
		return false;
	}

	@Override
	public boolean visit(DoubleLitArg n) {
		return false;
	}

	@Override
	public void endVisit(DoubleLitArg n) {
	}

	@Override
	public boolean visit(DestroyStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DestroyStatement n) {
		// check that argument is a served array
		IDec dec = n.getIdent().getDec();
		if (!(check(dec instanceof ArrayDec && ((ArrayDec) dec).getTypeName().equals("served"), n, dec.toString()
				+ "must be a served array"))) {
			return;
		}
	}

	@Override
	public boolean visit(BroadcastStatic n) {
		return true;
	}

	@Override
	public void endVisit(BroadcastStatic n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n.getPrimary());
		check(t.contains(INT), n, "illegal type of allocate argument " + n);
		IDec identDec = n.getIdent().getDec();
		if (!check(identDec instanceof ArrayDec && ((ArrayDec) identDec).getTypeName().equals("static"), n,
				n.getIdent() + " must be a static array"))
			return;
	}

	@Override
	public boolean visit(DataBlock n) { /* visit children */
		return true;
	}

	/*
	 * checks that given indices have same type as the declaration, or be a
	 * subindex of the declared type
	 */

	@Override
	public void endVisit(DataBlock n) {
		IDec identDec = n.getIdent().getDec();
		if (identDec == null)
			return; // error alreaday reported
		if (!check(identDec instanceof ArrayDec, n, n.getIdent() + " must be an array"))
			return;
		// ((ArrayDec)identDec).setUsed();
		IdentList indices = n.getIndices();
		DimensionList declaredDimensionList = ((ArrayDec) identDec).getDimensionList();
		if (!check(indices.size() == declaredDimensionList.size(), n, "number of indices of " + n.getIdent()
				+ " does not match declaration"))
			return;
		for (int i = 0; i < indices.size(); i++) {
			// get type of index
			IDec indexDec = indices.getIdentAt(i).getDec();
			if (indexDec == null) return;  //index not declared.  Error already reported.
			if (!check(indexDec instanceof IndexDec || indexDec instanceof SubIndexDec, n, n.getIndices().getIdentAt(i)
					.getIDENTIFIER()
					+ " must be an index or subindex"))
				return;
			String indexType = (indexDec instanceof IndexDec) ? ((IndexDec) indexDec).getTypeName()
					: ((IndexDec) ((SubIndexDec) indexDec).getParentIdent().getDec()).getTypeName();
			// get type of dimension in declaration
			IDec dimensionDec = declaredDimensionList.getDimensionAt(i).getDec();
			// if dimensionDec == null, the index was not properly declared.
			// this should have been detected when the children were visited.
			// return to avoid null pointer exception.
			if (dimensionDec == null)
				return;
			// get a string representing type, could be some index or subindex
			String dimensionType = (dimensionDec instanceof IndexDec) ? ((IndexDec) dimensionDec).getTypeName()
					: ((IndexDec) ((SubIndexDec) dimensionDec).getParentIdent().getDec()).getTypeName();
			if (!check(indexType.equals(dimensionType), n, indices.getIdentAt(i).getIDENTIFIER() + " and "
					+ declaredDimensionList.getDimensionAt(i).getIDENTIFIER() + " have incompatible types"))
				return;
		}
	}

	@Override
	public boolean visit(IdentList n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(IdentList n) { /* nop */
	}

	@Override
	public boolean visit(ContiguousDataBlock n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousDataBlock n) {
		IDec identDec = n.getIdent().getDec();
		if (identDec == null)
			return; // error alreaday reported
		if (!check(identDec instanceof ArrayDec, n, n.getIdent() + " must be an array"))
			return;
		if (!check(ASTUtils.isContiguous(identDec), n, n.getIdent() + "must be contiguous"))
			return;
		ContiguousIndexRangeExprList ranges = n.getContiguousIndexRangeExprList();
		DimensionList declaredDimensionList = ((ArrayDec) identDec).getDimensionList();
		if (!check(ranges.size() == declaredDimensionList.size(), n, "number of indices of " + n.getIdent()
				+ " does not match declaration"))
			return;
		for (int i = 0; i < ranges.size(); i++) {
			ContiguousIndexRangeExpr range = ranges.getContiguousIndexRangeExprAt(i);
			IExpression startExpr = range.getStartExpr();
			check(ASTUtils.getIExprTypes(startExpr).contains(INT), range, "lower value of range must be int");
			IExpression endExpr = range.getEndExpr();
			check(ASTUtils.getIExprTypes(endExpr).contains(INT), range, "upper value of range must be int");
		}
	}

	@Override
	public boolean visit(AllocIndexIdent n) {
		IDec dec = findAndSetDec(n);
		check(dec != null, n, n.toString() + " not declared");
		check(dec instanceof IndexDec || dec instanceof SubIndexDec, n, "illegal index; " + n.getName()
				+ " is not an index or subindex");
		return false;
	}

	@Override
	public void endVisit(AllocIndexIdent n) { /* nop */
	}

	@Override
	public boolean visit(AllocIndexWildCard n) { /* nothing to do */
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
	public void endVisit(AllocIndexList n) { /* nop */
	}

	@Override
	public boolean visit(ContiguousAllocateStatement n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousAllocateStatement n) {
		IDec arrayDec = n.getIdent().getDec();
		check(arrayDec instanceof ArrayDec, n, "array " + n.getIdent().getName() + " must be declared as an array");
		check(ASTUtils.isLocal(arrayDec), n, "array " + n.getIdent().getName() + " must be declared as  local array");
		check(ASTUtils.isContiguous(arrayDec), n, "array " + n.getIdent().getName()
				+ " must be declared as contiguous ");
		int rank = ((ArrayDec) arrayDec).getDimensionList().size();
		check(n.getContiguousIndexRangeExprList().size() == rank, n,
				"number of indices does not match delcaration of array");
	}

	@Override
	public boolean visit(ContiguousDeallocateStatement n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousDeallocateStatement n) {
		IDec arrayDec = n.getIdent().getDec();
		check(arrayDec instanceof ArrayDec && ASTUtils.isLocal(arrayDec) && ASTUtils.isContiguous(arrayDec), n,
				"array " + n + " must be declared as contiguous local array");
		int rank = ((ArrayDec) arrayDec).getDimensionList().size();
		check(n.getContiguousIndexRangeExprList().size() == rank, n,
				"number of indices does not match delcaration of array");
	}

	@Override
	public boolean visit(ContiguousIndexRangeExpr n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousIndexRangeExpr n) {
		EnumSet<EType> ts = ASTUtils.getIExprTypes(n.getStartExpr());
		check(ts.contains(INT), n, "illegal type of allocate argument " + n);
		EnumSet<EType> te = ASTUtils.getIExprTypes(n.getStartExpr());
		check(te.contains(INT), n, "illegal type of allocate argument " + n);
	}

	@Override
	public boolean visit(ContiguousIndexRangeExprList n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousIndexRangeExprList n) {
		/* nop */
	}

	@Override
	public boolean visit(RelationalExpression n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(RelationalExpression n) {
		EnumSet<EType> t0 = getIExprTypes(n.getUnaryExpressionLeft());
		EnumSet<EType> t1 = getIExprTypes(n.getUnaryExpressionRight());
		if (t0.contains(SCALAR) && t1.contains(SCALAR))
			return;
		if ((t0.contains(INT) || t0.contains(INDEX)) && (t1.contains(INT) || t1.contains(INDEX)))
			return;
		check(false, n, "Incompatible or illegal operands of relational expression.  Left side has type " + t0
				+ "right side has type " + t1);
	}

	@Override
	public boolean visit(AddExpr n) {
		return true;
	}

	@Override
	public void endVisit(AddExpr n) {
		EnumSet<EType> t0 = getIExprTypes(n.getExpression());
		EnumSet<EType> t1 = getIExprTypes(n.getTerm());
		if (t0.isEmpty() || t1.isEmpty())
			return; // expr doesn't have type, error already reported.
		if (t0.contains(SCALAR) && t1.contains(SCALAR)) {
			n.addType(SCALAR);
			return;
		}
		if (t0.contains(INT) && t1.contains(INT)) {
			n.addType(INT);
			return;
		}
		if (t0.contains(BLOCK) && t1.contains(BLOCK)) {
			IExpression e0 = n.getExpression();
			ITerm e1 = n.getTerm();
			check(e0 instanceof DataBlockExpr && e1 instanceof DataBlockExpr, n, "Both xpression must be a data block");
			checkCompatibleBlocks(n, ((DataBlockExpr) e0).getDataBlock(), ((DataBlockExpr) e1).getDataBlock());
			n.addType(BLOCK);
			return;
		}
		check(false, n, " incompatible types in expression");
	}

	@Override
	public boolean visit(SubtractExpr n) {
		return true;
	}

	@Override
	public void endVisit(SubtractExpr n) {
		EnumSet<EType> t0 = getIExprTypes(n.getExpression());
		EnumSet<EType> t1 = getIExprTypes(n.getTerm());
		if (t0.isEmpty() || t1.isEmpty())
			return; // contains error already reported by child
		if (t0.contains(SCALAR) && t1.contains(SCALAR)) {
			n.addType(SCALAR);
			return;
		}
		if (t0.contains(INT) && t1.contains(INT)) {
			n.addType(INT);
			return;
		}
		if (t0.contains(BLOCK) && t1.contains(BLOCK)) {
			IExpression e0 = n.getExpression();
			ITerm e1 = n.getTerm();
			check(e0 instanceof DataBlockExpr && e1 instanceof DataBlockExpr, n, "Both xpression must be a data block");
			checkCompatibleBlocks(n, ((DataBlockExpr) e0).getDataBlock(), ((DataBlockExpr) e1).getDataBlock());
			n.addType(BLOCK);
			return;
		}
		check(false, n, " incompatible types in expression");
	}

	@Override
	public boolean visit(StarExpr n) {
		return true;
	}

	@Override
	public void endVisit(StarExpr n) {
		ITerm leftOperand = n.getTerm();
		IExponentExpression rightOperand = n.getExponentExpression();
		EnumSet<EType> t0 = getIExprTypes(leftOperand);
		EnumSet<EType> t1 = getIExprTypes(rightOperand);
		if (t0.isEmpty() || t1.isEmpty())
			return;
		if (t0.contains(BLOCK) && t1.contains(BLOCK)) {
			if (!check(leftOperand instanceof DataBlockExpr && rightOperand instanceof DataBlockExpr, n,
					"only simple binary expression involving blocks allowed"))
				return;
			// check to see if result is scalar
			ArrayList<Ident> resultIndices = getContractionResultIndices(((DataBlockExpr) leftOperand).getDataBlock(),
					((DataBlockExpr) rightOperand).getDataBlock());
			if (resultIndices.size() == 0)
				n.addType(SCALAR);
			n.addType(BLOCK);
			// if either argument also has SCALAR, remove it
			t0.remove(SCALAR);
			t1.remove(SCALAR);
			return;
		}
		if (t0.contains(SCALAR) && t1.contains(BLOCK)) {
			check(rightOperand instanceof DataBlockExpr, n,
					"only nonnested binary expressions involving blocks allowed");
			n.addType(BLOCK);
			if (t1.contains(SCALAR))
				n.addType(SCALAR);
			return;
		}
		if (t0.contains(BLOCK) && t1.contains(SCALAR)) {
			check(leftOperand instanceof DataBlockExpr, n, "only nonnested binary expressions");
			n.addType(BLOCK);
			if (t0.contains(SCALAR))
				n.addType(SCALAR);
			return;
		}
		// check this after first checking for containing BLOCK to disallow
		// scalar-producing contractions to be nested in larger expression. May
		// want to fix this later.
		if (t0.contains(SCALAR) && t1.contains(SCALAR)) {
			n.addType(SCALAR);
			return;
		}
		if (t0.contains(INT) && t1.contains(INT)) {
			n.addType(INT);
			return;
		}
		check(false, n, "incompatible types in expression");
	}

	@Override
	public boolean visit(DivExpr n) {
		return true;
	}

	@Override
	public void endVisit(DivExpr n) {
		EnumSet<EType> t0 = getIExprTypes(n.getTerm());
		EnumSet<EType> t1 = getIExprTypes(n.getExponentExpression());
		if (t0.isEmpty() || t1.isEmpty())
			return;
		if (t0.contains(SCALAR) && t1.contains(SCALAR)) {
			n.addType(SCALAR);
			return;
		}
		if (t0.contains(INT) && t1.contains(INT)) {
			n.addType(INT);
			return;
		}
		check(false, n, " incompatible types in expression");
	}

	@Override
	public boolean visit(TensorExpr n) {
		return true;
	}

	@Override
	public void endVisit(TensorExpr n) {
		ITerm leftOperand = n.getTerm();
		IExponentExpression rightOperand = n.getExponentExpression();
		EnumSet<EType> t0 = getIExprTypes(leftOperand);
		EnumSet<EType> t1 = getIExprTypes(rightOperand);
		if (t0.isEmpty() || t1.isEmpty())
			return;
		if (!check(t0.contains(BLOCK) && leftOperand instanceof DataBlockExpr && t1.contains(BLOCK)
				&& rightOperand instanceof DataBlockExpr, n, "arguments to ^ must be data blocks"))
			return;
		// ensure that index sets are disjoint
		ArrayList<Ident> resultIndices = getContractionResultIndices(((DataBlockExpr) leftOperand).getDataBlock(),
				((DataBlockExpr) rightOperand).getDataBlock());
		check(resultIndices.size() == ((DataBlockExpr) leftOperand).getDataBlock().getIndices().size()
				+ ((DataBlockExpr) rightOperand).getDataBlock().getIndices().size(), n,
				"arguments to ^ must be data blocks with disjoint index sets");
		n.addType(BLOCK);
		// if either argument also has SCALAR, remove it
		t0.remove(SCALAR);
		t1.remove(SCALAR);
	}

	public boolean visit(ExponentExpr n) {
		return true;
	}

	public void endVisit(ExponentExpr n) {
		EnumSet<EType> t0 = getIExprTypes(n.getCastExpression());
		EnumSet<EType> t1 = getIExprTypes(n.getExponentExpression());
		if (t0.isEmpty() || t1.isEmpty())
			return;
		if (t0.isEmpty() || t1.isEmpty())
			return;
		if (t0.contains(SCALAR) && t1.contains(SCALAR)) {
			n.addType(SCALAR);
			return;
		}
		check(false, n, "both arguments to ** must be scalar");
	}

	@Override
	public boolean visit(IntCastExpr n) {
		return true;
	}

	@Override
	public void endVisit(IntCastExpr n) {
		EnumSet<EType> t = getIExprTypes(n.getCastExpression());
		if (t.isEmpty())
			return;
		check(t.contains(SCALAR), n, "cast to int only allowed from scalar");
		n.addType(INT);
	}

	@Override
	public boolean visit(ScalarCastExpr n) {
		return true;
	}

	@Override
	public void endVisit(ScalarCastExpr n) {
		EnumSet<EType> t = getIExprTypes(n.getCastExpression());
		if (t.isEmpty())
			return;
		check(t.contains(INT) || t.contains(INDEX), n, "cast to scalar only allowed from int or index");
		n.addType(SCALAR);
	}

	@Override
	public boolean visit(NegatedUnaryExpr n) {
		return true;
	}

	@Override
	public void endVisit(NegatedUnaryExpr n) {
		EnumSet<EType> t = getIExprTypes(n.getPrimary());
		if (t.isEmpty())
			return;
		if (t.contains(SCALAR)) {
			n.addType(SCALAR);
			return;
		}
		if (t.contains(INT)) {
			n.addType(INT);
			return;
		}
		check(false, n, "negation only supported for scalar and  int expressions");
	}

	@Override
	public boolean visit(SqrtUnaryExpr n) {
		return true;
	}

	@Override
	public void endVisit(SqrtUnaryExpr n) {
		EnumSet<EType> t = getIExprTypes(n.getPrimary());
		if (t.isEmpty())
			return;
		check(t.contains(SCALAR), n, "srt only supported for scalar expressions");
		n.addType(SCALAR);
		return;
	}

	@Override
	public boolean visit(ParenExpr n) {
		return true;
	}

	@Override
	public void endVisit(ParenExpr n) {
		EnumSet<EType> t = getIExprTypes(n.getExpression());
		n.addType(t);
	}

	@Override
	public boolean visit(IntLitExpr n) {
		return false;
	}

	@Override
	public void endVisit(IntLitExpr n) {
		n.addType(INT);
	}

	@Override
	public boolean visit(DoubleLitExpr n) {
		return false;
	}

	@Override
	public void endVisit(DoubleLitExpr n) {
		n.addType(SCALAR);
	}

	@Override
	public boolean visit(DataBlockExpr n) {
		return true;
	}

	@Override
	public void endVisit(DataBlockExpr n) {
		n.addType(BLOCK);
		if (allIndicesSimple(n.getDataBlock())) { // all indices are simple;
													// "blocks" in contractions
													// are actually scalars
			n.addType(SCALAR);
		}
	}

	@Override
	public boolean visit(ContiguousDataBlockExpr n) {
		return true;
	}

	@Override
	public void endVisit(ContiguousDataBlockExpr n) {
		n.addType(CONTIG_BLOCK);
	}

	@Override
	public boolean visit(Ident n) {
		try {
			IDec decl = findAndSetDec(n);
			check(decl != null, n, n.toString() + " not declared");
			if (decl instanceof IndexDec) {
				check(isDefinedInEnclosingScope(n, (IndexDec) decl, n), n, "index " + n.toString()
						+ " not defined by enclosing do or pardo loop");
			}
			if (decl instanceof ArrayDec) {
				((ArrayDec) decl).setUsed();
			}
		} catch (AmbiguousNameException e) {
			emitError(n, e.getMessage());
		}
		return false;
	}

	@Override
	public void endVisit(Ident n) { /* nop */
	}

	// @Override
	public void unimplementedVisitor(String s) {
	}

	@Override
	public boolean visit(IdentExpr n) {
		try {
			IDec decl = findAndSetDec(n);
			check(decl != null, n, n.toString() + " not declared");
			if (decl instanceof IndexDec) {
				check(isDefinedInEnclosingScope(n, (IndexDec) decl, n), n, "index " + n.toString()
						+ " not defined by enclosing do or pardo loop");
			}
			if (decl instanceof ArrayDec) {
				((ArrayDec) decl).setUsed();
			}
		} catch (AmbiguousNameException e) {
			emitError(n, e.getMessage());
		}
		return true;
	}

	@Override
	public void endVisit(IdentExpr n) {
		IDec dec = n.getDec();
		if (dec == null)
			return; // not declared. Error already reported by child
		if (dec instanceof IntDec)
			n.addType(INT);
		else if (dec instanceof ScalarDec)
			n.addType(SCALAR);
		else if (dec instanceof ArrayDec) {
			n.addType(ARRAY);
			((ArrayDec) dec).setUsed();
		} else if (dec instanceof IndexDec) {
			n.addType(INDEX);
			n.addType(INT);
		} else
			check(false, n, "compiler bug:  unexpected type in IdentExpr " + dec);
	}

	@Override
	public boolean visit(ModifierList n) { /* nothing to check */
		return false;
	}

	@Override
	public void endVisit(ModifierList n) { /* nop */
	}

	@Override
	public boolean visit(ArgList n) { // FIXME low priority, check against
										// declaration
		/* visit children */
		return true;
	}

	// /**
	// * These are the special superinstruction that take non-static array
	// * arguments without a block selector blocks_to_list list_to_blocks
	// * array_copy
	// * */
	// boolean nonstatic_noselector_array_allowed(ExecuteStatement n) {
	// String instruction_name = n.getIdent().getName();
	// if (instruction_name.equals("blocks_to_list"))
	// return true;
	// if (instruction_name.equals("list_to_blocks"))
	// return true;
	// if (instruction_name.equals("array_copy"))
	// return true;
	// return false;
	// }

	@Override
	public void endVisit(ArgList n) {
		// checking is done in each element of list
		// // if an argument is an IdentExpr, it must either be a scalar or a
		// // static array
		// for (int i = 0; i < n.size(); ++i) {
		// IArg arg = n.getArgAt(i);
		// if (arg instanceof IdentExpr) {
		// IdentExpr idparg = (IdentExpr) arg;
		// IDec dec = idparg.getDec();
		// if (dec instanceof ArrayDec) {
		// check(isStaticOrContiguousArray(dec)
		// || nonstatic_noselector_array_allowed((ExecuteStatement)
		// n.getParent()), idparg,
		// "execute " + ((ExecuteStatement) n.getParent()).getIdent().getName()
		// + " has array argument " + idparg.getName()
		// + " which is neither static nor contigous");
		// }
		// }
		// }
	}

	boolean allIndicesSimple(DataBlock datablock) {
		IdentList indices = datablock.getIndices();
		for (int i = 0; i < indices.size(); ++i) {
			Ident ind = indices.getIdentAt(i);
			IDec dec = ind.getDec();
			if (!(dec instanceof IndexDec && ((IndexDec) dec).getIndexKind().getIToken().getKind() == TK_index)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean visit(AssignToIdent n) {
		return true;
	}

	@Override
	public void endVisit(AssignToIdent n) { // TODO allow static to static
		Ident lhs = n.getIdent();
		IDec dec = lhs.getDec();
		if (dec == null)
			return; // lhs not declare, error was reported by child
		IExpression expr = n.getExpression();
		EnumSet<EType> t = ASTUtils.getIExprTypes(expr);
		if (dec instanceof IntDec && (t.contains(INT) || t.contains(INDEX)))
			return; // lhs declared as int, rhs is int expr or an index
		if (dec instanceof ScalarDec && t.contains(SCALAR))
			return; // lhs declared as scalar, rhs is scalar expr.
		// This will be the case if it is really a scalar expr, or
		// if it is a block with all simple indices, or a contraction with
		// result of rank 0.
		check(false, n, "incompatible types in assignment statement");
	}

	// returns true if given indices are same as declared
	boolean hasMatchingDeclaredIndices(DataBlock block, ContiguousDataBlock cblock) {
		IDec dec = block.getIdent().getDec();
		IDec cdec = cblock.getIdent().getDec();
		if (dec == null || cdec == null)
			return false;
		DimensionList declaredIndices = ((ArrayDec) dec).getDimensionList();
		DimensionList cDeclaredIndices = ((ArrayDec) cdec).getDimensionList();
		if (declaredIndices.size() != cDeclaredIndices.size()) {
			return false;
		}
		for (int i = 0; i < declaredIndices.size(); ++i) {
			Ident ident = declaredIndices.getDimensionAt(i);
			Ident cIdent = cDeclaredIndices.getDimensionAt(i);
			if (ident.getName().equals(cIdent.getName())) {
			}// indices are identical
			else { // if not, at least the index kind should be the same
				IndexKind kind = ((IndexDec) ident.getDec()).getIndexKind();
				IndexKind ckind = ((IndexDec) cIdent.getDec()).getIndexKind();
				if (kind.toString().equals(ckind.toString())) {
				} else {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean visit(AssignToBlock n) {
		return true;
	}

	@Override
	public void endVisit(AssignToBlock n) {
		DataBlock lhs = n.getDataBlock();
		IExpression expr = n.getExpression();
		EnumSet<EType> t = ASTUtils.getIExprTypes(expr);
		if (t.isEmpty())
			return; // error already reported by child
		int op = n.getAssignOp().getop().getKind();
		if (!check(t.contains(SCALAR) || t.contains(BLOCK) || t.contains(CONTIG_BLOCK), n,
				"incompatible type in assignment"))
			return;
		if (t.contains(SCALAR))
			return;
		// rhs is a block, check that indices are compatible
		IdentList lhsIndices = lhs.getIndices();
		if (expr instanceof DataBlockExpr) { // rhs is a single data block
			DataBlock rhs = ((DataBlockExpr) expr).getDataBlock();
			if (op == TK_ASSIGN) { // slices and insertions
									// allowed only for =
				boolean isSlice = hasDeclaredIndices(lhs) && isSubBlock(rhs);
				if (isSlice) { // lhs is subblock of rhs
					n.setSlice(true);
					return;
				}
				boolean isInsert = hasDeclaredIndices(rhs) && isSubBlock(lhs);
				if (isInsert) { // rhs is subblock of lhs
					n.setInsert(true);
					return;
				}
				String msg = isCompatibleBockWithTranspose(lhs, rhs);
				check(msg == null, n, msg);
				return;
			}
			// op == +=, -=, *=
			check(hasCompatibleIndices(lhs), n, "improper indices on lhs of assignment");
			check(hasCompatibleIndices(rhs), n, "improper indices on rhs of assignment");
			return;
		}
		if (expr instanceof ContiguousDataBlockExpr) { // rhs is a block from a
														// contiguous local
			ContiguousDataBlock rhs = ((ContiguousDataBlockExpr) expr).getContiguousDataBlock();
			check(op == TK_ASSIGN, n, "only plain assignment supported with contiguous locals");
			// check that the declared indices of the lhs and rhs match
			check(hasMatchingDeclaredIndices(lhs, rhs), n, "incompatible declared indices on left and right side");
			return;
		}
		if (expr instanceof StarExpr) {
			StarExpr starExpr = (StarExpr) expr;
			ITerm e0 = starExpr.getTerm();
			IExponentExpression e1 = starExpr.getExponentExpression();
			EnumSet<EType> t0 = getIExprTypes(e0);
			EnumSet<EType> t1 = getIExprTypes(e1);
			if (e0 instanceof DataBlockExpr && e1 instanceof DataBlockExpr) {
				// rhs is b0 * b1 where b0 and b1 are blocks
				DataBlock b0 = ((DataBlockExpr) e0).getDataBlock();
				DataBlock b1 = ((DataBlockExpr) e1).getDataBlock();
				ArrayList<Ident> exprIndices = getContractionResultIndices(b0, b1);
				int exprIndicesSize = exprIndices.size();
				ArrayList<String> names = new ArrayList(exprIndicesSize);
				for (int i = 0; i < exprIndicesSize; i++) {
					names.add(exprIndices.get(i).getName());
				}
				if (!check(lhsIndices.size() == exprIndices.size(), n, "left and right sides have different dimensions"))
					return;
				for (int i = 0; i < lhsIndices.size(); i++) {
					String lhsIndexName = lhsIndices.getIdentAt(i).getName();
					if (!check(names.contains(lhsIndexName), n, "indices do not match on left and right hand sides"))
						return;
				}
				return;
			}
			if (e0 instanceof DataBlockExpr && t1.contains(SCALAR)) {
				// rhs is b0 * scalar
				DataBlock b0 = ((DataBlockExpr) e0).getDataBlock();
				IdentList b0indices = b0.getIndices();
				if (!check(lhsIndices.size() == b0indices.size(), n, "number of indices does not match"))
					return;
				for (int i = 0; i < lhsIndices.size(); i++) {
					String lhsIndexName = lhsIndices.getIdentAt(i).getName();
					String block1IndexName = b0indices.getIdentAt(i).getName();
					if (!check(lhsIndexName.equals(block1IndexName), n, "indices do not match"))
						return;
				}
				return; // OK
			}
			if (t0.contains(SCALAR) && (e1 instanceof DataBlockExpr)) {
				// rhs is scalar * b1
				DataBlock b1 = ((DataBlockExpr) e1).getDataBlock();
				IdentList b1indices = b1.getIndices();
				if (!check(lhsIndices.size() == b1indices.size(), n, "number of indices does not match"))
					return;
				for (int i = 0; i < lhsIndices.size(); i++) {
					String lhsIndexName = lhsIndices.getIdentAt(i).getName();
					String block2IndexName = b1indices.getIdentAt(i).getName();
					if (!check(lhsIndexName.equals(block2IndexName), n, "indices do not match"))
						return;
				}
				return; // OK
			}
			check(false, n, "illegal * to block");
		}
		if (expr instanceof TensorExpr) {
			TensorExpr tensorExpr = (TensorExpr) expr;
			ITerm e0 = tensorExpr.getTerm();
			IExponentExpression e1 = tensorExpr.getExponentExpression();
			if (e0 instanceof DataBlockExpr && e1 instanceof DataBlockExpr) {
				// rhs is b0 * b1 where b0 and b1 are blocks
				DataBlock b0 = ((DataBlockExpr) e0).getDataBlock();
				DataBlock b1 = ((DataBlockExpr) e1).getDataBlock();
				ArrayList<Ident> exprIndices = getContractionResultIndices(b0, b1);
				int exprIndicesSize = exprIndices.size();
				ArrayList<String> names = new ArrayList(exprIndicesSize);
				for (int i = 0; i < exprIndicesSize; i++) {
					names.add(exprIndices.get(i).getName());
				}
				if (!check(lhsIndices.size() == exprIndices.size(), n, "left and right sides have different dimensions"))
					return;
				for (int i = 0; i < lhsIndices.size(); i++) {
					String lhsIndexName = lhsIndices.getIdentAt(i).getName();
					if (!check(names.contains(lhsIndexName), n, "indices do not match on left and right hand sides"))
						return;
				}
				return;
			}
		}
		if (expr instanceof AddExpr || expr instanceof SubtractExpr) {
			IExpression e0;
			ITerm e1;
			if (expr instanceof AddExpr) {
				AddExpr addExpr = (AddExpr) expr;
				e0 = addExpr.getExpression();
				e1 = addExpr.getTerm();
			} else {
				SubtractExpr subtractExpr = (SubtractExpr) expr;
				e0 = subtractExpr.getExpression();
				e1 = subtractExpr.getTerm();
			}
			check(e0 instanceof DataBlockExpr && e1 instanceof DataBlockExpr, n, "illegal rhs in assignment");
			DataBlock b0 = ((DataBlockExpr) e0).getDataBlock();
			DataBlock b1 = ((DataBlockExpr) e1).getDataBlock();
			IdentList b0indices = b0.getIndices();
			IdentList b1indices = b1.getIndices();
			if (!check(lhsIndices.size() == b0indices.size() && lhsIndices.size() == b1indices.size(), n,
					"number of indices on left and right side of assingment does not match"))
				return;
			for (int i = 0; i < lhsIndices.size(); i++) {
				String lhsIndexName = lhsIndices.getIdentAt(i).getName();
				String block1IndexName = b0indices.getIdentAt(i).getName();
				String block2IndexName = b1indices.getIdentAt(i).getName();
				if (!check(lhsIndexName.equals(block1IndexName) && lhsIndexName.equals(block2IndexName), n,
						"indices do not match"))
					return;
			}
			return;

		}
		check(false, n, "illegal assignment to block");

	}

	public boolean visit(AssignToContigousDataBlock n) {
		return true;
	}

	public void endVisit(AssignToContigousDataBlock n) {
		ContiguousDataBlock lhs = n.getContiguousDataBlock();
		IExpression expr = n.getExpression();
		EnumSet<EType> t = ASTUtils.getIExprTypes(expr);
		if (t.isEmpty())
			return; // error already reported by child
		int op = n.getAssignOp().getop().getKind();
		if (!check((t.contains(SCALAR) || t.contains(BLOCK)), n, "incompatible type in assignment"))
			return;
		if (t.contains(SCALAR))
			return;
		if (expr instanceof DataBlockExpr) { // rhs is a single data block
			DataBlock rhs = ((DataBlockExpr) expr).getDataBlock();
			check(op == TK_ASSIGN, n, "only plain assignment supported with contiguous locals");
			// check that the declared indices of the lhs and rhs match
			check(hasMatchingDeclaredIndices(rhs, lhs), n, "incompatible declared indices on left and right side");
			return;
		}
	}

	// returns true if index1 is a subindex of index2
	boolean isSubIndex(Ident index1, Ident index2) {
		IDec dec1 = index1.getDec();
		IDec dec2 = index2.getDec();
		return dec1 instanceof SubIndexDec && ((SubIndexDec) dec1).getParentIdent().getDec() == dec2;
	}

	// returns true if given indices are same as declared
	boolean hasDeclaredIndices(DataBlock block) {
		IdentList indices = block.getIndices();
		IDec dec = block.getIdent().getDec();
		if (dec == null)
			return false;
		DimensionList declaredIndices = ((ArrayDec) dec).getDimensionList();
		if (declaredIndices.size() < indices.size()) {
			return false; // this block sizes don't match,
			// this avoids index out of bounds exception.
			// the error should be found elsewhere.
			// THIS ASSUMPTION MAY NOT BE TRUE
		}
		for (int i = 0; i < indices.size(); ++i) {
			Ident declaredIdent = declaredIndices.getDimensionAt(i);
			Ident usedIdent = indices.getIdentAt(i);
			if (!declaredIdent.getName().equals(usedIdent.getName()))
				return false;

		}
		return true;
	}

	// indices in data block have the same type as those in the declaration
	boolean hasCompatibleIndices(DataBlock block) {
		IdentList indices = block.getIndices();
		IDec dec = block.getIdent().getDec();
		if (dec == null)
			return false;
		DimensionList declaredIndices = ((ArrayDec) dec).getDimensionList();
		if (declaredIndices.size() < indices.size()) {
			return false; // there are more indices given than declared.
		}
		for (int i = 0; i < indices.size(); ++i) {
			Ident declaredIndex = declaredIndices.getDimensionAt(i);
			IndexDec declaredIndexDec = (IndexDec) declaredIndex.getDec();
			Ident usedIdent = indices.getIdentAt(i);
			IndexDec usedIndexDec = (IndexDec) usedIdent.getDec();
			// lots' of "kinds" here. The IndexKind is the AST node representing
			// aoindex moaindex, etc.
			// getiKind returns the IToken, and getKind returns the parser's
			// constant for the keyword TK_aoindex, etc.
			if ((usedIndexDec == null) || declaredIndexDec.getIndexKind().getikind().getKind() != usedIndexDec.getIndexKind().getikind()
					.getKind()) {
				return false;
			}
		}
		return true;
	}

	// returns true if there is at least one index that is a subindex of
	// declared index and the rest are as declared
	boolean isSubBlock(DataBlock block) {
		IdentList indices = block.getIndices();
		IDec dec = block.getIdent().getDec();
		if (dec == null)
			return false; // this ident wasn't declared. This avoids null
							// pointer exception, error found elsewhere.
		DimensionList declaredIndices = ((ArrayDec) dec).getDimensionList();
		boolean isValid = true;
		boolean subBlock = false;
		if (declaredIndices.size() < indices.size()) {
			return false; // this block sizes don't match,
			// this avoids index out of bounds exception.
			// the error should be found elsewhere.
			// THIS ASSUMPTION MAY NOT BE TRUE
		}
		for (int i = 0; i < indices.size(); ++i) {
			Ident declaredIdent = declaredIndices.getDimensionAt(i);
			Ident usedIdent = indices.getIdentAt(i);
			boolean same = declaredIdent.getName().equals(usedIdent.getName());
			boolean isSub = !same && isSubIndex(usedIdent, declaredIdent);
			isValid = isValid & (same || isSub);
			subBlock = subBlock || isSub;
		}
		return isValid && subBlock;
	}

	private boolean contains(IdentList list, Ident ident) {
		String name = ident.getName();
		boolean result = false;
		for (Object id : list.getArrayList()) {
			String idName = ((Ident) id).getName();
			result = result || name.equals(idName);
		}
		return result;
	}

	private ArrayList<Ident> getContractionResultIndices(DataBlock block1, DataBlock block2) {
		IdentList ind1 = block1.getIndices();
		IdentList ind2 = block2.getIndices();
		int ind1Size = ind1.size();
		int ind2Size = ind2.size();
		ArrayList<Ident> SharedInd = new ArrayList<Ident>();
		ArrayList<Ident> UnsharedInd = new ArrayList<Ident>();
		for (int i = 0; i < ind1Size; i++) {
			Ident id = ind1.getIdentAt(i);
			if (contains(ind2, id)) {
				SharedInd.add(id);
			} else {
				UnsharedInd.add(id);
			}
		}
		for (int i = 0; i < ind2Size; i++) {
			Ident id = ind2.getIdentAt(i);
			if (!contains(SharedInd, id))
				UnsharedInd.add(id);
		}
		return UnsharedInd;

	}

	private boolean contains(ArrayList<Ident> list, Ident ident) {
		String name = ident.getName();
		boolean result = false;
		for (Object id : list) {
			String idName = ((Ident) id).getName();
			result = result || name.equals(idName);
		}
		return result;
	}

	@Override
	public boolean visit(RelOp n) { /* nothing to do */
		return false;
	}

	@Override
	public void endVisit(RelOp n) { /* nop */
	}

	@Override
	public boolean visit(AllocIndexListopt n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(AllocIndexListopt n) { /* nop, checked in parent */
	}

	@Override
	public boolean visit(StringLitExpr n) {
		n.addType(STRING);
		return false;
	}

	@Override
	public void endVisit(StringLitExpr n) {/* nop */
	}

	@Override
	public boolean visit(StringLiteral n) { /*
											 * nothing to check here. Correct
											 * usage determined by grammar
											 */
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
		EnumSet<EType> t = ASTUtils.getIExprTypes(n.getExpression());
		check(t.contains(SCALAR) || t.contains(INT) || t.contains(INDEX) || t.contains(STRING) || t.contains(BLOCK)
				|| t.contains(ARRAY) || t.contains(CONTIG_BLOCK), n, "unexpected argument for print statement");
	}

	@Override
	public boolean visit(PrintlnStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(PrintlnStatement n) {
		EnumSet<EType> t = ASTUtils.getIExprTypes(n.getExpression());
		check(t.contains(SCALAR) || t.contains(INT) || t.contains(INDEX) || t.contains(STRING) || t.contains(BLOCK)
				|| t.contains(ARRAY) || t.contains(CONTIG_BLOCK), n, "unexpected argument for println statement");
	}

	// @Override
	// public boolean visit(GpuOn n) { /* no children */
	// return false;
	// }
	//
	// @Override
	// public void endVisit(GpuOn n) {
	// /* nop */
	// }
	//
	// @Override
	// public boolean visit(GpuOff n) { /* no children */
	// return false;
	// }
	//
	// @Override
	// public void endVisit(GpuOff n) {
	// /* nop */
	// }

	@Override
	public boolean visit(GPUSection n) {// TODO check for nested GPU Sections
		return true;
	}

	@Override
	public void endVisit(GPUSection n) {
	}

	@Override
	public boolean visit(GPUAllocate n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUAllocate n) {
		IArg arg = n.getArg();
		if (arg instanceof IdentArg) {
			IDec dec = ((IdentArg) arg).getDec();
			check(dec instanceof ScalarDec || (dec instanceof ArrayDec && isStatic(dec)) || dec instanceof IntDec, n,
					"incorrect argument");
			return;
		}
		check(arg instanceof DataBlockArg, n, "Illegal argument " + arg);
	}

	@Override
	public boolean visit(GPUFree n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUFree n) {
		IArg arg = n.getArg();
		if (arg instanceof IdentArg) {
			IDec dec = ((IdentArg) arg).getDec();
			check(dec instanceof ScalarDec || (dec instanceof ArrayDec && isStatic(dec)) || dec instanceof IntDec, n,
					"incorrect argument");
			return;
		}
		check(arg instanceof DataBlockArg, n, "Illegal argument " + arg);
	}

	@Override
	public boolean visit(GPUPut n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUPut n) {
		IArg arg = n.getArg();
		if (arg instanceof IdentArg) {
			IDec dec = ((IdentArg) arg).getDec();
			check(dec instanceof ScalarDec || (dec instanceof ArrayDec && isStatic(dec)) || dec instanceof IntDec, n,
					"incorrect argument");
			return;
		}
		check(arg instanceof DataBlockArg, n, "Illegal argument " + arg);
	}

	@Override
	public boolean visit(GPUGet n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(GPUGet n) {
		IArg arg = n.getArg();
		if (arg instanceof IdentArg) {
			IDec dec = ((IdentArg) arg).getDec();
			check(dec instanceof ScalarDec || (dec instanceof ArrayDec && isStatic(dec)) || dec instanceof IntDec, n,
					"incorrect argument");
			return;
		}
		check(arg instanceof DataBlockArg, n, "Illegal argument " + arg);
	}

	@Override
	public boolean visit(SetPersistent n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(SetPersistent n) {
		IDec dec = n.getIdent().getDec();
		if (!check(!isPredefined(dec), n, "predefined object " + n.getIdent().getName() + " cannot be persistent")) {
			return;
		}
		if (dec instanceof ScalarDec)
			return;
		if (check(dec instanceof ArrayDec, n, "set_persistent argument " + n.getIdent().getName()
				+ " must be an array or scalar")) {
			String typeName = ((ArrayDec) dec).getTypeName();
			check(typeName.equals("static") || typeName.equals("served") || typeName.equals("distributed"), n,
					"set_persistent array argument " + n.getIdent().getName()
							+ " must be a static, served, or distributed");

		}
	}

	@Override
	public boolean visit(RestorePersistent n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(RestorePersistent n) {
		IDec dec = n.getIdent().getDec();
		if (!check(!isPredefined(dec), n, "predefined object " + n.getIdent().getName()
				+ " cannot be target of restore persistent")) {
			return;
		}
		if (dec instanceof ScalarDec)
			return;
		if (check(dec instanceof ArrayDec, n, "restore_persistent argument " + n.getIdent().getName()
				+ " must be an array or scalar")) {
			String typeName = ((ArrayDec) dec).getTypeName();
			check(typeName.equals("static") || typeName.equals("served") || typeName.equals("distributed"), n,
					"restore_persistent array argument " + n.getIdent().getName()
							+ " must be a static, served, or distributed");
		}
	}

	@Override
	public boolean visit(AssertSame n) {
		return true;
	}

	@Override
	public void endVisit(AssertSame n) {
		Ident ident = n.getIdent();
		check(ident.getDec() instanceof ScalarDec, n, "argument to assert_same must be scalar");
	}

	/**
	 * determines whether a given node is within a pardo statement. If it is in
	 * a procedure, the proc is added to a list. At the end of the program, all
	 * call sites of the procs in the list will be checked.
	 * 
	 */
	public boolean isInPardo(IAst node) {
		IAst tnode = ASTUtils.getEnclosingPardoOrProcDec(node);
		if (tnode == null)
			return false;
		if (tnode instanceof PardoStatement)
			return true;
		if (tnode instanceof ProcDec) {
			callSitesInPardoToCheck.add((ProcDec) tnode);
		}
		return false;
	}

	HashSet<ProcDec> callSitesInPardoToCheck = new HashSet<ProcDec>();

	/**
	 * determines whether the given index has a defined value at this point.
	 * Either it is within a pardo or do loop that defines the variable, or it
	 * is in a proc, and is defined in all callsites of the proc. Since the
	 * callsites have not necessarily been visited yet, they are added to a
	 * list, indexAtCallSitesToCheck. These are checked at the end of the
	 * program.
	 * 
	 * @param n
	 *            ident representing index
	 * @param node
	 *            node whose ancesters should define the index
	 * @return
	 */
	// public boolean isDefinedInEnclosingScope(Ident n, IAst node) {
	// IndexDec dec = (IndexDec) n.getDec();
	// IAst tnode = ASTUtils.getEnclosingLoopOrIDec(dec, node);
	// if (tnode == null)
	// return false;
	// if (tnode instanceof DoStatement || tnode instanceof PardoStatement)
	// return true;
	// if (tnode instanceof ProcDec) {
	// // indexAtCallSitesToCheck.add(new IndexAtCallSites(n, dec, (ProcDec)
	// tnode));
	// }
	// return true;
	// }

	public boolean isDefinedInEnclosingScope(ASTNode n, IndexDec dec, IAst node) {
		IAst tnode = ASTUtils.getEnclosingLoopOrIDec(dec, node);
		if (tnode == null)
			return false;
		if (tnode instanceof DoStatement || tnode instanceof PardoStatement)
			return true;
		if (tnode instanceof ProcDec) {
			indexAtCallSitesToCheck.add(new IndexAtCallSites(n, dec, (ProcDec) tnode));
		}
		return true;
	}

	// /**
	// * determines whether the given index has a defined value at this point.
	// * Either it is within a pardo or do loop that defines the variable, or it
	// * is in a proc, and is defined in all callsites of the proc. Since the
	// * callsites have not necessarily been visited yet, they are added to a
	// * list, indexAtCallSitesToCheck. These are checked at the end of the
	// * program.
	// *
	// * @param n
	// * ident representing index
	// * @param node
	// * node whose ancesters should define the index
	// * @return
	// */
	// public boolean isDefinedInEnclosingScope(IdentExpr n, IAst node) {
	// IndexDec dec = (IndexDec) n.getDec();
	// IAst tnode = ASTUtils.getEnclosingLoopOrIDec(dec, node);
	// if (tnode == null)
	// return false;
	// if (tnode instanceof DoStatement || tnode instanceof PardoStatement)
	// return true;
	// if (tnode instanceof ProcDec) {
	// indexAtCallSitesToCheck.add(new IndexAtCallSites(n, dec, (ProcDec)
	// tnode));
	// }
	// return true;
	// }

	/** List of indices that need to be checked along with their call sites */
	List<IndexAtCallSites> indexAtCallSitesToCheck = new ArrayList<IndexAtCallSites>();

	public boolean checkCallSites() {
		for (int i = 0; i < indexAtCallSitesToCheck.size(); ++i) {
			indexAtCallSitesToCheck.get(i).checkCallSite();
		}
		return true;
	}

	/**
	 * IndexAtCallSites encapsulates an index and call sites for the procedure
	 * where this index is found. These need to be checked to ensure that the
	 * index has a defined value at that point in the program.
	 */
	class IndexAtCallSites {
		ArrayList<CallStatement> callSites;
		ProcDec procDec;
		ASTNode identOrIdentExpr;
		IndexDec indexDec;

		IndexAtCallSites(ASTNode identOrIdentExpr, IndexDec indexDec, ProcDec procDec) {
			callSites = ASTUtils.getCallSites(indexDec, procDec);
			this.identOrIdentExpr = identOrIdentExpr;
			this.procDec = procDec;
			this.indexDec = indexDec;
		}

		/**
		 * traverses the list and ensures that indices are defined.
		 * 
		 * @return
		 */
		boolean checkCallSite() {
			Iterator<CallStatement> iter = callSites.iterator();
			while (iter.hasNext()) {
				CallStatement callStatement = iter.next();
				check(isDefinedInEnclosingScope(identOrIdentExpr, indexDec, callStatement), identOrIdentExpr, "index "
						+ identOrIdentExpr + " not defined at call site " + callStatement + " at line "
						+ callStatement.getLeftIToken().getLine());
			}
			return true;
		}

	}

}
