package Model.ADT.List;

import Model.Exception.MyException;
import Model.Exception.OutOfBoundsException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@SuppressWarnings("unchecked")
public class MyList<T> implements MyIList<T> {
    private final BlockingQueue<T> list; // Use a concurrent implementation
    public MyList() {
        list = new LinkedBlockingQueue<>();
    }
    @Override
    public void add(T item)
    {
        list.add(item);
    }

    @Override
    public T get(int index) throws MyException {
        if(index < 0 || index >= list.size())
            throw new OutOfBoundsException("Index out of bounds");
        Object[] array = list.toArray();
        return (T)array[index];
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
    public List<T> getList() {
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(T elem : list)
            str.append(elem).append("\n");
        return str.toString();
    }
}
