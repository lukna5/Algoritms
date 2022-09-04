import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //System.out.println(Math.sqrt(n));
        int n1 = n;
        for (int i = 2; i < Math.sqrt(n) + 1; i++) {
            while (n % i == 0){
                System.out.print(i + " ");
                n /= i;
            }
        }
        if (n > 1) {
            System.out.println(n);
        }
    }

}
