package lib.structure.segmenttree;

import java.util.Arrays;

/**
 * SegmentTree : セグメントツリー
 * 
 * 単一更新、区間取得をO(logN)で行う
 */
@SuppressWarnings("unchecked")
class SegmentTree<T> {

      private T[] tree;
      private int n;
      private Monoid<T> monoid;

      /**
       * @description 単位元で埋めます
       * @order N
       * @param n データの大きさ
       * @param monoid 単位元、二項演算のインターフェイス
       */
      public SegmentTree(int n , Monoid<T> monoid) {
            this.n = n;
            this.monoid = monoid;
            this.tree = (T[]) new Object[2 * n];
            Arrays.fill(tree, monoid.e());
      }

      /**
       * @description 指定した要素番号の値を更新します
       * @order logN
       * @param index 更新を行う要素番号 
       * @param value 更新後の値
       */
      public void update(int index, T value) {
            index += n;
            tree[index] = value;
            while (index > 1) {
                  index /= 2;
                  tree[index] = monoid.op(tree[2 * index], tree[2 * index + 1]);
            }
      }

      /**
       * @description 単一取得
       * @order logN
       * @param index 取得する要素番号
       * @return
       */
      public T query(int index) {
            return query(index,index+1);
      }

      /**
       * @description 区間[l,r)の二項演算の集計を取得
       * @order logN
       * @param index 取得する要素番号
       * @return
       */
      public T query(int l, int r) {
            T resLeft = monoid.e();
            T resRight = monoid.e();
            l += n;
            r += n;
            while (l < r) {
                  if ((l & 1) == 1) {
                        resLeft = monoid.op(resLeft, tree[l]);
                        l++;
                  }
                  if ((r & 1) == 1) {
                        r--;
                        resRight = monoid.op(tree[r], resRight);
                  }
                  l /= 2;
                  r /= 2;
            }
            return monoid.op(resLeft, resRight);
      }

}

/**
 * Monoid : モノイド
 * 
 * 単位元と二項演算を定義します
 */
interface Monoid<T> {

      /**
       * @description 集合Sにおいて任意の元aに対し任意の演算*で (a * e) = (e * a) = a を満たすeの値を定義
       * @return
       */
      public T e ();

      /**
       * @description 集合Sにおいて任意の元a,b,cに対し (a * b) * c = a * (b * c) を満たす演算*を定義 
       * @param a
       * @param b
       * @return
       */
      public T op (T a , T b);
      
}

