package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 111:  AllocIndexList ::= AllocIndex
 *<li>Rule 112:  AllocIndexList ::= AllocIndexList ,$ AllocIndex
 *</b>
 */
public class AllocIndexList extends AbstractASTNodeList implements IAllocIndexList
{
    public IAllocIndex getAllocIndexAt(int i) { return (IAllocIndex) getElementAt(i); }

    public AllocIndexList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public AllocIndexList(IAllocIndex _AllocIndex, boolean leftRecursive)
    {
        super((ASTNode) _AllocIndex, leftRecursive);
        ((ASTNode) _AllocIndex).setParent(this);
    }

    public void add(IAllocIndex _AllocIndex)
    {
        super.add((ASTNode) _AllocIndex);
        ((ASTNode) _AllocIndex).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AllocIndexList)) return false;
        if (! super.equals(o)) return false;
        AllocIndexList other = (AllocIndexList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IAllocIndex element = getAllocIndexAt(i);
            if (! element.equals(other.getAllocIndexAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getAllocIndexAt(i).hashCode());
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
                IAllocIndex element = getAllocIndexAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


