package Model.ADT.Dictionary;

import Model.MyException;

public interface MyIDictionary<U, V> {
    V lookup(U key);
    boolean isDefined(U key);
    V remove(U key) throws MyException;
    void put(U key, V value);
    String toString();
    void clear();
}
