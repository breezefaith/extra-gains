package problem2;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer lexer;

    public List<Element> parse() {
        List<Element> elements = new ArrayList<>();
        String tmp = null;
        while ((tmp = lexer.nextToken()) != null) {
            String cls = tmp;
            int x = Integer.parseInt(lexer.nextToken());
            int y = Integer.parseInt(lexer.nextToken());
        }
        return elements;
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }
}
