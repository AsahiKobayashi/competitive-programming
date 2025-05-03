package lib.structure.segmenttree;

import java.util.Arrays;
import java.util.function.BinaryOperator;

/**
 * SegmentTreeのlong限定ver
 * 機能はSegmentTree同様
 * ジェネリクスを使っていないためこっちの方が早くなります(なるはず)
 * Monoidインターフェイスの代替としてモノイドはコンストラクタで定義してください
 */
class LongSegmentTree {

      private long [] tree;
      private int n;
      private int e;
      private BinaryOperator<Long> op;

      public LongSegmentTree(int n , int e , BinaryOperator<Long> op) {
            this.n = n;
            this.e = e;
            this.op = op;
            this.tree = new long[2 * n];
            Arrays.fill(tree, e);
      }

      public void update(int index, int value) {
            index += n;
            tree[index] = value;
            while (index > 1) {
                  index /= 2;
                  tree[index] = op.apply(tree[2 * index], tree[2 * index + 1]);
            }
      }

      public long query(int index) {
            return query(index,index+1);
      }

      public long query(int l, int r) {
            long resLeft = e;
            long resRight = e;
            l += n;
            r += n;
            while (l < r) {
                  if ((l & 1) == 1) {
                        resLeft =  op.apply(resLeft, tree[l]);
                        l++;
                  }
                  if ((r & 1) == 1) {
                        r--;
                        resRight =  op.apply(tree[r], resRight);
                  }
                  l /= 2;
                  r /= 2;
            }
            return  op.apply(resLeft, resRight);
      }

}

