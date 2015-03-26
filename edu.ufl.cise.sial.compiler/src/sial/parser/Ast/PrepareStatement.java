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
 *<li>Rule 92:  Statement ::= prepare$ DataBlock$LHSDataBlock AssignOp Expression$Expression
 *</b>
 */
public class PrepareStatement extends ASTNode implements IStatement
{
    private DataBlock _LHSDataBlock;
    private AssignOp _AssignOp;
    private IExpression _Expression;

    public DataBlock getLHSDataBlock() { return _LHSDataBlock; }
    public AssignOp getAssignOp() { return _AssignOp; }
    public IExpression getExpression() { return _Expression; }

    public PrepareStatement(IToken leftIToken, IToken rightIToken,
                            DataBlock _LHSDataBlock,
                            AssignOp _AssignOp,
                            IExpression _Expression)
    {
        super(leftIToken, rightIToken);

        this._LHSDataBlock = _LHSDataBlock;
        ((ASTNode) _LHSDataBlock).setParent(this);
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
        list.add(_LHSDataBlock);
        list.add(_AssignOp);
        list.add(_Expression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PrepareStatement)) return false;
        if (! super.equals(o)) return false;
        PrepareStatement other = (PrepareStatement) o;
        if (! _LHSDataBlock.equals(other._LHSDataBlock)) return false;
        if (! _AssignOp.equals(other._AssignOp)) return false;
        if (! _Expression.equals(other._Expression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_LHSDataBlock.hashCode());
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
            _LHSDataBlock.accept(v);
            _AssignOp.accept(v);
            _Expression.accept(v);
        }
        v.endVisit(this);
    }
}


