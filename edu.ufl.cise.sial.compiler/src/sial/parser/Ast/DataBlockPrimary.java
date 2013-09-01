package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 130:  Primary ::= DataBlock
 *</b>
 */
public class DataBlockPrimary extends ASTNode implements IPrimary
{
    private DataBlock _DataBlock;

    public DataBlock getDataBlock() { return _DataBlock; }

    public DataBlockPrimary(IToken leftIToken, IToken rightIToken,
                            DataBlock _DataBlock)
    {
        super(leftIToken, rightIToken);

        this._DataBlock = _DataBlock;
        ((ASTNode) _DataBlock).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_DataBlock);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DataBlockPrimary)) return false;
        if (! super.equals(o)) return false;
        DataBlockPrimary other = (DataBlockPrimary) o;
        if (! _DataBlock.equals(other._DataBlock)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_DataBlock.hashCode());
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
            _DataBlock.accept(v);
        v.endVisit(this);
    }
}


