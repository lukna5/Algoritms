import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Krypto{
    public static class masx2{
        int[] m;
        public masx2(int[] m) {
            this.m = m;
        }

        public masx2(int a, int b, int c, int d){
            m = new int[4];
            m[0] = a;
            m[1] = b;
            m[2] = c;
            m[3] = d;
        }
    }
    static PrintWriter writer = new PrintWriter(System.out);
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static masx2[] mas;
    static int m;
    static int n;
    static int n1;
    static int r;
    public static void main(String[] args) throws IOException {
        build();
        //System.out.println(rsq(0, 0, n1, 0, 3));
        for (int i = 0; i < m; i++) {
            //showTree();
            showMas(rsq(0,0, n1, nextInt() - 1, nextInt()));
        }
        writer.close();
    }

    public static void showMas(masx2 mas1){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                writer.print(mas1.m[2 * i + j] + " ");
            }
            writer.println();
        }
        writer.println();
    }
    public static void build() throws IOException {
        r = nextInt();
        n = nextInt();
        m = nextInt();
        int count = 0;
        while (Math.pow(2, count) < n) count++;
        n1 = (int) Math.pow(2, count);
        mas = new masx2[2 * n1];
        for (int i = 0; i < 2 * n1; i++) {
            mas[i] = new masx2(1, 0, 0, 1);
        }
        for (int i = 0; i < n; i++) {
            mas[n1 + i - 1] = new masx2(nextInt(), nextInt(), nextInt(), nextInt());
        }
        //System.out.println(n1);
        for (int i = n1 - 2; i >= 0; i--) {
            mas[i] = multMatrix(mas[2 * i + 1], mas[2 * i + 2]);
        }
    }


    public static masx2 rsq(int v, int l, int r, int a, int b) {
        //System.out.println(a + " " + b);
        //System.out.println(v + " " + l + " " + r);
        if (l >= b || r <= a) return new masx2(1, 0, 0, 1);
        if (l >= a && r <= b) {
            //System.out.println(v + " deep" + mas[v]);
            return mas[v];
        }
        int mid = (l + r) / 2;
        //System.out.println(rsq(2 * v + 1, l, mid, a, b) + "rsq1");
        //System.out.println(rsq(2 * v + 2, mid, r, a, b) + "rsq2");
        //System.out.println(" from " + v + " " + l + " " + r );
        return multMatrix(rsq(2 * v + 1, l, mid, a, b), rsq(2 * v + 2, mid, r, a, b));
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
        //System.out.println(builder);
        return Integer.parseInt(builder.toString());
    }

    public static masx2 multMatrix(masx2 a1, masx2 b1){
        int[] a = a1.m;
        int[] b = b1.m;
        int[] result = new int[4];
        result[0] = (a[0] * b[0] % r + a[1] * b[2] % r) % r;
        result[1] = (a[0] * b[1] % r + a[1] * b[3] % r) % r;
        result[2] = (a[2] * b[0] % r + a[3] * b[2] % r) % r;
        result[3] = (a[2] * b[1] % r + a[3] * b[3] % r) % r;
        return new masx2(result);
    }
}
