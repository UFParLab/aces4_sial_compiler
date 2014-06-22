package sial.parser.Ast;

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
 *<li>Rule 11:  Modifiersopt ::= $Empty
 *<li>Rule 12:  Modifiersopt ::= Modifiers
 *<li>Rule 13:  Modifiers ::= Modifier
 *<li>Rule 14:  Modifiers ::= Modifiers Modifier
 *</b>
 */
public class ModifierList extends AbstractASTNodeList implements IModifiersopt, IModifiers
{
    public Modifier getModifierAt(int i) { return (Modifier) getElementAt(i); }

    public ModifierList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ModifierList(Modifier _Modifier, boolean leftRecursive)
    {
        super((ASTNode) _Modifier, leftRecursive);
        ((ASTNode) _Modifier).setParent(this);
    }

    public void add(Modifier _Modifier)
    {
        super.add((ASTNode) _Modifier);
        ((ASTNode) _Modifier).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ModifierList)) return false;
        if (! super.equals(o)) return false;
        ModifierList other = (ModifierList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Modifier element = getModifierAt(i);
            if (! element.equals(other.getModifierAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getModifierAt(i).hashCode());
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
            for (int i = 0; i < size(); i++)
            {
                Modifier element = getModifierAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


