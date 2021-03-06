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
 *<li>Rule 136:  Ident ::= IDENTIFIER
 *</b>
 */
public class Ident extends ASTNodeToken implements IIdent
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    public IToken getIDENTIFIER() { return leftIToken; }

    public Ident(SialParser environment, IToken token)    {
        super(token);
        this.environment = environment;
        initialize();
    }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }

    public void enter(Visitor v)
    {
        v.visit(this);
        v.endVisit(this);
    }

  IDec dec;
  public void setDec(IDec dec) { this.dec = dec; }
  public IDec getDec() { return dec; }
   public boolean equals(Object obj){
       
       if (!(obj instanceof Ident)) return false;
       return  obj == null? false : (getIDENTIFIER().toString().equalsIgnoreCase(((Ident)obj).getIDENTIFIER().toString()));
  }
  // private static final long serialVersionUID = -4338743197305594251L;
  // public int hashCode(){
 //  return (int)serialVersionUID;
 // }

  public String getName(){
       return toString().toLowerCase();
  }
 }


