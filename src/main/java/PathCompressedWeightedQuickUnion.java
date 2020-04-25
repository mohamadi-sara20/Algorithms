public class PathCompressedWeightedQuickUnion {
	private int[] id;
	private int[] sz;

	public PathCompressedWeightedQuickUnion(int N){
		id = new int[N];
		for(int i = 0; i < N; i++){
			id[i] = i;
		}
		sz = new int[N];
	}

	private int root(int i){
		while (true){
			if(i != id[i]){
				id[i] = id[id[i]];
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
		if(sz[i] < sz[j]){id[i] = j; sz[j] += sz[i];}
		else if(sz[j] < sz[i]){id[j] = i; sz[i] += sz[j];}
		else {id[i] = j;}
	}

	public void display_id(){
		for(int i = 0; i < id.length; i++){System.out.println(id[i]);}
	}

	public static void main(String[] args){
		PathCompressedWeightedQuickUnion quickunion = new PathCompressedWeightedQuickUnion(10);
		quickunion.union(2, 1);
		System.out.println(quickunion.connected(2, 1));
		quickunion.union(7, 2);
		System.out.println(quickunion.connected(7, 1));
		quickunion.display_id();
	}


}
