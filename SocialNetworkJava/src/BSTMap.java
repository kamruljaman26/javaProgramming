public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {

    public BSTMapNode<K, T> root; // Do not change this

    @Override
    public int size() {
        if (root == null) return 0;
        return size(root);
    }

    // Calculate total number of nude.
    private int size(BSTMapNode<K, T> node) {
        if (node == null) return 0;
        // recursive call
        return (size(node.left) + 1 + size(node.right));
    }

    @Override
    public boolean full() {
        // node will never full
        return false;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean update(K k, T e) {
//        remove(k);
        return insert(k, e);
    }

    @Override
    public Pair<Boolean, T> retrieve(K k) {
        if (findKey(k)) {
            BSTMapNode<K, T> node = root;

            while (node != null) {
                Integer nKey = (Integer) node.key;
                Integer kKey = (Integer) k;
                if (nKey.equals(kKey)) {
                    return new Pair<>(true, node.data);
                } else if (nKey > kKey) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }

        }
        return new Pair<>(false, null);
    }

    private boolean findKey(K key) {
        BSTMapNode<K, T> node = root;
        if (node == null) return false;
        while (node != null) {
            K nKey = node.key;
            if (nKey == key) {
                return true;
            } else if (nKey.compareTo(key) > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    @Override
    public boolean insert(K k, T e) {
        // if key already added
        if (findKey(k)) return false;

        if (root == null) {
            root = new BSTMapNode<>(k, e);
            return true;
        } else {
            root = insert(root, k, e);
            return true;
        }
    }

    private BSTMapNode<K, T> insert(BSTMapNode<K, T> node, K key, T data) {
        // if the root is null, create a new node an return it
        if (node == null) {
            return new BSTMapNode<>(key, data);
        }

        Integer kKey = (Integer) node.key;
        Integer nKey = (Integer) key;

        // if given key is less than the root node,
        // recur for left subtree
        if (nKey < kKey) {
            node.left = insert(node.left, key, data);
        } // else recur for right subtree
        else {
            node.right = insert(node.right, key, data);
        }

        return node;
    }

    @Override
    public boolean remove(K k) {
        boolean removed = false;
        BSTMapNode<K, T> p;
        p = remove(k, root, removed);//Not change
        root = p;
        return removed;
    }

    private BSTMapNode<K, T> remove(K key, BSTMapNode<K, T> p, boolean flag) {
        BSTMapNode<K, T> q, child;
        if (p == null) return null;
        Integer pKey = (Integer) p.key;
        Integer nKey = (Integer) key;
        if (nKey < pKey) {
            // remove in left child
            p.left = remove(key, p.left, flag);
        } else if (nKey > pKey) {
            // remove in right child
            p.right = remove(key, p.right, flag);
        } else {
            flag = true;
            if (p.left != null && p.right != null) {
                q = findMin(p.right);
                p.key = q.key;
                p.data = q.data;
                p.right = remove(q.key, p.right, flag);
            } else {
                if (p.right == null)
                    child = p.left;
                else
                    child = p.right;
                return child;
            }
        }
        return p;
    }

    private BSTMapNode<K, T> findMin(BSTMapNode<K, T> p) {
        if (p == null) {
            return null;
        }
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }


    // Todo:
    @Override
    public Iterator<Pair<K, T>> minIt() {
        return new Iterator<Pair<K, T>>() {

            private int currentIndex = 0;
            private Pair<K, T>[] iterArr;

            private void initArr(BSTMapNode<K, T> node) {
                if (node == null) {
                    return;
                } else {
                    initArr(node.left);
                    iterArr[currentIndex++] = new Pair<>(node.key, node.data);
                    initArr(node.right);
                }
            }

            @Override
            public boolean isValid() {
                if (iterArr == null) {
                    iterArr = new Pair[size()];
                    initArr(root);
                    currentIndex = 0;
                }
                return currentIndex >= 0 && currentIndex != size();
            }

            @Override
            public Pair<K, T> next() {
                if (isValid()) return iterArr[currentIndex++];
                return null;
            }

            @Override
            public Pair<K, T> prev() {
                if ((currentIndex - 1) < 0) return null;
                return iterArr[--currentIndex];
            }
        };
    }


    // Todo:
    @Override
    public Iterator<Pair<K, T>> maxIt() {
        return new Iterator<Pair<K, T>>() {

            private int currentIndex = 0;
            private Pair<K, T>[] iterArr;

            private void initArr(BSTMapNode<K, T> node) {
                if (node == null) {
                    return;
                } else {
                    initArr(node.left);
                    iterArr[currentIndex++] = new Pair<>(node.key, node.data);
                    initArr(node.right);
                }
            }

            @Override
            public boolean isValid() {
                if (iterArr == null) {
                    iterArr = new Pair[size()];
                    initArr(root);
                    currentIndex = size()-1;
                }
                return currentIndex >= 0 && currentIndex != size();
            }

            @Override
            public Pair<K, T> next() {
                if ((currentIndex + 1) >= size()) return null;
                return iterArr[++currentIndex];
            }

            @Override
            public Pair<K, T> prev() {
                if (isValid()) return iterArr[currentIndex--];
                return null;
            }
        };
    }
}
