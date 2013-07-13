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
 *<li>Rule 2:  Program ::= sial$ Ident$startName EOLs$ DecList StatementList endsial$ Ident$endName EOLsopt$
 *</b>
 */
public class Program extends ASTNode implements IProgram
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private Ident _startName;
    private DecList _DecList;
    private StatementList _StatementList;
    private Ident _endName;

    public Ident getstartName() { return _startName; }
    public DecList getDecList() { return _DecList; }
    public StatementList getStatementList() { return _StatementList; }
    public Ident getendName() { return _endName; }

    public Program(SialParser environment, IToken leftIToken, IToken rightIToken,
                   Ident _startName,
                   DecList _DecList,
                   StatementList _StatementList,
                   Ident _endName)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._startName = _startName;
        ((ASTNode) _startName).setParent(this);
        this._DecList = _DecList;
        ((ASTNode) _DecList).setParent(this);
        this._StatementList = _StatementList;
        ((ASTNode) _StatementList).setParent(this);
        this._endName = _endName;
        ((ASTNode) _endName).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_startName);
        list.add(_DecList);
        list.add(_StatementList);
        list.add(_endName);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Program)) return false;
        if (! super.equals(o)) return false;
        Program other = (Program) o;
        if (! _startName.equals(other._startName)) return false;
        if (! _DecList.equals(other._DecList)) return false;
        if (! _StatementList.equals(other._StatementList)) return false;
        if (! _endName.equals(other._endName)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_startName.hashCode());
        hash = hash * 31 + (_DecList.hashCode());
        hash = hash * 31 + (_StatementList.hashCode());
        hash = hash * 31 + (_endName.hashCode());
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
            _startName.accept(v);
            _DecList.accept(v);
            _StatementList.accept(v);
            _endName.accept(v);
        }
        v.endVisit(this);
    }
  
 public String getStartName(){
    return  getstartName().toString().toLowerCase();
 }
 
 public String getEndName(){
    return  getendName().toString().toLowerCase();
 }
 

}


