import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinTree{

    public static class data{
        long min;
        long kol;
        public data(long min, long kol) {
            this.min = min;
            this.kol = kol;
        }
    }
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static long[] min;
    static long[] kol;
    static int m;
    static int n;
    static int n1;
    public static void main(String[] args) throws IOException {
        build();
        /*for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");
        }

         */
        //System.out.println(rsq(0, 0, n1, 0, 3));
        for (int i = 0; i < m; i++) {
            //showTree();
            if (nextInt() == 1) set(0, nextInt(), 0, n1, nextInt());
            else{
                data res = rmq(0, 0, n1, nextInt(), nextInt());
                System.out.println(res.min + " " + res.kol);
            }
        }
    }
    public static void build() throws IOException {
        n = nextInt();
        int count = 0;
        while (Math.pow(2, count) < n) count++;
        n1 = (int) Math.pow(2, count);
        m = nextInt();
        //System.out.println(n1);
        min = new long[2 * n1];
        kol = new long[2 * n1];
        for (int i = 0; i < n; i++) {
            min[n1 - 1 + i] = nextInt();
            kol[n1 - 1 + i] = 1;
        }
        for (int i = n1 - 2; i >= 0; i--) {
            min[i] = Math.min(min[2 * i + 1], min[2 * i + 2]);
            if (min[2 * i + 1] == min[2 * i + 2]) kol[i] = kol[2 * i + 1] + kol[2 * i + 2];
            else if (min[2 * i + 1] < min[2 * i + 2]) kol[i] = kol[2 * i + 1];
            else kol[i] = kol[2 * i + 2];
        }
    }

    public static void set(int v, int ind, int a, int b, int k){
        //System.out.println(a + " " + b);
        if (b - a == 1) min[v] = k;
        else {
            int mid = (a + b) / 2;
            if (ind < mid) set(2 * v + 1, ind, a, mid, k);
            else set(2 * v + 2, ind, mid, b, k);
            min[v] = Math.min(min[2 * v + 1], min[2 * v + 2]);
            if (min[2 * v + 1] == min[2 * v + 2]) kol[v] = kol[2 * v + 1] + kol[2 * v + 2];
            else if (min[2 * v + 1] < min[2 * v + 2]) kol[v] = kol[2 * v + 1];
            else kol[v] = kol[2 * v + 2];
        }
    }

    public static data rmq(int v, int l, int r, int a, int b) {
        //System.out.println(a + " " + b);
        //System.out.println(v + " " + l + " " + r);
        if (l >= b || r <= a) return new data(1000000001, 1);
        if (l >= a && r <= b) {
            //System.out.println(v + " deep" + mas[v]);
            return new data(min[v], kol[v]);
        }
        int mid = (l + r) / 2;
        data res1 = rmq(2 * v + 1, l, mid, a, b);
        data res2 = rmq(2 * v + 2, mid, r, a, b);
        if (res1.min == res2.min){
            res1.kol += res2.kol;
            return res1;
        }
        else if (res1.min < res2.min) return res1;
        else return res2;
    }

    public static void showTree(){
        for (int i = 0; i < n; i++) {
            System.out.print(min[n + i - 1] + " ");
        }
        System.out.println();
    }

    public static int nextInt() throws IOException {
        StringBuilder builder = new StringBuilder();
        int next;
        while ((next = input.read()) <= 40){
        }
        builder.append(Character.toString(next));
        while ((next = input.read()) > 40){
            builder.append(Character.toString(next));
        }
        return Integer.parseInt(builder.toString());
    }
}
