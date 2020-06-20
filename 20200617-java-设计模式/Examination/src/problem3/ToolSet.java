package problem3;

public class ToolSet {
    private Canvas canvas;
    private ITool tool = new LineTool();

    public ToolSet() {
        System.out.println(String.format("Current tool is %s", tool.getType().name()));
    }

    public void draw(int x1, int y1, int x2, int y2) {
        switch (tool.getType()) {
            case Line:
                canvas.drawLine(x1, y1, x2, y2);
                break;
            case Rectangle:
                canvas.drawRect(x1, y1, x2, y2);
                break;
            case Circle:
                canvas.drawCircle(x1, y1, (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
                break;
        }
    }

    public void fill(int x1, int y1, int x2, int y2) {
        switch (tool.getType()) {
            case Line:
                canvas.drawLine(x1, y1, x2, y2);
                break;
            case Rectangle:
                canvas.fillRect(x1, y1, x2, y2);
                break;
            case Circle:
                canvas.fillCircle(x1, y1, (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
                break;
        }
    }

    public void nextTool() {
        tool = tool.next();
        System.out.println(String.format("Current tool is %s", tool.getType().name()));
    }

    public void prevTool() {
        tool = tool.prev();
        System.out.println(String.format("Current tool is %s", tool.getType().name()));
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public ITool getTool() {
        return tool;
    }
}
