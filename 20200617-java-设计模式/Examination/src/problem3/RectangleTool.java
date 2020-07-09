package problem3;

public class RectangleTool implements ITool {
    @Override
    public ITool next() {
        return new CircleTool();
    }

    @Override
    public ITool prev() {
        return new LineTool();
    }

    @Override
    public ToolType getType() {
        return ToolType.Rectangle;
    }
}
