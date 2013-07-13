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
 *<li>Rule 124:  Primary ::= Ident
 *</b>
 */
public class IdentPrimary extends ASTNodeToken implements IPrimary
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    public IToken getIdent() { return leftIToken; }

    public IdentPrimary(SialParser environment, IToken token)    {
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
       if (!(obj instanceof IdentPrimary)) 
	            return false;
        return  obj == null? false : this.toString().equals(obj.toString());
  }
  private static final long serialVersionUID = -4338743197305594251L;
  public int hashCode(){
       return (int)serialVersionUID;
  }
  public String getName(){
       return toString().toLowerCase();
  }
 }


