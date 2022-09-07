import java.util.Scanner;

public class Alo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long[][] position = new long[5][4];
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 4; j++) {
                if ((i==2 && j==2) || (i==3 && j == 2) || (i == 4)){
                    position[i][j] = 0;
                }
                else {
                    position[i][j] = 1;
                }
                //System.out.print(position[i][j]);
            }
            //System.out.println();
        }
        for (int i = 2; i < n + 1; i++) {
            long x11 = position[1][1];
            long x12 = position[1][2];
            long x13 = position[1][3];
            long x21 = position[2][1];
            long x23 = position[2][3];
            long x31 = position[3][1];
            long x32 = position[3][2];
            long x33 = position[3][3];
            long x42 = position[4][2];
            position[1][1] = (x32 + x23) % 1000000000;
            position[1][2] = (x31 + x33) % 1000000000;
            position[1][3] = (x21 + x32) % 1000000000;
            position[2][1] = (x13 + x33 + x42) % 1000000000;
            position[2][3] = (x11 + x31 + x42) % 1000000000;
            position[3][1] = (x12 + x23) % 1000000000;
            position[3][2] = (x11 + x13) % 1000000000;
            position[3][3] = (x21 + x12) % 1000000000;
            position[4][2] = (x21 + x23) % 1000000000;
        }
        long result = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 4; j++) {
                result+= (position[i][j])%1000000000;
            }
        }
        if (n == 1) result++;
        System.out.println(result % 1000000000);
    }
}
