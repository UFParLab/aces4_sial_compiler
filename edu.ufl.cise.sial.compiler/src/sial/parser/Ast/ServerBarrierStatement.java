package sial.parser.Ast;

import sial.parser.*;
import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 61:  Statement ::= server_barrier$ IdentOpt
 *</b>
 */
public class ServerBarrierStatement extends ASTNode implements IStatement
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private Ident _IdentOpt;

    /**
     * The value returned by <b>getIdentOpt</b> may be <b>null</b>
     */
    public Ident getIdentOpt() { return _IdentOpt; }

    public ServerBarrierStatement(SialParser environment, IToken leftIToken, IToken rightIToken,
                                  Ident _IdentOpt)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._IdentOpt = _IdentOpt;
        if (_IdentOpt != null) ((ASTNode) _IdentOpt).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_IdentOpt);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ServerBarrierStatement)) return false;
        if (! super.equals(o)) return false;
        ServerBarrierStatement other = (ServerBarrierStatement) o;
        if (_IdentOpt == null)
            if (other._IdentOpt != null) return false;
            else; // continue
        else if (! _IdentOpt.equals(other._IdentOpt)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_IdentOpt == null ? 0 : _IdentOpt.hashCode());
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
            if (_IdentOpt != null) _IdentOpt.accept(v);
        v.endVisit(this);
    }

  IDec dec;
  public void setDec(IDec dec) { this.dec = dec; }
  public IDec getDec() { return dec; }
  }


