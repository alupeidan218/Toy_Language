package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Exp.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Type.BoolType;
import Model.Value.*;

public class IfStmt implements IStmt {
    private final Exp exp;
    private final IStmt thenS;
    private final IStmt elseS;
    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value cond = exp.eval(symTable);
        if(cond.getType() instanceof BoolType) {
            if(((BoolValue) cond).getValue())
                stk.push(thenS);
            else
                stk.push(elseS);
        } else {
            throw new MyException("Conditional expression is not a boolean");
        }
        return state;
    }
    @Override
    public String toString() {return "(IF("+exp.toString()+") THEN("+thenS.toString()+
            ") ELSE("+elseS.toString()+"))";}
    @Override
    public IStmt copy() {
        return new IfStmt(exp.copy(), thenS.copy(), elseS.copy());
    }
}
