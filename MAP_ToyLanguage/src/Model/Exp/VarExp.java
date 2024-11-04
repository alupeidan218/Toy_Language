package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.SymTable.ISymTable;
import Model.Value.Value;

public class VarExp implements Exp {
    private final String id;
    public VarExp(String id) {
        this.id = id;
    }
    @Override
    public Value eval(ISymTable tbl) {
        return tbl.getValue(id);
    }
    @Override
    public String toString(){
        return id;
    }
    @Override
    public Exp copy() {return new VarExp(id);}
}
