package problem3;

public class Canvas {
    public void drawLine(int x1, int y1, int x2, int y2) {
        System.out.println(String.format("Drawing line: (%d, %d)->(%d, %d)", x1, y1, x2, y2));
    }

    public void drawRect(int x1, int y1, int x2, int y2) {
        System.out.println(String.format("Drawing rectangle: (%d, %d)->(%d, %d)", x1, y1, x2, y2));
    }

    public void fillRect(int x1, int y1, int x2, int y2) {
        System.out.println(String.format("Filling rectangle: (%d, %d)->(%d, %d)", x1, y1, x2, y2));
    }

    public void drawCircle(int x1, int y1, int radius) {
        System.out.println(String.format("Drawing circle: (%d, %d, %d)", x1, y1, radius));
    }

    public void fillCircle(int x1, int y1, int radius) {
        System.out.println(String.format("Filling circle: (%d, %d, %d)", x1, y1, radius));
    }
}
