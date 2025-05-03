package lib.technique;

/**
 * StaticRangeSum : 静的区間和取得
 * 
 * 任意の区間[l,r)の総和をO(1)で取得します (静的なクエリに限る)
 */
class StaticRangeSum {

    private long [] sum;

    /**
     * @description : 前処理を行います
     * @order : O(N)
     * @param array
     */
    StaticRangeSum(int [] array) {
        this.sum = new long[array.length + 1];
        for (int i = 0 ; i < array.length ; i ++) 
            sum[i + 1] = sum[i] + array[i];
    }

    /**
     * @description 前処理を行います
     * @order O(N)
     * @param array
     */
    StaticRangeSum(long [] array) {
        this.sum = new long[array.length + 1];
        for (int i = 0 ; i < array.length ; i ++) 
            sum[i + 1] = sum[i] + array[i];
    }

    /**
     * @description 区間 [l,r) の総和を取得します
     * @order O(1)
     * @param l 下限
     * @param r 上限
     * @return 区間 [l,r) の総和
     */
    public long get(int l , int r) {
        return sum[r] - sum[l];
    }

}
