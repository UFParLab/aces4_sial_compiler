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
 *<li>Rule 28:  IntDec ::= Modifiersopt int$ Ident
 *</b>
 */
public class IntDec extends ASTNode implements IIntDec
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private ModifierList _Modifiersopt;
    private Ident _Ident;

    public ModifierList getModifiersopt() { return _Modifiersopt; }
    public Ident getIdent() { return _Ident; }

    public IntDec(SialParser environment, IToken leftIToken, IToken rightIToken,
                  ModifierList _Modifiersopt,
                  Ident _Ident)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Modifiersopt = _Modifiersopt;
        ((ASTNode) _Modifiersopt).setParent(this);
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
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
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IntDec)) return false;
        if (! super.equals(o)) return false;
        IntDec other = (IntDec) o;
        if (! _Modifiersopt.equals(other._Modifiersopt)) return false;
        if (! _Ident.equals(other._Ident)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Modifiersopt.hashCode());
        hash = hash * 31 + (_Ident.hashCode());
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
        }
        v.endVisit(this);
    }
  public String getName(){
	return getIdent().getName();
	}
   }


