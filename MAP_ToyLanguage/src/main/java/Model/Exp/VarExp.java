package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Heap.IHeap;
import Model.SymTable.ISymTable;
import Model.Type.Type;
import Model.Value.Value;

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
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException
    {
        return typeEnv.lookup(id);
    }
    @Override
    public String toString(){
        return id;
    }
    @Override
    public Exp copy() {return new VarExp(id);}
}
