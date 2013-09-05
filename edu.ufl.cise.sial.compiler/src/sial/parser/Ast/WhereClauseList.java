package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<em>
 *<li>Rule 59:  WhereClauseList ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 60:  WhereClauseList ::= WhereClauseList WhereClause EOLs$
 *</b>
 */
public class WhereClauseList extends ASTNode implements IWhereClauseList
{
    private WhereClauseList _WhereClauseList;
    private WhereClause _WhereClause;

    /**
     * The value returned by <b>getWhereClauseList</b> may be <b>null</b>
     */
    public WhereClauseList getWhereClauseList() { return _WhereClauseList; }
    public WhereClause getWhereClause() { return _WhereClause; }

    public WhereClauseList(IToken leftIToken, IToken rightIToken,
                           WhereClauseList _WhereClauseList,
                           WhereClause _WhereClause)
    {
        super(leftIToken, rightIToken);

        this._WhereClauseList = _WhereClauseList;
        if (_WhereClauseList != null) ((ASTNode) _WhereClauseList).setParent(this);
        this._WhereClause = _WhereClause;
        ((ASTNode) _WhereClause).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_WhereClauseList);
        list.add(_WhereClause);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof WhereClauseList)) return false;
        if (! super.equals(o)) return false;
        WhereClauseList other = (WhereClauseList) o;
        if (_WhereClauseList == null)
            if (other._WhereClauseList != null) return false;
            else; // continue
        else if (! _WhereClauseList.equals(other._WhereClauseList)) return false;
        if (! _WhereClause.equals(other._WhereClause)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_WhereClauseList == null ? 0 : _WhereClauseList.hashCode());
        hash = hash * 31 + (_WhereClause.hashCode());
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
            if (_WhereClauseList != null) _WhereClauseList.accept(v);
            _WhereClause.accept(v);
        }
        v.endVisit(this);
    }
}


