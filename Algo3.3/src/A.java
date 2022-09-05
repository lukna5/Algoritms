import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
        private static class Reader {

            static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


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

            public static long readLong() throws IOException { // readlong
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
    public static void main(String[] args) throws IOException {
        String s = Reader.readLine();
        int m = Reader.readInt();
        for (int i = 0; i < m; i++) {
            int a = Reader.readInt() - 1;
            int b = Reader.readInt();
            int c = Reader.readInt() - 1;
            int d = Reader.readInt();
            //System.out.println(s.substring(a, b));
            if (b - a == d - c && s.substring(a, b).hashCode() == s.substring(c, d).hashCode()){
                System.out.println("Yes");
            }
            else System.out.println("No");
        }
    }
}
