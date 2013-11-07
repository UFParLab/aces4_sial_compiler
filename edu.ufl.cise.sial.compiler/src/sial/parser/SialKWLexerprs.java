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

    public final static int LA_STATE_OFFSET = 417;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 66;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 32;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 67;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 25;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 31;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 350;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 351;
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
            7,10,5,7,11,12,14,13,10,13,
            6,7,12,8,7,7
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
            1,1,1,1,1,1,1,1,69,99,
            123,133,89,13,131,57,94,29,66,33,
            138,46,65,22,140,129,135,143,74,149,
            145,150,153,155,156,41,81,159,160,68,
            158,164,96,9,166,172,82,162,101,174,
            175,97,177,108,179,109,181,182,183,185,
            188,190,189,198,199,200,191,201,209,211,
            205,214,216,217,219,222,223,224,225,226,
            228,232,227,80,234,240,236,244,50,247,
            248,142,249,252,253,254,255,257,261,259,
            263,267,268,269,270,272,274,27,277,278,
            282,280,289,290,293,294,299,301,303,305,
            306,307,313,308,285,314,316,318,319,53,
            114,321,322,329,323,331,334,335,110,338,
            336,340,341,344,345,351,349,353,356,357,
            117,358,363,365,366,355,371,369,373,374,
            375,377,121,376,378,380,390,389,393,394,
            381,396,397,120,400,401,404,405,407,413,
            406,417,421,414,419,423,424,427,430,433,
            435,431,436,439,437,440,441,445,449,17,
            453,455,456,462,464,450,457,468,469,471,
            472,473,474,481,475,479,477,484,486,487,
            494,492,496,500,501,503,504,502,508,509,
            513,514,518,510,520,523,524,525,528,529,
            530,534,539,526,540,541,542,545,549,550,
            543,555,556,558,561,562,565,568,569,571,
            574,575,572,577,578,586,580,589,592,594,
            583,597,587,598,602,605,606,608,613,609,
            616,611,617,621,623,625,626,629,628,351,
            351
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,0,9,
            10,11,0,13,6,3,0,5,18,19,
            4,0,1,2,24,4,0,11,0,3,
            9,5,0,12,13,3,8,5,6,13,
            0,15,2,15,18,0,1,19,3,0,
            10,6,0,4,22,3,0,12,6,10,
            11,5,13,7,0,0,1,0,12,4,
            5,7,8,0,7,8,3,4,14,0,
            0,0,1,3,0,4,5,7,0,10,
            17,11,13,0,1,0,0,2,0,20,
            0,1,6,15,4,9,13,0,0,0,
            3,3,5,0,1,20,0,4,9,0,
            0,13,0,25,8,6,17,7,0,10,
            0,15,0,1,0,5,16,0,1,0,
            1,0,0,9,0,23,4,19,0,0,
            2,7,0,4,0,0,2,0,0,0,
            1,0,21,0,12,0,9,2,13,11,
            7,0,11,0,0,1,0,6,0,3,
            0,0,0,10,0,3,6,0,0,0,
            0,4,8,4,13,17,8,0,0,0,
            0,0,5,5,0,5,16,8,0,1,
            0,7,2,0,1,0,0,2,0,3,
            2,0,0,0,0,0,0,0,7,7,
            7,0,0,0,8,0,12,12,11,0,
            1,10,9,0,9,2,0,0,0,2,
            2,0,0,0,0,9,0,4,0,7,
            0,1,0,7,13,7,0,0,0,0,
            16,0,10,0,8,8,0,0,10,0,
            9,0,6,10,0,16,7,6,0,0,
            2,14,0,0,2,6,12,4,0,1,
            0,1,0,1,0,0,0,0,1,4,
            6,5,0,0,2,0,3,0,0,0,
            0,0,0,5,4,4,4,12,0,1,
            0,1,15,0,0,0,3,0,1,0,
            0,1,8,0,0,10,3,3,0,10,
            0,1,0,1,0,0,0,0,4,2,
            12,6,0,7,0,0,1,5,0,5,
            0,1,0,0,0,0,0,0,1,0,
            0,9,8,3,11,6,18,11,0,0,
            15,2,0,0,6,0,0,5,3,0,
            0,5,9,0,0,0,0,0,9,6,
            10,5,0,0,10,3,0,1,0,14,
            0,1,0,0,2,7,0,14,2,0,
            0,2,0,1,0,0,0,14,0,0,
            0,7,2,0,0,15,2,9,0,0,
            2,12,0,17,0,0,0,22,4,10,
            8,0,1,0,1,9,11,0,0,2,
            0,0,0,0,0,7,0,7,0,8,
            0,1,8,0,6,0,0,1,16,16,
            14,0,7,0,1,0,5,14,3,0,
            0,0,0,0,1,5,4,0,0,0,
            9,12,0,0,2,8,8,0,5,0,
            1,4,0,0,0,0,17,0,0,0,
            6,2,5,0,11,2,14,9,0,0,
            0,0,0,3,0,1,21,8,0,0,
            12,10,3,5,0,0,14,0,4,4,
            0,0,1,3,0,1,9,0,0,1,
            0,0,2,0,0,1,0,0,11,0,
            7,10,0,6,8,0,0,2,0,1,
            11,0,10,0,3,2,0,0,0,3,
            14,0,1,6,0,0,2,0,0,2,
            0,6,0,1,6,0,0,1,8,4,
            0,1,0,1,0,0,2,0,0,2,
            0,0,0,8,6,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            351,82,71,75,77,72,80,73,351,79,
            78,83,351,81,140,87,351,86,76,74,
            288,351,111,112,70,115,351,287,351,209,
            113,210,351,114,110,101,95,99,98,206,
            10,208,396,365,207,351,103,94,105,351,
            128,106,351,187,100,227,351,104,228,188,
            190,91,189,90,351,351,96,351,89,97,
            360,107,109,351,135,134,121,122,108,351,
            351,351,143,130,351,142,144,131,351,183,
            120,129,185,351,93,351,351,139,351,184,
            351,146,151,382,147,150,92,351,351,351,
            154,157,153,351,229,138,351,230,238,351,
            53,156,351,350,412,271,239,260,351,388,
            351,251,351,85,351,88,259,351,102,351,
            116,351,351,118,351,84,119,117,351,351,
            123,124,351,125,351,351,369,351,351,351,
            133,351,193,351,126,351,136,368,127,132,
            137,351,145,351,351,149,351,141,351,152,
            351,351,351,148,351,159,158,351,351,351,
            351,161,160,162,385,155,163,351,351,351,
            351,351,164,165,351,168,167,166,351,169,
            351,171,170,351,172,351,351,173,351,174,
            175,351,351,351,351,351,351,351,176,177,
            357,351,351,351,182,351,178,179,180,351,
            366,181,354,351,186,364,351,351,351,192,
            194,351,351,351,351,191,351,197,351,196,
            351,394,351,352,195,386,351,351,351,351,
            198,351,199,351,200,201,351,351,202,351,
            204,351,211,205,351,203,213,212,351,351,
            214,379,351,351,216,215,224,217,351,218,
            351,363,351,219,351,351,351,351,223,221,
            220,358,351,351,222,351,225,351,351,351,
            351,351,351,362,231,232,234,226,351,233,
            351,235,367,351,351,351,236,351,240,351,
            351,243,237,351,351,241,244,245,351,242,
            351,246,351,248,351,351,351,351,254,395,
            247,249,351,250,351,351,391,252,351,253,
            351,389,351,351,351,351,351,351,262,351,
            351,256,356,268,257,263,255,258,351,351,
            261,264,351,351,265,351,351,266,269,351,
            351,270,267,351,351,351,351,351,384,383,
            272,274,351,351,273,275,351,276,351,380,
            351,277,351,351,279,278,351,376,417,351,
            351,416,351,280,351,351,351,375,351,351,
            351,281,285,351,351,413,371,282,351,351,
            286,284,351,283,351,351,351,393,289,291,
            405,351,370,351,374,355,290,351,351,292,
            351,351,351,351,351,353,351,399,351,402,
            351,295,296,351,297,351,351,390,293,294,
            378,351,298,351,415,351,299,377,300,351,
            351,351,351,351,304,302,303,351,351,351,
            305,301,351,351,372,306,307,351,359,351,
            310,308,351,351,351,351,309,351,351,351,
            312,315,313,351,311,316,381,314,351,351,
            351,351,351,320,351,322,318,319,351,351,
            317,321,324,323,351,351,325,351,326,327,
            351,351,392,329,351,330,328,351,351,373,
            351,351,398,351,351,332,351,351,410,351,
            331,397,351,333,403,351,351,334,351,336,
            335,351,387,351,337,338,351,351,351,339,
            406,351,341,340,351,351,342,351,351,343,
            351,401,351,414,407,351,351,346,345,344,
            351,411,351,347,351,351,409,351,351,408,
            351,351,351,348,400
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
