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
 *<li>Rule 118:  AssignOp ::= =$op
 *<li>Rule 119:  AssignOp ::= +=$op
 *<li>Rule 120:  AssignOp ::= -=$op
 *<li>Rule 121:  AssignOp ::= *=$op
 *</b>
 */
public class AssignOp extends ASTNodeToken implements IAssignOp
{
    public IToken getop() { return leftIToken; }

    public AssignOp(IToken token) { super(token); initialize(); }

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


