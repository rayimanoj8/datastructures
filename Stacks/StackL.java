package Stacks;
import LinkedList.Linkedlist;
import LinkedList.SingleLinkedlist;
public class StackL<T>{
    Linkedlist<T> top;
    int length;
    public StackL(){
        top = new SingleLinkedlist<>();
        length = 0;
    }
    public void push(T data){
        length++;
        top.insertAtStart(data);
    }
    public T pop(){
        T data = top.head();
        if(!isEmpty()){
            length--;
            top.deleteFront();
        }else{
            System.out.print("Stack UnderFlow");
        }
        return data;
    }
    public T peek(){
        return top.head();
    }
    public boolean isEmpty() {
        return top.isEmpty();
    }
    public void printStack(){
        top.printList();
        System.out.print("\n");
    }
}
