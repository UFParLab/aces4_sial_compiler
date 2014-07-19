%options package=sial.imp.parser
%options template=LexerTemplateF.gi
%options filter=SialKWLexer.gi
--
--

%Globals
    /.
    //import java.util.*;
    import org.eclipse.imp.parser.ILexer;
    ./
%End

%Define
    $additional_interfaces /., ILexer./
    $kw_lexer_class /.$SialKWLexer./
%End

%Include
    LexerBasicMapF.gi
%End

%Export
    --
    -- List all the token types the lexer will directly process
    -- and export to the parser. If a keyword lexer is used as
    -- a filter for this lexer, it may export a set of keywords
    -- that will also be passed along to the parser.
    -- 
    -- For example:
    --
        SINGLE_LINE_COMMENT
--		MULTI_LINE_COMMENT
        IDENTIFIER 
        INTLIT
        DOUBLELIT
        COMMA
        PLUS
        MINUS
        SLASH
        STAR
        TENSOR
        GREATER
        GEQ
        LESS
        LEQ
        EQ
        NEQ
--        AND
--        OR
        ASSIGN
        PLUS_ASSIGN
        MINUS_ASSIGN
        STAR_ASSIGN
        LEFTPAREN
        RIGHTPAREN
		LEFTSQUARE
		RIGHTSQUARE
        EOL
		STRINGLIT
		COLON
		EXP
%End

%Terminals
    CtlCharNotWS

    LF   CR   HT   FF

    a    b    c    d    e    f    g    h    i    j    k    l    m
    n    o    p    q    r    s    t    u    v    w    x    y    z
    _

    A    B    C    D    E    F    G    H    I    J    K    L    M
    N    O    P    Q    R    S    T    U    V    W    X    Y    Z

    0    1    2    3    4    5    6    7    8    9

    AfterASCII   ::= '\u0080..\ufffe'
    Space        ::= ' '
    LF           ::= NewLine
    CR           ::= Return
    HT           ::= HorizontalTab
    FF           ::= FormFeed
    DoubleQuote  ::= '"'
    SingleQuote  ::= "'"
    Percent      ::= '%'
    VerticalBar  ::= '|'
    Exclamation  ::= '!'
    AtSign       ::= '@'
    BackQuote    ::= '`'
    Tilde        ::= '~'
    Sharp        ::= '#'
    DollarSign   ::= '$'
    Ampersand    ::= '&'
    Caret        ::= '^'
    Colon        ::= ':'
    SemiColon    ::= ';'
    BackSlash    ::= '\'
    LeftBrace    ::= '{'
    RightBrace   ::= '}'
    LeftBracket  ::= '['
    RightBracket ::= ']'
    QuestionMark ::= '?'
    Comma        ::= ','
    Dot          ::= '.'
    LessThan     ::= '<'
    GreaterThan  ::= '>'
    Plus         ::= '+'
    Minus        ::= '-'
    Slash        ::= '/'
    Star         ::= '*'
    LeftParen    ::= '('
    RightParen   ::= ')'
    Equal        ::= '='
	Exp ::= '**'

%End

%Start
    Token
%End

%Rules
    Token ::= identifier    /.    checkForKeyWord();./
            | intlit        /.    makeToken($_INTLIT);./
            | doublelit 	    /.    makeToken($_DOUBLELIT);./
			| stringlit  /.  makeToken($_STRINGLIT);  ./
            | white         /.    skipToken();./
            | slc           /.    makeComment($_SINGLE_LINE_COMMENT);./							  
--		    | mlc       /. makeComment($_MULTI_LINE_COMMENT);./
            | ','           /.    makeToken($_COMMA);./
            | '+'           /.    makeToken($_PLUS);./
            | '-'           /.    makeToken($_MINUS);./
            | '/'           /.    makeToken($_SLASH);./
            | '*'           /.    makeToken($_STAR);./
            | '^'           /.    makeToken($_TENSOR);./
            | '('           /.    makeToken($_LEFTPAREN);./
            | ')'           /.    makeToken($_RIGHTPAREN);./
            | '>'           /.    makeToken($_GREATER);./
            | '>' '='       /.    makeToken($_GEQ);./
            | '<'           /.    makeToken($_LESS);./
            | '<' '='       /.    makeToken($_LEQ);./            
            | '=' '='       /.    makeToken($_EQ);./
            | '!' '='       /.    makeToken($_NEQ);./
--            | '&' '&'       /.    makeToken($_AND);./
--            | '|' '|'       /.    makeToken($_OR);./
            | '='           /.    makeToken($_ASSIGN);./
            | '+' '='	    /.    makeToken($_PLUS_ASSIGN);./
            | '-' '='	    /.    makeToken($_MINUS_ASSIGN);./
            | '*' '='	    /.    makeToken($_STAR_ASSIGN);./
            | eol           /.    makeToken($_EOL);./
		     | '['       /. makeToken($_LEFTSQUARE);./
            | ']'      /. makeToken($_RIGHTSQUARE);./   
            | ':'      /.makeToken($_COLON);./
			| '**'   /.makeToken($_EXP);./			 
--	    mlc  ::= '/' '#' Inside Hashes '/'

	stringlit ::=  '"' SLBody '"'
	
    identifier  ::= letter
                | identifier letter
                | identifier digit
                | identifier  '_'
				| identifier '.'

		
    intlit ::= digit
             | intlit digit
             
    eol ::= LF | CR 

   doublelit ::= intlit '.'
              |  intlit '.' intlit
              | '.'  intlit
    
    white ::= whiteChar
            | white whiteChar

    slc ::= '#' 
          | slc notEOL
		  
		  
--	 Inside ::= Inside Hashes NotSlashOrHash
--             | Inside '/'
--             | Inside NotSlashOrHash
 --            | %Empty

--   SpecialNotSlash -> '+' | '-' | -- exclude the hash as well
--                       '(' | ')' | '"' | '!' | '@' | '`' | '~' |
--                       '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
--                       '[' | ']' | '?' | ',' | '.' | '<' | '>' | '=' | '*'
	
--	    NotSlashOrHash -> letter
--                   | digit
--                    | SpecialNotSlash
--                    | whiteChar

--	 Hashes -> '#'
--           | Hashes '#'
	
	SLBody ::= %empty  | SLBody NotDQ
	

	
	    NotDQ -> letter
           | digit
           | specialNotDQ
           | whiteChar

					
					
    digit ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

    aA ::= a | A
    bB ::= b | B
    cC ::= c | C
    dD ::= d | D
    eE ::= e | E
    fF ::= f | F
    gG ::= g | G
    hH ::= h | H
    iI ::= i | I
    jJ ::= j | J
    kK ::= k | K
    lL ::= l | L
    mM ::= m | M
    nN ::= n | N
    oO ::= o | O
    pP ::= p | P
    qQ ::= q | Q
    rR ::= r | R
    sS ::= s | S
    tT ::= t | T
    uU ::= u | U
    vV ::= v | V
    wW ::= w | W
    xX ::= x | X
    yY ::= y | Y
    zZ ::= z | Z

    letter ::= aA | bB | cC | dD | eE | fF | gG | hH | iI | jJ | kK | lL | mM | nN | oO | pP | qQ | rR | sS | tT | uU | vV | wW | xX | yY | zZ

     --white space does not inlcude LF and CR as these are significant in SIAL
	    whiteChar ::= Space | HT | FF

   specialNotDQ  ::= '+' | '-' | '(' | ')' |  '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'

   notEOL ::= letter | digit | special | whiteChar
   
   special ::= specialNotDQ | '"'

--        escapeSequence -> 
--                    '\' b
 --                   | '\' t
--                    | '\' n
--                    | '\' f
--                    | '\' r
--                    | '\' '"'
--                    | '\' "'"
--                    | '\' '`'
--                    | '\' '\'

%End



 