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
 *<li>Rule 87:  ContiguousIndexRangeExpr ::= Expression$StartExpr : Expression$EndExpr
 *</b>
 */
public class ContiguousIndexRangeExpr extends ASTNode implements IContiguousIndexRangeExpr
{
    private IExpression _StartExpr;
    private ASTNodeToken _COLON;
    private IExpression _EndExpr;

    public IExpression getStartExpr() { return _StartExpr; }
    public ASTNodeToken getCOLON() { return _COLON; }
    public IExpression getEndExpr() { return _EndExpr; }

    public ContiguousIndexRangeExpr(IToken leftIToken, IToken rightIToken,
                                    IExpression _StartExpr,
                                    ASTNodeToken _COLON,
                                    IExpression _EndExpr)
    {
        super(leftIToken, rightIToken);

        this._StartExpr = _StartExpr;
        ((ASTNode) _StartExpr).setParent(this);
        this._COLON = _COLON;
        ((ASTNode) _COLON).setParent(this);
        this._EndExpr = _EndExpr;
        ((ASTNode) _EndExpr).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_StartExpr);
        list.add(_COLON);
        list.add(_EndExpr);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ContiguousIndexRangeExpr)) return false;
        if (! super.equals(o)) return false;
        ContiguousIndexRangeExpr other = (ContiguousIndexRangeExpr) o;
        if (! _StartExpr.equals(other._StartExpr)) return false;
        if (! _COLON.equals(other._COLON)) return false;
        if (! _EndExpr.equals(other._EndExpr)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_StartExpr.hashCode());
        hash = hash * 31 + (_COLON.hashCode());
        hash = hash * 31 + (_EndExpr.hashCode());
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
            _StartExpr.accept(v);
            _COLON.accept(v);
            _EndExpr.accept(v);
        }
        v.endVisit(this);
    }
}


