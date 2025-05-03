package lib.structure.segmenttree;

import java.util.Arrays;
import java.util.function.BinaryOperator;

class IntSegmentTree {

      private int [] tree;
      private int n;
      private int e;
      private BinaryOperator<Integer> op;

      public IntSegmentTree(int n , int e , BinaryOperator<Integer> op) {
            this.n = n;
            this.e = e;
            this.op = op;
            this.tree = new int[2 * n];
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

      public int query(int index) {
            return query(index,index+1);
      }

      public int query(int l, int r) {
            int resLeft = e;
            int resRight = e;
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
