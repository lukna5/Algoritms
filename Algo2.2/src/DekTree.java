import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class DekTree {
    private static class Node {
        int cost;
        int y;
        Node left;
        Node right;
        int size;

        public Node(int cost, Node left, Node right) {
            this.cost = cost;
            this.y = (int) (Math.random() * Integer.MAX_VALUE);
            this.left = left;
            this.right = right;
            this.size = getSize(left) + getSize(right) + 1;
        }
    }

    private static int getSize(Node node) {
        if (node == null) return 0;
        return node.size;
    }
    public static String next(BufferedReader input) throws IOException{
        StringBuilder builder = new StringBuilder();
        int next;
        while ((next = input.read()) <= 40){
        }
        builder.append(Character.toString(next));
        while ((next = input.read()) > 40){
            builder.append(Character.toString(next));
        }
        return builder.toString();
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

    private static Node root = null;
    public static void main(String[] args) throws IOException{
        Node root = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String command[] = new String[2];
        PrintWriter wr = new PrintWriter(System.out);
        //while ((command = next(input)) != null){
            int res;
            switch ("delete") {
                case ("insert"):
                    if (root == null) root = new Node(Integer.parseInt(command[1]), null, null);
                    else insert(root, Integer.parseInt(command[1]));
                    //printTree(root);
                    break;
                case("delete"):
                    root = remove(root, Integer.parseInt(command[1]));
                    break;
                case ("exists"):
                    wr.println(contain(root, Integer.parseInt(command[1])));
                    break;
                case ("next"):
                    res = next(root, Integer.MIN_VALUE, Integer.parseInt(command[1]));
                    if (res == Integer.MIN_VALUE) wr.println("none");
                    else wr.println(res);
                    break;
                case ("prev"):
                    res = prev(root, Integer.MIN_VALUE, Integer.parseInt(command[1]));
                    if (res == Integer.MIN_VALUE) wr.println("none");
                    else wr.println(res);
                    break;
            }
            //printTree(root);
            //System.out.println();
        //}
        input.close();
        wr.close();
    }
    public static boolean contain(Node node, int k){
        if (node == null) return false;
        if (k < node.cost) return contain(node.left, k);
        else if (k > node.cost) return contain(node.right, k);
        return true;
    }

    public static int next(Node node, int ans, int k){
        if (node == null) return ans;
        if (node.cost <= k) return next(node.right, ans, k);
        return next(node.left, node.cost, k);
    }

    public static int prev(Node node, int ans, int k){
        if (node == null) return ans;
        if (node.cost >= k) return prev(node.left, ans, k);
        return prev(node.right, node.cost, k);
    }
    public static void printTree(Node root, PrintWriter wr){
        if (root == null) return;
        printTree(root.left, wr);
        System.out.print(root.cost + " ");
        printTree(root.right, wr);
    }
    public static Node remove(Node root ,int k){
        Node[] pair = split(root, k);
        Node t1 = pair[0];
        pair = split(pair[1], 1);
        return merge(t1,pair[1]);
    }
    public static Node insert(Node root, int x){
        //if (root == null) return new Node(x, null, null);
        Node[] pair = split(root, x);
        Node t1 = pair[0];
        Node t2 = pair[1];
        t1 = merge(t1, new Node(x, null, null));
        return merge(t1, t2);
    }
    private static Node merge(Node first, Node second) {
        if (first == null) return second;
        if (second == null) return first;
        if (first.y > second.y) {
            first.right = merge(first.right, second);
            first.size = getSize(first.left) + getSize(first.right) + 1;
            return first;
        } else {
            second.left = merge(first, second.left);
            second.size = getSize(second.left) + getSize(second.right) + 1;
            return second;
        }
    }

    public static Node[] split(Node v, int k) {
        Node[] pair = new Node[2];
        pair[0] = null;
        pair[1] = null;
        if (v == null) return pair;
        if (getSize(v.left) >= k) {
            pair = split(v.left, k);
            v.left = pair[1];
            v.size = getSize(v.left) + getSize(v.right) + 1;
            pair[1] = v;
            return pair;
        } else {
            pair = split(v.right, k);
            v.right = pair[0];
            v.size = getSize(v.left) + getSize(v.right) + 1;
            pair[0] = v;
            return pair;
        }
    }
}