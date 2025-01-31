package Model.ADT.Stack;

import Model.Exception.MyException;

import java.util.List;

public interface MyIStack<T> {
    void push(T t);
    T pop() throws MyException;
    T top() throws MyException;
    boolean isEmpty();
    String toString();
    List<T> getList();
    void clear();
}
