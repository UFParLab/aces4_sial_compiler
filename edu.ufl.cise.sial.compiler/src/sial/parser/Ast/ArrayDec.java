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
 *<li>Rule 31:  ArrayDec ::= Modifiersopt ArrayKind Ident ($ DimensionList )$
 *</b>
 */
public class ArrayDec extends ASTNode implements IArrayDec
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private ModifierList _Modifiersopt;
    private ArrayKind _ArrayKind;
    private Ident _Ident;
    private DimensionList _DimensionList;

    public ModifierList getModifiersopt() { return _Modifiersopt; }
    public ArrayKind getArrayKind() { return _ArrayKind; }
    public Ident getIdent() { return _Ident; }
    public DimensionList getDimensionList() { return _DimensionList; }

    public ArrayDec(SialParser environment, IToken leftIToken, IToken rightIToken,
                    ModifierList _Modifiersopt,
                    ArrayKind _ArrayKind,
                    Ident _Ident,
                    DimensionList _DimensionList)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._Modifiersopt = _Modifiersopt;
        ((ASTNode) _Modifiersopt).setParent(this);
        this._ArrayKind = _ArrayKind;
        ((ASTNode) _ArrayKind).setParent(this);
        this._Ident = _Ident;
        ((ASTNode) _Ident).setParent(this);
        this._DimensionList = _DimensionList;
        ((ASTNode) _DimensionList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Modifiersopt);
        list.add(_ArrayKind);
        list.add(_Ident);
        list.add(_DimensionList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ArrayDec)) return false;
        if (! super.equals(o)) return false;
        ArrayDec other = (ArrayDec) o;
        if (! _Modifiersopt.equals(other._Modifiersopt)) return false;
        if (! _ArrayKind.equals(other._ArrayKind)) return false;
        if (! _Ident.equals(other._Ident)) return false;
        if (! _DimensionList.equals(other._DimensionList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Modifiersopt.hashCode());
        hash = hash * 31 + (_ArrayKind.hashCode());
        hash = hash * 31 + (_Ident.hashCode());
        hash = hash * 31 + (_DimensionList.hashCode());
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
            _ArrayKind.accept(v);
            _Ident.accept(v);
            _DimensionList.accept(v);
        }
        v.endVisit(this);
    }
  public String getName(){
	 return getIdent().getName();
	 }
	 public String getTypeName(){
	 return getArrayKind().toString().toLowerCase();
	 }
	}


