package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 124:  BinaryExpression ::= UnaryExpression$Expr1 BinOp UnaryExpression$Expr2
 *</b>
 */
public class BinaryExpression extends ASTNode implements IBinaryExpression
{
    private IUnaryExpression _Expr1;
    private IBinOp _BinOp;
    private IUnaryExpression _Expr2;

    public IUnaryExpression getExpr1() { return _Expr1; }
    public IBinOp getBinOp() { return _BinOp; }
    public IUnaryExpression getExpr2() { return _Expr2; }

    public BinaryExpression(IToken leftIToken, IToken rightIToken,
                            IUnaryExpression _Expr1,
                            IBinOp _BinOp,
                            IUnaryExpression _Expr2)
    {
        super(leftIToken, rightIToken);

        this._Expr1 = _Expr1;
        ((ASTNode) _Expr1).setParent(this);
        this._BinOp = _BinOp;
        ((ASTNode) _BinOp).setParent(this);
        this._Expr2 = _Expr2;
        ((ASTNode) _Expr2).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Expr1);
        list.add(_BinOp);
        list.add(_Expr2);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof BinaryExpression)) return false;
        if (! super.equals(o)) return false;
        BinaryExpression other = (BinaryExpression) o;
        if (! _Expr1.equals(other._Expr1)) return false;
        if (! _BinOp.equals(other._BinOp)) return false;
        if (! _Expr2.equals(other._Expr2)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Expr1.hashCode());
        hash = hash * 31 + (_BinOp.hashCode());
        hash = hash * 31 + (_Expr2.hashCode());
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
            _Expr1.accept(v);
            _BinOp.accept(v);
            _Expr2.accept(v);
        }
        v.endVisit(this);
    }
}


