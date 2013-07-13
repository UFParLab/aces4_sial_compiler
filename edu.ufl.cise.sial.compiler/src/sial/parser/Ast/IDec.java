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
 *<li>ScalarDec
 *<li>IntDec
 *<li>ArrayDec
 *<li>IndexDec
 *<li>SubIndexDec
 *<li>ProcDec
 *<li>SpecialDec
 *</ul>
 *</b>
 */
public interface IDec
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


