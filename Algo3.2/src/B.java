import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B {
    public static class Pair{
        int num;
        int length;

        public Pair(int num, int length) {
            this.num = num;
            this.length = length;
        }
    }
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
            while (!Character.isDigit(symb = reader.read()) && (symb != '-')) ;
            if (symb == '-') {
                builder.append('-');
                symb = reader.read();
            }
            while (symb < 58 && symb > 47) {
                builder.append(Character.toString(symb));
                symb = reader.read();
            }
            try {
                return Integer.parseInt(builder.toString());
            } catch (NumberFormatException e) {
                System.out.println("Can't Convert String into Integer");
                return -1;
            }
        }

        public static String readNext() throws IOException {
            int symb;
            StringBuilder builder = new StringBuilder();
            while ((symb = reader.read()) < 32 || symb > 122) ;
            builder.append(Character.toString(symb));
            while (!((symb = reader.read()) < 32 || symb > 122)) {
                builder.append(Character.toString(symb));
            }
            return builder.toString();
        }

        public static void close() throws IOException {
            reader.close();
        }
    }


        static HashMap<String, Integer> matrix;
        //static short[][] matrix;
        static int[] d;
        static boolean[] used;

        public static String toRebro(int v, int u) {
            return v + "_" + u;
        }

        public static class BinaryTree {
            Vertex start;

            public class Vertex {
                int num;
                int length;
                Vertex left = null;
                Vertex right = null;
                Vertex father;
                public Vertex(int num, int length) {
                    this.num = num;
                    this.length = length;
                }

                public Vertex(Pair pair) {
                    this.num = pair.num;
                    this.length = pair.length;
                }
            }

            public void add(Pair pair) {
                if (start == null) {
                    start = new Vertex(pair);
                    return;
                }
                addRecurs(start, pair);
            }
            public void addRecurs(Vertex vertex, Pair pair){
                if (vertex.length >= pair.length) {
                    if (vertex.left == null){
                        vertex.left = new Vertex(pair);
                    }
                    else {
                        addRecurs(vertex.left, pair);
                    }
                } else {
                    if (vertex.right == null){
                        vertex.right = new Vertex(pair);
                    }
                    else {
                        addRecurs(vertex.right, pair);
                    }
                }
            }
            public Pair pollFirst(){
                if (start == null) return null;
                Vertex next = start;
                Vertex last = start;
                while (next.left != null){
                    start = next;
                    next = next.left;
                }
                Pair res = new Pair(next.num, next.length);
                if (next == start) start = next.right;
                else {
                    last.left = next.right;
                }
                return res;
            }
            public void rm(Pair pair){
                delete(start, pair);
            }
            public Vertex delete(Vertex node, Pair pair){
                int k = pair.length;
                if (node == null) return null;
                if (k < node.length && node.num != pair.num) node.left = delete(node.left, pair);
                else if (k >= node.length && node.num != pair.num) node.right = delete(node.right, pair);
                else if (node.left != null && node.right != null) {
                    Vertex min = minimum(node.right);
                    node.num = min.num;
                    node.length = min.length;
                    node.right = delete(node.right, new Pair(node.num, node.length));
                }
                else if (node.left != null) node = node.left;
                else if (node.right != null)  node = node.right;
                else{
                    //System.out.println(node.x + " node");
                    node = null;

                }
                return node;
            }
            public static Vertex minimum(Vertex node){
                if (node.left == null) return node;
                else return minimum(node.left);
            }
        }
        public static void main(String[] args) throws IOException {
            Comparator<Pair> comparator = new Comparator<Pair>(){

                @Override
                public int compare(Pair v, Pair u) {
                    if (v.length > u.length) return 1;
                    else if (v.length == u.length && v.num == u.num) return 0;
                    return -1;
                }
            };
            int n = Reader.readInt();
            int m = Reader.readInt();
            matrix = new HashMap<>();
            d = new int[n + 1];
            used = new boolean[n + 1];
            for (int i = 1; i < n + 1; i++) {
                d[i] = Integer.MAX_VALUE;
                used[i] = false;
            }
            d[1] = 0;
            for (int i = 0; i < m; i++) {
                int v = Reader.readInt();
                int u = Reader.readInt();
                int r = Reader.readInt();
                matrix.put(toRebro(v, u), r);
                matrix.put(toRebro(u, v), r);
            }
            BinaryTree set = new BinaryTree();
            //ArrayList<Pair> pairs = new ArrayList();
            //Pair first = new Pair((1, 0))
            //pairs.add(new Pair());
            TreeSet<Pair> set1 = new TreeSet<>(comparator);
            set.add(new Pair(1, 0));
            set1.add(new Pair(1, 0));
            for (int i = 2; i < n + 1; i++) {
                set.add(new Pair(i, Integer.MAX_VALUE));
                set1.add(new Pair(i, Integer.MAX_VALUE));
            }
            printTree(set);
            for (int i = 1; i < n + 1; i++) {
                int v = 0;
                //if ((v == 0 || d[j] < d[v]) && !used[j]) v = j;
                //for (int j = 1; j < n + 1; j++) {
                  //  if ((v == 0 || d[j] < d[v]) && !used[j]) v = j;
                //}
                Pair pair = set.pollFirst();
                printTree(set);
                if (pair == null) {
                    System.out.println("Еще хуже");
                    break;

                }
                else {
                    v = pair.num;
                    if (used[v]) {
                        System.out.println("Плохи дела");
                        break;
                    }
                }
                if (d[v] == Integer.MAX_VALUE) break;
                used[v] = true;
                for (int j = 1; j < n + 1; j++) {
                    if (matrix.get(toRebro(v, j)) != null) {
                        int rebro = matrix.get(toRebro(v, j));
                        if (d[v] + rebro < d[j]) {
                            int length = d[j];
                            d[j] = d[v] + rebro;
                            set.rm(new Pair(j, length));
                            set.add(new Pair(j, d[j]));
                            printTree(set);
                            System.out.println("next");
                        }
                    }
                }
            }
        for (int i = 1; i < n + 1; i++) {
            System.out.print(d[i] + " ");
        }

        }
        public static void printTree(BinaryTree tree){
            BinaryTree.Vertex st = tree.start;
            recur(st);
            System.out.println();
        }
        public static void recur(BinaryTree.Vertex next){
            if (next == null) return;
            System.out.print(next.num + "-" + next.length +  " ");
            recur(next.left);
            recur(next.right);
        }
}
