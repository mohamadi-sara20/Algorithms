import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private Random r;
    private int n;
    private int c;
    private int[] taken = new int[1];

    // construct an empty randomized queue
    public RandomizedQueue() {
        long seed = System.currentTimeMillis();
        s = ((Item[]) new Object[1]);
        r = new Random();
        r.setSeed(seed);

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (n - c) <= 0;
    }


    // return the number of items on the randomized queue
    public int size() {
        return n - c;
    }

    // add the item
    public void enqueue(Item i) {
        if (i == null) throw new IllegalArgumentException();
        if (n == s.length && n >= 0) randomResize(s.length * 2);
        s[n] = i;
        taken[n] = 0;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item i = fetch(true);
        c++;
        if (size() == (s.length / 4) && s.length > 1) randomResize(s.length / 2);
        return i;
    }

    private void randomResize(int capacity) {
        Item[] newS = ((Item[]) new Object[capacity]);
        int[] newTaken = new int[capacity];

        int j = 0;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (taken[i] != 1) {
                newS[j] = s[i];
                j++;
                newTaken[k] = 0;
                k++;
            }
        }
        for (int p = k; p < newTaken.length; p++) newTaken[p] = 1;
        s = newS;
        taken = newTaken;
        n = j;
        c = 0;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return fetch(false);
    }

    private Item fetch(boolean markOut) {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int p = n - c;
        int chosen = r.nextInt(p);
        int i = 0;
        int j = 0;
        boolean found = false;
        for (i = 0; i < n; i++) {
            if (taken[i] == 1) continue;
            if (j == chosen) {
                found = true;
                break;
            }
            j++;
        }
        if (found) {
            if (markOut)
                taken[i] = 1;
            return s[i];
        } else throw new NoSuchElementException();
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private int i = 0;

        public boolean hasNext() {

            if (i == n) return false;

            if (taken[i] == 0) return true;

            while (taken[i] == 1 && i < n) {
                if (s[i] != null) i = i + 1;
                else if (s[i] == null) return false;
            }

            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            Item e = s[i];
            i++;
            return e;

        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.isEmpty();
        rq.size();
        rq.dequeue();
        rq.enqueue(9);
        System.out.println(rq.size());
//        rq.enqueue("4");
//        rq.enqueue("5");
//        rq.enqueue("6");
//        rq.enqueue("7");
//        rq.dequeue();
//        rq.dequeue();
//        System.out.println(rq.isEmpty());
//        System.out.println(rq.dequeue());
//        System.out.println(rq.dequeue());
//        System.out.println(rq.dequeue());
//        System.out.println(rq.dequeue());
//        System.out.println(rq.dequeue());
//        System.out.println(rq.isEmpty());
//        System.out.println(rq.size());


//        int p = 0;
//        for (int i = 0; i < 4000; i++) {
//            System.out.println("Size: " + rq.size());
//            int si = rq.size();
//            Random r = new Random();
//            p = r.nextInt(4);
//            switch (p) {
//                case 0 | 3:
//                    rq.enqueue("1");
//                    si++;
//                    if (!(si == rq.size())) {
//                        System.out.println("SSSSSS");
//                        break;
//                    }
//                    break;
//
//                case 1:
//                    if (rq.isEmpty()) {
//                        System.out.println("Queue is empty. passing!");
//                    } else {
//                        rq.sample();
//                        si--;
//                        if (!(si == rq.size())) {
//                            System.out.println("SSSSSS");
//                            break;
//                        }
//                    }
//                    break;
//                case 2:
//                    if (rq.isEmpty()) {
//                        System.out.println("Queue is empty. passing!");
//                    } else {
//                        rq.dequeue();
//                        si--;
//                        if (!(si == rq.size())) {
//                            System.out.println("SSSSSS");
//                            break;
//                        }
//                    }

//            }

//        }

    }
}
