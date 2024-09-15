package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();

    int fillCount();

    void enqueue(T x);

    T dequeue();

    T peek();

    Iterator<T> iterator();
}
