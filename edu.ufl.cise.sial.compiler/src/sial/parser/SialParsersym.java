package sial.parser;

public interface SialParsersym {
    public final static int
      TK_COMMA = 35,
      TK_PLUS = 67,
      TK_MINUS = 43,
      TK_STAR = 44,
      TK_SLASH = 68,
      TK_TENSOR = 69,
      TK_GREATER = 70,
      TK_GEQ = 71,
      TK_LESS = 72,
      TK_LEQ = 73,
      TK_EQ = 74,
      TK_NEQ = 75,
      TK_ASSIGN = 40,
      TK_PLUS_ASSIGN = 76,
      TK_MINUS_ASSIGN = 77,
      TK_STAR_ASSIGN = 78,
      TK_LEFTPAREN = 37,
      TK_RIGHTPAREN = 41,
      TK_sial = 79,
      TK_endsial = 80,
      TK_proc = 81,
      TK_endproc = 82,
      TK_return = 3,
      TK_call = 4,
      TK_pardo = 5,
      TK_endpardo = 83,
      TK_do = 6,
      TK_in = 45,
      TK_enddo = 46,
      TK_cycle = 7,
      TK_exit = 8,
      TK_if = 9,
      TK_else = 84,
      TK_endif = 47,
      TK_put = 10,
      TK_get = 11,
      TK_prepare = 12,
      TK_request = 13,
      TK_prequest = 14,
      TK_collective = 15,
      TK_execute = 16,
      TK_aoindex = 48,
      TK_moindex = 49,
      TK_moaindex = 50,
      TK_mobindex = 51,
      TK_index = 52,
      TK_laindex = 53,
      TK_subindex = 85,
      TK_of = 86,
      TK_scalar = 54,
      TK_int = 55,
      TK_static = 56,
      TK_temp = 57,
      TK_local = 58,
      TK_distributed = 59,
      TK_served = 60,
      TK_create = 17,
      TK_delete = 18,
      TK_allocate = 19,
      TK_deallocate = 20,
      TK_destroy = 21,
      TK_where = 87,
      TK_import = 88,
      TK_predefined = 61,
      TK_persistent = 62,
      TK_special = 89,
      TK_server_barrier = 22,
      TK_sip_barrier = 23,
      TK_section = 24,
      TK_endsection = 90,
      TK_print = 25,
      TK_println = 26,
      TK_print_index = 27,
      TK_print_scalar = 28,
      TK_sip_consistent = 63,
      TK_scoped_extent = 64,
      TK_contiguous = 65,
      TK_auto_allocate = 66,
      TK_gpu_on = 29,
      TK_gpu_off = 30,
      TK_gpu_allocate = 31,
      TK_gpu_free = 32,
      TK_gpu_put = 33,
      TK_gpu_get = 34,
      TK_EOF_TOKEN = 91,
      TK_SINGLE_LINE_COMMENT = 38,
      TK_IDENTIFIER = 1,
      TK_INTLIT = 39,
      TK_DOUBLELIT = 42,
      TK_EOL = 2,
      TK_STRINGLIT = 36,
      TK_ERROR_TOKEN = 92;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "IDENTIFIER",
                 "EOL",
                 "return",
                 "call",
                 "pardo",
                 "do",
                 "cycle",
                 "exit",
                 "if",
                 "put",
                 "get",
                 "prepare",
                 "request",
                 "prequest",
                 "collective",
                 "execute",
                 "create",
                 "delete",
                 "allocate",
                 "deallocate",
                 "destroy",
                 "server_barrier",
                 "sip_barrier",
                 "section",
                 "print",
                 "println",
                 "print_index",
                 "print_scalar",
                 "gpu_on",
                 "gpu_off",
                 "gpu_allocate",
                 "gpu_free",
                 "gpu_put",
                 "gpu_get",
                 "COMMA",
                 "STRINGLIT",
                 "LEFTPAREN",
                 "SINGLE_LINE_COMMENT",
                 "INTLIT",
                 "ASSIGN",
                 "RIGHTPAREN",
                 "DOUBLELIT",
                 "MINUS",
                 "STAR",
                 "in",
                 "enddo",
                 "endif",
                 "aoindex",
                 "moindex",
                 "moaindex",
                 "mobindex",
                 "index",
                 "laindex",
                 "scalar",
                 "int",
                 "static",
                 "temp",
                 "local",
                 "distributed",
                 "served",
                 "predefined",
                 "persistent",
                 "sip_consistent",
                 "scoped_extent",
                 "contiguous",
                 "auto_allocate",
                 "PLUS",
                 "SLASH",
                 "TENSOR",
                 "GREATER",
                 "GEQ",
                 "LESS",
                 "LEQ",
                 "EQ",
                 "NEQ",
                 "PLUS_ASSIGN",
                 "MINUS_ASSIGN",
                 "STAR_ASSIGN",
                 "sial",
                 "endsial",
                 "proc",
                 "endproc",
                 "endpardo",
                 "else",
                 "subindex",
                 "of",
                 "where",
                 "import",
                 "special",
                 "endsection",
                 "EOF_TOKEN",
                 "ERROR_TOKEN"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
