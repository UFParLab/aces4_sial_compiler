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
 *<li>Rule 49:  RangeVal ::= Ident
 *</b>
 */
public class IdentRangeVal extends ASTNodeToken implements IRangeVal
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    public IToken getIdent() { return leftIToken; }

    public IdentRangeVal(SialParser environment, IToken token)    {
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

   public String getName(){
   return toString().toLowerCase();
   }
   IDec dec;
   public void setDec(IDec dec) { this.dec = dec; }
   public IDec getDec() { return dec; }
  }


