import java.awt.*;

public class FilledGraphicsPainter extends GraphicsPainter {
    public FilledGraphicsPainter(Graphics g) {
        super(g);
    }

    public void drawRect(int x, int y, int width, int height) {
        _g.fillRect(x, y, width, height);
    }
}