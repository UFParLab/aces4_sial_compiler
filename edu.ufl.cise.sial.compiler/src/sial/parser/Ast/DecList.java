package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 18:  DecList ::= $Empty
 *<li>Rule 19:  DecList ::= DecList Dec EOLs$
 *</b>
 */
public class DecList extends AbstractASTNodeList implements IDecList
{
    public IDec getDecAt(int i) { return (IDec) getElementAt(i); }

    public DecList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public DecList(IDec _Dec, boolean leftRecursive)
    {
        super((ASTNode) _Dec, leftRecursive);
        ((ASTNode) _Dec).setParent(this);
    }

    public void add(IDec _Dec)
    {
        super.add((ASTNode) _Dec);
        ((ASTNode) _Dec).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DecList)) return false;
        if (! super.equals(o)) return false;
        DecList other = (DecList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IDec element = getDecAt(i);
            if (! element.equals(other.getDecAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getDecAt(i).hashCode());
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
                IDec element = getDecAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


