package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 59:  Statement ::= call$ Ident
 *</b>
 */
public class CallStatement extends ASTNode implements IStatement
{
    private Ident _Ident;

    public Ident getIdent() { return _Ident; }

    public CallStatement(IToken leftIToken, IToken rightIToken,
                         Ident _Ident)
    {
        super(leftIToken, rightIToken);

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
        list.add(_Ident);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof CallStatement)) return false;
        if (! super.equals(o)) return false;
        CallStatement other = (CallStatement) o;
        if (! _Ident.equals(other._Ident)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
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
            _Ident.accept(v);
        v.endVisit(this);
    }
}


