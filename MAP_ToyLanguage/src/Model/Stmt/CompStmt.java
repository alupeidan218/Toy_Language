package Model.Stmt;

import Model.ExeStack.IExeStack;
import Model.PrgState;

public class CompStmt implements IStmt {
    private final IStmt first;
    private final IStmt second;
    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public PrgState execute(PrgState state) {
        IExeStack stk = state.getStack();
        stk.push(second);
        stk.push(first);
        return null;
    }
    @Override
    public String toString(){
        return "("+first.toString()+","+second.toString()+")";
    }
    @Override
    public IStmt copy() {
        return new CompStmt(first.copy(), second.copy());
    }
}
