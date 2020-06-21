import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private State[][] grid;
    private final int n;
    private int openedSites;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private enum State {
        OPEN,
        BLOCK
    }

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        this.n = n;
        this.grid = new State[n][n];
        this.openedSites = 0;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = State.BLOCK;
            }
        }
    }

    private int getPosition(int row, int col) {
        return (row - 1) * this.n + (col - 1);
    }

    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > this.n || col > this.n) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        if (this.grid[row - 1][col - 1] == State.BLOCK) {
            this.grid[row - 1][col - 1] = State.OPEN;
            this.openedSites++;

            if (row == 1) {
                weightedQuickUnionUF.union(getPosition(row, col), this.n * this.n);
            }
            if (row == this.n) {
                weightedQuickUnionUF.union(getPosition(row, col), this.n * this.n + 1);
            }

            if (col < n && isOpen(row, col + 1)) {
                weightedQuickUnionUF.union(getPosition(row, col), getPosition(row, col + 1));
            }

            if (col > 1 && isOpen(row, col - 1)) {
                weightedQuickUnionUF.union(getPosition(row, col), getPosition(row, col - 1));
            }

            if (row < n && isOpen(row + 1, col)) {
                weightedQuickUnionUF.union(getPosition(row, col), getPosition(row + 1, col));
            }

            if (row > 1 && isOpen(row - 1, col)) {
                weightedQuickUnionUF.union(getPosition(row, col), getPosition(row - 1, col));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > this.n || col > this.n) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        return (this.grid[row - 1][col - 1] == State.OPEN);
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > this.n || col > this.n) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        return weightedQuickUnionUF.find(getPosition(row, col)) == weightedQuickUnionUF.find(n * n);
    }

    public int numberOfOpenSites() {
        return this.openedSites;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.find(n * n) == weightedQuickUnionUF.find(n * n + 1);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();

        Percolation percolation = new Percolation(n);

        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            percolation.open(i, j);
        }

        StdOut.println(percolation.numberOfOpenSites() + " open sites");

        if (percolation.percolates()) {
            StdOut.println("Percolates");
        } else {
            StdOut.println("Does not percolate");
        }

        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(percolation.isFull(i, j));
        }
    }
}
