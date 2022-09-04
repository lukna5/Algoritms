import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class A2 {
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
        public Vertex(int u, int v, long c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }

    private static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

    }

    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<Vertex> edges = new ArrayList<>();
    static ArrayList<Pair> edgeDigs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int n = Reader.readInt();
        int m = Reader.readInt();
        for (int i = 0; i < 101; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = Reader.readInt();
            int v = Reader.readInt();
            int c = Reader.readInt();
            Vertex vertex = new Vertex(u, v, c);
            edges.add(vertex);
            graph.get(u).add(2 * i);
            Vertex vertex1 = new Vertex(v, u, c);
            edges.add(vertex1);
            graph.get(v).add(2 * i + 1);
        }
        boolean[] visited = new boolean[101];
        long res = 0;
        while (true) {
            Arrays.fill(visited, false);
            long delta = dfs(1, Long.MAX_VALUE, n, visited);
            if (delta == 0){
                System.out.println(res);
                for (int i = 0; i < m; i++) {
                    System.out.println(edges.get(2 * i).f);
                }
                return;
            } else {
                res += delta;
            }
        }
    }

    static long dfs(int u, long f, int n, boolean[] visited) {
        if (u == n) {
            return f;
        }
        if (visited[u]){
            return 0;
        }
        visited[u] = true;
        for (int i = 0; i < graph.get(u).size(); i++) {
            int next = graph.get(u).get(i);
            Vertex e = edges.get(next);
            if (e.f < e.c) {
                long delta = dfs(e.v, Math.min(f, e.c - e.f), n, visited);
                if (delta > 0) {
                    e.f += delta;
                    int sho = u ^ 1;
                    edges.get(next ^ 1).f -= delta;
                    return delta;
                }
            }
        }

        return 0;
    }
}
