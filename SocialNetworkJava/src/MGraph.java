public class MGraph<K extends Comparable<K>> implements Graph<K> {

    public Set<K> nodes; // Do not change this
    public Map<K, Set<K>> adj; // Do not change this

    public MGraph() {
        nodes = new BSTSet<K>();
        adj = new BSTMap<K, Set<K>>();
    }

    @Override
    public boolean addNode(K i) {
        if (isNode(i)) {
            return false;
        }

        nodes.insert(i);
        adj.insert(i, new BSTSet<K>());
        return true;
    }

    @Override
    public boolean isNode(K i) {
        return nodes.exists(i);
    }

    @Override
    public boolean addEdge(K i, K j) {
        if (isNode(i) && isNode(j)) {
            Set<K> seti = adj.retrieve(i).second;
            Set<K> setj = adj.retrieve(j).second;
            if (seti == null || setj == null) return false;
            if (!seti.exists(j) && !setj.exists(i)) {
                seti.insert(j);
                setj.insert(i);
                adj.update(i, seti);
                adj.update(j, setj);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEdge(K i, K j) {
        if (isNode(i) && isNode(j)) {
            Set<K> listi = adj.retrieve(i).second;
            Set<K> listj = adj.retrieve(j).second;
            return listi.exists(j) && listj.exists(i);
        } else {
            return false;
        }
    }

    @Override
    public Set<K> neighb(K i) {
        return adj.retrieve(i).second;
    }

    @Override
    public int deg(K i) {
        if (isNode(i)) {
            return adj.retrieve(i).second.size();
        } else {
            return -1;
        }
    }

    @Override
    public Iterator<K> nodesIt() {
        // Return an iterator over nodes in increasing order starting with the smallest node.
        return nodes.minIt();
    }
}
