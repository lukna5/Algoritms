import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class A3 {
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

    private static class Vertex {
        int u;
        int v;
        long c;
        long f = 0;
        long w;

        public Vertex(int u, int v, long c, long w) {
            this.u = u;
            this.v = v;
            this.c = c;
            this.w = w;
        }
    }

    static int s = 1;
    static int t;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<Vertex> edges = new ArrayList<>();
    static long[] d = new long[101];
    static int[] from = new int[101];
    static long[] p = new long[101];

    public static void main(String[] args) throws IOException {
        int n = Reader.readInt();
        int m = Reader.readInt();
        t = n;
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = Reader.readInt();
            int v = Reader.readInt();
            int c = Reader.readInt();
            int w = Reader.readInt();
            Vertex vertex = new Vertex(u, v, c, w);
            edges.add(vertex);
            graph.get(u).add(2 * i);
            Vertex vertex1 = new Vertex(v, u, 0, -w);
            edges.add(vertex1);
            graph.get(v).add(2 * i + 1);
        }

        Arrays.fill(p, 101);
        dikstr();
        p = Arrays.copyOf(d, 101);
        long res = 0;
        while (d[t] != Long.MAX_VALUE) {
            res = dfs(from[t], 1, res).second;
            dikstr();
            for (int j = 0; j < 101; j++) {
                p[j] += d[j];
            }
        }
        System.out.println(res);

    }

    private static void dikstr() {
        Arrays.fill(d, Long.MAX_VALUE);
        d[s] = 0;
        ArrayList<Pair> set = new ArrayList<>();
        Arrays.fill(from, -1);
        set.add(new Pair(0, s));
        while (!set.isEmpty()) {
            int next = (int) set.remove(0).second;
            for (int id : graph.get(next)) {
                Vertex vertex = edges.get(id);
                long ww = vertex.w + p[next] - p[vertex.v];
                if (vertex.f < vertex.c && d[next] + ww < d[vertex.v]){
                    for (int i = 0; i < set.size(); i++) {
                        Pair pair = set.get(i);
                        if (pair.first == d[vertex.v] && vertex.v == pair.second){
                            set.remove(i);
                            break;
                        }
                    }
                    d[vertex.v] = d[next] + ww;
                    from[vertex.v] = id;
                    set.add(new Pair(d[vertex.v], vertex.v));
                }
            }
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

    private static Pair dfs(int id, long f, long res) {
        if (id == -1) {
            return new Pair(f, res);
        }
        Vertex vertex = edges.get(id);
        res += vertex.w;
        Pair dfss = dfs(from[vertex.u], Math.min(f, vertex.c - vertex.f), res);
        long delta = dfss.first;
        res = dfss.second;
        vertex.f += delta;
        edges.get(id ^ 1).f -= delta;
        return new Pair(delta, res);
    }
}
