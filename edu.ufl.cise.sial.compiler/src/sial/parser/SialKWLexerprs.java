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

    public final static int NUM_STATES = 262;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 30;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 386;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 60;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 32;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 61;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 25;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 31;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 325;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 326;
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
            7,10,5,7,11,12,14,13,10,13
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
            1,1,63,107,29,128,13,87,50,41,
            134,9,58,32,136,39,62,21,138,71,
            18,42,63,113,140,141,143,142,94,73,
            146,148,90,150,151,14,154,153,161,82,
            152,99,162,164,101,74,104,167,105,168,
            170,172,169,175,179,180,181,185,186,189,
            192,196,190,199,201,202,205,204,208,209,
            210,212,214,217,213,72,218,219,223,227,
            46,230,232,231,235,236,238,239,241,244,
            246,248,249,253,254,257,259,260,263,264,
            265,268,275,277,271,281,284,285,288,290,
            292,293,294,299,302,295,304,298,305,306,
            112,120,311,313,317,320,324,315,321,114,
            326,328,330,331,334,336,341,333,343,344,
            345,348,352,347,354,356,355,357,118,358,
            359,365,369,372,373,374,376,378,381,122,
            383,385,386,388,389,392,267,361,398,390,
            400,402,401,405,406,408,409,410,411,415,
            65,418,419,420,428,432,425,430,435,436,
            438,439,441,442,448,444,446,450,453,454,
            458,460,455,462,469,470,465,473,474,476,
            477,484,479,485,480,488,489,490,498,501,
            492,491,497,502,507,508,511,510,513,515,
            519,520,523,527,521,533,529,535,536,538,
            540,539,541,545,549,551,552,553,546,558,
            564,560,566,567,568,572,570,576,579,581,
            583,584,586,587,326,326
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,0,9,
            10,11,0,0,14,2,8,0,18,19,
            0,1,2,3,24,17,18,10,0,17,
            10,0,12,20,14,4,5,6,0,1,
            0,0,4,5,3,0,6,7,3,0,
            12,23,12,22,9,6,11,0,1,14,
            3,0,0,6,0,3,4,3,7,8,
            0,0,0,0,13,11,4,4,16,7,
            9,0,1,11,3,14,0,6,18,0,
            4,20,6,0,0,2,7,8,0,1,
            0,3,9,0,0,5,0,4,4,6,
            10,0,0,0,2,4,5,0,14,0,
            1,0,3,10,7,0,5,0,1,16,
            9,25,15,0,1,0,1,0,1,0,
            0,0,0,3,2,0,7,0,1,0,
            0,0,0,0,2,14,11,7,5,10,
            0,0,11,0,1,5,0,0,0,0,
            9,0,5,0,0,4,8,3,0,0,
            0,3,16,14,0,0,6,8,0,0,
            6,0,1,8,6,0,7,2,0,1,
            0,0,2,0,0,4,2,0,0,0,
            7,0,0,0,7,7,0,0,0,1,
            8,12,0,12,11,9,0,10,2,0,
            0,0,10,2,0,0,2,0,0,10,
            0,3,0,0,7,0,1,0,0,14,
            7,21,0,0,7,15,0,9,0,0,
            8,8,0,0,0,9,0,0,5,10,
            0,9,5,15,0,5,0,13,2,13,
            0,7,2,0,0,1,3,0,1,0,
            1,0,0,0,0,3,5,0,0,6,
            2,0,1,0,0,0,12,4,0,12,
            0,6,0,3,0,3,0,1,4,0,
            0,17,3,0,1,0,1,0,8,0,
            0,1,0,0,2,0,9,4,9,4,
            0,1,0,0,0,1,0,0,6,6,
            3,0,1,0,0,0,0,0,0,1,
            0,1,8,10,0,19,11,11,0,5,
            2,0,0,0,17,0,5,0,6,4,
            0,4,0,10,0,0,6,0,0,0,
            5,0,10,9,6,4,9,0,1,0,
            0,0,13,2,0,0,7,0,0,0,
            0,2,2,13,0,10,2,0,0,0,
            12,3,0,16,0,8,22,0,1,0,
            11,0,1,9,0,0,2,0,0,10,
            0,0,7,0,7,0,8,0,1,0,
            5,8,0,0,0,15,15,0,1,0,
            7,0,13,4,0,13,12,6,0,0,
            1,3,0,0,10,0,0,2,0,0,
            8,8,6,0,0,1,3,0,0,0,
            0,0,13,5,16,6,0,0,11,2,
            0,0,2,12,8,4,0,0,1,0,
            0,21,0,4,0,9,6,3,0,0,
            0,3,0,1,0,13,0,1,0,10,
            2,11,0,1,0,0,1,0,0,0,
            0,7,2,5,0,0,9,8,0,1,
            0,0,0,0,4,11,4,0,13,0,
            9,2,5,0,1,0,0,0,2,0,
            5,0,5,0,3,0,1,8,0,1,
            0,1,0,0,2,0,0,0,2,0,
            5,8,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            326,76,65,71,69,74,66,67,326,72,
            73,77,326,326,75,131,88,326,68,70,
            326,104,105,108,64,340,87,111,326,357,
            106,326,107,130,103,94,91,92,326,96,
            326,326,98,99,112,326,85,84,178,326,
            97,78,83,93,179,82,181,326,89,180,
            90,326,326,335,326,115,114,267,100,102,
            326,326,326,326,101,266,122,144,113,123,
            174,326,135,121,134,176,326,136,110,326,
            81,175,80,10,326,371,127,126,326,138,
            326,139,120,326,326,143,326,146,149,145,
            142,326,326,326,116,213,214,53,148,326,
            215,326,216,224,241,326,252,326,79,225,
            363,325,240,326,86,326,95,326,109,326,
            326,326,326,118,344,326,117,326,125,326,
            326,326,326,326,343,119,124,129,132,128,
            326,326,137,326,141,133,326,326,326,326,
            140,326,150,326,326,151,152,153,326,326,
            326,154,147,360,326,326,156,155,326,326,
            157,326,160,158,159,326,162,161,326,163,
            326,326,164,326,326,165,166,326,326,326,
            167,326,326,326,168,332,326,326,326,341,
            173,169,326,170,171,172,326,329,339,326,
            326,326,177,183,326,326,185,326,326,182,
            326,188,326,326,187,326,369,326,326,186,
            327,184,326,326,361,189,326,190,326,326,
            191,192,326,326,326,193,326,326,197,195,
            326,196,198,194,326,201,326,354,200,355,
            326,199,202,326,326,204,203,326,338,326,
            205,326,326,326,326,207,206,326,326,333,
            208,326,209,326,326,326,210,211,326,212,
            326,337,326,217,326,218,326,219,222,326,
            326,342,220,326,221,326,226,326,223,326,
            326,229,326,326,370,326,227,230,228,231,
            326,232,326,326,326,366,326,326,233,234,
            235,326,364,326,326,326,326,326,326,243,
            326,257,331,237,326,236,238,239,326,244,
            245,326,326,326,242,326,246,326,247,249,
            326,250,326,248,326,326,251,326,326,326,
            358,326,359,253,255,256,254,326,258,326,
            326,326,351,260,326,326,259,326,326,326,
            326,264,346,350,326,261,265,326,326,326,
            263,268,326,262,326,380,368,326,345,326,
            269,326,349,270,326,326,271,326,326,330,
            326,326,328,326,374,326,377,326,274,326,
            276,275,326,326,326,272,273,326,365,326,
            277,326,353,278,326,352,279,280,326,326,
            282,281,326,326,283,326,326,347,326,326,
            284,285,334,326,326,288,286,326,326,326,
            326,326,356,290,287,291,326,326,289,292,
            326,326,293,294,296,297,326,326,299,326,
            326,295,326,301,326,298,300,303,326,326,
            326,304,326,367,326,302,326,306,326,305,
            373,385,326,348,326,326,308,326,326,326,
            326,307,310,309,326,326,372,378,326,312,
            326,326,326,326,313,311,314,326,381,326,
            362,317,315,326,316,326,326,326,318,326,
            376,326,382,326,319,326,321,320,326,386,
            326,322,326,326,384,326,326,326,383,326,
            375,323
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
