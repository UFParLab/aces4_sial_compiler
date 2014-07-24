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
 *<li>IfStatement
 *<li>IfElseStatement
 *<li>AllocateStatement
 *<li>DeallocateStatement
 *<li>AllocIndexIdent
 *<li>AllocIndexWildCard
 *<li>ContiguousAllocateStatement
 *<li>ContiguousDeallocateStatement
 *<li>CreateStatement
 *<li>DeleteStatement
 *<li>PutStatement
 *<li>GetStatement
 *<li>PrepareStatement
 *<li>RequestStatement
 *<li>CollectiveStatement
 *<li>DestroyStatement
 *<li>PrintStatement
 *<li>PrintlnStatement
 *<li>DataBlockArg
 *<li>IdentArg
 *<li>DoubleLitArg
 *<li>IntLitArg
 *<li>ExecuteStatement
 *<li>AssignToIdent
 *<li>AssignToBlock
 *<li>GPUSection
 *<li>GPUAllocate
 *<li>GPUFree
 *<li>GPUPut
 *<li>GPUGet
 *<li>SetPersistent
 *<li>RestorePersistent
 *<li>AssertSame
 *<li>BroadcastStatic
 *<li>AssignOp
 *<li>DataBlock
 *<li>RelOp
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


