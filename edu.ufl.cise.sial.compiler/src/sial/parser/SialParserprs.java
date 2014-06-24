package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 94;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 8;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 9;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 20;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 140;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 94;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 646;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 136;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 48;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 142;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 190;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 90;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 90;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 509;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 510;
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
            0,0,0,0,0,0,0,0,0,1,
            0,0,0,1,0,1,0,1,0,1,
            0,0,0,0,0,0,1,0,1,0,
            1,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,1,0,0,0,0,
            0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            6,35,7,37,39,9,29,41,45,34,
            36,28,30,32,33,3,13,24,27,31,
            43,2,4,5,8,10,11,12,14,15,
            16,17,18,19,20,21,22,23,25,26,
            38,40,42,44,46,47,48,1
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
            0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static short baseCheck[] = {0,
            3,8,0,1,1,1,2,0,2,3,
            0,1,1,2,1,1,1,0,3,1,
            1,1,1,1,1,1,3,3,6,1,
            1,1,1,1,1,3,1,5,1,1,
            1,1,1,1,4,3,1,2,1,6,
            3,1,0,0,3,2,0,3,2,1,
            2,2,7,11,7,4,1,2,5,8,
            3,3,3,3,4,2,4,3,4,4,
            2,2,2,2,2,1,0,2,3,3,
            4,2,2,2,2,3,3,1,1,1,
            1,1,1,4,1,3,1,1,1,3,
            0,3,3,1,1,1,1,1,1,1,
            1,3,1,1,1,1,1,1,2,1,
            1,1,1,1,1,1,-12,-99,0,-69,
            0,0,0,0,0,-101,0,0,0,0,
            0,-6,0,0,0,-94,0,0,0,0,
            0,-109,0,0,0,0,0,0,0,0,
            0,0,0,-30,-47,0,0,-2,0,0,
            0,0,0,0,0,0,-124,0,0,-1,
            0,0,0,-71,0,0,0,-3,-111,0,
            0,0,0,0,0,0,0,0,-14,0,
            0,0,-38,0,0,0,0,-25,0,0,
            0,0,0,-26,0,0,-68,0,0,-27,
            0,0,0,0,0,-28,0,0,-70,0,
            0,-83,0,0,-4,0,0,-92,0,0,
            -102,0,0,-8,0,0,0,0,0,-108,
            0,0,-113,0,0,-19,0,0,-7,0,
            0,-125,0,0,-127,0,0,-5,0,0,
            0,0,0,-135,0,0,-137,0,0,-9,
            0,0,0,0,0,-110,0,-39,0,0,
            -10,0,0,0,0,-40,0,0,-41,0,
            0,-50,0,0,-42,0,0,-52,0,-53,
            0,-64,-56,0,0,0,0,-87,0,0,
            -75,0,-11,0,0,-128,0,0,-96,0,
            0,0,0,-97,0,0,-98,0,0,-114,
            -116,-121,0,-20,0,0,0,-129,0,-132,
            0,-77,0,0,-133,-13,-15,0,0,-18,
            0,0,0,-21,0,0,0,0,-16,0,
            -17,0,-22,0,-23,0,0,-24,0,-29,
            -31,0,0,-32,0,-33,0,-34,-35,-36,
            0,0,0,-37,0,-43,0,-44,0,-45,
            0,-46,0,-48,0,-49,-51,0,0,-54,
            0,-57,-59,0,0,-60,0,-61,0,-62,
            0,-65,-66,-74,0,0,0,-76,-82,-78,
            -79,-80,-81,-93,-85,0,-84,0,0,0,
            0,0,0,-86,-88,-104,0,-89,0,-100,
            0,-95,0,-103,0,0,-105,0,-106,0,
            -115,-117,0,0,-122,-123,-55,0,0,-126,
            -130,-58,0,0,-134,0,0,-138,0,-63,
            0,-140,0,0,-67,-72,-73,-90,-91,-107,
            -112,-118,-119,-120,-131,-136,-139,0
        };
    };
    public final static short baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static short rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            22,22,24,16,16,3,3,3,23,23,
            26,27,27,28,28,17,17,17,25,25,
            29,29,29,29,29,29,29,30,34,31,
            37,37,37,37,37,38,38,18,32,39,
            39,39,39,39,39,33,40,19,19,19,
            35,36,12,12,6,6,13,14,14,7,
            7,7,7,7,7,7,7,7,7,7,
            7,7,7,7,7,7,7,7,7,7,
            7,7,7,7,7,7,41,42,42,7,
            7,7,7,7,7,7,7,7,11,11,
            11,11,8,8,2,15,15,21,21,44,
            44,10,10,20,45,45,45,45,45,45,
            43,43,46,47,47,47,47,47,9,9,
            5,5,5,5,5,4,1,339,41,107,
            121,495,133,209,134,128,121,495,133,444,
            134,128,43,254,13,113,381,495,133,109,
            134,86,292,49,266,370,366,20,21,22,
            23,24,25,26,337,121,495,133,245,134,
            128,502,501,90,439,140,121,121,495,133,
            167,134,128,245,337,443,439,88,37,121,
            495,133,38,134,128,462,198,56,122,47,
            477,103,178,43,497,496,374,174,247,495,
            133,278,134,95,247,495,133,455,134,94,
            247,495,133,248,134,93,247,495,133,45,
            134,92,247,495,133,38,134,129,84,477,
            103,123,477,103,168,374,174,301,374,174,
            171,477,103,1,477,103,378,374,174,448,
            374,174,209,477,103,249,477,103,338,374,
            174,156,374,174,294,477,103,340,477,103,
            168,374,174,333,374,174,43,37,43,497,
            434,161,1,426,9,423,43,497,331,43,
            497,76,43,105,35,43,497,438,331,52,
            331,52,167,331,52,245,137,445,43,105,
            62,337,61,164,504,51,292,49,2,43,
            497,79,344,500,43,497,77,43,497,75,
            385,385,41,107,287,46,272,275,43,37,
            43,105,460,476,476,385,168,43,323,354,
            383,287,74,110,168,506,36,490,476,43,
            422,43,482,43,322,43,432,14,43,433,
            168,43,239,227,43,85,43,84,452,452,
            43,81,83,82,43,194,43,362,43,440,
            43,441,43,442,43,68,168,43,454,447,
            43,59,168,43,498,455,43,499,43,28,
            43,27,452,452,43,78,97,96,337,336,
            460,460,460,168,376,414,460,455,456,347,
            73,72,71,251,449,455,463,469,43,45,
            455,260,43,80,43,106,263,43,471,463,
            350,168,43,50,480,168,168,444,481,505,
            463,455,377,351,146,43,63,284,43,507,
            384,199,43,64,365,453,246,456,458,462,
            3,423,467,464,440,469,468,334,510,510
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
            30,31,32,33,34,35,0,0,36,2,
            0,1,0,1,0,43,0,1,48,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,82,0,1,45,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,29,30,31,32,33,34,35,0,
            0,1,0,1,78,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,38,39,
            0,41,2,0,44,2,0,0,2,2,
            0,1,89,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,40,40,0,1,
            88,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,29,30,31,
            32,33,34,35,0,0,0,1,0,1,
            80,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,29,30,31,
            32,33,34,35,38,39,0,42,2,81,
            44,0,1,0,1,47,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,29,30,31,32,33,34,35,38,
            0,1,41,0,90,0,0,0,0,0,
            1,48,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,
            31,32,33,34,35,0,0,0,42,46,
            0,1,0,0,0,2,47,49,50,51,
            52,53,54,55,56,57,58,59,60,61,
            62,63,64,68,69,70,71,72,73,0,
            74,75,76,0,77,2,41,79,38,39,
            45,83,0,86,44,87,49,50,51,52,
            53,54,55,56,57,58,59,60,61,0,
            65,66,67,0,62,63,64,0,0,36,
            2,0,0,40,0,0,2,0,36,0,
            84,0,0,0,2,43,0,0,0,85,
            2,0,0,0,0,36,0,0,0,0,
            37,0,43,0,0,0,39,0,40,37,
            39,0,37,0,46,0,37,0,37,42,
            0,38,36,36,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            510,646,510,570,420,312,417,414,577,175,
            315,309,306,298,213,404,391,408,406,412,
            410,400,320,318,416,398,399,396,394,390,
            236,230,224,218,388,385,8,4,464,517,
            510,646,510,646,87,614,510,646,579,570,
            420,312,417,414,577,175,315,309,306,298,
            213,404,391,408,406,412,410,400,320,318,
            416,398,399,396,394,390,236,230,224,218,
            388,385,475,510,646,618,570,420,312,417,
            414,577,175,315,309,306,298,213,404,391,
            408,406,412,410,400,320,318,416,398,399,
            396,394,390,236,230,224,218,388,385,510,
            510,646,510,646,383,570,420,312,417,414,
            577,175,315,309,306,298,213,404,391,408,
            406,412,410,400,320,318,416,398,399,396,
            394,390,236,230,224,218,388,385,640,645,
            18,242,517,10,641,517,3,510,516,516,
            510,646,601,570,420,312,417,414,577,175,
            315,309,306,298,213,404,391,408,406,412,
            410,400,320,318,416,398,399,396,394,390,
            236,230,224,218,388,385,515,515,510,646,
            576,570,420,312,417,414,577,175,315,309,
            306,298,213,404,391,408,406,412,410,400,
            320,318,416,398,399,396,394,390,236,230,
            224,218,388,385,510,510,510,646,510,646,
            472,570,420,312,417,414,577,175,315,309,
            306,298,213,404,391,408,406,412,410,400,
            320,318,416,398,399,396,394,390,236,230,
            224,218,388,385,640,645,19,339,517,360,
            641,510,646,510,646,485,570,420,312,417,
            414,577,175,315,309,306,298,213,404,391,
            408,406,412,410,400,320,318,416,398,399,
            396,394,390,236,230,224,218,388,385,557,
            53,646,503,510,509,510,510,510,54,510,
            646,580,570,420,312,417,414,577,175,315,
            309,306,298,213,404,391,408,406,412,410,
            400,320,318,416,398,399,396,394,390,236,
            230,224,218,388,385,120,510,510,608,492,
            89,646,12,55,54,517,488,11,11,11,
            11,11,11,11,11,11,11,11,11,11,
            525,526,527,625,627,624,626,628,629,510,
            609,610,611,510,152,516,636,379,640,645,
            633,381,510,269,641,367,549,550,551,552,
            553,554,430,428,540,541,542,543,544,510,
            635,634,637,102,525,526,527,510,510,464,
            516,510,132,515,54,510,517,510,352,111,
            458,510,57,510,517,622,510,65,58,187,
            517,510,510,510,510,358,510,510,510,510,
            328,510,539,510,510,510,290,510,515,328,
            645,510,328,510,467,510,138,510,296,162,
            510,558,336,464
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            10,165,61,59,61,157,101,383,383,103,
            60,104,383,123,157,157,157,171,174,103,
            383,157,157,157,54,54,54,54,383,316,
            157,157,157,101,101,157,157,157,157,157,
            157,157,157,157,157,157,4,157,383,157,
            157,159,159,157,315,159,383,321,157,157,
            157,157,323,163,101,101,367,15,4,52,
            316,319,169,157,316,316,167,167,167,167,
            383,188,54,199,195,236,157,240,157,319,
            169,16,378,52,157,157,157,157,1,329,
            4,200,157,277,157,390,386,241,6,157,
            4,386,64,278,383,391,157,197,57,386,
            1,383,383,4,279,390,392,6,157,330,
            389,157,391,157,331,195,392,157,238,157
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            45,1,0,39,44,38,41,1,0,77,
            86,40,2,0,2,1,4,3,22,23,
            6,5,24,8,7,9,19,20,17,18,
            10,11,12,13,14,15,21,26,25,27,
            28,16,29,30,31,32,33,34,35,89,
            0,40,2,1,39,44,38,0,90,2,
            77,86,0,1,4,3,22,23,6,5,
            24,8,7,9,19,20,17,18,10,11,
            12,13,14,15,21,26,25,27,28,16,
            29,30,31,32,33,34,35,48,82,0,
            39,0,2,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,83,
            79,87,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,29,30,
            31,32,33,34,35,78,1,0,40,2,
            1,0,40,2,90,0,40,2,37,0,
            62,63,64,49,50,51,52,53,54,57,
            58,59,60,61,55,56,0,70,68,71,
            69,72,73,0,40,2,36,0,2,1,
            4,3,22,23,6,5,24,8,7,9,
            19,20,17,18,10,11,12,13,14,15,
            21,26,25,27,28,16,29,30,31,32,
            33,34,35,88,0,40,2,46,0,2,
            1,4,3,22,23,6,5,24,8,7,
            9,19,20,17,18,10,11,12,13,14,
            15,21,26,25,27,28,16,29,30,31,
            32,33,34,35,80,0,2,85,1,4,
            3,22,23,6,5,24,8,7,9,19,
            20,17,18,10,11,12,13,14,15,21,
            26,25,27,28,16,29,30,31,32,33,
            34,35,81,0,37,74,75,76,42,0,
            84,0,78,80,81,47,88,89,82,2,
            1,4,3,22,23,6,5,24,8,7,
            9,19,20,17,18,10,11,12,13,14,
            15,21,26,25,27,28,16,29,30,31,
            32,33,34,35,48,0,68,69,70,71,
            72,73,1,38,44,39,37,45,66,65,
            41,67,40,2,0,43,36,0,81,2,
            85,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,29,30,
            31,32,33,34,35,47,0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            15,4,18,4,31,13,4,16,16,36,
            4,1,16,20,13,13,13,51,28,4,
            16,13,13,13,13,13,13,13,16,10,
            13,13,13,53,53,13,13,23,23,23,
            23,23,13,13,13,13,12,13,16,38,
            13,40,40,13,4,40,16,4,13,13,
            13,13,4,16,53,53,4,3,13,26,
            10,4,4,13,10,10,49,49,49,49,
            16,55,13,3,16,16,38,3,13,4,
            4,20,57,13,13,23,23,23,5,3,
            13,20,13,59,13,59,4,20,7,34,
            13,4,20,42,16,42,13,4,4,4,
            45,16,16,12,20,59,20,8,47,3,
            4,38,42,13,20,4,20,13,4,13
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static char nasr[] = {0,
            28,29,6,0,44,0,40,19,0,11,
            0,20,1,0,22,3,0,23,0,1,
            7,0,1,2,0,42,0,37,39,0,
            24,26,0,38,0,25,0,15,0,12,
            0,6,13,0,21,0,18,0,10,0,
            17,0,4,0,45,0,47,0,14,0
        };
    };
    public final static char nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static byte terminalIndex[] = {0,
            90,93,24,25,26,28,31,32,33,36,
            37,38,39,40,41,42,58,59,60,61,
            62,67,68,69,71,72,73,74,79,81,
            82,83,84,85,86,1,17,91,94,89,
            3,13,18,92,4,29,30,35,43,44,
            45,46,47,48,51,52,53,54,55,56,
            57,65,77,87,2,5,6,7,8,9,
            10,11,12,14,15,16,20,21,22,23,
            27,34,49,50,63,64,66,70,80,88,
            75,76,78,95
        };
    };
    public final static byte terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static byte nonterminalIndex[] = {0,
            98,114,99,116,118,0,110,119,123,0,
            115,0,111,0,113,0,102,106,109,112,
            121,96,0,97,0,100,0,101,103,0,
            0,0,0,0,0,0,104,105,107,108,
            117,0,120,122,124,0,125,0
        };
    };
    public final static byte nonterminalIndex[] = NonterminalIndex.nonterminalIndex;
    public final int nonterminalIndex(int index) { return nonterminalIndex[index]; }

    public interface ScopePrefix {
        public final static byte scopePrefix[] = {
            7,42,33,55,17,17,1,27,64
        };
    };
    public final static byte scopePrefix[] = ScopePrefix.scopePrefix;
    public final int scopePrefix(int index) { return scopePrefix[index]; }

    public interface ScopeSuffix {
        public final static byte scopeSuffix[] = {
            15,50,39,61,15,22,5,31,67
        };
    };
    public final static byte scopeSuffix[] = ScopeSuffix.scopeSuffix;
    public final int scopeSuffix(int index) { return scopeSuffix[index]; }

    public interface ScopeLhs {
        public final static char scopeLhs[] = {
            7,7,7,7,7,7,7,7,6
        };
    };
    public final static char scopeLhs[] = ScopeLhs.scopeLhs;
    public final int scopeLhs(int index) { return scopeLhs[index]; }

    public interface ScopeLa {
        public final static byte scopeLa[] = {
            48,47,81,47,48,82,89,88,40
        };
    };
    public final static byte scopeLa[] = ScopeLa.scopeLa;
    public final int scopeLa(int index) { return scopeLa[index]; }

    public interface ScopeStateSet {
        public final static byte scopeStateSet[] = {
            1,1,1,1,1,1,1,1,11
        };
    };
    public final static byte scopeStateSet[] = ScopeStateSet.scopeStateSet;
    public final int scopeStateSet(int index) { return scopeStateSet[index]; }

    public interface ScopeRhs {
        public final static char scopeRhs[] = {0,
            100,97,29,0,89,0,100,97,82,100,
            97,114,9,0,48,0,100,97,114,9,
            0,82,97,100,48,0,100,97,24,0,
            88,0,100,108,97,109,5,0,81,109,
            0,100,108,97,95,46,95,6,0,47,
            95,46,95,0,100,108,97,95,6,0,
            47,95,0,101,100,0,97,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            287,284,275,272,263,260,251,248,209,0,
            365,481,351,350,460,455,447,227,137,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,116,110,97,117,77,86,95,39,97,
            97,119,123,100,87,79,83,122,121,97,
            101,78,35,34,33,32,31,30,29,102,
            16,28,27,25,26,21,15,14,13,12,
            11,10,18,17,20,19,9,7,24,5,
            6,23,22,4,95,95,95,95,133,131,
            56,55,97,95,95,95,95,97,105,95,
            95,96,95,96,96,96,95,95,95,95,
            114,103,41,97,109,95,37,97,84,95,
            95,100,103,136,105,42,105,105,37,97,
            139,100,36,97,46,97,109,100,42,37,
            141,138,100,108,95,108,80,113,41,132,
            36,82,107,85,100,97,100,36,36,97,
            97,81,108,47,100,109,100,47,95,46
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
            "set_persistent",
            "restore_persistent",
            "sparse",
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
            "StringLiteral",
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
