package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 84;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 7;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 8;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 20;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 127;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 84;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 587;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 128;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 48;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 132;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 244;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 82;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 82;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 458;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 459;
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
            0,0,0,0,0,0,0,1,0,0,
            0,0,0,1,0,0,1,0,1,0,
            0,0,1,0,0,0,1,0,1,0,
            1,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,1,0,0,0,0,
            0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            6,35,7,9,29,40,38,48,44,34,
            36,30,32,33,3,13,24,27,28,31,
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
            0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            3,8,0,1,1,1,2,0,2,3,
            0,1,1,2,1,1,1,0,3,1,
            1,1,1,1,1,1,3,3,6,1,
            1,1,1,1,1,3,1,5,1,1,
            1,1,1,1,4,3,1,1,6,2,
            1,0,0,3,2,0,3,2,1,2,
            2,7,11,7,4,1,2,5,8,3,
            3,3,3,4,2,4,3,4,4,2,
            2,2,2,2,1,0,2,3,3,1,
            1,1,1,1,1,4,1,3,1,1,
            1,3,0,3,3,1,1,1,1,1,
            1,1,1,3,1,1,1,1,1,1,
            2,1,1,1,1,1,1,1,-12,-57,
            0,0,0,-40,0,0,0,0,0,-92,
            0,0,0,-23,0,-14,0,0,-116,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,-82,0,0,-111,
            0,0,0,0,0,0,0,0,0,-89,
            0,0,-2,-99,0,0,0,0,0,0,
            0,0,0,-72,0,0,-4,-90,0,0,
            0,0,0,0,-96,0,0,0,-7,0,
            0,-101,0,0,-11,-19,0,0,-112,0,
            0,-6,0,0,0,-114,0,0,-87,0,
            0,0,-122,0,0,-97,0,0,0,-124,
            0,0,-5,-1,0,0,0,-98,0,0,
            -15,0,0,0,0,-31,0,0,0,-32,
            0,0,-20,-3,0,0,0,-10,0,-33,
            0,0,0,-43,0,0,-34,0,0,-35,
            0,0,-45,0,-27,0,0,0,-46,0,
            -56,-28,0,0,-76,0,-84,0,0,-108,
            0,0,-85,0,0,0,-48,0,0,-86,
            0,0,-102,-104,-115,0,0,0,-18,-120,
            0,-119,0,0,0,0,-8,-9,-50,0,
            0,0,0,-13,0,0,0,-16,0,-17,
            0,-21,-22,0,0,-24,0,-25,0,-26,
            0,-29,0,-30,0,-36,0,-37,0,-38,
            0,-39,0,-41,0,-42,-44,0,0,-47,
            0,-49,-51,0,0,-52,0,-53,0,-54,
            0,-58,-59,-62,0,-63,-64,-70,-65,-66,
            -67,-68,-69,0,-81,0,0,0,0,0,
            0,0,-73,-55,-74,-75,0,0,0,-77,
            -78,0,-88,0,-83,0,0,-91,0,-93,
            0,-94,-103,0,-113,0,-105,0,-109,-60,
            -110,0,0,0,0,-117,-61,0,-71,0,
            -79,0,-121,0,-125,0,-127,0,-80,-95,
            -100,-106,-107,-118,-123,-126,0
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            22,22,24,15,15,3,3,3,23,23,
            26,27,27,28,28,16,16,16,25,25,
            29,29,29,29,29,29,29,30,34,31,
            37,37,37,37,37,38,38,17,32,39,
            39,39,39,39,39,33,40,18,18,35,
            36,19,19,4,4,12,13,13,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,41,42,42,5,5,
            11,11,11,11,6,6,2,14,14,21,
            21,44,44,10,10,20,45,45,45,45,
            45,45,43,43,46,47,47,47,47,47,
            9,9,7,7,7,7,7,8,1,263,
            293,439,125,146,293,439,125,120,126,395,
            218,120,126,388,3,13,40,307,95,33,
            37,342,144,313,393,130,216,319,334,20,
            21,22,23,24,25,26,36,332,439,125,
            293,439,125,89,85,126,113,120,126,388,
            293,439,125,103,293,439,125,120,126,105,
            55,120,126,114,295,439,125,30,74,307,
            95,121,126,342,144,109,307,95,87,297,
            342,144,1,307,95,140,302,342,144,153,
            307,95,33,327,342,144,183,307,95,35,
            99,342,144,225,307,95,341,48,342,144,
            264,307,95,69,222,342,144,197,33,37,
            101,33,50,376,452,373,33,437,430,264,
            33,437,384,141,31,35,183,1,105,9,
            33,437,386,451,33,97,38,33,437,75,
            33,437,387,38,51,334,453,243,405,38,
            51,222,366,129,197,33,97,33,437,78,
            35,99,61,33,437,76,2,372,60,450,
            33,437,74,324,324,341,48,219,226,321,
            324,102,33,97,240,431,431,361,361,144,
            268,215,431,46,361,14,455,263,33,372,
            33,329,361,33,291,404,33,382,33,84,
            33,83,33,80,33,383,33,389,33,390,
            33,391,33,392,33,67,361,33,406,403,
            33,58,361,33,441,410,33,449,33,28,
            33,27,104,3,33,77,3,3,151,380,
            380,380,380,361,415,299,413,303,310,73,
            72,71,70,150,147,338,106,198,140,422,
            150,33,45,150,205,33,79,212,33,98,
            33,423,218,361,167,218,425,33,49,361,
            373,361,436,180,454,314,150,379,320,381,
            233,375,184,33,62,33,456,33,63,382,
            360,369,385,371,255,386,219,459,459
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
            0,2,0,1,0,1,34,0,1,0,
            1,41,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,0,67,
            68,69,38,0,1,75,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,0,0,0,0,2,2,0,1,
            71,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,32,70,0,
            0,2,2,0,39,0,0,79,2,0,
            0,2,0,1,81,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,73,0,1,82,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,61,62,63,64,65,66,0,0,2,
            77,0,40,2,0,1,74,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,32,0,0,2,39,0,0,
            0,0,0,0,1,41,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,0,1,0,1,0,0,0,0,
            0,0,0,40,42,43,44,45,46,47,
            48,49,50,51,52,53,54,55,56,57,
            0,0,30,0,30,33,30,33,36,37,
            36,0,1,0,72,37,38,0,76,2,
            0,1,80,42,43,44,45,46,47,48,
            49,50,51,52,53,54,58,59,60,0,
            0,30,2,30,33,0,29,36,0,32,
            0,0,0,33,0,55,56,57,0,0,
            0,0,0,0,0,0,0,0,29,0,
            0,0,32,0,35,30,0,29,0,29,
            0,78,31,35,0,35,34,0,34,31,
            31,31,31,29,29,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            459,587,459,518,370,274,367,364,525,134,
            280,277,270,260,256,354,346,358,356,362,
            360,352,289,283,366,285,292,350,348,4,
            8,466,459,587,459,587,549,52,587,459,
            587,527,518,370,274,367,364,525,134,280,
            277,270,260,256,354,346,358,356,362,360,
            352,289,283,366,285,292,350,348,459,550,
            551,552,559,459,587,429,518,370,274,367,
            364,525,134,280,277,270,260,256,354,346,
            358,356,362,360,352,289,283,366,285,292,
            350,348,459,86,18,459,466,465,459,587,
            343,518,370,274,367,364,525,134,280,277,
            270,260,256,354,346,358,356,362,360,352,
            289,283,366,285,292,350,348,464,222,10,
            19,466,466,459,420,459,54,209,466,53,
            459,466,459,587,524,518,370,274,367,364,
            525,134,280,277,270,260,256,354,346,358,
            356,362,360,352,289,283,366,285,292,350,
            348,427,459,587,458,518,370,274,367,364,
            525,134,280,277,270,260,256,354,346,358,
            356,362,360,352,289,283,366,285,292,350,
            348,566,568,565,567,569,570,56,459,466,
            411,3,443,465,459,587,322,518,370,274,
            367,364,525,134,280,277,270,260,256,354,
            346,358,356,362,360,352,289,283,366,285,
            292,350,348,464,57,459,466,447,459,459,
            459,459,53,459,587,528,518,370,274,367,
            364,525,134,280,277,270,260,256,354,346,
            358,356,362,360,352,289,283,366,285,292,
            350,348,459,587,459,587,459,459,112,459,
            459,459,459,445,11,11,11,11,11,11,
            11,11,11,11,11,11,11,475,476,474,
            12,459,586,53,586,581,328,581,582,194,
            582,88,587,459,338,577,574,459,340,465,
            459,587,251,498,499,500,501,502,503,380,
            378,489,490,491,492,493,576,575,578,459,
            459,586,465,541,581,459,418,582,459,464,
            459,94,459,506,459,475,476,474,459,103,
            124,459,459,459,459,64,459,459,418,459,
            459,459,464,459,555,540,459,300,459,149,
            459,170,295,563,459,488,297,459,236,295,
            229,295,248,315,418
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            76,136,40,38,40,128,5,301,301,81,
            39,82,301,101,128,128,128,144,147,81,
            301,128,139,128,128,128,5,5,128,128,
            128,128,128,128,128,128,128,128,128,1,
            128,301,128,128,130,130,128,138,301,161,
            128,128,128,128,211,134,1,163,139,142,
            179,128,139,139,177,177,177,177,301,170,
            285,165,181,247,251,128,255,128,142,179,
            296,163,128,128,128,128,73,216,1,182,
            128,7,128,308,304,256,167,128,1,304,
            43,8,301,309,128,249,304,73,301,301,
            1,9,308,310,167,128,217,307,128,309,
            128,218,247,310,128,253,128
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            37,1,33,36,30,0,2,78,1,4,
            3,22,23,6,5,24,8,7,9,19,
            20,17,18,10,11,12,13,14,15,21,
            26,25,27,28,16,74,0,82,2,70,
            79,0,1,4,3,22,23,6,5,24,
            8,7,9,19,20,17,18,10,11,12,
            13,14,15,21,26,25,27,28,16,41,
            75,0,38,1,0,70,79,32,2,0,
            2,42,43,44,45,46,47,48,49,50,
            51,52,53,54,57,55,56,76,72,80,
            4,3,22,23,6,5,24,8,7,9,
            19,20,17,18,10,11,12,13,14,15,
            21,26,25,27,28,16,71,1,0,32,
            2,1,0,32,2,82,0,31,67,68,
            69,34,0,57,55,56,42,43,44,45,
            46,47,50,51,52,53,54,48,49,0,
            77,0,32,2,30,36,33,1,0,63,
            61,64,62,65,66,0,32,2,31,0,
            2,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,81,0,
            71,73,74,40,81,75,2,1,4,3,
            22,23,6,5,24,8,7,9,19,20,
            17,18,10,11,12,13,14,15,21,26,
            25,27,28,16,41,0,32,2,29,0,
            32,2,39,0,2,1,4,3,22,23,
            6,5,24,8,7,9,19,20,17,18,
            10,11,12,13,14,15,21,26,25,27,
            28,16,73,0,61,62,63,64,65,66,
            1,33,36,30,31,38,59,58,37,60,
            32,2,0,35,29,0,74,2,78,1,
            4,3,22,23,6,5,24,8,7,9,
            19,20,17,18,10,11,12,13,14,15,
            21,26,25,27,28,16,40,0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            28,4,36,4,25,6,4,29,29,38,
            4,1,29,12,6,6,6,49,17,4,
            29,6,10,6,6,6,4,4,6,6,
            33,33,33,33,33,6,6,6,6,5,
            6,29,40,6,42,42,6,4,29,4,
            6,6,6,6,4,29,6,51,10,4,
            4,6,10,10,55,55,55,55,29,53,
            4,6,3,29,29,40,3,6,4,4,
            57,6,6,33,33,33,20,3,6,12,
            6,8,6,8,4,12,22,31,6,4,
            12,46,29,46,6,4,4,44,29,29,
            5,12,8,12,23,15,3,4,40,46,
            6,12,4,12,6,4,6
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static char nasr[] = {0,
            28,29,4,0,20,1,0,13,0,11,
            0,1,5,0,17,0,37,39,0,44,
            0,40,18,0,24,26,0,22,3,0,
            38,0,1,2,0,23,0,25,0,14,
            0,19,0,21,0,4,12,0,16,0,
            42,0,45,0,10,0,47,0
        };
    };
    public final static char nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static byte terminalIndex[] = {0,
            80,83,24,25,26,28,31,32,33,36,
            37,38,39,40,41,42,58,59,60,61,
            62,68,69,72,74,75,76,77,1,84,
            17,79,81,13,18,82,3,4,29,30,
            35,43,44,45,46,47,48,51,52,53,
            54,55,56,57,65,66,70,2,5,6,
            7,8,9,10,11,12,14,15,16,20,
            21,22,23,27,34,49,50,63,64,67,
            73,78,71,85
        };
    };
    public final static byte terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static byte nonterminalIndex[] = {0,
            88,104,89,0,100,108,107,0,112,0,
            105,101,0,103,0,92,96,99,0,102,
            110,86,0,87,0,90,0,91,93,0,
            0,0,0,0,0,0,94,95,97,98,
            106,0,109,111,113,0,114,0
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
            41,40,74,40,41,75,81,32
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
            88,87,75,88,87,104,9,0,41,0,
            88,87,104,9,0,75,87,88,41,0,
            88,87,24,0,81,0,88,97,87,98,
            5,0,74,98,0,88,97,87,85,39,
            85,6,0,40,85,39,85,0,88,97,
            87,85,6,0,40,85,0,89,88,0,
            87,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            240,233,226,219,212,205,198,146,0,320,
            436,314,313,413,410,403,129,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,106,99,87,107,70,79,85,30,87,
            87,109,113,88,80,72,76,112,111,87,
            89,71,90,16,28,27,25,26,21,15,
            14,13,12,11,10,18,17,20,19,9,
            7,24,5,6,23,22,4,85,85,85,
            123,121,49,48,87,85,95,85,85,86,
            85,86,86,86,85,85,85,85,104,93,
            85,37,87,98,85,31,87,77,85,85,
            93,126,95,34,95,95,31,87,129,88,
            29,87,39,87,98,88,34,31,131,128,
            88,97,85,97,73,102,122,29,75,96,
            78,88,87,88,29,29,87,87,74,97,
            40,88,98,88,40,85,39
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
