package sial.parser;

public interface SialLexersym {
    public final static int
      Char_CtlCharNotWS = 101,
      Char_LF = 98,
      Char_CR = 99,
      Char_HT = 13,
      Char_FF = 14,
      Char_a = 15,
      Char_b = 16,
      Char_c = 17,
      Char_d = 18,
      Char_e = 19,
      Char_f = 20,
      Char_g = 21,
      Char_h = 22,
      Char_i = 23,
      Char_j = 24,
      Char_k = 25,
      Char_l = 26,
      Char_m = 27,
      Char_n = 28,
      Char_o = 29,
      Char_p = 30,
      Char_q = 31,
      Char_r = 32,
      Char_s = 33,
      Char_t = 34,
      Char_u = 35,
      Char_v = 36,
      Char_w = 37,
      Char_x = 38,
      Char_y = 39,
      Char_z = 40,
      Char__ = 68,
      Char_A = 41,
      Char_B = 42,
      Char_C = 43,
      Char_D = 44,
      Char_E = 45,
      Char_F = 46,
      Char_G = 47,
      Char_H = 48,
      Char_I = 49,
      Char_J = 50,
      Char_K = 51,
      Char_L = 52,
      Char_M = 53,
      Char_N = 54,
      Char_O = 55,
      Char_P = 56,
      Char_Q = 57,
      Char_R = 58,
      Char_S = 59,
      Char_T = 60,
      Char_U = 61,
      Char_V = 62,
      Char_W = 63,
      Char_X = 64,
      Char_Y = 65,
      Char_Z = 66,
      Char_0 = 2,
      Char_1 = 3,
      Char_2 = 4,
      Char_3 = 5,
      Char_4 = 6,
      Char_5 = 7,
      Char_6 = 8,
      Char_7 = 9,
      Char_8 = 10,
      Char_9 = 11,
      Char_AfterASCII = 102,
      Char_Space = 67,
      Char_DoubleQuote = 69,
      Char_SingleQuote = 84,
      Char_Percent = 85,
      Char_VerticalBar = 86,
      Char_Exclamation = 70,
      Char_AtSign = 87,
      Char_BackQuote = 88,
      Char_Tilde = 89,
      Char_Sharp = 71,
      Char_DollarSign = 90,
      Char_Ampersand = 91,
      Char_Caret = 72,
      Char_Colon = 92,
      Char_SemiColon = 93,
      Char_BackSlash = 94,
      Char_LeftBrace = 95,
      Char_RightBrace = 96,
      Char_LeftBracket = 73,
      Char_RightBracket = 74,
      Char_QuestionMark = 97,
      Char_Comma = 75,
      Char_Dot = 12,
      Char_LessThan = 76,
      Char_GreaterThan = 77,
      Char_Plus = 78,
      Char_Minus = 79,
      Char_Slash = 80,
      Char_Star = 81,
      Char_LeftParen = 82,
      Char_RightParen = 83,
      Char_Equal = 1,
      Char_EOF = 100;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "Equal",
                 "0",
                 "1",
                 "2",
                 "3",
                 "4",
                 "5",
                 "6",
                 "7",
                 "8",
                 "9",
                 "Dot",
                 "HT",
                 "FF",
                 "a",
                 "b",
                 "c",
                 "d",
                 "e",
                 "f",
                 "g",
                 "h",
                 "i",
                 "j",
                 "k",
                 "l",
                 "m",
                 "n",
                 "o",
                 "p",
                 "q",
                 "r",
                 "s",
                 "t",
                 "u",
                 "v",
                 "w",
                 "x",
                 "y",
                 "z",
                 "A",
                 "B",
                 "C",
                 "D",
                 "E",
                 "F",
                 "G",
                 "H",
                 "I",
                 "J",
                 "K",
                 "L",
                 "M",
                 "N",
                 "O",
                 "P",
                 "Q",
                 "R",
                 "S",
                 "T",
                 "U",
                 "V",
                 "W",
                 "X",
                 "Y",
                 "Z",
                 "Space",
                 "_",
                 "DoubleQuote",
                 "Exclamation",
                 "Sharp",
                 "Caret",
                 "LeftBracket",
                 "RightBracket",
                 "Comma",
                 "LessThan",
                 "GreaterThan",
                 "Plus",
                 "Minus",
                 "Slash",
                 "Star",
                 "LeftParen",
                 "RightParen",
                 "SingleQuote",
                 "Percent",
                 "VerticalBar",
                 "AtSign",
                 "BackQuote",
                 "Tilde",
                 "DollarSign",
                 "Ampersand",
                 "Colon",
                 "SemiColon",
                 "BackSlash",
                 "LeftBrace",
                 "RightBrace",
                 "QuestionMark",
                 "LF",
                 "CR",
                 "EOF",
                 "CtlCharNotWS",
                 "AfterASCII"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
