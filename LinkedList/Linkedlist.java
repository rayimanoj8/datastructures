package LinkedList;
public interface Linkedlist<T>{
    void insertAtStart(T data);
    void insertAtEnd(T data);
    void insert(T data);
    void insert(T data, int index);
    void deleteFront();
    void deleteEnd();
    void delete(T data) throws Exception;
    void reverse();
    T head();
    boolean isEmpty();
    void printList();
}
