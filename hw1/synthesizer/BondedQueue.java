package synthesizer;
import java.util.Iterator;

public interface BondedQueue<T> extends Iterable<T> {
    int capacity();

    int fillCount();

    void enqueue(T x);

    T dequeue();

    T peek();

    boolean isEmpty();

    boolean isFull();

    Iterator<T> iterator();
}
