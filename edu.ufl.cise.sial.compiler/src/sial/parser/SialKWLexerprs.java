package sial.parser;

public class SialKWLexerprs implements lpg.runtime.ParseTable, SialKWLexersym {
    public final static int ERROR_SYMBOL = 0;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 0;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 0;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 0;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 235;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 30;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 355;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 58;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 32;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 59;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 25;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 31;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 296;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 297;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return SialKWLexersym.isValidForParser; }


    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            2,1
        };
    };
    public final static byte prosthesesIndex[] = ProsthesesIndex.prosthesesIndex;
    public final int prosthesesIndex(int index) { return prosthesesIndex[index]; }

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            4,7,4,7,6,4,5,8,2,2,
            5,5,4,2,4,5,3,3,7,7,
            8,10,7,7,7,8,8,5,7,8,
            2,6,6,4,5,11,6,6,8,6,
            10,7,5,6,3,10,10,7,14,11,
            8,9,7,10,5,7,11,12
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            61,97,30,43,114,55,116,75,118,29,
            39,28,126,13,51,21,128,120,124,132,
            56,131,133,139,140,77,62,16,142,76,
            145,144,87,147,146,149,69,54,90,152,
            156,88,158,159,161,94,162,165,167,168,
            170,171,177,169,180,181,182,186,185,192,
            194,195,95,197,198,199,189,201,206,207,
            209,66,208,210,212,219,38,213,224,220,
            225,228,229,230,231,235,233,238,242,243,
            240,244,245,251,252,256,257,259,258,265,
            266,268,271,273,275,277,279,278,283,288,
            280,286,284,290,292,96,102,295,296,302,
            304,300,308,294,310,312,313,316,314,322,
            324,318,325,328,330,331,335,336,337,338,
            339,340,104,342,343,348,353,356,350,357,
            361,363,362,109,364,365,370,372,373,375,
            379,381,382,383,386,387,388,389,391,390,
            394,399,112,400,406,404,410,412,414,416,
            417,418,421,423,420,428,431,432,433,438,
            436,434,442,448,440,451,449,453,454,455,
            456,463,458,462,464,467,470,475,471,477,
            478,479,480,482,485,486,493,495,497,499,
            500,502,504,507,505,510,508,513,515,517,
            519,520,522,528,526,297,297
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,0,8,9,
            10,11,0,1,14,0,4,5,18,19,
            0,1,2,3,24,13,11,0,0,0,
            10,4,5,13,14,7,9,0,0,1,
            3,3,0,1,16,8,18,9,11,22,
            0,14,23,0,0,0,6,7,3,5,
            5,0,12,9,11,0,5,6,0,1,
            15,3,11,8,0,0,0,9,2,14,
            6,6,7,9,8,20,0,0,2,0,
            1,4,3,0,0,0,0,10,5,4,
            5,0,1,0,3,11,20,14,0,6,
            16,0,4,0,3,0,8,0,1,0,
            17,25,11,0,9,0,1,0,1,16,
            0,0,0,10,3,3,6,18,0,0,
            2,0,1,0,0,0,0,2,0,6,
            4,0,4,14,10,0,1,0,0,8,
            0,0,5,5,0,4,0,0,0,0,
            0,5,3,3,7,15,0,9,14,0,
            0,0,1,7,0,0,7,2,0,9,
            6,0,1,0,0,2,0,0,0,5,
            0,13,6,6,6,0,0,0,0,0,
            1,0,0,13,8,7,11,10,0,0,
            2,10,10,0,0,2,2,0,0,0,
            0,3,0,6,0,1,6,0,6,0,
            21,0,0,0,0,8,17,8,7,7,
            0,0,8,10,4,0,0,0,0,4,
            2,4,6,12,0,0,2,0,3,2,
            0,1,0,1,0,1,0,0,0,0,
            4,3,0,0,2,0,9,0,1,0,
            5,0,13,0,0,0,13,3,3,0,
            9,0,1,0,5,16,3,0,15,0,
            1,0,0,0,7,0,1,0,5,8,
            8,0,1,0,0,2,9,0,1,0,
            0,1,3,9,0,0,0,0,0,0,
            5,0,0,1,0,7,10,0,11,0,
            11,4,0,19,2,0,0,16,9,4,
            0,0,0,0,0,5,10,5,4,0,
            9,0,0,10,0,1,5,8,0,1,
            0,0,0,2,12,0,0,0,0,0,
            0,2,12,0,12,2,10,7,0,0,
            2,13,15,0,0,0,7,22,3,0,
            1,0,1,0,11,0,0,0,2,0,
            0,8,0,6,0,10,6,0,1,7,
            0,0,0,0,4,0,17,0,1,0,
            5,0,3,12,12,4,13,0,0,2,
            0,1,0,0,0,0,2,0,10,7,
            7,0,0,0,9,3,0,4,2,0,
            0,2,15,12,0,1,0,0,0,0,
            1,0,5,7,0,0,8,0,3,5,
            9,21,0,1,0,1,0,1,0,0,
            2,0,1,0,0,6,0,0,4,0,
            1,8,0,7,0,8,0,5,0,0,
            4,0,4,4,3,0,12,0,1,4,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            297,74,63,69,72,67,65,297,70,64,
            71,75,297,93,73,297,96,95,66,68,
            297,101,102,105,62,94,120,297,297,297,
            103,88,91,104,100,85,89,297,297,86,
            173,87,297,77,311,174,84,306,176,90,
            297,175,76,297,297,297,97,99,112,79,
            111,297,98,78,133,297,118,119,297,131,
            110,130,117,169,297,297,10,132,342,171,
            81,123,122,82,116,170,297,297,127,297,
            134,139,135,297,297,297,297,138,144,208,
            207,297,209,55,210,161,126,143,297,233,
            160,297,244,297,257,297,334,297,83,297,
            232,296,256,297,80,297,92,297,106,328,
            297,297,297,108,109,114,113,107,297,297,
            315,297,121,297,297,297,297,314,297,125,
            128,297,129,115,124,297,137,297,297,136,
            297,297,140,141,297,145,297,297,297,297,
            297,146,148,149,147,142,297,151,331,297,
            297,297,154,150,297,297,152,155,297,153,
            156,297,157,297,297,158,297,297,297,159,
            297,164,162,163,303,297,297,297,297,297,
            312,297,297,165,167,168,166,300,297,297,
            310,172,177,297,297,178,180,297,297,297,
            297,182,297,181,297,340,298,297,332,297,
            179,297,297,297,297,184,183,187,185,186,
            297,297,189,188,190,297,297,297,297,191,
            193,194,192,325,297,297,195,297,196,197,
            297,198,297,309,297,199,297,297,297,297,
            200,201,297,297,202,297,304,297,203,297,
            205,297,204,297,297,297,206,211,212,297,
            308,297,213,297,215,313,214,297,217,297,
            218,297,297,297,216,297,221,297,222,219,
            220,297,223,297,297,341,224,297,337,297,
            297,335,226,225,297,297,297,297,297,297,
            228,297,297,235,297,302,229,297,230,297,
            231,236,297,227,237,297,297,234,239,238,
            297,297,297,297,297,241,240,242,329,297,
            243,297,297,330,297,247,246,245,297,248,
            297,297,297,249,326,297,297,297,297,297,
            297,254,322,297,321,317,250,253,297,297,
            255,252,251,297,297,297,353,339,258,297,
            316,297,320,297,259,297,297,297,261,297,
            297,260,297,299,297,301,345,297,263,350,
            297,297,297,297,264,297,262,297,336,297,
            265,297,268,324,323,267,266,297,297,348,
            297,269,297,297,297,297,318,297,270,271,
            272,297,297,297,305,273,297,275,276,297,
            297,277,274,327,297,349,297,297,297,297,
            282,297,280,279,297,297,281,297,285,284,
            283,278,297,338,297,286,297,319,297,297,
            344,297,288,297,297,287,297,297,289,297,
            290,343,297,351,297,333,297,291,297,297,
            292,297,347,355,293,297,354,297,294,346
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    /**
     * assert(! goto_default);
     */
    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    /**
     * assert(! shift_default);
     */
    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}
