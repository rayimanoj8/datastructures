package Hashing;

import java.util.LinkedList;
public class HashSet<K> implements Set<K>{
    final int BUCKET_THRESHHOLD = 4;
    LinkedList<K> buckets[];
    int N;
    @SuppressWarnings("unchecked")
    public HashSet(){
        this.N = 4;
        buckets = new LinkedList[N];
        for(int i=0;i<N;i++)
            buckets[i] = new LinkedList<K>();
    }
    // hash function
    private int hash(K key){
        return Math.abs(key.hashCode()) % N;
    }
    // add
    public boolean add(K key){
        int ind = hash(key);
        if(contains(key))
            return false;
        buckets[ind].add(key);
        if(buckets[ind].size() > BUCKET_THRESHHOLD)
            rehash();
        return true;
    }
    // remove
    public boolean remove(K key){
        int ind = hash(key);
        LinkedList<K> ll = buckets[ind];
        return ll.remove(key);
    }
    // contains
    public boolean contains(K key){
        int ind = hash(key);
        var ll = buckets[ind];
        for(K i:ll)
        if(i.equals(key))
        return true;
        return false;
    }
    // size
    public int size(){
        int cnt = 0;
        for(LinkedList<K> node:buckets)
        cnt+=node.size();
        return cnt;
    }
    // rehash
    @SuppressWarnings("unchecked")
    private void rehash(){
        LinkedList<K> old[] = buckets;
        N = N*2;
        buckets = new LinkedList[N];
        for(int i=0;i<N;i++)
            buckets[i] = new LinkedList<K>();
        for(LinkedList<K> ll:old){
            for(K key:ll)
                add(key);
        }
    }
    // toString
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("{");
        for(var ll:buckets){
            for(K key:ll){
                str.append(key+",");
            }
        }str.append("}");
        return str.toString();
    }
}
