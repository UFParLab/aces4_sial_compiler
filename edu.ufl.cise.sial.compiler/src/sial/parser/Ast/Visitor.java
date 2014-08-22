package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;
  import sial.parser.context.ExpressionType.EType;
  import java.util.EnumSet;

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

    boolean visit(Modifier n);
    void endVisit(Modifier n);

    boolean visit(DecList n);
    void endVisit(DecList n);

    boolean visit(ScalarDec n);
    void endVisit(ScalarDec n);

    boolean visit(ScalarInitialValue n);
    void endVisit(ScalarInitialValue n);

    boolean visit(IntDec n);
    void endVisit(IntDec n);

    boolean visit(IntInitialValue n);
    void endVisit(IntInitialValue n);

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

    boolean visit(NegRangeVal n);
    void endVisit(NegRangeVal n);

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

    boolean visit(StopStatement n);
    void endVisit(StopStatement n);

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

    boolean visit(IfStatement n);
    void endVisit(IfStatement n);

    boolean visit(IfElseStatement n);
    void endVisit(IfElseStatement n);

    boolean visit(AllocateStatement n);
    void endVisit(AllocateStatement n);

    boolean visit(DeallocateStatement n);
    void endVisit(DeallocateStatement n);

    boolean visit(AllocIndexIdent n);
    void endVisit(AllocIndexIdent n);

    boolean visit(AllocIndexWildCard n);
    void endVisit(AllocIndexWildCard n);

    boolean visit(AllocIndexList n);
    void endVisit(AllocIndexList n);

    boolean visit(AllocIndexListopt n);
    void endVisit(AllocIndexListopt n);

    boolean visit(ContiguousAllocateStatement n);
    void endVisit(ContiguousAllocateStatement n);

    boolean visit(ContiguousDeallocateStatement n);
    void endVisit(ContiguousDeallocateStatement n);

    boolean visit(ContiguousIndexRangeExpr n);
    void endVisit(ContiguousIndexRangeExpr n);

    boolean visit(ContiguousIndexRangeExprList n);
    void endVisit(ContiguousIndexRangeExprList n);

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

    boolean visit(CollectiveStatement n);
    void endVisit(CollectiveStatement n);

    boolean visit(DestroyStatement n);
    void endVisit(DestroyStatement n);

    boolean visit(PrintStatement n);
    void endVisit(PrintStatement n);

    boolean visit(PrintlnStatement n);
    void endVisit(PrintlnStatement n);

    boolean visit(ContiguousDataBlockArg n);
    void endVisit(ContiguousDataBlockArg n);

    boolean visit(DataBlockArg n);
    void endVisit(DataBlockArg n);

    boolean visit(IdentArg n);
    void endVisit(IdentArg n);

    boolean visit(DoubleLitArg n);
    void endVisit(DoubleLitArg n);

    boolean visit(IntLitArg n);
    void endVisit(IntLitArg n);

    boolean visit(ArgList n);
    void endVisit(ArgList n);

    boolean visit(ExecuteStatement n);
    void endVisit(ExecuteStatement n);

    boolean visit(AssignToIdent n);
    void endVisit(AssignToIdent n);

    boolean visit(AssignToBlock n);
    void endVisit(AssignToBlock n);

    boolean visit(GPUSection n);
    void endVisit(GPUSection n);

    boolean visit(GPUAllocate n);
    void endVisit(GPUAllocate n);

    boolean visit(GPUFree n);
    void endVisit(GPUFree n);

    boolean visit(GPUPut n);
    void endVisit(GPUPut n);

    boolean visit(GPUGet n);
    void endVisit(GPUGet n);

    boolean visit(SetPersistent n);
    void endVisit(SetPersistent n);

    boolean visit(RestorePersistent n);
    void endVisit(RestorePersistent n);

    boolean visit(AssertSame n);
    void endVisit(AssertSame n);

    boolean visit(BroadcastStatic n);
    void endVisit(BroadcastStatic n);

    boolean visit(AssignOp n);
    void endVisit(AssignOp n);

    boolean visit(DataBlock n);
    void endVisit(DataBlock n);

    boolean visit(IdentList n);
    void endVisit(IdentList n);

    boolean visit(ContiguousDataBlock n);
    void endVisit(ContiguousDataBlock n);

    boolean visit(RelOp n);
    void endVisit(RelOp n);

    boolean visit(RelationalExpression n);
    void endVisit(RelationalExpression n);

    boolean visit(AddExpr n);
    void endVisit(AddExpr n);

    boolean visit(SubtractExpr n);
    void endVisit(SubtractExpr n);

    boolean visit(StarExpr n);
    void endVisit(StarExpr n);

    boolean visit(DivExpr n);
    void endVisit(DivExpr n);

    boolean visit(TensorExpr n);
    void endVisit(TensorExpr n);

    boolean visit(ExponentExpr n);
    void endVisit(ExponentExpr n);

    boolean visit(IntCastExpr n);
    void endVisit(IntCastExpr n);

    boolean visit(ScalarCastExpr n);
    void endVisit(ScalarCastExpr n);

    boolean visit(NegatedUnaryExpr n);
    void endVisit(NegatedUnaryExpr n);

    boolean visit(SqrtUnaryExpr n);
    void endVisit(SqrtUnaryExpr n);

    boolean visit(ParenExpr n);
    void endVisit(ParenExpr n);

    boolean visit(IntLitExpr n);
    void endVisit(IntLitExpr n);

    boolean visit(DoubleLitExpr n);
    void endVisit(DoubleLitExpr n);

    boolean visit(IdentExpr n);
    void endVisit(IdentExpr n);

    boolean visit(DataBlockExpr n);
    void endVisit(DataBlockExpr n);

    boolean visit(ContiguousDataBlockExpr n);
    void endVisit(ContiguousDataBlockExpr n);

    boolean visit(StringLitExpr n);
    void endVisit(StringLitExpr n);

    boolean visit(StringLiteral n);
    void endVisit(StringLiteral n);

    boolean visit(Ident n);
    void endVisit(Ident n);

}


