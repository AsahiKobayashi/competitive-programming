package lib.structure.UnionFindTree;

import java.util.Arrays;

/**
 * UnionFind : 素集合データ構造
 * 
 * 集合おいて要素のグループ分け、属しているかを各操作、均し計算量(α(N))で判定します。
 */
class UnionFindTree {

        private int [] parents , size;
        private int groups;

        /**
         * @description 各要素の初期化（すべて独立した状態）
         * @param n
         */
        UnionFindTree(int n) {
                this.parents = new int[n];
                this.size = new int[n];
                this.groups = n;
                Arrays.fill(size , 1);
                Arrays.setAll(parents,i -> i);
        }

        /**
         * @description 連結成分数を返します
         * @order 1
         * @return
         */
        public int size(){
                return groups;
        }

        /**
         * @description 指定した要素が属するグループの要素数を返します
         * @order α(N)
         * @param x
         * @return
         */
        public int count(int x){ 
                return size[root(x)] ; 
        }

        /**
         * @description 要素xと要素yが同じグループに属するか返します
         * @order α(N)
         * @param x
         * @param y
         * @return
         */
        public boolean same(int x ,int y){ 
                return root(x) == root(y) ; 
        }

        /**
         * @description 指定した要素の親を返します
         * @order α(N)
         * @param x
         * @return
         */
        public int root(int x){
                if(x == parents[x]) return x ;
                else parents[x] = root(parents[x]);
                return parents[x];
        }

        /**
         * @description 要素lと要素rを同じグループに属させます
         * @order α(N)
         * @param l
         * @param r
         */
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
