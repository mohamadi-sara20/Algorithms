public class Insertion{

	public int[] sort(int[] a){
		for(int i = 1; i < a.length; i++){
			int j = i;
			//compare part
			while(j >= 1 && a[j-1] > a[j]){
				//exchange part
				int temp = a[j-1];
				a[j-1] = a[j];
				a[j] = temp;
				j = j - 1;
			}
		}
	return a;
	}

	public void display(int[] arr){
		for(int i = 0; i < arr.length; i++) System.out.print(arr[i]);
		System.out.println();
	}

	public static void main(String[] args){
		Insertion i = new Insertion();
		int[] a = {5, 4, 3, 1};
		int[] b = {5, 9, 4, 1, 8, 4, 2};
		// i.sort(a);
		i.display(i.sort(a));
		i.display(i.sort(b));

	}


}