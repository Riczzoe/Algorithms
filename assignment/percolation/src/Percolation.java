import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int numberOfOpen;
    private int cols;
    private WeightedQuickUnionUF monteCarlo;
    private WeightedQuickUnionUF full;
    private boolean[][] isOpen;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        numberOfOpen = 0;
        cols = n;
        monteCarlo = new WeightedQuickUnionUF(n * n + 2);
        full = new WeightedQuickUnionUF(n * n + 1);
        isOpen = new boolean[n][n];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);
        if (isOpen[row - 1][col - 1]) {
            return;
        }

        isOpen[row - 1][col - 1] = true;
        numberOfOpen++;

        if (row == 1) {
            monteCarlo.union(getIndex(row, col), 0);
            full.union(getIndex(row, col), 0);
        }
        if (row == cols) {
            monteCarlo.union(getIndex(row, col), cols * cols + 1);
        }

        connectAdjacentSites(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        check(row, col);
        return isOpen[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        check(row, col);
        return full.find((row - 1) * cols + col) == full.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return monteCarlo.find(0) == monteCarlo.find(cols * cols + 1);
    }

    private void check(int row, int col) {
        if (row < 1 || row > cols || col < 1 || col > cols) {
            throw new IllegalArgumentException();
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1)* cols + col;
    }

    private void connectAdjacentSites(int row, int col) {
        int index = getIndex(row, col);

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isIllegal(row + i, col + j) && isOpen(row + i, col + j) &&
                    Math.abs(i - j) == 1) {
                    monteCarlo.union(index, getIndex(row + i, col + j));
                    full.union(index, getIndex(row + i, col + j));
                }
            }
        }
    }

    private boolean isIllegal(int row, int col) {
        if (row < 1 || row > cols || col < 1 || col > cols) {
            return false;
        }
        return true;
    }


    // test client (optional)
    public static void main(String[] args) {

    }
}