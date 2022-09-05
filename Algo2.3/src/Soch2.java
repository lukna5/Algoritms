import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Soch2 {
    public static int n;
    public static int k;
    public static PrintWriter writer;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        writer = new PrintWriter(System.out);
        n = in.nextInt();
        k = in.nextInt();
        recurs("", k + 1, 0);
        writer.close();
    }
    public static void recurs(String word, int last, int kol){
        if (kol == n){
            writer.println(word);
            return;
        }
        //ArrayList<Integer> list1 = new ArrayList<>(list);
        //int size = list1.size();
        int n1 = 1;
        while (n1 < k + 1){
            if (n1 >= last) {
                break;
            }
            word += n1 + " ";
            recurs(word, n1, kol + 1);
            word = word.substring(0, word.length() - Integer.toString(n1).length() - 1);
            n1++;
        }
    }
}
