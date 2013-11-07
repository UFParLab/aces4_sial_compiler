package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 92;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 7;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 8;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 20;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 132;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 92;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 628;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 137;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 48;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 140;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 191;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 91;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 91;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 490;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 491;
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
            0,0,0,0,0,0,0,1,0,0,
            0,1,0,1,0,1,0,1,0,0,
            0,0,0,0,1,0,1,0,1,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,1,0,0,0,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            6,35,7,38,48,9,29,40,44,34,
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
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static short baseCheck[] = {0,
            3,8,0,1,1,1,2,0,2,3,
            0,1,1,2,1,1,1,1,1,1,
            0,3,1,1,1,1,1,1,1,3,
            3,6,1,1,1,1,1,1,3,1,
            5,1,1,1,1,1,1,4,3,1,
            1,6,3,1,0,0,3,2,0,3,
            2,1,2,2,7,11,7,4,1,2,
            5,8,3,3,3,3,4,2,4,3,
            4,4,2,2,2,2,2,1,0,2,
            3,3,1,1,2,2,2,2,1,1,
            1,1,1,1,4,1,3,1,1,1,
            3,0,3,3,1,1,1,1,1,1,
            1,1,3,1,1,1,1,1,1,2,
            1,1,1,1,1,1,1,-12,-92,0,
            -63,0,0,0,0,0,-94,0,0,0,
            0,0,-6,0,0,0,-87,0,0,0,
            0,0,-102,0,0,0,0,0,0,0,
            0,0,0,0,-27,-44,0,0,-2,0,
            0,0,0,0,0,0,0,-116,0,0,
            -1,0,0,0,-65,0,0,0,-3,-104,
            0,0,0,0,0,0,0,0,0,-14,
            0,0,0,-35,0,0,0,0,-23,0,
            0,0,0,0,-24,0,0,-78,0,0,
            -25,0,0,0,0,0,-26,0,0,-82,
            0,0,-77,0,0,0,0,0,-95,0,
            0,-101,0,0,-4,0,0,-19,0,0,
            -106,0,0,-117,0,0,-7,0,0,-5,
            0,0,-119,0,0,-127,0,0,-8,0,
            0,0,0,0,-129,0,0,-103,0,-47,
            0,0,0,0,0,0,0,-36,0,0,
            -37,0,0,-11,0,0,-38,0,0,-39,
            0,0,-49,0,-50,0,-53,0,-61,-15,
            0,0,-81,0,0,0,0,-10,0,-89,
            0,0,-113,0,0,-120,0,0,-90,0,
            0,-91,0,0,-107,-109,-121,0,-124,0,
            0,0,0,0,0,-125,-9,0,0,0,
            -18,0,-13,0,0,0,-16,0,0,-17,
            0,-21,-22,0,0,-28,0,0,-29,0,
            -30,0,-33,0,-34,0,-40,0,-41,0,
            -42,0,-43,0,-45,0,-46,-48,0,0,
            -51,0,-54,-56,0,0,-57,0,-58,0,
            -59,0,-64,-68,0,-69,-70,-76,-71,-72,
            -73,-74,-75,-83,0,0,0,0,0,0,
            0,0,-79,-80,-86,0,0,-88,0,-93,
            -96,0,-97,-98,0,0,-99,-108,-110,0,
            0,-118,-114,-122,0,0,0,-20,-115,0,
            0,0,0,-126,0,0,-130,0,-132,0,
            -31,-32,-52,-55,-60,-62,-66,-67,-84,-85,
            -100,0,-105,-111,-112,-123,-128,-131,0
        };
    };
    public final static short baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static short rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            22,22,24,16,16,3,3,3,23,23,
            26,27,27,28,28,17,17,17,17,17,
            17,25,25,29,29,29,29,29,29,29,
            30,34,31,37,37,37,37,37,38,38,
            18,32,39,39,39,39,39,39,33,40,
            19,19,35,36,12,12,6,6,13,14,
            14,7,7,7,7,7,7,7,7,7,
            7,7,7,7,7,7,7,7,7,7,
            7,7,7,7,7,7,7,7,41,42,
            42,7,7,7,7,7,7,7,7,11,
            11,11,11,8,8,2,15,15,21,21,
            44,44,10,10,20,45,45,45,45,45,
            45,43,43,46,47,47,47,47,47,9,
            9,4,4,4,4,4,5,1,299,246,
            108,336,476,134,210,129,135,336,476,134,
            435,129,135,40,279,13,114,413,476,134,
            110,88,135,415,51,258,361,363,23,24,
            25,26,27,28,29,42,336,476,134,161,
            129,135,484,483,92,418,141,122,336,476,
            134,43,129,135,255,42,423,418,90,164,
            336,476,134,41,129,135,438,199,58,123,
            46,473,104,179,40,478,477,372,175,381,
            476,134,270,98,135,381,476,134,291,97,
            135,381,476,134,249,96,135,381,476,134,
            291,95,135,381,476,134,252,130,135,83,
            473,104,124,473,104,37,372,175,341,372,
            175,1,473,104,171,473,104,402,372,175,
            163,372,175,207,473,104,255,473,104,383,
            372,175,328,372,175,300,473,104,40,40,
            40,106,372,175,1,407,9,404,40,478,
            414,40,478,416,165,433,38,40,478,78,
            40,478,417,248,54,248,54,248,54,43,
            40,317,255,40,106,64,485,63,160,53,
            40,478,81,246,108,2,415,51,481,40,
            478,79,40,478,77,345,345,40,40,40,
            106,264,273,138,111,49,345,383,459,459,
            304,342,285,383,487,39,458,40,403,459,
            40,474,383,40,319,475,40,413,14,40,
            87,40,86,40,83,40,195,40,419,40,
            420,40,421,40,422,40,70,383,40,434,
            228,40,61,383,40,479,240,40,480,40,
            31,40,30,169,40,80,42,42,296,426,
            426,426,426,383,40,48,440,339,342,76,
            75,74,73,338,121,343,443,447,40,82,
            291,40,107,429,40,448,261,429,383,40,
            52,452,429,383,291,157,454,345,168,383,
            276,346,486,147,40,65,356,40,488,40,
            66,409,422,419,36,243,424,295,425,427,
            428,3,200,209,339,257,433,418,430,491,
            491
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
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,0,0,35,2,0,
            1,0,0,41,2,0,1,47,3,4,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,34,
            38,40,0,1,84,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,76,77,78,
            0,86,2,0,1,80,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,29,30,31,32,33,34,38,0,
            0,2,0,0,0,45,2,0,0,2,
            0,1,90,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,82,0,1,0,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,79,0,35,2,0,1,0,1,41,
            88,91,46,83,0,1,0,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,44,
            0,35,2,0,0,0,0,41,0,0,
            1,47,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,
            31,32,33,34,40,0,1,0,0,2,
            0,0,0,0,0,46,48,49,50,51,
            52,53,54,55,56,57,58,59,60,61,
            62,63,64,65,66,70,71,72,73,74,
            75,36,35,35,39,38,0,42,43,81,
            0,1,0,85,2,43,44,89,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,0,61,62,63,64,65,66,0,67,
            68,69,0,1,0,1,36,0,0,39,
            38,0,42,0,0,0,0,0,0,0,
            2,87,0,0,2,0,0,36,0,0,
            0,0,0,0,36,0,0,0,36,0,
            0,39,35,39,42,37,0,36,0,0,
            37,37,37,0,37,0,40,0,0,0,
            0,0,0,0,45,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            491,628,491,553,401,290,398,395,560,176,
            310,307,301,298,214,385,376,389,387,393,
            391,383,315,313,397,471,472,381,379,584,
            585,237,231,225,219,491,4,441,498,491,
            628,491,3,596,497,491,628,562,553,401,
            290,398,395,560,176,310,307,301,298,214,
            385,376,389,387,393,391,383,315,313,397,
            471,472,381,379,584,585,237,231,225,219,
            496,590,491,628,453,553,401,290,398,395,
            560,176,310,307,301,298,214,385,376,389,
            387,393,391,383,315,313,397,471,472,381,
            379,584,585,237,231,225,219,591,592,593,
            491,424,497,491,628,373,553,401,290,398,
            395,560,176,310,307,301,298,214,385,376,
            389,387,393,391,383,315,313,397,471,472,
            381,379,584,585,237,231,225,219,496,21,
            491,498,491,8,10,444,498,22,89,498,
            491,628,559,553,401,290,398,395,560,176,
            310,307,301,298,214,385,376,389,387,393,
            391,383,315,313,397,471,472,381,379,584,
            585,237,231,225,219,449,491,628,491,553,
            401,290,398,395,560,176,310,307,301,298,
            214,385,376,389,387,393,391,383,315,313,
            397,471,472,381,379,584,585,237,231,225,
            219,153,57,333,498,491,628,55,628,604,
            267,490,464,349,491,628,491,553,401,290,
            398,395,560,176,310,307,301,298,214,385,
            376,389,387,393,391,383,315,313,397,471,
            472,381,379,584,585,237,231,225,219,600,
            56,347,498,491,491,491,491,523,56,491,
            628,563,553,401,290,398,395,560,176,310,
            307,301,298,214,385,376,389,387,393,391,
            383,315,313,397,471,472,381,379,584,585,
            237,231,225,219,330,491,628,491,491,497,
            491,12,121,491,56,467,11,11,11,11,
            11,11,11,11,11,11,11,11,11,507,
            508,506,509,510,511,607,609,606,608,610,
            611,627,441,336,622,496,491,623,243,367,
            491,628,491,370,497,618,615,320,533,534,
            535,536,537,538,411,409,524,525,526,527,
            528,491,507,508,506,509,510,511,491,617,
            616,619,91,628,491,628,627,67,103,622,
            496,491,623,133,491,112,491,491,59,491,
            498,188,60,491,498,491,491,357,491,491,
            491,491,491,491,576,491,491,491,627,491,
            491,622,441,541,623,323,491,575,491,491,
            323,323,139,491,288,491,163,491,491,491,
            491,491,491,491,469
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            10,134,89,87,89,192,8,343,343,136,
            88,137,343,159,192,192,192,194,200,136,
            343,192,216,216,216,216,278,192,192,192,
            8,8,192,192,192,192,192,192,192,192,
            192,192,192,4,192,343,192,192,128,128,
            192,277,128,343,283,192,192,192,192,285,
            132,327,4,214,278,281,230,192,278,278,
            228,228,228,228,343,221,216,15,232,236,
            192,51,192,281,230,338,214,192,192,192,
            192,1,290,4,16,192,240,192,350,346,
            52,218,192,4,346,92,241,343,351,192,
            234,346,1,343,343,4,242,350,352,218,
            192,291,349,192,351,192,292,232,352,192,
            238,192
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            44,1,0,43,1,39,42,36,0,79,
            88,38,2,0,2,1,4,3,22,23,
            6,5,24,8,7,9,19,20,17,18,
            10,11,12,13,14,15,21,26,25,27,
            28,16,29,30,31,32,33,34,90,0,
            2,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,29,30,
            31,32,33,34,82,0,91,2,79,88,
            0,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,29,30,
            31,32,33,34,47,84,0,38,2,1,
            0,38,2,91,0,2,48,49,50,51,
            52,53,54,55,56,57,58,59,60,63,
            61,62,64,65,66,85,81,89,4,3,
            22,23,6,5,24,8,7,9,19,20,
            17,18,10,11,12,13,14,15,21,26,
            25,27,28,16,29,30,31,32,33,34,
            80,1,0,63,61,62,64,65,66,48,
            49,50,51,52,53,56,57,58,59,60,
            54,55,0,38,2,36,42,39,1,0,
            72,70,73,71,74,75,0,38,2,37,
            0,38,2,35,0,38,2,45,0,2,
            87,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,29,30,
            31,32,33,34,83,0,37,76,77,78,
            40,0,86,0,80,82,83,46,90,84,
            2,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,29,30,
            31,32,33,34,47,0,70,71,72,73,
            74,75,1,39,42,36,37,44,68,67,
            43,69,38,2,0,41,35,0,83,2,
            87,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,29,30,
            31,32,33,34,46,0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            15,4,18,4,29,13,4,16,16,38,
            4,1,16,20,13,13,13,47,26,4,
            16,13,13,13,13,13,10,13,13,13,
            4,4,13,13,23,23,23,23,23,13,
            13,13,13,12,13,16,34,13,36,36,
            13,4,36,16,4,13,13,13,13,4,
            16,4,13,49,10,4,4,13,10,10,
            53,53,53,53,16,51,13,3,16,16,
            34,3,13,4,4,55,13,13,23,23,
            23,5,3,13,20,13,57,13,57,4,
            20,7,32,13,4,20,42,16,42,13,
            4,4,40,16,16,12,20,57,20,8,
            45,3,4,34,42,13,20,4,20,13,
            4,13
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static char nasr[] = {0,
            28,29,6,0,44,0,40,19,0,11,
            0,20,1,0,22,3,0,23,0,1,
            7,0,1,2,0,37,39,0,24,26,
            0,38,0,15,0,12,0,25,0,21,
            0,6,13,0,18,0,17,0,42,0,
            45,0,10,0,47,0,14,0
        };
    };
    public final static char nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static byte terminalIndex[] = {0,
            88,91,24,25,26,28,31,32,33,36,
            37,38,39,40,41,42,58,59,60,61,
            62,68,69,70,72,73,74,75,80,81,
            82,83,84,85,1,92,17,87,89,13,
            18,90,3,4,29,30,35,43,44,45,
            46,47,48,51,52,53,54,55,56,57,
            65,66,76,77,78,79,2,5,6,7,
            8,9,10,11,12,14,15,16,20,21,
            22,23,27,34,49,50,63,64,67,71,
            86,93
        };
    };
    public final static byte terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static byte nonterminalIndex[] = {0,
            96,112,97,115,0,0,108,116,120,0,
            113,0,109,0,111,0,100,104,107,110,
            118,94,0,95,0,98,0,99,101,0,
            0,0,0,0,0,0,102,103,105,106,
            114,0,117,119,121,0,122,0
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
            7,7,7,7,7,7,7,6
        };
    };
    public final static char scopeLhs[] = ScopeLhs.scopeLhs;
    public final int scopeLhs(int index) { return scopeLhs[index]; }

    public interface ScopeLa {
        public final static byte scopeLa[] = {
            47,46,83,46,47,84,90,38
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
            98,95,84,98,95,112,9,0,47,0,
            98,95,112,9,0,84,95,98,47,0,
            98,95,24,0,90,0,98,106,95,107,
            5,0,83,107,0,98,106,95,93,45,
            93,6,0,46,93,45,93,0,98,106,
            95,93,6,0,46,93,0,99,98,0,
            95,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            285,276,273,264,261,252,249,210,0,356,
            454,346,345,440,240,228,138,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,114,108,95,115,79,88,93,36,95,
            95,117,121,98,89,81,85,120,119,95,
            99,80,34,33,32,31,100,16,28,27,
            25,26,21,15,14,13,12,11,10,18,
            17,20,19,9,7,24,5,6,23,22,
            4,93,93,93,93,131,129,55,54,95,
            93,93,103,93,93,94,93,94,94,94,
            93,93,93,93,112,101,43,95,107,93,
            37,95,86,93,93,101,134,103,40,103,
            103,37,95,137,98,35,95,45,95,107,
            98,40,37,139,136,98,106,93,106,82,
            111,130,35,84,105,87,98,95,98,35,
            35,95,95,83,106,46,98,107,98,46,
            93,45
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
            "gpu_on",
            "gpu_off",
            "gpu_allocate",
            "gpu_free",
            "gpu_put",
            "gpu_get",
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
