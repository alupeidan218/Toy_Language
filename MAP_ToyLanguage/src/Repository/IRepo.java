package Repository;
import Model.PrgState;
public interface IRepo {
    void add(PrgState element);
    boolean remove(PrgState element);
    int size();
    boolean isEmpty();
    PrgState getCrtPrg();
    void setCrt(int index);
}
