
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddSetMin{

    public static class data{
        long sum;
        long upd;
        long add;

        public data(long sum, long upd, long add) {
            this.sum = sum;
            this.upd = upd;
            this.add = add;
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
            int command = nextInt();
            if (command == 1) set(0,0, n1, nextInt(), nextInt(), nextInt());
            else if (command == 2) add(0,0, n1, nextInt(), nextInt(), nextInt());
            else System.out.println(rmq(0, 0, n1, nextInt(), nextInt()));
            //showMas();
        }
    }
    public static void showMas(){
        for (int i = 0; i < 2 * n1; i++) {
            System.out.print(" | " + t[i].upd + " " + t[i].add + " " + t[i].sum);
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
            t[i] = new data(0, -1, 0);
        }
    }

    public static long getValue(int v, int l, int r){
        if (t[v].upd != -1) return t[v].upd * (r - l);
        return t[v].sum + t[v].add * (r - l);
    }

    public static void push(int v, int l, int r) {
        int mid = (l + r) / 2;
        if (t[v].upd != -1) {
            t[2 * v + 1].upd = t[v].upd;
            t[2 * v + 2].upd = t[v].upd;
            t[2 * v + 1].add = 0;
            t[2 * v + 2].add = 0;
            t[v].upd = -1;
        }
        else {
            if (t[2 * v + 1].upd != -1) t[2 * v + 1].upd += t[v].add;
            else t[2 * v + 1].add += t[v].add;
            if (t[2 * v + 2].upd != -1) t[2 * v + 2].upd += t[v].add;
            else t[2 * v + 2].add += t[v].add;
        }
        t[v].add = 0;
        t[v].sum = getValue(2 * v + 1, l, mid) + getValue(2 * v + 2, mid, r);

    }

    public static void add(int v, int l, int r, int a, int b, int k){
        //System.out.println(a + " " + b);
        if (l >= b || r <= a) return;
        if (l >= a && r <= b){
            if (t[v].upd != -1) t[v].upd += k;
            else t[v].add += k;
            return;
        }
        int mid = (l + r) / 2;
        push(v, l, r);
        add(2 * v + 1, l, mid, a, b, k);
        add(2 * v + 2, mid, r, a, b, k);
        t[v].sum = getValue(2 * v + 1, l, mid) + getValue(2 * v + 2, mid, r);
    }

    public static void set(int v, int l, int r, int a, int b, int k){
        //System.out.println(a + " " + b);
        if (l >= b || r <= a) return;
        if (l >= a && r <= b){
            t[v].upd = k;
            t[v].add = 0;
            return;
        }
        int mid = (l + r) / 2;
        push(v, l, r);
        set(2 * v + 1, l, mid, a, b, k);
        set(2 * v + 2, mid, r, a, b, k);
        t[v].sum = getValue(2 * v + 1, l, mid) + getValue(2 * v + 2, mid, r);
    }

    public static long rmq(int v, int l, int r, int a, int b) {
        //System.out.println(a + " " + b);
        //System.out.println(v + " " + l + " " + r);
        //if (v == 3) System.out.println(l + " " + a + " " + b + " " + r);
        if (l >= b || r <= a) return 0;
        if (l >= a && r <= b) {
            return getValue(v, l, r);
        }
        push(v, l, r);
        int mid = (l + r) / 2;
        return rmq(2 * v + 1, l, mid, a, b) + rmq(2 * v + 2, mid, r, a, b);
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