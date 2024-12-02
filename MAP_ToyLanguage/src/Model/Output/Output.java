package Model.Output;
import Model.ADT.List.*;

public class Output implements IOutput {
    final MyIList<String> list;
    public Output() {
        this.list = new MyList<>();
    }
    public void add(String st) {
        this.list.add(st);
    }
    public String toString() {
        return "Output:\n" + list.toString();
    }
    public void clear() {
        this.list.clear();
    }
}
