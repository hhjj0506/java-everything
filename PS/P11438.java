package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P11438 {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int kmax;
    static int[][] parent;
    static boolean[] visited;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        tree = new ArrayList[n + 1];
        depth = new int[n + 1];
        visited = new boolean[n + 1];
        kmax = 0;
        int temp = 1;

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }

        while (temp <= n) { // get the deepest possible depth
            temp <<= 1;
            kmax++;
        }

        parent = new int[kmax + 1][n + 1];
        BFS(1);
        for (int i = 1; i <= kmax; i++) {
            for (int j = 1; j <= n; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lca = findLca(a, b);
            System.out.println(lca);
        }
    }

    static int findLca(int a, int b) {
        if (depth[a] > depth[b]) { // make b the deeper depth
            int temp = a;
            a = b;
            b = temp;
        }
        for (int k = kmax; k >= 0; k--) { // synchronize two nodes' depth (faster version)
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[parent[k][b]]) {
                    b = parent[k][b];
                }
            }
        }
        for (int k = kmax; k >= 0 && a != b; k--) { // move to each node's parent until they meet (faster version)
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        int lca = a;
        if (a != b) {
            lca = parent[0][lca];
        }

        return lca;
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
                    parent[0][next] = now; // save parent node
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
