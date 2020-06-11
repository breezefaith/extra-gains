package t3;

public abstract class Monster {
    private String name;

    public abstract String attack();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
