package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 74:  Statement ::= if$ RelationalExpression EOLs$ StatementList$ifStatements else$ EOLs$ StatementList$elseStatements endif$
 *</b>
 */
public class IfElseStatement extends ASTNode implements IStatement
{
    private RelationalExpression _RelationalExpression;
    private StatementList _ifStatements;
    private StatementList _elseStatements;

    public RelationalExpression getRelationalExpression() { return _RelationalExpression; }
    public StatementList getifStatements() { return _ifStatements; }
    public StatementList getelseStatements() { return _elseStatements; }

    public IfElseStatement(IToken leftIToken, IToken rightIToken,
                           RelationalExpression _RelationalExpression,
                           StatementList _ifStatements,
                           StatementList _elseStatements)
    {
        super(leftIToken, rightIToken);

        this._RelationalExpression = _RelationalExpression;
        ((ASTNode) _RelationalExpression).setParent(this);
        this._ifStatements = _ifStatements;
        ((ASTNode) _ifStatements).setParent(this);
        this._elseStatements = _elseStatements;
        ((ASTNode) _elseStatements).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_RelationalExpression);
        list.add(_ifStatements);
        list.add(_elseStatements);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IfElseStatement)) return false;
        if (! super.equals(o)) return false;
        IfElseStatement other = (IfElseStatement) o;
        if (! _RelationalExpression.equals(other._RelationalExpression)) return false;
        if (! _ifStatements.equals(other._ifStatements)) return false;
        if (! _elseStatements.equals(other._elseStatements)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_RelationalExpression.hashCode());
        hash = hash * 31 + (_ifStatements.hashCode());
        hash = hash * 31 + (_elseStatements.hashCode());
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
            _ifStatements.accept(v);
            _elseStatements.accept(v);
        }
        v.endVisit(this);
    }
}


