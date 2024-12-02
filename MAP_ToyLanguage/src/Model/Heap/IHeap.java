package Model.Heap;
import Model.Value.Value;
import Model.Exception.*;

import java.util.Map;

public interface IHeap {
    int allocate(Value val);
    void deallocate(int addr) throws InvalidAddressException;
    void write(int addr, Value val) throws InvalidAddressException;
    Value read(int addr) throws InvalidAddressException;
    String toString();
    Map<Integer, Value> getContent();
    Integer getNextFree();
    void setContent(Map<Integer, Value> content, Integer nextFree);
    void clear();
}
