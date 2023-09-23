package PS;

import java.util.Scanner;

public class P1010 {
    public static void main(String[] args) {
        long[][] d = new long[31][31];
        int t, n, m;
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 31; i++) {
            d[i][1] = i;
            d[i][0] = 1;
            d[i][i] = 1;
        }

        for (int i = 2; i < 31; i++) {
            for (int j = 1; j < i; j++) {
                d[i][j] = d[i - 1][j] + d[i - 1][j - 1];
            }
        }

        t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            m = sc.nextInt();
            System.out.println(d[m][n]);
        }
    }
}
