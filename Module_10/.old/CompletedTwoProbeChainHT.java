//package edu.ser222.m03_04;
package Module_10;

/**
 * A symbol entries implemented using a hashtable with chaining.
 * Does not support load balancing or resizing.
 * 
 * @author (put your name here), Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompletedTwoProbeChainHT<Key, Value> implements TwoProbeChainHT<Key, Value> {
    private int capacity = 10;
    private LinkedList<TwoProbeChainHT<Key, Value>>[] entries;
    private int size;
    private Key key;
    private Value value;

    // any constructors must be made public
    CompletedTwoProbeChainHT() {
        entries = new LinkedList[capacity];
        size = 0;
    }

    CompletedTwoProbeChainHT(Key key, Value val) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % entries.length;
    }

    @Override
    public int hash2(Key key) {
        return (((key.hashCode() & 0x7fffffff % entries.length) * 31) % entries.length);
    }

    @Override
    public void put(Key key, Value val) {
        int index = hash(key);
        if (entries[index] == null) {
            entries[index] = new LinkedList<>();
        }

        // Check if the key already exists in the TwoProbeChainHT
        TwoProbeChainHT<Key, Value> chainHT = findChainHT(entries[index], key);
        if (chainHT != null) {
            chainHT.put(key, val); // Update the value if the key already exists
        } else {
            // If the TwoProbeChainHT is not found, add a new one to the list
            entries[index].add(new TwoProbeChainHT<>(key, val));
        }
    }

    @Override
    public Value get(Key key) {
        int index1 = hash(key);
        int index2 = hash2(key);
        int indexToSearch = (entries[index1] == null || entries[index1].size() <= entries[index2].size()) ? index1
                : index2;

        if (entries[indexToSearch] != null) {
            for (TwoProbeChainHT<Key, Value> entry : entries[indexToSearch]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    @Override
    public void delete(Key key) {
        int index1 = hash(key);
        int index2 = hash2(key);
        int indexToDelete = (entries[index1] == null || entries[index1].size() <= entries[index2].size()) ? index1
                : index2;

        if (entries[indexToDelete] != null) {
            entries[indexToDelete].removeIf(entry -> entry.getKey().equals(key));
            size--;
        }
    }

    @Override
    public boolean contains(Key key) {
        int index1 = hash(key);
        int index2 = hash2(key);
        int indexToSearch = (entries[index1] == null || entries[index1].size() <= entries[index2].size()) ? index1
                : index2;

        if (entries[indexToSearch] != null) {
            for (TwoProbeChainHT<Key, Value> entry : entries[indexToSearch]) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        for (LinkedList<TwoProbeChainHT<Key, Value>> list : entries) {
            if (list != null && !list.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> keysQueue = new LinkedList<>();
        for (LinkedList<TwoProbeChainHT<Key, Value>> list : entries) {
            if (list != null) {
                for (TwoProbeChainHT<Key, Value> chainHT : list) {
                    keysQueue.add(chainHT.getKey());
                }
            }
        }
        return keysQueue;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE TWOPROBECHAINHT
    //////////////////////////////////////////////////////////////////////////////////////////////// INTERFACE.

    @Override
    public int getM() {
        return entries.length;
    }

    @Override
    public int getChainSize(int i) {
        return entries[i].size();
    }
}