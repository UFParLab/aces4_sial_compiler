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
 *<li>Rule 53:  SpecialDec ::= special$ Ident Sigopt
 *</b>
 */
public class SpecialDec extends ASTNode implements ISpecialDec
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private Ident _Ident;
    private Sigopt _Sigopt;

    public Ident getIdent() { return _Ident; }
    /**
     * The value returned by <b>getSigopt</b> may be <b>null</b>
     */
    public Sigopt getSigopt() { return _Sigopt; }

    public SpecialDec(SialParser environment, IToken leftIToken, IToken rightIToken,
                      Ident _Ident,
                      Sigopt _Sigopt)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._Sigopt = _Sigopt;
        if (_Sigopt != null) ((ASTNode) _Sigopt).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        list.add(_Sigopt);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SpecialDec)) return false;
        if (! super.equals(o)) return false;
        SpecialDec other = (SpecialDec) o;
        if (! _Ident.equals(other._Ident)) return false;
        if (_Sigopt == null)
            if (other._Sigopt != null) return false;
            else; // continue
        else if (! _Sigopt.equals(other._Sigopt)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_Sigopt == null ? 0 : _Sigopt.hashCode());
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
            if (_Sigopt != null) _Sigopt.accept(v);
        }
        v.endVisit(this);
    }
  public String getName(){
  return getIdent().getName();
  }
  	 int addr;  //index of this function in function table
	 public void setAddr(int addr){this.addr = addr;}
	 public int getAddr(){return addr;}
 }


