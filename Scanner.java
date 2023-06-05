import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private final String source; //store source as simple string
    private final List<Token> tokens = new ArrayList<>(); //list will be filled with tokens that will be generated

    Scanner(String source)
    {
        this.source = source;
    }

    List<Token> scanTokens()
    {
        return tokens;
    }
}
