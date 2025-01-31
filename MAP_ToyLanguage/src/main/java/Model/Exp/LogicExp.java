package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Exception.TypeException;
import Model.Exception.TypeMismatchException;
import Model.Exception.UnknownOperatorException;
import Model.Heap.IHeap;
import Model.SymTable.ISymTable;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

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
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException
    {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if(typ1.equals(new BoolType()))
        {
            if(typ2.equals(new BoolType()))
            {
                return new BoolType();
            } else {
                throw new TypeMismatchException("Second operand is not a boolean");
            }
        } else {
            throw new TypeMismatchException("First operand is not a boolean");
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
