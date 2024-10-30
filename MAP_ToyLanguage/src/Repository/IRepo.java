package Repository;
import Model.Exception.MyException;
import Model.PrgState;
public interface IRepo {
    void add(PrgState element);
    boolean remove(PrgState element);
    int size();
    boolean isEmpty();
    PrgState getCrtPrg();
    void logPrgStateExec() throws MyException;
    void setCrt(int index);
}
