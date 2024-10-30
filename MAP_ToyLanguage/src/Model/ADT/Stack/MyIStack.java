package Model.ADT.Stack;

import Model.Exception.MyException;

public interface MyIStack<T> {
    void push(T t);
    T pop() throws MyException;
    T top() throws MyException;
    boolean isEmpty();
    String toString();
    void clear();
}
