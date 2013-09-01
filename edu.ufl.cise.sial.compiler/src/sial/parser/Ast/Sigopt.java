package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<em>
 *<li>Rule 54:  Sigopt ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 55:  Sigopt ::= INTLIT
 *</b>
 */
public class Sigopt extends ASTNodeToken implements ISigopt
{
    public IToken getINTLIT() { return leftIToken; }

    public Sigopt(IToken token) { super(token); initialize(); }

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


