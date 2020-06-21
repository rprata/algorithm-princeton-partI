import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int trials;
    private final double[] percolationThresholds;
    private double mean;
    private double stddev;

    public PercolationStats(int n, int trials) {
        if (n <= 0 && trials <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        this.trials = trials;
        this.percolationThresholds = new double[trials];
        this.mean = 0.0;
        this.stddev = 0.0;
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates())
            {
                int randomRow = StdRandom.uniform(1, n + 1);
                int randomCol = StdRandom.uniform(1, n + 1);
                percolation.open(randomRow, randomCol);
            }
            this.percolationThresholds[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        if (this.mean == 0.0)
            this.mean = StdStats.mean(percolationThresholds);
        return this.mean;
    }

    public double stddev() {
        if (this.stddev == 0.0)
            this.stddev = StdStats.stddev(percolationThresholds);
        return this.stddev;
    }

    private double error() {
        return 1.96 * this.stddev() / Math.sqrt(trials);
    }
    public double confidenceLo() {
        return this.mean() - this.error();
    }

    public double confidenceHi() {
        return this.mean() + this.error();
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, t);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
