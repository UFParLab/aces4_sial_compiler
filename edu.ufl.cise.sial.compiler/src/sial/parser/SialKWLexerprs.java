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

    public final static int NUM_STATES = 296;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 30;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 438;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 69;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 32;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 70;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 25;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 31;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 368;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 369;
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
            8,7,7,14,18,6,11,14,4
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
            1,1,1,1,1,1,1,1,1,1,
            1,72,104,145,107,14,136,96,119,51,
            38,47,61,42,149,68,69,21,151,153,
            144,155,73,70,156,9,158,146,167,160,
            99,82,161,173,98,164,52,88,176,168,
            79,177,110,179,180,112,92,31,181,182,
            114,184,187,191,192,193,195,197,198,204,
            207,208,194,211,214,210,218,219,221,222,
            227,224,228,230,232,233,236,238,239,17,
            240,241,243,250,44,253,256,257,245,261,
            244,262,266,267,269,270,271,274,276,278,
            279,282,285,286,280,292,24,293,294,297,
            298,299,303,307,310,311,313,316,317,320,
            319,324,327,321,329,330,331,332,117,122,
            335,336,337,340,350,348,342,352,339,354,
            357,359,361,362,364,368,373,367,375,378,
            370,124,382,379,383,386,388,390,392,394,
            395,397,396,128,399,400,407,412,413,402,
            409,417,418,422,423,425,131,427,429,430,
            433,435,428,443,445,434,436,447,441,450,
            454,457,453,459,461,462,463,464,465,468,
            470,133,466,471,478,481,484,479,486,488,
            491,492,493,494,495,500,503,504,505,507,
            508,511,514,516,520,518,523,525,526,527,
            528,532,533,537,534,542,544,545,546,548,
            552,547,553,560,554,557,563,564,137,565,
            570,569,573,579,571,572,581,582,587,580,
            591,593,595,596,598,599,600,601,605,603,
            610,612,613,615,616,617,622,625,626,627,
            632,634,635,636,639,640,642,641,650,645,
            648,652,653,658,661,659,664,369,369
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,0,9,
            10,11,12,0,1,7,0,17,18,19,
            0,1,2,0,24,5,10,4,12,6,
            0,11,12,13,4,12,20,0,1,16,
            20,0,19,0,3,4,0,6,5,12,
            0,0,9,10,8,12,6,7,7,9,
            0,1,16,22,18,5,6,0,0,0,
            3,4,0,4,5,7,8,5,0,1,
            13,0,14,5,6,4,17,0,7,2,
            9,0,0,2,3,0,9,0,0,4,
            2,6,11,0,7,8,0,20,10,0,
            1,0,1,0,5,4,0,4,0,3,
            4,0,1,0,6,12,5,0,25,23,
            0,8,0,3,7,0,0,5,2,16,
            10,9,15,0,0,0,10,3,0,1,
            0,16,0,1,0,0,6,0,13,0,
            0,18,5,0,9,11,0,0,2,9,
            3,12,0,1,11,0,0,2,0,0,
            0,0,3,0,4,9,0,4,10,3,
            0,0,0,0,0,4,0,0,17,5,
            8,5,12,0,1,8,0,0,15,0,
            0,2,6,0,1,8,6,0,0,1,
            0,0,2,0,7,4,0,0,2,0,
            7,0,0,2,7,0,7,0,0,0,
            0,1,0,0,0,13,8,10,13,0,
            11,2,0,11,2,0,0,0,3,15,
            0,0,2,2,21,0,0,11,0,0,
            0,5,7,0,1,0,7,0,0,0,
            10,0,7,15,0,0,8,10,3,8,
            11,0,0,0,10,3,0,0,0,3,
            2,10,0,0,7,3,0,14,2,0,
            0,1,0,1,5,0,0,1,0,0,
            0,6,3,0,6,2,0,1,0,0,
            0,0,4,13,0,0,0,6,0,0,
            5,0,13,9,5,4,16,0,12,0,
            1,0,5,0,0,17,0,1,0,8,
            0,0,1,0,11,2,0,0,10,0,
            10,4,0,1,0,1,7,0,0,13,
            3,0,0,2,6,0,1,0,6,0,
            1,0,5,0,0,0,0,3,0,0,
            1,0,0,8,11,9,0,6,0,3,
            19,0,0,2,16,3,0,0,1,11,
            4,0,0,1,0,4,0,0,0,0,
            6,3,0,0,0,0,4,11,4,10,
            0,14,0,1,0,1,0,14,2,0,
            15,2,0,0,14,2,0,1,0,7,
            0,0,0,0,0,0,2,0,1,0,
            0,2,11,8,16,5,13,0,0,17,
            0,1,22,0,1,0,9,0,10,2,
            0,0,0,0,0,3,11,7,7,0,
            1,8,0,0,0,3,0,0,0,15,
            0,1,9,0,1,0,9,0,14,0,
            14,6,0,4,0,0,0,0,6,5,
            13,0,0,0,2,8,0,11,5,8,
            15,0,6,0,0,0,0,0,3,2,
            9,0,0,0,2,4,0,11,14,0,
            17,2,0,0,0,1,13,4,0,0,
            0,0,0,5,12,6,4,21,0,0,
            0,0,1,5,4,15,0,1,9,18,
            0,1,0,1,0,0,1,0,0,0,
            0,7,0,3,0,1,8,10,9,0,
            1,0,0,2,0,0,0,3,16,4,
            0,0,10,2,0,0,0,3,3,3,
            14,0,1,0,0,0,1,3,0,0,
            0,0,9,5,0,6,5,0,8,0,
            1,0,0,2,0,3,9,0,0,2,
            0,1,18,0,0,2,8,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            369,86,75,84,79,81,76,77,369,87,
            82,83,85,369,90,130,369,73,78,80,
            369,116,117,369,74,120,192,220,194,221,
            369,118,115,119,161,217,193,369,98,219,
            114,369,218,369,103,106,369,104,196,97,
            369,369,199,197,100,198,96,95,143,94,
            369,101,383,105,99,102,378,369,369,369,
            110,109,369,127,128,111,113,125,369,149,
            108,369,112,148,150,136,126,369,137,146,
            135,369,369,158,160,369,144,369,10,92,
            414,91,159,369,141,140,369,145,134,369,
            152,369,157,369,153,156,369,165,369,239,
            238,369,240,369,93,164,241,52,368,89,
            369,427,369,284,272,369,369,300,425,263,
            406,299,271,369,369,369,333,88,369,107,
            369,400,369,122,369,369,121,369,132,369,
            369,123,131,369,129,124,369,369,387,138,
            147,133,369,139,142,369,369,386,369,369,
            369,369,155,369,162,151,369,166,154,167,
            369,369,369,369,369,168,369,369,163,170,
            169,171,403,369,173,172,369,369,176,369,
            369,179,174,369,178,175,177,369,369,181,
            369,369,182,369,180,183,369,369,184,369,
            185,369,369,187,186,369,375,369,369,369,
            369,384,369,369,369,188,191,190,189,369,
            372,382,369,195,438,369,369,369,200,202,
            369,369,203,205,204,369,369,201,369,369,
            369,207,206,369,412,369,370,369,369,369,
            209,369,404,208,369,369,211,210,214,212,
            215,369,369,369,213,222,369,369,369,223,
            225,216,369,369,224,226,369,397,227,369,
            369,229,369,381,228,369,369,231,369,369,
            369,230,232,369,376,233,369,234,369,369,
            369,369,236,235,369,369,369,380,369,369,
            243,369,237,242,245,248,385,369,244,369,
            246,369,247,369,369,250,369,252,369,249,
            369,369,255,369,251,256,369,369,253,369,
            254,257,369,258,369,260,262,369,369,259,
            261,369,369,413,264,369,409,369,265,369,
            407,369,266,369,369,369,369,269,369,369,
            274,369,369,374,268,270,369,278,369,275,
            267,369,369,276,273,277,369,369,435,279,
            280,369,369,282,369,281,369,369,369,369,
            283,401,369,369,369,369,286,402,287,285,
            369,398,369,288,369,289,369,394,291,369,
            290,432,369,369,393,431,369,292,369,293,
            369,369,369,369,369,369,297,369,298,369,
            369,389,294,422,428,301,296,369,369,295,
            369,388,411,369,392,369,302,369,303,304,
            369,369,369,369,369,305,373,371,416,369,
            307,419,369,369,369,308,369,369,369,306,
            369,408,309,369,430,369,310,369,396,369,
            395,311,369,312,369,369,369,369,314,315,
            313,369,369,369,390,318,369,317,320,319,
            316,369,377,369,369,369,369,369,323,324,
            321,369,369,369,327,325,369,326,399,369,
            322,328,369,369,369,334,329,332,369,369,
            369,369,369,336,331,335,337,330,369,369,
            369,369,410,338,341,339,369,342,426,340,
            369,391,369,343,369,369,345,369,369,369,
            369,344,369,347,369,348,420,415,346,369,
            436,369,369,350,369,369,369,351,349,352,
            369,369,405,353,369,369,369,354,418,355,
            423,369,429,369,369,369,357,424,369,369,
            369,369,356,358,369,359,360,369,361,369,
            362,369,369,433,369,417,363,369,369,364,
            369,365,437,369,369,434,366
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
