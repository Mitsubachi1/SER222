public     private Node<Key, Value> balance(Node<Key, Value> x) {
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
{
    
}
