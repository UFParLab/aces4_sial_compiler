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
 *<li>Rule 127:  IndexCastIdent ::= IndexCastopt Ident
 *</b>
 */
public class IndexCastIdent extends ASTNode implements IIndexCastIdent
{
    private IndexCastopt _IndexCastopt;
    private Ident _Ident;

    /**
     * The value returned by <b>getIndexCastopt</b> may be <b>null</b>
     */
    public IndexCastopt getIndexCastopt() { return _IndexCastopt; }
    public Ident getIdent() { return _Ident; }

    public IndexCastIdent(IToken leftIToken, IToken rightIToken,
                          IndexCastopt _IndexCastopt,
                          Ident _Ident)
    {
        super(leftIToken, rightIToken);

        this._IndexCastopt = _IndexCastopt;
        if (_IndexCastopt != null) ((ASTNode) _IndexCastopt).setParent(this);
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
        list.add(_IndexCastopt);
        list.add(_Ident);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IndexCastIdent)) return false;
        if (! super.equals(o)) return false;
        IndexCastIdent other = (IndexCastIdent) o;
        if (_IndexCastopt == null)
            if (other._IndexCastopt != null) return false;
            else; // continue
        else if (! _IndexCastopt.equals(other._IndexCastopt)) return false;
        if (! _Ident.equals(other._Ident)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_IndexCastopt == null ? 0 : _IndexCastopt.hashCode());
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
            if (_IndexCastopt != null) _IndexCastopt.accept(v);
            _Ident.accept(v);
        }
        v.endVisit(this);
    }
}


