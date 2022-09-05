
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;

public class Earthquake{

    public static class data{
        int min;
        int upd;
        int max;
        int build;
        public data(int min, int max, int upd, int build) {
            this.min = min;
            this.upd = upd;
            this.max = max;
            this.build = build;
        }
    }
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter writer = new PrintWriter(System.out);
    static data[] t;
    static int m;
    static int n;
    static int n1;
    public static void main(String[] args) throws IOException {
        build();
        for (int i = 0; i < m; i++) {
            if (nextInt() == 1){
                int ind = nextInt();
                set(0,0, n1, ind, ind + 1, nextInt());
            }
            else writer.println(rmq(0, 0, n1, nextInt(), nextInt(), nextInt()));
            //showMas();
        }
        writer.close();
    }

    public static void showMas(){
        for (int i = 0; i < 2 * n1; i++) {
            System.out.print(" | " + t[i].upd + " " + t[i].min + " " + t[i].max + " " +  t[i].build);
            if (i == 0 || i == 2 || i == 6) System.out.println();
        }
        System.out.println();
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
            t[i] = new data(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 0);
        }
    }

    public static int getMin(int v){
        if (t[v].upd != Integer.MIN_VALUE) return t[v].upd;
        return t[v].min;
    }

    public static int getMax(int v){
        if (t[v].upd != Integer.MIN_VALUE) return t[v].upd;
        return t[v].max;
    }



    public static void push(int v) {
        if (t[v].upd != Integer.MIN_VALUE) {
            t[2 * v + 1].upd = t[v].upd;
            t[2 * v + 2].upd = t[v].upd;
                    t[v].min = Math.min(t[2 * v + 1].min, t[2 * v + 2].max);
                    t[v].max = Math.max(t[2 * v + 1].min, t[2 * v + 2].max);
                    t[v].build = t[2 * v + 1].build + t[2 * v + 2].build;
                    t[v].upd = Integer.MIN_VALUE;

        }

    }

    public static void set(int v, int l, int r, int a, int b, int k) {
        //System.out.println(a + " " + b);
        if (l >= b || r <= a) return;
        if (l >= a && r <= b) {
            t[v].upd = k;
            return;
        }
        int mid = (l + r) / 2;
        push(v);
        set(2 * v + 1, l, mid, a, b, k);
        set(2 * v + 2, mid, r, a, b, k);
        t[v].min = Math.min(t[2 * v + 1].min, t[2 * v + 2].min);
        t[v].max = Math.max(t[2 * v + 1].max, t[2 * v + 2].max);
        t[v].build = t[2 * v + 1].build + t[2 * v + 2].build;
    }

    public static data getValue(int v, int l, int r){
        data res = new data(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 0);
        res.upd = t[v].upd;
        if (t[v].upd == 0){
            return res;
        }
        if (t[v].upd != Integer.MIN_VALUE){
            res.min = t[v].upd;
            res.max = t[v].upd;
            res.build = r - l;
            return res;
        }
        else {
            res.min = t[v].min;
            res.max = t[v].max;
            res.build = t[v].build;
            return res;
        }
    }
    public static int rmq(int v, int l, int r, int a, int b, int k) {
        if (l >= b || r <= a) return 0;
        if (l >= a && r <= b) {
            data res = getValue(v, l, r);
            if (v >= n1 - 1){
                if (res.min != Integer.MAX_VALUE && res.min <= k) {
                    set(0, 0, n1, l, r, 0);
                    return 1;
                }
                return 0;
            } else {
                if (res.min != Integer.MAX_VALUE && res.min > k) return 0;
                else if (res.max != Integer.MIN_VALUE && res.max <= k){
                    int res1 = res.build;
                    set(0, 0, n1, l, r, 0);
                    return res1;
                }
                int mid = (l + r) / 2;
                push(v);
                return rmq(2 * v + 1, l, mid, a, b, k) + rmq(2 * v + 2, mid, r, a, b, k);
            }
        }
        else {
            push(v);
            int mid = (l + r) / 2;
            return rmq(2 * v + 1, l, mid, a, b, k) + rmq(2 * v + 2, mid, r, a, b, k);
        }
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