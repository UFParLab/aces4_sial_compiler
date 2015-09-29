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
 *<li>Rule 71:  PardoPragma ::= $Empty
 *<li>Rule 72:  PardoPragma ::= StringLiteral
 *</b>
 */
public class PardoPragma extends ASTNode implements IPardoPragma
{
    private StringLiteral _StringLiteral;

    /**
     * The value returned by <b>getStringLiteral</b> may be <b>null</b>
     */
    public StringLiteral getStringLiteral() { return _StringLiteral; }

    public PardoPragma(IToken leftIToken, IToken rightIToken,
                       StringLiteral _StringLiteral)
    {
        super(leftIToken, rightIToken);

        this._StringLiteral = _StringLiteral;
        if (_StringLiteral != null) ((ASTNode) _StringLiteral).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_StringLiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PardoPragma)) return false;
        if (! super.equals(o)) return false;
        PardoPragma other = (PardoPragma) o;
        if (_StringLiteral == null)
            if (other._StringLiteral != null) return false;
            else; // continue
        else if (! _StringLiteral.equals(other._StringLiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_StringLiteral == null ? 0 : _StringLiteral.hashCode());
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
            if (_StringLiteral != null) _StringLiteral.accept(v);
        v.endVisit(this);
    }
}


