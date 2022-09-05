import java.util.*;

public class Components {
    static class Pair implements Comparable<Pair>

    {

        int x, y;

        Pair(int x, int y)

        {

            this.x = x;

            this.y = y;

        }



        @Override

        public int compareTo(Pair a)

        {

            if (x == a.x) return y - a.y;

            return x - a.x;

        }

    }
    public static class Vertex{
        boolean visited = false;
        boolean isVisited = false;
        int num;
        int color = -1;
        ArrayList<Vertex> sons = new ArrayList<>();
        ArrayList<Vertex> fathers = new ArrayList<>();
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
        graf = new Vertex[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graf[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int in1 = in.nextInt();
            int in2 = in.nextInt();
            if (!map.containsKey(toRebro(in1, in2)) && in1 != in2) {
                graf[in1].sons.add(graf[in2]);
                graf[in2].fathers.add(graf[in1]);
                map.put(toRebro(in1, in2), i + 1);
            }
        }
        findComp();
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

    static TreeSet<Pair> sett = new TreeSet<Pair>();
    public static void findComp(){

        for (int i = 1; i < n + 1; i++) {
            if (!graf[i].visited) dfsF(graf[i]);
        }
        int col = 0;
        for (int i = 1; i < n + 1; i++) {
            //if (!graf[i].visited){
            if (orders.get(n - i).color == -1) {
                dfsS(orders.get(n - i), col++);
            }
            //}
        }
        int resC = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < graf[i].sons.size(); j++) {
                if (graf[i].color != graf[i].sons.get(j).color) sett.add(
                        new Pair(graf[i].color, graf[i].sons.get(j).color));
            }
        }
        System.out.println(sett.size());
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
    static Vector<Vertex> orders = new Vector<Vertex>();
    static Stack<Integer> components = new Stack<>();

    public static void dfsF(Vertex v){
        v.visited = true;
        for (int i = 0; i < v.sons.size(); i++) {
            if (!v.sons.get(i).visited) dfsF(v.sons.get(i));
        }
        orders.add(v);
    }
    public static void dfsS(Vertex v, int col){
        v.color = col;
        for (int i = 0; i < v.fathers.size(); i++) {
            if (v.fathers.get(i).color == -1) dfsS(v.fathers.get(i), col);
        }
    }
    public static int dfsSimple(Vertex v){
        if (v.isVisited) return 1;
        v.isVisited = true;
        int counter = 0;
        for (int i = 0; i < v.sons.size(); i++) {
            counter += dfsSimple(v.sons.get(i));
        }
        return counter;
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
