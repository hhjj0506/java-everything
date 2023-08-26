package PS;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class P1033 {
    static ArrayList<Node>[] A;
    static long lcm;
    static boolean[] visited;
    static long[] D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = sc.nextInt();
        A = new ArrayList[n];
        visited = new boolean[n];
        D = new long[n];
        lcm = 1;

        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();

            A[a].add(new Node(b, p, q));
            A[b].add(new Node(a, q, p));
            lcm *= (p * q / gcd(p, q));
        }

        D[0] = lcm;
        DFS(0);
        long mgcd = D[0];

        for (int i = 1; i < n; i++) {
            mgcd = gcd(mgcd, D[i]);
        }

        for (int i = 0; i < n; i++) {
            System.out.print(D[i] / mgcd + " ");
        }
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static void DFS(int node) {
        visited[node] = true;

        for (Node i : A[node]) {
            int next = i.getB();
            if (!visited[next]) {
                D[next] = D[node] * i.getQ() / i.getP();
                DFS(next);
            }
        }
    }
}

class Node {
    int b;
    int p;
    int q;

    public Node(int b, int p, int q) {
        super();
        this.b = b;
        this.p = p;
        this.q = q;
    }
    public int getB() {
        return b;
    }
    public int getP() {
        return p;
    }
    public int getQ() {
        return q;
    }
}
