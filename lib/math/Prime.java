package lib.math;

import java.util.HashMap;
import java.util.Map;

class Prime {

        private int [] prime ;

        /**
         * @description エラトステネスの篩を初期化に行います
         * @order NloglogN
         * @param n 調べる値の最大値
         */
        public Prime(int n) {
                this.prime = new int[n + 1];
                for(int i = 0 ; i <= n ; i ++) prime[i] = i ;
                        for(int i = 2 ; i * i <= n ; i ++) 
                        if(prime[i] == i) for(int j = 2 ; i * j <= n ; j ++) {
                        if(prime[i * j] > i) 
                                prime[i * j] = i ;
                }
        }

        /**
         * @description 素数であるか判定します
         * @order 1
         * @param n
         * @return
         */
        public boolean isPrime(int n) {
                return 1 < n && (prime[n] == n) ;
        }

        /**
         * @description 素因数分解を行います
         * @order logN
         * @param n
         * @return
         */
        public Map<Integer,Integer> factor(int n) {
                Map<Integer,Integer> fact = new HashMap<>();
                while(n > 1) {
                        fact.put(prime[n] , fact.getOrDefault(prime[n] , 0) + 1);
                        n /= prime[n];
                }
                return fact ;
        }

}
