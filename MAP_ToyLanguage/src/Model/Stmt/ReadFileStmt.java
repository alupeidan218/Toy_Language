package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.*;
import Model.Exp.Exp;
import Model.PrgState;
import Model.SymTable.ISymTable;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Model.Heap.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt {
    private final Exp exp;
    private final String var_name;
    public ReadFileStmt(Exp exp, String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }
    public PrgState execute(PrgState state) throws MyException {
        ISymTable sym = state.getSymTable();
        IHeap heap = state.getHeap();
        Value vl = sym.getValue(var_name);
        if(vl == null) {
            throw new KeyNotFoundException(var_name + " is not defined!");
        }
        if(!vl.getType().equals(new IntType())) {
            throw new TypeMismatchException("Type of variable " + var_name + " is not an integer!");
        }
        Value str = exp.eval(sym, heap);
        if(!str.getType().equals(new StringType())) {
            throw new TypeMismatchException("File name must be of type string!");
        }
        BufferedReader br = state.getFileTable().lookup(((StringValue)str).getValue());
        if(br == null) {
            throw new FileException("File " + ((StringValue) str).getValue() + " not found!");
        }
        try {
            String line = br.readLine();
            if(line != null) {
                int ival = Integer.parseInt(line);
                sym.setValue(var_name, new IntValue(ival));
            } else {
                sym.setValue(var_name, new IntValue(0));
            }
        } catch(IOException e) {
            throw new FileException(e.getMessage());
        }
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException
    {
        Type typexp = exp.typecheck(typeEnv);
        if(typexp.equals(new StringType())) {
            Type typevar = typeEnv.lookup(var_name);
            if(typevar.equals(new IntType())) {
                return typeEnv;
            } else {
                throw new TypeMismatchException("Type variable " + var_name + " is not an integer!");
            }
        } else {
            throw new TypeMismatchException("File name must be a string type!");
        }
    }
    @Override
    public String toString() {
        return "Reading file " + exp.toString() + " into variable " + var_name;
    }
    @Override
    public IStmt copy() {
        return new ReadFileStmt(exp.copy(), var_name);
    }
}
