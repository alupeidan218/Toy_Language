package Model.ADT.Dictionary;

import Model.Exception.KeyNotFoundException;
import Model.Exception.MyException;

import java.util.List;
import java.util.Map;

public interface MyIDictionary<U, V> {
    V lookup(U key);
    boolean isDefined(U key);
    V remove(U key) throws RuntimeException;
    void put(U key, V value);
    String toString();
    List<U> getKeys();
    void clear();
    Map<U, V> toMap();
}
