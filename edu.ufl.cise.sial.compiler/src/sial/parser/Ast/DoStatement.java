package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 67:  Statement ::= do$ Ident$StartIndex EOLs$ WhereClauseList StatementList enddo$ Ident$EndIndex
 *</b>
 */
public class DoStatement extends ASTNode implements IStatement
{
    private Ident _StartIndex;
    private WhereClauseList _WhereClauseList;
    private StatementList _StatementList;
    private Ident _EndIndex;

    public Ident getStartIndex() { return _StartIndex; }
    /**
     * The value returned by <b>getWhereClauseList</b> may be <b>null</b>
     */
    public WhereClauseList getWhereClauseList() { return _WhereClauseList; }
    public StatementList getStatementList() { return _StatementList; }
    public Ident getEndIndex() { return _EndIndex; }

    public DoStatement(IToken leftIToken, IToken rightIToken,
                       Ident _StartIndex,
                       WhereClauseList _WhereClauseList,
                       StatementList _StatementList,
                       Ident _EndIndex)
    {
        super(leftIToken, rightIToken);

        this._StartIndex = _StartIndex;
        ((ASTNode) _StartIndex).setParent(this);
        this._WhereClauseList = _WhereClauseList;
        if (_WhereClauseList != null) ((ASTNode) _WhereClauseList).setParent(this);
        this._StatementList = _StatementList;
        ((ASTNode) _StatementList).setParent(this);
        this._EndIndex = _EndIndex;
        ((ASTNode) _EndIndex).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_StartIndex);
        list.add(_WhereClauseList);
        list.add(_StatementList);
        list.add(_EndIndex);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DoStatement)) return false;
        if (! super.equals(o)) return false;
        DoStatement other = (DoStatement) o;
        if (! _StartIndex.equals(other._StartIndex)) return false;
        if (_WhereClauseList == null)
            if (other._WhereClauseList != null) return false;
            else; // continue
        else if (! _WhereClauseList.equals(other._WhereClauseList)) return false;
        if (! _StatementList.equals(other._StatementList)) return false;
        if (! _EndIndex.equals(other._EndIndex)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_StartIndex.hashCode());
        hash = hash * 31 + (_WhereClauseList == null ? 0 : _WhereClauseList.hashCode());
        hash = hash * 31 + (_StatementList.hashCode());
        hash = hash * 31 + (_EndIndex.hashCode());
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
            if (_WhereClauseList != null) _WhereClauseList.accept(v);
            _StatementList.accept(v);
            _EndIndex.accept(v);
        }
        v.endVisit(this);
    }
}


