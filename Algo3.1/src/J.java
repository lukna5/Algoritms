    import java.io.*;
    import java.util.*;

    public class J {
        private static class Reader {

            static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            public static String readLine() throws IOException {
                return reader.readLine();
            }

            public static String readSymb() throws IOException {
                return Character.toString(reader.read());
            }

            public static int readInt() throws IOException { // readlong
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

            public static long readLong() throws IOException { // readlong
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
                    return Long.parseLong(builder.toString());
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
        public static class Vertex{
            long v;
            Vertex father = null;
            long fatherNum;
            ArrayList<Vertex> sons = new ArrayList<>();
            int color;

            public Vertex(long v) {
                this.v = v;
                fatherNum = v;
            }
        }
        public static class Rebro {
            int v;
            int u;
            int w;

            public Rebro(int v, int u, int w) {
                this.v = v;
                this.u = u;
                this.w = w;
            }

        }
        public static int gets(int v){
            if (fathers[v] != v){
                fathers[v] = gets(fathers[v]);
            }
            return fathers[v];
        }
        public static void unite(int v, int u){
            int v1 = gets(v);
            int u1 = gets(u);
            if (v1 != u1){
                fathers[v1] = u1;
            }
        }
        public static ArrayList<Rebro> graph = new ArrayList<>();
        static int[] fathers;
        static int[] sizes;

        public static void main(String[] args) throws IOException {
            long res = 0;
            int n = Reader.readInt();
            int m = Reader.readInt();
            sizes = new int[n + 1];
            fathers = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                fathers[i] = i;
                sizes[i] = 0;
            }
            for (int i = 0; i < m; i++) {
                graph.add(new Rebro((Reader.readInt() - 1), (Reader.readInt() - 1), Reader.readInt()));
            }
            Collections.sort(graph, new Comparator<Rebro>() {
                @Override
                public int compare(Rebro o1, Rebro o2) {
                    if (o1.w == o2.w) return 0;
                    if (o1.w < o2.w) return -1;
                    return 1;
                }
            });
            for (int i = 0; i < graph.size(); i++) {
                Rebro next = graph.get(i);
                int v = next.v;
                int u = next.u;
                int w = next.w;

                if (gets(v) != gets(u)){
                    unite(v, u);
                    res += w;
                }
                //System.out.println();
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            writer.write(Long.toString(res));
            writer.close();
        }
    }
