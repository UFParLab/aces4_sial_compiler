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
 *<li>Rule 125:  IndexExpressionList ::= IndexExpression
 *<li>Rule 126:  IndexExpressionList ::= IndexExpressionList ,$ IndexExpression
 *</b>
 */
public class IndexExpressionList extends AbstractASTNodeList implements IIndexExpressionList
{
    public IIndexExpression getIndexExpressionAt(int i) { return (IIndexExpression) getElementAt(i); }

    public IndexExpressionList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public IndexExpressionList(IIndexExpression _IndexExpression, boolean leftRecursive)
    {
        super((ASTNode) _IndexExpression, leftRecursive);
        ((ASTNode) _IndexExpression).setParent(this);
    }

    public void add(IIndexExpression _IndexExpression)
    {
        super.add((ASTNode) _IndexExpression);
        ((ASTNode) _IndexExpression).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IndexExpressionList)) return false;
        if (! super.equals(o)) return false;
        IndexExpressionList other = (IndexExpressionList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IIndexExpression element = getIndexExpressionAt(i);
            if (! element.equals(other.getIndexExpressionAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getIndexExpressionAt(i).hashCode());
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
                IIndexExpression element = getIndexExpressionAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


