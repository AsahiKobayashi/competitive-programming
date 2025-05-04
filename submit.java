import java.util.*;
import java.io.*;

class Main implements Runnable{
        public static void main(String ... args) {
                new Thread(null, new Main(), "", Runtime.getRuntime().maxMemory()).start() ;
        }

        @Override
        public void run() { 
                FastInputOutput io = new FastInputOutput();
                new Solver().solve(io);
                io.flush();
        }   
}

record Pair<T,S>(T first,S second) {}

class Solver extends Base{

        Solver() {  }

        void solve(FastInputOutput io) {
                
        }  

}

class Function {
        public static final long nC2(long n) { return (n*(n-1))/2; }
        public static final long sum(long n) { return (n*(n+1))/2; }
        public static final long ceil(long u,long d){ return (u+d-1)/d; }
        public static final long gcd(long a,long b) { return b==0 ?a:gcd(b,a%b); }
        public static final long lcm(long a,long b) { return a/gcd(a,b)*b; }
        public static final long sqrt(long x) { long low=0,high=Math.min(x,1L<<32);while(low<=high){long mid=(low+high)>>>1;long sq=mid*mid;if(sq==x)return mid;if(sq<0||sq>x)high=mid-1;else low=mid+1;}return high; }
}

class Base {
        // const
        public static final int INF_INT = (1 << 30);
        public static final long INF_LONG = (1L << 60);
        public static final String YES = "Yes" , NO = "No";
        public static final int [][] D4 = {{-1,0},{0,1},{1,0},{0,-1}};  // {y,x}
        public static final int [][] D8 = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}}; // {y,x}
        public static final long [] MOD = {998244353,1000000007};
}



class FastInputOutput extends PrintWriter {

        FastInputOutput(){ super(System.out); }

        private final InputStream in = System.in ;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;

        private boolean hasNextByte() {
                if (ptr < buflen) {
                        return true;
                }
                else{
                        ptr = 0;
                        try {
                                buflen = in.read(buffer);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        if (buflen <= 0) {
                                return false;
                        }
                }
                return true;
        }

        private int readByte() { 
                if (hasNextByte()) return buffer[ptr++]; else return -1;
        }

        private static boolean isPrintableChar(int c) { 
                return 33 <= c && c <= 126;
        }

        private boolean hasNext() { 
                while(hasNextByte() && !isPrintableChar(buffer[ptr])) {
                        ptr++; 
                }
                return hasNextByte();
        }

        public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                StringBuilder sb = new StringBuilder();
                int b = readByte();
                while(isPrintableChar(b)) {
                        sb.appendCodePoint(b);
                        b = readByte();
                }
                return sb.toString();
        }

        public long nextLong() {
                if (!hasNext()) throw new NoSuchElementException();
                long n = 0;
                boolean minus = false;
                int b = readByte();
                if (b == '-') {
                        minus = true;
                        b = readByte();
                }
                if (b < '0' || '9' < b) {
                        throw new NumberFormatException();
                }
                while(true){
                if ('0' <= b && b <= '9') {
                        n *= 10;
                        n += b - '0';
                }else if(b == -1 || !isPrintableChar(b)){
                        return minus ? -n : n;
                }else{
                        throw new NumberFormatException();
                }
                        b = readByte();
                }
        }

        public int nextInt() {
                long nl = nextLong();
                if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
                return (int) nl;
        }

        public double nextDouble() { 
                return Double.parseDouble(next());
        }

        public char nextChar() {
                return next().charAt(0);
        }

        public int [] nextInt(int n) {
                int [] array = new int[n];
                for(int i = 0 ; i < n ; i ++)
                        array[i] = nextInt();
                return array ;
        }

        public int [][] nextInt(int n , int m) {
                int [][] array = new int[n][m];
                for(int i = 0 ; i < n ; i ++)
                        array[i] = nextInt(m);
                return array ;
        }

        public long [] nextLong(int n) {
                long [] array = new long[n];
                for(int i = 0 ; i < n ; i ++)
                        array[i] = nextLong();
                return array ;
        }

        public long [][] nextLong(int n , int m) {
                long [][] array = new long[n][m];
                for(int i = 0 ; i < n ; i ++)
                        array[i] = nextLong(m);
                return array ;
        }

        public double [] nextDouble(int n) {
                double [] array = new double[n];
                for(int i = 0 ; i < n ; i ++)
                        array[i] = nextDouble();
                return array ;
        }

        public String [] next(int n) {
                String [] array = new String[n];
                for(int i = 0 ; i < n ; i ++)
                        array[i] = next();
                return array ;
        }

        public char [] nextChar(int n) {
                return next().toCharArray();
        }

}