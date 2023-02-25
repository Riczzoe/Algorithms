import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double RATE = 1.96;
    private double mean;
    private double standardDeviation;
    private int trials;
    private int size;
    private double[] threshold;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }
        threshold = new double[trials];
        this.trials = trials;
        size = n;
        mean = 0;
        standardDeviation = 0;
        experiment();
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return standardDeviation;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - (RATE * standardDeviation) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + (RATE * standardDeviation) / Math.sqrt(trials);
    }

    private void experiment() {
        int times = 0;
        while (times < trials) {
            Percolation percolation = new Percolation(size);
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(size) + 1;
                int col = StdRandom.uniformInt(size) + 1;
                percolation.open(row, col);
            }
            threshold[times] = percolation.numberOfOpenSites() * 1.0/ (size * size);
            times++;
        }
        mean = StdStats.mean(threshold);
        standardDeviation = StdStats.stddev(threshold);
    }
    // test client (see below)
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java-algs4 PercolationStats n T");
            return;
        }
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(n, t);
        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.println("95% confidence interval = [" + percStats.confidenceLo()
                + ", " + percStats.confidenceHi() + "]");
    }

}
