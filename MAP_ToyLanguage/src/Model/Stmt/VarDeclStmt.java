package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.AlreadyDeclaredException;
import Model.Exception.MyException;
import Model.PrgState;
import Model.SymTable.ISymTable;
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
        ISymTable symTable = state.getSymTable();
        Value vl = symTable.getValue(name);
        if(vl == null)
        {
            symTable.declareValue(name, type);
        } else {
            throw new AlreadyDeclaredException("Variable " + name + " is already declared");
        }
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException
    {
        typeEnv.put(name, type);
        return typeEnv;
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
