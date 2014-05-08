package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 17:  Modifier ::= scoped_extent
 *</b>
 */
public class ScopedExtentModifier extends ASTNodeToken implements IModifier
{
    public IToken getscoped_extent() { return leftIToken; }

    public ScopedExtentModifier(IToken token) { super(token); initialize(); }

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


