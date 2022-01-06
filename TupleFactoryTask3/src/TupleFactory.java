package jacobwilliamproject;

public class TupleFactory<T, S> {

    private final TuplePool<T, S> tuplePool;

    // default constructor
    public TupleFactory() {
        tuplePool = new TuplePool<>();
    }

    //Here we passed the values to create and returns a tuple object.
    public Tuple<T, S> create(T t, S s) {
        return intern(new Tuple<>(t, s));
    }

    //  intern() it can be inserted into the pool And return tuple
    public Tuple<T, S> intern(Tuple<T, S> tuple) {
        return tuplePool.insertTuple(tuple);
    }
}
