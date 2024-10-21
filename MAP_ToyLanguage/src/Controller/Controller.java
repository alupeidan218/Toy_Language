package Controller;
import Model.ADT.Stack.MyIStack;
import Model.Stmt.IStmt;
import Repository.*;
import Model.*;
public class Controller {
    final IRepo repo;
    public Controller(IRepo repo) {
        this.repo = repo;
    }
    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStack();
        if(stk.isEmpty()) throw new MyException("PrgState stack is empty :(");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }
    public void changePrg(int index) {
        repo.setCrt(index);
    }
    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();
        System.out.println(prg);
        while(!prg.getStack().isEmpty()) {
            PrgState step = oneStep(prg);
            System.out.println(step);
        }
        prg.restart();
    }
}
