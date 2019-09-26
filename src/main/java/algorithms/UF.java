package algorithms;
public class UF {
    private int[] id;

    public UF(int N){this.id = new int[N];
    for(int i=0; i < N; i++){
        id[i] = i;
        }

    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for(int i = 0; i < this.id.length; i++){
            if (id[i] == pid){
                id[i] = qid;
            }
        }
    }

    private int root(int p){
        while (p != id[p]){
            p = id[p];
        }
        return p;
    }

    public boolean quickFind(int p, int q){return root(p) == root(q);}

    public void quickUnion(int p, int q){
        int pid = root(p);
        int qid = root(q);
        id[pid] = qid;
    }
    public void weightedQuickUnion(int p, int q){

    }
}
