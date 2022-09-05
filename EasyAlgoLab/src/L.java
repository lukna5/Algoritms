import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class L {
    public static int n;
    public static int k;
    public static PrintWriter writer;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        writer = new PrintWriter(System.out);
        n = in.nextInt();
        k = in.nextInt();
        recurs(new StringBuilder(), 0,  0, 1, 0);
        writer.close();
    }
    public static int recurs(StringBuilder word, int lefts, int rights, int kol, int balance){
        if (balance < 0) return kol;
        if (lefts == n && rights == n){
            if (kol % k == 0) {
                writer.println(word);
                return kol + 1;
            }
            return kol + 1;
        }
        StringBuilder word1 = new StringBuilder(word.toString());
        if (lefts < n) {
            word.append("(");
            kol = recurs(word, lefts + 1, rights, kol, balance + 1);
        }
        if (rights < n) {
            word1.append(")");
            kol = recurs(word1, lefts, rights + 1, kol, balance - 1);
        }
        //ArrayList<Integer> list1 = new ArrayList<>(list);
        //int size = list1.size();
        //System.out.println(kol);
        return kol;
    }
}
