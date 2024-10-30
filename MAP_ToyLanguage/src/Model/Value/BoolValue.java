package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value {
    final private boolean val;
    public BoolValue(boolean value) {val = value;}
    public boolean getValue() {return val;}
    public String toString() {return Boolean.toString(val);}
    public Type getType() {return new BoolType();}
    public Value copy() {return new BoolValue(val);}
}
