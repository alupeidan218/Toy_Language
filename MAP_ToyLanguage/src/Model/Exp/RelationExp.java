package Model.Exp;

import Model.Exception.TypeMismatchException;
import Model.Exception.UnknownOperatorException;
import Model.SymTable.ISymTable;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class RelationExp implements Exp {
    Exp exp1, exp2;
    String op;
    public RelationExp(Exp exp1, Exp exp2, String op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }
    public Value eval(ISymTable tbl){
        Value v1 = exp1.eval(tbl);
        Value v2 = exp2.eval(tbl);
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
    public String toString(){
        return exp1.toString() + " " + op + " " + exp2.toString();
    }
    public Exp copy(){
        return new RelationExp(exp1.copy(), exp2.copy(), op);
    }
}
