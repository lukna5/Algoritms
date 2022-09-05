import javax.swing.event.TreeSelectionEvent;
import java.util.*;
import java.util.AbstractMap;

public class Bridges {
    public static class Vertex{
        boolean visited = false;
        int num;
        String color = "white";
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
    static TreeSet<Integer> set = new TreeSet();
    static Vertex[] graf;
    static Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) {
       // map.put(new Rebro(1, 2), 3);
        //System.out.println(map.get(new Rebro(1, 2)));
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
            map.put(in1 + "_" + in2, i + 1);
            map.put(in2 + "_" + in1, i + 1);
        }
        find();
    }
    public static void find(){
        time = 0;
        for (Vertex vertex : graf) {
            if (!vertex.visited) dfs(vertex, null);
        }
        System.out.println(set.size());
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        /*for(Map.Entry<Integer, Integer> pair : map.entrySet())
        {
            Integer value = pair.getValue();
            System.out.println(value);
        }

         */
        /*System.out.println(res1.size());
        //Collections.sort(res1);
        for (int i = 0; i < res1.size(); i++) {
           System.out.print(res1.get(i) + 1 + " ");
        }

         */
    }
    /*public static int getNum(Rebro rebro){
        for (int i = 0; i < list.size(); i++) {
            if ((rebro.s == list.get(i).s && rebro.b == list.get(i).b) || (rebro.s == list.get(i).b && rebro.b == list.get(i).s)){
                return i;
            }
            //System.out.println(rebro.s + " " + rebro.b);
            //System.out.println(list.get(i).s + " " + list.get(i).b);
            //System.out.println("Next");
        }
        return -1;
    }

     */

    public static void dfs(Vertex v, Vertex batya){
        v.visited = true;
        time++;
        v.enter = v.up = time;
        for (int i = 0; i < v.sons.size(); i++) {
            if (batya != null && v.sons.get(i).num == batya.num) continue;
            if (v.sons.get(i).visited) v.up = Math.min(v.up, v.sons.get(i).enter);
            else {
                dfs(v.sons.get(i), v);
                v.up = Math.min(v.up, v.sons.get(i).up);
                if (v.sons.get(i).up > v.enter)
                    set.add(map.get(Integer.toString(v.num) + "_" + Integer.toString(v.sons.get(i).num)));
            }
        }

        //res.push(v.num + 1);
    }
}
