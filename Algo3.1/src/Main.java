import java.util.*;



class Pair implements Comparable<Pair>

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



public class Main

{

    static ArrayList<Integer>[] g, gr;

    static int used[], color[];

    static Vector<Integer> top = new Vector<Integer>();

    static TreeSet<Pair> s = new TreeSet<Pair>();



    static void dfs1(int v)

    {

        used[v] = 1;

        for(int i = 0; i < g[v].size(); i++)

        {

            int to = g[v].get(i);

            if (used[to] == 0) dfs1(to);

        }

        top.add(v);

    }



    static void dfs2(int v, int c)

    {

        color[v] = c;

        for(int i = 0; i < gr[v].size(); i++)

        {

            int to = gr[v].get(i);

            if (color[to] == -1) dfs2(to,c);

        }

    }



    @SuppressWarnings("unchecked")

    public static void main(String[] args)

    {

        Scanner con = new Scanner(System.in);

        int n = con.nextInt();

        int m = con.nextInt();



        g = new ArrayList[n+1];

        for(int i = 0; i <= n; i++)

            g[i] = new ArrayList<Integer>();

        gr = new ArrayList[n+1];

        for(int i = 0; i <= n; i++)

            gr[i] = new ArrayList<Integer>();



        for (int i = 0; i < m; i++)

        {

            int a = con.nextInt();

            int b = con.nextInt();

            g[a].add(b);

            gr[b].add(a);

        }



        used = new int[n+1];

        for(int i = 1; i <= n; i++)

            if (used[i] == 0) dfs1(i);



        color  = new int[n+1];

        Arrays.fill(color, -1);

        int c = 0;

        for(int i = 1; i <= n; i++)

        {

            int v = top.get(n-i);

            if (color[v] == -1) dfs2(v,c++);

        }



        for(int i = 1; i < g.length; i++)

            for(int j = 0; j < g[i].size(); j++)

            {

                int to = g[i].get(j);

                if (color[i] != color[to])

                    s.add(new Pair(color[i],color[to]));

            }



        System.out.println(s.size());

        con.close();

    }

}  