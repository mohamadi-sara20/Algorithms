import java.util.NoSuchElementException;
import java.util.Scanner;

public class Permutation {

    public static void main(String[] args) {
        int c = Integer.parseInt(args[0]);
        Scanner scanner = new Scanner(System.in, "utf-8");
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (scanner.hasNext()) {
            String str = readString(scanner);
            rq.enqueue(str);
        }
        if (rq.size() < c) return;

        for (int j = 0; j < c; j++) {
            System.out.println(rq.dequeue());
        }
    }

    private static String readString(Scanner scanner) {
        try {
            return scanner.next();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'String' value from standard input, "
                    + "but no more tokens are available");
        }
    }
}
