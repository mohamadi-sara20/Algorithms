import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] sites;
    private int numberOfOpenSites = 0;
    private final int n;
    private final WeightedQuickUnionUF uf;
    private int top = -1;
    private int bottom = -1;

    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException();
        this.sites = new boolean[n][n];
        this.n = n;
        this.uf = new WeightedQuickUnionUF(n * n);
    }

    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException();
        if (this.isOpen(row, col))
            return;
        row--;
        col--;
        if (row == 0 || row == n - 1) {
            for (int i = 0; i < n - 1; i++)
                this.uf.union(this.n * row + col, this.n * row + i);
            if (row == 0)
                top = this.uf.find(this.n * row + col);
            if (row == n - 1)
                bottom = this.uf.find(this.n * row + col);
        }

        this.sites[row][col] = true;
        int current = this.n * row + col;
        if (row != 0 && this.isOpen(row, col + 1))
            uf.union(current, this.n * (row - 1) + col);
        if (row != n - 1 && this.isOpen(row + 2, col + 1))
            uf.union(current, this.n * (row + 1) + col);
        if (col != 0 && this.isOpen(row + 1, col))
            uf.union(current, this.n * row + col - 1);
        if (col != n - 1 && this.isOpen(row + 1, col + 2))
            uf.union(current, this.n * row + col + 1);
        this.numberOfOpenSites++;
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || col  < 1 || row > n || col > n)
            throw new IllegalArgumentException();
        return this.sites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException();
        row--;
        col--;

        if (top == -1) return false;
        if(!this.isOpen(row + 1, col + 1)) return false;

        return uf.connected(this.n * row + col, top);
    }

    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }

    public boolean percolates() {
        if (top == -1)
            return false;
        else if (bottom == -1)
            return false;
        return this.uf.connected(top, bottom);
    }
    public static void main(String[] args){
        Percolation percolation = new Percolation(3);
        boolean[][] a = {{true, false, true}, {true, false, true}, {true, false, true}};
        for(int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i][j]) percolation.open(i + 1, j + 1);
            }



        }
        for(int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++)
            System.out.println(percolation.isFull(i + 1, j + 1));

    }
    }

}

