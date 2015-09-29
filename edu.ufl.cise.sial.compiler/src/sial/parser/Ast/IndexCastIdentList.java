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
 *<li>Rule 127:  IndexCastIndices ::= IndexCastIdent
 *<li>Rule 128:  IndexCastIndices ::= IndexCastIndices ,$ IndexCastIdent
 *</b>
 */
public class IndexCastIdentList extends AbstractASTNodeList implements IIndexCastIndices
{
    public IndexCastIdent getIndexCastIdentAt(int i) { return (IndexCastIdent) getElementAt(i); }

    public IndexCastIdentList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public IndexCastIdentList(IndexCastIdent _IndexCastIdent, boolean leftRecursive)
    {
        super((ASTNode) _IndexCastIdent, leftRecursive);
        ((ASTNode) _IndexCastIdent).setParent(this);
    }

    public void add(IndexCastIdent _IndexCastIdent)
    {
        super.add((ASTNode) _IndexCastIdent);
        ((ASTNode) _IndexCastIdent).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IndexCastIdentList)) return false;
        if (! super.equals(o)) return false;
        IndexCastIdentList other = (IndexCastIdentList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IndexCastIdent element = getIndexCastIdentAt(i);
            if (! element.equals(other.getIndexCastIdentAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getIndexCastIdentAt(i).hashCode());
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
                IndexCastIdent element = getIndexCastIdentAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


