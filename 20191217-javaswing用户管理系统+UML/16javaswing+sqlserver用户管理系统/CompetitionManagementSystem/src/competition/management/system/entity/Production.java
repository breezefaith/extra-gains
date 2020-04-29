package competition.management.system.entity;

public class Production extends AbstractEntity{
    protected String description;
    protected String path;

    public Production() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
