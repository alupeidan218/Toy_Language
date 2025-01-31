package Model.ExeStack;

import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Exception.MyException;
import Model.Stmt.IStmt;

import java.util.List;

public class ExeStack implements IExeStack {
    final MyIStack<IStmt> stack;
    public ExeStack() {
        this.stack = new MyStack<>();
    }
    public void push(IStmt stmt) {
        stack.push(stmt);
    }
    public IStmt pop() throws MyException {
        return stack.pop();
    }
    public IStmt top() throws MyException {
        return stack.top();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("Execution stack: \n");
        MyIStack<IStmt> dupStk = new MyStack<>();
        try{
           while(!this.stack.isEmpty()) {
               dupStk.push(this.stack.pop());
               sb.append(dupStk.top().toString()).append("\n");
           }
           while(!dupStk.isEmpty()) {
               this.stack.push(dupStk.pop());
           }
        }catch(MyException e){
            throw new MyException(e.getMessage());
        }
        return sb.toString();
    }
    public List<IStmt> toList() {
        return stack.getList();
    }
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    public void clear() {
        stack.clear();
    }
}
