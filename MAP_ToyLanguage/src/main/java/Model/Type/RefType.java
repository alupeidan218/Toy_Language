package Model.Type;

import Model.Value.RefValue;
import Model.Value.Value;

public class RefType implements Type {
    final Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    public boolean equals(Object other) {
        if (other instanceof RefType)
            return inner.equals(((RefType) other).getInner());
        else
            return false;
    }

    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    public Value defaultValue() {
        return new RefValue(0, inner);
    }
}
