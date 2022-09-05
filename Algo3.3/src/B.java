import java.io.*;

public class B {
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
    public static void prefixFunction(String s) throws IOException {
        int k;
        p[0] = -1;
        for (int i = 1; i < s.length() + 1; i++) {
            k = p[i - 1];
            while (k > -1 && s.charAt(k) != s.charAt(i - 1)){
                k = p[k];
            }
            p[i] = k + 1;
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < s.length(); i++) {
            writer.write(p[i + 1] + " ");
        }
        writer.close();
    }
    public static int[] p;
    public static void main(String[] args) throws IOException {
        String s = Reader.readLine();
        p = new int[s.length() + 1];

        prefixFunction(s);
    }
}
