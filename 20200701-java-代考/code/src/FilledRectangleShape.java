public class FilledRectangleShape {
    int _x, _y, _width, _height;

    public FilledRectangleShape(int x, int y, int width, int height) {
        _x = x;
        _y = y;
        _width = width;
        _height = height;
    }

    public void paint(Painter painter) {
        painter.drawRect(_x, _y, _width, _height);
    }
}