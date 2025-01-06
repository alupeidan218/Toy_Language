package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.ExeStack.ExeStack;
import Model.PrgState;
import Model.Type.Type;

public class ForkStmt implements IStmt {
    private final IStmt stmt;
    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }
    public PrgState execute(PrgState state) throws MyException
    {
        PrgState newState = new PrgState(stmt);
        newState.setSymTable(state.getSymTable().copy());
        newState.setOut(state.getOut());
        newState.setFileTable(state.getFileTable());
        newState.setHeap(state.getHeap());
        return newState;
    }
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException
    {
        return stmt.typecheck(typeEnv);
    }
    public String toString()
    {
        return "fork(" + stmt.toString() + ")";
    }
    public IStmt copy(){
        return new ForkStmt(stmt.copy());
    }
}
