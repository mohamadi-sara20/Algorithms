public class ResizingStack {
	private int N;
	private String[] s;

	public ResizingStack(){
		s = new String[1];
	}

	public void resize(int capacity){
		String[] new_s = new String[capacity];
		for(int i = 0; i < N; i ++){
			new_s[i] = s[i];
		}
		s = new_s;
	}

	public String pop(){
		String i = s[--N];
		s[N] = null;
		if(N == s.length / 4 && N > 0) {resize(s.length/2);}
		return i;
	}

	public void push(String i){
		if (N == s.length) resize(s.length * 2);
		s[N++] = i;
	}

	public boolean is_empty(){return N == 0;}

	public static void main(String[] args){
		ResizingStack s = new ResizingStack();
		System.out.println(s.N);
		s.push("1");
		System.out.println(s.N);
		s.push("2");
		System.out.println(s.N);
		s.push("3");
		s.push("3");
		System.out.println(s.N);
		System.out.println(s.pop());
		System.out.println(s.N);
		System.out.println(s.pop());
		System.out.println(s.N);

	}



}
