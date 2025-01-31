package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.Type;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) {
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException
    {
        return typeEnv;
    }
    @Override
    public String toString() {
        return ";";
    }
    @Override
    public IStmt copy(){
        return new NopStmt();
    }
}
