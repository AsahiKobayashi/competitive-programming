class UnionFindTree {

    private int [] parents , size;
    private int groups;

    UnionFindTree(int n) {
        this.parents = new int[n];
        this.size = new int[n];
        this.groups = n;
        Arrays.fill(size , 1);
        Arrays.setAll(parents,i -> i);
    }

    public int size(){
        return groups;
    }

    public int count(int x){ 
        return size[root(x)] ; 
    }

    public boolean same(int x ,int y){ 
        return root(x) == root(y) ; 
    }

    public int root(int x){
        if(x == parents[x]) return x ;
        else parents[x] = root(parents[x]);
        return parents[x];
    }

    public void unite(int l ,int r){
        int x = root(l);
        int y = root(r);
        if(x == y) return ;
        if(x < y) {
            int tmp = x;
            x = y ;
            y = tmp;
        }
        int par = x,ch = y;
        groups--;
        size[par] += size[ch];
        parents[ch] = par;
    }

}
