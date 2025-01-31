package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Exception.TypeMismatchException;
import Model.Exp.Exp;
import Model.Heap.IHeap;
import Model.PrgState;
import Model.SymTable.ISymTable;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements IStmt {
    private final String id;
    private final Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        ISymTable symTbl = state.getSymTable();
        IHeap heap = state.getHeap();
        Value vl = symTbl.getValue(id);
        if(vl != null){
            Value val = exp.eval(symTbl, heap);
            Type typeId = (val).getType();
            if(val.getType().equals(typeId))
            {
                symTbl.setValue(id, val);
            }else{
                throw new TypeMismatchException("Declared type of variable"+id+
                        " and type of the assigned expression do not match");
            }
        }else{
            throw new MyException(id+" is not defined");
        }
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException
    {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if(typevar.equals(typexp))
            return typeEnv;
        else
            throw new TypeMismatchException("Assignment: right hand side and left hand side have different types");
    }
    @Override
    public String toString() {return id+"="+exp.toString();}
    @Override
    public IStmt copy() {
        return new AssignStmt(id, exp.copy());
    }
}
