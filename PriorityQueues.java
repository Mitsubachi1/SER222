import java.util.PriorityQueue;

public class PriorityQueues {
    

public class StackUsingPriorityQueue<T> {
    private PriorityQueue<StackNode<T>> priorityQueue;
    private int priorityCounter;

    public StackUsingPriorityQueue() {
        priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(b.priority, a.priority)); // Reverse order for max priority on top
        priorityCounter = 0;
    }

    public void push(T item) {
        priorityQueue.offer(new StackNode<>(item, priorityCounter--));
    }

    public T pop() {
        if (!isEmpty()) {
            return priorityQueue.poll().data;
        }
        return null;
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    private static class StackNode<T> {
        T data;
        int priority;

        public StackNode(T data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }
}

public static void main (String[] args){
    PriorityQueues priorityQueues = new PriorityQueues();
    StackUsingPriorityQueue<Integer> stack = priorityQueues.new StackUsingPriorityQueue<>();
    stack.push(3);
    stack.push(9);
    stack.push(1);
    System.out.print(stack.pop());
    System.out.print(stack.pop());
    
}

}
