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
 *<li>Rule 126:  RelationalExpression ::= Expression$UnaryExpressionLeft RelOp Expression$UnaryExpressionRight
 *</b>
 */
public class RelationalExpression extends ASTNode implements IRelationalExpression
{
    private IExpression _UnaryExpressionLeft;
    private RelOp _RelOp;
    private IExpression _UnaryExpressionRight;

    public IExpression getUnaryExpressionLeft() { return _UnaryExpressionLeft; }
    public RelOp getRelOp() { return _RelOp; }
    public IExpression getUnaryExpressionRight() { return _UnaryExpressionRight; }

    public RelationalExpression(IToken leftIToken, IToken rightIToken,
                                IExpression _UnaryExpressionLeft,
                                RelOp _RelOp,
                                IExpression _UnaryExpressionRight)
    {
        super(leftIToken, rightIToken);

        this._UnaryExpressionLeft = _UnaryExpressionLeft;
        ((ASTNode) _UnaryExpressionLeft).setParent(this);
        this._RelOp = _RelOp;
        ((ASTNode) _RelOp).setParent(this);
        this._UnaryExpressionRight = _UnaryExpressionRight;
        ((ASTNode) _UnaryExpressionRight).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_UnaryExpressionLeft);
        list.add(_RelOp);
        list.add(_UnaryExpressionRight);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof RelationalExpression)) return false;
        if (! super.equals(o)) return false;
        RelationalExpression other = (RelationalExpression) o;
        if (! _UnaryExpressionLeft.equals(other._UnaryExpressionLeft)) return false;
        if (! _RelOp.equals(other._RelOp)) return false;
        if (! _UnaryExpressionRight.equals(other._UnaryExpressionRight)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_UnaryExpressionLeft.hashCode());
        hash = hash * 31 + (_RelOp.hashCode());
        hash = hash * 31 + (_UnaryExpressionRight.hashCode());
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
            _UnaryExpressionLeft.accept(v);
            _RelOp.accept(v);
            _UnaryExpressionRight.accept(v);
        }
        v.endVisit(this);
    }
}


