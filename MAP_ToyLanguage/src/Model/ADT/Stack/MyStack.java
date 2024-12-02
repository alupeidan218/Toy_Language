package Model.ADT.Stack;
import Model.Exception.MyException;
import Model.Exception.StackEmptyException;

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
            throw new StackEmptyException("Stack is empty");
        return stack.pop();
    }
    public T top() throws MyException {
        if(stack.isEmpty())
            throw new StackEmptyException("Stack is empty");
        return stack.peek();
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
