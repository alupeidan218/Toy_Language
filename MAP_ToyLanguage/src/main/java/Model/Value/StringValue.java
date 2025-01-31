package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value {
    private final String val;
    public StringValue(String val) {
        this.val = val;
    }
    public Type getType() {
        return new StringType();
    }
    public Value copy() {
        return new StringValue(val);
    }
    public String getValue() {
        return val;
    }
    public String toString() {
        return val;
    }
    public boolean equals(Object other){
        return other instanceof StringValue && val.equals(((StringValue)other).val);
    }
}
