package Model.ADT.List;

import Model.Exception.MyException;

import java.util.List;

public interface MyIList<T> {
    void add(T item);
    boolean remove(T item);
    List<T> getList();
    void clear();
    T get(int index) throws MyException;
    String toString();
}
