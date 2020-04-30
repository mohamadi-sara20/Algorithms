public class Shell{
	private int[] h;
	public Shell(int[] h){
		this.h = h;
	}

	public int[] sort(int[] a){
		int[] h_arr = {3, 2, 1};
		for(int c= 0; c < this.h.length; c++){
		int h = this.h[c];
		int i = 0;
		while(i < a.length - h){		
		int a1 = a[i];
		int a2 = a[i + h];
		if(a1 > a2){
			a[i] = a2;
			a[i + h] = a1;
			}
		i++;
		}
	}
		return a;
	}

	public void display(int[] arr){
		for(int i = 0; i < arr.length; i++) System.out.print(arr[i]);
		System.out.println();
	}

	public void setH(int[] h){this.h = h;}
	public static void main(String[] args){
		int[] h1 = {3, 2, 1};
		Shell shell = new Shell(h1);
		int[] a = {5, 9, 4, 1, 8, 3, 2};
		shell.display(shell.sort(a));

		int[] h2 = {5, 3, 1};
		shell.setH(h2);
		int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		shell.display(shell.sort(b));

	}


}