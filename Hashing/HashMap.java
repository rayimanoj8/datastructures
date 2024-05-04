package Hashing;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class HashMap <K,V> implements Map<K,V>{
    @SuppressWarnings("hiding")
    final int BUCKET_THRESHHOLD = 4;
    LinkedList<Node<K,V>> buckets[];
    int N; // size of bucket;
    @SuppressWarnings("unchecked")
    public HashMap(){
        this.N = 4;
        this.buckets = new LinkedList[N];
        for(int i=0;i<N;i++)
            this.buckets[i] = new LinkedList<Node<K,V>>(); 
    }
    private int hashFun(K key){
        return Math.abs(key.hashCode()) % N;
    }
    Node<K,V> search(K key,LinkedList<Node<K,V>> ll){
        for(Node<K,V> node : ll){
            if(node.key.equals(key))
                return node;
        }
        return null;
    }
    // put
    public void put(K key,V value){
        int ind = hashFun(key);
        Node<K,V> node = search(key,buckets[ind]);
        if(node == null)
            buckets[ind].add(new Node<K,V>(key,value));
        else
            node.value = value;
        if(buckets[ind].size() > BUCKET_THRESHHOLD)
            rehash();
    }
    // get
    public V get(K key){
        int ind = hashFun(key);
        Node<K,V> node = search(key, buckets[ind]);
        if(node == null)
            return null;
        return node.value;
    }
    // remove
    public boolean remove(K key){
        if(containsKey(key)){
            int ind = hashFun(key);
            LinkedList<Node<K,V>> ll = buckets[ind];
            ll.remove(search(key, ll));
            return true;
        }
        return false;
    }
    // rehash
    @SuppressWarnings("unchecked")
    private void rehash(){
        LinkedList<Node<K,V>> old[] = buckets;
        buckets = new LinkedList[N*2];
        this.N = this.N*2;
        for(int i=0;i<N;i++){
            buckets[i] = new LinkedList<Node<K,V>>();
        }
        for(LinkedList<Node<K,V>> ll : old){
            for(Node<K,V> node: ll){
                put(node.key,node.value);
            }
        }
    }
    // contains
    public boolean containsKey(K key){
        int ind = hashFun(key);
        LinkedList<Node<K,V>> nodes = buckets[ind];
        for(Node<K,V> node: nodes){
            if(node.key == key)
            return true;
        }
        return false;
    }
    // size
    public int size(){
        int cnt = 0;
        for(LinkedList<Node<K,V>> ll : buckets)
        cnt+=ll.size();
        return cnt;
    }
    // keys
    public List<K> keys(){
        List<K> list = new ArrayList<>();
        for(LinkedList<Node<K,V>> ll : buckets)
        for(Node<K,V> node :ll)
        list.add(node.key);
        return list;
    }
    // values
    public List<V> values(){
        List<V> list = new ArrayList<>();
        for(LinkedList<Node<K,V>> ll : buckets)
        for(Node<K,V> node :ll)
        list.add(node.value);
        return list;
    }
    // to string
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("{ ");
        for(LinkedList<Node<K,V>> l : buckets){
            if(l.toString().equals("[]")) continue;
            String obj = l.toString();
            obj = obj.substring(1,obj.length()-1);
            str.append(obj+", ");
        }str.append("}");
        return str.toString();
    }
}
