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
 *<li>Rule 116:  Statement ::= broadcast_from$ Primary Ident
 *</b>
 */
public class BroadcastStatic extends ASTNode implements IStatement
{
    private IPrimary _Primary;
    private Ident _Ident;

    public IPrimary getPrimary() { return _Primary; }
    public Ident getIdent() { return _Ident; }

    public BroadcastStatic(IToken leftIToken, IToken rightIToken,
                           IPrimary _Primary,
                           Ident _Ident)
    {
        super(leftIToken, rightIToken);

        this._Primary = _Primary;
        ((ASTNode) _Primary).setParent(this);
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Primary);
        list.add(_Ident);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof BroadcastStatic)) return false;
        if (! super.equals(o)) return false;
        BroadcastStatic other = (BroadcastStatic) o;
        if (! _Primary.equals(other._Primary)) return false;
        if (! _Ident.equals(other._Ident)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Primary.hashCode());
        hash = hash * 31 + (_Ident.hashCode());
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
            _Primary.accept(v);
            _Ident.accept(v);
        }
        v.endVisit(this);
    }
}


