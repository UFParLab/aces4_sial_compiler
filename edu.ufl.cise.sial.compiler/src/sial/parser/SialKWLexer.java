package sial.parser;

import lpg.runtime.*;

public class SialKWLexer extends SialKWLexerprs
{
    private char[] inputChars;
    private final int keywordKind[] = new int[62 + 1];

    public int[] getKeywordKinds() { return keywordKind; }

    public int lexer(int curtok, int lasttok)
    {
        int current_kind = getKind(inputChars[curtok]),
            act;

        for (act = tAction(START_STATE, current_kind);
             act > NUM_RULES && act < ACCEPT_ACTION;
             act = tAction(act, current_kind))
        {
            curtok++;
            current_kind = (curtok > lasttok
                                   ? SialKWLexersym.Char_EOF
                                   : getKind(inputChars[curtok]));
        }

        if (act > ERROR_ACTION)
        {
            curtok++;
            act -= ERROR_ACTION;
        }

        return keywordKind[act == ERROR_ACTION  || curtok <= lasttok ? 0 : act];
    }

    public void setInputChars(char[] inputChars) { this.inputChars = inputChars; }


    //
    // Each upper case letter is mapped into its corresponding
    // lower case counterpart. For example, if an 'A' appears
    // in the input, it is mapped into SialKWLexersym.Char_a just
    // like 'a'.
    //
    final static int tokenKind[] = new int[128];
    static
    {
        tokenKind['$'] = SialKWLexersym.Char_DollarSign;
        tokenKind['%'] = SialKWLexersym.Char_Percent;
        tokenKind['_'] = SialKWLexersym.Char__;

        tokenKind['a'] = SialKWLexersym.Char_a;
        tokenKind['b'] = SialKWLexersym.Char_b;
        tokenKind['c'] = SialKWLexersym.Char_c;
        tokenKind['d'] = SialKWLexersym.Char_d;
        tokenKind['e'] = SialKWLexersym.Char_e;
        tokenKind['f'] = SialKWLexersym.Char_f;
        tokenKind['g'] = SialKWLexersym.Char_g;
        tokenKind['h'] = SialKWLexersym.Char_h;
        tokenKind['i'] = SialKWLexersym.Char_i;
        tokenKind['j'] = SialKWLexersym.Char_j;
        tokenKind['k'] = SialKWLexersym.Char_k;
        tokenKind['l'] = SialKWLexersym.Char_l;
        tokenKind['m'] = SialKWLexersym.Char_m;
        tokenKind['n'] = SialKWLexersym.Char_n;
        tokenKind['o'] = SialKWLexersym.Char_o;
        tokenKind['p'] = SialKWLexersym.Char_p;
        tokenKind['q'] = SialKWLexersym.Char_q;
        tokenKind['r'] = SialKWLexersym.Char_r;
        tokenKind['s'] = SialKWLexersym.Char_s;
        tokenKind['t'] = SialKWLexersym.Char_t;
        tokenKind['u'] = SialKWLexersym.Char_u;
        tokenKind['v'] = SialKWLexersym.Char_v;
        tokenKind['w'] = SialKWLexersym.Char_w;
        tokenKind['x'] = SialKWLexersym.Char_x;
        tokenKind['y'] = SialKWLexersym.Char_y;
        tokenKind['z'] = SialKWLexersym.Char_z;

        tokenKind['A'] = SialKWLexersym.Char_a;
        tokenKind['B'] = SialKWLexersym.Char_b;
        tokenKind['C'] = SialKWLexersym.Char_c;
        tokenKind['D'] = SialKWLexersym.Char_d;
        tokenKind['E'] = SialKWLexersym.Char_e;
        tokenKind['F'] = SialKWLexersym.Char_f;
        tokenKind['G'] = SialKWLexersym.Char_g;
        tokenKind['H'] = SialKWLexersym.Char_h;
        tokenKind['I'] = SialKWLexersym.Char_i;
        tokenKind['J'] = SialKWLexersym.Char_j;
        tokenKind['K'] = SialKWLexersym.Char_k;
        tokenKind['L'] = SialKWLexersym.Char_l;
        tokenKind['M'] = SialKWLexersym.Char_m;
        tokenKind['N'] = SialKWLexersym.Char_n;
        tokenKind['O'] = SialKWLexersym.Char_o;
        tokenKind['P'] = SialKWLexersym.Char_p;
        tokenKind['Q'] = SialKWLexersym.Char_q;
        tokenKind['R'] = SialKWLexersym.Char_r;
        tokenKind['S'] = SialKWLexersym.Char_s;
        tokenKind['T'] = SialKWLexersym.Char_t;
        tokenKind['U'] = SialKWLexersym.Char_u;
        tokenKind['V'] = SialKWLexersym.Char_v;
        tokenKind['W'] = SialKWLexersym.Char_w;
        tokenKind['X'] = SialKWLexersym.Char_x;
        tokenKind['Y'] = SialKWLexersym.Char_y;
        tokenKind['Z'] = SialKWLexersym.Char_z;
    };

    final int getKind(char c)
    {
        return (c < 128 ? tokenKind[c] : 0);
    }


    public SialKWLexer(char[] inputChars, int identifierKind)
    {
        this.inputChars = inputChars;
        keywordKind[0] = identifierKind;

        //
        // Rule 1:  Keyword ::= s i a l
        //
        keywordKind[1] = (SialParsersym.TK_sial);

        //
        // Rule 2:  Keyword ::= e n d s i a l
        //
        keywordKind[2] = (SialParsersym.TK_endsial);

        //
        // Rule 3:  Keyword ::= p r o c
        //
        keywordKind[3] = (SialParsersym.TK_proc);

        //
        // Rule 4:  Keyword ::= e n d p r o c
        //
        keywordKind[4] = (SialParsersym.TK_endproc);

        //
        // Rule 5:  Keyword ::= r e t u r n
        //
        keywordKind[5] = (SialParsersym.TK_return);

        //
        // Rule 6:  Keyword ::= c a l l
        //
        keywordKind[6] = (SialParsersym.TK_call);

        //
        // Rule 7:  Keyword ::= p a r d o
        //
        keywordKind[7] = (SialParsersym.TK_pardo);

        //
        // Rule 8:  Keyword ::= e n d p a r d o
        //
        keywordKind[8] = (SialParsersym.TK_endpardo);

        //
        // Rule 9:  Keyword ::= d o
        //
        keywordKind[9] = (SialParsersym.TK_do);

        //
        // Rule 10:  Keyword ::= i n
        //
        keywordKind[10] = (SialParsersym.TK_in);

        //
        // Rule 11:  Keyword ::= e n d d o
        //
        keywordKind[11] = (SialParsersym.TK_enddo);

        //
        // Rule 12:  Keyword ::= c y c l e
        //
        keywordKind[12] = (SialParsersym.TK_cycle);

        //
        // Rule 13:  Keyword ::= e x i t
        //
        keywordKind[13] = (SialParsersym.TK_exit);

        //
        // Rule 14:  Keyword ::= i f
        //
        keywordKind[14] = (SialParsersym.TK_if);

        //
        // Rule 15:  Keyword ::= e l s e
        //
        keywordKind[15] = (SialParsersym.TK_else);

        //
        // Rule 16:  Keyword ::= e n d i f
        //
        keywordKind[16] = (SialParsersym.TK_endif);

        //
        // Rule 17:  Keyword ::= p u t
        //
        keywordKind[17] = (SialParsersym.TK_put);

        //
        // Rule 18:  Keyword ::= g e t
        //
        keywordKind[18] = (SialParsersym.TK_get);

        //
        // Rule 19:  Keyword ::= p r e p a r e
        //
        keywordKind[19] = (SialParsersym.TK_prepare);

        //
        // Rule 20:  Keyword ::= r e q u e s t
        //
        keywordKind[20] = (SialParsersym.TK_request);

        //
        // Rule 21:  Keyword ::= p r e q u e s t
        //
        keywordKind[21] = (SialParsersym.TK_prequest);

        //
        // Rule 22:  Keyword ::= c o l l e c t i v e
        //
        keywordKind[22] = (SialParsersym.TK_collective);

        //
        // Rule 23:  Keyword ::= e x e c u t e
        //
        keywordKind[23] = (SialParsersym.TK_execute);

        //
        // Rule 24:  Keyword ::= a o i n d e x
        //
        keywordKind[24] = (SialParsersym.TK_aoindex);

        //
        // Rule 25:  Keyword ::= m o i n d e x
        //
        keywordKind[25] = (SialParsersym.TK_moindex);

        //
        // Rule 26:  Keyword ::= m o a i n d e x
        //
        keywordKind[26] = (SialParsersym.TK_moaindex);

        //
        // Rule 27:  Keyword ::= m o b i n d e x
        //
        keywordKind[27] = (SialParsersym.TK_mobindex);

        //
        // Rule 28:  Keyword ::= i n d e x
        //
        keywordKind[28] = (SialParsersym.TK_index);

        //
        // Rule 29:  Keyword ::= l a i n d e x
        //
        keywordKind[29] = (SialParsersym.TK_laindex);

        //
        // Rule 30:  Keyword ::= s u b i n d e x
        //
        keywordKind[30] = (SialParsersym.TK_subindex);

        //
        // Rule 31:  Keyword ::= o f
        //
        keywordKind[31] = (SialParsersym.TK_of);

        //
        // Rule 32:  Keyword ::= s c a l a r
        //
        keywordKind[32] = (SialParsersym.TK_scalar);

        //
        // Rule 33:  Keyword ::= s t a t i c
        //
        keywordKind[33] = (SialParsersym.TK_static);

        //
        // Rule 34:  Keyword ::= t e m p
        //
        keywordKind[34] = (SialParsersym.TK_temp);

        //
        // Rule 35:  Keyword ::= l o c a l
        //
        keywordKind[35] = (SialParsersym.TK_local);

        //
        // Rule 36:  Keyword ::= d i s t r i b u t e d
        //
        keywordKind[36] = (SialParsersym.TK_distributed);

        //
        // Rule 37:  Keyword ::= s e r v e d
        //
        keywordKind[37] = (SialParsersym.TK_served);

        //
        // Rule 38:  Keyword ::= c r e a t e
        //
        keywordKind[38] = (SialParsersym.TK_create);

        //
        // Rule 39:  Keyword ::= a l l o c a t e
        //
        keywordKind[39] = (SialParsersym.TK_allocate);

        //
        // Rule 40:  Keyword ::= d e l e t e
        //
        keywordKind[40] = (SialParsersym.TK_delete);

        //
        // Rule 41:  Keyword ::= d e a l l o c a t e
        //
        keywordKind[41] = (SialParsersym.TK_deallocate);

        //
        // Rule 42:  Keyword ::= d e s t r o y
        //
        keywordKind[42] = (SialParsersym.TK_destroy);

        //
        // Rule 43:  Keyword ::= w h e r e
        //
        keywordKind[43] = (SialParsersym.TK_where);

        //
        // Rule 44:  Keyword ::= i m p o r t
        //
        keywordKind[44] = (SialParsersym.TK_import);

        //
        // Rule 45:  Keyword ::= i n t
        //
        keywordKind[45] = (SialParsersym.TK_int);

        //
        // Rule 46:  Keyword ::= p r e d e f i n e d
        //
        keywordKind[46] = (SialParsersym.TK_predefined);

        //
        // Rule 47:  Keyword ::= p e r s i s t e n t
        //
        keywordKind[47] = (SialParsersym.TK_persistent);

        //
        // Rule 48:  Keyword ::= s p e c i a l
        //
         keywordKind[48] = (SialParsersym.TK_special);

        //
        // Rule 49:  Keyword ::= s e r v e r _ b a r r i e r
        //
         keywordKind[49] = (SialParsersym.TK_server_barrier);

        //
        // Rule 50:  Keyword ::= s i p _ b a r r i e r
        //
        keywordKind[50] = (SialParsersym.TK_sip_barrier);

        //
        // Rule 51:  Keyword ::= c o n s t a n t
        //
        keywordKind[51] = (SialParsersym.TK_constant);

        //
        // Rule 52:  Keyword ::= c o n f i g u r e
        //
        keywordKind[52] = (SialParsersym.TK_configure);

        //
        // Rule 53:  Keyword ::= s e c t i o n
        //
        keywordKind[53] = (SialParsersym.TK_section);

        //
        // Rule 54:  Keyword ::= e n d s e c t i o n
        //
        keywordKind[54] = (SialParsersym.TK_endsection);

        //
        // Rule 55:  Keyword ::= p r i n t
        //
        keywordKind[55] = (SialParsersym.TK_print);

        //
        // Rule 56:  Keyword ::= p r i n t l n
        //
        keywordKind[56] = (SialParsersym.TK_println);

        //
        // Rule 57:  Keyword ::= p r i n t _ i n d e x
        //
        keywordKind[57] = (SialParsersym.TK_print_index);

        //
        // Rule 58:  Keyword ::= p r i n t _ s c a l a r
        //
        keywordKind[58] = (SialParsersym.TK_print_scalar);

        //
        // Rule 59:  Keyword ::= s i p _ c o n s i s t e n t
        //
        keywordKind[59] = (SialParsersym.TK_sip_consistent);

        //
        // Rule 60:  Keyword ::= s c o p e d _ e x t e n t
        //
        keywordKind[60] = (SialParsersym.TK_scoped_extent);

        //
        // Rule 61:  Keyword ::= c o n t i g u o u s
        //
        keywordKind[61] = (SialParsersym.TK_contiguous);

        //
        // Rule 62:  Keyword ::= a u t o _ a l l o c a t e
        //
        keywordKind[62] = (SialParsersym.TK_auto_allocate);

        for (int i = 0; i < keywordKind.length; i++)
        {
            if (keywordKind[i] == 0)
                keywordKind[i] = identifierKind;
        }
    }
}

