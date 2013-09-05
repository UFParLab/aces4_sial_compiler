
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
    persistent
    special
    server_barrier
    sip_barrier
    constant
    configure
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
15     Modifier$Sip_ConsistentModifier ::= sip_consistent
16     Modifier$PredefinedModifier ::= predefined
17     Modifier$PersistentModifier ::= persistent
18     Modifier$ScopedExtent ::= scoped_extent
19     Modifier$Contiguous ::= contiguous
20     Modifier$Auto_AllocateModifier ::= auto_allocate
21     DecList$$Dec ::= $Empty
22               | DecList Dec EOLs $
23     Dec ::= ScalarDec
24           | ArrayDec
25           | IndexDec
26           | SubIndexDec
27           | IntDec
28           | ProcDec
29           | SpecialDec
30     ScalarDec ::= Modifiersopt scalar $ Ident
31     IntDec ::= Modifiersopt int $ Ident
32     ArrayDec ::= Modifiersopt ArrayKind Ident ( $ DimensionList ) $
33     ArrayKind$ArrayKind ::= static $akind
34                 | temp $akind
35                 | local $akind
36                 | distributed $akind
37                 | served $akind
38     DimensionList$$Dimension ::= Dimension
39                     | DimensionList , $ Dimension
40     Dimension ::= Ident
41     IndexDec ::= Modifiersopt IndexKind Ident = $ Range
42     IndexKind$IndexKind ::= aoindex $ikind
43                 | moindex $ikind
44                 | moaindex $ikind
45                 | mobindex $ikind
46                 | index $ikind
47                 | laindex $ikind
48     SubIndexDec$SubIndexDec ::= subindex $ Ident of $ Ident $ParentIdent
49     Range ::= RangeVal $RangeValStart , $ RangeVal $RangeValEnd
50     RangeVal$IntLitRangeVal ::= INTLIT
51     RangeVal$IdentRangeVal ::= Ident
52     ProcDec ::= proc $ Ident EOLs $ StatementList endproc Ident $endIdent
53     SpecialDec ::= special $ Ident IdentOpt $Signature
54     IdentOpt ::= Ident
55                | $Empty
56     StatementList$$Statement ::= $Empty
57                     | StatementList Statement EOLs $
58     WhereClause ::= where $ RelationalExpression
59     WhereClauseList ::= $Empty
60                       | WhereClauseList WhereClause EOLs $
61     Statement$CallStatement ::= call $ Ident
62     Statement$ReturnStatement ::= return $
63     Statement$ServerBarrierStatement ::= server_barrier $ IdentOpt
64     Statement$SipBarrierStatement ::= sip_barrier $ IdentOpt
65     Statement$DoStatement ::= do $ Ident $StartIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex
66     Statement$DoStatementSubIndex ::= do $ Ident $StartIndex in $ Ident $StartParentIndex EOLs $ WhereClauseList StatementList enddo $ Ident $EndIndex in $ Ident $EndParentIndex
67     Statement$PardoStatement ::= pardo $ Indices $StartIndices EOLs $ WhereClauseList StatementList endpardo $ Indices $EndIndices
68     Statement$Section ::= section $ EOLs $ StatementList endsection $
69     Statement$ExitStatement ::= exit $
70     Statement$CycleStatement ::= cycle $ Ident
71     Statement$IfStatement ::= if $ RelationalExpression EOLs $ StatementList endif $
72     Statement$IfElseStatement ::= if $ RelationalExpression EOLs $ StatementList $ifStatements else $ EOLs $ StatementList $elseStatements endif $
73     Statement$AllocateStatement ::= allocate $ Ident AllocIndexListopt
74     Statement$DeallocateStatement ::= deallocate $ Ident AllocIndexListopt
75     Statement$CreateStatement ::= create $ Ident AllocIndexListopt
76     Statement$DeleteStatement ::= delete $ Ident AllocIndexListopt
77     Statement$PutStatement ::= put $ DataBlock $LHSDataBlock AssignOp DataBlock $RHSDataBlock
78     Statement$GetStatement ::= get $ DataBlock
79     Statement$PrepareStatement ::= prepare $ DataBlock $LHSDataBlock AssignOp DataBlock $RHSDataBlock
80     Statement$RequestStatement ::= request $ DataBlock Ident
81     Statement$PrequestStatement ::= prequest $ DataBlock $LHSDataBlock = $ DataBlock $RHSDataBlock
82     Statement$CollectiveStatement ::= collective $ Ident $LHSIdent AssignOp Ident $RHSIdent
83     Statement$DestroyStatement ::= destroy $ Ident
84     Statement$PrintlnStatement ::= println $ STRINGLIT
85     Statement$PrintStatement ::= print $ STRINGLIT
86     Statement$PrintIndexStatement ::= print_index $ Ident
87     Statement$PrintScalarStatement ::= print_scalar $ Ident
88     Arg ::= Primary
89     ArgList$$Arg ::= $Empty
90               | ArgList Arg
91     Statement$ExecuteStatement ::= execute $ Ident ArgList
92     Statement$AssignStatement ::= ScalarOrBlockVar AssignOp Expression
93     AssignOp$AssignOpEqual ::= =
94     AssignOp$AssignOpPlus ::= +=
95     AssignOp$AssignOpMinus ::= -=
96     AssignOp$AssignOpStar ::= *=
97     ScalarOrBlockVar ::= Ident
98                        | DataBlock
99     DataBlock ::= Ident ( $ Indices ) $
100    Indices$$Ident ::= Ident
101              | Indices , $ Ident
102    AllocIndex$AllocIndexIdent ::= Ident
103    AllocIndex$AllocIndexWildCard ::= * $
104    AllocIndexList$$AllocIndex ::= AllocIndex
105                     | AllocIndexList , $ AllocIndex
106    AllocIndexListopt ::= $Empty
107                        | ( AllocIndexList )
108    RelationalExpression ::= UnaryExpression $UnaryExpressionLeft RelOp UnaryExpression $UnaryExpressionRight
109    RelOp$RelOp ::= < $op
110            | > $op
111            | <= $op
112            | >= $op
113            | == $op
114            | != $op
115    Expression ::= UnaryExpression $UnaryExpression
116                 | BinaryExpression $BinaryExpression
117    BinaryExpression ::= UnaryExpression $Expr1 BinOp UnaryExpression $Expr2
118    BinOp$BinOpStar ::= *
119    BinOp$BinOpDiv ::= /
120    BinOp$BinOpPlus ::= +
121    BinOp$BinOpMinus ::= -
122    BinOp$BinOpTensor ::= ^
123    UnaryExpression ::= Primary
124    UnaryExpression$NegatedUnary ::= - $ Primary
125    Primary$IntLitPrimary ::= INTLIT
126    Primary$DoubleLitPrimary ::= DOUBLELIT
127    Primary$IdentPrimary ::= Ident
128    Primary$DataBlockPrimary ::= DataBlock
129    Primary$StringLitPrimary ::= StringLiteral
130    StringLiteral ::= STRINGLIT
131    Ident ::= IDENTIFIER
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:67:1:67:8:621:628: Informative: The terminal constant is useless.
/home/basbas/aces4_compiler/edu.ufl.cise.sial.compiler/src/sial/parser/SialKWLexer.gi:68:1:68:9:631:639: Informative: The terminal configure is useless.


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
persistent ::= persistent
special ::= special
server_barrier ::= server_barrier
sip_barrier ::= sip_barrier
constant ::= constant
configure ::= configure
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
StringLiteral ::= 

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
Statement ::= section EOLs StatementList  .endsection 
StatementList ::= StatementList Statement  .EOLs 

