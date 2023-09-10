package PS;

import java.util.PriorityQueue;
import java.util.Scanner;

public class P1197 {
    static int[] parent;
    static PriorityQueue<pEdge> queue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // # of nodes
        int m = sc.nextInt(); // # of edges
        queue = new PriorityQueue<>(); // use priority queue for automatic sorting
        parent = new int[n + 1];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int v = sc.nextInt();
            queue.add(new pEdge(s, e, v));
        }

        int useEdge = 0;
        int res = 0;

        while (useEdge < n - 1) {
            pEdge now = queue.poll();
            if (find(now.s) != find(now.e)) { // if parents are not same, cycle does not appear when connected
                union(now.s, now.e);
                res = res + now.v;
                useEdge++;
            }
        }

        System.out.println(res);
    }

    public static void union(int a, int b) { // connect representative nodes
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

class pEdge implements Comparable<pEdge> {
    int s, e, v;

    pEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    @Override
    public int compareTo(pEdge o) { // rewrite compareTo to sort asendingly by v value
        return this.v - o.v;
    }
}
