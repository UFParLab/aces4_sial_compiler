package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;
  import sial.parser.context.ExpressionType.EType;
  import java.util.EnumSet;

public abstract class AbstractVisitor implements Visitor
{
    public abstract void unimplementedVisitor(String s);

    public boolean preVisit(IAst element) { return true; }

    public void postVisit(IAst element) {}

    public boolean visit(ASTNodeToken n) { unimplementedVisitor("visit(ASTNodeToken)"); return true; }
    public void endVisit(ASTNodeToken n) { unimplementedVisitor("endVisit(ASTNodeToken)"); }

    public boolean visit(Sial n) { unimplementedVisitor("visit(Sial)"); return true; }
    public void endVisit(Sial n) { unimplementedVisitor("endVisit(Sial)"); }

    public boolean visit(Program n) { unimplementedVisitor("visit(Program)"); return true; }
    public void endVisit(Program n) { unimplementedVisitor("endVisit(Program)"); }

    public boolean visit(ImportProgList n) { unimplementedVisitor("visit(ImportProgList)"); return true; }
    public void endVisit(ImportProgList n) { unimplementedVisitor("endVisit(ImportProgList)"); }

    public boolean visit(ImportProg n) { unimplementedVisitor("visit(ImportProg)"); return true; }
    public void endVisit(ImportProg n) { unimplementedVisitor("endVisit(ImportProg)"); }

    public boolean visit(ModifierList n) { unimplementedVisitor("visit(ModifierList)"); return true; }
    public void endVisit(ModifierList n) { unimplementedVisitor("endVisit(ModifierList)"); }

    public boolean visit(Modifier n) { unimplementedVisitor("visit(Modifier)"); return true; }
    public void endVisit(Modifier n) { unimplementedVisitor("endVisit(Modifier)"); }

    public boolean visit(DecList n) { unimplementedVisitor("visit(DecList)"); return true; }
    public void endVisit(DecList n) { unimplementedVisitor("endVisit(DecList)"); }

    public boolean visit(ScalarDec n) { unimplementedVisitor("visit(ScalarDec)"); return true; }
    public void endVisit(ScalarDec n) { unimplementedVisitor("endVisit(ScalarDec)"); }

    public boolean visit(ScalarInitialValue n) { unimplementedVisitor("visit(ScalarInitialValue)"); return true; }
    public void endVisit(ScalarInitialValue n) { unimplementedVisitor("endVisit(ScalarInitialValue)"); }

    public boolean visit(IntDec n) { unimplementedVisitor("visit(IntDec)"); return true; }
    public void endVisit(IntDec n) { unimplementedVisitor("endVisit(IntDec)"); }

    public boolean visit(IntInitialValue n) { unimplementedVisitor("visit(IntInitialValue)"); return true; }
    public void endVisit(IntInitialValue n) { unimplementedVisitor("endVisit(IntInitialValue)"); }

    public boolean visit(ArrayDec n) { unimplementedVisitor("visit(ArrayDec)"); return true; }
    public void endVisit(ArrayDec n) { unimplementedVisitor("endVisit(ArrayDec)"); }

    public boolean visit(ArrayKind n) { unimplementedVisitor("visit(ArrayKind)"); return true; }
    public void endVisit(ArrayKind n) { unimplementedVisitor("endVisit(ArrayKind)"); }

    public boolean visit(DimensionList n) { unimplementedVisitor("visit(DimensionList)"); return true; }
    public void endVisit(DimensionList n) { unimplementedVisitor("endVisit(DimensionList)"); }

    public boolean visit(IndexDec n) { unimplementedVisitor("visit(IndexDec)"); return true; }
    public void endVisit(IndexDec n) { unimplementedVisitor("endVisit(IndexDec)"); }

    public boolean visit(IndexKind n) { unimplementedVisitor("visit(IndexKind)"); return true; }
    public void endVisit(IndexKind n) { unimplementedVisitor("endVisit(IndexKind)"); }

    public boolean visit(SubIndexDec n) { unimplementedVisitor("visit(SubIndexDec)"); return true; }
    public void endVisit(SubIndexDec n) { unimplementedVisitor("endVisit(SubIndexDec)"); }

    public boolean visit(Range n) { unimplementedVisitor("visit(Range)"); return true; }
    public void endVisit(Range n) { unimplementedVisitor("endVisit(Range)"); }

    public boolean visit(IntLitRangeVal n) { unimplementedVisitor("visit(IntLitRangeVal)"); return true; }
    public void endVisit(IntLitRangeVal n) { unimplementedVisitor("endVisit(IntLitRangeVal)"); }

    public boolean visit(NegRangeVal n) { unimplementedVisitor("visit(NegRangeVal)"); return true; }
    public void endVisit(NegRangeVal n) { unimplementedVisitor("endVisit(NegRangeVal)"); }

    public boolean visit(IdentRangeVal n) { unimplementedVisitor("visit(IdentRangeVal)"); return true; }
    public void endVisit(IdentRangeVal n) { unimplementedVisitor("endVisit(IdentRangeVal)"); }

    public boolean visit(ProcDec n) { unimplementedVisitor("visit(ProcDec)"); return true; }
    public void endVisit(ProcDec n) { unimplementedVisitor("endVisit(ProcDec)"); }

    public boolean visit(SpecialDec n) { unimplementedVisitor("visit(SpecialDec)"); return true; }
    public void endVisit(SpecialDec n) { unimplementedVisitor("endVisit(SpecialDec)"); }

    public boolean visit(StatementList n) { unimplementedVisitor("visit(StatementList)"); return true; }
    public void endVisit(StatementList n) { unimplementedVisitor("endVisit(StatementList)"); }

    public boolean visit(WhereClause n) { unimplementedVisitor("visit(WhereClause)"); return true; }
    public void endVisit(WhereClause n) { unimplementedVisitor("endVisit(WhereClause)"); }

    public boolean visit(WhereClauseList n) { unimplementedVisitor("visit(WhereClauseList)"); return true; }
    public void endVisit(WhereClauseList n) { unimplementedVisitor("endVisit(WhereClauseList)"); }

    public boolean visit(CallStatement n) { unimplementedVisitor("visit(CallStatement)"); return true; }
    public void endVisit(CallStatement n) { unimplementedVisitor("endVisit(CallStatement)"); }

    public boolean visit(ReturnStatement n) { unimplementedVisitor("visit(ReturnStatement)"); return true; }
    public void endVisit(ReturnStatement n) { unimplementedVisitor("endVisit(ReturnStatement)"); }

    public boolean visit(StopStatement n) { unimplementedVisitor("visit(StopStatement)"); return true; }
    public void endVisit(StopStatement n) { unimplementedVisitor("endVisit(StopStatement)"); }

    public boolean visit(ServerBarrierStatement n) { unimplementedVisitor("visit(ServerBarrierStatement)"); return true; }
    public void endVisit(ServerBarrierStatement n) { unimplementedVisitor("endVisit(ServerBarrierStatement)"); }

    public boolean visit(SipBarrierStatement n) { unimplementedVisitor("visit(SipBarrierStatement)"); return true; }
    public void endVisit(SipBarrierStatement n) { unimplementedVisitor("endVisit(SipBarrierStatement)"); }

    public boolean visit(DoStatement n) { unimplementedVisitor("visit(DoStatement)"); return true; }
    public void endVisit(DoStatement n) { unimplementedVisitor("endVisit(DoStatement)"); }

    public boolean visit(DoStatementSubIndex n) { unimplementedVisitor("visit(DoStatementSubIndex)"); return true; }
    public void endVisit(DoStatementSubIndex n) { unimplementedVisitor("endVisit(DoStatementSubIndex)"); }

    public boolean visit(PardoStatement n) { unimplementedVisitor("visit(PardoStatement)"); return true; }
    public void endVisit(PardoStatement n) { unimplementedVisitor("endVisit(PardoStatement)"); }

    public boolean visit(Section n) { unimplementedVisitor("visit(Section)"); return true; }
    public void endVisit(Section n) { unimplementedVisitor("endVisit(Section)"); }

    public boolean visit(ExitStatement n) { unimplementedVisitor("visit(ExitStatement)"); return true; }
    public void endVisit(ExitStatement n) { unimplementedVisitor("endVisit(ExitStatement)"); }

    public boolean visit(IfStatement n) { unimplementedVisitor("visit(IfStatement)"); return true; }
    public void endVisit(IfStatement n) { unimplementedVisitor("endVisit(IfStatement)"); }

    public boolean visit(IfElseStatement n) { unimplementedVisitor("visit(IfElseStatement)"); return true; }
    public void endVisit(IfElseStatement n) { unimplementedVisitor("endVisit(IfElseStatement)"); }

    public boolean visit(AllocateStatement n) { unimplementedVisitor("visit(AllocateStatement)"); return true; }
    public void endVisit(AllocateStatement n) { unimplementedVisitor("endVisit(AllocateStatement)"); }

    public boolean visit(DeallocateStatement n) { unimplementedVisitor("visit(DeallocateStatement)"); return true; }
    public void endVisit(DeallocateStatement n) { unimplementedVisitor("endVisit(DeallocateStatement)"); }

    public boolean visit(AllocIndexIdent n) { unimplementedVisitor("visit(AllocIndexIdent)"); return true; }
    public void endVisit(AllocIndexIdent n) { unimplementedVisitor("endVisit(AllocIndexIdent)"); }

    public boolean visit(AllocIndexWildCard n) { unimplementedVisitor("visit(AllocIndexWildCard)"); return true; }
    public void endVisit(AllocIndexWildCard n) { unimplementedVisitor("endVisit(AllocIndexWildCard)"); }

    public boolean visit(AllocIndexList n) { unimplementedVisitor("visit(AllocIndexList)"); return true; }
    public void endVisit(AllocIndexList n) { unimplementedVisitor("endVisit(AllocIndexList)"); }

    public boolean visit(AllocIndexListopt n) { unimplementedVisitor("visit(AllocIndexListopt)"); return true; }
    public void endVisit(AllocIndexListopt n) { unimplementedVisitor("endVisit(AllocIndexListopt)"); }

    public boolean visit(ContiguousAllocateStatement n) { unimplementedVisitor("visit(ContiguousAllocateStatement)"); return true; }
    public void endVisit(ContiguousAllocateStatement n) { unimplementedVisitor("endVisit(ContiguousAllocateStatement)"); }

    public boolean visit(ContiguousDeallocateStatement n) { unimplementedVisitor("visit(ContiguousDeallocateStatement)"); return true; }
    public void endVisit(ContiguousDeallocateStatement n) { unimplementedVisitor("endVisit(ContiguousDeallocateStatement)"); }

    public boolean visit(ContiguousIndexRangeExpr n) { unimplementedVisitor("visit(ContiguousIndexRangeExpr)"); return true; }
    public void endVisit(ContiguousIndexRangeExpr n) { unimplementedVisitor("endVisit(ContiguousIndexRangeExpr)"); }

    public boolean visit(ContiguousIndexRangeExprList n) { unimplementedVisitor("visit(ContiguousIndexRangeExprList)"); return true; }
    public void endVisit(ContiguousIndexRangeExprList n) { unimplementedVisitor("endVisit(ContiguousIndexRangeExprList)"); }

    public boolean visit(CreateStatement n) { unimplementedVisitor("visit(CreateStatement)"); return true; }
    public void endVisit(CreateStatement n) { unimplementedVisitor("endVisit(CreateStatement)"); }

    public boolean visit(DeleteStatement n) { unimplementedVisitor("visit(DeleteStatement)"); return true; }
    public void endVisit(DeleteStatement n) { unimplementedVisitor("endVisit(DeleteStatement)"); }

    public boolean visit(PutStatement n) { unimplementedVisitor("visit(PutStatement)"); return true; }
    public void endVisit(PutStatement n) { unimplementedVisitor("endVisit(PutStatement)"); }

    public boolean visit(GetStatement n) { unimplementedVisitor("visit(GetStatement)"); return true; }
    public void endVisit(GetStatement n) { unimplementedVisitor("endVisit(GetStatement)"); }

    public boolean visit(PrepareStatement n) { unimplementedVisitor("visit(PrepareStatement)"); return true; }
    public void endVisit(PrepareStatement n) { unimplementedVisitor("endVisit(PrepareStatement)"); }

    public boolean visit(RequestStatement n) { unimplementedVisitor("visit(RequestStatement)"); return true; }
    public void endVisit(RequestStatement n) { unimplementedVisitor("endVisit(RequestStatement)"); }

    public boolean visit(CollectiveStatement n) { unimplementedVisitor("visit(CollectiveStatement)"); return true; }
    public void endVisit(CollectiveStatement n) { unimplementedVisitor("endVisit(CollectiveStatement)"); }

    public boolean visit(DestroyStatement n) { unimplementedVisitor("visit(DestroyStatement)"); return true; }
    public void endVisit(DestroyStatement n) { unimplementedVisitor("endVisit(DestroyStatement)"); }

    public boolean visit(PrintStatement n) { unimplementedVisitor("visit(PrintStatement)"); return true; }
    public void endVisit(PrintStatement n) { unimplementedVisitor("endVisit(PrintStatement)"); }

    public boolean visit(PrintlnStatement n) { unimplementedVisitor("visit(PrintlnStatement)"); return true; }
    public void endVisit(PrintlnStatement n) { unimplementedVisitor("endVisit(PrintlnStatement)"); }

    public boolean visit(ContiguousDataBlockArg n) { unimplementedVisitor("visit(ContiguousDataBlockArg)"); return true; }
    public void endVisit(ContiguousDataBlockArg n) { unimplementedVisitor("endVisit(ContiguousDataBlockArg)"); }

    public boolean visit(DataBlockArg n) { unimplementedVisitor("visit(DataBlockArg)"); return true; }
    public void endVisit(DataBlockArg n) { unimplementedVisitor("endVisit(DataBlockArg)"); }

    public boolean visit(IdentArg n) { unimplementedVisitor("visit(IdentArg)"); return true; }
    public void endVisit(IdentArg n) { unimplementedVisitor("endVisit(IdentArg)"); }

    public boolean visit(DoubleLitArg n) { unimplementedVisitor("visit(DoubleLitArg)"); return true; }
    public void endVisit(DoubleLitArg n) { unimplementedVisitor("endVisit(DoubleLitArg)"); }

    public boolean visit(IntLitArg n) { unimplementedVisitor("visit(IntLitArg)"); return true; }
    public void endVisit(IntLitArg n) { unimplementedVisitor("endVisit(IntLitArg)"); }

    public boolean visit(ArgList n) { unimplementedVisitor("visit(ArgList)"); return true; }
    public void endVisit(ArgList n) { unimplementedVisitor("endVisit(ArgList)"); }

    public boolean visit(ExecuteStatement n) { unimplementedVisitor("visit(ExecuteStatement)"); return true; }
    public void endVisit(ExecuteStatement n) { unimplementedVisitor("endVisit(ExecuteStatement)"); }

    public boolean visit(AssignToIdent n) { unimplementedVisitor("visit(AssignToIdent)"); return true; }
    public void endVisit(AssignToIdent n) { unimplementedVisitor("endVisit(AssignToIdent)"); }

    public boolean visit(AssignToBlock n) { unimplementedVisitor("visit(AssignToBlock)"); return true; }
    public void endVisit(AssignToBlock n) { unimplementedVisitor("endVisit(AssignToBlock)"); }

    public boolean visit(AssignToContigousDataBlock n) { unimplementedVisitor("visit(AssignToContigousDataBlock)"); return true; }
    public void endVisit(AssignToContigousDataBlock n) { unimplementedVisitor("endVisit(AssignToContigousDataBlock)"); }

    public boolean visit(GPUSection n) { unimplementedVisitor("visit(GPUSection)"); return true; }
    public void endVisit(GPUSection n) { unimplementedVisitor("endVisit(GPUSection)"); }

    public boolean visit(GPUAllocate n) { unimplementedVisitor("visit(GPUAllocate)"); return true; }
    public void endVisit(GPUAllocate n) { unimplementedVisitor("endVisit(GPUAllocate)"); }

    public boolean visit(GPUFree n) { unimplementedVisitor("visit(GPUFree)"); return true; }
    public void endVisit(GPUFree n) { unimplementedVisitor("endVisit(GPUFree)"); }

    public boolean visit(GPUPut n) { unimplementedVisitor("visit(GPUPut)"); return true; }
    public void endVisit(GPUPut n) { unimplementedVisitor("endVisit(GPUPut)"); }

    public boolean visit(GPUGet n) { unimplementedVisitor("visit(GPUGet)"); return true; }
    public void endVisit(GPUGet n) { unimplementedVisitor("endVisit(GPUGet)"); }

    public boolean visit(SetPersistent n) { unimplementedVisitor("visit(SetPersistent)"); return true; }
    public void endVisit(SetPersistent n) { unimplementedVisitor("endVisit(SetPersistent)"); }

    public boolean visit(RestorePersistent n) { unimplementedVisitor("visit(RestorePersistent)"); return true; }
    public void endVisit(RestorePersistent n) { unimplementedVisitor("endVisit(RestorePersistent)"); }

    public boolean visit(AssertSame n) { unimplementedVisitor("visit(AssertSame)"); return true; }
    public void endVisit(AssertSame n) { unimplementedVisitor("endVisit(AssertSame)"); }

    public boolean visit(BroadcastStatic n) { unimplementedVisitor("visit(BroadcastStatic)"); return true; }
    public void endVisit(BroadcastStatic n) { unimplementedVisitor("endVisit(BroadcastStatic)"); }

    public boolean visit(AssignOp n) { unimplementedVisitor("visit(AssignOp)"); return true; }
    public void endVisit(AssignOp n) { unimplementedVisitor("endVisit(AssignOp)"); }

    public boolean visit(DataBlock n) { unimplementedVisitor("visit(DataBlock)"); return true; }
    public void endVisit(DataBlock n) { unimplementedVisitor("endVisit(DataBlock)"); }

    public boolean visit(IdentList n) { unimplementedVisitor("visit(IdentList)"); return true; }
    public void endVisit(IdentList n) { unimplementedVisitor("endVisit(IdentList)"); }

    public boolean visit(ContiguousDataBlock n) { unimplementedVisitor("visit(ContiguousDataBlock)"); return true; }
    public void endVisit(ContiguousDataBlock n) { unimplementedVisitor("endVisit(ContiguousDataBlock)"); }

    public boolean visit(RelOp n) { unimplementedVisitor("visit(RelOp)"); return true; }
    public void endVisit(RelOp n) { unimplementedVisitor("endVisit(RelOp)"); }

    public boolean visit(RelationalExpression n) { unimplementedVisitor("visit(RelationalExpression)"); return true; }
    public void endVisit(RelationalExpression n) { unimplementedVisitor("endVisit(RelationalExpression)"); }

    public boolean visit(AddExpr n) { unimplementedVisitor("visit(AddExpr)"); return true; }
    public void endVisit(AddExpr n) { unimplementedVisitor("endVisit(AddExpr)"); }

    public boolean visit(SubtractExpr n) { unimplementedVisitor("visit(SubtractExpr)"); return true; }
    public void endVisit(SubtractExpr n) { unimplementedVisitor("endVisit(SubtractExpr)"); }

    public boolean visit(StarExpr n) { unimplementedVisitor("visit(StarExpr)"); return true; }
    public void endVisit(StarExpr n) { unimplementedVisitor("endVisit(StarExpr)"); }

    public boolean visit(DivExpr n) { unimplementedVisitor("visit(DivExpr)"); return true; }
    public void endVisit(DivExpr n) { unimplementedVisitor("endVisit(DivExpr)"); }

    public boolean visit(TensorExpr n) { unimplementedVisitor("visit(TensorExpr)"); return true; }
    public void endVisit(TensorExpr n) { unimplementedVisitor("endVisit(TensorExpr)"); }

    public boolean visit(ExponentExpr n) { unimplementedVisitor("visit(ExponentExpr)"); return true; }
    public void endVisit(ExponentExpr n) { unimplementedVisitor("endVisit(ExponentExpr)"); }

    public boolean visit(IntCastExpr n) { unimplementedVisitor("visit(IntCastExpr)"); return true; }
    public void endVisit(IntCastExpr n) { unimplementedVisitor("endVisit(IntCastExpr)"); }

    public boolean visit(ScalarCastExpr n) { unimplementedVisitor("visit(ScalarCastExpr)"); return true; }
    public void endVisit(ScalarCastExpr n) { unimplementedVisitor("endVisit(ScalarCastExpr)"); }

    public boolean visit(NegatedUnaryExpr n) { unimplementedVisitor("visit(NegatedUnaryExpr)"); return true; }
    public void endVisit(NegatedUnaryExpr n) { unimplementedVisitor("endVisit(NegatedUnaryExpr)"); }

    public boolean visit(SqrtUnaryExpr n) { unimplementedVisitor("visit(SqrtUnaryExpr)"); return true; }
    public void endVisit(SqrtUnaryExpr n) { unimplementedVisitor("endVisit(SqrtUnaryExpr)"); }

    public boolean visit(ParenExpr n) { unimplementedVisitor("visit(ParenExpr)"); return true; }
    public void endVisit(ParenExpr n) { unimplementedVisitor("endVisit(ParenExpr)"); }

    public boolean visit(IntLitExpr n) { unimplementedVisitor("visit(IntLitExpr)"); return true; }
    public void endVisit(IntLitExpr n) { unimplementedVisitor("endVisit(IntLitExpr)"); }

    public boolean visit(DoubleLitExpr n) { unimplementedVisitor("visit(DoubleLitExpr)"); return true; }
    public void endVisit(DoubleLitExpr n) { unimplementedVisitor("endVisit(DoubleLitExpr)"); }

    public boolean visit(IdentExpr n) { unimplementedVisitor("visit(IdentExpr)"); return true; }
    public void endVisit(IdentExpr n) { unimplementedVisitor("endVisit(IdentExpr)"); }

    public boolean visit(DataBlockExpr n) { unimplementedVisitor("visit(DataBlockExpr)"); return true; }
    public void endVisit(DataBlockExpr n) { unimplementedVisitor("endVisit(DataBlockExpr)"); }

    public boolean visit(ContiguousDataBlockExpr n) { unimplementedVisitor("visit(ContiguousDataBlockExpr)"); return true; }
    public void endVisit(ContiguousDataBlockExpr n) { unimplementedVisitor("endVisit(ContiguousDataBlockExpr)"); }

    public boolean visit(StringLitExpr n) { unimplementedVisitor("visit(StringLitExpr)"); return true; }
    public void endVisit(StringLitExpr n) { unimplementedVisitor("endVisit(StringLitExpr)"); }

    public boolean visit(StringLiteral n) { unimplementedVisitor("visit(StringLiteral)"); return true; }
    public void endVisit(StringLiteral n) { unimplementedVisitor("endVisit(StringLiteral)"); }

    public boolean visit(Ident n) { unimplementedVisitor("visit(Ident)"); return true; }
    public void endVisit(Ident n) { unimplementedVisitor("endVisit(Ident)"); }


    public boolean visit(ASTNode n)
    {
        if (n instanceof ASTNodeToken) return visit((ASTNodeToken) n);
        else if (n instanceof Sial) return visit((Sial) n);
        else if (n instanceof Program) return visit((Program) n);
        else if (n instanceof ImportProgList) return visit((ImportProgList) n);
        else if (n instanceof ImportProg) return visit((ImportProg) n);
        else if (n instanceof ModifierList) return visit((ModifierList) n);
        else if (n instanceof Modifier) return visit((Modifier) n);
        else if (n instanceof DecList) return visit((DecList) n);
        else if (n instanceof ScalarDec) return visit((ScalarDec) n);
        else if (n instanceof ScalarInitialValue) return visit((ScalarInitialValue) n);
        else if (n instanceof IntDec) return visit((IntDec) n);
        else if (n instanceof IntInitialValue) return visit((IntInitialValue) n);
        else if (n instanceof ArrayDec) return visit((ArrayDec) n);
        else if (n instanceof ArrayKind) return visit((ArrayKind) n);
        else if (n instanceof DimensionList) return visit((DimensionList) n);
        else if (n instanceof IndexDec) return visit((IndexDec) n);
        else if (n instanceof IndexKind) return visit((IndexKind) n);
        else if (n instanceof SubIndexDec) return visit((SubIndexDec) n);
        else if (n instanceof Range) return visit((Range) n);
        else if (n instanceof IntLitRangeVal) return visit((IntLitRangeVal) n);
        else if (n instanceof NegRangeVal) return visit((NegRangeVal) n);
        else if (n instanceof IdentRangeVal) return visit((IdentRangeVal) n);
        else if (n instanceof ProcDec) return visit((ProcDec) n);
        else if (n instanceof SpecialDec) return visit((SpecialDec) n);
        else if (n instanceof StatementList) return visit((StatementList) n);
        else if (n instanceof WhereClause) return visit((WhereClause) n);
        else if (n instanceof WhereClauseList) return visit((WhereClauseList) n);
        else if (n instanceof CallStatement) return visit((CallStatement) n);
        else if (n instanceof ReturnStatement) return visit((ReturnStatement) n);
        else if (n instanceof StopStatement) return visit((StopStatement) n);
        else if (n instanceof ServerBarrierStatement) return visit((ServerBarrierStatement) n);
        else if (n instanceof SipBarrierStatement) return visit((SipBarrierStatement) n);
        else if (n instanceof DoStatement) return visit((DoStatement) n);
        else if (n instanceof DoStatementSubIndex) return visit((DoStatementSubIndex) n);
        else if (n instanceof PardoStatement) return visit((PardoStatement) n);
        else if (n instanceof Section) return visit((Section) n);
        else if (n instanceof ExitStatement) return visit((ExitStatement) n);
        else if (n instanceof IfStatement) return visit((IfStatement) n);
        else if (n instanceof IfElseStatement) return visit((IfElseStatement) n);
        else if (n instanceof AllocateStatement) return visit((AllocateStatement) n);
        else if (n instanceof DeallocateStatement) return visit((DeallocateStatement) n);
        else if (n instanceof AllocIndexIdent) return visit((AllocIndexIdent) n);
        else if (n instanceof AllocIndexWildCard) return visit((AllocIndexWildCard) n);
        else if (n instanceof AllocIndexList) return visit((AllocIndexList) n);
        else if (n instanceof AllocIndexListopt) return visit((AllocIndexListopt) n);
        else if (n instanceof ContiguousAllocateStatement) return visit((ContiguousAllocateStatement) n);
        else if (n instanceof ContiguousDeallocateStatement) return visit((ContiguousDeallocateStatement) n);
        else if (n instanceof ContiguousIndexRangeExpr) return visit((ContiguousIndexRangeExpr) n);
        else if (n instanceof ContiguousIndexRangeExprList) return visit((ContiguousIndexRangeExprList) n);
        else if (n instanceof CreateStatement) return visit((CreateStatement) n);
        else if (n instanceof DeleteStatement) return visit((DeleteStatement) n);
        else if (n instanceof PutStatement) return visit((PutStatement) n);
        else if (n instanceof GetStatement) return visit((GetStatement) n);
        else if (n instanceof PrepareStatement) return visit((PrepareStatement) n);
        else if (n instanceof RequestStatement) return visit((RequestStatement) n);
        else if (n instanceof CollectiveStatement) return visit((CollectiveStatement) n);
        else if (n instanceof DestroyStatement) return visit((DestroyStatement) n);
        else if (n instanceof PrintStatement) return visit((PrintStatement) n);
        else if (n instanceof PrintlnStatement) return visit((PrintlnStatement) n);
        else if (n instanceof ContiguousDataBlockArg) return visit((ContiguousDataBlockArg) n);
        else if (n instanceof DataBlockArg) return visit((DataBlockArg) n);
        else if (n instanceof IdentArg) return visit((IdentArg) n);
        else if (n instanceof DoubleLitArg) return visit((DoubleLitArg) n);
        else if (n instanceof IntLitArg) return visit((IntLitArg) n);
        else if (n instanceof ArgList) return visit((ArgList) n);
        else if (n instanceof ExecuteStatement) return visit((ExecuteStatement) n);
        else if (n instanceof AssignToIdent) return visit((AssignToIdent) n);
        else if (n instanceof AssignToBlock) return visit((AssignToBlock) n);
        else if (n instanceof AssignToContigousDataBlock) return visit((AssignToContigousDataBlock) n);
        else if (n instanceof GPUSection) return visit((GPUSection) n);
        else if (n instanceof GPUAllocate) return visit((GPUAllocate) n);
        else if (n instanceof GPUFree) return visit((GPUFree) n);
        else if (n instanceof GPUPut) return visit((GPUPut) n);
        else if (n instanceof GPUGet) return visit((GPUGet) n);
        else if (n instanceof SetPersistent) return visit((SetPersistent) n);
        else if (n instanceof RestorePersistent) return visit((RestorePersistent) n);
        else if (n instanceof AssertSame) return visit((AssertSame) n);
        else if (n instanceof BroadcastStatic) return visit((BroadcastStatic) n);
        else if (n instanceof AssignOp) return visit((AssignOp) n);
        else if (n instanceof DataBlock) return visit((DataBlock) n);
        else if (n instanceof IdentList) return visit((IdentList) n);
        else if (n instanceof ContiguousDataBlock) return visit((ContiguousDataBlock) n);
        else if (n instanceof RelOp) return visit((RelOp) n);
        else if (n instanceof RelationalExpression) return visit((RelationalExpression) n);
        else if (n instanceof AddExpr) return visit((AddExpr) n);
        else if (n instanceof SubtractExpr) return visit((SubtractExpr) n);
        else if (n instanceof StarExpr) return visit((StarExpr) n);
        else if (n instanceof DivExpr) return visit((DivExpr) n);
        else if (n instanceof TensorExpr) return visit((TensorExpr) n);
        else if (n instanceof ExponentExpr) return visit((ExponentExpr) n);
        else if (n instanceof IntCastExpr) return visit((IntCastExpr) n);
        else if (n instanceof ScalarCastExpr) return visit((ScalarCastExpr) n);
        else if (n instanceof NegatedUnaryExpr) return visit((NegatedUnaryExpr) n);
        else if (n instanceof SqrtUnaryExpr) return visit((SqrtUnaryExpr) n);
        else if (n instanceof ParenExpr) return visit((ParenExpr) n);
        else if (n instanceof IntLitExpr) return visit((IntLitExpr) n);
        else if (n instanceof DoubleLitExpr) return visit((DoubleLitExpr) n);
        else if (n instanceof IdentExpr) return visit((IdentExpr) n);
        else if (n instanceof DataBlockExpr) return visit((DataBlockExpr) n);
        else if (n instanceof ContiguousDataBlockExpr) return visit((ContiguousDataBlockExpr) n);
        else if (n instanceof StringLitExpr) return visit((StringLitExpr) n);
        else if (n instanceof StringLiteral) return visit((StringLiteral) n);
        else if (n instanceof Ident) return visit((Ident) n);
        throw new UnsupportedOperationException("visit(" + n.getClass().toString() + ")");
    }
    public void endVisit(ASTNode n)
    {
        if (n instanceof ASTNodeToken) endVisit((ASTNodeToken) n);
        else if (n instanceof Sial) endVisit((Sial) n);
        else if (n instanceof Program) endVisit((Program) n);
        else if (n instanceof ImportProgList) endVisit((ImportProgList) n);
        else if (n instanceof ImportProg) endVisit((ImportProg) n);
        else if (n instanceof ModifierList) endVisit((ModifierList) n);
        else if (n instanceof Modifier) endVisit((Modifier) n);
        else if (n instanceof DecList) endVisit((DecList) n);
        else if (n instanceof ScalarDec) endVisit((ScalarDec) n);
        else if (n instanceof ScalarInitialValue) endVisit((ScalarInitialValue) n);
        else if (n instanceof IntDec) endVisit((IntDec) n);
        else if (n instanceof IntInitialValue) endVisit((IntInitialValue) n);
        else if (n instanceof ArrayDec) endVisit((ArrayDec) n);
        else if (n instanceof ArrayKind) endVisit((ArrayKind) n);
        else if (n instanceof DimensionList) endVisit((DimensionList) n);
        else if (n instanceof IndexDec) endVisit((IndexDec) n);
        else if (n instanceof IndexKind) endVisit((IndexKind) n);
        else if (n instanceof SubIndexDec) endVisit((SubIndexDec) n);
        else if (n instanceof Range) endVisit((Range) n);
        else if (n instanceof IntLitRangeVal) endVisit((IntLitRangeVal) n);
        else if (n instanceof NegRangeVal) endVisit((NegRangeVal) n);
        else if (n instanceof IdentRangeVal) endVisit((IdentRangeVal) n);
        else if (n instanceof ProcDec) endVisit((ProcDec) n);
        else if (n instanceof SpecialDec) endVisit((SpecialDec) n);
        else if (n instanceof StatementList) endVisit((StatementList) n);
        else if (n instanceof WhereClause) endVisit((WhereClause) n);
        else if (n instanceof WhereClauseList) endVisit((WhereClauseList) n);
        else if (n instanceof CallStatement) endVisit((CallStatement) n);
        else if (n instanceof ReturnStatement) endVisit((ReturnStatement) n);
        else if (n instanceof StopStatement) endVisit((StopStatement) n);
        else if (n instanceof ServerBarrierStatement) endVisit((ServerBarrierStatement) n);
        else if (n instanceof SipBarrierStatement) endVisit((SipBarrierStatement) n);
        else if (n instanceof DoStatement) endVisit((DoStatement) n);
        else if (n instanceof DoStatementSubIndex) endVisit((DoStatementSubIndex) n);
        else if (n instanceof PardoStatement) endVisit((PardoStatement) n);
        else if (n instanceof Section) endVisit((Section) n);
        else if (n instanceof ExitStatement) endVisit((ExitStatement) n);
        else if (n instanceof IfStatement) endVisit((IfStatement) n);
        else if (n instanceof IfElseStatement) endVisit((IfElseStatement) n);
        else if (n instanceof AllocateStatement) endVisit((AllocateStatement) n);
        else if (n instanceof DeallocateStatement) endVisit((DeallocateStatement) n);
        else if (n instanceof AllocIndexIdent) endVisit((AllocIndexIdent) n);
        else if (n instanceof AllocIndexWildCard) endVisit((AllocIndexWildCard) n);
        else if (n instanceof AllocIndexList) endVisit((AllocIndexList) n);
        else if (n instanceof AllocIndexListopt) endVisit((AllocIndexListopt) n);
        else if (n instanceof ContiguousAllocateStatement) endVisit((ContiguousAllocateStatement) n);
        else if (n instanceof ContiguousDeallocateStatement) endVisit((ContiguousDeallocateStatement) n);
        else if (n instanceof ContiguousIndexRangeExpr) endVisit((ContiguousIndexRangeExpr) n);
        else if (n instanceof ContiguousIndexRangeExprList) endVisit((ContiguousIndexRangeExprList) n);
        else if (n instanceof CreateStatement) endVisit((CreateStatement) n);
        else if (n instanceof DeleteStatement) endVisit((DeleteStatement) n);
        else if (n instanceof PutStatement) endVisit((PutStatement) n);
        else if (n instanceof GetStatement) endVisit((GetStatement) n);
        else if (n instanceof PrepareStatement) endVisit((PrepareStatement) n);
        else if (n instanceof RequestStatement) endVisit((RequestStatement) n);
        else if (n instanceof CollectiveStatement) endVisit((CollectiveStatement) n);
        else if (n instanceof DestroyStatement) endVisit((DestroyStatement) n);
        else if (n instanceof PrintStatement) endVisit((PrintStatement) n);
        else if (n instanceof PrintlnStatement) endVisit((PrintlnStatement) n);
        else if (n instanceof ContiguousDataBlockArg) endVisit((ContiguousDataBlockArg) n);
        else if (n instanceof DataBlockArg) endVisit((DataBlockArg) n);
        else if (n instanceof IdentArg) endVisit((IdentArg) n);
        else if (n instanceof DoubleLitArg) endVisit((DoubleLitArg) n);
        else if (n instanceof IntLitArg) endVisit((IntLitArg) n);
        else if (n instanceof ArgList) endVisit((ArgList) n);
        else if (n instanceof ExecuteStatement) endVisit((ExecuteStatement) n);
        else if (n instanceof AssignToIdent) endVisit((AssignToIdent) n);
        else if (n instanceof AssignToBlock) endVisit((AssignToBlock) n);
        else if (n instanceof AssignToContigousDataBlock) endVisit((AssignToContigousDataBlock) n);
        else if (n instanceof GPUSection) endVisit((GPUSection) n);
        else if (n instanceof GPUAllocate) endVisit((GPUAllocate) n);
        else if (n instanceof GPUFree) endVisit((GPUFree) n);
        else if (n instanceof GPUPut) endVisit((GPUPut) n);
        else if (n instanceof GPUGet) endVisit((GPUGet) n);
        else if (n instanceof SetPersistent) endVisit((SetPersistent) n);
        else if (n instanceof RestorePersistent) endVisit((RestorePersistent) n);
        else if (n instanceof AssertSame) endVisit((AssertSame) n);
        else if (n instanceof BroadcastStatic) endVisit((BroadcastStatic) n);
        else if (n instanceof AssignOp) endVisit((AssignOp) n);
        else if (n instanceof DataBlock) endVisit((DataBlock) n);
        else if (n instanceof IdentList) endVisit((IdentList) n);
        else if (n instanceof ContiguousDataBlock) endVisit((ContiguousDataBlock) n);
        else if (n instanceof RelOp) endVisit((RelOp) n);
        else if (n instanceof RelationalExpression) endVisit((RelationalExpression) n);
        else if (n instanceof AddExpr) endVisit((AddExpr) n);
        else if (n instanceof SubtractExpr) endVisit((SubtractExpr) n);
        else if (n instanceof StarExpr) endVisit((StarExpr) n);
        else if (n instanceof DivExpr) endVisit((DivExpr) n);
        else if (n instanceof TensorExpr) endVisit((TensorExpr) n);
        else if (n instanceof ExponentExpr) endVisit((ExponentExpr) n);
        else if (n instanceof IntCastExpr) endVisit((IntCastExpr) n);
        else if (n instanceof ScalarCastExpr) endVisit((ScalarCastExpr) n);
        else if (n instanceof NegatedUnaryExpr) endVisit((NegatedUnaryExpr) n);
        else if (n instanceof SqrtUnaryExpr) endVisit((SqrtUnaryExpr) n);
        else if (n instanceof ParenExpr) endVisit((ParenExpr) n);
        else if (n instanceof IntLitExpr) endVisit((IntLitExpr) n);
        else if (n instanceof DoubleLitExpr) endVisit((DoubleLitExpr) n);
        else if (n instanceof IdentExpr) endVisit((IdentExpr) n);
        else if (n instanceof DataBlockExpr) endVisit((DataBlockExpr) n);
        else if (n instanceof ContiguousDataBlockExpr) endVisit((ContiguousDataBlockExpr) n);
        else if (n instanceof StringLitExpr) endVisit((StringLitExpr) n);
        else if (n instanceof StringLiteral) endVisit((StringLiteral) n);
        else if (n instanceof Ident) endVisit((Ident) n);
        throw new UnsupportedOperationException("visit(" + n.getClass().toString() + ")");
    }
}

