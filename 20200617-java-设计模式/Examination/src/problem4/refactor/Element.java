package problem4.refactor;

public abstract class Element {
    private int x;
    private int y;

    public abstract int[] getImageData();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
