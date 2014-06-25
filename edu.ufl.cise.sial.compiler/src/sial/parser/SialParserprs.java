package sial.parser;

public class SialParserprs implements lpg.runtime.ParseTable, SialParsersym {
    public final static int ERROR_SYMBOL = 96;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 9;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 10;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 28;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 176;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 96;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 818;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 153;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 51;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 147;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 361;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 95;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 95;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 664;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 665;
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
            0,0,0,0,0,1,0,0,0,1,
            1,0,1,0,0,1,0,0,0,0,
            0,0,0,1,0,1,0,1,0,0,
            0,0,0,0,0,0,0,1,1,0,
            0,0,0,0,1,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            6,42,44,51,49,50,7,48,41,9,
            31,43,45,30,36,32,34,35,40,3,
            13,26,29,33,37,39,2,4,5,8,
            10,11,12,14,15,16,17,18,19,20,
            21,22,23,24,25,27,28,38,46,47,
            1
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
            0,0,0,0,0,0
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
            1,2,5,8,3,3,1,1,1,3,
            0,3,6,6,1,3,1,1,3,3,
            3,4,2,4,3,4,4,2,2,2,
            2,2,2,1,1,1,0,2,3,3,
            3,4,2,2,2,2,3,3,2,1,
            1,1,1,4,1,3,1,1,1,1,
            1,1,3,1,3,3,1,3,3,3,
            1,4,4,1,2,3,1,1,1,1,
            1,1,1,-12,-10,-3,-136,0,0,0,
            0,0,0,0,0,0,-120,0,0,0,
            0,0,0,-19,0,0,-86,0,0,0,
            0,-18,0,0,0,0,0,0,0,0,
            0,0,0,0,0,-137,0,0,0,0,
            0,0,0,0,0,-52,0,-49,0,0,
            0,0,0,0,0,0,0,0,-5,0,
            -2,0,-4,0,-157,0,0,0,0,0,
            0,0,0,0,-163,0,0,0,0,0,
            0,-89,0,0,-40,0,0,0,0,0,
            -14,0,0,0,-72,0,0,0,0,0,
            0,0,0,0,-88,0,0,0,0,0,
            0,-71,0,0,-95,0,0,0,0,0,
            0,0,0,0,-113,0,0,0,0,0,
            0,0,0,0,-119,0,0,0,0,0,
            0,-91,0,0,-164,0,0,0,0,0,
            0,0,0,0,-117,0,0,0,0,0,
            0,-7,0,-118,0,0,0,0,0,0,
            -11,0,-121,0,0,0,0,0,0,-122,
            0,0,0,0,0,0,-139,0,0,0,
            0,0,0,-140,0,0,0,0,0,0,
            -1,-26,0,0,-27,0,0,0,-28,0,
            0,-29,0,0,0,-109,0,0,-6,0,
            0,0,-151,0,0,-131,0,0,-102,0,
            0,-104,0,0,-123,0,0,-132,0,0,
            0,-130,0,0,0,0,-20,0,0,-138,
            0,0,0,-158,0,0,-160,0,0,0,
            0,-15,0,0,0,-94,0,0,-171,0,
            0,-60,0,-173,0,0,-41,0,0,0,
            -54,0,0,0,0,-42,0,0,-43,0,
            0,-44,0,0,0,-55,0,-58,0,-66,
            -76,0,-106,0,0,-8,0,-36,-144,0,
            0,0,0,-146,0,-107,0,0,0,0,
            -108,0,0,0,0,-161,0,-162,0,0,
            -168,-9,-167,0,-13,-16,0,-21,0,-65,
            0,0,-17,0,0,-69,0,-70,0,0,
            0,-22,0,-23,0,-24,0,-25,0,-30,
            -31,-32,0,-33,0,-73,0,-34,0,-35,
            0,-37,0,-75,0,-38,0,-39,0,-45,
            0,-46,0,-47,0,-48,0,-50,0,-51,
            -53,0,-56,0,-57,-59,0,-61,0,-62,
            0,-79,0,-63,0,-74,0,-64,0,-67,
            -68,-77,0,0,0,-78,0,0,-80,-81,
            0,-82,-84,0,-83,0,-100,0,-85,-101,
            -96,-92,-93,0,-125,0,0,0,0,0,
            0,-97,0,-105,0,-112,-124,0,-126,0,
            -127,0,-145,-147,0,0,-155,-156,-159,0,
            -165,-169,0,0,0,-174,0,0,-87,0,
            0,0,-176,0,-90,0,-98,-99,-103,-110,
            -111,-114,-115,-116,-128,-129,-133,-134,-135,-141,
            -142,-143,-148,-149,-150,-152,-153,-154,-166,-170,
            -172,-175,0
        };
    };
    public final static short baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static short rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            27,27,29,20,20,7,7,7,28,28,
            31,32,32,33,33,21,21,21,30,30,
            34,34,34,34,34,34,34,35,42,42,
            39,43,43,36,44,44,44,44,44,45,
            45,22,37,46,46,46,46,46,46,38,
            47,23,23,23,40,41,14,14,10,10,
            16,17,17,11,11,11,11,11,11,11,
            11,11,11,11,11,11,11,25,25,48,
            48,15,15,11,11,19,19,19,26,26,
            11,11,11,11,11,11,11,11,11,11,
            11,11,11,11,13,13,13,49,49,11,
            11,11,11,11,11,11,11,11,11,11,
            12,12,12,12,2,18,18,50,50,50,
            50,50,50,24,9,9,9,8,8,8,
            8,5,5,5,6,6,4,4,4,4,
            4,4,3,1,413,52,40,495,629,150,
            151,144,137,141,251,635,657,503,629,150,
            151,144,140,141,3,13,88,163,629,150,
            151,145,416,656,219,154,174,182,495,20,
            21,22,23,24,25,26,495,629,150,151,
            144,137,141,14,635,657,50,125,503,629,
            150,151,144,137,141,88,635,242,560,254,
            558,204,658,45,592,503,629,150,151,144,
            137,141,589,635,242,495,629,150,151,144,
            137,141,453,635,657,50,508,534,1,60,
            9,85,555,521,89,503,629,150,151,144,
            137,141,498,635,639,469,629,150,151,144,
            137,141,396,635,644,503,629,150,151,144,
            137,141,389,635,646,503,629,150,151,144,
            137,141,295,635,644,503,629,150,151,144,
            137,141,396,635,652,503,629,150,151,144,
            137,141,395,635,660,503,629,150,151,144,
            137,141,167,650,503,629,150,151,144,137,
            141,53,651,503,629,150,151,144,139,141,
            503,629,150,151,144,138,141,503,629,150,
            151,144,143,141,503,629,150,151,144,142,
            141,77,492,508,104,492,508,104,223,492,
            508,104,492,508,104,116,80,77,115,50,
            466,156,114,80,77,113,41,53,221,124,
            555,521,507,508,104,165,555,521,50,41,
            498,79,215,555,521,108,498,82,80,653,
            1,555,521,498,255,555,521,297,555,521,
            39,498,50,458,649,498,50,125,498,355,
            555,521,400,42,414,555,521,50,508,461,
            498,64,56,655,645,498,50,508,572,50,
            508,93,50,508,576,66,64,56,64,56,
            77,64,56,50,508,96,207,223,295,410,
            65,100,55,155,410,95,50,508,94,414,
            2,50,508,92,417,618,41,53,50,41,
            618,410,207,50,125,207,50,556,207,331,
            337,434,407,50,432,500,471,618,552,50,
            40,661,50,460,50,119,50,570,50,571,
            207,294,50,526,50,103,56,272,50,102,
            50,101,295,255,353,99,50,98,50,566,
            50,562,50,579,405,582,408,583,50,72,
            207,50,593,50,63,4,207,302,50,637,
            50,638,553,591,50,587,294,275,50,590,
            295,295,294,118,117,392,294,91,604,553,
            50,640,553,553,476,50,641,558,481,207,
            560,396,459,344,90,399,606,76,75,595,
            611,402,50,49,50,97,396,50,126,50,
            613,399,469,207,50,54,410,207,207,399,
            619,396,50,67,621,659,50,662,474,555,
            30,429,27,50,68,296,491,411,557,46,
            564,570,349,528,354,502,515,563,565,519,
            412,474,536,571,573,521,522,513,527,576,
            544,579,343,665,665
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,0,0,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,0,
            0,1,39,3,0,0,2,44,3,0,
            1,0,0,2,2,0,54,55,0,56,
            57,58,62,0,1,63,64,65,66,67,
            68,69,70,71,72,73,0,0,2,0,
            1,0,42,2,0,1,41,87,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,0,1,46,50,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,29,30,31,32,33,34,35,36,
            37,38,0,1,0,1,0,83,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,0,42,43,0,45,2,0,
            48,45,0,0,0,1,0,94,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,0,0,1,50,93,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,0,0,0,0,1,95,85,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,82,0,44,2,45,
            86,46,0,0,91,2,51,52,0,56,
            57,58,0,0,0,1,3,61,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,50,41,0,44,2,0,0,
            2,53,60,60,0,1,53,0,1,0,
            0,0,0,0,1,0,62,4,5,6,
            7,8,9,10,11,12,13,14,15,16,
            17,18,19,20,21,22,23,24,25,26,
            27,28,29,30,31,32,33,34,35,36,
            37,38,0,49,44,3,49,46,0,0,
            2,49,51,52,49,0,54,55,0,1,
            0,3,0,0,61,63,64,65,66,67,
            68,69,70,71,72,73,74,75,89,74,
            75,0,1,41,0,1,84,3,40,90,
            88,0,0,1,92,3,0,1,50,39,
            42,43,0,45,0,3,48,3,0,46,
            0,0,54,55,51,52,0,0,76,77,
            78,79,80,81,43,0,42,43,3,45,
            46,40,48,0,42,43,3,45,47,43,
            48,0,0,41,0,41,0,0,40,0,
            40,40,0,0,0,47,40,47,47,0,
            0,59,0,47,0,0,41,2,0,0,
            53,0,0,0,41,0,0,0,0,0,
            39,39,0,39,0,39,0,0,0,0,
            0,44,39,44,42,0,0,43,39,0,
            0,0,0,0,42,0,0,0,40,0,
            0,0,0,0,0,0,0,0,0,59,
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
            665,818,665,665,729,553,206,551,548,736,
            208,452,449,446,437,245,538,522,542,540,
            546,544,536,456,441,550,468,532,530,528,
            524,520,372,369,365,362,518,516,514,8,
            665,818,426,654,4,111,672,785,315,665,
            818,18,10,672,672,107,568,564,665,786,
            787,788,738,57,818,708,709,710,711,712,
            713,699,700,701,702,703,3,665,671,665,
            818,19,716,672,665,818,324,617,729,553,
            206,551,548,736,208,452,449,446,437,245,
            538,522,542,540,546,544,536,456,441,550,
            468,532,530,528,524,520,372,369,365,362,
            518,516,514,665,818,743,670,729,553,206,
            551,548,736,208,452,449,446,437,245,538,
            522,542,540,546,544,536,456,441,550,468,
            532,530,528,524,520,372,369,365,362,518,
            516,514,665,818,665,818,665,512,729,553,
            206,551,548,736,208,452,449,446,437,245,
            538,522,542,540,546,544,536,456,441,550,
            468,532,530,528,524,520,372,369,365,362,
            518,516,514,665,812,813,665,817,671,665,
            285,492,665,665,665,818,665,777,729,553,
            206,551,548,736,208,452,449,446,437,245,
            538,522,542,540,546,544,536,456,441,550,
            468,532,530,528,524,520,372,369,365,362,
            518,516,514,665,665,818,670,735,729,553,
            206,551,548,736,208,452,449,446,437,245,
            538,522,542,540,546,544,536,456,441,550,
            468,532,530,528,524,520,372,369,365,362,
            518,516,514,665,665,134,665,818,664,614,
            729,553,206,551,548,736,208,452,449,446,
            437,245,538,522,542,540,546,544,536,456,
            441,550,468,532,530,528,524,520,372,369,
            365,362,518,516,514,379,59,785,672,817,
            493,340,665,665,322,671,333,167,665,786,
            787,788,665,665,665,818,315,622,729,553,
            206,551,548,736,208,452,449,446,437,245,
            538,522,542,540,546,544,536,456,441,550,
            468,532,530,528,524,520,372,369,365,362,
            518,516,514,670,324,58,463,672,61,665,
            672,347,633,609,665,818,811,665,818,58,
            665,136,58,665,818,12,739,729,553,206,
            551,548,736,208,452,449,446,437,245,538,
            522,542,540,546,544,536,456,441,550,468,
            532,530,528,524,520,372,369,365,362,518,
            516,514,665,580,386,315,585,340,665,665,
            671,681,333,167,681,665,11,11,665,818,
            105,177,665,135,626,11,11,11,11,11,
            11,11,11,11,11,11,680,682,602,680,
            682,665,506,324,665,818,496,177,607,225,
            503,665,665,818,422,177,109,506,670,153,
            812,813,85,817,110,315,265,315,665,340,
            665,665,642,643,333,167,665,665,793,795,
            792,794,796,797,771,133,812,813,315,817,
            752,607,265,86,812,813,315,817,789,771,
            265,665,81,324,149,324,665,31,383,28,
            488,235,665,665,665,747,235,698,749,665,
            665,305,665,748,665,62,324,672,69,665,
            354,665,665,665,324,665,665,665,665,665,
            426,376,665,426,665,398,665,665,665,665,
            665,647,157,648,697,665,665,694,196,665,
            665,665,665,665,717,665,665,665,607,665,
            665,665,665,665,665,665,665,665,665,486
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static char asb[] = {0,
            178,229,86,84,86,57,176,280,280,1,
            85,2,280,21,57,57,57,67,70,1,
            280,57,57,57,57,95,95,95,95,280,
            287,57,57,57,57,176,176,57,57,57,
            57,57,57,57,57,57,283,283,91,57,
            280,57,57,279,279,57,286,279,280,225,
            57,57,57,57,231,227,176,176,277,294,
            98,91,278,287,290,279,287,287,292,292,
            57,292,57,292,280,92,387,89,397,388,
            137,339,343,57,91,300,57,290,294,296,
            296,99,406,278,57,57,57,57,183,294,
            294,237,91,413,413,411,91,91,91,91,
            91,91,138,57,347,57,424,420,406,301,
            63,57,65,96,420,59,59,186,91,91,
            388,388,406,348,280,425,57,415,65,420,
            183,420,417,420,280,280,91,349,424,426,
            63,57,59,91,238,423,57,425,57,418,
            239,339,426,57,345,57
        };
    };
    public final static char asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            2,63,64,65,66,67,68,54,55,69,
            70,71,72,73,74,49,75,88,84,92,
            5,4,23,24,7,6,25,9,8,10,
            20,21,18,19,11,12,13,14,15,16,
            22,27,26,28,29,30,17,31,32,33,
            34,35,36,37,38,83,1,0,46,45,
            48,43,3,1,42,0,74,49,75,63,
            64,65,66,67,68,69,70,71,72,73,
            54,55,0,95,2,82,91,0,55,54,
            3,45,48,42,1,43,0,2,1,5,
            4,23,24,7,6,25,9,8,10,20,
            21,18,19,11,12,13,14,15,16,22,
            27,26,28,29,30,17,31,32,33,34,
            35,36,37,38,94,0,2,1,5,4,
            23,24,7,6,25,9,8,10,20,21,
            18,19,11,12,13,14,15,16,22,27,
            26,28,29,30,17,31,32,33,34,35,
            36,37,38,93,0,45,0,82,91,50,
            2,0,46,1,0,1,5,4,23,24,
            7,6,25,9,8,10,20,21,18,19,
            11,12,13,14,15,16,22,27,26,28,
            29,30,17,31,32,33,34,35,36,37,
            38,62,87,0,89,0,50,2,95,0,
            83,85,86,61,93,94,87,2,1,5,
            4,23,24,7,6,25,9,8,10,20,
            21,18,19,11,12,13,14,15,16,22,
            27,26,28,29,30,17,31,32,33,34,
            35,36,37,38,62,0,39,43,1,50,
            2,0,1,49,0,39,56,57,58,44,
            0,50,2,39,0,50,2,44,0,2,
            1,5,4,23,24,7,6,25,9,8,
            10,20,21,18,19,11,12,13,14,15,
            16,22,27,26,28,29,30,17,31,32,
            33,34,35,36,37,38,85,0,50,2,
            40,0,50,2,60,0,2,90,1,5,
            4,23,24,7,6,25,9,8,10,20,
            21,18,19,11,12,13,14,15,16,22,
            27,26,28,29,30,17,31,32,33,34,
            35,36,37,38,86,0,39,40,53,47,
            59,50,2,46,51,52,78,76,79,77,
            80,81,41,3,0,50,2,41,3,0,
            41,3,53,0,59,0,59,41,3,47,
            40,0,86,2,90,1,5,4,23,24,
            7,6,25,9,8,10,20,21,18,19,
            11,12,13,14,15,16,22,27,26,28,
            29,30,17,31,32,33,34,35,36,37,
            38,61,0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            28,27,7,27,19,26,27,29,29,5,
            27,1,29,24,26,26,26,12,9,27,
            29,26,26,26,26,31,31,31,31,29,
            52,26,26,26,26,45,45,26,26,25,
            25,25,25,25,26,26,26,26,16,26,
            29,14,26,43,43,26,52,43,29,27,
            26,26,26,26,27,29,45,45,27,27,
            3,26,54,52,27,43,52,52,56,56,
            26,56,26,56,29,26,27,26,22,27,
            3,29,29,14,26,3,26,27,27,58,
            60,24,27,31,26,25,25,25,34,27,
            27,3,26,27,27,27,26,26,26,26,
            26,26,24,26,62,26,62,27,27,24,
            38,41,27,27,27,26,26,24,26,26,
            27,27,27,47,29,47,26,27,27,27,
            36,27,27,27,29,29,16,24,62,24,
            39,50,26,26,3,27,14,47,26,27,
            24,27,24,26,27,26
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static char nasr[] = {0,
            33,34,10,0,30,0,28,0,44,46,
            0,21,0,18,0,1,24,0,29,31,
            0,50,0,11,2,1,0,27,7,0,
            1,13,0,48,0,25,0,47,23,0,
            45,0,14,0,3,0,10,16,0,22,
            0,12,0,49,0,15,0,43,0,42,
            0,17,0
        };
    };
    public final static char nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static char terminalIndex[] = {0,
            92,95,3,27,28,29,31,34,35,36,
            39,40,41,42,43,44,45,61,62,63,
            64,65,70,71,72,74,75,76,77,78,
            80,82,83,84,85,86,87,89,19,1,
            2,93,94,13,96,4,20,17,79,91,
            5,6,18,54,55,14,15,16,21,32,
            33,38,46,47,48,49,50,51,56,57,
            58,59,60,68,88,7,8,9,10,11,
            12,23,24,25,26,30,37,52,53,66,
            67,69,73,81,90,97
        };
    };
    public final static char terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static char nonterminalIndex[] = {0,
            100,121,123,128,127,0,101,126,120,0,
            112,122,124,0,0,113,0,115,119,0,
            104,108,111,114,116,118,98,0,99,0,
            102,0,103,105,0,0,0,0,0,0,
            0,0,0,106,107,109,110,117,0,125,
            0
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
            11,11,11,11,11,11,11,11,4,10
        };
    };
    public final static char scopeLhs[] = ScopeLhs.scopeLhs;
    public final int scopeLhs(int index) { return scopeLhs[index]; }

    public interface ScopeLa {
        public final static byte scopeLa[] = {
            62,61,86,61,62,87,94,93,53,50
        };
    };
    public final static byte scopeLa[] = ScopeLa.scopeLa;
    public final int scopeLa(int index) { return scopeLa[index]; }

    public interface ScopeStateSet {
        public final static byte scopeStateSet[] = {
            21,21,21,21,21,21,21,21,1,31
        };
    };
    public final static byte scopeStateSet[] = ScopeStateSet.scopeStateSet;
    public final int scopeStateSet(int index) { return scopeStateSet[index]; }

    public interface ScopeRhs {
        public final static char scopeRhs[] = {0,
            105,48,0,53,0,106,103,31,0,94,
            0,106,103,87,106,103,120,10,0,62,
            0,106,103,120,10,0,87,103,106,62,
            0,106,103,25,0,93,0,106,113,103,
            114,6,0,86,114,0,106,113,103,97,
            60,97,7,0,61,97,60,97,0,106,
            113,103,97,7,0,61,97,0,107,106,
            0,103,0
        };
    };
    public final static char scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            305,235,225,354,347,196,157,340,333,167,
            295,324,315,285,275,265,177,255,208,0,
            434,429,417,414,410,402,395,389,251,0,
            491,621,474,469,606,591,302,272,154,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static char inSymb[] = {0,
            0,123,116,103,124,82,91,97,45,103,
            103,126,130,106,92,84,88,129,128,103,
            107,83,38,37,36,35,34,33,32,31,
            98,17,30,29,28,26,27,22,16,15,
            14,13,12,11,19,18,21,20,10,8,
            25,6,7,24,23,5,97,97,97,97,
            142,140,55,54,103,97,97,97,1,97,
            103,108,97,97,98,98,98,98,97,97,
            49,97,49,97,120,3,97,48,105,104,
            103,114,97,39,108,103,89,97,97,97,
            97,106,105,145,108,44,108,108,39,97,
            97,103,48,54,55,105,3,41,146,52,
            51,46,106,40,103,60,103,114,105,106,
            44,39,44,44,144,39,39,106,53,53,
            104,104,105,113,97,113,85,119,3,141,
            40,122,105,122,87,112,90,106,103,106,
            59,40,40,59,103,103,86,113,61,105,
            106,114,106,61,97,60
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
            "AllocIndex",
            "AllocIndexList",
            "ContiguousAllocIndexExprList",
            "ContiguousAllocIndexExpr",
            "Expression",
            "DataBlock",
            "AssignOp",
            "StringLiteral",
            "Arg",
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
