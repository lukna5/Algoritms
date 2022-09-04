import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
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
    private static class Triplet {
        long x;
        long y;
        long d;

        public Triplet(long x, long y, long d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        long a = Reader.readInt();
        long b = Reader.readInt();
        long n = Reader.readInt();
        long m = Reader.readInt();


        Triplet d = goSearch(n, -1 * m, b - a);
        long x = d.x;
        long y = d.y;
        long d1 = d.d;
        long res = a + x * n;
        while (res < 0) {
            res += n * m;
        }
        while (res > n * m){
            res -= m * n;
        }
        System.out.println(res);
    }
    private static Triplet goSearch(long a, long b, long c){
        Triplet res = gcd(Math.abs(a), Math.abs(b));
        long x = res.x * c / res.d;
        long y = res.y * c / res.d;
        if (a < 0) x *= -1;
        if (b < 0) y *= -1;
        return new Triplet(x, y, res.d);
    }

    private static Triplet gcd(long n, long m) {
        if (n == 0){
            return new Triplet(0, 1, m);
        }
        long n1 = n;
        long m1 = m;
        Triplet res = gcd(m % n, n);
        long x = res.y - (m1 / n1) * res.x;
        long y = res.x;
        return new Triplet(x, y, res.d);
    }
}
