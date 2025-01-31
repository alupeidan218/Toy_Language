package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Exception.TypeMismatchException;
import Model.Heap.IHeap;
import Model.SymTable.ISymTable;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class ReadHeapExp implements Exp {
    final Exp expr;
    public ReadHeapExp(Exp expr) {
        this.expr = expr;
    }
    public Value eval(ISymTable tbl, IHeap heap){
        Value val = expr.eval(tbl, heap);
        if(!(val instanceof RefValue))
            throw new TypeMismatchException("Value is not a ref value");
        int addr = ((RefValue)val).getAddr();
        return heap.read(addr);
    }
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException
    {
        Type typ = expr.typecheck(typeEnv);
        if(!(typ instanceof RefType))
            throw new TypeMismatchException("The rH argument is not a ref type");
        RefType reft = (RefType)typ;
        return reft.getInner();
    }
    public Exp copy() {
        return new ReadHeapExp(expr.copy());
    }
    public String toString() {
        return "Deref(" + expr.toString() + ")";
    }
}
