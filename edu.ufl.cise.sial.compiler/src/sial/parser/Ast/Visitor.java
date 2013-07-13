package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

public interface Visitor extends IAstVisitor
{
    boolean visit(ASTNode n);
    void endVisit(ASTNode n);

    boolean visit(ASTNodeToken n);
    void endVisit(ASTNodeToken n);

    boolean visit(Sial n);
    void endVisit(Sial n);

    boolean visit(Program n);
    void endVisit(Program n);

    boolean visit(ImportProgList n);
    void endVisit(ImportProgList n);

    boolean visit(ImportProg n);
    void endVisit(ImportProg n);

    boolean visit(ModifierList n);
    void endVisit(ModifierList n);

    boolean visit(ConstantModifier n);
    void endVisit(ConstantModifier n);

    boolean visit(PredefinedModifier n);
    void endVisit(PredefinedModifier n);

    boolean visit(PersistentModifier n);
    void endVisit(PersistentModifier n);

    boolean visit(DecList n);
    void endVisit(DecList n);

    boolean visit(ScalarDec n);
    void endVisit(ScalarDec n);

    boolean visit(IntDec n);
    void endVisit(IntDec n);

    boolean visit(ArrayDec n);
    void endVisit(ArrayDec n);

    boolean visit(ArrayKind n);
    void endVisit(ArrayKind n);

    boolean visit(DimensionList n);
    void endVisit(DimensionList n);

    boolean visit(IndexDec n);
    void endVisit(IndexDec n);

    boolean visit(IndexKind n);
    void endVisit(IndexKind n);

    boolean visit(SubIndexDec n);
    void endVisit(SubIndexDec n);

    boolean visit(Range n);
    void endVisit(Range n);

    boolean visit(IntLitRangeVal n);
    void endVisit(IntLitRangeVal n);

    boolean visit(IdentRangeVal n);
    void endVisit(IdentRangeVal n);

    boolean visit(ProcDec n);
    void endVisit(ProcDec n);

    boolean visit(SpecialDec n);
    void endVisit(SpecialDec n);

    boolean visit(StatementList n);
    void endVisit(StatementList n);

    boolean visit(WhereClause n);
    void endVisit(WhereClause n);

    boolean visit(WhereClauseList n);
    void endVisit(WhereClauseList n);

    boolean visit(CallStatement n);
    void endVisit(CallStatement n);

    boolean visit(ReturnStatement n);
    void endVisit(ReturnStatement n);

    boolean visit(ServerBarrierStatement n);
    void endVisit(ServerBarrierStatement n);

    boolean visit(SipBarrierStatement n);
    void endVisit(SipBarrierStatement n);

    boolean visit(DoStatement n);
    void endVisit(DoStatement n);

    boolean visit(DoStatementSubIndex n);
    void endVisit(DoStatementSubIndex n);

    boolean visit(PardoStatement n);
    void endVisit(PardoStatement n);

    boolean visit(Section n);
    void endVisit(Section n);

    boolean visit(ExitStatement n);
    void endVisit(ExitStatement n);

    boolean visit(CycleStatement n);
    void endVisit(CycleStatement n);

    boolean visit(IfStatement n);
    void endVisit(IfStatement n);

    boolean visit(IfElseStatement n);
    void endVisit(IfElseStatement n);

    boolean visit(AllocateStatement n);
    void endVisit(AllocateStatement n);

    boolean visit(DeallocateStatement n);
    void endVisit(DeallocateStatement n);

    boolean visit(CreateStatement n);
    void endVisit(CreateStatement n);

    boolean visit(DeleteStatement n);
    void endVisit(DeleteStatement n);

    boolean visit(PutStatement n);
    void endVisit(PutStatement n);

    boolean visit(GetStatement n);
    void endVisit(GetStatement n);

    boolean visit(PrepareStatement n);
    void endVisit(PrepareStatement n);

    boolean visit(RequestStatement n);
    void endVisit(RequestStatement n);

    boolean visit(PrequestStatement n);
    void endVisit(PrequestStatement n);

    boolean visit(CollectiveStatement n);
    void endVisit(CollectiveStatement n);

    boolean visit(DestroyStatement n);
    void endVisit(DestroyStatement n);

    boolean visit(PrintlnStatement n);
    void endVisit(PrintlnStatement n);

    boolean visit(PrintStatement n);
    void endVisit(PrintStatement n);

    boolean visit(PrintIndexStatement n);
    void endVisit(PrintIndexStatement n);

    boolean visit(PrintScalarStatement n);
    void endVisit(PrintScalarStatement n);

    boolean visit(ArgList n);
    void endVisit(ArgList n);

    boolean visit(ExecuteStatement n);
    void endVisit(ExecuteStatement n);

    boolean visit(AssignStatement n);
    void endVisit(AssignStatement n);

    boolean visit(AssignOpEqual n);
    void endVisit(AssignOpEqual n);

    boolean visit(AssignOpPlus n);
    void endVisit(AssignOpPlus n);

    boolean visit(AssignOpMinus n);
    void endVisit(AssignOpMinus n);

    boolean visit(AssignOpStar n);
    void endVisit(AssignOpStar n);

    boolean visit(DataBlock n);
    void endVisit(DataBlock n);

    boolean visit(IdentList n);
    void endVisit(IdentList n);

    boolean visit(AllocIndexIdent n);
    void endVisit(AllocIndexIdent n);

    boolean visit(AllocIndexWildCard n);
    void endVisit(AllocIndexWildCard n);

    boolean visit(AllocIndexList n);
    void endVisit(AllocIndexList n);

    boolean visit(AllocIndexListopt n);
    void endVisit(AllocIndexListopt n);

    boolean visit(RelationalExpression n);
    void endVisit(RelationalExpression n);

    boolean visit(RelOp n);
    void endVisit(RelOp n);

    boolean visit(BinaryExpression n);
    void endVisit(BinaryExpression n);

    boolean visit(BinOpStar n);
    void endVisit(BinOpStar n);

    boolean visit(BinOpDiv n);
    void endVisit(BinOpDiv n);

    boolean visit(BinOpPlus n);
    void endVisit(BinOpPlus n);

    boolean visit(BinOpMinus n);
    void endVisit(BinOpMinus n);

    boolean visit(BinOpTensor n);
    void endVisit(BinOpTensor n);

    boolean visit(NegatedUnary n);
    void endVisit(NegatedUnary n);

    boolean visit(IntLitPrimary n);
    void endVisit(IntLitPrimary n);

    boolean visit(DoubleLitPrimary n);
    void endVisit(DoubleLitPrimary n);

    boolean visit(IdentPrimary n);
    void endVisit(IdentPrimary n);

    boolean visit(DataBlockPrimary n);
    void endVisit(DataBlockPrimary n);

    boolean visit(StringLitPrimary n);
    void endVisit(StringLitPrimary n);

    boolean visit(StringLiteral n);
    void endVisit(StringLiteral n);

    boolean visit(Ident n);
    void endVisit(Ident n);

}


