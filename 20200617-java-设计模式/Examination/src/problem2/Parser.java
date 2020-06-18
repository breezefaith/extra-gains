package problem2;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer lexer;

    public List<Element> parse() throws GameLoadException {
        List<Element> elements = new ArrayList<>();
        String tmp = null;
        while ((tmp = lexer.nextToken()) != null) {
            String cls = tmp;
            int x = Integer.parseInt(lexer.nextToken());
            int y = Integer.parseInt(lexer.nextToken());

            try {
                Element element = null;
                switch (cls) {
                    case "Character":
                        element = new Character(x, y);
                        break;
                    case "Brick":
                        element = new Brick(x, y);
                        break;
                    case "Enemy":
                        element = new Enemy(x, y);
                        break;
                    default:
                        throw new Exception();
                }
                if (element != null) {
                    elements.add(element);
                }
            } catch (Exception e) {
                throw new GameLoadException();
            }
        }
        return elements;
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }
}
