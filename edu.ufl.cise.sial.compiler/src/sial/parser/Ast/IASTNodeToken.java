package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;
  import sial.parser.context.ExpressionType.EType;
  import java.util.EnumSet;

/**
 * is always implemented by <b>ASTNodeToken</b>. It is also implemented by:
 *<b>
 *<ul>
 *<li>Modifier
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
 *<li>AllocIndexIdent
 *<li>AllocIndexWildCard
 *<li>ContiguousAllocateStatement
 *<li>ContiguousDeallocateStatement
 *<li>ContiguousAllocIndexSingleExpr
 *<li>ContiguousAllocIndexRangeExpr
 *<li>ContiguousAllocIndexWildExpr
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
 *<li>PrintIntStatement
 *<li>DataBlockArg
 *<li>IdentArg
 *<li>DoubleLitArg
 *<li>ExecuteStatement
 *<li>AssignToIdent
 *<li>AssignToBlock
 *<li>GPUSection
 *<li>GPUAllocateBlock
 *<li>GPUFreeBlock
 *<li>GPUPutBlock
 *<li>GPUGetBlock
 *<li>SetPersistent
 *<li>RestorePersistent
 *<li>AssertSame
 *<li>AssignOp
 *<li>DataBlock
 *<li>RelOp
 *<li>AddExpr
 *<li>SubtractExpr
 *<li>StarExpr
 *<li>DivExpr
 *<li>TensorExpr
 *<li>IntCastExpr
 *<li>ScalarCastExpr
 *<li>NegatedUnaryExpr
 *<li>ParenExpr
 *<li>IntLitExpr
 *<li>DoubleLitExpr
 *<li>IdentExpr
 *<li>DataBlockExpr
 *<li>StringLitExpr
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


