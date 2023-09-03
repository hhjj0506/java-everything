package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1753 {
    public static int v, e, k;
    public static int[] distance;
    public static boolean[] visited;
    public static ArrayList<Edge>[] list;
    public static PriorityQueue<Edge> q = new PriorityQueue<Edge>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(bf.readLine());
        distance = new int[v + 1];
        visited = new boolean[v + 1];
        list = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {
            list[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i <= v; i++) { // set distance as max int to indicate no route
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Edge(v, w));
        }

        q.add(new Edge(k, 0)); // set k as starting point
        distance[k] = 0;

        while (!q.isEmpty()) {
            Edge current = q.poll();
            int cv = current.vertex;
            if (visited[cv]) { // already visited node is not going into queue
                continue;
            }
            visited[cv] = true;

            for (int i = 0; i < list[cv].size(); i++) {
                Edge temp = list[cv].get(i);
                int next = temp.vertex;
                int val = temp.val;
                if (distance[next] > distance[cv] + val) { // update the distance as shortest
                    distance[next] = val + distance[cv];
                    q.add(new Edge(next, distance[next]));
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            if (visited[i]) {
                System.out.println(distance[i]);
            } else {
                System.out.println("INF");
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int vertex, val;

    Edge(int vertex, int val) {
        this.vertex = vertex;
        this.val = val;
    }

    public int compareTo(Edge e) {
        if (this.val > e.val) {
            return 1;
        } else {
            return -1;
        }
    }
}