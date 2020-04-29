public class ArrayQueue{
	private int N;
	private int B;
	private String[] s;

	public ArrayQueue(){
		s = new String[1];
	}

	public void enqueue(String i){		
		if(N == s.length  && N > 0) resize(s.length * 2);
		s[N++] = i;
	}

	public String dequeue(){
		String i = s[B];
		s[B] = null;
		B++;
		//when queue is emptied but indices are reset. 
		//when N is 1; e.g., and B is 0; and you perform dequeue, B becomes 1 like N. But they should both be reset. 
		if(B == N) {B = 0; N = 0;}

		if(N - B == (s.length / 4) && N - B > 0){
			resize(s.length / 2);

		}
		return i;
	}

	public boolean is_empty(){return s.length == 0;}
	private void resize(int capacity){
		System.out.print("Resizing to: " );
		System.out.println(capacity);


		String[] new_s = new String[capacity];
		int j = 0;
		for(int i = B; i < N; i++){
			new_s[j] = s[i];
			j++;
		}

		N = N - B;
		B = 0;
		s = new_s;
	}

	private void display_queue(){
		for(int i = 0; i < s.length; i++) System.out.print(s[i]);
		System.out.println();
	}

	private int size(){return s.length;}

	public static void main(String[] args){
		ArrayQueue a = new ArrayQueue();
		a.enqueue("1");
		a.enqueue("2");
		a.enqueue("3");
		a.enqueue("4");
		a.enqueue("5");
		a.enqueue("6");
		a.enqueue("7");
		a.enqueue("8");
		a.dequeue();
		a.dequeue();
		a.dequeue();
		a.dequeue();
		a.dequeue();
		a.dequeue();
		a.dequeue();
		a.dequeue();
		a.enqueue("8");
		a.display_queue();

	}
}