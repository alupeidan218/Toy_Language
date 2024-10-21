package Model.Exp;

import Model.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Value.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> table) throws MyException;
    String toString();
    Exp copy();
}
