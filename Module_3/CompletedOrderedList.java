//package edu.ser222.m01_03;
package Module_3; //local storage

/**
 * CompletedOrderedList represents an implementation of an ordered list that
 * builds on
 * CompletedList.
 *
 * @author Angel Chiquito, Acuna
 * @version 10/21/23
 */
public class CompletedOrderedList<T extends Comparable<T>> extends CompletedList<T>
        implements OrderedListADT<T> {

    /**
     * Adds the specified element to this collection at the proper location
     *
     * @param element the element to be added to this collection
     * @throws NullPointerException if element is null
     */
    public void add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot add a null element.");
        } else {
            DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element); //create new node using constructor
            if (isEmpty()) {
                // If the list is empty, set the new node as both the head and the tail
                head = newNode;
                tail = newNode;
            } else if (element.compareTo(head.getData()) <= 0) {
                // If the new element is less than or equal to the head, insert it as the new
                // head
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
            } else if (element.compareTo(tail.getData()) >= 0) {
                // If the new element is greater than or equal to the tail, insert it as the new
                // tail
                newNode.setPrevious(tail);
                tail.setNext(newNode);
                tail = newNode;
            } else {
                // Otherwise, find the appropriate location to insert the element within the
                // list
                DoubleLinearNode<T> current = head;
                while (current != null && element.compareTo(current.getData()) > 0) {
                    current = current.getNext();
                }
                // Insert the new node before the current node
                newNode.setNext(current);
                newNode.setPrevious(current.getPrevious());
                current.getPrevious().setNext(newNode);
                current.setPrevious(newNode);
            }
            count++;
            modChange++;
        }
        
    }
    
}
