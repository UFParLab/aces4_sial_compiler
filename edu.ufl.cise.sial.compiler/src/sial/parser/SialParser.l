
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
    stop
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
65     Statement$StopStatement ::= stop $
66     Statement$ServerBarrierStatement ::= server_barrier $
67     Statement$SipBarrierStatement ::= sip_barrier $
68     Statement$DoStatement ::= do $ Ident $StartIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex
69     Statement$DoStatementSubIndex ::= do $ Ident $StartIndex in $ Ident $StartParentIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex in $ Ident $EndParentIndex
70     Statement$PardoStatement ::= pardo $ Indices $StartIndices EOLs $ WhereClauseList StatementList endpardo $ Indices $EndIndices
71     Statement$Section ::= section $ EOLs $ StatementList endsection $
72     Statement$ExitStatement ::= exit $
73     Statement$IfStatement ::= if $ RelationalExpression EOLs $ StatementList endif $
74     Statement$IfElseStatement ::= if $ RelationalExpression EOLs $ StatementList $ifStatements else $ EOLs $ StatementList $elseStatements endif $
75     Statement$AllocateStatement ::= allocate $ Ident AllocIndexListopt
76     Statement$DeallocateStatement ::= deallocate $ Ident AllocIndexListopt
77     AllocIndex$AllocIndexIdent ::= Ident
78     AllocIndex$AllocIndexWildCard ::= * $
79     AllocIndexList$$AllocIndex ::= AllocIndex
80                      | AllocIndexList , $ AllocIndex
81     AllocIndexListopt ::= $Empty
82                         | [ AllocIndexList ]
83     Statement$ContiguousAllocateStatement ::= allocate contiguous $ Ident [ $ ContiguousIndexRangeExprList ] $
84     Statement$ContiguousDeallocateStatement ::= deallocate contiguous $ Ident [ $ ContiguousIndexRangeExprList ] $
85     ContiguousIndexRangeExpr$ContiguousIndexRangeExpr ::= Expression $StartExpr : Expression $EndExpr
86     ContiguousIndexRangeExprList$$ContiguousIndexRangeExpr ::= ContiguousIndexRangeExpr
87                                    | ContiguousIndexRangeExprList , $ ContiguousIndexRangeExpr
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
98     Arg$ContiguousDataBlockArg ::= ContiguousDataBlock
99     Arg$DataBlockArg ::= DataBlock
100    Arg$IdentArg ::= IDENTIFIER
101    Arg$DoubleLitArg ::= DOUBLELIT
102    Arg$IntLitArg ::= INTLIT
103    ArgList$$Arg ::= $Empty
104              | ArgList Arg
105    Statement$ExecuteStatement ::= execute $ Ident ArgList
106    Statement$AssignToIdent ::= Ident AssignOp Expression
107    Statement$AssignToBlock ::= DataBlock AssignOp Expression
108    Statement$AssignToContigousDataBlock ::= ContiguousDataBlock AssignOp Expression
109    Statement$GPUSection ::= gpu_on $ EOLs $ StatementList gpu_off $
110    Statement$GPUAllocate ::= gpu_allocate $ Arg
111    Statement$GPUFree ::= gpu_free $ Arg
112    Statement$GPUPut ::= gpu_put $ Arg
113    Statement$GPUGet ::= gpu_get $ Arg
114    Statement$SetPersistent ::= set_persistent $ Ident StringLiteral
115    Statement$RestorePersistent ::= restore_persistent $ Ident StringLiteral
116    Statement$AssertSame ::= assert_same $ Ident
117    Statement$BroadcastStatic ::= broadcast_from $ Primary Ident
118    AssignOp$AssignOp ::= = $op
119               | += $op
120               | -= $op
121               | *= $op
122    DataBlock$DataBlock ::= Ident [ $ Indices ] $
123    Indices$$Ident ::= Ident
124              | Indices , $ Ident
125    ContiguousDataBlock$ContiguousDataBlock ::= Ident [ $ ContiguousIndexRangeExprList ] $
126    RelOp$RelOp ::= < $op
127            | > $op
128            | <= $op
129            | >= $op
130            | == $op
131            | != $op
132    RelationalExpression ::= Expression $UnaryExpressionLeft RelOp Expression $UnaryExpressionRight
133    Expression ::= Term
134    Expression$AddExpr ::= Expression + Term
135    Expression$SubtractExpr ::= Expression - Term
136    Term ::= ExponentExpression
137    Term$StarExpr ::= Term * $ ExponentExpression
138    Term$DivExpr ::= Term / $ ExponentExpression
139    Term$TensorExpr ::= Term ^ ExponentExpression
140    ExponentExpression ::= CastExpression
141    ExponentExpression$ExponentExpr ::= ExponentExpression ** CastExpression
142    CastExpression ::= UnaryExpression
143    CastExpression$IntCastExpr ::= ( $ int $ ) $ CastExpression
144    CastExpression$ScalarCastExpr ::= ( $ scalar $ ) $ CastExpression
145    UnaryExpression ::= Primary
146    UnaryExpression$NegatedUnaryExpr ::= - $ Primary
147    UnaryExpression$SqrtUnaryExpr ::= sqrt $ Primary
148    Primary$ParenExpr ::= ( $ Expression ) $
149    Primary$IntLitExpr ::= INTLIT
150    Primary$DoubleLitExpr ::= DOUBLELIT
151    Primary$IdentExpr ::= Ident
152    Primary$DataBlockExpr ::= DataBlock
153    Primary$ContiguousDataBlockExpr ::= ContiguousDataBlock
154    Primary$StringLitExpr ::= StringLiteral
155    StringLiteral ::= STRINGLIT
156    Ident ::= IDENTIFIER
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
stop ::= stop
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
ContiguousIndexRangeExprList ::= ContiguousIndexRangeExprList
ContiguousIndexRangeExpr ::= ContiguousIndexRangeExpr
Expression ::= Expression
DataBlock ::= DataBlock
AssignOp ::= AssignOp
Arg ::= Arg
ContiguousDataBlock ::= ContiguousDataBlock
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
ContiguousDataBlock ::= Ident LEFTSQUARE ContiguousIndexRangeExprList  .
                        RIGHTSQUARE 
Statement ::= gpu_on EOLs StatementList  .gpu_off 
Statement ::= section EOLs StatementList  .endsection 
Primary ::= LEFTPAREN Expression  .RIGHTPAREN 
StatementList ::= StatementList Statement  .EOLs 
ContiguousIndexRangeExpr ::= Expression  .COLON Expression 

