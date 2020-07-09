package problem2;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Lexer lexer;

    public List<Element> parse() throws GameLoadException {
        try {
            List<Element> elements = new ArrayList<>();
            String tmp = null;
            while ((tmp = lexer.nextToken()) != null) {
                String cls = tmp;
                int x = Integer.parseInt(lexer.nextToken());
                int y = Integer.parseInt(lexer.nextToken());

                switch (cls) {
                    case "Character":
                        elements.add(new Character(x, y));
                        break;
                    case "Brick":
                        elements.add(new Brick(x, y));
                        break;
                    case "Enemy":
                        elements.add(new Enemy(x, y));
                        break;
                }
            }
            return elements;
        } catch (Exception e) {
            throw new GameLoadException();
        }
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }
}
