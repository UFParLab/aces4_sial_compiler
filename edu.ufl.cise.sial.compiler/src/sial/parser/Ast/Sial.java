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
 *<li>Rule 1:  Sial ::= EOLsopt$ ImportProgList Program
 *</b>
 */
public class Sial extends ASTNode implements ISial
{
    private SialParser environment;
    public SialParser getEnvironment() { return environment; }

    private ImportProgList _ImportProgList;
    private Program _Program;

    public ImportProgList getImportProgList() { return _ImportProgList; }
    public Program getProgram() { return _Program; }

    public Sial(SialParser environment, IToken leftIToken, IToken rightIToken,
                ImportProgList _ImportProgList,
                Program _Program)
    {
        super(leftIToken, rightIToken);

        this.environment = environment;
        this._ImportProgList = _ImportProgList;
        ((ASTNode) _ImportProgList).setParent(this);
        this._Program = _Program;
        ((ASTNode) _Program).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ImportProgList);
        list.add(_Program);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Sial)) return false;
        if (! super.equals(o)) return false;
        Sial other = (Sial) o;
        if (! _ImportProgList.equals(other._ImportProgList)) return false;
        if (! _Program.equals(other._Program)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ImportProgList.hashCode());
        hash = hash * 31 + (_Program.hashCode());
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
            _ImportProgList.accept(v);
            _Program.accept(v);
        }
        v.endVisit(this);
    }

  
SymbolTable astSymbolTable;
public SymbolTable getSymbolTable(){return astSymbolTable;}	
public void setSymbolTable(SymbolTable sym){astSymbolTable = sym;}
public boolean isSymbolTablePopulated(){return astSymbolTable.isSymbolTablePopulated();}

String fileName;  //name of file containing source code, should be  canonical path
public void setFileName(String fileName){this.fileName = fileName;}
public String getFileName(){return fileName;}

Date created;
public boolean after(long modified){
   return created.after(new Date(modified));
}
   
public void  initialize(boolean isImported){
astSymbolTable = new SymbolTable(this);
created = new Date();
this.isImported = isImported;
}

boolean isImported;  //indicates that this program is imported into another SIAL program
public boolean isImported(){
       return isImported;
}



}


