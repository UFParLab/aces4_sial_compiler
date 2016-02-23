package sial.code_gen;

import static sial.parser.context.ASTUtils.getIntVal;
import sial.parser.SialParsersym;
import sial.parser.Ast.ASTNodeToken;
import sial.parser.Ast.AbstractVisitor;
import sial.parser.Ast.ArrayDec;
import sial.parser.Ast.ArrayKind;
import sial.parser.Ast.DecList;
import sial.parser.Ast.DimensionList;
import sial.parser.Ast.IDec;
import sial.parser.Ast.IRangeVal;
import sial.parser.Ast.IdentRangeVal;
import sial.parser.Ast.IndexDec;
import sial.parser.Ast.IndexKind;
import sial.parser.Ast.IntDec;
import sial.parser.Ast.IntLitRangeVal;
import sial.parser.Ast.NegRangeVal;
import sial.parser.Ast.Range;
import sial.parser.Ast.ScalarDec;
import sial.parser.Ast.SubIndexDec;
import sial.parser.context.ASTUtils;

public class DeclarationVisitor extends AbstractVisitor implements SialParsersym, SipConstants {
	
	@Override
	public boolean visit(ArrayDec n) {
		if (n.isUsed()) {
			int attribute = TypeConstantMap.getTypeConstant(n.getTypeName());
			attribute = attribute | ASTUtils.getModifierAttributes(n);
			int priority = 0;
			if (n.getTypeName().toLowerCase() == "distributed")
				priority = SipConstants.distributed_array_priority;
			else if (n.getTypeName().toLowerCase() == "served")
				priority = SipConstants.served_array_priority;
			else if (n.getTypeName().toLowerCase() == "local" && n.isAllSimpleIndices())
				priority = SipConstants.local_all_indices_simple;
			DimensionList dimensions = n.getDimensionList();
			int rank = dimensions.size();
			int[] indarray = new int[rank];
			for (int i = 0; i != rank; i++) {
				IDec indexIDec = dimensions.getDimensionAt(i).getDec();						
				indarray[i] = indexTable.getIndex(indexIDec);
			}			
			arrayTable.addArrayEntry(n, rank, attribute, indarray, priority);
		} else {
			warn("Array " + n + " at line " + lineno(n)
					+ " is never used.  It is not included in the array table.");
		}
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
	public void unimplementedVisitor(String s) {
		// TODO Auto-generated method stub
	}
	


}
