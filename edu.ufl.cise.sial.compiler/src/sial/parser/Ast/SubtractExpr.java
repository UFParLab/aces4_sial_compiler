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
 *<li>Rule 142:  Expression ::= Expression - Term
 *</b>
 */
public class SubtractExpr extends ASTNode implements IExpression
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private IExpression _Expression;
    private ASTNodeToken _MINUS;
    private ITerm _Term;

    public IExpression getExpression() { return _Expression; }
    public ASTNodeToken getMINUS() { return _MINUS; }
    public ITerm getTerm() { return _Term; }

    public SubtractExpr(SialParser environment, IToken leftIToken, IToken rightIToken,
                        IExpression _Expression,
                        ASTNodeToken _MINUS,
                        ITerm _Term)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Expression = _Expression;
        ((ASTNode) _Expression).setParent(this);
        this._MINUS = _MINUS;
        ((ASTNode) _MINUS).setParent(this);
        this._Term = _Term;
        ((ASTNode) _Term).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Expression);
        list.add(_MINUS);
        list.add(_Term);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SubtractExpr)) return false;
        if (! super.equals(o)) return false;
        SubtractExpr other = (SubtractExpr) o;
        if (! _Expression.equals(other._Expression)) return false;
        if (! _MINUS.equals(other._MINUS)) return false;
        if (! _Term.equals(other._Term)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Expression.hashCode());
        hash = hash * 31 + (_MINUS.hashCode());
        hash = hash * 31 + (_Term.hashCode());
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
            _Expression.accept(v);
            _MINUS.accept(v);
            _Term.accept(v);
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


