package Model.ADT.Dictionary;

import Model.MyException;

import java.util.HashMap;

public class MyDictionary<U, V> implements MyIDictionary<U, V> {
    private final HashMap<U, V> map;
    public MyDictionary() {
        map = new HashMap<>();
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
    public V remove(U key) throws MyException {
        if(!map.containsKey(key))
            throw new MyException("Key not found");
        return map.remove(key);
    }
    @Override
    public boolean isDefined(U key) {
        return map.containsKey(key);
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(U key : map.keySet()){
            V value = map.get(key);
            s.append(key.toString()).append(": ").append(value).append("\n");
        }
        return s.toString();
    }
    @Override
    public void clear(){
        map.clear();
    }
}
