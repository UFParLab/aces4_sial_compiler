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
 *<li>Rule 148:  ExponentExpression ::= ExponentExpression ** CastExpression
 *</b>
 */
public class ExponentExpr extends ASTNode implements IExponentExpression
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private IExponentExpression _ExponentExpression;
    private ASTNodeToken _EXP;
    private ICastExpression _CastExpression;

    public IExponentExpression getExponentExpression() { return _ExponentExpression; }
    public ASTNodeToken getEXP() { return _EXP; }
    public ICastExpression getCastExpression() { return _CastExpression; }

    public ExponentExpr(SialParser environment, IToken leftIToken, IToken rightIToken,
                        IExponentExpression _ExponentExpression,
                        ASTNodeToken _EXP,
                        ICastExpression _CastExpression)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._ExponentExpression = _ExponentExpression;
        ((ASTNode) _ExponentExpression).setParent(this);
        this._EXP = _EXP;
        ((ASTNode) _EXP).setParent(this);
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
        list.add(_ExponentExpression);
        list.add(_EXP);
        list.add(_CastExpression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ExponentExpr)) return false;
        if (! super.equals(o)) return false;
        ExponentExpr other = (ExponentExpr) o;
        if (! _ExponentExpression.equals(other._ExponentExpression)) return false;
        if (! _EXP.equals(other._EXP)) return false;
        if (! _CastExpression.equals(other._CastExpression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ExponentExpression.hashCode());
        hash = hash * 31 + (_EXP.hashCode());
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
        {
            _ExponentExpression.accept(v);
            _EXP.accept(v);
            _CastExpression.accept(v);
        }
        v.endVisit(this);
    }
  EnumSet<EType>  typeSet = EnumSet.noneOf(EType.class);;
  public EnumSet<EType> getTypeSet() { return typeSet;}
  public void addType(EType t){;
     typeSet.add(t);
  }
  public boolean hasType(EType t){
  return typeSet.contains(t);
  }
 }


