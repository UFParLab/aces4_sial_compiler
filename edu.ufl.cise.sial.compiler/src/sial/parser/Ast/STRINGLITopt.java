package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<em>
 *<li>Rule 99:  STRINGLITopt ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 100:  STRINGLITopt ::= STRINGLIT
 *</b>
 */
public class STRINGLITopt extends ASTNodeToken implements ISTRINGLITopt
{
    public IToken getSTRINGLIT() { return leftIToken; }

    public STRINGLITopt(IToken token) { super(token); initialize(); }

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


