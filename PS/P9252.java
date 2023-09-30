package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P9252 {
    private static long[][] dp;
    private static ArrayList<Character> path;
    private static char[] a;
    private static char[] b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        dp = new long[a.length + 1][b.length + 1];
        path = new ArrayList<Character>();

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[a.length][b.length]);
        getText(a.length, b.length);

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i));
        }
        System.out.println();
    }

    private static void getText(int r, int c) {
        if (r == 0 || c == 0) {
            return;
        }

        if (a[r - 1] == b[c - 1]) {
            path.add(a[r - 1]);
            getText(r - 1, c - 1);
        } else {
            if (dp[r - 1][c] > dp[r][c - 1]) {
                getText(r - 1, c);
            } else {
                getText(r, c - 1);
            }
        }
    }
}
