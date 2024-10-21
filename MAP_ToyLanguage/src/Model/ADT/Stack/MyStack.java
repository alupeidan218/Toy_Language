package Model.ADT.Stack;
import Model.MyException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private final Stack<T> stack;
    public MyStack() {
        stack = new Stack<>();
    }
    @Override
    public void push(T t) {
        stack.push(t);
    }
    @Override
    public T pop() throws MyException {
        if(stack.isEmpty())
            throw new MyException("Stack is empty!");
        return stack.pop();
    }
    @Override
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for(T element : stack)
            str.append(element).append("\n");
        return str.toString();
    }
    @Override
    public void clear(){
        stack.clear();
    }
}
