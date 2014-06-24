package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 96:  Statement ::= gpu_put$ Primary
 *</b>
 */
public class GpuPut extends ASTNode implements IStatement
{
    private IPrimary _Primary;

    public IPrimary getPrimary() { return _Primary; }

    public GpuPut(IToken leftIToken, IToken rightIToken,
                  IPrimary _Primary)
    {
        super(leftIToken, rightIToken);

        this._Primary = _Primary;
        ((ASTNode) _Primary).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Primary);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof GpuPut)) return false;
        if (! super.equals(o)) return false;
        GpuPut other = (GpuPut) o;
        if (! _Primary.equals(other._Primary)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Primary.hashCode());
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
            _Primary.accept(v);
        v.endVisit(this);
    }
}

