package problem2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws GameLoadException, IOException {
        String filename = setUp("game.txt");
        Lexer lexer = new Lexer(filename);

        ParserFactory parserFactory = new ParserFactory();
        GameLoaderFactory gameLoaderFactory = new GameLoaderFactory();

        Parser parser = parserFactory.createParser(lexer);
        GameLoader gameLoader = gameLoaderFactory.createGameLoader(parser);

        Game game = gameLoader.load();
        System.out.println(game);
    }

    /**
     * initial an txt file for loading
     * @param filename
     * @return
     * @throws IOException
     */
    public static String setUp(String filename) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/" + filename);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println("Character 5 10 Enemy 1 1 Enemy 10 1 Brick 0 0 Brick 3 9 Enemy 5 3");
        printWriter.close();
        return file.getAbsolutePath();
    }
}
