/**
 * BinaryTrie
 * 
 * Trie木を0と1に限定した木
 * 
 * 一律 O(bit長) で動作します
 */
class BinaryTrie {

    private static class Node {
        int[] child = {-1, -1};
        int count = 0;
    }

    private final List<Node> nodes = new ArrayList<>();
    private final int BITLEN;

    /**
     * bit長を定義
     *  32bit整数型であれば BITLEN = 30
     *  64bit整数型であれば BITLEN = 60
     * @param BITLEN bit長
     */
    public BinaryTrie(int BITLEN) {
        this.BITLEN = BITLEN;
        nodes.add(new Node());
    }

    /**
     * 要素数を返します
     * @return 要素数
     */
    public int size() {
        return nodes.get(0).count;
    }

    /**
     * 要素 x を追加します
     * @param x 追加する要素
     */
    public void add(long x) {
        int p = 0;
        for (int i = BITLEN - 1; i >= 0; i--) {
            nodes.get(p).count++;
            int b = (int) ((x >> i) & 1L);
            if (nodes.get(p).child[b] == -1) {
                nodes.get(p).child[b] = nodes.size();
                nodes.add(new Node());
            }
            p = nodes.get(p).child[b];
        }
        nodes.get(p).count++;
    }

    /**
     * 要素 x を削除します
     * @param x 削除する要素
     */
    public void remove(long x) {
        if (count(x) == 0) return;
        int p = 0;
        for (int i = BITLEN - 1; i >= 0; i--) {
            nodes.get(p).count--;
            int b = (int) ((x >> i) & 1L);
            p = nodes.get(p).child[b];
        }
        nodes.get(p).count--;
    }

    /**
     * 要素 x が存在するか判定します
     * @param x 調べる要素
     * @return 含まれるかの真偽値
     */
    public boolean contains(long x) {
        return count(x) > 0;
    }

    /**
     * 最小値を取得します
     * @return 最小値
     */
    public long first() {
        return size() == 0 ? -1 : get(1);
    }

    /**
     * 最大値を取得します
     * @return 最大値
     */
    public long last() {
        return size() == 0 ? -1 : get(size());
    }

    /**
     * k 番目に大きい要素を取得します
     * @param k 番目
     * @return k 番目に大きい要素
     */
    public long kthLargest(int k) {
        return get(size() - k + 1);
    }

    /**
     * k 番目に小さい要素を取得します
     * @param k 番目
     * @return k 番目に小さい要素
     */
    public long kthSmallest(int k) {
        return get(k);
    }

    /**
     * 要素xが何個存在するか返します
     * @param x 調べる要素
     * @return 個数
     */
    public int count(long x) {
        int p = 0;
        for (int i = BITLEN - 1; i >= 0; i--) {
            int b = (int) ((x >> i) & 1L);
            if (nodes.get(p).child[b] == -1) return 0;
            p = nodes.get(p).child[b];
        }
        return nodes.get(p).count;
    }

    /**
     * x 未満の値の個数を返します
     * @param x 調べる要素の境界値
     * @return x 未満の値の個数
     */
    public int lowerCount(long x) {
        int p = 0, count = 0;
        for (int i = BITLEN - 1; i >= 0; i--) {
            int bit = (int) ((x >> i) & 1L);
            if (bit == 1) {
                int left = nodes.get(p).child[0];
                if (left != -1) count += nodes.get(left).count;
            }
            p = nodes.get(p).child[bit];
            if (p == -1) break;
        }
        return count;
    }

    /**
     * x 以下の値の個数を返します
     * @param x 調べる要素の境界値
     * @return x 以下の値の個数
     */
    public int floorCount(long x) {
        return lowerCount(x + 1);
    }

    /**
     * x より大きい値の個数を返します
     * @param x 調べる要素の境界値
     * @return x より大きい値の個数
     */
    public int higherCount(long x) {
        return this.size() - floorCount(x);
    }

    /**
     * x 以上の値の個数を返します
     * @param x 調べる要素の境界値
     * @return x 以上の値の個数
     */
    public int ceilingCount(long x) {
        return this.size() - lowerCount(x);
    }

    /**
     * 値の範囲 [l,r) に含まれる要素数を返します
     * @param l 下限 
     * @param r 上限
     * @return 値の範囲 [l,r) に含まれる要素の個数
     */
    public int rangeCount(long l, long r) {
        return lowerCount(r) - lowerCount(l);
    }

    /**
     * x 未満のうち最大の要素を取得します
     * @param x 調べる要素
     * @return x 未満のうち最大の要素
     */
    public long lower(long x) {
        int c = lowerCount(x);
        if (c == 0) return -1;
        return get(c);
    }

    /**
     * x 以下のうち最大の要素を取得します
     * @param x 調べる要素
     * @return x 以下のうち最大の要素
     */
    public long floor(long x) {
        int c = floorCount(x);
        if (c == 0) return -1;
        return get(c);
    }

    /**
     * x より大きい要素のうち最小の要素を取得します
     * @param x 調べる要素
     * @return x より大きい要素のうち最小の要素
     */
    public long higher(long x) {
        int c = ceilingCount(x + 1);
        if (c == 0) return -1;
        return get(size() - c + 1);
    }

    /**
     * x 以上のうち最大の要素を取得します
     * @param x 調べる要素
     * @return x 以上のうち最大の要素
     */
    public long ceiling(long x) {
        int c = ceilingCount(x);
        if (c == 0) return -1;
        return get(size() - c + 1);
    }

    /**
     * 含まれない値のうち最小の値を返します
     * @return 含まれない値のうち最小の値
     */
    public long mex() {
        int p = 0;
        long res = 0;
        for (int i = BITLEN - 1; i >= 0; i--) {
            int left = nodes.get(p).child[0];
            if (left == -1 || nodes.get(left).count < (1 << i)) {
                p = (left == -1 ? -1 : left);
            } else {
                res |= (1L << i);
                p = nodes.get(p).child[1];
            }
            if (p == -1) break;
        }
        return res;
    }

    private long get(int k) {
        if (k <= 0 || k > size()) throw new IllegalArgumentException("Invalid k");
        int p = 0;
        long res = 0;
        for (int i = BITLEN - 1; i >= 0; i--) {
            res <<= 1;
            int left = nodes.get(p).child[0];
            int leftSize = (left == -1 ? 0 : nodes.get(left).count);
            if (leftSize >= k) {
                p = left;
            } else {
                k -= leftSize;
                p = nodes.get(p).child[1];
                res |= 1L;
            }
        }
        return res;
    }
}
