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
        PQK<Double, K> pqk = new ArrayPQK<>(k);
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
        return null;
    }

    // Return the top k recommended friends for user i using weighted common neighbors method.
    // If i does not exist, return null. In case of a tie, users with the lowest id are selected.
    public static <K extends Comparable<K>> PQK<Double, K> recommendWCN(Graph<K> g, K i, int k) {
        return null;
    }
}
