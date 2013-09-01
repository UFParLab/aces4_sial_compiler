package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 94:  Statement ::= ScalarOrBlockVar AssignOp Expression
 *</b>
 */
public class AssignStatement extends ASTNode implements IStatement
{
    private IScalarOrBlockVar _ScalarOrBlockVar;
    private IAssignOp _AssignOp;
    private IExpression _Expression;

    public IScalarOrBlockVar getScalarOrBlockVar() { return _ScalarOrBlockVar; }
    public IAssignOp getAssignOp() { return _AssignOp; }
    public IExpression getExpression() { return _Expression; }

    public AssignStatement(IToken leftIToken, IToken rightIToken,
                           IScalarOrBlockVar _ScalarOrBlockVar,
                           IAssignOp _AssignOp,
                           IExpression _Expression)
    {
        super(leftIToken, rightIToken);

        this._ScalarOrBlockVar = _ScalarOrBlockVar;
        ((ASTNode) _ScalarOrBlockVar).setParent(this);
        this._AssignOp = _AssignOp;
        ((ASTNode) _AssignOp).setParent(this);
        this._Expression = _Expression;
        ((ASTNode) _Expression).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ScalarOrBlockVar);
        list.add(_AssignOp);
        list.add(_Expression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AssignStatement)) return false;
        if (! super.equals(o)) return false;
        AssignStatement other = (AssignStatement) o;
        if (! _ScalarOrBlockVar.equals(other._ScalarOrBlockVar)) return false;
        if (! _AssignOp.equals(other._AssignOp)) return false;
        if (! _Expression.equals(other._Expression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ScalarOrBlockVar.hashCode());
        hash = hash * 31 + (_AssignOp.hashCode());
        hash = hash * 31 + (_Expression.hashCode());
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
            _ScalarOrBlockVar.accept(v);
            _AssignOp.accept(v);
            _Expression.accept(v);
        }
        v.endVisit(this);
    }
}


