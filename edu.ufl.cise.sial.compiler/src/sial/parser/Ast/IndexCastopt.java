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
 *<li>Rule 128:  IndexCastopt ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 129:  IndexCastopt ::= ($ index$ )$IndexCast
 *</b>
 */
public class IndexCastopt extends ASTNode implements IIndexCastopt
{
    private ASTNodeToken _IndexCast;

    public ASTNodeToken getIndexCast() { return _IndexCast; }

    public IndexCastopt(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _IndexCast)
    {
        super(leftIToken, rightIToken);

        this._IndexCast = _IndexCast;
        ((ASTNode) _IndexCast).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_IndexCast);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IndexCastopt)) return false;
        if (! super.equals(o)) return false;
        IndexCastopt other = (IndexCastopt) o;
        if (! _IndexCast.equals(other._IndexCast)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_IndexCast.hashCode());
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
            _IndexCast.accept(v);
        v.endVisit(this);
    }
}


