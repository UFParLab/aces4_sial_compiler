
Predefined Macros:

    $rule_number
    $rule_text
    $rule_size
    $input_file
    $current_line
    $next_line
    $#identifier
    $symbol_declarations
    $num_rules
    $num_terminals
    $num_nonterminals
    $num_non_terminals
    $num_symbols
    $template
    $file_prefix
    $package
    $ast_package
    $ast_type
    $exp_type
    $prs_type
    $sym_type
    $action_type
    $visitor_type
    $prefix
    $suffix
    $entry_name
    $entry_marker



Defined Symbols:



    $Header
    /.
                    //
                    // Rule $rule_number:  $rule_text
                    //
                    ./


    $BeginAction
    /.$Header$case $rule_number: {./


    $EndAction
    /.                break;
                    }./


    $BeginJava
    /.$BeginAction
                        $symbol_declarations./


    $EndJava
    /.$EndAction./


    $NoAction
    /.$Header$case $rule_number:
                        break;./


    $BadAction
    /.$Header$case $rule_number:
                        throw new Error("No action specified for rule " + $rule_number);./


    $NullAction
    /.$Header$case $rule_number:
                        setResult(null);
                        break;./


    $BeginActions
    /.
            public void ruleAction(int ruleNumber)
            {
                switch (ruleNumber)
                {./


    $SplitActions
    /.
    	            default:
    	                ruleAction$rule_number(ruleNumber);
    	                break;
    	        }
    	        return;
    	    }
    	
    	    public void ruleAction$rule_number(int ruleNumber)
    	    {
    	        switch (ruleNumber)
    	        {./


    $EndActions
    /.
                    default:
                        break;
                }
                return;
            }./


    $entry_declarations
    /.
            public $ast_class parse$entry_name()
            {
                return parse$entry_name(null, 0);
            }
                
            public $ast_class parse$entry_name(Monitor monitor)
            {
                return parse$entry_name(monitor, 0);
            }
                
            public $ast_class parse$entry_name(int error_repair_count)
            {
                return parse$entry_name(null, error_repair_count);
            }
                
            public void resetParse$entry_name()
            {
                dtParser.resetParserEntry($sym_type.$entry_marker);
            }
            
            public $ast_class parse$entry_name(Monitor monitor, int error_repair_count)
            {
                dtParser.setMonitor(monitor);
                
                try
                {
                    return ($ast_class) dtParser.parseEntry($sym_type.$entry_marker);
                }
                catch (BadParseException e)
                {
                    prsStream.reset(e.error_token); // point to error token
    
                    DiagnoseParser diagnoseParser = new DiagnoseParser(prsStream, prsTable);
                    diagnoseParser.diagnoseEntry($sym_type.$entry_marker, e.error_token);
                }
    
                return null;
            }
        ./


    $additional_interfaces
    /., IParser./


    $ast_class
    /.Object./


    $unimplemented_symbols_warning
    /.false./


    $setSym1
    /. // macro setSym1 is deprecated. Use function setResult
                    getParser().setSym1./


    $setResult
    /. // macro setResult is deprecated. Use function setResult
                     getParser().setSym1./


    $getSym
    /. // macro getSym is deprecated. Use function getRhsSym
                  getParser().getSym./


    $getToken
    /. // macro getToken is deprecated. Use function getRhsTokenIndex
                    getParser().getToken./


    $getIToken
    /. // macro getIToken is deprecated. Use function getRhsIToken
                     prsStream.getIToken./


    $getLeftSpan
    /. // macro getLeftSpan is deprecated. Use function getLeftSpan
                       getParser().getFirstToken./


    $getRightSpan
    /. // macro getRightSpan is deprecated. Use function getRightSpan
                        getParser().getLastToken./


Aliases:

    , ::= COMMA
    + ::= PLUS
    - ::= MINUS
    * ::= STAR
    / ::= SLASH
    ^ ::= TENSOR
    > ::= GREATER
    >= ::= GEQ
    < ::= LESS
    <= ::= LEQ
    == ::= EQ
    != ::= NEQ
    = ::= ASSIGN
    += ::= PLUS_ASSIGN
    -= ::= MINUS_ASSIGN
    *= ::= STAR_ASSIGN
    ( ::= LEFTPAREN
    ) ::= RIGHTPAREN


Start:

    Sial

Eof:

    EOF_TOKEN

Error:

    ERROR_TOKEN

Terminals:

    COMMA
    PLUS
    MINUS
    STAR
    SLASH
    TENSOR
    GREATER
    GEQ
    LESS
    LEQ
    EQ
    NEQ
    ASSIGN
    PLUS_ASSIGN
    MINUS_ASSIGN
    STAR_ASSIGN
    LEFTPAREN
    RIGHTPAREN
    sial
    endsial
    proc
    endproc
    return
    call
    pardo
    endpardo
    do
    in
    enddo
    cycle
    exit
    if
    else
    endif
    put
    get
    prepare
    request
    prequest
    collective
    execute
    aoindex
    moindex
    moaindex
    mobindex
    index
    laindex
    subindex
    of
    scalar
    int
    static
    temp
    local
    distributed
    served
    create
    delete
    allocate
    deallocate
    destroy
    where
    import
    predefined
    special
    server_barrier
    sip_barrier
    section
    endsection
    print
    println
    print_index
    print_scalar
    sip_consistent
    scoped_extent
    contiguous
    auto_allocate
    gpu_on
    gpu_off
    gpu_allocate
    gpu_free
    gpu_put
    gpu_get
    set_persistent
    restore_persistent
    sparse
    EOF_TOKEN
    SINGLE_LINE_COMMENT
    IDENTIFIER
    INTLIT
    DOUBLELIT
    EOL
    STRINGLIT
    ERROR_TOKEN


Soft Keywords:



Rules:

0      '$accept' ::= Sial

1      Sial ::= EOLsopt $ ImportProgList Program
2      Program ::= sial $ Ident $startName EOLs $ DecList StatementList endsial $ Ident $endName EOLsopt $
3      EOLsopt$ ::= $Empty
4                | EOLs $
5      EOLs$ ::= SINGLE_LINE_COMMENT $
6             | EOL $
7             | EOLs $ EOL $
8      ImportProgList$$ImportProg ::= $Empty
9                       | ImportProgList ImportProg
10     ImportProg ::= import $ STRINGLIT EOLs $
11     Modifiersopt$$Modifier ::= $Empty
12                    | Modifiers
13     Modifiers$$Modifier ::= Modifier
14                 | Modifiers Modifier
15     Modifier$PredefinedModifier ::= predefined
16     Modifier$ContiguousModifier ::= contiguous
17     Modifier$SparseModifier ::= sparse
18     DecList$$Dec ::= $Empty
19               | DecList Dec EOLs $
20     Dec ::= ScalarDec
21           | ArrayDec
22           | IndexDec
23           | SubIndexDec
24           | IntDec
25           | ProcDec
26           | SpecialDec
27     ScalarDec ::= Modifiersopt scalar $ Ident
28     IntDec ::= Modifiersopt int $ Ident
29     ArrayDec ::= Modifiersopt ArrayKind Ident ( $ DimensionList ) $
30     ArrayKind$ArrayKind ::= static $akind
31                 | temp $akind
32                 | local $akind
33                 | distributed $akind
34                 | served $akind
35     DimensionList$$Dimension ::= Dimension
36                     | DimensionList , $ Dimension
37     Dimension ::= Ident
38     IndexDec ::= Modifiersopt IndexKind Ident = $ Range
39     IndexKind$IndexKind ::= aoindex $ikind
40                 | moindex $ikind
41                 | moaindex $ikind
42                 | mobindex $ikind
43                 | index $ikind
44                 | laindex $ikind
45     SubIndexDec$SubIndexDec ::= subindex $ Ident of $ Ident $ParentIdent
46     Range ::= RangeVal $RangeValStart , $ RangeVal $RangeValEnd
47     RangeVal$IntLitRangeVal ::= INTLIT
48     RangeVal$NegRangeVal ::= - $ INTLIT
49     RangeVal$IdentRangeVal ::= Ident
50     ProcDec ::= proc $ Ident EOLs $ StatementList endproc Ident $endIdent
51     SpecialDec ::= special $ Ident IdentOpt $Signature
52     IdentOpt ::= Ident
53                | $Empty
54     StatementList$$Statement ::= $Empty
55                     | StatementList Statement EOLs $
56     WhereClause ::= where $ RelationalExpression
57     WhereClauseList ::= $Empty
58                       | WhereClauseList WhereClause EOLs $
59     Statement$CallStatement ::= call $ Ident
60     Statement$ReturnStatement ::= return $
61     Statement$ServerBarrierStatement ::= server_barrier $ IdentOpt
62     Statement$SipBarrierStatement ::= sip_barrier $ IdentOpt
63     Statement$DoStatement ::= do $ Ident $StartIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex
64     Statement$DoStatementSubIndex ::= do $ Ident $StartIndex in $ Ident $StartParentIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex in $ Ident $EndParentIndex
65     Statement$PardoStatement ::= pardo $ Indices $StartIndices EOLs $ WhereClauseList StatementList endpardo $ Indices $EndIndices
66     Statement$Section ::= section $ EOLs $ StatementList endsection $
67     Statement$ExitStatement ::= exit $
68     Statement$CycleStatement ::= cycle $ Ident
69     Statement$IfStatement ::= if $ RelationalExpression EOLs $ StatementList endif $
70     Statement$IfElseStatement ::= if $ RelationalExpression EOLs $ StatementList $ifStatements else $ EOLs $ StatementList $elseStatements endif $
71     Statement$AllocateStatement ::= allocate $ Ident AllocIndexListopt
72     Statement$DeallocateStatement ::= deallocate $ Ident AllocIndexListopt
73     Statement$CreateStatement ::= create $ Ident AllocIndexListopt
74     Statement$DeleteStatement ::= delete $ Ident AllocIndexListopt
75     Statement$PutStatement ::= put $ DataBlock $LHSDataBlock AssignOp DataBlock $RHSDataBlock
76     Statement$GetStatement ::= get $ DataBlock
77     Statement$PrepareStatement ::= prepare $ DataBlock $LHSDataBlock AssignOp DataBlock $RHSDataBlock
78     Statement$RequestStatement ::= request $ DataBlock Ident
79     Statement$PrequestStatement ::= prequest $ DataBlock $LHSDataBlock = $ DataBlock $RHSDataBlock
80     Statement$CollectiveStatement ::= collective $ Ident $LHSIdent AssignOp Ident $RHSIdent
81     Statement$DestroyStatement ::= destroy $ Ident
82     Statement$PrintlnStatement ::= println $ StringLiteral
83     Statement$PrintStatement ::= print $ StringLiteral
84     Statement$PrintIndexStatement ::= print_index $ Ident
85     Statement$PrintScalarStatement ::= print_scalar $ Ident
86     Arg ::= Primary
87     ArgList$$Arg ::= $Empty
88               | ArgList Arg
89     Statement$ExecuteStatement ::= execute $ Ident ArgList
90     Statement$AssignStatement ::= ScalarOrBlockVar AssignOp Expression
91     Statement$GpuStatement ::= gpu_on $ EOLs $ StatementList gpu_off $
92     Statement$GpuAllocate ::= gpu_allocate $ Primary
93     Statement$GpuFree ::= gpu_free $ Primary
94     Statement$GpuPut ::= gpu_put $ Primary
95     Statement$GpuGet ::= gpu_get $ Primary
96     Statement$SetPersistent ::= set_persistent $ Ident StringLiteral
97     Statement$RestorePersistent ::= restore_persistent $ Ident StringLiteral
98     AssignOp$AssignOpEqual ::= =
99     AssignOp$AssignOpPlus ::= +=
100    AssignOp$AssignOpMinus ::= -=
101    AssignOp$AssignOpStar ::= *=
102    ScalarOrBlockVar ::= Ident
103                       | DataBlock
104    DataBlock ::= Ident ( $ Indices ) $
105    Indices$$Ident ::= Ident
106              | Indices , $ Ident
107    AllocIndex$AllocIndexIdent ::= Ident
108    AllocIndex$AllocIndexWildCard ::= * $
109    AllocIndexList$$AllocIndex ::= AllocIndex
110                     | AllocIndexList , $ AllocIndex
111    AllocIndexListopt ::= $Empty
112                        | ( AllocIndexList )
113    RelationalExpression ::= UnaryExpression $UnaryExpressionLeft RelOp UnaryExpression $UnaryExpressionRight
114    RelOp$RelOp ::= < $op
115            | > $op
116            | <= $op
117            | >= $op
118            | == $op
119            | != $op
120    Expression ::= UnaryExpression $UnaryExpression
121                 | BinaryExpression $BinaryExpression
122    BinaryExpression ::= UnaryExpression $Expr1 BinOp UnaryExpression $Expr2
123    BinOp$BinOpStar ::= *
124    BinOp$BinOpDiv ::= /
125    BinOp$BinOpPlus ::= +
126    BinOp$BinOpMinus ::= -
127    BinOp$BinOpTensor ::= ^
128    UnaryExpression ::= Primary
129    UnaryExpression$NegatedUnary ::= - $ Primary
130    Primary$IntLitPrimary ::= INTLIT
131    Primary$DoubleLitPrimary ::= DOUBLELIT
132    Primary$IdentPrimary ::= Ident
133    Primary$DataBlockPrimary ::= DataBlock
134    Primary$StringLitPrimary ::= StringLiteral
135    StringLiteral ::= STRINGLIT
136    Ident ::= IDENTIFIER
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:74:1:74:14:711:724: Informative: The terminal sip_consistent is useless.
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:75:1:75:13:726:738: Informative: The terminal scoped_extent is useless.
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:77:1:77:13:751:763: Informative: The terminal auto_allocate is useless.


 IBM LALR Parser Generator              2.0.17 (Jul 17 2009)


Name map:

'$empty' ::= '$empty'
COMMA ::= ,
PLUS ::= +
MINUS ::= -
STAR ::= *
SLASH ::= /
TENSOR ::= ^
GREATER ::= >
GEQ ::= >=
LESS ::= <
LEQ ::= <=
EQ ::= ==
NEQ ::= !=
ASSIGN ::= =
PLUS_ASSIGN ::= +=
MINUS_ASSIGN ::= -=
STAR_ASSIGN ::= *=
LEFTPAREN ::= (
RIGHTPAREN ::= )
sial ::= sial
endsial ::= endsial
proc ::= proc
endproc ::= endproc
return ::= return
call ::= call
pardo ::= pardo
endpardo ::= endpardo
do ::= do
in ::= in
enddo ::= enddo
cycle ::= cycle
exit ::= exit
if ::= if
else ::= else
endif ::= endif
put ::= put
get ::= get
prepare ::= prepare
request ::= request
prequest ::= prequest
collective ::= collective
execute ::= execute
aoindex ::= aoindex
moindex ::= moindex
moaindex ::= moaindex
mobindex ::= mobindex
index ::= index
laindex ::= laindex
subindex ::= subindex
of ::= of
scalar ::= scalar
int ::= int
static ::= static
temp ::= temp
local ::= local
distributed ::= distributed
served ::= served
create ::= create
delete ::= delete
allocate ::= allocate
deallocate ::= deallocate
destroy ::= destroy
where ::= where
import ::= import
predefined ::= predefined
special ::= special
server_barrier ::= server_barrier
sip_barrier ::= sip_barrier
section ::= section
endsection ::= endsection
print ::= print
println ::= println
print_index ::= print_index
print_scalar ::= print_scalar
sip_consistent ::= sip_consistent
scoped_extent ::= scoped_extent
contiguous ::= contiguous
auto_allocate ::= auto_allocate
gpu_on ::= gpu_on
gpu_off ::= gpu_off
gpu_allocate ::= gpu_allocate
gpu_free ::= gpu_free
gpu_put ::= gpu_put
gpu_get ::= gpu_get
set_persistent ::= set_persistent
restore_persistent ::= restore_persistent
sparse ::= sparse
EOF_TOKEN ::= EOF_TOKEN
SINGLE_LINE_COMMENT ::= SINGLE_LINE_COMMENT
IDENTIFIER ::= IDENTIFIER
INTLIT ::= INTLIT
DOUBLELIT ::= DOUBLELIT
EOL ::= EOL
STRINGLIT ::= STRINGLIT
ERROR_TOKEN ::= ERROR_TOKEN
Sial ::= Sial
Program ::= Program
Ident ::= Ident
EOLs ::= EOLs
ImportProg ::= ImportProg
Modifiers ::= Modifiers
Modifier ::= Modifier
Dec ::= Dec
ArrayKind ::= ArrayKind
DimensionList ::= DimensionList
Dimension ::= Dimension
IndexKind ::= IndexKind
Range ::= Range
RangeVal ::= RangeVal
Statement ::= Statement
WhereClause ::= WhereClause
RelationalExpression ::= RelationalExpression
Indices ::= Indices
DataBlock ::= DataBlock
AssignOp ::= AssignOp
StringLiteral ::= StringLiteral
Arg ::= Arg
Primary ::= Primary
ScalarOrBlockVar ::= ScalarOrBlockVar
Expression ::= Expression
AllocIndex ::= AllocIndex
AllocIndexList ::= AllocIndexList
UnaryExpression ::= UnaryExpression
RelOp ::= RelOp
BinOp ::= BinOp
EOLsopt ::= 
ImportProgList ::= 
DecList ::= 
StatementList ::= 
Modifiersopt ::= 
ScalarDec ::= 
ArrayDec ::= 
IndexDec ::= 
SubIndexDec ::= 
IntDec ::= 
ProcDec ::= 
SpecialDec ::= 
IdentOpt ::= 
WhereClauseList ::= 
AllocIndexListopt ::= 
ArgList ::= 
BinaryExpression ::= 

 IBM LALR Parser Generator              2.0.17 (Jul 17 2009)


Scopes:

Statement ::= if RelationalExpression EOLs StatementList else EOLs 
              StatementList  .endif 
Statement ::= do Ident in Ident EOLs WhereClauseList StatementList  .enddo 
              Ident in Ident 
Statement ::= pardo Indices EOLs WhereClauseList StatementList  .endpardo 
              Indices 
Statement ::= do Ident EOLs WhereClauseList StatementList  .enddo Ident 
Statement ::= if RelationalExpression EOLs StatementList  .endif 
Statement ::= if RelationalExpression EOLs StatementList  .else EOLs 
              StatementList endif 
Statement ::= gpu_on EOLs StatementList  .gpu_off 
Statement ::= section EOLs StatementList  .endsection 
StatementList ::= StatementList Statement  .EOLs 

