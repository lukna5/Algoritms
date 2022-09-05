import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Soch1 {
    public static int n;
    public static int k;
    public static PrintWriter writer;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        writer = new PrintWriter(System.out);
        n = in.nextInt();
        k = in.nextInt();
        recurs("", 0, k);
        writer.close();
    }
    public static void recurs(String word, int n2, int k){
        if (n2 == n){
            writer.println(word);
            return;
        }
        if (k == 0){
            word += "0 ";
            recurs(word, n2 + 1, k);
            return;
        }
        if (n - n2 > k){
            word += "0 ";
            recurs(word, n2 + 1, k);
            word = word.substring(0, word.length() - 2);
        }
        word += "1 ";
        recurs(word, n2 + 1, k - 1);
        //ArrayList<Integer> list1 = new ArrayList<>(list);
        //int size = list1.size();
    }
}
