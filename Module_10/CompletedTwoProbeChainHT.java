//package edu.ser222.m03_04;
package Module_10;
/**
 * A symbol table implemented using a hashtable with chaining.
 * Does not support load balancing or resizing.
 * 
 * @author (put your name here), Sedgewick and Wayne, Acuna
 */
import java.util.LinkedList;
import java.util.Queue;

public class CompletedTwoProbeChainHT<Key, Value> implements TwoProbeChainHT<Key, Value> {
    private static final int DEFAULT_CAPACITY = 10;
    private LinkedList<TwoProbeChainHT<Key, Value>>[] table;
    private int size;
    //any constructors must be made public
    CompletedTwoProbeChainHT(){
        table = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }
    CompletedTwoProbeChainHT(Key key, Value val){
        this();
        put(key, val);
    }
    @Override
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    @Override
    public int hash2(Key key) {
        return (((key.hashCode() & 0x7fffffff % table.length) * 31 ) % table.length);
    }

    @Override
    public void put(Key key, Value val) {
        //TODO
    }

    @Override
    public Value get(Key key) {
        int index1 = hash(key);
        int index2 = hash2(key);
        int indexToSearch = (table[index1] == null || table[index1].size() <= table[index2].size()) ? index1 : index2;

        if (table[indexToSearch] != null) {
            for (TwoProbeChainHT<Key, Value> entry : table[indexToSearch]) {
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
    // THESE METHODS ARE ONLY FOR GRADING AND COME FROM THE TWOPROBECHAINHT INTERFACE.

    @Override
    public int getM() {
        //TODO. We suggest something like:
        //return M;

        return 0;
    }

    @Override
    public int getChainSize(int i) {
        //TODO. We suggest something like:
        //return entries[i].size();

        return 0;
    }
}