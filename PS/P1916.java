package PS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class P1916 {
    public static int n, m;
    public static ArrayList<cNode>[] A; // adjacency list to save graph val
    public static int[] distance; // distance between the cities
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());
        A = new ArrayList[n + 1];
        distance = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE); // fill distance array with max values

        for (int i = 0; i <= n; i++) {
            A[i] = new ArrayList<cNode>();
        }

        for (int i = 0; i < m; i++) { // put graph's edges into adjacency list
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            A[start].add(new cNode(end, weight));
        }

        st = new StringTokenizer(bf.readLine());
        int startIndex = Integer.parseInt(st.nextToken());
        int endIndex = Integer.parseInt(st.nextToken());
        bw.write(dijkstra(startIndex, endIndex) + "\n");
        bw.flush();
        bw.close();
        bf.close();
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<cNode> pq = new PriorityQueue<>();
        pq.offer(new cNode(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            cNode curCity = pq.poll();
            int now = curCity.target;
            if (!visited[now]) {
                visited[now] = true;
                for (cNode n : A[now]) { // update value if current node's distance + val is smaller than target node's distance
                    if (!visited[n.target] && distance[n.target] > distance[now] + n.val) {
                        distance[n.target] = distance[now] + n.val;
                        pq.add(new cNode(n.target, distance[n.target]));
                    }
                }
            }
        }

        return distance[end];
    }
}

class cNode implements Comparable<cNode> {
    int target;
    int val;

    cNode (int target, int val) {
        this.target = target;
        this.val = val;
    }

    @Override
    public int compareTo(cNode o) {
        return val = o.val;
    }
}