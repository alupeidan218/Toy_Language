package Model.ADT.Dictionary;

import Model.Exception.KeyNotFoundException;
import Model.Exception.MyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyDictionary<U, V> implements MyIDictionary<U, V> {
    private final ConcurrentHashMap<U, V> map;
    public MyDictionary() {
        map = new ConcurrentHashMap<>();
    }
    @Override
    public V lookup(U key) {
        return map.get(key);
    }
    @Override
    public void put(U key, V value) {
        map.put(key, value);
    }
    @Override
    public void remove(U key) throws MyException {
        if(!map.containsKey(key))
            throw new KeyNotFoundException("Key not found");
        map.remove(key);
    }
    @Override
    public boolean isDefined(U key) {
        return map.containsKey(key);
    }

    @Override
    public List<U> getKeys() {
        return new ArrayList<>(map.keySet());
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(U key : map.keySet()){
            V value = map.get(key);
            s.append(key.toString()).append("-> ").append(value).append("\n");
        }
        return s.toString();
    }
    @Override
    public void clear(){
        map.clear();
    }
    @Override
    public Map<U, V> toMap() {
        return map;
    }
    @Override
    public MyIDictionary<U, V> copy() {
        MyIDictionary<U, V> copy = new MyDictionary<>();
        for(U key : map.keySet()){
            copy.put(key, map.get(key));
        }
        return copy;
    }
}
