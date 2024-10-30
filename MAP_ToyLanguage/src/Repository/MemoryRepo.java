package Repository;
import java.util.ArrayList;
import Model.PrgState;

public class MemoryRepo implements IRepo {
    private final ArrayList<PrgState> elems;
    int current = 0;
    public MemoryRepo() {
        elems = new ArrayList<>();

    }
    @Override
    public void add(PrgState elem) {
        elems.add(elem);
    }
    @Override
    public boolean remove(PrgState elem) {
        return elems.remove(elem);
    }
    @Override
    public boolean isEmpty()
    {
        return elems.isEmpty();
    }
    @Override
    public int size(){
        return elems.size();
    }
    @Override
    public PrgState getCrtPrg()
    {
        return elems.get(current);
    }
    @Override
    public void setCrt(int index){
        current = index;
    }
}
