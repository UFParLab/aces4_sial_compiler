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
 *<li>Rule 155:  StringLiteral ::= STRINGLIT
 *</b>
 */
public class StringLiteral extends ASTNodeToken implements IStringLiteral
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    public IToken getSTRINGLIT() { return leftIToken; }

    public StringLiteral(SialParser environment, IToken token)    {
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
 
  String stringValue;
  public void setStringValue(String stringValue){
         this.stringValue = stringValue;
  }
  public String  getStringValue(){
         return stringValue;
  }
  
 }


