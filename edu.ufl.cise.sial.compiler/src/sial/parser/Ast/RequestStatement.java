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
 *<li>Rule 93:  Statement ::= request$ DataBlock
 *</b>
 */
public class RequestStatement extends ASTNode implements IStatement
{
    private DataBlock _DataBlock;

    public DataBlock getDataBlock() { return _DataBlock; }

    public RequestStatement(IToken leftIToken, IToken rightIToken,
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
        if (! (o instanceof RequestStatement)) return false;
        if (! super.equals(o)) return false;
        RequestStatement other = (RequestStatement) o;
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


