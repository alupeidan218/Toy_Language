package Model.Stmt;

import Model.Exception.KeyNotFoundException;
import Model.Exception.TypeMismatchException;
import Model.Exp.Exp;
import Model.PrgState;
import Model.SymTable.ISymTable;
import Model.Type.RefType;
import Model.Value.RefValue;
import Model.Value.Value;
import Model.Heap.*;

import java.util.Map;

public class WriteHeapStmt implements IStmt {
    private final String var_name;
    private final Exp expr;
    public WriteHeapStmt(String var_name, Exp expr) {
        this.var_name = var_name;
        this.expr = expr;
    }
    public PrgState execute(PrgState state) {
        ISymTable tbl = state.getSymTable();
        IHeap heap = state.getHeap();
        Value var = tbl.getValue(var_name);
        if(var == null){
            throw new KeyNotFoundException("Variable " + var_name + " not found");
        }
        if(!(var.getType() instanceof RefType)){
            throw new TypeMismatchException("Type of var is not ref");
        }
        int addr = ((RefValue)var).getAddr();
        Map<Integer, Value> map = heap.getContent();
        if(!map.containsKey(addr)) {
            throw new KeyNotFoundException("Address " + addr + " is invalid");
        }
        Value val = expr.eval(tbl, heap);
        if(!val.getType().equals(((RefType) var.getType()).getInner()))
        {
            throw new TypeMismatchException("Type mismatch");
        }
        heap.write(addr, val);
        return null;
    }
    public String toString() {
        return "Ref " + var_name + " -> " + expr.toString();
    }
    public IStmt copy(){
        return new WriteHeapStmt(var_name, expr.copy());
    }
}
