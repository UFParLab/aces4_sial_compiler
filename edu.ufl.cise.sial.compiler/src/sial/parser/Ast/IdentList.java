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
 *<li>Rule 112:  Indices ::= Ident
 *<li>Rule 113:  Indices ::= Indices ,$ Ident
 *</b>
 */
public class IdentList extends AbstractASTNodeList implements IIndices
{
    public Ident getIdentAt(int i) { return (Ident) getElementAt(i); }

    public IdentList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public IdentList(Ident _Ident, boolean leftRecursive)
    {
        super((ASTNode) _Ident, leftRecursive);
        ((ASTNode) _Ident).setParent(this);
    }

    public void add(Ident _Ident)
    {
        super.add((ASTNode) _Ident);
        ((ASTNode) _Ident).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IdentList)) return false;
        if (! super.equals(o)) return false;
        IdentList other = (IdentList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Ident element = getIdentAt(i);
            if (! element.equals(other.getIdentAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getIdentAt(i).hashCode());
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
                Ident element = getIdentAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


