public class ArrayDeque<T> {

    private int size;
    private T[] item;
    private int front;
    private int last;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
        front = item.length / 2;
        last = item.length / 2 + 1;
    }


    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = item[(front + 1) % item.length];
            front++;
        }
        item = temp;
        front = capacity - 1;
        last = size;
    }

    public void addFirst(T x) {
        if (size == item.length) {
            resize(size * 2);
        }
        item[front] = x;
        front = (front - 1 + item.length) % item.length;
        size++;
    }

    public void addLast(T x) {
        if (size == item.length) {
            resize(size * 2);
        }
        item[last] = x;
        last = (last + 1) % item.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        front = (front + 1) % item.length;
        T x = item[front];
        item[front] = null;
        size--;
        if ((double) size / item.length < 0.25 && size > 16) {
            resize(item.length / 2);
        }
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        last = (last - 1 + item.length) % item.length;
        T x = item[last];
        item[last] = null;
        size--;
        if ((double) size / item.length < 0.25 && size > 16) {
            resize(item.length / 2);
        }
        return x;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int start = front + 1;
        return item[(start + index) % item.length];
    }

    public void printDeque() {
        int start = front + 1;
        for (int i = 0; i < size; i++) {

            System.out.print(item[start % item.length] + " ");
            start++;
        }
    }
}
