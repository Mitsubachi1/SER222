//package edu.ser222.m03_04;
package Module_10;
/**
 * A symbol table implemented using a hashtable with linear probing.
 * 
 * @author (put your name here), Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompletedLinearProbingHT<Key, Value> implements ProbingHT<Key, Value> {

    //any constructors must be made public

    @Override
    public int hash(Key key, int i) {
        //TODO
        return 0;
    }

    @Override
    public void put(Key key, Value val) {
        int index1 = hash(key);
        int index2 = hash2(key);
        int indexToInsert = (table[index1] == null || table[index1].size() <= table[index2].size()) ? index1 : index2;

        if (table[indexToInsert] == null) {
            table[indexToInsert] = new LinkedList<>();
        }

        // Check if the key already exists, if so, update the value
        for (Entry<Key, Value> entry : table[indexToInsert]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(val);
                return;
            }
        }

        table[indexToInsert].add(new Entry<>(key, val));
        size++;
    }

    @Override
    public Value get(Key key) {
        int index1 = hash(key);
        int index2 = hash2(key);
        int indexToSearch = (table[index1] == null || table[index1].size() <= table[index2].size()) ? index1 : index2;

        if (table[indexToSearch] != null) {
            for (Entry<Key, Value> entry : table[indexToSearch]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    @Override
    public void delete(Key key) {
        //TODO
    }

    @Override
    public boolean contains(Key key) {
        //TODO
        return false;
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return false;
    }

    @Override
    public int size() {
        //TODO
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        //TODO
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE PROBINGHT INTERFACE.

    @Override
    public int getM() {
        //TODO. We suggest something like:
        //return M;

        return 0;
    }

    @Override
    public Object getTableEntry(int i) {
        //TODO. We suggest something like:
        //return entries[i];

        return 0;
    }
}