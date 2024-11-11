package Model.SymTable;

import Model.Exception.MyException;
import Model.Type.Type;
import Model.Value.*;

import java.util.Map;

public interface ISymTable {
    public void declareValue(String name, Type type) throws MyException;
    public Value getValue(String name) throws MyException;
    public void setValue(String name, Value value) throws MyException;
    public void removeValue(String name) throws MyException;
    public String toString();
    public void clear();
    public Map<String, Value> getContent();
}
