package Model.Exp;

import Model.Exception.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.SymTable.ISymTable;
import Model.Value.Value;

public interface Exp {
    Value eval(ISymTable table) throws MyException;
    String toString();
    Exp copy();
}
