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
 * is implemented by <b>DimensionList</b>
 */
public interface IDimensionList
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


