import java.io.*;
import java.util.Scanner;

public class BinaryClimb {
    private static int root;
    private static int[] d;
    private static int[] p;
    private static int[][] dp;
    private static int h;
    private static int getD(int v){
        if (d[v] == -1){
            if (!(v == root)) d[v] = getD(p[v]) + 1;
            else d[v] = 0;
        }
        //System.out.println(p[v] + " v " + v + " d " + d[v] );
        return d[v];
    }
    private static final StreamTokenizer input = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static final PrintWriter out = new PrintWriter(System.out);

    static int nextInt() throws IOException {
        input.nextToken();
        return (int) input.nval;
    }
    static int lca(int v, int u){
        if (d[v] > d[u]){
            int temp = v;
            v = u;
            u = temp;
        }
        for (int i = h; i >= 0; i--) {
            if (d[dp[u][i]] - d[v] >= 0) u = dp[u][i];
        }
        if (v == u) return u;
        for (int i = h; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]){
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        //System.out.println(v + " v");
        return p[v];
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        h = (int) Math.ceil(Math.log(n + 1)/Math.log(2));
        dp = new int[n + 1][h + 1];
        p = new int[n + 1];
        d = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            d[i] = -1;
        }
        for (int i = 2; i < n + 1; i++) {
            p[i] = input.nextInt();
            //System.out.println(p[i]);
        }
        root = 1;
        p[1] = 1;

        for (int i = 1; i < n + 1; i++) {
            //System.out.println(i);
            getD(i);
            //System.out.println("d " + i + " " + d[i]);
        }
        dp[root][0] = root;
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = p[i];
        }
        for (int i = 1; i < h + 1; i++){
             for (int v = 1; v < n + 1; v++){
                dp[v][i] = dp[dp[v][i - 1]][i - 1];
            }
        }
        int m = input.nextInt();
        for (int i = 0; i < m; i++) {
            out.println(lca(input.nextInt(), input.nextInt()));
        }
/*
        for (int i = 1; i < n + 1; i++) {

            h = (int) Math.ceil(Math.log(d[i] + 1)/Math.log(2));;

            //System.out.println(h + " " + d[i]);
            out.print(i + ": ");
            for (int j = 0; j < h; j++) {
                //if (dp[i][j] == 0) continue;
                out.print(dp[i][j] + " ");
            }
            out.println();
        }

 */



        out.close();
    }
}
