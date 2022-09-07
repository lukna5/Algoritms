import java.util.Scanner;

public class CherepashkaNatashka {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[][] cost = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        int[][] Px = new int[n + 1][m + 1];
        int[][] Py = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                cost[i][j] = input.nextInt();
            }
        }
        dp[1][1] = cost[1][1];
        Px[1][1] = 0;
        Py[1][1] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (i == 1 && j != 1) {
                    dp[i][j] = dp[i][j - 1] + cost[i][j];
                    Px[i][j] = i;
                    Py[i][j] = j - 1;
                } else if (j == 1 && i != 1) {
                    dp[i][j] = dp[i - 1][j] + cost[i][j];
                    Px[i][j] = i - 1;
                    Py[i][j] = j;
                } else if (j != 1 && i != 1){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + cost[i][j];
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        Px[i][j] = i - 1;
                        Py[i][j] = j;
                    } else {
                        Px[i][j] = i;
                        Py[i][j] = j - 1;
                    }
                }
            }
        }
        int indexx = Px[n][m];
        int indexy = Py[n][m];
        int x = n;
        int y = m;
        int last = x;
        StringBuilder path = new StringBuilder();
        while (indexx > 0 || indexy > 0) {
            int indexx1 = indexx;
            //System.out.println(indexx + " " + indexy);
            if (x == 1 && y == 1){
                if (last == 1){
                    path.append("R");
                } else path.append("D");
            }
            else if (x != Px[x][y]){
                //System.out.println(x + " " + Px[x][y]);
                path.append("D");
            } else{
                //System.out.println(x + " " + Px[x][y]);
                path.append("R");
            }
            last = x;
            x = Px[x][y];
            y = Py[last][y];
            indexx = Px[indexx][indexy];
            indexy = Py[indexx1][indexy];
            //System.out.println(x + " " + y);
        }
        /*for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                System.out.print(Px[i][j] + " " + Py[i][j] + " next ");
            }
            System.out.println();
        }
         */
        System.out.println(dp[n][m]);
        System.out.println(path.reverse());
    }
}
