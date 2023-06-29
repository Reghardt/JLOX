class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line)
    {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line; //line for error handeling, very simple but line is good enough
    }

    public String toString()
    {
        return type + " " + lexeme + " " + literal;
    }
}
