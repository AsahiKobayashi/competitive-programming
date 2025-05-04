package lib.math;

/**
 * Pascal's Triangle : パスカルの三角形
 * 
 * 前処理O(N^2)でnCrの値取得をO(1)で行います
 * combination[n][r] = nCr
 * 
 * 制約: N <= 3000
 */
class PascalsTriangle {
        
        /**
         * @description パスカルの三角形を構築します
         * @order N^2
         * @param n
         * @return
         */
        public final long [][] pascal_ncr(final int n) {
                long[][] combination = new long[n][n];
                for(int i = 0; i < n ; i ++)
                        combination[i][0] = 1;
                for(int i = 1; i < n ; i ++)
                        for(int j = 1; j <= i; j ++ )
                                combination[i][j] = combination[i-1][j-1] + combination[i-1][j];
                return combination ;
        }

}
