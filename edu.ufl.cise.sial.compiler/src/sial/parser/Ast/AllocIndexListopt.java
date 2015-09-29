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
 *<em>
 *<li>Rule 83:  AllocIndexListopt ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 84:  AllocIndexListopt ::= [ AllocIndexList ]
 *</b>
 */
public class AllocIndexListopt extends ASTNode implements IAllocIndexListopt
{
    private ASTNodeToken _LEFTSQUARE;
    private AllocIndexList _AllocIndexList;
    private ASTNodeToken _RIGHTSQUARE;

    public ASTNodeToken getLEFTSQUARE() { return _LEFTSQUARE; }
    public AllocIndexList getAllocIndexList() { return _AllocIndexList; }
    public ASTNodeToken getRIGHTSQUARE() { return _RIGHTSQUARE; }

    public AllocIndexListopt(IToken leftIToken, IToken rightIToken,
                             ASTNodeToken _LEFTSQUARE,
                             AllocIndexList _AllocIndexList,
                             ASTNodeToken _RIGHTSQUARE)
    {
        super(leftIToken, rightIToken);

        this._LEFTSQUARE = _LEFTSQUARE;
        ((ASTNode) _LEFTSQUARE).setParent(this);
        this._AllocIndexList = _AllocIndexList;
        ((ASTNode) _AllocIndexList).setParent(this);
        this._RIGHTSQUARE = _RIGHTSQUARE;
        ((ASTNode) _RIGHTSQUARE).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_LEFTSQUARE);
        list.add(_AllocIndexList);
        list.add(_RIGHTSQUARE);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AllocIndexListopt)) return false;
        if (! super.equals(o)) return false;
        AllocIndexListopt other = (AllocIndexListopt) o;
        if (! _LEFTSQUARE.equals(other._LEFTSQUARE)) return false;
        if (! _AllocIndexList.equals(other._AllocIndexList)) return false;
        if (! _RIGHTSQUARE.equals(other._RIGHTSQUARE)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_LEFTSQUARE.hashCode());
        hash = hash * 31 + (_AllocIndexList.hashCode());
        hash = hash * 31 + (_RIGHTSQUARE.hashCode());
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
            _LEFTSQUARE.accept(v);
            _AllocIndexList.accept(v);
            _RIGHTSQUARE.accept(v);
        }
        v.endVisit(this);
    }
}


