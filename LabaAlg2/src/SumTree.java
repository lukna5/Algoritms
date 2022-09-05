import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class SumTree {
    public static class Node{
        int x;
        Node left;
        Node right;
        int h = 1;
        int sum;
        int mostLeft;
        int mostRight;
        public Node(int x, Node left, Node right) {
            this.x = x;
            this.left = left;
            this.right = right;
            mostLeft = x;
            mostRight = x;
            sum = x;
            //this.h = 0;
        }
    }

    private static Node rotateRight(Node node){
        Node y = node.left;
        node.left = y.right;
        y.right = node;
        fix(node);
        calc(node);
        fix(y);
        calc(y);
        return y;
    }

    private static Node balance(Node node){
        if (node == null) return null;
        fix(node);
        calc(node);
        if (Math.abs(getBalance(node)) <= 1) return node;
        if (getH(node.left) < getH(node.right)){
            if (getBalance(node.right) == 1) node.right = rotateRight(node.right);
            return rotateLeft(node);
        } else {
            if (getBalance(node.left) == -1) node.left = rotateLeft(node.left);
            Node res = rotateRight(node);
            calc(res);
            return res;
        }
    }

    private static Node rotateLeft(Node node){
        Node y = node.right;
        node.right = y.left;
        y.left = node;
        fix(node);
        calc(node);
        fix(y);
        calc(y);
        return y;
    }

    public static String[] next(BufferedReader input) throws IOException {
        StringBuilder builder = new StringBuilder();
        String next = input.readLine();
        if (next == null) return null;
        String res[];
        res = next.split(" ");
        return res;
    }
    public static int nextInt(BufferedReader input) throws IOException {
        StringBuilder builder = new StringBuilder();
        int next;
        while ((next = input.read()) <= 40){
        }
        builder.append(Character.toString(next));
        while ((next = input.read()) > 40){
            builder.append(Character.toString(next));
        }
        return Integer.parseInt(builder.toString());
    }

    private static void fix(Node node){
        node.h = Math.max(getH(node.left), getH(node.right)) + 1;
    }

    private static int getH(Node node){
        if (node == null) return 0;
        return node.h;
    }

    private static int getBalance(Node node){
        if (node == null) return 0;
        return getH(node.left) - getH(node.right);
    }

    public static void main(String[] args) throws IOException{
        Node root = null;
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);
        String command[] = new String[2];
        int res = -1;
        int n = input.nextInt();
        PrintWriter wr = new PrintWriter(System.out);
        for (int i = 0; i < n; i++) {
            command[0] = input.next();
            //command[1] = input.next();
            switch (command[0]) {
                case ("+"):

                        if (res == -1) root = insert(root, input.nextInt());
                        else root = insert(root, (input.nextInt() + res) % 1000000000);

                    res = -1;

                    //printTree(root);
                    break;
                case("?"):
                    res = sum(root, input.nextInt(), input.nextInt());
                    System.out.println(res);
                    break;

            }
            //printTree(root);
            //System.out.println();
        }
        input.close();
        wr.close();
    }
    public static int sum(Node root, int l, int r){
        calc(root);
        if (root == null) return 0;
        //System.out.println(l + "  " + r);
        if (l > root.mostRight || r < root.mostLeft) return 0;
        if (l <= root.mostLeft && r >= root.mostRight){
            //System.out.println(getSum(root) + " 777");
            return getSum(root);
        }
        int res = sum(root.left, l, r) + sum(root.right, l, r);
        if (l <= root.x) res += root.x;
        return res;

    }
    public static Node insert(Node node, int k){
        if (node == null) return new Node(k, null, null);
        if (node.x == k) return node;
        if (node.x > k) node.left = insert(node.left, k);
        else node.right = insert(node.right, k);
        return balance(node);
    }
    private static void calc(Node v){
        if (v != null){
            if (v.left != null) v.mostLeft = v.left.mostLeft;
            else v.mostLeft = v.x;
            if (v.right != null) v.mostRight = v.right.mostRight;
            else v.mostRight = v.x;
            v.sum = getSum(v.left) + getSum(v.right) + v.x;
        }
    }
    private static int getSum(Node v){
        if (v == null) return 0;
        return v.sum;
    }
    public static Node delete(Node node, int k){
        if (node == null) return null;
        if (k < node.x) node.left = delete(node.left, k);
        else if (k > node.x) node.right = delete(node.right, k);
        else if (node.left != null && node.right != null) {
            node.x = minimum(node.right).x;
            node.right = delete(node.right, node.x);
        }
        else{
            if (node.left != null) node = node.left;
            else if (node.right != null)  node = node.right;
            else node = null;
        }
        return balance(node);
    }

    public static Node minimum(Node node){
        if (node.left == null) return node;
        else return minimum(node.left);
    }

    public static boolean contain(Node node, int k){
        if (node == null) return false;
        if (k < node.x) return contain(node.left, k);
        else if (k > node.x) return contain(node.right, k);
        return true;
    }

    public static int next(Node node, int ans, int k){
        if (node == null) return ans;
        if (node.x <= k) return next(node.right, ans, k);
        return next(node.left, node.x, k);
    }

    public static int prev(Node node, int ans, int k){
        if (node == null) return ans;
        if (node.x >= k) return prev(node.left, ans, k);
        return prev(node.right, node.x, k);
    }

    private static void printTree(Node node){
        if (node == null) return;
        if (node.left != null) printTree(node.left);
        System.out.print(" | " + node.x + " " + node.mostLeft + " " + node.mostRight + " " + node.sum + " | ");
        if (node.right != null) printTree(node.right);
    }
}
