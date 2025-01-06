package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.Type;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException;
    String toString();
    IStmt copy();
}
