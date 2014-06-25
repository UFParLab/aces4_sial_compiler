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
 *<li>Rule 50:  Range ::= RangeVal$RangeValStart :$ RangeVal$RangeValEnd
 *</b>
 */
public class Range extends ASTNode implements IRange
{
    private IRangeVal _RangeValStart;
    private IRangeVal _RangeValEnd;

    public IRangeVal getRangeValStart() { return _RangeValStart; }
    public IRangeVal getRangeValEnd() { return _RangeValEnd; }

    public Range(IToken leftIToken, IToken rightIToken,
                 IRangeVal _RangeValStart,
                 IRangeVal _RangeValEnd)
    {
        super(leftIToken, rightIToken);

        this._RangeValStart = _RangeValStart;
        ((ASTNode) _RangeValStart).setParent(this);
        this._RangeValEnd = _RangeValEnd;
        ((ASTNode) _RangeValEnd).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_RangeValStart);
        list.add(_RangeValEnd);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Range)) return false;
        if (! super.equals(o)) return false;
        Range other = (Range) o;
        if (! _RangeValStart.equals(other._RangeValStart)) return false;
        if (! _RangeValEnd.equals(other._RangeValEnd)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_RangeValStart.hashCode());
        hash = hash * 31 + (_RangeValEnd.hashCode());
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
            _RangeValStart.accept(v);
            _RangeValEnd.accept(v);
        }
        v.endVisit(this);
    }
}


