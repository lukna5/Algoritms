import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Game {
    private static class Reader {

        static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        public static String readLine() throws IOException {
            return reader.readLine();
        }
        public static String readSymb() throws IOException {
            return Character.toString(reader.read());
        }
        public static int readInt() throws IOException {
            StringBuilder builder = new StringBuilder();
            int symb;
            while(!Character.isDigit(symb = reader.read()) && (symb != '-'));
            if (symb == '-') {
                builder.append('-');
                symb = reader.read();
            }
            while(symb < 58 && symb > 47){
                builder.append(Character.toString(symb));
                symb = reader.read();
            }
            try {
                return Integer.parseInt(builder.toString());
            }
            catch (NumberFormatException e){
                System.out.println("Can't Convert String into Integer");
                return -1;
            }
        }
        public static String readNext() throws IOException {
            int symb;
            StringBuilder builder = new StringBuilder();
            while ((symb = reader.read()) < 32 || symb > 122);
            builder.append(Character.toString(symb));
            while (!((symb = reader.read()) < 32 || symb > 122)){
                builder.append(Character.toString(symb));
            }
            return builder.toString();
        }
        public static void close() throws IOException {
            reader.close();
        }


    }
    public static class Vertex{
        ArrayList<Vertex> sons = new ArrayList<>();
        int num;
        ArrayList<Vertex> fathers = new ArrayList<>();
        public Vertex(int num) {
            this.num = num;
        }
        public void add(Vertex v){
            sons.add(v);
        }
    }
    public static void main(String[] args) throws IOException {
        int n = Reader.readInt();
        int m = Reader.readInt();
        int s = Reader.readInt();
        ArrayList<Vertex> graph = new ArrayList<>();
        graph.add(new Vertex(0));
        for (int i = 1; i < n + 1; i++) {
            graph.add(new Vertex(i));
        }
        for (int i = 0; i < m; i++) {
            int v = Reader.readInt();
            int u = Reader.readInt();
            graph.get(v).add(graph.get(u));
            graph.get(u).fathers.add(graph.get(v));
        }
        ArrayDeque<Vertex> deque = new ArrayDeque<>();
        int[] d = new int[n + 1];
        int[] out = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            out[i] = graph.get(i).sons.size();
            d[i] = -1;
        }
        for (int i = 1; i < n + 1; i++) {
            if (out[i] == 0) {
                d[i] = 0;
                deque.addLast(graph.get(i));
            }
        }

        while (!deque.isEmpty()){
            Vertex next = deque.poll();
            if (d[next.num] == 0){
                for (int i = 0; i < next.fathers.size(); i++) {
                    Vertex u = next.fathers.get(i);
                    if (d[u.num] == -1){
                        d[u.num] = 1;
                        deque.addLast(u);
                    }
                }
            }
            else {
                for (int i = 0; i < next.fathers.size(); i++) {
                    Vertex u = next.fathers.get(i);
                    out[u.num]--;
                    if (out[u.num] == 0){
                        d[u.num] = 0;
                        deque.addLast(u);
                    }
                }
            }
        }
        if (d[s] == 1) {
            System.out.println("First player wins");
        } else {
            System.out.println("Second player wins");
        }
    }
}
