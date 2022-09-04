import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class B2 {
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
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<Vertex> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int n = Reader.readInt();
        int m = Reader.readInt();
        s = 1;
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int u = Reader.readInt();
            int v = Reader.readInt();
            long c = Reader.readInt();
            Vertex vertex = new Vertex(u, v, c);
            edges.add(vertex);
            map.put(toString(vertex), i);
            graph.get(u).add(2 * i);
            Vertex vertex1 = new Vertex(v, u, c);
            map.put(toString(vertex1), i);
            edges.add(vertex1);
            graph.get(v).add(2 * i + 1);
        }
        long max = 0;
        int[] p = new int[100010];
        Arrays.fill(p, 0);
        t = n;
        while (bfs(p, 100010)){
            Arrays.fill(p, 0);
            long ff = dfs(s, 100010, p);
            while (ff > 0){
                max += ff;
                ff = dfs(s, 100010, p);
            }
        }
        int size = 100010;
        boolean[] visited = new boolean[size];
        Arrays.fill(visited, false);
        bfs777(visited);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            Vertex next = edges.get(i);
            if (visited[next.u] && !visited[next.v]){
                res.add(map.get(toString(next)));
            }
        }
        System.out.println(res.size() + " " + max);
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + 1 + " ");
        }
    }

    private static String toString(Vertex next) {
        return next.u + "_" + next.v + "_" + next.c;
    }

    private static void bfs777(boolean[] visited) {
        visited[s] = true;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.push(s);
        while (!deque.isEmpty()){
            int next = deque.pollFirst();
            for (int id: graph.get(next)) {
                Vertex vertex = edges.get(id);
                if (vertex.f < vertex.c && !visited[vertex.v]){
                    d[vertex.v] = d[next] + 1;
                    visited[vertex.v] = true;
                    deque.push(vertex.v);
                }
            }
        }
    }

    private static long dfs(int u, long min, int[] p) {
        if (u == t || min == 0){
            return min;
        }
        for (;  p[u] < graph.get(u).size(); p[u]++) {
            Vertex uv = edges.get(graph.get(u).get(p[u]));
            if (d[uv.v] == d[u] + 1) {
                long delta = dfs(uv.v, Math.min(min, uv.c - uv.f), p);
                if (delta > 0) {
                    uv.f += delta;
                    edges.get(graph.get(u).get(p[u]) ^ 1).f -= delta;
                    return delta;
                }
            }
        }
        return 0;
    }

    static int s;
    static int t;
    static int[] d = new int[100010];

    private static boolean bfs(int[] p, int size) {
        Arrays.fill(d, size);
        d[s] = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.push(s);
        while (!deque.isEmpty()){
            int next = deque.pollFirst();
            for (int id: graph.get(next)) {
                Vertex vertex = edges.get(id);
                if (vertex.f < vertex.c && d[vertex.v] == size){
                    d[vertex.v] = d[next] + 1;
                    deque.push(vertex.v);
                }
            }
        }
        return d[t] != size;
    }
}
