package Model.Output;

import Model.Exception.MyException;
import Model.Stmt.IStmt;

public interface IOutput {
    public void add(String st);
    public String toString();
    public void clear();
}
