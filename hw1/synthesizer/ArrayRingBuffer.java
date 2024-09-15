package synthesizer;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    //index to the head queue and the tail queue
    int first;
    int last;
    T[] rb;

    //The constructor
    public ArrayRingBuffer(int capacity) {
        //set value of the list
        first = 0;
        last = 0;
        this.capacity = capacity;

        //instantiate the list
        rb = (T[]) new Object[capacity];
    }

    @Override
    public void enqueue(T x) {
      if (fillCount == capacity) {
          throw new RuntimeException("Ring Buffer Overflow");
      }
      enquequeHelper(x);
    }

    private void enquequeHelper(T x) {
        rb[last] = x;
        last = (last + 1) % capacity;

        fillCount++;
    }


    @Override
    public T dequeue() {
     if (fillCount == 0) {
         throw new RuntimeException("Ring Buffer Underflow");
     }
     return dequeueHelper();
    }

    private T dequeueHelper() {
        T item = rb[first];
        first = (first + 1) % capacity;

        fillCount--;

        return item;
    }

    @Override
    public T peek() {
        return rb[first];
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        int position;
        int current;

        public ArrayIterator() {
            current = 0;
            position = first;
        }

        public boolean hasNext() {
            return current < fillCount;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No sunch element to iterate.");
            }

            T value = rb[position];
            current++;
            position = (position + 1) % capacity;
            return value;
        }
    }
}