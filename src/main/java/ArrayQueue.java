public class ArrayQueue{
	private int N;
	private int B;
	private String[] s;

	public ArrayQueue(){
		s = new String[1];
	}

	public void enqueue(String i){		
		if(N == s.length && N > 0) resize(s.length * 2);
		s[N++] = i;
	}

	public String dequeue(){
		String i = s[B];
		s[B] = null;
		B++;

		if(N - B == (s.length / 4) && N-B > 0){
			resize(s.length / 2);
			B = 0;
			N = 0;

		}
		return i;
	}

	public boolean is_empty(){return s.length == 0;}
	private void resize(int capacity){
		String[] new_s = new String[capacity];
		int j = 0;
		for(int i = B; i < N; i++){
			new_s[j] = s[i];
			j++;
		}
		s = new_s;
	}

	private void display_queue(){
		for(int i = 0; i < s.length; i++) System.out.print(s[i]);
		System.out.println();
	}

	public static void main(String[] args){
		ArrayQueue a = new ArrayQueue();
		a.enqueue("1");
		a.enqueue("2");
		a.enqueue("3");
		a.dequeue();
		a.dequeue();
		a.dequeue();
		a.enqueue("4");
		a.dequeue();
		a.enqueue("5");
		a.enqueue("6");
		a.dequeue();
		a.dequeue();
	}
}