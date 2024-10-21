package Model;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.Stmt.IStmt;
import Model.Value.Value;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<String> out;
    private final IStmt originalPrg;
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symTable, MyIList<String> out, IStmt prg) {
        exeStack = stk;
        this.symTable = symTable;
        this.out = out;
        originalPrg = prg.copy();
        stk.push(prg);
    }
    public MyIStack<IStmt> getStack(){
        return exeStack;
    }
    public void setStack(MyIStack<IStmt> exeStack){
        this.exeStack = exeStack;
    }
    public MyIDictionary<String, Value> getSymTable(){
        return symTable;
    }
    public void setSymTable(MyIDictionary<String, Value> symTable){
        this.symTable = symTable;
    }
    public MyIList<String> getOut(){
        return out;
    }
    public void setOut(MyIList<String> out){
        this.out = out;
    }
    public void restart(){
        this.exeStack.clear();
        this.symTable.clear();
        this.out.clear();
        this.exeStack.push(originalPrg.copy());
    }
    public String toString()
    {
        return "Execution stack: \n" + exeStack.toString() +
                "\n" +
                "SymTable: \n" +
                symTable.toString() +
                "\n" +
                "Out: \n" +
                out.toString() +
                "\n";
    }
}
