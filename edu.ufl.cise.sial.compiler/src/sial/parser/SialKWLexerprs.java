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

    public final static int NUM_STATES = 297;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 30;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 441;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 70;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 32;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 71;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 25;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 31;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 370;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 371;
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
            8,7,7,14,18,6,11,14,4,4
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
            1,1,73,137,104,144,14,139,96,146,
            51,38,47,61,42,147,68,69,21,150,
            153,151,149,73,70,157,9,158,159,168,
            161,99,82,165,175,98,171,52,88,177,
            178,79,180,110,183,184,112,92,114,31,
            185,117,186,188,192,194,195,196,200,199,
            208,206,210,211,213,214,220,216,224,227,
            217,228,231,232,233,234,235,237,241,244,
            17,242,245,243,247,44,255,256,258,260,
            261,262,264,265,266,272,270,271,274,279,
            281,282,286,287,289,290,291,296,24,297,
            298,301,303,305,308,313,309,316,318,320,
            321,324,323,328,331,325,333,334,335,336,
            119,125,339,340,341,344,354,352,346,356,
            343,358,361,363,365,366,368,372,377,371,
            379,382,374,124,386,383,387,390,392,394,
            396,398,399,401,400,127,403,404,411,416,
            417,406,413,421,422,426,427,429,128,431,
            433,434,437,439,432,447,449,438,440,451,
            445,454,458,461,457,463,465,466,467,468,
            469,472,474,136,470,475,482,485,488,483,
            490,492,495,496,497,498,499,504,507,508,
            509,511,512,515,518,520,524,522,527,529,
            530,531,532,536,537,541,538,546,548,549,
            550,552,556,551,557,564,558,561,567,568,
            133,569,574,573,577,583,575,576,585,586,
            591,584,595,597,599,600,602,603,604,605,
            609,607,614,616,617,619,620,621,626,629,
            630,631,636,638,639,640,643,644,646,645,
            654,649,652,656,657,662,665,663,668,371,
            371
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
            2,6,11,0,7,8,3,20,10,0,
            1,0,1,0,5,4,0,4,0,6,
            4,3,4,0,0,1,0,0,12,5,
            3,8,0,7,2,0,0,10,0,16,
            5,15,10,0,9,0,0,1,0,0,
            0,6,0,1,16,6,0,0,0,11,
            0,25,5,0,0,9,23,0,18,2,
            0,13,12,9,0,1,0,0,2,0,
            3,11,0,0,0,0,3,0,9,4,
            3,0,10,0,0,0,0,4,0,0,
            5,17,8,12,5,0,8,0,1,0,
            0,6,0,0,1,0,0,8,6,0,
            4,2,7,0,1,15,0,0,2,2,
            0,0,0,0,0,2,0,7,7,7,
            0,0,0,0,0,1,0,13,2,13,
            10,8,11,11,0,0,2,0,3,0,
            0,0,2,0,0,0,2,0,11,0,
            0,0,7,0,15,12,5,7,0,1,
            0,0,21,10,15,0,0,7,0,0,
            0,10,3,8,8,0,0,0,10,3,
            0,11,0,3,0,10,2,0,0,7,
            3,14,0,5,2,0,1,0,1,0,
            0,1,0,0,0,6,3,0,6,2,
            0,1,0,0,0,0,4,13,0,0,
            0,6,0,0,5,0,13,9,5,4,
            16,0,12,0,1,0,5,0,0,17,
            0,1,0,8,0,0,1,0,11,2,
            0,0,10,0,10,4,0,1,0,1,
            7,0,0,13,3,0,0,2,6,0,
            1,0,6,0,1,0,5,0,0,0,
            0,3,0,0,1,0,0,8,11,9,
            0,6,0,3,19,0,0,2,16,3,
            0,0,1,11,4,0,0,1,0,4,
            0,0,0,0,6,3,0,0,0,0,
            4,11,4,10,0,14,0,1,0,1,
            0,14,2,0,15,2,0,0,14,2,
            0,1,0,7,0,0,0,0,0,0,
            2,0,1,0,0,2,11,8,16,5,
            13,0,0,17,0,1,22,0,1,0,
            9,0,10,2,0,0,0,0,0,3,
            11,7,7,0,1,8,0,0,0,3,
            0,0,0,15,0,1,9,0,1,0,
            9,0,14,0,14,6,0,4,0,0,
            0,0,6,5,13,0,0,0,2,8,
            0,11,5,8,15,0,6,0,0,0,
            0,0,3,2,9,0,0,0,2,4,
            0,11,14,0,17,2,0,0,0,1,
            13,4,0,0,0,0,0,5,12,6,
            4,21,0,0,0,0,1,5,4,15,
            0,1,9,18,0,1,0,1,0,0,
            1,0,0,0,0,7,0,3,0,1,
            8,10,9,0,1,0,0,2,0,0,
            0,3,16,4,0,0,10,2,0,0,
            0,3,3,3,14,0,1,0,0,0,
            1,3,0,0,0,0,9,5,0,6,
            5,0,8,0,1,0,0,2,0,3,
            9,0,0,2,0,1,18,0,0,2,
            8,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            371,87,76,85,80,82,77,78,371,88,
            83,84,86,371,91,131,371,74,79,81,
            371,117,118,371,75,121,194,222,196,223,
            371,119,116,120,164,219,195,371,99,221,
            115,371,220,371,104,107,371,105,198,98,
            371,371,201,199,101,200,97,96,144,95,
            371,102,385,106,100,103,380,371,371,371,
            111,110,371,128,129,112,114,126,371,150,
            109,371,113,149,151,137,127,371,138,147,
            136,371,371,159,161,371,145,371,10,93,
            416,92,160,371,142,141,89,146,135,371,
            153,371,158,371,154,157,371,163,371,162,
            167,241,240,371,371,242,52,371,166,243,
            286,429,371,274,427,371,371,408,371,265,
            302,273,335,371,301,371,371,108,371,371,
            371,94,371,123,402,122,371,371,371,125,
            371,370,132,371,371,130,90,371,124,389,
            371,133,134,139,371,140,371,371,388,371,
            148,143,371,371,371,371,156,371,152,168,
            169,371,155,371,371,371,371,170,371,371,
            172,165,171,405,173,371,174,371,175,371,
            371,176,371,371,180,371,371,177,179,371,
            185,181,182,371,183,178,371,371,184,186,
            371,371,371,371,371,189,371,187,188,377,
            371,371,371,371,371,386,371,190,384,191,
            192,193,374,197,371,371,440,371,202,371,
            371,371,205,371,371,371,207,371,203,371,
            371,371,208,371,204,441,209,372,371,414,
            371,371,206,211,210,371,371,406,371,371,
            371,212,216,213,214,371,371,371,215,224,
            371,217,371,225,371,218,227,371,371,226,
            228,399,371,230,229,371,231,371,383,371,
            371,233,371,371,371,232,234,371,378,235,
            371,236,371,371,371,371,238,237,371,371,
            371,382,371,371,245,371,239,244,247,250,
            387,371,246,371,248,371,249,371,371,252,
            371,254,371,251,371,371,257,371,253,258,
            371,371,255,371,256,259,371,260,371,262,
            264,371,371,261,263,371,371,415,266,371,
            411,371,267,371,409,371,268,371,371,371,
            371,271,371,371,276,371,371,376,270,272,
            371,280,371,277,269,371,371,278,275,279,
            371,371,437,281,282,371,371,284,371,283,
            371,371,371,371,285,403,371,371,371,371,
            288,404,289,287,371,400,371,290,371,291,
            371,396,293,371,292,434,371,371,395,433,
            371,294,371,295,371,371,371,371,371,371,
            299,371,300,371,371,391,296,424,430,303,
            298,371,371,297,371,390,413,371,394,371,
            304,371,305,306,371,371,371,371,371,307,
            375,373,418,371,309,421,371,371,371,310,
            371,371,371,308,371,410,311,371,432,371,
            312,371,398,371,397,313,371,314,371,371,
            371,371,316,317,315,371,371,371,392,320,
            371,319,322,321,318,371,379,371,371,371,
            371,371,325,326,323,371,371,371,329,327,
            371,328,401,371,324,330,371,371,371,336,
            331,334,371,371,371,371,371,338,333,337,
            339,332,371,371,371,371,412,340,343,341,
            371,344,428,342,371,393,371,345,371,371,
            347,371,371,371,371,346,371,349,371,350,
            422,417,348,371,438,371,371,352,371,371,
            371,353,351,354,371,371,407,355,371,371,
            371,356,420,357,425,371,431,371,371,371,
            359,426,371,371,371,371,358,360,371,361,
            362,371,363,371,364,371,371,435,371,419,
            365,371,371,366,371,367,439,371,371,436,
            368
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
