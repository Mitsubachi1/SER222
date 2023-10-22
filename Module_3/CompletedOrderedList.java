package edu.ser222.m01_03;
//package Module_3; //local storage

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


    public void add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        } else {
            DoubleLinearNode<T> newNode = new DoubleLinearNode<>(element); //create new node using constructor
            if (isEmpty()) { //on empty set all to newnode
                head = newNode;
                tail = newNode;
            } else if (element.compareTo(head.getData()) <= 0) { //on less than set as new head
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
            } else if (element.compareTo(tail.getData()) >= 0) { //on greater set as new tail
                newNode.setPrevious(tail);
                tail.setNext(newNode);
                tail = newNode;
            } else {
                DoubleLinearNode<T> current = head;
                while (current != null && element.compareTo(current.getData()) > 0) {
                    current = current.getNext();
                }
                //add newNode before current
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
