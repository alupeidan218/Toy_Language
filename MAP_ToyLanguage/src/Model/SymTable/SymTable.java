package Model.SymTable;
import Model.Exception.MyException;
import Model.Stmt.IStmt;
import Model.ADT.Dictionary.*;
import Model.Type.Type;
import Model.Value.Value;

import java.util.Map;

public class SymTable implements ISymTable {
    MyIDictionary<String, Value> tbl;
    public SymTable() {
        this.tbl = new MyDictionary<>();
    }
    public void declareValue(String name, Type type) throws MyException {
        tbl.put(name, type.defaultValue());
    }
    public Value getValue(String name) throws MyException {
        return tbl.lookup(name);
    }
    public void setValue(String name, Value value) throws MyException {
        tbl.put(name, value);
    }
    public void removeValue(String name) throws MyException {
        tbl.remove(name);
    }
    public String toString() {
        return "Symbols table:\n" + tbl.toString();
    }
    public void clear() {
        tbl.clear();
    }
    public Map<String, Value> getContent(){
        return tbl.toMap();
    }
}
