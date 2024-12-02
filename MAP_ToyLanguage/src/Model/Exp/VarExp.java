package Model.Exp;

import Model.SymTable.ISymTable;
import Model.Value.Value;
import Model.Heap.*;

public class VarExp implements Exp {
    private final String id;
    public VarExp(String id) {
        this.id = id;
    }
    @Override
    public Value eval(ISymTable tbl, IHeap heap) {
        return tbl.getValue(id);
    }
    @Override
    public String toString(){
        return id;
    }
    @Override
    public Exp copy() {return new VarExp(id);}
}
