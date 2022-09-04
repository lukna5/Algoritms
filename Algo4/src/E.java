import java.io.*;

public class E {
    private static class Reader {

        //static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        static BufferedReader reader;

        static {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public static String readLine() throws IOException {
            return reader.readLine();
        }
        public static String readSymb() throws IOException {
            return Character.toString(reader.read());
        }
        public static int readInt() throws IOException { // readlong
            StringBuilder builder = new StringBuilder();
            int symb;
            while(!Character.isDigit(symb = reader.read()) && (symb != '-'));
            if (symb == '-') {
                builder.append('-');
                symb = reader.read();
            }
            while(symb < 58 && symb > 47){
                builder.append(Character.toString(symb));
                symb = reader.read();
            }
            try {
                return Integer.parseInt(builder.toString());
            }
            catch (NumberFormatException e){
                System.out.println("Can't Convert String into Integer");
                return -1;
            }
        }
        public static String readNext() throws IOException {
            int symb;
            StringBuilder builder = new StringBuilder();
            while ((symb = reader.read()) < 32 || symb > 122);
            builder.append(Character.toString(symb));
            while (!((symb = reader.read()) < 32 || symb > 122)){
                builder.append(Character.toString(symb));
            }
            return builder.toString();
        }
        public static void close() throws IOException {
            reader.close();
        }

    }

    private static long[] sum(long[] a, long[] b){
        long[] res = new long[size + 1];
        int resSize = size;
        for (int i = 0; i < size + 1; i++) {
            if (i > n){
                res[i] = b[i];
                resSize = m;
            } else if (i > m){
                res[i] = a[i];
                resSize = n;
            }
            else {
                res[i] = mod(a[i] + b[i]) % 998244353;
            }
        }
        size = 0;
        for (int i = resSize; i >= 0; i--) {
            if (res[i] != 0){
                size = i;
                break;
            }
        }
        return res;
    }

    private static long[] mult(long[] a, long[] b){
        size = n + m + 2;
        long[] res = new long[size + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                res[i + j] = mod(res[i + j] + mod(a[i] * b[j]));
            }
        }
        size = 0;
        for (int i = n + m; i >= 0; i--) {
            if (res[i] != 0) {
                size = i;
                break;
            }
        }
        return res;
    }

    private static long getIndex(int ind, long[] b){
        if (ind > m){
            return 0;
        }
        return b[ind];
    }
    private static long[] getInverse(long[] b){
        long[] res = new long[1001];
        for (int i = 0; i <= 1000; i++) {
            res[i] = 0;
            if (i == 0) res[0] = 1;
            for (int j = 1; j <= i; j++) {
                res[i] = mod(res[i]- mod(getIndex(j, b) * res[i - j]));
            }
        }
        return res;
    }
    private static long[] division(long[] a, long[] b) {
        long[] b1 = getInverse(b);
        long[] res = new long[2002];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < 1001; j++) {
                res[i + j] = mod(res[i + j] + (mod(a[i] * b1[j])));
            }
        }
        return res;
    }
    private static long mod(long a){
        if (a < 0) return (MOD + a) % MOD;
        else return a % MOD;
    }
    public static long MOD = 998244353;
    public static int n;
    public static int m;
    public static int size;
    public static void main(String[] args) throws IOException {
        n = Reader.readInt();
        m = n;
        size = Math.max(n, m);
        long[] a = new long[n + 1];
        long[] b = new long[m + 1];
        for (int i = 0; i < n + 1; i++) {
            a[i] = Reader.readInt();
        }
        for (int i = 0; i < m + 1; i++) {
            b[i] = Reader.readInt();
        }
        long[] mult = mult(a, b);
        //System.out.println(size);
        for (int i = 0; i < size + 1; i++) {
            System.out.print(mult[i] + " ");
        }
    }
}