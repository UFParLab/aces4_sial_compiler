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
 *<li>Rule 92:  Statement ::= put$ DataBlock$LHSDataBlock AssignOp DataBlock$RHSDataBlock
 *</b>
 */
public class PutStatement extends ASTNode implements IStatement
{
    private DataBlock _LHSDataBlock;
    private AssignOp _AssignOp;
    private DataBlock _RHSDataBlock;

    public DataBlock getLHSDataBlock() { return _LHSDataBlock; }
    public AssignOp getAssignOp() { return _AssignOp; }
    public DataBlock getRHSDataBlock() { return _RHSDataBlock; }

    public PutStatement(IToken leftIToken, IToken rightIToken,
                        DataBlock _LHSDataBlock,
                        AssignOp _AssignOp,
                        DataBlock _RHSDataBlock)
    {
        super(leftIToken, rightIToken);

        this._LHSDataBlock = _LHSDataBlock;
        ((ASTNode) _LHSDataBlock).setParent(this);
        this._AssignOp = _AssignOp;
        ((ASTNode) _AssignOp).setParent(this);
        this._RHSDataBlock = _RHSDataBlock;
        ((ASTNode) _RHSDataBlock).setParent(this);
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
        list.add(_RHSDataBlock);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PutStatement)) return false;
        if (! super.equals(o)) return false;
        PutStatement other = (PutStatement) o;
        if (! _LHSDataBlock.equals(other._LHSDataBlock)) return false;
        if (! _AssignOp.equals(other._AssignOp)) return false;
        if (! _RHSDataBlock.equals(other._RHSDataBlock)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_LHSDataBlock.hashCode());
        hash = hash * 31 + (_AssignOp.hashCode());
        hash = hash * 31 + (_RHSDataBlock.hashCode());
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
            _RHSDataBlock.accept(v);
        }
        v.endVisit(this);
    }
}


