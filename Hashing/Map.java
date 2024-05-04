package Hashing;

import java.util.List;

class Node <K,V>{
    K key;
    V value;
    Node(K key,V value){
        this.key = key;
        this.value = value;
    }
    public String toString(){
        return key + " : " + value;
    }
}
public interface Map <K,V>{
    void put(K key,V value);
    V get(K key);
    boolean remove(K key);
    boolean containsKey(K key);
    int size();
    List<K> keys();
    List<V> values();
    String toString();
}
