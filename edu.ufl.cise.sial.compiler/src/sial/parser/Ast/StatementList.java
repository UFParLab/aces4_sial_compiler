package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 54:  StatementList ::= $Empty
 *<li>Rule 55:  StatementList ::= StatementList Statement EOLs$
 *</b>
 */
public class StatementList extends AbstractASTNodeList implements IStatementList
{
    public IStatement getStatementAt(int i) { return (IStatement) getElementAt(i); }

    public StatementList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public StatementList(IStatement _Statement, boolean leftRecursive)
    {
        super((ASTNode) _Statement, leftRecursive);
        ((ASTNode) _Statement).setParent(this);
    }

    public void add(IStatement _Statement)
    {
        super.add((ASTNode) _Statement);
        ((ASTNode) _Statement).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof StatementList)) return false;
        if (! super.equals(o)) return false;
        StatementList other = (StatementList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IStatement element = getStatementAt(i);
            if (! element.equals(other.getStatementAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getStatementAt(i).hashCode());
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
            for (int i = 0; i < size(); i++)
            {
                IStatement element = getStatementAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


