import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class C1 {
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

    private static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static boolean[] visited1;
    static int[] p = new int[151];


    static boolean dfs(int v) {
        if (visited1[v]) return false;
        visited1[v] = true;
        for (int i = 0; i < graph.get(v).size(); i++) {
            int to = graph.get(v).get(i);
            if (p[to] == -1 || dfs(p[to])) {
                p[to] = v;
                return true;
            }
        }
        return false;
    }

    static void dfs1(int v) {
        if (visited1[v]) return;
        visited1[v] = true;
        for (int i = 0; i < graph.get(v).size(); i++) {
            int to = graph.get(v).get(i);
            visited[to] = true;
            if (p[to] != -1) {
                dfs1(p[to]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int k = Reader.readInt();
        for (int i = 0; i < k; i++) {
            int m = Reader.readInt();
            int n = Reader.readInt();
            Arrays.fill(p, -1);
            graph = new ArrayList<>();
            graph.add(new ArrayList<>());
            updateBool(visited);
            updateBool(visited1);
            for (int j = 1; j <= m; j++) {
                graph.add(new ArrayList<>());
                boolean[] vis1 = new boolean[n + 1];
                updateBool(vis1);
                int next;
                while ((next = Reader.readInt()) != 0) {
                    vis1[next] = true;
                }
                for (int l = 1; l <= n; l++) {
                    if (!vis1[l]) {
                        graph.get(j).add(l);
                    }
                }
            }
            for (int j = 1; j <= m; j++) {
                visited1 = new boolean[151];
                updateBool(visited1);
                dfs(j);
            }
            visited = new boolean[151];
            boolean[] can = new boolean[151];
            updateBoolTrue(can);
            updateBool(visited);
            updateBool(visited1);
            for (int j = 1; j <= n; j++) {
                if (p[j] != -1) {
                    can[p[j]] = false;
                }
            }
            for (int j = 1; j <= m; j++) {
                if (can[j] && !visited1[j]) {
                    dfs1(j);
                }
            }
            ArrayList<Integer> BOY = new ArrayList<>();
            ArrayList<Integer> GIRL = new ArrayList<>();
            for (int j = 1; j <= m; j++) {
                if (visited1[j]) {
                    BOY.add(j);
                }
            }
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    GIRL.add(j);
                }
            }
            System.out.println(BOY.size() + GIRL.size());
            System.out.println(BOY.size() + " " + GIRL.size());
            Iterator<Integer> iterator = BOY.iterator();
            int first = 0;
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println();
            iterator = GIRL.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            BOY.clear();
            GIRL.clear();
            System.out.println();
            System.out.println();
        }
    }

    private static void updateBoolTrue(boolean[] visited2) {
        Arrays.fill(visited2, true);
    }

    private static void updateBool(boolean[] visited2) {
        if (visited2 != null) {
            Arrays.fill(visited2, false);
        }
    }
}
