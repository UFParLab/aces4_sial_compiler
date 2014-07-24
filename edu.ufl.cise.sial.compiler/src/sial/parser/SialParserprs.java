package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 99;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 9;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 10;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 28;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 172;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 99;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 852;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 152;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 52;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 151;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 428;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 93;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 93;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 699;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 700;
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
            0,0,0,0,0,0,0,0,0,1,
            0,0,0,0,1,0,0,1,0,0,
            0,0,1,0,0,0,1,0,1,0,
            1,0,0,0,0,0,0,0,0,0,
            1,1,0,0,0,0,1,0,1,0,
            0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            6,42,46,47,51,52,50,49,7,41,
            9,31,43,44,32,34,35,40,3,13,
            26,29,33,36,37,39,2,4,5,8,
            10,11,12,14,15,16,17,18,19,20,
            21,22,23,24,25,27,28,30,38,45,
            48,1
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
            0,0,0,0,0,0,0,0,0
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
            0,3,2,1,1,1,7,11,7,4,
            1,5,8,3,3,1,1,1,3,0,
            3,6,6,1,3,1,3,2,2,4,
            2,4,2,4,2,2,2,1,1,1,
            1,0,2,3,3,3,4,2,2,2,
            2,3,3,2,3,1,1,1,1,4,
            1,3,1,1,1,1,1,1,3,1,
            3,3,1,3,3,3,1,3,1,4,
            4,1,2,2,3,1,1,1,1,1,
            1,1,-12,-10,-46,0,0,0,0,0,
            0,0,0,0,0,-132,0,0,0,0,
            0,0,0,-38,0,0,-2,0,-23,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,-135,0,0,0,0,0,
            0,0,0,-96,0,-133,0,0,0,0,
            0,0,0,-73,0,0,0,0,-157,0,
            0,-137,0,0,0,0,0,0,0,0,
            -4,0,-74,0,0,0,0,-6,0,0,
            0,-5,-7,-27,0,0,0,0,-153,0,
            0,0,0,0,0,0,0,0,0,-159,
            0,0,0,0,0,0,0,0,-11,0,
            0,0,0,-28,0,0,-20,0,-34,0,
            0,0,0,0,0,0,0,0,0,-35,
            0,0,0,0,0,0,0,0,-68,0,
            -62,0,0,0,0,0,0,0,0,0,
            0,-69,0,0,0,0,0,0,0,0,
            -87,0,-75,0,0,0,0,0,0,0,
            0,0,0,-91,0,0,0,0,0,0,
            0,0,-92,0,-110,0,0,0,0,0,
            0,0,0,0,0,-117,0,0,0,0,
            0,0,0,0,-116,0,-160,0,0,0,
            0,0,0,0,0,0,0,-102,0,0,
            0,0,0,0,0,0,-103,0,0,0,
            0,0,0,0,0,-104,0,0,0,0,
            0,0,0,-107,0,0,0,0,0,0,
            0,-108,0,0,0,0,0,0,0,-109,
            0,0,0,0,0,0,0,-1,-14,0,
            0,-29,0,0,-114,0,0,-30,0,0,
            0,-99,0,0,-86,0,0,-101,0,0,
            -54,0,-59,0,0,-118,0,0,-63,0,
            -66,0,-125,0,0,-126,0,0,-127,0,
            -138,0,0,-19,0,-3,-154,0,0,-156,
            0,0,0,0,-167,0,0,0,0,0,
            -67,0,-169,0,0,0,0,-39,0,0,
            -40,0,0,0,0,-41,0,0,-48,0,
            -52,0,0,0,-60,-90,0,0,-71,0,
            -111,0,0,0,-140,0,-112,0,0,-142,
            -149,0,0,0,-164,0,-158,0,-8,0,
            0,-9,-163,0,0,0,-18,0,-72,0,
            0,-13,-15,0,-76,0,-21,0,0,0,
            0,-16,0,-17,0,0,0,-22,0,-24,
            0,-25,0,-26,0,-31,-32,-33,0,-36,
            0,-37,0,-70,0,-42,0,-43,0,0,
            -44,0,-45,0,-47,-49,0,-50,0,-51,
            -53,-55,0,0,-56,0,-57,0,-78,0,
            -58,0,0,-61,0,-64,-65,-79,0,0,
            -82,0,-80,-81,0,-83,0,-84,-85,-88,
            0,-89,-97,0,-120,0,-122,0,0,-77,
            0,-93,0,-155,0,-119,0,-121,0,-141,
            0,0,0,-143,0,-151,-152,-161,0,0,
            -165,0,-170,0,0,0,-172,0,0,-94,
            -95,-98,-100,-105,0,-106,-113,-115,-123,-124,
            -128,-129,-130,-131,-134,-136,-139,-144,-145,-146,
            -147,-148,-150,-162,-166,-168,-171,0
        };
    };
    public final static short baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static short rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            27,27,29,19,19,9,9,9,28,28,
            31,32,32,33,33,20,20,20,30,30,
            34,34,34,34,34,34,34,35,42,42,
            39,43,43,36,44,44,44,44,44,45,
            45,21,37,46,46,46,46,46,46,38,
            47,22,22,22,40,41,48,48,11,11,
            15,16,16,12,12,12,12,12,12,12,
            12,12,12,12,12,12,25,25,49,49,
            24,24,12,12,18,18,26,26,12,12,
            12,12,12,12,12,12,12,12,14,14,
            14,14,50,50,12,12,12,12,12,12,
            12,12,12,12,12,12,13,13,13,13,
            2,17,17,51,51,51,51,51,51,23,
            10,10,10,8,8,8,8,7,7,5,
            5,5,6,6,6,4,4,4,4,4,
            4,3,1,391,158,443,459,149,150,142,
            549,139,133,555,429,445,443,459,149,150,
            142,141,139,13,326,491,93,155,629,4,
            459,149,150,614,153,474,547,552,20,21,
            22,23,24,25,26,443,459,149,150,142,
            549,139,133,555,524,692,443,459,149,150,
            142,140,139,86,4,459,149,150,144,48,
            53,691,443,459,149,150,142,549,139,133,
            555,44,692,4,459,149,150,143,326,539,
            86,50,244,471,37,491,98,30,693,443,
            459,149,150,142,549,139,133,555,111,445,
            443,459,149,150,142,549,139,133,555,437,
            692,1,60,9,37,491,98,514,87,443,
            459,149,150,142,549,139,133,555,110,519,
            443,459,149,150,142,549,139,133,555,526,
            640,443,459,149,150,142,549,139,133,555,
            442,672,443,459,149,150,142,549,139,133,
            555,526,673,428,459,149,150,142,549,139,
            133,555,456,672,443,459,149,150,142,549,
            139,133,555,526,680,443,459,149,150,142,
            549,139,133,555,463,685,443,459,149,150,
            142,549,139,133,555,526,687,443,459,149,
            150,142,549,139,133,555,471,695,443,459,
            149,150,142,549,139,133,683,443,459,149,
            150,142,549,139,133,684,443,459,149,150,
            142,549,139,138,443,459,149,150,142,549,
            139,136,443,459,149,150,142,549,139,135,
            443,459,149,150,142,549,139,134,75,82,
            600,577,37,491,98,79,76,231,37,491,
            98,557,118,600,577,432,109,476,447,491,
            98,196,108,518,557,177,159,600,577,45,
            78,463,103,206,600,577,48,53,557,326,
            41,1,600,577,3,557,40,246,600,577,
            285,600,577,557,686,338,600,577,688,557,
            39,468,557,392,600,577,356,557,326,491,
            618,326,491,91,242,557,326,491,623,326,
            121,389,56,42,690,75,326,121,605,56,
            602,326,491,92,231,464,630,326,491,90,
            464,79,76,679,2,464,477,326,41,466,
            657,480,466,326,121,657,493,431,154,249,
            657,269,466,326,511,154,79,466,40,55,
            696,277,326,601,326,451,453,14,326,515,
            326,114,326,616,326,617,466,282,326,584,
            326,95,326,609,51,299,326,89,326,88,
            312,331,621,394,628,466,326,632,326,63,
            483,466,326,670,321,326,671,326,204,282,
            343,326,633,334,326,115,474,474,282,113,
            112,512,345,282,326,677,326,678,512,466,
            39,521,284,531,448,539,527,539,365,635,
            377,637,326,49,539,75,326,122,326,650,
            466,525,74,530,326,54,466,466,526,644,
            535,326,67,326,697,658,694,326,68,485,
            520,522,384,378,288,27,532,533,534,157,
            383,538,540,156,162,488,204,493,543,545,
            487,489,321,503,546,494,547,550,700,700
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,0,0,1,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,0,1,0,0,
            37,3,39,0,0,42,3,0,1,2,
            0,48,50,51,0,0,0,2,58,4,
            0,59,60,61,62,63,64,65,66,67,
            68,69,0,37,0,39,38,3,0,1,
            36,0,1,45,37,85,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,34,35,0,1,45,
            0,43,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,
            23,24,25,26,27,28,29,30,31,32,
            33,34,35,0,0,0,0,0,0,1,
            3,0,81,5,6,7,8,9,10,11,
            12,13,14,15,16,17,18,19,20,21,
            22,23,24,25,26,27,28,29,30,31,
            32,33,34,35,38,0,43,41,43,46,
            47,46,47,0,43,0,1,46,47,92,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,34,
            35,38,0,0,41,0,1,93,0,91,
            5,6,7,8,9,10,11,12,13,14,
            15,16,17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,34,
            35,0,87,0,0,1,3,0,83,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            0,40,2,80,4,0,1,79,45,84,
            0,1,89,52,53,54,49,0,1,56,
            0,57,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,
            23,24,25,26,27,28,29,30,31,32,
            33,34,35,0,44,55,0,0,2,2,
            4,4,0,0,2,2,4,4,0,1,
            0,0,1,0,1,58,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,34,35,0,1,2,
            0,0,49,2,44,4,0,44,0,3,
            50,51,0,1,2,0,0,1,57,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,0,0,37,0,39,0,3,42,
            0,0,82,0,44,48,86,50,51,37,
            90,39,0,37,42,39,0,0,0,2,
            48,4,0,0,2,2,4,4,36,72,
            70,71,0,36,73,74,75,76,77,78,
            45,0,42,0,72,42,3,0,36,0,
            3,0,40,0,38,0,38,41,3,41,
            0,0,0,0,52,53,54,0,0,0,
            38,3,0,41,0,0,0,36,3,0,
            0,88,0,0,0,0,0,36,0,40,
            0,0,0,40,0,0,0,0,36,36,
            40,0,0,0,37,0,0,0,39,0,
            49,37,0,0,38,0,0,0,0,0,
            0,0,0,0,0,0,0,55,0,0,
            0,0,0,0,0,56,0,0,0,0,
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
            700,852,700,700,852,764,598,509,596,771,
            155,506,501,498,174,582,578,588,586,593,
            591,580,765,766,595,290,279,576,438,432,
            274,244,574,572,570,179,700,461,700,8,
            846,706,847,4,148,851,707,700,852,689,
            102,301,611,607,700,97,700,378,772,387,
            700,743,744,745,746,747,748,734,735,736,
            737,738,700,801,3,800,646,706,700,852,
            516,700,852,705,751,656,764,598,509,596,
            771,155,506,501,498,174,582,578,588,586,
            593,591,580,765,766,595,290,279,576,438,
            432,274,244,574,572,570,179,700,852,705,
            700,777,764,598,509,596,771,155,506,501,
            498,174,582,578,588,586,593,591,580,765,
            766,595,290,279,576,438,432,274,244,574,
            572,570,179,130,700,132,700,18,700,852,
            707,131,568,764,598,509,596,771,155,506,
            501,498,174,582,578,588,586,593,591,580,
            765,766,595,290,279,576,438,432,274,244,
            574,572,570,179,646,700,420,820,420,412,
            404,412,404,700,420,700,852,412,404,807,
            764,598,509,596,771,155,506,501,498,174,
            582,578,588,586,593,591,580,765,766,595,
            290,279,576,438,432,274,244,574,572,570,
            179,531,700,700,781,700,852,699,137,770,
            764,598,509,596,771,155,506,501,498,174,
            582,578,588,586,593,591,580,765,766,595,
            290,279,576,438,432,274,244,574,572,570,
            179,700,642,700,700,852,706,700,654,764,
            598,509,596,771,155,506,501,498,174,582,
            578,588,586,593,591,580,765,766,595,290,
            279,576,438,432,274,244,574,572,570,179,
            84,816,378,238,387,700,852,396,705,543,
            700,852,243,817,818,819,166,700,852,648,
            700,661,764,598,509,596,771,155,506,501,
            498,174,582,578,588,586,593,591,580,765,
            766,595,290,279,576,438,432,274,244,574,
            572,570,179,700,624,367,96,106,378,378,
            387,387,105,700,378,378,387,387,57,852,
            58,700,852,700,852,773,764,598,509,596,
            771,155,506,501,498,174,582,578,588,586,
            593,591,580,765,766,595,290,279,576,438,
            432,274,244,574,572,570,179,700,852,233,
            12,700,845,378,716,387,10,626,700,707,
            11,11,700,852,233,700,104,461,663,11,
            11,11,11,11,11,11,11,11,11,11,
            715,717,99,58,846,700,847,700,706,851,
            700,700,562,700,716,323,564,674,676,846,
            553,847,700,801,851,800,700,94,700,378,
            323,387,129,85,378,378,387,387,152,214,
            715,717,700,516,824,826,823,825,827,828,
            705,80,542,19,214,851,707,59,516,700,
            707,700,816,31,537,58,260,733,707,783,
            28,700,700,700,817,818,819,700,61,700,
            260,707,700,782,700,62,69,435,707,700,
            700,249,700,700,700,700,700,469,700,466,
            700,700,700,681,700,700,700,700,195,222,
            682,700,700,700,732,700,700,700,729,700,
            206,752,700,700,646,700,700,700,700,700,
            700,700,700,700,700,700,700,219,700,700,
            700,700,700,700,700,667
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            181,302,61,59,61,53,64,258,258,1,
            60,2,258,21,53,53,53,309,312,1,
            258,53,140,53,53,53,142,142,142,142,
            258,330,53,138,138,53,53,53,53,53,
            53,53,53,326,326,138,258,53,53,53,
            329,257,258,189,53,53,53,53,191,300,
            53,138,233,64,64,254,337,66,138,255,
            304,235,140,140,136,236,304,330,330,330,
            53,335,53,335,258,245,101,339,343,53,
            138,146,53,333,337,55,55,383,67,304,
            255,138,138,138,385,385,138,138,138,138,
            53,53,337,186,337,197,138,102,53,347,
            53,396,392,304,147,261,53,263,144,236,
            236,138,138,304,138,392,138,265,304,348,
            258,397,53,387,263,392,392,389,186,392,
            258,258,138,349,396,398,261,53,138,138,
            198,395,53,397,53,390,199,339,398,53,
            345,53
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            3,59,60,61,62,63,64,50,51,65,
            66,67,68,69,70,44,71,86,82,90,
            6,5,22,23,8,7,24,9,10,19,
            20,17,18,11,12,13,14,15,21,25,
            26,16,27,28,29,30,31,32,33,34,
            35,81,1,0,45,3,40,0,93,3,
            80,89,0,42,0,3,1,6,5,22,
            23,8,7,24,9,10,19,20,17,18,
            11,12,13,14,15,21,25,26,16,27,
            28,29,30,31,32,33,34,35,92,0,
            3,1,6,5,22,23,8,7,24,9,
            10,19,20,17,18,11,12,13,14,15,
            21,25,26,16,27,28,29,30,31,32,
            33,34,35,91,0,51,50,2,72,42,
            48,1,37,39,0,3,1,6,5,22,
            23,8,7,24,9,10,19,20,17,18,
            11,12,13,14,15,21,25,26,16,27,
            28,29,30,31,32,33,34,35,83,0,
            80,89,45,3,0,43,1,0,87,0,
            81,83,84,57,91,92,85,3,1,6,
            5,22,23,8,7,24,9,10,19,20,
            17,18,11,12,13,14,15,21,25,26,
            16,27,28,29,30,31,32,33,34,35,
            58,0,1,36,79,38,49,41,55,45,
            3,43,46,47,75,73,76,74,77,78,
            4,2,0,36,37,39,1,45,3,0,
            2,1,37,0,1,6,5,22,23,8,
            7,24,9,10,19,20,17,18,11,12,
            13,14,15,21,25,26,16,27,28,29,
            30,31,32,33,34,35,58,85,0,45,
            3,93,0,45,3,4,2,0,70,44,
            71,59,60,61,62,63,64,65,66,67,
            68,69,50,51,0,1,44,0,36,52,
            53,54,40,0,45,3,36,0,45,3,
            38,0,45,3,56,0,3,88,1,6,
            5,22,23,8,7,24,9,10,19,20,
            17,18,11,12,13,14,15,21,25,26,
            16,27,28,29,30,31,32,33,34,35,
            84,0,4,2,49,0,55,0,55,4,
            2,41,38,0,84,3,88,1,6,5,
            22,23,8,7,24,9,10,19,20,17,
            18,11,12,13,14,15,21,25,26,16,
            27,28,29,30,31,32,33,34,35,57,
            0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            18,24,37,24,12,23,24,19,19,5,
            24,1,19,21,23,23,23,50,34,24,
            19,23,23,23,23,23,15,15,15,15,
            19,52,23,23,23,23,23,22,22,22,
            22,23,23,23,23,7,19,39,23,23,
            52,41,19,24,23,23,23,23,24,19,
            23,23,24,56,56,24,24,3,23,54,
            24,24,23,23,23,24,24,52,52,52,
            23,58,23,58,19,27,3,19,19,39,
            23,3,23,24,24,10,60,24,21,24,
            15,23,23,23,24,24,23,23,23,23,
            22,22,24,25,24,3,23,21,23,62,
            23,62,24,24,21,29,32,24,24,24,
            24,23,23,24,23,24,23,21,24,43,
            19,43,23,24,24,24,24,24,46,24,
            19,19,7,21,62,21,30,48,23,23,
            3,24,39,43,23,24,21,24,21,23,
            24,23
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static char nasr[] = {0,
            33,34,11,0,30,0,1,23,0,43,
            0,29,31,0,1,14,0,27,9,0,
            12,2,1,0,49,0,51,0,47,22,
            0,45,0,44,46,0,28,0,17,0,
            48,0,11,15,0,25,0,21,0,20,
            0,13,0,50,0,3,0,24,0,42,
            0,16,0
        };
    };
    public final static char nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static char terminalIndex[] = {0,
            95,3,98,2,28,29,30,32,36,37,
            40,41,42,43,45,46,62,63,64,65,
            66,71,72,73,75,76,81,83,84,85,
            86,87,88,90,91,19,96,1,97,13,
            20,99,4,80,94,5,6,17,18,55,
            56,14,15,16,21,33,34,39,47,48,
            49,50,51,52,57,58,59,60,61,69,
            89,92,7,8,9,10,11,12,22,24,
            25,26,27,31,38,53,54,67,68,70,
            74,82,93,35,44,77,78,79,100
        };
    };
    public final static char terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static char nonterminalIndex[] = {0,
            103,124,127,128,132,0,131,130,104,123,
            0,115,125,126,116,0,118,122,0,107,
            111,114,117,0,119,121,101,0,102,0,
            105,0,106,108,0,0,0,0,0,0,
            0,0,0,109,110,112,113,0,120,0,
            129,0
        };
    };
    public final static char nonterminalIndex[] = NonterminalIndex.nonterminalIndex;
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
            12,12,12,12,12,12,12,12,4,11
        };
    };
    public final static char scopeLhs[] = ScopeLhs.scopeLhs;
    public final int scopeLhs(int index) { return scopeLhs[index]; }

    public interface ScopeLa {
        public final static byte scopeLa[] = {
            58,57,84,57,58,85,92,91,49,45
        };
    };
    public final static byte scopeLa[] = ScopeLa.scopeLa;
    public final int scopeLa(int index) { return scopeLa[index]; }

    public interface ScopeStateSet {
        public final static byte scopeStateSet[] = {
            27,27,27,27,27,27,27,27,1,37
        };
    };
    public final static byte scopeStateSet[] = ScopeStateSet.scopeStateSet;
    public final int scopeStateSet(int index) { return scopeStateSet[index]; }

    public interface ScopeRhs {
        public final static char scopeRhs[] = {0,
            109,48,0,49,0,110,108,27,0,92,
            0,110,108,85,110,108,122,10,0,58,
            0,110,108,122,10,0,85,108,110,58,
            0,110,108,24,0,91,0,110,115,108,
            116,7,0,84,116,0,110,115,108,100,
            56,100,8,0,57,100,56,100,0,110,
            115,108,100,8,0,57,100,0,111,110,
            0,108,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            367,260,249,222,195,206,166,356,345,420,
            412,404,396,387,378,334,323,233,214,312,
            301,155,290,279,179,0,493,485,480,477,
            471,463,456,442,429,0,535,658,530,525,
            365,343,321,299,153,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,126,118,108,127,80,89,100,42,108,
            108,129,133,110,90,82,86,132,131,108,
            111,81,35,34,33,32,31,30,29,28,
            27,101,16,26,25,21,15,14,13,12,
            11,18,17,20,19,10,24,7,8,6,
            100,100,100,100,145,143,51,50,108,100,
            103,48,100,100,100,1,100,108,112,100,
            109,104,72,2,48,107,109,100,101,101,
            44,100,44,100,122,109,108,116,100,36,
            112,108,87,100,100,100,100,109,110,109,
            149,2,4,79,50,51,47,46,43,112,
            112,112,100,36,100,108,150,110,38,108,
            56,108,116,109,110,40,36,40,40,107,
            107,49,49,109,36,148,36,110,109,115,
            100,115,83,121,2,144,125,109,38,125,
            85,114,88,110,108,110,55,38,38,55,
            108,108,84,115,57,109,110,116,110,57,
            100,56
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
            ":",
            "**",
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
            "broadcast_from",
            "sqrt",
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
            "AllocIndex",
            "AllocIndexList",
            "ContiguousAllocIndexExprList",
            "ContiguousAllocIndexExpr",
            "Expression",
            "DataBlock",
            "AssignOp",
            "Arg",
            "StringLiteral",
            "Primary",
            "RelOp",
            "Term",
            "ExponentExpression",
            "CastExpression"
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
