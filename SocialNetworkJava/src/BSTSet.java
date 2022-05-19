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
		if (root == null) return false;

		BSTSetNode<K> node = root;
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
			root = new BSTSetNode<K>(k);
			return true;
		} else {
			root = insert(root, k);
			return true;
		}
	}

	private BSTSetNode<K> insert(BSTSetNode<K> node, K key) {
		// if the root is null, create a new node an return it
		if (node == null) {
			return new BSTSetNode<K>(key);
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
		if(!exists(k)) return false; // if not exist
		BSTSetNode<K> p;
		p = remove(k, root, false);
		root = p;
		return !exists(k);
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

	@Override
	public Iterator<K> minIt() {
		return new Iterator<K>() {

			private int currentIndex = 0;
			private K[] iterArr;

			private void initArr(BSTSetNode<K> node) {
				if (node != null) {
					initArr(node.left);
					iterArr[currentIndex++] = node.key;
					initArr(node.right);
				}
			}

			@Override
			@SuppressWarnings("unchecked")
			public boolean isValid() {
				if (iterArr == null) {
					iterArr = (K[]) new Comparable[size()];
					initArr(root);
					currentIndex = 0;
				}
				return currentIndex >= 0 && currentIndex < size();
			}

			@Override
			public K next() {
				return iterArr[currentIndex++];
			}

			@Override
			public K prev() {
				return iterArr[--currentIndex];
			}
		};
	}

	@Override
	public Iterator<K> maxIt() {
		return new Iterator<K>() {

			private int currentIndex = 0;
			private K[] iterArr;

			private void initArr(BSTSetNode<K> node) {
				if (node != null) {
					initArr(node.left);
					iterArr[currentIndex++] = node.key;
					initArr(node.right);
				}
			}

			@Override
			@SuppressWarnings("unchecked")
			public boolean isValid() {
				if (iterArr == null) {
					iterArr = (K[]) new Comparable[size()];
					initArr(root);
					currentIndex = size()-1;
				}
				return currentIndex >= 0 && currentIndex < size();
			}

			@Override
			public K next() {
				return iterArr[currentIndex++];
			}

			@Override
			public K prev() {
				return iterArr[currentIndex--];
			}
		};
	}

/*
	public static void main(String[] args) {
		BSTSet<Integer> set = new BSTSet<>();

		set.insert(6);
		set.insert(7);
		set.insert(8);

		Iterator<Integer> integerIterator = set.maxIt();
		integerIterator.isValid();

		System.out.println(integerIterator.next());
		boolean remove = set.remove(7);
		System.out.println(remove);

		Iterator<Integer> integerIterator2 = set.maxIt();
		while (integerIterator2.isValid()) {
			System.out.println(integerIterator2.prev());
		}
	}
*/

}
