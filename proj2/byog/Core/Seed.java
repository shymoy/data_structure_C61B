package byog.Core;

import java.util.Random;

public class Seed {
    private long seed;

    public Seed() {
        this.seed = 1;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long n) {
        seed = n;
    }

    public Random getRandom() {
        return new Random(seed);
    }
}
