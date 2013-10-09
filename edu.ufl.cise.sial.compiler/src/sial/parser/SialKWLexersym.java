package sial.parser;

public interface SialKWLexersym {
    public final static int
      Char_DollarSign = 26,
      Char_Percent = 27,
      Char__ = 15,
      Char_a = 4,
      Char_b = 16,
      Char_c = 10,
      Char_d = 9,
      Char_e = 1,
      Char_f = 17,
      Char_g = 19,
      Char_h = 23,
      Char_i = 3,
      Char_j = 28,
      Char_k = 29,
      Char_l = 7,
      Char_m = 18,
      Char_n = 8,
      Char_o = 6,
      Char_p = 14,
      Char_q = 20,
      Char_r = 5,
      Char_s = 11,
      Char_t = 2,
      Char_u = 12,
      Char_v = 21,
      Char_w = 24,
      Char_x = 13,
      Char_y = 22,
      Char_z = 30,
      Char_EOF = 25;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "e",
                 "t",
                 "i",
                 "a",
                 "r",
                 "o",
                 "l",
                 "n",
                 "d",
                 "c",
                 "s",
                 "u",
                 "x",
                 "p",
                 "_",
                 "b",
                 "f",
                 "m",
                 "g",
                 "q",
                 "v",
                 "y",
                 "h",
                 "w",
                 "EOF",
                 "DollarSign",
                 "Percent",
                 "j",
                 "k",
                 "z"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
