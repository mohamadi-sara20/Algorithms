import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Item[] s;
    private int n;
    private int b;

    // construct an empty deque
    public Deque() {
        s = (Item[]) new Object[1];
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private int j = 0;

        public boolean hasNext() {
            int i = j + b + 1;
            boolean h = i < n;
            return h;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item e = s[j + b + 1];
            j++;
            return e;
        }
    }


    // is the deque empty?
    public boolean isEmpty() {
        return n - b - 1 <= 0;
    }

    // return the number of items on the deque
    public int size() {
        if (isEmpty()) return 0;
        return n - b - 1;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null item");
        }
        if (b == -1) {
            resize(s.length * 4);
        }
        if (b == n) {
            n++;
        }
        s[b--] = item;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null item");
        }
        if (n == s.length && n > 0) {
            resize(s.length * 4);
        }
        if (b == n) {
            b--;
        }
        s[n++] = item;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("empty deque");
        }
        if (n - (b + 1) == s.length / 8) {
            resize(s.length / 4);
        }
        Item e = s[++b];
        s[b] = null;
        if (e == null) {
            throw new java.util.NoSuchElementException("empty deque");
        }
        return e;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("empty deque");
        }
        if (n - (b + 1) == s.length / 8) {
            resize(s.length / 4);
        }
        Item e = s[--n];
        s[n] = null;
        if (e == null) {
            throw new java.util.NoSuchElementException("empty deque");
        }
        return e;
    }

    private void resize(int capacity) {

        Item[] newS = ((Item[]) new Object[capacity]);
        int j = capacity / 2;
        for (int i = b + 1; i < n; i++) {
            newS[j] = s[i];
            j++;
        }
        s = newS;
        n = j;
        b = (capacity / 2) - 1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addFirst("5");
        deque.addFirst("6");
        deque.addFirst("7");
        deque.addFirst("8");

    }
}
