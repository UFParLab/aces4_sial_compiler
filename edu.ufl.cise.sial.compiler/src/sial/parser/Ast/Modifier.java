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
 *<b>
 *<li>Rule 15:  Modifier ::= predefined$modifier
 *<li>Rule 16:  Modifier ::= contiguous$modifier
 *<li>Rule 17:  Modifier ::= sparse$modifier
 *</b>
 */
public class Modifier extends ASTNodeToken implements IModifier
{
    public IToken getmodifier() { return leftIToken; }

    public Modifier(IToken token) { super(token); initialize(); }

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
}


