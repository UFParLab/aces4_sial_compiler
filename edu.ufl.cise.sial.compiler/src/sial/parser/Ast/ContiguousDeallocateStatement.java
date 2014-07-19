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
 *<li>Rule 84:  Statement ::= deallocate contiguous$ Ident [$ ContiguousAllocIndexExprList ]$
 *</b>
 */
public class ContiguousDeallocateStatement extends ASTNode implements IStatement
{
    private ASTNodeToken _deallocate;
    private Ident _Ident;
    private ContiguousAllocIndexExprList _ContiguousAllocIndexExprList;

    public ASTNodeToken getdeallocate() { return _deallocate; }
    public Ident getIdent() { return _Ident; }
    public ContiguousAllocIndexExprList getContiguousAllocIndexExprList() { return _ContiguousAllocIndexExprList; }

    public ContiguousDeallocateStatement(IToken leftIToken, IToken rightIToken,
                                         ASTNodeToken _deallocate,
                                         Ident _Ident,
                                         ContiguousAllocIndexExprList _ContiguousAllocIndexExprList)
    {
        super(leftIToken, rightIToken);

        this._deallocate = _deallocate;
        ((ASTNode) _deallocate).setParent(this);
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._ContiguousAllocIndexExprList = _ContiguousAllocIndexExprList;
        ((ASTNode) _ContiguousAllocIndexExprList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_deallocate);
        list.add(_Ident);
        list.add(_ContiguousAllocIndexExprList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ContiguousDeallocateStatement)) return false;
        if (! super.equals(o)) return false;
        ContiguousDeallocateStatement other = (ContiguousDeallocateStatement) o;
        if (! _deallocate.equals(other._deallocate)) return false;
        if (! _Ident.equals(other._Ident)) return false;
        if (! _ContiguousAllocIndexExprList.equals(other._ContiguousAllocIndexExprList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_deallocate.hashCode());
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_ContiguousAllocIndexExprList.hashCode());
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
            _deallocate.accept(v);
            _Ident.accept(v);
            _ContiguousAllocIndexExprList.accept(v);
        }
        v.endVisit(this);
    }
}

