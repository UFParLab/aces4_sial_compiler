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
 *<li>Rule 139:  Expression ::= Expression + Term
 *</b>
 */
public class AddExpr extends ASTNode implements IExpression
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private IExpression _Expression;
    private ASTNodeToken _PLUS;
    private ITerm _Term;

    public IExpression getExpression() { return _Expression; }
    public ASTNodeToken getPLUS() { return _PLUS; }
    public ITerm getTerm() { return _Term; }

    public AddExpr(SialParser environment, IToken leftIToken, IToken rightIToken,
                   IExpression _Expression,
                   ASTNodeToken _PLUS,
                   ITerm _Term)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Expression = _Expression;
        ((ASTNode) _Expression).setParent(this);
        this._PLUS = _PLUS;
        ((ASTNode) _PLUS).setParent(this);
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
        list.add(_PLUS);
        list.add(_Term);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AddExpr)) return false;
        if (! super.equals(o)) return false;
        AddExpr other = (AddExpr) o;
        if (! _Expression.equals(other._Expression)) return false;
        if (! _PLUS.equals(other._PLUS)) return false;
        if (! _Term.equals(other._Term)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Expression.hashCode());
        hash = hash * 31 + (_PLUS.hashCode());
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
            _PLUS.accept(v);
            _Term.accept(v);
        }
        v.endVisit(this);
    }
  EnumSet<EType>  typeSet = EnumSet.noneOf(EType.class);
  public EnumSet<EType> getTypeSet() { return typeSet;}
  public void addType(EType t){;
     typeSet.add(t);
  }
  public boolean hasType(EType t){
  return typeSet.contains(t);
  }
 }


