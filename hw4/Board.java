import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private int[][] tiless;

    public Board(int[][] tiles) {
        if (tiles == null) throw new IllegalArgumentException();
        tiless = deepCopy(tiles);
    }

    public String toString() {
        StringBuilder s = new StringBuilder(tiless.length + "\n");

        for (int i = 0; i < tiless.length; i++) {
            for (int j = 0; j < tiless[i].length; j++) {
                s.append(tiless[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public int dimension() {
        return tiless.length;
    }

    public int hamming() {
        int counter = 0;
//        if (tiless[tiless.length - 1][tiless.length - 1] != 0) counter++;
        for (int i = 0; i < tiless.length; i++) {
            for (int j = 0; j < tiless[i].length; j++) {
                if (i == j && j == tiless.length - 1) {
                    continue;
                } else if (tiless[i][j] != i + j + 1 + ((tiless.length - 1) * i)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int manhattan() {
        int manhattanDist = 0;
        for (int i = 0; i < tiless.length; i++) {
            for (int j = 0; j < tiless[i].length; j++) {
                if (tiless[i][j] == 0) continue;
                else {
                    if (tiless[i][j] != i + j + 1 + ((tiless.length - 1) * i)) {
                        int row = 0;
                        int col = 0;
                        if (tiless[i][j] % dimension() == 0) {
                            col = dimension() - 1;
                            row = tiless[i][j] / (dimension() + 1);
                        } else {
                            row = tiless[i][j] / dimension();
                            col = Math.abs(tiless[i][j] - (tiless.length * row) - 1);
                        }

                        manhattanDist += Math.abs(row - i);
                        manhattanDist += Math.abs(col - j);
                    }
                }
            }
        }
        return manhattanDist;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (getClass() != y.getClass()) return false;
        Board board = (Board) y;
        if (this.dimension() != board.dimension()) return false;
        for (int i = 0; i < tiless.length; i++) {
            for (int j = 0; j < tiless[i].length; j++) {
                if (tiless[i][j] != board.tiless[i][j]) return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<Board>();

        int blankRow = 0;
        int blankCol = 0;
        int temp;

        for (int i = 0; i < tiless.length; i++) {
            for (int j = 0; j < tiless[i].length; j++) {
                if (tiless[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
            }
        }

        if (blankRow != tiless.length - 1) {

            int[][] state = deepCopy(tiless);
            temp = state[blankRow][blankCol];
            state[blankRow][blankCol] = state[blankRow + 1][blankCol];
            state[blankRow + 1][blankCol] = temp;
            Board b = new Board(state);
            neighbors.add(b);
        }
        if (blankRow != 0) {
            int[][] state = deepCopy(tiless);
            temp = state[blankRow][blankCol];
            state[blankRow][blankCol] = state[blankRow - 1][blankCol];
            state[blankRow - 1][blankCol] = temp;
            Board b = new Board(state);
            neighbors.add(b);
        }

        if (blankCol != 0) {
            int[][] state = deepCopy(tiless);
            temp = state[blankRow][blankCol];
            state[blankRow][blankCol] = state[blankRow][blankCol - 1];
            state[blankRow][blankCol - 1] = temp;
            Board b = new Board(state);
            neighbors.add(b);
        }

        if (blankCol != tiless.length - 1) {
            int[][] state = deepCopy(tiless);
            temp = state[blankRow][blankCol];
            state[blankRow][blankCol] = state[blankRow][blankCol + 1];
            state[blankRow][blankCol + 1] = temp;
            Board b = new Board(state);
            neighbors.add(b);
        }
        return neighbors;

    }

    public Board twin() {
        Board twin = new Board(deepCopy(tiless));
        int temp;
        for (int i = 0; i < twin.tiless.length; i++) {
            for (int j = 0; j < twin.tiless[i].length - 1; j++) {
                if (twin.tiless[i][j] != 0 && twin.tiless[i][j + 1] != 0) {
                    temp = twin.tiless[i][j];
                    twin.tiless[i][j] = twin.tiless[i][j + 1];
                    twin.tiless[i][j + 1] = temp;
                    return twin;
                }
            }
        }
        return null;
    }


    private static int[][] deepCopy(int[][] array) {
        int[][] copyArray = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            copyArray[i] = Arrays.copyOf(array[i], array.length);
        }
        return copyArray;
    }

    public static void main(String[] args) {


        int[][] a = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
                {41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
                {51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
                {61, 62, 63, 64, 65, 66, 67, 68, 69, 70},
                {71, 72, 73, 74, 75, 76, 77, 78, 79, 80},
                {81, 82, 83, 84, 85, 86, 87, 88, 89, 90},
                {91, 92, 93, 94, 95, 96, 97, 98, 99, 0}
        };

        Board b = new Board(a);
        System.out.println(b.isGoal());
    }
}
