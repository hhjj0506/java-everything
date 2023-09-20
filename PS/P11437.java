package PS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P11437 {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of nodes
        tree = new ArrayList[n + 1];
        depth = new int[n + 1];
        parent = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n - 1; i++) { // save graph data
            int s = sc.nextInt();
            int e = sc.nextInt();
            tree[s].add(e);
            tree[e].add(s);
        }

        BFS(1); // get depth by BFS
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(); // two nodes to get lca
            int b = sc.nextInt();
            int lca = findLca(a, b);
            System.out.println(lca);
        }
    }

    static int findLca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (depth[a] != depth[b]) { // synchronize two nodes' depth
            a = parent[a];
        }
        while (a != b) { // move to each node's parent until they meet
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        visited[node] = true;
        int level = 1;
        int size = 1;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : tree[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    parent[next] = now; // save parent node
                    depth[next] = level; // save depth
                }
            }
            cnt++;
            if (cnt == size) {
                cnt = 0;
                size = queue.size();
                level++;
            }
        }
    }
}
