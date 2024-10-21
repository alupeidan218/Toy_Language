package Model.Type;
import Model.Value.*;
public interface Type {
    boolean equals(Object another);
    Value defaultValue();
    String toString();
}
