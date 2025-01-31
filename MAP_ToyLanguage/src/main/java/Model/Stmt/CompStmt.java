package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.ExeStack.IExeStack;
import Model.PrgState;
import Model.Type.Type;

public class CompStmt implements IStmt {
    private final IStmt first;
    private final IStmt second;
    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public PrgState execute(PrgState state) {
        IExeStack stk = state.getStack();
        stk.push(second);
        stk.push(first);
        return null;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException
    {
        return second.typecheck(first.typecheck(typeEnv));
    }
    @Override
    public String toString(){
        return "("+first.toString()+","+second.toString()+")";
    }
    @Override
    public IStmt copy() {
        return new CompStmt(first.copy(), second.copy());
    }
}
