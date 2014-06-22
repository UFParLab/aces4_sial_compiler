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
 * is implemented by:
 *<b>
 *<ul>
 *<li>DataBlock
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
public interface IExpression
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


