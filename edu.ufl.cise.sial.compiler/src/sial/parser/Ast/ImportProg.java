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
 *<li>Rule 10:  ImportProg ::= import$ STRINGLIT EOLs$
 *</b>
 */
public class ImportProg extends ASTNode implements IImportProg
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private ASTNodeToken _STRINGLIT;

    public ASTNodeToken getSTRINGLIT() { return _STRINGLIT; }

    public ImportProg(SialParser environment, IToken leftIToken, IToken rightIToken,
                      ASTNodeToken _STRINGLIT)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._STRINGLIT = _STRINGLIT;
        ((ASTNode) _STRINGLIT).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_STRINGLIT);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ImportProg)) return false;
        if (! super.equals(o)) return false;
        ImportProg other = (ImportProg) o;
        if (! _STRINGLIT.equals(other._STRINGLIT)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_STRINGLIT.hashCode());
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
            _STRINGLIT.accept(v);
        v.endVisit(this);
    }
 Sial importAst;
  public void setAst(Sial ast){importAst = ast;}
  public Sial getAst(){return importAst;}
  public String getName(){
       return ASTUtils.getStringVal(getSTRINGLIT().toString());
  }
 }


