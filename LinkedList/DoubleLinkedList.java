package LinkedList;


public class DoubleLinkedList<T> implements Linkedlist<T>{
    class Node{
        T data;
        Node next;
        Node prev;
        Node(T data){
            this.data = data;
        }
        public String toString(){
            return data.toString();
        }
    }
    Node head,tail;
    int size;
    public DoubleLinkedList() {
        this.head = null;
    }

    // Implement the methods from the LinkedList interface
    @Override
    public void insertAtStart(T data) {
        if(head == null){
            head = new Node(data);
            tail = head;
        }else{
            Node temp = new Node(data);
            temp.data = data;
            // private int size;
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
    }

    @Override
    public void insertAtEnd(T data) {
        if(head==null){
            head = new Node(data);
            tail = head;
        }else{
            tail.next = new Node(data);
            tail.next.prev = tail;
            tail = tail.next;
        }
        this.size++;
    }

    @Override
    public void insert(T data) {
        insertAtEnd(data);
    }

    @Override
    public void insert(T data, int index) {
        if(head==null){
            head = new Node(data);
        }else{
            if(index==0){
                insertAtStart(data);
                return;
            }else if(index>=size){
                insertAtEnd(data);
                return;
            }
            Node temp = head;
            int pos = 1;
            while(pos<index){
                temp = temp.next;
                pos++;
            }
            Node newNode = new Node(data);
            temp.next.prev = newNode;
            newNode.next = temp.next;
            temp.next = newNode;
            newNode.prev = temp;
        }
        this.size++;
    }
    public void merge(DoubleLinkedList<T> l) {
        if(tail!=null){
            tail.next = l.head;
            l.head.prev = tail;
            return ;
        }head = tail = l.head;
    }

    @Override
    public void deleteFront() {
        if(head==null || head == tail){
            head = tail = null;
            return;
        }else{
            head = head.next;
            head.prev = null;
        }
    }

    @Override
    public void deleteEnd() {
        if(head==null || head==tail){
            head = tail = null;
            return;
        }else{
            tail = tail.prev;
            tail.next = null;
        }
    }

    @Override
    public void delete(T data) {
        if(head.data == data){
            head = head.next;
            head.prev=null;
            return;
        }
        Node temp = head,prev = null;
        while(temp!=null){
            if(temp.data == data){
                prev.next = temp.next;
                if(temp.next==null){
                    tail = tail.prev;
                }
                if(temp.next!=null)
                    temp.next.prev = prev;
                return ;
            }else{
                prev = temp;
                temp = temp.next;
            }
        }
    }

    @Override
    public void reverse() {
        Node temp = head;
        while(temp!=null){
            Node t = temp.prev;
            temp.prev = temp.next;
            temp.next = t;
            temp = temp.prev;
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder result = new StringBuilder();

        // Start with an opening bracket
        result.append("[");

        while (current != null) {
            result.append(current.data);
            if (current.next != null) {
                // Add a comma and space if there are more elements
                result.append(", ");
            }
            current = current.next;
        }

        // End with a closing bracket
        result.append("]");

        return result.toString();
    }
    @Override
public T head() {
    return this.head.data;
}
public boolean isEmpty(){
    return head==null;
}
public void printList(){
    Node temp = head;
    while(temp!=null){
        System.out.print("| "+temp.data+" |");
        temp=temp.next;
    }
}
}
