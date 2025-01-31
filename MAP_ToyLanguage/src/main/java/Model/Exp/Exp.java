package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Heap.IHeap;
import Model.SymTable.ISymTable;
import Model.Type.Type;
import Model.Value.Value;

public interface Exp {
    Value eval(ISymTable table, IHeap heap) throws MyException;
    Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
    String toString();
    Exp copy();
}
