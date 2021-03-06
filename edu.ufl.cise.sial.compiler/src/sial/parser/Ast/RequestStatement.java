package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 78:  Statement ::= request$ DataBlock Ident
 *</b>
 */
public class RequestStatement extends ASTNode implements IStatement
{
    private DataBlock _DataBlock;
    private Ident _Ident;

    public DataBlock getDataBlock() { return _DataBlock; }
    public Ident getIdent() { return _Ident; }

    public RequestStatement(IToken leftIToken, IToken rightIToken,
                            DataBlock _DataBlock,
                            Ident _Ident)
    {
        super(leftIToken, rightIToken);

        this._DataBlock = _DataBlock;
        ((ASTNode) _DataBlock).setParent(this);
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
        list.add(_DataBlock);
        list.add(_Ident);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof RequestStatement)) return false;
        if (! super.equals(o)) return false;
        RequestStatement other = (RequestStatement) o;
        if (! _DataBlock.equals(other._DataBlock)) return false;
        if (! _Ident.equals(other._Ident)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_DataBlock.hashCode());
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
            _DataBlock.accept(v);
            _Ident.accept(v);
        }
        v.endVisit(this);
    }
}


