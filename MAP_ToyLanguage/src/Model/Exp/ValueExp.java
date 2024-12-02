package Model.Exp;

import Model.SymTable.ISymTable;
import Model.Value.Value;
import Model.Heap.*;

public class ValueExp implements Exp {
    private final Value e;
    public ValueExp(Value e) {
        this.e = e;
    }
    @Override
    public Value eval(ISymTable tbl, IHeap heap) {return e;}

    @Override
    public String toString() {
        return e.toString();
    }
    @Override
    public Exp copy(){
        return new ValueExp(e.copy());
    }
}
