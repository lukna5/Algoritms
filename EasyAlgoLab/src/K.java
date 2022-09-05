import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class K {
    public static int n;
    public static PrintWriter writer;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        writer = new PrintWriter(System.out);
        n = in.nextInt();
        recurs("", 0, false);
        writer.close();
    }
    public static void recurs(String word, int n2, boolean was){
        if (n2 == n){
            writer.println(word);
            return;
        }
        word += "0 ";
        recurs(word, n2 + 1, false);
        word = word.substring(0, word.length() - 2);
        word += "1 ";
        if (!was) recurs(word, n2 + 1, true);
        //ArrayList<Integer> list1 = new ArrayList<>(list);
        //int size = list1.size();
    }
}
