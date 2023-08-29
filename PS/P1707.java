package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P1707 {
    static ArrayList<Integer>[] A;
    static int[] check;
    static boolean[] visited;
    static boolean isEven;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            String[] s = bf.readLine().split(" ");
            int v = Integer.parseInt(s[0]);
            int e = Integer.parseInt(s[1]);
            A = new ArrayList[v + 1];
            visited = new boolean[v + 1];
            check = new int[v + 1];
            isEven = true;

            for (int j = 1; j <= v; j++) {
                A[j] = new ArrayList<Integer>();
            }

            for (int j = 0; j < e; j++) {
                s = bf.readLine().split(" ");
                int start = Integer.parseInt(s[0]);
                int end = Integer.parseInt(s[1]);
                A[start].add(end);
                A[end].add(start);
            }

            // repeat DFS for each node and check if the graph is bipartial (graph can be split into two groups that are not connected)
            for (int j =  1; j <= v; j++) {
                if (isEven) {
                    DFS(j);
                } else {
                    break;
                }
            }

            check[1] = 0;
            if (isEven) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void DFS(int node) {
        visited[node] = true;

        for (int i : A[node]) {
            if (!visited[i]) {
                // put nearby node into different group than previous node
                check[i] = (check[node] + 1) % 2;
                DFS(i);
            } else if (check[node] == check[i]) { // already visited node is in same group as current node, graph is not bipartial
                isEven = false;
            }
        }
    }
}
