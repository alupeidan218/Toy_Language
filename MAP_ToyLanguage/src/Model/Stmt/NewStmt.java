package Model.Stmt;

import Model.Exception.KeyNotFoundException;
import Model.Exception.MyException;
import Model.Exception.TypeMismatchException;
import Model.Exp.Exp;
import Model.Heap.IHeap;
import Model.PrgState;
import Model.SymTable.ISymTable;
import Model.SymTable.SymTable;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import Model.Heap.*;

public class NewStmt implements IStmt {
    private final String var_name;
    private final Exp expression;
    public NewStmt(String var_name, Exp expression) {
        this.var_name = var_name;
        this.expression = expression;
    }
    public PrgState execute(PrgState state) {
        ISymTable tbl = state.getSymTable();
        IHeap heap = state.getHeap();
        Value val = tbl.getValue(var_name);
        if(val == null) {
            throw new KeyNotFoundException(var_name + " not defined");
        }
        if(!(val.getType() instanceof RefType)) {
            throw new MyException(var_name + " is not a ref type");
        }
        Value exp_val = expression.eval(tbl, heap);
        Type locationType =((RefType)val.getType()).getInner();
        if(!exp_val.getType().equals(locationType)){
            throw new TypeMismatchException("Expression and location types are different");
        }
        int addr = heap.allocate(exp_val);
        tbl.setValue(var_name, new RefValue(addr, locationType));
        return state;
    }
    public String toString() {
        return "Ref " + var_name + "=" + expression.toString();
    }
    public IStmt copy(){
        return new NewStmt(var_name, expression.copy());
    }
}
