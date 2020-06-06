import java.util.Arrays;
public class Merge{

	public void display(int[] a){for(int i = 0; i < a.length; i++) System.out.print(a[i] + " ");}
	

	public boolean isSorted(int[] a){
		for(int i=0; i<a.length-1;i++){
			if(a[i] > a[i+1]) return false;
		}
		return true;
	}


	public void copyArray(int[] a, int[] b, int low, int high){
		for(int i = low; i <= high; i++) b[i] = a[i];
	}

	
	public void merge(int[] a, int[] aux, int low, int mid, int high){
		copyArray(a, aux, low, high);
		int i = low;
		int j = mid;
		for(int k = low; k <= high; k++){
			if (i > mid) a[k] = aux[j++];
			else if (j > high) a[k] = aux[i++]; 
			else if (aux[j] < aux[i]) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}


	public void sort(int[] a, int[] aux, int low, int high){
		if(high <= low) return;
		int mid = low + (high - low) / 2;
		sort(a, aux, low, mid);
		sort(a, aux, mid + 1, high);
		merge(a, aux, low, mid + 1, high);
	}


	public static void main(String[] args){

		int[] a = {5, 3,0,2, 1};
		Merge m = new Merge();
		int[] aux = new int[a.length];
		m.sort(a, aux, 0, a.length - 1);
		m.display(a);
	}
}