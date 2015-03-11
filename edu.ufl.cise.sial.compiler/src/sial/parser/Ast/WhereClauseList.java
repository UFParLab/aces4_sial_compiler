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
 *<li>Rule 61:  WhereClauseList ::= $Empty
 *<li>Rule 62:  WhereClauseList ::= WhereClauseList WhereClause EOLs$
 *</b>
 */
public class WhereClauseList extends AbstractASTNodeList implements IWhereClauseList
{
    public WhereClause getWhereClauseAt(int i) { return (WhereClause) getElementAt(i); }

    public WhereClauseList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public WhereClauseList(WhereClause _WhereClause, boolean leftRecursive)
    {
        super((ASTNode) _WhereClause, leftRecursive);
        ((ASTNode) _WhereClause).setParent(this);
    }

    public void add(WhereClause _WhereClause)
    {
        super.add((ASTNode) _WhereClause);
        ((ASTNode) _WhereClause).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof WhereClauseList)) return false;
        if (! super.equals(o)) return false;
        WhereClauseList other = (WhereClauseList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            WhereClause element = getWhereClauseAt(i);
            if (! element.equals(other.getWhereClauseAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getWhereClauseAt(i).hashCode());
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
                WhereClause element = getWhereClauseAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


