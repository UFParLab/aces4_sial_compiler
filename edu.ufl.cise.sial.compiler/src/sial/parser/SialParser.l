
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
    [ ::= LEFTSQUARE
    ] ::= RIGHTSQUARE
    : ::= COLON
    ** ::= EXP


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
    LEFTSQUARE
    RIGHTSQUARE
    COLON
    EXP
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
    print_int
    contiguous
    gpu_on
    gpu_off
    gpu_allocate
    gpu_free
    gpu_put
    gpu_get
    set_persistent
    restore_persistent
    sparse
    assert_same
    broadcast_from
    sqrt
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
15     Modifier$Modifier ::= predefined $modifier
16                | contiguous $modifier
17                | sparse $modifier
18     DecList$$Dec ::= $Empty
19               | DecList Dec EOLs $
20     Dec ::= ScalarDec
21           | ArrayDec
22           | IndexDec
23           | SubIndexDec
24           | IntDec
25           | ProcDec
26           | SpecialDec
27     ScalarDec ::= Modifiersopt scalar $ Ident ScalarInitializationOpt
28     ScalarInitializationOpt$ScalarInitialValue ::= $Empty
29                               | = DOUBLELIT
30     IntDec ::= Modifiersopt int $ Ident IntInitializationOpt
31     IntInitializationOpt$IntInitialValue ::= $Empty
32                            | = INTLIT
33     ArrayDec ::= Modifiersopt ArrayKind Ident [ $ DimensionList ] $
34     ArrayKind$ArrayKind ::= static $akind
35                 | temp $akind
36                 | local $akind
37                 | distributed $akind
38                 | served $akind
39     DimensionList$$Dimension ::= Dimension
40                     | DimensionList , $ Dimension
41     Dimension ::= Ident
42     IndexDec ::= Modifiersopt IndexKind Ident = $ Range
43     IndexKind$IndexKind ::= aoindex $ikind
44                 | moindex $ikind
45                 | moaindex $ikind
46                 | mobindex $ikind
47                 | index $ikind
48                 | laindex $ikind
49     SubIndexDec$SubIndexDec ::= subindex $ Ident of $ Ident $ParentIdent
50     Range ::= RangeVal $RangeValStart : $ RangeVal $RangeValEnd
51     RangeVal$IntLitRangeVal ::= INTLIT
52     RangeVal$NegRangeVal ::= - $ INTLIT
53     RangeVal$IdentRangeVal ::= Ident
54     ProcDec ::= proc $ Ident EOLs $ StatementList endproc Ident $endIdent
55     SpecialDec ::= special $ Ident IdentOpt $Signature
56     IdentOpt ::= Ident
57                | $Empty
58     StatementList$$Statement ::= $Empty
59                     | StatementList Statement EOLs $
60     WhereClause ::= where $ RelationalExpression
61     WhereClauseList ::= $Empty
62                       | WhereClauseList WhereClause EOLs $
63     Statement$CallStatement ::= call $ Ident
64     Statement$ReturnStatement ::= return $
65     Statement$ServerBarrierStatement ::= server_barrier $
66     Statement$SipBarrierStatement ::= sip_barrier $
67     Statement$DoStatement ::= do $ Ident $StartIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex
68     Statement$DoStatementSubIndex ::= do $ Ident $StartIndex in $ Ident $StartParentIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex in $ Ident $EndParentIndex
69     Statement$PardoStatement ::= pardo $ Indices $StartIndices EOLs $ WhereClauseList StatementList endpardo $ Indices $EndIndices
70     Statement$Section ::= section $ EOLs $ StatementList endsection $
71     Statement$ExitStatement ::= exit $
72     Statement$IfStatement ::= if $ RelationalExpression EOLs $ StatementList endif $
73     Statement$IfElseStatement ::= if $ RelationalExpression EOLs $ StatementList $ifStatements else $ EOLs $ StatementList $elseStatements endif $
74     Statement$AllocateStatement ::= allocate $ Ident AllocIndexListopt
75     Statement$DeallocateStatement ::= deallocate $ Ident AllocIndexListopt
76     AllocIndex$AllocIndexIdent ::= Ident
77     AllocIndex$AllocIndexWildCard ::= * $
78     AllocIndexList$$AllocIndex ::= AllocIndex
79                      | AllocIndexList , $ AllocIndex
80     AllocIndexListopt ::= $Empty
81                         | [ AllocIndexList ]
82     Statement$ContiguousAllocateStatement ::= allocate contiguous $ Ident [ $ ContiguousAllocIndexExprList ] $
83     Statement$ContiguousDeallocateStatement ::= deallocate contiguous $ Ident [ $ ContiguousAllocIndexExprList ] $
84     ContiguousAllocIndexExpr$ContiguousAllocIndexSingleExpr ::= Expression
85     ContiguousAllocIndexExpr$ContiguousAllocIndexRangeExpr ::= Expression $StartExpr : Expression $EndExpr
86     ContiguousAllocIndexExprList$$ContiguousAllocIndexExpr ::= ContiguousAllocIndexExpr
87                                    | ContiguousAllocIndexExprList , $ ContiguousAllocIndexExpr
88     Statement$CreateStatement ::= create $ Ident
89     Statement$DeleteStatement ::= delete $ Ident
90     Statement$PutStatement ::= put $ DataBlock $LHSDataBlock AssignOp DataBlock $RHSDataBlock
91     Statement$GetStatement ::= get $ DataBlock
92     Statement$PrepareStatement ::= prepare $ DataBlock $LHSDataBlock AssignOp DataBlock $RHSDataBlock
93     Statement$RequestStatement ::= request $ DataBlock
94     Statement$CollectiveStatement ::= collective $ Ident AssignOp Expression
95     Statement$DestroyStatement ::= destroy $ Ident
96     Statement$PrintStatement ::= print $ Expression
97     Statement$PrintlnStatement ::= println $ Expression
98     Arg$DataBlockArg ::= DataBlock
99     Arg$IdentArg ::= IDENTIFIER
100    Arg$DoubleLitArg ::= DOUBLELIT
101    Arg$IntLitArg ::= INTLIT
102    ArgList$$Arg ::= $Empty
103              | ArgList Arg
104    Statement$ExecuteStatement ::= execute $ Ident ArgList
105    Statement$AssignToIdent ::= Ident AssignOp Expression
106    Statement$AssignToBlock ::= DataBlock AssignOp Expression
107    Statement$GPUSection ::= gpu_on $ EOLs $ StatementList gpu_off $
108    Statement$GPUAllocate ::= gpu_allocate $ Arg
109    Statement$GPUFree ::= gpu_free $ Arg
110    Statement$GPUPut ::= gpu_put $ Arg
111    Statement$GPUGet ::= gpu_get $ Arg
112    Statement$SetPersistent ::= set_persistent $ Ident StringLiteral
113    Statement$RestorePersistent ::= restore_persistent $ Ident StringLiteral
114    Statement$AssertSame ::= assert_same $ Ident
115    Statement$BroadcastStatic ::= broadcast_from $ Primary Ident
116    AssignOp$AssignOp ::= = $op
117               | += $op
118               | -= $op
119               | *= $op
120    DataBlock ::= Ident [ $ Indices ] $
121    Indices$$Ident ::= Ident
122              | Indices , $ Ident
123    RelOp$RelOp ::= < $op
124            | > $op
125            | <= $op
126            | >= $op
127            | == $op
128            | != $op
129    RelationalExpression ::= Expression $UnaryExpressionLeft RelOp Expression $UnaryExpressionRight
130    Expression ::= Term
131    Expression$AddExpr ::= Expression + Term
132    Expression$SubtractExpr ::= Expression - Term
133    Term ::= ExponentExpression
134    Term$StarExpr ::= Term * $ ExponentExpression
135    Term$DivExpr ::= Term / $ ExponentExpression
136    Term$TensorExpr ::= Term ^ ExponentExpression
137    ExponentExpression ::= CastExpression
138    ExponentExpression$ExponentExpr ::= CastExpression ** ExponentExpression
139    CastExpression ::= UnaryExpression
140    CastExpression$IntCastExpr ::= ( $ int $ ) $ CastExpression
141    CastExpression$ScalarCastExpr ::= ( $ scalar $ ) $ CastExpression
142    UnaryExpression ::= Primary
143    UnaryExpression$NegatedUnaryExpr ::= - $ Primary
144    UnaryExpression$SqrtUnaryExpr ::= sqrt $ Primary
145    Primary$ParenExpr ::= ( $ Expression ) $
146    Primary$IntLitExpr ::= INTLIT
147    Primary$DoubleLitExpr ::= DOUBLELIT
148    Primary$IdentExpr ::= Ident
149    Primary$DataBlockExpr ::= DataBlock
150    Primary$StringLitExpr ::= StringLiteral
151    StringLiteral ::= STRINGLIT
152    Ident ::= IDENTIFIER
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:27:1:27:5:302:306: Informative: The terminal cycle is useless.
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:36:1:36:8:351:358: Informative: The terminal prequest is useless.
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:72:1:72:11:686:696: Informative: The terminal print_index is useless.
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:73:1:73:12:698:709: Informative: The terminal print_scalar is useless.
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:74:1:74:9:711:719: Informative: The terminal print_int is useless.


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
LEFTSQUARE ::= [
RIGHTSQUARE ::= ]
COLON ::= :
EXP ::= **
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
print_int ::= print_int
contiguous ::= contiguous
gpu_on ::= gpu_on
gpu_off ::= gpu_off
gpu_allocate ::= gpu_allocate
gpu_free ::= gpu_free
gpu_put ::= gpu_put
gpu_get ::= gpu_get
set_persistent ::= set_persistent
restore_persistent ::= restore_persistent
sparse ::= sparse
assert_same ::= assert_same
broadcast_from ::= broadcast_from
sqrt ::= sqrt
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
AllocIndex ::= AllocIndex
AllocIndexList ::= AllocIndexList
ContiguousAllocIndexExprList ::= ContiguousAllocIndexExprList
ContiguousAllocIndexExpr ::= ContiguousAllocIndexExpr
Expression ::= Expression
DataBlock ::= DataBlock
AssignOp ::= AssignOp
Arg ::= Arg
StringLiteral ::= StringLiteral
Primary ::= Primary
RelOp ::= RelOp
Term ::= Term
ExponentExpression ::= ExponentExpression
CastExpression ::= CastExpression
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
ScalarInitializationOpt ::= 
IntInitializationOpt ::= 
IdentOpt ::= 
WhereClauseList ::= 
AllocIndexListopt ::= 
ArgList ::= 
UnaryExpression ::= 

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
Primary ::= LEFTPAREN Expression  .RIGHTPAREN 
StatementList ::= StatementList Statement  .EOLs 

