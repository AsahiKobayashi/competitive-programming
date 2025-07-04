interface Monoid<T> {
    T e();
    T op(T a, T b);
}

class SqrtRootDecomposition<T> {

    int sqrt , size ;
    T [] data ;
    T [] blocks ;
    Monoid<T> monoid ;

    @SuppressWarnings("unchecked")
    SqrtRootDecomposition(int n , Monoid<T> monoid) {
        this.sqrt = 512 ;
        this.size = (n + sqrt - 1) / sqrt ;
        this.data = (T []) new Object[size * sqrt];
        this.blocks = (T []) new Object[size];
        this.monoid = monoid;
        Arrays.fill(data , monoid.e());
        Arrays.fill(blocks , monoid.e());
    }
  
    public void update(int idx , T value) {
        data[idx] = value ;
        T upd = monoid.e();
        int l = (idx / sqrt) * sqrt , r = l + sqrt ;
        blocks[idx / sqrt] = monoid.e();
        for(int i = l ; i < r ; i ++)
            upd = monoid.op(upd , data[i]);
        blocks[idx / sqrt] = upd;
    }

    public T query(int l , int r) {
        return calc(l, r - 1);
    }

    private T calc(int l , int r) {
        int leftBlockId = l / sqrt , rightBlockId = r / sqrt ;
        T res = monoid.e() ;
        if(leftBlockId == rightBlockId) res = same(l , r);
        else if(rightBlockId - leftBlockId == 1) res = monoid.op(calcLeft(l) , calcRight(r));
        else res = monoid.op(monoid.op(calcLeft(l) , calcMid(leftBlockId , rightBlockId) ) , calcRight(r));
        return res ;
    }

    private T calcLeft(int l) { 
        T res = monoid.e() ;
        for(int i = (l % sqrt) , j = 0 ; i < sqrt ; i ++ , j ++)
            res = monoid.op(res , data[l + j]);
        return res ; 
    }

    private T calcRight(int r) {
        T res = monoid.e() ;
        for(int i = 0 , j = 0 ; i <= (r % sqrt) ; i ++ , j ++) {
            res = monoid.op(res , data[r - j]);
        }
        return res ;
    }

    private T calcMid(int lb , int rb) {
        T res = monoid.e() ;
        for(int i = lb + 1 ; i < rb ; i ++)
            res = monoid.op(res , blocks[i]);
        return res ; 
    }

    private T same(int l , int r) {
        T res = monoid.e();
        for(int i = l ; i <= r ; i ++)
            res = monoid.op(res , data[i]);
        return res ;
    }

    public void debugData (){
        for(int i = 0 ; i < data.length ; i ++)
            System.out.print(query(i, i + 1)+" ");
        System.out.println();
    }

    public void debugblock (){
        for(int i = 0 ; i < blocks.length ; i ++)
            System.out.print(blocks[i]+" ");
        System.out.println();
    }

}
