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
 *<li>Rule 84:  ContiguousAllocIndexExpr ::= Expression
 *</b>
 */
public class ContiguousAllocIndexSingleExpr extends ASTNode implements IContiguousAllocIndexExpr
{
    private IExpression _Expression;

    public IExpression getExpression() { return _Expression; }

    public ContiguousAllocIndexSingleExpr(IToken leftIToken, IToken rightIToken,
                                          IExpression _Expression)
    {
        super(leftIToken, rightIToken);

        this._Expression = _Expression;
        ((ASTNode) _Expression).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Expression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ContiguousAllocIndexSingleExpr)) return false;
        if (! super.equals(o)) return false;
        ContiguousAllocIndexSingleExpr other = (ContiguousAllocIndexSingleExpr) o;
        if (! _Expression.equals(other._Expression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Expression.hashCode());
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
            _Expression.accept(v);
        v.endVisit(this);
    }
}


