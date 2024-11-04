package Model.ExeStack;

import Model.Exception.MyException;
import Model.Stmt.IStmt;

public interface IExeStack {
    public void push(IStmt st);
    public IStmt pop() throws MyException;
    public IStmt top() throws MyException;
    public String toString();
    public void clear();
    public boolean isEmpty();
}
