class WaveletMatrix {

        private int [][] data;
        private int n, bit;

        WaveletMatrix(int [] d, int bit) {
            n = d.length;
            this.bit = bit;
            int[] dat = d.clone();
            data = new int[bit][n];
            int[] que = new int[n];
            for (int k = bit - 1; k >= 0; k--) {
                int que1 = 0, que2 = n;
                for (int i = 0; i < n; i++) {
                    if ((data[k][i] = (dat[i] >> k) & 1) == 1)
                        que[--que2] = dat[i];
                    else
                        que[que1++] = dat[i];
                }
                for (int i = 0; i < que1; i++)
                    dat[i] = que[i];
                for (int i = n - 1, j = que1; i >= que1; i--, j++)
                    dat[j] = que[i];
                for (int i = 1; i < n; i++)
                    data[k][i] += data[k][i - 1];
            }
        }

        WaveletMatrix(int [] dat) {
            this(dat, 31);
        }

        public int kth_smallest(int l, int r, int kth) {
            int ans = 0;
            for (int k = bit - 1; k >= 0; k--) {
                int s = r - l - sum(k, l, r);
                if (kth < s) {
                    l = zer(k, l);
                    r = l + s;
                } else {
                    kth -= s;
                    ans |= 1 << k;
                    int ran = r - l - s;
                    l = zer(k, n) + one(k, l);
                    r = l + ran;
                }
            }
            return ans;
        }

        public int kth_largest(int l, int r, int kth) {
            return kth_smallest(l, r, r - l - kth);
        }

        public int count_lower(int l, int r, int lower) {
            int ans = 0;
            for (int k = bit - 1; k >= 0; k--) {
             int s = r - l - sum(k, l, r);
                if (((lower >> k) & 1) == 1) {
                    ans += s;
                    int ran = r - l - s;
                    l = zer(k, n) + one(k, l);
                    r = l + ran;
                } else {
                    l = zer(k, l);
                    r = l + s;
                }
            }
            return ans;
        }

        public int count_upper(int l, int r, int upper) {
            return r - l - count_lower(l, r, upper + 1);
        }

        public int count(int l, int r, int x) {
            return count_lower(l, r, x + 1) - count_lower(l, r, x);
        }

        public int higher(int l, int r, int upper) {
            int cnt = count_lower(l, r, upper + 1);
            return kth_smallest(l, r, cnt);
        }

        public int lower(int l, int r, int lower) {
            int cnt = count_upper(l, r, lower - 1);
            return kth_largest(l, r, cnt);
        }

        public int access(int idx) {
            int ans = 0;
            for (int k = bit - 1; k >= 0; k--) {
                if (get(k, idx) == 1) {
                    ans |= 1 << k;
                    idx = zer(k, n) + one(k, idx);
                } else
                    idx -= data[k][idx];
            }
            return ans;
        }

        private int get(int bi, int id) {
            if (id == 0)
                return data[bi][0];
            return data[bi][id] - data[bi][id - 1];
        }

        private int one(int bi, int l) {
            if (l == 0)
                return 0;
            return data[bi][l - 1];
        }

        private int zer(int bi, int l) {
            return l - one(bi, l);
        }

        private int sum(int bi, int l, int r) {
            return one(bi, r) - one(bi, l);
        }

    }
