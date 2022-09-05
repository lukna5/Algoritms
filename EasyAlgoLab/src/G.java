import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class G {
    public static int n;
    public static int k;
    public static PrintWriter writer;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        writer = new PrintWriter(System.out);
        n = in.nextInt();
        k = in.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(i);
        }
        recurs(list, "", 0);
        writer.close();
    }
    public static void recurs(ArrayList<Integer> list, String word, int n2){
        if (n2 == n){
            writer.println(word);
            return;
        }
        //ArrayList<Integer> list1 = new ArrayList<>(list);
        //int size = list1.size();
        int n1 = 0;
        while (n1 < k){
            word += n1 + " ";
            recurs(list, word, n2 + 1);
            word = word.substring(0, word.length() - 2);
            n1++;
        }
    }
}
