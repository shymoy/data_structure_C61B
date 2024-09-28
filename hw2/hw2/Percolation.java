package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private final int size;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufHelper;
    private final int topSite;
    private final int bottomSite;
    private int openSiteNumber;

    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException("Number of percolations must be greater than 0");
        }

        //initial all element
        openSiteNumber = 0;
        this.size = N;
        uf = new WeightedQuickUnionUF(N * N + 1);
        ufHelper = new WeightedQuickUnionUF(N * N + 2);
        topSite = N * N;
        bottomSite = N * N + 1;
        grid = new boolean[N][N];

        //to be all blocked
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    private void validateIndices(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private int index(int i, int j) {
        return i * size + j;
    }

    public void open(int row, int col) {
        validateIndices(row, col);

        if (!grid[row][col]) {
            if (row == 0) {
                uf.union(index(row, col), topSite);
                ufHelper.union(index(row, col), topSite);
            }

            if (row == size - 1) {
                ufHelper.union(index(row, col), bottomSite);
            }

            grid[row][col] = true;
            openSiteNumber++;
        }

        connectIfopen(row + 1, col, row, col);
        connectIfopen(row - 1, col, row, col);
        connectIfopen(row, col + 1, row, col);
        connectIfopen(row, col - 1, row, col);

    }

    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return uf.connected(index(row, col), topSite);
    }

    public int numberOfOpenSites() {
        return openSiteNumber;
    }

    public boolean percolates() {
        return ufHelper.connected(topSite, bottomSite);
    }

    private void connectIfopen(int row1, int col1, int row2, int col2) {
        if (row1 >= 0 && row1 < size
                && col1 >= 0 && col1 < size
                && isOpen(row1, col1) && isOpen(row2, col2)) {
            uf.union(index(row1, col1), index(row2, col2));
            ufHelper.union(index(row1, col2), index(row2, col1));
        }
    }

    public static void main(String[] args) {

    }
}
