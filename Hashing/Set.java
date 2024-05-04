package Hashing;

public interface Set <K>{
    boolean add(K key);
    boolean remove(K key);
    boolean contains(K key);
    int size();
    String toString();
}
