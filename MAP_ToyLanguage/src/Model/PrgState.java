package Model;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.Stmt.IStmt;
import Model.Value.Value;
import Model.FileTable.*;
import Model.FileTable.*;
import Model.ExeStack.*;
import Model.Output.*;
import Model.SymTable.*;

public class PrgState {
    private IExeStack exeStack;
    private ISymTable symTable;
    private IOutput out;
    private IFileTable fileTable;
    private final IStmt originalPrg;
    public PrgState(IExeStack stk, ISymTable symTable, IOutput out, IFileTable tbl, IStmt prg) {
        exeStack = stk;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = tbl;
        originalPrg = prg.copy();
        stk.push(prg);
    }
    public IExeStack getStack(){
        return exeStack;
    }
    public void setStack(IExeStack exeStack){
        this.exeStack = exeStack;
    }
    public ISymTable getSymTable(){
        return symTable;
    }
    public void setSymTable(ISymTable symTable){
        this.symTable = symTable;
    }
    public IOutput getOut(){
        return out;
    }
    public void setOut(IOutput out){
        this.out = out;
    }
    public IFileTable getFileTable(){
        return fileTable;
    }
    public void setFileTable(IFileTable fileTable){
        this.fileTable = fileTable;
    }
    public void restart(){
        this.exeStack.clear();
        this.symTable.clear();
        this.out.clear();
        this.fileTable.clear();
        this.exeStack.push(originalPrg.copy());
    }
    public String toString()
    {
        return exeStack.toString() +
                "\n" +
                symTable.toString() +
                "\n" +
                out.toString() +
                "\n" +
                fileTable.toString() +
                "\n";
    }
}
