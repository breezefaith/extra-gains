package problem3;

public class Main {
    public static void main(String[] args) {
        ToolSet toolSet = new ToolSet();
        toolSet.setCanvas(new Canvas());

        toolSet.draw(1, 1, 2, 2);
        toolSet.fill(1, 1, 2, 2);

        toolSet.nextTool();
        toolSet.draw(1, 1, 2, 2);
        toolSet.fill(1, 1, 2, 2);

        toolSet.nextTool();
        toolSet.draw(1, 1, 2, 2);
        toolSet.fill(1, 1, 2, 2);

        toolSet.prevTool();
        toolSet.draw(1, 1, 2, 2);
        toolSet.fill(1, 1, 2, 2);

        toolSet.prevTool();
        toolSet.draw(1, 1, 2, 2);
        toolSet.fill(1, 1, 2, 2);
    }
}
