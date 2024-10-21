package Model.Stmt;

import Model.PrgState;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) {
        return state;
    }
    @Override
    public String toString() {
        return "NOP statement";
    }
    @Override
    public IStmt copy(){
        return new NopStmt();
    }
}
