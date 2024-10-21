package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.MyException;
import Model.PrgState;
import Model.Exp.*;
import Model.Value.*;

public class PrintStmt implements IStmt {
    private final Exp exp;
    public PrintStmt(Exp exp) {
        this.exp = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<String> out = state.getOut();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value v = exp.eval(symTable);
        out.add(v.toString());
        return state;
    }
    @Override
    public String toString(){return "print(" + exp.toString() + ")";}
    @Override
    public IStmt copy(){
        return new PrintStmt(exp.copy());
    }
}