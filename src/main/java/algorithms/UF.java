package algorithms;
public class UF {
    private int[] id;

    public static void main(String[] args){
        int[] a = {0, 1, 2, 3, 4, 1, 5, 1, 7, 8};
        int b = 8;
        UF uf = new UF(10);
        uf.id = a;
        System.out.println(uf.root(8));
        System.out.println(uf.root(9));
        System.out.println(uf.root(5));


    }

    public UF(int N){
        this.id = new int[N];
        for(int i=0; i < N; i++) id[i] = i;
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for(int i = 0; i < this.id.length; i++){
            if (id[i] == pid)
                id[i] = qid;
        }
    }

    private int root(int p){
        if(p == id[p]) return p;
        else return root(id[p]);
    }


}
