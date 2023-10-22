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

    // The following three variables are a suggested start if you are using a list
    // implementation.
    protected int count;
    protected int modChange;
    protected DoubleLinearNode<T> head, tail;

    public class DoubleLinearNode<T> {
        private T elementData;
        private DoubleLinearNode<T> next;
        private DoubleLinearNode<T> previous;

        public DoubleLinearNode(T element) {
            this.elementData = element;
            this.next = null;
            this.previous = null;
        }

        // Getters and setters for the element, next, and previous references
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
        public void setPrevious(DoubleLinearNode<T> node){
            previous = node;
        }
    }

    // TODO: implement this!
    /**
     * Removes and returns the first element from this collection.
     * 
     * @return the first element from this collection
     * @throws NoSuchElementException if the collection is empty
     */
    public T removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("The collection is empty.");
        }
        T element = head.getData();
        head = head.getNext();
        count--;
        modChange++;
        return element;
    }

    /**
     * Removes and returns the last element from this collection.
     *
     * @return the last element from this collection
     * @throws NoSuchElementException if the collection is empty
     */
    public T removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("The collection is empty.");
        }
        T element = tail.getData();
        DoubleLinearNode<T> newTail = head;
        while (newTail.getNext() != tail) {
            newTail = newTail.getNext();
        }
        newTail.setNext(null);
        tail = newTail;
        count--;
        modChange++;
        return element;
    }

    /**
     * Removes and returns the specified element from this collection.
     *
     * @param element the element to be removed from the collection
     * @throws NoSuchElementException if the target is not in the collection
     */
    public T remove(T element) {
        // Placeholder logic to find and remove the element
        if (!contains(element)) {
            throw new NoSuchElementException("The element is not in the collection.");
        }
        // Implement the logic to remove the element and update the list
        modChange++;
        return element;
    }

    /**
     * Returns, without removing, the first element in the collection.
     *
     * @return a reference to the first element in this collection
     * @throws NoSuchElementException if the collection is empty
     */
    public T first() {
        if (isEmpty()) {
            throw new NoSuchElementException("The collection is empty.");
        }
        return head.getData();
    }

    /**
     * Returns, without removing, the last element in the collection.
     *
     * @return a reference to the last element in this collection
     * @throws NoSuchElementException if the collection is empty
     */
    public T last() {
        if (isEmpty()) {
            throw new NoSuchElementException("The collection is empty.");
        }
        return tail.getData();
    }

    /**
     * Returns true if this collection contains the specified target element, false
     * otherwise.
     *
     * @param target the target that is being sought in the collection
     * @return true if the collection contains this element
     */
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

    /**
     * Returns true if this collection is empty and false otherwise.
     *
     * @return true if this collection empty
     */
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else
            return false;
    }

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements in this collection
     */
    public int size() {
        return count;
    }

    /**
     * Returns an iterator for the elements in this collection. The returned object
     * must have implementations of hasNext() and next() that throw
     * ConcurrentModificationException when the contents of the list change.
     *
     * @return an iterator over the elements in this collection
     */
    public Iterator<T> iterator() {
        // Placeholder implementation of iterator
        return new Iterator<T>() {
            private int expectedModChange = modChange;
            private DoubleLinearNode<T> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModChange != modChange) {
                    throw new ConcurrentModificationException("Collection modified during iteration.");
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

    /**
     * Returns a string representation of this collection. If the list is empty,
     * returns "empty".
     *
     * @return a string representation of this collection
     */
    public String toString() {
        // Placeholder implementation to provide a basic string representation
        StringBuilder sb = new StringBuilder();
        DoubleLinearNode<T> current = head;
        while (current != null) {
            sb.append(current.getData()).append(", ");
            current = current.getNext();
        }
        if (sb.length() >= 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
