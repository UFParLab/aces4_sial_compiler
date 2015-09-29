
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
61     WhereClauseList$$WhereClause ::= $Empty
62                       | WhereClauseList WhereClause EOLs $
63     Statement$CallStatement ::= call $ Ident
64     Statement$ReturnStatement ::= return $
65     Statement$StopStatement ::= stop $
66     Statement$ServerBarrierStatement ::= server_barrier $
67     Statement$SipBarrierStatement ::= sip_barrier $
68     Statement$DoStatement ::= do $ Ident $StartIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex
69     Statement$DoStatementSubIndex ::= do $ Ident $StartIndex in $ Ident $StartParentIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex in $ Ident $EndParentIndex
70     Statement$PardoStatement ::= pardo $ Indices $StartIndices PardoPragma $Pragma EOLs $ WhereClauseList StatementList endpardo $ Indices $EndIndices
71     PardoPragma$PardoPragma ::= $Empty
72                   | StringLiteral
73     Statement$Section ::= section $ EOLs $ StatementList endsection $
74     Statement$ExitStatement ::= exit $
75     Statement$IfStatement ::= if $ RelationalExpression EOLs $ StatementList endif $
76     Statement$IfElseStatement ::= if $ RelationalExpression EOLs $ StatementList $ifStatements else $ EOLs $ StatementList $elseStatements endif $
77     Statement$AllocateStatement ::= allocate $ Ident AllocIndexListopt
78     Statement$DeallocateStatement ::= deallocate $ Ident AllocIndexListopt
79     AllocIndex$AllocIndexIdent ::= Ident
80     AllocIndex$AllocIndexWildCard ::= * $
81     AllocIndexList$$AllocIndex ::= AllocIndex
82                      | AllocIndexList , $ AllocIndex
83     AllocIndexListopt ::= $Empty
84                         | [ AllocIndexList ]
85     Statement$ContiguousAllocateStatement ::= allocate contiguous $ Ident [ $ ContiguousIndexRangeExprList ] $
86     Statement$ContiguousDeallocateStatement ::= deallocate contiguous $ Ident [ $ ContiguousIndexRangeExprList ] $
87     ContiguousIndexRangeExpr$ContiguousIndexRangeExpr ::= Expression $StartExpr : Expression $EndExpr
88     ContiguousIndexRangeExprList$$ContiguousIndexRangeExpr ::= ContiguousIndexRangeExpr
89                                    | ContiguousIndexRangeExprList , $ ContiguousIndexRangeExpr
90     Statement$CreateStatement ::= create $ Ident
91     Statement$DeleteStatement ::= delete $ Ident
92     Statement$PutStatement ::= put $ DataBlock $LHSDataBlock AssignOp Expression $Expression
93     Statement$GetStatement ::= get $ DataBlock
94     Statement$PrepareStatement ::= prepare $ DataBlock $LHSDataBlock AssignOp Expression $Expression
95     Statement$RequestStatement ::= request $ DataBlock
96     Statement$CollectiveStatement ::= collective $ Ident AssignOp Expression
97     Statement$DestroyStatement ::= destroy $ Ident
98     Statement$PrintStatement ::= print $ Expression
99     Statement$PrintlnStatement ::= println $ Expression
100    Arg$ContiguousDataBlockArg ::= ContiguousDataBlock
101    Arg$DataBlockArg ::= DataBlock
102    Arg$IdentArg ::= IDENTIFIER
103    Arg$DoubleLitArg ::= DOUBLELIT
104    Arg$IntLitArg ::= INTLIT
105    ArgList$$Arg ::= $Empty
106              | ArgList Arg
107    Statement$ExecuteStatement ::= execute $ Ident ArgList
108    Statement$AssignToIdent ::= Ident AssignOp Expression
109    Statement$AssignToBlock ::= DataBlock AssignOp Expression
110    Statement$AssignToContigousDataBlock ::= ContiguousDataBlock AssignOp Expression
111    Statement$GPUSection ::= gpu_on $ EOLs $ StatementList gpu_off $
112    Statement$GPUAllocate ::= gpu_allocate $ Arg
113    Statement$GPUFree ::= gpu_free $ Arg
114    Statement$GPUPut ::= gpu_put $ Arg
115    Statement$GPUGet ::= gpu_get $ Arg
116    Statement$SetPersistent ::= set_persistent $ Ident StringLiteral
117    Statement$RestorePersistent ::= restore_persistent $ Ident StringLiteral
118    Statement$AssertSame ::= assert_same $ Ident
119    Statement$BroadcastStatic ::= broadcast_from $ Primary Ident
120    AssignOp$AssignOp ::= = $op
121               | += $op
122               | -= $op
123               | *= $op
124    DataBlock$DataBlock ::= Ident [ $ IndexCastIndices ] $
125    Indices$$Ident ::= Ident
126              | Indices , $ Ident
127    IndexCastIndices$$IndexCastIdent ::= IndexCastIdent
128                       | IndexCastIndices , $ IndexCastIdent
129    IndexCastIdent ::= IndexCastopt Ident
130    IndexCastopt ::= $Empty
131                   | ( $ index $ ) $IndexCast
132    ContiguousDataBlock$ContiguousDataBlock ::= Ident [ $ ContiguousIndexRangeExprList ] $
133    RelOp$RelOp ::= < $op
134            | > $op
135            | <= $op
136            | >= $op
137            | == $op
138            | != $op
139    RelationalExpression ::= Expression $CastExpressionLeft RelOp Expression $CastExpressionRight
140    Expression ::= Term
141    Expression$AddExpr ::= Expression + Term
142    Expression$SubtractExpr ::= Expression - Term
143    Term ::= ExponentExpression
144    Term$StarExpr ::= Term * $ ExponentExpression
145    Term$DivExpr ::= Term / $ ExponentExpression
146    Term$TensorExpr ::= Term ^ ExponentExpression
147    ExponentExpression ::= CastExpression
148    ExponentExpression$ExponentExpr ::= ExponentExpression ** CastExpression
149    CastExpression ::= UnaryExpression
150    CastExpression$IntCastExpr ::= ( $ int $ ) $ CastExpression
151    CastExpression$ScalarCastExpr ::= ( $ scalar $ ) $ CastExpression
152    CastExpression$IndexCastExpr ::= ( $ index $ ) $ CastExpression
153    UnaryExpression ::= Primary
154    UnaryExpression$NegatedUnaryExpr ::= - $ Primary
155    UnaryExpression$SqrtUnaryExpr ::= sqrt $ Primary
156    Primary$ParenExpr ::= ( $ Expression ) $
157    Primary$IntLitExpr ::= INTLIT
158    Primary$DoubleLitExpr ::= DOUBLELIT
159    Primary$IdentExpr ::= Ident
160    Primary$DataBlockExpr ::= DataBlock
161    Primary$ContiguousDataBlockExpr ::= ContiguousDataBlock
162    Primary$StringLitExpr ::= StringLiteral
163    StringLiteral ::= STRINGLIT
164    Ident ::= IDENTIFIER
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
StringLiteral ::= StringLiteral
AllocIndex ::= AllocIndex
AllocIndexList ::= AllocIndexList
ContiguousIndexRangeExprList ::= ContiguousIndexRangeExprList
ContiguousIndexRangeExpr ::= ContiguousIndexRangeExpr
Expression ::= Expression
DataBlock ::= DataBlock
AssignOp ::= AssignOp
Arg ::= Arg
ContiguousDataBlock ::= ContiguousDataBlock
Primary ::= Primary
IndexCastIndices ::= IndexCastIndices
IndexCastIdent ::= IndexCastIdent
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
PardoPragma ::= 
AllocIndexListopt ::= 
ArgList ::= 
IndexCastopt ::= 
UnaryExpression ::= 

 IBM LALR Parser Generator              2.0.17 (Jul 17 2009)


Scopes:

Statement ::= if RelationalExpression EOLs StatementList else EOLs 
              StatementList  .endif 
Statement ::= do Ident in Ident EOLs WhereClauseList StatementList  .enddo 
              Ident in Ident 
Statement ::= pardo Indices PardoPragma EOLs WhereClauseList StatementList  .
              endpardo Indices 
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

