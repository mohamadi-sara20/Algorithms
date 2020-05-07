import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        int c = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            rq.enqueue(str);
        }
        if (rq.size() < c) return;

        for (int j = 0; j < c; j++) {
            StdOut.println(rq.dequeue());
        }
    }
}


