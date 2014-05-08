package sial.parser.Ast;

import sial.parser.*;
import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 47:  SubIndexDec ::= subindex$ Ident of$ Ident$ParentIdent
 *</b>
 */
public class SubIndexDec extends ASTNode implements ISubIndexDec
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private Ident _Ident;
    private Ident _ParentIdent;

    public Ident getIdent() { return _Ident; }
    public Ident getParentIdent() { return _ParentIdent; }

    public SubIndexDec(SialParser environment, IToken leftIToken, IToken rightIToken,
                       Ident _Ident,
                       Ident _ParentIdent)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._ParentIdent = _ParentIdent;
        ((ASTNode) _ParentIdent).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        list.add(_ParentIdent);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SubIndexDec)) return false;
        if (! super.equals(o)) return false;
        SubIndexDec other = (SubIndexDec) o;
        if (! _Ident.equals(other._Ident)) return false;
        if (! _ParentIdent.equals(other._ParentIdent)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_ParentIdent.hashCode());
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
            _Ident.accept(v);
            _ParentIdent.accept(v);
        }
        v.endVisit(this);
    }
  public String getName(){
  return getIdent().getName();
  }
  public String getParentName(){
  return getParentIdent().toString().toLowerCase();
  }
 }


