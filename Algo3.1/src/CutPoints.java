import java.util.*;

public class CutPoints {
    public static class Vertex{
        boolean visited = false;
        int num;
        ArrayList<Vertex> sons = new ArrayList<>();
        int enter;
        int up;
        public Vertex(int num) {
            this.num = num;
        }
    }
    public static class Rebro{
        int s;
        int b;

        public Rebro(int s, int b) {
            this.s = s;
            this.b = b;
        }
    }
    static int time = 0;
    static TreeSet<Integer> set = new TreeSet<>();
   // static ArrayList<Integer> res1 = new ArrayList<>();
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
            int in1 = in.nextInt() - 1;
            int in2 = in.nextInt() - 1;
            graf[in1].sons.add(graf[in2]);
            graf[in2].sons.add(graf[in1]);

        }
        find();
        System.out.println(set.size());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }/*

        System.out.println(res.size());
        while (!res.empty()) System.out.print((res.pop() + 1) + " ");

        */
    }
    public static void find(){
        time = 0;
        for (Vertex vertex : graf) {
            if (!vertex.visited) dfs(vertex, null);
        }
        //while (!res.empty()){
        //   System.out.print(res.pop() + " ");
        //}
    }
    public static void dfs(Vertex v, Vertex batya){
        v.visited = true;
        time++;
        int c = 0;
        v.up = v.enter = time;
        for (int i = 0; i < v.sons.size(); i++) {
            if (batya != null && v.sons.get(i).num == batya.num) continue;
            if (v.sons.get(i).visited) v.up = Math.min(v.up, v.sons.get(i).enter);
            else {
                dfs(v.sons.get(i), v);
                c++;
                v.up = Math.min(v.up, v.sons.get(i).up);
                if (batya != null && v.sons.get(i).up >= v.enter){
                   // res.push(v.num);
                    set.add(v.num + 1);
                }
            }
        }
        if (batya == null && c >= 2) {
            //res.push(v.num);
            set.add(v.num + 1);
        }
            //System.out.println(getNum(new Bridges.Rebro(v.num, batya.num)));
        //res.push(v.num + 1);
    }
}
