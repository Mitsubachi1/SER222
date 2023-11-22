package edu.ser222.m03_04;
//package Module_10;

/**
 * A symbol table implemented using a hashtable with chaining.
 * Does not support load balancing or resizing.
 * 
 * @author Angel Chiquito, Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompletedTwoProbeChainHT<Key, Value> implements TwoProbeChainHT<Key, Value> {
    public static final int initcap = 997;
    private int M;
    private LinkedList<Node<Key, Value>>[] entries;

    // any constructors must be made public
    private static class Node<Key, Value> {
        Key key;
        Value value;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public CompletedTwoProbeChainHT() {
        this(initcap);
    }

    public CompletedTwoProbeChainHT(int capacity) {
        M = capacity;
        entries = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            entries[i] = new LinkedList<>();
        }
    }

    @Override
    public int hash(Key key) {
        return ((key.hashCode() & 0x7fffffff) % M);
    }

    @Override
    public int hash2(Key key) {
        return (((key.hashCode() & 0x7fffffff) % M) * 31) % M;
    }

    @Override
    public void put(Key key, Value val) {
        int index1 = hash(key);
        int index2 = hash2(key);
        Node element = new Node(key, val);
        int selectedIndex = (entries[index1].size() <= entries[index2].size()) ? index1 : index2; // balance

        // check indexes for existing node
        for (Node<Key, Value> node : entries[index1]) { // iter nodes in entries of index to return val
            if (node.key.equals(key)) {
                node.value = val;
                return;
            }
        }
        for (Node<Key, Value> node : entries[index2]) { // iter nodes in entries of index to return val
            if (node.key.equals(key)) {
                node.value = val;
                return;
            }
        }
        entries[selectedIndex].add(element); // add element to list
    }

    @Override
    public Value get(Key key) {
        int index1 = hash(key);
        int index2 = hash2(key);

        for (Node<Key, Value> node : entries[index1]) { // iter nodes in entries of index to return val
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        for (Node<Key, Value> node : entries[index2]) { // iter nodes in entries of index to return val
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        int index1 = hash(key);
        int index2 = hash2(key);
        LinkedList<Node<Key, Value>> selectedList = entries[index1]; // check primary
        for (Node<Key, Value> entry : selectedList) { // nodes inside list
            if (entry.key.equals(key)) {
                selectedList.remove(entry);
            }
            break; // break loop on found
        }
        selectedList = entries[index2]; // check secondary
        for (Node<Key, Value> entry : selectedList) { // nodes inside list
            if (entry.key.equals(key)) {
                selectedList.remove(entry);
            }
            break; // break loop on found
        }
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        for (LinkedList<Node<Key, Value>> entry : entries) {
            if (!entry.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        int size = 0;
        for (LinkedList<Node<Key, Value>> entry : entries) { // auto main, add up entries
            size += entry.size();
        }
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        LinkedList<Key> keyList = new LinkedList<>();
        for (LinkedList<Node<Key, Value>> entry : entries) {
            for (Node<Key, Value> node : entry) {
                keyList.add(node.key);
            }
        }
        return keyList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE TWOPROBECHAINHT
    //////////////////////////////////////////////////////////////////////////////////////////////// INTERFACE.

    @Override
    public int getM() {
        return M;
    }

    @Override
    public int getChainSize(int i) {
        return entries[i].size();
    }
}