package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Heap.IHeap;
import Model.SymTable.ISymTable;
import Model.Type.Type;
import Model.Value.Value;

public class ValueExp implements Exp {
    private final Value e;
    public ValueExp(Value e) {
        this.e = e;
    }
    @Override
    public Value eval(ISymTable tbl, IHeap heap) {return e;}
    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException
    {
        return e.getType();
    }
    @Override
    public String toString() {
        return e.toString();
    }
    @Override
    public Exp copy(){
        return new ValueExp(e.copy());
    }
}
