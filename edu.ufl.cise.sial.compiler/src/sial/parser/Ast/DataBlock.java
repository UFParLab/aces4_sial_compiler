package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 99:  DataBlock ::= Ident ($ Indices )$
 *</b>
 */
public class DataBlock extends ASTNode implements IDataBlock
{
    private Ident _Ident;
    private IdentList _Indices;

    public Ident getIdent() { return _Ident; }
    public IdentList getIndices() { return _Indices; }

    public DataBlock(IToken leftIToken, IToken rightIToken,
                     Ident _Ident,
                     IdentList _Indices)
    {
        super(leftIToken, rightIToken);

        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._Indices = _Indices;
        ((ASTNode) _Indices).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        list.add(_Indices);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DataBlock)) return false;
        if (! super.equals(o)) return false;
        DataBlock other = (DataBlock) o;
        if (! _Ident.equals(other._Ident)) return false;
        if (! _Indices.equals(other._Indices)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_Indices.hashCode());
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
            _Indices.accept(v);
        }
        v.endVisit(this);
    }
}


