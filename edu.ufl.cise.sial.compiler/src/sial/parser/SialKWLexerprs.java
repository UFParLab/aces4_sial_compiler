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

    public final static int NUM_STATES = 302;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 30;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 442;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 68;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 32;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 69;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 25;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 31;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 373;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 374;
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
            7,12,8,7,7,14,18,6
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
            71,136,43,55,138,91,141,32,101,42,
            53,30,142,60,62,14,145,148,84,149,
            64,150,151,155,156,160,17,104,71,157,
            163,103,165,166,75,170,171,79,175,115,
            177,21,87,178,117,179,109,180,182,188,
            184,189,195,190,197,200,201,202,204,207,
            211,205,214,216,219,221,220,224,225,226,
            229,230,234,233,88,236,237,239,247,37,
            235,246,250,251,51,252,256,257,258,259,
            260,269,266,261,272,275,276,263,279,277,
            28,284,281,285,291,292,296,297,300,302,
            305,307,308,310,311,312,318,316,320,321,
            322,324,122,128,282,327,325,328,335,339,
            340,342,344,90,346,348,349,350,353,354,
            360,355,362,364,326,118,365,366,370,373,
            371,377,379,380,381,382,383,124,384,387,
            385,392,396,398,399,402,406,409,410,413,
            130,414,415,416,417,418,426,420,430,432,
            422,428,437,438,440,441,444,446,447,448,
            449,450,451,454,459,455,133,395,461,462,
            468,471,466,474,475,478,479,482,480,481,
            484,489,492,493,491,494,495,503,506,509,
            510,501,511,512,518,519,521,522,523,520,
            527,524,532,536,534,539,538,540,542,545,
            552,544,541,553,555,556,559,561,564,569,
            563,570,571,573,576,580,583,578,585,587,
            590,591,593,594,595,596,598,599,604,608,
            607,612,613,614,615,619,620,623,626,627,
            631,634,630,637,638,636,643,644,646,648,
            649,653,656,655,650,660,659,665,666,669,
            671,672,374,374
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,0,9,
            10,11,12,0,1,2,0,4,18,19,
            0,1,9,3,24,12,13,0,12,0,
            3,0,3,6,5,6,0,6,7,12,
            4,0,0,16,13,18,10,11,12,8,
            0,22,0,1,0,1,4,16,6,0,
            19,0,3,0,5,23,3,4,7,8,
            0,21,13,3,0,14,2,7,0,1,
            17,11,4,0,6,11,0,0,2,0,
            0,5,9,3,20,9,6,10,9,12,
            0,1,0,0,0,2,17,20,0,7,
            8,3,12,10,0,1,0,0,4,3,
            12,0,6,0,3,8,5,0,1,0,
            7,4,0,16,5,0,4,0,15,10,
            0,0,1,11,0,1,6,0,0,0,
            0,2,4,16,0,0,0,7,4,0,
            25,2,0,1,0,0,19,11,13,0,
            0,2,7,9,0,5,0,0,0,0,
            3,0,0,0,5,11,10,0,0,0,
            3,8,4,12,0,17,0,8,4,0,
            0,0,6,0,0,6,0,1,8,6,
            0,7,2,0,1,0,15,2,0,0,
            0,3,2,0,0,0,7,2,0,0,
            7,7,0,0,0,0,0,1,0,5,
            8,13,13,10,9,0,0,9,2,0,
            0,0,2,2,9,0,0,0,0,0,
            0,4,0,7,15,0,7,12,0,1,
            10,0,7,15,0,0,0,15,0,8,
            0,0,8,0,0,10,10,9,5,5,
            0,0,11,2,14,0,0,7,2,0,
            5,0,1,4,0,1,0,0,1,0,
            0,0,6,2,5,0,6,0,1,0,
            0,0,3,0,0,0,0,0,13,6,
            4,4,7,13,0,1,12,16,0,0,
            1,0,4,0,3,0,1,0,0,0,
            1,8,0,0,0,3,3,10,10,0,
            1,0,1,0,0,0,2,13,5,0,
            0,6,0,1,4,6,0,1,0,0,
            0,0,0,0,0,5,0,1,9,5,
            8,0,11,2,0,0,18,0,0,16,
            5,0,8,6,3,0,1,9,0,0,
            1,3,0,0,0,0,0,0,6,0,
            5,0,9,6,10,0,10,0,3,0,
            1,0,1,14,7,14,0,0,2,0,
            0,2,2,0,1,0,0,0,0,0,
            0,14,7,0,0,2,2,9,0,1,
            0,0,16,13,4,0,17,0,1,22,
            0,1,11,0,0,10,2,0,0,0,
            0,0,9,0,7,7,5,8,0,1,
            0,0,0,0,0,15,0,5,15,8,
            0,7,0,1,14,0,1,14,0,0,
            0,0,3,13,6,4,6,0,0,0,
            0,0,0,0,2,6,0,9,8,8,
            4,0,15,0,11,0,1,0,0,0,
            0,0,5,0,0,6,2,14,17,11,
            9,0,0,2,0,0,13,3,0,1,
            0,21,0,0,12,10,6,4,0,0,
            0,3,0,4,4,0,14,0,3,0,
            1,9,0,1,0,1,0,1,11,0,
            0,1,0,0,0,0,7,0,0,2,
            5,8,10,0,1,11,0,0,2,11,
            3,0,0,0,0,0,3,5,0,0,
            2,10,0,1,5,0,0,2,14,0,
            0,5,2,0,1,0,0,0,1,4,
            11,5,0,0,1,0,1,0,0,0,
            8,4,0,1,0,0,8,2,0,0,
            11,2,8,5,0,0,2,2,0,1,
            0,0,0,2,0,0,0,0,8,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            374,84,73,77,79,82,74,75,374,81,
            80,85,83,374,112,113,374,116,78,76,
            374,151,114,150,72,111,115,374,128,374,
            214,374,103,215,100,101,374,93,92,211,
            190,374,374,213,91,212,191,193,192,97,
            374,102,374,98,374,87,99,388,383,374,
            96,374,106,374,107,86,122,123,108,110,
            374,198,105,131,374,109,141,132,374,144,
            121,130,143,374,145,139,374,374,152,374,
            374,154,119,89,140,153,88,186,245,188,
            374,95,374,10,374,419,246,187,374,136,
            135,160,94,129,374,147,374,374,148,157,
            159,374,156,52,232,434,233,374,234,374,
            267,235,374,258,279,374,296,374,266,411,
            374,374,104,295,374,117,90,374,374,374,
            374,124,120,405,374,374,374,125,126,374,
            373,392,374,134,374,374,118,133,127,374,
            374,391,138,137,374,142,374,374,374,374,
            155,374,374,374,161,146,149,374,374,374,
            162,163,164,408,374,158,374,166,165,374,
            374,374,167,374,374,168,374,172,169,171,
            374,174,173,374,175,374,170,176,374,374,
            374,177,178,374,374,374,179,181,374,374,
            180,380,374,374,374,374,374,389,374,194,
            185,182,183,184,377,374,374,189,387,374,
            374,374,197,199,195,374,374,374,374,374,
            374,202,374,201,196,374,375,200,374,417,
            204,374,409,203,374,374,374,208,374,205,
            374,374,206,374,374,207,210,209,216,217,
            374,374,236,219,402,374,374,218,221,374,
            220,374,223,222,374,386,374,374,225,374,
            374,374,224,227,226,374,381,374,228,374,
            374,374,230,374,374,374,374,374,229,385,
            237,239,257,231,374,240,238,390,374,374,
            242,374,241,374,243,374,247,374,374,374,
            250,244,374,374,374,251,252,248,249,374,
            253,374,255,374,374,374,418,254,256,374,
            374,259,374,414,261,260,374,412,374,374,
            374,374,374,374,374,264,374,269,263,270,
            379,374,265,271,374,374,262,374,374,268,
            272,374,427,273,275,374,442,274,374,374,
            277,276,374,374,374,374,374,374,278,374,
            406,374,407,282,280,374,281,374,283,374,
            284,374,285,403,286,399,374,374,287,374,
            374,439,438,374,288,374,374,374,374,374,
            374,398,289,374,374,293,394,290,374,294,
            374,374,435,292,297,374,291,374,393,416,
            374,397,298,374,374,299,300,374,374,374,
            374,374,378,374,376,421,301,424,374,304,
            374,374,374,374,374,302,374,306,303,305,
            374,307,374,413,401,374,437,400,374,374,
            374,374,309,310,308,312,311,374,374,374,
            374,374,374,374,395,382,374,314,315,316,
            317,374,313,374,318,374,320,374,374,374,
            374,374,322,374,374,323,325,404,319,321,
            324,374,374,326,374,374,327,330,374,332,
            374,328,374,374,329,331,333,334,374,374,
            374,335,374,337,338,374,336,374,340,374,
            415,339,374,341,374,396,374,342,432,374,
            374,344,374,374,374,374,343,374,374,347,
            346,425,420,374,349,345,374,374,351,348,
            350,374,374,374,374,374,353,352,374,374,
            354,410,374,356,355,374,374,357,428,374,
            374,423,358,374,436,374,374,374,360,361,
            359,429,374,374,363,374,433,374,374,374,
            362,364,374,366,374,374,365,431,374,374,
            368,440,367,422,374,374,430,369,374,370,
            374,374,374,441,374,374,374,374,371
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
