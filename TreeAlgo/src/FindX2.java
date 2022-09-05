import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindX2{

    public static class data{
        long min;
        long max;
        public data(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static data[] t;
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
            if (nextInt() == 1) set(0,nextInt(), 0, n1, nextInt());
            else{
                int k = nextInt();
                System.out.println(rmq(0, 0, n1, nextInt(), n1, k));
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
        t = new data[2 * n1];
        for (int i = 0; i < 2 * n1; i++) {
            t[i] = new data(-1, 0);
        }
        for (int i = 0; i < n; i++) {
            int next = nextInt();
            t[n1 + i - 1].max = next;
            //t[n1 + i - 1].min = next;
        }
        for (int i = n1 - 2; i >= 0; i--) {
            t[i].max = Math.max(t[2 * i + 1].max, t[2 * i + 2].max);
            //t[i].min = Math.min(t[2 * i + 2].min, t[2 * i + 2].min);
        }
    }

    public static long getValue(int v){
        if (t[v].max != 1234567890) return t[v].max;
        return t[v].min;
    }


    public static void set(int v, int ind, int a, int b, int k){
        //System.out.println(a + " " + b);
        if (b - a <= 1){
            t[v].max = k;
            //t[v].min = k;
            return;
        }
        int mid = (a + b) / 2;
        if (ind < mid) set(2 * v + 1, ind, a, mid, k);
        else set(2 * v + 2, ind, mid, b, k);
        t[v].max = Math.max(t[2 * v + 1].max, t[2 * v + 2].max);
    }

    public static long rmq(int v, int l, int r, int a, int b, int k) {
        //System.out.println(a + " " + b);
        //System.out.println(v + " " + l + " " + r);
        if (l >= b || r <= a) return -1;
        if (l >= a && r <= b) {
            if (t[v].max < k) return -1;
            if (v >= n1 - 1){
                return v - n1 + 1;
            } else {
                int mid = (l + r) / 2;
                if (k <= t[2 * v + 1].max) return rmq(2 * v + 1, l, mid, a, b, k);
                return rmq(2 * v + 2, mid, r, a, b, k);
            }
        }
        int mid = (l + r) / 2;
        long res1 = rmq(2 * v + 1, l, mid, a, b, k);
        long res2 = rmq(2 * v + 2, mid, r, a, b, k);
        if ((res1 == res2) && res1 == -1) return res1;
        if (res1 == -1) res1 = Integer.MAX_VALUE;
        if (res2 == -1) res2 = Integer.MAX_VALUE;
        return Math.min(res1, res2);
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
