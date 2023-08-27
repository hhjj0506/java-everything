package PS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class P18352 {
    static int visited[];
    static ArrayList<Integer>[] A; // adjacency list
    static int n, m, k, x;
    static List<Integer> ans;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        x = sc.nextInt();
        A = new ArrayList[n + 1];
        ans = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) { // save graph data
            int start = sc.nextInt();
            int end = sc.nextInt();
            A[start].add(end);
        }

        visited = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            visited[i] = -1;
        }
        BFS(x); // execute BFS
        for (int i = 0; i <= n; i++) { // if distance is K, add the node to answer
            if (visited[i] == k) {
                ans.add(i);
            }
        }

        if (ans.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(ans);
            for (int temp : ans) {
                System.out.println(temp);
            }
        }
    }

    private static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        visited[node]++;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i : A[now]) {
                if (visited[i] == -1) {
                    visited[i] = visited[now] + 1;
                    queue.add(i);
                }
            }
        }
    }
}
