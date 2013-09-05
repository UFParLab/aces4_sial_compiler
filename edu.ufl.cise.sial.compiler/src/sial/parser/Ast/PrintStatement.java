package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 85:  Statement ::= print$ STRINGLIT
 *</b>
 */
public class PrintStatement extends ASTNode implements IStatement
{
    private ASTNodeToken _STRINGLIT;

    public ASTNodeToken getSTRINGLIT() { return _STRINGLIT; }

    public PrintStatement(IToken leftIToken, IToken rightIToken,
                          ASTNodeToken _STRINGLIT)
    {
        super(leftIToken, rightIToken);

        this._STRINGLIT = _STRINGLIT;
        ((ASTNode) _STRINGLIT).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_STRINGLIT);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PrintStatement)) return false;
        if (! super.equals(o)) return false;
        PrintStatement other = (PrintStatement) o;
        if (! _STRINGLIT.equals(other._STRINGLIT)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_STRINGLIT.hashCode());
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
            _STRINGLIT.accept(v);
        v.endVisit(this);
    }
}


