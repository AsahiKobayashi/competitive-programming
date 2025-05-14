class Prime {

    private int [] prime ;

    public Prime(int n) {
        this.prime = new int[n + 1];
        for(int i = 0 ; i <= n ; i ++) prime[i] = i ;
            for(int i = 2 ; i * i <= n ; i ++) 
            if(prime[i] == i) for(int j = 2 ; i * j <= n ; j ++) {
            if(prime[i * j] > i) prime[i * j] = i ;
        }
    }

    public boolean isPrime(int n) {
        return 1 < n && (prime[n] == n) ;
    }

    public Map<Integer,Integer> factor(int n) {
        Map<Integer,Integer> fact = new HashMap<>();
        while(n > 1) {
            fact.put(prime[n] , fact.getOrDefault(prime[n] , 0) + 1);
            n /= prime[n];
        }
        return fact ;
    }

}
