import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class C {
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
        int from;
        int to;
        int weight;

        public Rebro(int from, int to, int weight) {
            this.from = from;
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
    static int[][] matrix;
    static int[][][] d;
    static int[][] d1;


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = Reader.readInt();
        HashMap <Integer, Vertex> matrix = new HashMap<>();
        HashMap <Integer, Rebro> reb = new HashMap<>();
        //d = new int[n + 1][n + 1][n + 1];
        d1 = new int[n + 1][n + 1];
        int[] d = new int[n + 1];
        int[] p = new int[n + 1];
        for (int i = 0; i < n; i++) {
            p[i] = -1;
            matrix.put(i, new Vertex(i));
        }

        int m = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int next = Reader.readInt();
                if (Math.abs(next) <= 10000) {
                    matrix.get(i).sons.add(new Rebro(i, j, next));
                    reb.put(m, new Rebro(i, j, next));
                    m++;
                }
                //d[0][i][j] = matrix[i][j];
            }
        }
        long x = 0;
        for (int i = 0; i < n; i++) {
            x = -1;
            for (int j = 0; j < m; j++) {
                int from = reb.get(j).from;
                int to = reb.get(j).to;
                int weight = reb.get(j).weight;
                if (d[to] > d[from] + weight) {
                    d[to] = Math.max(Integer.MIN_VALUE, d[from] + weight);
                    p[to] = from;
                    x = to;
                }
            }
        }
        if (x == -1) System.out.println("NO");
        else {
            long xx = x;
            for (int i = 0; i < n; i++) {
                xx = p[(int) xx];
            }
            ArrayList<Long> res = new ArrayList<>();
            long next = p[(int) xx];
            for (long i = xx; ; i = p[(int) i]) {
                res.add(i);
                if (i == xx && res.size() > 1){
                    break;
                }
            }
            System.out.println("YES");
            System.out.println(res.size() - 1);
            for (int i = res.size() - 2; i >= 0 ; i--) {
                System.out.print((res.get(i) + 1) + " ");
            }
        }

        Reader.close();
    }
    public static String toRebro(int v, int u) {
        return v + "_" + u;
    }

    /*static boolean containsMinusCycle(int n){
        int dd[] = new int[n + 1];
        dd[0] = 1;
        for (int i = 2; i < n + 1; i++) {
            int index = (i + 1) % (n + 1);
            if (index == 0) index = 1;
            while (index != i){
                if (matrix[i][index] != 1000000){
                    if (dd[index] > dd[i] + matrix[i][index]) {
                        dd[index] = dd[i] + matrix[i][index];
                    }
                }
                index = (index + 1) % (n + 1);
                if (index == 0) index = 1;
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if (dd[j] > dd[i] + matrix[i][j]) return false;
            }
        }
        return true;
    }
     */
}
