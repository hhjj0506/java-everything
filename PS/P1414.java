package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1414 {
    static int[] parent;
    static int n, sum;
    static PriorityQueue<lEdge> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        queue = new PriorityQueue<>();
        parent = new int[n];
        int useEdge = 0;
        int res = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char[] charTemp = st.nextToken().toCharArray(); // parse string into char array

            for (int j = 0; j < n; j++) { // calculate length of each lan cable according to alphabet
                int temp = 0;
                if (charTemp[j] >= 'a' && charTemp[j] <= 'z') {
                    temp = charTemp[j] - 'a' + 1;
                } else if (charTemp[j] >= 'A' && charTemp[j] <= 'Z') {
                    temp = charTemp[j] - 'A' + 27;
                }

                sum += temp; // save total length of lan cables
                if (i != j && temp != 0) {
                    queue.add(new lEdge(i, j, temp));
                }
            }
        }

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        while (!queue.isEmpty()) {
            lEdge now = queue.poll();
            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                res += now.v;
                useEdge++;
            }
        }

        if (useEdge == n - 1) {
            System.out.println(sum - res);
        } else {
            System.out.println(-1);
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }
    
    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
}

class lEdge implements Comparable<lEdge> {
    int s, e, v;

    lEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    @Override
    public int compareTo(lEdge o) {
        return this.v - o.v;
    }
}
