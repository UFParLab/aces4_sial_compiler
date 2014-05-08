package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 93;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 8;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 9;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 20;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 140;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 93;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 650;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 138;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 48;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 141;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 192;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 92;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 92;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 511;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 512;
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
            0,0,0,0,0,0,0,0,1,0,
            0,0,1,0,1,0,1,0,1,0,
            0,0,0,0,0,1,0,1,0,1,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,1,0,0,0,0,0,
            0
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
            0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static short baseCheck[] = {0,
            3,8,0,1,1,1,2,0,2,3,
            0,1,1,2,1,1,1,1,1,0,
            3,1,1,1,1,1,1,1,3,3,
            6,1,1,1,1,1,1,3,1,5,
            1,1,1,1,1,1,4,3,1,2,
            1,6,3,1,0,0,3,2,0,3,
            2,1,2,2,7,11,7,4,1,2,
            5,8,3,3,3,3,4,2,4,3,
            4,4,2,2,2,2,2,1,0,2,
            3,3,4,2,2,2,2,3,3,1,
            1,1,1,1,1,4,1,3,1,1,
            1,3,0,3,3,1,1,1,1,1,
            1,1,1,3,1,1,1,1,1,1,
            2,1,1,1,1,1,1,1,-12,-99,
            0,-69,0,0,0,0,0,-101,0,0,
            0,0,0,-6,0,0,0,-94,0,0,
            0,0,0,-109,0,0,0,0,0,0,
            0,0,0,0,0,-30,-47,0,0,-2,
            0,0,0,0,0,0,0,0,-124,0,
            0,-1,0,0,0,-71,0,0,0,-3,
            -111,0,0,0,0,0,0,0,0,0,
            -14,0,0,0,-38,0,0,0,0,-25,
            0,0,0,0,0,-26,0,0,-68,0,
            0,-27,0,0,0,0,0,-28,0,0,
            -70,0,0,-83,0,0,-4,0,0,-92,
            0,0,-102,0,0,-8,0,0,0,0,
            0,-108,0,0,-113,0,0,-19,0,0,
            -7,0,0,-125,0,0,-127,0,0,-5,
            0,0,0,0,0,-135,0,0,-137,0,
            0,-9,0,0,0,0,0,-110,0,-39,
            0,0,-10,0,0,0,0,-40,0,0,
            -41,0,0,-50,0,0,-42,0,0,-52,
            0,-53,0,-64,-56,0,0,0,0,-87,
            0,0,-75,0,-11,0,0,-128,0,0,
            -96,0,0,0,0,-97,0,0,-98,0,
            0,-114,-116,-121,0,-20,0,0,0,-129,
            0,-132,0,-77,0,0,-133,-13,-15,0,
            0,-18,0,0,0,-21,0,0,0,0,
            -16,0,-17,0,-22,0,-23,0,0,-24,
            0,-29,-31,0,0,-32,0,-33,0,-34,
            -35,-36,0,0,0,-37,0,-43,0,-44,
            0,-45,0,-46,0,-48,0,-49,-51,0,
            0,-54,0,-57,-59,0,0,-60,0,-61,
            0,-62,0,-65,-66,-74,0,0,0,-76,
            -82,-78,-79,-80,-81,-93,-85,0,-84,0,
            0,0,0,0,0,-86,-88,-104,0,-89,
            0,-100,0,-95,0,-103,0,0,-105,0,
            -106,0,-115,-117,0,0,-122,-123,-55,0,
            0,-126,-130,-58,0,0,-134,0,0,-138,
            0,-63,0,-140,0,0,-67,-72,-73,-90,
            -91,-107,-112,-118,-119,-120,-131,-136,-139,0
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
            25,25,29,29,29,29,29,29,29,30,
            34,31,37,37,37,37,37,38,38,18,
            32,39,39,39,39,39,39,33,40,19,
            19,19,35,36,12,12,6,6,13,14,
            14,7,7,7,7,7,7,7,7,7,
            7,7,7,7,7,7,7,7,7,7,
            7,7,7,7,7,7,7,7,41,42,
            42,7,7,7,7,7,7,7,7,7,
            11,11,11,11,8,8,2,15,15,21,
            21,44,44,10,10,20,45,45,45,45,
            45,45,43,43,46,47,47,47,47,47,
            9,9,5,5,5,5,5,4,1,342,
            120,109,123,497,135,211,136,130,123,497,
            135,446,136,130,41,256,13,115,419,497,
            135,111,136,88,170,51,268,372,368,22,
            23,24,25,26,27,28,340,123,497,135,
            249,136,130,504,503,92,441,142,123,123,
            497,135,293,136,130,247,340,445,441,90,
            37,123,497,135,40,136,130,464,200,58,
            124,47,479,105,180,41,499,498,376,176,
            250,497,135,280,136,97,250,497,135,471,
            136,96,250,497,135,250,136,95,250,497,
            135,44,136,94,250,497,135,38,136,131,
            83,479,105,125,479,105,464,376,176,303,
            376,176,172,479,105,1,479,105,383,376,
            176,386,376,176,213,479,105,252,479,105,
            381,376,176,158,376,176,297,479,105,343,
            479,105,464,376,176,335,376,176,41,39,
            41,499,436,43,1,428,9,425,41,499,
            333,41,499,78,41,107,37,41,499,440,
            290,54,290,54,293,290,54,247,139,447,
            41,107,64,340,63,166,506,53,170,51,
            2,41,499,81,346,502,41,499,79,41,
            499,77,122,122,120,109,210,48,274,277,
            41,39,41,107,474,478,478,122,464,41,
            325,356,384,289,76,112,464,508,38,492,
            478,41,424,41,484,41,324,41,434,14,
            41,435,464,41,241,229,41,87,41,86,
            389,389,41,83,85,84,41,196,41,364,
            41,442,41,443,41,444,41,70,464,41,
            456,449,41,61,464,41,500,457,41,501,
            41,30,41,29,389,389,41,80,99,98,
            340,339,474,474,474,464,385,459,462,471,
            458,349,75,74,73,253,422,471,476,471,
            41,47,471,262,41,82,41,108,265,41,
            473,476,352,464,41,52,482,464,464,455,
            483,507,476,471,335,353,148,41,65,286,
            41,509,334,201,41,66,367,456,337,470,
            338,475,3,429,415,467,445,477,480,482,
            512,512
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
            0,1,0,0,2,43,0,1,48,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,0,1,84,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,0,0,
            1,0,0,1,0,1,80,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            38,39,0,41,45,0,44,2,0,0,
            1,0,1,91,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,34,35,38,87,0,
            41,2,0,1,90,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,0,0,
            1,0,1,82,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,34,35,38,39,0,
            1,0,0,44,2,83,0,1,47,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,40,0,0,2,0,0,0,0,
            92,0,0,1,48,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,42,42,
            0,42,0,0,0,0,0,0,0,47,
            49,50,51,52,53,54,55,56,57,58,
            59,60,61,62,63,64,65,66,70,71,
            72,73,74,75,0,76,77,78,0,1,
            86,0,81,2,39,41,85,39,0,45,
            89,49,50,51,52,53,54,55,56,57,
            58,59,60,61,0,62,63,64,65,66,
            36,67,68,69,0,0,38,39,0,79,
            2,40,44,0,36,2,0,46,88,0,
            0,43,2,0,0,0,0,2,2,0,
            36,0,0,0,0,0,0,43,0,0,
            0,37,37,0,36,0,0,0,40,0,
            0,0,0,40,38,0,37,0,0,0,
            37,37,0,0,0,36,0,0,0,0,
            0,0,0,0,0,0,0,46,0,0,
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
            512,650,512,574,422,314,419,416,581,177,
            317,311,308,300,215,406,393,410,408,414,
            412,402,322,320,418,400,401,398,396,392,
            238,232,226,220,390,387,8,4,466,519,
            512,650,20,89,519,618,512,650,583,574,
            422,314,419,416,581,177,317,311,308,300,
            215,406,393,410,408,414,412,402,322,320,
            418,400,401,398,396,392,238,232,226,220,
            390,387,512,650,477,574,422,314,419,416,
            581,177,317,311,308,300,215,406,393,410,
            408,414,412,402,322,320,418,400,401,398,
            396,392,238,232,226,220,390,387,512,512,
            650,56,512,650,512,650,385,574,422,314,
            419,416,581,177,317,311,308,300,215,406,
            393,410,408,414,412,402,322,320,418,400,
            401,398,396,392,238,232,226,220,390,387,
            644,649,512,244,622,10,645,519,512,512,
            650,512,650,605,574,422,314,419,416,581,
            177,317,311,308,300,215,406,393,410,408,
            414,412,402,322,320,418,400,401,398,396,
            392,238,232,226,220,390,387,561,189,21,
            505,519,512,650,580,574,422,314,419,416,
            581,177,317,311,308,300,215,406,393,410,
            408,414,412,402,322,320,418,400,401,398,
            396,392,238,232,226,220,390,387,512,512,
            650,512,650,474,574,422,314,419,416,581,
            177,317,311,308,300,215,406,393,410,408,
            414,412,402,322,320,418,400,401,398,396,
            392,238,232,226,220,390,387,644,649,55,
            650,512,3,645,518,362,512,650,487,574,
            422,314,419,416,581,177,317,311,308,300,
            215,406,393,410,408,414,412,402,322,320,
            418,400,401,398,396,392,238,232,226,220,
            390,387,517,57,512,519,512,512,512,512,
            511,56,512,650,584,574,422,314,419,416,
            581,177,317,311,308,300,215,406,393,410,
            408,414,412,402,322,320,418,400,401,398,
            396,392,238,232,226,220,390,387,341,164,
            512,612,512,12,122,512,512,512,512,490,
            11,11,11,11,11,11,11,11,11,11,
            11,11,11,528,527,529,530,531,629,631,
            628,630,632,633,512,613,614,615,91,650,
            460,512,381,518,292,640,383,649,512,637,
            369,553,554,555,556,557,558,432,430,544,
            545,546,547,548,512,528,527,529,530,531,
            338,639,638,641,104,134,644,649,512,154,
            518,517,645,512,354,518,512,469,271,512,
            56,626,519,113,512,59,60,519,519,67,
            360,512,512,512,512,512,512,543,512,512,
            512,330,330,512,466,512,512,512,517,512,
            512,512,512,517,562,512,330,512,512,512,
            140,298,512,512,512,466,512,512,512,512,
            512,512,512,512,512,512,512,494
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            10,167,61,59,61,159,101,387,387,103,
            60,104,387,125,159,159,159,173,178,103,
            387,159,159,159,54,54,54,54,387,320,
            159,159,159,101,101,159,159,159,159,159,
            159,159,159,159,159,159,4,159,387,159,
            159,161,161,159,319,161,387,325,159,159,
            159,159,327,165,101,101,371,15,4,52,
            320,323,171,159,320,320,169,169,169,169,
            387,192,54,203,199,240,159,244,159,323,
            171,16,382,52,159,159,159,159,1,333,
            4,204,159,281,159,394,390,245,6,159,
            4,390,64,282,387,395,159,201,57,390,
            1,387,387,4,283,394,396,6,159,334,
            393,159,395,159,335,199,396,159,242,159
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            45,1,0,39,44,38,41,1,0,79,
            88,40,2,0,2,1,4,3,22,23,
            6,5,24,8,7,9,19,20,17,18,
            10,11,12,13,14,15,21,26,25,27,
            28,16,29,30,31,32,33,34,35,91,
            0,40,2,1,39,44,38,0,92,2,
            79,88,0,1,4,3,22,23,6,5,
            24,8,7,9,19,20,17,18,10,11,
            12,13,14,15,21,26,25,27,28,16,
            29,30,31,32,33,34,35,48,84,0,
            39,0,2,49,50,51,52,53,54,55,
            56,57,58,59,60,61,63,62,64,65,
            66,85,81,89,4,3,22,23,6,5,
            24,8,7,9,19,20,17,18,10,11,
            12,13,14,15,21,26,25,27,28,16,
            29,30,31,32,33,34,35,80,1,0,
            40,2,1,0,40,2,92,0,40,2,
            37,0,63,62,64,65,66,49,50,51,
            52,53,54,57,58,59,60,61,55,56,
            0,72,70,73,71,74,75,0,40,2,
            36,0,2,1,4,3,22,23,6,5,
            24,8,7,9,19,20,17,18,10,11,
            12,13,14,15,21,26,25,27,28,16,
            29,30,31,32,33,34,35,90,0,40,
            2,46,0,2,1,4,3,22,23,6,
            5,24,8,7,9,19,20,17,18,10,
            11,12,13,14,15,21,26,25,27,28,
            16,29,30,31,32,33,34,35,82,0,
            2,87,1,4,3,22,23,6,5,24,
            8,7,9,19,20,17,18,10,11,12,
            13,14,15,21,26,25,27,28,16,29,
            30,31,32,33,34,35,83,0,37,76,
            77,78,42,0,86,0,80,82,83,47,
            90,91,84,2,1,4,3,22,23,6,
            5,24,8,7,9,19,20,17,18,10,
            11,12,13,14,15,21,26,25,27,28,
            16,29,30,31,32,33,34,35,48,0,
            70,71,72,73,74,75,1,38,44,39,
            37,45,68,67,41,69,40,2,0,43,
            36,0,83,2,87,1,4,3,22,23,
            6,5,24,8,7,9,19,20,17,18,
            10,11,12,13,14,15,21,26,25,27,
            28,16,29,30,31,32,33,34,35,47,
            0
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
            89,92,24,25,26,28,31,32,33,36,
            37,38,39,40,41,42,58,59,60,61,
            62,67,68,69,71,72,73,74,79,81,
            82,83,84,85,86,1,17,90,93,88,
            3,13,18,91,4,29,30,35,43,44,
            45,46,47,48,51,52,53,54,55,56,
            57,65,75,76,77,78,2,5,6,7,
            8,9,10,11,12,14,15,16,20,21,
            22,23,27,34,49,50,63,64,66,70,
            80,87,94
        };
    };
    public final static byte terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static byte nonterminalIndex[] = {0,
            97,113,98,115,117,0,109,118,122,0,
            114,0,110,0,112,0,101,105,108,111,
            120,95,0,96,0,99,0,100,102,0,
            0,0,0,0,0,0,103,104,106,107,
            116,0,119,121,123,0,124,0
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
            48,47,83,47,48,84,91,90,40
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
            99,96,29,0,91,0,99,96,84,99,
            96,113,9,0,48,0,99,96,113,9,
            0,84,96,99,48,0,99,96,24,0,
            90,0,99,107,96,108,5,0,83,108,
            0,99,107,96,94,46,94,6,0,47,
            94,46,94,0,99,107,96,94,6,0,
            47,94,0,100,99,0,96,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            289,286,277,274,265,262,253,250,211,0,
            367,483,353,352,462,457,449,229,139,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,115,109,96,116,79,88,94,39,96,
            96,118,122,99,89,81,85,121,120,96,
            100,80,35,34,33,32,31,30,29,101,
            16,28,27,25,26,21,15,14,13,12,
            11,10,18,17,20,19,9,7,24,5,
            6,23,22,4,94,94,94,94,132,130,
            56,55,96,94,94,94,94,96,104,94,
            94,95,94,95,95,95,94,94,94,94,
            113,102,41,96,108,94,37,96,86,94,
            94,99,102,135,104,42,104,104,37,96,
            138,99,36,96,46,96,108,99,42,37,
            140,137,99,107,94,107,82,112,41,131,
            36,84,106,87,99,96,99,36,36,96,
            96,83,107,47,99,108,99,47,94,46
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
