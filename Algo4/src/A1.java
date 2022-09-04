import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class A1 {
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

    static boolean dfs(int v, boolean[] visited) {
        if (visited[v]) return false;
        visited[v] = true;
        for (int i = 0; i < graph.get(v).size(); i++) {
            int to = graph.get(v).get(i);
            if (p[to] == -1 || dfs(p[to], visited)) {
                p[to] = v;
                return true;
            }
        }
        return false;
    }

    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] p;

    public static void main(String[] args) throws IOException {
        int n = Reader.readInt();
        int m = Reader.readInt();
        int max = Math.max(n, m);
        p = new int[m];
        Arrays.fill(p, -1);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            int next;
            while ((next = Reader.readInt()) != 0) {
                graph.get(i).add(next - 1);
            }
        }
        boolean[] visited = new boolean[max];
        for (int i = 0; i < n; i++) {
            updateBool(visited);
            dfs(i, visited);
        }
        int ans = 0;
        int[] dopmas = new int[n];
        Arrays.fill(dopmas, -1);
        for (int i = 0; i < m; i++) {
            if (p[i] != -1){
                dopmas[p[i]] = i;
                ans++;
            }
        }
        System.out.println(ans);
        for (int i = 0; i < n; i++) {
            if (dopmas[i] != -1){
                System.out.println(i + 1 + " " + (dopmas[i] + 1));
            }
        }
    }

    private static void updateBool(boolean[] visited) {
        Arrays.fill(visited, false);
    }
}
