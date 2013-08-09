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
 *<li>Rule 53:  SpecialDec ::= special$ Ident
 *</b>
 */
public class SpecialDec extends ASTNode implements ISpecialDec
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private Ident _Ident;

    public Ident getIdent() { return _Ident; }

    public SpecialDec(SialParser environment, IToken leftIToken, IToken rightIToken,
                      Ident _Ident)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SpecialDec)) return false;
        if (! super.equals(o)) return false;
        SpecialDec other = (SpecialDec) o;
        if (! _Ident.equals(other._Ident)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
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
            _Ident.accept(v);
        v.endVisit(this);
    }
  public String getName(){
  return getIdent().getName();
  }
  	 int addr;  //index of this function in function table
	 public void setAddr(int addr){this.addr = addr;}
	 public int getAddr(){return addr;}
 }


