package sial.parser;

public class SialLexerprs implements lpg.runtime.ParseTable, SialLexersym {
    public final static int ERROR_SYMBOL = 0;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 0;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 0;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 0;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 19;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 102;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 544;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 179;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 43;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 145;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 180;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 100;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 103;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 364;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 365;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return SialLexersym.isValidForParser; }


    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            1,0,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            12,11,13,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,4,
            16,2,3,5,6,7,8,9,10,14,
            15,43,1
        };
    };
    public final static byte prosthesesIndex[] = ProsthesesIndex.prosthesesIndex;
    public final int prosthesesIndex(int index) { return prosthesesIndex[index]; }

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,2,1,2,2,2,
            1,2,2,2,1,1,1,1,3,1,
            2,2,2,2,1,2,1,1,2,3,
            2,1,2,1,2,0,2,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            32,32,32,32,32,32,32,32,32,32,
            32,32,32,32,32,32,32,32,32,32,
            32,32,32,32,32,32,32,32,32,35,
            33,33,33,33,33,30,30,38,38,34,
            34,34,36,36,37,37,39,39,41,41,
            41,41,1,1,1,1,1,1,1,1,
            1,1,4,4,5,5,6,6,7,7,
            8,8,9,9,10,10,11,11,12,12,
            13,13,14,14,15,15,16,16,17,17,
            18,18,19,19,20,20,21,21,22,22,
            23,23,24,24,25,25,26,26,27,27,
            28,28,29,29,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,2,2,2,2,
            3,3,3,31,31,31,31,31,31,31,
            31,31,31,31,31,31,31,31,31,31,
            31,31,31,31,31,31,31,31,31,31,
            31,31,31,31,40,40,40,40,42,42,
            197,35,30,42,114,115,116,117,118,119,
            120,121,122,123,124,125,126,127,128,129,
            130,131,132,133,134,135,136,137,138,139,
            254,363,211,300,3,4,249,219,25,1,
            175,174,177,114,115,116,117,118,119,120,
            121,122,123,124,125,126,127,128,129,130,
            131,132,133,134,135,136,137,138,139,399,
            178,410,43,265,349,36,374,36,414,45,
            416,176,99,49,48,51,114,115,116,117,
            118,119,120,121,122,123,124,125,126,127,
            128,129,130,131,132,133,134,135,136,137,
            138,139,262,50,398,36,418,420,422,424,
            282,32,31,47,114,115,116,117,118,119,
            120,121,122,123,124,125,126,127,128,129,
            130,131,132,133,134,135,136,137,138,139,
            362,35,386,35,365,365,365,365,365,365,
            365,365,365,365,365,365,365,365,365,365,
            365,365,365,365,365,365,365,365,365,365,
            256,365,294,365,365
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,88,89,
            90,91,92,93,94,95,96,97,0,1,
            2,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,29,30,31,
            32,33,34,35,36,37,38,39,40,41,
            42,43,44,45,46,47,48,49,50,51,
            52,53,54,55,56,57,58,59,60,61,
            62,63,64,65,66,67,68,69,70,71,
            72,73,74,75,76,77,78,79,80,81,
            82,83,84,85,86,87,88,89,90,91,
            92,93,94,95,96,97,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,0,69,70,71,72,73,
            74,75,76,77,78,79,80,81,82,83,
            84,0,0,2,3,4,5,6,7,8,
            9,10,11,12,98,99,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,34,35,36,37,38,
            39,40,41,42,43,44,45,46,47,48,
            49,50,51,52,53,54,55,56,57,58,
            59,60,61,62,63,64,65,66,0,68,
            2,3,4,5,6,7,8,9,10,11,
            12,0,0,2,3,4,5,6,7,8,
            9,10,11,0,0,2,3,4,5,6,
            7,8,9,10,11,0,0,2,3,4,
            5,6,7,8,9,10,11,0,0,2,
            3,4,5,6,7,8,9,10,11,0,
            1,13,14,0,1,0,1,0,1,0,
            1,0,1,0,1,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,100,0,0,67,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            6,533,417,418,419,420,421,422,423,424,
            425,426,516,506,507,427,429,431,433,435,
            437,439,441,443,445,447,449,451,453,455,
            457,459,461,463,465,467,469,471,473,475,
            477,428,430,432,434,436,438,440,442,444,
            446,448,450,452,454,456,458,460,462,464,
            466,468,470,472,474,476,478,505,536,544,
            512,534,519,520,527,528,530,531,532,508,
            509,537,535,510,511,522,517,524,513,514,
            515,538,518,521,523,525,526,529,365,533,
            417,418,419,420,421,422,423,424,425,426,
            516,506,507,427,429,431,433,435,437,439,
            441,443,445,447,449,451,453,455,457,459,
            461,463,465,467,469,471,473,475,477,428,
            430,432,434,436,438,440,442,444,446,448,
            450,452,454,456,458,460,462,464,466,468,
            470,472,474,476,478,505,536,394,512,534,
            519,520,527,528,530,531,532,508,509,537,
            535,510,511,522,517,524,513,514,515,538,
            518,521,523,525,526,529,365,258,417,418,
            419,420,421,422,423,424,425,426,330,506,
            507,427,429,431,433,435,437,439,441,443,
            445,447,449,451,453,455,457,459,461,463,
            465,467,469,471,473,475,477,428,430,432,
            434,436,438,440,442,444,446,448,450,452,
            454,456,458,460,462,464,466,468,470,472,
            474,476,478,505,46,253,251,409,377,393,
            391,392,372,260,296,299,298,375,297,378,
            379,1,365,417,418,419,420,421,422,423,
            424,425,426,399,402,403,427,429,431,433,
            435,437,439,441,443,445,447,449,451,453,
            455,457,459,461,463,465,467,469,471,473,
            475,477,428,430,432,434,436,438,440,442,
            444,446,448,450,452,454,456,458,460,462,
            464,466,468,470,472,474,476,478,2,398,
            417,418,419,420,421,422,423,424,425,426,
            332,365,365,417,418,419,420,421,422,423,
            424,425,426,41,365,417,418,419,420,421,
            422,423,424,425,426,39,365,417,418,419,
            420,421,422,423,424,425,426,40,5,417,
            418,419,420,421,422,423,424,425,426,365,
            385,506,507,21,384,17,383,15,381,11,
            389,9,388,8,387,365,365,365,365,365,
            365,365,365,365,365,365,365,365,365,365,
            365,365,365,365,365,365,365,365,365,365,
            365,365,365,365,365,365,365,365,365,365,
            365,365,364,365,365,505
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
