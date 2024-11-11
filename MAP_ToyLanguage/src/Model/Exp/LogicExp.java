package Model.Exp;

import Model.Exception.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.TypeException;
import Model.Exception.UnknownOperatorException;
import Model.SymTable.ISymTable;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.Value;
import Model.Heap.*;

public class LogicExp implements Exp {
    private final Exp e1;
    private final Exp e2;
    private final String op;
    public LogicExp(Exp e1, Exp e2, String op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }
    @Override
    public Value eval(ISymTable tbl, IHeap heap) throws MyException
    {
        Value v1,v2;
        v1= e1.eval(tbl, heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue)v1;
                BoolValue b2 = (BoolValue)v2;
                boolean n1,n2;
                n1 = b1.getValue();
                n2 = b2.getValue();
                if(op.equals("and")) return new BoolValue(n1 && n2);
                if(op.equals("or")) return new BoolValue(n1 || n2);
                throw new UnknownOperatorException("Unknown operator");
            }else
                throw new TypeException("second operand is not a boolean");
        }else {
            throw new TypeException("first operand is not a boolean");
        }
    }
    @Override
    public String toString()
    {
        return e1.toString() + " " + op + " " + e2.toString();
    }
    @Override
    public Exp copy()
    {
        return new LogicExp(e1.copy(), e2.copy(), op);
    }
}
