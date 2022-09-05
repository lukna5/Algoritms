import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class J {
    public static int n;
    public static PrintWriter writer;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        writer = new PrintWriter(System.out);
        n = in.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            list.add(i);
        }
        recurs(list, "");
writer.close();
    }
    public static void recurs(ArrayList<Integer> list, String word){
        if (list.isEmpty()){
            writer.println(word);
            return;
        }
        //ArrayList<Integer> list1 = new ArrayList<>(list);
        //int size = list1.size();
        int n1 = n;
        while (n1 > 0){
            if (!list.contains(n1)){
                n1--;
                continue;
            }
            list.remove((Object) n1);
            word += n1 + " ";
            recurs(list, word);
            list.add(n1);
            word = word.substring(0, word.length() - 2);
            n1--;
        }
    }
}
