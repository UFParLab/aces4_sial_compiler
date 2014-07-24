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
 *<li>Rule 144:  UnaryExpression ::= sqrt$ Primary
 *</b>
 */
public class SqrtUnaryExpr extends ASTNode implements IUnaryExpression
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private IPrimary _Primary;

    public IPrimary getPrimary() { return _Primary; }

    public SqrtUnaryExpr(SialParser environment, IToken leftIToken, IToken rightIToken,
                         IPrimary _Primary)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Primary = _Primary;
        ((ASTNode) _Primary).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Primary);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SqrtUnaryExpr)) return false;
        if (! super.equals(o)) return false;
        SqrtUnaryExpr other = (SqrtUnaryExpr) o;
        if (! _Primary.equals(other._Primary)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Primary.hashCode());
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
            _Primary.accept(v);
        v.endVisit(this);
    }
  EnumSet<EType>  typeSet = EnumSet.noneOf(EType.class);;
  public EnumSet<EType> getTypeSet() { return typeSet;}
  public void addType(EType t){
//	  if (typeSet == null){ 
//	     typeSet = EnumSet.of(t);
//		 }
//	     else typeSet.add(t);
     typeSet.add(t);
  }
  public boolean hasType(EType t){
  return typeSet.contains(t);
  }
 }


