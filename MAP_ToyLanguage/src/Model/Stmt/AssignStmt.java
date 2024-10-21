package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Exception.TypeMismatchException;
import Model.PrgState;
import Model.Exp.*;
import Model.Value.*;
import Model.Type.*;

public class AssignStmt implements IStmt {
    private final String id;
    private final Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.isDefined(id)){
            Value val = exp.eval(symTbl);
            Type typeId = (symTbl.lookup(id)).getType();
            if(val.getType().equals(typeId))
            {
                symTbl.put(id, val);
            }else{
                throw new TypeMismatchException("Declared type of variable"+id+
                        " and type of the assigned expression do not match");
            }
        }else{
            throw new MyException(id+" is not defined");
        }
        return state;
    }
    @Override
    public String toString() {return id+"="+exp.toString();}
    @Override
    public IStmt copy() {
        return new AssignStmt(id, exp.copy());
    }
}
