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

    public final static int NUM_STATES = 299;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 30;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 437;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 67;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 32;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 68;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 25;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 31;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 369;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 370;
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
            10,7,5,6,3,10,7,14,11,7,
            10,5,7,11,12,14,13,10,13,6,
            7,12,8,7,7,14,18
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
            1,1,1,1,1,1,1,1,1,70,
            97,45,123,130,50,135,40,88,41,58,
            33,136,62,63,14,138,134,142,144,48,
            143,148,149,150,154,157,91,69,159,160,
            21,158,164,72,166,172,78,162,102,165,
            178,85,176,104,174,95,181,182,183,184,
            185,190,189,195,196,198,199,203,204,208,
            200,211,213,216,217,218,221,222,224,223,
            227,231,230,86,233,234,237,243,32,239,
            232,249,244,250,254,253,255,256,257,261,
            263,259,267,268,272,273,274,277,18,279,
            278,280,286,289,290,294,297,298,301,303,
            304,306,307,308,314,312,316,317,318,321,
            109,115,319,323,326,331,333,337,324,339,
            111,340,342,343,344,346,351,348,354,356,
            358,359,117,360,364,365,368,372,373,361,
            376,377,378,380,112,381,383,386,390,393,
            394,387,395,401,402,404,121,406,407,408,
            409,412,416,411,421,423,414,426,427,430,
            432,435,438,436,431,9,440,441,442,446,
            450,452,129,445,456,457,460,462,464,466,
            465,469,470,473,471,472,480,481,483,484,
            485,486,490,492,501,488,503,496,498,505,
            506,510,512,514,511,517,515,516,524,525,
            528,529,530,531,534,536,544,532,533,539,
            548,547,549,553,555,556,561,562,563,564,
            567,568,576,570,578,582,580,584,586,589,
            588,590,572,591,592,594,601,600,604,607,
            605,606,612,613,616,615,621,624,622,626,
            627,629,630,634,636,639,640,643,645,641,
            646,650,651,653,658,661,659,663,370,370
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,0,9,
            10,11,12,0,1,2,3,0,18,19,
            0,4,9,6,24,12,13,7,8,12,
            22,0,0,16,3,18,4,5,6,0,
            0,10,11,12,0,6,7,0,8,0,
            3,4,13,4,22,6,16,0,1,19,
            3,0,0,6,17,4,5,23,0,7,
            8,0,4,2,13,7,14,0,1,11,
            3,0,11,6,0,0,2,0,1,5,
            0,20,2,9,0,10,0,12,4,12,
            10,0,1,0,3,20,12,4,0,6,
            0,0,4,5,0,1,0,3,7,9,
            0,25,0,1,8,5,15,17,0,0,
            10,3,16,0,0,0,1,0,1,11,
            6,0,0,0,2,16,3,0,0,0,
            9,3,19,0,7,2,0,0,0,0,
            1,0,13,0,0,0,9,2,12,11,
            7,0,11,0,10,0,5,0,1,4,
            0,0,0,0,0,5,4,3,0,0,
            17,8,3,12,0,0,8,0,0,0,
            6,6,0,0,1,8,7,0,6,2,
            0,1,0,15,2,0,0,0,2,4,
            0,0,0,0,7,2,0,7,7,0,
            0,0,0,0,1,13,0,8,0,13,
            10,9,0,0,2,9,15,9,0,0,
            2,2,0,0,0,0,0,3,0,7,
            0,1,0,7,21,12,0,0,10,7,
            15,0,0,0,8,8,0,0,0,0,
            0,10,9,5,5,0,10,15,0,0,
            2,14,7,0,5,2,0,0,1,3,
            0,1,0,0,1,0,0,0,6,2,
            5,0,6,0,1,0,0,0,0,4,
            0,3,0,0,13,0,6,4,3,13,
            0,1,0,16,12,3,0,1,0,0,
            1,0,0,0,1,0,8,0,1,4,
            0,10,10,0,4,0,1,0,0,0,
            0,2,5,0,0,7,13,0,1,6,
            6,0,0,1,3,0,0,0,18,0,
            0,5,0,1,9,0,0,8,11,0,
            5,2,0,0,0,9,16,5,4,6,
            0,0,1,0,4,0,0,0,0,6,
            0,0,5,0,9,0,10,6,10,4,
            0,1,0,1,14,0,0,14,2,0,
            0,0,7,2,0,0,2,0,1,0,
            0,0,7,14,0,0,16,2,9,0,
            1,0,8,2,13,0,0,17,3,0,
            1,0,1,0,0,0,2,11,0,0,
            0,0,0,10,9,7,7,5,8,0,
            0,1,0,0,0,0,15,0,5,0,
            8,0,1,6,15,0,7,0,14,14,
            0,1,0,6,0,0,4,3,13,0,
            0,0,2,0,0,0,0,3,9,8,
            15,8,6,0,0,1,11,0,0,0,
            0,0,0,0,5,0,6,2,0,11,
            17,14,9,0,13,2,0,0,0,1,
            12,4,0,21,0,0,10,3,6,4,
            0,0,0,0,3,3,0,0,1,0,
            4,0,9,2,14,0,1,0,1,0,
            11,0,1,0,1,0,7,0,0,0,
            0,0,1,0,5,10,8,4,11,0,
            0,11,2,0,0,0,0,2,5,10,
            4,0,0,1,0,0,5,2,14,5,
            0,0,2,0,1,0,0,1,0,0,
            5,3,11,0,1,0,1,8,0,0,
            0,3,0,1,0,0,2,8,8,0,
            0,2,0,0,2,5,11,0,0,2,
            0,1,0,0,2,0,8,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            370,83,72,78,76,81,73,74,370,80,
            79,84,82,370,111,112,115,370,77,75,
            370,211,113,212,71,110,114,135,134,208,
            412,370,370,210,188,209,102,99,100,370,
            370,189,191,190,370,92,91,370,96,370,
            122,121,90,88,101,87,384,370,97,95,
            98,370,370,379,120,105,106,85,370,107,
            109,370,130,140,104,131,108,370,143,129,
            142,370,138,144,370,370,150,370,94,152,
            10,139,415,151,370,184,370,186,158,93,
            128,370,146,370,147,185,157,155,370,154,
            370,52,229,230,370,231,370,232,263,241,
            370,369,370,86,430,275,262,242,370,370,
            407,292,254,370,370,370,103,370,116,291,
            89,370,370,370,123,401,119,370,370,370,
            118,125,117,370,124,388,370,370,370,370,
            133,370,126,370,370,370,136,387,127,132,
            137,370,145,370,148,370,141,370,149,153,
            370,370,370,370,370,159,160,162,370,370,
            156,161,163,404,370,370,164,370,370,370,
            165,166,370,370,170,167,172,370,169,171,
            370,173,370,168,174,370,370,370,176,175,
            370,370,370,370,177,179,370,178,376,370,
            370,370,370,370,385,180,370,183,370,181,
            182,373,370,370,383,187,193,192,370,370,
            194,196,370,370,370,370,370,199,370,198,
            370,413,370,371,195,197,370,370,201,405,
            200,370,370,370,202,203,370,370,370,370,
            370,204,206,213,214,370,207,205,370,370,
            216,398,215,370,217,218,370,370,220,219,
            370,382,370,370,222,370,370,370,221,224,
            223,370,377,370,225,370,370,370,370,227,
            370,233,370,370,226,370,381,239,235,228,
            370,236,370,386,234,237,370,238,370,370,
            243,370,370,370,246,370,240,370,249,247,
            370,244,245,370,248,370,251,370,370,370,
            370,414,252,370,370,253,250,370,410,255,
            256,370,370,408,257,370,370,370,258,370,
            370,260,370,265,259,370,370,375,261,370,
            266,267,370,370,370,270,264,268,271,269,
            370,370,273,370,272,370,370,370,370,274,
            370,370,402,370,403,370,276,278,277,279,
            370,280,370,281,399,370,370,395,283,370,
            370,370,282,435,370,370,434,370,284,370,
            370,370,285,394,370,370,431,289,286,370,
            290,370,423,390,288,370,370,287,293,370,
            389,370,393,370,370,370,296,294,370,370,
            370,370,370,295,374,372,417,297,420,370,
            370,300,370,370,370,370,298,370,302,370,
            301,370,409,304,299,370,303,370,397,396,
            370,433,370,307,370,370,305,308,306,370,
            370,370,391,370,370,370,370,313,310,311,
            309,312,378,370,370,316,314,370,370,370,
            370,370,370,370,318,370,319,321,370,317,
            315,400,320,370,323,322,370,370,370,328,
            325,326,370,324,370,370,327,330,329,331,
            370,370,370,370,333,334,370,370,411,370,
            336,370,335,343,332,370,337,370,392,370,
            428,370,338,370,340,370,339,370,370,370,
            370,370,345,370,342,416,421,346,341,370,
            370,344,347,370,370,370,370,350,348,406,
            349,370,370,352,370,370,351,353,424,419,
            370,370,354,370,432,370,370,356,370,370,
            425,357,355,370,359,370,429,358,370,370,
            370,360,370,362,370,370,427,361,363,370,
            370,436,370,370,426,418,364,370,370,365,
            370,366,370,370,437,370,367
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
