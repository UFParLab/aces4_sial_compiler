package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 58:  WhereClause ::= where$ RelationalExpression
 *</b>
 */
public class WhereClause extends ASTNode implements IWhereClause
{
    private RelationalExpression _RelationalExpression;

    public RelationalExpression getRelationalExpression() { return _RelationalExpression; }

    public WhereClause(IToken leftIToken, IToken rightIToken,
                       RelationalExpression _RelationalExpression)
    {
        super(leftIToken, rightIToken);

        this._RelationalExpression = _RelationalExpression;
        ((ASTNode) _RelationalExpression).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_RelationalExpression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof WhereClause)) return false;
        if (! super.equals(o)) return false;
        WhereClause other = (WhereClause) o;
        if (! _RelationalExpression.equals(other._RelationalExpression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_RelationalExpression.hashCode());
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
            _RelationalExpression.accept(v);
        v.endVisit(this);
    }
}


