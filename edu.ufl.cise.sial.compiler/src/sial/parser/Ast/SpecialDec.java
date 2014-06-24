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
 *<li>Rule 51:  SpecialDec ::= special$ Ident IdentOpt$Signature
 *</b>
 */
public class SpecialDec extends ASTNode implements ISpecialDec
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private Ident _Ident;
    private Ident _Signature;

    public Ident getIdent() { return _Ident; }
    /**
     * The value returned by <b>getSignature</b> may be <b>null</b>
     */
    public Ident getSignature() { return _Signature; }

    public SpecialDec(SialParser environment, IToken leftIToken, IToken rightIToken,
                      Ident _Ident,
                      Ident _Signature)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._Signature = _Signature;
        if (_Signature != null) ((ASTNode) _Signature).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        list.add(_Signature);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SpecialDec)) return false;
        if (! super.equals(o)) return false;
        SpecialDec other = (SpecialDec) o;
        if (! _Ident.equals(other._Ident)) return false;
        if (_Signature == null)
            if (other._Signature != null) return false;
            else; // continue
        else if (! _Signature.equals(other._Signature)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_Signature == null ? 0 : _Signature.hashCode());
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
            if (_Signature != null) _Signature.accept(v);
        }
        v.endVisit(this);
    }
  public String getName(){
  return getIdent().getName();
  }
  	 int addr;  //index of this function in function table
	 public void setAddr(int addr){this.addr = addr;}
	 public int getAddr(){return addr;}
	 
	 int numArgs; 
	 public void setNumArgs(int numArgs){this.numArgs = numArgs;}
	 public int  getNumArgs(){return numArgs;}
	 int[] intent;  //array holding intents for each argument.  
	 public void setIntent(int[] intent){this.intent = intent;}
	 public int[] getIntent(){return intent;}
 }


