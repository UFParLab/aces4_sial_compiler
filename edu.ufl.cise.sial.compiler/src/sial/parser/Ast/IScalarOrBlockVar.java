package sial.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>DataBlock
 *<li>Ident
 *</ul>
 *</b>
 */
public interface IScalarOrBlockVar
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


