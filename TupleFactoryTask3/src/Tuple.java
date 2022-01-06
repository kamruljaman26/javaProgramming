package jacobwilliamproject;

import java.util.Objects;

public class Tuple<T, S> {

    private T t;
    private S s;

    // default constructor
    public Tuple(T t, S s) {
        this.t = t;
        this.s = s;
    }

    // get T
    public T getT() {
        return t;
    }

    // set T
    public void setT(T t) {
        this.t = t;
    }

    // get S
    public S getS() {
        return s;
    }

    // set S
    public void setS(S s) {
        this.s = s;
    }

    // equal method for compare to Tuple
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

    // convert the tuple as a string
    @Override
    public String toString() {
//        return "Tuple: (T=" + t + ", S=" + s + ')';
        return String.format("Tuple:(T=%s, S=%s) ", t, s);
    }
}
