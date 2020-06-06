import java.util.Arrays;

public class Search {
	private int[] id;

	public Search(int[] id){
		this.id = id;
	}

	public int binarySearch(int num){
		int start = 0;
		int end = id.length;
		int mid;
		if(id.length == 0){return -1;}
		while(start <= end){
			mid = (start + end) / 2;
			if(num == id[mid]){return mid;}
			if(num > id[mid]){start = mid + 1;}
			if(num < id[mid]){end = mid - 1;}
		}
		return -1;
		
	}

	public int recursiveBinarySearch(int num, int start, int end){
		int mid = (start + end) / 2;
		if(id[mid] == num){return mid;}
		else if(num > id[mid]){return recursiveBinarySearch(num, mid + 1, end);}
		else if(num < id[mid]){return recursiveBinarySearch(num, start, mid - 1);}
		return -1;
	}



	

	public static void main(String[] args){
		int[] d = {10, 20, 30, 40, 50, 60, 70};
		Search search = new Search(d);



		
	}


}
