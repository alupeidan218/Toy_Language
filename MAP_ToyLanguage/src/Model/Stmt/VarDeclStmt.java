package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.AlreadyDeclaredException;
import Model.Exception.MyException;
import Model.PrgState;
import Model.Type.*;
import Model.Value.*;

public class VarDeclStmt implements IStmt {
    private final String name;
    private final Type type;
    public VarDeclStmt(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(!symTable.isDefined(name))
        {
            symTable.put(name, type.defaultValue());
        } else {
            throw new AlreadyDeclaredException("Variable " + name + " is already declared");
        }
        return state;
    }
    @Override
    public String toString() {
        return type + " " + name;
    }
    @Override
    public IStmt copy() {
        return new VarDeclStmt(name, type);
    }
}
