import java.util.Arrays;

public class Myaso {
    public static void main(String[] args) {
        int[] mas = {1, 2, 3};
        int number = 5;
        metod(mas, number);
        System.out.println(Arrays.toString(mas) + " " + number);
    }
    public static void metod(int[] mas, int number){
        mas[1] =8;
        number = 4;
    }
}
