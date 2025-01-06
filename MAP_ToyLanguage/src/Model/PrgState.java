package Model;

import Model.Exception.MyException;
import Model.Exception.StackEmptyException;
import Model.Stmt.IStmt;
import Model.FileTable.*;
import Model.ExeStack.*;
import Model.Output.*;
import Model.SymTable.*;
import Model.Heap.*;

import java.util.concurrent.atomic.AtomicInteger;

public class PrgState {
    private IExeStack exeStack;
    private ISymTable symTable;
    private IOutput out;
    private IFileTable fileTable;
    private IHeap heap;
    private final IStmt originalPrg;
    private static final AtomicInteger idInc = new AtomicInteger(0);
    private final Integer id;
    public PrgState(IStmt prg) {
        this.id = nextId();
        this.exeStack = new ExeStack();
        this.symTable = new SymTable();
        this.out = new Output();
        this.fileTable = new FileTable();
        this.heap = new Heap();
        originalPrg = prg.copy();
        exeStack.push(prg);
    }
    public static int nextId() {
        return idInc.getAndIncrement();
    }
    public PrgState oneStep() throws MyException {
        if (exeStack.isEmpty()) throw new StackEmptyException("PrgState stack is empty :(");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
    public IExeStack getStack(){
        return exeStack;
    }
    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
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
    public IHeap getHeap(){
        return heap;
    }
    public void setHeap(IHeap heap){
        this.heap = heap;
    }

    public String toString()
    {
        return "PROGRAM ID: " + id +
                "\n" +
                exeStack.toString() +
                "\n" +
                symTable.toString() +
                "\n" +
                heap.toString() +
                "\n" +
                out.toString() +
                "\n" +
                fileTable.toString() +
                "\n";
    }
}
