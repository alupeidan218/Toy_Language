package Model.Stmt;

import Model.Exception.TypeException;
import Model.ExeStack.IExeStack;
import Model.Exp.Exp;
import Model.Exception.MyException;
import Model.PrgState;
import Model.SymTable.ISymTable;
import Model.Type.BoolType;
import Model.Value.*;
import Model.Heap.*;

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
        IExeStack stk = state.getStack();
        ISymTable symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        Value cond = exp.eval(symTable, heap);
        if(cond.getType() instanceof BoolType) {
            if(((BoolValue) cond).getValue())
                stk.push(thenS);
            else
                stk.push(elseS);
        } else {
            throw new TypeException("Conditional expression is not a boolean");
        }
        return null;
    }
    @Override
    public String toString() {return "(IF("+exp.toString()+") THEN("+thenS.toString()+
            ") ELSE("+elseS.toString()+"))";}
    @Override
    public IStmt copy() {
        return new IfStmt(exp.copy(), thenS.copy(), elseS.copy());
    }
}
