package Model.Stmt;

import Model.Exception.MyException;
import Model.ExeStack.ExeStack;
import Model.PrgState;

public class ForkStmt implements IStmt {
    private final IStmt stmt;
    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }
    public PrgState execute(PrgState state) throws MyException
    {
        return new PrgState(new ExeStack(),
                state.getSymTable().copy(),
                state.getOut(),
                state.getFileTable(),
                state.getHeap(),
                stmt);
    }
    public String toString()
    {
        return "fork(" + stmt.toString() + ")";
    }
    public IStmt copy(){
        return new ForkStmt(stmt.copy());
    }
}
