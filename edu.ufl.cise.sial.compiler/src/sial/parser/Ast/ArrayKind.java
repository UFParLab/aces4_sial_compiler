package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 30:  ArrayKind ::= static$akind
 *<li>Rule 31:  ArrayKind ::= temp$akind
 *<li>Rule 32:  ArrayKind ::= local$akind
 *<li>Rule 33:  ArrayKind ::= distributed$akind
 *<li>Rule 34:  ArrayKind ::= served$akind
 *</b>
 */
public class ArrayKind extends ASTNodeToken implements IArrayKind
{
    public IToken getakind() { return leftIToken; }

    public ArrayKind(IToken token) { super(token); initialize(); }

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


