import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class dm1 {
    public static class Struct{
        String letters;
        char from;
        public Struct(char from, String let) {
            this.letters = let;
            this.from = from;
        }
    }
    public static boolean[][] toLet = new boolean[70][70];
    public static boolean[][][] toCombine = new boolean[70][70][70];
    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("automaton.in"));
        FileWriter wr = new FileWriter("automaton.out");
        int n = sc.nextInt();
        String start = sc.next();
        for (int i = 0; i < n; i++) {
            String first = sc.next();
            sc.next();
            String second = sc.next();
            if (second.length() == 2){
                toCombine[first.charAt(0)- 65][second.charAt(0)- 65][second.charAt(1)- 65] = true;
            }else {
                toLet[first.charAt(0)- 65][second.charAt(0)- 65] = true;
            }
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            //System.out.println(recurs(start, sc.next()));
            if (recurs(start, sc.next())) wr.write("yes \n");
            else wr.write("no \n");
        }
        wr.close();
    }
    public static boolean recurs(String word, String end){
        boolean res = false;
        if (word.length() == 0 && end.length() == 0) return true;
        if (word.length() == 0 || end.length() == 0) return false;
        if (word.length() > end.length()) return false;
        while(word.length() > 0 && Character.isLowerCase(word.charAt(0))){
            if (word.charAt(0) != end.charAt(0)) return false;
            end = end.substring(1);
            word = word.substring(1);
        }
        if (word.length() == 0 && end.length() == 0) return true;
        if (word.length() == 0 || end.length() == 0) return false;
        //System.out.println((char) (int)word.charAt(0));
        int next1 = (int)word.charAt(0) - 65;
        int next2 = (int)end.charAt(0) - 65;
        word = word.substring(1);
        end = end.substring(1);
        //System.out.println(next1);
        if (word.length() == 0 && end.length() == 0 && next1 == next2) return true;
        if (word.length() == 0 && end.length() == 0 && toLet[next1][next2]) return true;
        for (int i = 0; i < 70; i++) {
            if (toCombine[next1][next2][i]) {
               // System.out.println(i + " i");
                res |= recurs(Character.toString(i + 65) + word,end);
                if (res) return true;
            }
        }
        if(toLet[next1][next2]){
            res |= recurs(word, end);
        }

        return res;
    }
}
