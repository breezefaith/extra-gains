public class City {
    private int id;
    private int x;
    private int y;

    // provide members here
    public double distance(City b) {
        // how to calculate the Euclidean distance between this and b?
        return Math.sqrt((b.getX() - x) * (b.getX() - x) + (b.getY() - y) * (b.getY() - y));
    }

    public City() {
        id = 0;
        x = 0;
        y = 0;
    }

    public City(int id, int x, int y) {
        setId(id);
        setX(x);
        setY(y);
    }

    public City(String sid, String sx, String sy) {
        this(Integer.valueOf(sid), Integer.valueOf(sx), Integer.valueOf(sy));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
