package PS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1260 { // important problem!!!
    static boolean visited[];
    static ArrayList<Integer>[] A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // node
        int m = sc.nextInt(); // edge
        int start = sc.nextInt();
        A = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            A[s].add(e);
            A[e].add(s);
        }

        // sort the arraylist to visit the smaller numbers first
        for (int i = 1; i <= n; i++) {
            Collections.sort(A[i]);
        }

        visited = new boolean[n + 1];
        DFS(start);
        System.out.println();
        visited = new boolean[n + 1];
        BFS(start);
        System.out.println();
    }

    public static void DFS(int node) {
        System.out.print(node + " ");
        visited[node] = true;
        for (int i : A[node]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    public static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            System.out.print(now + " ");

            for (int i : A[now]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
