package Model.ADT.List;

import Model.Exception.MyException;

public interface MyIList<T> {
    void add(T item);
    boolean remove(T item);
    void clear();
    T get(int index) throws MyException;
    String toString();
}
