import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class E {
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
    }
    public static void fordBel(){
        d[s] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (int k = 0; k < matrix.get(j).sons.size(); k++) {
                    Rebro rebro = matrix.get(j).sons.get(k);
                    int to = rebro.to;
                    long weight = rebro.weight;
                    if (d[j] < Long.MAX_VALUE && (d[to] > d[j] + weight)){
                        d[to] = d[j] + weight;
                    }
                }
            }
        }
    }
    static boolean[] used;
    static int n;
    static int s;
    static long[] d;
    static HashMap<Integer, Vertex> matrix;
    public static void main(String[] args) throws IOException {
        n = (int) Reader.readInt();
        int m = (int) Reader.readInt();
        s = (int) Reader.readInt();
        matrix = new HashMap<>();
        //d = new int[n + 1][n + 1][n + 1];
        d = new long[n + 1];
        used = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            d[i] = Long.MAX_VALUE;
            matrix.put(i, new Vertex(i));
        }
        for (int i = 0; i < m; i++) {
            matrix.get((int) Reader.readInt()).sons.add(new Rebro((int) Reader.readInt(), Reader.readInt()));
        }
        fordBel();
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < matrix.get(i).sons.size(); j++) {
                Rebro rebro = matrix.get(i).sons.get(j);
                if (d[i] < Long.MAX_VALUE && d[rebro.to] > d[i] + rebro.weight && !used[rebro.to]){
                    dfs(rebro.to);
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            if (d[i] == Long.MAX_VALUE) System.out.println("*");
            else if (used[i]) System.out.println("-");
            else System.out.println(d[i]);
        }
    }
}
