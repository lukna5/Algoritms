import java.util.ArrayList;
import java.util.Scanner;

public class VTraveSidit {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int[] cost = new int[n + 1];
        cost[0] = 0;
        cost[n] = 0;
        for (int i = 2; i < n; i++) {
            cost[i] = input.nextInt();
        }
        int[] dp = new int[n + 1];
        int[] P = new int[n + 1];
        int[] jumps = new int[n + 1];
        jumps[1] = 0;
        P[1] = 0;
        dp[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            int max = -200000000;
            int index1 = i - 1;
            int index = Math.max(1, i - k);
            for (int j = index; j < i; j++) {
                if (max < dp[j]){
                    max = dp[j];
                    index1 = j;
                }
            }
            jumps[i] += jumps[index1] + 1;
            P[i] = index1;
            //System.out.println(max + " " + cost[i]);
            dp[i] = max + cost[i];
            //System.out.println(dp[i]);
        }
        /*for (int i = 0; i < n - 1; i++) {
            System.out.print(jumps[i] + " ");
        }

         */
        /*for (int i = 1; i < n + 1; i++) {
            System.out.print(P[i] + " ");
        }

         */
        System.out.println(dp[n]);
        System.out.println(jumps[n]);
        int index2 = n;
        int count = jumps[n] - 1;
        int[] results = new int[jumps[n] + 1];
        while (index2 > 1){
            results[count] = index2;
            count--;
            index2 = P[index2];
        }
        System.out.print("1 ");
        for (int i = 0; i < jumps[n]; i++) {
            System.out.print(results[i] + " ");
        }
        //System.out.println(n);
    }
}
