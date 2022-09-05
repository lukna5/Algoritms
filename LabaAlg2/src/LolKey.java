import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class LolKey {
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
        Scanner sc = new Scanner(System.in);
        PrintWriter wr = new PrintWriter(System.out);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = sc.nextInt();
        int n = sc.nextInt();
        for (int i = 0; i < size; i++) {
            if (root == null) root = new Node(sc.nextInt(), null, null);
            else {
                root = insert(sc.nextInt(), i);
            }
        }
        for (int i = 0; i < n; i++) {
            String command = sc.next();
            if (command.equals("add")){
                size++;
                int k = sc.nextInt();
                root = insert(sc.nextInt(), k);
            }
            else {
                size--;
                root = remove(sc.nextInt() - 1);
            }
            //System.out.println(size);
            //printTree(root, wr);
        }
        reader.close();
        //wr.println(size);
        System.out.println(size);
        printTree(root, wr);
        //wr.close();
    }
    public static Node mTF(Node node, int l, int r){
        Node[] pair1 = split(node, l - 1);
        Node[] pair2 = split(pair1[1], r);
        Node t = merge(pair1[0], pair2[1]);
        return merge(pair2[0], t);
    }

    public static void printTree(Node root, PrintWriter wr){
        if (root == null) return;
        printTree(root.left, wr);
        System.out.print(root.cost + " ");
        printTree(root.right, wr);
    }
    public static Node remove(int k){
        Node[] pair = split(root, k);
        Node t1 = pair[0];
        pair = split(pair[1], 1);
        return merge(t1,pair[1]);
    }
    public static Node insert(int x, int k){
        //if (root == null) return new Node(x, null, null);
        Node[] pair = split(root, k);
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
            pair = split(v.right, k - getSize(v.left) - 1);
            v.right = pair[0];
            v.size = getSize(v.left) + getSize(v.right) + 1;
            pair[0] = v;
            return pair;
        }
    }
}