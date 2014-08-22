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
 *<li>Rule 69:  Statement ::= do$ Ident$StartIndex in$ Ident$StartParentIndex EOLs$ WhereClauseList StatementList enddo$ Ident$EndIndex in$ Ident$EndParentIndex
 *</b>
 */
public class DoStatementSubIndex extends ASTNode implements IStatement
{
    private Ident _StartIndex;
    private Ident _StartParentIndex;
    private WhereClauseList _WhereClauseList;
    private StatementList _StatementList;
    private Ident _EndIndex;
    private Ident _EndParentIndex;

    public Ident getStartIndex() { return _StartIndex; }
    public Ident getStartParentIndex() { return _StartParentIndex; }
    /**
     * The value returned by <b>getWhereClauseList</b> may be <b>null</b>
     */
    public WhereClauseList getWhereClauseList() { return _WhereClauseList; }
    public StatementList getStatementList() { return _StatementList; }
    public Ident getEndIndex() { return _EndIndex; }
    public Ident getEndParentIndex() { return _EndParentIndex; }

    public DoStatementSubIndex(IToken leftIToken, IToken rightIToken,
                               Ident _StartIndex,
                               Ident _StartParentIndex,
                               WhereClauseList _WhereClauseList,
                               StatementList _StatementList,
                               Ident _EndIndex,
                               Ident _EndParentIndex)
    {
        super(leftIToken, rightIToken);

        this._StartIndex = _StartIndex;
        ((ASTNode) _StartIndex).setParent(this);
        this._StartParentIndex = _StartParentIndex;
        ((ASTNode) _StartParentIndex).setParent(this);
        this._WhereClauseList = _WhereClauseList;
        if (_WhereClauseList != null) ((ASTNode) _WhereClauseList).setParent(this);
        this._StatementList = _StatementList;
        ((ASTNode) _StatementList).setParent(this);
        this._EndIndex = _EndIndex;
        ((ASTNode) _EndIndex).setParent(this);
        this._EndParentIndex = _EndParentIndex;
        ((ASTNode) _EndParentIndex).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_StartIndex);
        list.add(_StartParentIndex);
        list.add(_WhereClauseList);
        list.add(_StatementList);
        list.add(_EndIndex);
        list.add(_EndParentIndex);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DoStatementSubIndex)) return false;
        if (! super.equals(o)) return false;
        DoStatementSubIndex other = (DoStatementSubIndex) o;
        if (! _StartIndex.equals(other._StartIndex)) return false;
        if (! _StartParentIndex.equals(other._StartParentIndex)) return false;
        if (_WhereClauseList == null)
            if (other._WhereClauseList != null) return false;
            else; // continue
        else if (! _WhereClauseList.equals(other._WhereClauseList)) return false;
        if (! _StatementList.equals(other._StatementList)) return false;
        if (! _EndIndex.equals(other._EndIndex)) return false;
        if (! _EndParentIndex.equals(other._EndParentIndex)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_StartIndex.hashCode());
        hash = hash * 31 + (_StartParentIndex.hashCode());
        hash = hash * 31 + (_WhereClauseList == null ? 0 : _WhereClauseList.hashCode());
        hash = hash * 31 + (_StatementList.hashCode());
        hash = hash * 31 + (_EndIndex.hashCode());
        hash = hash * 31 + (_EndParentIndex.hashCode());
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
            _StartIndex.accept(v);
            _StartParentIndex.accept(v);
            if (_WhereClauseList != null) _WhereClauseList.accept(v);
            _StatementList.accept(v);
            _EndIndex.accept(v);
            _EndParentIndex.accept(v);
        }
        v.endVisit(this);
    }
}


