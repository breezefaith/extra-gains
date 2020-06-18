package problem3;

public class ToolSet {
    private Canvas canvas;

    private ITool tool = new LineTool();

    public void draw(int x1, int y1, int x2, int y2) {
        if (tool instanceof LineTool) {
            canvas.drawLine(x1, y1, x2, y2);
        } else if (tool instanceof RectangleTool) {
            canvas.drawRect(x1, y1, x2, y2);
        } else if (tool instanceof CircleTool) {
            canvas.drawRect(x1, y1, x2, y2);
        }
    }

    public void fill(int x1, int y1, int x2, int y2) {
        if (tool instanceof RectangleTool) {
            canvas.fillRect(x1, y1, x2, y2);
        } else if (tool instanceof CircleTool) {
            canvas.fillCircle(x1, y1, (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
        }
    }

    public void nextTool() {
        if (tool instanceof LineTool) {
            tool = new RectangleTool();
        } else if (tool instanceof RectangleTool) {
            tool = new CircleTool();
        } else if (tool instanceof CircleTool) {
            tool = new LineTool();
        }
    }

    public void prevTool() {
        if (tool instanceof LineTool) {
            tool = new CircleTool();
        } else if (tool instanceof RectangleTool) {
            tool = new LineTool();
        } else if (tool instanceof CircleTool) {
            tool = new RectangleTool();
        }
    }
}
