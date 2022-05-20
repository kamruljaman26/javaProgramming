import java.io.File;
import java.util.Scanner;

public class Recommender {

    // Read graph from file. The file is a text file where each line contains an edge.
    // The end and start of the edge are separated by space(s) or tabs (see graph.txt).
    public static Graph<Integer> read(String fileName) {
        try {
            Graph<Integer> g = new MGraph<>();
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                g.addNode(i);
                int j = scanner.nextInt();
                g.addNode(j);
                g.addEdge(i, j);
            }
            scanner.close();
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Return the top k recommended friends for user i using the popular users method.
    // If i does not exist, return null. In case of a tie, users with the lowest id are selected.
    public static <K extends Comparable<K>> PQK<Double, K> recommendDeg(Graph<K> g, K i, int k) {
        PQK<Double, K> pqk = new ArrayPQK<Double, K>(k);
        //If Node is found
        if (g.isNode(i)) {
            Iterator<K> iterator = g.nodesIt();
            while (iterator.isValid()) {
                K key = iterator.next();
                if (!g.isEdge(i, key)) {
                    if (i != key) {
                        pqk.enqueue((double) g.deg(key), key);
                    }
                }
            }
            return pqk;

        } else {
            return null;
        }
    }


    // Return the top k recommended friends for user i using common neighbors method.
    // If i does not exist, return null. In case of a tie, users with the lowest id are selected.
    public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {

        // if not exist
        if (!g.isNode(i)) return null;

        // common neighbors
        int pi = 1000000;
        PQK<Double, K> mainPQ = new ArrayPQK<Double, K>(k);

        // build priority queue
        PQK<Double, K> pqk = new ArrayPQK<Double, K>(k);
        Iterator<K> iterator = g.nodesIt();
        while (iterator.isValid()) {
            K key = iterator.next();
            if (!g.isEdge(i, key)) {
                if (i != key) {
                    pqk.enqueue((double) g.deg(key), key);
                }
            }
        }

        // find common neighbor
        Set<K> commonNeigh = new BSTSet<K>();
        Set<K> neighb = g.neighb(i);
        Iterator<K> neiIter = neighb.minIt();
        while (neiIter.isValid()) {
            K next = neiIter.next();
//            System.out.print(next+"= ");
            Set<K> n = g.neighb(next);
            Iterator<K> ii = n.minIt();
            while (ii.isValid()) {
                K nn = ii.next();
//                System.out.print(nn+", ");
                if (!(nn.compareTo(i) == 0) && !neighb.exists(nn)) {
                    commonNeigh.insert(nn);
                }
            }
//            System.out.println("::");
        }

        // find friend based on common neighbor
        Iterator<K> commonNeighIter = commonNeigh.minIt();
        while (commonNeighIter.isValid()) {
            K node = commonNeighIter.next();
//            System.out.println(node);
            Iterator<K> frineds = neighb.minIt();
            int count = 0;
            while (frineds.isValid()) {
                K friendNode = frineds.next();
//                System.out.print(friendNode + ", ");
                if (g.isEdge(node, friendNode)) {
                    count++;
                }
            }
            mainPQ.enqueue((double) count, node);
        }
//        System.out.println("\n");

        // add remain friends
        for (int j = 0; j < 4 - mainPQ.length(); j++) {
            mainPQ.enqueue(0.0, pqk.serve().second);
        }


        return mainPQ;
    }

    // Return the top k recommended friends for user i using weighted common neighbors method.
    // If i does not exist, return null. In case of a tie, users with the lowest id are selected.
    public static <K extends Comparable<K>> PQK<Double, K> recommendWCN(Graph<K> g, K i, int k) {
        // if not exist
        if (!g.isNode(i)) return null;

        // common neighbors
        int pi = 1000000;
        PQK<Double, K> mainPQ = new ArrayPQK<Double, K>(k);

        // build priority queue
        PQK<Double, K> pqk = new ArrayPQK<Double, K>(k);
        Iterator<K> iterator = g.nodesIt();
        while (iterator.isValid()) {
            K key = iterator.next();
            if (!g.isEdge(i, key)) {
                if (i != key) {
                    pqk.enqueue((double) g.deg(key), key);
                }
            }
        }

        // find common neighbor
        Set<K> commonNeigh = new BSTSet<K>();
        Set<K> neighb = g.neighb(i);
        Iterator<K> neiIter = neighb.minIt();
        while (neiIter.isValid()) {
            K next = neiIter.next();
//            System.out.print(next+"= ");
            Set<K> n = g.neighb(next);
            Iterator<K> ii = n.minIt();
            while (ii.isValid()) {
                K nn = ii.next();
//                System.out.print(nn+", ");
                if (!(nn.compareTo(i) == 0) && !neighb.exists(nn)) {
                    commonNeigh.insert(nn);
                }
            }
//            System.out.println("::");
        }

        // find friend based on common neighbor
        Iterator<K> commonNeighIter = commonNeigh.minIt();
        while (commonNeighIter.isValid()) {
            K node = commonNeighIter.next();
//            System.out.println(node);
            Iterator<K> frineds = neighb.minIt();
            double weightPi = 0;
            while (frineds.isValid()) {
                K friendNode = frineds.next();
//                System.out.print(friendNode + ", ");
                if (g.isEdge(node, friendNode)) {
                    weightPi += (double) 1 / g.deg(friendNode);
                }
            }

            mainPQ.enqueue(weightPi, node);
        }
//        System.out.println("\n");

        // add remain friends
        for (int j = 0; j < 4 - mainPQ.length(); j++) {
            mainPQ.enqueue(0.0, pqk.serve().second);
        }


        return mainPQ;
    }

}
