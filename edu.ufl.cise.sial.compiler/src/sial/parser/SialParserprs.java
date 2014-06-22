package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 95;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 9;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 10;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 20;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 164;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 95;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 753;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 146;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 49;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 144;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 316;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 94;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 94;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 606;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 607;
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
            0,0,0,0,0,0,0,0,0,0,
            0,0,1,0,0,0,0,0,1,1,
            0,1,0,1,0,0,0,0,0,0,
            1,0,1,0,1,0,0,0,0,0,
            0,0,0,0,1,1,0,0,0,0,
            1,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            6,37,39,7,49,47,48,9,31,46,
            42,38,40,30,36,32,34,35,3,13,
            26,29,33,43,2,4,5,8,10,11,
            12,14,15,16,17,18,19,20,21,22,
            23,24,25,27,28,41,44,45,1
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
            0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static short baseCheck[] = {0,
            3,8,0,1,1,1,2,0,2,3,
            0,1,1,2,1,1,1,0,3,1,
            1,1,1,1,1,1,4,0,2,4,
            0,2,6,1,1,1,1,1,1,3,
            1,5,1,1,1,1,1,1,4,3,
            1,2,1,6,3,1,0,0,3,2,
            0,3,2,1,2,2,7,11,7,4,
            1,2,5,8,3,3,3,3,4,2,
            4,3,4,4,2,2,2,2,2,2,
            1,1,1,0,2,3,3,3,4,2,
            2,2,2,3,3,2,1,1,1,1,
            4,1,3,1,1,1,3,0,3,1,
            1,1,1,1,1,3,1,3,3,1,
            3,3,3,1,4,4,1,2,3,1,
            1,1,1,1,1,1,-12,-107,0,-2,
            -49,0,0,0,0,0,0,0,-6,0,
            0,0,-113,0,0,0,0,0,0,0,
            -18,0,0,0,-153,0,0,0,0,0,
            0,0,0,0,0,0,-148,0,0,0,
            0,0,0,0,0,0,0,0,-52,0,
            -72,0,0,0,-8,0,0,0,0,0,
            0,0,-86,0,0,0,0,0,0,0,
            -145,0,0,0,-93,0,0,0,-9,0,
            0,0,0,-31,0,0,-109,0,0,0,
            -4,0,0,0,0,0,0,0,-115,0,
            0,0,-13,0,0,0,0,-57,0,0,
            -114,0,0,0,-66,0,0,0,0,0,
            0,-116,0,0,0,-7,0,0,0,-117,
            0,0,0,0,0,0,0,-118,0,0,
            0,-19,0,0,0,-133,0,0,0,-71,
            0,0,0,-134,0,0,0,0,0,0,
            0,-84,0,0,0,-1,0,-15,0,0,
            -14,0,0,-26,0,0,-27,0,0,0,
            -28,0,0,0,0,0,0,-11,-10,0,
            0,-16,0,0,-29,0,0,-100,0,0,
            -102,0,0,-119,0,0,0,0,-126,0,
            0,-73,0,0,-127,0,0,0,-128,0,
            -132,0,0,-149,0,0,-151,0,0,0,
            -3,-5,0,-17,0,0,0,-54,0,0,
            -159,0,0,-161,0,0,-40,0,0,0,
            -20,0,0,-41,0,0,0,0,0,0,
            0,0,-42,0,0,-43,0,0,-44,0,
            0,-55,0,-58,0,-76,0,-92,0,-104,
            0,0,-105,0,0,0,-138,0,-140,0,
            -106,0,0,-157,0,0,0,-152,0,-156,
            0,0,0,-21,0,-22,0,0,-60,0,
            -23,0,-24,0,-25,0,-30,0,-65,0,
            0,-32,0,-33,0,-34,0,-35,0,-36,
            -37,-69,0,0,-38,0,-39,0,-45,0,
            -46,0,-47,0,-48,0,-50,0,-51,-53,
            0,-59,0,-56,0,0,-61,0,-62,0,
            -63,0,-64,0,-67,-68,-74,0,0,-77,
            -78,-79,-80,-81,-82,-83,-87,-89,0,0,
            -90,0,0,-94,0,0,0,0,0,0,
            -91,0,-95,0,0,-98,-108,-99,-103,0,
            -120,0,-121,-123,0,-122,0,-139,-141,0,
            -146,0,-147,-150,0,-154,0,-158,0,0,
            0,-162,0,0,0,-164,0,-70,-75,-85,
            0,-88,-96,-97,-101,-110,0,0,-111,-112,
            -124,-125,-129,-130,-131,-135,-136,-137,-142,-143,
            -144,-155,-160,-163,0
        };
    };
    public final static short baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static short rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            25,25,27,19,19,4,4,4,26,26,
            29,30,30,31,31,20,20,20,28,28,
            32,32,32,32,32,32,32,33,40,40,
            37,41,41,34,42,42,42,42,42,43,
            43,21,35,44,44,44,44,44,44,36,
            45,22,22,22,38,39,14,14,8,8,
            16,17,17,9,9,9,9,9,9,9,
            9,9,9,9,9,9,9,9,9,9,
            9,9,9,9,9,9,9,9,9,9,
            9,13,13,13,46,46,9,9,9,9,
            9,9,9,9,9,9,9,12,12,12,
            12,2,18,18,24,24,47,47,15,15,
            48,48,48,48,48,48,23,11,11,11,
            10,10,10,10,6,6,6,7,7,5,
            5,5,5,5,5,3,1,410,208,114,
            394,466,580,143,144,321,137,130,134,46,
            205,582,527,466,580,143,144,13,137,130,
            134,460,116,596,526,46,41,292,171,253,
            20,21,22,23,24,25,26,466,580,143,
            144,14,137,130,134,595,40,582,527,46,
            112,466,580,143,144,205,137,130,134,339,
            60,582,585,449,580,143,144,531,137,130,
            134,208,114,582,590,466,580,143,144,205,
            137,130,134,338,352,582,592,466,580,143,
            144,43,137,130,134,117,201,582,590,466,
            580,143,144,205,137,130,134,401,294,582,
            598,466,580,143,144,78,137,130,134,241,
            225,597,466,580,143,144,514,137,133,134,
            466,580,143,144,2,137,132,134,466,580,
            143,144,3,137,131,134,466,580,143,144,
            60,137,136,134,466,580,143,144,348,137,
            135,134,161,580,143,144,78,138,46,424,
            241,85,258,234,41,578,91,41,578,91,
            454,41,578,91,509,381,507,103,49,48,
            102,150,46,502,101,41,578,91,123,258,
            234,457,578,91,165,258,234,454,100,214,
            258,234,76,454,95,39,53,147,454,46,
            41,1,258,234,255,258,234,296,258,234,
            454,63,454,454,46,459,454,599,58,56,
            39,353,258,234,411,258,234,46,578,579,
            454,52,66,454,46,578,426,382,351,1,
            42,9,601,46,578,520,46,578,80,46,
            578,521,58,56,58,56,58,56,46,112,
            46,578,83,46,578,81,65,468,55,468,
            82,46,578,79,468,374,591,377,39,53,
            46,112,394,563,205,563,46,265,469,75,
            563,46,106,46,515,46,516,205,603,53,
            50,300,46,362,46,90,46,89,46,88,
            515,515,295,87,86,46,85,46,517,46,
            522,46,523,46,524,46,525,46,72,205,
            46,541,205,528,46,63,534,46,583,46,
            584,46,546,46,548,515,515,352,105,104,
            352,352,522,522,522,522,205,451,60,549,
            547,459,433,441,60,553,354,78,77,76,
            75,344,359,46,49,554,542,60,545,46,
            84,46,113,343,343,371,46,558,205,46,
            54,205,564,205,343,566,60,602,46,67,
            437,439,46,604,391,249,46,68,517,521,
            536,444,164,539,540,452,348,30,27,404,
            469,485,505,523,519,486,293,347,510,44,
            541,512,398,547,518,607,607
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
            30,31,32,33,34,35,36,37,0,1,
            0,1,0,0,2,0,1,0,0,2,
            2,0,0,2,2,53,54,0,1,0,
            60,2,0,61,62,63,64,65,66,67,
            68,69,70,71,0,0,38,0,0,2,
            42,0,39,43,0,1,86,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,0,1,47,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,36,37,
            0,1,88,0,0,1,82,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,42,43,0,45,2,0,1,46,
            50,48,49,0,1,93,3,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,29,30,31,32,33,34,35,36,
            37,47,0,46,0,1,92,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,0,0,0,0,1,84,3,4,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,34,
            35,36,37,40,41,41,0,0,46,85,
            48,49,0,0,2,2,0,0,55,56,
            57,0,0,1,59,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,36,37,
            47,40,46,0,48,49,0,0,51,2,
            0,58,0,0,0,0,55,56,57,0,
            0,1,60,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,0,1,
            0,0,0,0,51,0,0,1,0,0,
            2,0,53,54,0,0,1,0,0,59,
            61,62,63,64,65,66,67,68,69,70,
            71,72,73,74,0,0,38,94,38,38,
            42,43,83,45,44,44,87,39,50,43,
            91,53,54,38,0,47,38,42,43,0,
            45,0,44,0,0,50,0,0,0,51,
            0,0,0,39,39,75,76,77,78,79,
            80,72,73,74,81,0,52,52,0,0,
            0,0,38,90,0,0,0,38,44,0,
            39,0,0,44,0,0,89,41,45,45,
            40,43,41,52,42,0,0,0,0,0,
            0,0,0,0,0,58,41,0,40,0,
            41,40,42,0,40,39,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            607,753,607,671,504,199,500,497,678,151,
            419,416,413,404,397,487,472,491,489,495,
            493,485,422,388,499,480,481,478,476,474,
            467,345,331,327,324,465,463,461,607,753,
            607,482,4,607,614,607,753,18,10,614,
            614,19,59,614,614,513,511,57,753,58,
            680,614,8,650,651,652,653,654,655,641,
            642,643,644,645,607,94,600,3,607,613,
            658,607,448,700,607,753,561,671,504,199,
            500,497,678,151,419,416,413,404,397,487,
            472,491,489,495,493,485,422,388,499,480,
            481,478,476,474,467,345,331,327,324,465,
            463,461,607,753,612,671,504,199,500,497,
            678,151,419,416,413,404,397,487,472,491,
            489,495,493,485,422,388,499,480,481,478,
            476,474,467,345,331,327,324,465,463,461,
            607,753,543,127,607,753,456,671,504,199,
            500,497,678,151,419,416,413,404,397,487,
            472,491,489,495,493,485,422,388,499,480,
            481,478,476,474,467,345,331,327,324,465,
            463,461,747,748,607,752,613,607,753,288,
            237,280,272,607,753,706,671,504,199,500,
            497,678,151,419,416,413,404,397,487,472,
            491,489,495,493,485,422,388,499,480,481,
            478,476,474,467,345,331,327,324,465,463,
            461,612,607,722,607,753,677,671,504,199,
            500,497,678,151,419,416,413,404,397,487,
            472,491,489,495,493,485,422,388,499,480,
            481,478,476,474,467,345,331,327,324,465,
            463,461,129,607,92,607,753,559,671,504,
            199,500,497,678,151,419,416,413,404,397,
            487,472,491,489,495,493,485,422,388,499,
            480,481,478,476,474,467,345,331,327,324,
            465,463,461,714,428,146,607,607,288,450,
            280,272,61,607,614,613,128,607,715,716,
            717,607,607,753,568,671,504,199,500,497,
            678,151,419,416,413,404,397,487,472,491,
            489,495,493,485,422,388,499,480,481,478,
            476,474,467,345,331,327,324,465,463,461,
            612,714,288,607,280,272,607,62,296,614,
            607,556,607,607,607,607,715,716,717,58,
            607,753,681,671,504,199,500,497,678,151,
            419,416,413,404,397,487,472,491,489,495,
            493,485,422,388,499,480,481,478,476,474,
            467,345,331,327,324,465,463,461,607,753,
            607,98,607,607,304,607,96,482,607,12,
            613,607,11,11,607,607,753,58,607,572,
            11,11,11,11,11,11,11,11,11,11,
            11,622,623,624,607,607,312,606,163,163,
            747,748,342,752,261,261,384,551,213,700,
            318,586,589,312,97,612,163,747,748,126,
            752,607,261,607,607,213,607,607,607,746,
            607,118,607,551,221,728,730,727,729,731,
            732,622,623,624,159,142,718,726,607,607,
            607,31,163,276,28,607,69,163,261,607,
            175,607,607,261,607,607,187,428,229,752,
            430,636,148,640,639,607,607,607,607,607,
            607,607,607,607,607,576,428,607,365,607,
            369,593,659,607,594,551
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            86,43,32,30,32,147,45,243,243,91,
            31,92,243,111,147,147,147,4,7,91,
            243,147,147,147,147,27,27,27,27,243,
            36,147,147,147,147,45,45,147,147,147,
            147,147,147,147,147,147,147,147,23,147,
            243,147,147,242,242,147,35,242,243,192,
            147,147,147,147,194,41,45,45,240,248,
            47,23,241,36,39,242,36,36,246,246,
            246,246,243,24,380,21,387,381,250,289,
            332,147,23,293,147,39,248,336,336,48,
            396,241,147,147,147,147,1,200,23,403,
            403,401,23,23,23,23,23,23,251,147,
            340,147,409,405,396,294,149,147,151,28,
            405,153,23,23,381,381,396,341,243,410,
            147,291,151,405,1,243,243,23,342,409,
            411,149,147,201,408,147,410,147,202,289,
            411,147,334,147
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            46,1,0,72,73,74,61,62,63,64,
            65,66,67,68,69,70,71,53,54,0,
            54,53,38,45,50,42,1,43,0,94,
            2,81,90,0,41,55,56,57,40,0,
            47,2,94,0,45,0,2,1,4,3,
            22,23,6,5,24,8,7,9,19,20,
            17,18,10,11,12,13,14,15,21,26,
            25,27,28,29,16,30,31,32,33,34,
            35,36,37,93,0,81,90,47,2,0,
            2,61,62,63,64,65,66,53,54,67,
            68,69,70,71,72,73,74,87,83,91,
            4,3,22,23,6,5,24,8,7,9,
            19,20,17,18,10,11,12,13,14,15,
            21,26,25,27,28,29,16,30,31,32,
            33,34,35,36,37,82,1,0,38,1,
            42,0,1,4,3,22,23,6,5,24,
            8,7,9,19,20,17,18,10,11,12,
            13,14,15,21,26,25,27,28,29,16,
            30,31,32,33,34,35,36,37,60,86,
            0,88,0,82,84,85,59,92,93,86,
            2,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,29,16,30,
            31,32,33,34,35,36,37,60,0,41,
            43,1,47,2,0,47,2,41,0,2,
            1,4,3,22,23,6,5,24,8,7,
            9,19,20,17,18,10,11,12,13,14,
            15,21,26,25,27,28,29,16,30,31,
            32,33,34,35,36,37,92,0,47,2,
            39,0,2,1,4,3,22,23,6,5,
            24,8,7,9,19,20,17,18,10,11,
            12,13,14,15,21,26,25,27,28,29,
            16,30,31,32,33,34,35,36,37,84,
            0,47,2,58,0,47,2,40,0,2,
            89,1,4,3,22,23,6,5,24,8,
            7,9,19,20,17,18,10,11,12,13,
            14,15,21,26,25,27,28,29,16,30,
            31,32,33,34,35,36,37,85,0,41,
            51,47,2,46,48,49,77,75,78,76,
            79,80,44,38,0,47,2,44,38,0,
            44,38,51,0,52,39,0,85,2,89,
            1,4,3,22,23,6,5,24,8,7,
            9,19,20,17,18,10,11,12,13,14,
            15,21,26,25,27,28,29,16,30,31,
            32,33,34,35,36,37,59,0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            23,29,42,29,44,28,29,24,24,33,
            29,1,24,26,28,28,28,10,20,29,
            24,28,28,28,28,30,30,30,30,24,
            18,28,28,28,28,52,52,28,28,27,
            27,27,27,27,28,28,28,28,7,28,
            24,14,28,47,47,28,18,47,24,29,
            28,28,28,28,29,24,52,52,29,29,
            3,28,35,18,29,47,18,18,54,54,
            54,54,24,28,29,28,56,29,3,24,
            24,14,28,3,28,29,29,58,60,26,
            29,30,28,27,27,27,5,3,28,29,
            29,29,28,28,28,28,28,28,26,28,
            62,28,62,29,29,26,37,40,29,29,
            29,26,28,28,29,29,29,49,24,49,
            28,29,29,29,16,24,24,7,26,62,
            26,38,12,3,29,14,49,28,26,29,
            26,28,29,28
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static char nasr[] = {0,
            31,32,8,0,47,0,1,23,0,20,
            0,21,0,18,0,24,0,12,0,42,
            44,0,25,4,0,9,2,1,0,1,
            13,0,28,0,46,0,45,22,0,43,
            0,26,0,27,29,0,14,0,8,16,
            0,3,0,15,0,48,0,41,0,40,
            0,17,0
        };
    };
    public final static char nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static byte terminalIndex[] = {0,
            91,94,26,27,28,30,33,34,35,38,
            39,40,41,42,43,44,60,61,62,63,
            64,69,70,71,73,74,75,76,77,79,
            81,82,83,84,85,86,88,3,1,13,
            19,92,93,2,95,4,90,5,6,17,
            18,20,53,54,14,15,16,31,32,37,
            45,46,47,48,49,50,55,56,57,58,
            59,67,78,87,7,8,9,10,11,12,
            22,23,24,25,29,36,51,52,65,66,
            68,72,80,89,96
        };
    };
    public final static byte terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static byte nonterminalIndex[] = {0,
            99,115,117,100,125,124,0,0,111,123,
            119,116,118,0,0,112,0,114,0,103,
            107,110,113,120,97,0,98,0,101,0,
            102,104,0,0,0,0,0,0,0,0,
            0,105,106,108,109,0,121,122,0
        };
    };
    public final static byte nonterminalIndex[] = NonterminalIndex.nonterminalIndex;
    public final int nonterminalIndex(int index) { return nonterminalIndex[index]; }

    public interface ScopePrefix {
        public final static byte scopePrefix[] = {
            12,47,38,60,22,22,6,32,1,69
        };
    };
    public final static byte scopePrefix[] = ScopePrefix.scopePrefix;
    public final int scopePrefix(int index) { return scopePrefix[index]; }

    public interface ScopeSuffix {
        public final static byte scopeSuffix[] = {
            20,55,44,66,20,27,10,36,4,72
        };
    };
    public final static byte scopeSuffix[] = ScopeSuffix.scopeSuffix;
    public final int scopeSuffix(int index) { return scopeSuffix[index]; }

    public interface ScopeLhs {
        public final static char scopeLhs[] = {
            9,9,9,9,9,9,9,9,5,8
        };
    };
    public final static char scopeLhs[] = ScopeLhs.scopeLhs;
    public final int scopeLhs(int index) { return scopeLhs[index]; }

    public interface ScopeLa {
        public final static byte scopeLa[] = {
            60,59,85,59,60,86,93,92,51,47
        };
    };
    public final static byte scopeLa[] = ScopeLa.scopeLa;
    public final int scopeLa(int index) { return scopeLa[index]; }

    public interface ScopeStateSet {
        public final static byte scopeStateSet[] = {
            17,17,17,17,17,17,17,17,1,27
        };
    };
    public final static byte scopeStateSet[] = ScopeStateSet.scopeStateSet;
    public final int scopeStateSet(int index) { return scopeStateSet[index]; }

    public interface ScopeRhs {
        public final static char scopeRhs[] = {0,
            106,50,0,51,0,103,99,30,0,93,
            0,103,99,86,103,99,118,9,0,60,
            0,103,99,118,9,0,86,99,103,60,
            0,103,99,24,0,92,0,103,112,99,
            113,5,0,85,113,0,103,112,99,96,
            58,96,6,0,59,96,58,96,0,103,
            112,99,96,6,0,59,96,0,104,103,
            0,99,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            187,304,296,288,280,272,249,261,163,237,
            225,213,312,201,151,0,394,391,377,374,
            371,359,354,348,321,0,444,566,439,437,
            547,534,528,300,147,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,120,114,99,121,81,90,96,45,99,
            99,123,127,103,91,83,87,126,125,99,
            104,82,37,36,35,34,33,32,31,30,
            97,16,29,28,27,25,26,21,15,14,
            13,12,11,10,18,17,20,19,9,7,
            24,5,6,23,22,4,96,96,96,96,
            139,137,54,53,99,96,96,96,1,96,
            99,107,96,96,97,97,97,97,96,96,
            96,96,118,38,96,50,106,105,99,113,
            96,41,107,99,88,96,96,96,96,103,
            106,141,107,40,107,107,41,99,50,53,
            54,106,38,44,143,49,48,46,103,39,
            99,58,99,113,106,103,40,41,40,40,
            142,103,51,51,105,105,106,112,96,112,
            84,117,38,138,39,86,111,89,103,99,
            103,39,39,99,99,85,112,59,103,113,
            103,59,96,58
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
            "[",
            "]",
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
            "print_int",
            "contiguous",
            "gpu_on",
            "gpu_off",
            "gpu_allocate",
            "gpu_free",
            "gpu_put",
            "gpu_get",
            "set_persistent",
            "restore_persistent",
            "sparse",
            "assert_same",
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
            "Expression",
            "AllocIndex",
            "AllocIndexList",
            "RelOp",
            "Term",
            "CastExpression",
            "Primary"
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
