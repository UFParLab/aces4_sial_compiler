
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
50     Range ::= RangeVal $RangeValStart , $ RangeVal $RangeValEnd
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
65     Statement$ServerBarrierStatement ::= server_barrier $ IdentOpt
66     Statement$SipBarrierStatement ::= sip_barrier $ IdentOpt
67     Statement$DoStatement ::= do $ Ident $StartIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex
68     Statement$DoStatementSubIndex ::= do $ Ident $StartIndex in $ Ident $StartParentIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex in $ Ident $EndParentIndex
69     Statement$PardoStatement ::= pardo $ Indices $StartIndices EOLs $ WhereClauseList StatementList endpardo $ Indices $EndIndices
70     Statement$Section ::= section $ EOLs $ StatementList endsection $
71     Statement$ExitStatement ::= exit $
72     Statement$CycleStatement ::= cycle $ Ident
73     Statement$IfStatement ::= if $ RelationalExpression EOLs $ StatementList endif $
74     Statement$IfElseStatement ::= if $ RelationalExpression EOLs $ StatementList $ifStatements else $ EOLs $ StatementList $elseStatements endif $
75     Statement$AllocateStatement ::= allocate $ Ident AllocIndexListopt
76     Statement$DeallocateStatement ::= deallocate $ Ident AllocIndexListopt
77     Statement$CreateStatement ::= create $ Ident AllocIndexListopt
78     Statement$DeleteStatement ::= delete $ Ident AllocIndexListopt
79     Statement$PutStatement ::= put $ DataBlock $LHSDataBlock AssignOp DataBlock $RHSDataBlock
80     Statement$GetStatement ::= get $ DataBlock
81     Statement$PrepareStatement ::= prepare $ DataBlock $LHSDataBlock AssignOp DataBlock $RHSDataBlock
82     Statement$RequestStatement ::= request $ DataBlock IdentOpt
83     Statement$PrequestStatement ::= prequest $ DataBlock $LHSDataBlock = $ DataBlock $RHSDataBlock
84     Statement$CollectiveStatement ::= collective $ Ident $LHSIdent AssignOp Ident $RHSIdent
85     Statement$DestroyStatement ::= destroy $ Ident
86     Statement$PrintlnStatement ::= println $ StringLiteral
87     Statement$PrintStatement ::= print $ StringLiteral
88     Statement$PrintIndexStatement ::= print_index $ Ident
89     Statement$PrintScalarStatement ::= print_scalar $ Ident
90     Statement$PrintIntStatement ::= print_int $ Ident
91     Arg$DataBlockArg ::= DataBlock
92     Arg$IdentArg ::= IDENTIFIER
93     Arg$DoubleLitArg ::= DOUBLELIT
94     ArgList$$Arg ::= $Empty
95               | ArgList Arg
96     Statement$ExecuteStatement ::= execute $ Ident ArgList
97     Statement$AssignToIdent ::= Ident AssignOp Expression
98     Statement$AssignToBlock ::= DataBlock AssignOp Expression
99     Statement$GPUSection ::= gpu_on $ EOLs $ StatementList gpu_off $
100    Statement$GPUAllocateBlock ::= gpu_allocate $ Arg
101    Statement$GPUFreeBlock ::= gpu_free $ Arg
102    Statement$GPUPutBlock ::= gpu_put $ Arg
103    Statement$GPUGetBlock ::= gpu_get $ Arg
104    Statement$SetPersistent ::= set_persistent $ Ident StringLiteral
105    Statement$RestorePersistent ::= restore_persistent $ Ident StringLiteral
106    Statement$AssertSame ::= assert_same $ Ident
107    AssignOp$AssignOp ::= = $op
108               | += $op
109               | -= $op
110               | *= $op
111    DataBlock ::= Ident [ $ Indices ] $
112    Indices$$Ident ::= Ident
113              | Indices , $ Ident
114    AllocIndex$AllocIndexIdent ::= Ident
115    AllocIndex$AllocIndexWildCard ::= * $
116    AllocIndexList$$AllocIndex ::= AllocIndex
117                     | AllocIndexList , $ AllocIndex
118    AllocIndexListopt ::= $Empty
119                        | [ AllocIndexList ]
120    RelOp$RelOp ::= < $op
121            | > $op
122            | <= $op
123            | >= $op
124            | == $op
125            | != $op
126    RelationalExpression ::= Expression $UnaryExpressionLeft RelOp Expression $UnaryExpressionRight
127    Expression ::= Term
128    Expression$AddExpr ::= Expression + Term
129    Expression$SubtractExpr ::= Expression - Term
130    Term ::= CastExpression
131    Term$StarExpr ::= Term * $ CastExpression
132    Term$DivExpr ::= Term / $ CastExpression
133    Term$TensorExpr ::= Term ^ CastExpression
134    CastExpression ::= UnaryExpression
135    CastExpression$IntCastExpr ::= ( $ int $ ) $ CastExpression
136    CastExpression$ScalarCastExpr ::= ( $ scalar $ ) $ CastExpression
137    UnaryExpression ::= Primary
138    UnaryExpression$NegatedUnaryExpr ::= - $ Primary
139    Primary$ParenExpr ::= ( $ Expression ) $
140    Primary$IntLitExpr ::= INTLIT
141    Primary$DoubleLitExpr ::= DOUBLELIT
142    Primary$IdentExpr ::= Ident
143    Primary$DataBlockExpr ::= DataBlock
144    Primary$StringLitExpr ::= StringLiteral
145    StringLiteral ::= STRINGLIT
146    Ident ::= IDENTIFIER


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
Expression ::= Expression
AllocIndex ::= AllocIndex
AllocIndexList ::= AllocIndexList
RelOp ::= RelOp
Term ::= Term
CastExpression ::= CastExpression
Primary ::= Primary
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

