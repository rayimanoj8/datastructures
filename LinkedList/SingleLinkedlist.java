package LinkedList;

public class SingleLinkedlist<T> implements Linkedlist<T> {
    class Node {
        T data;
        Node next = null;

        Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    Node head;
    public int size;

    public SingleLinkedlist() {
        this.head = null;
    }

    public SingleLinkedlist(T... arr) {
        for (T i : arr) {
            insert(i);
        }
    }

    public void insertAtStart(T data) {
        if (head == null) {
            head = new Node(data);
        } else {
            Node temp = new Node(data);
            temp.next = head;
            head = temp;
        }
        this.size++;
    }

    public void insertAtEnd(T data) {
        if (head == null) {
            head = new Node(data);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(data);
        }
        this.size++;
    }

    public void insert(T data) {
        insert(data, size + 1);
    }

    public void insert(T data, int index) {
        if (head == null) {
            head = new Node(data);
        } else {
            if (index == 0) {
                insertAtStart(data);
                return;
            } else if (index >= size) {
                insertAtEnd(data);
                return;
            }
            Node temp = head;
            int pos = 1;
            while (pos < index) {
                temp = temp.next;
                pos++;
            }
            Node newNode = new Node(data);
            newNode.next = temp.next;
            temp.next = newNode;
        }
        this.size++;
    }

    public void merge(SingleLinkedlist<T> l) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = l.head;
        // free(l.head);
    }

    public void deleteFront() {
        if (head == null) {
            return;
        }
        head = head.next;
        size--;
    }

    public void deleteEnd() {
        if (head == null || size == 1) {
            head = null;
            return;
        }
        Node temp = head;
        while (temp.next.next != null)
            temp = temp.next;
        temp.next = null;
        size--;
    }

    public void delete(T data) throws Exception {
        if (head.data == data) {
            head = head.next;
            size--;
            return;
        }
        Node temp = head, prev = null;
        while (temp != null) {
            if (temp.data == data) {
                prev.next = temp.next;
                size--;
                return;
            } else {
                prev = temp;
                temp = temp.next;
            }
        }
        throw new Exception("Data Is Not Present In the LinkedList");
    }

    public void reverse() {
        if (size < 2)
            return;
        Node curr = head;
        Node prev = null, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
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

    public T head() {
        return this.head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        System.out.print(this.toString());
    }
}