/**
 *
 * @param <T>
 * @param <S>
 */
public class TuplePool<T, S> {

    private final TupleHashSet<T, S> tupleSet;
    private int size;

    public TuplePool() {
        tupleSet = new TupleHashSet<>();
        size = 0;
    }


    //With this method a tuple shall be inserted into the pool.
    public Tuple<T, S> insertTuple(Tuple<T, S> tuple) {
        // if tuple already not exist return null & successfully added
        if (!tupleSet.contains(tuple) && tupleSet.insert(tuple)) {
            size++;
            return tuple;
        }
        // if not able to added
        return null;
    }
    // This method shall find a tuple with the passed values in the pool

    public Tuple<T, S> GetValue(Tuple<T, S> tvalue) {
        return tupleSet.find(tvalue);
    }

    // unique tuples shall be counted and retrievable via this method
    public int getByValue() {
        return size;
    }

}
