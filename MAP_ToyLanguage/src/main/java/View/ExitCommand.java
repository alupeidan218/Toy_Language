package View;

public class ExitCommand extends Command {
    public ExitCommand(Integer key, String desc) {
        super(key, desc);
    }
    @Override
    public void execute() {
        System.exit(0);
    }
}
