import java.util.PriorityQueue;


    public class QueueUsingPriorityQueue<T> {
    private PriorityQueue<QueueNode<T>> priorityQueue;
    private int priorityCounter;

    public QueueUsingPriorityQueue() {
        priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.priority, b.priority));
        priorityCounter = 0;
    }

    public void enqueue(T item) {
        priorityQueue.offer(new QueueNode<>(item, priorityCounter--));
    }

    public T dequeue() {
        if (!isEmpty()) {
            return priorityQueue.poll().data;
        }
        return null;
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    private static class QueueNode<T> {
        T data;
        int priority;

        public QueueNode(T data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    public static void main(String[] args) {
        QueueUsingPriorityQueue<Integer> bong = new QueueUsingPriorityQueue<>();
        bong.enqueue(3);
        bong.enqueue(5);
        bong.enqueue(1);
        bong.enqueue(10);
        System.out.print(bong.dequeue());
        System.out.print(bong.dequeue());
    }
}
