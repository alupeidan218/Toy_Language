package Controller;
import Model.ADT.Stack.MyIStack;
import Model.Exception.MyException;
import Model.Exception.StackEmptyException;
import Model.ExeStack.ExeStack;
import Model.ExeStack.IExeStack;
import Model.Stmt.IStmt;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.*;
import Model.*;

import java.util.*;
import java.util.stream.Collectors;

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
    public Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap)
    {
        return heap.entrySet().stream().filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Map<Integer, Value> heap) {
        return symTableValues.stream().filter(v -> v instanceof RefValue)
                .map(e -> (RefValue) e)
                .flatMap(value -> {
                    List<Integer> addresses = new ArrayList<Integer>();
                    while(true) {
                        if(value.getAddr() == 0){
                            break;
                        }
                        if(!addresses.contains(value.getAddr())) {
                            addresses.add(value.getAddr());
                        }
                        Value ref_value = heap.get(value.getAddr());
                        if(!(ref_value instanceof RefValue)){
                            break;
                        }
                        value = (RefValue) ref_value;
                    }
                    return addresses.stream();
                }).collect(Collectors.toList());
    }


    public void changePrg(int index) {
        repo.setCrt(index);
    }
    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();
        repo.logPrgStateExec();
        while(!prg.getStack().isEmpty()) {
            prg = oneStep(prg);
            prg.getHeap().setContent(unsafeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getContent().values(), prg.getHeap().getContent()),
                            prg.getHeap().getContent()));
            repo.logPrgStateExec();
        }
        /*if(displayFlag)
        {
            System.out.println(prg);
        }*/
        prg.restart();
    }
}
