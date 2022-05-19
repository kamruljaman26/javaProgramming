public class ArrayPQK<P extends Comparable<P>, T> implements PQK<P, T> {

    private final int maxsize;
    private int front = 0, rear = 0;
    private int size = 0;

    private final T[] data;
    private final P[] priority;

    @SuppressWarnings("unchecked")
    public ArrayPQK(int k) {
        maxsize = k;
        data = (T[]) new Object[maxsize];
        priority = (P[]) new Comparable[maxsize];
    }

    @Override
    public int length() {
        if (size >= maxsize) size = maxsize;
        return size;
    }

    @Override
    public void enqueue(P pr, T e) {
        // for first element
        if (front == 0 && rear == 0) {
            data[rear] = e;
            priority[rear] = pr;
            rear++;
            size++;
        } else if (front < rear) {
            if (length() == maxsize) {
                int index1 = maxsize - 1;
                if (pr.compareTo(priority[index1]) > 0) sortQueue(pr, e, index1);
            } else {
                sortQueue(pr, e, rear);
                rear++;
                size++;
            }
        }
    }

    // sort the que based on priority
    // used insertion sorting algorithm (Modified)
    private void sortQueue(P pr, T e, int indx) {
        data[indx] = e;
        priority[indx] = pr;
        int index = indx - 1;

        while (index >= 0 && pr.compareTo(priority[index]) > 0) {
            data[index + 1] = data[index];
            priority[index + 1] = priority[index];
            index = index - 1;
        }

        data[index + 1] = e;
        priority[index + 1] = pr;
    }

    @Override
    public Pair<P, T> serve() {
        Pair<P, T> e = new Pair<P, T>(priority[front], data[front]);
        for (int i = 1; i < maxsize; i++) {
            data[i - 1] = data[i];
            priority[i - 1] = priority[i];
        }
        rear--;
        size--;
        return e;
    }
}
