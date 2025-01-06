package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.AlreadyDeclaredException;
import Model.Exception.MyException;
import Model.Exception.TypeMismatchException;
import Model.FileTable.IFileTable;
import Model.PrgState;
import Model.Exp.*;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

public class OpenRStmt implements IStmt {
    private final Exp exp;
    public OpenRStmt(Exp exp) {
        this.exp = exp;
    }
    public PrgState execute(PrgState state) throws MyException {
        Value v = exp.eval(state.getSymTable(), state.getHeap());
        if(v.getType().equals(new StringType()))
        {
            IFileTable tbl = state.getFileTable();
            String name = ((StringValue) v).getValue();
            if(tbl.lookup(name) == null){
                tbl.openFile(name);
            } else {
                throw new AlreadyDeclaredException("File already exists!");
            }
        } else {
            throw new TypeMismatchException("File name is not a string!");
        }
        return null;
    }
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException
    {
        Type typexp = exp.typecheck(typeEnv);
        if(typexp.equals(new StringType()))
            return typeEnv;
        else
            throw new TypeMismatchException("File name must be of string type!");
    }
    public String toString(){
        return "Opening file " + exp.toString();
    }
    public IStmt copy() {
        return new OpenRStmt(exp.copy());
    }
}
