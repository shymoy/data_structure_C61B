package synthesizer;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Iterator;

public class TestArrayRingBuffer {
    ArrayRingBuffer<Integer> L = new ArrayRingBuffer<>(10);


    @Test
    public void testEnqueue() {
        L.enqueue(1);
        L.enqueue(5);
        L.enqueue(6);
        L.enqueue(2);
        L.enqueue(12);
        L.enqueue(9);
        int expect = 1;
        int actual = L.peek();
        assertEquals(expect, actual);
    }

    @Test
    public void testDequeue() {
        L.enqueue(1);
        L.enqueue(5);
        L.enqueue(6);
        L.enqueue(2);
        int expect = L.dequeue();
        int actual = 1;
        assertEquals(expect, actual);
    }

    public static void main(String[] args) {
        ArrayRingBuffer<Integer> L = new ArrayRingBuffer<>(10);
        L.enqueue(1);
        L.enqueue(5);
        L.enqueue(6);
        L.enqueue(2);
        L.enqueue(12);
        L.enqueue(9);
        Iterator<Integer> it = L.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
