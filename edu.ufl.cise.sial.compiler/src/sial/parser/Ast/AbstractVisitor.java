package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

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

    public boolean visit(Sip_ConsistentModifier n) { unimplementedVisitor("visit(Sip_ConsistentModifier)"); return true; }
    public void endVisit(Sip_ConsistentModifier n) { unimplementedVisitor("endVisit(Sip_ConsistentModifier)"); }

    public boolean visit(PredefinedModifier n) { unimplementedVisitor("visit(PredefinedModifier)"); return true; }
    public void endVisit(PredefinedModifier n) { unimplementedVisitor("endVisit(PredefinedModifier)"); }

    public boolean visit(PersistentModifier n) { unimplementedVisitor("visit(PersistentModifier)"); return true; }
    public void endVisit(PersistentModifier n) { unimplementedVisitor("endVisit(PersistentModifier)"); }

    public boolean visit(ScopedExtentModifier n) { unimplementedVisitor("visit(ScopedExtentModifier)"); return true; }
    public void endVisit(ScopedExtentModifier n) { unimplementedVisitor("endVisit(ScopedExtentModifier)"); }

    public boolean visit(ContiguousModifier n) { unimplementedVisitor("visit(ContiguousModifier)"); return true; }
    public void endVisit(ContiguousModifier n) { unimplementedVisitor("endVisit(ContiguousModifier)"); }

    public boolean visit(Auto_AllocateModifier n) { unimplementedVisitor("visit(Auto_AllocateModifier)"); return true; }
    public void endVisit(Auto_AllocateModifier n) { unimplementedVisitor("endVisit(Auto_AllocateModifier)"); }

    public boolean visit(DecList n) { unimplementedVisitor("visit(DecList)"); return true; }
    public void endVisit(DecList n) { unimplementedVisitor("endVisit(DecList)"); }

    public boolean visit(ScalarDec n) { unimplementedVisitor("visit(ScalarDec)"); return true; }
    public void endVisit(ScalarDec n) { unimplementedVisitor("endVisit(ScalarDec)"); }

    public boolean visit(IntDec n) { unimplementedVisitor("visit(IntDec)"); return true; }
    public void endVisit(IntDec n) { unimplementedVisitor("endVisit(IntDec)"); }

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

    public boolean visit(CycleStatement n) { unimplementedVisitor("visit(CycleStatement)"); return true; }
    public void endVisit(CycleStatement n) { unimplementedVisitor("endVisit(CycleStatement)"); }

    public boolean visit(IfStatement n) { unimplementedVisitor("visit(IfStatement)"); return true; }
    public void endVisit(IfStatement n) { unimplementedVisitor("endVisit(IfStatement)"); }

    public boolean visit(IfElseStatement n) { unimplementedVisitor("visit(IfElseStatement)"); return true; }
    public void endVisit(IfElseStatement n) { unimplementedVisitor("endVisit(IfElseStatement)"); }

    public boolean visit(AllocateStatement n) { unimplementedVisitor("visit(AllocateStatement)"); return true; }
    public void endVisit(AllocateStatement n) { unimplementedVisitor("endVisit(AllocateStatement)"); }

    public boolean visit(DeallocateStatement n) { unimplementedVisitor("visit(DeallocateStatement)"); return true; }
    public void endVisit(DeallocateStatement n) { unimplementedVisitor("endVisit(DeallocateStatement)"); }

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

    public boolean visit(PrequestStatement n) { unimplementedVisitor("visit(PrequestStatement)"); return true; }
    public void endVisit(PrequestStatement n) { unimplementedVisitor("endVisit(PrequestStatement)"); }

    public boolean visit(CollectiveStatement n) { unimplementedVisitor("visit(CollectiveStatement)"); return true; }
    public void endVisit(CollectiveStatement n) { unimplementedVisitor("endVisit(CollectiveStatement)"); }

    public boolean visit(DestroyStatement n) { unimplementedVisitor("visit(DestroyStatement)"); return true; }
    public void endVisit(DestroyStatement n) { unimplementedVisitor("endVisit(DestroyStatement)"); }

    public boolean visit(PrintlnStatement n) { unimplementedVisitor("visit(PrintlnStatement)"); return true; }
    public void endVisit(PrintlnStatement n) { unimplementedVisitor("endVisit(PrintlnStatement)"); }

    public boolean visit(PrintStatement n) { unimplementedVisitor("visit(PrintStatement)"); return true; }
    public void endVisit(PrintStatement n) { unimplementedVisitor("endVisit(PrintStatement)"); }

    public boolean visit(PrintIndexStatement n) { unimplementedVisitor("visit(PrintIndexStatement)"); return true; }
    public void endVisit(PrintIndexStatement n) { unimplementedVisitor("endVisit(PrintIndexStatement)"); }

    public boolean visit(PrintScalarStatement n) { unimplementedVisitor("visit(PrintScalarStatement)"); return true; }
    public void endVisit(PrintScalarStatement n) { unimplementedVisitor("endVisit(PrintScalarStatement)"); }

    public boolean visit(ArgList n) { unimplementedVisitor("visit(ArgList)"); return true; }
    public void endVisit(ArgList n) { unimplementedVisitor("endVisit(ArgList)"); }

    public boolean visit(ExecuteStatement n) { unimplementedVisitor("visit(ExecuteStatement)"); return true; }
    public void endVisit(ExecuteStatement n) { unimplementedVisitor("endVisit(ExecuteStatement)"); }

    public boolean visit(AssignStatement n) { unimplementedVisitor("visit(AssignStatement)"); return true; }
    public void endVisit(AssignStatement n) { unimplementedVisitor("endVisit(AssignStatement)"); }

    public boolean visit(GpuOn n) { unimplementedVisitor("visit(GpuOn)"); return true; }
    public void endVisit(GpuOn n) { unimplementedVisitor("endVisit(GpuOn)"); }

    public boolean visit(GpuOff n) { unimplementedVisitor("visit(GpuOff)"); return true; }
    public void endVisit(GpuOff n) { unimplementedVisitor("endVisit(GpuOff)"); }

    public boolean visit(GpuAllocate n) { unimplementedVisitor("visit(GpuAllocate)"); return true; }
    public void endVisit(GpuAllocate n) { unimplementedVisitor("endVisit(GpuAllocate)"); }

    public boolean visit(GpuFree n) { unimplementedVisitor("visit(GpuFree)"); return true; }
    public void endVisit(GpuFree n) { unimplementedVisitor("endVisit(GpuFree)"); }

    public boolean visit(GpuPut n) { unimplementedVisitor("visit(GpuPut)"); return true; }
    public void endVisit(GpuPut n) { unimplementedVisitor("endVisit(GpuPut)"); }

    public boolean visit(GpuGet n) { unimplementedVisitor("visit(GpuGet)"); return true; }
    public void endVisit(GpuGet n) { unimplementedVisitor("endVisit(GpuGet)"); }

    public boolean visit(AssignOpEqual n) { unimplementedVisitor("visit(AssignOpEqual)"); return true; }
    public void endVisit(AssignOpEqual n) { unimplementedVisitor("endVisit(AssignOpEqual)"); }

    public boolean visit(AssignOpPlus n) { unimplementedVisitor("visit(AssignOpPlus)"); return true; }
    public void endVisit(AssignOpPlus n) { unimplementedVisitor("endVisit(AssignOpPlus)"); }

    public boolean visit(AssignOpMinus n) { unimplementedVisitor("visit(AssignOpMinus)"); return true; }
    public void endVisit(AssignOpMinus n) { unimplementedVisitor("endVisit(AssignOpMinus)"); }

    public boolean visit(AssignOpStar n) { unimplementedVisitor("visit(AssignOpStar)"); return true; }
    public void endVisit(AssignOpStar n) { unimplementedVisitor("endVisit(AssignOpStar)"); }

    public boolean visit(DataBlock n) { unimplementedVisitor("visit(DataBlock)"); return true; }
    public void endVisit(DataBlock n) { unimplementedVisitor("endVisit(DataBlock)"); }

    public boolean visit(IdentList n) { unimplementedVisitor("visit(IdentList)"); return true; }
    public void endVisit(IdentList n) { unimplementedVisitor("endVisit(IdentList)"); }

    public boolean visit(AllocIndexIdent n) { unimplementedVisitor("visit(AllocIndexIdent)"); return true; }
    public void endVisit(AllocIndexIdent n) { unimplementedVisitor("endVisit(AllocIndexIdent)"); }

    public boolean visit(AllocIndexWildCard n) { unimplementedVisitor("visit(AllocIndexWildCard)"); return true; }
    public void endVisit(AllocIndexWildCard n) { unimplementedVisitor("endVisit(AllocIndexWildCard)"); }

    public boolean visit(AllocIndexList n) { unimplementedVisitor("visit(AllocIndexList)"); return true; }
    public void endVisit(AllocIndexList n) { unimplementedVisitor("endVisit(AllocIndexList)"); }

    public boolean visit(AllocIndexListopt n) { unimplementedVisitor("visit(AllocIndexListopt)"); return true; }
    public void endVisit(AllocIndexListopt n) { unimplementedVisitor("endVisit(AllocIndexListopt)"); }

    public boolean visit(RelationalExpression n) { unimplementedVisitor("visit(RelationalExpression)"); return true; }
    public void endVisit(RelationalExpression n) { unimplementedVisitor("endVisit(RelationalExpression)"); }

    public boolean visit(RelOp n) { unimplementedVisitor("visit(RelOp)"); return true; }
    public void endVisit(RelOp n) { unimplementedVisitor("endVisit(RelOp)"); }

    public boolean visit(BinaryExpression n) { unimplementedVisitor("visit(BinaryExpression)"); return true; }
    public void endVisit(BinaryExpression n) { unimplementedVisitor("endVisit(BinaryExpression)"); }

    public boolean visit(BinOpStar n) { unimplementedVisitor("visit(BinOpStar)"); return true; }
    public void endVisit(BinOpStar n) { unimplementedVisitor("endVisit(BinOpStar)"); }

    public boolean visit(BinOpDiv n) { unimplementedVisitor("visit(BinOpDiv)"); return true; }
    public void endVisit(BinOpDiv n) { unimplementedVisitor("endVisit(BinOpDiv)"); }

    public boolean visit(BinOpPlus n) { unimplementedVisitor("visit(BinOpPlus)"); return true; }
    public void endVisit(BinOpPlus n) { unimplementedVisitor("endVisit(BinOpPlus)"); }

    public boolean visit(BinOpMinus n) { unimplementedVisitor("visit(BinOpMinus)"); return true; }
    public void endVisit(BinOpMinus n) { unimplementedVisitor("endVisit(BinOpMinus)"); }

    public boolean visit(BinOpTensor n) { unimplementedVisitor("visit(BinOpTensor)"); return true; }
    public void endVisit(BinOpTensor n) { unimplementedVisitor("endVisit(BinOpTensor)"); }

    public boolean visit(NegatedUnary n) { unimplementedVisitor("visit(NegatedUnary)"); return true; }
    public void endVisit(NegatedUnary n) { unimplementedVisitor("endVisit(NegatedUnary)"); }

    public boolean visit(IntLitPrimary n) { unimplementedVisitor("visit(IntLitPrimary)"); return true; }
    public void endVisit(IntLitPrimary n) { unimplementedVisitor("endVisit(IntLitPrimary)"); }

    public boolean visit(DoubleLitPrimary n) { unimplementedVisitor("visit(DoubleLitPrimary)"); return true; }
    public void endVisit(DoubleLitPrimary n) { unimplementedVisitor("endVisit(DoubleLitPrimary)"); }

    public boolean visit(IdentPrimary n) { unimplementedVisitor("visit(IdentPrimary)"); return true; }
    public void endVisit(IdentPrimary n) { unimplementedVisitor("endVisit(IdentPrimary)"); }

    public boolean visit(DataBlockPrimary n) { unimplementedVisitor("visit(DataBlockPrimary)"); return true; }
    public void endVisit(DataBlockPrimary n) { unimplementedVisitor("endVisit(DataBlockPrimary)"); }

    public boolean visit(StringLitPrimary n) { unimplementedVisitor("visit(StringLitPrimary)"); return true; }
    public void endVisit(StringLitPrimary n) { unimplementedVisitor("endVisit(StringLitPrimary)"); }

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
        else if (n instanceof Sip_ConsistentModifier) return visit((Sip_ConsistentModifier) n);
        else if (n instanceof PredefinedModifier) return visit((PredefinedModifier) n);
        else if (n instanceof PersistentModifier) return visit((PersistentModifier) n);
        else if (n instanceof ScopedExtentModifier) return visit((ScopedExtentModifier) n);
        else if (n instanceof ContiguousModifier) return visit((ContiguousModifier) n);
        else if (n instanceof Auto_AllocateModifier) return visit((Auto_AllocateModifier) n);
        else if (n instanceof DecList) return visit((DecList) n);
        else if (n instanceof ScalarDec) return visit((ScalarDec) n);
        else if (n instanceof IntDec) return visit((IntDec) n);
        else if (n instanceof ArrayDec) return visit((ArrayDec) n);
        else if (n instanceof ArrayKind) return visit((ArrayKind) n);
        else if (n instanceof DimensionList) return visit((DimensionList) n);
        else if (n instanceof IndexDec) return visit((IndexDec) n);
        else if (n instanceof IndexKind) return visit((IndexKind) n);
        else if (n instanceof SubIndexDec) return visit((SubIndexDec) n);
        else if (n instanceof Range) return visit((Range) n);
        else if (n instanceof IntLitRangeVal) return visit((IntLitRangeVal) n);
        else if (n instanceof IdentRangeVal) return visit((IdentRangeVal) n);
        else if (n instanceof ProcDec) return visit((ProcDec) n);
        else if (n instanceof SpecialDec) return visit((SpecialDec) n);
        else if (n instanceof StatementList) return visit((StatementList) n);
        else if (n instanceof WhereClause) return visit((WhereClause) n);
        else if (n instanceof WhereClauseList) return visit((WhereClauseList) n);
        else if (n instanceof CallStatement) return visit((CallStatement) n);
        else if (n instanceof ReturnStatement) return visit((ReturnStatement) n);
        else if (n instanceof ServerBarrierStatement) return visit((ServerBarrierStatement) n);
        else if (n instanceof SipBarrierStatement) return visit((SipBarrierStatement) n);
        else if (n instanceof DoStatement) return visit((DoStatement) n);
        else if (n instanceof DoStatementSubIndex) return visit((DoStatementSubIndex) n);
        else if (n instanceof PardoStatement) return visit((PardoStatement) n);
        else if (n instanceof Section) return visit((Section) n);
        else if (n instanceof ExitStatement) return visit((ExitStatement) n);
        else if (n instanceof CycleStatement) return visit((CycleStatement) n);
        else if (n instanceof IfStatement) return visit((IfStatement) n);
        else if (n instanceof IfElseStatement) return visit((IfElseStatement) n);
        else if (n instanceof AllocateStatement) return visit((AllocateStatement) n);
        else if (n instanceof DeallocateStatement) return visit((DeallocateStatement) n);
        else if (n instanceof CreateStatement) return visit((CreateStatement) n);
        else if (n instanceof DeleteStatement) return visit((DeleteStatement) n);
        else if (n instanceof PutStatement) return visit((PutStatement) n);
        else if (n instanceof GetStatement) return visit((GetStatement) n);
        else if (n instanceof PrepareStatement) return visit((PrepareStatement) n);
        else if (n instanceof RequestStatement) return visit((RequestStatement) n);
        else if (n instanceof PrequestStatement) return visit((PrequestStatement) n);
        else if (n instanceof CollectiveStatement) return visit((CollectiveStatement) n);
        else if (n instanceof DestroyStatement) return visit((DestroyStatement) n);
        else if (n instanceof PrintlnStatement) return visit((PrintlnStatement) n);
        else if (n instanceof PrintStatement) return visit((PrintStatement) n);
        else if (n instanceof PrintIndexStatement) return visit((PrintIndexStatement) n);
        else if (n instanceof PrintScalarStatement) return visit((PrintScalarStatement) n);
        else if (n instanceof ArgList) return visit((ArgList) n);
        else if (n instanceof ExecuteStatement) return visit((ExecuteStatement) n);
        else if (n instanceof AssignStatement) return visit((AssignStatement) n);
        else if (n instanceof GpuOn) return visit((GpuOn) n);
        else if (n instanceof GpuOff) return visit((GpuOff) n);
        else if (n instanceof GpuAllocate) return visit((GpuAllocate) n);
        else if (n instanceof GpuFree) return visit((GpuFree) n);
        else if (n instanceof GpuPut) return visit((GpuPut) n);
        else if (n instanceof GpuGet) return visit((GpuGet) n);
        else if (n instanceof AssignOpEqual) return visit((AssignOpEqual) n);
        else if (n instanceof AssignOpPlus) return visit((AssignOpPlus) n);
        else if (n instanceof AssignOpMinus) return visit((AssignOpMinus) n);
        else if (n instanceof AssignOpStar) return visit((AssignOpStar) n);
        else if (n instanceof DataBlock) return visit((DataBlock) n);
        else if (n instanceof IdentList) return visit((IdentList) n);
        else if (n instanceof AllocIndexIdent) return visit((AllocIndexIdent) n);
        else if (n instanceof AllocIndexWildCard) return visit((AllocIndexWildCard) n);
        else if (n instanceof AllocIndexList) return visit((AllocIndexList) n);
        else if (n instanceof AllocIndexListopt) return visit((AllocIndexListopt) n);
        else if (n instanceof RelationalExpression) return visit((RelationalExpression) n);
        else if (n instanceof RelOp) return visit((RelOp) n);
        else if (n instanceof BinaryExpression) return visit((BinaryExpression) n);
        else if (n instanceof BinOpStar) return visit((BinOpStar) n);
        else if (n instanceof BinOpDiv) return visit((BinOpDiv) n);
        else if (n instanceof BinOpPlus) return visit((BinOpPlus) n);
        else if (n instanceof BinOpMinus) return visit((BinOpMinus) n);
        else if (n instanceof BinOpTensor) return visit((BinOpTensor) n);
        else if (n instanceof NegatedUnary) return visit((NegatedUnary) n);
        else if (n instanceof IntLitPrimary) return visit((IntLitPrimary) n);
        else if (n instanceof DoubleLitPrimary) return visit((DoubleLitPrimary) n);
        else if (n instanceof IdentPrimary) return visit((IdentPrimary) n);
        else if (n instanceof DataBlockPrimary) return visit((DataBlockPrimary) n);
        else if (n instanceof StringLitPrimary) return visit((StringLitPrimary) n);
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
        else if (n instanceof Sip_ConsistentModifier) endVisit((Sip_ConsistentModifier) n);
        else if (n instanceof PredefinedModifier) endVisit((PredefinedModifier) n);
        else if (n instanceof PersistentModifier) endVisit((PersistentModifier) n);
        else if (n instanceof ScopedExtentModifier) endVisit((ScopedExtentModifier) n);
        else if (n instanceof ContiguousModifier) endVisit((ContiguousModifier) n);
        else if (n instanceof Auto_AllocateModifier) endVisit((Auto_AllocateModifier) n);
        else if (n instanceof DecList) endVisit((DecList) n);
        else if (n instanceof ScalarDec) endVisit((ScalarDec) n);
        else if (n instanceof IntDec) endVisit((IntDec) n);
        else if (n instanceof ArrayDec) endVisit((ArrayDec) n);
        else if (n instanceof ArrayKind) endVisit((ArrayKind) n);
        else if (n instanceof DimensionList) endVisit((DimensionList) n);
        else if (n instanceof IndexDec) endVisit((IndexDec) n);
        else if (n instanceof IndexKind) endVisit((IndexKind) n);
        else if (n instanceof SubIndexDec) endVisit((SubIndexDec) n);
        else if (n instanceof Range) endVisit((Range) n);
        else if (n instanceof IntLitRangeVal) endVisit((IntLitRangeVal) n);
        else if (n instanceof IdentRangeVal) endVisit((IdentRangeVal) n);
        else if (n instanceof ProcDec) endVisit((ProcDec) n);
        else if (n instanceof SpecialDec) endVisit((SpecialDec) n);
        else if (n instanceof StatementList) endVisit((StatementList) n);
        else if (n instanceof WhereClause) endVisit((WhereClause) n);
        else if (n instanceof WhereClauseList) endVisit((WhereClauseList) n);
        else if (n instanceof CallStatement) endVisit((CallStatement) n);
        else if (n instanceof ReturnStatement) endVisit((ReturnStatement) n);
        else if (n instanceof ServerBarrierStatement) endVisit((ServerBarrierStatement) n);
        else if (n instanceof SipBarrierStatement) endVisit((SipBarrierStatement) n);
        else if (n instanceof DoStatement) endVisit((DoStatement) n);
        else if (n instanceof DoStatementSubIndex) endVisit((DoStatementSubIndex) n);
        else if (n instanceof PardoStatement) endVisit((PardoStatement) n);
        else if (n instanceof Section) endVisit((Section) n);
        else if (n instanceof ExitStatement) endVisit((ExitStatement) n);
        else if (n instanceof CycleStatement) endVisit((CycleStatement) n);
        else if (n instanceof IfStatement) endVisit((IfStatement) n);
        else if (n instanceof IfElseStatement) endVisit((IfElseStatement) n);
        else if (n instanceof AllocateStatement) endVisit((AllocateStatement) n);
        else if (n instanceof DeallocateStatement) endVisit((DeallocateStatement) n);
        else if (n instanceof CreateStatement) endVisit((CreateStatement) n);
        else if (n instanceof DeleteStatement) endVisit((DeleteStatement) n);
        else if (n instanceof PutStatement) endVisit((PutStatement) n);
        else if (n instanceof GetStatement) endVisit((GetStatement) n);
        else if (n instanceof PrepareStatement) endVisit((PrepareStatement) n);
        else if (n instanceof RequestStatement) endVisit((RequestStatement) n);
        else if (n instanceof PrequestStatement) endVisit((PrequestStatement) n);
        else if (n instanceof CollectiveStatement) endVisit((CollectiveStatement) n);
        else if (n instanceof DestroyStatement) endVisit((DestroyStatement) n);
        else if (n instanceof PrintlnStatement) endVisit((PrintlnStatement) n);
        else if (n instanceof PrintStatement) endVisit((PrintStatement) n);
        else if (n instanceof PrintIndexStatement) endVisit((PrintIndexStatement) n);
        else if (n instanceof PrintScalarStatement) endVisit((PrintScalarStatement) n);
        else if (n instanceof ArgList) endVisit((ArgList) n);
        else if (n instanceof ExecuteStatement) endVisit((ExecuteStatement) n);
        else if (n instanceof AssignStatement) endVisit((AssignStatement) n);
        else if (n instanceof GpuOn) endVisit((GpuOn) n);
        else if (n instanceof GpuOff) endVisit((GpuOff) n);
        else if (n instanceof GpuAllocate) endVisit((GpuAllocate) n);
        else if (n instanceof GpuFree) endVisit((GpuFree) n);
        else if (n instanceof GpuPut) endVisit((GpuPut) n);
        else if (n instanceof GpuGet) endVisit((GpuGet) n);
        else if (n instanceof AssignOpEqual) endVisit((AssignOpEqual) n);
        else if (n instanceof AssignOpPlus) endVisit((AssignOpPlus) n);
        else if (n instanceof AssignOpMinus) endVisit((AssignOpMinus) n);
        else if (n instanceof AssignOpStar) endVisit((AssignOpStar) n);
        else if (n instanceof DataBlock) endVisit((DataBlock) n);
        else if (n instanceof IdentList) endVisit((IdentList) n);
        else if (n instanceof AllocIndexIdent) endVisit((AllocIndexIdent) n);
        else if (n instanceof AllocIndexWildCard) endVisit((AllocIndexWildCard) n);
        else if (n instanceof AllocIndexList) endVisit((AllocIndexList) n);
        else if (n instanceof AllocIndexListopt) endVisit((AllocIndexListopt) n);
        else if (n instanceof RelationalExpression) endVisit((RelationalExpression) n);
        else if (n instanceof RelOp) endVisit((RelOp) n);
        else if (n instanceof BinaryExpression) endVisit((BinaryExpression) n);
        else if (n instanceof BinOpStar) endVisit((BinOpStar) n);
        else if (n instanceof BinOpDiv) endVisit((BinOpDiv) n);
        else if (n instanceof BinOpPlus) endVisit((BinOpPlus) n);
        else if (n instanceof BinOpMinus) endVisit((BinOpMinus) n);
        else if (n instanceof BinOpTensor) endVisit((BinOpTensor) n);
        else if (n instanceof NegatedUnary) endVisit((NegatedUnary) n);
        else if (n instanceof IntLitPrimary) endVisit((IntLitPrimary) n);
        else if (n instanceof DoubleLitPrimary) endVisit((DoubleLitPrimary) n);
        else if (n instanceof IdentPrimary) endVisit((IdentPrimary) n);
        else if (n instanceof DataBlockPrimary) endVisit((DataBlockPrimary) n);
        else if (n instanceof StringLitPrimary) endVisit((StringLitPrimary) n);
        else if (n instanceof StringLiteral) endVisit((StringLiteral) n);
        else if (n instanceof Ident) endVisit((Ident) n);
        throw new UnsupportedOperationException("visit(" + n.getClass().toString() + ")");
    }
}

