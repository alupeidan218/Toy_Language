package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Exception.TypeMismatchException;
import Model.ExeStack.IExeStack;
import Model.Exp.Exp;
import Model.Heap.IHeap;
import Model.PrgState;
import Model.SymTable.ISymTable;
import Model.Type.BoolType;
import Model.Type.Type;
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
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException
    {
        Type typexp = expr.typecheck(typeEnv);
        if(typexp.equals(new BoolType()))
        {
            stmt.typecheck(typeEnv.copy());
            return typeEnv;
        } else {
            throw new MyException("The condition of WHILE does not have the type bool");
        }
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
