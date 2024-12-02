package Repository;
import Model.Exception.MyException;
import Model.PrgState;

import java.util.List;

public interface IRepo {
    void add(PrgState element);
    boolean remove(PrgState element);
    int size();
    boolean isEmpty();
    void logPrgStateExec(PrgState state) throws MyException;
    void setCrt(int index);
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> list);
}
