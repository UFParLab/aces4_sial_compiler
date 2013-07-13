package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 106:  RelOp ::= <$op
 *<li>Rule 107:  RelOp ::= >$op
 *<li>Rule 108:  RelOp ::= <=$op
 *<li>Rule 109:  RelOp ::= >=$op
 *<li>Rule 110:  RelOp ::= ==$op
 *<li>Rule 111:  RelOp ::= !=$op
 *</b>
 */
public class RelOp extends ASTNodeToken implements IRelOp
{
    public IToken getop() { return leftIToken; }

    public RelOp(IToken token) { super(token); initialize(); }

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


