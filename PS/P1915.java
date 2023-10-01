package PS;

import java.util.Scanner;

public class P1915 {
    public static void main(String[] args) {
        long[][] d = new long[1001][1001];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long max = 0;

        for (int i = 0; i < n; i++) {
            String mline = sc.next();
            for (int j = 0; j < m; j++) {
                d[i][j] = Long.parseLong(String.valueOf(mline.charAt(j)));
                if (d[i][j] == 1 && j > 0 && i > 0) {
                    d[i][j] = Math.min(d[i - 1][j - 1], Math.min(d[i - 1][j], d[i][j - 1])) + d[i][j];
                }
                if (max < d[i][j]) {
                    max = d[i][j];
                }
            }
        }

        System.out.println(max * max);
    }
}
