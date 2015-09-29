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
 *<li>Rule 88:  ContiguousIndexRangeExprList ::= ContiguousIndexRangeExpr
 *<li>Rule 89:  ContiguousIndexRangeExprList ::= ContiguousIndexRangeExprList ,$ ContiguousIndexRangeExpr
 *</b>
 */
public class ContiguousIndexRangeExprList extends AbstractASTNodeList implements IContiguousIndexRangeExprList
{
    public ContiguousIndexRangeExpr getContiguousIndexRangeExprAt(int i) { return (ContiguousIndexRangeExpr) getElementAt(i); }

    public ContiguousIndexRangeExprList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ContiguousIndexRangeExprList(ContiguousIndexRangeExpr _ContiguousIndexRangeExpr, boolean leftRecursive)
    {
        super((ASTNode) _ContiguousIndexRangeExpr, leftRecursive);
        ((ASTNode) _ContiguousIndexRangeExpr).setParent(this);
    }

    public void add(ContiguousIndexRangeExpr _ContiguousIndexRangeExpr)
    {
        super.add((ASTNode) _ContiguousIndexRangeExpr);
        ((ASTNode) _ContiguousIndexRangeExpr).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ContiguousIndexRangeExprList)) return false;
        if (! super.equals(o)) return false;
        ContiguousIndexRangeExprList other = (ContiguousIndexRangeExprList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            ContiguousIndexRangeExpr element = getContiguousIndexRangeExprAt(i);
            if (! element.equals(other.getContiguousIndexRangeExprAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getContiguousIndexRangeExprAt(i).hashCode());
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
                ContiguousIndexRangeExpr element = getContiguousIndexRangeExprAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


