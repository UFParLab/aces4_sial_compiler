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
 *<li>Rule 108:  Statement ::= Ident AssignOp Expression
 *</b>
 */
public class AssignToIdent extends ASTNode implements IStatement
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private Ident _Ident;
    private AssignOp _AssignOp;
    private IExpression _Expression;

    public Ident getIdent() { return _Ident; }
    public AssignOp getAssignOp() { return _AssignOp; }
    public IExpression getExpression() { return _Expression; }

    public AssignToIdent(SialParser environment, IToken leftIToken, IToken rightIToken,
                         Ident _Ident,
                         AssignOp _AssignOp,
                         IExpression _Expression)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._AssignOp = _AssignOp;
        ((ASTNode) _AssignOp).setParent(this);
        this._Expression = _Expression;
        ((ASTNode) _Expression).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Ident);
        list.add(_AssignOp);
        list.add(_Expression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AssignToIdent)) return false;
        if (! super.equals(o)) return false;
        AssignToIdent other = (AssignToIdent) o;
        if (! _Ident.equals(other._Ident)) return false;
        if (! _AssignOp.equals(other._AssignOp)) return false;
        if (! _Expression.equals(other._Expression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_AssignOp.hashCode());
        hash = hash * 31 + (_Expression.hashCode());
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
            _AssignOp.accept(v);
            _Expression.accept(v);
        }
        v.endVisit(this);
    }
 boolean slice;
   boolean insert;
   public boolean isSlice(){return slice;}
   public void setSlice(boolean val){ slice = val; }
   public boolean isInsert(){return insert;}
   public void setInsert(boolean val){insert = val;}
 }


