package sial.parser;

import sial.parser.Ast.*;
import lpg.runtime.*;
import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;
  import sial.parser.context.ExpressionType.EType;
  import java.util.EnumSet;

public class SialParser implements RuleAction, IParser
{
    private PrsStream prsStream = null;
    
    private boolean unimplementedSymbolsWarning = false;

    private static ParseTable prsTable = new SialParserprs();
    public ParseTable getParseTable() { return prsTable; }

    private DeterministicParser dtParser = null;
    public DeterministicParser getParser() { return dtParser; }

    private void setResult(Object object) { dtParser.setSym1(object); }
    public Object getRhsSym(int i) { return dtParser.getSym(i); }

    public int getRhsTokenIndex(int i) { return dtParser.getToken(i); }
    public IToken getRhsIToken(int i) { return prsStream.getIToken(getRhsTokenIndex(i)); }
    
    public int getRhsFirstTokenIndex(int i) { return dtParser.getFirstToken(i); }
    public IToken getRhsFirstIToken(int i) { return prsStream.getIToken(getRhsFirstTokenIndex(i)); }

    public int getRhsLastTokenIndex(int i) { return dtParser.getLastToken(i); }
    public IToken getRhsLastIToken(int i) { return prsStream.getIToken(getRhsLastTokenIndex(i)); }

    public int getLeftSpan() { return dtParser.getFirstToken(); }
    public IToken getLeftIToken()  { return prsStream.getIToken(getLeftSpan()); }

    public int getRightSpan() { return dtParser.getLastToken(); }
    public IToken getRightIToken() { return prsStream.getIToken(getRightSpan()); }

    public int getRhsErrorTokenIndex(int i)
    {
        int index = dtParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (err instanceof ErrorToken ? index : 0);
    }
    public ErrorToken getRhsErrorIToken(int i)
    {
        int index = dtParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (ErrorToken) (err instanceof ErrorToken ? err : null);
    }

    public void reset(ILexStream lexStream)
    {
        prsStream = new PrsStream(lexStream);
        dtParser.reset(prsStream);

        try
        {
            prsStream.remapTerminalSymbols(orderedTerminalSymbols(), prsTable.getEoftSymbol());
        }
        catch(NullExportedSymbolsException e) {
        }
        catch(NullTerminalSymbolsException e) {
        }
        catch(UnimplementedTerminalsException e)
        {
            if (unimplementedSymbolsWarning) {
                java.util.ArrayList unimplemented_symbols = e.getSymbols();
                System.out.println("The Lexer will not scan the following token(s):");
                for (int i = 0; i < unimplemented_symbols.size(); i++)
                {
                    Integer id = (Integer) unimplemented_symbols.get(i);
                    System.out.println("    " + SialParsersym.orderedTerminalSymbols[id.intValue()]);               
                }
                System.out.println();
            }
        }
        catch(UndefinedEofSymbolException e)
        {
            throw new Error(new UndefinedEofSymbolException
                                ("The Lexer does not implement the Eof symbol " +
                                 SialParsersym.orderedTerminalSymbols[prsTable.getEoftSymbol()]));
        }
    }
    
    public SialParser()
    {
        try
        {
            dtParser = new DeterministicParser(prsStream, prsTable, (RuleAction) this);
        }
        catch (NotDeterministicParseTableException e)
        {
            throw new Error(new NotDeterministicParseTableException
                                ("Regenerate SialParserprs.java with -NOBACKTRACK option"));
        }
        catch (BadParseSymFileException e)
        {
            throw new Error(new BadParseSymFileException("Bad Parser Symbol File -- SialParsersym.java. Regenerate SialParserprs.java"));
        }
    }

    public SialParser(ILexStream lexStream)
    {
        this();
        reset(lexStream);
    }

    public int numTokenKinds() { return SialParsersym.numTokenKinds; }
    public String[] orderedTerminalSymbols() { return SialParsersym.orderedTerminalSymbols; }
    public String getTokenKindName(int kind) { return SialParsersym.orderedTerminalSymbols[kind]; }            
    public int getEOFTokenKind() { return prsTable.getEoftSymbol(); }
    public IPrsStream getIPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getParseStream() { return prsStream; }

    public Object parser()
    {
        return parser(null, 0);
    }
        
    public Object parser(Monitor monitor)
    {
        return parser(monitor, 0);
    }
        
    public Object parser(int error_repair_count)
    {
        return parser(null, error_repair_count);
    }
        
    public Object parser(Monitor monitor, int error_repair_count)
    {
        dtParser.setMonitor(monitor);

        try
        {
            return (Object) dtParser.parse();
        }
        catch (BadParseException e)
        {
            prsStream.reset(e.error_token); // point to error token

            DiagnoseParser diagnoseParser = new DiagnoseParser(prsStream, prsTable);
            diagnoseParser.diagnose(e.error_token);
        }

        return null;
    }

    //
    // Additional entry points, if any
    //
    



private SymbolTable symbolTable;
public  SymbolTable getSymbolTable(){return symbolTable;}

public static final int WARNING_CODE = 14;  //value chosen to be different from the codes defined in lpg.runtime.ParseErrorCodes
public void resolve(ASTNode root) {
if (root != null) {

/* print the AST */
//    PrintVisitor printVisitor = new PrintVisitor();
//    root.accept(printVisitor);

/* populate the symbol table and perform type checking */
 assert root instanceof Sial: "called resolve with subAST";
    symbolTable = new SymbolTable((Sial)root);
    ( (Sial) root).setSymbolTable(symbolTable);
    TypeCheckVisitor  symbolTableVisitor = new TypeCheckVisitor(this);
    root.accept(symbolTableVisitor);
}
}


       private IPrsStream prs;
       private ILexStream lex;


        public void emitError(IToken id, String message)
        {   if (prs==null) prs=getIPrsStream();
            if (lex==null){lex = prs.getILexStream();}
            lex.getMessageHandler().handleMessage(ParseErrorCodes.NO_MESSAGE_CODE,
                                                  lex.getLocation(id.getStartOffset(),
                                                                  id.getEndOffset()
                                                                  ),
                                                  lex.getLocation(0, 0),
                                                  prs.getFileName(),
                                                  new String [] { message });
        }

       public void emitError(ASTNode node, String message)
       {if (prs==null) prs=getIPrsStream();
            if (lex==null){lex = prs.getILexStream();}
         lex.getMessageHandler().handleMessage(ParseErrorCodes.NO_MESSAGE_CODE,
                			          lex.getLocation(node.getLeftIToken().getStartOffset(),
                			                           node.getRightIToken().getEndOffset()
                			                          ),
                				  lex.getLocation(0, 0),
                				  prs.getFileName(),
                				  new String [] { node + "--- " + message });
        }

       public void emitError(int startOffset, int endOffset, String message)
       {  if (prs==null) prs=getIPrsStream();
            if (lex==null){lex = prs.getILexStream();}
            lex.getMessageHandler().handleMessage(
                ParseErrorCodes.NO_MESSAGE_CODE,
                lex.getLocation(startOffset, endOffset),
                lex.getLocation(0, 0),
                prs.getFileName(),
                new String [] { message });
        }

       public void emitWarning(IToken id, String message)
        {   if (prs==null) prs=getIPrsStream();
            if (lex==null){lex = prs.getILexStream();}
            lex.getMessageHandler().handleMessage(WARNING_CODE,
                                                  lex.getLocation(id.getStartOffset(),
                                                                  id.getEndOffset()
                                                                  ),
                                                  lex.getLocation(0, 0),
                                                  prs.getFileName(),
                                                  new String [] { message });
        }

       public void emitWarning(ASTNode node, String message)
       {if (prs==null) prs=getIPrsStream();
            if (lex==null){lex = prs.getILexStream();}
         lex.getMessageHandler().handleMessage(WARNING_CODE,
                			          lex.getLocation(node.getLeftIToken().getStartOffset(),
                			                           node.getRightIToken().getEndOffset()
                			                          ),
                				  lex.getLocation(0, 0),
                				  prs.getFileName(),
                				  new String [] { node + "--- " + message });
        }

       public void emitWarning(int startOffset, int endOffset, String message)
       {  if (prs==null) prs=getIPrsStream();
            if (lex==null){lex = prs.getILexStream();}
            lex.getMessageHandler().handleMessage(
                WARNING_CODE,
                lex.getLocation(startOffset, endOffset),
                lex.getLocation(0, 0),
                prs.getFileName(),
                new String [] { message });
        }



    public void ruleAction(int ruleNumber)
    {
        switch (ruleNumber)
        {

            //
            // Rule 1:  Sial ::= EOLsopt$ ImportProgList Program
            //
            case 1: {
                setResult(
                    new Sial(SialParser.this, getLeftIToken(), getRightIToken(),
                             (ImportProgList)getRhsSym(2),
                             (Program)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 2:  Program ::= sial$ Ident$startName EOLs$ DecList StatementList endsial$ Ident$endName EOLsopt$
            //
            case 2: {
                setResult(
                    new Program(SialParser.this, getLeftIToken(), getRightIToken(),
                                (Ident)getRhsSym(2),
                                (DecList)getRhsSym(4),
                                (StatementList)getRhsSym(5),
                                (Ident)getRhsSym(7))
                );
                break;
            }
            //
            // Rule 3:  EOLsopt ::= $Empty
            //
            case 3: {
                setResult(null);
                break;
            }
            //
            // Rule 4:  EOLsopt ::= EOLs$
            //
            case 4:
                break;
            //
            // Rule 5:  EOLs ::= SINGLE_LINE_COMMENT$
            //
            case 5: {
                setResult(null);
                break;
            }
            //
            // Rule 6:  EOLs ::= EOL$
            //
            case 6: {
                setResult(null);
                break;
            }
            //
            // Rule 7:  EOLs ::= EOLs$ EOL$
            //
            case 7: {
                setResult(null);
                break;
            }
            //
            // Rule 8:  ImportProgList ::= $Empty
            //
            case 8: {
                setResult(
                    new ImportProgList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 9:  ImportProgList ::= ImportProgList ImportProg
            //
            case 9: {
                ((ImportProgList)getRhsSym(1)).add((ImportProg)getRhsSym(2));
                break;
            }
            //
            // Rule 10:  ImportProg ::= import$ STRINGLIT EOLs$
            //
            case 10: {
                setResult(
                    new ImportProg(SialParser.this, getLeftIToken(), getRightIToken(),
                                   new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 11:  Modifiersopt ::= $Empty
            //
            case 11: {
                setResult(
                    new ModifierList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 12:  Modifiersopt ::= Modifiers
            //
            case 12:
                break;
            //
            // Rule 13:  Modifiers ::= Modifier
            //
            case 13: {
                setResult(
                    new ModifierList((Modifier)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 14:  Modifiers ::= Modifiers Modifier
            //
            case 14: {
                ((ModifierList)getRhsSym(1)).add((Modifier)getRhsSym(2));
                break;
            }
            //
            // Rule 15:  Modifier ::= predefined$modifier
            //
            case 15: {
                setResult(
                    new Modifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 16:  Modifier ::= contiguous$modifier
            //
            case 16: {
                setResult(
                    new Modifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 17:  Modifier ::= sparse$modifier
            //
            case 17: {
                setResult(
                    new Modifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 18:  DecList ::= $Empty
            //
            case 18: {
                setResult(
                    new DecList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 19:  DecList ::= DecList Dec EOLs$
            //
            case 19: {
                ((DecList)getRhsSym(1)).add((IDec)getRhsSym(2));
                break;
            }
            //
            // Rule 20:  Dec ::= ScalarDec
            //
            case 20:
                break;
            //
            // Rule 21:  Dec ::= ArrayDec
            //
            case 21:
                break;
            //
            // Rule 22:  Dec ::= IndexDec
            //
            case 22:
                break;
            //
            // Rule 23:  Dec ::= SubIndexDec
            //
            case 23:
                break;
            //
            // Rule 24:  Dec ::= IntDec
            //
            case 24:
                break;
            //
            // Rule 25:  Dec ::= ProcDec
            //
            case 25:
                break;
            //
            // Rule 26:  Dec ::= SpecialDec
            //
            case 26:
                break;
            //
            // Rule 27:  ScalarDec ::= Modifiersopt scalar$ Ident ScalarInitializationOpt
            //
            case 27: {
                setResult(
                    new ScalarDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                  (ModifierList)getRhsSym(1),
                                  (Ident)getRhsSym(3),
                                  (ScalarInitialValue)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 28:  ScalarInitializationOpt ::= $Empty
            //
            case 28: {
                setResult(
                    new ScalarInitialValue(getLeftIToken(), getRightIToken(),
                                           (ASTNodeToken)null,
                                           (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 29:  ScalarInitializationOpt ::= = DOUBLELIT
            //
            case 29: {
                setResult(
                    new ScalarInitialValue(getLeftIToken(), getRightIToken(),
                                           new ASTNodeToken(getRhsIToken(1)),
                                           new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 30:  IntDec ::= Modifiersopt int$ Ident IntInitializationOpt
            //
            case 30: {
                setResult(
                    new IntDec(SialParser.this, getLeftIToken(), getRightIToken(),
                               (ModifierList)getRhsSym(1),
                               (Ident)getRhsSym(3),
                               (IntInitialValue)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 31:  IntInitializationOpt ::= $Empty
            //
            case 31: {
                setResult(
                    new IntInitialValue(getLeftIToken(), getRightIToken(),
                                        (ASTNodeToken)null,
                                        (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 32:  IntInitializationOpt ::= = INTLIT
            //
            case 32: {
                setResult(
                    new IntInitialValue(getLeftIToken(), getRightIToken(),
                                        new ASTNodeToken(getRhsIToken(1)),
                                        new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 33:  ArrayDec ::= Modifiersopt ArrayKind Ident [$ DimensionList ]$
            //
            case 33: {
                setResult(
                    new ArrayDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                 (ModifierList)getRhsSym(1),
                                 (ArrayKind)getRhsSym(2),
                                 (Ident)getRhsSym(3),
                                 (DimensionList)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 34:  ArrayKind ::= static$akind
            //
            case 34: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 35:  ArrayKind ::= temp$akind
            //
            case 35: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 36:  ArrayKind ::= local$akind
            //
            case 36: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 37:  ArrayKind ::= distributed$akind
            //
            case 37: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 38:  ArrayKind ::= served$akind
            //
            case 38: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 39:  DimensionList ::= Dimension
            //
            case 39: {
                setResult(
                    new DimensionList((Ident)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 40:  DimensionList ::= DimensionList ,$ Dimension
            //
            case 40: {
                ((DimensionList)getRhsSym(1)).add((Ident)getRhsSym(3));
                break;
            }
            //
            // Rule 41:  Dimension ::= Ident
            //
            case 41:
                break;
            //
            // Rule 42:  IndexDec ::= Modifiersopt IndexKind Ident =$ Range
            //
            case 42: {
                setResult(
                    new IndexDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                 (ModifierList)getRhsSym(1),
                                 (IndexKind)getRhsSym(2),
                                 (Ident)getRhsSym(3),
                                 (Range)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 43:  IndexKind ::= aoindex$ikind
            //
            case 43: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 44:  IndexKind ::= moindex$ikind
            //
            case 44: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 45:  IndexKind ::= moaindex$ikind
            //
            case 45: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 46:  IndexKind ::= mobindex$ikind
            //
            case 46: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 47:  IndexKind ::= index$ikind
            //
            case 47: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 48:  IndexKind ::= laindex$ikind
            //
            case 48: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 49:  SubIndexDec ::= subindex$ Ident of$ Ident$ParentIdent
            //
            case 49: {
                setResult(
                    new SubIndexDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                    (Ident)getRhsSym(2),
                                    (Ident)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 50:  Range ::= RangeVal$RangeValStart :$ RangeVal$RangeValEnd
            //
            case 50: {
                setResult(
                    new Range(getLeftIToken(), getRightIToken(),
                              (IRangeVal)getRhsSym(1),
                              (IRangeVal)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 51:  RangeVal ::= INTLIT
            //
            case 51: {
                setResult(
                    new IntLitRangeVal(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 52:  RangeVal ::= -$ INTLIT
            //
            case 52: {
                setResult(
                    new NegRangeVal(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 53:  RangeVal ::= Ident
            //
            case 53: {
                //
                // When garbage collection is not available, delete getRhsSym(1)
                //
                setResult(
                    new IdentRangeVal(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 54:  ProcDec ::= proc$ Ident EOLs$ StatementList endproc Ident$endIdent
            //
            case 54: {
                setResult(
                    new ProcDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                (Ident)getRhsSym(2),
                                (StatementList)getRhsSym(4),
                                new ASTNodeToken(getRhsIToken(5)),
                                (Ident)getRhsSym(6))
                );
                break;
            }
            //
            // Rule 55:  SpecialDec ::= special$ Ident IdentOpt$Signature
            //
            case 55: {
                setResult(
                    new SpecialDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                   (Ident)getRhsSym(2),
                                   (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 56:  IdentOpt ::= Ident
            //
            case 56:
                break;
            //
            // Rule 57:  IdentOpt ::= $Empty
            //
            case 57: {
                setResult(null);
                break;
            }
            //
            // Rule 58:  StatementList ::= $Empty
            //
            case 58: {
                setResult(
                    new StatementList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 59:  StatementList ::= StatementList Statement EOLs$
            //
            case 59: {
                ((StatementList)getRhsSym(1)).add((IStatement)getRhsSym(2));
                break;
            }
            //
            // Rule 60:  WhereClause ::= where$ RelationalExpression
            //
            case 60: {
                setResult(
                    new WhereClause(getLeftIToken(), getRightIToken(),
                                    (RelationalExpression)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 61:  WhereClauseList ::= $Empty
            //
            case 61: {
                setResult(
                    new WhereClauseList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 62:  WhereClauseList ::= WhereClauseList WhereClause EOLs$
            //
            case 62: {
                ((WhereClauseList)getRhsSym(1)).add((WhereClause)getRhsSym(2));
                break;
            }
            //
            // Rule 63:  Statement ::= call$ Ident
            //
            case 63: {
                setResult(
                    new CallStatement(getLeftIToken(), getRightIToken(),
                                      (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 64:  Statement ::= return$
            //
            case 64: {
                setResult(
                    new ReturnStatement(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 65:  Statement ::= stop$
            //
            case 65: {
                setResult(
                    new StopStatement(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 66:  Statement ::= server_barrier$
            //
            case 66: {
                setResult(
                    new ServerBarrierStatement(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 67:  Statement ::= sip_barrier$
            //
            case 67: {
                setResult(
                    new SipBarrierStatement(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 68:  Statement ::= do$ Ident$StartIndex EOLs$ WhereClauseList StatementList enddo$ Ident$EndIndex
            //
            case 68: {
                setResult(
                    new DoStatement(getLeftIToken(), getRightIToken(),
                                    (Ident)getRhsSym(2),
                                    (WhereClauseList)getRhsSym(4),
                                    (StatementList)getRhsSym(5),
                                    (Ident)getRhsSym(7))
                );
                break;
            }
            //
            // Rule 69:  Statement ::= do$ Ident$StartIndex in$ Ident$StartParentIndex EOLs$ WhereClauseList StatementList enddo$ Ident$EndIndex in$ Ident$EndParentIndex
            //
            case 69: {
                setResult(
                    new DoStatementSubIndex(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2),
                                            (Ident)getRhsSym(4),
                                            (WhereClauseList)getRhsSym(6),
                                            (StatementList)getRhsSym(7),
                                            (Ident)getRhsSym(9),
                                            (Ident)getRhsSym(11))
                );
                break;
            }
            //
            // Rule 70:  Statement ::= pardo$ Indices$StartIndices PardoPragma$Pragma EOLs$ WhereClauseList StatementList endpardo$ Indices$EndIndices
            //
            case 70: {
                setResult(
                    new PardoStatement(getLeftIToken(), getRightIToken(),
                                       (IdentList)getRhsSym(2),
                                       (IPardoPragma)getRhsSym(3),
                                       (WhereClauseList)getRhsSym(5),
                                       (StatementList)getRhsSym(6),
                                       (IdentList)getRhsSym(8))
                );
                break;
            }
            //
            // Rule 71:  PardoPragma ::= $Empty
            //
            case 71: {
                setResult(
                    new PardoPragma(getLeftIToken(), getRightIToken(),
                                    (StringLiteral)null)
                );
                break;
            }
            //
            // Rule 72:  PardoPragma ::= StringLiteral
            //
            case 72: {
                setResult(
                    new PardoPragma(getLeftIToken(), getRightIToken(),
                                    (StringLiteral)getRhsSym(1))
                );
                break;
            }
            //
            // Rule 73:  Statement ::= section$ EOLs$ StatementList endsection$
            //
            case 73: {
                setResult(
                    new Section(getLeftIToken(), getRightIToken(),
                                (StatementList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 74:  Statement ::= exit$
            //
            case 74: {
                setResult(
                    new ExitStatement(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 75:  Statement ::= if$ RelationalExpression EOLs$ StatementList endif$
            //
            case 75: {
                setResult(
                    new IfStatement(getLeftIToken(), getRightIToken(),
                                    (RelationalExpression)getRhsSym(2),
                                    (StatementList)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 76:  Statement ::= if$ RelationalExpression EOLs$ StatementList$ifStatements else$ EOLs$ StatementList$elseStatements endif$
            //
            case 76: {
                setResult(
                    new IfElseStatement(getLeftIToken(), getRightIToken(),
                                        (RelationalExpression)getRhsSym(2),
                                        (StatementList)getRhsSym(4),
                                        (StatementList)getRhsSym(7))
                );
                break;
            }
            //
            // Rule 77:  Statement ::= allocate$ Ident AllocIndexListopt
            //
            case 77: {
                setResult(
                    new AllocateStatement(getLeftIToken(), getRightIToken(),
                                          (Ident)getRhsSym(2),
                                          (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 78:  Statement ::= deallocate$ Ident AllocIndexListopt
            //
            case 78: {
                setResult(
                    new DeallocateStatement(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2),
                                            (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 79:  AllocIndex ::= Ident
            //
            case 79: {
                //
                // When garbage collection is not available, delete getRhsSym(1)
                //
                setResult(
                    new AllocIndexIdent(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 80:  AllocIndex ::= *$
            //
            case 80: {
                setResult(
                    new AllocIndexWildCard(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 81:  AllocIndexList ::= AllocIndex
            //
            case 81: {
                setResult(
                    new AllocIndexList((IAllocIndex)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 82:  AllocIndexList ::= AllocIndexList ,$ AllocIndex
            //
            case 82: {
                ((AllocIndexList)getRhsSym(1)).add((IAllocIndex)getRhsSym(3));
                break;
            }
            //
            // Rule 83:  AllocIndexListopt ::= $Empty
            //
            case 83: {
                setResult(null);
                break;
            }
            //
            // Rule 84:  AllocIndexListopt ::= [ AllocIndexList ]
            //
            case 84: {
                setResult(
                    new AllocIndexListopt(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          (AllocIndexList)getRhsSym(2),
                                          new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 85:  Statement ::= allocate contiguous$ Ident [$ ContiguousIndexRangeExprList ]$
            //
            case 85: {
                setResult(
                    new ContiguousAllocateStatement(getLeftIToken(), getRightIToken(),
                                                    new ASTNodeToken(getRhsIToken(1)),
                                                    (Ident)getRhsSym(3),
                                                    (ContiguousIndexRangeExprList)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 86:  Statement ::= deallocate contiguous$ Ident [$ ContiguousIndexRangeExprList ]$
            //
            case 86: {
                setResult(
                    new ContiguousDeallocateStatement(getLeftIToken(), getRightIToken(),
                                                      new ASTNodeToken(getRhsIToken(1)),
                                                      (Ident)getRhsSym(3),
                                                      (ContiguousIndexRangeExprList)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 87:  ContiguousIndexRangeExpr ::= Expression$StartExpr : Expression$EndExpr
            //
            case 87: {
                setResult(
                    new ContiguousIndexRangeExpr(getLeftIToken(), getRightIToken(),
                                                 (IExpression)getRhsSym(1),
                                                 new ASTNodeToken(getRhsIToken(2)),
                                                 (IExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 88:  ContiguousIndexRangeExprList ::= ContiguousIndexRangeExpr
            //
            case 88: {
                setResult(
                    new ContiguousIndexRangeExprList((ContiguousIndexRangeExpr)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 89:  ContiguousIndexRangeExprList ::= ContiguousIndexRangeExprList ,$ ContiguousIndexRangeExpr
            //
            case 89: {
                ((ContiguousIndexRangeExprList)getRhsSym(1)).add((ContiguousIndexRangeExpr)getRhsSym(3));
                break;
            }
            //
            // Rule 90:  Statement ::= create$ Ident
            //
            case 90: {
                setResult(
                    new CreateStatement(getLeftIToken(), getRightIToken(),
                                        (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 91:  Statement ::= delete$ Ident
            //
            case 91: {
                setResult(
                    new DeleteStatement(getLeftIToken(), getRightIToken(),
                                        (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 92:  Statement ::= put$ DataBlock$LHSDataBlock AssignOp Expression$Expression
            //
            case 92: {
                setResult(
                    new PutStatement(getLeftIToken(), getRightIToken(),
                                     (DataBlock)getRhsSym(2),
                                     (AssignOp)getRhsSym(3),
                                     (IExpression)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 93:  Statement ::= get$ DataBlock
            //
            case 93: {
                setResult(
                    new GetStatement(getLeftIToken(), getRightIToken(),
                                     (DataBlock)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 94:  Statement ::= prepare$ DataBlock$LHSDataBlock AssignOp Expression$Expression
            //
            case 94: {
                setResult(
                    new PrepareStatement(getLeftIToken(), getRightIToken(),
                                         (DataBlock)getRhsSym(2),
                                         (AssignOp)getRhsSym(3),
                                         (IExpression)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 95:  Statement ::= request$ DataBlock
            //
            case 95: {
                setResult(
                    new RequestStatement(getLeftIToken(), getRightIToken(),
                                         (DataBlock)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 96:  Statement ::= collective$ Ident AssignOp Expression
            //
            case 96: {
                setResult(
                    new CollectiveStatement(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2),
                                            (AssignOp)getRhsSym(3),
                                            (IExpression)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 97:  Statement ::= destroy$ Ident
            //
            case 97: {
                setResult(
                    new DestroyStatement(getLeftIToken(), getRightIToken(),
                                         (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 98:  Statement ::= print$ Expression
            //
            case 98: {
                setResult(
                    new PrintStatement(getLeftIToken(), getRightIToken(),
                                       (IExpression)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 99:  Statement ::= println$ Expression
            //
            case 99: {
                setResult(
                    new PrintlnStatement(getLeftIToken(), getRightIToken(),
                                         (IExpression)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 100:  Arg ::= ContiguousDataBlock
            //
            case 100: {
                setResult(
                    new ContiguousDataBlockArg(getLeftIToken(), getRightIToken(),
                                               (ContiguousDataBlock)getRhsSym(1))
                );
                break;
            }
            //
            // Rule 101:  Arg ::= DataBlock
            //
            case 101: {
                setResult(
                    new DataBlockArg(getLeftIToken(), getRightIToken(),
                                     (DataBlock)getRhsSym(1))
                );
                break;
            }
            //
            // Rule 102:  Arg ::= IDENTIFIER
            //
            case 102: {
                setResult(
                    new IdentArg(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 103:  Arg ::= DOUBLELIT
            //
            case 103: {
                setResult(
                    new DoubleLitArg(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 104:  Arg ::= INTLIT
            //
            case 104: {
                setResult(
                    new IntLitArg(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 105:  ArgList ::= $Empty
            //
            case 105: {
                setResult(
                    new ArgList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 106:  ArgList ::= ArgList Arg
            //
            case 106: {
                ((ArgList)getRhsSym(1)).add((IArg)getRhsSym(2));
                break;
            }
            //
            // Rule 107:  Statement ::= execute$ Ident ArgList
            //
            case 107: {
                setResult(
                    new ExecuteStatement(getLeftIToken(), getRightIToken(),
                                         (Ident)getRhsSym(2),
                                         (ArgList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 108:  Statement ::= Ident AssignOp Expression
            //
            case 108: {
                setResult(
                    new AssignToIdent(SialParser.this, getLeftIToken(), getRightIToken(),
                                      (Ident)getRhsSym(1),
                                      (AssignOp)getRhsSym(2),
                                      (IExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 109:  Statement ::= DataBlock AssignOp Expression
            //
            case 109: {
                setResult(
                    new AssignToBlock(SialParser.this, getLeftIToken(), getRightIToken(),
                                      (DataBlock)getRhsSym(1),
                                      (AssignOp)getRhsSym(2),
                                      (IExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 110:  Statement ::= ContiguousDataBlock AssignOp Expression
            //
            case 110: {
                setResult(
                    new AssignToContigousDataBlock(getLeftIToken(), getRightIToken(),
                                                   (ContiguousDataBlock)getRhsSym(1),
                                                   (AssignOp)getRhsSym(2),
                                                   (IExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 111:  Statement ::= gpu_on$ EOLs$ StatementList gpu_off$
            //
            case 111: {
                setResult(
                    new GPUSection(getLeftIToken(), getRightIToken(),
                                   (StatementList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 112:  Statement ::= gpu_allocate$ Arg
            //
            case 112: {
                setResult(
                    new GPUAllocate(getLeftIToken(), getRightIToken(),
                                    (IArg)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 113:  Statement ::= gpu_free$ Arg
            //
            case 113: {
                setResult(
                    new GPUFree(getLeftIToken(), getRightIToken(),
                                (IArg)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 114:  Statement ::= gpu_put$ Arg
            //
            case 114: {
                setResult(
                    new GPUPut(getLeftIToken(), getRightIToken(),
                               (IArg)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 115:  Statement ::= gpu_get$ Arg
            //
            case 115: {
                setResult(
                    new GPUGet(getLeftIToken(), getRightIToken(),
                               (IArg)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 116:  Statement ::= set_persistent$ Ident StringLiteral
            //
            case 116: {
                setResult(
                    new SetPersistent(getLeftIToken(), getRightIToken(),
                                      (Ident)getRhsSym(2),
                                      (StringLiteral)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 117:  Statement ::= restore_persistent$ Ident StringLiteral
            //
            case 117: {
                setResult(
                    new RestorePersistent(getLeftIToken(), getRightIToken(),
                                          (Ident)getRhsSym(2),
                                          (StringLiteral)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 118:  Statement ::= assert_same$ Ident
            //
            case 118: {
                setResult(
                    new AssertSame(getLeftIToken(), getRightIToken(),
                                   (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 119:  Statement ::= broadcast_from$ Primary Ident
            //
            case 119: {
                setResult(
                    new BroadcastStatic(getLeftIToken(), getRightIToken(),
                                        (IPrimary)getRhsSym(2),
                                        (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 120:  AssignOp ::= =$op
            //
            case 120: {
                setResult(
                    new AssignOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 121:  AssignOp ::= +=$op
            //
            case 121: {
                setResult(
                    new AssignOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 122:  AssignOp ::= -=$op
            //
            case 122: {
                setResult(
                    new AssignOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 123:  AssignOp ::= *=$op
            //
            case 123: {
                setResult(
                    new AssignOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 124:  DataBlock ::= Ident [$ IndexCastIndices ]$
            //
            case 124: {
                setResult(
                    new DataBlock(getLeftIToken(), getRightIToken(),
                                  (Ident)getRhsSym(1),
                                  (IndexCastIdentList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 125:  Indices ::= Ident
            //
            case 125: {
                setResult(
                    new IdentList((Ident)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 126:  Indices ::= Indices ,$ Ident
            //
            case 126: {
                ((IdentList)getRhsSym(1)).add((Ident)getRhsSym(3));
                break;
            }
            //
            // Rule 127:  IndexCastIndices ::= IndexCastIdent
            //
            case 127: {
                setResult(
                    new IndexCastIdentList((IndexCastIdent)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 128:  IndexCastIndices ::= IndexCastIndices ,$ IndexCastIdent
            //
            case 128: {
                ((IndexCastIdentList)getRhsSym(1)).add((IndexCastIdent)getRhsSym(3));
                break;
            }
            //
            // Rule 129:  IndexCastIdent ::= IndexCastopt Ident
            //
            case 129: {
                setResult(
                    new IndexCastIdent(getLeftIToken(), getRightIToken(),
                                       (IndexCastopt)getRhsSym(1),
                                       (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 130:  IndexCastopt ::= $Empty
            //
            case 130: {
                setResult(null);
                break;
            }
            //
            // Rule 131:  IndexCastopt ::= ($ index$ )$IndexCast
            //
            case 131: {
                setResult(
                    new IndexCastopt(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 132:  ContiguousDataBlock ::= Ident [$ ContiguousIndexRangeExprList ]$
            //
            case 132: {
                setResult(
                    new ContiguousDataBlock(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(1),
                                            (ContiguousIndexRangeExprList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 133:  RelOp ::= <$op
            //
            case 133: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 134:  RelOp ::= >$op
            //
            case 134: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 135:  RelOp ::= <=$op
            //
            case 135: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 136:  RelOp ::= >=$op
            //
            case 136: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 137:  RelOp ::= ==$op
            //
            case 137: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 138:  RelOp ::= !=$op
            //
            case 138: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 139:  RelationalExpression ::= Expression$CastExpressionLeft RelOp Expression$CastExpressionRight
            //
            case 139: {
                setResult(
                    new RelationalExpression(getLeftIToken(), getRightIToken(),
                                             (IExpression)getRhsSym(1),
                                             (RelOp)getRhsSym(2),
                                             (IExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 140:  Expression ::= Term
            //
            case 140:
                break;
            //
            // Rule 141:  Expression ::= Expression + Term
            //
            case 141: {
                setResult(
                    new AddExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                (IExpression)getRhsSym(1),
                                new ASTNodeToken(getRhsIToken(2)),
                                (ITerm)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 142:  Expression ::= Expression - Term
            //
            case 142: {
                setResult(
                    new SubtractExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                     (IExpression)getRhsSym(1),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (ITerm)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 143:  Term ::= ExponentExpression
            //
            case 143:
                break;
            //
            // Rule 144:  Term ::= Term *$ ExponentExpression
            //
            case 144: {
                setResult(
                    new StarExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                 (ITerm)getRhsSym(1),
                                 (IExponentExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 145:  Term ::= Term /$ ExponentExpression
            //
            case 145: {
                setResult(
                    new DivExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                (ITerm)getRhsSym(1),
                                (IExponentExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 146:  Term ::= Term ^ ExponentExpression
            //
            case 146: {
                setResult(
                    new TensorExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                   (ITerm)getRhsSym(1),
                                   new ASTNodeToken(getRhsIToken(2)),
                                   (IExponentExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 147:  ExponentExpression ::= CastExpression
            //
            case 147:
                break;
            //
            // Rule 148:  ExponentExpression ::= ExponentExpression ** CastExpression
            //
            case 148: {
                setResult(
                    new ExponentExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                     (IExponentExpression)getRhsSym(1),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (ICastExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 149:  CastExpression ::= UnaryExpression
            //
            case 149:
                break;
            //
            // Rule 150:  CastExpression ::= ($ int$ )$ CastExpression
            //
            case 150: {
                setResult(
                    new IntCastExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                    (ICastExpression)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 151:  CastExpression ::= ($ scalar$ )$ CastExpression
            //
            case 151: {
                setResult(
                    new ScalarCastExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                       (ICastExpression)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 152:  CastExpression ::= ($ index$ )$ CastExpression
            //
            case 152: {
                setResult(
                    new IndexCastExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                      (ICastExpression)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 153:  UnaryExpression ::= Primary
            //
            case 153:
                break;
            //
            // Rule 154:  UnaryExpression ::= -$ Primary
            //
            case 154: {
                setResult(
                    new NegatedUnaryExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                         (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 155:  UnaryExpression ::= sqrt$ Primary
            //
            case 155: {
                setResult(
                    new SqrtUnaryExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                      (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 156:  Primary ::= ($ Expression )$
            //
            case 156: {
                setResult(
                    new ParenExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                  (IExpression)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 157:  Primary ::= INTLIT
            //
            case 157: {
                setResult(
                    new IntLitExpr(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 158:  Primary ::= DOUBLELIT
            //
            case 158: {
                setResult(
                    new DoubleLitExpr(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 159:  Primary ::= Ident
            //
            case 159: {
                //
                // When garbage collection is not available, delete getRhsSym(1)
                //
                setResult(
                    new IdentExpr(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 160:  Primary ::= DataBlock
            //
            case 160: {
                setResult(
                    new DataBlockExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                      (DataBlock)getRhsSym(1))
                );
                break;
            }
            //
            // Rule 161:  Primary ::= ContiguousDataBlock
            //
            case 161: {
                setResult(
                    new ContiguousDataBlockExpr(SialParser.this, getLeftIToken(), getRightIToken(),
                                                (ContiguousDataBlock)getRhsSym(1))
                );
                break;
            }
            //
            // Rule 162:  Primary ::= StringLiteral
            //
            case 162: {
                //
                // When garbage collection is not available, delete getRhsSym(1)
                //
                setResult(
                    new StringLitExpr(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 163:  StringLiteral ::= STRINGLIT
            //
            case 163: {
                setResult(
                    new StringLiteral(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 164:  Ident ::= IDENTIFIER
            //
            case 164: {
                setResult(
                    new Ident(SialParser.this, getRhsIToken(1))
                );
                break;
            }
    
            default:
                break;
        }
        return;
    }
}

