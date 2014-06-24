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
            // Rule 15:  Modifier ::= predefined
            //
            case 15: {
                setResult(
                    new PredefinedModifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 16:  Modifier ::= contiguous
            //
            case 16: {
                setResult(
                    new ContiguousModifier(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 17:  Modifier ::= sparse
            //
            case 17: {
                setResult(
                    new SparseModifier(getRhsIToken(1))
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
            // Rule 27:  ScalarDec ::= Modifiersopt scalar$ Ident
            //
            case 27: {
                setResult(
                    new ScalarDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                  (ModifierList)getRhsSym(1),
                                  (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 28:  IntDec ::= Modifiersopt int$ Ident
            //
            case 28: {
                setResult(
                    new IntDec(SialParser.this, getLeftIToken(), getRightIToken(),
                               (ModifierList)getRhsSym(1),
                               (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 29:  ArrayDec ::= Modifiersopt ArrayKind Ident ($ DimensionList )$
            //
            case 29: {
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
            // Rule 30:  ArrayKind ::= static$akind
            //
            case 30: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 31:  ArrayKind ::= temp$akind
            //
            case 31: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 32:  ArrayKind ::= local$akind
            //
            case 32: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 33:  ArrayKind ::= distributed$akind
            //
            case 33: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 34:  ArrayKind ::= served$akind
            //
            case 34: {
                setResult(
                    new ArrayKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 35:  DimensionList ::= Dimension
            //
            case 35: {
                setResult(
                    new DimensionList((Ident)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 36:  DimensionList ::= DimensionList ,$ Dimension
            //
            case 36: {
                ((DimensionList)getRhsSym(1)).add((Ident)getRhsSym(3));
                break;
            }
            //
            // Rule 37:  Dimension ::= Ident
            //
            case 37:
                break;
            //
            // Rule 38:  IndexDec ::= Modifiersopt IndexKind Ident =$ Range
            //
            case 38: {
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
            // Rule 39:  IndexKind ::= aoindex$ikind
            //
            case 39: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 40:  IndexKind ::= moindex$ikind
            //
            case 40: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 41:  IndexKind ::= moaindex$ikind
            //
            case 41: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 42:  IndexKind ::= mobindex$ikind
            //
            case 42: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 43:  IndexKind ::= index$ikind
            //
            case 43: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 44:  IndexKind ::= laindex$ikind
            //
            case 44: {
                setResult(
                    new IndexKind(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 45:  SubIndexDec ::= subindex$ Ident of$ Ident$ParentIdent
            //
            case 45: {
                setResult(
                    new SubIndexDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                    (Ident)getRhsSym(2),
                                    (Ident)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 46:  Range ::= RangeVal$RangeValStart ,$ RangeVal$RangeValEnd
            //
            case 46: {
                setResult(
                    new Range(getLeftIToken(), getRightIToken(),
                              (IRangeVal)getRhsSym(1),
                              (IRangeVal)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 47:  RangeVal ::= INTLIT
            //
            case 47: {
                setResult(
                    new IntLitRangeVal(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 48:  RangeVal ::= -$ INTLIT
            //
            case 48: {
                setResult(
                    new NegRangeVal(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 49:  RangeVal ::= Ident
            //
            case 49: {
                //
                // When garbage collection is not available, delete getRhsSym(1)
                //
                setResult(
                    new IdentRangeVal(SialParser.this, getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 50:  ProcDec ::= proc$ Ident EOLs$ StatementList endproc Ident$endIdent
            //
            case 50: {
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
            // Rule 51:  SpecialDec ::= special$ Ident IdentOpt$Signature
            //
            case 51: {
                setResult(
                    new SpecialDec(SialParser.this, getLeftIToken(), getRightIToken(),
                                   (Ident)getRhsSym(2),
                                   (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 52:  IdentOpt ::= Ident
            //
            case 52:
                break;
            //
            // Rule 53:  IdentOpt ::= $Empty
            //
            case 53: {
                setResult(null);
                break;
            }
            //
            // Rule 54:  StatementList ::= $Empty
            //
            case 54: {
                setResult(
                    new StatementList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 55:  StatementList ::= StatementList Statement EOLs$
            //
            case 55: {
                ((StatementList)getRhsSym(1)).add((IStatement)getRhsSym(2));
                break;
            }
            //
            // Rule 56:  WhereClause ::= where$ RelationalExpression
            //
            case 56: {
                setResult(
                    new WhereClause(getLeftIToken(), getRightIToken(),
                                    (RelationalExpression)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 57:  WhereClauseList ::= $Empty
            //
            case 57: {
                setResult(null);
                break;
            }
            //
            // Rule 58:  WhereClauseList ::= WhereClauseList WhereClause EOLs$
            //
            case 58: {
                setResult(
                    new WhereClauseList(getLeftIToken(), getRightIToken(),
                                        (WhereClauseList)getRhsSym(1),
                                        (WhereClause)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 59:  Statement ::= call$ Ident
            //
            case 59: {
                setResult(
                    new CallStatement(getLeftIToken(), getRightIToken(),
                                      (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 60:  Statement ::= return$
            //
            case 60: {
                setResult(
                    new ReturnStatement(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 61:  Statement ::= server_barrier$ IdentOpt
            //
            case 61: {
                setResult(
                    new ServerBarrierStatement(SialParser.this, getLeftIToken(), getRightIToken(),
                                               (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 62:  Statement ::= sip_barrier$ IdentOpt
            //
            case 62: {
                setResult(
                    new SipBarrierStatement(SialParser.this, getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 63:  Statement ::= do$ Ident$StartIndex EOLs$ WhereClauseList StatementList enddo$ Ident$EndIndex
            //
            case 63: {
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
            // Rule 64:  Statement ::= do$ Ident$StartIndex in$ Ident$StartParentIndex EOLs$ WhereClauseList StatementList enddo$ Ident$EndIndex in$ Ident$EndParentIndex
            //
            case 64: {
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
            // Rule 65:  Statement ::= pardo$ Indices$StartIndices EOLs$ WhereClauseList StatementList endpardo$ Indices$EndIndices
            //
            case 65: {
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
            // Rule 66:  Statement ::= section$ EOLs$ StatementList endsection$
            //
            case 66: {
                setResult(
                    new Section(SialParser.this, getLeftIToken(), getRightIToken(),
                                (StatementList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 67:  Statement ::= exit$
            //
            case 67: {
                setResult(
                    new ExitStatement(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 68:  Statement ::= cycle$ Ident
            //
            case 68: {
                setResult(
                    new CycleStatement(getLeftIToken(), getRightIToken(),
                                       (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 69:  Statement ::= if$ RelationalExpression EOLs$ StatementList endif$
            //
            case 69: {
                setResult(
                    new IfStatement(getLeftIToken(), getRightIToken(),
                                    (RelationalExpression)getRhsSym(2),
                                    (StatementList)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 70:  Statement ::= if$ RelationalExpression EOLs$ StatementList$ifStatements else$ EOLs$ StatementList$elseStatements endif$
            //
            case 70: {
                setResult(
                    new IfElseStatement(getLeftIToken(), getRightIToken(),
                                        (RelationalExpression)getRhsSym(2),
                                        (StatementList)getRhsSym(4),
                                        (StatementList)getRhsSym(7))
                );
                break;
            }
            //
            // Rule 71:  Statement ::= allocate$ Ident AllocIndexListopt
            //
            case 71: {
                setResult(
                    new AllocateStatement(getLeftIToken(), getRightIToken(),
                                          (Ident)getRhsSym(2),
                                          (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 72:  Statement ::= deallocate$ Ident AllocIndexListopt
            //
            case 72: {
                setResult(
                    new DeallocateStatement(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2),
                                            (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 73:  Statement ::= create$ Ident AllocIndexListopt
            //
            case 73: {
                setResult(
                    new CreateStatement(getLeftIToken(), getRightIToken(),
                                        (Ident)getRhsSym(2),
                                        (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 74:  Statement ::= delete$ Ident AllocIndexListopt
            //
            case 74: {
                setResult(
                    new DeleteStatement(getLeftIToken(), getRightIToken(),
                                        (Ident)getRhsSym(2),
                                        (AllocIndexListopt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 75:  Statement ::= put$ DataBlock$LHSDataBlock AssignOp DataBlock$RHSDataBlock
            //
            case 75: {
                setResult(
                    new PutStatement(getLeftIToken(), getRightIToken(),
                                     (DataBlock)getRhsSym(2),
                                     (IAssignOp)getRhsSym(3),
                                     (DataBlock)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 76:  Statement ::= get$ DataBlock
            //
            case 76: {
                setResult(
                    new GetStatement(getLeftIToken(), getRightIToken(),
                                     (DataBlock)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 77:  Statement ::= prepare$ DataBlock$LHSDataBlock AssignOp DataBlock$RHSDataBlock
            //
            case 77: {
                setResult(
                    new PrepareStatement(getLeftIToken(), getRightIToken(),
                                         (DataBlock)getRhsSym(2),
                                         (IAssignOp)getRhsSym(3),
                                         (DataBlock)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 78:  Statement ::= request$ DataBlock Ident
            //
            case 78: {
                setResult(
                    new RequestStatement(getLeftIToken(), getRightIToken(),
                                         (DataBlock)getRhsSym(2),
                                         (Ident)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 79:  Statement ::= prequest$ DataBlock$LHSDataBlock =$ DataBlock$RHSDataBlock
            //
            case 79: {
                setResult(
                    new PrequestStatement(getLeftIToken(), getRightIToken(),
                                          (DataBlock)getRhsSym(2),
                                          (DataBlock)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 80:  Statement ::= collective$ Ident$LHSIdent AssignOp Ident$RHSIdent
            //
            case 80: {
                setResult(
                    new CollectiveStatement(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2),
                                            (IAssignOp)getRhsSym(3),
                                            (Ident)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 81:  Statement ::= destroy$ Ident
            //
            case 81: {
                setResult(
                    new DestroyStatement(getLeftIToken(), getRightIToken(),
                                         (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 82:  Statement ::= println$ StringLiteral
            //
            case 82: {
                setResult(
                    new PrintlnStatement(getLeftIToken(), getRightIToken(),
                                         (StringLiteral)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 83:  Statement ::= print$ StringLiteral
            //
            case 83: {
                setResult(
                    new PrintStatement(getLeftIToken(), getRightIToken(),
                                       (StringLiteral)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 84:  Statement ::= print_index$ Ident
            //
            case 84: {
                setResult(
                    new PrintIndexStatement(getLeftIToken(), getRightIToken(),
                                            (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 85:  Statement ::= print_scalar$ Ident
            //
            case 85: {
                setResult(
                    new PrintScalarStatement(getLeftIToken(), getRightIToken(),
                                             (Ident)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 86:  Arg ::= Primary
            //
            case 86:
                break;
            //
            // Rule 87:  ArgList ::= $Empty
            //
            case 87: {
                setResult(
                    new ArgList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 88:  ArgList ::= ArgList Arg
            //
            case 88: {
                ((ArgList)getRhsSym(1)).add((IArg)getRhsSym(2));
                break;
            }
            //
            // Rule 89:  Statement ::= execute$ Ident ArgList
            //
            case 89: {
                setResult(
                    new ExecuteStatement(getLeftIToken(), getRightIToken(),
                                         (Ident)getRhsSym(2),
                                         (ArgList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 90:  Statement ::= ScalarOrBlockVar AssignOp Expression
            //
            case 90: {
                setResult(
                    new AssignStatement(SialParser.this, getLeftIToken(), getRightIToken(),
                                        (IScalarOrBlockVar)getRhsSym(1),
                                        (IAssignOp)getRhsSym(2),
                                        (IExpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 91:  Statement ::= gpu_on$ EOLs$ StatementList gpu_off$
            //
            case 91: {
                setResult(
                    new GpuStatement(getLeftIToken(), getRightIToken(),
                                     (StatementList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 92:  Statement ::= gpu_allocate$ Primary
            //
            case 92: {
                setResult(
                    new GpuAllocate(getLeftIToken(), getRightIToken(),
                                    (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 93:  Statement ::= gpu_free$ Primary
            //
            case 93: {
                setResult(
                    new GpuFree(getLeftIToken(), getRightIToken(),
                                (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 94:  Statement ::= gpu_put$ Primary
            //
            case 94: {
                setResult(
                    new GpuPut(getLeftIToken(), getRightIToken(),
                               (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 95:  Statement ::= gpu_get$ Primary
            //
            case 95: {
                setResult(
                    new GpuGet(getLeftIToken(), getRightIToken(),
                               (IPrimary)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 96:  Statement ::= set_persistent$ Ident StringLiteral
            //
            case 96: {
                setResult(
                    new SetPersistent(getLeftIToken(), getRightIToken(),
                                      (Ident)getRhsSym(2),
                                      (StringLiteral)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 97:  Statement ::= restore_persistent$ Ident StringLiteral
            //
            case 97: {
                setResult(
                    new RestorePersistent(getLeftIToken(), getRightIToken(),
                                          (Ident)getRhsSym(2),
                                          (StringLiteral)getRhsSym(3))
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

