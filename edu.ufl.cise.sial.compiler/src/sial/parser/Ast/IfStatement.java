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
 *<li>Rule 73:  Statement ::= if$ RelationalExpression EOLs$ StatementList endif$
 *</b>
 */
public class IfStatement extends ASTNode implements IStatement
{
    private RelationalExpression _RelationalExpression;
    private StatementList _StatementList;

    public RelationalExpression getRelationalExpression() { return _RelationalExpression; }
    public StatementList getStatementList() { return _StatementList; }

    public IfStatement(IToken leftIToken, IToken rightIToken,
                       RelationalExpression _RelationalExpression,
                       StatementList _StatementList)
    {
        super(leftIToken, rightIToken);

        this._RelationalExpression = _RelationalExpression;
        ((ASTNode) _RelationalExpression).setParent(this);
        this._StatementList = _StatementList;
        ((ASTNode) _StatementList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_RelationalExpression);
        list.add(_StatementList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IfStatement)) return false;
        if (! super.equals(o)) return false;
        IfStatement other = (IfStatement) o;
        if (! _RelationalExpression.equals(other._RelationalExpression)) return false;
        if (! _StatementList.equals(other._StatementList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_RelationalExpression.hashCode());
        hash = hash * 31 + (_StatementList.hashCode());
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
            _RelationalExpression.accept(v);
            _StatementList.accept(v);
        }
        v.endVisit(this);
    }
}


