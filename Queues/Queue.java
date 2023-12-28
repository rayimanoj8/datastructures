package Queues;

public interface Queue<T> {
    void enque(T data);
    T deque();
    void printQueue();
    boolean isEmpty();
    T peek();
}