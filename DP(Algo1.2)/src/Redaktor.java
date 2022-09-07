import java.util.Scanner;

public class Redaktor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String first = input.nextLine();
        String second = input.nextLine();
        int[][] dp = new int[first.length()+1][second.length()+1];
        for (int i = 0; i < first.length() + 1; i++) {
            for (int j = 0; j < second.length() + 1; j++) {
                if (i == 0) dp[0][j] = j;
                else if (j == 0) dp[i][0] = i;
                else{
                    if (first.charAt(i-1) == second.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else{
                        dp[i][j] = Math.min(Math.min(dp[i-1][j-1]+1, dp[i-1][j] + 1), dp[i][j-1] + 1);
                    }
                }
            }
        }
        /*for (int i = 0; i < first.length() + 1; i++) {
            for (int j = 0; j < second.length() + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

         */
        System.out.println(dp[first.length()][second.length()]);
    }
}
