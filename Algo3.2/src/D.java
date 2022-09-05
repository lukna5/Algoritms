import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class D {
    private static class Reader {

        static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        public static String readLine() throws IOException {
            return reader.readLine();
        }
        public static String readSymb() throws IOException {
            return Character.toString(reader.read());
        }
        public static int readInt() throws IOException {
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
    public static class Rebro{
        int to;
        int weight;

        public Rebro(int to, int weight) {
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
    public static void main(String[] args) throws IOException {
        int n = Reader.readInt();
        int m = Reader.readInt();
        int k = Reader.readInt();
        int s = Reader.readInt();
        s--;
        HashMap<Integer, Vertex> matrix = new HashMap<>();
        //d = new int[n + 1][n + 1][n + 1];
        int[][] d = new int[k + 1][n];
        for (int i = 0; i < k + 1; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = Integer.MAX_VALUE;
            }
        }
        d[0][s] = 0;
        for (int i = 0; i < n; i++) {
            matrix.put(i, new Vertex(i));
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < matrix.get(j).sons.size(); l++) {
                    if (d[i][j] == Integer.MAX_VALUE) continue;
                    int v = j;
                    int u = matrix.get(j).sons.get(l).to;
                    int w = matrix.get(j).sons.get(l).weight;
                    d[i + 1][u] = Math.min(d[i + 1][u], d[i][v] + w);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (d[k][i] == Integer.MAX_VALUE){
                System.out.println("-1");
            } else {
                System.out.println(d[k][i]);
            }
        }
    }
    public static String toRebro(int v, int u) {
        return v + "_" + u;
    }
}
