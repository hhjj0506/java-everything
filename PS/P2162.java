package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2162 {
    static int parent[] = new int[3001];
    static int l[][] = new int[3001][4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int ans = 0, res = 0;

        for (int i = 1; i <= n; i++) {
            parent[i] = -1;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            l[i][0] = Integer.parseInt(st.nextToken());
            l[i][1] = Integer.parseInt(st.nextToken());
            l[i][2] = Integer.parseInt(st.nextToken());
            l[i][3] = Integer.parseInt(st.nextToken());

            for (int j = 1; j < i; j++) {
                if (isCross(l[i][0], l[i][1], l[i][2], l[i][3], l[j][0], l[j][1], l[j][2], l[j][3]) == true) {
                    union(i, j);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (parent[i] < 0) {
                ans++;
                res = Math.min(res, parent[i]);
            }
        }

        System.out.println(ans);
        System.out.println(-res);
    }

    private static int find(int i) {
        if (parent[i] < 0) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    private static void union(int i, int j) {
        int p = find(i);
        int q = find(j);
        if (p == q) {
            return;
        }
        parent[p] += parent[q];
        parent[q] = p;
    }

    static int CCW(long x1, long y1, long x2, long y2, long x3, long y3) {
        long temp = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
        if (temp > 0) {
            return 1;
        } else if (temp < 0) {
            return -1;
        }
        return 0;
    }

    private static boolean isOverlab(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2)
        && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2)) {
            return true;
        }

        return false;
    }

    private static boolean isCross(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        int abc = CCW(x1, y1, x2, y2, x3, y3);
        int abd = CCW(x1, y1, x2, y2, x4, y4);
        int cda = CCW(x3, y3, x4, y4, x1, y1);
        int cdb = CCW(x3, y3, x4, y4, x2, y2);

        if (abc * abd == 0 && cda * cdb == 0) { // if line segment is straight
            return isOverlab(x1, y1, x2, y2, x3, y3, x4, y4); // check if it overlabs
        } else if (abc * abd <= 0 && cda * cdb <= 0) { // if line segment overlabs
            return true;
        }

        return false;
    }
}
