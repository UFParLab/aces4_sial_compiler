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
 *<li>Rule 31:  IntInitializationOpt ::= $Empty
 *<li>Rule 32:  IntInitializationOpt ::= = INTLIT
 *</b>
 */
public class IntInitialValue extends ASTNode implements IIntInitializationOpt
{
    private ASTNodeToken _ASSIGN;
    private ASTNodeToken _INTLIT;

    /**
     * The value returned by <b>getASSIGN</b> may be <b>null</b>
     */
    public ASTNodeToken getASSIGN() { return _ASSIGN; }
    /**
     * The value returned by <b>getINTLIT</b> may be <b>null</b>
     */
    public ASTNodeToken getINTLIT() { return _INTLIT; }

    public IntInitialValue(IToken leftIToken, IToken rightIToken,
                           ASTNodeToken _ASSIGN,
                           ASTNodeToken _INTLIT)
    {
        super(leftIToken, rightIToken);

        this._ASSIGN = _ASSIGN;
        if (_ASSIGN != null) ((ASTNode) _ASSIGN).setParent(this);
        this._INTLIT = _INTLIT;
        if (_INTLIT != null) ((ASTNode) _INTLIT).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ASSIGN);
        list.add(_INTLIT);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IntInitialValue)) return false;
        if (! super.equals(o)) return false;
        IntInitialValue other = (IntInitialValue) o;
        if (_ASSIGN == null)
            if (other._ASSIGN != null) return false;
            else; // continue
        else if (! _ASSIGN.equals(other._ASSIGN)) return false;
        if (_INTLIT == null)
            if (other._INTLIT != null) return false;
            else; // continue
        else if (! _INTLIT.equals(other._INTLIT)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ASSIGN == null ? 0 : _ASSIGN.hashCode());
        hash = hash * 31 + (_INTLIT == null ? 0 : _INTLIT.hashCode());
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
            if (_ASSIGN != null) _ASSIGN.accept(v);
            if (_INTLIT != null) _INTLIT.accept(v);
        }
        v.endVisit(this);
    }
}


