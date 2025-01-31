package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Exception.TypeMismatchException;
import Model.Exception.UnknownOperatorException;
import Model.Heap.IHeap;
import Model.SymTable.ISymTable;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class RelationExp implements Exp {
    final Exp exp1;
    final Exp exp2;
    final String op;
    public RelationExp(Exp exp1, Exp exp2, String op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }
    public Value eval(ISymTable tbl, IHeap heap){
        Value v1 = exp1.eval(tbl, heap);
        Value v2 = exp2.eval(tbl, heap);
        if(!v1.getType().equals(new IntType()) || !v2.getType().equals(new IntType())){
            throw new TypeMismatchException("Expressions must be integers");
        }
        int n1,n2;
        n1 = ((IntValue)v1).getValue();
        n2 = ((IntValue)v2).getValue();
        return switch (op) {
            case "<" -> new BoolValue(n1 < n2);
            case "<=" -> new BoolValue(n1 <= n2);
            case "==" -> new BoolValue(n1 == n2);
            case "!=" -> new BoolValue(n1 != n2);
            case ">" -> new BoolValue(n1 > n2);
            case ">=" -> new BoolValue(n1 >= n2);
            default -> throw new UnknownOperatorException("Unknown relational operator");
        };
    }
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException
    {
        Type typ1, typ2;
        typ1 = exp1.typecheck(typeEnv);
        typ2 = exp2.typecheck(typeEnv);
        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())){
                return new BoolType();
            } else {
                throw new MyException("second operand is not an integer");
            }
        } else {
            throw new MyException("first operand is not an integer");
        }
    }
    public String toString(){
        return exp1.toString() + " " + op + " " + exp2.toString();
    }
    public Exp copy(){
        return new RelationExp(exp1.copy(), exp2.copy(), op);
    }
}
