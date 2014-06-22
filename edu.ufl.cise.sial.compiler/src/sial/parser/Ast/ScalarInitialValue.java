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
 *<li>Rule 28:  ScalarInitializationOpt ::= $Empty
 *<li>Rule 29:  ScalarInitializationOpt ::= = DOUBLELIT
 *</b>
 */
public class ScalarInitialValue extends ASTNode implements IScalarInitializationOpt
{
    private ASTNodeToken _ASSIGN;
    private ASTNodeToken _DOUBLELIT;

    /**
     * The value returned by <b>getASSIGN</b> may be <b>null</b>
     */
    public ASTNodeToken getASSIGN() { return _ASSIGN; }
    /**
     * The value returned by <b>getDOUBLELIT</b> may be <b>null</b>
     */
    public ASTNodeToken getDOUBLELIT() { return _DOUBLELIT; }

    public ScalarInitialValue(IToken leftIToken, IToken rightIToken,
                              ASTNodeToken _ASSIGN,
                              ASTNodeToken _DOUBLELIT)
    {
        super(leftIToken, rightIToken);

        this._ASSIGN = _ASSIGN;
        if (_ASSIGN != null) ((ASTNode) _ASSIGN).setParent(this);
        this._DOUBLELIT = _DOUBLELIT;
        if (_DOUBLELIT != null) ((ASTNode) _DOUBLELIT).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ASSIGN);
        list.add(_DOUBLELIT);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ScalarInitialValue)) return false;
        if (! super.equals(o)) return false;
        ScalarInitialValue other = (ScalarInitialValue) o;
        if (_ASSIGN == null)
            if (other._ASSIGN != null) return false;
            else; // continue
        else if (! _ASSIGN.equals(other._ASSIGN)) return false;
        if (_DOUBLELIT == null)
            if (other._DOUBLELIT != null) return false;
            else; // continue
        else if (! _DOUBLELIT.equals(other._DOUBLELIT)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ASSIGN == null ? 0 : _ASSIGN.hashCode());
        hash = hash * 31 + (_DOUBLELIT == null ? 0 : _DOUBLELIT.hashCode());
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
            if (_DOUBLELIT != null) _DOUBLELIT.accept(v);
        }
        v.endVisit(this);
    }
}


