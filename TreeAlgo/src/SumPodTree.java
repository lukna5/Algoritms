import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SumPodTree {

    public static class data{
        long sum;
        long pref;
        long suf;
        long res;
        public data(long sum, long pref, long suf, long res) {
            this.sum = sum;
            this.res = res;
            this.pref = pref;
            this.suf = suf;
        }
    }
    static PrintWriter writer = new PrintWriter(System.out);
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static data[] t;
    static long m;
    static int n;
    static int n1;
    public static void main(String[] args) throws IOException {
        build();
        System.out.println(t[0].res);
        for (int i = 0; i < m; i++) {
            set(0, nextInt(), 0, n1, nextInt());
            writer.println(t[0].res);
        }
        writer.close();
    }

    public static data createData(long sum){
        return new data(sum, Math.max(0, sum), Math.max(0, sum), Math.max(0, sum));
    }

    public static data update(data left, data right){
        long sum = left.sum + right.sum;
        long pref = Math.max(left.pref, left.sum + right.pref);
        long suf = Math.max(right.suf, right.sum + left.suf);
        long res = Math.max(Math.max(left.res, right.res), left.suf + right.pref);
        return new data(sum, pref, suf, res);
    }

    public static void build() throws IOException {
        n = nextInt();
        int count = 0;
        while (Math.pow(2, count) < n) count++;
        n1 = (int) Math.pow(2, count);
        m = nextInt();
        //System.out.println(n1);
        t = new data[2 * n1];
        for (int i = 0; i < 2 * n1; i++) {
            t[i] = new data(0, 0, 0, 0 );
        }
        for (int i = 0; i < n; i++) {
            t[n1 - 1 + i] = createData(nextInt());
        }

        for (int i = n1 - 2; i >= 0; i--) {
            t[i] = update(t[2 * i + 1], t[2 * i + 2]);
        }
    }

    public static void set(int v, int ind, int a, int b, int k){
        //System.out.println(a + " " + b);
        if (b - a == 1) t[v] = createData(k);
        else {
            int mid = (a + b) / 2;
            if (ind < mid) set(2 * v + 1, ind, a, mid, k);
            else set(2 * v + 2, ind, mid, b, k);
            t[v] = update(t[2 * v + 1], t[2 * v + 2]);
        }
    }

    public static data rmq(int v, int l, int r, int a, int b) {
        //System.out.println(a + " " + b);
        //System.out.println(v + " " + l + " " + r);
        if (l >= b || r <= a) return new data(0, 0, 0, 0);
        if (l >= a && r <= b) {
            //System.out.println(v + " deep" + mas[v]);
            return t[v];
        }
        int mid = (l + r) / 2;
        data res1 = rmq(2 * v + 1, l, mid, a, b);
        data res2 = rmq(2 * v + 2, mid, r, a, b);
        if (res1.sum >= 0 && 0 <= res2.sum){
            res1.sum += res2.sum;
            return res1;
        }
        else if (res1.sum < res2.sum) return res1;
        else return res2;
    }

    public static void showTree(){
        for (int i = 0; i < n; i++) {
            System.out.print(t[n + i - 1].sum + " ");
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
