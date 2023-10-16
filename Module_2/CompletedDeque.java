//package edu.ser222.m01_03;
package Module_2;

import java.util.NoSuchElementException;

/**
 * This program provides an implementation of the Deque interface. Also provides
 * a main that
 * demonstrates it.
 * 
 * @author Angel Chiquito, Acuna
 * @version (version)
 */

public class CompletedDeque<Item> implements Deque<Item> {
    int count = 0;
    // TODO: implement all the methods
    private Node<Item> front;
    private Node<Item> back;

    public void enqueueFront(Item element) {
        Node<Item> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
            back = newNode;
        } else {
            newNode.next = front;
            front.previous = newNode;
            front = newNode;
        }
        count++;
    }

    /**
     * Adds one element to the back of this deque.
     * 
     * @param element the element to be added to the back of the deque
     */
    public void enqueueBack(Item element) {
        Node<Item> newNode = new Node<>(element);
        if (isEmpty()) {
            front = newNode;
            back = newNode;
        } else {
            newNode.previous = back;
            back.next = newNode;
            back = newNode;
        }
        count++;
    }

    /**
     * Removes and returns the element at the front of this deque.
     * Throws an exception if the deque is empty.
     * 
     * @return the element at the front of this deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueFront() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(); // throw if empty
        }
        Item element = front.data; // keep tab on front data
        if (front == back) { // case of one element
            front = null;
            back = null;
        } else { // case of many
            front = front.next;
            front.previous = null;
        }
        count--;
        return element;
    }

    /**
     * Removes and returns the element at the back of this deque.
     * Throw an exception if the deque is empty.
     * 
     * @return the element at the back of the deque.
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueBack() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(); // throw on empty
        }
        Item element = back.data;
        if (back == front) { // case of one element
            front = null;
            back = null;
        } else { // case of many
            back = back.previous;
            back.next = null;
        }
        count--;
        return element;
    }

    /**
     * Returns, without removing, the element at the front of this deque.
     * Should throw an exception if the deque is empty.
     * 
     * @return the first element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item first() throws NoSuchElementException {
        Item element = front.data;
        return element;
    }

    /**
     * Returns, without removing, the element at the back of this deque.
     * Should throw an exception if the deque is empty.
     * 
     * @return the last element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item last() throws NoSuchElementException {
        Item element = back.data;
        return element;
    }

    /**
     * Returns true if this deque is empty and false otherwise.
     * 
     * @return if deque empty
     */
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of elements in this deque.
     * 
     * @return the number of elements in the deque
     */
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of this deque. The back element
     * occurs first, and each element is separated by a space. If the
     * deque is empty, returns "empty".
     * 
     * @return the string representation of the deque
     */
    @Override
    public String toString() {
        StringBuilder stringresult = new StringBuilder();
        Node<Item> node = front;
        if (isEmpty()) {
            return "empty";
        }
        while (node != null) {
            stringresult.append(node.data);
            stringresult.append(" "); // space
            node = node.next; // move to the next node
        }
        return stringresult.toString();
    }

    private static class Node<Item> { // node class for node
        private Item data;
        private Node<Item> next;
        private Node<Item> previous;

        public Node(Item data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    /**
     * Program entry point for deque.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        CompletedDeque<Integer> deque = new CompletedDeque<>();

        // standard queue behavior
        deque.enqueueBack(3);
        deque.enqueueBack(7);
        deque.enqueueBack(4);
        deque.dequeueFront();
        deque.enqueueBack(9);
        deque.enqueueBack(8);
        deque.dequeueFront();
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());

        // deque features
        System.out.println(deque.dequeueFront());
        deque.enqueueFront(1);
        deque.enqueueFront(11);
        deque.enqueueFront(3);
        deque.enqueueFront(5);
        System.out.println(deque.dequeueBack());
        System.out.println(deque.dequeueBack());
        System.out.println(deque.last());
        deque.dequeueFront();
        deque.dequeueFront();
        System.out.println(deque.first());
        System.out.println("size: " + deque.size());
        System.out.println("contents:\n" + deque.toString());
    }
}