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
 *<li>Rule 41:  IndexDec ::= Modifiersopt IndexKind Ident =$ Range
 *</b>
 */
public class IndexDec extends ASTNode implements IIndexDec
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private ModifierList _Modifiersopt;
    private IndexKind _IndexKind;
    private Ident _Ident;
    private Range _Range;

    public ModifierList getModifiersopt() { return _Modifiersopt; }
    public IndexKind getIndexKind() { return _IndexKind; }
    public Ident getIdent() { return _Ident; }
    public Range getRange() { return _Range; }

    public IndexDec(SialParser environment, IToken leftIToken, IToken rightIToken,
                    ModifierList _Modifiersopt,
                    IndexKind _IndexKind,
                    Ident _Ident,
                    Range _Range)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Modifiersopt = _Modifiersopt;
        ((ASTNode) _Modifiersopt).setParent(this);
        this._IndexKind = _IndexKind;
        ((ASTNode) _IndexKind).setParent(this);
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._Range = _Range;
        ((ASTNode) _Range).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Modifiersopt);
        list.add(_IndexKind);
        list.add(_Ident);
        list.add(_Range);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IndexDec)) return false;
        if (! super.equals(o)) return false;
        IndexDec other = (IndexDec) o;
        if (! _Modifiersopt.equals(other._Modifiersopt)) return false;
        if (! _IndexKind.equals(other._IndexKind)) return false;
        if (! _Ident.equals(other._Ident)) return false;
        if (! _Range.equals(other._Range)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Modifiersopt.hashCode());
        hash = hash * 31 + (_IndexKind.hashCode());
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_Range.hashCode());
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
            _IndexKind.accept(v);
            _Ident.accept(v);
            _Range.accept(v);
        }
        v.endVisit(this);
    }
  public String getName(){
	  return getIdent().getName();
	  }
	  public String getTypeName(){
	  return getIndexKind().getikind().toString().toLowerCase();
	  }   
	 }


