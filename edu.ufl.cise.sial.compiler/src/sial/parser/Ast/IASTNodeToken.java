package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 * is always implemented by <b>ASTNodeToken</b>. It is also implemented by:
 *<b>
 *<ul>
 *<li>PredefinedModifier
 *<li>ContiguousModifier
 *<li>SparseModifier
 *<li>ArrayKind
 *<li>IndexKind
 *<li>IntLitRangeVal
 *<li>NegRangeVal
 *<li>IdentRangeVal
 *<li>CallStatement
 *<li>ReturnStatement
 *<li>ServerBarrierStatement
 *<li>SipBarrierStatement
 *<li>DoStatement
 *<li>DoStatementSubIndex
 *<li>PardoStatement
 *<li>Section
 *<li>ExitStatement
 *<li>CycleStatement
 *<li>IfStatement
 *<li>IfElseStatement
 *<li>AllocateStatement
 *<li>DeallocateStatement
 *<li>CreateStatement
 *<li>DeleteStatement
 *<li>PutStatement
 *<li>GetStatement
 *<li>PrepareStatement
 *<li>RequestStatement
 *<li>PrequestStatement
 *<li>CollectiveStatement
 *<li>DestroyStatement
 *<li>PrintlnStatement
 *<li>PrintStatement
 *<li>PrintIndexStatement
 *<li>PrintScalarStatement
 *<li>ExecuteStatement
 *<li>AssignStatement
 *<li>GpuStatement
 *<li>GpuAllocate
 *<li>GpuFree
 *<li>GpuPut
 *<li>GpuGet
 *<li>SetPersistent
 *<li>RestorePersistent
 *<li>AssignOpEqual
 *<li>AssignOpPlus
 *<li>AssignOpMinus
 *<li>AssignOpStar
 *<li>DataBlock
 *<li>AllocIndexIdent
 *<li>AllocIndexWildCard
 *<li>RelOp
 *<li>BinOpStar
 *<li>BinOpDiv
 *<li>BinOpPlus
 *<li>BinOpMinus
 *<li>BinOpTensor
 *<li>IntLitPrimary
 *<li>DoubleLitPrimary
 *<li>IdentPrimary
 *<li>DataBlockPrimary
 *<li>StringLitPrimary
 *<li>StringLiteral
 *<li>Ident
 *</ul>
 *</b>
 */
public interface IASTNodeToken
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


