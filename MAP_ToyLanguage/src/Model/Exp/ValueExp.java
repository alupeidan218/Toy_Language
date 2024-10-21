package Model.Exp;

import Model.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Value.Value;

public class ValueExp implements Exp {
    private final Value e;
    public ValueExp(Value e) {
        this.e = e;
    }
    @Override
    public Value eval(MyIDictionary<String,Value> tbl) {return e;}

    @Override
    public String toString() {
        return e.toString();
    }
    @Override
    public Exp copy(){
        return new ValueExp(e.copy());
    }
}
