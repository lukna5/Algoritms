import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class MANE {
    public static void main(String[] args) throws FileNotFoundException {
        BigInteger a = new BigInteger("3");
        System.out.println(a.toString());
       /* Scanner sc = new Scanner(new File("out.txt"));
        BigInteger a = new BigInteger(sc.nextLine());
        BigInteger b = new BigInteger(sc.nextLine());
        BigInteger c = a.subtract(b);
        PrintWriter wr = new PrintWriter("out1.txt");
        wr.write(c.toString() + "\n");
        BigInteger d1 = a.mod(b);
        //wr.write(d1.toString());
        wr.close();
        //System.out.println(c);

        */
    }
}
