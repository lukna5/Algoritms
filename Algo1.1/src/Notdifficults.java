import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Notdifficults {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        int[] result = new int[t];
        for (int t1=0; t1<t; t1++){
        int n = input.nextInt();
        int mas[] = new int[n];
        Map map = new HashMap();
        for (int i = 0; i<n; i++) mas[i]=input.nextInt();
        for (int j = n-1; j>0; j--) {
            for (int i = 0; i < j; i++) {
                int a = 2 * mas[j] - mas[i];
                if ((map.containsKey(a))) {
                    result[t1] += (int) map.get(a);
                }
            }
            if (map.containsKey(mas[j])) map.put(mas[j], (int) map.get(mas[j]) + 1);
            else map.put(mas[j], 1);
        }
        }
        for (int i = 0; i<t; i++) System.out.println(result[i]);
    }
}