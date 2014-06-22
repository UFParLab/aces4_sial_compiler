package sial.parser.Ast;

import sial.parser.*;
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
 *<li>Rule 70:  Statement ::= section$ EOLs$ StatementList endsection$
 *</b>
 */
public class Section extends ASTNode implements IStatement
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private StatementList _StatementList;

    public StatementList getStatementList() { return _StatementList; }

    public Section(SialParser environment, IToken leftIToken, IToken rightIToken,
                   StatementList _StatementList)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._StatementList = _StatementList;
        ((ASTNode) _StatementList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_StatementList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Section)) return false;
        if (! super.equals(o)) return false;
        Section other = (Section) o;
        if (! _StatementList.equals(other._StatementList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_StatementList.hashCode());
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
            _StatementList.accept(v);
        v.endVisit(this);
    }

		//NOT CLEAR WHY THIS IS HERE!!!!
  IDec dec;
  public void setDec(IDec dec) { this.dec = dec; }
  public IDec getDec() { return dec; }
  }


