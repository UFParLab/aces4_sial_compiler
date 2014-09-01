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
 *<li>Rule 126:  RelOp ::= <$op
 *<li>Rule 127:  RelOp ::= >$op
 *<li>Rule 128:  RelOp ::= <=$op
 *<li>Rule 129:  RelOp ::= >=$op
 *<li>Rule 130:  RelOp ::= ==$op
 *<li>Rule 131:  RelOp ::= !=$op
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


