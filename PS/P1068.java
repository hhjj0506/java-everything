package PS;

import java.util.ArrayList;
import java.util.Scanner;

public class P1068 {
    static int ans = 0;
    static int deleteNode = 0;
    static boolean[] visited;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        tree = new ArrayList[n];
        visited = new boolean[n];
        int root = 0;

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n; i++) {
            int parent = sc.nextInt();
            if (parent != -1) { // save tree info
                tree[i].add(parent);
                tree[parent].add(i);
            } else { // if parent is given as -1, the node is root node
                root = i;
            }
        }

        deleteNode = sc.nextInt();

        if (deleteNode == root) {
            System.out.println(0);
        } else {
            DFS(root);
            System.out.println(ans);
        }
    }

    static void DFS(int num) {
        visited[num] = true;
        int childNode = 0;
        
        for (int i : tree[num]) {
            if (visited[i] == false && i != deleteNode) { // if node is deleted node, stop searching
                childNode++;
                DFS(i);
            }
        }

        if (childNode == 0) { // when current node does not have a child node, consider it a leaf node then increment answer
            ans++;
        }
    }
}
