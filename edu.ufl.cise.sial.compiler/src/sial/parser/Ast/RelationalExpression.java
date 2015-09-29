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
 *<b>
 *<li>Rule 139:  RelationalExpression ::= Expression$CastExpressionLeft RelOp Expression$CastExpressionRight
 *</b>
 */
public class RelationalExpression extends ASTNode implements IRelationalExpression
{
    private IExpression _CastExpressionLeft;
    private RelOp _RelOp;
    private IExpression _CastExpressionRight;

    public IExpression getCastExpressionLeft() { return _CastExpressionLeft; }
    public RelOp getRelOp() { return _RelOp; }
    public IExpression getCastExpressionRight() { return _CastExpressionRight; }

    public RelationalExpression(IToken leftIToken, IToken rightIToken,
                                IExpression _CastExpressionLeft,
                                RelOp _RelOp,
                                IExpression _CastExpressionRight)
    {
        super(leftIToken, rightIToken);

        this._CastExpressionLeft = _CastExpressionLeft;
        ((ASTNode) _CastExpressionLeft).setParent(this);
        this._RelOp = _RelOp;
        ((ASTNode) _RelOp).setParent(this);
        this._CastExpressionRight = _CastExpressionRight;
        ((ASTNode) _CastExpressionRight).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_CastExpressionLeft);
        list.add(_RelOp);
        list.add(_CastExpressionRight);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof RelationalExpression)) return false;
        if (! super.equals(o)) return false;
        RelationalExpression other = (RelationalExpression) o;
        if (! _CastExpressionLeft.equals(other._CastExpressionLeft)) return false;
        if (! _RelOp.equals(other._RelOp)) return false;
        if (! _CastExpressionRight.equals(other._CastExpressionRight)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_CastExpressionLeft.hashCode());
        hash = hash * 31 + (_RelOp.hashCode());
        hash = hash * 31 + (_CastExpressionRight.hashCode());
        return hash;
    }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }

    public void enter(Visitor v)
    {
        boolean checkChildren = v.visit(this);
        if (checkChildren)
        {
            _CastExpressionLeft.accept(v);
            _RelOp.accept(v);
            _CastExpressionRight.accept(v);
        }
        v.endVisit(this);
    }
}


