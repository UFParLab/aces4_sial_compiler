package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<em>
 *<li>Rule 106:  AllocIndexListopt ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 107:  AllocIndexListopt ::= ( AllocIndexList )
 *</b>
 */
public class AllocIndexListopt extends ASTNode implements IAllocIndexListopt
{
    private ASTNodeToken _LEFTPAREN;
    private AllocIndexList _AllocIndexList;
    private ASTNodeToken _RIGHTPAREN;

    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    public AllocIndexList getAllocIndexList() { return _AllocIndexList; }
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }

    public AllocIndexListopt(IToken leftIToken, IToken rightIToken,
                             ASTNodeToken _LEFTPAREN,
                             AllocIndexList _AllocIndexList,
                             ASTNodeToken _RIGHTPAREN)
    {
        super(leftIToken, rightIToken);

        this._LEFTPAREN = _LEFTPAREN;
        ((ASTNode) _LEFTPAREN).setParent(this);
        this._AllocIndexList = _AllocIndexList;
        ((ASTNode) _AllocIndexList).setParent(this);
        this._RIGHTPAREN = _RIGHTPAREN;
        ((ASTNode) _RIGHTPAREN).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_LEFTPAREN);
        list.add(_AllocIndexList);
        list.add(_RIGHTPAREN);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AllocIndexListopt)) return false;
        if (! super.equals(o)) return false;
        AllocIndexListopt other = (AllocIndexListopt) o;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (! _AllocIndexList.equals(other._AllocIndexList)) return false;
        if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_LEFTPAREN.hashCode());
        hash = hash * 31 + (_AllocIndexList.hashCode());
        hash = hash * 31 + (_RIGHTPAREN.hashCode());
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
            _LEFTPAREN.accept(v);
            _AllocIndexList.accept(v);
            _RIGHTPAREN.accept(v);
        }
        v.endVisit(this);
    }
}


