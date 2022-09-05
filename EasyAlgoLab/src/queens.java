import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class queens {
    public static class struct{
        int x;
        int y;
        boolean table[][];
        public struct(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public struct(boolean[][] table) {
            this.table = table;
        }
        public static boolean identic(boolean[][] table1, boolean[][] table2){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (table1[i][j] != table2[i][j]) return false;
                }
            }
            return true;
        }
    };
    public static int n;
    public static ArrayList <struct> list;
    public static void main(String[] args) {
        list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        boolean[][] table = new boolean[n][n];
        boolean[][] table1 = new boolean[n][n];
        recurs(table, table1, n, 0);
        System.out.println(list.size());

    }
    public static void recurs(boolean[][] table, boolean[][] table11, int num, int res){
        if (num == 0){
            for (int i = 0; i < list.size(); i++) {
                if (struct.identic(list.get(i).table, table11)) return;
            }
            list.add(new struct(table11));
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] || table11[i][j]) continue;
                boolean[][] table1 = new boolean[n][n];
                boolean[][] table2 = new boolean[n][n];
                boolean[][] table22 = new boolean[n][n];
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        table1[k][l] = table[k][l];
                        table22[k][l] = table11[k][l];
                    }
                }
                for (int k = 0; k < n; k++) {
                    table[i][k] = true;
                    table[k][j] = true;
                }
                int l = j;
                for (int k = i; k < n && l < n; k++) {
                    table[k][l++] = true;
                }
                l = j;
                for (int k = i; k < n && l >= 0; k++) {
                        table[k][l--] = true;
                }
                l = j;
                for (int k = i; k >= 0 && l < n; k--) {
                        table[k][l++] = true;
                }
                l = j;
                for (int k = i; k >= 0 && l >= 0; k--) {
                    table[k][l--] = true;
                }
                table[i][j] = true;
                for (int k = 0; k < n; k++) {
                    for (int l1 = 0; l1 < n; l1++) {
                        table2[k][l1] = table[k][l1];
                    }
                }
                //System.out.println(" Now is num " + num + " i " + i  + " j " + j);
               /* for (int k = 0; k < n; k++) {
                    for (int l1 = 0; l1 < n; l1++) {
                        System.out.print(table[k][l1] + " ");
                    }
                    System.out.println();
                }
                System.out.println();

                */
                table22[i][j] = true;
                recurs(table2, table22, num - 1, res);
                table = table1;
            }
        }
    }
}
