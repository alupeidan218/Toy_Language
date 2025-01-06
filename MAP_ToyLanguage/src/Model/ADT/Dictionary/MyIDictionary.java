package Model.ADT.Dictionary;

import java.util.List;
import java.util.Map;

public interface MyIDictionary<U, V> {
    V lookup(U key);
    boolean isDefined(U key);
    void remove(U key) throws RuntimeException;
    void put(U key, V value);
    String toString();
    List<U> getKeys();
    void clear();
    Map<U, V> toMap();
    MyIDictionary<U, V> copy();
}
