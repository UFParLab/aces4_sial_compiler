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
 *<li>Rule 136:  Term ::= Term ^ ExponentExpression
 *</b>
 */
public class TensorExpr extends ASTNode implements ITerm
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private ITerm _Term;
    private ASTNodeToken _TENSOR;
    private IExponentExpression _ExponentExpression;

    public ITerm getTerm() { return _Term; }
    public ASTNodeToken getTENSOR() { return _TENSOR; }
    public IExponentExpression getExponentExpression() { return _ExponentExpression; }

    public TensorExpr(SialParser environment, IToken leftIToken, IToken rightIToken,
                      ITerm _Term,
                      ASTNodeToken _TENSOR,
                      IExponentExpression _ExponentExpression)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Term = _Term;
        ((ASTNode) _Term).setParent(this);
        this._TENSOR = _TENSOR;
        ((ASTNode) _TENSOR).setParent(this);
        this._ExponentExpression = _ExponentExpression;
        ((ASTNode) _ExponentExpression).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Term);
        list.add(_TENSOR);
        list.add(_ExponentExpression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof TensorExpr)) return false;
        if (! super.equals(o)) return false;
        TensorExpr other = (TensorExpr) o;
        if (! _Term.equals(other._Term)) return false;
        if (! _TENSOR.equals(other._TENSOR)) return false;
        if (! _ExponentExpression.equals(other._ExponentExpression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Term.hashCode());
        hash = hash * 31 + (_TENSOR.hashCode());
        hash = hash * 31 + (_ExponentExpression.hashCode());
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
            _Term.accept(v);
            _TENSOR.accept(v);
            _ExponentExpression.accept(v);
        }
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


