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
		while(true){
			mid = (start + end) / 2;
			if(num == id[mid]){return mid;}
			else{
				if(num > id[mid]){start = mid + 1;}
				if(num < id[mid]){end = mid - 1;}
				if(start == end && id[start] != num){return -1;}
			}
		}
	}


	

	public static void main(String[] args){
		int[] d = {};
		Search search = new Search(d);
		System.out.println(search.binarySearch(3));
		
	}


}
