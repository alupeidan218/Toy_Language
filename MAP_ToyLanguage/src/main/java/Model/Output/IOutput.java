package Model.Output;

import java.util.List;

public interface IOutput {
    void add(String st);
    String toString();
    List<String> getOutputAsList();
    void clear();
}
