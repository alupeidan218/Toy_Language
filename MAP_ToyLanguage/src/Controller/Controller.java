package Controller;
import Model.Exception.InvalidAddressException;
import Model.Exception.MyException;
import Model.Heap.IHeap;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.*;
import Model.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller {
    final IRepo repo;
    private boolean displayFlag = false;
    private ExecutorService executor;
    public Controller(IRepo repo) {
        this.repo = repo;
    }
    public void switchDisplayFlag() {
        this.displayFlag = !this.displayFlag;
    }
    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public void conservativeGarbageCollector(List<PrgState> states)
    {
        if(states.isEmpty())
            return;
        IHeap heap = states.getFirst().getHeap();
        List<Integer> activeAddresses = states.stream().flatMap(e ->
                getAddrFromSymTable(e.getSymTable().getContent().values(), e.getHeap().getContent()).stream()).toList();
        heap.getContent().keySet().stream().filter(e -> !activeAddresses.contains(e)).forEach(e-> {
                try {
                    heap.deallocate(e);
                } catch (InvalidAddressException ex){
                    throw new RuntimeException(ex);
                }
         }
        );
    }
    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap)
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

    public void oneStepForAllPrg(List<PrgState> prgList) {
        prgList.forEach(repo::logPrgStateExec);
        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>)(
                p::oneStep
                )).collect(Collectors.toList());
        try{
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> { try {return future.get();}
                    catch(InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    }).filter(Objects::nonNull)
                    .toList();

            prgList.addAll(newPrgList);
            if(displayFlag) {
                repo.getPrgList().forEach(prg -> System.out.println(prg.toString() + "\n"));
            }
            conservativeGarbageCollector(repo.getPrgList());
            prgList.forEach(repo::logPrgStateExec);
            repo.setPrgList(prgList);
        } catch(InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void allStep() throws MyException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while(!prgList.isEmpty()){
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);
    }
}
