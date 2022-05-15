public class BSTSet<K extends Comparable<K>> implements Set<K> {

	public BSTSetNode<K> root; // Do not change this

	@Override
	public int size() {
		return size(root);
	}

	// calculate size recursively
	private int size(BSTSetNode<K> node) {
		if (node == null) {
			return 0;
		} else {
			return (size(node.left) + 1 + size(node.right));
		}
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
	public boolean exists(K k) {
		BSTSetNode<K> node = root;
		if (node == null) return false;
		while (node != null) {
			K nKey = node.key;
			if (nKey == k) {
				return true;
			} else if (nKey.compareTo(k) > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return false;
	}

	@Override
	public boolean insert(K k) {
		// if key already added
		if (exists(k)) return false;

		if (root == null) {
			root = new BSTSetNode<>(k);
			return true;
		} else {
			root = insert(root, k);
			return true;
		}
	}

	private BSTSetNode<K> insert(BSTSetNode<K> node, K key) {
		// if the root is null, create a new node an return it
		if (node == null) {
			return new BSTSetNode<>(key);
		}

		Integer kKey = (Integer) node.key;
		Integer nKey = (Integer) key;

		// if given key is less than the root node,
		// recur for left subtree
		if (nKey < kKey) {
			node.left = insert(node.left, key);
		} // else recur for right subtree
		else {
			node.right = insert(node.right, key);
		}

		return node;
	}

	@Override
	public boolean remove(K k) {
		boolean removed = false;
		BSTSetNode<K> p;
		p = remove(k, root, removed);//Not change
		root = p;
		return removed;
	}

	private BSTSetNode<K> remove(K key, BSTSetNode<K> p, boolean flag) {
		BSTSetNode<K> q, child;
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
				q = p.right;
				p.key = q.key;
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

	// todo
	@Override
	public Iterator<K> minIt() {
		return new Iterator<K>() {

			private int currentIndex = 0;
			private Object[] iterArr;

			private void initArr(BSTSetNode<K> node) {
				if (node == null) {
					return;
				} else {
					initArr(node.left);
					iterArr[currentIndex++] = node.key;
					initArr(node.right);
				}
			}

			@Override
			public boolean isValid() {
				if (iterArr == null) {
					System.out.println("s::"+size());
					iterArr =  new Object[size()];
					initArr(root);
					currentIndex = 0;
				}
				return currentIndex >= 0 && currentIndex < size();
			}

			@Override
			public K next() {
				if ((currentIndex) >= size()) return null;
				return (K) iterArr[currentIndex++];
			}

			@Override
			public K prev() {
				if (isValid()) return (K) iterArr[--currentIndex];
				return null;
			}
		};
	}

	//todo
	@Override
	public Iterator<K> maxIt() {
		return new Iterator<K>() {

			private int currentIndex = 0;
			private Object[] iterArr;

			private void initArr(BSTSetNode<K> node) {
				if (node == null) {
					return;
				} else {
					initArr(node.left);
					iterArr[currentIndex++] = node.key;
					initArr(node.right);
				}
			}

			@Override
			public boolean isValid() {
				if (iterArr == null) {
					iterArr =  new Object[size()];
					initArr(root);
					currentIndex = size()-1;
				}
				return currentIndex >= 0 && currentIndex < size();
			}

			@Override
			public K next() {
				if (currentIndex >= size()) return null;
				return (K) iterArr[currentIndex++];
			}

			@Override
			public K prev() {
				if (isValid()) return (K) iterArr[currentIndex--];
				return null;
			}
		};
	}
}
