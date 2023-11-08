//package edu.ser222.m02_02;
package Module_6;

/**
 * Implements various divide and conquer algorithms.
 *
 * Last updated 4/2/2022.
 *
 * Completion time: 10 hours
 *
 * @author Angel Chiquito, Acuna, Sedgewick and Wayne
 * @verison 11/4/23
 */
import java.util.Random;

public class CompletedMerging implements MergingAlgorithms {

    @Override
    public <T extends Comparable> Queue<T> mergeQueues(Queue<T> q1, Queue<T> q2) {
        Queue<T> mergedQueue = new ListQueue<>();
        Queue<T> sortedmergedQueue = new ListQueue<>();
        // queue up elements
        while (!q1.isEmpty()) {
            mergedQueue.enqueue(q1.dequeue());
        }

        while (!q2.isEmpty()) {
            mergedQueue.enqueue(q2.dequeue());
        }
        // make arr, copy over elements, sort, then back to Queue<T>
        Comparable[] sortedQueue = new Comparable[mergedQueue.size()];
        int i = 0;
        while (!mergedQueue.isEmpty()) {
            T element = mergedQueue.dequeue();
            if (element != null) {
                sortedQueue[i] = element;
                i++;
            }
        }
        sort(sortedQueue);
        for (Comparable element : sortedQueue) {
            sortedmergedQueue.enqueue((T) element);
        }
        return sortedmergedQueue;
    }

    @Override
    public void sort(Comparable[] a) {
        Comparable[] sortedArray = mergesort(a);
        System.arraycopy(sortedArray, 0, a, 0, a.length);
    }

    @Override
    public Comparable[] mergesort(Comparable[] a) {
        if (a.length <= 1) {
            return a;
        }
        int mid = a.length / 2;
        Comparable[] left = new Comparable[mid];
        Comparable[] right = new Comparable[a.length - mid];
        System.arraycopy(a, 0, left, 0, mid);
        System.arraycopy(a, mid, right, 0, a.length - mid);
        left = mergesort(left);
        right = mergesort(right);

        return merge(left, right);
    }

    @Override
    public Comparable[] merge(Comparable[] a, Comparable[] b) {
        Comparable[] result = new Comparable[a.length + b.length]; // new arr with size of both arr
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i].compareTo(b[j]) <= 0) { // compare
                result[k] = a[i];
                i++;
            } else {
                result[k] = b[j];
                j++;
            }
            k++;
        }
        while (i < a.length) {
            result[k] = a[i];
            i++;
            k++;
        }
        while (j < b.length) {
            result[k] = b[j];
            j++;
            k++;
        }
        return result;
    }

    @Override
    public void shuffle(Object[] a) { //!Homework bit
        Random rand = new Random();
        int n = a.length;
        Object[] shuffled = new Object[n]; // new arr

        for (int i = 0; i < n; i++) {
            int j = rand.nextInt(i + 1); // using random

            if (j != i) {
                shuffled[i] = shuffled[j];
            }

            shuffled[j] = a[i];
        }
        System.arraycopy(shuffled, 0, a, 0, n); // move over to a
    }

    /**
     * entry point for sample output.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue<String> q1 = new ListQueue<>();
        q1.enqueue("E");
        q1.enqueue("L");
        q1.enqueue("O");
        q1.enqueue("R");
        q1.enqueue("T");
        Queue<String> q2 = new ListQueue<>();
        q2.enqueue("A");
        q2.enqueue("E");
        q2.enqueue("M");
        q2.enqueue("P");
        q2.enqueue("S");
        q2.enqueue("X");
        Queue<Integer> q3 = new ListQueue<>();
        q3.enqueue(5);
        q3.enqueue(12);
        q3.enqueue(15);
        q3.enqueue(17);
        q3.enqueue(20);
        Queue<Integer> q4 = new ListQueue<>();
        q4.enqueue(1);
        q4.enqueue(4);
        q4.enqueue(12);
        q4.enqueue(13);
        q4.enqueue(16);
        q4.enqueue(18);

        MergingAlgorithms ma = new CompletedMerging();

        // Q1 - sample test cases
        Queue merged1 = ma.mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue merged2 = ma.mergeQueues(q3, q4);
        System.out.println(merged2.toString());

        // Q2 - sample test cases
        String[] a = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
        ma.sort(a);
        assert isSorted(a);
        show(a);

        // Q3 - sample test cases
        String[] b = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
        ma.shuffle(b);
        show(b);

        ma.shuffle(b);
        show(b);
    }

    // below are utilities functions, please do not change them.

    // sorting helper from text
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // sorting helper from text
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }

    // sorting helper from text
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1]))
                return false;

        return true;
    }
}