package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 *<b>
 *<li>Rule 8:  ImportProgList ::= $Empty
 *<li>Rule 9:  ImportProgList ::= ImportProgList ImportProg
 *</b>
 */
public class ImportProgList extends AbstractASTNodeList implements IImportProgList
{
    public ImportProg getImportProgAt(int i) { return (ImportProg) getElementAt(i); }

    public ImportProgList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ImportProgList(ImportProg _ImportProg, boolean leftRecursive)
    {
        super((ASTNode) _ImportProg, leftRecursive);
        ((ASTNode) _ImportProg).setParent(this);
    }

    public void add(ImportProg _ImportProg)
    {
        super.add((ASTNode) _ImportProg);
        ((ASTNode) _ImportProg).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ImportProgList)) return false;
        if (! super.equals(o)) return false;
        ImportProgList other = (ImportProgList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            ImportProg element = getImportProgAt(i);
            if (! element.equals(other.getImportProgAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getImportProgAt(i).hashCode());
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
                ImportProg element = getImportProgAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


