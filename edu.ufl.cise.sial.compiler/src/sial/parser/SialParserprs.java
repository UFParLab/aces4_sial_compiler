package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 88;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 7;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 8;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 20;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 128;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 88;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 599;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 133;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 49;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 137;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 250;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 85;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 85;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 465;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 466;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return SialParsersym.isValidForParser; }


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
            0,1,0,0,0,0,0,1,0,0,
            1,0,1,0,0,0,1,0,0,0,
            1,0,1,0,1,0,0,0,0,0,
            0,0,0,0,0,0,0,0,1,0,
            1,0,0,0,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            6,36,7,9,30,41,39,49,45,35,
            37,31,33,34,3,13,24,27,29,32,
            43,2,4,5,8,10,11,12,14,15,
            16,17,18,19,20,21,22,23,25,26,
            28,38,40,42,44,46,47,48,1
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
            0,0,0,0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            3,8,0,1,1,1,2,0,2,3,
            0,1,1,2,1,1,1,1,1,1,
            0,3,1,1,1,1,1,1,1,3,
            3,6,1,1,1,1,1,1,3,1,
            5,1,1,1,1,1,1,4,3,1,
            1,6,3,0,1,1,0,0,3,2,
            0,3,2,1,2,2,7,11,7,4,
            1,2,5,8,3,3,3,3,4,2,
            4,3,4,4,2,2,2,2,2,1,
            0,2,3,3,1,1,1,1,1,1,
            4,1,3,1,1,1,3,0,3,3,
            1,1,1,1,1,1,1,1,3,1,
            1,1,1,1,1,2,1,1,1,1,
            1,1,1,-12,-58,0,0,0,-40,0,
            0,0,0,0,-93,0,0,0,-23,0,
            -14,0,0,-116,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            -2,0,-83,0,0,-112,0,0,0,0,
            0,0,0,0,0,-90,0,0,-10,-100,
            0,0,0,0,0,0,0,0,0,-73,
            0,0,-4,-91,0,0,0,0,0,0,
            -97,0,0,0,0,0,0,-102,0,0,
            -88,0,0,0,-113,0,0,-109,0,0,
            0,-115,0,0,-7,-5,0,0,-123,0,
            0,0,-19,0,0,-125,0,0,0,-1,
            0,0,0,-8,-98,0,0,-99,0,0,
            -95,0,-6,0,0,0,-31,0,0,-60,
            -3,0,0,0,0,-32,0,0,-11,0,
            0,0,-33,0,0,-34,0,0,-35,0,
            0,-43,0,0,0,0,-45,0,-46,0,
            -20,-57,-117,0,0,0,-77,0,-85,0,
            0,-86,0,0,-103,0,0,0,0,0,
            0,-87,0,0,-105,-114,0,-121,0,-120,
            0,0,-9,-18,-13,0,0,0,0,0,
            -15,0,-49,0,-16,0,-17,0,-21,0,
            -59,0,-22,0,-24,0,-25,0,-26,0,
            -29,0,-30,0,-36,0,-37,0,-38,0,
            -39,0,-41,0,-42,-44,0,0,-47,0,
            -71,-50,-82,0,0,-52,0,-53,0,-54,
            0,-55,0,0,-63,0,-64,-65,-27,-66,
            -67,-68,-69,-70,-79,0,0,0,0,0,
            0,0,0,-74,-28,-75,-76,0,0,0,
            -78,-84,0,-89,0,-48,0,0,-92,0,
            0,-94,0,-104,-106,0,0,-110,-111,-118,
            0,0,-51,0,-122,0,-126,0,-128,0,
            -56,-61,-62,-72,-80,-81,-96,-101,-107,-108,
            -119,-124,-127,0
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            22,22,24,15,15,3,3,3,23,23,
            26,27,27,28,28,16,16,16,16,16,
            16,25,25,29,29,29,29,29,29,29,
            30,34,31,37,37,37,37,37,38,38,
            17,32,39,39,39,39,39,39,33,40,
            18,18,35,36,41,41,19,19,4,4,
            12,13,13,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            42,43,43,5,5,11,11,11,11,6,
            6,2,14,14,21,21,45,45,10,10,
            20,46,46,46,46,46,46,44,44,47,
            48,48,48,48,48,9,9,7,7,7,
            7,7,8,1,272,304,454,130,151,304,
            454,130,125,131,383,265,125,131,381,3,
            13,40,426,100,231,51,349,149,315,404,
            135,243,334,335,23,24,25,26,27,28,
            29,72,49,370,454,130,304,454,130,94,
            90,131,118,125,131,381,304,454,130,144,
            304,454,130,125,131,110,60,125,131,119,
            363,454,130,30,77,426,100,126,131,349,
            149,112,426,100,134,92,349,149,1,426,
            100,33,104,349,149,159,426,100,33,104,
            349,149,189,426,100,335,70,349,149,234,
            426,100,106,333,349,149,273,426,100,107,
            36,349,149,203,109,231,51,189,106,40,
            1,265,9,106,254,271,458,106,453,452,
            3,31,171,459,325,38,106,453,395,148,
            388,422,386,106,453,397,106,453,80,106,
            453,398,106,102,236,41,460,155,56,155,
            56,149,36,106,40,203,416,106,102,106,
            453,83,106,453,81,227,66,2,65,225,
            39,457,106,453,79,227,265,439,227,232,
            106,102,246,109,302,109,279,439,301,328,
            439,106,343,271,462,106,382,106,443,109,
            14,35,451,106,302,106,351,106,89,106,
            88,106,85,106,270,106,400,106,401,106,
            402,106,403,106,72,109,106,417,414,106,
            63,154,109,306,53,421,106,455,106,456,
            106,31,106,30,173,106,82,3,3,388,
            390,390,390,390,109,106,48,424,312,322,
            78,77,76,75,228,389,309,108,204,145,
            261,228,106,84,228,211,233,186,218,106,
            103,190,106,434,109,106,52,326,109,109,
            228,440,461,145,239,106,67,106,463,106,
            68,224,312,373,391,392,394,310,337,398,
            374,266,401,396,466,466
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,0,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,0,
            0,2,0,1,0,0,34,2,0,0,
            1,41,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,33,0,
            38,0,70,71,72,0,0,1,78,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,0,1,0,0,2,
            2,0,1,74,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            33,33,73,0,0,2,39,0,0,2,
            2,82,0,0,0,1,85,0,0,1,
            84,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,76,0,1,
            0,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,64,65,66,
            67,68,69,0,80,2,0,0,40,2,
            0,1,0,0,1,77,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,32,31,0,0,2,2,0,0,
            0,0,0,1,41,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,0,32,0,1,0,0,81,0,0,
            2,0,40,42,43,44,45,46,47,48,
            49,50,51,52,53,54,55,56,57,58,
            59,60,0,30,0,32,0,29,29,36,
            37,33,37,38,35,34,75,0,0,0,
            79,0,0,0,83,0,55,56,57,58,
            59,60,0,1,30,29,61,62,63,0,
            1,35,0,0,42,43,44,45,46,47,
            48,49,50,51,52,53,54,0,0,0,
            0,0,30,0,32,0,0,0,36,30,
            0,32,29,31,0,36,0,0,35,0,
            0,0,0,0,0,0,0,30,30,0,
            31,31,0,0,31,34,29,0,0,29,
            0,0,0,0,39,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            466,599,466,530,379,292,376,373,537,139,
            289,286,283,276,267,363,355,367,365,371,
            369,361,299,297,375,399,415,359,357,4,
            8,473,466,599,91,3,561,472,466,466,
            599,539,530,379,292,376,373,537,139,289,
            286,283,276,267,363,355,367,365,371,369,
            361,299,297,375,399,415,359,357,471,466,
            571,466,562,563,564,466,466,599,438,530,
            379,292,376,373,537,139,289,286,283,276,
            267,363,355,367,365,371,369,361,299,297,
            375,399,415,359,357,466,599,466,466,472,
            472,466,599,353,530,379,292,376,373,537,
            139,289,286,283,276,267,363,355,367,365,
            371,369,361,299,297,375,399,415,359,357,
            471,471,263,21,466,473,432,10,22,473,
            473,235,466,466,57,599,465,466,466,599,
            536,530,379,292,376,373,537,139,289,286,
            283,276,267,363,355,367,365,371,369,361,
            299,297,375,399,415,359,357,435,466,599,
            466,530,379,292,376,373,537,139,289,286,
            283,276,267,363,355,367,365,371,369,361,
            299,297,375,399,415,359,357,578,580,577,
            579,581,582,59,405,473,58,58,445,473,
            466,599,99,466,599,330,530,379,292,376,
            373,537,139,289,286,283,276,267,363,355,
            367,365,371,369,361,299,297,375,399,415,
            359,357,516,307,61,62,473,473,466,466,
            54,58,466,599,540,530,379,292,376,373,
            537,139,289,286,283,276,267,363,355,367,
            365,371,369,361,299,297,375,399,415,359,
            357,12,521,466,599,117,466,176,466,466,
            472,466,447,11,11,11,11,11,11,11,
            11,11,11,11,11,11,482,483,481,484,
            485,486,466,598,466,593,466,429,429,594,
            200,471,589,586,567,309,345,466,466,466,
            347,466,466,466,341,466,482,483,481,484,
            485,486,466,599,333,228,588,587,590,93,
            599,575,466,466,508,509,510,511,512,513,
            392,390,499,500,501,502,503,466,466,108,
            129,466,598,466,593,466,466,466,594,598,
            69,593,303,307,466,594,466,466,498,466,
            466,466,466,466,466,466,466,553,552,466,
            221,307,466,466,258,255,154,466,466,429,
            466,466,466,466,449
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            128,139,92,90,92,88,5,311,311,38,
            91,39,311,61,88,88,88,141,147,38,
            311,88,252,88,88,88,5,5,88,88,
            88,88,88,88,88,88,88,88,88,1,
            88,311,88,88,133,133,88,251,161,311,
            257,88,88,88,88,259,137,1,165,252,
            255,181,88,252,252,179,179,179,179,311,
            172,295,167,183,213,217,88,221,88,255,
            181,306,165,88,88,88,88,125,264,1,
            184,88,7,88,318,314,222,169,88,1,
            314,95,8,311,319,88,215,314,125,311,
            311,1,9,318,320,169,88,265,317,88,
            319,88,266,213,320,88,219,88
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            37,1,32,36,30,0,2,81,1,4,
            3,22,23,6,5,24,8,7,9,19,
            20,17,18,10,11,12,13,14,15,21,
            26,25,27,28,16,77,0,2,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,57,55,56,58,59,60,79,75,83,
            4,3,22,23,6,5,24,8,7,9,
            19,20,17,18,10,11,12,13,14,15,
            21,26,25,27,28,16,74,1,0,85,
            2,73,82,0,1,4,3,22,23,6,
            5,24,8,7,9,19,20,17,18,10,
            11,12,13,14,15,21,26,25,27,28,
            16,41,78,0,38,1,0,73,82,33,
            2,0,33,2,1,0,33,2,85,0,
            57,55,56,58,59,60,42,43,44,45,
            46,47,50,51,52,53,54,48,49,0,
            33,2,32,0,33,2,30,36,32,1,
            0,66,64,67,65,68,69,0,33,2,
            31,0,2,1,4,3,22,23,6,5,
            24,8,7,9,19,20,17,18,10,11,
            12,13,14,15,21,26,25,27,28,16,
            84,0,33,2,29,0,33,2,39,0,
            2,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,76,0,
            31,70,71,72,34,0,80,0,74,76,
            77,40,84,78,2,1,4,3,22,23,
            6,5,24,8,7,9,19,20,17,18,
            10,11,12,13,14,15,21,26,25,27,
            28,16,41,0,64,65,66,67,68,69,
            1,32,36,30,31,38,62,61,37,63,
            33,2,0,35,29,0,77,2,81,1,
            4,3,22,23,6,5,24,8,7,9,
            19,20,17,18,10,11,12,13,14,15,
            21,26,25,27,28,16,40,0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            27,4,38,4,21,6,4,28,28,15,
            4,1,28,12,6,6,6,49,24,4,
            28,6,10,6,6,6,4,4,6,6,
            35,35,35,35,35,6,6,6,6,5,
            6,28,40,6,42,42,6,4,51,28,
            4,6,6,6,6,4,28,6,53,10,
            4,4,6,10,10,59,59,59,59,28,
            55,4,6,3,28,28,40,3,6,4,
            4,57,6,6,35,35,35,17,3,6,
            12,6,8,6,8,4,12,30,33,6,
            4,12,46,28,46,6,4,4,19,28,
            28,5,12,8,12,31,44,3,4,40,
            46,6,12,4,12,6,4,6
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static char nasr[] = {0,
            28,29,4,0,20,1,0,13,0,11,
            0,1,5,0,25,0,45,0,21,0,
            24,26,0,37,39,0,22,3,0,40,
            18,0,38,0,1,2,0,23,0,14,
            0,19,0,17,0,4,12,0,16,0,
            41,0,43,0,46,0,48,0,10,0
        };
    };
    public final static char nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static byte terminalIndex[] = {0,
            84,87,24,25,26,28,31,32,33,36,
            37,38,39,40,41,42,58,59,60,61,
            62,68,69,72,74,75,76,77,1,88,
            17,85,83,13,18,86,3,4,29,30,
            35,43,44,45,46,47,48,51,52,53,
            54,55,56,57,65,66,78,79,80,81,
            2,5,6,7,8,9,10,11,12,14,
            15,16,20,21,22,23,27,34,49,50,
            63,64,67,73,82,70,71,89
        };
    };
    public final static byte terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static byte nonterminalIndex[] = {0,
            92,108,93,0,104,112,111,0,116,0,
            109,105,0,107,0,96,100,103,0,106,
            114,90,0,91,0,94,0,95,97,0,
            0,0,0,0,0,0,98,99,101,102,
            0,110,0,113,115,117,0,118,0
        };
    };
    public final static byte nonterminalIndex[] = NonterminalIndex.nonterminalIndex;
    public final int nonterminalIndex(int index) { return nonterminalIndex[index]; }

    public interface ScopePrefix {
        public final static byte scopePrefix[] = {
            1,36,27,49,11,11,21,58
        };
    };
    public final static byte scopePrefix[] = ScopePrefix.scopePrefix;
    public final int scopePrefix(int index) { return scopePrefix[index]; }

    public interface ScopeSuffix {
        public final static byte scopeSuffix[] = {
            9,44,33,55,9,16,25,61
        };
    };
    public final static byte scopeSuffix[] = ScopeSuffix.scopeSuffix;
    public final int scopeSuffix(int index) { return scopeSuffix[index]; }

    public interface ScopeLhs {
        public final static char scopeLhs[] = {
            5,5,5,5,5,5,5,4
        };
    };
    public final static char scopeLhs[] = ScopeLhs.scopeLhs;
    public final int scopeLhs(int index) { return scopeLhs[index]; }

    public interface ScopeLa {
        public final static byte scopeLa[] = {
            41,40,77,40,41,78,84,33
        };
    };
    public final static byte scopeLa[] = ScopeLa.scopeLa;
    public final int scopeLa(int index) { return scopeLa[index]; }

    public interface ScopeStateSet {
        public final static byte scopeStateSet[] = {
            1,1,1,1,1,1,1,10
        };
    };
    public final static byte scopeStateSet[] = ScopeStateSet.scopeStateSet;
    public final int scopeStateSet(int index) { return scopeStateSet[index]; }

    public interface ScopeRhs {
        public final static char scopeRhs[] = {0,
            92,91,78,92,91,108,9,0,41,0,
            92,91,108,9,0,78,91,92,41,0,
            92,91,24,0,84,0,92,101,91,102,
            5,0,77,102,0,92,101,91,89,39,
            89,6,0,40,89,39,89,0,92,101,
            91,89,6,0,40,89,0,93,92,0,
            91,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            246,239,232,225,218,211,204,151,0,328,
            440,325,315,424,421,414,134,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,110,103,91,111,73,82,89,30,91,
            91,113,117,92,83,75,79,116,115,91,
            93,74,94,16,28,27,25,26,21,15,
            14,13,12,11,10,18,17,20,19,9,
            7,24,5,6,23,22,4,89,89,89,
            89,127,125,49,48,91,89,99,89,89,
            90,89,90,90,90,89,89,89,89,108,
            97,89,37,91,102,89,31,91,80,89,
            89,97,131,99,34,99,99,31,91,134,
            92,29,91,39,91,102,92,34,31,136,
            133,92,101,89,101,76,106,126,29,78,
            100,81,92,91,92,29,29,91,91,77,
            101,40,92,102,92,40,89,39
        };
    };
    public final static char inSymb[] = InSymb.inSymb;
    public final int inSymb(int index) { return inSymb[index]; }

    public interface Name {
        public final static String name[] = {
            "",
            ",",
            "+",
            "-",
            "*",
            "/",
            "^",
            ">",
            ">=",
            "<",
            "<=",
            "==",
            "!=",
            "=",
            "+=",
            "-=",
            "*=",
            "(",
            ")",
            "$empty",
            "sial",
            "endsial",
            "proc",
            "endproc",
            "return",
            "call",
            "pardo",
            "endpardo",
            "do",
            "in",
            "enddo",
            "cycle",
            "exit",
            "if",
            "else",
            "endif",
            "put",
            "get",
            "prepare",
            "request",
            "prequest",
            "collective",
            "execute",
            "aoindex",
            "moindex",
            "moaindex",
            "mobindex",
            "index",
            "laindex",
            "subindex",
            "of",
            "scalar",
            "int",
            "static",
            "temp",
            "local",
            "distributed",
            "served",
            "create",
            "delete",
            "allocate",
            "deallocate",
            "destroy",
            "where",
            "import",
            "predefined",
            "persistent",
            "special",
            "server_barrier",
            "sip_barrier",
            "constant",
            "configure",
            "section",
            "endsection",
            "print",
            "println",
            "print_index",
            "print_scalar",
            "sip_consistent",
            "scoped_extent",
            "contiguous",
            "auto_allocate",
            "EOF_TOKEN",
            "SINGLE_LINE_COMMENT",
            "IDENTIFIER",
            "INTLIT",
            "DOUBLELIT",
            "EOL",
            "STRINGLIT",
            "ERROR_TOKEN",
            "Sial",
            "Program",
            "Ident",
            "EOLs",
            "ImportProg",
            "Modifiers",
            "Modifier",
            "Dec",
            "ArrayKind",
            "DimensionList",
            "Dimension",
            "IndexKind",
            "Range",
            "RangeVal",
            "Statement",
            "WhereClause",
            "RelationalExpression",
            "Indices",
            "DataBlock",
            "AssignOp",
            "Arg",
            "Primary",
            "ScalarOrBlockVar",
            "Expression",
            "AllocIndex",
            "AllocIndexList",
            "UnaryExpression",
            "RelOp",
            "BinOp"
        };
    };
    public final static String name[] = Name.name;
    public final String name(int index) { return name[index]; }

    public final int originalState(int state) {
        return -baseCheck[state];
    }
    public final int asi(int state) {
        return asb[originalState(state)];
    }
    public final int nasi(int state) {
        return nasb[originalState(state)];
    }
    public final int inSymbol(int state) {
        return inSymb[originalState(state)];
    }

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
