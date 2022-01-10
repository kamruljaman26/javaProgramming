import java.util.Iterator;

/**
 * Tuple Factory will create and hold tuples
 *
 * @param <T>
 * @param <S>
 */
public class TupleFactory<T, S> {

    // create tuple pool
    private final TuplePool<T, S> tuplePool;

    /**
     * default constructor
     */
    public TupleFactory() {
        tuplePool = new TuplePool<>();
    }

    /**
     * The method will create and store tuple object based on values.
     *
     * @param t object
     * @param s object
     * @return new tuple
     */
    public Tuple<T, S> create(T t, S s) {
        return intern(new Tuple<>(t, s));
    }

    /**
     * The method will insert the given tuple in the tuplepool
     *
     * @param tuple provide tuple
     * @return tuple object
     */
    public Tuple<T, S> intern(Tuple<T, S> tuple) {
        return tuplePool.insertTuple(tuple);
    }
}
