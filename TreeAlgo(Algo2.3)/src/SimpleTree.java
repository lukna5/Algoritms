import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SimpleTree{
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static long[] mas;
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
            else System.out.println(rsq(0, 0, n1, nextInt(), nextInt()));
        }
    }
    public static void build() throws IOException {
        n = nextInt();
        int count = 0;
        while (Math.pow(2, count) < n) count++;
        n1 = (int) Math.pow(2, count);
        m = nextInt();
        //System.out.println(n1);
        mas = new long[2 * n1];
        for (int i = 0; i < n; i++) {
            mas[n1 - 1 + i] = nextInt();
        }
        for (int i = n1 - 2; i >= 0; i--) {
            mas[i] = mas[2 * i + 1] + mas[2 * i + 2];
        }
    }

    public static void set(int v, int ind, int a, int b, int k){
        //System.out.println(a + " " + b);
        if (b - a == 1) mas[v] = k;
        else {
            int mid = (a + b) / 2;
            if (ind < mid) set(2 * v + 1, ind, a, mid, k);
            else set(2 * v + 2, ind, mid, b, k);
            mas[v] = mas[2 * v + 1] + mas[2 * v + 2];
        }
    }

    public static long rsq(int v, int l, int r, int a, int b) {
        //System.out.println(a + " " + b);
        //System.out.println(v + " " + l + " " + r);
        if (l >= b || r <= a) return 0;
        if (l >= a && r <= b) {
            //System.out.println(v + " deep" + mas[v]);
            return mas[v];
        }
        int mid = (l + r) / 2;
        //System.out.println(rsq(2 * v + 1, l, mid, a, b) + "rsq1");
        //System.out.println(rsq(2 * v + 2, mid, r, a, b) + "rsq2");
        //System.out.println(" from " + v + " " + l + " " + r );
        long result = rsq(2 * v + 1, l, mid, a, b) + rsq(2 * v + 2, mid, r, a, b);
        return result;
    }
    
    public static void showTree(){
        for (int i = 0; i < n; i++) {
            System.out.print(mas[n + i - 1] + " ");
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
