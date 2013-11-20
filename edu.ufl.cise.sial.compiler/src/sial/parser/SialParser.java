package sial.parser;

import sial.parser.Ast.*;
import lpg.runtime.*;
import org.eclipse.imp.parser.IParser;
  import sial.parser.context.*;
  import java.util.Date;
  import java.util.ArrayList;
  import java.util.List;

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
                    new ModifierList((IModifier)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 14:  Modifiers ::= Modifiers Modifier
            //
            case 14: {
                ((ModifierList)getRhsSym(1)).add((IModifier)getRhsSym(2));
                break;
            }
            //
            // Rule 15:  Modifier ::= sip_consistent
            //
            case 15: {
                setResult(
                    new Sip_ConsistentModifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 16:  Modifier ::= predefined
            //
            case 16: {
                setResult(
                    new PredefinedModifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 17:  Modifier ::= persistent
            //
            case 17: {
                setResult(
                    new PersistentModifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 18:  Modifier ::= scoped_extent
            //
            case 18: {
                setResult(
                    new ScopedExtentModifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 19:  Modifier ::= contiguous
            //
            case 19: {
                setResult(
                    new ContiguousModifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 20:  Modifier ::= auto_allocate
            //
            case 20: {
                setResult(
                    new Auto_AllocateModifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 21:  DecList ::= $Empty
            //
            case 21: {
                setResult(
                    new DecList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 22:  DecList ::= DecList Dec EOLs$
            //
            case 22: {
                ((DecList)getRhsSym(1)).add((IDec)getRhsSym(2));
                break;
            }
            //
            // Rule 23:  Dec ::= ScalarDec
            //
            case 23:
                break;
            //
            // Rule 24:  Dec ::= ArrayDec
            //
            case 24:
                break;
            //
            // Rule 25:  Dec ::= IndexDec
            //
            case 25:
                break;
            //
            // Rule 26:  Dec ::= SubIndexDec
            //
            case 26:
                break;
            //
            // Rule 27:  Dec ::= IntDec
            //
            case 27:
                break;
            //
            // Rule 28:  Dec ::= ProcDec
            //
            case 28:
                break;
            //
            // Rule 29:  Dec ::= SpecialDec
            //
            case 29:
                break;
            //
            // Rule 30:  ScalarDec ::= Modifiersopt scalar$ Ident
            //
            case 30: {
                setResult(
                    new ScalarDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                  (ModifierList)getRhsSym(1),
                                  (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 31:  IntDec ::= Modifiersopt int$ Ident
            //
            case 31: {
                setResult(
                    new IntDec(SialParser.this, getLeftIToken(), getRightIToken(),
                               (ModifierList)getRhsSym(1),
                               (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 32:  ArrayDec ::= Modifiersopt ArrayKind Ident ($ DimensionList )$
            //
            case 32: {
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
            // Rule 33:  ArrayKind ::= static$akind
            //
            case 33: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 34:  ArrayKind ::= temp$akind
            //
            case 34: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 35:  ArrayKind ::= local$akind
            //
            case 35: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 36:  ArrayKind ::= distributed$akind
            //
            case 36: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 37:  ArrayKind ::= served$akind
            //
            case 37: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 38:  DimensionList ::= Dimension
            //
            case 38: {
                setResult(
                    new DimensionList((Ident)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 39:  DimensionList ::= DimensionList ,$ Dimension
            //
            case 39: {
                ((DimensionList)getRhsSym(1)).add((Ident)getRhsSym(3));
                break;
            }
            //
            // Rule 40:  Dimension ::= Ident
            //
            case 40:
                break;
            //
            // Rule 41:  IndexDec ::= Modifiersopt IndexKind Ident =$ Range
            //
            case 41: {
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
            // Rule 42:  IndexKind ::= aoindex$ikind
            //
            case 42: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 43:  IndexKind ::= moindex$ikind
            //
            case 43: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 44:  IndexKind ::= moaindex$ikind
            //
            case 44: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 45:  IndexKind ::= mobindex$ikind
            //
            case 45: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 46:  IndexKind ::= index$ikind
            //
            case 46: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 47:  IndexKind ::= laindex$ikind
            //
            case 47: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 48:  SubIndexDec ::= subindex$ Ident of$ Ident$ParentIdent
            //
            case 48: {
                setResult(
                    new SubIndexDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                    (Ident)getRhsSym(2),
                                    (Ident)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 49:  Range ::= RangeVal$RangeValStart ,$ RangeVal$RangeValEnd
            //
            case 49: {
                setResult(
                    new Range(getLeftIToken(), getRightIToken(),
                              (IRangeVal)getRhsSym(1),
                              (IRangeVal)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 50:  RangeVal ::= INTLIT
            //
            case 50: {
                setResult(
                    new IntLitRangeVal(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 51:  RangeVal ::= Ident
            //
            case 51: {
                //
                // When garbage collection is not available, delete getRhsSym(1)
                //
                setResult(
                    new IdentRangeVal(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 52:  ProcDec ::= proc$ Ident EOLs$ StatementList endproc Ident$endIdent
            //
            case 52: {
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
            // Rule 53:  SpecialDec ::= special$ Ident IdentOpt$Signature
            //
            case 53: {
                setResult(
                    new SpecialDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                   (Ident)getRhsSym(2),
                                   (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 54:  IdentOpt ::= Ident
            //
            case 54:
                break;
            //
            // Rule 55:  IdentOpt ::= $Empty
            //
            case 55: {
                setResult(null);
                break;
            }
            //
            // Rule 56:  StatementList ::= $Empty
            //
            case 56: {
                setResult(
                    new StatementList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 57:  StatementList ::= StatementList Statement EOLs$
            //
            case 57: {
                ((StatementList)getRhsSym(1)).add((IStatement)getRhsSym(2));
                break;
            }
            //
            // Rule 58:  WhereClause ::= where$ RelationalExpression
            //
            case 58: {
                setResult(
                    new WhereClause(getLeftIToken(), getRightIToken(),
                                    (RelationalExpression)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 59:  WhereClauseList ::= $Empty
            //
            case 59: {
                setResult(null);
                break;
            }
            //
            // Rule 60:  WhereClauseList ::= WhereClauseList WhereClause EOLs$
            //
            case 60: {
                setResult(
                    new WhereClauseList(getLeftIToken(), getRightIToken(),
                                        (WhereClauseList)getRhsSym(1),
                                        (WhereClause)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 61:  Statement ::= call$ Ident
            //
            case 61: {
                setResult(
                    new CallStatement(getLeftIToken(), getRightIToken(),
                                      (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 62:  Statement ::= return$
            //
            case 62: {
                setResult(
                    new ReturnStatement(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 63:  Statement ::= server_barrier$ IdentOpt
            //
            case 63: {
                setResult(
                    new ServerBarrierStatement(SialParser.this, getLeftIToken(), getRightIToken(),
                                               (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 64:  Statement ::= sip_barrier$ IdentOpt
            //
            case 64: {
                setResult(
                    new SipBarrierStatement(SialParser.this, getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 65:  Statement ::= do$ Ident$StartIndex EOLs$ WhereClauseList StatementList enddo$ Ident$EndIndex
            //
            case 65: {
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
            // Rule 66:  Statement ::= do$ Ident$StartIndex in$ Ident$StartParentIndex EOLs$ WhereClauseList StatementList enddo$ Ident$EndIndex in$ Ident$EndParentIndex
            //
            case 66: {
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
            // Rule 67:  Statement ::= pardo$ Indices$StartIndices EOLs$ WhereClauseList StatementList endpardo$ Indices$EndIndices
            //
            case 67: {
                setResult(
                    new PardoStatement(getLeftIToken(), getRightIToken(),
                                       (IdentList)getRhsSym(2),
                                       (WhereClauseList)getRhsSym(4),
                                       (StatementList)getRhsSym(5),
                                       (IdentList)getRhsSym(7))
                );
                break;
            }
            //
            // Rule 68:  Statement ::= section$ EOLs$ StatementList endsection$
            //
            case 68: {
                setResult(
                    new Section(SialParser.this, getLeftIToken(), getRightIToken(),
                                (StatementList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 69:  Statement ::= exit$
            //
            case 69: {
                setResult(
                    new ExitStatement(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 70:  Statement ::= cycle$ Ident
            //
            case 70: {
                setResult(
                    new CycleStatement(getLeftIToken(), getRightIToken(),
                                       (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 71:  Statement ::= if$ RelationalExpression EOLs$ StatementList endif$
            //
            case 71: {
                setResult(
                    new IfStatement(getLeftIToken(), getRightIToken(),
                                    (RelationalExpression)getRhsSym(2),
                                    (StatementList)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 72:  Statement ::= if$ RelationalExpression EOLs$ StatementList$ifStatements else$ EOLs$ StatementList$elseStatements endif$
            //
            case 72: {
                setResult(
                    new IfElseStatement(getLeftIToken(), getRightIToken(),
                                        (RelationalExpression)getRhsSym(2),
                                        (StatementList)getRhsSym(4),
                                        (StatementList)getRhsSym(7))
                );
                break;
            }
            //
            // Rule 73:  Statement ::= allocate$ Ident AllocIndexListopt
            //
            case 73: {
                setResult(
                    new AllocateStatement(getLeftIToken(), getRightIToken(),
                                          (Ident)getRhsSym(2),
                                          (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 74:  Statement ::= deallocate$ Ident AllocIndexListopt
            //
            case 74: {
                setResult(
                    new DeallocateStatement(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2),
                                            (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 75:  Statement ::= create$ Ident AllocIndexListopt
            //
            case 75: {
                setResult(
                    new CreateStatement(getLeftIToken(), getRightIToken(),
                                        (Ident)getRhsSym(2),
                                        (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 76:  Statement ::= delete$ Ident AllocIndexListopt
            //
            case 76: {
                setResult(
                    new DeleteStatement(getLeftIToken(), getRightIToken(),
                                        (Ident)getRhsSym(2),
                                        (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 77:  Statement ::= put$ DataBlock$LHSDataBlock AssignOp DataBlock$RHSDataBlock
            //
            case 77: {
                setResult(
                    new PutStatement(getLeftIToken(), getRightIToken(),
                                     (DataBlock)getRhsSym(2),
                                     (IAssignOp)getRhsSym(3),
                                     (DataBlock)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 78:  Statement ::= get$ DataBlock
            //
            case 78: {
                setResult(
                    new GetStatement(getLeftIToken(), getRightIToken(),
                                     (DataBlock)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 79:  Statement ::= prepare$ DataBlock$LHSDataBlock AssignOp DataBlock$RHSDataBlock
            //
            case 79: {
                setResult(
                    new PrepareStatement(getLeftIToken(), getRightIToken(),
                                         (DataBlock)getRhsSym(2),
                                         (IAssignOp)getRhsSym(3),
                                         (DataBlock)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 80:  Statement ::= request$ DataBlock Ident
            //
            case 80: {
                setResult(
                    new RequestStatement(getLeftIToken(), getRightIToken(),
                                         (DataBlock)getRhsSym(2),
                                         (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 81:  Statement ::= prequest$ DataBlock$LHSDataBlock =$ DataBlock$RHSDataBlock
            //
            case 81: {
                setResult(
                    new PrequestStatement(getLeftIToken(), getRightIToken(),
                                          (DataBlock)getRhsSym(2),
                                          (DataBlock)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 82:  Statement ::= collective$ Ident$LHSIdent AssignOp Ident$RHSIdent
            //
            case 82: {
                setResult(
                    new CollectiveStatement(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2),
                                            (IAssignOp)getRhsSym(3),
                                            (Ident)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 83:  Statement ::= destroy$ Ident
            //
            case 83: {
                setResult(
                    new DestroyStatement(getLeftIToken(), getRightIToken(),
                                         (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 84:  Statement ::= println$ STRINGLIT
            //
            case 84: {
                setResult(
                    new PrintlnStatement(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 85:  Statement ::= print$ STRINGLIT
            //
            case 85: {
                setResult(
                    new PrintStatement(getLeftIToken(), getRightIToken(),
                                       new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 86:  Statement ::= print_index$ Ident
            //
            case 86: {
                setResult(
                    new PrintIndexStatement(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 87:  Statement ::= print_scalar$ Ident
            //
            case 87: {
                setResult(
                    new PrintScalarStatement(getLeftIToken(), getRightIToken(),
                                             (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 88:  Arg ::= Primary
            //
            case 88:
                break;
            //
            // Rule 89:  ArgList ::= $Empty
            //
            case 89: {
                setResult(
                    new ArgList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 90:  ArgList ::= ArgList Arg
            //
            case 90: {
                ((ArgList)getRhsSym(1)).add((IArg)getRhsSym(2));
                break;
            }
            //
            // Rule 91:  Statement ::= execute$ Ident ArgList
            //
            case 91: {
                setResult(
                    new ExecuteStatement(getLeftIToken(), getRightIToken(),
                                         (Ident)getRhsSym(2),
                                         (ArgList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 92:  Statement ::= ScalarOrBlockVar AssignOp Expression
            //
            case 92: {
                setResult(
                    new AssignStatement(SialParser.this, getLeftIToken(), getRightIToken(),
                                        (IScalarOrBlockVar)getRhsSym(1),
                                        (IAssignOp)getRhsSym(2),
                                        (IExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 93:  Statement ::= gpu_on$ EOLs$ StatementList gpu_off$
            //
            case 93: {
                setResult(
                    new GpuStatement(getLeftIToken(), getRightIToken(),
                                     (StatementList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 94:  Statement ::= gpu_allocate$ Primary
            //
            case 94: {
                setResult(
                    new GpuAllocate(getLeftIToken(), getRightIToken(),
                                    (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 95:  Statement ::= gpu_free$ Primary
            //
            case 95: {
                setResult(
                    new GpuFree(getLeftIToken(), getRightIToken(),
                                (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 96:  Statement ::= gpu_put$ Primary
            //
            case 96: {
                setResult(
                    new GpuPut(getLeftIToken(), getRightIToken(),
                               (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 97:  Statement ::= gpu_get$ Primary
            //
            case 97: {
                setResult(
                    new GpuGet(getLeftIToken(), getRightIToken(),
                               (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 98:  AssignOp ::= =
            //
            case 98: {
                setResult(
                    new AssignOpEqual(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 99:  AssignOp ::= +=
            //
            case 99: {
                setResult(
                    new AssignOpPlus(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 100:  AssignOp ::= -=
            //
            case 100: {
                setResult(
                    new AssignOpMinus(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 101:  AssignOp ::= *=
            //
            case 101: {
                setResult(
                    new AssignOpStar(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 102:  ScalarOrBlockVar ::= Ident
            //
            case 102:
                break;
            //
            // Rule 103:  ScalarOrBlockVar ::= DataBlock
            //
            case 103:
                break;
            //
            // Rule 104:  DataBlock ::= Ident ($ Indices )$
            //
            case 104: {
                setResult(
                    new DataBlock(getLeftIToken(), getRightIToken(),
                                  (Ident)getRhsSym(1),
                                  (IdentList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 105:  Indices ::= Ident
            //
            case 105: {
                setResult(
                    new IdentList((Ident)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 106:  Indices ::= Indices ,$ Ident
            //
            case 106: {
                ((IdentList)getRhsSym(1)).add((Ident)getRhsSym(3));
                break;
            }
            //
            // Rule 107:  AllocIndex ::= Ident
            //
            case 107: {
                //
                // When garbage collection is not available, delete getRhsSym(1)
                //
                setResult(
                    new AllocIndexIdent(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 108:  AllocIndex ::= *$
            //
            case 108: {
                setResult(
                    new AllocIndexWildCard(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 109:  AllocIndexList ::= AllocIndex
            //
            case 109: {
                setResult(
                    new AllocIndexList((IAllocIndex)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 110:  AllocIndexList ::= AllocIndexList ,$ AllocIndex
            //
            case 110: {
                ((AllocIndexList)getRhsSym(1)).add((IAllocIndex)getRhsSym(3));
                break;
            }
            //
            // Rule 111:  AllocIndexListopt ::= $Empty
            //
            case 111: {
                setResult(null);
                break;
            }
            //
            // Rule 112:  AllocIndexListopt ::= ( AllocIndexList )
            //
            case 112: {
                setResult(
                    new AllocIndexListopt(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          (AllocIndexList)getRhsSym(2),
                                          new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 113:  RelationalExpression ::= UnaryExpression$UnaryExpressionLeft RelOp UnaryExpression$UnaryExpressionRight
            //
            case 113: {
                setResult(
                    new RelationalExpression(getLeftIToken(), getRightIToken(),
                                             (IUnaryExpression)getRhsSym(1),
                                             (RelOp)getRhsSym(2),
                                             (IUnaryExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 114:  RelOp ::= <$op
            //
            case 114: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 115:  RelOp ::= >$op
            //
            case 115: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 116:  RelOp ::= <=$op
            //
            case 116: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 117:  RelOp ::= >=$op
            //
            case 117: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 118:  RelOp ::= ==$op
            //
            case 118: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 119:  RelOp ::= !=$op
            //
            case 119: {
                setResult(
                    new RelOp(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 120:  Expression ::= UnaryExpression$UnaryExpression
            //
            case 120:
                break;
            //
            // Rule 121:  Expression ::= BinaryExpression$BinaryExpression
            //
            case 121:
                break;
            //
            // Rule 122:  BinaryExpression ::= UnaryExpression$Expr1 BinOp UnaryExpression$Expr2
            //
            case 122: {
                setResult(
                    new BinaryExpression(getLeftIToken(), getRightIToken(),
                                         (IUnaryExpression)getRhsSym(1),
                                         (IBinOp)getRhsSym(2),
                                         (IUnaryExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 123:  BinOp ::= *
            //
            case 123: {
                setResult(
                    new BinOpStar(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 124:  BinOp ::= /
            //
            case 124: {
                setResult(
                    new BinOpDiv(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 125:  BinOp ::= +
            //
            case 125: {
                setResult(
                    new BinOpPlus(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 126:  BinOp ::= -
            //
            case 126: {
                setResult(
                    new BinOpMinus(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 127:  BinOp ::= ^
            //
            case 127: {
                setResult(
                    new BinOpTensor(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 128:  UnaryExpression ::= Primary
            //
            case 128:
                break;
            //
            // Rule 129:  UnaryExpression ::= -$ Primary
            //
            case 129: {
                setResult(
                    new NegatedUnary(getLeftIToken(), getRightIToken(),
                                     (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 130:  Primary ::= INTLIT
            //
            case 130: {
                setResult(
                    new IntLitPrimary(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 131:  Primary ::= DOUBLELIT
            //
            case 131: {
                setResult(
                    new DoubleLitPrimary(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 132:  Primary ::= Ident
            //
            case 132: {
                //
                // When garbage collection is not available, delete getRhsSym(1)
                //
                setResult(
                    new IdentPrimary(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 133:  Primary ::= DataBlock
            //
            case 133: {
                setResult(
                    new DataBlockPrimary(getLeftIToken(), getRightIToken(),
                                         (DataBlock)getRhsSym(1))
                );
                break;
            }
            //
            // Rule 134:  Primary ::= StringLiteral
            //
            case 134: {
                //
                // When garbage collection is not available, delete getRhsSym(1)
                //
                setResult(
                    new StringLitPrimary(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 135:  StringLiteral ::= STRINGLIT
            //
            case 135: {
                setResult(
                    new StringLiteral(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 136:  Ident ::= IDENTIFIER
            //
            case 136: {
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

