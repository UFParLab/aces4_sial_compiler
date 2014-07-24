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
 *<li>Rule 113:  Statement ::= restore_persistent$ Ident StringLiteral
 *</b>
 */
public class RestorePersistent extends ASTNode implements IStatement
{
    private Ident _Ident;
    private StringLiteral _StringLiteral;

    public Ident getIdent() { return _Ident; }
    public StringLiteral getStringLiteral() { return _StringLiteral; }

    public RestorePersistent(IToken leftIToken, IToken rightIToken,
                             Ident _Ident,
                             StringLiteral _StringLiteral)
    {
        super(leftIToken, rightIToken);

        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._StringLiteral = _StringLiteral;
        ((ASTNode) _StringLiteral).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        list.add(_StringLiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof RestorePersistent)) return false;
        if (! super.equals(o)) return false;
        RestorePersistent other = (RestorePersistent) o;
        if (! _Ident.equals(other._Ident)) return false;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_StringLiteral.hashCode());
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
            _StringLiteral.accept(v);
        }
        v.endVisit(this);
    }
}


