package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 84:  Statement ::= println$ StringLiteral
 *</b>
 */
public class PrintlnStatement extends ASTNode implements IStatement
{
    private StringLiteral _StringLiteral;

    public StringLiteral getStringLiteral() { return _StringLiteral; }

    public PrintlnStatement(IToken leftIToken, IToken rightIToken,
                            StringLiteral _StringLiteral)
    {
        super(leftIToken, rightIToken);

        this._StringLiteral = _StringLiteral;
        ((ASTNode) _StringLiteral).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_StringLiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PrintlnStatement)) return false;
        if (! super.equals(o)) return false;
        PrintlnStatement other = (PrintlnStatement) o;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_StringLiteral.hashCode());
        return hash;
    }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }

    public void enter(Visitor v)
    {
        boolean checkChildren = v.visit(this);
        if (checkChildren)
            _StringLiteral.accept(v);
        v.endVisit(this);
    }
}


