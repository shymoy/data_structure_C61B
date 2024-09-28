package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int times;
    private final double[] thresholds;
    private PercolationFactory pf;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        times = T;
        pf = pf;
        thresholds = new double[T];

        for (int i = 0; i < times; i++) {
            Percolation p = pf.make(N);
            int openedSites = 0;

            while (!p.percolates()) {
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }

            openedSites = p.numberOfOpenSites();
            thresholds[i] = (double) openedSites / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLow() {
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / Math.sqrt(times);
    }                                  // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / Math.sqrt(times);
    }                              // high endpoint of 95% confidence interval
}

