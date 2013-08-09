package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 42:  IndexKind ::= aoindex$ikind
 *<li>Rule 43:  IndexKind ::= moindex$ikind
 *<li>Rule 44:  IndexKind ::= moaindex$ikind
 *<li>Rule 45:  IndexKind ::= mobindex$ikind
 *<li>Rule 46:  IndexKind ::= index$ikind
 *<li>Rule 47:  IndexKind ::= laindex$ikind
 *</b>
 */
public class IndexKind extends ASTNodeToken implements IIndexKind
{
    public IToken getikind() { return leftIToken; }

    public IndexKind(IToken token) { super(token); initialize(); }

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


