package PS;

import java.util.ArrayList;
import java.util.Scanner;

public class P11725 {
    static int n;
    static boolean[] visited;
    static ArrayList<Integer>[] tree;
    static int[] ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n + 1];
        tree = new ArrayList[n + 1];
        ans = new int[n + 1];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        DFS(1); // DFS from the root node
        for (int i = 2; i <= n; i++) {
            System.out.println(ans[i]);
        }
    }

    static void DFS(int num) {
        visited[num] = true;
        for (int i : tree[num]) {
            if (!visited[i]) {
                ans[i] = num; // save parent nodes
                DFS(i);
            }
        }
    }
}
