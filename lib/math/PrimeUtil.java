class PrimeUtil {

    private static final int N = 500000;
    private static final int[] prime = new int[N + 1];

    static {
        for (int i = 0; i <= N; i++) {
            prime[i] = i;
        }
        for (int i = 2; i * i <= N; i++) {
            if (prime[i] == i) {
                for (int j = i * i; j <= N; j += i) {
                    if (prime[j] > i) {
                        prime[j] = i;
                    }
                }
            }
        }
    }

    public static boolean isPrime(int x) {
        return x > 1 && prime[x] == x;
    }

    public static Map<Integer, Integer> factor(int x) {
        Map<Integer, Integer> fact = new HashMap<>();
        while (x > 1) {
            int p = prime[x];
            fact.put(p, fact.getOrDefault(p, 0) + 1);
            x /= p;
        }
        return fact;
    }
    
}
