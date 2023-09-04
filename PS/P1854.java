package PS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1854 {
    public static void main(String[] args) throws IOException {
        int n, m, k;
        int[][] A = new int[1001][1001];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer>[] distQueue = new PriorityQueue[n + 1];
        Comparator<Integer> cp = new Comparator<Integer>() { // set ascending sort as default
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : -1;
            }
        };

        for (int i = 0; i <= n; i++) {
            distQueue[i] = new PriorityQueue<Integer>(k, cp);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            A[a][b] = c;
        }

        PriorityQueue<ccNode> pq = new PriorityQueue<>();
        pq.add(new ccNode(1, 0));
        distQueue[1].add(0);

        while (!pq.isEmpty()) {
            ccNode u = pq.poll();
            for (int adjNode = 1; adjNode <= n; adjNode++) {
                if (A[u.node][adjNode] != 0) { // search all connected nodes
                    if (distQueue[adjNode].size() < k) { // if saved routes are less than k, just save to fill the priority queue
                        distQueue[adjNode].add(u.cost + A[u.node][adjNode]);
                        pq.add(new ccNode(adjNode, u.cost + A[u.node][adjNode]));
                    } 
                    // if there are k routes saved and current route + cost is smaller than the biggest cost in the queue, remove the biggest val in queue and save current route + cost
                    else if (distQueue[adjNode].peek() > u.cost + A[u.node][adjNode]) {
                        distQueue[adjNode].poll();
                        distQueue[adjNode].add(u.cost + A[u.node][adjNode]);
                        pq.add(new ccNode(adjNode, u.cost + A[u.node][adjNode]));
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) { // print out kth routes
            if (distQueue[i].size() == k) {
                bw.write(distQueue[i].peek() + "\n");
            } else {
                bw.write(-1 + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

class ccNode implements Comparable<ccNode> {
    int node;
    int cost;

    ccNode(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(ccNode o) {
        return this.cost < o.cost ? 1 : -1;
    }
}