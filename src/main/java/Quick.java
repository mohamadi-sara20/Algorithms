import java.util.Random;
public class Quick {

	public int partition(Character[] a, int lo, int high){
		int i = lo + 1;
		int j = high;
		System.out.println("Partitioned with low: " + lo + " and high: " + high);


		while (true){
			while (a[i].compareTo(a[lo]) < 0){i++; if (i >= high) break;}
			while (a[j].compareTo(a[lo]) > 0){j--; if (j <= lo) break;}
		
			if (j <= i) break;

			Character temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}

		System.out.println("final J: " + (a[j]));
		display(a);
		Character temp = a[j];
		a[j] = a[lo];
		a[lo] = temp;
		display(a);

		return j;

	}


	public void sort(Character[]a, int lo, int high){
		if(high - lo < 1) return;
		int pivot = partition(a, lo, high);
		sort(a, lo, pivot - 1);
		sort(a, pivot + 1, high);

	}
	
	public void display(Character[] a){
		for(int i = 0; i < a.length; i++) System.out.print(a[i] + " "); 
		System.out.println("");
	}

	public static void main(String[] args){
		Character[] example = {'K', 'C', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q', 'R', 'X', 'O', 'S'};
		Quick q = new Quick();
		q.sort(example, 0, example.length - 1);
		q.display(example);

	}

}