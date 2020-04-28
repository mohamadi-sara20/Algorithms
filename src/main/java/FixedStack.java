public class FixedStack {
	private String[] s;
	private int N = 0;

	public FixedStack(int capacity){
		s = new String[capacity];
	}

	public void push(String i){
		s[N++] = i;
	}


	//loitering problem -__- hence the second line
	public String pop(){
		String i = s[--N];
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

		FixedStack s = new FixedStack(7);
		s.push("1");
		System.out.println(s.N);
		s.display_stack();
		s.push("2");
		System.out.println(s.N);
		s.display_stack();

		s.push("3");
		System.out.println(s.N);
		s.display_stack();

		s.pop();

		System.out.println(s.N);
		s.display_stack();

		s.push("4");
		System.out.println(s.N);
		s.display_stack();
		



	}


}
