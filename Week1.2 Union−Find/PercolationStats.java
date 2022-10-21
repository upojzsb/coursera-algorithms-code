import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] exampleResult;


    private double confidence_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        exampleResult = new double[trials];

        for (int t = 0; t < trials; t++) {
            Percolation example = new Percolation(n);

            while (!example.percolates()) { // Not percolate
                int indexOfSystem, rowOfSystem, colOfSystem;
                do { // generated position is opened

                    // generated integer fall into [a, b）
                    indexOfSystem = StdRandom.uniformInt(1, n * n + 1);
                    rowOfSystem = (indexOfSystem - 1) / n + 1;
                    colOfSystem = (indexOfSystem - 1) % n + 1;
                } while (example.isOpen(rowOfSystem, colOfSystem));

                example.open(rowOfSystem, colOfSystem);
            }

            // Percolate
            exampleResult[t] = (double) example.numberOfOpenSites() / n / n;
            // StdOut.println(exampleResult[t]);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(exampleResult);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(exampleResult);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - confidence_95 * stddev() / Math.sqrt(exampleResult.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + confidence_95 * stddev() / Math.sqrt(exampleResult.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n, t;

        n = Integer.parseInt(args[0]);
        t = Integer.parseInt(args[1]);


        PercolationStats ps = new PercolationStats(n, t);

        StdOut.printf("%-23s = %f\n", "mean", ps.mean());
        StdOut.printf("%-23s = %f\n", "stddev", ps.stddev());
        StdOut.printf("%-10s = [%f, %f]\n",
                      "95% confidence interval",
                      ps.confidenceLo(),
                      ps.confidenceHi()
        );

    }

}
