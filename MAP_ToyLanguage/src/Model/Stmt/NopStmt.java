package Model.Stmt;

import Model.PrgState;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) {
        return null;
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
