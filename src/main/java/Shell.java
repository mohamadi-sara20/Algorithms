public class Shell{
	private int[] gap_arr = {1, 3, 5, 7, 16};

	public int[] sort(int[] a){
		for(int gap: gap_arr){
			for(int i = gap; i < a.length; i++){
				for(int j= i; j >= gap && a[j - gap] > a[j]; j=j-gap){
					int temp = a[j];
					a[j] = a[j-gap];
					a[j-gap] = temp;
				}
			}
		}
		return a;

	}



	public void display(int[]a){
	        for(int i=0;i<a.length;i++)System.out.println(a[i]);
	    }



    public static void main(String[] args) {
        int[] a = {10,9,8,7,6,5,4,3,2,1};
        Shell shell = new Shell();
        shell.display(shell.sort(a));

        
    }



}