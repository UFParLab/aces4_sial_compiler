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
 *<li>Rule 77:  Statement ::= create$ Ident AllocIndexListopt
 *</b>
 */
public class CreateStatement extends ASTNode implements IStatement
{
    private Ident _Ident;
    private AllocIndexListopt _AllocIndexListopt;

    public Ident getIdent() { return _Ident; }
    /**
     * The value returned by <b>getAllocIndexListopt</b> may be <b>null</b>
     */
    public AllocIndexListopt getAllocIndexListopt() { return _AllocIndexListopt; }

    public CreateStatement(IToken leftIToken, IToken rightIToken,
                           Ident _Ident,
                           AllocIndexListopt _AllocIndexListopt)
    {
        super(leftIToken, rightIToken);

        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._AllocIndexListopt = _AllocIndexListopt;
        if (_AllocIndexListopt != null) ((ASTNode) _AllocIndexListopt).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        list.add(_AllocIndexListopt);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof CreateStatement)) return false;
        if (! super.equals(o)) return false;
        CreateStatement other = (CreateStatement) o;
        if (! _Ident.equals(other._Ident)) return false;
        if (_AllocIndexListopt == null)
            if (other._AllocIndexListopt != null) return false;
            else; // continue
        else if (! _AllocIndexListopt.equals(other._AllocIndexListopt)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_AllocIndexListopt == null ? 0 : _AllocIndexListopt.hashCode());
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
            _Ident.accept(v);
            if (_AllocIndexListopt != null) _AllocIndexListopt.accept(v);
        }
        v.endVisit(this);
    }
}


