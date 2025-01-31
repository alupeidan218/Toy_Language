package Model.Type;

import Model.Value.StringValue;
import Model.Value.Value;

public class StringType implements Type {
    public boolean equals(Object other) {
        return other instanceof StringType;
    }
    public Value defaultValue() {
        return new StringValue("0");
    }
    public String toString() {
        return "String";
    }
}
