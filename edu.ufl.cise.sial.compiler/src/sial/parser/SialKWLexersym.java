package sial.parser;

public interface SialKWLexersym {
    public final static int
      Char_DollarSign = 26,
      Char_Percent = 27,
      Char__ = 15,
      Char_a = 3,
      Char_b = 17,
      Char_c = 9,
      Char_d = 10,
      Char_e = 1,
      Char_f = 16,
      Char_g = 18,
      Char_h = 23,
      Char_i = 4,
      Char_j = 28,
      Char_k = 29,
      Char_l = 7,
      Char_m = 19,
      Char_n = 8,
      Char_o = 6,
      Char_p = 12,
      Char_q = 20,
      Char_r = 5,
      Char_s = 11,
      Char_t = 2,
      Char_u = 13,
      Char_v = 21,
      Char_w = 24,
      Char_x = 14,
      Char_y = 22,
      Char_z = 30,
      Char_EOF = 25;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "e",
                 "t",
                 "a",
                 "i",
                 "r",
                 "o",
                 "l",
                 "n",
                 "c",
                 "d",
                 "s",
                 "p",
                 "u",
                 "x",
                 "_",
                 "f",
                 "b",
                 "g",
                 "m",
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
