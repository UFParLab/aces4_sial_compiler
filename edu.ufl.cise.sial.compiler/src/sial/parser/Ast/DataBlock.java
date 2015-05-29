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
 *<li>Rule 122:  DataBlock ::= Ident [$ IndexCastIndices ]$
 *</b>
 */
public class DataBlock extends ASTNode implements IDataBlock
{
    private Ident _Ident;
    private IndexCastIdentList _IndexCastIndices;

    public Ident getIdent() { return _Ident; }
    public IndexCastIdentList getIndexCastIndices() { return _IndexCastIndices; }

    public DataBlock(IToken leftIToken, IToken rightIToken,
                     Ident _Ident,
                     IndexCastIdentList _IndexCastIndices)
    {
        super(leftIToken, rightIToken);

        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._IndexCastIndices = _IndexCastIndices;
        ((ASTNode) _IndexCastIndices).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        list.add(_IndexCastIndices);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DataBlock)) return false;
        if (! super.equals(o)) return false;
        DataBlock other = (DataBlock) o;
        if (! _Ident.equals(other._Ident)) return false;
        if (! _IndexCastIndices.equals(other._IndexCastIndices)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_IndexCastIndices.hashCode());
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
            _IndexCastIndices.accept(v);
        }
        v.endVisit(this);
    }
}


