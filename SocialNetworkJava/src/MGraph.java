public class MGraph<K extends Comparable<K>> implements Graph<K> {

	public Set<K> nodes; // Do not change this
	public Map<K, Set<K>> adj; // Do not change this

	@Override
	public boolean addNode(K i) {
		return false;
	}

	@Override
	public boolean isNode(K i) {
		return false;
	}

	@Override
	public boolean addEdge(K i, K j) {
		return false;
	}

	@Override
	public boolean isEdge(K i, K j) {
		return false;
	}

	@Override
	public Set<K> neighb(K i) {
		return null;
	}

	@Override
	public int deg(K i) {
		return 0;
	}

	@Override
	public Iterator<K> nodesIt() {
		return null;
	}
}
