import java.util.Iterator;
public class GenericResizingStack<Item> implements Iterable<Item>{
	private int N;
	private Item[] s;

	@SuppressWarnings("unchecked")
	public GenericResizingStack(){
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

	public Item pop(){
		Item i = s[--N];
		s[N] = null;
		if(N == s.length / 4 && N > 0) {resize(s.length/2);}
		return i;
	}

	public void push(Item i){
		if (N == s.length) resize(s.length * 2);
		s[N++] = i;
	}

	public boolean is_empty(){return N == 0;}
	public static void main(String[] args){
		GenericResizingStack<Integer> s = new GenericResizingStack<Integer>();
		System.out.println(s.N);
		s.push(1);
		System.out.println(s.N);
		s.push(2);
		System.out.println(s.N);
		s.push(3);
		s.push(5);
		System.out.println(s.N);
		System.out.println(s.pop());
		System.out.println(s.N);
		System.out.println(s.pop());
		System.out.println(s.N);

	}



}
