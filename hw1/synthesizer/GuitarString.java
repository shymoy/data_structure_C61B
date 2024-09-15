package synthesizer;


import java.util.Random;

public class GuitarString {
    private static final int SR = 44100;
    private static final double DECAR = .996;

    private Random random = new Random();
    //to store the voice data
    private BoundedQueue<Double> buffer;

    //initalize
    public GuitarString(double frecuency) {
        int capacity = (int) Math.round(SR / frecuency);
        buffer = new ArrayRingBuffer<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }

    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }

        while (!buffer.isFull()) {
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    public void tic() {
        double firstSound = buffer.dequeue();
        double secondSound = buffer.peek();
        double sound = DECAR * 0.5 * (firstSound + secondSound);

        buffer.enqueue(sound);
    }

    public double sample() {
        return buffer.peek();
    }
}
