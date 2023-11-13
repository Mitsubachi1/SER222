//package edu.ser222.m03_02;
package Module_9;

/**
 * A binary search tree based implementation of a symbol table.
 * 
 * Completion time: (your completion time)
 *
 * @author (your name), Sedgewick, Acuna
 * @version (version)
 */
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CompletedBST<Key extends Comparable<Key>, Value> implements BST<Key, Value> {
    private Node<Key, Value> root;

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.N;
    }

    @Override
    public Value get(Key key) {
        Node<Key, Value> iter = root;

        while (iter != null) {
            int cmp = key.compareTo(iter.key);

            if (cmp < 0)
                iter = iter.left;
            else if (cmp > 0)
                iter = iter.right;
            else
                return iter.val;
        }

        return null;
    }

    private Value get(Node<Key, Value> x, Key key) {
        // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node<Key, Value> x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    @Override
    public Key min() {
        if (root == null)
            throw new NoSuchElementException();
        return min(root).key;
    }

    private Node<Key, Value> min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    @Override
    public Key max() {
        if (root == null)
            throw new NoSuchElementException();
        return max(root).key;
    }

    private Node<Key, Value> max(Node x) {
        if (x.right == null)
            return x;
        return max(x.right);
    }

    @Override
    public Key floor(Key key) {
        if (root == null)
            throw new NoSuchElementException();

        Node<Key, Value> x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node<Key, Value> floor(Node<Key, Value> x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node<Key, Value> t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    @Override
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node<Key, Value> select(Node x, int k) {
        if (x == null)
            return null;
        int t = size(x.left);
        if (t > k)
            return select(x.left, k);
        else if (t < k)
            return select(x.right, k - t - 1);
        else
            return x;
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node<Key, Value> x) {
        // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }

    @Override
    public void deleteMin() {
        if (root == null)
            throw new NoSuchElementException();
        root = deleteMin(root);
    }

    private Node<Key, Value> deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterable<Key> keys() {
        if (root == null)
            return new LinkedList<>();
        else
            return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node<Key, Value> x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null)
            return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)
            keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0)
            queue.add(x.key);
        if (cmphi > 0)
            keys(x.right, queue, lo, hi);
    }

    public Key ceiling(Key key) {
        // SKIP, UNNEEDED
        return null;
    }

    public Node getRoot() {
        return root;
    }

    public boolean contains(Key key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void deleteMax() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        root = deleteMax(root);
    }

    private Node<Key, Value> deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int size(Key lo, Key hi) { // cmp nodes and add size if in bounds
        if (lo.compareTo(hi) > 0) {
            return 0; // bad range set
        }
        return size(root, lo, hi);
    }

    private int size(Node<Key, Value> x, Key lo, Key hi) {
        if (x == null) {
            return 0;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        int count = 0;
        if (cmplo < 0) {
            count += size(x.left, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            count++;
        }
        if (cmphi > 0) {
            count += size(x.right, lo, hi);
        }
        return count;
    }

    public void putFast(Key key, Value val) {
        put(key, val);
    }

    public Value getFast(Key key) {
        return get(key);
    }

    public void balance() {
        root = balance(root); // balance root
    }

    private Node<Key, Value> balance(Node<Key, Value> x) {
        if (x == null) {
            return null;
        }

        // update size before balance
        x.N = size(x.left) + size(x.right) + 1;

        int balanceFactor = getBalanceFactor(x);
        if (balanceFactor > 1) { // left heavy: right rotation
            if (getBalanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (balanceFactor < -1) { // right heavy: left rotation
            if (getBalanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        // if its balanced
        x.left = balance(x.left);
        x.right = balance(x.right);
        return x;
    }

    private int getBalanceFactor(Node<Key, Value> x) {
        return getHeight(x.left) - getHeight(x.right);
    }

    private int getHeight(Node<Key, Value> x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    private Node<Key, Value> rotateRight(Node<Key, Value> x) {
        Node<Key, Value> y = x.left;
        x.left = y.right;
        y.right = x;

        // update size
        x.N = size(x.left) + size(x.right) + 1;
        y.N = size(y.left) + size(y.right) + 1;

        return y;
    }

    private Node<Key, Value> rotateLeft(Node<Key, Value> x) {
        Node<Key, Value> y = x.right;
        x.right = y.left;
        y.left = x;

        x.N = size(x.left) + size(x.right) + 1;
        y.N = size(y.left) + size(y.right) + 1;

        return y;
    }

    public String displayLevel(Key key) { // should print out the keys it traverses when it gets to given key
        if (root == null) {
            return "empty";
        }
        StringBuilder path = new StringBuilder();
        Queue<Node<Key, Value>> cue = new LinkedList<>();
        cue.add(getRoot());
        while (!cue.isEmpty()) {
            Node<Key, Value> curr = cue.poll();
            if (curr != null) {
                path.append(curr.val).append(" ");
                cue.add(curr.left);
                cue.add(curr.right);
            }
        }
        return path.toString().trim();
    }

    /**
     * entry point for testing.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BST<Integer, String> bst = new CompletedBST();

        bst.put(10, "TEN");
        bst.put(3, "THREE");
        bst.put(1, "ONE");
        bst.put(5, "FIVE");
        bst.put(2, "TWO");
        bst.put(7, "SEVEN");

        System.out.println("Before balance:");
        System.out.println(bst.displayLevel(10)); // root


        System.out.println("After balance:");
        bst.balance();
        System.out.println(bst.displayLevel(5)); // root
    }
}