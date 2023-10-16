//package edu.ser222.m01_03;
package Module_2;

import java.util.NoSuchElementException;

/**
 * This program provides an implementation of the Deque interface. Also provides
 * a main that
 * demonstrates it.
 * 
 * @author (your name), Acuna
 * @version (version)
 */

public class CompletedDeque<Item> implements Deque<Item> {
    int count = 0;
    // TODO: implement all the methods

    public void enqueueFront(Item element){

    }

    /**
     * Adds one element to the back of this deque.
     * 
     * @param element the element to be added to the back of the deque
     */
    public void enqueueBack(Item element){

    }

    /**
     * Removes and returns the element at the front of this deque.
     * Throws an exception if the deque is empty.
     * 
     * @return the element at the front of this deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueFront() throws NoSuchElementException{
        return null; // placeholder MMUST CHANGE
    }

    /**
     * Removes and returns the element at the back of this deque.
     * Throw an exception if the deque is empty.
     * 
     * @return the element at the back of the deque.
     * @throws NoSuchElementException if the deque is empty
     */
    public Item dequeueBack() throws NoSuchElementException{
        return null; // placeholder MMUST CHANGE
    }

    /**
     * Returns, without removing, the element at the front of this deque.
     * Should throw an exception if the deque is empty.
     * 
     * @return the first element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item first() throws NoSuchElementException{
        return null; // placeholder MMUST CHANGE
    }

    /**
     * Returns, without removing, the element at the back of this deque.
     * Should throw an exception if the deque is empty.
     * 
     * @return the last element in the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item last() throws NoSuchElementException{
        return null; // placeholder MMUST CHANGE
    }

    /**
     * Returns true if this deque is empty and false otherwise.
     * 
     * @return if deque empty
     */
    public boolean isEmpty(){
        return true; // placeholder
    }

    /**
     * Returns the number of elements in this deque.
     * 
     * @return the number of elements in the deque
     */
    public int size(){
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
    public String toString(){
        return "";
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