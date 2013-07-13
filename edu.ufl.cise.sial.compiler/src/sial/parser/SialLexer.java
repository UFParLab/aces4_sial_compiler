package sial.parser;

import lpg.runtime.*;

//import java.util.*;
import org.eclipse.imp.parser.ILexer;

public class SialLexer implements RuleAction, ILexer
{
    private SialLexerLpgLexStream lexStream;
    
    private static ParseTable prs = new SialLexerprs();
    public ParseTable getParseTable() { return prs; }

    private LexParser lexParser = new LexParser();
    public LexParser getParser() { return lexParser; }

    public int getToken(int i) { return lexParser.getToken(i); }
    public int getRhsFirstTokenIndex(int i) { return lexParser.getFirstToken(i); }
    public int getRhsLastTokenIndex(int i) { return lexParser.getLastToken(i); }

    public int getLeftSpan() { return lexParser.getToken(1); }
    public int getRightSpan() { return lexParser.getLastToken(); }

    public void resetKeywordLexer()
    {
        if (kwLexer == null)
              this.kwLexer = new SialKWLexer(lexStream.getInputChars(), SialParsersym.TK_IDENTIFIER);
        else this.kwLexer.setInputChars(lexStream.getInputChars());
    }

    public void reset(String filename, int tab) throws java.io.IOException
    {
        lexStream = new SialLexerLpgLexStream(filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }

    public void reset(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }
    
    public void reset(char[] input_chars, String filename, int tab)
    {
        lexStream = new SialLexerLpgLexStream(input_chars, filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }
    
    public SialLexer(String filename, int tab) throws java.io.IOException 
    {
        reset(filename, tab);
    }

    public SialLexer(char[] input_chars, String filename, int tab)
    {
        reset(input_chars, filename, tab);
    }

    public SialLexer(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }

    public SialLexer() {}

    public ILexStream getILexStream() { return lexStream; }

    /**
     * @deprecated replaced by {@link #getILexStream()}
     */
    public ILexStream getLexStream() { return lexStream; }

    private void initializeLexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (lexStream.getInputChars() == null)
            throw new NullPointerException("LexStream was not initialized");
        lexStream.setPrsStream(prsStream);
        prsStream.makeToken(start_offset, end_offset, 0); // Token list must start with a bad token
    }

    private void addEOF(IPrsStream prsStream, int end_offset)
    {
        prsStream.makeToken(end_offset, end_offset, SialParsersym.TK_EOF_TOKEN); // and end with the end of file token
        prsStream.setStreamLength(prsStream.getSize());
    }

    public void lexer(IPrsStream prsStream)
    {
        lexer(null, prsStream);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream)
    {
        initializeLexer(prsStream, 0, -1);
        lexParser.parseCharacters(monitor);  // Lex the input characters
        addEOF(prsStream, lexStream.getStreamIndex());
    }

    public void lexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        lexer(null, prsStream, start_offset, end_offset);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (start_offset <= 1)
             initializeLexer(prsStream, 0, -1);
        else initializeLexer(prsStream, start_offset - 1, start_offset - 1);

        lexParser.parseCharacters(monitor, start_offset, end_offset);

        addEOF(prsStream, (end_offset >= lexStream.getStreamIndex() ? lexStream.getStreamIndex() : end_offset + 1));
    }

    /**
     * If a parse stream was not passed to this Lexical analyser then we
     * simply report a lexical error. Otherwise, we produce a bad token.
     */
    public void reportLexicalError(int startLoc, int endLoc) {
        IPrsStream prs_stream = lexStream.getPrsStream();
        if (prs_stream == null)
            lexStream.reportLexicalError(startLoc, endLoc);
        else {
            //
            // Remove any token that may have been processed that fall in the
            // range of the lexical error... then add one error token that spans
            // the error range.
            //
            for (int i = prs_stream.getSize() - 1; i > 0; i--) {
                if (prs_stream.getStartOffset(i) >= startLoc)
                     prs_stream.removeLastToken();
                else break;
            }
            prs_stream.makeToken(startLoc, endLoc, 0); // add an error token to the prsStream
        }        
    }

    //
    // The Lexer contains an array of characters as the input stream to be parsed.
    // There are methods to retrieve and classify characters.
    // The lexparser "token" is implemented simply as the index of the next character in the array.
    // The Lexer extends the abstract class LpgLexStream with an implementation of the abstract
    // method getKind.  The template defines the Lexer class and the lexer() method.
    // A driver creates the action class, "Lexer", passing an Option object to the constructor.
    //
    SialKWLexer kwLexer;
    boolean printTokens;
    private final static int ECLIPSE_TAB_VALUE = 4;

    public int [] getKeywordKinds() { return kwLexer.getKeywordKinds(); }

    public SialLexer(String filename) throws java.io.IOException
    {
        this(filename, ECLIPSE_TAB_VALUE);
        this.kwLexer = new SialKWLexer(lexStream.getInputChars(), SialParsersym.TK_IDENTIFIER);
    }

    /**
     * @deprecated function replaced by {@link #reset(char [] content, String filename)}
     */
    public void initialize(char [] content, String filename)
    {
        reset(content, filename);
    }
    
    final void makeToken(int left_token, int right_token, int kind)
    {
        lexStream.makeToken(left_token, right_token, kind);
    }
    
    final void makeToken(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.makeToken(startOffset, endOffset, kind);
        if (printTokens) printValue(startOffset, endOffset);
    }

    final void makeComment(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.getIPrsStream().makeAdjunct(startOffset, endOffset, kind);
    }

    final void skipToken()
    {
        if (printTokens) printValue(getLeftSpan(), getRightSpan());
    }
    
    final void checkForKeyWord()
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    //
    // This flavor of checkForKeyWord is necessary when the default kind
    // (which is returned when the keyword filter doesn't match) is something
    // other than _IDENTIFIER.
    //
    final void checkForKeyWord(int defaultKind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        if (kwKind == SialParsersym.TK_IDENTIFIER)
            kwKind = defaultKind;
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    final void printValue(int startOffset, int endOffset)
    {
        String s = new String(lexStream.getInputChars(), startOffset, endOffset - startOffset + 1);
        System.out.print(s);
    }

    //
    //
    //
    static class SialLexerLpgLexStream extends LpgLexStream
    {
    public final static int tokenKind[] =
    {
        SialLexersym.Char_CtlCharNotWS,    // 000    0x00
        SialLexersym.Char_CtlCharNotWS,    // 001    0x01
        SialLexersym.Char_CtlCharNotWS,    // 002    0x02
        SialLexersym.Char_CtlCharNotWS,    // 003    0x03
        SialLexersym.Char_CtlCharNotWS,    // 004    0x04
        SialLexersym.Char_CtlCharNotWS,    // 005    0x05
        SialLexersym.Char_CtlCharNotWS,    // 006    0x06
        SialLexersym.Char_CtlCharNotWS,    // 007    0x07
        SialLexersym.Char_CtlCharNotWS,    // 008    0x08
        SialLexersym.Char_HT,              // 009    0x09
        SialLexersym.Char_LF,              // 010    0x0A
        SialLexersym.Char_CtlCharNotWS,    // 011    0x0B
        SialLexersym.Char_FF,              // 012    0x0C
        SialLexersym.Char_CR,              // 013    0x0D
        SialLexersym.Char_CtlCharNotWS,    // 014    0x0E
        SialLexersym.Char_CtlCharNotWS,    // 015    0x0F
        SialLexersym.Char_CtlCharNotWS,    // 016    0x10
        SialLexersym.Char_CtlCharNotWS,    // 017    0x11
        SialLexersym.Char_CtlCharNotWS,    // 018    0x12
        SialLexersym.Char_CtlCharNotWS,    // 019    0x13
        SialLexersym.Char_CtlCharNotWS,    // 020    0x14
        SialLexersym.Char_CtlCharNotWS,    // 021    0x15
        SialLexersym.Char_CtlCharNotWS,    // 022    0x16
        SialLexersym.Char_CtlCharNotWS,    // 023    0x17
        SialLexersym.Char_CtlCharNotWS,    // 024    0x18
        SialLexersym.Char_CtlCharNotWS,    // 025    0x19
        SialLexersym.Char_CtlCharNotWS,    // 026    0x1A
        SialLexersym.Char_CtlCharNotWS,    // 027    0x1B
        SialLexersym.Char_CtlCharNotWS,    // 028    0x1C
        SialLexersym.Char_CtlCharNotWS,    // 029    0x1D
        SialLexersym.Char_CtlCharNotWS,    // 030    0x1E
        SialLexersym.Char_CtlCharNotWS,    // 031    0x1F
        SialLexersym.Char_Space,           // 032    0x20
        SialLexersym.Char_Exclamation,     // 033    0x21
        SialLexersym.Char_DoubleQuote,     // 034    0x22
        SialLexersym.Char_Sharp,           // 035    0x23
        SialLexersym.Char_DollarSign,      // 036    0x24
        SialLexersym.Char_Percent,         // 037    0x25
        SialLexersym.Char_Ampersand,       // 038    0x26
        SialLexersym.Char_SingleQuote,     // 039    0x27
        SialLexersym.Char_LeftParen,       // 040    0x28
        SialLexersym.Char_RightParen,      // 041    0x29
        SialLexersym.Char_Star,            // 042    0x2A
        SialLexersym.Char_Plus,            // 043    0x2B
        SialLexersym.Char_Comma,           // 044    0x2C
        SialLexersym.Char_Minus,           // 045    0x2D
        SialLexersym.Char_Dot,             // 046    0x2E
        SialLexersym.Char_Slash,           // 047    0x2F
        SialLexersym.Char_0,               // 048    0x30
        SialLexersym.Char_1,               // 049    0x31
        SialLexersym.Char_2,               // 050    0x32
        SialLexersym.Char_3,               // 051    0x33
        SialLexersym.Char_4,               // 052    0x34
        SialLexersym.Char_5,               // 053    0x35
        SialLexersym.Char_6,               // 054    0x36
        SialLexersym.Char_7,               // 055    0x37
        SialLexersym.Char_8,               // 056    0x38
        SialLexersym.Char_9,               // 057    0x39
        SialLexersym.Char_Colon,           // 058    0x3A
        SialLexersym.Char_SemiColon,       // 059    0x3B
        SialLexersym.Char_LessThan,        // 060    0x3C
        SialLexersym.Char_Equal,           // 061    0x3D
        SialLexersym.Char_GreaterThan,     // 062    0x3E
        SialLexersym.Char_QuestionMark,    // 063    0x3F
        SialLexersym.Char_AtSign,          // 064    0x40
        SialLexersym.Char_A,               // 065    0x41
        SialLexersym.Char_B,               // 066    0x42
        SialLexersym.Char_C,               // 067    0x43
        SialLexersym.Char_D,               // 068    0x44
        SialLexersym.Char_E,               // 069    0x45
        SialLexersym.Char_F,               // 070    0x46
        SialLexersym.Char_G,               // 071    0x47
        SialLexersym.Char_H,               // 072    0x48
        SialLexersym.Char_I,               // 073    0x49
        SialLexersym.Char_J,               // 074    0x4A
        SialLexersym.Char_K,               // 075    0x4B
        SialLexersym.Char_L,               // 076    0x4C
        SialLexersym.Char_M,               // 077    0x4D
        SialLexersym.Char_N,               // 078    0x4E
        SialLexersym.Char_O,               // 079    0x4F
        SialLexersym.Char_P,               // 080    0x50
        SialLexersym.Char_Q,               // 081    0x51
        SialLexersym.Char_R,               // 082    0x52
        SialLexersym.Char_S,               // 083    0x53
        SialLexersym.Char_T,               // 084    0x54
        SialLexersym.Char_U,               // 085    0x55
        SialLexersym.Char_V,               // 086    0x56
        SialLexersym.Char_W,               // 087    0x57
        SialLexersym.Char_X,               // 088    0x58
        SialLexersym.Char_Y,               // 089    0x59
        SialLexersym.Char_Z,               // 090    0x5A
        SialLexersym.Char_LeftBracket,     // 091    0x5B
        SialLexersym.Char_BackSlash,       // 092    0x5C
        SialLexersym.Char_RightBracket,    // 093    0x5D
        SialLexersym.Char_Caret,           // 094    0x5E
        SialLexersym.Char__,               // 095    0x5F
        SialLexersym.Char_BackQuote,       // 096    0x60
        SialLexersym.Char_a,               // 097    0x61
        SialLexersym.Char_b,               // 098    0x62
        SialLexersym.Char_c,               // 099    0x63
        SialLexersym.Char_d,               // 100    0x64
        SialLexersym.Char_e,               // 101    0x65
        SialLexersym.Char_f,               // 102    0x66
        SialLexersym.Char_g,               // 103    0x67
        SialLexersym.Char_h,               // 104    0x68
        SialLexersym.Char_i,               // 105    0x69
        SialLexersym.Char_j,               // 106    0x6A
        SialLexersym.Char_k,               // 107    0x6B
        SialLexersym.Char_l,               // 108    0x6C
        SialLexersym.Char_m,               // 109    0x6D
        SialLexersym.Char_n,               // 110    0x6E
        SialLexersym.Char_o,               // 111    0x6F
        SialLexersym.Char_p,               // 112    0x70
        SialLexersym.Char_q,               // 113    0x71
        SialLexersym.Char_r,               // 114    0x72
        SialLexersym.Char_s,               // 115    0x73
        SialLexersym.Char_t,               // 116    0x74
        SialLexersym.Char_u,               // 117    0x75
        SialLexersym.Char_v,               // 118    0x76
        SialLexersym.Char_w,               // 119    0x77
        SialLexersym.Char_x,               // 120    0x78
        SialLexersym.Char_y,               // 121    0x79
        SialLexersym.Char_z,               // 122    0x7A
        SialLexersym.Char_LeftBrace,       // 123    0x7B
        SialLexersym.Char_VerticalBar,     // 124    0x7C
        SialLexersym.Char_RightBrace,      // 125    0x7D
        SialLexersym.Char_Tilde,           // 126    0x7E

        SialLexersym.Char_AfterASCII,      // for all chars in range 128..65534
        SialLexersym.Char_EOF              // for '\uffff' or 65535 
    };
            
    public final int getKind(int i)  // Classify character at ith location
    {
        int c = (i >= getStreamLength() ? '\uffff' : getCharValue(i));
        return (c < 128 // ASCII Character
                  ? tokenKind[c]
                  : c == '\uffff'
                       ? SialLexersym.Char_EOF
                       : SialLexersym.Char_AfterASCII);
    }

    public String[] orderedExportedSymbols() { return SialParsersym.orderedTerminalSymbols; }

    public SialLexerLpgLexStream(String filename, int tab) throws java.io.IOException
    {
        super(filename, tab);
    }

    public SialLexerLpgLexStream(char[] input_chars, String filename, int tab)
    {
        super(input_chars, filename, tab);
    }

    public SialLexerLpgLexStream(char[] input_chars, String filename)
    {
        super(input_chars, filename, 1);
    }
    }

    public void ruleAction(int ruleNumber)
    {
        switch(ruleNumber)
        {

            //
            // Rule 1:  Token ::= identifier
            //
            case 1: { 
                checkForKeyWord();
            break;
            }
            //
            // Rule 2:  Token ::= intlit
            //
            case 2: { 
                makeToken(SialParsersym.TK_INTLIT);
            break;
            }
            //
            // Rule 3:  Token ::= doublelit
            //
            case 3: { 
                makeToken(SialParsersym.TK_DOUBLELIT);
            break;
            }
            //
            // Rule 4:  Token ::= stringlit
            //
            case 4: { 
              makeToken(SialParsersym.TK_STRINGLIT);  
            break;
            }
            //
            // Rule 5:  Token ::= white
            //
            case 5: { 
                skipToken();
            break;
            }
            //
            // Rule 6:  Token ::= slc
            //
            case 6: { 
                makeComment(SialParsersym.TK_SINGLE_LINE_COMMENT);
            break;
            }
            //
            // Rule 7:  Token ::= ,
            //
            case 7: { 
                makeToken(SialParsersym.TK_COMMA);
            break;
            }
            //
            // Rule 8:  Token ::= +
            //
            case 8: { 
                makeToken(SialParsersym.TK_PLUS);
            break;
            }
            //
            // Rule 9:  Token ::= -
            //
            case 9: { 
                makeToken(SialParsersym.TK_MINUS);
            break;
            }
            //
            // Rule 10:  Token ::= /
            //
            case 10: { 
                makeToken(SialParsersym.TK_SLASH);
            break;
            }
            //
            // Rule 11:  Token ::= *
            //
            case 11: { 
                makeToken(SialParsersym.TK_STAR);
            break;
            }
            //
            // Rule 12:  Token ::= ^
            //
            case 12: { 
                makeToken(SialParsersym.TK_TENSOR);
            break;
            }
            //
            // Rule 13:  Token ::= (
            //
            case 13: { 
                makeToken(SialParsersym.TK_LEFTPAREN);
            break;
            }
            //
            // Rule 14:  Token ::= )
            //
            case 14: { 
                makeToken(SialParsersym.TK_RIGHTPAREN);
            break;
            }
            //
            // Rule 15:  Token ::= >
            //
            case 15: { 
                makeToken(SialParsersym.TK_GREATER);
            break;
            }
            //
            // Rule 16:  Token ::= > =
            //
            case 16: { 
                makeToken(SialParsersym.TK_GEQ);
            break;
            }
            //
            // Rule 17:  Token ::= <
            //
            case 17: { 
                makeToken(SialParsersym.TK_LESS);
            break;
            }
            //
            // Rule 18:  Token ::= < =
            //
            case 18: { 
                makeToken(SialParsersym.TK_LEQ);
            break;
            }
            //
            // Rule 19:  Token ::= = =
            //
            case 19: { 
                makeToken(SialParsersym.TK_EQ);
            break;
            }
            //
            // Rule 20:  Token ::= ! =
            //
            case 20: { 
                makeToken(SialParsersym.TK_NEQ);
            break;
            }
            //
            // Rule 21:  Token ::= =
            //
            case 21: { 
                makeToken(SialParsersym.TK_ASSIGN);
            break;
            }
            //
            // Rule 22:  Token ::= + =
            //
            case 22: { 
                makeToken(SialParsersym.TK_PLUS_ASSIGN);
            break;
            }
            //
            // Rule 23:  Token ::= - =
            //
            case 23: { 
                makeToken(SialParsersym.TK_MINUS_ASSIGN);
            break;
            }
            //
            // Rule 24:  Token ::= * =
            //
            case 24: { 
                makeToken(SialParsersym.TK_STAR_ASSIGN);
            break;
            }
            //
            // Rule 25:  Token ::= eol
            //
            case 25: { 
                makeToken(SialParsersym.TK_EOL);
            break;
            }
    
            default:
                break;
        }
        return;
    }
}

