import java.util.Scanner;

public class SimpleTree {
    public static class Node{
        int x;
        Node left;
        Node right;

        public Node(int x, Node left, Node right) {
            this.x = x;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        Node root = null;
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String command = input.next();
            int res;
            switch (command) {
                case ("insert"):
                    if (root == null) root = new Node(input.nextInt(), null, null);
                    else insert(root, input.nextInt());
                    //printTree(root);
                    break;
                case("delete"):
                    root = delete(root, input.nextInt());
                    break;
                case ("exists"):
                    System.out.println(contain(root, input.nextInt()));
                    break;
                case ("next"):
                    res = next(root, Integer.MIN_VALUE, input.nextInt());
                    if (res == Integer.MIN_VALUE) System.out.println("none");
                    else System.out.println(res);
                    break;
                case ("prev"):
                    res = prev(root, Integer.MIN_VALUE, input.nextInt());
                    if (res == Integer.MIN_VALUE) System.out.println("none");
                    else System.out.println(res);
                    break;
            }
            //printTree(root);
            //System.out.println();
        }
    }

    public static Node insert(Node node, int k){
        if (node == null) return new Node(k, null, null);
        if (node.x == k) return node;
        if (node.x > k) node.left = insert(node.left, k);
        else node.right = insert(node.right, k);
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
        System.out.print(node.x + " | ");
        if (node.left != null) printTree(node.left);
        if (node.right != null) printTree(node.right);
    }
}
