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

    public final static int NUM_STATES = 127;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 88;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 593;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 131;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 48;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 136;
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

    public final static int ACCEPT_ACTION = 461;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 462;
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
            0,0,0,0,0,0,0,0,0,1,
            0,0,0,0,0,0
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
            1,6,2,1,0,0,3,2,0,3,
            2,1,2,2,7,11,7,4,1,2,
            5,8,3,3,3,3,4,2,4,3,
            4,4,2,2,2,2,2,1,0,2,
            3,3,1,1,1,1,1,1,4,1,
            3,1,1,1,3,0,3,3,1,1,
            1,1,1,1,1,1,3,1,1,1,
            1,1,1,2,1,1,1,1,1,1,
            1,-12,-57,0,0,0,-40,0,0,0,
            0,0,-92,0,0,0,-23,0,-14,0,
            0,-116,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,-82,
            0,0,-111,0,0,0,0,0,0,0,
            0,0,-89,0,0,-2,-99,0,0,0,
            0,0,0,0,0,0,-72,0,0,-4,
            -90,0,0,0,0,0,0,-96,0,0,
            0,-7,0,0,-101,0,0,-11,-19,0,
            0,-112,0,0,-6,0,0,0,-114,0,
            0,-87,0,0,0,-122,0,0,-97,0,
            0,0,-124,0,0,-5,-1,0,0,0,
            -98,0,0,-15,0,0,0,0,-31,0,
            0,0,-32,0,0,-20,-3,0,0,0,
            -10,0,-33,0,0,0,-43,0,0,-34,
            0,0,-35,0,0,-45,0,-27,0,0,
            0,-46,0,-56,-28,0,0,-76,0,-84,
            0,0,-108,0,0,-85,0,0,0,-48,
            0,0,-86,0,0,-102,-104,-115,0,0,
            0,-18,-120,0,-119,0,0,0,0,-8,
            -9,-50,0,0,0,0,-13,0,0,0,
            -16,0,-17,0,-21,-22,0,0,-24,0,
            -25,0,-26,0,-29,0,-30,0,-36,0,
            -37,0,-38,0,-39,0,-41,0,-42,-44,
            0,0,-47,0,-49,-51,0,0,-52,0,
            -53,0,-54,0,-58,-59,-62,0,-63,-64,
            -70,-65,-66,-67,-68,-69,0,-81,0,0,
            0,0,0,0,0,-73,-55,-74,-75,0,
            0,0,-77,-78,0,-88,0,-83,0,0,
            -91,0,-93,0,-94,-103,0,-113,0,-105,
            0,-109,-60,-110,0,0,0,0,-117,-61,
            0,-71,0,-79,0,-121,0,-125,0,-127,
            0,-80,-95,-100,-106,-107,-118,-123,-126,0
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
            18,18,35,36,19,19,4,4,12,13,
            13,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,5,5,
            5,5,5,5,5,5,5,5,41,42,
            42,5,5,11,11,11,11,6,6,2,
            14,14,21,21,44,44,10,10,20,45,
            45,45,45,45,45,43,43,46,47,47,
            47,47,47,9,9,7,7,7,7,7,
            8,1,272,304,442,128,149,304,442,128,
            123,129,398,224,123,129,391,3,13,40,
            310,98,30,40,345,147,316,396,133,219,
            322,337,23,24,25,26,27,28,29,39,
            363,442,128,304,442,128,92,88,129,116,
            123,129,391,304,442,128,72,304,442,128,
            123,129,108,58,123,129,117,309,442,128,
            34,77,310,98,124,129,345,147,112,310,
            98,90,76,345,147,1,310,98,143,333,
            345,147,159,310,98,30,330,345,147,189,
            310,98,32,102,345,147,234,310,98,38,
            51,345,147,273,310,98,69,231,345,147,
            200,30,40,104,30,53,379,455,376,30,
            440,433,267,30,440,387,144,35,38,186,
            1,108,9,30,440,389,454,30,100,41,
            30,440,78,30,440,390,149,54,308,456,
            246,408,149,54,231,335,132,200,30,100,
            30,440,81,32,102,64,30,440,79,2,
            374,63,453,30,440,77,265,265,38,51,
            222,229,302,265,105,30,100,243,434,434,
            370,370,147,271,218,434,49,370,14,458,
            266,30,375,30,332,370,30,294,407,30,
            385,30,87,30,86,30,83,30,386,30,
            392,30,393,30,394,30,395,30,70,370,
            30,409,406,30,61,370,30,444,413,30,
            452,30,31,30,30,107,3,30,80,3,
            3,154,391,391,391,391,370,418,306,416,
            306,313,76,75,74,73,156,153,371,109,
            201,143,425,156,30,48,156,208,30,82,
            215,30,101,30,426,224,370,170,224,428,
            30,52,370,191,370,439,183,457,317,156,
            390,323,394,236,230,187,30,65,30,459,
            30,66,397,366,388,307,389,266,398,228,
            462,462
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
            0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            462,593,462,524,373,277,370,367,531,137,
            283,280,273,263,259,357,349,361,359,365,
            363,355,292,286,369,288,295,353,351,462,
            593,462,593,4,8,469,555,462,593,462,
            593,533,524,373,277,370,367,531,137,283,
            280,273,263,259,357,349,361,359,365,363,
            355,292,286,369,288,295,353,351,462,565,
            512,462,556,557,558,462,462,593,432,524,
            373,277,370,367,531,137,283,280,273,263,
            259,357,349,361,359,365,363,355,292,286,
            369,288,295,353,351,331,89,21,462,469,
            468,462,593,346,524,373,277,370,367,531,
            137,283,280,273,263,259,357,349,361,359,
            365,363,355,292,286,369,288,295,353,351,
            467,225,10,22,469,469,462,423,55,593,
            212,462,57,462,469,56,461,469,462,593,
            530,524,373,277,370,367,531,137,283,280,
            273,263,259,357,349,361,359,365,363,355,
            292,286,369,288,295,353,351,430,462,593,
            462,524,373,277,370,367,531,137,283,280,
            273,263,259,357,349,361,359,365,363,355,
            292,286,369,288,295,353,351,572,574,571,
            573,575,576,59,300,469,414,462,446,462,
            3,462,468,462,593,325,524,373,277,370,
            367,531,137,283,280,273,263,259,357,349,
            361,359,365,363,355,292,286,369,288,295,
            353,351,467,239,56,60,450,469,462,462,
            462,56,462,593,534,524,373,277,370,367,
            531,137,283,280,273,263,259,357,349,361,
            359,365,363,355,292,286,369,288,295,353,
            351,12,462,462,593,115,462,462,462,593,
            462,462,448,11,11,11,11,11,11,11,
            11,11,11,11,11,11,478,479,477,480,
            481,482,462,592,462,318,587,547,592,588,
            197,587,583,580,588,173,341,462,462,462,
            343,462,462,462,254,462,478,479,477,480,
            481,482,91,593,546,462,582,581,584,462,
            462,468,468,97,504,505,506,507,508,509,
            383,381,495,496,497,498,499,462,462,462,
            106,462,592,127,421,587,462,67,588,421,
            561,467,467,462,298,462,462,462,462,462,
            462,462,462,462,462,462,303,152,462,462,
            298,232,569,494,298,462,421,251
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            76,139,40,38,40,131,5,307,307,81,
            39,82,307,104,131,131,131,147,153,81,
            307,131,142,131,131,131,5,5,131,131,
            131,131,131,131,131,131,131,131,131,1,
            131,307,131,131,133,133,131,141,307,167,
            131,131,131,131,217,137,1,169,142,145,
            185,131,142,142,183,183,183,183,307,176,
            291,171,187,253,257,131,261,131,145,185,
            302,169,131,131,131,131,73,222,1,188,
            131,7,131,314,310,262,173,131,1,310,
            43,8,307,315,131,255,310,73,307,307,
            1,9,314,316,173,131,223,313,131,315,
            131,224,253,316,131,259,131
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            37,1,33,36,30,0,2,81,1,4,
            3,22,23,6,5,24,8,7,9,19,
            20,17,18,10,11,12,13,14,15,21,
            26,25,27,28,16,77,0,85,2,73,
            82,0,1,4,3,22,23,6,5,24,
            8,7,9,19,20,17,18,10,11,12,
            13,14,15,21,26,25,27,28,16,41,
            78,0,38,1,0,73,82,32,2,0,
            2,42,43,44,45,46,47,48,49,50,
            51,52,53,54,57,55,56,58,59,60,
            79,75,83,4,3,22,23,6,5,24,
            8,7,9,19,20,17,18,10,11,12,
            13,14,15,21,26,25,27,28,16,74,
            1,0,32,2,1,0,32,2,85,0,
            31,70,71,72,34,0,57,55,56,58,
            59,60,42,43,44,45,46,47,50,51,
            52,53,54,48,49,0,80,0,32,2,
            30,36,33,1,0,66,64,67,65,68,
            69,0,32,2,31,0,2,1,4,3,
            22,23,6,5,24,8,7,9,19,20,
            17,18,10,11,12,13,14,15,21,26,
            25,27,28,16,84,0,74,76,77,40,
            84,78,2,1,4,3,22,23,6,5,
            24,8,7,9,19,20,17,18,10,11,
            12,13,14,15,21,26,25,27,28,16,
            41,0,32,2,29,0,32,2,39,0,
            2,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,76,0,
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
            84,87,24,25,26,28,31,32,33,36,
            37,38,39,40,41,42,58,59,60,61,
            62,68,69,72,74,75,76,77,1,88,
            17,83,85,13,18,86,3,4,29,30,
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
            110,0,113,115,117,0,118,0
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
            243,236,229,222,215,208,201,149,0,323,
            439,317,316,416,413,406,132,0
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
            127,125,49,48,91,89,99,89,89,90,
            89,90,90,90,89,89,89,89,108,97,
            89,37,91,102,89,31,91,80,89,89,
            97,130,99,34,99,99,31,91,133,92,
            29,91,39,91,102,92,34,31,135,132,
            92,101,89,101,76,106,126,29,78,100,
            81,92,91,92,29,29,91,91,77,101,
            40,92,102,92,40,89,39
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
