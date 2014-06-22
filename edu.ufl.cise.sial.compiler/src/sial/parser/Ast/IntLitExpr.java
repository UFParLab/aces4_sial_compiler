package sial.parser.Ast;

import sial.parser.*;
import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;
  import sial.parser.context.ExpressionType.EType;
  import java.util.EnumSet;

/**
 *<b>
 *<li>Rule 140:  Primary ::= INTLIT
 *</b>
 */
public class IntLitExpr extends ASTNodeToken implements IPrimary
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    public IToken getINTLIT() { return leftIToken; }

    public IntLitExpr(SialParser environment, IToken token)    {
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
  EnumSet<EType>  typeSet;
  public EnumSet<EType> getTypeSet() { return typeSet;}
  public void addType(EType t){
  if (typeSet == null){ 
     typeSet = EnumSet.of(t);
	 }
     else typeSet.add(t);
  }
  public boolean hasType(EType t){
  return typeSet.contains(t);
  }
 }


