%options package=sial.parser
%options la =2
%options template=dtParserTemplateF.gi
%options import_terminals=SialLexer.gi
%options parent_saved,automatic_ast=toplevel,visitor=preorder,ast_directory=./Ast,ast_type=ASTNode


%Globals
    /.import org.eclipse.imp.parser.IParser;
      import sial.parser.context.*;
	  import java.util.Date;
	  import java.util.ArrayList;
	  import java.util.List;
    ./
%End

%Define
    $ast_class /.Object./
    $additional_interfaces /., IParser./
%End

	%Terminals
	--terminals whose alias is not a valid Java identifier
	COMMA ::= ','
	PLUS ::= '+'
	MINUS ::= '-'
	STAR ::= '*'
	SLASH ::= '/'
	TENSOR ::= '^'
	GREATER ::= '>'
	GEQ ::= '>='
	LESS ::= '<'
	LEQ ::= '<='
	EQ ::= '=='
	NEQ ::= '!='
	-- AND ::= '&&'
	-- OR ::= '||'
	ASSIGN ::= '='
	PLUS_ASSIGN ::= '+='
	MINUS_ASSIGN ::= '-='
	STAR_ASSIGN ::= '*='
	LEFTPAREN ::= '('
	RIGHTPAREN ::= ')'
	
	
	%End

	%Start
		Sial
	%End
		
	
	
	%Rules		
	
	Sial ::= EOLsopt $  
	ImportProgList  
	Program
	
	/.
	  
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
	
   ./
   
   Program ::= sial$ Ident$startName  EOLs$  
   DecList 
   StatementList  
   endsial$  Ident$endName EOLsopt$
   /.  
	 public String getStartName(){
	    return  getstartName().toString().toLowerCase();
	 }
	 
	 public String getEndName(){
	    return  getendName().toString().toLowerCase();
	 }
	 

	./
    EOLsopt$ ::=  %empty | EOLs$ 
	EOLs$ ::=  SINGLE_LINE_COMMENT$  |  EOL$ | EOLs$ EOL$ 
	
	ImportProgList$$ImportProg ::= %empty | ImportProgList ImportProg 

	ImportProg ::= import$ STRINGLIT EOLs$--use this to get case sensitive  file name which should have .sial or .sialx suffix 
	/. Sial importAst;
	  public void setAst(Sial ast){importAst = ast;}
	  public Sial getAst(){return importAst;}
	  public String getName(){
	       return ASTUtils.getStringVal(getSTRINGLIT().toString());
	  }
	 ./
	 
---declarations--------------------------	 
	 Modifiersopt$$Modifier ::= %Empty
                             | Modifiers
	 
							 
	Modifiers$$Modifier ::= Modifier
                          | Modifiers Modifier

    Modifier$ConstantModifier ::= 'constant'
    Modifier$PredefinedModifier ::= 'predefined'
    Modifier$PersistentModifier ::= 'persistent'
	

	 
	 DecList$$Dec ::= %empty | DecList Dec EOLs$
	 Dec ::= ScalarDec  | ArrayDec  |  IndexDec | SubIndexDec | IntDec  | ProcDec | SpecialDec
	 
	 ScalarDec ::= Modifiersopt scalar$ Ident
	 /.  public String getName(){
	   return getIdent().getName();
	   }
	  ./
	  
	  IntDec ::= Modifiersopt   int$  Ident
	  /.  public String getName(){
		return getIdent().getName();
		}
	   ./
	   
	   ArrayDec ::= Modifiersopt  ArrayKind Ident '('$ DimensionList ')'$
	   /.  public String getName(){
		 return getIdent().getName();
		 }
		 public String getTypeName(){
		 return getArrayKind().toString().toLowerCase();
		 }
		./
		
		ArrayKind$ArrayKind ::=
		static$akind 
		| temp$akind  
		| local$akind    
		| distributed$akind
		| served$akind
		
		
		DimensionList$$Dimension ::= Dimension | DimensionList ','$ Dimension
		Dimension ::= Ident
--		DimensionListopt ::= %empty |  '(' DimensionList ')' 
		
		IndexDec ::= Modifiersopt IndexKind Ident =$ Range
		/.  public String getName(){
		  return getIdent().getName();
		  }
		  public String getTypeName(){
		  return getIndexKind().getikind().toString().toLowerCase();
		  }   
		 ./
		 
		 IndexKind$IndexKind ::=
		 aoindex$ikind
		 | moindex$ikind
		 | moaindex$ikind
		 | mobindex$ikind
		 | index$ikind
		 | laindex$ikind
		 
		 SubIndexDec$SubIndexDec ::= subindex$ Ident of$ Ident$ParentIdent 			  
	/.  public String getName(){
	  return getIdent().getName();
	  }
	  public String getParentName(){
	  return getParentIdent().toString().toLowerCase();
	  }
	 ./
	 
	 Range ::= RangeVal$RangeValStart ','$ RangeVal$RangeValEnd
	 RangeVal$IntLitRangeVal ::= INTLIT 
	 RangeVal$IdentRangeVal ::=  Ident
	 /.
	   public String getName(){
	   return toString().toLowerCase();
	   }
	   IDec dec;
	   public void setDec(IDec dec) { this.dec = dec; }
	   public IDec getDec() { return dec; }
	  ./
	  

	  ProcDec ::= proc$ Ident EOLs$ StatementList endproc Ident$endIdent  
	  /.  public String getName(){
		return getIdent().getName();
		}
		private  int addr;  //index of start of proc in optable
		 public void setAddr(int addr){this.addr = addr;}
		 public int getAddr(){return addr;}
		 
		 private List<CallStatement> callSites = new ArrayList<CallStatement>();
		 public  List<CallStatement> getCallSites(){return callSites;}
	   ./

	SpecialDec ::= special$  Ident  --TODO add args
	/.  public String getName(){
	  return getIdent().getName();
	  }
	  	 int addr;  //index of this function in function table
		 public void setAddr(int addr){this.addr = addr;}
		 public int getAddr(){return addr;}
	 ./
	 

     IdentOpt ::= Ident | %empty
	 
	 
	 
	 StatementList$$Statement ::= %empty | StatementList Statement EOLs$ 
	 
	 WhereClause ::= where$ RelationalExpression
	 WhereClauseList ::= %empty | WhereClauseList WhereClause EOLs$
	 
	  Statement$CallStatement ::= call$  Ident
	 
	  Statement$ReturnStatement ::= return$
	 
	 --TODO check need for IdentOpt
	--TODO AcesHack decl is there to easily allow "binary compatibility" with existing sip
     --  and should be removed once the sip supports reading function names
     --this is treated as a call to execute _server_barrier
	 Statement$ServerBarrierStatement ::= server_barrier$ IdentOpt
	 	/.
	  IDec dec;
	  public void setDec(IDec dec) { this.dec = dec; }
	  public IDec getDec() { return dec; }
	  ./
	  
	 --TODO  check need for IdentOpt
	--TODO AcesHack decl is there to easily allow "binary compatibility" with existing sip
     --  and should be removed once the sip supports reading function names
     --this is treated as a call to execute _sip_barrier, so the IDec is for the barrier superinstruction, not the IdentOpt
	 --Statement$SipBarrierStatement ::= sip_barrier$ IdentOpt
     Statement$SipBarrierStatement ::= sip_barrier$ IdentOpt
	 /.
	  IDec dec;
	  public void setDec(IDec dec) { this.dec = dec; }
	  public IDec getDec() { return dec; }
	  ./
	  
	 Statement$DoStatement ::= do$ Ident$StartIndex  EOLs$ 
	 WhereClauseList
	 StatementList 
	 enddo$ Ident$EndIndex
	 
	 Statement$DoStatementSubIndex ::= do$ Ident$StartIndex in$ Ident$StartParentIndex EOLs$		
	 WhereClauseList
	 StatementList
	 enddo$ Ident$EndIndex in$ Ident$EndParentIndex
	 
	 
	 
	 Statement$PardoStatement ::= pardo$ Indices$StartIndices EOLs$ 
	 WhereClauseList 
	 StatementList 
	 endpardo$ Indices$EndIndices
	 
	 Statement$Section ::= section$ EOLs$  --the endsection is treated like a server barrier
	 StatementList  -- all statements must be PardoStatements, but this will be verified during type checking
	 endsection$ 
	 	 /.
	  IDec dec;
	  public void setDec(IDec dec) { this.dec = dec; }
	  public IDec getDec() { return dec; }
	  ./
	 
	 Statement$ExitStatement ::= exit$
	 
	 Statement$CycleStatement ::=  cycle$ Ident
	 
	 Statement$IfStatement ::=  if$ RelationalExpression EOLs$ StatementList endif$
	 
	 Statement$IfElseStatement ::= if$ RelationalExpression EOLs$ StatementList$ifStatements  else$ EOLs$ StatementList$elseStatements endif$
	 
	 Statement$AllocateStatement ::= allocate$ Ident AllocIndexListopt
	
	Statement$DeallocateStatement ::= deallocate$ Ident AllocIndexListopt
	
	 --Statement$CreateStatement ::= create$ Ident DimensionListopt
    Statement$CreateStatement ::= create$ Ident AllocIndexListopt
	 
	 Statement$DeleteStatement ::=  delete$ Ident AllocIndexListopt
	
	Statement$PutStatement ::= put$ DataBlock$LHSDataBlock AssignOp DataBlock$RHSDataBlock
	
	Statement$GetStatement ::=  get$  DataBlock
	
	Statement$PrepareStatement ::= prepare$ DataBlock$LHSDataBlock  AssignOp DataBlock$RHSDataBlock
	
	Statement$RequestStatement ::= request$ DataBlock Ident
	
	Statement$PrequestStatement ::= prequest$  DataBlock$LHSDataBlock '=' $ DataBlock$RHSDataBlock
	
	Statement$CollectiveStatement ::= collective$ Ident$LHSIdent AssignOp Ident$RHSIdent
	
	--	Statement$ComputeIntegralsStatement ::= compute_integrals$ DataBlock
	Statement$DestroyStatement::= destroy$ Ident
	
    Statement$PrintlnStatement ::= println$ STRINGLIT
	Statement$PrintStatement ::= print$ STRINGLIT
	Statement$PrintIndexStatement ::= print_index$ Ident
    Statement$PrintScalarStatement::= print_scalar$ Ident
	
	Arg ::= Primary  
	ArgList$$Arg ::=  %empty | ArgList Arg
	Statement$ExecuteStatement ::= execute$ Ident ArgList
	--It would be better to have a comma separating the args
	
	Statement$AssignStatement ::=  ScalarOrBlockVar AssignOp Expression
	
	AssignOp$AssignOpEqual  ::=    '='
	AssignOp$AssignOpPlus  ::=    '+='
	AssignOp$AssignOpMinus  ::=   '-='
	AssignOp$AssignOpStar   ::= '*='
	
	ScalarOrBlockVar ::= Ident | DataBlock

 	DataBlock ::= Ident '('$ Indices ')'$
	Indices$$Ident ::= Ident | Indices ','$ Ident
	
	AllocIndex$AllocIndexIdent ::= Ident
	/.
	  IDec dec;
	  public void setDec(IDec dec) { this.dec = dec; }
	  public IDec getDec() { return dec; }
	  public String getName(){
	         return toString().toLowerCase();
	  }
	  ./
	AllocIndex$AllocIndexWildCard ::= '*'$
	AllocIndexList$$AllocIndex ::= AllocIndex | AllocIndexList ',' $ AllocIndex
    AllocIndexListopt ::= %empty | '(' AllocIndexList ')'
	
	RelationalExpression ::= UnaryExpression$UnaryExpressionLeft RelOp UnaryExpression$UnaryExpressionRight
	RelOp$RelOp  ::= '<'$op | '>'$op | '<='$op | '>='$op  | '=='$op | '!='$op
	
	Expression ::= UnaryExpression$UnaryExpression | BinaryExpression$BinaryExpression
	BinaryExpression ::= UnaryExpression$Expr1 BinOp UnaryExpression$Expr2
	BinOp$BinOpStar ::= '*' 
	BinOp$BinOpDiv ::= '/'
	BinOp$BinOpPlus ::= '+'
	BinOp$BinOpMinus  ::= '-'
	BinOp$BinOpTensor ::= '^'

	UnaryExpression ::= Primary
	UnaryExpression$NegatedUnary ::= '-' $ Primary
	
	Primary$IntLitPrimary ::= INTLIT
	Primary$DoubleLitPrimary ::= DOUBLELIT
	Primary$IdentPrimary  ::= Ident
		/.
	  IDec dec;
	  public void setDec(IDec dec) { this.dec = dec; }
	  public IDec getDec() { return dec; }
	  public boolean equals(Object obj){
	       if (!(obj instanceof IdentPrimary)) 
		            return false;
	        return  obj == null? false : this.toString().equals(obj.toString());
	  }
	  private static final long serialVersionUID = -4338743197305594251L;
	  public int hashCode(){
	       return (int)serialVersionUID;
	  }
	  public String getName(){
	       return toString().toLowerCase();
	  }
	 ./
	Primary$DataBlockPrimary ::= DataBlock
    Primary$StringLitPrimary ::= StringLiteral
	
	StringLiteral ::= STRINGLIT
	/. 
	  String stringValue;
	  public void setStringValue(String stringValue){
	         this.stringValue = stringValue;
	  }
	  public String  getStringValue(){
	         return stringValue;
      }
	  
	 ./
	
	Ident ::= IDENTIFIER
	/.
	  IDec dec;
	  public void setDec(IDec dec) { this.dec = dec; }
	  public IDec getDec() { return dec; }
       public boolean equals(Object obj){
	       
	       if (!(obj instanceof Ident)) return false;
	       return  obj == null? false : (getIDENTIFIER().toString().equalsIgnoreCase(((Ident)obj).getIDENTIFIER().toString()));
	  }
	  // private static final long serialVersionUID = -4338743197305594251L;
	  // public int hashCode(){
	 //  return (int)serialVersionUID;
	 // }

	  public String getName(){
	       return toString().toLowerCase();
	  }
	 ./
	 
	%End
		
	
	%Headers


/.
  


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



./

%End





