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
 *<li>Rule 115:  Statement ::= gpu_put$ Arg
 *</b>
 */
public class GPUPutBlock extends ASTNode implements IStatement
{
    private IArg _Arg;

    public IArg getArg() { return _Arg; }

    public GPUPutBlock(IToken leftIToken, IToken rightIToken,
                       IArg _Arg)
    {
        super(leftIToken, rightIToken);

        this._Arg = _Arg;
        ((ASTNode) _Arg).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Arg);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof GPUPutBlock)) return false;
        if (! super.equals(o)) return false;
        GPUPutBlock other = (GPUPutBlock) o;
        if (! _Arg.equals(other._Arg)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Arg.hashCode());
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
            _Arg.accept(v);
        v.endVisit(this);
    }
}


