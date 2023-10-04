package PS;

import java.util.Scanner;

public class P11049 {
    static int n;
    static Matrix[] m;
    static int[][] d;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = new Matrix[n + 1];
        d = new int[n + 1][n + 1];

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                d[i][j] = -1;
            }
        }

        for (int i = 1; i <= n; i++) {
            int y = sc.nextInt();
            int x = sc.nextInt();
            m[i] = new Matrix(y, x);
        }

        System.out.println(execute(1, n));
    }

    // top-down approach to get formula for DP
    static int execute(int s, int e) {
        int res = Integer.MAX_VALUE;
        if (d[s][e] != -1) { // if the part is already executed, don't execute again
            return d[s][e];
        }

        if (s == e) { // 1 matrix
            return 0;
        }
        if (s + 1 == e) { // 2 matrixes
            return m[s].y * m[s].x * m[e].x;
        } 

        for (int i = s; i < e; i++) { // more than 3 matrixes == make formula
            res = Math.min(res, m[s].y * m[i].x * m[e].x + execute(s, i) + execute(i + 1, e));
        }
        d[s][e] = res;

        return d[s][e];
    }

    static class Matrix {
        private int y;
        private int x;

        Matrix(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
