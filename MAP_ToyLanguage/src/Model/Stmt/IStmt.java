package Model.Stmt;

import Model.Exception.MyException;
import Model.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    String toString();
    IStmt copy();
}
