//package edu.ser222.m03_04;
package Module_10;
import java.util.ArrayList;
/**
 * A symbol table implemented using a hashtable with linear probing.
 * 
 * @author Angel Chiquito, Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CompletedLinearProbingHT<Key, Value> implements ProbingHT<Key, Value> {
    private static final int initcap = 997; 
    private int M; 
    private int size;
    private Node<Key, Value>[] table; 
    //any constructors must be made public
    private static class Node<Key, Value> {
        Key key;
        Value value;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public CompletedLinearProbingHT() {
        this(initcap);
    }

    public CompletedLinearProbingHT(int capacity) {
        M = capacity;
        size = 0;
        table = new Node[M];
    }
    @Override
    public int hash(Key key, int i) {
        //TODO
        return 0;
    }

    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        int i;
        for (i = hash(key, 0); table[i] != null; i = (i + 1) % M) {
            if (table[i].key.equals(key)) {
                table[i].value = val;
                return;
            }
        }
        table[i] = new Node<>(key, val);
        size++;
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        for (int i = hash(key, 0); table[i] != null; i = (i + 1) % M) {
            if (table[i].key.equals(key)) {
                return table[i].value;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        int i = hash(key, 0);
        while (table[i] != null) {
            if (table[i].key.equals(key)) {
                table[i] = null;
                size--;

                // Rehash to ensure no clustering
                i = (i + 1) % M;
                while (table[i] != null) {
                    Node<Key, Value> rehashNode = table[i];
                    table[i] = null;
                    size--;
                    put(rehashNode.key, rehashNode.value);
                    i = (i + 1) % M;
                }
                return;
            }
            i = (i + 1) % M;
        }
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        List<Key> keyList = new ArrayList<>();
        for (Node<Key, Value> node : table) {
            if (node != null) {
                keyList.add(node.key);
            }
        }
        return keyList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE PROBINGHT INTERFACE.

    @Override
    public int getM() {
        return M;
    }

    @Override
    public Object getTableEntry(int i) {
        if (table[i] != null){ 
            return table[i].value;
        }
        return null;
    }
}