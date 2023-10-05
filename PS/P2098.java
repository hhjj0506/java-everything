package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2098 {
    private static final int INF = 1000000 * 16 + 1;
    private static int n;
    private static int[][] w;
    private static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine().trim());
        w = new int[16][16];
        d = new int[16][1 << 16];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1 << n; j++) {
                d[i][j] = INF;
            }
        }

        System.out.println(tsp(0, 1));
    }

    private static int tsp(int c, int v) { // get all possibilites by searching completely then make recursion
        if (v == (1 << n) - 1) { // when all nodes are visited
            return w[c][0] == 0 ? INF : w[c][0];
        }
        if (d[c][v] != INF) { // when node was already visited
            return d[c][v];
        }

        for (int i = 0; i < n; i++) {
            if ((v & (1 << i)) == 0 && w[c][i] != 0) { // when node is not visited and can be reached from current city
                d[c][v] = Math.min(d[c][v], tsp(i, (v | (1 << i))) + w[c][i]);
            }
        }

        return d[c][v];
    }
}
