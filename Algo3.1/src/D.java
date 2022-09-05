import java.util.*;

public class D {
    public static class Vertex{
        boolean visited = false;
        int num;
        int color = 0;
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
    static int n;
    static int time = 0;
    static Map<String, Integer> map = new HashMap<>();
    static TreeSet<Integer> set = new TreeSet<>();
    // static ArrayList<Integer> res1 = new ArrayList<>();
    static Stack<Integer> res = new Stack<>();
    static Vertex[] graf;
    static HashMap <Integer, Integer> was = new HashMap<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
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
                if (!map.containsKey(toRebro(in1, in2))) {
                    map.put(toRebro(in1, in2, 2), i + 1);
                    map.put(toRebro(in2, in1, 2), i + 1);
                }
                else{
                    map.put(toRebro(in1, in2, 1), i + 1);
                    map.put(toRebro(in2, in1, 1), i + 1);
                }
        }
        for (int i = 0; i < n; i++) {
            graf[i].visited = false;
        }
        findC();
        System.out.println(colors);
        for (int i = 0; i < n; i++) {
            System.out.print(graf[i].color + " ");
        }
        /*

        System.out.println(res.size());
        while (!res.empty()) System.out.print((res.pop() + 1) + " ");

        */
    }
    public static String toRebro(int a, int b){
        return (a + "_" + b);
    }
    public static String toRebro(int a, int b, int num){
        return (a + "_" + b + "_" + num);
    }
    public static void findC(){
        colors = 0;
        for (Vertex vertex : graf) {
            if (!vertex.visited) dfs(vertex, null);
        }
        for (int i = 0; i < n; i++) {
            if (graf[i].color == 0) {
                colors++;
                mark(graf[i], colors);
            }
        }
    }
    static int colors = 0;
    public static void mark(Vertex v, int colors2){
        v.color = colors2;
        for (int i = 0; i < v.sons.size(); i++) {
            if (v.sons.get(i).color == 0) {
                if (v.enter < v.sons.get(i).up) {
                    colors++;
                    mark(v.sons.get(i), colors);
                } else mark(v.sons.get(i), colors2);
            }
        }
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
        boolean have_identic = false;
        boolean was_identic = false;
        v.visited = true;
        time++;
        v.enter = v.up = time;
        for (int i = 0; i < v.sons.size(); i++) {
            if (batya != null && v.sons.get(i).num == batya.num && !was_identic){
                was_identic = true;
                continue;
            }
            if (v.sons.get(i).visited) v.up = Math.min(v.up, v.sons.get(i).enter);
            else {
                dfs(v.sons.get(i), v);
                v.up = Math.min(v.up, v.sons.get(i).up);
            }
        }

        //res.push(v.num + 1);
    }
}
