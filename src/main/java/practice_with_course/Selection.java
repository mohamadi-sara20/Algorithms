public class Selection{
	public int[] sort(int[] arr){
		for(int i = 0; i < arr.length; i++){
			int min_ind = i;
			// Find the min in the remaining
			for(int j = i + 1; j < arr.length; j++){
				if(arr[j] < arr[min_ind]) min_ind = j;
			}

			// Do the exchange only once
			int temp = arr[i];
			arr[i] = arr[min_ind];
			arr[min_ind] = temp;

		}
		return arr;
	}

	public void display(int[] arr){
		for(int i = 0; i < arr.length; i++) System.out.println(arr[i]);
	}

	public static void main(String[] args){
		int[] a = {3,1, 2, 0, 5, 4};
		Selection s = new Selection();
		s.display(s.sort(a));
	}
}