package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 86;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 7;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 8;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 20;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 128;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 86;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 596;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 131;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 48;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 134;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 247;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 85;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 85;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 464;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 465;
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
            0,0,0,0,0,0,0,0,0,1,
            0,0,0,0,0,1,0,1,0,1,
            0,1,0,0,0,0,0,0,1,0,
            1,0,1,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,1,0,0,
            0,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            6,35,7,9,29,40,38,48,44,34,
            36,28,30,32,33,3,13,24,27,31,
            42,2,4,5,8,10,11,12,14,15,
            16,17,18,19,20,21,22,23,25,26,
            37,39,41,43,45,46,47,1
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
            0,0,0,0,0,0
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
            1,6,3,1,0,0,3,2,0,3,
            2,1,2,2,7,11,7,4,1,2,
            5,8,3,3,3,3,4,2,4,3,
            4,4,2,2,2,2,2,1,0,2,
            3,3,1,1,1,1,1,1,4,1,
            3,1,1,1,3,0,3,3,1,1,
            1,1,1,1,1,1,3,1,1,1,
            1,1,1,2,1,1,1,1,1,1,
            1,-12,-58,0,0,0,-40,0,0,0,
            0,0,-2,0,0,0,-6,0,0,-14,
            0,0,-15,0,0,0,0,-4,0,0,
            0,0,0,0,0,0,0,0,-18,-83,
            0,0,-112,0,0,0,0,0,0,0,
            0,0,-90,0,0,0,-100,0,0,0,
            0,0,0,0,0,0,-73,0,0,-10,
            -91,0,0,0,0,0,0,-97,0,0,
            0,-93,0,0,-102,0,0,-88,0,0,
            0,-113,0,0,0,0,0,0,-115,0,
            0,-98,0,0,0,-123,0,0,0,-5,
            0,0,-125,0,0,-19,-1,0,0,0,
            0,-99,0,-31,0,0,-32,0,0,-23,
            -3,0,0,0,-59,0,-43,0,0,0,
            0,0,-33,0,0,-34,0,0,-35,0,
            0,0,0,0,0,-45,0,-46,0,0,
            -49,0,-57,-8,-103,0,0,0,0,0,
            -77,0,0,-85,0,0,0,0,0,-86,
            0,0,-87,0,0,0,-105,-109,0,-95,
            0,-116,0,-117,0,-7,-121,-120,0,0,
            0,-9,-13,0,0,0,-16,0,0,0,
            0,0,0,-17,0,-21,-22,0,0,-24,
            0,-25,0,-26,0,-29,0,-30,0,-36,
            0,-37,0,-38,0,-39,0,-41,0,-42,
            -44,0,0,-47,0,-50,-52,0,0,-53,
            0,-54,0,-55,0,-60,-63,0,-64,-65,
            -71,-66,-67,-68,-69,-70,0,-82,0,0,
            0,0,0,0,0,-74,-11,-75,-76,0,
            0,0,-78,-79,0,-89,0,-84,0,0,
            -92,0,-94,0,-104,-106,0,0,-110,-111,
            -118,0,0,-114,0,0,-122,0,-126,0,
            -128,0,-20,-27,0,-28,-48,0,-51,-56,
            -61,-62,-72,-80,-81,-96,-101,-107,-108,-119,
            -124,-127,0
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            22,22,24,16,16,3,3,3,23,23,
            26,27,27,28,28,17,17,17,17,17,
            17,25,25,29,29,29,29,29,29,29,
            30,34,31,37,37,37,37,37,38,38,
            18,32,39,39,39,39,39,39,33,40,
            19,19,35,36,12,12,4,4,13,14,
            14,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,41,42,
            42,5,5,11,11,11,11,6,6,2,
            15,15,21,21,44,44,10,10,20,45,
            45,45,45,45,45,43,43,46,47,47,
            47,47,47,9,9,7,7,7,7,7,
            8,1,272,304,453,128,150,304,453,128,
            123,129,398,72,123,129,391,30,294,13,
            40,447,98,30,291,346,260,396,34,246,
            169,333,23,24,25,26,27,28,29,302,
            363,453,128,304,453,128,92,88,129,116,
            123,129,391,304,453,128,14,304,453,128,
            123,129,108,58,123,129,117,309,453,128,
            108,77,447,98,124,129,346,260,112,447,
            98,90,224,346,260,1,447,98,32,102,
            346,260,159,447,98,132,295,346,260,189,
            447,98,38,51,346,260,234,447,98,104,
            69,346,260,273,447,98,333,231,346,260,
            158,458,30,40,30,452,451,30,452,387,
            3,35,457,261,1,107,9,30,100,143,
            38,133,41,30,452,389,30,452,78,30,
            452,390,408,380,240,377,149,54,149,54,
            459,149,54,231,370,265,158,200,64,222,
            63,30,100,53,30,452,81,170,430,2,
            30,452,79,30,452,77,456,265,32,102,
            224,229,38,51,30,40,76,265,30,100,
            430,243,370,370,317,407,443,30,376,105,
            430,49,39,461,30,449,370,30,293,450,
            30,265,30,87,30,86,30,83,30,386,
            30,392,30,393,30,394,30,395,30,70,
            370,30,409,406,30,61,370,30,454,413,
            30,455,30,31,30,30,3,30,80,3,
            3,154,391,391,391,391,370,418,306,416,
            310,313,76,75,74,73,156,143,371,109,
            201,212,320,156,30,48,156,208,30,82,
            215,30,101,30,425,370,30,52,434,370,
            370,156,431,460,224,236,183,30,65,30,
            462,30,66,144,308,187,335,374,327,147,
            153,191,390,394,230,397,366,388,307,389,
            266,398,228,465,465
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
            1,0,1,0,0,2,34,0,1,0,
            1,41,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,0,38,
            33,0,70,71,72,0,0,1,78,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,30,0,0,0,2,
            2,0,1,74,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            32,73,0,0,2,2,0,39,0,1,
            82,0,0,0,2,0,85,2,0,1,
            84,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,76,0,1,
            0,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,64,65,66,
            67,68,69,0,34,2,80,0,40,0,
            0,0,2,0,1,77,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,32,34,0,0,39,2,0,0,
            0,0,0,1,41,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,0,0,0,1,0,0,0,0,1,
            0,0,40,42,43,44,45,46,47,48,
            49,50,51,52,53,54,55,56,57,58,
            59,60,0,30,0,29,33,30,30,36,
            37,33,37,38,36,81,75,0,0,0,
            79,0,0,0,83,0,55,56,57,58,
            59,60,0,1,30,0,61,62,63,0,
            0,2,2,0,42,43,44,45,46,47,
            48,49,50,51,52,53,54,0,0,0,
            0,0,30,0,29,33,0,0,36,29,
            35,32,32,0,31,0,0,0,0,0,
            0,0,0,0,0,0,29,29,0,0,
            31,31,35,35,31,0,29,31,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            465,596,465,527,374,267,371,368,534,137,
            279,276,273,257,254,358,350,362,360,366,
            364,356,288,286,370,444,446,354,352,465,
            596,465,596,4,8,472,558,465,596,465,
            596,536,527,374,267,371,368,534,137,279,
            276,273,257,254,358,350,362,360,366,364,
            356,288,286,370,444,446,354,352,465,568,
            515,465,559,560,561,465,465,596,429,527,
            374,267,371,368,534,137,279,276,273,257,
            254,358,350,362,360,366,364,356,288,286,
            370,444,446,354,352,332,89,21,465,472,
            471,465,596,347,527,374,267,371,368,534,
            137,279,276,273,257,254,358,350,362,360,
            366,364,356,288,286,370,444,446,354,352,
            470,147,10,22,472,472,465,423,55,596,
            326,465,57,465,472,56,464,472,465,596,
            533,527,374,267,371,368,534,137,279,276,
            273,257,254,358,350,362,360,366,364,356,
            288,286,370,444,446,354,352,426,465,596,
            465,527,374,267,371,368,534,137,279,276,
            273,257,254,358,350,362,360,366,364,356,
            288,286,370,444,446,354,352,575,577,574,
            576,578,579,59,304,472,414,465,437,465,
            3,465,471,465,596,328,527,374,267,371,
            368,534,137,279,276,273,257,254,358,350,
            362,360,366,364,356,288,286,370,444,446,
            354,352,470,232,56,60,441,472,465,465,
            465,56,465,596,537,527,374,267,371,368,
            534,137,279,276,273,257,254,358,350,362,
            360,366,364,356,288,286,370,444,446,354,
            352,12,465,465,596,115,465,465,465,596,
            465,465,439,11,11,11,11,11,11,11,
            11,11,11,11,11,11,481,482,480,483,
            484,485,465,595,465,322,590,550,595,591,
            197,590,586,583,591,173,337,465,465,465,
            344,465,465,465,153,465,481,482,480,483,
            484,485,91,596,549,465,585,584,587,465,
            465,471,471,97,507,508,509,510,511,512,
            384,382,498,499,500,501,502,465,465,465,
            106,465,595,127,421,590,465,67,591,421,
            564,470,470,465,301,465,465,465,465,465,
            465,465,465,465,465,465,318,324,465,465,
            301,218,572,497,301,465,421,252
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            148,166,9,7,9,82,5,307,307,32,
            8,33,307,55,82,82,82,12,18,32,
            307,82,248,82,82,82,5,5,82,82,
            82,82,82,82,82,82,82,82,82,1,
            82,307,82,82,160,160,82,247,160,307,
            253,82,82,82,82,255,164,1,153,248,
            251,177,82,248,248,175,175,175,175,307,
            168,291,155,179,209,213,82,217,82,251,
            177,302,153,82,82,82,82,145,260,1,
            180,82,84,82,314,310,218,157,82,1,
            310,115,85,307,315,82,211,310,145,307,
            307,1,86,314,316,157,82,261,313,82,
            315,82,262,209,316,82,215,82
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            37,1,33,36,30,0,85,2,73,82,
            0,57,55,56,58,59,60,42,43,44,
            45,46,47,50,51,52,53,54,48,49,
            0,2,42,43,44,45,46,47,48,49,
            50,51,52,53,54,57,55,56,58,59,
            60,79,75,83,4,3,22,23,6,5,
            24,8,7,9,19,20,17,18,10,11,
            12,13,14,15,21,26,25,27,28,16,
            74,1,0,2,81,1,4,3,22,23,
            6,5,24,8,7,9,19,20,17,18,
            10,11,12,13,14,15,21,26,25,27,
            28,16,77,0,1,4,3,22,23,6,
            5,24,8,7,9,19,20,17,18,10,
            11,12,13,14,15,21,26,25,27,28,
            16,41,78,0,38,1,0,73,82,32,
            2,0,32,2,30,36,33,1,0,32,
            2,1,0,32,2,85,0,66,64,67,
            65,68,69,0,32,2,31,0,2,1,
            4,3,22,23,6,5,24,8,7,9,
            19,20,17,18,10,11,12,13,14,15,
            21,26,25,27,28,16,84,0,32,2,
            29,0,32,2,39,0,2,1,4,3,
            22,23,6,5,24,8,7,9,19,20,
            17,18,10,11,12,13,14,15,21,26,
            25,27,28,16,76,0,31,70,71,72,
            34,0,80,0,74,76,77,40,84,78,
            2,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,41,0,
            64,65,66,67,68,69,1,33,36,30,
            31,38,62,61,37,63,32,2,0,35,
            29,0,77,2,81,1,4,3,22,23,
            6,5,24,8,7,9,19,20,17,18,
            10,11,12,13,14,15,21,26,25,27,
            28,16,40,0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            28,4,38,4,22,6,4,29,29,13,
            4,1,29,8,6,6,6,11,25,4,
            29,6,36,6,6,6,4,4,6,6,
            33,33,33,33,33,6,6,6,6,5,
            6,29,42,6,44,44,6,4,44,29,
            4,6,6,6,6,4,29,6,40,36,
            4,4,6,36,36,55,55,55,55,29,
            53,4,6,3,29,29,42,3,6,4,
            4,57,6,6,33,33,33,17,3,6,
            8,6,15,6,15,4,8,19,31,6,
            4,8,46,29,46,6,4,4,49,29,
            29,5,8,15,8,20,51,3,4,42,
            46,6,8,4,8,6,4,6
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static char nasr[] = {0,
            28,29,4,0,20,1,0,1,5,0,
            17,0,25,0,14,0,44,0,40,19,
            0,24,26,0,37,39,0,22,3,0,
            38,0,1,2,0,11,0,23,0,42,
            0,15,0,12,0,4,13,0,21,0,
            18,0,45,0,10,0,47,0
        };
    };
    public final static char nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static byte terminalIndex[] = {0,
            82,85,24,25,26,28,31,32,33,36,
            37,38,39,40,41,42,58,59,60,61,
            62,68,69,70,72,73,74,75,1,86,
            17,81,83,13,18,84,3,4,29,30,
            35,43,44,45,46,47,48,51,52,53,
            54,55,56,57,65,66,76,77,78,79,
            2,5,6,7,8,9,10,11,12,14,
            15,16,20,21,22,23,27,34,49,50,
            63,64,67,71,80,87
        };
    };
    public final static byte terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static byte nonterminalIndex[] = {0,
            90,106,91,0,102,110,109,0,114,0,
            107,0,103,0,105,0,94,98,101,104,
            112,88,0,89,0,92,0,93,95,0,
            0,0,0,0,0,0,96,97,99,100,
            108,0,111,113,115,0,116,0
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
            41,40,77,40,41,78,84,32
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
            90,89,78,90,89,106,9,0,41,0,
            90,89,106,9,0,78,89,90,41,0,
            90,89,24,0,84,0,90,100,89,101,
            5,0,77,101,0,90,100,89,87,39,
            87,6,0,40,87,39,87,0,90,100,
            89,87,6,0,40,87,0,91,90,0,
            89,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            243,236,229,222,215,208,201,150,0,327,
            431,317,295,416,413,406,132,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,108,102,89,109,73,82,87,30,89,
            89,111,115,90,83,75,79,114,113,89,
            91,74,92,16,28,27,25,26,21,15,
            14,13,12,11,10,18,17,20,19,9,
            7,24,5,6,23,22,4,87,87,87,
            87,125,123,49,48,89,87,97,87,87,
            88,87,88,88,88,87,87,87,87,106,
            95,87,37,89,101,87,31,89,80,87,
            87,95,128,97,34,97,97,31,89,131,
            90,29,89,39,89,101,90,34,31,133,
            130,90,100,87,100,76,105,124,29,78,
            99,81,90,89,90,29,29,89,89,77,
            100,40,90,101,90,40,87,39
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
