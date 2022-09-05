import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class D {
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

    public static void zFunction(String s) throws IOException {
        int left = 0;
        for (int i = 1; i < s.length(); i++) {
            z[i] = Math.max(0, Math.min(left + z[left] - i, z[i - left]));
            while (i + z[i] < s.length() && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            if (i == 1 || i + z[i] > left + z[left]){
                left = i;
            }
        }
    }
    public static int[] z;
    public static void main(String[] args) throws IOException {
        String p = Reader.readLine();
        String t = Reader.readLine();
        String s = p + "#" + t;
        z = new int[s.length() + 1];
        zFunction(s);
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = p.length(); i < s.length(); i++) {
            if (z[i] == p.length()){
                deque.push(i - p.length());
            }
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(deque.size() + "\n");
        while (!deque.isEmpty()){
            writer.write(deque.pollLast() + " ");
        }
        writer.close();
    }
}
