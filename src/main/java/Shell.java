public class Shell{
	private int[] gap_arr = {1, 3, 7};

	public int[] sort(int[] a){
		for(int gap: gap_arr){
			for(int i = 0; i < a.length; i++){
				for(int j= i; j >= gap; j=j-gap){
				
					if(a[j - gap] > a[j])
						{
							int temp = a[j];
							a[j] = a[j-gap];
							a[j-gap] = temp;
						}

					else continue;
				}
			}
		}
		return a;
	}



	public int[] my_shell_sort(int[] a){

		for(int h: gap_arr){

			for(int i = 0; i < a.length; i++){
				int j = i;
				//compare part
				while(j >= h && a[j-h] > a[j]){
					//exchange part
					int temp = a[j-h];
					a[j-h] = a[j];
					a[j] = temp;
					j = j - h;
				}
		}
	}
		return a;
	}

	public void display(int[]a){
	        for(int i=0;i<a.length;i++)System.out.print(a[i] + ", ");
	        	System.out.println();
	    }

    public static void main(String[] args) {
        int[] a = {62,83,18,53,7,17,95,86,47,69,25,28,};
        a = {1,2,3,4,5,6,7,8,9,10};
        Shell shell = new Shell();
        shell.display(shell.sort(a));
        shell.display(shell.my_shell_sort(a));
   
    }

}