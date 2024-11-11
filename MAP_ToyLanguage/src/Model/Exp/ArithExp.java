package Model.Exp;

import Model.Exception.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Heap.IHeap;
import Model.SymTable.ISymTable;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.Value;

public class ArithExp implements Exp {
    private final Exp e1;
    private final Exp e2;
    private final String op;
    public ArithExp(Exp e1, Exp e2, String op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    /*
        1 -> +
        2 -> -
        3 -> *
        4 -> /
    */

    @Override
    public Value eval(ISymTable tbl, IHeap heap) throws MyException {
        Value v1,v2;
        v1= e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getValue();
                n2 = i2.getValue();
                switch (op) {
                    case "+" -> {
                        return new IntValue(n1 + n2);
                    }
                    case "-" -> {
                        return new IntValue(n1 - n2);
                    }
                    case "*" -> {
                        return new IntValue(n1 * n2);
                    }
                    case "/" -> {
                        if (n2 == 0) throw new MyException("division by zero");
                        else return new IntValue(n1 / n2);
                    }
                }
                throw new MyException("Unknown operator");
            }else
                throw new MyException("second operand is not an integer");
        }else {
            throw new MyException("first operand is not an integer");
        }
    }
    @Override
    public String toString(){
        return e1.toString() + op + e2.toString();
    }
    @Override
    public Exp copy()
    {
        return new ArithExp(e1.copy(), e2.copy(), op);
    }
}
