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
 *<li>Rule 88:  ContiguousAllocIndexExprList ::= ContiguousAllocIndexExpr
 *<li>Rule 89:  ContiguousAllocIndexExprList ::= ContiguousAllocIndexExprList ,$ ContiguousAllocIndexExpr
 *</b>
 */
public class ContiguousAllocIndexExprList extends AbstractASTNodeList implements IContiguousAllocIndexExprList
{
    public IContiguousAllocIndexExpr getContiguousAllocIndexExprAt(int i) { return (IContiguousAllocIndexExpr) getElementAt(i); }

    public ContiguousAllocIndexExprList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ContiguousAllocIndexExprList(IContiguousAllocIndexExpr _ContiguousAllocIndexExpr, boolean leftRecursive)
    {
        super((ASTNode) _ContiguousAllocIndexExpr, leftRecursive);
        ((ASTNode) _ContiguousAllocIndexExpr).setParent(this);
    }

    public void add(IContiguousAllocIndexExpr _ContiguousAllocIndexExpr)
    {
        super.add((ASTNode) _ContiguousAllocIndexExpr);
        ((ASTNode) _ContiguousAllocIndexExpr).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ContiguousAllocIndexExprList)) return false;
        if (! super.equals(o)) return false;
        ContiguousAllocIndexExprList other = (ContiguousAllocIndexExprList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IContiguousAllocIndexExpr element = getContiguousAllocIndexExprAt(i);
            if (! element.equals(other.getContiguousAllocIndexExprAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getContiguousAllocIndexExprAt(i).hashCode());
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
                IContiguousAllocIndexExpr element = getContiguousAllocIndexExprAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


