import java.util.Iterator;
public class GenericLinkedBag<Item> implements Iterable<Item> {
    private Node first = null;
    private int n;

    private class Node{
        Item item;
        Node next;
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){return current.next != null;}
        public void remove(){ /*not supported here */ }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
            }

    }
    public Iterator<Item> iterator(){return new ListIterator();}
    public boolean is_empty(){return first == null;}
    public int size(){return this.n;}
    public void add(Item i){
        Node oldfirst = first;
        first = new Node();
        first.item = i;
        first.next = oldfirst;
        n++;
    }


    public static void main(String[] args){
        GenericLinkedBag<Integer> s = new GenericLinkedBag<Integer>();
        s.add(1);
        System.out.println(s.size());
        s.add(2);
        s.add(4);
        System.out.println(s.size());
        


    }



}
