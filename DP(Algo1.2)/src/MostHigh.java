import java.util.ArrayList;
import java.util.Scanner;

public class MostHigh {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] mas = new int[n+1];
        int[] dp = new int[n+1];
        int[] P = new int[n+1];
        P[1] = 0;
        for (int i = 1; i < n + 1; i++) {
            mas[i] = input.nextInt();
            dp[i] = 1;
        }
        int max = 0;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i; j++) {
                if (mas[j] < mas[i] && max < dp[j]) {
                    dp[i] = dp[j] + 1;
                    max = dp[j];
                    P[i] = j;
                }
            }
            max = 0;
        }
        int result = 0;
        /*for (int i = 1; i < n + 1; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();


         */
        int index = n;
        for (int i = 1; i < n + 1; i++) {
            if (dp[i] > result){
                result = dp[i];
                index = i;
            }
        }
        System.out.println(result);
        ArrayList<String> list = new ArrayList<>();
        while (index > 0){
            list.add(mas[index] + " ");
            index = P[index];
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i));
        }
    }
}
