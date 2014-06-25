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
 *<li>Rule 95:  Statement ::= request$ DataBlock IdentOpt
 *</b>
 */
public class RequestStatement extends ASTNode implements IStatement
{
    private DataBlock _DataBlock;
    private Ident _IdentOpt;

    public DataBlock getDataBlock() { return _DataBlock; }
    /**
     * The value returned by <b>getIdentOpt</b> may be <b>null</b>
     */
    public Ident getIdentOpt() { return _IdentOpt; }

    public RequestStatement(IToken leftIToken, IToken rightIToken,
                            DataBlock _DataBlock,
                            Ident _IdentOpt)
    {
        super(leftIToken, rightIToken);

        this._DataBlock = _DataBlock;
        ((ASTNode) _DataBlock).setParent(this);
        this._IdentOpt = _IdentOpt;
        if (_IdentOpt != null) ((ASTNode) _IdentOpt).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_DataBlock);
        list.add(_IdentOpt);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof RequestStatement)) return false;
        if (! super.equals(o)) return false;
        RequestStatement other = (RequestStatement) o;
        if (! _DataBlock.equals(other._DataBlock)) return false;
        if (_IdentOpt == null)
            if (other._IdentOpt != null) return false;
            else; // continue
        else if (! _IdentOpt.equals(other._IdentOpt)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_DataBlock.hashCode());
        hash = hash * 31 + (_IdentOpt == null ? 0 : _IdentOpt.hashCode());
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
            if (_IdentOpt != null) _IdentOpt.accept(v);
        }
        v.endVisit(this);
    }
}


