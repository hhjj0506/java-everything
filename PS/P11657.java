package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11657 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        long[] distance;
        cEdge[] edges;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new cEdge[m + 1];
        distance = new long[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            edges[i] = new cEdge(s, e, t);
        }

        distance[1] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cEdge edge = edges[j];
                // update distance value if there is faster route
                if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.time) {
                    distance[edge.end] = distance[edge.start] + edge.time;
                }
            }
        }

        boolean isNegativeCycle = false;

        // check if there is negative number cycle by moving throgh graph after it has found shortest routes
        for (int i = 0; i < m; i++) {
            cEdge edge = edges[i];
            if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.time) {
                isNegativeCycle = true;
            }
        }

        if (!isNegativeCycle) { // if there is no negative cycle
            for (int i = 2; i <= n; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(distance[i]);
                }
            }
        } else { // if negative cycle exists
            System.out.println("-1");
        }
    }
}

class cEdge {
    int start, end, time;

    public cEdge(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}
