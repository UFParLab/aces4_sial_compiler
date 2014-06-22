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

    public final static int NUM_STATES = 281;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 30;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 419;
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

    public final static int ACCEPT_ACTION = 351;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 352;
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
            10,5,7,11,12,9,10,6,7,12,
            8,7,7,14,18,6,11
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
            81,14,136,130,101,142,49,22,44,63,
            32,143,69,71,16,147,75,104,43,61,
            31,139,150,151,152,153,100,80,157,158,
            103,156,162,74,161,169,90,170,87,171,
            112,96,173,174,175,114,180,176,182,185,
            184,190,191,196,187,198,200,201,202,205,
            204,211,214,209,217,218,220,221,223,222,
            228,229,230,47,231,232,234,244,41,237,
            236,243,248,249,251,254,252,256,255,262,
            259,264,267,268,269,277,272,278,26,281,
            273,282,286,289,290,294,295,297,301,299,
            303,305,307,309,312,310,315,316,317,318,
            117,122,321,322,320,326,334,336,324,337,
            338,339,342,343,346,348,354,356,349,359,
            361,363,124,365,344,366,370,372,374,368,
            378,376,381,380,9,382,383,391,393,396,
            385,386,400,401,403,405,408,125,409,410,
            411,412,414,417,422,419,424,425,426,429,
            430,434,436,437,438,441,444,443,445,448,
            455,129,446,458,450,464,466,462,468,469,
            470,474,475,473,477,482,484,485,486,488,
            489,493,491,501,495,496,502,504,506,510,
            512,509,514,522,515,513,523,525,529,531,
            532,536,526,535,540,541,131,543,546,549,
            547,550,548,557,558,562,560,564,567,570,
            571,573,574,575,577,578,585,587,581,590,
            588,594,595,596,598,602,600,604,605,610,
            611,612,613,617,619,622,627,623,628,630,
            352,352
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,0,9,
            10,11,12,0,6,0,1,2,18,19,
            5,0,1,15,24,0,11,12,13,4,
            0,0,7,12,3,4,23,12,7,9,
            0,16,0,0,19,5,0,5,0,9,
            10,8,12,22,6,7,10,9,12,16,
            0,18,0,1,4,5,20,5,0,7,
            0,3,4,0,0,2,6,17,8,0,
            0,13,9,4,14,6,0,1,9,0,
            1,5,18,20,5,0,7,2,3,0,
            0,2,0,0,4,25,11,7,6,10,
            8,0,1,0,11,4,0,4,0,3,
            4,0,1,0,0,12,5,3,0,0,
            0,8,2,5,10,0,1,9,0,16,
            10,0,0,1,6,16,0,1,7,0,
            0,0,0,2,5,0,0,0,1,0,
            0,0,2,13,12,9,11,6,0,0,
            0,3,0,0,0,0,4,4,9,0,
            10,0,3,0,0,4,0,12,5,0,
            0,17,8,7,5,0,1,0,8,0,
            0,0,1,0,0,8,2,7,0,6,
            0,1,4,0,15,2,0,0,2,0,
            0,0,0,6,2,6,6,0,0,0,
            0,0,1,0,13,0,0,8,10,3,
            13,11,0,0,11,2,11,0,0,2,
            0,0,2,0,0,0,5,15,0,6,
            6,0,1,0,6,0,0,0,0,21,
            15,0,0,10,8,8,0,0,10,3,
            0,0,11,3,3,0,14,10,0,0,
            2,6,3,0,0,2,0,1,0,5,
            0,1,0,1,0,7,0,3,0,0,
            2,0,1,7,0,0,0,0,4,0,
            0,0,13,0,7,0,5,4,13,9,
            5,12,16,0,1,0,0,0,0,1,
            5,0,0,0,8,0,1,0,0,2,
            7,10,10,0,17,0,1,4,0,1,
            0,13,0,3,0,0,2,0,6,0,
            1,0,7,0,1,0,5,0,3,0,
            0,0,0,1,0,0,19,8,11,9,
            0,7,0,3,2,0,11,16,3,0,
            0,1,0,4,0,1,4,0,0,0,
            0,0,3,0,7,4,0,1,0,11,
            10,0,1,0,0,0,2,14,0,0,
            2,2,14,0,1,0,0,0,15,14,
            0,6,0,0,0,0,2,0,1,0,
            0,11,16,8,0,13,2,0,9,22,
            17,0,5,0,1,0,1,0,0,0,
            2,10,0,0,0,6,0,3,11,6,
            8,0,1,0,0,0,3,0,0,1,
            0,15,0,1,0,0,9,7,14,14,
            0,0,7,0,4,0,5,13,0,0,
            2,0,0,0,0,0,11,8,15,8,
            7,0,0,9,0,0,5,3,0,17,
            0,0,4,2,0,0,14,2,13,0,
            0,11,0,1,4,0,0,0,0,0,
            4,12,7,5,5,21,0,0,1,0,
            4,0,1,0,1,18,0,1,9,0,
            0,1,0,0,0,6,0,0,1,3,
            0,8,10,9,0,1,0,0,2,0,
            10,4,3,0,0,0,2,0,3,0,
            3,0,1,0,0,1,3,14,9,0,
            0,0,0,1,5,5,0,0,0,8,
            2,0,0,1,3,9,0,0,2,0,
            0,2,0,0,0,8,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            352,83,72,81,76,78,74,73,52,84,
            79,80,82,352,262,352,111,112,75,77,
            115,352,94,261,71,352,113,110,114,211,
            352,352,212,93,99,102,85,208,100,123,
            352,210,352,352,209,188,352,119,352,191,
            189,96,190,101,91,92,184,90,186,366,
            352,95,352,97,121,122,185,98,352,361,
            352,106,105,352,352,140,107,120,109,352,
            352,104,138,130,108,131,352,146,129,352,
            143,147,117,139,142,352,144,151,153,10,
            352,397,352,352,88,351,152,87,135,128,
            134,352,150,352,118,149,352,158,352,230,
            229,352,231,352,352,157,232,274,352,352,
            352,410,408,289,389,352,86,288,352,253,
            320,352,352,103,124,383,352,116,89,352,
            352,352,352,370,125,352,352,352,133,352,
            352,352,369,126,127,132,136,137,352,352,
            352,141,352,352,352,352,154,155,145,352,
            148,352,159,352,352,160,352,386,162,352,
            352,156,161,166,163,352,165,352,164,352,
            352,352,170,352,352,167,171,169,352,172,
            352,173,175,352,168,174,352,352,176,352,
            352,352,352,177,179,178,358,352,352,352,
            352,352,367,352,180,352,352,183,182,192,
            181,355,352,352,187,365,193,352,352,195,
            352,352,197,352,352,352,199,194,352,198,
            353,352,395,352,387,352,352,352,352,196,
            200,352,352,201,202,203,352,352,204,205,
            352,352,206,213,214,352,380,207,352,352,
            216,215,217,352,352,218,352,220,352,219,
            352,364,352,222,352,221,352,223,352,352,
            224,352,225,359,352,352,352,352,227,352,
            352,352,226,352,363,352,234,239,228,233,
            236,235,368,352,237,352,352,352,352,242,
            238,352,352,352,240,352,245,352,352,246,
            254,243,244,352,241,352,248,247,352,250,
            352,249,352,251,352,352,396,352,252,352,
            392,352,255,352,390,352,256,352,259,352,
            352,352,352,264,352,352,257,357,258,260,
            352,268,352,265,266,352,269,263,267,352,
            352,418,352,270,352,272,271,352,352,352,
            352,352,384,352,273,276,352,277,352,385,
            275,352,278,352,352,352,280,381,352,352,
            415,414,377,352,281,352,352,352,279,376,
            352,282,352,352,352,352,286,352,287,352,
            352,283,411,405,352,285,372,352,291,394,
            284,352,290,352,371,352,375,352,352,352,
            293,292,352,352,352,354,352,294,356,399,
            402,352,296,352,352,352,297,352,352,391,
            352,295,352,413,352,352,298,299,379,378,
            352,352,302,352,300,352,303,301,352,352,
            373,352,352,352,352,352,305,306,304,307,
            360,352,352,309,352,352,308,311,352,310,
            352,352,312,314,352,352,382,315,316,352,
            352,313,352,321,319,352,352,352,352,352,
            324,318,322,323,325,317,352,352,393,352,
            327,352,328,352,374,326,352,329,409,352,
            352,331,352,352,352,330,352,352,334,333,
            352,403,398,332,352,419,352,352,335,352,
            388,337,336,352,352,352,338,352,339,352,
            401,352,412,352,352,341,407,406,340,352,
            352,352,352,345,342,343,352,352,352,344,
            416,352,352,348,400,346,352,352,347,352,
            352,417,352,352,352,349
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
