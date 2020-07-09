import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Graphics g = null;
        FilledGraphicsPainter p = new FilledGraphicsPainter(g);
        FilledRectangleShape rect = new FilledRectangleShape(5, 10, 12, 34);
        rect.paint(p);
    }
}
