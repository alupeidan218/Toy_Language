package Controller;
import Model.ADT.Stack.MyIStack;
import Model.Exception.MyException;
import Model.Exception.StackEmptyException;
import Model.ExeStack.ExeStack;
import Model.ExeStack.IExeStack;
import Model.Stmt.IStmt;
import Repository.*;
import Model.*;
public class Controller {
    final IRepo repo;
    private boolean displayFlag = false;
    public Controller(IRepo repo) {
        this.repo = repo;
    }
    public void switchDisplayFlag() {
        this.displayFlag = !this.displayFlag;
    }
    public PrgState oneStep(PrgState state) throws MyException {
        if(displayFlag) {
            System.out.println(state);
        }
        IExeStack stk = state.getStack();
        if(stk.isEmpty()) throw new StackEmptyException("PrgState stack is empty :(");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }
    public void changePrg(int index) {
        repo.setCrt(index);
    }
    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();
        repo.logPrgStateExec();
        while(!prg.getStack().isEmpty()) {
            prg = oneStep(prg);
            repo.logPrgStateExec();
        }
        if(displayFlag)
        {
            System.out.println(prg);
        }
        prg.restart();
    }
}
