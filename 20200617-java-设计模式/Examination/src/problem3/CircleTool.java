package problem3;

public class CircleTool implements ITool {
    @Override
    public ITool next() {
        return new LineTool();
    }

    @Override
    public ITool prev() {
        return new RectangleTool();
    }

    @Override
    public ToolType getType() {
        return ToolType.Circle;
    }
}
