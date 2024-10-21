package Model.ADT.Stack;

import Model.MyException;

public interface MyIStack<T> {
    void push(T t);
    T pop() throws MyException;
    boolean isEmpty();
    String toString();
    void clear();
}
