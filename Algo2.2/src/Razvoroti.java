import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Razvoroti {
    private static class Node {
        int cost;
        int y;
        Node left;
        Node right;
        int size;
        boolean rev;
        public Node(int cost, Node left, Node right) {
            this.cost = cost;
            this.y = (int) (Math.random() * Integer.MAX_VALUE);
            this.left = left;
            this.right = right;
            this.size = getSize(left) + getSize(right) + 1;
            this.rev = false;
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
    public static void main(String[] args) throws IOException {
        Node root = null;
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        PrintWriter wr = new PrintWriter(System.out);
        int operations = sc.nextInt();
        for (int i = 0; i < size; i++) {
            root = insert(root, i + 1, i);
        }
        otherPrintTree(root);
        //printTree(root, wr);
        //System.out.println();
        for (int i = 0; i < operations; i++) {
            root = reverse(root, sc.nextInt(), sc.nextInt());
            printTree(root);
            System.out.println();
        }
        printTree(root);
        wr.close();
    }
    public static Node reverse(Node node, int l, int r){
        //System.out.println(l + " " + r);
        Node[] pair1 = split(node, l - 1);
        Node[] pair2 = split(pair1[1], r - l + 1);
        System.out.println("first");
        pair2[0].rev ^= true;
        Node t = merge(pair2[0], pair2[1]);
        //printTree(t);
        System.out.println();
        return merge(pair1[0], t);
    }

    public static Node mTF(Node node, int l, int r){
        //System.out.println(l + " " + r);
        Node[] pair1 = split(node, l - 1);
        Node[] pair2 = split(pair1[1], r - l + 1);
        Node t = merge(pair1[0], pair2[1]);
        return merge(pair2[0], t);
    }

    private static void push(Node v){
        if (v != null){
            if (v.rev){
                v.rev = false;
                Node t = v.left;
                v.left = v.right;
                v.right = t;
                if (v.left != null) v.left.rev ^= true;
                if (v.right != null) v.right.rev ^= true;
            }
        }
    }


    public static void otherPrintTree(Node root){
        if (root == null) return;
        System.out.print(root.cost + " ");
        printTree(root.left);
        printTree(root.right);
    }
    public static void printTree(Node root){
        if (root == null) return;
        push(root);
        printTree(root.left);
        System.out.print(root.cost + " ");
        printTree(root.right);
    }
    public static Node insert(Node root, int x, int k){
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
            //push(first);
            return first;
        } else {
            second.left = merge(first, second.left);
            second.size = getSize(second.left) + getSize(second.right) + 1;
            //push(second);
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
            push(v);
            return pair;
        } else {
            pair = split(v.right, k - getSize(v.left) - 1);
            v.right = pair[0];
            v.size = getSize(v.left) + getSize(v.right) + 1;
            pair[0] = v;
            push(v);
            return pair;
        }
    }
}