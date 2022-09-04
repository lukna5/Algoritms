import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {
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
            while (!Character.isDigit(symb = reader.read()) && (symb != '-')) ;
            if (symb == '-') {
                builder.append('-');
                symb = reader.read();
            }
            while (symb < 58 && symb > 47) {
                builder.append(Character.toString(symb));
                symb = reader.read();
            }
            try {
                return Integer.parseInt(builder.toString());
            } catch (NumberFormatException e) {
                System.out.println("Can't Convert String into Integer");
                return -1;
            }
        }

        public static String readNext() throws IOException {
            int symb;
            StringBuilder builder = new StringBuilder();
            while ((symb = reader.read()) < 32 || symb > 122) ;
            builder.append(Character.toString(symb));
            while (!((symb = reader.read()) < 32 || symb > 122)) {
                builder.append(Character.toString(symb));
            }
            return builder.toString();
        }

        public static void close() throws IOException {
            reader.close();
        }


    }

    private static class Pair {
        long first;
        long second;

        public Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }
    }

    private static class Triplet {
        long first;
        long second;
        long third;

        public Triplet(long first, long second, long third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    private static Pair getFactor(long x) {
        for (int i = 2; i <= x; i++) {
            if (x % i == 0) {
                return new Pair(i, (x / i));
            }
        }
        return null;
    }

    static long get_reverse(long a, long mod) {
        Triplet triplet = gcd(a, mod);
        triplet.second /= triplet.first;
        if (triplet.second < 0) {
            triplet.second += mod;
        }
        return triplet.second;
    }

    static Triplet gcd(long a, long b) {
        if (b == 0) {
            return new Triplet(a, 1, 0);
        }
        Triplet res = gcd(b, mod(a, b));
        long d = res.first;
        long x = res.second;
        long y = res.third;
        return new Triplet(d, y, x - y * (a / b));
    }

    public static void main(String[] args) throws IOException {
        int n = Reader.readInt();
        int e = Reader.readInt();
        int c = Reader.readInt();
        Pair pair = getFactor(n);
        long modded = (pair.first - 1) * (pair.second - 1);
        long d1 = get_reverse(e, modded);
        System.out.println(bin(c, d1, n));
    }
    static long mod(long n, long d)
    {
        long result = n % d;
        if (Math.signum(result) * Math.signum(d) < 0)
            result += d;
        return result;
    }
    private static long bin(long a, long nn, int modded) {
        long res = 1;
        while (nn > 0) {
            if ((nn & 1) != 0) {
                res *= a;
                res = mod(res, modded);
                --nn;
            } else {
                a *= a;
                a = mod(a, modded);
                nn /= 2;
            }
        }
        return res;
    }
}
