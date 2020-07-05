import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solver {
    private final Board board;
    private final int[][] state;
    private int moves = -2;

    //    private static ArrayList<String> seen;
//    private static boolean filterRepeatedStates = false;
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        state = rebuildBoardFromString(initial.toString());
        board = new Board(deepCopy(state));
        //        seen = new ArrayList<>();

    }

    private class Node implements Comparator<Node>, Comparable<Node> {
        private final Board board;
        private final int depth;
        private final Node parent;

        public Node(Board b, Node p, int m) {
            depth = m;
            parent = p;
            board = b;
        }

        public int getDepth() {
            return this.depth;
        }

        public int compare(Node node1, Node node2) {
            if (node1.board.manhattan() + node1.getDepth() > node2.board.manhattan() + node2.getDepth())
                return 1;
            if (node1.board.manhattan() + node1.getDepth() < node2.board.manhattan() + node2.getDepth())
                return -1;
            return 0;
        }

        public int compareTo(Node node2) {
            if (this.board.manhattan() + this.getDepth() > node2.board.manhattan() + node2.getDepth())
                return 1;
            if (this.board.manhattan() + this.getDepth() < node2.board.manhattan() + node2.getDepth())
                return -1;
            return 0;
        }
    }

    private int[][] rebuildBoardFromString(String s) {

        Scanner scanner = new Scanner(s);
        int dim = scanner.nextInt();
        int[][] res = new int[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                res[i][j] = scanner.nextInt();
            }
        }
        return res;
    }


    private static int[][] deepCopy(int[][] array) {
        int[][] copyArray = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            copyArray[i] = Arrays.copyOf(array[i], array.length);
        }
        return copyArray;
    }

    public boolean isSolvable() {
        int inversions = 0;
        ArrayList<Integer> ar = new ArrayList<>();

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] != 0)
                    ar.add(state[i][j]);
            }
        }
        for (int i = 0; i < ar.size(); i++) {
            for (int j = i; j < ar.size(); j++) {
                if (ar.get(i) > ar.get(j))
                    inversions++;
            }
        }

        if (this.board.dimension() % 2 != 0) {
            return inversions % 2 == 0;
        }

        int blankRow = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] == 0) {
                    blankRow = i;
                }
            }
        }
        blankRow++;
        if (blankRow % 2 != 0) {
            if (inversions % 2 != 0)
                return true;
        }
        if (blankRow % 2 == 0) {
            if (inversions % 2 == 0)
                return true;
        }
        return false;
    }

    // min number of moves to solve initial board
    public int moves() {
        // lazy loading
        if(!isSolvable()) return -1;
        if (moves == -2) {
            ArrayList<Board> b = (ArrayList<Board>) solution();
            moves = b.size() - 1;

        }
        return moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
//        seen = null;
        ArrayList<Board> sol = new ArrayList<>();
        MinPQ<Node> possibleStates = new MinPQ<Node>();
        Node initial = new Node(board, null, 0);
        possibleStates.insert(initial);

        Node answer = initial;
        while (!possibleStates.isEmpty()) {
            Node node = possibleStates.delMin();
            if (node.board.isGoal()) {
                answer = node;
                break;
            }
            Iterable<Board> arrayList = node.board.neighbors();
            for (Board x : arrayList) {
                Node node1 = new Node(x, node, node.getDepth() + 1);
                if(node.parent != null && node1.board.equals(node.parent.board)) continue;
                possibleStates.insert(node1);
            }
        }


        while (answer != null) {
            sol.add(answer.board);
            answer = answer.parent;
        }

        ArrayList<Board> reverseSol = new ArrayList<>();

        for (int i = sol.size() - 1; i >= 0; i--) {
            reverseSol.add(sol.get(i));
        }

        return reverseSol;


    }

//    private static boolean isRepeated(int[][] state){
//        if (!filterRepeatedStates)
//            return false;
//
//        String s = "";
//        for(int i = 0; i < state.length;i++){
//            for(int j = 0; j < state[i].length; j++){
//                s = s + state[i][j];
//            }
//        }
//
//        if(seen == null)
//            seen = new ArrayList<>();
//
//        if(seen.contains(s)) return true;
//        seen.add(s);
//        return false;
//    }

    public static void main(String[] args) {

//        int[][] a = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
//                {11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
//                {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
//                {31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
//                {41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
//                {51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
//                {61, 62, 63, 64, 65, 66, 67, 68, 69, 70},
//                {71, 72, 73, 74, 75, 76, 77, 78, 79, 80},
//                {81, 82, 83, 84, 85, 86, 87, 88, 89, 90},
//                {91, 92, 93, 94, 95, 96, 97, 98, 99, 0}
//        };
        int[][] a = {{1,0}, {3,2} };
        Board b = new Board(a);
        Solver s = new Solver(b);
        System.out.println(s.moves());
    }
}
