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
 *<li>Rule 110:  Statement ::= ContiguousDataBlock AssignOp Expression
 *</b>
 */
public class AssignToContigousDataBlock extends ASTNode implements IStatement
{
    private ContiguousDataBlock _ContiguousDataBlock;
    private AssignOp _AssignOp;
    private IExpression _Expression;

    public ContiguousDataBlock getContiguousDataBlock() { return _ContiguousDataBlock; }
    public AssignOp getAssignOp() { return _AssignOp; }
    public IExpression getExpression() { return _Expression; }

    public AssignToContigousDataBlock(IToken leftIToken, IToken rightIToken,
                                      ContiguousDataBlock _ContiguousDataBlock,
                                      AssignOp _AssignOp,
                                      IExpression _Expression)
    {
        super(leftIToken, rightIToken);

        this._ContiguousDataBlock = _ContiguousDataBlock;
        ((ASTNode) _ContiguousDataBlock).setParent(this);
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
        list.add(_ContiguousDataBlock);
        list.add(_AssignOp);
        list.add(_Expression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AssignToContigousDataBlock)) return false;
        if (! super.equals(o)) return false;
        AssignToContigousDataBlock other = (AssignToContigousDataBlock) o;
        if (! _ContiguousDataBlock.equals(other._ContiguousDataBlock)) return false;
        if (! _AssignOp.equals(other._AssignOp)) return false;
        if (! _Expression.equals(other._Expression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ContiguousDataBlock.hashCode());
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
            _ContiguousDataBlock.accept(v);
            _AssignOp.accept(v);
            _Expression.accept(v);
        }
        v.endVisit(this);
    }
}


