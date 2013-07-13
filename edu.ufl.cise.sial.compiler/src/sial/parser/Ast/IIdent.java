package sial.parser.Ast;

/**
 * is always implemented by <b>ASTNodeToken</b>. It is also implemented by <b>Ident</b>
 */
public interface IIdent extends IDimension, IRangeVal, IIdentOpt, IScalarOrBlockVar, IAllocIndex, IPrimary, IASTNodeToken {}


