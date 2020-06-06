public class QuickUnion {
	private int[] id;

	public QuickUnion(int N){
		id = new int[N];
		for(int i = 0; i < N; i++){
			id[i] = i;
		}
	}

	private int root(int i){
		while (true){
			if(i != id[i]){
				i = id[i];
			}
			else break;
		}
		return i;
	}

	public boolean connected(int p, int q){
		return root(p) == root(q);
	}

	public void union(int p, int q){
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}

	public static void main(String[] args){
		QuickUnion quickunion = new QuickUnion(10);
		quickunion.union(2, 1);
		quickunion.union(7, 2);
		System.out.println(quickunion.connected(7, 1));
	}




}
