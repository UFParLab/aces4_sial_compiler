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
 *<li>Rule 54:  ProcDec ::= proc$ Ident EOLs$ StatementList endproc Ident$endIdent
 *</b>
 */
public class ProcDec extends ASTNode implements IProcDec
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private Ident _Ident;
    private StatementList _StatementList;
    private ASTNodeToken _endproc;
    private Ident _endIdent;

    public Ident getIdent() { return _Ident; }
    public StatementList getStatementList() { return _StatementList; }
    public ASTNodeToken getendproc() { return _endproc; }
    public Ident getendIdent() { return _endIdent; }

    public ProcDec(SialParser environment, IToken leftIToken, IToken rightIToken,
                   Ident _Ident,
                   StatementList _StatementList,
                   ASTNodeToken _endproc,
                   Ident _endIdent)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._StatementList = _StatementList;
        ((ASTNode) _StatementList).setParent(this);
        this._endproc = _endproc;
        ((ASTNode) _endproc).setParent(this);
        this._endIdent = _endIdent;
        ((ASTNode) _endIdent).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        list.add(_StatementList);
        list.add(_endproc);
        list.add(_endIdent);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ProcDec)) return false;
        if (! super.equals(o)) return false;
        ProcDec other = (ProcDec) o;
        if (! _Ident.equals(other._Ident)) return false;
        if (! _StatementList.equals(other._StatementList)) return false;
        if (! _endproc.equals(other._endproc)) return false;
        if (! _endIdent.equals(other._endIdent)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_StatementList.hashCode());
        hash = hash * 31 + (_endproc.hashCode());
        hash = hash * 31 + (_endIdent.hashCode());
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
        {
            _Ident.accept(v);
            _StatementList.accept(v);
            _endproc.accept(v);
            _endIdent.accept(v);
        }
        v.endVisit(this);
    }
  public String getName(){
	return getIdent().getName();
	}
	private  int addr;  //index of start of proc in optable
	 public void setAddr(int addr){this.addr = addr;}
	 public int getAddr(){return addr;}
	 
	 private List<CallStatement> callSites = new ArrayList<CallStatement>();
	 public  List<CallStatement> getCallSites(){return callSites;}
   }


