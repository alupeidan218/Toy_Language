package Model.Value;
import Model.Type.Type;
import Model.Type.RefType;

public class RefValue implements Value {
    final int address;
    final Type locationType;
    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }
    public Type getType() {
        return new RefType(locationType);
    }
    public int getAddr() {return address;}
    public Value copy() {
        return new RefValue(address, locationType);
    }
    public boolean equals(Object other)
    {
        return other instanceof RefValue && address == ((RefValue)other).address && locationType == ((RefValue)other).locationType;
    }
    public String toString(){
        return "Reference(" + address + "," + locationType.toString() + ")";
    }
}
