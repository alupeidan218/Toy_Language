package Model.Exp;

import Model.Exception.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Heap.IHeap;
import Model.SymTable.ISymTable;
import Model.Value.Value;

public interface Exp {
    Value eval(ISymTable table, IHeap heap) throws MyException;
    String toString();
    Exp copy();
}
