package PS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1167 {
    static boolean visited[];
    static int[] distance;
    static ArrayList<Edge>[] A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        A = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<Edge>();
        }

        // save graph data in A
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();

            while (true) {
                int end = sc.nextInt();
                if (end == -1) {
                    break;
                }
                int v = sc.nextInt();
                A[start].add(new Edge(end, v));
            }
        }

        distance = new int[n + 1];
        visited = new boolean[n + 1];
        BFS(1);
        int max = 1;

        // set the starting point as the biggest value in the distance (the node with the biggest distance)
        for (int i = 2; i <= n; i++) {
            if (distance[max] < distance[i]) {
                max = i;
            }
        }

        distance = new int[n + 1];
        visited = new boolean[n + 1];
        BFS(max); // start searching from new node
        Arrays.sort(distance);
        System.out.println(distance[n]);
    }

    private static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        visited[index] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Edge i : A[now]) {
                int e = i.e;
                int val = i.val;
                if (!visited[e]) {
                    visited[e] = true;
                    queue.add(e);
                    distance[e] = distance[now] + val; // update distance value by adding current distance value
                }
            }
        }
    }

    static class Edge {
        int e;
        int val;

        public Edge(int e, int val) {
            this.e = e;
            this.val = val;
        }
    }
}
