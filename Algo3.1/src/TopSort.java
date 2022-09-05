import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class TopSort {
    public static class Vertex{
        boolean visited = false;
        int num;
        String color = "white";
        ArrayList<Vertex> sons = new ArrayList<>();

        public Vertex(int num) {
            this.num = num;
        }
    }
    static Stack<Integer> res = new Stack<>();
    static Vertex[] graf;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        graf = new Vertex[n];
        for (int i = 0; i < n; i++) {
            graf[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            graf[in.nextInt() - 1].sons.add(graf[in.nextInt() - 1]);
        }
        topSort();

    }
    public static void topSort(){
        boolean cycled = false;
        for (Vertex vertex : graf) {
            //System.out.println("Start " + (vertex.num + 1));
            cycled |= checkCycle(vertex);
            //System.out.println(cycled);
        }
        if (cycled){
            System.out.println(-1);
            return;
        }
        for (Vertex vertex : graf) {
            dfs(vertex);
        }
        while (!res.empty()){
            System.out.print(res.pop() + " ");
        }
    }
    public static boolean checkCycle(Vertex v){
        //System.out.println("Здесь " + (v.num + 1));
        if (v.color.equals("grey")) return true;
        else if (v.color.equals("black")) return false;
        v.color = "grey";
        boolean inf = false;
        for (int i = 0; i < v.sons.size(); i++) {
            inf |= checkCycle(v.sons.get(i));
        }
        v.color = "black";
        return inf;
    }
    public static void dfs(Vertex v){
        if (v.visited) return;
        v.visited = true;
        for (int i = 0; i < v.sons.size(); i++) {
            dfs(v.sons.get(i));
        }
        res.push(v.num + 1);
    }
}
