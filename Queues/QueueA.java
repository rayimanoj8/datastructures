package Queues;

public class QueueA<T> implements Queue<T> {
    T queue[];
    int length;
    int front, rear;

    public QueueA(T[] data) {
        this.queue = data;
        front = 0;
        rear = -1;
        length = data.length;
    }

    @Override
    public void enque(T data) {
        if (rear >= length - 1) {
            System.out.println("Queue OverFlow");
        } else {
            rear++;
            queue[rear] = data;
        }
    }

    @Override
    public T deque() {
        T data = null;
        if (front < 0) {
            System.out.println("Queue UnderFlow");
        } else {
            data = queue[front++];
        }
        return data;
    }
    @Override
    public boolean isEmpty() {
        if (front > rear) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public T peek(){
        return queue[front];
    }
    @Override
    public void printQueue(){
        String res = "[";
        for(int i=front;i<=rear;i++){
            if(i==rear)
                res+=queue[i];
            else
                res+=queue[i]+",";
        }
        System.out.println(res+"]");
    }
}
