package problem3;

public interface ITool {
    ITool next();

    ITool prev();

    ToolType getType();
}
