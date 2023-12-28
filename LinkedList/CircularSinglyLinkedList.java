package LinkedList;

public class CircularSinglyLinkedList<T> implements Linkedlist<T> {
    class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    @Override
    public void insertAtStart(T data) {
        Node newNode = new Node(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            tail.next = head;
        } else {
            Node temp = head;
            newNode.next = temp;
            head = newNode;
            tail.next = head;
        }
        size++;
    }

    @Override
    public void insertAtEnd(T data) {
        if (size == 0) {
            insertAtStart(data);
        } else {
            Node newNode = new Node(data);
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
            size++;
        }
    }
    public void insert(T data){
        insertAtEnd(data);
    }
    @Override
    public void insert(T data, int index) {
        if (index > size || index < 0) {
            System.out.println("Invalid index");
        } else if (index == 0) {
            insertAtStart(data);
        } else if (index == size) {
            insertAtEnd(data);
        } else {
            Node newNode = new Node(data);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    @Override
    public void deleteFront() {
        if (size == 0) {
            System.out.println("List is empty");
        } else {
            head = head.next;
            tail.next = head;
            size--;
        }
    }

    @Override
    public void deleteEnd() {
        if (size == 0) {
            System.out.println("List is empty");
        } else if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else {
            Node current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            tail = current;
            tail.next = head;
            size--;
        }
    }

    @Override
    public void delete(T data) {
        if (size == 0) {
            System.out.println("List is empty");
        } else if (head.data.equals(data)) {
            deleteFront();
        } else if (tail.data.equals(data)) {
            deleteEnd();
        } else {
            Node prev = null;
            Node temp = head;
            while(temp!=tail){
                if(temp.data == data){
                    prev.next = temp.next;
                }
                prev = temp;
                temp=temp.next;
            }
      }
   }

   @Override
   public void reverse() {
       // Implement this method if needed
   }

   @Override
   public String toString() {
       StringBuilder result = new StringBuilder("[");
       Node current = head;

       for (int i = 0; i < size; i++) {
           result.append(current.data);
           if (i < size - 1) {
               result.append(", ");
           }
           current = current.next;
       }

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
    while(temp!=tail){
        System.out.print("| "+temp.data+" |");
        temp=temp.next;
    }
    System.out.print("| "+temp.data+" |");
}
}