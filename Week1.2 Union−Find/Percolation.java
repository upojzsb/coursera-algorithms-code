import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // The 0-th and n+1-th element in uf is the top and bottom layer
    private WeightedQuickUnionUF uf, ufWithoutBottom;
    private boolean isUFModified;
    private boolean[][] system;
    private int n;
    private int topNode;
    private int bottomNode;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (!validate(n))
            throw new IllegalArgumentException();

        this.n = n;
        topNode = 0;
        bottomNode = n * n + 1;

        // Define and initialize the system
        system = new boolean[n + 1][n + 1]; // Just for convenient, only [1, n]x[1, n] is used
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                system[i][j] = false;
            }
        }

        constructUF();
        uf = new WeightedQuickUnionUF(bottomNode - topNode + 1);
        ufWithoutBottom = new WeightedQuickUnionUF(bottomNode - topNode + 1);
        isUFModified = false;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException();

        if (!isOpen(row, col)) {
            isUFModified = true;
        }

        system[row][col] = true;

        int index = rowcolumn2index(row, col);

        // The 1-st row, connect it with 0
        if (row == 1) {
            uf.union(topNode, index);
            ufWithoutBottom.union(topNode, index);
        }

        // The last row, connect it with n*n+1
        if (row == n) {
            uf.union(bottomNode, index);
            // ufWithoutBottom is not connected with the bottom Node. If it does, the
            // return value of isFull function of graph with multiple channel will be
            // incorrect.
        }

        // Up
        if ((validate(row - 1, col)) && (system[row - 1][col])) {
            uf.union(index, rowcolumn2index(row - 1, col));
            ufWithoutBottom.union(index, rowcolumn2index(row - 1, col));
        }

        // Down
        if ((validate(row + 1, col)) && (system[row + 1][col])) {
            uf.union(index, rowcolumn2index(row + 1, col));
            ufWithoutBottom.union(index, rowcolumn2index(row + 1, col));
        }

        // Left
        if ((validate(row, col - 1)) && (system[row][col - 1])) {
            uf.union(index, rowcolumn2index(row, col - 1));
            ufWithoutBottom.union(index, rowcolumn2index(row, col - 1));
        }

        // Right
        if ((validate(row, col + 1)) && (system[row][col + 1])) {
            uf.union(index, rowcolumn2index(row, col + 1));
            ufWithoutBottom.union(index, rowcolumn2index(row, col + 1));
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException();

        return system[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException();

        if (isUFModified) {
            isUFModified = false;
            constructUF();
        }

        // return uf.find(topNode) == uf.find(rowcolumn2index(row, col));
        return ufWithoutBottom.find(topNode) == ufWithoutBottom.find(rowcolumn2index(row, col));

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ret += system[i][j] ? 1 : 0;
            }
        }

        return ret;
    }

    // does the system percolate?
    public boolean percolates() {
        if (isUFModified) {
            isUFModified = false;
            constructUF();
        }

        return uf.find(topNode) == uf.find(bottomNode);
    }

    // Construct a Union-find data structure
    private void constructUF() {
        // uf = new WeightedQuickUnionUF(bottomNode - topNode + 1);
        // ufWithoutBottom = new WeightedQuickUnionUF(bottomNode - topNode + 1);
        //
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 1; j <= n; j++) {
        //         if (system[i][j]) {
        //             int index = rowcolumn2index(i, j);
        //
        //             // The 1-st row, connect it with 0
        //             if (i == 1) {
        //                 uf.union(topNode, index);
        //                 ufWithoutBottom.union(topNode, index);
        //             }
        //
        //             // The last row, connect it with n*n+1
        //             if (i == n) {
        //                 uf.union(bottomNode, index);
        //                 // ufWithoutBottom is not connected with the bottom Node. If it does, the
        //                 // return value of isFull function of graph with multiple channel will be
        //                 // incorrect.
        //             }
        //
        //             // Up
        //             if ((validate(i - 1, j)) && (system[i - 1][j])) {
        //                 uf.union(index, rowcolumn2index(i - 1, j));
        //                 ufWithoutBottom.union(index, rowcolumn2index(i - 1, j));
        //             }
        //
        //             // Down
        //             if ((validate(i + 1, j)) && (system[i + 1][j])) {
        //                 uf.union(index, rowcolumn2index(i + 1, j));
        //                 ufWithoutBottom.union(index, rowcolumn2index(i + 1, j));
        //             }
        //
        //             // Left
        //             if ((validate(i, j - 1)) && (system[i][j - 1])) {
        //                 uf.union(index, rowcolumn2index(i, j - 1));
        //                 ufWithoutBottom.union(index, rowcolumn2index(i, j - 1));
        //             }
        //
        //             // Right
        //             if ((validate(i, j + 1)) && (system[i][j + 1])) {
        //                 uf.union(index, rowcolumn2index(i, j + 1));
        //                 ufWithoutBottom.union(index, rowcolumn2index(i, j + 1));
        //             }
        //
        //         }
        //     }
        // }
    }

    // Convert the row-column to i-th element
    private int rowcolumn2index(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException();

        return (row - 1) * n + col;
    }

    // Validate the input parameters
    private boolean validate(int row, int col) {
        if ((row < 1) || (row > n) || (col < 1) || (col > n))
            return false;
        return true;
    }

    private boolean validate(int nn) {
        if (nn <= 0)
            return false;
        return true;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 3;
        int r, c;
        Percolation p = new Percolation(n);

        r = 1;
        c = 3;
        StdOut.println(Integer.toString(r) + " " + Integer.toString(c));
        p.open(r, c);
        StdOut.println(p.isOpen(r, c));
        StdOut.println(p.percolates());
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.isFull(r, c));

        r = 2;
        c = 3;
        StdOut.println(Integer.toString(r) + " " + Integer.toString(c));
        p.open(r, c);
        StdOut.println(p.isOpen(r, c));
        StdOut.println(p.percolates());
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.isFull(r, c));


        r = 3;
        c = 3;
        StdOut.println(Integer.toString(r) + " " + Integer.toString(c));
        p.open(r, c);
        StdOut.println(p.isOpen(r, c));
        StdOut.println(p.percolates());
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.isFull(r, c));


        r = 3;
        c = 1;
        StdOut.println(Integer.toString(r) + " " + Integer.toString(c));
        p.open(r, c);
        StdOut.println(p.isOpen(r, c));
        StdOut.println(p.percolates());
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.isFull(r, c));


        r = 2;
        c = 1;
        StdOut.println(Integer.toString(r) + " " + Integer.toString(c));
        p.open(r, c);
        StdOut.println(p.isOpen(r, c));
        StdOut.println(p.percolates());
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.isFull(r, c));


        r = 1;
        c = 1;
        StdOut.println(Integer.toString(r) + " " + Integer.toString(c));
        p.open(r, c);
        StdOut.println(p.isOpen(r, c));
        StdOut.println(p.percolates());
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.isFull(r, c));


    }
}
