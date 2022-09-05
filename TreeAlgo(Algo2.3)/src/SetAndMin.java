import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetAndMin{

    public static class data{
        long min;
        long upd;
        public data(long min, long upd) {
            this.min = min;
            this.upd = upd;
        }
    }
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static data[] t;
    static int m;
    static int n;
    static int n1;
    public static void main(String[] args) throws IOException {
        build();
        for (int i = 0; i < m; i++) {
            if (nextInt() == 1) set(0,0, n1, nextInt(), nextInt(), nextInt());
            else System.out.println(rmq(0, 0, n1, nextInt(), nextInt()));
        }
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
            t[i] = new data(0, -1);
        }
    }

    public static long getValue(int v){
        if (t[v].upd != -1) return t[v].upd;
        return t[v].min;
    }

    public static void push(int v) {
        if (t[v].upd != -1) {
            t[2 * v + 1].upd = t[v].upd;
            t[2 * v + 2].upd = t[v].upd;
            t[v].min = Math.min(getValue(2 * v + 1), getValue(2 * v + 2));
            t[v].upd = -1;
        }

    }

    public static void set(int v, int l, int r, int a, int b, int k){
        //System.out.println(a + " " + b);
        if (l >= b || r <= a) return;
        if (l >= a && r <= b){
            t[v].upd = k;
            return;
        }
        int mid = (l + r) / 2;
        push(v);
        set(2 * v + 1, l, mid, a, b, k);
        set(2 * v + 2, mid, r, a, b, k);
        t[v].min = Math.min(getValue(2 * v + 1), getValue(2 * v + 2));
    }

    public static long rmq(int v, int l, int r, int a, int b) {
        //System.out.println(a + " " + b);
        //System.out.println(v + " " + l + " " + r);
        //if (v == 3) System.out.println(l + " " + a + " " + b + " " + r);
        if (l >= b || r <= a) return Integer.MAX_VALUE;
        if (l >= a && r <= b) {
            return getValue(v);
        }
        push(v);
        int mid = (l + r) / 2;
        return Math.min(rmq(2 * v + 1, l, mid, a, b), rmq(2 * v + 2, mid, r, a, b));
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