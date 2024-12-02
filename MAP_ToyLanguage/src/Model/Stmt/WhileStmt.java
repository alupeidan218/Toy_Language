package Model.Stmt;

import Model.Exception.TypeMismatchException;
import Model.ExeStack.IExeStack;
import Model.Exp.Exp;
import Model.Heap.IHeap;
import Model.PrgState;
import Model.SymTable.ISymTable;
import Model.Value.BoolValue;
import Model.Value.Value;

public class WhileStmt implements IStmt {
    final IStmt stmt;
    final Exp expr;
    public WhileStmt(IStmt stmt, Exp expr) {
        this.stmt = stmt;
        this.expr = expr;
    }
    public PrgState execute(PrgState state)
    {
        ISymTable tbl = state.getSymTable();
        IHeap heap = state.getHeap();
        IExeStack stk = state.getStack();
        Value val = expr.eval(tbl, heap);
        if(!(val instanceof BoolValue))
            throw new TypeMismatchException("Expression must evaluate to a boolean value!");
        if(((BoolValue) val).getValue()){
               stk.push(this);
               stk.push(stmt);
        }
        return null;
    }
    public String toString()
    {
        return "while " + expr.toString() + " do " + stmt.toString();
    }
    public IStmt copy()
    {
        return new WhileStmt(stmt, expr.copy());
    }
}
