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
 *<li>Rule 52:  RangeVal ::= -$ INTLIT
 *</b>
 */
public class NegRangeVal extends ASTNode implements IRangeVal
{
    private ASTNodeToken _INTLIT;

    public ASTNodeToken getINTLIT() { return _INTLIT; }

    public NegRangeVal(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _INTLIT)
    {
        super(leftIToken, rightIToken);

        this._INTLIT = _INTLIT;
        ((ASTNode) _INTLIT).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_INTLIT);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof NegRangeVal)) return false;
        if (! super.equals(o)) return false;
        NegRangeVal other = (NegRangeVal) o;
        if (! _INTLIT.equals(other._INTLIT)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_INTLIT.hashCode());
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
            _INTLIT.accept(v);
        v.endVisit(this);
    }
}


