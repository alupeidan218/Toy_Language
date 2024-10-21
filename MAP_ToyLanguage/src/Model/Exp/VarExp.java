package Model.Exp;

import Model.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Value.Value;

public class VarExp implements Exp {
    private final String id;
    public VarExp(String id) {
        this.id = id;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl) {
        return tbl.lookup(id);
    }
    @Override
    public String toString(){
        return id;
    }
    @Override
    public Exp copy() {return new VarExp(id);}
}
