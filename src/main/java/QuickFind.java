public class QuickFind {
    private int[] id;

    public QuickFind(int N){
        id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
    }


    public boolean is_connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for(int i = 0; i < id.length; i++){
            if (id[i] == pid){
                id[i] = qid;
            }
        }
    }



    public static void main(String[] args){
        QuickFind quickFind = new QuickFind(5);
        System.out.println(quickFind.is_connected(2, 3));
        quickFind.union(2, 3);
        System.out.println(quickFind.is_connected(2, 3));
        quickFind.union(3, 4);
        System.out.println(quickFind.is_connected(3, 4));
        System.out.println(quickFind.is_connected(1, 3));



    }
}
