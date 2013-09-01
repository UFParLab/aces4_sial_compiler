package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 84:  Statement ::= collective$ Ident$LHSIdent AssignOp Ident$RHSIdent
 *</b>
 */
public class CollectiveStatement extends ASTNode implements IStatement
{
    private Ident _LHSIdent;
    private IAssignOp _AssignOp;
    private Ident _RHSIdent;

    public Ident getLHSIdent() { return _LHSIdent; }
    public IAssignOp getAssignOp() { return _AssignOp; }
    public Ident getRHSIdent() { return _RHSIdent; }

    public CollectiveStatement(IToken leftIToken, IToken rightIToken,
                               Ident _LHSIdent,
                               IAssignOp _AssignOp,
                               Ident _RHSIdent)
    {
        super(leftIToken, rightIToken);

        this._LHSIdent = _LHSIdent;
        ((ASTNode) _LHSIdent).setParent(this);
        this._AssignOp = _AssignOp;
        ((ASTNode) _AssignOp).setParent(this);
        this._RHSIdent = _RHSIdent;
        ((ASTNode) _RHSIdent).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_LHSIdent);
        list.add(_AssignOp);
        list.add(_RHSIdent);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof CollectiveStatement)) return false;
        if (! super.equals(o)) return false;
        CollectiveStatement other = (CollectiveStatement) o;
        if (! _LHSIdent.equals(other._LHSIdent)) return false;
        if (! _AssignOp.equals(other._AssignOp)) return false;
        if (! _RHSIdent.equals(other._RHSIdent)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_LHSIdent.hashCode());
        hash = hash * 31 + (_AssignOp.hashCode());
        hash = hash * 31 + (_RHSIdent.hashCode());
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
            _LHSIdent.accept(v);
            _AssignOp.accept(v);
            _RHSIdent.accept(v);
        }
        v.endVisit(this);
    }
}


