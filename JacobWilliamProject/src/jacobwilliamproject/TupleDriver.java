package jacobwilliamproject;


import java.util.Iterator;

public class TupleDriver {
    // for test purpose only
    public static void main(String[] args) {
        TupleHashSet<Integer, Integer> set = new TupleHashSet<>();
        // Inserting Elements
        for (int i = 0; i < TupleHashSet.SIZE * 2; i++) {
            set.insert(new Tuple<>(i, i * i));
        }
        // print all values from set
        for (Tuple tuple : set) {
            System.out.print(tuple + " ");
        }

        System.out.println();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        TupleHashSet set2 = new TupleHashSet();
        set2.insert(new Tuple(2, 2));
        set2.insert(new Tuple(2, 2));
        set2.insert(new Tuple(2, 3));

        Iterator tupleIterator = set2.iterator();
        while (tupleIterator.hasNext()) {
            System.out.print(tupleIterator.next()+" ");
        }
    }
}
