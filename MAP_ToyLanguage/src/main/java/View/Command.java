package View;

public abstract class Command {
    private final Integer key;
    private final String description;
    public Command(Integer key, String description) {
        this.key = key;
        this.description = description;
    }
    public abstract void execute();
    public Integer getKey() {
        return key;
    }
    public String getDescription() {
        return description;
    }
}
