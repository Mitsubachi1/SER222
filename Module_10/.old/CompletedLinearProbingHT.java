//package edu.ser222.m03_04;
package Module_10;

import java.security.Key;
/**
 * A symbol table implemented using a hashtable with linear probing.
 * 
 * @author (put your name here), Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompletedLinearProbingHT<Key, Value> implements ProbingHT<Key, Value> {
    private static final int INITIAL_CAPACITY = 16;
    private int M; // Current capacity
    private int size; // Number of key-value pairs
    private Entry<Key, Value>[] entries;
    private LinkedList<TwoProbeChainHT<Key, Value>>[] entries;

    // any constructors must be made public

    private static class Entry<Key, Value> {
        private Key key;
        private Value value;

        public Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }
    }
    
    public CompletedLinearProbingHT() {
        this(INITIAL_CAPACITY);
    }

    public CompletedLinearProbingHT(int initialCapacity) {
        this.M = initialCapacity;
        this.size = 0;
        this.entries = new Entry[M];
    }

    @Override
    public int hash(Key key, int i) {
        return (key.hashCode() & 0x7fffffff + i) % M;
    }

    @Override
    public void put(Key key, Value val) {
        int i = 0;
        while (i < M) {
            int index = hash(key, i);
            if (keys[index] == null) {
                keys[index] = key;
                values[index] = val;
                size++;
                return;
            }
            i++;
        }
    }

    @Override
    public Value get(Key key) {
        int i = 0;
        while (i < M) {
            int index = hash(key, i);
            if (keys[index] != null && keys[index].equals(key)) {
                return values[index];
            }
            i++;
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        int i = 0;
        while (i < M) {
            int index = hash(key, i);
            if (keys[index] != null && keys[index].equals(key)) {
                keys[index] = null;
                values[index] = null;
                size--;
                // Handle any rehashing or resizing if necessary
                return;
            }
            i++;
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
        // TODO
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        // TODO
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE PROBINGHT INTERFACE.

    @Override
    public int getM() {
        return M;
    }

    @Override
    public Object getTableEntry(int i) {
        if (i < 0 || i >= M) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return entries[i];
    }
}