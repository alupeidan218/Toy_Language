package Model.Heap;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.InvalidAddressException;
import Model.Exception.KeyNotFoundException;
import Model.Value.Value;

import java.util.Map;

public class Heap implements IHeap {
    final MyIDictionary<Integer, Value> heap;
    Integer nextFree;

    public Heap() {
        heap = new MyDictionary<>();
        nextFree = 1; // 0 is for null address
    }

    public int allocate(Value val) {
        heap.put(nextFree, val);
        nextFree++;
        return nextFree - 1;
    }

    public void deallocate(int addr) throws InvalidAddressException {
        try {
            heap.remove(addr);
        } catch (KeyNotFoundException e) {
            throw new InvalidAddressException("Address " + addr + " is invalid");
        }
    }

    public void write(int addr, Value val) throws InvalidAddressException {
        if (!heap.isDefined(addr)) {
            throw new InvalidAddressException("Address " + addr + " is invalid");
        }
        heap.put(addr, val);
    }

    public Value read(int addr) throws InvalidAddressException {
        Value val = heap.lookup(addr);
        if (val == null) {
            throw new InvalidAddressException("Address " + addr + " is invalid");
        }
        return val;
    }

    public String toString() {
        return "Heap:\n" + heap.toString();
    }

    public Map<Integer, Value> getContent() {
        return heap.toMap();
    }

    public Integer getNextFree() {
        return nextFree;
    }

    public void setContent(Map<Integer, Value> content, Integer nextFree) {
        heap.clear();
        for (Map.Entry<Integer, Value> entry : content.entrySet())
            heap.put(entry.getKey(), entry.getValue());
        this.nextFree = nextFree;
    }
    public void clear(){
        heap.clear();
    }
}
