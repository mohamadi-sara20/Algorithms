public class GenericFixedArrayStack <T> {
	private T[] s;
	private int N = 0;

	@SuppressWarnings("unchecked")
	public GenericFixedArrayStack(int capacity){
		s = (T[]) new Object[capacity];
	}

	public void push(T i){
		s[N++] = i;
	}


	//loitering problem -__- hence the second line
	public T pop(){
		T i = s[--N];
		s[N] = null;
		return i;

	}

	public boolean is_empty(){
		return N == 0;
	}

	public void display_stack(){
		for (int i = 0; i < s.length; i ++){
			System.out.println(s[i]);
		}
	}

	public static void main(String[] args){

		GenericFixedArrayStack<Integer> s = new GenericFixedArrayStack<Integer>(7);
		s.push(1);
		System.out.println(s.N);
		s.display_stack();
		s.push(12);
		System.out.println(s.N);
		s.display_stack();

		s.push(4);
		System.out.println(s.N);
		s.display_stack();

		s.pop();

		System.out.println(s.N);
		s.display_stack();

		s.push(9);
		System.out.println(s.N);
		s.display_stack();
		



	}


}
