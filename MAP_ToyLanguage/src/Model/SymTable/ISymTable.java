package Model.SymTable;

import Model.Exception.MyException;
import Model.Type.Type;
import Model.Value.*;

import java.util.Map;

public interface ISymTable {
    void declareValue(String name, Type type) throws MyException;
    Value getValue(String name) throws MyException;
    void setValue(String name, Value value) throws MyException;
    void removeValue(String name) throws MyException;
    String toString();
    void clear();
    Map<String, Value> getContent();
    ISymTable copy();
}
