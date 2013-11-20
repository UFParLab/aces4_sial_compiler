package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 92;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 8;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 9;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 20;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 135;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 92;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 635;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 136;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 48;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 140;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 190;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 91;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 91;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 498;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 499;
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
            3,3,4,2,2,2,2,1,1,1,
            1,1,1,4,1,3,1,1,1,3,
            0,3,3,1,1,1,1,1,1,1,
            1,3,1,1,1,1,1,1,2,1,
            1,1,1,1,1,1,-12,-95,0,-65,
            0,0,0,0,0,-97,0,0,0,0,
            0,-6,0,0,0,-90,0,0,0,0,
            0,-105,0,0,0,0,0,0,0,0,
            0,0,0,-28,-45,0,0,-2,0,0,
            0,0,0,0,0,0,-119,0,0,-1,
            0,0,0,-67,0,0,0,-3,-107,0,
            0,0,0,0,0,0,0,0,-14,0,
            0,0,-36,0,0,0,0,-23,0,0,
            0,0,0,-24,0,0,-64,0,0,-25,
            0,0,0,0,0,-26,0,0,-66,0,
            0,-79,0,0,-4,0,0,-88,0,0,
            -98,0,0,-8,0,0,0,0,0,-104,
            0,0,-109,0,0,-19,0,0,-7,0,
            0,-120,0,0,-122,0,0,-5,0,0,
            0,0,0,-130,0,0,-132,0,0,-9,
            0,0,0,0,0,-106,0,-37,0,0,
            -10,0,0,0,0,-38,0,0,-39,0,
            0,-48,0,0,-40,0,0,-50,0,-51,
            0,-62,-54,0,0,0,0,-83,0,0,
            -71,0,-11,0,0,-123,0,0,-92,0,
            0,0,0,-93,0,0,-94,0,0,-110,
            -112,-116,0,-20,0,0,0,-124,0,-127,
            0,-73,0,0,-128,-13,-15,0,0,-18,
            0,0,0,-21,0,0,0,0,-16,0,
            -17,0,-22,0,-27,-89,0,0,-29,0,
            -30,0,-31,0,-34,0,-35,0,-41,0,
            -42,0,-43,0,-44,0,-46,0,-47,-49,
            0,0,-52,0,-55,-57,0,0,-58,0,
            -59,0,-60,0,-70,0,-72,-78,-74,-75,
            -76,-77,0,-81,0,-80,0,0,0,0,
            0,0,-82,-84,-100,0,-85,0,-96,0,
            -91,0,-99,0,0,-101,0,-102,0,-111,
            -113,0,0,-117,-118,-121,0,0,-125,-129,
            0,0,0,-32,0,-133,0,-135,0,0,
            -33,-53,-56,-61,-63,-68,-69,-86,-87,-103,
            -108,-114,-115,-126,-131,-134,0
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
            42,7,7,7,7,7,7,7,11,11,
            11,11,8,8,2,15,15,21,21,44,
            44,10,10,20,45,45,45,45,45,45,
            43,43,46,47,47,47,47,47,9,9,
            4,4,4,4,4,5,1,335,164,107,
            372,485,133,209,128,134,372,485,133,386,
            128,134,39,254,13,113,448,485,133,109,
            88,134,205,51,266,370,366,23,24,25,
            26,27,28,29,41,372,485,133,157,128,
            134,492,491,92,428,140,121,372,485,133,
            42,128,134,245,41,432,428,90,166,372,
            485,133,41,128,134,451,198,58,122,45,
            482,103,178,39,487,486,374,174,416,485,
            133,278,97,134,416,485,133,284,96,134,
            416,485,133,248,95,134,416,485,133,208,
            94,134,416,485,133,36,129,134,82,482,
            103,122,482,103,167,374,174,301,374,174,
            170,482,103,1,482,103,377,374,174,409,
            374,174,209,482,103,249,482,103,81,374,
            174,156,374,174,292,482,103,336,482,103,
            167,374,174,333,374,174,39,40,39,487,
            425,158,1,419,9,416,39,487,331,39,
            487,78,39,105,38,39,487,427,161,54,
            161,54,42,161,54,245,137,434,39,105,
            64,41,63,244,493,53,205,51,2,39,
            487,81,344,490,39,487,79,39,487,77,
            375,375,164,107,245,49,272,275,39,40,
            39,105,459,465,465,375,167,39,323,354,
            378,287,76,110,167,495,39,484,465,39,
            415,39,483,39,322,167,379,14,227,39,
            239,39,87,39,86,39,83,39,194,39,
            362,39,429,39,430,39,431,39,70,167,
            39,443,436,39,61,167,39,488,444,39,
            489,39,31,39,30,39,80,41,332,459,
            459,459,167,199,374,449,284,445,347,75,
            74,73,251,119,284,329,458,39,48,284,
            260,39,82,39,106,263,39,460,329,350,
            167,39,52,466,167,167,329,469,494,284,
            39,65,351,146,420,284,39,496,39,66,
            365,450,290,35,283,334,288,456,289,460,
            3,418,453,437,462,463,465,499,499
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
            30,31,32,33,0,0,34,2,0,1,
            0,0,40,2,0,1,46,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,37,39,
            0,0,1,83,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,75,76,77,0,85,
            2,0,1,79,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,37,0,0,78,2,
            0,1,44,0,1,0,0,87,2,0,
            1,90,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,
            31,32,33,37,0,1,43,0,0,1,
            89,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,29,30,31,
            32,33,38,0,0,2,2,91,0,1,
            81,3,4,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,29,30,31,
            32,33,0,0,2,2,0,0,0,0,
            82,0,1,45,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,36,39,39,0,0,
            2,0,0,0,0,0,1,46,3,4,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,36,
            0,0,1,0,0,2,0,0,0,0,
            45,47,48,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,65,
            69,70,71,72,73,74,35,34,0,38,
            37,0,41,42,80,0,1,0,84,0,
            42,43,88,47,48,49,50,51,52,53,
            54,55,56,57,58,59,0,60,61,62,
            63,64,65,35,66,67,68,0,1,0,
            35,34,0,38,35,0,41,40,0,0,
            86,0,0,2,0,0,0,0,0,0,
            34,0,0,0,0,0,40,0,0,0,
            0,0,35,0,35,38,34,0,41,0,
            0,36,0,0,36,36,34,0,0,0,
            0,0,0,0,0,0,0,0,44,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            499,635,499,561,413,312,410,407,568,175,
            315,309,306,298,213,397,389,401,399,405,
            403,395,320,318,409,474,481,393,391,385,
            236,230,224,218,499,4,453,506,499,635,
            499,3,603,505,499,635,570,561,413,312,
            410,407,568,175,315,309,306,298,213,397,
            389,401,399,405,403,395,320,318,409,474,
            481,393,391,385,236,230,224,218,504,597,
            499,499,635,464,561,413,312,410,407,568,
            175,315,309,306,298,213,397,389,401,399,
            405,403,395,320,318,409,474,481,393,391,
            385,236,230,224,218,598,599,600,499,447,
            505,499,635,383,561,413,312,410,407,568,
            175,315,309,306,298,213,397,389,401,399,
            405,403,395,320,318,409,474,481,393,391,
            385,236,230,224,218,504,499,21,152,506,
            55,635,456,499,635,8,499,269,505,499,
            635,592,561,413,312,410,407,568,175,315,
            309,306,298,213,397,389,401,399,405,403,
            395,320,318,409,474,481,393,391,385,236,
            230,224,218,504,499,635,607,89,499,635,
            567,561,413,312,410,407,568,175,315,309,
            306,298,213,397,389,401,399,405,403,395,
            320,318,409,474,481,393,391,385,236,230,
            224,218,549,10,22,506,506,498,499,635,
            461,561,413,312,410,407,568,175,315,309,
            306,298,213,397,389,401,399,405,403,395,
            320,318,409,474,481,393,391,385,236,230,
            224,218,57,56,506,506,499,499,499,102,
            360,499,635,470,561,413,312,410,407,568,
            175,315,309,306,298,213,397,389,401,399,
            405,403,395,320,318,409,474,481,393,391,
            385,236,230,224,218,328,339,162,59,499,
            506,499,499,132,56,499,635,571,561,413,
            312,410,407,568,175,315,309,306,298,213,
            397,389,401,399,405,403,395,320,318,409,
            474,481,393,391,385,236,230,224,218,328,
            499,499,635,499,56,505,499,12,120,499,
            476,11,11,11,11,11,11,11,11,11,
            11,11,11,11,515,516,514,517,518,519,
            614,616,613,615,617,618,634,453,499,629,
            504,499,630,242,379,499,635,499,381,499,
            625,622,367,541,542,543,544,545,546,423,
            421,532,533,534,535,536,499,515,516,514,
            517,518,519,290,624,623,626,91,635,499,
            634,352,499,629,584,499,630,611,111,499,
            187,60,67,506,499,499,499,499,499,499,
            358,499,499,499,499,499,531,499,499,499,
            499,499,634,499,583,629,336,499,630,499,
            499,328,499,499,138,296,453,499,499,499,
            499,499,499,499,499,499,499,499,478
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            10,160,59,57,59,152,8,373,373,97,
            58,98,373,120,152,152,152,166,172,97,
            373,152,52,52,52,52,373,308,152,152,
            152,8,8,152,152,152,152,152,152,152,
            152,152,152,152,4,152,373,152,152,154,
            154,152,307,154,373,313,152,152,152,152,
            315,158,357,15,4,50,308,311,164,152,
            308,308,162,162,162,162,373,186,52,197,
            193,232,152,236,152,311,164,16,368,50,
            152,152,152,152,1,321,4,198,152,271,
            152,380,376,237,54,152,4,376,62,272,
            373,381,152,195,376,1,373,373,4,273,
            380,382,54,152,322,379,152,381,152,323,
            193,382,152,234,152
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            43,1,0,42,1,38,41,35,0,78,
            87,37,2,0,2,1,4,3,22,23,
            6,5,24,8,7,9,19,20,17,18,
            10,11,12,13,14,15,21,26,25,27,
            28,16,29,30,31,32,33,90,0,37,
            2,35,41,38,1,0,91,2,78,87,
            0,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,29,30,
            31,32,33,46,83,0,2,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            62,60,61,63,64,65,84,80,88,4,
            3,22,23,6,5,24,8,7,9,19,
            20,17,18,10,11,12,13,14,15,21,
            26,25,27,28,16,29,30,31,32,33,
            79,1,0,37,2,1,0,37,2,91,
            0,37,2,36,0,62,60,61,63,64,
            65,47,48,49,50,51,52,55,56,57,
            58,59,53,54,0,71,69,72,70,73,
            74,0,37,2,34,0,2,1,4,3,
            22,23,6,5,24,8,7,9,19,20,
            17,18,10,11,12,13,14,15,21,26,
            25,27,28,16,29,30,31,32,33,89,
            0,37,2,44,0,2,1,4,3,22,
            23,6,5,24,8,7,9,19,20,17,
            18,10,11,12,13,14,15,21,26,25,
            27,28,16,29,30,31,32,33,81,0,
            2,86,1,4,3,22,23,6,5,24,
            8,7,9,19,20,17,18,10,11,12,
            13,14,15,21,26,25,27,28,16,29,
            30,31,32,33,82,0,36,75,76,77,
            39,0,85,0,79,81,82,45,89,90,
            83,2,1,4,3,22,23,6,5,24,
            8,7,9,19,20,17,18,10,11,12,
            13,14,15,21,26,25,27,28,16,29,
            30,31,32,33,46,0,69,70,71,72,
            73,74,1,38,41,35,36,43,67,66,
            42,68,37,2,0,40,34,0,82,2,
            86,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,16,29,30,
            31,32,33,45,0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            15,4,18,4,31,13,4,16,16,36,
            4,1,16,20,13,13,13,51,28,4,
            16,13,13,13,13,13,16,10,13,13,
            13,4,4,13,13,23,23,23,23,23,
            13,13,13,13,12,13,16,38,13,40,
            40,13,4,40,16,4,13,13,13,13,
            4,16,4,3,13,26,10,4,4,13,
            10,10,49,49,49,49,16,55,13,3,
            16,16,38,3,13,4,4,20,53,13,
            13,23,23,23,5,3,13,20,13,57,
            13,57,4,20,7,34,13,4,20,42,
            16,42,13,4,4,45,16,16,12,20,
            57,20,8,47,3,4,38,42,13,20,
            4,20,13,4,13
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
            17,0,47,0,45,0,14,0
        };
    };
    public final static char nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static byte terminalIndex[] = {0,
            88,91,24,25,26,28,31,32,33,36,
            37,38,39,40,41,42,58,59,60,61,
            62,68,69,70,72,73,74,75,80,82,
            83,84,85,1,92,17,87,89,13,18,
            90,3,4,29,30,35,43,44,45,46,
            47,48,51,52,53,54,55,56,57,65,
            66,76,77,78,79,2,5,6,7,8,
            9,10,11,12,14,15,16,20,21,22,
            23,27,34,49,50,63,64,67,71,81,
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
            46,45,82,45,46,83,90,89,37
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
            98,95,29,0,90,0,98,95,83,98,
            95,112,9,0,46,0,98,95,112,9,
            0,83,95,98,46,0,98,95,24,0,
            89,0,98,106,95,107,5,0,82,107,
            0,98,106,95,93,44,93,6,0,45,
            93,44,93,0,98,106,95,93,6,0,
            45,93,0,99,98,0,95,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            287,284,275,272,263,260,251,248,209,0,
            365,469,351,350,449,444,436,227,137,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,114,108,95,115,78,87,93,35,95,
            95,117,121,98,88,80,84,120,119,95,
            99,79,33,32,31,30,29,100,16,28,
            27,25,26,21,15,14,13,12,11,10,
            18,17,20,19,9,7,24,5,6,23,
            22,4,93,93,93,93,131,129,54,53,
            95,93,93,95,103,93,93,94,93,94,
            94,94,93,93,93,93,112,101,42,95,
            107,93,36,95,85,93,93,98,101,134,
            103,39,103,103,36,95,137,98,34,95,
            44,95,107,98,39,36,139,136,98,106,
            93,106,81,111,130,34,83,105,86,98,
            95,98,34,34,95,95,82,106,45,98,
            107,98,45,93,44
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
