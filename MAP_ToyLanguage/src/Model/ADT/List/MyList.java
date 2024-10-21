package Model.ADT.List;

import Model.MyException;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {
    private final ArrayList<T> list;
    public MyList() {
        list = new ArrayList<>();
    }
    @Override
    public void add(T item)
    {
        list.add(item);
    }

    @Override
    public T get(int index) throws MyException {
        if(index < 0 || index >= list.size())
            throw new MyException("Index out of bounds");
        return list.get(index);
    }

    @Override
    public boolean remove(T item)
    {
        return list.remove(item);
    }

    @Override
    public void clear()
    {
        list.clear();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(T elem : list)
            str.append(elem).append("\n");
        return str.toString();
    }
}
