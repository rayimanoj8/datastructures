package Heaps;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> implements Heap<T> {
    List<T> Heap;
    int size;
    int maxsize;
    private final int FRONT = 1;
    public MinHeap(){
        size = 0;
        Heap = new ArrayList<>();
        Heap.add(null);
    }
    public MinHeap(int MaxSize) {
        this.maxsize = MaxSize;
        size = 0;
        Heap = new ArrayList<>(MaxSize + 1);
        Heap.add(null);
    }

    @Override
    public void enque(T data) {
        if (size == 0) {
            size++;
            Heap.add(data);
        } else {
            size++;
            Heap.add(data);
            int i = size;
            while (i > 1) {
                int parent = parent(i);
                if (Heap.get(parent).compareTo(Heap.get(i)) > 0) {
                    swap(parent, i);
                    i = parent;
                } else {
                    return;
                }
            }
        }
    }

    private void swap(int i, int j) {
        T temp = Heap.get(i);
        Heap.set(i, Heap.get(j));
        Heap.set(j, temp);
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        return (pos > (size / 2));
    }

    @Override
    public T deque() {
        T popped = Heap.get(FRONT);
        Heap.set(FRONT, Heap.get(size--));
        Heap.remove(size);
        minHeapify(FRONT);
        return popped;
    }

    private void minHeapify(int pos) {
        if (!isLeaf(pos)) {
            int leftChild = leftChild(pos);
            int rightChild = rightChild(pos);
            int swapPos = pos;
    
            // Check if right child is within bounds
            if (rightChild <= size) {
                // Compare left and right child, and find the one with the smaller value
                if (Heap.get(leftChild).compareTo(Heap.get(rightChild)) < 0 || (Heap.get(leftChild).compareTo(Heap.get(rightChild)) == 0 && leftChild < rightChild)) {
                    swapPos = leftChild;
                } else {
                    swapPos = rightChild;
                }
            } else {
                swapPos = leftChild;
            }
    
            // Check if the swap position has a smaller value than the current position
            if (Heap.get(pos).compareTo(Heap.get(swapPos)) > 0) {
                swap(pos, swapPos);
                minHeapify(swapPos);
            }
        }
    }
    public T peek(){
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
                heap += Heap.get(i).toString();
                continue;
            }
            heap += Heap.get(i).toString() + ", ";
        }
        return heap+"]";
    }
    public void printQueue(){
        System.out.println(this.toString());
    }
}