import java.util.Scanner;

public class Kkkk {
    public static class Node{
        int x;
        Node left;
        Node right;
        int size;
        public Node(int x, Node left, Node right) {
            this.x = x;
            this.left = left;
            this.right = right;
            this.size = getSize(left) + getSize(right) + 1;
        }
    }

    private static int getSize(Node v){
        if (v == null) return 0;
        return v.size;
    }
    public static void main(String[] args) {
        Node root = null;
        Scanner input = new Scanner(System.in);
        int size = 0;
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            String command = input.next();
            int res;
            switch (command) {
                case ("+1"):
                    size++;
                    if (root == null) root = new Node(input.nextInt(), null, null);
                    else insert(root, input.nextInt());
                    //printTree(root);
                    break;
                case("-1"):
                    size--;
                    root = delete(root, input.nextInt());
                    break;
                case ("0"):
                    System.out.println(findK(root, size - input.nextInt()));
            }
            //printTree(root);
            //System.out.println();
            //printTree(root);
            //System.out.println();
        }
    }

    public static int findK(Node node, int k){
        node.size = getSize(node.left) + getSize(node.right) + 1;
        if (k == getSize(node.left)) return node.x;
        //System.out.println(k + " k");
        //if (node == null) return 0;
        //if (k == 0) return node.x;
        if (k < getSize(node.left)) return findK(node.left, k);
        return findK(node.right, getSize(node.right) - k + 1);
    }
    public static Node insert(Node node, int k){
        if (node == null) return new Node(k, null, null);
        if (node.x == k) return node;
        if (node.x > k){
            node.left = insert(node.left, k);
        }
        else node.right = insert(node.right, k);
        node.size = getSize(node.left) + getSize(node.right) + 1;
        return node;
    }

    public static Node delete(Node node, int k){
        if (node == null) return null;
        if (k < node.x) node.left = delete(node.left, k);
        else if (k > node.x) node.right = delete(node.right, k);
        else if (node.left != null && node.right != null) {
            node.x = minimum(node.right).x;
            node.right = delete(node.right, node.x);
        }
        else if (node.left != null) node = node.left;
        else if (node.right != null)  node = node.right;
        else{
            //System.out.println(node.x + " node");
            node = null;

        }
        if (node != null) node.size = getSize(node.left) + getSize(node.right) + 1;

        return node;
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
        if (node.left != null) printTree(node.left);
        System.out.print(node.x + " | ");

        if (node.right != null) printTree(node.right);
    }
}
