package PS;

import java.util.Scanner;

public class P1991 {
    static int[][] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        tree = new int[26][2]; //[][0] = parent node, [][1] = left child, [][2] = right child

        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split(" ");
            int node = temp[0].charAt(0) - 'A'; // convert to index int by subtracting A
            char left = temp[1].charAt(0);
            char right = temp[2].charAt(0);

            // save -1 when child node don't exist
            if (left == '.') {
                tree[node][0] = -1;
            } else {
                tree[node][0] = left - 'A';
            }
            if (right == '.') {
                tree[node][1] = -1;
            } else {
                tree[node][1] = right - 'A';
            }
        }

        preorder(0);
        System.out.println();
        inorder(0);
        System.out.println();
        postorder(0);
        System.out.println();
    }

    public static void preorder(int now) {
        if (now == -1) {
            return;
        }
        System.out.print((char)(now + 'A')); // current node
        preorder(tree[now][0]); // left
        preorder(tree[now][1]); // right
    }

    public static void inorder(int now) {
        if (now == -1) {
            return;
        }
        inorder(tree[now][0]); // left
        System.out.print((char)(now + 'A')); // current node
        inorder(tree[now][1]); // right
    }

    public static void postorder(int now) {
        if (now == -1) {
            return;
        }
        postorder(tree[now][0]); // left
        postorder(tree[now][1]); // right
        System.out.print((char)(now + 'A')); // current node
    }
}
