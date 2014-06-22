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
 *<li>Rule 136:  CastExpression ::= ($ scalar$ )$ CastExpression
 *</b>
 */
public class ScalarCastExpr extends ASTNode implements ICastExpression
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private ICastExpression _CastExpression;

    public ICastExpression getCastExpression() { return _CastExpression; }

    public ScalarCastExpr(SialParser environment, IToken leftIToken, IToken rightIToken,
                          ICastExpression _CastExpression)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._CastExpression = _CastExpression;
        ((ASTNode) _CastExpression).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_CastExpression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ScalarCastExpr)) return false;
        if (! super.equals(o)) return false;
        ScalarCastExpr other = (ScalarCastExpr) o;
        if (! _CastExpression.equals(other._CastExpression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_CastExpression.hashCode());
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
            _CastExpression.accept(v);
        v.endVisit(this);
    }
  EnumSet<EType>  typeSet;
  public EnumSet<EType> getTypeSet() { return typeSet;}
  public void addType(EType t){
  if (typeSet == null){ 
     typeSet = EnumSet.of(t);
	 }
     else typeSet.add(t);
  }
  public boolean hasType(EType t){
  return typeSet.contains(t);
  }
 }

