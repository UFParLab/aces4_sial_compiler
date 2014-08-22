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
 *<li>Rule 103:  ArgList ::= $Empty
 *<li>Rule 104:  ArgList ::= ArgList Arg
 *</b>
 */
public class ArgList extends AbstractASTNodeList implements IArgList
{
    public IArg getArgAt(int i) { return (IArg) getElementAt(i); }

    public ArgList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ArgList(IArg _Arg, boolean leftRecursive)
    {
        super((ASTNode) _Arg, leftRecursive);
        ((ASTNode) _Arg).setParent(this);
    }

    public void add(IArg _Arg)
    {
        super.add((ASTNode) _Arg);
        ((ASTNode) _Arg).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ArgList)) return false;
        if (! super.equals(o)) return false;
        ArgList other = (ArgList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IArg element = getArgAt(i);
            if (! element.equals(other.getArgAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getArgAt(i).hashCode());
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
                IArg element = getArgAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


