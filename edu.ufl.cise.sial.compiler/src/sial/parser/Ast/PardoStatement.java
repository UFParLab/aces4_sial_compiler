package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 64:  Statement ::= pardo$ Indices$StartIndices EOLs$ WhereClauseList StatementList endpardo$ Indices$EndIndices
 *</b>
 */
public class PardoStatement extends ASTNode implements IStatement
{
    private IdentList _StartIndices;
    private WhereClauseList _WhereClauseList;
    private StatementList _StatementList;
    private IdentList _EndIndices;

    public IdentList getStartIndices() { return _StartIndices; }
    /**
     * The value returned by <b>getWhereClauseList</b> may be <b>null</b>
     */
    public WhereClauseList getWhereClauseList() { return _WhereClauseList; }
    public StatementList getStatementList() { return _StatementList; }
    public IdentList getEndIndices() { return _EndIndices; }

    public PardoStatement(IToken leftIToken, IToken rightIToken,
                          IdentList _StartIndices,
                          WhereClauseList _WhereClauseList,
                          StatementList _StatementList,
                          IdentList _EndIndices)
    {
        super(leftIToken, rightIToken);

        this._StartIndices = _StartIndices;
        ((ASTNode) _StartIndices).setParent(this);
        this._WhereClauseList = _WhereClauseList;
        if (_WhereClauseList != null) ((ASTNode) _WhereClauseList).setParent(this);
        this._StatementList = _StatementList;
        ((ASTNode) _StatementList).setParent(this);
        this._EndIndices = _EndIndices;
        ((ASTNode) _EndIndices).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_StartIndices);
        list.add(_WhereClauseList);
        list.add(_StatementList);
        list.add(_EndIndices);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PardoStatement)) return false;
        if (! super.equals(o)) return false;
        PardoStatement other = (PardoStatement) o;
        if (! _StartIndices.equals(other._StartIndices)) return false;
        if (_WhereClauseList == null)
            if (other._WhereClauseList != null) return false;
            else; // continue
        else if (! _WhereClauseList.equals(other._WhereClauseList)) return false;
        if (! _StatementList.equals(other._StatementList)) return false;
        if (! _EndIndices.equals(other._EndIndices)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_StartIndices.hashCode());
        hash = hash * 31 + (_WhereClauseList == null ? 0 : _WhereClauseList.hashCode());
        hash = hash * 31 + (_StatementList.hashCode());
        hash = hash * 31 + (_EndIndices.hashCode());
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
            _StartIndices.accept(v);
            if (_WhereClauseList != null) _WhereClauseList.accept(v);
            _StatementList.accept(v);
            _EndIndices.accept(v);
        }
        v.endVisit(this);
    }
}


