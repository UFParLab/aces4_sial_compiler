package sial.parser.context;

import static sial.parser.context.ASTUtils.getIntVal;
import static sial.parser.context.ASTUtils.getEnclosingProc;
import static sial.parser.context.ASTUtils.isConstant;
import static sial.parser.context.ASTUtils.isPredefined;
import static sial.parser.context.ASTUtils.isStaticOrContiguousArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lpg.runtime.IAst;
import lpg.runtime.IAstVisitor;
import lpg.runtime.IToken;
import sial.code_gen.SipConstants;
import sial.parser.SialParser;
import sial.parser.SialParsersym;
import sial.parser.Ast.ASTNode;
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
import sial.parser.Ast.AssignOpPlus;
import sial.parser.Ast.AssignStatement;
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
import sial.parser.Ast.BinOpMinus;
import sial.parser.Ast.DoStatement;
import sial.parser.Ast.DoStatementSubIndex;
import sial.parser.Ast.DoubleLitPrimary;
import sial.parser.Ast.ExecuteStatement;
import sial.parser.Ast.ExitStatement;
import sial.parser.Ast.GetStatement;
import sial.parser.Ast.IAllocIndex;
import sial.parser.Ast.IArg;
import sial.parser.Ast.IAssignOp;
import sial.parser.Ast.IBinOp;
import sial.parser.Ast.IDec;
import sial.parser.Ast.IDimensionList;
import sial.parser.Ast.IExpression;
import sial.parser.Ast.IPrimary;
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
import sial.parser.context.AcesRangeChecks;
import sial.parser.context.AmbiguousNameException;
import sial.parser.context.SymbolTable;

/** Performs type checking on SIAL programs*/
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

	public List<IDec> constants = new ArrayList<IDec>();

	public TypeCheckVisitor(SialParser parser) {
		this.parser = parser;
	}


	/** Finds and returns the declaration of the given Ident, and sets the Ident's dec field.
	 * If the ident is unqualified and defined in multiple imported files, this will throw an
	 * AmbiguousNameException.
	 * @param s the Ident to look up 
	 * @return  the IDec representing the declaration of s
	 * @throws AmbiguousNameException
	 */
	private IDec findAndSetDec(Ident s) throws AmbiguousNameException {
		assert s != null : "findDec with null argument";
		String name = s.getName();
		IDec dec = symbolTable.lookup(name);
		s.setDec(dec);
		return dec;
	}

	/** Finds and returns the declaration of the given IdentPrimary (which is an identifier
	 * used in an expression), and sets the IdentPrimary's dec field.
	 * If the ident is unqualified and defined in multiple imported files, this will throw an
	 * AmbiguousNameException.
	 * @param s the IdentPrimary to look up 
	 * @return  the IDec representing the declaration of s
	 * @throws AmbiguousNameException
	 */
	private IDec findAndSetDec(IdentPrimary s) throws AmbiguousNameException {
		assert s != null : "findDec with null argument";
		String name = s.getName();
		IDec dec = symbolTable.lookup(name);
		s.setDec(dec);
		return dec;
	}


//This is obsolete code for language element sections.  Needs to be revisited if sections are supported
//	// The end of a section creates an implicit server barrier, which is treated the same as ServerBarrierStatement
//	// "execute aceshack_server_barrier"
//	private IDec findAndSetDec(Section n) {
//		assert n != null : "findDec with null argument";
//		try {
//			String name = "aceshack_server_barrier";
//			IDec dec = symbolTable.lookup(name);
//			n.setDec(dec);
//			return dec;
//		} catch (AmbiguousNameException e) {
//			emitError(n, e.getMessage());
//			return null;
//		}
//	}

	/** Finds and returns the declaration of the given AllocIndexIdent and sets its dec field.
	 * If the ident is unqualified and defined in multiple imported files, this will give an error.
	 * @param s the IdentPrimary to look up 
	 * @return  the IDec representing the declaration of s
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
	 *            the IdentPrimary to look up
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
		parser.emitError(node, message);
	}

/**
 * Convenience function for managing error checks.  If the condition is false, an error message is printed.
 * For the most part, the typing rules of the language are embedded in calls to check statements.
 * @param condition  The condition to evaluate
 * @param n The current ASTNode
 * @param msg  The Error message to report
 * @return whether the condition holds or not
 */
	boolean check(boolean condition, ASTNode n, String msg) {
		if (condition)
			return true;
		emitError(n, msg);
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
		check(symbolTable.insert(name, new ProgramDec(n)), n,
				"program name already used");
		return true;
	}

	@Override
	public void endVisit(Program n) {
		String name = n.getStartName();
		String endName = n.getendName().getName();
		check(name.equals(endName), n,
				" mismatched names at beginning and end of program");
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
		check(symbolTable.insert(name, n), id, "Duplicate declaration of "
				+ name);
		return true;
	}

	@Override
	public void endVisit(ScalarDec n) { /* nop */
	}

	/* int declaration */
	@Override
	public boolean visit(IntDec n) {
		check(isPredefined(n), n,
				"only constant, predefined ints currently implemented");
		Ident id = n.getIdent();
		String name = n.getName();
		check(symbolTable.insert(name, n), id, "Duplicate declaration of "
				+ name);
		constants.add(n); // add to constant list
		return true;
	}

	@Override
	public void endVisit(IntDec n) { /* nop */
	}

	/* array declaration */
	@Override
	public boolean visit(ArrayDec n) {
//		check(!isConstant(n), n, "Constant arrays are not implemented");
		Ident id = n.getIdent();
		String name = n.getName();
		check(symbolTable.insert(name, n), id, "Duplicate declaration of "
				+ name);
		return true;
	}

	@Override
	public void endVisit(ArrayDec n) {/* nop */
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
				check((dec instanceof IndexDec || dec instanceof SubIndexDec),
						ident,
						ident
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
		check(!isConstant(n), n, "index cannot be constant");
		Ident id = n.getIdent();
		String name = n.getName();
		check(symbolTable.insert(name, n), id, "Duplicate declaration of "
				+ name);
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
		if (!check(symbolTable.insert(name, n), id, "Duplicate declaration of "
				+ name))
				return false;
		// check that parent index is 1) already declared and 2) is an index
		// type
		Ident parentId = n.getParentIdent();
		String parentName = n.getParentName();
		IDec parentNode;
		try {
			parentNode = findAndSetDec(parentId);

			check(parentNode instanceof IndexDec, parentId, parentName
					+ " must be declared as an index type");
		} catch (AmbiguousNameException e) {
			assert false : "this shouldn't happen";
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void endVisit(SubIndexDec n) { /*nop*/
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
		check(getIntVal(n) >= 1, n, "index range must be at least 1");
		return false;
	}

	@Override
	public void endVisit(IntLitRangeVal n) { /* nop */
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
		check(symbolTable.insert(name, n), n.getIdent(),
				"Duplicate declaration of " + name);
		check( getEnclosingProc(n) == null, n, "nested procedures are not allowed");
		// visit children
		return true;
	}

	@Override
	public void endVisit(ProcDec n) {
		String startName = n.getName();
		String endName = n.getendIdent().getName();
		check(startName.equals(endName), n,
				"mismatched names at beginning and end of procedure");
	}

	//special instructions can only be declared once.  No qualification is possible
	@Override
	public boolean visit(SpecialDec n) {
		String name = n.getName();
		check(symbolTable.insert(name, n), n, "Duplicate declaration of "
				+ name);
		Ident signature = n.getSignature();
		if (signature == null){
			n.setNumArgs(0);
		}
		else {
			String args = signature.getName(); // lower case version of string that was given as signature
			int numArgs = args.length();
			for (int i = 0; i < numArgs; ++i){
				char intent_val = args.charAt(i);
				switch(intent_val){
				case 'r': case 'w': case 'u': break; //there are the legal options
				default:
					check(false, n, "Illegal character " + intent_val  + " in special instruction signature descriptor");
				}
			}
			n.setNumArgs(numArgs);
		}
		return false;  //don't visit children
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
	public void endVisit(WhereClause n) { /*At least one argument must be an index.  Together with type checking of relational statement, this implies the other is an int*/
		RelationalExpression e = n.getRelationalExpression();
		IUnaryExpression eleft = e.getUnaryExpressionLeft();
		IUnaryExpression eright = e.getUnaryExpressionRight();
		boolean eLeftIsIndex = eleft instanceof IdentPrimary
				&& (((IdentPrimary) eleft).getDec() instanceof IndexDec || ((IdentPrimary) eleft).getDec() instanceof SubIndexDec);
		boolean eRightIsIndex = eright instanceof IdentPrimary
				&& (((IdentPrimary) eright).getDec() instanceof IndexDec || ((IdentPrimary) eright).getDec() instanceof SubIndexDec);
	    check( (eLeftIsIndex || eRightIsIndex), n, "At least one argument in a where clause must be an index");
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
		check(dec instanceof ProcDec, n, n.getIdent().toString()
				+ " not declared as proc");
		((ProcDec) dec).getCallSites().add(n);
		check( !inpardo || !procsContainingPardo.contains(dec), n, 
				"Procedure containing a pardo is being called from inside a pardo");
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
//		IDec dec = findAndSetDec(n);
//		check(dec != null,
//				n,
//				"current implementation requires declaration of special aces_hacks_server_barrier");
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
		check(dec instanceof IndexDec, n, identTop.toString()
				+ " is not an index");
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
		check(startParentIndexName.equals(endParentIndexName),
				startParentIndexIdent,
				"mismatched parent index names at start and end of do loop");
		// check types
		// 1. index must be a subindex
		// 2. parent index must be an index, and must match type of parent index
		// in declaration
		IDec dec = startIndexIdent.getDec();
		if (check(dec instanceof SubIndexDec, startIndexIdent,
				"do loop sub index must be declared as subindex type")) {
			SubIndexDec subIndexDec = (SubIndexDec) dec;
			IDec parentIndexDecFromDec = subIndexDec.getParentIdent().getDec();
			IDec parentIndexDecFromUse = startParentIndexIdent.getDec();
			check(parentIndexDecFromDec.equals(parentIndexDecFromUse),
					startParentIndexIdent,
					"do loop parent index type must match type of declared parent index");
		}
	}

	boolean inpardo = false;
	Set<ProcDec> procsContainingPardo = new HashSet<ProcDec>();
	
	@Override
	public boolean visit(PardoStatement n) {
		check(!inpardo, n, "pardo nested inside another pardo");
		inpardo = true;
		procsContainingPardo.add(getEnclosingProc(n)); //adds null if not in proc
		IdentList startIndices = n.getStartIndices();
		check(startIndices != null && startIndices.size() > 0, n,
				"missing indices in pardo statement");
		/* visit children */
		return true;
	}

	@Override
	public void endVisit(PardoStatement n) {
		IdentList startIdents = n.getStartIndices();
		IdentList endIdents = n.getEndIndices();
		inpardo = false;
		if (!check(startIdents.size() == endIdents.size(), n, "unmatched indices at beginning and end of pardo"))
			return;
		for (int i = 0; i < n.getStartIndices().size(); i++){
			if (! check( startIdents.getIdentAt(i).getName().equals(endIdents.getIdentAt(i).getName()), n, "unmatched indices at beginning and end of pardo"))
				return;
		}
	}

//TODO add check that pardos are independent
	@Override
    public boolean visit(Section n){
		//all statements must be pardo statements
		List statements = n.getStatementList().getList();
		for (Object s: statements){
			check( (s instanceof PardoStatement), (ASTNode)s, "illegal statement in section, must be a pardo");
		}
		return true;
	}
	
//	@Override
//	public void endVisit(Section n){
//		IDec dec = findAndSetDec(n);
//		check(dec != null,
//				n,
//				"current implementation requires declaration of special aces_hacks_server_barrier");
//	}


	@Override
	public boolean visit(ExitStatement n) { /* nothing to check */
		return false;
	}

	@Override
	public void endVisit(ExitStatement n) { /* nop */
	}

	@Override
	public boolean visit(CycleStatement n) {
		check(false, n, "cycle statement not implemented");
		return false;
	}

	@Override
	public void endVisit(CycleStatement n) { /* nop */
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
		if (!(check(dec instanceof ArrayDec
				&& ((ArrayDec) dec).getTypeName().equals("local"), n, n
				.getIdent().toString() + "must be declared as local array"))) {
			return;
		}
		// check indices
		ArrayDec arrayDec = (ArrayDec) dec;
		DimensionList decDims = arrayDec.getDimensionList();
		if (n.getAllocIndexListopt() == null)
			return; // no indices given
		AllocIndexList allocDims = n.getAllocIndexListopt().getAllocIndexList();
		if (!check(decDims.size() == allocDims.size(), n,
				"index list in allocate statement incompatible with declaration of "
						+ n.getIdent()))
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
				check(declaredIDec instanceof IndexDec,
						(AllocIndexIdent) index,
						"index in allocate statement incompatible with declaration of array");
				String indexType = ((IndexDec) indexDec).getTypeName();

				IndexDec declaredDec = (IndexDec) declaredIDec;
				String declaredIndexType = declaredDec.getTypeName();
				check(indexType.equals(declaredIndexType),
						(AllocIndexIdent) index,
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
			check(indexParent.equals(declaredIndexParent),
					(AllocIndexIdent) index,
					"index in allocate statement incompatible with declaration of array; superindices must match");
			return;
		}

	}

	@Override
	public boolean visit(DeallocateStatement n) { /* visit children */
		return true;
	}

	// TODO this was copied from endVisit(AllocateStatement)
	@Override
	public void endVisit(DeallocateStatement n) { // check array name
		IDec dec = n.getIdent().getDec();
		if (!(check(dec instanceof ArrayDec
				&& ((ArrayDec) dec).getTypeName().equals("local"), n, n
				.getIdent().toString() + "must be declared as local array"))) {
			return;
		}
		// check indices
		ArrayDec arrayDec = (ArrayDec) dec;
		DimensionList decDims = arrayDec.getDimensionList();
		if (n.getAllocIndexListopt() == null)
			return; // no indices given
		AllocIndexList allocDims = n.getAllocIndexListopt().getAllocIndexList();
		if (!check(decDims.size() == allocDims.size(), n,
				"index list in allocate statement incompatible with declaration of "
						+ n.getIdent()))
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
				check(declaredIDec instanceof IndexDec,
						(AllocIndexIdent) index,
						"index in allocate statement incompatible with declaration of array");
				String indexType = ((IndexDec) indexDec).getTypeName();

				IndexDec declaredDec = (IndexDec) declaredIDec;
				String declaredIndexType = declaredDec.getTypeName();
				check(indexType.equals(declaredIndexType),
						(AllocIndexIdent) index,
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
			check(indexParent.equals(declaredIndexParent),
					(AllocIndexIdent) index,
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
		if (!(check(dec instanceof ArrayDec
				&& ((ArrayDec) dec).getTypeName().equals("distributed"), n, n
				.getIdent().toString()
				+ " must be declared as distributed array"))) {
			return;
		}
		// check indices
		ArrayDec arrayDec = (ArrayDec) dec;
		DimensionList decDims = arrayDec.getDimensionList();
		if (n.getAllocIndexListopt() == null)
			return; // no indices given
		AllocIndexList allocDims = n.getAllocIndexListopt().getAllocIndexList();
		if (!check(decDims.size() == allocDims.size(), n,
				"index list in allocate statement incompatible with declaration of "
						+ n.getIdent()))
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
				check(declaredIDec instanceof IndexDec,
						(AllocIndexIdent) index,
						"index in create statement incompatible with declaration of array");
				String indexType = ((IndexDec) indexDec).getTypeName();

				IndexDec declaredDec = (IndexDec) declaredIDec;
				String declaredIndexType = declaredDec.getTypeName();
				check(indexType.equals(declaredIndexType),
						(AllocIndexIdent) index,
						"index in create statement incompatible with declaration of array");
				return;
			}
			// indexDec is a SubIndex, parent declarations must be the same
			// This rule may be too strict.
			check(declaredIDec instanceof SubIndexDec, (AllocIndexIdent) index,
					"index in create statement incompatible with declaration of array");
			String indexParent = ((SubIndexDec) indexDec).getParentName();
			SubIndexDec declaredDec = (SubIndexDec) declaredIDec;
			String declaredIndexParent = declaredDec.getParentName();
			check(indexParent.equals(declaredIndexParent),
					(AllocIndexIdent) index,
					"index in create statement incompatible with declaration of array; superindices must match");
			return;
		}

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
		if (!(check(dec instanceof ArrayDec
				&& ((ArrayDec) dec).getTypeName().equals("distributed"), n, n
				.getIdent().toString()
				+ "must be declared as distributed array"))) {
			return;
		}
		// check indices
		ArrayDec arrayDec = (ArrayDec) dec;
		DimensionList decDims = arrayDec.getDimensionList();
		if (n.getAllocIndexListopt() == null)
			return; // no indices given
		AllocIndexList allocDims = n.getAllocIndexListopt().getAllocIndexList();
		if (!check(decDims.size() == allocDims.size(), n,
				"index list in allocate statement incompatible with declaration of "
						+ n.getIdent()))
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
				check(declaredIDec instanceof IndexDec,
						(AllocIndexIdent) index,
						"index in allocate statement incompatible with declaration of array");
				String indexType = ((IndexDec) indexDec).getTypeName();

				IndexDec declaredDec = (IndexDec) declaredIDec;
				String declaredIndexType = declaredDec.getTypeName();
				check(indexType.equals(declaredIndexType),
						(AllocIndexIdent) index,
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
			check(indexParent.equals(declaredIndexParent),
					(AllocIndexIdent) index,
					"index in allocate statement incompatible with declaration of array; superindices must match");
			return;
		}
	}

	@Override
	public boolean visit(PutStatement n) {
		IAssignOp op = n.getAssignOp();
		check(op instanceof AssignOpPlus || op instanceof AssignOpEqual, n,
				"illegal operator;  only = and += are supported");
		return true;
	}

	@Override
	public void endVisit(PutStatement n) {
		DataBlock lhs = n.getLHSDataBlock();
		DataBlock rhs = n.getRHSDataBlock();
		// check that lhs is a distributed array
		IDec dec = lhs.getIdent().getDec();
		if (!(check(dec instanceof ArrayDec
				&& ((ArrayDec) dec).getTypeName().equals("distributed"), n, lhs
				.getIdent().toString() + "must be a distributed array"))) {
			return;
		}
		checkCompatibleBlocks(n, lhs, rhs);
	}


	private void checkCompatibleBlockWithTranspose(ASTNode n, DataBlock lhs,
			DataBlock rhs) {
		// check that indices on both sides match exactly, or are a permutation
		IdentList lhsIndices = lhs.getIndices();
		IdentList rhsIndices = rhs.getIndices();
		int lhsSize = lhsIndices.size();
		int rhsSize = rhsIndices.size();
		int minIndices = lhsSize < rhsSize ? lhsSize : rhsSize;
		if (lhsSize == rhsSize) {// must  be a permutation

			for (int i = 0; i < lhsSize; i++) {
				if (!check(contains(rhsIndices, lhsIndices.getIdentAt(i)), n,
						"incompatible index lists"))
					return;
			}
			return;
		}
		// otherwise one is longer than the other--extras must be simple indices
		for (int i = 0; i < minIndices; i++) { // check that common "positions"
												// have the same contents
			if (!check(
					lhsIndices.getIdentAt(i).getName()
							.equals(rhsIndices.getIdentAt(i).getName()), n,
					"inconsistent index lists"))
				return;
		}
		for (int i = minIndices; i < lhsSize; i++) { // if there are more
														// indices on the lhs,
														// make sure they are
														// simple
			if (!check(
					((IndexDec) lhsIndices.getIdentAt(i).getDec())
							.getTypeName().equals("index"),
					n,
					"extra indices on left side must be simple indices, i.e. the index type must be \"index\""))
				return;
		}
		for (int i = minIndices; i < rhsSize; i++) { // if there are more
														// indices on the rhs,
														// make sure they are
														// simple
			if (!check(
					((IndexDec) rhsIndices.getIdentAt(i).getDec())
							.getTypeName().equals("index"),
					n,
					"extra indices on right side must be simple indices, i.e. the index type must be \"index\""))
				return;
		}
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
			if (!check(
					lhsIndices.getIdentAt(i).getName()
							.equals(rhsIndices.getIdentAt(i).getName()), n,
					"inconsistent index lists"))
				return;
		}
		for (int i = minIndices; i < lhsSize; i++) { // if there are more
														// indices on the lhs,
														// make sure they are
														// simple
			if (!check(
					((IndexDec) lhsIndices.getIdentAt(i).getDec())
							.getTypeName().equals("index"),
					n,
					"extra indices on left side must be simple indices, i.e. the index type must be \"index\""))
				;
		}
		for (int i = minIndices; i < rhsSize; i++) { // if there are more
														// indices on the rhs,
														// make sure they are
														// simple
			if (!check(
					((IndexDec) rhsIndices.getIdentAt(i).getDec())
							.getTypeName().equals("index"),
					n,
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
		if (!(check(dec instanceof ArrayDec
				&& ((ArrayDec) dec).getTypeName().equals("distributed"), n, n
				.getDataBlock().getIdent().toString()
				+ "must be a distributed array"))) {
			return;
		}
	}

	@Override
	public boolean visit(PrepareStatement n) {
		IAssignOp op = n.getAssignOp();
		check(op instanceof AssignOpPlus || op instanceof AssignOpEqual, n,
				"illegal operator;  only = and += are supported");
		return true;
	}

	@Override
	public void endVisit(PrepareStatement n) {
		DataBlock lhs = n.getLHSDataBlock();
		DataBlock rhs = n.getRHSDataBlock();
		// check that lhs is a served array
		IDec dec = lhs.getIdent().getDec();
		if (!(check(dec instanceof ArrayDec
				&& ((ArrayDec) dec).getTypeName().equals("served"), n, lhs
				.getIdent().toString() + " must be a served array"))) {
			return;
		}
		// check that indices on both sides match exactly, or that there is an
		// extra index which is a simple index
		IdentList lhsIndices = lhs.getIndices();
		IdentList rhsIndices = rhs.getIndices();
		int lhsSize = lhsIndices.size();
		int rhsSize = rhsIndices.size();
		int minIndices = lhsSize < rhsSize ? lhsSize : rhsSize;
		for (int i = 0; i < minIndices; i++) { // check that common "positions"
												// have the same contents
			if (!check(
					lhsIndices.getIdentAt(i).getName()
							.equals(rhsIndices.getIdentAt(i).getName()), n,
					"inconsistent index lists"))
				return;
		}
		for (int i = minIndices; i < lhsSize; i++) { // if there are more
														// indices on the lhs,
														// make sure they are
														// simple
			if (!check(
					((IndexDec) lhsIndices.getIdentAt(i).getDec())
							.getTypeName().equals("index"),
					n,
					"extra indices on left side must be simple indices, i.e. the index type must be \"index\""))
				;
		}
		for (int i = minIndices; i < rhsSize; i++) { // if there are more
														// indices on the rhs,
														// make sure they are
														// simple
			if (!check(
					((IndexDec) rhsIndices.getIdentAt(i).getDec())
							.getTypeName().equals("index"),
					n,
					"extra indices on right side must be simple indices, i.e. the index type must be \"index\""))
				;
		}
	}

	@Override
	public boolean visit(RequestStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(RequestStatement n) {
		IDec dec = n.getDataBlock().getIdent().getDec();
		if (!(check(dec instanceof ArrayDec
				&& ((ArrayDec) dec).getTypeName().equals("served"), n, n
				.getDataBlock().getIdent().toString()
				+ "must be a served array"))) {
			return;
		}
	}

	@Override
	public boolean visit(PrequestStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(PrequestStatement n) {
		DataBlock lhs = n.getLHSDataBlock();
		DataBlock rhs = n.getRHSDataBlock();
		// check that lhs is a temp array
		IDec lhsDec = lhs.getIdent().getDec();
		if (!(check(lhsDec instanceof ArrayDec
				&& ((ArrayDec) lhsDec).getTypeName().equals("temp"), n, lhs
				.getIdent().toString() + " must be a temp array"))) {
			return;
		}
		// check that rhs is served array
		IDec rhsDec = rhs.getIdent().getDec();
		if (!(check(rhsDec instanceof ArrayDec
				&& ((ArrayDec) rhsDec).getTypeName().equals("served"), n, lhs
				.getIdent().toString() + " must be a served array"))) {
			return;
		}
		// check that indices on both sides have compatible types.
		// this means that the indices are identical until some point where the lhs indices change to 
		//  something of index type
		//  example  T(a,b,i,j) = S(a,b,c,d) where i and j are declared index.
		IdentList lhsIndices = lhs.getIndices();
		IdentList rhsIndices = rhs.getIndices();
		if (!check(lhsIndices.size() == rhsIndices.size(), n,
				"index lists do not have the same size")){
			return;
		}

		for (int i = 0; i < lhsIndices.size(); i++) {
			String lhsType = ((IndexDec) lhsIndices.getIdentAt(i).getDec())
					.getTypeName();
			String rhsType = ((IndexDec) rhsIndices.getIdentAt(i).getDec())
					.getTypeName();

			if (!check(lhsType.equals(rhsType) || lhsType.equals("index"), n,
					"inconsistent types for indices " + lhsType + " and "
							+ rhsType))
				return;
		}
	}

	@Override
	public boolean visit(CollectiveStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(CollectiveStatement n) {
		IDec lhsDec = n.getLHSIdent().getDec();
		check(lhsDec instanceof ScalarDec, n,
				"Collective requires scalar arguments");
		IDec rhsDec = n.getRHSIdent().getDec();
		check(rhsDec instanceof ScalarDec, n,
				"Collective requires scalar arguments");
		IAssignOp op = n.getAssignOp();
		check(op instanceof AssignOpPlus, n, "Collective operator must be +=");
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
		if (!check(dec instanceof SpecialDec, n, n.getIdent()
				.toString() + " not declared as special instruction")) return;
		int expected_args = ((SpecialDec)dec).getNumArgs();
		int num_args = n.getArgList().size();
		check (num_args == expected_args, n, 
				"execute  " + n.getIdent() + " has "+num_args+" arguments but expects " + expected_args);
		}

	@Override
	public boolean visit(DestroyStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DestroyStatement n) {
		// check that argument is a served array
		IDec dec = n.getIdent().getDec();
		if (!(check(dec instanceof ArrayDec
				&& ((ArrayDec) dec).getTypeName().equals("served"), n,
				dec.toString() + "must be a served array"))) {
			return;
		}
	}

	@Override
	public boolean visit(DataBlock n) { /* visit children */
		return true;
	}

	/* checks that given indices have same type as the declaration, or be a subindex of the declared type */

	@Override
	public void endVisit(DataBlock n) {
		IDec identDec = n.getIdent().getDec();
		if (!check(identDec instanceof ArrayDec, n, n.getIdent()
				+ " must be an array"))
			return;
		IdentList indices = n.getIndices();
		DimensionList declaredDimensionList = ((ArrayDec) identDec)
				.getDimensionList();
		if (!check(indices.size() == declaredDimensionList.size(), n,
				"number of indices of " + n.getIdent()
						+ " does not match declaration"))
			return;
		for (int i = 0; i < indices.size(); i++) {
			// get type of index
			IDec indexDec = indices.getIdentAt(i).getDec();
			if (!check(indexDec instanceof IndexDec
					|| indexDec instanceof SubIndexDec, n, n.getIndices()
					.getIdentAt(i).getIDENTIFIER()
					+ " must be an index or subindex"))
				return;
			String indexType = (indexDec instanceof IndexDec) ? ((IndexDec) indexDec)
					.getTypeName() : ((IndexDec) ((SubIndexDec) indexDec)
					.getParentIdent().getDec()).getTypeName();
			// get type of dimension in declaration
			IDec dimensionDec = declaredDimensionList.getDimensionAt(i)
					.getDec();
			// get a string representing type, could be some index or subindex
			String dimensionType = (dimensionDec instanceof IndexDec) ? ((IndexDec) dimensionDec)
					.getTypeName() : ((IndexDec) ((SubIndexDec) dimensionDec)
					.getParentIdent().getDec()).getTypeName();
			if (!check(indexType.equals(dimensionType), n, indices
					.getIdentAt(i).getIDENTIFIER()
					+ " and "
					+ declaredDimensionList.getDimensionAt(i).getIDENTIFIER()
					+ "have incompatible types"))
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
	public boolean visit(AllocIndexIdent n) {
		IDec decl = findAndSetDec(n);
		check(decl != null, n, n.toString() + " not declared");
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
	public boolean visit(RelationalExpression n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(RelationalExpression n) {
        IUnaryExpression eleft = n.getUnaryExpressionLeft();
        IUnaryExpression eright = n.getUnaryExpressionRight();
        boolean eLeftHasDoubleType = (eleft instanceof IdentPrimary && ((IdentPrimary)eleft).getDec() instanceof ScalarDec) 
        		|| (eleft instanceof DoubleLitPrimary) 
        		|| ((eleft instanceof NegatedUnary) && (((NegatedUnary)eleft).getPrimary() instanceof DoubleLitPrimary));
        //has Int type if index, subindex, int, or pos or neg int literal
        boolean eLeftHasIntType = (eleft instanceof IdentPrimary 
        		    &&  (((IdentPrimary)eleft).getDec() instanceof IntDec || ((IdentPrimary)eleft).getDec() instanceof IndexDec || ((IdentPrimary)eleft).getDec() instanceof SubIndexDec))
        		|| (eleft instanceof IntLitPrimary)
        		|| ((eleft instanceof NegatedUnary) && (((NegatedUnary)eleft).getPrimary() instanceof IntLitPrimary));	
        boolean eRightHasDoubleType = (eright instanceof IdentPrimary && ((IdentPrimary)eright).getDec() instanceof ScalarDec) 
        		|| (eright instanceof DoubleLitPrimary) 
        		|| ((eright instanceof NegatedUnary) && (((NegatedUnary)eright).getPrimary() instanceof DoubleLitPrimary));
        boolean eRightHasIntType = (eright instanceof IdentPrimary 
        		   && (((IdentPrimary)eright).getDec() instanceof IntDec || ((IdentPrimary)eright).getDec() instanceof IndexDec || ((IdentPrimary)eright).getDec() instanceof SubIndexDec))
        		|| (eright instanceof IntLitPrimary)
        		|| ((eright instanceof NegatedUnary) && (((NegatedUnary)eright).getPrimary() instanceof IntLitPrimary));	
        check( (eLeftHasDoubleType && eRightHasDoubleType) || (eLeftHasIntType && eRightHasIntType), n, 
        		"Both operands of relational expression must have same type, which must be int or scalar");
	}



	
	@Override
	public boolean visit(BinaryExpression n) {/* visit children */
		// type checking of binary expressions done in AssignmentStatement
		return true;
	}

	@Override
	public void endVisit(BinaryExpression n) {/* nop */
	}

	@Override
	public boolean visit(NegatedUnary n) {/* visit child*/
		return true;
	}

	@Override
	public void endVisit(NegatedUnary n) {
		// current compiler only allows negating literals
		// TODO upgrade this
		IPrimary primary = n.getPrimary();
		check((primary instanceof IntLitPrimary || primary instanceof DoubleLitPrimary),
				n, "negation of identifier not implemented");
	}

	@Override
	public boolean visit(IntLitPrimary n) { /* nothing to do */
		return false;
	}

	@Override
	public void endVisit(IntLitPrimary n) { /* nop */
	}

	@Override
	public boolean visit(DoubleLitPrimary n) { /* nothing to do */
		return false;
	}

	@Override
	public void endVisit(DoubleLitPrimary n) { /* nop */
	}

	@Override
	public boolean visit(Ident n) {
		try {
			IDec decl = findAndSetDec(n);
			check(decl != null, n, n.toString() + " not declared");
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
	public boolean visit(DataBlockPrimary n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(DataBlockPrimary n) { /* nop */
	}

	@Override
	public boolean visit(IdentPrimary n) {
		try {
			IDec decl = findAndSetDec(n);
			check(decl != null, n, n.toString() + " not declared");
		} catch (AmbiguousNameException e) {
			emitError(n, e.getMessage());
		}
		return false;
	}

	@Override
	public void endVisit(IdentPrimary n) { /* nop */
	}

	@Override
	public boolean visit(ModifierList n) { /* nothing to check */
		return false;
	}

	@Override
	public void endVisit(ModifierList n) { /* nop */
	}

//	@Override
//	public boolean visit(ConstantModifier n) { /* nothing to check */
//		return false;
//	}
//
//	@Override
//	public void endVisit(ConstantModifier n) { /* nop */
//	}

	@Override
	public boolean visit(PredefinedModifier n) { /* nothing to check */
		return false;
	}

	@Override
	public void endVisit(PredefinedModifier n) { /* nop */
	}

	@Override
	public boolean visit(PersistentModifier n) { /* nothing to check */
		return false;
	}

	@Override
	public void endVisit(PersistentModifier n) { /* nop */
	}

	@Override
	public boolean visit(ArgList n) {
		// TODO for now, just visit children. Later check type against some sort
		// of declaration
		return true;
	}

	/** These are the special superinstruction that take non-static array arguments without a block selector 
	 * 	blocks_to_list
	 *  list_to_blocks
	 *  array_copy
	 *  */
	boolean nonstatic_noselector_array_allowed(ArgList n){
		String instruction_name  = ((ExecuteStatement) n.getParent()).getIdent().getName();
		if (instruction_name.equals("blocks_to_list")) return true;
		if (instruction_name.equals("list_to_blocks")) return true;
		if (instruction_name.equals("array_copy")) return true;
		return false;
	}
	
//  This version gives an error if superinstruction called with implicit selector	
	@Override
	public void endVisit(ArgList n) {
		// if an argument is an IdentPrimary, it must either be a scalar or a static array
		for (int i = 0; i < n.size(); ++i){
			IArg arg = n.getArgAt(i);
			if (arg instanceof IdentPrimary){
				IdentPrimary idparg = (IdentPrimary)arg;
				IDec dec = idparg.getDec();
				if (dec instanceof ArrayDec){
					check(isStaticOrContiguousArray(dec) || nonstatic_noselector_array_allowed(n), idparg, 
							"execute " + ((ExecuteStatement) n.getParent()).getIdent().getName() +
							" has array argument " + idparg.getName() + " which is neither static nor contigous");
				}
			}
		}
	}
	

	@Override
	public boolean visit(AssignStatement n) { /* visit children */
		return true;
	}

	@Override
	public void endVisit(AssignStatement n) {
		IScalarOrBlockVar lhs = n.getScalarOrBlockVar();
//		IAssignOp op = n.getAssignOp();
		IExpression expr = n.getExpression();
		if (lhs instanceof Ident) {
			if (isInt(expr)) {
				check(((Ident) lhs).getDec() instanceof IntDec
						|| ((Ident) lhs).getDec() instanceof ScalarDec, n,
						"incompatible types in assignment");
			} else if (isScalar(expr)) {
				check(((Ident) lhs).getDec() instanceof ScalarDec, n,
						"incompatible types in assignment");
			} else if (expr instanceof BinaryExpression) {
				check(binaryIsInt((BinaryExpression) expr)
						|| binaryIsScalar((BinaryExpression) expr), n,
						"right hand side ");

			}
		} else { // (lhs instanceof DataBlock)

			IdentList lhsIndices = ((DataBlock) lhs).getIndices();
			if (isInt(expr) || isScalar(expr))
				return;
			if (expr instanceof DataBlockPrimary){
//			   boolean isSlice = checkStrictSubOrEq((DataBlock) lhs,
//						((DataBlockPrimary) expr).getDataBlock());
			   boolean isSlice = hasDeclaredIndices( (DataBlock)lhs ) && isSubBlock(((DataBlockPrimary) expr).getDataBlock() );
			   if (isSlice){ //lhs is subblock of rhs
				   n.setSlice(true);
				   return;
			   }
			   boolean isInsert = hasDeclaredIndices( ((DataBlockPrimary) expr).getDataBlock() ) && isSubBlock( (DataBlock)lhs );
			   if (isInsert){ //rhs is subblock of lsh
				   n.setInsert(true);
				   return;
			   }					
				checkCompatibleBlockWithTranspose(n, (DataBlock) lhs,
						((DataBlockPrimary) expr).getDataBlock());
				return;
			}
			if (expr instanceof DataBlock) {
				assert false;
				return;
			}
			if (expr instanceof BinaryExpression) {
				BinaryExpression binExpr = (BinaryExpression) expr;
				IUnaryExpression expr1 = binExpr.getExpr1();
				IUnaryExpression expr2 = binExpr.getExpr2();

				if (expr1 instanceof DataBlockPrimary
						&& expr2 instanceof DataBlockPrimary) {
					DataBlock block1 = ((DataBlockPrimary) expr1)
							.getDataBlock();
					DataBlock block2 = ((DataBlockPrimary) expr2)
							.getDataBlock();
					IBinOp binOp = binExpr.getBinOp();
					if (binOp instanceof BinOpStar
							|| binOp instanceof BinOpTensor) { // rhs is a
																// contraction
																// or tensor
						ArrayList<Ident> exprIndices = getContractionResultIndices(
								block1, block2);
						int exprIndicesSize = exprIndices.size();
						ArrayList<String> names = new ArrayList(exprIndicesSize);
						for (int i = 0; i < exprIndicesSize; i++) {
							names.add(exprIndices.get(i).getName());
						}
						if (!check(lhsIndices.size() == exprIndices.size(), n,
								"left and right sides have different dimensions"))
							return;
						for (int i = 0; i < lhsIndices.size(); i++) {
							String lhsIndexName = lhsIndices.getIdentAt(i)
									.getName();
							if (!check(names.contains(lhsIndexName), n,
									"indices do not match on left and right hand sides"))
								return;
						}
						return;
					}
					if (binOp instanceof BinOpPlus || binOp instanceof BinOpMinus) {
						IdentList block1indices = block1.getIndices();
						IdentList block2indices = block2.getIndices();
						if (!check(lhsIndices.size() == block1indices.size()
								&& lhsIndices.size() == block2indices.size(),
								n, "number of indices does not match"))
							return;
						for (int i = 0; i < lhsIndices.size(); i++) {
							String lhsIndexName = lhsIndices.getIdentAt(i)
									.getName();
							String block1IndexName = block1indices
									.getIdentAt(i).getName();
							String block2IndexName = block2indices
									.getIdentAt(i).getName();
							if (!check(lhsIndexName.equals(block1IndexName)
									&& lhsIndexName.equals(block2IndexName), n,
									"indices do not match"))
								return;
						}
						return;

					}
					assert false;
				} else if (expr1 instanceof DataBlockPrimary && isScalar(expr2)) {
					DataBlock block1 = ((DataBlockPrimary) expr1)
							.getDataBlock();
					IdentList block1indices = block1.getIndices();
					if (!check(lhsIndices.size() == block1indices.size(), n,
							"number of indices does not match"))
						return;
					for (int i = 0; i < lhsIndices.size(); i++) {
						String lhsIndexName = lhsIndices.getIdentAt(i)
								.getName();
						String block1IndexName = block1indices.getIdentAt(i)
								.getName();
						if (!check(lhsIndexName.equals(block1IndexName), n,
								"indices do not match"))
							return;
					}
					return;
				} else if (expr2 instanceof DataBlockPrimary && isScalar(expr1)) {
					DataBlock block2 = ((DataBlockPrimary) expr2)
							.getDataBlock();
					IdentList block2indices = block2.getIndices();
					if (!check(lhsIndices.size() == block2indices.size(), n,
							"number of indices does not match"))
						return;
					for (int i = 0; i < lhsIndices.size(); i++) {
						String lhsIndexName = lhsIndices.getIdentAt(i)
								.getName();
						String block2IndexName = block2indices.getIdentAt(i)
								.getName();
						if (!check(lhsIndexName.equals(block2IndexName), n,
								"indices do not match"))
							return;
					}
					return;
				}

			}
			return;
		}
	}


//	returns true if index1 is a subindex of index2
	boolean isSubIndex(Ident index1, Ident index2){
		IDec dec1 = index1.getDec();
		IDec dec2 = index2.getDec();
		return dec1 instanceof SubIndexDec && ((SubIndexDec)dec1).getParentIdent().getDec() == dec2;
	}
   
	//returns true if given indices are same as declared
	boolean hasDeclaredIndices(DataBlock block){
		   IdentList indices = block.getIndices();
		   IDec dec = block.getIdent().getDec();
		   if (dec == null) return false;
		   DimensionList declaredIndices = ((ArrayDec)dec).getDimensionList();
		   boolean hasDeclared = true;
		   for (int i = 0; i < indices.size(); ++i){
			   Ident declaredIdent = declaredIndices.getDimensionAt(i);
			   Ident usedIdent = indices.getIdentAt(i);
			   boolean same = declaredIdent.getName().equals(usedIdent.getName());
			   hasDeclared = hasDeclared && same;
		   }
		   return hasDeclared;
	}
	
	//returns true if there is at least one index that is a subindex of declared index and the rest are as declared
	boolean isSubBlock(DataBlock block){
		   IdentList indices = block.getIndices();
		   IDec dec = block.getIdent().getDec();
		   if (dec == null) return false;  //this ident wasn't declared.  This avoids null pointer exception, error found elsewhere.
		   DimensionList declaredIndices = ((ArrayDec)dec).getDimensionList();
		   boolean isValid = true;
		   boolean subBlock = false;
		   for (int i = 0; i < indices.size(); ++i){
			   Ident declaredIdent = declaredIndices.getDimensionAt(i);
			   Ident usedIdent = indices.getIdentAt(i);
			   boolean same = declaredIdent.getName().equals(usedIdent.getName());
			   boolean isSub = !same && isSubIndex(usedIdent,declaredIdent);
			   isValid = isValid & (same || isSub);
			   subBlock = subBlock || isSub;
		   }
		   return isValid && subBlock;
	}

	// This is loose for now and allows int and scalars to be mixed
	private boolean binaryIsScalar(BinaryExpression expr) {
		IUnaryExpression expr1 = expr.getExpr1();
		IUnaryExpression expr2 = expr.getExpr2();
		IBinOp op = expr.getBinOp();
		if (isScalar(expr1) && isScalar(expr2) && !(op instanceof BinOpTensor)) {
			return true;
		}
		if (isScalar(expr1) && isInt(expr2) && !(op instanceof BinOpTensor)) {
			return true;
		}
		if (isInt(expr1) && isScalar(expr2) && !(op instanceof BinOpTensor)) {
			return true;
		} else if (expr1 instanceof DataBlockPrimary
				&& expr2 instanceof DataBlockPrimary && op instanceof BinOpStar) {
			return getContractionResultIndices(
					((DataBlockPrimary) expr1).getDataBlock(),
					((DataBlockPrimary) expr2).getDataBlock()).size() == 0;
		}
		return false;
	}

	private boolean isInt(IExpression expr) {
		return (expr instanceof IntLitPrimary)
				|| (expr instanceof IdentPrimary && ((IdentPrimary) expr)
						.getDec() instanceof IntDec)
				|| (expr instanceof NegatedUnary)
				&& isInt(((NegatedUnary) expr).getPrimary())
				|| (expr instanceof BinaryExpression)
				&& binaryIsInt((BinaryExpression) expr);
	}


	private boolean binaryIsInt(BinaryExpression expr) {
		IUnaryExpression expr1 = expr.getExpr1();
		IUnaryExpression expr2 = expr.getExpr2();
		IBinOp op = expr.getBinOp();
		return (isInt(expr1) && isInt(expr2) && !(op instanceof BinOpTensor));
	}

	private boolean isScalar(IExpression expr) {
		return (expr instanceof DoubleLitPrimary)
				|| (expr instanceof IdentPrimary && ((IdentPrimary) expr)
						.getDec() instanceof ScalarDec)
				|| (expr instanceof NegatedUnary)
				&& isScalar(((NegatedUnary) expr).getPrimary())
				|| (expr instanceof DataBlockPrimary) 
				&& isDataBlockScalar((DataBlockPrimary) expr)
				;
	}

	//a Data block has a scalar value when all of its indices are simple
    private boolean isDataBlockScalar(DataBlockPrimary expr) {
		IdentList indices = expr.getDataBlock().getIndices();
		boolean allSimple = true;
		for (int i = 0; i < indices.size() && allSimple; i++){
			IDec dec = indices.getIdentAt(i).getDec();
			allSimple &= (dec instanceof IndexDec) && ( ((IndexDec)dec).getTypeName().equals("index"));
		}
		return allSimple;
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

	private ArrayList<Ident> getContractionResultIndices(DataBlock block1,
			DataBlock block2) {
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
			if (!contains(SharedInd,id))
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
	public boolean visit(StringLitPrimary n) {
		// TODO upgrade this
		check(false, n, "String Literals in expressions not yet implemented");
		return true;
	}

	@Override
	public void endVisit(StringLitPrimary n) { /* nop */
	}

	@Override
	public boolean visit(StringLiteral n) {
		// TODO upgrade this--for now, don't visit the children of a PrintStatement or PrintlnStatement, so don't need to check here.
		check(n.getParent() instanceof ImportProg, n,
				"String literals may only appear in import, print, and println statements");
		return true;
	}

	@Override
	public void endVisit(StringLiteral n) { /* nop */
	}
	
	@Override
	public boolean visit(PrintStatement n){  /* don't visit children */
		return false;	
	}	@Override
	public void endVisit(PrintStatement n){  /* nop */
	}
	
	@Override
	public boolean visit(PrintlnStatement n){  /* don't visit children */
		return false;	
	}
	@Override
	public void endVisit(PrintlnStatement n){  /* nop */
	}
	
	@Override
	public boolean visit(PrintIndexStatement n){  /* don't visit children */
		return true;	
	}
	@Override
	public void endVisit(PrintIndexStatement n){  
		check(n.getIdent().getDec() instanceof IndexDec || n.getIdent().getDec() instanceof SubIndexDec, n, 
				"Argument to print_index statement must be an index variable");
	}
	
	@Override
	public boolean visit(PrintScalarStatement n){  /* don't visit children */
		return true;	
	}
	@Override
	public void endVisit(PrintScalarStatement n){ 
		check(n.getIdent().getDec() instanceof ScalarDec, n, 
				"Argument to print_index statement must be an scalar variable");
	}
	
}
