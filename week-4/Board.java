import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int[][] mTiles;
    private final int dimension;
    private int blankRow;
    private int blankCol;

    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        mTiles = copy(tiles);
        dimension = mTiles.length;
        blankRow = -1;
        blankCol = -1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (mTiles[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    return;
                }
            }
        }
    }

    public String toString() {
        StringBuilder board = new StringBuilder();
        board.append(dimension).append("\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board.append(String.format("%2d ", mTiles[i][j]));
            }
            board.append("\n");
        }
        return board.toString();
    }

    public int dimension() {
        return dimension;
    }

    public int hamming() {
        int hammingDistance = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == blankRow && j == blankCol) {
                    continue;
                }
                if (manhattan(i, j) != 0) {
                    hammingDistance++;
                }
            }
        }
        return hammingDistance;
    }
    
    public int manhattan() {
        int manhattanDistance = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == blankRow && j == blankCol) {
                    continue;
                }
                manhattanDistance += manhattan(i, j);
            }
        }
        return manhattanDistance;
    }

    private int manhattan(int row, int col) {
        int destVal = mTiles[row][col] - 1;
        int destRow = destVal / dimension;
        int destCol = destVal % dimension;
        return Math.abs(destRow - row) + Math.abs(destCol - col);
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (this.getClass() != y.getClass())
            return false;

        Board that = (Board) y;
        if (this.blankCol != that.blankCol)
            return false;
        if (this.blankRow != that.blankRow)
            return false;
        if (this.dimension != that.dimension)
            return false;
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                if (this.mTiles[i][j] != that.mTiles[i][j])
                    return false;
        return true;
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();
        if (blankRow > 0) {
            int[][] top = copy(mTiles);
            swap(top, blankRow, blankCol, blankRow - 1, blankCol);
            neighbors.add(new Board(top));
        }
        if (blankRow < dimension - 1) {
            int[][] bottom = copy(mTiles);
            swap(bottom, blankRow, blankCol, blankRow + 1, blankCol);
            neighbors.add(new Board(bottom));
        }
        if (blankCol > 0) {
            int[][] left = copy(mTiles);
            swap(left, blankRow, blankCol, blankRow, blankCol - 1);
            neighbors.add(new Board(left));
        }
        if (blankCol < dimension - 1) {
            int[][] right = copy(mTiles);
            swap(right, blankRow, blankCol, blankRow, blankCol + 1);
            neighbors.add(new Board(right));
        }
        return neighbors;
    }

    public Board twin() {
        int[][] cloned = copy(mTiles);
        if (blankRow != 0) {
            swap(cloned, 0, 0, 0, 1);
        } else {
            swap(cloned, 1, 0, 1, 1);
        }
        return new Board(cloned);
    }

    private void swap(int[][] mat, int rowA, int colA, int rowB, int colB) {
        int swap = mat[rowA][colA];
        mat[rowA][colA] = mat[rowB][colB];
        mat[rowB][colB] = swap;
    }

    private int[][] copy(int[][] mat) {
        int[][] clone = new int[mat.length][];
        for (int i = 0; i < mat.length; i++) {
            clone[i] = mat[i].clone();
        }
        return clone;
    }

    public static void main(String[] args) {

    }
}
