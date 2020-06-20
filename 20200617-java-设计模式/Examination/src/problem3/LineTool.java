package problem3;

public class LineTool implements ITool {
    @Override
    public ITool next() {
        return new RectangleTool();
    }

    @Override
    public ITool prev() {
        return new CircleTool();
    }

    @Override
    public ToolType getType() {
        return ToolType.Line;
    }
}
