import java.util.Iterator;
public class GenericResizingBag<Item> implements Iterable<Item>{
	private int N;
	private Item[] s;

	@SuppressWarnings("unchecked")
	public GenericResizingBag(){
		s = ((Item[])new Object[1]);
	}

	public Iterator<Item> iterator(){return new ListIterator();}
	private class ListIterator implements Iterator<Item>{
		private int i = N;
		public boolean hasNext(){return i > 0;}
		public void remove(){/**/}
        public Item next(){
        	return s[--i];
        }
	}

	@SuppressWarnings("unchecked")
	public void resize(int capacity){
		Item[] new_s = ((Item[])new Object[capacity]);
		for(int i = 0; i < N; i ++){
			new_s[i] = s[i];
		}
		s = new_s;
	}


	public void add(Item i){
		if (N == s.length) resize(s.length * 2);
		s[N++] = i;
	}
	public int size(){return N;}
	public boolean is_empty(){return N == 0;}

	public static void main(String[] args){
		GenericResizingBag<Integer> s = new GenericResizingBag<Integer>();
		s.add(1);
		s.add(2);
		System.out.println(s.size());
		System.out.println(s.is_empty());
		

	}



}
