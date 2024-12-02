package Model.ExeStack;

import Model.Exception.MyException;
import Model.Stmt.IStmt;

public interface IExeStack {
    void push(IStmt st);
    IStmt pop() throws MyException;
    IStmt top() throws MyException;
    String toString();
    void clear();
    boolean isEmpty();
}
