//package edu.ser222.m01_03;
package Module_3; //local storage

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CompletedList represents an implementation of a list.
 *
 * @author Angel Chiquito, Acuna
 * @version 10/21/23
 */

public class CompletedList<T> implements ListADT<T>, Iterable<T> {

    protected int count;
    protected int modChange;
    protected DoubleLinearNode<T> head, tail;

    public class DoubleLinearNode<T> {
        private T elementData;
        private DoubleLinearNode<T> next;
        private DoubleLinearNode<T> previous;

        public DoubleLinearNode(T element) { // constructor
            this.elementData = element;
            this.next = null;
            this.previous = null;
        }

        // get set
        public T getData() {
            return elementData;
        }

        public DoubleLinearNode<T> getNext() {
            return next;
        }

        public DoubleLinearNode<T> getPrevious() {
            return previous;
        }

        public void setNext(DoubleLinearNode<T> node) {
            next = node;
        }

        public void setPrevious(DoubleLinearNode<T> node) {
            previous = node;
        }
    }

    public T removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = head.getData();
        if (head == tail) { // handle one element case
            head = null;
            tail = null;
            count--;
            modChange++;
            return element;
        }
        head = head.getNext();
        count--;
        modChange++;
        return element;
    }

    public T removeLast() throws NoSuchElementException {
        if (isEmpty()) { // on empty
            throw new NoSuchElementException();
        }
        T element = tail.getData();
        DoubleLinearNode<T> newTail = head;
        if (head == tail) { // handle one element case
            head = null;
            tail = null;

        }
        while (newTail.getNext() != tail) {
            newTail = newTail.getNext();
        }
        newTail.setNext(null);
        tail = newTail;
        count--;
        modChange++;
        return element;
    }

    public T remove(T element) {
        if (!contains(element)) {
            throw new NoSuchElementException();
        }
        /*
         * find the element
         * get the previous and next
         * now that we got that lets set the previous node to next node
         * set element to null to remove data
         * also remove count and upp the modchange
         */
        DoubleLinearNode<T> nodeToRemove = head;
        while (nodeToRemove != null && !nodeToRemove.getData().equals(element)) {
            nodeToRemove = nodeToRemove.getNext(); // find node to remove
        }

        if (nodeToRemove == null) {
            throw new NoSuchElementException();
        }
        // get the elements
        DoubleLinearNode<T> previousNode = nodeToRemove.getPrevious();
        DoubleLinearNode<T> nextNode = nodeToRemove.getNext();
        // account for null cases
        if (previousNode != null) {
            previousNode.setNext(nextNode);
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.setPrevious(previousNode);
        } else {
            tail = previousNode;
        }
        // delete data for space
        T removedElement = nodeToRemove.getData();
        nodeToRemove.setNext(null);
        nodeToRemove.setPrevious(null);
        modChange++;
        count--;

        return removedElement;
    }

    public T first() {
        if (isEmpty() || head == null) { // case check if head be null
            throw new NoSuchElementException();
        }
        return head.getData();
    }

    public T last() {
        if (isEmpty() || tail == null) { // case check if tail is null
            throw new NoSuchElementException();
        }
        return tail.getData();
    }

    // ?BUGCHECK:COMPLETE, WORKS
    public boolean contains(T target) {
        DoubleLinearNode<T> current = head;
        while (current != null) {
            if (current.getData().equals(target)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean isEmpty() {
        if (count == 0 || (head == null && tail == null)) { // use head tail and count to check for empty
            return true;
        } else
            return false;
    }

    public int size() {
        return count;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModChange = modChange;
            private DoubleLinearNode<T> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModChange != modChange) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = current.getData();
                current = current.getNext();
                return element;
            }
        };
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        DoubleLinearNode<T> current = head;
        if (count == 0) {
            return "empty";
        }
        while (current != null) {
            output.append(current.getData()).append(" ");
            current = current.getNext();
        }

        return output.toString();
    }
}
