package Model.Value;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value {
    final private int val;
    public IntValue(int value) {val = value;}
    public int getValue() {return val;}
    public String toString() {return Integer.toString(val);}
    public Type getType() {return new IntType();}
    public Value copy() {return new IntValue(val);}
}
