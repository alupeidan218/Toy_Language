package Model.ExeStack;

import Model.Exception.MyException;
import Model.Stmt.IStmt;

import java.util.List;

public interface IExeStack {
    void push(IStmt st);
    IStmt pop() throws MyException;
    IStmt top() throws MyException;
    String toString();
    List<IStmt> toList();
    void clear();
    boolean isEmpty();
}
