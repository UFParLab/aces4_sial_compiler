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

    public final static int NUM_STATES = 271;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 30;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 399;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 62;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 32;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 63;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 25;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 31;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 336;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 337;
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
            8,9,7,10,5,7,11,12,14,13,
            10,13
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
            1,1,1,1,65,27,42,29,117,90,
            50,41,128,9,58,32,136,39,62,21,
            139,132,142,68,63,97,141,143,145,144,
            89,73,149,153,95,155,150,14,13,159,
            162,82,163,104,164,168,101,166,108,156,
            109,171,175,177,178,179,180,184,185,187,
            188,194,197,199,195,203,205,206,76,208,
            209,211,212,213,217,214,219,72,220,221,
            226,229,46,232,233,234,237,238,240,241,
            243,246,248,250,245,251,256,260,261,262,
            265,263,266,270,271,280,281,283,284,288,
            290,293,295,297,299,300,267,305,301,277,
            306,308,309,311,115,121,312,313,318,321,
            322,325,327,100,330,328,332,333,336,338,
            343,345,346,348,349,353,357,351,355,359,
            360,361,366,364,118,362,367,371,378,377,
            381,383,384,385,386,126,390,394,391,395,
            399,397,398,406,408,402,410,412,413,416,
            418,419,420,421,422,423,427,434,127,431,
            437,441,442,444,446,447,448,449,451,453,
            454,459,462,460,465,464,466,471,472,476,
            475,482,481,483,489,490,484,493,495,496,
            499,497,502,506,504,508,509,510,511,520,
            512,525,515,521,523,528,530,532,535,533,
            540,541,542,547,549,534,553,555,551,559,
            556,561,562,564,563,570,568,573,575,576,
            578,580,584,585,591,587,594,588,598,600,
            602,604,605,607,608,337,337
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,0,9,
            10,11,0,0,14,2,8,5,18,19,
            0,1,2,3,24,17,0,19,0,1,
            10,0,12,20,14,4,5,6,0,1,
            0,0,4,5,0,0,6,7,3,0,
            12,25,12,22,9,6,11,0,1,14,
            3,0,0,6,23,3,4,0,7,8,
            3,0,0,0,13,0,4,2,16,7,
            9,0,1,11,3,14,11,6,0,0,
            2,20,17,4,0,6,0,9,2,0,
            0,7,8,0,1,5,3,0,0,10,
            10,4,4,6,0,16,0,0,4,5,
            0,1,14,3,7,0,0,0,1,3,
            5,0,15,17,9,0,1,11,0,1,
            0,0,0,0,0,3,2,7,0,0,
            19,10,0,1,0,0,7,14,0,11,
            2,0,0,0,10,0,5,0,1,4,
            0,16,9,11,0,5,0,0,0,0,
            4,3,3,0,0,8,0,0,14,0,
            6,8,6,0,0,8,0,1,0,6,
            2,7,0,1,0,0,2,0,0,4,
            0,0,0,0,7,7,0,7,0,0,
            0,1,9,12,12,0,8,11,0,10,
            2,0,0,0,2,10,0,0,2,0,
            0,10,0,3,0,0,7,0,1,0,
            0,14,7,9,21,0,7,15,8,0,
            0,0,0,8,0,0,0,5,9,0,
            0,10,6,9,5,15,0,7,13,0,
            0,2,0,0,2,5,3,0,12,0,
            3,2,0,1,0,1,0,1,0,0,
            0,1,3,5,0,0,2,0,0,4,
            0,0,0,0,3,3,6,0,1,12,
            0,0,1,3,0,17,0,0,4,0,
            1,0,0,1,8,0,9,0,0,4,
            9,4,0,1,0,0,2,0,0,1,
            0,6,0,6,0,3,0,1,0,0,
            0,0,4,0,0,0,0,1,18,10,
            0,11,18,8,11,5,0,0,17,2,
            0,5,0,0,0,0,6,4,4,0,
            0,6,10,0,0,5,0,0,0,10,
            4,0,9,9,6,0,1,0,1,0,
            13,0,0,2,13,0,7,0,0,0,
            0,0,0,0,2,13,0,10,2,8,
            0,12,12,0,16,2,0,22,8,3,
            0,0,1,0,1,0,0,0,0,2,
            0,11,0,0,9,7,10,7,0,0,
            8,0,1,0,0,0,0,8,15,5,
            0,0,1,15,0,0,13,7,13,4,
            0,0,0,0,2,5,12,6,0,0,
            1,3,0,10,0,0,0,2,0,3,
            8,0,8,0,6,0,1,0,0,0,
            0,0,2,5,0,6,13,16,11,0,
            0,2,0,12,0,1,4,0,8,0,
            1,0,0,0,0,21,9,6,4,0,
            0,0,3,3,11,13,0,1,0,1,
            0,10,0,1,0,0,2,7,0,1,
            0,0,0,0,9,2,5,0,8,0,
            1,4,0,11,0,0,0,0,4,0,
            1,9,5,0,0,2,0,0,13,5,
            0,5,2,0,0,8,3,0,1,0,
            1,0,1,0,0,2,0,0,0,2,
            0,5,8,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            337,78,67,73,71,76,68,69,337,74,
            75,79,337,337,77,133,90,134,72,70,
            337,106,107,110,66,351,337,89,337,81,
            108,337,109,132,105,96,93,94,337,98,
            337,337,100,101,337,337,87,86,182,337,
            99,336,85,95,183,84,185,337,91,184,
            92,337,337,346,80,117,116,337,102,104,
            114,337,337,337,103,337,124,168,115,125,
            178,337,137,123,136,180,170,138,10,337,
            382,179,169,83,337,82,337,122,118,337,
            337,129,128,337,140,145,141,337,337,230,
            144,148,151,147,337,231,337,55,219,220,
            337,221,150,222,249,337,337,337,88,277,
            260,337,248,368,374,337,97,276,337,111,
            337,337,337,337,337,120,355,119,337,337,
            112,113,337,127,337,337,131,121,337,126,
            354,337,337,337,130,337,135,337,143,146,
            337,149,142,139,337,152,337,337,337,337,
            153,155,156,337,337,154,337,337,371,337,
            158,157,159,337,337,160,337,162,337,161,
            163,164,337,165,337,337,166,337,337,167,
            337,337,337,337,171,172,337,343,337,337,
            337,352,176,173,174,337,177,175,337,340,
            350,337,337,337,187,181,337,337,189,337,
            337,186,337,192,337,337,191,337,380,337,
            337,190,338,194,188,337,372,193,195,337,
            337,337,337,196,337,337,337,201,197,337,
            337,199,344,200,202,198,337,203,365,337,
            337,204,337,337,206,205,207,337,216,337,
            208,209,337,210,337,349,337,211,337,337,
            337,215,213,212,337,337,214,337,337,217,
            337,337,337,337,223,224,348,337,225,218,
            337,337,227,226,337,353,337,337,228,337,
            232,337,337,235,229,337,233,337,337,236,
            234,237,337,238,337,337,381,337,337,377,
            337,239,337,240,337,241,337,375,337,337,
            337,337,244,337,337,337,337,251,242,245,
            337,246,243,342,247,252,337,337,250,253,
            337,254,337,337,337,337,255,257,258,337,
            337,259,256,337,337,369,337,337,337,370,
            264,337,261,262,263,337,265,337,266,337,
            366,337,337,268,362,337,267,337,337,337,
            337,337,337,337,274,361,337,269,357,273,
            337,271,272,337,270,275,337,379,393,278,
            337,337,356,337,360,337,337,337,337,281,
            337,279,337,337,280,339,341,385,337,337,
            390,337,284,337,337,337,337,285,282,286,
            337,337,376,283,337,337,364,287,363,288,
            337,337,337,337,388,291,289,290,337,337,
            293,292,337,294,337,337,337,358,337,297,
            295,337,296,337,345,337,299,337,337,337,
            337,337,303,301,337,302,367,298,300,337,
            337,304,337,305,337,389,308,337,307,337,
            310,337,337,337,337,306,309,311,312,337,
            337,337,314,315,398,313,337,378,337,317,
            337,316,337,359,337,337,384,318,337,319,
            337,337,337,337,383,321,320,337,391,337,
            323,324,337,322,337,337,337,337,325,337,
            327,373,326,337,337,328,337,337,394,387,
            337,395,329,337,337,331,330,337,332,337,
            399,337,333,337,337,397,337,337,337,396,
            337,386,334
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
