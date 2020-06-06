import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final double[] threshold;

    public PercolationStats(int n, int trials) {
        double current_threshold;
        int randRow, randCol;
        threshold = new double[trials];
        this.trials = trials;
        if (n < 1 && trials < 1)
            throw new IllegalArgumentException();
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                randRow = StdRandom.uniform(n);
                randCol = StdRandom.uniform(n);
                percolation.open(randRow + 1, randCol + 1);
            }
            current_threshold = percolation.numberOfOpenSites() / (double) (n * n);
            threshold[i] = current_threshold;
        }
    }

    public double mean() {
        return StdStats.mean(this.threshold);
    }

    public double stddev() {
        return StdStats.stddev(this.threshold);
    }

    public double confidenceLo() {
        return this.mean() - CONFIDENCE_95 * (this.stddev()) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return this.mean() + CONFIDENCE_95 * (this.stddev()) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        if (args.length < 2)
            throw new IllegalArgumentException("Not enought arguments!");
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, T);
        System.out.println(String.format("mean\t\t\t = %f", percolationStats.mean()));
        System.out.println(String.format("stddev\t\t\t = %f", percolationStats.stddev()));
        System.out.println(String.format("95%% confidence interval\t\t\t = [%f - %f]",
                percolationStats.confidenceLo(), percolationStats.confidenceHi()));
    }
}
