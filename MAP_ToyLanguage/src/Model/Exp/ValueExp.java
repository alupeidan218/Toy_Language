package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.SymTable.ISymTable;
import Model.Value.Value;

public class ValueExp implements Exp {
    private final Value e;
    public ValueExp(Value e) {
        this.e = e;
    }
    @Override
    public Value eval(ISymTable tbl) {return e;}

    @Override
    public String toString() {
        return e.toString();
    }
    @Override
    public Exp copy(){
        return new ValueExp(e.copy());
    }
}
