package Queues;

import LinkedList.Linkedlist;
import LinkedList.SingleLinkedlist;

public class QueueL<T> implements Queue<T>{
    Linkedlist<T> front,rear;
    public QueueL(){
        front = new SingleLinkedlist<>();
        rear = front;
    }
    public void enque(T data){
        rear.insertAtEnd(data);
    }
    public T deque(){
        T data = null;
        if(!isEmpty()){
            data = front.head();
            front.deleteFront();
        }else{
            System.out.println("Queue UnderFlow");
        }
        return data;
    }
    @Override
    public T peek(){
        return front.head();
    }
    @Override
    public boolean isEmpty() {
        return front.isEmpty();
    }
    public void printQueue(){
        System.out.println(front);
    }
}
