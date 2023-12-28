package Heaps;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> implements Heap<T>{
    List<T> Heap;
    int size = 0;
    private final int FRONT = 1;
    public MaxHeap(int N){
        Heap = new ArrayList<>(N);
        Heap.add(null);
    }
    @Override
    public void enque(T data){
        if(size==0){
            Heap.add(data);
            size++;
        }else{
            size++;
            Heap.add(data);
            int i = size;
            while(i>1){
                int parent = parent(i);
                if(Heap.get(parent).compareTo(Heap.get(i))<0){
                    swap(i,parent);
                    i = parent;
                }else
                    return ;
            }
        }
    }
    private void swap(int i,int j){
        T temp = Heap.get(i);
        Heap.set(i,Heap.get(j));
        Heap.set(j,temp);
    }
    int parent(int i){
        return i/2;
    }
    @Override
    public T deque() {
        T popped = Heap.get(FRONT);
        swap(FRONT,size--);
        maxHeapify(FRONT);
        return popped;
    }
    private void maxHeapify(int pos) {
        int left = leftChild(pos);
        int right = rightChild(pos);
        int largest = pos;
        if(left<=size && Heap.get(left).compareTo(Heap.get(largest)) > 0)
            largest = left;
        if(right<=size && Heap.get(right).compareTo(Heap.get(largest)) > 0)
            largest = right;
        if(largest!=pos){
            swap(pos, largest);
            maxHeapify(largest);
        }
    }
    private int leftChild(int pos){
        return 2*pos;
    }
    private int rightChild(int pos) {
        return 2*pos+1;
    }
    @Override
    public T peek() {
        return Heap.get(FRONT);
    }
    public boolean isEmpty(){
        return size==0;
    }
    @Override
    public String toString() {
        String heap = "[";
        for (int i = 1; i <= size; i++) {
            if (i == size) {
                heap += Heap.get(i);
                continue;
            }
            heap += Heap.get(i) + ", ";
        }
        return heap+"]";
    }
    @Override
    public void printQueue() {
        System.out.println(this.toString());
    }
    
}