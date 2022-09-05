    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Scanner;

    public class First {
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
        static int[][] matrix;
        static int[][][] d;
        static int[][] d1;


        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            int n = Reader.readInt();
            matrix = new int[n + 1][n + 1];
            //d = new int[n + 1][n + 1][n + 1];
            d1 = new int[n + 1][n + 1];

            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    for (int k = 0; k <= n; k++) {
                        //d[i][j][k] = 2147483400;
                        d1[j][k] = 2147483400;
                    }
                }
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    matrix[i][j] = Reader.readInt();
                    //d[0][i][j] = matrix[i][j];
                    if (i == j) d1[i][j] = 0;
                    else d1[i][j] = matrix[i][j];
                }
            }

            for (int k = 1; k < n + 1; k++) {
                for (int i = 1; i < n + 1; i++) {
                    for (int j = 1; j < n + 1; j++) {
                        //d[i][j][k] = Math.min(d[i - 1][j][k], d[i - 1][j][i] + d[i-1][i][k]);
                        d1[i][j] = Math.min(d1[i][j], d1[i][k] + d1[k][j]);
                    }
                }
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    /*
                    int min = 2147483400;
                    for (int k = 1; k <= n; k   ++) {
                        min = Math.min(min, d[k][i][j]);
                    }
                    System.out.print(min + " ");

                     */
                    System.out.print(d1[i][j] + " ");
                }
                System.out.println();
            }
            Reader.close();
        }
    }
