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
 *<li>Rule 27:  ScalarDec ::= Modifiersopt scalar$ Ident ScalarInitializationOpt
 *</b>
 */
public class ScalarDec extends ASTNode implements IScalarDec
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private ModifierList _Modifiersopt;
    private Ident _Ident;
    private ScalarInitialValue _ScalarInitializationOpt;

    public ModifierList getModifiersopt() { return _Modifiersopt; }
    public Ident getIdent() { return _Ident; }
    /**
     * The value returned by <b>getScalarInitializationOpt</b> may be <b>null</b>
     */
    public ScalarInitialValue getScalarInitializationOpt() { return _ScalarInitializationOpt; }

    public ScalarDec(SialParser environment, IToken leftIToken, IToken rightIToken,
                     ModifierList _Modifiersopt,
                     Ident _Ident,
                     ScalarInitialValue _ScalarInitializationOpt)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Modifiersopt = _Modifiersopt;
        ((ASTNode) _Modifiersopt).setParent(this);
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._ScalarInitializationOpt = _ScalarInitializationOpt;
        if (_ScalarInitializationOpt != null) ((ASTNode) _ScalarInitializationOpt).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Modifiersopt);
        list.add(_Ident);
        list.add(_ScalarInitializationOpt);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ScalarDec)) return false;
        if (! super.equals(o)) return false;
        ScalarDec other = (ScalarDec) o;
        if (! _Modifiersopt.equals(other._Modifiersopt)) return false;
        if (! _Ident.equals(other._Ident)) return false;
        if (_ScalarInitializationOpt == null)
            if (other._ScalarInitializationOpt != null) return false;
            else; // continue
        else if (! _ScalarInitializationOpt.equals(other._ScalarInitializationOpt)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Modifiersopt.hashCode());
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_ScalarInitializationOpt == null ? 0 : _ScalarInitializationOpt.hashCode());
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
            _Modifiersopt.accept(v);
            _Ident.accept(v);
            if (_ScalarInitializationOpt != null) _ScalarInitializationOpt.accept(v);
        }
        v.endVisit(this);
    }
  public String getName(){
   return getIdent().getName();
   }
  }


