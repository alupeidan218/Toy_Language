package Model.Stmt;

import Model.Exception.MyException;
import Model.Output.IOutput;
import Model.PrgState;
import Model.Exp.*;
import Model.SymTable.ISymTable;
import Model.Value.*;

public class PrintStmt implements IStmt {
    private final Exp exp;
    public PrintStmt(Exp exp) {
        this.exp = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        IOutput out = state.getOut();
        ISymTable symTable = state.getSymTable();
        Value v = exp.eval(symTable, state.getHeap());
        out.add(v.toString());
        return null;
    }
    @Override
    public String toString(){return "print(" + exp.toString() + ")";}
    @Override
    public IStmt copy(){
        return new PrintStmt(exp.copy());
    }
}