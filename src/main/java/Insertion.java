public class Insertion{

	public int[] sort(int[] a){
		for(int i = 1; i < a.length; i++){
			int j = i - 1;
			int current_ind = i;
			while(j >= 0 && a[j] > a[current_ind]){
				int temp = a[current_ind];
				a[current_ind] = a[j];
				a[j] = temp;
				current_ind = current_ind - 1;
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
		// i.sort(a);
		i.display(i.sort(a));
	}


}