import java.util.Objects;

/**
 * Tuple object to hold tuple values
 * @param <T>
 * @param <S>
 */
public class Tuple<T, S> {

    private T t;
    private S s;

    /**
     * default constructor
     * @param t object
     * @param s object
     */
    public Tuple(T t, S s) {
        this.t = t;
        this.s = s;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public S getS() {
        return s;
    }

    public void setS(S s) {
        this.s = s;
    }

    // equal method to compare 2 object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return t.equals(tuple.t) && s.equals(tuple.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, s);
    }

    // represent the Tuple as a string
    @Override
    public String toString() {
        return String.format("Tuple:(T=%s, S=%s) ", t, s);
    }
}
