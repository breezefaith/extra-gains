import java.awt.*;

public class GraphicsPainter implements Painter {
    protected Color DEFAULT_COLOR = Color.BLACK;
    protected Graphics _g;

    public GraphicsPainter(Graphics g) {
        this._g = g;
        _g.setColor(DEFAULT_COLOR);
    }

    public void drawRect(int x, int y, int width, int height) {
        _g.drawRect(x, y, width, height);
    }
}