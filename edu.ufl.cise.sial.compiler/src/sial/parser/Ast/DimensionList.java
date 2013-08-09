package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 38:  DimensionList ::= Dimension
 *<li>Rule 39:  DimensionList ::= DimensionList ,$ Dimension
 *</b>
 */
public class DimensionList extends AbstractASTNodeList implements IDimensionList
{
    public Ident getDimensionAt(int i) { return (Ident) getElementAt(i); }

    public DimensionList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public DimensionList(Ident _Dimension, boolean leftRecursive)
    {
        super((ASTNode) _Dimension, leftRecursive);
        ((ASTNode) _Dimension).setParent(this);
    }

    public void add(Ident _Dimension)
    {
        super.add((ASTNode) _Dimension);
        ((ASTNode) _Dimension).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DimensionList)) return false;
        if (! super.equals(o)) return false;
        DimensionList other = (DimensionList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Ident element = getDimensionAt(i);
            if (! element.equals(other.getDimensionAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getDimensionAt(i).hashCode());
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
                Ident element = getDimensionAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


