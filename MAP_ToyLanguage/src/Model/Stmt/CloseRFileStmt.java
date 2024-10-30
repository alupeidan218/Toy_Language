package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.FileException;
import Model.Exception.TypeException;
import Model.Exception.TypeMismatchException;
import Model.Exp.Exp;
import Model.PrgState;
import Model.Type.StringType;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt {
    private final Exp exp;
    public CloseRFileStmt(Exp exp) {
        this.exp = exp;
    }
    public PrgState execute(PrgState state) {
        Value str = exp.eval(state.getSymTable());
        if(!str.getType().equals(new StringType()))
            throw new TypeMismatchException("File name must be string!");
        String str_val = ((StringValue)str).getValue();
        BufferedReader br = state.getFileTable().lookup(str_val);
        if(br == null)
            throw new TypeMismatchException("File not found!");
        try{
            br.close();
        } catch(IOException e){
            throw new FileException(e.getMessage());
        }
        state.getSymTable().removeValue(str_val);
        return state;
    }
    public String toString(){
        return "Closing file " + exp.toString();
    }
    public IStmt copy() {
        return new CloseRFileStmt(exp.copy());
    }
}
