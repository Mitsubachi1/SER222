package edu.ser222.m03_04;
//package Module_10;

/**
 * A symbol table implemented using a hashtable with quadratic probing.
 * 
 * @author Angel Chiquito, Acuna
 */
public class CompletedQuadProbingHT<Key, Value> extends CompletedLinearProbingHT<Key, Value> {
    private static final int initcap = 997;
    private static int M;
    public CompletedQuadProbingHT() {
        this(initcap);
    }
    public CompletedQuadProbingHT(int capacity){
        super(capacity);
        M = capacity;
    }

    @Override
    public int hash(Key key, int i) {
        return ((key.hashCode() & 0x7fffffff) + i *i) % M;
    }
}