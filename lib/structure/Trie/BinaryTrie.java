class BinaryTrie {

    private static class Node {
        int[] child = {-1, -1};
        int count = 0;
    }

    private final List<Node> nodes = new ArrayList<>();
    private final int BITLEN;

    public BinaryTrie(int BITLEN) {
        this.BITLEN = BITLEN;
        nodes.add(new Node());
    }

    public int size() {
        return nodes.get(0).count;
    }

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

    public boolean contains(long x) {
        return count(x) > 0;
    }

    public long first() {
        return size() == 0 ? -1 : get(1);
    }

    public long last() {
        return size() == 0 ? -1 : get(size());
    }

    public long kthLargest(int k) {
        return get(size() - k + 1);
    }

    public long kthSmallest(int k) {
        return get(k);
    }
    
    public int count(long x) {
        int p = 0;
        for (int i = BITLEN - 1; i >= 0; i--) {
            int b = (int) ((x >> i) & 1L);
            if (nodes.get(p).child[b] == -1) return 0;
            p = nodes.get(p).child[b];
        }
        return nodes.get(p).count;
    }

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
    
    public int floorCount(long x) {
        return lowerCount(x + 1);
    }

    public int higherCount(long x) {
        return this.size() - floorCount(x);
    }

    public int ceilingCount(long x) {
        return this.size() - lowerCount(x);
    }

    public int rangeCount(long l, long r) {
        return lowerCount(r) - lowerCount(l);
    }

    public long lower(long x) {
        int c = lowerCount(x);
        if (c == 0) return -1;
        return get(c);
    }
    
    public long floor(long x) {
        int c = floorCount(x);
        if (c == 0) return -1;
        return get(c);
    }

    public long higher(long x) {
        int c = ceilingCount(x + 1);
        if (c == 0) return -1;
        return get(size() - c + 1);
    }

    public long ceiling(long x) {
        int c = ceilingCount(x);
        if (c == 0) return -1;
        return get(size() - c + 1);
    }

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
