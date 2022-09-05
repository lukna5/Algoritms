import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Grandi {
    private static class Reader {

        static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        public static String readLine() throws IOException {
            return reader.readLine();
        }
        public static String readSymb() throws IOException {
            return Character.toString(reader.read());
        }
        public static long readInt() throws IOException { // readlong
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
                return Long.parseLong(builder.toString());
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
    public static class Rebro{
        int to;
        long weight;

        public Rebro(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public static class Vertex{
        int num;
        ArrayList<Rebro> sons = new ArrayList<>();

        public Vertex(int num) {
            this.num = num;
        }
    }
    public static void dfs(int v){
        used[v] = true;
        for (int i = 0; i < matrix.get(v).sons.size(); i++) {
            if (!used[matrix.get(v).sons.get(i).to]){
                dfs(matrix.get(v).sons.get(i).to);
            }
        }
        grandiA[v] = count(v);
    }
    public static int count(int v){
        if (matrix.get(v).sons.isEmpty()) {
            return 0;
        }
        return mex(v);
    }
    public static int mex(int v){
        int size = matrix.get(v).sons.size();
        boolean[] cont = new boolean[size + 1];
        for (int i = 0; i < size; i++) {
            Rebro next = matrix.get(v).sons.get(i);
            if (grandiA[next.to] > size) continue;
            else cont[grandiA[next.to]] = true;
        }
        for (int i = 0; i < size + 1; i++) {
            if (!cont[i]){
                return i;
            }
        }
        return 0;
    }
    static boolean[] used;
    static int n;
    static int s;
    static long[] d;
    static int[] grandiA;
    static HashMap<Integer, Vertex> matrix;
    public static void main(String[] args) throws IOException {
        n = (int) Reader.readInt();
        int m = (int) Reader.readInt();
        grandiA = new int[n + 1];
        matrix = new HashMap<>();
        //d = new int[n + 1][n + 1][n + 1];
        d = new long[n + 1];
        used = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            d[i] = Long.MAX_VALUE;
            matrix.put(i, new Vertex(i));
        }
        for (int i = 0; i < m; i++) {
            matrix.get((int) Reader.readInt() - 1).sons.add(new Rebro((int) Reader.readInt() - 1, 0));
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]){
                dfs(i);
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(grandiA[i]);
        }
    }
}
