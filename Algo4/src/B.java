import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class B {
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

        public static long readInt() throws IOException { // readlong
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
                return Long.parseLong(builder.toString());
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

    static long mod(long n, long d) {
        long result = n % d;
        if (Math.signum(result) * Math.signum(d) < 0)
            result += d;
        return result;
    }

    public static void main(String[] args) throws IOException {
        int n = (int) Reader.readInt();
        for (int i = 0; i < n; i++) {
            long next = Reader.readInt();
            if (isPrime(next)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean isPrime(long next) {
        if (next == 2 || next == 3) {
            return true;
        }
        if (next == 1 || next % 2 == 0) {
            return false;
        }
        long num = next - 1;
        long s1 = 0;
        while (mod(num, 2) == 0) {
            s1 = s1 + 1;
            num = num / 2;
        }
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int forRand = Integer.MAX_VALUE;
            if (next < Integer.MAX_VALUE) {
                forRand = (int) next;
            }
            long rnd = random.nextInt((forRand - 4)) + 2;
            long a = rnd;
            long b = bin(a, num, next);
            if (b == 1 || b == next - 1) {
                continue;
            }
            for (int j = 1; j < s1; j++) {
                b = mod(b * b, next);
                if (b == 1) {
                    return false;
                } else if (b == next - 1) {
                    break;
                }
            }
            if (b != next - 1) {
                return false;
            }
        }
        return true;
    }

    private static long bin(long a, long num, long modded) {
        long res = 1;
        while (num != 0) {
            if (num > 0) {
                res = res * a;
                res = mod(res, modded);
                num = num - 1;
            } else {
                a = a * a;
                a = mod(a, modded);
                num = num / 2;
            }
        }
        return res;
    }
}
